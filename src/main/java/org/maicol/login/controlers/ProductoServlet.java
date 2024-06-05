package org.maicol.login.controlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.maicol.login.models.Producto;
import org.maicol.login.services.LoginService;
import org.maicol.login.services.LoginServiceImplement;
import org.maicol.login.services.ProductoService;
import org.maicol.login.services.ProductoServiceImplement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productoServlet", "/productos"})
public class ProductoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductoService service = new ProductoServiceImplement();
        List<Producto> productos = service.listar();
        LoginService auth= new LoginServiceImplement();
        Optional<String> usernameOptional = auth.getUsername(request);

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.print("<!DOCTYPE html>");
            out.print("<html lang='en'>");
            out.print("<head>");
            out.print("<meta charset='UTF-8'>");
            out.print("<title>Listado de Productos</title>");
            out.print("<link rel='stylesheet' href='styles.css'>");
            out.print("</head>");
            out.print("<body>");
            out.print("<div class='container'>");
            out.print("<h3>Listado de Productos</h3>");
            out.print("<ul>");
            out.print("<li><a href='/Login_war_exploded/ServletLogin'>Regresar</a></li>");
            out.print("</ul>");

            if(usernameOptional.isPresent()) {
                out.print("<div style='color:blue;'>Hola " + usernameOptional.get() + ", Bienvenido</div>");
            }

            out.print("<table>");
            out.print("<thead>");
            out.print("<tr>");
            out.print("<th>Id</th>");
            out.print("<th>Nombre</th>");
            out.print("<th>Categoría</th>");
            out.print("<th>Descripción</th>");
            if (usernameOptional.isPresent()) {
                out.print("<th>Precio</th>");
            }
            out.print("</tr>");
            out.print("</thead>");
            out.print("<tbody>");
            for (Producto p : productos) {
                out.print("<tr>");
                out.print("<td>" + p.getIdProducto() + "</td>");
                out.print("<td>" + p.getNombre() + "</td>");
                out.print("<td>" + p.getCategoria() + "</td>");
                out.print("<td>" + p.getDescripcion() + "</td>");
                if (usernameOptional.isPresent()) {
                    out.print("<td>" + p.getPrecio() + "</td>");
                }
                out.print("</tr>");
            }
            out.print("</tbody>");
            out.print("</table>");
            out.print("</div>");
            out.print("</body>");
            out.print("</html>");
        }
    }
}
