<%@ page contentType="text/html;charset=UTF-8" language="java" import="org.maicol.login.models.*" %>
<% Carro carro = (Carro) session.getAttribute("carro"); %>
<html>
<head>
    <title>Carro de compras</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
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
        <th>ACCIONES</th>
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
            <td>
                <button type="submit">Guardar</button>
            </td>
        </form>
        <td>
            <form action="<%= request.getContextPath() %>/EliminarDelCarroServlet" method="post">
                <input type="hidden" name="idProducto" value="<%= item.getProducto().getIdProducto() %>">
                <button type="submit">Eliminar</button>
            </form>
        </td>
    </tr>
    <% } %>
    <tr>
        <td colspan="4">Total:</td>
        <td><%= carro.getTotal() %></td>
    </tr>
</table>
<% } %>

<p><a href="<%= request.getContextPath() %>/productos">Seguir comprando</a></p>
<p><a href="<%= request.getContextPath() %>/index.html">Volver</a></p>

</body>
</html>
