
package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClientesDAO {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private Conexion cn = new Conexion();
    private int resp;

    
    public List<Clientes> listar() {
        String sql = "Select * from Clientes";
        List<Clientes> listaClientes = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Clientes cliente = new Clientes();
                cliente.setCodigoCliente(rs.getInt("codigoCliente"));
                cliente.setNITCliente(rs.getString("NITCliente"));
                cliente.setNombresCliente(rs.getString("nombresCliente"));
                cliente.setApellidosCliente(rs.getString("apellidosCliente"));
                cliente.setDireccionCliente(rs.getString("direccionCliente"));
                cliente.setTelefonoCliente(rs.getString("telefonoCliente"));
                listaClientes.add(cliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaClientes;
    }


    public int agregar(Clientes cliente) {
        String sql = "insert into Clientes (NITCliente, nombresCliente, apellidosCliente, direccionCliente, telefonoCliente) values (?,?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNITCliente());
            ps.setString(2, cliente.getNombresCliente());
            ps.setString(3, cliente.getApellidosCliente());
            ps.setString(4, cliente.getDireccionCliente());
            ps.setString(5, cliente.getTelefonoCliente());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }


    public Clientes listarCodigoCliente(int id) {
        Clientes cliente = new Clientes();
        String sql = "Select * from Clientes where codigoCliente = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                cliente.setCodigoCliente(rs.getInt("codigoCliente"));
                cliente.setNITCliente(rs.getString("NITCliente"));
                cliente.setNombresCliente(rs.getString("nombresCliente"));
                cliente.setApellidosCliente(rs.getString("apellidosCliente"));
                cliente.setDireccionCliente(rs.getString("direccionCliente"));
                cliente.setTelefonoCliente(rs.getString("telefonoCliente"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cliente;
    }


    public int actualizar(Clientes cliente) {
        String sql = "Update Clientes set NITCliente = ?, nombresCliente = ?, apellidosCliente = ?, direccionCliente = ?, telefonoCliente = ? where codigoCliente = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNITCliente());
            ps.setString(2, cliente.getNombresCliente());
            ps.setString(3, cliente.getApellidosCliente());
            ps.setString(4, cliente.getDireccionCliente());
            ps.setString(5, cliente.getTelefonoCliente());
            ps.setInt(6, cliente.getCodigoCliente());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }


    public void eliminar(int id) {
        String sql = "Delete from Clientes where codigoCliente = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}