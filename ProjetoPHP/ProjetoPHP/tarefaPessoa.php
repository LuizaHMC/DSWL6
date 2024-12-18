<?php
include 'conexao.php';

$membroId = $_GET['id'];
$tarefas = [];

// Consultar tarefas do membro
$stmt = $conn->prepare("SELECT id, nome, descricao, concluida FROM tarefas WHERE dono_id = ?");
$stmt->bind_param("i", $membroId);
$stmt->execute();
$result = $stmt->get_result();

while ($row = $result->fetch_assoc()) {
    $tarefas[] = $row;
}
$stmt->close();
$conn->close();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tarefas do Membro</title>
    <style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f5f5f5;
        margin: 0;
        padding: 0;
    }

    h1 {
        text-align: center;
        margin: 20px 0;
        color: #333;
    }

    table {
        width: 80%;
        margin: 20px auto;
        border-collapse: collapse;
        background-color: #fff;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    th, td {
        padding: 15px;
        text-align: left;
        border: 1px solid #ddd;
    }

    th {
        background-color: #007BFF;
        color: white;
        text-transform: uppercase;
    }

    tr:nth-child(even) {
        background-color: #f9f9f9;
    }

    tr:hover {
        background-color: #f1f1f1;
    }

    a {
        text-decoration: none;
        color: #007BFF;
    }

    a:hover {
        text-decoration: underline;
    }

    .success-message {
        color: green;
        font-weight: bold;
        text-align: center;
        margin: 20px 0;
    }

    .button-container {
        text-align: center;
        margin: 20px;
    }

    .button-container a {
        display: inline-block;
        padding: 10px 20px;
        background-color: #007BFF;
        color: white;
        border-radius: 5px;
        text-decoration: none;
        transition: background-color 0.3s ease;
    }

    .button-container a:hover {
        background-color: #0056b3;
    }
    
        a.voltar {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 15px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            text-align: center;
        }

        a.voltar:hover {
            background-color: #0056b3;
        }
</style>
    
</head>

<body>
    <h1>Tarefas do Membro</h1>
    
    <?php if (isset($_GET['success'])): ?>
    <p style="color: green; font-weight: bold;"><?php echo htmlspecialchars($_GET['success']); ?></p>
	<?php endif; ?>
    
    
    <table>
        <tr>
            <th>ID</th>
            <th>Título</th>
            <th>Descrição</th>
            <th>Status</th>
            <th>Ação</th>
        </tr>
        <?php foreach ($tarefas as $tarefa): ?>
            <tr>
                <td><?php echo $tarefa['id']; ?></td>
                <td><?php echo $tarefa['nome']; ?></td>
                <td><?php echo $tarefa['descricao']; ?></td>
                <td><?php echo $tarefa['concluida'] ? 'Concluída' : 'Pendente'; ?></td>
                <td>
                    <?php if (!$tarefa['concluida']): ?>
                        <a href="marcarConcluida.php?id=<?php echo $tarefa['id']; ?>">Marcar como Concluída</a>
                    <?php endif; ?>
                </td>
            </tr>
        <?php endforeach; ?>
    </table>
    <a class = 'voltar' href="equipe.php">Voltar</a>
</body>
</html>

