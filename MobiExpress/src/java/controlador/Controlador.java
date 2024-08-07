/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import modelo.CargoEmpleado;
import modelo.CargoEmpleadoDAO;
import modelo.CategoriaProducto;
import modelo.CategoriaProductoDAO;
import modelo.Clientes;
import modelo.ClientesDAO;
import modelo.Compras;
import modelo.ComprasDAO;
import modelo.DetalleCompra;
import modelo.DetalleCompraDAO;
import modelo.DetallePedido;
import modelo.DetallePedidoDAO;
import modelo.Empleado;
import modelo.EmpleadoDAO;
import modelo.Pedido;
import modelo.PedidoDAO;
import modelo.Producto;
import modelo.ProductoDAO;
import modelo.Proveedores;
import modelo.ProveedoresDAO;


/**
 *
 * @author DELL
 */
@MultipartConfig
public class Controlador extends HttpServlet {
    Empleado empleado = new Empleado();
    EmpleadoDAO empleadoDao = new EmpleadoDAO();
    Clientes cliente = new Clientes();
    ClientesDAO clienteDao = new ClientesDAO();
    CargoEmpleado cargoEmpleado = new CargoEmpleado();
    CargoEmpleadoDAO cargoEmpDao = new CargoEmpleadoDAO();
    Compras compras = new Compras();
    ComprasDAO comprasDao = new ComprasDAO();
    DetalleCompra detalleComp = new DetalleCompra();
    DetalleCompraDAO detalleCompDao = new DetalleCompraDAO();
    Proveedores proveedor = new Proveedores();
    ProveedoresDAO proveedoresDao = new ProveedoresDAO();
    Producto producto = new Producto();
    ProductoDAO productoDao = new ProductoDAO();
    Pedido pedido=new Pedido();
    PedidoDAO pedidoDao = new PedidoDAO();
    DetallePedido detallePedido = new DetallePedido();
    DetallePedidoDAO detallePedidoDao = new DetallePedidoDAO();
    CategoriaProducto categoriaProducto = new CategoriaProducto();
    CategoriaProductoDAO categoriaProductoDao = new CategoriaProductoDAO();
    Proveedores Proveedores = new Proveedores();
    List<DetallePedido> listaCarrito = new ArrayList<>();
    double precio,subTotal, montoTotal = 0.00;
    int codCategoriaProducto;
    int codProducto;
    int cantidad;
    int codCompra;
    int codCliente;
    int codEmpleado;
    int codCargoEmpleado;
    String Imagen;
    String respuesta = "Holaa";
    List<DetalleCompra> listaDeDetalles = new ArrayList<>();
    List<Producto> listaDeProductos = new ArrayList<>();
    List<DetallePedido> listaDetallePedido = new ArrayList<>();
    ProveedoresDAO ProveedoresDAO = new ProveedoresDAO();
    DetalleCompra detalleCompra = new DetalleCompra();
    DetalleCompraDAO detalleCompraDao = new DetalleCompraDAO();
    Proveedores proveedores = new Proveedores();
    List<DetalleCompra> listaDeComp = new ArrayList<>();
    int codProveedor;
    int codDetalle;
    int cantid, item;
    String direccion;
    String fecha;
    double totalDetalle,costo;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        String accion2 = request.getParameter("accion2");
        
        if(menu.equals("Principal")){
            request.getRequestDispatcher("Principal.jsp").forward(request, response);
        }else if(menu.equals("PrincipalEmp")){
            request.getRequestDispatcher("PrincipalEmp.jsp").forward(request, response);
        }else if(menu.equals("Home")){
            request.getRequestDispatcher("Home.jsp").forward(request, response);
        }else if(menu.equals("Empleados")){
            switch(accion){
            case "Listar":
                    List listaEmpleados = empleadoDao.listar();
                    request.setAttribute("listaEmpleados", listaEmpleados);
                    break;
            case "Agregar":
                empleado.setDPIEmpleado(request.getParameter("txtDPIEmpleado"));
                empleado.setNombresEmpleado(request.getParameter("txtNombresEmpleado"));
                empleado.setApellidosEmpleado(request.getParameter("txtApellidosEmpleado"));
                empleado.setTelefonoEmpleado(request.getParameter("txtTelefonoEmpleado"));
                if(request.getParameter("txtCodigoCargo").isEmpty())
                    empleado.setCodigoCargoEmpleado(0);
                else
                    empleado.setCodigoCargoEmpleado(Integer.parseInt(request.getParameter("txtCodigoCargo")));
                empleado.setUsuario(request.getParameter("txtUsuarioEmpleado"));
                empleado.setPasswor(request.getParameter("txtContraEmpleado"));
                Part archivoImagen = request.getPart("flImagen");
                if(archivoImagen != null){
                    InputStream contenidoArchivo = archivoImagen.getInputStream();
                    byte[] imagenEnBytes = leerBytes(contenidoArchivo);
                    String imagenBase64 = Base64.getEncoder().encodeToString(imagenEnBytes);
                    empleado.setImagen(imagenBase64);
                }else
                    System.out.println("Imagen nula");
                if(empleado.getDPIEmpleado().isEmpty()||empleado.getNombresEmpleado().isEmpty()||empleado.getApellidosEmpleado().isEmpty()||empleado.getTelefonoEmpleado().isEmpty()||empleado.getCodigoCargoEmpleado()==0||empleado.getUsuario().isEmpty()||empleado.getPasswor().isEmpty()||empleado.getImagen().isEmpty()){
                    respuesta = "No puede dejar espacios vacíos";
                    request.setAttribute("respuesta",respuesta);
                    request.getRequestDispatcher("Controlador?menu=Empleados&accion=Listar").forward(request, response);
                }else
                    empleadoDao.agregar(empleado);
                request.getRequestDispatcher("Controlador?menu=Empleados&accion=Listar").forward(request, response);
                break;
            case "Editar":
                codEmpleado = Integer.parseInt(request.getParameter("codigoEmpleado"));
                Empleado emp = empleadoDao.buscarcodigoEmpleado(codEmpleado);
                Imagen = emp.getImagen();
                request.setAttribute("empleado", emp);
                request.getRequestDispatcher("Controlador?menu=Empleados&accion=Listar").forward(request, response);
                break; 
            case "Actualizar":
                empleado.setCodigoEmpleado(codEmpleado);
                empleado.setDPIEmpleado(request.getParameter("txtDPIEmpleado"));
                empleado.setNombresEmpleado(request.getParameter("txtNombresEmpleado"));
                empleado.setApellidosEmpleado(request.getParameter("txtApellidosEmpleado"));
                empleado.setTelefonoEmpleado(request.getParameter("txtTelefonoEmpleado"));
                empleado.setUsuario(request.getParameter("txtUsuarioEmpleado"));
                empleado.setPasswor(request.getParameter("txtContraEmpleado"));
                archivoImagen = request.getPart("flImagen");
                if(archivoImagen.getSize() != 0){
                    InputStream contenidoArchivo = archivoImagen.getInputStream();
                    byte[] imagenEnBytes = leerBytes(contenidoArchivo);
                    String imagenBase64 = Base64.getEncoder().encodeToString(imagenEnBytes);
                    empleado.setImagen(imagenBase64);
                }else{
                    empleado.setImagen(Imagen);
                }
                empleadoDao.actualizarEmpleado(empleado);
                request.getRequestDispatcher("Controlador?menu=Empleados&accion=Listar").forward(request, response);
                break;
            case "Eliminar":
                codEmpleado = Integer.parseInt(request.getParameter("codigoEmpleado"));
                empleadoDao.eliminarEmpleado(codEmpleado);
                request.getRequestDispatcher("Controlador?menu=Empleados&accion=Listar").forward(request, response);
                break;
            case "BuscarCargo":
                empleado.setDPIEmpleado(request.getParameter("txtDPIEmpleado"));
                empleado.setNombresEmpleado(request.getParameter("txtNombresEmpleado"));
                empleado.setApellidosEmpleado(request.getParameter("txtApellidosEmpleado"));
                empleado.setTelefonoEmpleado(request.getParameter("txtTelefonoEmpleado"));
                empleado.setUsuario(request.getParameter("txtUsuarioEmpleado"));
                empleado.setPasswor(request.getParameter("txtContraEmpleado"));
                codCargoEmpleado = Integer.parseInt(request.getParameter("txtCodigoCargo"));
                cargoEmpleado = cargoEmpDao.buscarCodigoCargoEmpleado(codCargoEmpleado);
                empleado.setCodigoCargoEmpleado(cargoEmpleado.getCodigoCargoEmpleado());
                request.setAttribute("empleado", empleado);
                request.setAttribute("cargoEmpleado", cargoEmpleado);
                request.getRequestDispatcher("Controlador?menu=Empleados&accion=Listar").forward(request, response);
                break;
                
            }
            request.getRequestDispatcher("Empleados.jsp").forward(request, response);
        }else if (menu.equals("Clientes")){
            switch (accion) {
                case "Listar":
                    List<Clientes> listaCliente = clienteDao.listar();
                    request.setAttribute("listaCliente", listaCliente);
                    break;
                case "Agregar":
                    String nitCli = request.getParameter("txtNITCliente");
                    String nomCli = request.getParameter("txtNombresCliente");
                    String apeCli = request.getParameter("txtApellidosCliente");
                    String direCli = request.getParameter("txtDireccionCliente");
                    String telCli = request.getParameter("txtTelefonoCliente");
                    cliente.setNITCliente(nitCli);
                    cliente.setNombresCliente(nomCli);
                    cliente.setApellidosCliente(apeCli);
                    cliente.setDireccionCliente(direCli);
                    cliente.setTelefonoCliente(telCli);
                    if(cliente.getNITCliente().isEmpty()||cliente.getNombresCliente().isEmpty()||cliente.getApellidosCliente().isEmpty()||cliente.getDireccionCliente().isEmpty()||cliente.getTelefonoCliente().isEmpty()){
                        respuesta = "No puede dejar espacios vacíos";
                        request.setAttribute("respuesta",respuesta);
                        request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    }else
                        clienteDao.agregar(cliente);
                        request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;

                case "Editar":
                    codCliente = Integer.parseInt(request.getParameter("codigoCliente"));
                    Clientes c = clienteDao.listarCodigoCliente(codCliente);
                    request.setAttribute("cliente", c);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    nitCli = request.getParameter("txtNITCliente");
                    nomCli = request.getParameter("txtNombresCliente");
                    apeCli = request.getParameter("txtApellidosCliente");
                    direCli = request.getParameter("txtDireccionCliente");
                    telCli = request.getParameter("txtTelefonoCliente");
                    cliente.setNITCliente(nitCli);
                    cliente.setNombresCliente(nomCli);
                    cliente.setApellidosCliente(apeCli);
                    cliente.setDireccionCliente(direCli);
                    cliente.setTelefonoCliente(telCli);
                    cliente.setCodigoCliente(codCliente);
                    if(cliente.getNITCliente().isEmpty()||cliente.getNombresCliente().isEmpty()||cliente.getApellidosCliente().isEmpty()||cliente.getDireccionCliente().isEmpty()||cliente.getTelefonoCliente().isEmpty()){
                        respuesta = "No puede dejar espacios vacíos";
                        request.setAttribute("respuesta",respuesta);
                        request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    }else
                        clienteDao.actualizar(cliente);
                        request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    codCliente = Integer.parseInt(request.getParameter("codigoCliente"));
                    clienteDao.eliminar(codCliente);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
            }
            request.getRequestDispatcher("Clientes.jsp").forward(request, response);
            
        }else if(menu.equals("Compras")){
            switch(accion){
                case "Listar":
                    List <Compras> listaDeCompras = comprasDao.listar();
                    request.setAttribute("listaDeCompras", listaDeCompras);
                    break;
                 case "EliminarDetalle":
                    codCompra = Integer.parseInt(request.getParameter("numeroCompra"));
                    comprasDao.eliminar(codCompra);
                    request.getRequestDispatcher("Controlador?menu=Compras&accion=Listar").forward(request, response);
                break;
            }
            switch(accion2){
                case "Detalle":
                    codCompra = Integer.parseInt(request.getParameter("codCompra"));
                    List<DetalleCompra> listaDetalleCompra = detalleCompraDao.detalleDeCompra(codCompra);
                    request.setAttribute("listaDeDetalles", listaDetalleCompra);
                    break;
                case "Eliminar":
                    codDetalle = Integer.parseInt(request.getParameter("codigoDetalleCompra"));
                    comprasDao.eliminar(codCompra);
                    request.getRequestDispatcher("Controlador?menu=Compras&accion=Listar").forward(request, response);
                    break;
            }
            
            request.getRequestDispatcher("Compras.jsp").forward(request, response);
        }else if(menu.equals("AgregarCompra")){
            switch(accion){
                case "ListarDe":
                        List listaDetalleCompra = detalleCompraDao.listar();
                        request.setAttribute("listaDetalleCompra", listaDetalleCompra);
                    break;
                case "BuscarProveedor":
                    String codProveerS = request.getParameter("txtCodigoProveedor");
                    if(codProveerS==null || codProveerS.trim().isEmpty()){
                        request.setAttribute("proveedores", proveedores);
                    request.setAttribute("detalleCompra", detalleCompra);
                    request.setAttribute("compras", compras);
                    request.setAttribute("producto", producto);
                    request.setAttribute("totalDetalle", totalDetalle);
                    request.setAttribute("listaDeComp", listaDeComp);
                    }else{
                    codProveedor = Integer.parseInt(request.getParameter("txtCodigoProveedor"));
                    proveedores.setCodigoProveedor(codProveedor);
                    proveedores = proveedoresDao.listarCodigoProveedor(codProveedor);
                    request.setAttribute("proveedores", proveedores);
                    request.setAttribute("detalleCompra", detalleCompra);
                    request.setAttribute("compras", compras);
                    request.setAttribute("producto", producto);
                    request.setAttribute("totalDetalle", totalDetalle);
                    request.setAttribute("listaDeComp", listaDeComp);
                    
                    }
                    break;
                case "BuscarProducto":
                    String codProductoS = request.getParameter("txtCodigoProducto");
                        if(codProductoS==null|| codProductoS.trim().isEmpty()){
                            request.setAttribute("compras", compras);
                            request.setAttribute("detalleCompra", detalleCompra);
                            request.setAttribute("proveedores", proveedores);
                        }else{
                        codProducto = Integer.parseInt(request.getParameter("txtCodigoProducto"));
                        producto.setCodigoProducto(codProducto);
                        producto = productoDao.buscarCodigoProducto(codProducto);
                        request.setAttribute("detalleCompra", detalleCompra);
                        request.setAttribute("producto", producto);
                        request.setAttribute("compras", compras);
                        request.setAttribute("proveedores", proveedores);
                        request.setAttribute("totalDetalle", totalDetalle);
                        request.setAttribute("listaDeComp", listaDeComp);}
                    
                    break;
                case "BuscarCompra":
                    String comprasS = request.getParameter("txtCodigoCompra");
                    if(comprasS==null||comprasS.trim().isEmpty()){
                        request.setAttribute("compras", compras);
                        request.setAttribute("detalleCompra", detalleCompra);
                        request.setAttribute("proveedores", proveedores);
                    }else{
                        codCompra = Integer.parseInt(request.getParameter("txtCodigoCompra"));
                        compras.setNumeroCompra(codCompra);
                        compras = comprasDao.listarNumeroCompra(codCompra);
                        request.setAttribute("compras", compras);
                        request.setAttribute("detalleCompra", detalleCompra);
                        request.setAttribute("proveedores", proveedores);
                        request.setAttribute("producto", producto);
                        request.setAttribute("totalDetalle", totalDetalle);
                        request.setAttribute("listaDeComp", listaDeComp);
                    }
                    break;
                case "AgregarDetalles":
                    String codProveedorS = request.getParameter("txtCodigoCompra");
                    String costoS = request.getParameter("txtPrecio");
                    String direccionS = request.getParameter("txtDireccion");
                    String cantidaS = request.getParameter("txtCantidad");
                    String fecha = request.getParameter("txtFechaRecepcion");
                    request.setAttribute("compras", compras);
                    request.setAttribute("detalleCompra", detalleCompra);
                    request.setAttribute("proveedores", proveedores);
                    request.setAttribute("producto", producto);
                    if(codProveedorS == null || codProveedorS.trim().isEmpty()||costoS==null||costoS.trim().isEmpty()||direccionS==null||direccionS.trim().isEmpty()|| cantidaS==null || cantidaS.trim().isEmpty()){
                        request.setAttribute("totalDetalle", totalDetalle);
                        request.setAttribute("listaDeComp", listaDeComp);
                    }else{
                        totalDetalle = 0.0;
                        codProveedor = Integer.parseInt(request.getParameter("txtCodigoCompra"));
                        codProducto = producto.getCodigoProducto();
                        codCompra = compras.getNumeroCompra();
                        costo = Double.parseDouble(request.getParameter("txtPrecio"));
                        cantid = Integer.parseInt(request.getParameter("txtCantidad"));
                        subTotal = costo*cantid;
                        direccion = request.getParameter("txtDireccion");
                        fecha = request.getParameter("txtFechaRecepcion");
                        item = item +1;
                        DetalleCompra detalleCompras = new DetalleCompra();
                        detalleCompras.setItem(item);
                        detalleCompras.setCodigoProveedor(codProveedor);
                        detalleCompras.setCodigoProducto(codProducto);
                        detalleCompras.setNumeroCompra(codCompra);
                        detalleCompras.setCosto(costo);
                        detalleCompras.setCantidad(cantid);
                        detalleCompras.setSubTotal(subTotal);
                        detalleCompras.setDireccion(direccion);
                        detalleCompras.setFechaRecepcion(fecha);
                        listaDeComp.add(detalleCompras);
                        for(int i=0; i<listaDeComp.size(); i++){
                            totalDetalle = totalDetalle + listaDeComp.get(i).getSubTotal();
                        }
                        request.setAttribute("totalDetalle", totalDetalle);
                        request.setAttribute("listaDeComp", listaDeComp);
                    }
                    break;
                case "Eliminar":
                    int item = Integer.parseInt(request.getParameter("item"));
                    for (int i = 0; i < listaDeComp.size(); i++) {
                        DetalleCompra dc = listaDeComp.get(i);
                        if (dc.getItem()== item) {
                            listaDeComp.remove(i);
                        }
                    }
                    totalDetalle = 0.0;
                    for (DetalleCompra dc : listaDeComp) {
                        totalDetalle += dc.getSubTotal();
                    }
                    request.setAttribute("totalDetalle", totalDetalle);
                    request.setAttribute("listaDeComp", listaDeComp);
                    break;
                case "Cancelar":
                    listaDeComp.clear();
                    totalDetalle = 0.0;
                    codProveedor = 0;
                    codProducto = 0;
                    codCompra =0;
                    
                    proveedores.setCodigoProveedor(codProveedor);
                    proveedores = proveedoresDao.listarCodigoProveedor(codProveedor);
                    request.setAttribute("proveedores", proveedores);
                    
                    producto.setCodigoProducto(codProducto);
                    producto = productoDao.buscarCodigoProducto(codProducto);
                    request.setAttribute("producto", producto);
                    
                    compras.setNumeroCompra(codCompra);
                    compras = comprasDao.listarNumeroCompra(codCompra);
                    request.setAttribute("compras", compras);
                    
                    request.setAttribute("totalDetalle", totalDetalle);
                    request.setAttribute("listaDeComp", listaDeComp);
                    break;
                case "Guardar Datos":
                    for (DetalleCompra dc : listaDeComp) {
                        detalleCompraDao.agregar(dc);
                    }
                    listaDeComp.clear();
                    totalDetalle = 0.0;

                    request.setAttribute("totalDetalle", totalDetalle);
                    request.setAttribute("listaDeComp", listaDeComp);
                    request.setAttribute("mensaje", "Datos guardados exitosamente.");
                    break;
            }
            request.getRequestDispatcher("DetalleCompra.jsp").forward(request, response);
        }else if(menu.equals("AgregarPedido")){
            switch(accion){
                case"VerInventario":
                    String fechaentrega = request.getParameter("txtFechaEntrega");
                    String fecharetorno = request.getParameter("txtFechaRetorno");
                    if(request.getParameter("txtCodigoEmpleado").isEmpty()||request.getParameter("txtCodigoCliente").isEmpty()){
                    }else{
                        pedido.setCodigoCliente(Integer.parseInt(request.getParameter("txtCodigoCliente")));
                        pedido.setCodigoEmpleado(Integer.parseInt(request.getParameter("txtCodigoEmpleado")));
                    }
                    pedido.setDireccion(request.getParameter("txtDireccion"));
                    Date fechaEntrega = convertirFecha(request.getParameter("txtFechaEntrega"));
                    List<Pedido> listaDePedidos = new ArrayList<>();
                    //consigue los pedidos en un rango de dias
                    listaDePedidos = pedidoDao.buscarPedidos(fechaEntrega);
                    //consigue los detalles de todos los pedidos
                    List<DetallePedido> listaDetalles = new ArrayList<>();
                    listaDetalles= detallePedidoDao.buscarDetalles(listaDePedidos);
                    List<Producto> listaProductos = new ArrayList<>();
                    //cantidad de stock de productos
                    listaProductos = productoDao.listar();
                    listaProductos = productoDao.calcularStockDisponible(listaProductos, listaDetalles);
                    listaCarrito = detallePedidoDao.asignarStock(listaProductos, listaCarrito);
                    request.setAttribute("listaDeDetalles", listaCarrito);
                    request.setAttribute("fechae", fechaentrega);
                    request.setAttribute("fechar", fecharetorno);
                    request.setAttribute("pedido", pedido);
                    request.getRequestDispatcher("Controlador?menu=AgregarPedido&accion=default").forward(request, response);
                    break;
                case"Listar":
                    request.setAttribute("listaDeDetalles", listaCarrito);
                    request.setAttribute("total", montoTotal);
                    break;
                case"Eliminar":
                    DetallePedido det = (DetallePedido)request.getAttribute("detalleAEliminar");
                    listaCarrito.remove(det);
                    request.getRequestDispatcher("Controlador?menu=AgregarPedido&accion=Listar").forward(request, response);
                    break;
                case"Establecer":
                     fechaentrega = request.getParameter("txtFechaEntrega");
                     fecharetorno = request.getParameter("txtFechaRetorno");
                    pedido.setFechaDeEntrega(convertirFecha(fechaentrega));
                    pedido.setFechaDeRetorno(convertirFecha(fecharetorno));
                    if(request.getParameter("txtCodigoEmpleado").isEmpty()||request.getParameter("txtCodigoCliente").isEmpty()){
                    }else{
                        pedido.setCodigoCliente(Integer.parseInt(request.getParameter("txtCodigoCliente")));
                        pedido.setCodigoEmpleado(Integer.parseInt(request.getParameter("txtCodigoEmpleado")));
                    }
                    pedido.setDireccion(request.getParameter("txtDireccion"));
                    montoTotal = 0.0;
                    codProducto = Integer.parseInt(request.getParameter("txtCodProducto"));
                    for(int i=0;i<listaCarrito.size();i++){
                        if(codProducto ==listaCarrito.get(i).getCodigoProducto()){
                            subTotal=0;
                            det = listaCarrito.get(i);
                            det.setCantidad(Integer.parseInt(request.getParameter("txtCantidad")));
                            det.setDescuento(Integer.parseInt(request.getParameter("txtDescuento")));
                            subTotal=det.getCantidad()*det.getPrecioRenta();
                            double descuento = det.getDescuento() / 100.0;
                            double totalConDescuento = subTotal - (subTotal * descuento);
                            det.setSubTotal(totalConDescuento);
                            listaCarrito.set(i, det);
                        }
                        montoTotal = montoTotal+listaCarrito.get(i).getSubTotal();
                    }
                    request.setAttribute("fechae", fechaentrega);
                    request.setAttribute("fechar", fecharetorno);
                    request.setAttribute("pedido", pedido);
                    request.setAttribute("total", montoTotal);
                    request.getRequestDispatcher("Controlador?menu=AgregarPedido&accion=Listar").forward(request, response);
                    break;
                case"Confirmar Pedido":
                    fechaentrega = request.getParameter("txtFechaEntrega");
                    fecharetorno = request.getParameter("txtFechaRetorno");
                    pedido.setFechaDeEntrega(convertirFecha(fechaentrega));
                    pedido.setFechaDeRetorno(convertirFecha(fecharetorno));
                    pedido.setCodigoCliente(Integer.parseInt(request.getParameter("txtCodigoCliente")));
                    pedido.setCodigoEmpleado(Integer.parseInt(request.getParameter("txtCodigoEmpleado")));
                    pedido.setDireccion(request.getParameter("txtDireccion"));
                    pedido.setMontoTotal(montoTotal);
                    pedidoDao.agregar(pedido);
                    /*int codigoPedido = pedidoDao.encontrarPedidoRecienAgregado(pedido);
                    for(int i=0;i<listaCarrito.size();i++){
                        det = listaCarrito.get(i);
                        det.setNumeroPedido(codigoPedido);
                        detallePedidoDao.agregar(det);
                    }
                    listaCarrito.clear();
                    montoTotal=0;*/
                    request.getRequestDispatcher("Controlador?menu=AgregarPedido&accion=Listar").forward(request, response);
                    break;
                case"BuscarCliente":
                    fechaentrega = request.getParameter("txtFechaEntrega");
                     fecharetorno = request.getParameter("txtFechaRetorno");
                    pedido.setFechaDeEntrega(convertirFecha(request.getParameter("txtFechaEntrega")));
                    pedido.setFechaDeRetorno(convertirFecha(request.getParameter("txtFechaRetorno")));
                    if(request.getParameter("txtCodigoEmpleado").isEmpty()||request.getParameter("txtCodigoCliente").isEmpty()){
                    }else{
                        pedido.setCodigoCliente(Integer.parseInt(request.getParameter("txtCodigoCliente")));
                        pedido.setCodigoEmpleado(Integer.parseInt(request.getParameter("txtCodigoEmpleado")));
                    }
                    pedido.setDireccion(request.getParameter("txtDireccion"));
                    request.setAttribute("fechae", fechaentrega);
                    request.setAttribute("fechar", fecharetorno);
                    request.setAttribute("pedido", pedido);
                    codCliente=Integer.parseInt(request.getParameter("txtCodigoCliente"));
                    pedido.setCodigoCliente(codCliente);
                    cliente = clienteDao.listarCodigoCliente(codCliente);
                    request.setAttribute("cliente", cliente.getNombresCliente()+" "+cliente.getApellidosCliente());
                    request.setAttribute("pedido", pedido);
                    request.getRequestDispatcher("Controlador?menu=AgregarPedido&accion=Listar").forward(request, response);
                    break;
                case"BuscarEmpleado":
                    fechaentrega = request.getParameter("txtFechaEntrega");
                     fecharetorno = request.getParameter("txtFechaRetorno");
                    pedido.setFechaDeEntrega(convertirFecha(request.getParameter("txtFechaEntrega")));
                    pedido.setFechaDeRetorno(convertirFecha(request.getParameter("txtFechaRetorno")));
                    if(request.getParameter("txtCodigoEmpleado").isEmpty()||request.getParameter("txtCodigoCliente").isEmpty()){
                    }else{
                        pedido.setCodigoCliente(Integer.parseInt(request.getParameter("txtCodigoCliente")));
                        pedido.setCodigoEmpleado(Integer.parseInt(request.getParameter("txtCodigoEmpleado")));
                    }
                    pedido.setDireccion(request.getParameter("txtDireccion"));
                    request.setAttribute("fechae", fechaentrega);
                    request.setAttribute("fechar", fecharetorno);
                    request.setAttribute("pedido", pedido);
                    codEmpleado=Integer.parseInt(request.getParameter("txtCodigoEmpleado"));
                    pedido.setCodigoEmpleado(codEmpleado);
                    empleado = empleadoDao.buscarcodigoEmpleado(codEmpleado);
                    request.setAttribute("empleado", empleado.getNombresEmpleado());
                    request.setAttribute("pedido", pedido);
                    request.getRequestDispatcher("Controlador?menu=AgregarPedido&accion=Listar").forward(request, response);
                    break;
            }
            request.getRequestDispatcher("Carrito.jsp").forward(request, response);
        }else if (menu.equals("Proveedores")) {
            switch (accion) {
                case "Listar":
                    List listaProveedores = proveedoresDao.listar();
                    request.setAttribute("Proveedores", listaProveedores);
                    break;
                case "Agregar":
                    String nitProveedor = request.getParameter("txtNITProveedor");
                    String nombreProveedor = request.getParameter("txtNombreProveedor");
                    String apellidoProveedor = request.getParameter("txtApellidoProveedor");
                    String razonSocial = request.getParameter("txtRazonSocial");
                    String contactoPrincipal = request.getParameter("txtContactoPrincipal");
                    String paginaWeb = request.getParameter("txtPaginaWeb");
                    Proveedores.setNITProveedor(nitProveedor);
                    Proveedores.setNombreProveedor(nombreProveedor);
                    Proveedores.setApellidoProveedor(apellidoProveedor);
                    Proveedores.setRazonSocial(razonSocial);
                    Proveedores.setContactoPrincipal(contactoPrincipal);
                    Proveedores.setPaginaWeb(paginaWeb);
                    if (Proveedores.getNITProveedor().isEmpty() || Proveedores.getNombreProveedor().isEmpty() || Proveedores.getApellidoProveedor().isEmpty() || Proveedores.getRazonSocial().isEmpty() || Proveedores.getContactoPrincipal().isEmpty() || Proveedores.getPaginaWeb().isEmpty()) {
                        respuesta = "No puede dejar espacios vacíos";
                        request.setAttribute("respuesta", respuesta);
                        request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar").forward(request, response);
                    }else{
                        new ProveedoresDAO().agregar(Proveedores);
                        request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar").forward(request, response);
                    }
                    break;
                case "Editar":
                    int codProveedor = Integer.parseInt(request.getParameter("codigoProveedor"));
                    Proveedores  = new ProveedoresDAO().listarCodigoProveedor(codProveedor);
                    request.setAttribute("proveedor", Proveedores);
                    request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String nitProv = request.getParameter("txtNITProveedor");
                    String nombreProv = request.getParameter("txtNombreProveedor");
                    String apellidoProv = request.getParameter("txtApellidoProveedor");
                    String razonSocialProv = request.getParameter("txtRazonSocial");
                    String contactoPrincipalProv = request.getParameter("txtContactoPrincipal");
                    String paginaWebProv = request.getParameter("txtPaginaWeb");            
                    Proveedores.setNITProveedor(nitProv);
                    Proveedores.setNombreProveedor(nombreProv);
                    Proveedores.setApellidoProveedor(apellidoProv);
                    Proveedores.setRazonSocial(razonSocialProv);
                    Proveedores.setContactoPrincipal(contactoPrincipalProv);
                    Proveedores.setPaginaWeb(paginaWebProv);
                    if (Proveedores.getNITProveedor().isEmpty() || Proveedores.getNombreProveedor().isEmpty() || Proveedores.getApellidoProveedor().isEmpty() || Proveedores.getRazonSocial().isEmpty() || Proveedores.getContactoPrincipal().isEmpty() || Proveedores.getPaginaWeb().isEmpty()) {
                        respuesta = "No puede dejar espacios vacíos";
                        request.setAttribute("respuesta", respuesta);
                        request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar").forward(request, response);
                    }else{
                        new ProveedoresDAO().actualizar(Proveedores);
                        request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar").forward(request, response);
                    }
                    break;
                case "Eliminar":
                    codProveedor = Integer.parseInt(request.getParameter("codigoProveedor"));
                    new ProveedoresDAO().eliminar(codProveedor);
                    request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar").forward(request, response);
                    break;
                }
                    request.getRequestDispatcher("Proveedores.jsp").forward(request, response);
            }else if (menu.equals("Producto")){
            switch(accion){
                case "Listar":
                    List listaProducto = productoDao.listar();
                    request.setAttribute("listaProducto", listaProducto);
                    break;
                case "Agregar":
                    producto.setProducto(request.getParameter("txtProducto"));
                    producto.setDescripcion(request.getParameter("txtDescripcion"));
                    if(request.getParameter("txtExistencia").isEmpty())
                        producto.setExistencia(0);
                    else
                        producto.setExistencia(Integer.parseInt(request.getParameter("txtExistencia")));
                    producto.setTamanio(request.getParameter("txtTamanio"));
                    Part archivoImagenProducto = request.getPart("flImagenProductos");
                    if(archivoImagenProducto != null){
                        InputStream contenidoArchivoPD = archivoImagenProducto.getInputStream();
                        byte[] imagenEnBytesPD = leerBytes(contenidoArchivoPD);
                        String imagenEnBytes64PD = Base64.getEncoder().encodeToString(imagenEnBytesPD);
                        producto.setImgProducto(imagenEnBytes64PD);
                    }else{
                        System.out.println("Imagen Nula");
                    }
                    if(request.getParameter("txtCostoRenta").isEmpty())
                        producto.setCostoRenta(0);
                    else 
                        producto.setCostoRenta(Double.parseDouble(request.getParameter("txtCostoRenta")));
                    if(request.getParameter("txtCodigoCategoria").isEmpty())
                        producto.setCodigoCategoriaProducto(0);
                    else 
                        producto.setCodigoCategoriaProducto(Integer.parseInt((request.getParameter("txtCodigoCategoria"))));
                    if (producto.getProducto().isEmpty() || producto.getDescripcion().isEmpty() || producto.getCostoRenta() == 0 || producto.getTamanio().isEmpty() || producto.getCodigoCategoriaProducto() == 0 || producto.getImgProducto().isEmpty()){
                        respuesta = "No se puede dejar espacios vacíos";
                        request.setAttribute("respuesta", respuesta);
                        request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    }else{
                        productoDao.agregar(producto);
                    }
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    codProducto = Integer.parseInt(request.getParameter("codigoProducto"));
                    Producto p = productoDao.buscarCodigoProducto(codProducto);
                    Imagen = p.getImgProducto();
                    request.setAttribute("producto", p);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String Producto = request.getParameter("txtProducto");
                    String descripcion = request.getParameter("txtDescripcion");
                    String tamanio = request.getParameter("txtTamanio");
                    int existencia = 0;
                    double costoRenta = 0;
                    if(request.getParameter("txtExistencia").length()!= 0)          
                        existencia=Integer.parseInt(request.getParameter("txtExistencia"));
                    if(request.getParameter("txtCostoRenta").length()!= 0)
                        costoRenta=Double.parseDouble(request.getParameter("txtCostoRenta"));

                    producto.setCodigoProducto(codProducto);
                    producto.setProducto(Producto);
                    producto.setDescripcion(descripcion);
                    producto.setCostoRenta(costoRenta);
                    producto.setExistencia(existencia);
                    producto.setTamanio(tamanio);
                    archivoImagenProducto = request.getPart("flImagenProductos");
                    if(archivoImagenProducto.getSize() !=0){
                        try (InputStream contenidoArchivoPD = archivoImagenProducto.getInputStream()) {
                            byte[] imagenEnBytesPD = leerBytes(contenidoArchivoPD);
                            String imagenEnBytes64PD = Base64.getEncoder().encodeToString(imagenEnBytesPD);
                            producto.setImgProducto(imagenEnBytes64PD);
                        } catch (IOException e) {
                            e.printStackTrace();
                            respuesta = "Error al procesar la imagen";
                            request.setAttribute("respuesta", respuesta);
                            request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                            break;
                        }
                    } else {
                        producto.setImgProducto(Imagen);
                    }
                    if (producto.getProducto().isEmpty() || producto.getDescripcion().isEmpty() || producto.getCostoRenta() == 0 || producto.getTamanio().isEmpty()){
                        respuesta = "No se puede dejar espacios vacíos";
                        request.setAttribute("respuesta", respuesta);
                    } else {
                        productoDao.actualizarProducto(producto);
                    }
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    codProducto = Integer.parseInt(request.getParameter("codigoProducto"));
                    productoDao.eliminarProducto(codProducto);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "BuscarCategoria":
                    producto.setProducto(request.getParameter("txtProducto"));
                    producto.setDescripcion(request.getParameter("txtDescripcion"));
                    if(request.getParameter("txtCostoRenta").isEmpty())
                            producto.setCostoRenta(0);
                        else 
                            producto.setCostoRenta(Double.parseDouble(request.getParameter("txtCostoRenta")));
                        if(request.getParameter("txtCodigoCategoria").isEmpty())
                            producto.setCodigoCategoriaProducto(0);
                        else
                    producto.setExistencia(Integer.parseInt(request.getParameter("txtExistencia")));
                    producto.setTamanio(request.getParameter("txtTamanio"));
                    codCategoriaProducto = Integer.parseInt(request.getParameter("txtCodigoCategoria"));
                    categoriaProducto = categoriaProductoDao.buscarCategoriaProducto(codCategoriaProducto);
                    producto.setCodigoCategoriaProducto(categoriaProducto.getCodigoCategoriaProducto());
                    request.setAttribute("producto", producto);
                    request.setAttribute("categoriaProducto", categoriaProducto);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "AgregarACarrito":
                    DetallePedido det = new DetallePedido();
                    det.setPrecioRenta(Double.parseDouble(request.getParameter("costoRenta")));
                    det.setNombreProducto(request.getParameter("nombreProducto"));
                    det.setCodigoProducto(Integer.parseInt(request.getParameter("codigoProducto")));
                    det.setDescuento(0);
                    listaCarrito.add(det);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
            }
            request.getRequestDispatcher("Producto.jsp").forward(request, response);
        }else if (menu.equals("CargoEmpleado")){
            switch(accion){
                case "Listar":
                    List listaCargos = cargoEmpDao.listar();
                    request.setAttribute("cargo", listaCargos);
                break;
                case "Agregar":
                    String nombreCargo = request.getParameter("txtNombreCargo");
                    double salario = Double.parseDouble(request.getParameter("txtSalario"));
                    String descripcion = request.getParameter("txtDescripcionCargo");
                    String jornada = request.getParameter("txtJornada");

                    CargoEmpleado cargo = new CargoEmpleado();
                    cargo.setNombreCargo(nombreCargo);
                    cargo.setSalario(salario);
                    cargo.setDescripcionCargo(descripcion);
                    cargo.setJornada(jornada);

                    cargoEmpDao.agregar(cargo);
                    request.getRequestDispatcher("Controlador?menu=CargoEmpleado&accion=Listar").forward(request, response);
                break;
                case "Editar":
                    codCargoEmpleado = Integer.parseInt(request.getParameter("codigoCargoEmpleado"));
                    CargoEmpleado car = cargoEmpDao.buscarCodigoCargoEmpleado(codCargoEmpleado);
                    request.setAttribute("cargoEmpleado", car);
                    request.getRequestDispatcher("Controlador?menu=CargoEmpleado&accion=Listar").forward(request, response);
                break;
                case "Actualizar":
                    String nombreCar = request.getParameter("txtNombreCargo");
                    double salarioCar = Double.parseDouble(request.getParameter("txtSalario"));
                    String descripcionCar = request.getParameter("txtDescripcionCargo");
                    String jornadaCar = request.getParameter("txtJornada");
                    cargoEmpleado.setNombreCargo(nombreCar);
                    cargoEmpleado.setSalario(salarioCar);
                    cargoEmpleado.setDescripcionCargo(descripcionCar);
                    cargoEmpleado.setJornada(jornadaCar);
                    cargoEmpleado.setCodigoCargoEmpleado(codCargoEmpleado);
                    cargoEmpDao.actualizarCargo(cargoEmpleado);
                    request.getRequestDispatcher("Controlador?menu=CargoEmpleado&accion=Listar").forward(request, response);
                break;
                case "Eliminar":
                    codCargoEmpleado = Integer.parseInt(request.getParameter("codigoCargoEmpleado"));
                    cargoEmpDao.eliminarCargo(codCargoEmpleado);
                    request.getRequestDispatcher("Controlador?menu=CargoEmpleado&accion=Listar").forward(request, response);
                break;
            }
            request.getRequestDispatcher("CargoEmpleado.jsp").forward(request, response);
        }
        }
    private java.sql.Date convertirFecha(String fechaString){
        java.sql.Date fecha = null;
        try {
            // Convertir el String a java.util.Date
            java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(fechaString);

            // Convertir java.util.Date a java.sql.Date
            fecha = new java.sql.Date(utilDate.getTime());
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de excepciones
        }
        return fecha;
    }
    private byte[] leerBytes(InputStream contenidoArchivo) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[16384];
        while ((nRead = contenidoArchivo.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        return buffer.toByteArray();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
