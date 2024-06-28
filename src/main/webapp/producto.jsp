<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Optional" %>
<%@ page import="org.maicol.login.models.Producto" %>
<%@ page import="org.maicol.login.repositories.ProductoRepositoryJdbcImpl" %>
<%@ page import="org.maicol.login.services.ProductoService" %>
<%@ page import="org.maicol.login.services.ProductoServiceJdbcImplement" %>
<%@ page import="org.maicol.login.services.LoginService" %>
<%@ page import="org.maicol.login.services.LoginServiceImplement" %>

<%
    Connection conn = (Connection) request.getAttribute("conn");
    ProductoService service = new ProductoServiceJdbcImplement(conn);

    List<Producto> productos = service.listar();
    LoginService auth = new LoginServiceImplement();
    Optional<String> usernameOptional = auth.getUsername(request);

    response.setContentType("text/html;charset=UTF-8");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Listado de Productos</title>
    <style>
        body {

            background-color: black;
            color: white; /* Color de texto blanco */
            font-family: Arial, sans-serif; /* Fuente sans-serif */
            margin: 0;
            padding: 0;
        }
        .container {
            width: 100%;
            max-width: 1200px; /* Ajusta según el tamaño deseado */
            margin: 20px auto;
            padding: 20px;
            background-color: #343a40; /* Color gris oscuro */
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
            overflow-x: auto; /* Permite scroll horizontal en pantallas pequeñas */
        }
        h3 {
            text-align: center; /* Centra el título */
            color: #007bff; /* Color azul para encabezados */
            margin-bottom: 20px; /* Espacio inferior */
        }
        .btn-back {
            background-color: #dc3545; /* Color rojo */
            color: white;
            border: none;
            padding: 10px 20px;
            text-decoration: none;
            cursor: pointer;
            border-radius: 3px;
            float: right; /* Alinea a la derecha */
            margin-top: -40px; /* Ajuste para alinear con el borde superior */
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid white; /* Líneas de la tabla en blanco */
        }
        .button {
            color: white; /* Texto blanco */
            padding: 10px 20px; /* Espaciado interno */
            text-decoration: none; /* Sin subrayado */
            display: inline-block;
            margin: 5px; /* Espaciado externo */
            border-radius: 5px; /* Bordes redondeados */
            cursor: pointer; /* Cursor de mano */
        }
        .btn-new-product {
            background-color: #17a2b8; /* Color celeste */
        }
        .btn-new-product:hover {
            background-color: #138496; /* Celeste más oscuro */
        }
        .btn-add-cart {
            background-color: #28a745; /* Color verde */
        }
        .btn-add-cart:hover {
            background-color: #218838; /* Verde más oscuro */
        }
        .btn-edit {
            background-color: #fd7e14; /* Color naranja */
        }
        .btn-edit:hover {
            background-color: #e36f0c; /* Naranja más oscuro */
        }
        .btn-delete {
            background-color: #dc3545; /* Color rojo */
        }
        .btn-delete:hover {
            background-color: #c82333; /* Rojo más oscuro */
        }

        /* Estilos responsive */
        @media (max-width: 768px) {
            .container {
                padding: 10px;
            }
            th, td {
                padding: 5px;
            }
            .btn-back {
                margin-top: -20px;
                padding: 5px 10px;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <h3>Carrito de adidas</h3>
    <ul>
        <a href="<%= request.getContextPath() %>/index.html" class="btn-back">Regresar</a>

    </ul>


    <% if (usernameOptional.isPresent()) { %>
    <div style="color: white;">Hola <%= usernameOptional.get() %>, Bienvenido</div>
    <p><a class="button btn-new-product" href="<%= request.getContextPath() %>/crearProducto.jsp">Nuevo Producto</a></p>
    <% } %>

    <table>
        <thead>
        <tr>
            <th>IdArticulo</th>
            <th>IdCodigo</th>
            <th>Nombre</th>
            <th>Stock</th>
            <th>Categoria</th>
            <th>Descripcion</th>
            <th>Imagen</th>
            <th>Condicion</th>
            <% if (usernameOptional.isPresent()) { %>
            <th>Precio</th>
            <% } %>
            <% if (usernameOptional.isPresent()) { %>
            <th>Acciones</th>
            <% } %>
        </tr>
        </thead>
        <tbody>
        <% for (Producto p : productos) { %>
        <tr>
            <td><%= p.getIdProducto() %></td>
            <td><%= p.getCodigo() %></td>
            <td><%= p.getNombre() %></td>
            <td><%= p.getStock() %></td>
            <td><%= p.getCategoria().getIdCategoria() %></td>
            <td><%= p.getDescripcion() %></td>
            <td><%= p.getImagen() %></td>
            <td><%= p.getCondicion() %></td>

            <% if (usernameOptional.isPresent()) { %>
            <td><%= p.getPrecio() %></td>
            <td>
                <a class="button btn-add-cart" href="<%= request.getContextPath() %>/agregar-carro?id=<%= p.getIdProducto() %>">Agregar </a>
                <br>
                <a class="button btn-edit" href="<%= request.getContextPath() %>/editarProducto.jsp?id=<%= p.getIdProducto() %>">Editar</a>
                <br>
                <a class="button btn-delete" href="<%= request.getContextPath() %>/eliminarProducto?id=<%= p.getIdProducto() %>" onclick="return confirm('¿Estás seguro de que quieres eliminar este producto?')">Eliminar</a>
            </td>
            <% } %>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>
