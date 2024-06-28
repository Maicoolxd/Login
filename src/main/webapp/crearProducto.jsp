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
    <style>
        body {
            background-color: black;
            color: white;
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            padding: 20px;
            box-sizing: border-box;
            overflow: hidden; /* Para evitar que el contenido se desborde */
        }
        .container {
            background-color: grey;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
            width: 100%;
            max-width: 600px;
            box-sizing: border-box;
            overflow-y: auto; /* Añadir scroll si el contenido es demasiado alto */
            max-height: 90vh; /* Para asegurarse de que el contenedor no sea más alto que la vista */
        }
        h3 {
            text-align: center;
            color: white;
            margin-bottom: 20px;
        }
        form div {
            margin-bottom: 10px;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        input[type="text"],
        input[type="number"],
        select {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #28a745; /* Verde */
            color: white;
            border: none;
            padding: 10px 20px;
            text-decoration: none;
            cursor: pointer;
            border-radius: 3px;
            width: 100%;
            box-sizing: border-box;
        }
        input[type="submit"]:hover {
            background-color: #218838; /* Verde oscuro */
        }
        .btn-back {
            background-color: #dc3545; /* Rojo */
            color: white;
            border: none;
            padding: 10px 20px;
            text-decoration: none;
            cursor: pointer;
            border-radius: 3px;
            width: 100%;
            box-sizing: border-box;
            display: inline-block;
            text-align: center;
        }
        .btn-back:hover {
            background-color: #c82333; /* Rojo oscuro */
        }
    </style>
</head>
<body>
<div class="container">
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
        <div style="display: flex; justify-content: space-between; width: 100%;">
            <a href="javascript:history.back()" class="btn-back">Regresar</a>
            <input type="submit" value="Crear Producto">
        </div>
    </form>
</div>
</body>
</html>
