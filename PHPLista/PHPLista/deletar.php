<?php
// Conexão com o banco de dados
$conn = new mysqli('localhost', 'root', 'ifsp', 'CadastroDB');

if ($conn->connect_error) {
    die("Conexão falhou: " . $conn->connect_error);
}

// Obtendo o ID do usuário para deletar
$id = $_GET['id'];

// Excluindo o usuário
$sql = "DELETE FROM usuarios WHERE id=$id";
if ($conn->query($sql) === TRUE) {
    echo "Usuário excluído com sucesso!";
} else {
    echo "Erro ao excluir: " . $conn->error;
}

$conn->close();
?>
