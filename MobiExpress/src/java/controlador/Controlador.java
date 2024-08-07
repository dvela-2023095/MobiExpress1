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
import java.util.Base64;
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
import modelo.Empleado;
import modelo.EmpleadoDAO;
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
    Producto producto = new Producto();
    ProductoDAO productoDao = new ProductoDAO();
    ProveedoresDAO ProveedoresDAO = new ProveedoresDAO();
    Proveedores Proveedores = new Proveedores();
    CategoriaProducto categoriaProducto = new CategoriaProducto();
    CategoriaProductoDAO categoriaProductoDao = new CategoriaProductoDAO();
    int codCategoriaProducto;
    int codProducto;
    int codCliente;
    int codEmpleado;
    int codCargoEmpleado;
    String Imagen;
    String respuesta = "Holaa";
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
                request.getRequestDispatcher("Clientes.jsp").forward(request, response);
                break;

            case "Agregar":
                String nitCliente = request.getParameter("txtNITCliente");
                String nombreCliente = request.getParameter("txtNombresCliente");
                String apellidoCliente = request.getParameter("txtApellidosCliente");
                String direccionCliente = request.getParameter("txtDireccionCliente");
                String telefonoCliente = request.getParameter("txtTelefonoCliente");
                cliente.setNITCliente(nitCliente);
                cliente.setNombresCliente(nombreCliente);
                cliente.setApellidosCliente(apellidoCliente);
                cliente.setDireccionCliente(direccionCliente);
                cliente.setTelefonoCliente(telefonoCliente);
                if(cliente.getNITCliente().isEmpty()||cliente.getNombresCliente().isEmpty()||cliente.getApellidosCliente().isEmpty()||cliente.getDireccionCliente().isEmpty()||cliente.getTelefonoCliente().isEmpty()){
                    respuesta = "No puede dejar espacios vacíos";
                    request.setAttribute("respuesta",respuesta);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    clienteDao.agregar(cliente);
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
        }else if (menu.equals("Proveedores")) {
    switch (accion) {
        case "Listar":
            List listaProveedores = ProveedoresDAO.listar();
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
                    if(request.getParameter("txtCostoRenta").isEmpty())
                        producto.setCostoRenta(0);
                    else 
                        producto.setCostoRenta(Double.parseDouble(request.getParameter("txtCostoRenta")));
                    if(request.getParameter("txtCodigoCategoria").isEmpty())
                        producto.setCodigoCategoriaProducto(0);
                    else 
                        producto.setCodigoCategoriaProducto(Integer.parseInt((request.getParameter("txtCodigoCategoria"))));
                    if (producto.getProducto().isEmpty() || producto.getDescripcion().isEmpty() || producto.getCostoRenta() == 0 || producto.getTamanio().isEmpty() || producto.getCodigoCategoriaProducto() == 0){
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
                    request.setAttribute("producto", p);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                break;
                case "Actualizar":
                    String Producto = request.getParameter("txtProducto");
                    String descripcion = request.getParameter("txtDescripcion");
                    String tamanio = request.getParameter("txtTamanio");
                    int existencia =0;
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
                    if (producto.getProducto().isEmpty() || producto.getDescripcion().isEmpty() || producto.getCostoRenta() == 0 || producto.getTamanio().isEmpty() || producto.getCodigoCategoriaProducto() == 0){
                        respuesta = "No se puede dejar espacios vacíos";
                        request.setAttribute("respuesta", respuesta);
                    }else 
                        productoDao.actualizarProducto(producto);                   
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
                categoriaProducto = categoriaProductoDao.buscarCategoriaProducto(codCategoriaProducto);
                producto.setCodigoCategoriaProducto(categoriaProducto.getCodigoCategoriaProducto());
                request.setAttribute("producto", producto);
                request.setAttribute("categoriaProducto", categoriaProducto);
                request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                break;
            }
            request.getRequestDispatcher("Producto.jsp").forward(request, response);
        }
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
