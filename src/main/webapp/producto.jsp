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
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="container">
    <h3>Carrito de adidas</h3>
    <ul>
        <input type="button" value="Regresar" class="btn" onclick="window.history.back()"/>
    </ul>

    <% if (usernameOptional.isPresent()) { %>
    <div style="color:blue;">Hola <%= usernameOptional.get() %>, Bienvenido</div>
    <p><a href="<%=request.getContextPath()%>/crearProducto.jsp">Nuevo Producto</a></p>
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
            <td><a href="<%= request.getContextPath() %>/agregar-carro?id=<%= p.getIdProducto() %>">agregar al carro</a>
            <a href="<%= request.getContextPath() %>/editarProducto.jsp?id=<%= p.getIdProducto() %>">Editar</a>
            <a href="<%= request.getContextPath() %>/EliminarProductoServlet?id=<%= p.getIdProducto() %>">Eliminar</a></td>
            <% } %>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>
