<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Proveedores</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <style>
    </style>
    </head>
    <body>
        <div class="d-flex">
            <div class="card col-sm-4">
                <div class="card-body">
                    <form action="Controlador?menu=Proveedores" method="POST">
                        <div class="form-group">
                            <label><strong>NIT:</strong></label>
                            <input type="text" value="${proveedor.getNITProveedor()}" name="txtNITProveedor" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Nombre:</strong></label>
                            <input type="text" value="${proveedor.getNombreProveedor()}" name="txtNombreProveedor" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Apellido:</strong></label>
                            <input type="text" value="${proveedor.getApellidoProveedor()}" name="txtApellidoProveedor" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Razón Social:</strong></label>
                            <input type="text" value="${proveedor.getRazonSocial()}" name="txtRazonSocial" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Contacto Principal:</strong></label>
                            <input type="text" value="${proveedor.getContactoPrincipal()}" name="txtContactoPrincipal" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Página Web:</strong></label>
                            <input type="text" value="${proveedor.getPaginaWeb()}" name="txtPaginaWeb" class="form-control">
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
                            <th>CODIGO</th>
                            <th>NIT</th>
                            <th>NOMBRE</th>
                            <th>APELLIDO</th>
                            <th>RAZÓN SOCIAL</th>
                            <th>CONTACTO PRINCIPAL</th>
                            <th>PÁGINA WEB</th>
                            <th>ACCIONES</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Asegúrate de que este nombre "proveedores" coincide con el nombre que usas en el controlador -->
                        <c:forEach var="proveedor" items="${Proveedores}">
                            <tr>
                                <td>${proveedor.getCodigoProveedor()}</td>
                                <td>${proveedor.getNITProveedor()}</td>
                                <td>${proveedor.getNombreProveedor()}</td>
                                <td>${proveedor.getApellidoProveedor()}</td>
                                <td>${proveedor.getRazonSocial()}</td>
                                <td>${proveedor.getContactoPrincipal()}</td>
                                <td>${proveedor.getPaginaWeb()}</td>
                                <td>
                                    <a class="btn btn-warning" href="Controlador?menu=Proveedores&accion=Editar&codigoProveedor=${proveedor.getCodigoProveedor()}">Editar</a>
                                    <a class="btn btn-danger" href="Controlador?menu=Proveedores&accion=Eliminar&codigoProveedor=${proveedor.getCodigoProveedor()}">Eliminar</a>
                                     
                                </td>        
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div> <!-- d-flex -->
        
    </body>
</html>

