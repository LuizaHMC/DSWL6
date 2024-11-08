<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lista de Membros da Equipe</title>
    <link rel="stylesheet" type="text/css" href="style.css"> <!-- Se você tiver um CSS -->
</head>
<body>
    <h1>Lista de Membros da Equipe</h1>
    
    <!-- Exibir mensagem de sucesso ou erro -->
    <c:if test="${not empty successMessage}">
        <p style="color: green;">${successMessage}</p>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <p style="color: red;">${errorMessage}</p>
    </c:if>
    
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Sobrenome</th>
            <th>Email</th>
            <th>Cargo</th>
        </tr>
        <c:forEach var="membro" items="${membros}">
            <tr>
                <td>${membro.id}</td>
                <td>${membro.nome}</td>
                <td>${membro.sobrenome}</td>
                <td>${membro.email}</td>
                <td>${membro.cargo}</td>
                <td><a href="tarefasPorMembro?id=${membro.id}">Ver Tarefas</a></td>
                <td><a href="DeletarMembro?membroId=${membro.id}">Deletar membro</a></td>
            </tr>
        </c:forEach>
    </table>

    <h2>Adicionar Novo Membro</h2>
    <form action="equipes" method="post">
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
    
    
    <a href="index.jsp">Voltar</a>
</body>
</html>
