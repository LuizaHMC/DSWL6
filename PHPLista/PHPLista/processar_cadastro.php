<?php
// Configuração do banco de dados
$host = 'localhost';
$dbname = 'CadastroDB';
$username = 'root';
$password = 'ifsp';

// Conexão com o banco de dados
$conn = new mysqli($host, $username, $password, $dbname);

// Verifica se houve erro na conexão
if ($conn->connect_error) {
    die("Conexão falhou: " . $conn->connect_error);
}

// Obtendo os dados do formulário
$nome = $_POST['nome'];
$email = $_POST['email'];
$senha = password_hash($_POST['senha'], PASSWORD_DEFAULT); // Criptografar a senha

// Inserindo no banco de dados
$sql = "INSERT INTO usuarios (nome, email, senha) VALUES ('$nome', '$email', '$senha')";

if ($conn->query($sql) === TRUE) {
    echo "Usuário cadastrado com sucesso!";
} else {
    echo "Erro: " . $sql . "<br>" . $conn->error;
}

$conn->close();
?>
