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
import modelo.Empleado;
import modelo.EmpleadoDAO;


/**
 *
 * @author DELL
 */
@MultipartConfig
public class Controlador extends HttpServlet {
    Empleado empleado = new Empleado();
    EmpleadoDAO empleadoDao = new EmpleadoDAO();
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
        }else if(menu.equals("Home")){
            request.getRequestDispatcher("Home.jsp").forward(request, response);
        }else if(menu.equals("Empleados")){
            if(accion.equals("Listar")){
                    List listaEmpleados = empleadoDao.listar();
                    request.setAttribute("listaEmpleados", listaEmpleados);
            }else if (accion.equals("Agregar")){
                    empleado.setDPIEmpleado(request.getParameter("txtDPIEmpleado"));
                    empleado.setNombresEmpleado(request.getParameter("txtNombresEmpleado"));
                    empleado.setApellidosEmpleado(request.getParameter("txtApellidosEmpleado"));
                    empleado.setTelefonoEmpleado(request.getParameter("txtTelefonoEmpleado"));
                    empleado.setCodigoCargoEmpleado(Integer.parseInt(request.getParameter("txtCodCargoEmpleado")));
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
                    empleadoDao.agregar(empleado);
            }
            request.getRequestDispatcher("Empleados.jsp").forward(request, response);
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
