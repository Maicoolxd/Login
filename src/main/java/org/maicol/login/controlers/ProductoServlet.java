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

import static java.lang.System.out;

@WebServlet({"/productoServlet", "/productos"})
public class ProductoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductoService service = new ProductoServiceImplement();
        List<Producto> productos = service.listar();
        LoginService auth= new LoginServiceImplement();
        //validadando que el usuario exista
        Optional<String> usernameOptional = auth.getUsername(request);

        try (PrintWriter out= response.getWriter()){
            out.print("<!DOCTYPE html>");
            out.print("<html>");
            out.print("<head>");
            out.print("</head>");
            out.print("<body>");
            out.print("<h1>Listado productos </h1>");
            if(usernameOptional.isPresent()){
                out.print("<div style= 'color:blue;'> Hola "+ usernameOptional.get()+" Bienvenido");
            }
            out.print("<table>");
            out.print("<tr>");
            out.print("<th> id</th>");
            out.print("<th> Nombre </th>");
            out.print("<th> Categoria </th>");
            out.print("<th> Descripcion </th>");
            if(usernameOptional.isPresent()) {
                out.print("<th> Precio </th>");
            }
            out.print("</tr>");
            productos.forEach(p ->{
                out.print("<tr>");
                out.print("<td>"+ p.getIdProducto() +"</td>");
                out.print("<td>"+ p.getNombre() +"</td>");
                out.print("<td>"+ p.getCategoria() +"</td>");
                out.print("<td>"+ p.getDescripcion() +"</td>");
                out.print("<td>"+ p.getPrecio() +"</td>");
                out.print("</tr>");

            });

            out.print("</table>");
            response.setContentType("text/html;charset UTF-8");

            out.print("</body>");
            out.print("</html>");
        }
    }
}

