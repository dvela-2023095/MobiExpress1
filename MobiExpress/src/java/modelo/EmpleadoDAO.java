
package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
=======
>>>>>>> rmartin-2023205


public class EmpleadoDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
<<<<<<< HEAD
    int resp;
=======
    
>>>>>>> rmartin-2023205
    public Empleado validar (String usuario, String passwor){
        Empleado empleado = new Empleado();
        String sql = "select * from Empleados where usuario = ? and passwor = ?";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, passwor);
            rs=ps.executeQuery();
            while (rs.next()){
                empleado.setCodigoEmpleado(rs.getInt("codigoEmpleado"));
                empleado.setNombresEmpleado(rs.getString("nombresEmpleado"));
                empleado.setUsuario(rs.getString("usuario"));
                empleado.setPasswor(rs.getString("passwor"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return empleado;
    }
<<<<<<< HEAD
    
    public List listar(){
        String sql = "select * from Empleados";
        Empleado emp = new Empleado();
        List<Empleado> listaEmpleados = new ArrayList<>();
        try{
            con = cn.Conexion();
            ps=con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                emp.setCodigoEmpleado(rs.getInt(1));
                emp.setNITEmpleado(rs.getString(2));
                emp.setNombresEmpleado(rs.getString(3));
                emp.setApellidosEmpleado(rs.getString(4));
                emp.setTelefonoEmpleado(rs.getString(5));
                emp.setCodigoCargoEmpleado(rs.getInt(6));
                emp.setUsuario(rs.getString(7));
                emp.setPasswor(rs.getString(8));
                listaEmpleados.add(emp);
            }
                
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaEmpleados;
    }
    
    public int agregar(Empleado emp){
        String sql = "insert into Empleados(NITEmpleado, nombresEmpleado, apellidosEmpleado, telefonoEmpleado, codigoCargoEmpleado, usuario, passwor)values(?,?,?,?,?,?,?)";
        try{
            con = cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, emp.getNITEmpleado());
            ps.setString(2, emp.getNombresEmpleado());
            ps.setString(3, emp.getApellidosEmpleado());
            ps.setString(4, emp.getTelefonoEmpleado());
            ps.setInt(5, emp.getCodigoCargoEmpleado());
            ps.setString(6, emp.getUsuario());
            ps.setString(7, emp.getPasswor());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    public Empleado buscarcodigoEmpleado(int id){
        String sql = "select * from Empleados where codigoEmpleado = ? ";
        Empleado emp = new Empleado();
        try{
            con = cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            rs=ps.executeQuery();
            while(rs.next()){
                emp.setCodigoEmpleado(rs.getInt(1));
                emp.setNITEmpleado(rs.getString(2));
                emp.setNombresEmpleado(rs.getString(3));
                emp.setApellidosEmpleado(rs.getString(4));
                emp.setTelefonoEmpleado(rs.getString(5));
                emp.setCodigoCargoEmpleado(rs.getInt(6));
                emp.setUsuario(rs.getString(7));
                emp.setPasswor(rs.getString(8));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return emp;
    }
    
    public int actualizarEmpleado(Empleado emp){
        String sql="Update Empleados set NITEmpleado=?,nombresEmpleado=?, apellidosEmpleado=?, telefonoEmpleado=?, usuario=?, passwor=? where codigoEmpleado = ?";
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, emp.getNITEmpleado());
            ps.setString(2, emp.getNombresEmpleado());
            ps.setString(3, emp.getApellidosEmpleado());
            ps.setString(4, emp.getTelefonoEmpleado());
            ps.setString(5, emp.getUsuario());
            ps.setString(6, emp.getPasswor());
            ps.setInt(7, emp.getCodigoEmpleado());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    public void eliminarEmpleado(int id){
        String sql="delete from Empleados where codigoEmpleado=?";
        try{
            con = cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
=======
>>>>>>> rmartin-2023205
}
