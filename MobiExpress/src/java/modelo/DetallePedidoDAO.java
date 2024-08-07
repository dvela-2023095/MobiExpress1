
package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DetallePedidoDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    
    //Método Buscar
    public DetallePedido buscarCodigoDetallePedido(int id){
        DetallePedido dp = new DetallePedido();
        String sql = "select * from DetallePedido where codigoDetallePedido";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                dp.setCodigoDetallePedido(rs.getInt(1));
                dp.setPrecioRenta(rs.getDouble(2));
                dp.setCantidad(rs.getInt(3));
                dp.setSubTotal(rs.getDouble(4));
                dp.setDescuento(rs.getInt(5));
                dp.setCodigoProducto(rs.getInt(6));
                dp.setNumeroPedido(rs.getInt(7));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dp;
    } 
    
    //Método Listar
    public List<DetallePedido> listar(){
        List<DetallePedido> listaDetallePedidos = new ArrayList<>();
        DetallePedido dp = new DetallePedido();
        String sql = "select * from DetallePedido";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                dp.setCodigoDetallePedido(rs.getInt(1));
                dp.setPrecioRenta(rs.getDouble(2));
                dp.setCantidad(rs.getInt(3));
                dp.setSubTotal(rs.getDouble(4));
                dp.setDescuento(rs.getInt(5));
                dp.setCodigoProducto(rs.getInt(6));
                dp.setNumeroPedido(rs.getInt(7));
                listaDetallePedidos.add(dp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaDetallePedidos;
    }
    
    //Método Agregar
    public int agregar(DetallePedido dp){
        String sql = " insert into DetallePedido(precioRenta, cantidad, subTotal, descuento, codigoProducto, numeroPedido) values (?,?,?,?,?,?)";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, dp.getPrecioRenta());
            ps.setInt(2, dp.getCantidad());
            ps.setDouble(3, dp.getSubTotal());
            ps.setInt(4, dp.getDescuento());
            ps.setInt(5, dp.getCodigoProducto());
            ps.setInt(6, dp.getNumeroPedido());
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    //Método Buscar
    public DetallePedido buscarDetallePedido(int id){
        DetallePedido dp = new DetallePedido();
        String sql = "select * from where codigoDetallePedido=?";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()){
                dp.setCodigoDetallePedido(rs.getInt(1));
                dp.setPrecioRenta(rs.getDouble(2));
                dp.setCantidad(rs.getInt(3));
                dp.setSubTotal(rs.getDouble(4));
                dp.setDescuento(rs.getInt(5));
                dp.setCodigoProducto(rs.getInt(6));
                dp.setNumeroPedido(rs.getInt(7));
            }       
        }catch (Exception e){
            e.printStackTrace();
        }
        return dp;
    }
    
    //Método Actualizar
    public int actualizarDetallePedido(DetallePedido dp){
        String sql = "Update DetallePedido set precioRenta=?, cantidad=?, subTotal=?, Descuento=?, codigoProducto=?, numeroPedido=? where codigoDetallePedido=?";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, dp.getPrecioRenta());
            ps.setInt(2, dp.getCantidad());
            ps.setDouble(3, dp.getSubTotal());
            ps.setInt(4, dp.getDescuento());
            ps.setInt(5, dp.getCodigoProducto());
            ps.setInt(6, dp.getNumeroPedido());
            ps.setInt(7, dp.getCodigoDetallePedido());
            resp = ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    public List buscarDetalles(List<Pedido> listaDePedidos){
        List<DetallePedido> listaDetalles = new ArrayList<>();
        for(int i=0;i<listaDePedidos.size();i++){
            String sql = "select*from DetallePedido where numeroPedido=?";
            try{
                con = cn.Conexion();
                ps = con.prepareStatement(sql);
                ps.setInt(1, listaDePedidos.get(i).getNumeroPedido());
                rs = ps.executeQuery();
                while(rs.next()){
                    DetallePedido detalle = new DetallePedido();
                    detalle.setCantidad(rs.getInt(3));
                    detalle.setCodigoProducto(rs.getInt(6));
                    listaDetalles.add(detalle);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }
        return listaDetalles;
    }
    
    public List asignarStock(List<Producto> listaDeProductos,List<DetallePedido>listaDeDetalles){
        for(int i=0;i<listaDeDetalles.size();i++){
            for(int j=0;j<listaDeProductos.size();j++){
                if(listaDeDetalles.get(i).getCodigoProducto()==listaDeProductos.get(j).getCodigoProducto()){
                    DetallePedido det = new DetallePedido();
                    det=listaDeDetalles.get(i);
                    det.setCantidad(listaDeProductos.get(j).getExistencia());
                    listaDeDetalles.set(i, det);
                }else{
                    
                }
            }
        }
       return listaDeDetalles;
    }
    public List buscarDetalles(int id){
        String sql = "select DP.*,P.producto from DetallePedido DP,Producto P where numeroPedido="+id;
        List<DetallePedido> listaDetalles = new ArrayList<>();
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                DetallePedido dp = new DetallePedido();
                dp.setCodigoDetallePedido(rs.getInt(1));
                dp.setPrecioRenta(rs.getDouble(2));
                dp.setCantidad(rs.getInt(3));
                dp.setSubTotal(rs.getDouble(4));
                dp.setDescuento(rs.getInt(5));
                dp.setCodigoProducto(rs.getInt(6));
                dp.setNumeroPedido(rs.getInt(7));
                dp.setNombreProducto(rs.getString(8));
                listaDetalles.add(dp);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaDetalles;
    }
    
    public void eliminar(int id){
        String sql = "delete from DetallePedido where codigoDetallePedido="+id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}





