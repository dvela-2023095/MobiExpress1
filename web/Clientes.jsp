<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Clientes</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>
    <div class="container mt-4">
        <!-- Mostrar mensaje de respuesta -->
        <c:if test="${not empty respuesta}">
            <div class="alert alert-warning">
                ${respuesta}
            </div>
        </c:if>

        <div class="d-flex">
            <div class="card col-sm-4">
                <div class="card-body">
                    <form action="Controlador?menu=Clientes" method="POST">
                        <div class="form-group">
                            <label><strong>NIT:</strong></label>
                            <input type="text" value="${cliente.getNITCliente()}" name="txtNITCliente" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Nombres:</strong></label>
                            <input type="text" value="${cliente.getNombresCliente()}" name="txtNombresCliente" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Apellidos:</strong></label>
                            <input type="text" value="${cliente.getApellidosCliente()}" name="txtApellidosCliente" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Dirección:</strong></label>
                            <input type="text" value="${cliente.getDireccionCliente()}" name="txtDireccionCliente" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Teléfono:</strong></label>
                            <input type="text" value="${cliente.getTelefonoCliente()}" name="txtTelefonoCliente" class="form-control">
                        </div>
                        <input type="submit" name="accion" value="Agregar" class="btn btn-info">
                        <input type="submit" name="accion" value="Actualizar" class="btn btn-success">
                    </form>
                </div> <!-- card-body -->
            </div><!-- card -->
            <div class="col-sm-8">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>CÓDIGO</th>
                            <th>NIT</th>
                            <th>NOMBRES</th>
                            <th>APELLIDOS</th>
                            <th>DIRECCIÓN</th>
                            <th>TELÉFONO</th>
                            <th>ACCIONES</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="cliente" items="${clientes}">
                            <tr>
                                <td>${cliente.getCodigoCliente()}</td>
                                <td>${cliente.getNITCliente()}</td>
                                <td>${cliente.getNombresCliente()}</td>
                                <td>${cliente.getApellidosCliente()}</td>
                                <td>${cliente.getDireccionCliente()}</td>
                                <td>${cliente.getTelefonoCliente()}</td>
                                <td>
                                    <a class="btn btn-warning" href="Controlador?menu=Clientes&accion=Editar&codigoCliente=${cliente.getCodigoCliente()}">Editar</a>
                                    <a class="btn btn-danger" href="Controlador?menu=Clientes&accion=Eliminar&codigoCliente=${cliente.getCodigoCliente()}">Eliminar</a>
                                </td>        
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div> <!-- d-flex -->
    </div> <!-- container -->

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
</body>
</html>
