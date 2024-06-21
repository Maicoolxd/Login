<%--
  Created by IntelliJ IDEA.
  User: USUARIO
  Date: 20/6/2024
  Time: 8:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*, org.maicol.login.models.*"%>
<%
    List<Categoria> categorias = (List<Categoria>)  request.getAttribute("categorias");
    Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
    Producto producto = (Producto) request.getAttribute("producto");
%>Z
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Crear Producto</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles.css">
</head>
<body>
<div class="container">
    <h3>Crear Producto</h3>
    <form action="<%= request.getContextPath() %>/crearProducto.jsp" method="post">
        <label for="codigo">Codigo:</label>
        <input type="text" id="codigo" name="codigo" required><br>
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required><br>
        <label for="stock">Stock:</label>
        <input type="number" id="stock" name="stock" required><br>
        <label for="categoria">Categoria</label>
        <select id="categoria" name="categoria" required>
                    <option value="">---Seleccionar---</option>
                    <% for (Categoria c: categorias){ %>
                    <option value="<%=c.getIdCategoria()%>"><%=c.getNombre()%></option><% } %>
         </select><br>

        <label for="descripcion">Descripción:</label>
        <textarea id="descripcion" name="descripcion" required></textarea><br>
        <label for="imagen">Imagen:</label>
        <input type="text" id="imagen" name="imagen" required><br>
        <label for="condicion">Condición:</label>
        <input type="text" id="condicion" name="condicion" required><br>
        <label for="precio">Precio:</label>
        <input type="number" step="0.01" id="precio" name="precio" required><br>
        <br>
        <input type="submit" value="Crear Producto">
    </form>
</div>
</body>
</html>
