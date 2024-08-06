package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProveedoresDAO {
   private Connection con;
   private PreparedStatement ps;
   private ResultSet rs;
   private Conexion cn = new Conexion();
   private int resp;

   // Método para listar todos los proveedores
   public List<Proveedores> listar() {
       String sql = "select * from Proveedores";
       List<Proveedores> listaProveedores = new ArrayList<>();
       try {
           con = cn.Conexion();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while (rs.next()) {
               Proveedores proveedor = new Proveedores();
               proveedor.setCodigoProveedor(rs.getInt("codigoProveedor"));
               proveedor.setNITProveedor(rs.getString("NITProveedor"));
               proveedor.setNombreProveedor(rs.getString("nombreProveedor"));
               proveedor.setApellidoProveedor(rs.getString("apellidoProveedor"));
               proveedor.setRazonSocial(rs.getString("razonSocial"));
               proveedor.setContactoPrincipal(rs.getString("contactoPrincipal"));
               proveedor.setPaginaWeb(rs.getString("paginaWeb"));
               listaProveedores.add(proveedor);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return listaProveedores;
   }

   // Método para agregar un nuevo proveedor
   public int agregar(Proveedores proveedor) {
       String sql = "insert into Proveedores (NITProveedor, nombreProveedor, apellidoProveedor, razonSocial, contactoPrincipal, paginaWeb) values (?, ?, ?, ?, ?, ?)";
       try {
           con = cn.Conexion();
           ps = con.prepareStatement(sql);
           ps.setString(1, proveedor.getNITProveedor());
           ps.setString(2, proveedor.getNombreProveedor());
           ps.setString(3, proveedor.getApellidoProveedor());
           ps.setString(4, proveedor.getRazonSocial());
           ps.setString(5, proveedor.getContactoPrincipal());
           ps.setString(6, proveedor.getPaginaWeb());
           resp = ps.executeUpdate();
       } catch (Exception e) {
           e.printStackTrace();
       }
       return resp;
   }

   // Método para listar un proveedor por su código
   public Proveedores listarCodigoProveedor(int id) {
       Proveedores proveedor = new Proveedores();
       String sql = "Select * from Proveedores where codigoProveedor = " + id;
       try {
           con = cn.Conexion();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while (rs.next()) {
               proveedor.setCodigoProveedor(rs.getInt("codigoProveedor"));
               proveedor.setNITProveedor(rs.getString("NITProveedor"));
               proveedor.setNombreProveedor(rs.getString("nombreProveedor"));
               proveedor.setApellidoProveedor(rs.getString("apellidoProveedor"));
               proveedor.setRazonSocial(rs.getString("razonSocial"));
               proveedor.setContactoPrincipal(rs.getString("contactoPrincipal"));
               proveedor.setPaginaWeb(rs.getString("paginaWeb"));
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return proveedor;
   }

   // Método para actualizar un proveedor
   public int actualizar(Proveedores proveedor) {
       String sql = "Update Proveedores set NITProveedor = ?, nombreProveedor = ?, apellidoProveedor = ?, razonSocial = ?, contactoPrincipal = ?, paginaWeb = ? where codigoProveedor = ?";
       try {
           con = cn.Conexion();
           ps = con.prepareStatement(sql);
           ps.setString(1, proveedor.getNITProveedor());
           ps.setString(2, proveedor.getNombreProveedor());
           ps.setString(3, proveedor.getApellidoProveedor());
           ps.setString(4, proveedor.getRazonSocial());
           ps.setString(5, proveedor.getContactoPrincipal());
           ps.setString(6, proveedor.getPaginaWeb());
           ps.setInt(7, proveedor.getCodigoProveedor());
           resp = ps.executeUpdate();
       } catch (Exception e) {
           e.printStackTrace();
       }
       return resp;
   }

   // Método para eliminar un proveedor
   public void eliminar(int id) {
       String sql = "Delete from Proveedores where codigoProveedor = " + id;
       try {
           con = cn.Conexion();
           ps = con.prepareStatement(sql);
           ps.executeUpdate();
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
}
