<?php
include 'conexao.php';

$tarefaId = $_POST['tarefaId'];
$membroId = $_POST['membroId'];

$sql = "UPDATE tarefas SET dono_id = $membroId WHERE id = $tarefaId";
$conn->query($sql);

$conn->close();
header("Location: tarefas.php");
exit;
?>
