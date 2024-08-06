package modelo;


public class CargoEmpleado {
    private int codigoCargoEmpleado;
    private String nombreCargo;
    private double salario;
    private String descripcionCargo;
    private String jornada;

    public CargoEmpleado() {
    }

    public CargoEmpleado(int codigoCargoEmpleado, String nombreCargo, double salario, String descripcionCargo, String jornada) {
        this.codigoCargoEmpleado = codigoCargoEmpleado;
        this.nombreCargo = nombreCargo;
        this.salario = salario;
        this.descripcionCargo = descripcionCargo;
        this.jornada = jornada;
    }

    public int getCodigoCargoEmpleado() {
        return codigoCargoEmpleado;
    }

    public void setCodigoCargoEmpleado(int codigoCargoEmpleado) {
        this.codigoCargoEmpleado = codigoCargoEmpleado;
    }

    public String getNombreCargo() {
        return nombreCargo;
    }

    public void setNombreCargo(String nombreCargo) {
        this.nombreCargo = nombreCargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getDescripcionCargo() {
        return descripcionCargo;
    }

    public void setDescripcionCargo(String descripcionCargo) {
        this.descripcionCargo = descripcionCargo;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }
    
    
    
}
