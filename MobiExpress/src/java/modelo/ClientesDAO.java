
package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class ClientesDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;
 
    public Clientes validar (String usuario, String passwor){
        Clientes cliente = new Clientes();
        String sql = "select * from Clientes where usuario = ? and passwor = ?";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, passwor);
            rs=ps.executeQuery();
            while (rs.next()){
                cliente.setCodigoCliente(rs.getInt("codigoCliente"));
                cliente.setNombresCliente(rs.getString("nombresCliente"));
                cliente.setUsuario(rs.getString("usuario"));
                cliente.setPasswor(rs.getString("passwor"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return cliente;
    }
    
    public List lista(){
        String sql = "select * from Clientes";
        Clientes cliente = new Clientes();
        List<Clientes> listaCliente = new ArrayList<>();
        try{
            con = cn.Conexion();
            ps = con.prepareCall(sql);
            rs = ps.executeQuery();
            while(rs.next()){
             cliente.setCodigoCliente(rs.getInt(1));
             cliente.setNITCliente(rs.getString(2));
             cliente.setNombresCliente(rs.getString(3));
             cliente.setApellidosCliente(rs.getString(4));
             cliente.setDireccionCliente(rs.getString(5));
             cliente.setTelefonoCliente(rs.getString(6));
             cliente.setUsuario(rs.getString(7));
             cliente.setPasswor(rs.getString(8));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaCliente;
    }
    
    public int agregar(Clientes cliente){
        String sql = "Insert into Clientes(NITCliente, nombresCliente, apellidosCliente, direccionCliente, telefonoCliente, "
                + "usuario, passwor) values (?,?,?,?,?,?,?)";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNITCliente());
            ps.setString(2, cliente.getNombresCliente());
            ps.setString(3, cliente.getApellidosCliente());
            ps.setString(4, cliente.getDireccionCliente());
            ps.setString(5, cliente.getTelefonoCliente());
            ps.setString(6, cliente.getUsuario());
            ps.setString(7, cliente.getPasswor());
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    public Clientes buscarcodigoCliente(int id){
        String sql = "select * from Clientes where codigoCliente = ?";
        Clientes cliente = new Clientes();
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                cliente.setCodigoCliente(rs.getInt(1));
                cliente.setNITCliente(rs.getString(2));
                cliente.setNombresCliente(rs.getString(3));
                cliente.setApellidosCliente(rs.getString(4));
                cliente.setDireccionCliente(rs.getString(5));
                cliente.setTelefonoCliente(rs.getString(6));
                cliente.setUsuario(rs.getString(7));
                cliente.setPasswor(rs.getString(8));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return cliente;
    }
    
    public int actualizarCliente(Clientes cliente){
        String sql = "Update Cliente set NITCliente = ?, nombresCliente = ?, apellidosCliente = ?, direccionCliente = ?"
                + "  telefonoCliente = ?, usuario = ?, passwor = ?";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNITCliente());
            ps.setString(2, cliente.getNombresCliente());
            ps.setString(3, cliente.getApellidosCliente());
            ps.setString(4, cliente.getDireccionCliente());
            ps.setString(5, cliente.getTelefonoCliente());
            ps.setString(6, cliente.getUsuario());
            ps.setString(7, cliente.getPasswor());
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    public void eliminarCliente(int id){
        String sql = "delete from Clientes where codigoCliente = ?";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
