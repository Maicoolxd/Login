<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ingresando al Sistema</title>
    <style>
        body {
            background-color: #000000; /* Fondo negro */
            color: #ffffff; /* Letras blancas */
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .content {
            background-color: #444444; /* Fondo gris del contenido */
            text-align: center;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
            width: 300px;
            position: relative; /* Posición relativa para posicionar el botón */
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
        }
        label {
            margin-bottom: 10px;
        }
        input[type="text"],
        input[type="password"] {
            padding: 10px;
            margin-bottom: 20px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
        }
        input[type="submit"] {
            background-color: #007bff; /* Color azul del botón */
            color: #ffffff;
            padding: 10px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #0056b3; /* Azul más oscuro al pasar el ratón */
        }
        .return-button {
            position: absolute;
            top: 10px;
            right: 10px;
        }
        .return-button a {
            background-color: #dc3545; /* Color rojo del botón */
            color: #ffffff;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
            font-size: 16px;
        }
        .return-button a:hover {
            background-color: #c82333; /* Rojo más oscuro al pasar el ratón */
        }
    </style>
</head>
<body><div class="return-button">
    <a href="<%= request.getContextPath() %>/index.html">Regresar</a>
</div>
<div class="content">

    <form action="Login" method="post">
        <h1>Iniciar sesión</h1>
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
    </form>
</div>

</body>
</html>
