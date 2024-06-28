<%@ page contentType="text/html;charset=UTF-8" language="java" import="org.maicol.login.models.*, org.maicol.login.repositories.ProductoRepositoryJdbcImpl, java.sql.Connection" %>
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
    <style>
        body {
            background-color: black;
            color: white;
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 0;
            padding: 20px;
            box-sizing: border-box;
        }
        h3 {
            color: #007bff;
            margin-bottom: 20px;
        }
        form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        div {
            margin-bottom: 10px;
            width: 100%;
            max-width: 400px;
            text-align: left;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        input[type="text"],
        input[type="number"] {
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
            margin-top: 20px;
        }
        input[type="submit"]:hover {
            background-color: #218838; /* Verde oscuro al pasar el cursor */
        }
        a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
        }
        a.red {
            background-color: #dc3545; /* Rojo */
            color: white;
        }
        a.red:hover {
            background-color: #c82333; /* Rojo oscuro al pasar el cursor */
        }
    </style>
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

<a href="<%= request.getContextPath() %>/index.html" class="red">Regresar</a>

</body>
</html>
