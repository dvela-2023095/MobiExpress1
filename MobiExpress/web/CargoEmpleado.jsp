<%-- 
    Document   : CargoEmpleado
    Created on : 9/07/2024, 05:09:22 PM
    Author     : informatica
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cargo Empleado</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    </head>
    <body>
        <div class="d-flex">
            <div class="card col-sm-4">
                <div class="card-body">
                    <form action="Controlador?menu=CargoEmpleado" method="POST">
                        <div class="form-group">
                            <label><strong>Nombre:</strong></label>
                            <input type="text" value="${cargoEmpleado.getNombreCargo()}" name="txtNombreCargo" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Salario:</strong></label>
                            <input type="text" value="${cargoEmpleado.getSalario()}" name="txtSalario" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Descripción:</strong></label>
                            <input type="text" value="${cargoEmpleado.getDescripcionCargo()}" name="txtDescripcionCargo" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Jornada:</strong></label>
                            <input type="text" value="${cargoEmpleado.getJornada()}" name="txtJornada" class="form-control">
                        </div>
                        <input type="submit" name="accion" value="Agregar" class="btn btn-info">
                        <input type="submit" name="accion" value="Actualizar" class="btn btn-success">                  
                    </form>
                </div> <!-- cardbody -->
            </div><!-- card -->
            <div class="col-sm-8">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>CODIGO</th>
                            <th>NOMBRE</th>
                            <th>SALARIO</th>
                            <th>DESCRIPCION</th>
                            <th>JORNADA</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="cargo" items="${cargo}">
                        <tr>
                            <td>${cargo.getCodigoCargoEmpleado()}</td>
                            <td>${cargo.getNombreCargo()}</td>
                            <td>${cargo.getSalario()}</td>
                            <td>${cargo.getDescripcionCargo()}</td>
                            <td>${cargo.getJornada()}</td>
                            <td>
                                <a class="btn btn-warning" href="Controlador?menu=CargoEmpleado&accion=Editar&codigoCargoEmpleado=${cargo.getCodigoCargoEmpleado()}">Editar</a>
                                <a class="btn btn-danger" href="Controlador?menu=CargoEmpleado&accion=Eliminar&codigoCargoEmpleado=${cargo.getCodigoCargoEmpleado()}">Eliminar</a>
                            </td>        
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            
            
        </div>  <!-- dfkex -->
        
        
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>