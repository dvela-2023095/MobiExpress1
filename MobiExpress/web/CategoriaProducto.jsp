<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Categoria Producto</title>

        <link rel="stylesheet" href="css/bootstrap.min.css">

    </head>

    <body>

        <div class="d-flex">

            <div class="card col-sm-4 bg-primary">

                <div class="card-body">

                    <form action="Controlador?menu=CategoriaProducto" method="POST" enctype="multipart/form-data">

                        <input type="hidden" name="codigoCategoriaProducto" value="${categoriaproducto.codigoCategoriaProducto}">

                        <div class="form-group">

                            <label><strong>Categoria:</strong></label>

                            <input type="text" value="${categoriaproducto.getCategoria()}" name="txtCategoria" class="form-control">

                        </div>

                        <div class="form-group">

                            <label><strong>Tipo De Producto:</strong></label>

                            <input type="text" value="${categoriaproducto.getTipoDeProducto()}" name="txtTipoDeProducto" class="form-control">

                        </div>

                        <div class="form-group">

                            <label><strong>Color:</strong></label>

                            <input type="text" value="${categoriaproducto.getColor()}" name="txtColor" class="form-control">

                        </div>

                        <div class="form-group">

                            <label><strong>Marca:</strong></label>

                            <input type="text" value="${categoriaproducto.getMarca()}" name="txtMarca" class="form-control">

                        </div>

                        <input type="submit" name="accion" value="Agregar" class="btn btn-info">

                        <input type="submit" name="accion" value="Actualizar" class="btn btn-success">

                    </form>

                </div> <!-- cardbody -->

            </div><!-- card -->

            <div class="col-sm-8">

                <table class="table table-hover">

                    <thead>

                        <tr>

                            <th>CODIGO</th>

                            <th>CATEGORIA</th>

                            <th>TIPO DE PRODUCTO</th>

                            <th>COLOR</th>

                            <th>MARCA</th>

                            <th>ACCIONES</th>

                        </tr>

                    </thead>

                    <tbody>

                        <c:forEach var="categoriaproducto" items="${listCategoriaProducto}">

                        <tr>

                            <td>${categoriaproducto.getCodigoCategoriaProducto()}</td>

                            <td>${categoriaproducto.getCategoria()}</td>

                            <td>${categoriaproducto.getTipoDeProducto()}</td>

                            <td>${categoriaproducto.getColor()}</td>

                            <td>${categoriaproducto.getMarca()}</td>

                            <td>

                                <a class="btn btn-warning" href="Controlador?menu=CategoriaProducto&accion=Editar&codigoCategoriaProducto=${categoriaproducto.getCodigoCategoriaProducto()}">Editar</a>

                                <a class="btn btn-danger" href="Controlador?menu=CategoriaProducto&accion=Eliminar&codigoCategoriaProducto=${categoriaproducto.getCodigoCategoriaProducto()}">Eliminar</a>

                            </td>        

                        </tr>

                        </c:forEach>

                    </tbody>

                </table>

            </div>

        </div> <!-- d-flex -->

    </body>

</html>

