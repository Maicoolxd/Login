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
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="container">
    <h3>Editar Producto</h3>
    <form action="<%= request.getContextPath() %>/EditarProducto" method="post">
        <input type="hidden" name="id" value="<%= request.getParameter("id") %>">
        <label for="codigo">Código:</label>
        <input type="text" id="codigo" name="codigo" value="<%= producto.getCodigo() %>" required><br>
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" value="<%= producto.getNombre() %>" required><br>
        <label for="stock">Stock:</label>
        <input type="number" id="stock" name="stock" value="<%= producto.getStock() %>" required><br>
        <label for="categoria">Categoría:</label>
        <select id="categoria" name="categoria" required>
            <option value="<%= producto.getCategoria().getIdCategoria() %>"><%= producto.getCategoria().getNombre() %></option>

        </select><br>
        <label for="descripcion">Descripción:</label><br>
        <textarea id="descripcion" name="descripcion" rows="4" cols="50"><%= producto.getDescripcion() %></textarea><br>
        <label for="imagen">URL Imagen:</label>
        <input type="text" id="imagen" name="imagen" value="<%= producto.getImagen() %>"><br>
        <label for="condicion">Condición:</label>
        <input type="number" id="condicion" name="condicion" value="<%= producto.getCondicion() %>" required><br>
        <label for="precio">Precio:</label>
        <input type="number" id="precio" name="precio" value="<%= producto.getPrecio() %>" step="0.01" required><br>
        <input type="submit" value="Guardar Cambios">
    </form>
    <p><a href="<%= request.getContextPath() %>/carro.jsp">Volver al Carro</a></p>
</div>
</body>
</html>