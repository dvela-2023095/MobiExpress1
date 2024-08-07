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
    Proveedores Proveedores = new Proveedores();
    List<DetallePedido> listaCarrito = new ArrayList<>();
    double precio,subTotal, montoTotal = 0.00;
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
                
            }
            switch(accion2){
                case "Detalle":
                    codCompra = Integer.parseInt(request.getParameter("codCompra"));
                    List<DetalleCompra> listaDeDetalles = detalleCompDao.detalleDeCompra(codCompra);
                    request.setAttribute("listaDeDetalles", listaDeDetalles);
                    break;
            }
            request.getRequestDispatcher("Compras.jsp").forward(request, response);
        }else if(menu.equals("AgregarCompra")){
            switch(accion){
                case"Agregar":
                    String descripcion = request.getParameter("txtDescripcionCompra");
                    String estado = request.getParameter("txtEstado");
                    compras.setDescripcion(descripcion);
                    compras.setFechaDeCompra(convertirFecha(request.getParameter("txtFechaCompra")));
                    compras.setEstado(estado);
                    compras.setMontoTotal(0.00);
                    comprasDao.agregar(compras);
                    break;
                case "BuscarProveedor":
                    proveedor=proveedoresDao.listarCodigoProveedor(Integer.parseInt(request.getParameter("txtCodigoProveedor")));
                    request.setAttribute("proveedor", proveedor);
                    request.getRequestDispatcher("Controlador?menu=AgregarCompra&accion=default").forward(request, response);
                    break;
                case "BuscarProducto":
                    producto=productoDao.buscarProducto(Integer.parseInt(request.getParameter("txtCodigoProducto")));
                    request.setAttribute("producto", producto);
                    request.getRequestDispatcher("Controlador?menu=AgregarCompra&accion=default").forward(request, response);
                    break;
                case "BuscarCompra":
                    compras=comprasDao.listarNumeroCompra(Integer.parseInt(request.getParameter("txtCodigoCompra")));
                    request.setAttribute("compras", compras);
                    request.getRequestDispatcher("Controlador?menu=AgregarCompra&accion=default").forward(request, response);
                    break;
                case "Agregar Detalle":
                    /*precio=Double.parseDouble(request.getParameter("txtPrecio");
                    cantidad=Integer.parseInt(request.getParameter("txtCantidad");
                    subTotal= precio*cantidad;*/
                    montoTotal=0.0;
                    DetalleCompra detalle = new DetalleCompra();
                    detalle.setCodigoProveedor(Integer.parseInt(request.getParameter("txtCodigoProveedor")));
                    detalle.setCodigoProducto(Integer.parseInt(request.getParameter("txtCodigoProducto")));
                    detalle.setNumeroCompra(Integer.parseInt(request.getParameter("txtCodigoCompra")));
                    detalle.setCosto(Double.parseDouble(request.getParameter("txtPrecio")));
                    detalle.setCantidad(Integer.parseInt(request.getParameter("txtCantidad")));
                    detalle.setDireccion((request.getParameter("txtDireccion")));
                    detalle.setFechaReception(convertirFecha(request.getParameter("txtFechaRecepcion")));
                    detalle.setSubTotal(detalle.getCantidad()*detalle.getCosto());
                    listaDeDetalles.add(detalle);
                    for(int i=0; i<listaDeDetalles.size();i++){
                        montoTotal = montoTotal + listaDeDetalles.get(i).getSubTotal();
                    }
                    request.setAttribute("listaDeDetalles", listaDeDetalles);
                    request.setAttribute("montoTotal", montoTotal);
                    break;
            }
            request.getRequestDispatcher("DetalleCompra.jsp").forward(request, response);
        }else if(menu.equals("AgregarPedido")){
            switch(accion){
                case"VerInventario":
                    listaCarrito.clear();
                    DetallePedido det1 = new DetallePedido(7, 25.00, 3, 75.00, "10%", 1, 4, "mesa redonda");
                    DetallePedido det2 = new DetallePedido(8, 30.00, 2, 60.00, "5%", 2, 4, "mesa Rectangular");
                    listaCarrito.add(det1);
                    listaCarrito.add(det2);
                    Date fechaEntrega = convertirFecha(request.getParameter("dtFecha Entrega"));
                    List<Pedido> listaDePedidos = new ArrayList<>();
                    //consigue los pedidos en un rango de dias
                    listaDePedidos = pedidoDao.buscarPedidos(fechaEntrega);
                    //consigue los detalles de todos los pedidos
                    List<DetallePedido> listaDetalles = new ArrayList<>();
                    listaDetalles= detallePedidoDao.buscarDetalles(listaDePedidos);
                    List<Producto> listaProductos = new ArrayList<>();
                    //cantidad de stock de productos
                    //listaProductos = productoDao.productosDeDetalle(listaDetalles);
                    listaProductos = productoDao.listar();
                    listaProductos = productoDao.calcularStockDisponible(listaProductos, listaDetalles);
                    listaCarrito = detallePedidoDao.asignarStock(listaProductos, listaCarrito);
                    request.setAttribute("listaDeDetalles", listaCarrito);
                    request.getRequestDispatcher("Controlador?menu=AgregarPedido&accion=default").forward(request, response);
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
