<?php
include 'conexao.php';

// Obter o ID da tarefa da URL
$tarefaId = isset($_GET['id']) ? (int) $_GET['id'] : 0;

if ($tarefaId > 0) {
    // Atualizar o status da tarefa para "concluída"
    $stmt = $conn->prepare("UPDATE tarefas SET concluida = 1 WHERE id = ?");
    $stmt->bind_param("i", $tarefaId);

    if ($stmt->execute()) {
        // Redirecionar de volta para a página de tarefas com mensagem de sucesso
        header("Location: tarefaPessoa.php?id=" . $_GET['membroId'] . "&success=Tarefa marcada como concluída!");
        exit();
    } else {
        // Exibir mensagem de erro
        echo "Erro ao atualizar a tarefa: " . $conn->error;
    }
    $stmt->close();
} else {
    echo "ID de tarefa inválido!";
}

$conn->close();
?>
