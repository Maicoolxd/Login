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
import java.util.HashMap;
import java.util.Map;
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //obtememos la conexion
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImplement(conn);
        String nombre = req.getParameter("nombre");
        Integer idCategoria;
        try{
           idCategoria = Integer.parseInt(req.getParameter("categoria"));
        }catch (NumberFormatException e){
            idCategoria=0;
        }
        String descripcion = req.getParameter("descripcion");
        double precio;
        try {
            precio = Double.parseDouble(req.getParameter("precio"));
        }
        catch (NumberFormatException e){
            precio=0;
        }
        Integer idProducto;
        try {
            idProducto= Integer.parseInt(req.getParameter("id"));
        }catch (NumberFormatException e){
            idProducto=0;
        }
        //verificamos que los campos vacios o nulos nos muestre un mensaje
        Map<String, String> errores = new HashMap<>();
        if(nombre == null || nombre.isBlank()){
            errores.put("nombre","El campo nombre debe ser requerido");
        }
        if(descripcion == null || descripcion.isBlank()){
            errores.put("descripcion","El campo descripcion debe ser requerido");
        }
        if(precio == 0){
            errores.put("precio","El campo precio debe ser requerido");
        }
        if(idCategoria.equals(0)){
            errores.put("categoria", "El campo categoria debe ser requerido");
        }

        Producto producto = new Producto();
        producto.setIdProducto(idProducto);
        producto.setNombre(nombre);
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(idCategoria);
        producto.setCategoria(categoria);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        //si el error esta vacio me va permitir guardar el producto
        if(errores.isEmpty()){
            service.guardar(producto);
            resp.sendRedirect("/productos");
        }else {
            req.setAttribute("errores", errores);
            req.setAttribute("categorias", service.listarCategorias());
            req.setAttribute("producto", producto);
            getServletContext().getRequestDispatcher("/crearProducto.jsp").forward(req, resp);
        }
    }
}
