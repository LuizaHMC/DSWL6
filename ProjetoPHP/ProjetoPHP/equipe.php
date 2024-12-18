
<?php
include 'conexao.php';
    
    $successMessage = '';
    $errorMessage = '';
    
    // Processar adição de novo membro
    if ($_SERVER['REQUEST_METHOD'] === 'POST') {
        $nome = $_POST['nome'];
        $sobrenome = $_POST['sobrenome'];
        $email = $_POST['email'];
        $cargo = $_POST['cargo'];
        
        $stmt = $conn->prepare("INSERT INTO equipe (nome, sobrenome, email, cargo) VALUES (?, ?, ?, ?)");
        $stmt->bind_param("ssss", $nome, $sobrenome, $email, $cargo);
        
        if ($stmt->execute()) {
            $successMessage = "Membro '$nome' adicionado com sucesso!";
        } else {
            $errorMessage = "Erro ao adicionar o membro: " . $conn->error;
        }
        $stmt->close();
    }
    
    // Consultar membros
    $membros = [];
    $result = $conn->query("SELECT id, nome, sobrenome, email, cargo FROM equipe");
    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            $membros[] = $row;
        }
    }
    
    $conn->close();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lista de Membros da Equipe</title>
    <style>
        /* Reset básico de estilos */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        /* Corpo da página */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 20px;
        }

        /* Título principal */
        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        /* Mensagens de sucesso e erro */
        p {
            text-align: center;
            font-weight: bold;
        }

        /* Mensagem de sucesso */
        p[style="color: green;"] {
            color: green;
        }

        /* Mensagem de erro */
        p[style="color: red;"] {
            color: red;
        }

        /* Tabela */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 30px;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ccc;
        }

        th {
            background-color: #f2f2f2;
            color: #333;
        }

        tr:hover {
            background-color: #e9e9e9;
        }

        /* Botões de ação */
        .buttonAction {
            display: inline-block;
            margin: 5px;
            padding: 8px 15px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }

        .buttonAction:hover {
            background-color: #45a049;
        }

        /* Formulário para adicionar novo membro */
        .group {
            margin-top: 20px;
            padding: 20px;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 8px;
            width: 50%;
            margin-left: auto;
            margin-right: auto;
        }

        .group h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-size: 14px;
        }

        input[type="text"],
        input[type="email"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        button[type="submit"] {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
            font-size: 16px;
        }

        button[type="submit"]:hover {
            background-color: #45a049;
        }

        /* Link de Voltar */
        .voltar {
            display: block;
            text-align: center;
            margin-top: 20px;
            font-size: 16px;
            color: #333;
            text-decoration: none;
        }

        .voltar:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h1>Lista de Membros da Equipe</h1>

    <?php if (!empty($successMessage)): ?>
        <p style="color: green;"><?php echo $successMessage; ?></p>
    <?php endif; ?>
    <?php if (!empty($errorMessage)): ?>
        <p style="color: red;"><?php echo $errorMessage; ?></p>
    <?php endif; ?>

    <table>
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Sobrenome</th>
            <th>Email</th>
            <th>Cargo</th>
            <th>Ações</th>
        </tr>
        <?php foreach ($membros as $membro): ?>
            <tr>
                <td><?php echo $membro['id']; ?></td>
                <td><?php echo $membro['nome']; ?></td>
                <td><?php echo $membro['sobrenome']; ?></td>
                <td><?php echo $membro['email']; ?></td>
                <td><?php echo $membro['cargo']; ?></td>
                <td>
                    <a class="buttonAction" href="tarefaPessoa.php?id=<?php echo $membro['id']; ?>">Ver Tarefas</a>
                    <a class="buttonAction" href="deletarMembro.php?id=<?php echo $membro['id']; ?>">Deletar Membro</a>
                </td>
            </tr>
        <?php endforeach; ?>
    </table>

    <div class="group">
        <h2>Adicionar Novo Membro</h2>
        <form action="equipe.php" method="post">
            <label for="nome">Nome:</label>
            <input type="text" name="nome" required><br>
            <label for="sobrenome">Sobrenome:</label>
            <input type="text" name="sobrenome" required><br>
            <label for="email">Email:</label>
            <input type="email" name="email" required><br>
            <label for="cargo">Cargo:</label>
            <input type="text" name="cargo" required><br>
            <button type="submit">Adicionar Membro</button>
        </form>
    </div>

    <a class="voltar" href="index.php">Voltar</a>
</body>
</html>

