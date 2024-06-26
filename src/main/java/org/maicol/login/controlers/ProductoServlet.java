package org.maicol.login.controlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.maicol.login.models.Categoria;
import org.maicol.login.models.Producto;
import org.maicol.login.services.ProductoService;
import org.maicol.login.services.ProductoServiceJdbcImplement;
import org.maicol.login.services.LoginService;
import org.maicol.login.services.LoginServiceImplement;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productoServlet", "/productos"})
public class ProductoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = (Connection) request.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImplement(conn);
        List<Producto> productos = service.listar();

        LoginService auth = new LoginServiceImplement();
        Optional<String> usernameOptional = auth.getUsername(request);
        request.setAttribute("productos", productos);
        request.setAttribute("username", usernameOptional);

        request.getRequestDispatcher("/producto.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = (Connection) request.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImplement(conn);

        // Recuperar parámetros del formulario de creación de productos
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        String nombre = request.getParameter("nombre");
        int stock = Integer.parseInt(request.getParameter("stock"));
        int idCategoria = Integer.parseInt(request.getParameter("categoria"));
        String descripcion = request.getParameter("descripcion");
        String imagen = request.getParameter("imagen");
        int condicion = Integer.parseInt(request.getParameter("condicion"));
        double precio = Double.parseDouble(request.getParameter("precio"));



        // Crear un objeto Producto con los datos del formulario
        Producto producto = new Producto();
        producto.setCodigo(codigo);
        producto.setNombre(nombre);
        producto.setStock(stock);

        Categoria categoria = new Categoria();
        categoria.setIdCategoria(idCategoria);
        producto.setCategoria(categoria);

        producto.setDescripcion(descripcion);
        producto.setImagen(imagen);
        producto.setCondicion(condicion);
        producto.setPrecio(precio);

        // Guardar el producto usando el servicio
        service.guardar(producto);

        // Redirigir de vuelta a la página de productos después de guardar
        response.sendRedirect(request.getContextPath() + "/productos");
    }
}
