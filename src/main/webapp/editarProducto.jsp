<%@ page contentType="text/html;charset=UTF-8" language="java" import="org.maicol.login.models.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="org.maicol.login.repositories.ProductoRepositoryJdbcImpl" %>
<%@ page import="org.maicol.login.models.Producto" %>

<%
    int id = Integer.parseInt(request.getParameter("id"));
    Connection conn = (Connection) request.getAttribute("conn");
    if (conn == null) {
        conn = (Connection) session.getAttribute("conn");
    }

    ProductoRepositoryJdbcImpl productoRepo = new ProductoRepositoryJdbcImpl(conn);
    Producto producto = productoRepo.porId(id);

    if (producto == null) {
        throw new NullPointerException("Producto no encontrado.");
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Editar Producto</title>
</head>
<body>
<h3>Editar Producto</h3>
<form action="<%= request.getContextPath() %>/EditarProducto" method="post">
    <input type="hidden" name="id" value="<%= producto.getIdProducto() %>">
    <div>
        <label for="codigo">Código:</label>
        <input type="text" id="codigo" name="codigo" value="<%= producto.getCodigo() %>" required>
    </div>
    <div>
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" value="<%= producto.getNombre() %>" required>
    </div>
    <div>
        <label for="stock">Stock:</label>
        <input type="number" id="stock" name="stock" value="<%= producto.getStock() %>" required>
    </div>
    <div>
        <label for="categoria">Categoría:</label>
        <input type="text" id="categoria" name="categoria" value="<%= producto.getCategoria().getIdCategoria() %>" required>
    </div>
    <div>
        <label for="descripcion">Descripción:</label>
        <input type="text" id="descripcion" name="descripcion" value="<%= producto.getDescripcion() %>" required>
    </div>
    <div>
        <label for="imagen">Imagen:</label>
        <input type="text" id="imagen" name="imagen" value="<%= producto.getImagen() %>" required>
    </div>
    <div>
        <label for="condicion">Condición:</label>
        <input type="number" id="condicion" name="condicion" value="<%= producto.getCondicion() %>" required>
    </div>
    <div>
        <label for="precio">Precio:</label>
        <input type="number" id="precio" name="precio" value="<%= producto.getPrecio() %>" required>
    </div>
    <div>
        <input type="submit" value="Guardar Cambios">
    </div>
</form>
</body>
</html>
