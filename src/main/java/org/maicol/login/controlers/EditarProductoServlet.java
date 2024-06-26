package org.maicol.login.controlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.maicol.login.models.Categoria;
import org.maicol.login.models.Producto;
import org.maicol.login.services.ProductoService;
import org.maicol.login.services.ProductoServiceJdbcImplement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/editarProducto")
public class EditarProductoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener la sesión actual
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        // Verificar si el usuario es administrador o no
        if (!"ADMIN".equals(role)) {
            // Mostrar mensaje con estilo HTML
            String errorMessage = "<html><head><title>Error de permisos</title>" +
                    "<style>" +
                    "body { background-color: black; color: white; text-align: center; font-size: 24px; }" +
                    ".btn { background-color: red; color: white; padding: 10px 20px; text-decoration: none; }" +
                    "</style></head>" +
                    "<body>" +
                    "<h1>No tienes permisos para editar un producto.</h1>" +
                    "<a class='btn' href='" + request.getContextPath() + "/producto.jsp'>Regresar</a>" +
                    "</body></html>";
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(errorMessage);
            return;
        }

        // Si es administrador, continuar con el proceso de editar el producto
        // Obtener la conexión de la solicitud
        Connection conn = (Connection) request.getAttribute("conn");

        // Inicializar el servicio de productos con la conexión
        ProductoService service = new ProductoServiceJdbcImplement(conn);

        // Recuperar parámetros del formulario de edición de productos
        int id = Integer.parseInt(request.getParameter("id"));
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        String nombre = request.getParameter("nombre");
        int stock = Integer.parseInt(request.getParameter("stock"));
        int idCategoria = Integer.parseInt(request.getParameter("categoria"));
        String descripcion = request.getParameter("descripcion");
        String imagen = request.getParameter("imagen");
        int condicion = Integer.parseInt(request.getParameter("condicion"));
        double precio = Double.parseDouble(request.getParameter("precio"));

        // Validación de campos
        Map<String, String> errores = validarCampos(nombre, stock, idCategoria, descripcion, condicion, precio);

        if (!errores.isEmpty()) {
            // Si hay errores, devolver al formulario con mensajes de error
            request.setAttribute("errores", errores);
            request.getRequestDispatcher("/editarProducto.jsp").forward(request, response);
            return;
        }

        // Crear un objeto Producto con los datos del formulario
        Producto producto = new Producto();
        producto.setIdProducto(id);
        producto.setCodigo(codigo);
        producto.setNombre(nombre);
        producto.setStock(stock);

        // Crear una instancia de la categoría y asignarle el ID
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(idCategoria);
        producto.setCategoria(categoria);

        producto.setDescripcion(descripcion);
        producto.setImagen(imagen);
        producto.setCondicion(condicion);
        producto.setPrecio(precio);

        // Actualizar el producto usando el servicio
        try {
            service.actualizar(producto);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Redirigir de vuelta a la página de producto.jsp después de guardar
        response.sendRedirect(request.getContextPath() + "/producto.jsp");
    }

    private Map<String, String> validarCampos(String nombre, int stock, int idCategoria, String descripcion, int condicion, double precio) {
        Map<String, String> errores = new HashMap<>();

        if (nombre == null || nombre.isBlank()) {
            errores.put("nombre", "El campo nombre es requerido.");
        }
        if (stock <= 0) {
            errores.put("stock", "El campo stock debe ser mayor que cero.");
        }
        if (idCategoria <= 0) {
            errores.put("categoria", "Debe seleccionar una categoría válida.");
        }
        if (descripcion == null || descripcion.isBlank()) {
            errores.put("descripcion", "El campo descripción es requerido.");
        }
        if (condicion <= 0) {
            errores.put("condicion", "El campo condición debe ser mayor que cero.");
        }
        if (precio <= 0) {
            errores.put("precio", "El campo precio debe ser mayor que cero.");
        }

        return errores;
    }
}
