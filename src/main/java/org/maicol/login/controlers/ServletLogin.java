package org.maicol.login.controlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.maicol.login.services.LoginService;
import org.maicol.login.services.LoginServiceImplement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet({"/Login", "/ServletLogin"})
public class ServletLogin extends HttpServlet {
    final static String ADMIN_USERNAME = "admin";
    final static String ADMIN_PASSWORD = "12345";
    final static String CLIENTE_USERNAME = "cliente";
    final static String CLIENTE_PASSWORD = "cliente123";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginService auth = new LoginServiceImplement();
        Optional<String> usernameOptional = auth.getUsername(request);

        if (usernameOptional.isPresent()) {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.print("<!DOCTYPE html>");
                out.print("<html>");
                out.print("<head>");
                out.print("<title>Inicio de sesión</title>");
                out.print("<style>");
                out.print("body { background-color: #000000; color: #ffffff; font-family: Arial, sans-serif; }");
                out.print("h3 { text-align: center; }");
                out.print(".button { color: white; padding: 10px 20px; text-align: center; text-decoration: none; display: inline-block; margin: 10px 10px; border-radius: 5px; }");
                out.print(".button-red { background-color: #dc3545; }");
                out.print(".button-red:hover { background-color: #c82333; }");
                out.print(".button-green { background-color: #28a745; }");
                out.print(".button-green:hover { background-color: #218838; }");
                out.print(".button-container { text-align: center; }");
                out.print("</style>");
                out.print("</head>");
                out.print("<body>");
                out.print("<h3>Hola " + usernameOptional.get() + ", has iniciado sesión con éxito</h3>");
                out.print("<div class='button-container'>");
                out.print("<a class='button button-green' href='" + request.getContextPath() + "/index.html'>Volver</a>");
                out.print("<a class='button button-red' href='" + request.getContextPath() + "/logout'>Cerrar Sesión</a>");
                out.print("</div>");
                out.print("</body>");
                out.print("</html>");
            }
        } else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();
        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            session.setAttribute("username", username);
            session.setAttribute("role", "ADMIN");
            response.sendRedirect(request.getContextPath() + "/ServletLogin");
        } else if (CLIENTE_USERNAME.equals(username) && CLIENTE_PASSWORD.equals(password)) {
            session.setAttribute("username", username);
            session.setAttribute("role", "CLIENTE");
            response.sendRedirect(request.getContextPath() + "/ServletLogin");
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos, no está autorizado para ingresar al sistema");
        }
    }
}
