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

import java.io.IOException;
import java.net.IDN;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/formulario")
public class ProductoFormServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = (Connection) request.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImplement(conn);
        //si no encuetra un producto no inicializa en 0
        Integer idCategoria;
        try {
            idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
        }catch (NumberFormatException e){
            idCategoria = 0;
        }
        Producto producto = new Producto();
        producto.setCategoria(new Categoria());
        if(idCategoria >0){
            Optional<Producto> o = service.porId(idCategoria);
            if(o.isPresent()){
                producto = o.get();
            }
        }
        request.setAttribute("categorias", service.listarCategorias());
        request.setAttribute("productos", producto);
        getServletContext().getRequestDispatcher("/crearProducto.jsp").forward(request,response);
    }
}
