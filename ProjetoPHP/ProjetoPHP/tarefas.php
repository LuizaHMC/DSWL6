
<?php
include 'conexao.php';

// Obtém todas as tarefas
$sqlTarefas = "SELECT * FROM tarefas WHERE concluida = 0";
$resultTarefas = $conn->query($sqlTarefas);
$tarefas = $resultTarefas->fetch_all(MYSQLI_ASSOC);

// Obtém todos os projetos
$sqlProjetos = "SELECT * FROM projetos";
$resultProjetos = $conn->query($sqlProjetos);
$projetos = $resultProjetos->fetch_all(MYSQLI_ASSOC);

// Obtém todos os membros
$sqlMembros = "SELECT * FROM equipe";
$resultMembros = $conn->query($sqlMembros);
$membros = $resultMembros->fetch_all(MYSQLI_ASSOC);

$conn->close();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lista de Tarefas</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f9f9f9;
        }

        h2 {
            color: #333;
            text-align: center;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
            background-color: #fff;
        }

        table th, table td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }

        table th {
            background-color: #f4f4f4;
            font-weight: bold;
        }

        table tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        table tr:hover {
            background-color: #f1f1f1;
        }

        .group {
            margin: 20px 0;
            padding: 15px;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        .group h2 {
            margin-top: 0;
            font-size: 1.2em;
            color: #555;
        }

        label {
            display: block;
            margin: 10px 0 5px;
            font-weight: bold;
        }

        input, select, button {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }

        button {
            background-color: #007bff;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 1em;
        }

        button:hover {
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
<h2>Lista de Tarefas</h2>
<table border="1">
    <thead>
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Descrição</th>
            <th>Data Início</th>
            <th>Data Fim</th>
            <th>Projeto</th>
            <th>Responsável</th>
        </tr>
    </thead>
    <tbody>
        <?php foreach ($tarefas as $tarefa): ?>
            <tr>
                <td><?= htmlspecialchars($tarefa['id']) ?></td>
                <td><?= htmlspecialchars($tarefa['nome']) ?></td>
                <td><?= htmlspecialchars($tarefa['descricao']) ?></td>
                <td><?= htmlspecialchars($tarefa['data_inicio']) ?></td>
                <td><?= htmlspecialchars($tarefa['data_fim']) ?></td>
                <td>
                    <?php foreach ($projetos as $projeto): ?>
                        <?php if ($projeto['id'] === $tarefa['projeto_id']): ?>
                            <?= htmlspecialchars($projeto['nome']) ?>
                        <?php endif; ?>
                    <?php endforeach; ?>
                </td>
                <td>
                    <?php foreach ($membros as $membro): ?>
                        <?php if ($membro['id'] === $tarefa['dono_id']): ?>
                            <?= htmlspecialchars($membro['nome']) ?>
                        <?php endif; ?>
                    <?php endforeach; ?>
                </td>
            </tr>
        <?php endforeach; ?>
    </tbody>
</table>

<div class="group">
    <h2>Adicionar Tarefa</h2>
    <form action="AdicionarTarefa.php" method="post">
        <label for="nome">Nome:</label>
        <input type="text" name="nome" required/><br>

        <label for="descricao">Descrição:</label>
        <input type="text" name="descricao" required/><br>

        <label for="dataInicio">Data Início:</label>
        <input type="date" name="dataInicio" required/><br>

        <label for="dataFim">Data Fim:</label>
        <input type="date" name="dataFim" required/><br>

        <label for="projetoId">Selecione o Projeto:</label>
        <select name="projetoId" required>
            <option value="">Selecione um projeto</option>
            <?php foreach ($projetos as $projeto): ?>
                <option value="<?= htmlspecialchars($projeto['id']) ?>">
                    <?= htmlspecialchars($projeto['nome']) ?>
                </option>
            <?php endforeach; ?>
        </select><br>

        <button type="submit">Adicionar tarefa</button>
    </form>
</div>

<div class="group">
    <h2>Adicionar Membro à Tarefa</h2>
    <form action="AdicionarMembro.php" method="post">
        <label for="tarefaId">Selecione a Tarefa:</label>
        <select name="tarefaId" required>
            <option value="">Selecione uma tarefa</option>
            <?php foreach ($tarefas as $tarefa): ?>
                <option value="<?= htmlspecialchars($tarefa['id']) ?>">
                    <?= htmlspecialchars($tarefa['nome']) ?>
                </option>
            <?php endforeach; ?>
        </select><br>

        <label for="membroId">Selecione o Membro:</label>
        <select name="membroId" required>
            <option value="">Selecione um membro</option>
            <?php foreach ($membros as $membro): ?>
                <option value="<?= htmlspecialchars($membro['id']) ?>">
                    <?= htmlspecialchars($membro['nome']) ?>
                </option>
            <?php endforeach; ?>
        </select><br>

        <button type="submit">Adicionar membro</button>
    </form>
</div>

<a class="voltar" href="index.php">Voltar</a>
</body>
</html>
