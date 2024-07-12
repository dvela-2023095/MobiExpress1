<%-- 
    Document   : index
    Created on : 09-jul-2024, 18:53:59
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Acces Page</title>
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
    <div class="pestaña">
        <h1>Inicia Sesión</h1>
        <img src="img/imagen-logo.png" height="300px" width="300px">
        <form action="Validar" method="POST">
            <div class="Usuario">
                <input name="txtUser" type="text" required>
                <label>Usuario</label>
            </div>
            <div class="Usuario">
                <input name="txtPass" type="password" required>
                <label>Contraseña</label>
            </div>
            <div class="Bienvenida">Bienvenido a MobiExpress</div>
            <input type="submit" name="accion" value="Ingresar"></input>
        </form>
        
    </div>
</body>
</html>
