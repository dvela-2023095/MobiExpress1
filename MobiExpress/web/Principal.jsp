<%-- 
    Document   : Principal
    Created on : 25/07/2024, 12:01:08 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página Principal</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
    </head>
    <body class="bg-warning">
        <nav class="navbar navbar-expand-lg navbar-light bg-warning" >
            
            <div class="collapse navbar-collapse" id="navbarNav">
              <ul class="navbar-nav">
                <li class="nav-item active">
                  <a style="margin-left:  10px; border: none;" class="btn btn-outline-dark" href="Controlador?menu=Home" target="myFrame">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                  <a style="margin-left:  10px; border: none;" class="btn btn-outline-dark" class="nav-link" href="Controlador?menu=Empleados&accion=Listar" target="myFrame">Empleados</a>
                </li>
                <li class="nav-item">
                  <a style="margin-left:  10px; border: none;" class="btn btn-outline-dark" class="nav-link" href="Controlador?menu=Proveedores&accion=Listar"  target="myFrame">Proveedores</a>
                </li>
                <li class="nav-item">
                  <a style="margin-left:  10px; border: none;" class="btn btn-outline-dark" class="nav-link" href="Controlador?menu=Proveedores&accion=Listar"  target="myFrame">Proveedores</a>
                </li>
                <li class="nav-item">
                    <a style="margin-left:  10px; border: none;" class="btn btn-outline-dark" class="nav-link" href="Controlador?menu=Clientes&accion=Listar" target="myFrame">Clientes</a>
                </li>
                
              </ul>
            </div>
            <div class="dropdown" style="padding-right: 20px; float: left;">
                <button style="border: none;" class="btn btn-outline-light dropdown-toggle" type="button" id="dropdownButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  ${usuario.getNombresEmpleado()}
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownButton">
                    <a class="dropdown-item" href="#" >
                        <img class="rounded" src="data:image/png;base64,${usuario.getImagen()}" alt="60" width="100%" height="100">
                    </a>
                    <a class="dropdown-item" href=""></a>
                    <a class="text-center dropdown-item" href="">${usuario.getUsuario()}</a>
                    <div class="dropdown-divider"></div>
                    <form action="ValidarEmpleado" method="POST">
                        <button name="accion" value="Ingresar" class="text-center dropdown-item text-danger" href="#">Salir</button>
                    </form>
                </div>
            </div>

          </nav>
        <div class="m-4" style="height: 590px;">
                        <iframe name="myFrame" style="height: 100%; width: 100%; border: 0.5;"></iframe>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    </body>
</html>
