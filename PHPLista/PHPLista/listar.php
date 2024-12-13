<?php
// Conexão com o banco de dados
$conn = new mysqli('localhost', 'root', 'ifsp', 'CadastroDB');

if ($conn->connect_error) {
    die("Conexão falhou: " . $conn->connect_error);
}

// Buscando todos os usuários
$result = $conn->query("SELECT * FROM usuarios");

?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Listar Usuários</title>
</head>
<body>
    <h1>Lista de Usuários</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Email</th>
            <th>Ações</th>
        </tr>
        <?php while ($row = $result->fetch_assoc()): ?>
            <tr>
                <td><?= $row['id'] ?></td>
                <td><?= $row['nome'] ?></td>
                <td><?= $row['email'] ?></td>
                <td>
                    <a href="alterar.php?id=<?= $row['id'] ?>">Alterar</a>
                    <a href="deletar.php?id=<?= $row['id'] ?>" onclick="return confirm('Tem certeza?')">Deletar</a>
                </td>
            </tr>
        <?php endwhile; ?>
    </table>
    
    <button onclick="window.location.href='index.php'">Voltar</button>
    
</body>
</html>
