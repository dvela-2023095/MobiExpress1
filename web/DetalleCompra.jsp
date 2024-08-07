<%-- 
    Document   : DetalleCompra
    Created on : 10/07/2024, 09:29:26 PM
    Author     : Hp
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    </head>
    <body>
        <div class="d-flex col-sm-12">
            <div class="col-sm-5">
                <form action="Controlador?menu=AgregarCompra" method="post">
                    <div class="card ">
                        <div class="form-group">
                        <label><strong>Detalles de la Compra</strong></label>
                        </div>
                        <div class="form-group d-flex">
                            <div class="col-sm-6 d-flex">
                                <input type="text" name="txtCodigoProveedor" value="${proveedores.getCodigoProveedor()}" class="form-control" placeholder="Cod. Proveedor">
                                <button type="submit" name="accion" value="BuscarProveedor" class="btn-outline-info">Buscar</button>
                            </div>
                            <div class="col-sm-6">
                                <input type="text" name="txtNombreProveedor" value="${proveedores.getNombreProveedor()}" class="form-control" placeholder="Proveedor">
                            </div>
                        </div>
                        <div class="form-group d-flex">
                            <div class="col-sm-6 d-flex">
                                <input type="text" name="txtCodigoProducto" value="${producto.getCodigoProducto()}" class="form-control" placeholder="Cod.Producto">
                                <button type="submit" name="accion" value="BuscarProducto" class="btn-outline-info">Buscar</button>
                            </div>
                            <div class="col-sm-6">
                                <input type="text" name="txtNombreProducto" value="${producto.getDescripcion()}" class="form-control" placeholder="Producto">
                            </div>
                        </div>
                        <div class="form-group d-flex">
                            <div class="col-sm-6 d-flex">
                                <input type="text" name="txtCodigoCompra" value="${compras.getNumeroCompra()}" class="form-control" placeholder="No. Compra">
                                <button type="submit" name="accion" value="BuscarCompra" class="btn-outline-info">Buscar</button>
                            </div>
                            <div class="col-sm-6">
                                <input type="text" name="txtDescripcionCompra" value="${compras.getDescripcion()}" class="form-control" placeholder="Compra">
                            </div>
                        </div>
        
        
                        <div class="form-group d-flex">
                            <div class="col-sm-6 d-flex">
                                <input type="text" name="txtPrecio"  value="${producto.getCostoRenta()}" class="form-control" placeholder="Precio Unitario">
                            </div>
                            <div class="col-sm-4">
                                <input type="text" name="txtCantidad" placeholder="Cantidad" class="form-control">
                            </div>
                        </div>
                        <div class="form-group d-flex">
                            <div class="col-sm-6 d-flex">
                                <input type="text" name="txtDireccion" value="${detalleCompra.getDireccion()}" class="form-control" placeholder="Direccion de compra">
                            </div>
                            <div class="col-sm-6">
                                <input type="date" name="txtFechaRecepcion" value="${detalleCompra.getFechaRecepcion()}" class="form-control" placeholder="Fecha de Recepción">
                            </div>
                        </div> 
                        <div class="form-group">
                            
                            <input type="submit" name="accion" value="AgregarDetalles" class="btn btn-success">
                        </div>
                    </div>
                </form>
                <form action="Controlador?menu=AgregarCompra" method="POST">
                    <div class="">
                        <div class="form-group">
                            <label><strong>Compra:</strong></label>
                            <input type="text" value="" name="txtDescripcionCompra" class="form-control" placeholder="Descripcion de la Compra" >
                            
                        </div>
                        <label ><strong>Fecha de Compra:</strong></label>
                        <div class="form-group d-flex">
                            <div class="col-sm-6">
                                <input type="date" name="txtFechaCompra" value="" class="form-control">
                            </div>
                            <div class="col-sm-6 d-flex">
                                <input type="text" name="txtEstado" value="" class="form-control" placeholder="Estado de Compra">
                            </div>
                        </div> 
                        <div class="form-group d-flex">
                            <div class="col-sm-5">
                                <input type="submit" value="Agregar Compra" name="accion" class="btn btn-info">
                            </div>
                            <div class="col-sm-5">
                                <input type="submit" value="Actualizar Compra" name="accion" class="btn btn-success">
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
                                    <td><strong>COD. DETALLE</strong></td>
                                    <td><Strong>PROVEEDOR</Strong></td>
                                    <td><Strong>PRODUCTO</Strong></td>
                                    <td><Strong>COMPRA</Strong></td>
                                    <td><Strong>PRECIO</Strong></td>
                                    <td><Strong>CANTIDAD</Strong></td>
                                    <td><Strong>SUB-TOTAL</Strong></td>
                                    <td><Strong>DIRECCION DE COMPRA</Strong></td>
                                    <td><Strong>FECHA DE RECEPCIÓN</Strong></td>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="list" items="${listaDeComp}">
                                    <tr>
                                        <td>${list.getItem()}</td>
                                        <td>${list.getCodigoProveedor()}</td>
                                        <td>${list.getCodigoProducto()}</td>
                                        <td>${list.getNumeroCompra()}</td>
                                        <td>${list.getCosto()}</td>
                                        <td>${list.getCantidad()}</td>
                                        <td>${list.getSubTotal()}</td>
                                        <td>${list.getDireccion()}</td>
                                        <td>${list.getFechaRecepcion()}</td>
                                        <td class="d-flex">
                                            <a href="Controlador?menu=AgregarCompra&accion=Eliminar&item=${list.getItem()}" class="btn btn-danger" style="margin-left: 10px;">Eliminar</a>
                                        </td>
                                    </tr>
                                    
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                     <form action="Controlador?menu=AgregarCompra" method="POST">
                    <div class=" d-flex ">
                        <div class="col-sm-6">
                            <input type="submit" name="accion" value="Guardar Datos" class="btn btn-success">
                            <input type="submit" value="Cancelar" name="accion" class="btn btn-danger">
                        </div>
                        <div class="col-sm-4 ml-auto">
                            <input type="text" name="txtTotal" value="Q.${totalDetalle}" class="form-control">
                        </div>
                    </div>
                     </form>
                </div>
            </div>
        </div>
       
    </body>
</html>
