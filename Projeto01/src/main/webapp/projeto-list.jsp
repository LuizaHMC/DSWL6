<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Projetos</title>
</head>
<body>
    <h1>Lista de Projetos</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Descrição</th>
            <th>Data Início</th>
            <th>Data Fim</th>
        </tr>
        <c:forEach var="projeto" items="${projetos}">
            <tr>
                <td>${projeto.id}</td>
                <td>${projeto.nome}</td>
                <td>${projeto.descricao}</td>
                <td>${projeto.dataInicio}</td>
                <td>${projeto.dataFim}</td>
            </tr>
        </c:forEach>
    </table>

    <h2>Adicionar Projeto</h2>
    <form action="projetos" method="post">
        <label for="nome">Nome:</label>
        <input type="text" name="nome" required><br>
        <label for="descricao">Descrição:</label>
        <input type="text" name="descricao" required><br>
        <label for="dataInicio">Data Início:</label>
        <input type="date" name="dataInicio" required><br>
        <label for="dataFim">Data Fim:</label>
        <input type="date" name="dataFim" required><br>
        <button type="submit">Criar projeto</button>
    </form>
    
    
    <a href="index.jsp">Voltar</a>
    
</body>
</html>
