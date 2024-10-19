<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Produtos</title>
</head>
<body>
    <h1>Produtos</h1>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Preço</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="produto" items="${produtos}">
                <tr>
                    <td>${produto.id}</td>
                    <td>${produto.nome}</td>
                    <td>${produto.preco}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <h2>Adicionar Produto</h2>
    <form action="produtos" method="post">
        <label for="id">ID:</label>
        <input type="text" id="id" name="id" required><br>
        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" required><br>
        <label for="preco">Preço:</label>
        <input type="number" id="preco" name="preco" step="0.01" required><br>
        <button type="submit">Salvar</button>
    </form>

    <br>
    <a href="index.jsp">Voltar ao Início</a>
</body>
</html>