
package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    
    public Producto buscarProducto(int id){
        Producto producto = new Producto();
        String sql = "select*from Producto where codigoProducto="+id;
        try{
            con = cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                producto.setCodigoProducto(rs.getInt(1));
                producto.setProducto(rs.getString(2));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return producto;
    }
    public List listar() {
        String sql = "select * from Producto";
        List<Producto> listaProducto = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto prod = new Producto();
                prod.setCodigoProducto(rs.getInt(1));
                prod.setProducto(rs.getString(2));
                prod.setDescripción(rs.getString(3));
                prod.setCostoRenta(rs.getDouble(4));
                prod.setExistencia(rs.getInt(5));
                prod.setTamanio(rs.getString(6));
                prod.setCodigoCategoriaProducto(rs.getInt(7));
                listaProducto.add(prod);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaProducto;
    }
    
    public List productosDetalle(int noCompra){
        String sql = "select P.producto from Producto P, DetalleCompra DC where DC.numeroCompra="+noCompra+" and P.codigoProducto = DC.codigoProducto; ";
        List<String> listaProductos= new ArrayList<>();
        try{
            con = cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                String prod="";
                prod = rs.getString(1);
                listaProductos.add(prod);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaProductos;
    }
    
    public List productosDeDetalle(List<DetallePedido> listaDetalle){
        String sql="select existencia,codigoProducto from Producto where codigoProducto= ?";
        List<Producto> listaProductos=new ArrayList<>();
        try{
            for(int i=0;i<listaDetalle.size();i++){
                con = cn.Conexion();
                ps=con.prepareStatement(sql);
                ps.setInt(1, listaDetalle.get(i).getCodigoProducto());
                rs=ps.executeQuery();
                while(rs.next()){
                    Producto prod = new Producto();
                    prod.setExistencia(rs.getInt(1));
                    prod.setCodigoProducto(rs.getInt(2));
                    listaProductos.add(prod);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaProductos;
    }
    
    public List calcularStockDisponible(List<Producto> listaProducto,List<DetallePedido>listaDetalle){
        for(int i=0;i<listaProducto.size();i++){
            for(int j=0;j<listaDetalle.size();j++){
                if(listaProducto.get(i).getCodigoProducto()==listaDetalle.get(j).getCodigoProducto()){
                    Producto prod = listaProducto.get(i);
                    prod.setExistencia(prod.getExistencia()-listaDetalle.get(j).getCantidad());
                    listaProducto.set(i, prod);
                }
            }
        }
        return listaProducto;
    }
}
