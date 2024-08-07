
package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ComprasDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    
    public Compras validar(int numeroCompra,String descripcion){
        Compras compra = new Compras();
        String sql = "select * from Compras where numeroCompra = ? and descripcion = ?";
        try{
            con =cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, numeroCompra);
            ps.setString(2, descripcion);
            rs =ps.executeQuery();
            while (rs.next()){
                compra.setNumeroCompra(rs.getInt("numeroCompra"));
                compra.setDescripcion(rs.getString("descripcion"));
                compra.setMontoTotal(rs.getDouble("montoTotal"));
                compra.setFechaDeCompra(rs.getDate("fechaDeCompra"));
                compra.setEstado(rs.getString("estado"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        return compra;
    }
    
    public List<Compras> listar(){
        String sql = "select * from Compras";
        List<Compras>listaCompras = new ArrayList<>();
            try{
                con = cn.Conexion();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
                Compras compra = new Compras();
                compra.setNumeroCompra(rs.getInt("numeroCompra"));
                compra.setDescripcion(rs.getString("descripcion"));
                compra.setMontoTotal(rs.getDouble("montoTotal"));
                compra.setFechaDeCompra(rs.getDate("fechaDeCompra"));
                compra.setEstado(rs.getString("estado"));
                listaCompras.add(compra);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        
        return listaCompras;
    }
    
    public int agregar(Compras compra){
        String sql = "insert into Compras (numeroCompra, descripcion, montoTotal, fechaDeCompra, estado) values (?, ?, ?, ?, ?)";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            ps.setString(1, compra.getDescripcion());
            ps.setDouble(2, compra.getMontoTotal());
            ps.setDate(3,new java.sql.Date(compra.getFechaDeCompra().getTime()));
            ps.setString(4, compra.getEstado());
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    
    public Compras listarNumeroCompra(int id) {
        Compras compra = new Compras();
        String sql = "Select * from Compras where numeroCompra = " + id;
        try {
            con = cn.Conexion();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
                compra.setNumeroCompra(rs.getInt("numeroCompra"));
                compra.setDescripcion(rs.getString("descripcion"));
                compra.setMontoTotal(rs.getDouble("montoTotal"));
                compra.setFechaDeCompra(rs.getDate("fechaDeCompra"));
                compra.setEstado(rs.getString("estado"));
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return compra;
    }
    
     public int actualizar(Compras compra) {
        String sql = "Update Compras set descripcion = ?, montoTotal = ?, fechaDeCompra = ?, estado = ? where numeroCompra = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, compra.getDescripcion());
            ps.setDouble(2, compra.getMontoTotal());
            ps.setDate(3, new java.sql.Date(compra.getFechaDeCompra().getTime()));
            ps.setString(4, compra.getEstado());
            ps.setInt(5, compra.getNumeroCompra());
            resp = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }
     
     public void eliminar(int id){
         String sql = "Delete from Compras where numeroCompra =" +id;
         try{
             con = cn.Conexion();
             ps = con.prepareStatement(sql);
             ps.executeUpdate();
         }catch(Exception e){
             e.printStackTrace();
         }
     }
}