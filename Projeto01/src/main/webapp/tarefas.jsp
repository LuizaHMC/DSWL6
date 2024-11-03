<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lista de Tarefas</title>
</head>
<body>
    <h1>Lista de Tarefas</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Descrição</th>
            <th>Data de Início</th>
            <th>Data de Fim</th>
            <th>Concluída</th> 
        </tr>
        <c:forEach var="tarefa" items="${tarefas}">
		    <tr>
		        <td>${tarefa.id}</td>
		        <td>${tarefa.nome}</td>
		        <td>${tarefa.descricao}</td>
		        <td>${tarefa.dataInicio}</td>
		        <td>${tarefa.dataFim}</td>
		        <td>
		            <form action="marcarConcluida" method="post">
		                <input type="hidden" name="id" value="${tarefa.id}">
		                <input type="hidden" name="membroId" value="${membro.id}">
		                <input type="checkbox" name="concluida" 
					       <c:if test="${tarefa.concluida}">checked="checked"</c:if> 
					       		onclick="this.form.submit()">
		            </form>
		        </td>
		    </tr>
		</c:forEach>
    </table>

    <a href="equipes">Voltar</a>
</body>
</html>