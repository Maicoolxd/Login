<%@ page contentType="text/html;charset=UTF-8" language="java" import="org.maicol.login.models.*, org.maicol.login.repositories.ProductoRepositoryJdbcImpl, java.sql.Connection" %>
<% Carro carro = (Carro) session.getAttribute("carro"); %>
<html>
<head>
    <title>Carro de compras</title>
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
        .container {
            background-color: grey;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
            width: 100%;
            max-width: 800px;
            margin: 0 auto; /* Centrar el container horizontalmente */
            box-sizing: border-box;
            overflow-y: auto; /* Añadir scroll si el contenido es demasiado alto */
            max-height: 80vh; /* Para asegurarse de que el contenedor no sea más alto que la vista */
        }
        h1 {
            color: white;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid white; /* Líneas de tabla blancas */
            padding: 8px;
            text-align: center; /* Texto centrado */
        }
        th {
            background-color: #f2f2f2; /* Fondo gris claro para encabezados */
            color: black; /* Color de texto negro */
        }
        a {
            display: inline-block;
            margin: 10px;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
            color: white;
        }
        a.green {
            background-color: #28a745; /* Verde */
        }
        a.green:hover {
            background-color: #218838; /* Verde oscuro al pasar el cursor */
        }
        a.orange {
            background-color: #fd7e14; /* Naranja */
            color: white; /* Color de texto blanco */
        }
        a.orange:hover {
            background-color: #e07612; /* Naranja oscuro al pasar el cursor */
        }
        a.red {
            background-color: #dc3545; /* Rojo */
        }
        a.red:hover {
            background-color: #c82333; /* Rojo oscuro al pasar el cursor */
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Carro de compras</h1>

    <% if (carro == null || carro.getItems().isEmpty()) { %>
    <p>Lo sentimos, no hay productos en el carro de compras</p>
    <% } else { %>
    <table>
        <tr>
            <th>ID PRODUCTO</th>
            <th>NOMBRE</th>
            <th>PRECIO</th>
            <th>CANTIDAD</th>
            <th>SUBTOTAL</th>
        </tr>
        <% for (ItemCarro item : carro.getItems()) { %>
        <tr>
            <form action="<%= request.getContextPath() %>/EditarEnCarroServlet" method="post">
                <input type="hidden" name="idProducto" value="<%= item.getProducto().getIdProducto() %>">
                <td><%= item.getProducto().getIdProducto() %></td>
                <td><%= item.getProducto().getNombre() %></td>
                <td><%= item.getProducto().getPrecio() %></td>
                <td><input type="number" name="cantidad" value="<%= item.getCantidad() %>"></td>
                <td><%= item.getImporte() %></td>

            </form>
        </tr>
        <% } %>
        <tr>
            <td colspan="4">Total:</td>
            <td><%= carro.getTotal() %></td>
        </tr>
    </table>
    <% } %>

    <p>
        <a href="<%= request.getContextPath() %>/productos" class="green">Seguir comprando</a>
        <a href="<%= request.getContextPath() %>/index.html" class="red">Volver al carrito</a>
    </p>
</div>
</body>
</html>
