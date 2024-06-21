<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.maicol.login.models.Producto" %>
<%
    Producto producto = (Producto) request.getAttribute("producto");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Editar Producto</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles.css">
</head>
<body>
<div class="container">
    <h3>Editar Producto</h3>
    <form action="<%= request.getContextPath() %>/EditarProducto" method="post">
        <input type="hidden" name="id" value="<%= producto.getIdProducto() %>">
        <label for="codigo">Código:</label>
        <input type="text" id="codigo" name="codigo" value="<%= producto.getCodigo() %>" required><br>
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" value="<%= producto.getNombre() %>" required><br>
        <label for="stock">Stock:</label>
        <input type="number" id="stock" name="stock" value="<%= producto.getStock() %>" required><br>
        <!--<label for="categoria">Categoría:</label>
        <input type="text" id="categoria" name="categoria" value="<%= producto.getCategoria() %>" required><br>-->
        <label for="descripcion">Descripción:</label>
        <textarea id="descripcion" name="descripcion" required><%= producto.getDescripcion() %></textarea><br>
        <label for="imagen">Imagen:</label>
        <input type="text" id="imagen" name="imagen" value="<%= producto.getImagen() %>" required><br>
        <label for="condicion">Condición:</label>
        <input type="text" id="condicion" name="condicion" value="<%= producto.getCondicion() %>" required><br>
        <label for="precio">Precio:</label>
        <input type="number" step="0.01" id="precio" name="precio" value="<%= producto.getPrecio() %>" required><br>
        <input type="submit" value="Editar">
    </form>
</div>
</body>
</html>
