<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Pedidos</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body class="bg-primary">
    <div class="container mt-4">
        <div class="row">
            <div class=" col-11 d-flex">
            <!-- Tabla de compras -->
            <div class="col-sm-11">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>NO. PEDIDO</th>
                            <th>DIRECCION</th>
                            <th>MONTO TOTAL</th>
                            <th>ENTREGA</th>
                            <th>RETORNO</th>
                            <th>CLIENTE</th>
                            <th>EMPLEADO</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="pedido" items="${listaDePedidos}">
                            <tr>
                                <td>${pedido.getNumeroPedido()}</td>
                                <td>${pedido.getDireccion()}</td>
                                <td>${pedido.getMontoTotal()}</td>
                                <td>${pedido.getFechaDeEntrega()}</td>
                                <td>${pedido.getFechaDeRetorno()}</td>
                                <td>${pedido.getCliente()}</td>
                                <td>${pedido.getEmpleado()}</td>
                                <td>
                                    
                                    <a class="btn btn-danger" 
                                       href="Controlador?menu=Pedidos&accion=EliminarPedido&numeroPedido=${pedido.getNumeroPedido()}&accion2=default">Eliminar</a>
                                    <a class="btn btn-info" 
                                       href="Controlador?menu=Pedidos&accion=Listar&accion2=Detalle&numeroPedido=${pedido.getNumeroPedido()}">Detalles</a>
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
                            <th>PRODUCTO</th>
                            <th>PRECIO RENTA</th>
                            <th>CANTIDAD</th>
                            <th>DESCUENTO</th>
                            <th>SUBTOTAL</th>
                            
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="detallePed" items="${listaDeDetalles}">
                            <tr>
                                <td>${detallePed.getNombreProducto()}</td>
                                <td>${detallePed.getPrecioRenta()}</td>
                                <td>${detallePed.getCantidad()}</td>
                                <td>${detallePed.getDescuento()}</td>
                                <td>${detallePed.getSubTotal()}</td>
                                <td>
                                    <a class="btn btn-danger" 
                                       href="Controlador?menu=Pedidos&accion=Listar&numeroDetalle=${detallePed.getCodigoDetallePedido()}&accion2=Eliminar">Eliminar</a>
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
