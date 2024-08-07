
package modelo;

import java.util.Date;


public class Pedido {
    private int numeroPedido;
    private String direccion;
    private double montoTotal;
    private Date fechaDeEntrega;
    private Date fechaDeRetorno;
    private int codigoCliente;
    private int codigoEmpleado;
    private String Cliente;
    private String Empleado;

    public Pedido(){}

    public Pedido(int numeroPedido, String direccion, double montoTotal, Date fechaDeEntrega, Date fechaDeRetorno, int codigoCliente, int codigoEmpleado, String Cliente, String Empleado) {
        this.numeroPedido = numeroPedido;
        this.direccion = direccion;
        this.montoTotal = montoTotal;
        this.fechaDeEntrega = fechaDeEntrega;
        this.fechaDeRetorno = fechaDeRetorno;
        this.codigoCliente = codigoCliente;
        this.codigoEmpleado = codigoEmpleado;
        this.Cliente = Cliente;
        this.Empleado = Empleado;
    }

    

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Date getFechaDeEntrega() {
        return fechaDeEntrega;
    }

    public void setFechaDeEntrega(Date fechaDeEntrega) {
        this.fechaDeEntrega = fechaDeEntrega;
    }

    public Date getFechaDeRetorno() {
        return fechaDeRetorno;
    }

    public void setFechaDeRetorno(Date fechaDeRetorno) {
        this.fechaDeRetorno = fechaDeRetorno;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public int getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(int codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String Cliente) {
        this.Cliente = Cliente;
    }

    public String getEmpleado() {
        return Empleado;
    }

    public void setEmpleado(String Empleado) {
        this.Empleado = Empleado;
    }
    
    
    
}
