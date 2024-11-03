<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.projeto.dto.ProjetoDTO" %>
<%@ page import="com.projeto.dto.EquipeDTO" %> 
<%@ page import="com.projeto.dto.TarefaDTO" %> 
<%@ page import="com.projeto.model.Projeto" %>
<%@ page import="com.projeto.model.Tarefa" %>
<%@ page import="com.projeto.model.Equipe" %> 
<%
    ProjetoDTO projetoDTO = new ProjetoDTO();
    List<Projeto> projetos = projetoDTO.list();
    request.setAttribute("projetos", projetos);

    EquipeDTO equipeDTO = new EquipeDTO();
    List<Equipe> membros = equipeDTO.listNames(); // Usando o novo método
    request.setAttribute("membros", membros); // Passando a lista para a JSP
    
    TarefaDTO tarefaDTO = new TarefaDTO();
    List<Tarefa> tarefas = tarefaDTO.listPendentes(); // Método que deve retornar a lista de tarefas
    request.setAttribute("tarefas", tarefas); // Passando a lista de tarefas para a JSP
    RequestDispatcher dispatcher = request.getRequestDispatcher("tarefa-list.jsp");
    

%>

<html>
<head>
    <title>Lista de Tarefas</title>
</head>
<body>
<h2>Lista de Tarefas</h2>
<table border="1">
    <thead>
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Descrição</th>
            <th>Data Início</th>
            <th>Data Fim</th>
            <th>Projeto</th>
            <th>Responsável</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="tarefa" items="${tarefas}">
            <tr>
                <td>${tarefa.id}</td>
                <td>${tarefa.nome}</td>
                <td>${tarefa.descricao}</td>
                <td>${tarefa.dataInicio}</td>
                <td>${tarefa.dataFim}</td>
                <td>
                    <c:forEach var="projeto" items="${projetos}">
                        <c:if test="${projeto.id == tarefa.projetoId}">${projeto.nome}</c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="responsavel" items="${membros}">
                        <c:if test="${responsavel.id == tarefa.donoId}">${responsavel.nome}</c:if>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<h2>Adicionar Tarefa</h2>
<form action="AdicionarTarefa" method="post">
    <label for="nome">Nome:</label>
    <input type="text" name="nome" required/><br>

    <label for="descricao">Descrição:</label>
    <input type="text" name="descricao" required/><br>

    <label for="dataInicio">Data Início:</label>
    <input type="date" name="dataInicio" required/><br>

    <label for="dataFim">Data Fim:</label>
    <input type="date" name="dataFim" required/><br>

    <label for="projetoId">Selecione o Projeto:</label>
    <select name="projetoId" required>
        <option value="">Selecione um projeto</option>
        <c:forEach var="projeto" items="${projetos}">
            <option value="${projeto.id}">${projeto.nome}</option>
        </c:forEach>
    </select><br>

    <button type="submit">Adicionar tarefa</button>
</form>

<h2>Adicionar Membro à Tarefa</h2>
<form action="AdicionarMembro" method="post"> 
    <label for="tarefaId">Selecione a Tarefa:</label>
    <select name="tarefaId" required>
        <option value="">Selecione uma tarefa</option>
        <c:forEach var="tarefa" items="${tarefas}">
            <option value="${tarefa.id}">${tarefa.nome}</option>
        </c:forEach>
    </select><br>

    <label for="membroId">Selecione o Membro:</label>
	<select name="membroId" required>
	    <option value="">Selecione um membro</option>
	    <c:forEach var="membro" items="${membros}">
	        <option value="${membro.id}">${membro.nome}</option> 
	    </c:forEach>
	</select><br>

    <button type="submit">Adicionar membro</button>
</form>

<a href="index.jsp">Voltar</a>
</body>
</html>
