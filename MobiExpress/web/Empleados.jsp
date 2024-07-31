<%-- 
    Document   : Empleados
    Created on : 25/07/2024, 12:56:32 PM
    Author     : DELL
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Empleados</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
    </head>
    <body class="bg-light">
        <div class="d-flex">
            <div class="card col-sm-4">
                <div class="card-body">
                    <form action="Controlador?menu=Empleados" method="POST" enctype="multipart/form-data">
                        <div class="form-group">
                            <label><strong>DPI:</strong></label>
                            <input type="text" value="${empleado.getDPIEmpleado()}" name="txtDPIEmpleado" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Nombres:</strong></label>
                            <input type="text" value="${empleado.getNombresEmpleado()}" name="txtNombresEmpleado" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Apellidos:</strong></label>
                            <input type="text" value="${empleado.getApellidosEmpleado()}" name="txtApellidosEmpleado" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Telefono:</strong></label>
                            <input type="text" value="${empleado.getTelefonoEmpleado()}" name="txtTelefonoEmpleado" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Cod. Cargo:</strong></label>
                            <input type="text" value="${empleado.getCodigoCargoEmpleado()}" name="txtCodCargoEmpleado" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Usuario:</strong></label>
                            <input type="text" value="${empleado.getUsuario()}" name="txtUsuarioEmpleado" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Contraseña:</strong></label>
                            <input type="text" value="${empleado.getPasswor()}" name="txtContraEmpleado" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Foto de usuario:</strong></label>
                            <input type="file" id="imagen" accept="image/png,image/jpg" value="" name="flImagen" class="form-control">
                        </div>
                        <div class="form-group">
                            <label name="respuesta" class="text-danger">${respuesta}</label>
                        </div>
                        
                        <input type="submit" name="accion" value="Agregar" class="btn btn-info">
                        <input type="submit" name="accion" value="Actualizar" class="btn btn-success">
                    </form>
                </div>
            </div>
            <div class="col-sm-8">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>CODIGO</th>
                            <th>DPI</th>
                            <th>NOMBRES</th>
                            <th>APELLIDOS</th>
                            <th>TELÉFONO</th>
                            <th>CARGO</th>
                            <th>USUARIO</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="empleado" items="${listaEmpleados}">
                            <tr>
                                <td>${empleado.getCodigoEmpleado()}</td>
                                <td>${empleado.getDPIEmpleado()}</td>
                                <td>${empleado.getNombresEmpleado()}</td>
                                <td>${empleado.getApellidosEmpleado()}</td>
                                <td>${empleado.getTelefonoEmpleado()}</td>
                                <td>${empleado.getCodigoCargoEmpleado()}</td>
                                <td>${empleado.getUsuario()}</td>
                                <td>
                                    <a class="btn btn-warning" href="Controlador?menu=Empleados&accion=Editar&codigoEmpleado=${empleado.getCodigoEmpleado()}">Editar</a>
                                    <a class="btn btn-danger" href="Controlador?menu=Empleados&accion=Eliminar&codigoEmpleado=${empleado.getCodigoEmpleado()}">Eliminar</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
