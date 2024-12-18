<?php
include 'conexao.php';

$nome = $_POST['nome'];
$descricao = $_POST['descricao'];
$dataInicio = $_POST['dataInicio'];
$dataFim = $_POST['dataFim'];
$projetoId = $_POST['projetoId'];

$sql = "INSERT INTO tarefas (nome, descricao, data_inicio, data_fim, projeto_id) VALUES ('$nome', '$descricao', '$dataInicio', '$dataFim', $projetoId)";
$conn->query($sql);

$conn->close();
header("Location: tarefas.php");
exit;
?>
