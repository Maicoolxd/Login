package org.maicol.login.controlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.maicol.login.models.Categoria;
import org.maicol.login.models.Producto;
import org.maicol.login.services.ProductoService;
import org.maicol.login.services.ProductoServiceJdbcImplement;

import java.io.IOException;
import java.sql.Connection;

public class CrearProductoServlet extends HttpServlet {

    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection conn = (Connection) request.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImplement(conn);

        int codigo = Integer.parseInt(request.getParameter("codigo"));
        String nombre = request.getParameter("nombre");
        int stock = Integer.parseInt(request.getParameter("stock"));
        String descripcion = request.getParameter("descripcion");
        String imagen = request.getParameter("imagen");
        int condicion = Integer.parseInt(request.getParameter("condicion"));
        Double precio = Double.parseDouble(request.getParameter("precio"));

        Producto producto = new Producto();
        producto.setCodigo(codigo);
        producto.setNombre(nombre);
        producto.setStock(stock);
        producto.setDescripcion(descripcion);
        producto.setImagen(imagen);
        producto.setCondicion(condicion);
        producto.setPrecio(precio);



    }
}
