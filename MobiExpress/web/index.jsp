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
        <form action="ValidarEmpleado" method="POST">
            <div class="Usuario">
                <input name="txtUser" type="text" placeholder="Usuario" required>
            </div>
            <div class="Usuario">
                <input name="txtPass" type="password" placeholder="Contraseña" required>
            </div>
            <div >
                <label class="Bienvenida">Bienvenido a MobiExpress</label>
            </div>
            <div>
                <label class="alerta" style="color:red;">${alerta}</label>
            </div>
            <input type="submit" name="accion" value="Ingresar"></input>
        </form>
        
    </div>
</body>
</html>
