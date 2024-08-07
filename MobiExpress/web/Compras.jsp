<%-- 
    Document   : Compras
    Created on : 18/07/2024, 06:24:33 AM
    Author     : Ricardo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Compras</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
    </head>
    <body>
        <div class="d-flex" >
            <div class="col-sm-6" style="width: 50%">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>No. de Compra</th>
                            <th>Descripción</th>
                            <th>Monto Total</th>
                            <th>Fecha de Compra</th>
                            <th>Estado</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="compra" items="${listaDeCompras}">
                            <tr>
                                <td>${compra.getNumeroCompra()}</td>
                                <td>${compra.getDescripcion()}</td>
                                <td>${compra.getMontoTotal()}</td>
                                <td>${compra.getFechaDeCompra()}</td>
                                <td>${compra.getEstado()}</td>
                                <td class="d-block">
                                    <a class="btn btn-warning" href="">Editar</a>
                                    <a class="btn btn-danger" href="">Eliminar</a>
                                    <a class="btn btn-info" href="Controlador?menu=Compras&accion=Listar&accion2=Detalle&codCompra=${compra.getNumeroCompra()}">Detalles</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- Tabla de detalle compra-->
            <div class="col-sm-6" style="width: 50%">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Codigo Detalle</th>
                            <th>Producto</th>
                            <th>Cantidad</th>
                            <th>Costo unitario</th>
                            <th>Direccion</th>
                            <th>Sub Total</th>
                            <th>Fecha de Recepción</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="detalleComp" items="${listaDeDetalles}">
                            <tr>
                                <td>${detalleComp.getCodigoDetalleCompra()}</td>
                                <td>${detalleComp.getNombreProducto()}</td>
                                <td>${detalleComp.getCantidad()}</td>
                                <td>${detalleComp.getCosto()}</td>
                                <td>${detalleComp.getDireccion()}</td>
                                <td>${detalleComp.getSubTotal()}</td>
                                <td>${detalleComp.getFechaReception()}</td>
                                <td class="d-flex">
                                    <a class="btn btn-danger" href="">Eliminar</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    </body>
</html>
