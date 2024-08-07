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
    public List<Producto> listar() {
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

    // Método Agregar
    public int agregar(Producto producto) {
        String sql = "INSERT INTO Producto (producto, descripcion, costoRenta, existencia, tamanio, codigoCategoriaProducto) values (?, ?, ?, ?, ?, ?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, producto.getProducto());
            ps.setString(2, producto.getDescripción());
            ps.setDouble(3, producto.getCostoRenta());
            ps.setInt(4, producto.getExistencia());
            ps.setString(5, producto.getTamanio());
            ps.setInt(6, producto.getCodigoCategoriaProducto());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    // Método Buscar
    public Producto listarCodigoProducto(int id) {
        Producto prod = new Producto();
        String sql = "select * from Producto where codigoProducto = " + id;
try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                prod.setCodigoProducto(rs.getInt(1));
                prod.setProducto(rs.getString(2));
                prod.setDescripción(rs.getString(3));
                prod.setCostoRenta(rs.getDouble(4));
                prod.setExistencia(rs.getInt(5));
                prod.setTamanio(rs.getString(6));
                prod.setCodigoCategoriaProducto(rs.getInt(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prod;
    }

    // Método Actualizar
    public int actualizarProducto(Producto producto) {
        String sql = "update Producto set producto = ?, descripcion = ?, costoRenta = ?, existencia = ?, tamanio = ?, codigoCategoriaProducto = ? where codigoProducto = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, producto.getProducto());
            ps.setString(2, producto.getDescripción());
            ps.setDouble(3, producto.getCostoRenta());
            ps.setInt(4, producto.getExistencia());
            ps.setString(5, producto.getTamanio());
            ps.setInt(6, producto.getCodigoCategoriaProducto());
            ps.setInt(7, producto.getCodigoProducto());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    // Método Eliminar
    public void eliminarProducto (int id) {
        String sql = "delete from Producto where codigoProducto = " + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
