<?php
$host = 'localhost';
$dbname = 'projeto_php';
$username = 'root';
$password = 'ifsp';

$conn = new mysqli($host, $username, $password, $dbname);

if ($conn->connect_error) {
    die("Conexão falhou: " . $conn->connect_error);
}

?>