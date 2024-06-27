<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Eliminar Producto</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="container">
    <h3>Confirmar Eliminación</h3>
    <p>¿Está seguro que desea eliminar el producto seleccionado?</p>
    <form action="<%= request.getContextPath() %>/EliminarProductoServlet" method="post">
        <input type="hidden" name="id" value="<%= request.getParameter("id") %>">
        <input type="submit" value="Confirmar Eliminación">
    </form>
    <p><a href="<%= request.getContextPath() %>/carro.jsp">Cancelar</a></p>
</div>
</body>
</html>
