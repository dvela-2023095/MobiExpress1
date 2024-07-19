package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CargoEmpleadoDAO {
    Conexion cn= new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    int resp;
    
    public CargoEmpleado validar(String nombreCargo, int codigoCargoEmpleado){
        CargoEmpleado cargoEmpleado=new CargoEmpleado();
        String sql= "select * from CargoEmpleado where nombreCargo = ? and codigoCargoEmpleado = ?";
        try{
            con= cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, nombreCargo);
            ps.setInt(2, codigoCargoEmpleado);
            rs=ps.executeQuery();
            
            while(rs.next()){
                cargoEmpleado.setCodigoCargoEmpleado(rs.getInt("codigoCargoEmpleado"));
                cargoEmpleado.setNombreCargo(rs.getString("nombreCargo"));
                cargoEmpleado.setSalario(rs.getDouble("salario"));
                cargoEmpleado.setDescripcionCargo(rs.getString("descripcionCargo"));
                cargoEmpleado.setJornada(rs.getString("jornada"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return cargoEmpleado;
    }
    
    public List listar(){
        String sql = "select * from CargoEmpleado";
        CargoEmpleado cargo = new CargoEmpleado();
        List<CargoEmpleado> listaCargoEmpleado = new ArrayList<>();
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                cargo.setCodigoCargoEmpleado(rs.getInt(1));
                cargo.setNombreCargo(rs.getString(2));
                cargo.setSalario(rs.getDouble(3));
                cargo.setDescripcionCargo(rs.getString(4));
                cargo.setJornada(rs.getString(5));
                listaCargoEmpleado.add(cargo);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaCargoEmpleado;
    }
    
    public int agregar(CargoEmpleado cargo){
        String sql = "insert into CargoEmpleado(nombreCargo, salario, descripcionCargo, jornada)values(?,?,?,?);";
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, cargo.getNombreCargo());
            ps.setDouble(2, cargo.getSalario());
            ps.setString(3, cargo.getDescripcionCargo());
            ps.setString(4, cargo.getJornada());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    public CargoEmpleado buscarCodigoCargoEmpleado(int id){
        String sql= "select * from CargoEmpleado where codigoCargoEmpleado = ?";
        CargoEmpleado cargo = new CargoEmpleado();
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            rs=ps.executeQuery();
            while(rs.next()){
                cargo.setCodigoCargoEmpleado(rs.getInt(1));
                cargo.setNombreCargo(rs.getString(2));
                cargo.setSalario(rs.getDouble(3));
                cargo.setDescripcionCargo(rs.getString(4));
                cargo.setJornada(rs.getString(5));
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return cargo;
    }
    
    public int actualizarCargo(CargoEmpleado cargo){
        String sql="Update CargoEmpleado set nombreCargo = ?, salario = ?, descripcionCargo = ?, jornada = ?";
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, cargo.getNombreCargo());
            ps.setDouble(2, cargo.getSalario());
            ps.setString(3, cargo.getDescripcionCargo());
            ps.setString(4, cargo.getJornada());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    public void eliminarCargo(int id){
        String sql="delete from CargoEmpleado where codigoCargoEmpleado = ?";
        try{
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
