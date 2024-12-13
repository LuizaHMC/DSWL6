<?php
// Conexão com o banco de dados
$conn = new mysqli('localhost', 'root', 'ifsp', 'CadastroDB');

if ($conn->connect_error) {
    die("Conexão falhou: " . $conn->connect_error);
}

// Obtendo o ID do usuário para alteração
$id = $_GET['id'] ?? null;

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $nome = $_POST['nome'];
    $email = $_POST['email'];

    $sql = "UPDATE usuarios SET nome='$nome', email='$email' WHERE id=$id";
    if ($conn->query($sql) === TRUE) {
        echo "Usuário atualizado com sucesso!";
    } else {
        echo "Erro ao atualizar: " . $conn->error;
    }
}

// Buscando os dados do usuário
$result = $conn->query("SELECT * FROM usuarios WHERE id=$id");
$usuario = $result->fetch_assoc();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Alterar Usuário</title>
</head>
<body>
    <h1>Alterar Usuário</h1>
    <form action="" method="POST">
        <label for="nome">Nome:</label><br>
        <input type="text" id="nome" name="nome" value="<?= $usuario['nome'] ?>" required><br><br>

        <label for="email">Email:</label><br>
        <input type="email" id="email" name="email" value="<?= $usuario['email'] ?>" required><br><br>

        <button type="submit">Salvar Alterações</button>
    </form>
    
    <button onclick="window.location.href='index.php'">Voltar</button>
</body>
</html>
