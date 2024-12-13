<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro</title>
</head>
<body>
    <h1>Cadastro de Usuário</h1>
    <form action="processar_cadastro.php" method="POST">
        <label for="nome">Nome:</label><br>
        <input type="text" id="nome" name="nome" required><br><br>

        <label for="email">Email:</label><br>
        <input type="email" id="email" name="email" required><br><br>

        <label for="senha">Senha:</label><br>
        <input type="password" id="senha" name="senha" required><br><br>

        <button type="submit">Cadastrar</button>
    </form>
    
    <button onclick="window.location.href='index.php'">Voltar</button>
</body>
</html>