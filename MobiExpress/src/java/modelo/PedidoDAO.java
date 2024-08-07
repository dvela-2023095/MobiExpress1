
package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PedidoDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    int resp;
    
    //LISTAR
   public List listar(){
       String sql = "select * from Pedidos";
       Pedido ped = new Pedido();
       List<Pedido> listaPedidos = new ArrayList<>();
       try{
           con = cn.Conexion();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while(rs.next()){
               ped.setNumeroPedido(rs.getInt(1));
               ped.setDireccion(rs.getString(2));
               ped.setMontoTotal(rs.getDouble(3));
               ped.setFechaDeEntrega(rs.getDate(4));
               ped.setFechaDeRetorno(rs.getDate(5));
               ped.setCodigoCliente(rs.getInt(6));
               ped.setCodigoEmpleado(rs.getInt(7));
               listaPedidos.add(ped);
           }
       }catch(Exception e){
           e.printStackTrace();
       }
       
       return listaPedidos;
       
   }
   //AGREGAR
    public int agregar(Pedido ped){
            String sql = "insert into Pedidos(direccion,montoTotal,fechaDeEntrega,fechaDeRetorno,codigoCliente,codigoEmpleado) values(?,?,?,?,?,?)";
            try{
                con = cn.Conexion();
                ps = con.prepareStatement(sql);
                ps.setString(1, ped.getDireccion());
                ps.setDouble(2, ped.getMontoTotal());
                ps.setDate(3, new java.sql.Date(ped.getFechaDeEntrega().getTime()));
                ps.setDate(4, new java.sql.Date(ped.getFechaDeRetorno().getTime()));
                ps.setInt(5, ped.getCodigoCliente());
                ps.setInt(6, ped.getCodigoEmpleado());
                ps.executeUpdate();
            }catch(Exception e){
                e.printStackTrace();
            }
        return resp;
        }
    
    //BUSCAR
    
    public Pedido buscarNumeroPedido(int id){
        String sql = "select * from Pedidos where numeroPedido = ?";
        Pedido ped = new Pedido();
        try{
            con = cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            rs=ps.executeQuery();
            while(rs.next()){
               ped.setNumeroPedido(rs.getInt(1));
               ped.setDireccion(rs.getString(2));
               ped.setMontoTotal(rs.getDouble(3));
               ped.setFechaDeEntrega(rs.getDate(4));
               ped.setFechaDeRetorno(rs.getDate(5));
               ped.setCodigoCliente(rs.getInt(6));
               ped.setCodigoEmpleado(rs.getInt(7));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return ped;
    }
    //ACTUALIZAR
    public int actualizarPedido(Pedido ped){
        String sql="Update Pedidos set direccion=?, fechaDeEntrega=?, fechaDeRetorno=?";
        try{
             con = cn.Conexion();
                ps = con.prepareStatement(sql);
                ps.setString(1, ped.getDireccion());
                ps.setDouble(2, ped.getMontoTotal());
                ps.setDate(3, new java.sql.Date(ped.getFechaDeEntrega().getTime()));
                ps.setDate(4, new java.sql.Date(ped.getFechaDeRetorno().getTime()));
                ps.setInt(5, ped.getCodigoCliente());
                ps.setInt(6, ped.getCodigoEmpleado());
                ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    //ELIMINAR
    public void eliminarPedido(int id){
        String sql="delete from Pedidos where numeroPedido = ?";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        }catch(Exception e){
         e.printStackTrace();
        }
    }
    
    public List buscarPedidos(Date fechaEntrega){
        List<Pedido> listaDePedidos = new ArrayList<>();
        String sql = "select*from Pedidos where fechaDeRetorno >= ? and fechaDeEntrega <= ?";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(fechaEntrega.getTime()));
            ps.setDate(2, new java.sql.Date(fechaEntrega.getTime()));
            rs = ps.executeQuery();
            while(rs.next()){
               Pedido ped = new Pedido();
               ped.setNumeroPedido(rs.getInt(1));
               ped.setDireccion(rs.getString(2));
               ped.setMontoTotal(rs.getDouble(3));
               ped.setFechaDeEntrega(rs.getDate(4));
               ped.setFechaDeRetorno(rs.getDate(5));
               ped.setCodigoCliente(rs.getInt(6));
               ped.setCodigoEmpleado(rs.getInt(7));
               listaDePedidos.add(ped);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaDePedidos;
    }
    public int encontrarPedidoRecienAgregado(Pedido pedido){
        int codigo = 0;
        String sql = "select numeroPedido from Pedidos where direccion=? and montoTotal=? and fechaDeEntrega=? and fechaDeRetorno=? and codigoCliente=? and codigoEmpleado=?;";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, pedido.getDireccion());
            ps.setDouble(2, pedido.getMontoTotal());
            ps.setDate(3, new java.sql.Date(pedido.getFechaDeEntrega().getTime()));
            ps.setDate(4, new java.sql.Date(pedido.getFechaDeRetorno().getTime()));
            ps.setInt(5, pedido.getCodigoCliente());
            ps.setInt(6, pedido.getCodigoEmpleado());
            rs = ps.executeQuery();
            while(rs.next()){
                codigo = rs.getInt(1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return codigo;
    }
}

   


