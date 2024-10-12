package br.edu.ifsp.tads.dswl6;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;


@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Obtém a sessão atual
        HttpSession session = request.getSession(false);
        if (session != null) {
            String username = (String) session.getAttribute("username");
            out.println("<h1>Bem-vindo, " + username + "!</h1>");
            out.println("<button onclick=\"window.location.href='/ExerciciosAula03/logout';\">Logout</button>");
            // Cria um cookie e define o tempo de expiração para 1 hora
            Cookie userCookie = new Cookie("username", username);
            userCookie.setMaxAge(60 * 60); // 1 hora
            response.addCookie(userCookie);
            
        } else {
            out.println("<h1>Não há uma sessão ativa. Faça o login.</h1>");
        }
    }
}
