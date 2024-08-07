/*
Fecha de Creación:
    18/07/2024
*/
package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



public class DetalleCompraDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    
    public List listar(){
        String sql = "Select codigoDetalleCompra,codigoProveedor, codigoProducto, numeroCompra,costo,cantidad,subTotal,direccion,fechaRecepcion from DetalleCompra;";
        List<DetalleCompra> listaDetalleCompra = new ArrayList<>();
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                DetalleCompra em = new DetalleCompra();
                em.setCodigoDetalleCompra(rs.getInt(1));
                em.setCodigoProducto(rs.getInt(2));
                em.setCodigoProveedor(rs.getInt(3));
                em.setNumeroCompra(rs.getInt(4));
                em.setCosto(rs.getDouble(5));
                em.setCantidad(rs.getInt(6));
                em.setSubTotal(rs.getDouble(7));
                em.setDireccion(rs.getString(8));
                em.setFechaRecepcion(rs.getString(9));
                listaDetalleCompra.add(em);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return listaDetalleCompra;
    }
    
    public int agregar(DetalleCompra deCom){
        String sql = "insert into DetalleCompra (cantidad, costo, direccion, subTotal, fechaRecepcion, codigoProveedor, codigoProducto, numeroCompra) values (?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, deCom.getCantidad());
            ps.setDouble(2, deCom.getCosto());
            ps.setString(3, deCom.getDireccion());
            ps.setDouble(4, deCom.getSubTotal());
            ps.setString(5, deCom.getFechaRecepcion());
            ps.setInt(6, deCom.getCodigoProveedor());
            ps.setInt(7, deCom.getCodigoProducto());
            ps.setInt(8, deCom.getNumeroCompra());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    public DetalleCompra buscarDetallleCompra(int id){
        DetalleCompra deCom = new DetalleCompra();
        String sql = "Select * from DetalleCompra where codigoDetalleCompra = "+id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                deCom.setCodigoProveedor(rs.getInt(2));
                deCom.setCodigoProducto(rs.getInt(3));
                deCom.setNumeroCompra(rs.getInt(4));
                deCom.setCosto(rs.getDouble(5));
                deCom.setCantidad(rs.getInt(6));
                deCom.setSubTotal(rs.getDouble(7));
                deCom.setDireccion(rs.getString(8));
                deCom.setFechaRecepcion(rs.getString(9));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return deCom;
    }
    
    public int actualizarDetalleCompra(DetalleCompra deCom){
        String sql = "Update DetalleCompra set cantidad = ?, "
                + "costo = ?, direccion = ?, "
                + "subTotal = ?, "
                + "fechaRecepcion = ? "
                + "where codigoEmpleado = ?";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, deCom.getCantidad());
            ps.setDouble(2, deCom.getCosto());
            ps.setString(3,deCom.getDireccion());
            ps.setDouble(4, deCom.getSubTotal());
            ps.setString(5, deCom.getFechaRecepcion());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    public void eliminarDetalleCompra(int id){
        String sql = "Delete from DetalleCompra where codigoDetalleCompra ="+id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public List detalleDeCompra( int codCompra){
        String sql = "Select DC.*, P.producto from DetalleCompra DC, Producto P where DC.codigoProducto=P.codigoProducto and DC.numeroCompra ="+ codCompra;
        List<DetalleCompra> listaDetalleCompra = new ArrayList<>();
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                DetalleCompra em = new DetalleCompra();
                em.setCodigoDetalleCompra(rs.getInt(1));
                em.setCantidad(rs.getInt(2));
                em.setCosto(rs.getDouble(3));
                em.setDireccion(rs.getString(4));
                em.setSubTotal(rs.getDouble(5));
                em.setFechaRecepcion(rs.getString(6));
                em.setCodigoProducto(rs.getInt(7));
                em.setCodigoProveedor(rs.getInt(8));
                em.setNumeroCompra(rs.getInt(9));
                em.setNombreProducto(rs.getString(10));
                listaDetalleCompra.add(em);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaDetalleCompra;
    }
}
