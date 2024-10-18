<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Boas Vindas</title>
</head>
<body>

	<%
	    String nome = request.getParameter("nome");
	
	    if (nome == null || nome.trim().isEmpty()) {
	        nome = "Visitante";
	    }
	%>

    <h1>Olá, <%= nome %></h1>    

</body>
</html>