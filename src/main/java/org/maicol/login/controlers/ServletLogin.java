package org.maicol.login.controlers;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.maicol.login.services.LoginService;
import org.maicol.login.services.LoginServiceImplement;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

//creamos el Path para la conexion al Servlet
@WebServlet({"/Login", "/ServletLogin"})
public class ServletLogin extends HttpServlet {
    final static String USERNAME="admin";
    final static String PASSWORD="12345";

    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginService auth = new LoginServiceImplement();
        //creo una variable opcional de tipo String

        Optional <String> usernameOptional = auth.getUsername(request);
        //si el usernameOptional tiene algun dato

        if(usernameOptional.isPresent()) {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.print("<!DOCTYPE html>");
                out.print("<html>");
                out.print("<head>");
                out.print("<title>Inicio de sesión</title>");
                out.print("<link rel='stylesheet' type='text/css' href='styles.css'>");
                out.print("</head>");
                out.print("</head>");
                out.print("<body>");
                out.print("<h3> Hola " + usernameOptional.get() + "has inciado sesion con exito</h3>");
                out.println("<p><a href='" + request.getContextPath() + "/index.html'>Volver</a></p>");
                out.println("<p><a href='" + request.getContextPath() + "/logout'>Cerrar Sesion</a></p>");
                out.print("</body>");
                out.print("</html>");
            }
        }else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }
    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");

        if(USERNAME.equals(username) && PASSWORD.equals(password)){
            HttpSession session=request.getSession();
            session.setAttribute("username",username);
            response.sendRedirect(request.getContextPath()+"/ServletLogin");
        }else{
    response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Lo sentimos no esta autorizado para ingresar al sistema");
        }
    }
}



