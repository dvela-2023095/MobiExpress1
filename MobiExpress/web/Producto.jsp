<%-- 
    Document   : Producto
    Created on : Aug 3, 2024, 11:57:25 PM
    Author     : PC
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Producto</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
    </head>
    <body class="bg-light">
        <div class="d-flex">
            <div class="card col-sm-4">
                <div class="card-body">
                    <form action="Controlador?menu=Producto" method="POST" enctype="multipart/form-data">
                        <div class="form-group">
                            <label><strong>Producto:</strong></label>
                            <input type="text" value="${producto.producto}" name="txtProducto" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Descripcion:</strong></label>
                            <input type="text" value="${producto.descripcion}" name="txtDescripcion" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Costo Renta:</strong></label>
                            <input type="text" value="${producto.costoRenta}" name="txtCostoRenta" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Existencia:</strong></label>
                            <input type="text" value="${producto.existencia}" name="txtExistencia" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Tamanioo:</strong></label>
                            <input type="text" value="${producto.tamanio}" name="txtTamanio" class="form-control">
                        </div>
                        <label><strong>Cod. Categoria Producto:</strong></label>
                        <div class="form-group d-flex">
                            <div class="d-flex">
                                <input type="text" name="txtCodigoCategoria" value="${producto.codigoCategoriaProducto}" class="form-control" placeholder="Codigo">
                                <button type="submit" name="accion" value="BuscarCategoria" class="btn-outline-info">Buscar</button>
                            </div>
                            <div>
                                <input type="text" name="txtCategoria" value="${categoriaProducto.getCategoria()}" class="form-control" placeholder="Categoria">
                            </div>
                        </div>
                        <div class="form-group">
                            <label name="respuesta" class="text-danger">${respuesta}</label>
                        </div>
                        
                        <input type="submit" name="accion" value="Agregar" class="btn btn-info">
                        <input type="submit" name="accion" value="Actualizar" class="btn btn-success">                   
                    </form>
                </div>
            </div>
            <div class="col-sm-6">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>CODIGO</th>
                            <th>PRODUCTO</th>
                            <th>DESCRIPCION</th>
                            <th>COSTO RENTA</th>
                            <th>EXISTENCIA</th>
                            <th>TAMANIO</th>
                            <th>COD.CATEGORIA PRODUCTO</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="producto" items="${listaProducto}">
                            <tr>
                                <td>${producto.codigoProducto}</td>
                                <td>${producto.producto}</td>
                                <td>${producto.descripcion}</td>
                                <td>${producto.costoRenta}</td>
                                <td>${producto.existencia}</td>
                                <td>${producto.tamanio}</td>
                                <td>${producto.codigoCategoriaProducto}</td>
                                <td>
                                    <a class="btn btn-warning" href="Controlador?menu=Producto&accion=Editar&codigoProducto=${producto.codigoProducto}">Editar</a>
                                    <a class="btn btn-danger" href="Controlador?menu=Producto&accion=Eliminar&codigoProducto=${producto.codigoProducto}">Eliminar</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>