<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Compras</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body class="bg-primary">
    <div class="container mt-4">
        <div class="row">
            <div class="d-flex">
            <!-- Tabla de compras -->
            <div class="col-sm-6">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>NO. DE COMPRA</th>
                            <th>DESCRIPCION</th>
                            <th>MONTO TOTAL</th>
                            <th>FECHA DE COMPRA</th>
                            <th>ESTADO</th>
                            <th>ACCIONES</th>
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
                                <td>
                                    <a class="btn btn-warning" href="Controlador?menu=AgregarCompra&accion=Listar">Editar</a>
                                    <a class="btn btn-danger" 
                                       href="Controlador?menu=Compras&accion=Eliminar&numeroCompra=${compra.getNumeroCompra()}&accion2=default">Eliminar</a>
                                    <a class="btn btn-info" 
                                       href="Controlador?menu=Compras&accion=Listar&accion2=Detalle&codCompra=${compra.getNumeroCompra()}">Detalles</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- Tabla de detalle compra -->
            <div class="col-sm-6">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>CODIGO</th>
                            <th>PRODUCTO</th>
                            <th>CANTIDAD</th>
                            <th>COSTO UNITARIO</th>
                            <th>DIRECCION</th>
                            <th>SUB TOTAL</th>
                            <th>FECHA DE RECEPCION</th>
                            <th>ACCIONES</th>
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
                                <td>${detalleComp.getFechaRecepcion()}</td>
                                <td>
                                    <a class="btn btn-danger" 
                                       href="Controlador?menu=Compras&accion=EliminarDetalle&numeroCompra=${detalleComp.getNumeroCompra()}&accion2=default">Eliminar</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    </div>    
</body>
</html>

