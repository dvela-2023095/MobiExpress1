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
import modelo.Clientes;
import modelo.ClientesDAO;
import modelo.Empleado;
import modelo.EmpleadoDAO;
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
    ProveedoresDAO ProveedoresDAO = new ProveedoresDAO();
    Proveedores Proveedores = new Proveedores();
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
            switch(accion){
                case "Listar":
                    List listaCliente = clienteDao.listar();
                    request.setAttribute("clientes", listaCliente);
                break;
                case "Agregar":
                    String NIT = request.getParameter("txtNITCliente");
                    String nombres = request.getParameter("txtNombresCliente");
                    String apellidos = request.getParameter("txtApellidosCliente");
                    String direccion = request.getParameter("txtDireccionCliente");
                    String telefono = request.getParameter("txtTelefonoEmpleado");
                    cliente.setNITCliente(NIT);
                    cliente.setNombresCliente(nombres);
                    cliente.setApellidosCliente(apellidos);
                    cliente.setDireccionCliente(direccion);
                    cliente.setTelefonoCliente(telefono);
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
                    String NITCli = request.getParameter("txtNITCliente");
                    String nombresCli = request.getParameter("txtNombresCliente");
                    String apellidosCli = request.getParameter("txtApellidosCliente");
                    String direccionCli = request.getParameter("txtDireccionCliente");
                    String telefonoCli = request.getParameter("txtTelefonoCliente");
                    cliente.setNITCliente(NITCli);
                    cliente.setNombresCliente(nombresCli);
                    cliente.setApellidosCliente(apellidosCli);
                    cliente.setDireccionCliente(direccionCli);
                    cliente.setTelefonoCliente(telefonoCli);
                    cliente.setCodigoCliente(codCliente);
                    clienteDao.actualizarCliente(cliente);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                break;
                case "Eliminar":
                    codCliente = Integer.parseInt(request.getParameter("codigoCliente"));
                    clienteDao.eliminarCliente(codCliente);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                break;
            }
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

            Proveedores proveedor = new Proveedores();
            proveedor.setNITProveedor(nitProveedor);
            proveedor.setNombreProveedor(nombreProveedor);
            proveedor.setApellidoProveedor(apellidoProveedor);
            proveedor.setRazonSocial(razonSocial);
            proveedor.setContactoPrincipal(contactoPrincipal);
            proveedor.setPaginaWeb(paginaWeb);

            new ProveedoresDAO().agregar(proveedor);
            request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar").forward(request, response);
            break;
        case "Editar":
            int codProveedor = Integer.parseInt(request.getParameter("codigoProveedor"));
            Proveedores p = new ProveedoresDAO().listarCodigoProveedor(codProveedor);
            request.setAttribute("proveedor", p);
            request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar").forward(request, response);
            break;
        case "Actualizar":
            int codigoProveedor = Integer.parseInt(request.getParameter("codigoProveedor"));
            String nitProv = request.getParameter("txtNITProveedor");
            String nombreProv = request.getParameter("txtNombreProveedor");
            String apellidoProv = request.getParameter("txtApellidoProveedor");
            String razonSocialProv = request.getParameter("txtRazonSocial");
            String contactoPrincipalProv = request.getParameter("txtContactoPrincipal");
            String paginaWebProv = request.getParameter("txtPaginaWeb");

            Proveedores proveedorAct = new Proveedores();
            proveedorAct.setCodigoProveedor(codigoProveedor);
            proveedorAct.setNITProveedor(nitProv);
            proveedorAct.setNombreProveedor(nombreProv);
            proveedorAct.setApellidoProveedor(apellidoProv);
            proveedorAct.setRazonSocial(razonSocialProv);
            proveedorAct.setContactoPrincipal(contactoPrincipalProv);
            proveedorAct.setPaginaWeb(paginaWebProv);

            new ProveedoresDAO().actualizar(proveedorAct);
            request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar").forward(request, response);
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
