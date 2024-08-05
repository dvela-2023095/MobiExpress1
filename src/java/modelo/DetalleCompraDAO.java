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
        String sql = "Select * from DetalleCompra";
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
                em.setObservaciones(rs.getString(5));
                em.setFechaReception(rs.getDate(6));
                em.setCodigoProducto(rs.getInt(7));
                em.setCodigoProveedor(rs.getInt(8));
                em.setNumeroCompra(rs.getInt(9));
                listaDetalleCompra.add(em);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return listaDetalleCompra;
    }
    
    public int agregar(DetalleCompra deCom){
        String sql = "insert into DetalleCompra (cantidad, costo, direccion, observaciones, fechaReception, codigoProveedor, codigoProducto, numeroCompra) values (?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, deCom.getCantidad());
            ps.setDouble(2, deCom.getCosto());
            ps.setString(3, deCom.getDireccion());
            ps.setString(4, deCom.getObservaciones());
            ps.setDate(5, new java.sql.Date(deCom.getFechaReception().getTime()));
            ps.setInt(6, deCom.getCodigoProveedor());
            ps.setInt(7, deCom.getCodigoProducto());
            ps.setInt(8, deCom.getNumeroCompra());
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
                deCom.setCantidad(rs.getInt(2));
                deCom.setCosto(rs.getDouble(3));
                deCom.setDireccion(rs.getString(4));
                deCom.setObservaciones(rs.getString(5));
                deCom.setFechaReception(rs.getDate(6));
                deCom.setCodigoProveedor(rs.getInt(7));
                deCom.setCodigoProducto(rs.getInt(8));
                deCom.setNumeroCompra(rs.getInt(9));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return deCom;
    }
    
    public int actualizarDetalleCompra(DetalleCompra deCom){
        String sql = "Update DetalleCompra set cantidad = ?, "
                + "costo = ?, direccion = ?, "
                + "observaciones = ?, "
                + "fechaReception = ? "
                + "where codigoEmpleado = ?";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, deCom.getCantidad());
            ps.setDouble(2, deCom.getCosto());
            ps.setString(3,deCom.getDireccion());
            ps.setString(4, deCom.getObservaciones());
            //ps.setDate(6, deCom.getFechaReception());
            ps.setDate(6, new java.sql.Date(deCom.getFechaReception().getTime()));
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
}
