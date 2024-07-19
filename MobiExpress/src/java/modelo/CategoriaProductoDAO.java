
package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class CategoriaProductoDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    int resp;
    
    public List listar(){
        String sql = "select * from CategoriaProducto";
        CategoriaProducto cp = new CategoriaProducto();
        List<CategoriaProducto> listaCategoriaProducto = new ArrayList<>();
        try{
            con = cn.Conexion();
            ps =con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                cp.setCodigoCategoriaProducto(rs.getInt(1));
                cp.setCategoria(rs.getString(2));
                cp.setTipoDeProducto(rs.getString(3));
                cp.setColor(rs.getString(4));
                cp.setMarca(rs.getString(5));
                
            }
        }catch(Exception e){
         e.printStackTrace();
        }
        return listaCategoriaProducto;
    }
    
    public int agregar(CategoriaProducto cp){
        String sql = "insert CategoriaProducto(categoria,tipoDeProducto,color,marca) values (?,?,?,?)";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cp.getCategoria());
            ps.setString(2, cp.getTipoDeProducto());
            ps.setString(3, cp.getColor());
            ps.setString(4, cp.getMarca());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    //Buscar
    
    public CategoriaProducto buscarCodigo(int id){
        String sql = "select * from categoriaProducto where codigoCategoriaProducto =?";
        CategoriaProducto cp = new CategoriaProducto();
        try{
              while(rs.next()){
                cp.setCodigoCategoriaProducto(rs.getInt(1));
                cp.setCategoria(rs.getString(2));
                cp.setTipoDeProducto(rs.getString(3));
                cp.setColor(rs.getString(4));
                cp.setMarca(rs.getString(5));
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return cp;
    }
    
    public int actualizarCategoriaProducto(CategoriaProducto cp){
        String sql = "Update CategoriaProducto set categoria=?, tipoDeProducto=?, color=?, marca=?";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cp.getCategoria());
            ps.setString(2, cp.getTipoDeProducto());
            ps.setString(3, cp.getColor());
            ps.setString(4, cp.getMarca());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    public void eliminarCategoriaProducto(int id){
        String sql = "delete from CategoriaProducto where codigoCategoriaProducto =? ";
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
