<%--
  Created by IntelliJ IDEA.
  User: USUARIO
  Date: 30/5/2024
  Time: 8:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ingresando al Sistema</title>
</head>
<body>
<h1>Iniciar sesion</h1>
<form action="Login" method="post"></form>
<div>
    <label for="username">Ingrese el nombre del usuario</label>
    <input type="text" name="username" id="username">
</div>
<div>
    <label for="password">Ingrese la contraseña</label>
    <input type="password" name="password" id="password">
</div>
<div>
    <input type="submit" value="Login">
</div>

</body>
</html>
