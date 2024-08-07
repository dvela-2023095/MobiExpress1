

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
    </head>
    <body>
        <div class="d-flex col-sm-12 ">
            <div class="col-sm-5 ">
                <form action="Controlador?menu=AgregarPedido" method="post">
                    <div class="card bg-primary ">
                        <div class="form-group">
                        <label><strong>Datos del Pedido</strong></label>
                        </div>
                        <div class="form-group">
                            <label><strong>Fecha de Entrega:</strong></label>
                            <div class="col-sm-6 d-flex">
                                <input type="date" name="txtFechaEntrega" value="${fechae}" class="form-control" >
                                <button type="submit" name="accion" value="VerInventario" class="btn-outline-info col-sm-8">Ver Inventario</button>
                            </div>
                        </div>
                        <label><strong>Establecer Cantidad y Descuento:</strong></label>
                        <div class="form-group d-flex">
                            <div class="col-sm-6 d-flex">
                                <input type="text" name="txtCodProducto" value="" class="form-control" placeholder="Cod. Producto">
                            </div>
                            <div class="col-sm-6 d-flex">
                                <input type="text" name="txtCantidad" value="" class="form-control" placeholder="Cantidad">
                                <button type="submit" name="accion" value="Establecer" class="btn-outline-info">Establecer</button>
                            </div>
                        </div>
                        <div class="col-sm-6 d-flex">
                                <input type="text" name="txtDescuento" value="" class="form-control" placeholder="Descuento">
                        </div>
                        <div class="form-group">
                            <label><strong>Fecha de Retorno:</strong></label>
                            <div class="col-sm-6">
                                <input type="date" name="txtFechaRetorno" value="${fechar}" class="form-control" placeholder="">
                            </div>
                        </div>
                        <div class="form-group d-flex">
                            <div class="col-sm-6 d-flex">
                                <input type="text" name="txtCodigoCliente" value="${pedido.getCodigoCliente()}" class="form-control" placeholder="Cod. Cliente">
                                <button type="submit" name="accion" value="BuscarCliente" class="btn-outline-info">Buscar</button>
                            </div>
                            <div class="col-sm-6">
                                <input type="text" name="txtNombreCliente" value="${cliente}" class="form-control" placeholder="Cliente">
                            </div>
                        </div>
                        <div class="form-group d-flex">
                            <div class="col-sm-6 d-flex">
                                <input type="text" name="txtCodigoEmpleado" value="${pedido.getCodigoEmpleado()}" class="form-control" placeholder="Cod. Empleado">
                                <button type="submit" name="accion" value="BuscarEmpleado" class="btn-outline-info">Buscar</button>
                            </div>
                            <div class="col-sm-6">
                                <input type="text" name="txtNombreEmpleado" value="${empleado}" class="form-control" placeholder="Empleado">
                            </div>
                        </div>
                        <div class="form-group ">
                            <div class="col-sm-6 d-flex">
                                <input type="text" name="txtDireccion" value="${pedido.getDireccion()}" class="form-control" placeholder="Direccion de entrega">
                            </div>
                        </div>
                        <div class="d-flex">
                            <div class="form-group col-sm-5">
                            <input type="submit" value="ConfirmarPedido" name="accion" class="btn btn-success">
                            </div>
                            <div class="form-group col-sm-3 ">
                                <input type="submit" value="Cancelar Pedido" name="accion" class="btn btn-warning">
                            </div>
                        </div>
                    </div>
                </form>
                
            </div>
            <div class="col-sm-6">
                <div class="">
                    <div class="">
                        
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <td><Strong>COD.PRODUCTO</Strong></td>
                                    <td><Strong>PRODUCTO</Strong></td>
                                    <td><Strong>PRECIO RENTA</Strong></td>
                                    <td><Strong>CANTIDAD</Strong></td>
                                    <td><Strong>SUBTOTAL</Strong></td>
                                    <td><Strong>DESCUENTO</Strong></td>
                                    <td><Strong>COD. PEDIDO</Strong></td>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="list" items="${listaDeDetalles}">
                                <tr>
                                    <td>${list.getCodigoProducto()}</td>
                                    <td>${list.getNombreProducto()}</td>
                                    <td>${list.getPrecioRenta()}</td>
                                    <td>${list.getCantidad()}</td>
                                    <td>${list.getSubTotal()}</td>
                                    <td>${list.getDescuento()}</td>
                                    <td><input type="text" name="txtCodPedido" placeholder="${list.getNumeroPedido()}"></td>
                                    <td class="d-flex">
                                        <a href="Controlador?menu=AgregarPedido&accion=MeterCantidad&detalle=${list}" class="btn btn-danger" style="margin-left: 10px;">Establecer Cantidad</a>
                                        <a href="Controlador?menu=AgregarPedido&accion=Eliminar&detalleAEliminar=${list}" class="btn btn-danger" style="margin-left: 10px;">Descartar</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class=" d-flex ">
                        <div class="col-sm-6 ">
                            <input type="text" name="txtTotal" value="Q.${total}" class="form-control">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
