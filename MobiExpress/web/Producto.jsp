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
            <div class="card col-sm-4 bg-primary">
                <div class="card-body">
                    <form action="Controlador?menu=Producto" method="POST" enctype="multipart/form-data">
                        <div class="form-group">
                            <label><strong>Producto:</strong></label>
                            <input type="text" value="${producto.getProducto()}" name="txtProducto" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Descripcion:</strong></label>
                            <input type="text" value="${producto.getDescripcion()}" name="txtDescripcion" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Costo Renta:</strong></label>
                            <input type="text" value="${producto.getCostoRenta()}" name="txtCostoRenta" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Existencia:</strong></label>
                            <input type="text" value="${producto.getExistencia()}" name="txtExistencia" class="form-control">
                        </div>
                        <div class="form-group">
                            <label><strong>Tamanio:</strong></label>
                            <input type="text" value="${producto.getTamanio()}" name="txtTamanio" class="form-control">
                        </div>
                        <label><strong>Cod. Categoria Producto:</strong></label>
                        <div class="form-group d-flex">
                            <div class="d-flex">
                                <input type="text" name="txtCodigoCategoria" value="${producto.getCodigoCategoriaProducto()}" class="form-control" placeholder="Codigo">
                                <button type="submit" name="accion" value="BuscarCategoria" class="btn-outline-info">Buscar</button>
                            </div>
                            <div>
                                <input type="text" name="txtCategoria" value="${categoriaProducto.getCategoria()}" class="form-control" placeholder="Categoria">
                            </div>
                        </div>
                        <div class="form-group">
                            <label><strong>Foto Producto:</strong></label>
                            <input type="file" id="imagen" accept="image/png,image/jpg" name="flImagenProductos" class="form-control">
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
                            <th>FOTO PRODUCTO</th>
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
                                <td>${producto.getCodigoProducto()}</td>
                                <td><img class="rounded" src="data:image/png;base64,${producto.getImgProducto()}" alt="60" width="100%" height="100"></td>
                                <td>${producto.getProducto()}</td>
                                <td>${producto.getDescripcion()}</td>
                                <td>${producto.getCostoRenta()}</td>
                                <td>${producto.getExistencia()}</td>
                                <td>${producto.getTamanio()}</td>
                                <td>${producto.getCodigoCategoriaProducto()}</td>
                                <td>
                                    <a class="btn btn-warning" href="Controlador?menu=Producto&accion=Editar&codigoProducto=${producto.getCodigoProducto()}">Editar</a>
                                    <a class="btn btn-danger" href="Controlador?menu=Producto&accion=Eliminar&codigoProducto=${producto.getCodigoProducto()}">Eliminar</a>
                                    <a class="btn btn-info" href="Controlador?menu=Producto&accion=AgregarACarrito&nombreProducto=${producto.getProducto()}&costoRenta=${producto.getCostoRenta()}&codigoProducto=${producto.getCodigoProducto()}">Carrito</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>