
package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ClientesDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public Clientes validar (String usuario, String passwor){
        Clientes cliente = new Clientes();
        String sql = "select * from Clientes where usuario = ? and passwor = ?";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, passwor);
            rs=ps.executeQuery();
            while (rs.next()){
                cliente.setCodigoCliente(rs.getInt("codigoCliente"));
                cliente.setNombresCliente(rs.getString("nombresCliente"));
                cliente.setUsuario(rs.getString("usuario"));
                cliente.setPasswor(rs.getString("passwor"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return cliente;
    }
}
