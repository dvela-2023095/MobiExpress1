

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
        <div class="d-flex col-sm-12">
            <div class="col-sm-5">
                <form action="Controlador?menu=AgregarPedido" method="post">
                    <div class="card ">
                        <div class="form-group">
                        <label><strong>Datos del Pedido</strong></label>
                        </div>
                        <div class="form-group">
                            <label>Fecha de Entrega:</label>
                            <div class="col-sm-6 d-flex">
                                <input type="date" name="dtFecha Entrega" value="" class="form-control" placeholder="">
                                <button type="submit" name="accion" value="VerInventario" class="btn-outline-info col-sm-8">Ver Inventario</button>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>Fecha de Retorno:</label>
                            <div class="col-sm-6">
                                <input type="date" name="dtFecha Retorno" value="" class="form-control" placeholder="">
                            </div>
                        </div>
                        <div class="form-group d-flex">
                            <div class="col-sm-6 d-flex">
                                <input type="text" name="txtCodigoCliente" value="" class="form-control" placeholder="Cod. Cliente">
                                <button type="submit" name="accion" value="BuscarCliente" class="btn-outline-info">Buscar</button>
                            </div>
                            <div class="col-sm-6">
                                <input type="text" name="txtNombreCliente" value="" class="form-control" placeholder="Cliente">
                            </div>
                        </div>
                        <div class="form-group d-flex">
                            <div class="col-sm-6 d-flex">
                                <input type="text" name="txtCodigoEmpleado" value="" class="form-control" placeholder="Cod. Empleado">
                                <button type="submit" name="accion" value="BuscarEmpleado" class="btn-outline-info">Buscar</button>
                            </div>
                            <div class="col-sm-6">
                                <input type="text" name="txtNombreEmpleado" value="" class="form-control" placeholder="Empleado">
                            </div>
                        </div>
                        <div class="form-group ">
                            <div class="col-sm-6 d-flex">
                                <input type="text" name="txtDirección" value="${cantidad}" class="form-control" placeholder="Direccion de entrega">
                            </div>
                        </div>
                        <div class="d-flex">
                            <div class="form-group col-sm-5">
                            <input type="submit" value="Confirmar Pedido" name="accion" class="btn btn-success">
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
                                    <td>${list.getNombreProducto()}</td>
                                    <td>${list.getPrecioRenta()}</td>
                                    <td><input type="text" name="txtCantidad" placeholder="Disponible :${list.getCantidad()}"></td>
                                    <td>${list.getSubTotal()}</td>
                                    <td>${list.getDescuento()}</td>
                                    <td><input type="text" name="txtCodPedido" placeholder="${list.getNumeroPedido()}"></td>
                                    <td class="d-flex">
                                        <a href="Controlador?menu=AgregarPedido&accion=Eliminar&detalleAEliminar=${list}" class="btn btn-danger" style="margin-left: 10px;">Descartar</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class=" d-flex ">
                        <div class="col-sm-6 ">
                            <input type="text" name="txtTotal" value="Q." class="form-control">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
