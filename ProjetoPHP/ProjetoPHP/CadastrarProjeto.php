<?php
include 'conexao.php';

// Processar o formulário de cadastro
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $nome = $_POST['nome'] ?? null;
    $descricao = $_POST['descricao'] ?? null;
    $dataInicio = $_POST['dataInicio'] ?? null;
    $dataFim = $_POST['dataFim'] ?? null;

    // Verificar se todos os campos foram preenchidos
    if ($nome && $descricao && $dataInicio && $dataFim) {
        $sqlInsert = "INSERT INTO projetos (nome, descricao, data_inicio, data_fim) 
                      VALUES ('$nome', '$descricao', '$dataInicio', '$dataFim')";

        if ($conn->query($sqlInsert) === TRUE) {
            echo "<p style='color: green; text-align: center;'>Projeto cadastrado com sucesso!</p>";
        } else {
            echo "<p style='color: red; text-align: center;'>Erro ao cadastrar projeto: " . $conn->error . "</p>";
        }
    } else {
        echo "<p style='color: red; text-align: center;'>Por favor, preencha todos os campos!</p>";
    }
}

// Obter os projetos cadastrados
$sqlSelect = "SELECT * FROM projetos";
$result = $conn->query($sqlSelect);

$projetos = [];
if ($result && $result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        $projetos[] = $row;
    }
}

$conn->close();
?>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Projetos</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
            color: #333;
        }

        h1 {
            text-align: center;
            margin: 20px 0;
            color: #444;
        }

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        table th, table td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }

        table th {
            background-color: #f4f4f4;
            color: #333;
        }

        .group {
            width: 60%;
            margin: 30px auto;
            padding: 20px;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .group h2 {
            margin-bottom: 15px;
            text-align: center;
        }

        .group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        .group input {
            width: calc(100% - 20px);
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .group button {
            display: block;
            width: 100%;
            padding: 10px;
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        .group button:hover {
            background-color: #0056b3;
        }

        .voltar {
            display: block;
            width: 120px;
            margin: 20px auto;
            text-align: center;
            padding: 10px;
            text-decoration: none;
            background-color: #007BFF;
            color: white;
            border-radius: 4px;
            font-size: 16px;
        }

        .voltar:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h1>Lista de Projetos</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Descrição</th>
            <th>Data Início</th>
            <th>Data Fim</th>
        </tr>
        <?php foreach ($projetos as $projeto): ?>
            <tr>
                <td><?= htmlspecialchars($projeto['id']) ?></td>
                <td><?= htmlspecialchars($projeto['nome']) ?></td>
                <td><?= htmlspecialchars($projeto['descricao']) ?></td>
                <td><?= htmlspecialchars($projeto['data_inicio']) ?></td>
                <td><?= htmlspecialchars($projeto['data_fim']) ?></td>
            </tr>
        <?php endforeach; ?>
    </table>

    <div class="group">
        <h2>Cadastrar Projeto</h2>
        <form action="CadastrarProjeto.php" method="post">
            <label for="nome">Nome:</label>
            <input type="text" name="nome" required /><br>

            <label for="descricao">Descrição:</label>
            <input type="text" name="descricao" required /><br>

            <label for="dataInicio">Data Início:</label>
            <input type="date" name="dataInicio" required /><br>

            <label for="dataFim">Data Fim:</label>
            <input type="date" name="dataFim" required /><br>

            <button type="submit">Cadastrar projeto</button>
        </form>
    </div>
    <a class="voltar" href="index.php">Voltar</a>
</body>
</html>
