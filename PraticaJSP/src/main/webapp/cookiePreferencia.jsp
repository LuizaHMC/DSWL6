<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Escolha seu Idioma</title>
</head>
<body>
    <h1>Escolha seu Idioma</h1>

    <form method="post" action="">
        <label>
            <input type="radio" name="language" value="espanhol" required> Espanhol
        </label><br>
        <label>
            <input type="radio" name="language" value="ingles"> Ingl�s
        </label><br>
        <label>
            <input type="radio" name="language" value="portugues"> Portugu�s
        </label><br><br>
        <button type="submit">Salvar Prefer�ncia</button>
    </form>
    
    <%
        // L� o cookie da prefer�ncia
        String cookieValue = null;
        String languageCookieName = "languagePreference";
        Cookie[] cookies = request.getCookies();
        
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (languageCookieName.equals(cookie.getName())) {
                    cookieValue = cookie.getValue();
                    break;
                }
            }
        }

        // Verifica se o formul�rio foi enviado
        if ("post".equalsIgnoreCase(request.getMethod())) {
            String selectedLanguage = request.getParameter("language");
            // Cria ou atualiza o cookie
            Cookie languageCookie = new Cookie(languageCookieName, selectedLanguage);
            languageCookie.setMaxAge(60 * 60 * 24); // Expira em 1 dia
            response.addCookie(languageCookie);
            cookieValue = selectedLanguage; // Atualiza a exibi��o
        }
    %>

    <p>Sua prefer�ncia cookie �: <%= cookieValue != null ? cookieValue : "nenhuma" %></p>
</body>
</html>