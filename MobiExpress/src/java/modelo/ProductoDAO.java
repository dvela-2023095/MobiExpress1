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

    // Método Listar
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
                prod.setDescripcion(rs.getString(3));
                prod.setCostoRenta(rs.getDouble(4));
                prod.setExistencia(rs.getInt(5));
                prod.setTamanio(rs.getString(6));
                prod.setImgProducto(rs.getString(7));
                prod.setCodigoCategoriaProducto(rs.getInt(8));
                listaProducto.add(prod);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaProducto;
    }

    // Método Agregar
    public int agregar(Producto producto) {
        String sql = "insert into Producto(producto, descripcion, costoRenta, existencia, tamanio, codigoCategoriaProducto, imgProducto) values (?, ?, ?, ?, ?, ?, ?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, producto.getProducto());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getCostoRenta());
            ps.setInt(4, producto.getExistencia());
            ps.setString(5, producto.getTamanio());
            ps.setInt(6, producto.getCodigoCategoriaProducto());
            ps.setString(7, producto.getImgProducto());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    // Método Buscar
    public Producto buscarCodigoProducto(int id) {
        Producto prod = new Producto();
        String sql = "select * from Producto where codigoProducto = " + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                prod.setCodigoProducto(rs.getInt(1));
                prod.setProducto(rs.getString(2));
                prod.setDescripcion(rs.getString(3));
                prod.setCostoRenta(rs.getDouble(4));
                prod.setExistencia(rs.getInt(5));
                prod.setTamanio(rs.getString(6));
                prod.setImgProducto(rs.getString(7));
                prod.setCodigoCategoriaProducto(rs.getInt(8));
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return prod;
    }

    // Método Actualizar
    public int actualizarProducto(Producto producto) {
        String sql = "update Producto set producto = ?, descripcion = ?, costoRenta = ?, existencia = ?, tamanio = ?, imgProducto = ? where codigoProducto = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, producto.getProducto());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getCostoRenta());
            ps.setInt(4, producto.getExistencia());
            ps.setString(5, producto.getTamanio());
            ps.setString(6, producto.getImgProducto());
            ps.setInt(7, producto.getCodigoProducto());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    // Método Eliminar
    public void eliminarProducto (int id) {
        String sql = "delete from Producto where codigoProducto = ? ";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
