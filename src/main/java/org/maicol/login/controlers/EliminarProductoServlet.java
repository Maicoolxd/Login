package org.maicol.login.controlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.maicol.login.services.ProductoService;
import org.maicol.login.services.ProductoServiceJdbcImplement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/eliminarProducto")
public class EliminarProductoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener la sesión actual
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        // Verificar si el usuario es administrador
        if (!"ADMIN".equals(role)) {
            // Mostrar mensaje con estilo HTML
            String errorMessage = "<html><head><title>Error de permisos</title>" +
                    "<style>" +
                    "body { background-color: black; color: white; text-align: center; font-size: 24px; }" +
                    ".btn { background-color: red; color: white; padding: 10px 20px; text-decoration: none; }" +
                    "</style></head>" +
                    "<body>" +
                    "<h1>No tienes permisos para eliminar un producto.</h1>" +
                    "<a class='btn' href='" + request.getContextPath() + "/producto.jsp'>Regresar</a>" +
                    "</body></html>";
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(errorMessage);
            return;
        }

        // Si es administrador, continuar con el proceso de eliminar el producto
        // Obtener la conexión de la solicitud
        Connection conn = (Connection) request.getAttribute("conn");

        // Inicializar el servicio de productos con la conexión
        ProductoService service = new ProductoServiceJdbcImplement(conn);

        // Obtener el ID del producto a eliminar desde los parámetros de la solicitud
        int idProducto = Integer.parseInt(request.getParameter("id"));

        // Eliminar el producto utilizando el servicio
        try {
            service.eliminar(idProducto);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Redirigir de vuelta a la página de productos después de eliminar
        response.sendRedirect(request.getContextPath() + "/productos");
    }
}
