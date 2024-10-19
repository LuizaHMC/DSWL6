<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Carrinhos</title>
</head>
<body>
    <h1>Carrinhos de Compras</h1>
    <table border="1">
        <thead>
            <tr>
                <th>ID do Carrinho</th>
                <th>Cliente</th>
                <th>Produtos</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="carrinho" items="${carrinhos}">
                <tr>
                    <td>${carrinho.id}</td>
                    <td>${carrinho.cliente.nome}</td>
                    <td>
                        <ul>
                            <c:forEach var="produto" items="${carrinho.produtos}">
                                <li>${produto.nome} - R$ ${produto.preco}</li>
                            </c:forEach>
                        </ul>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <h2>Adicionar Carrinho</h2>
    <form action="carrinhos" method="post">
        <label for="id">ID do Carrinho:</label>
        <input type="text" id="id" name="id" required><br>
        
        <label for="clienteId">Cliente:</label>
        <select id="clienteId" name="clienteId" required>
            <c:forEach var="cliente" items="${clientes}">
                <option value="${cliente.id}">${cliente.nome}</option>
            </c:forEach>
        </select><br>

        <!-- Adicionar lógica de produtos aqui -->
        <label for="produtoId">Produtos:</label>
        <select id="produtoId" name="produtoId" multiple required>
            <c:forEach var="produto" items="${produtos}">
                <option value="${produto.id}">${produto.nome} - R$ ${produto.preco}</option>
            </c:forEach>
        </select><br>

        <button type="submit">Criar Carrinho</button>
    </form>

    <br>
    <a href="index.jsp">Voltar ao Início</a>
</body>
</html>