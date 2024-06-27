<%@ page import="java.util.List" %>
<%@ page import="org.maicol.login.models.Categoria" %>
<%@ page import="org.maicol.login.repositories.CategoriaRepositoryJdbcImplement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="org.maicol.login.repositories.CategoriaRepositoryJdbcImplement" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*, org.maicol.login.models.*"%>
<%
    Connection conn = (Connection) request.getAttribute("conn");
    if (conn == null) {
        conn = (Connection) session.getAttribute("conn");
    }

    if (conn == null) {
        throw new NullPointerException("Conexión a la base de datos es nula.");
    }

    CategoriaRepositoryJdbcImplement categoriaService = new CategoriaRepositoryJdbcImplement(conn);
    List<Categoria> categorias = categoriaService.listar();

    if (categorias == null) {
        throw new NullPointerException("La lista de categorías es nula.");
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Crear Producto</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles.css">
</head>
<body>
<h3>Crear Producto</h3>
<form action="<%= request.getContextPath() %>/agregarProducto" method="post">
    <div>
        <label for="codigo">Código:</label>
        <input type="text" id="codigo" name="codigo" required>
    </div>
    <div>
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required>
    </div>
    <div>
        <label for="stock">Stock:</label>
        <input type="number" id="stock" name="stock" required>
    </div>
    <div>
        <label for="categoria">Categoría:</label>
        <select id="categoria" name="categoria" required>
            <% for (Categoria categoria : categorias) { %>
            <option value="<%= categoria.getIdCategoria() %>"><%= categoria.getNombre() %></option>
            <% } %>
        </select>
    </div>
    <div>
        <label for="descripcion">Descripción:</label>
        <input type="text" id="descripcion" name="descripcion" required>
    </div>
    <div>
        <label for="imagen">Imagen:</label>
        <input type="text" id="imagen" name="imagen" required>
    </div>
    <div>
        <label for="condicion">Condición:</label>
        <input type="number" id="condicion" name="condicion" required>
    </div>
    <div>
        <label for="precio">Precio:</label>
        <input type="number" id="precio" name="precio" required>
    </div>

    <div>
        <input type="submit" value="Crear Producto">
    </div>
</form>
</body>
</html>
