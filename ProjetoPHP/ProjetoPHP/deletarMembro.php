<?php
include 'conexao.php';

if (isset($_GET['id'])) {
    $membroId = $_GET['id'];

    // Verificar se o membro possui tarefas
    $stmt = $conn->prepare("SELECT COUNT(*) AS total FROM tarefas WHERE dono_id = ?");
    $stmt->bind_param("i", $membroId);
    $stmt->execute();
    $result = $stmt->get_result()->fetch_assoc();

    if ($result['total'] > 0) {
        echo "<p>Não é possível deletar o membro enquanto ele tiver tarefas atribuídas.</p>";
        echo '<a href="equipe.php">Voltar</a>';
    } else {
        $stmt = $conn->prepare("DELETE FROM equipe WHERE id = ?");
        $stmt->bind_param("i", $membroId);

        if ($stmt->execute()) {
            header("Location: equipe.php?success=deletado");
        } else {
            echo "<p>Erro ao deletar o membro: " . $conn->error . "</p>";
        }
    }

    $stmt->close();
}
$conn->close();
?>
