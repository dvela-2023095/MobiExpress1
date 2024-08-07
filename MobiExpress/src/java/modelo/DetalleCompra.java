package modelo;




import java.util.Date;


public class DetalleCompra {
    private int codigoDetalleCompra;
    private int cantidad;
    private double costo;
    private String direccion;
    private String observaciones;
    private Date fechaReception;
    private int codigoProveedor;
    private int codigoProducto;
    private int numeroCompra;

    public DetalleCompra() {
    }

    public DetalleCompra(int codigoDetalleCompra, int cantidad, double costo, String direccion, String observaciones, Date fechaReception, int codigoProveedor, int codigoProducto, int numeroCompra) {
        this.codigoDetalleCompra = codigoDetalleCompra;
        this.cantidad = cantidad;
        this.costo = costo;
        this.direccion = direccion;
        this.observaciones = observaciones;
        this.fechaReception = fechaReception;
        this.codigoProveedor = codigoProveedor;
        this.codigoProducto = codigoProducto;
        this.numeroCompra = numeroCompra;
    }

    public int getCodigoDetalleCompra() {
        return codigoDetalleCompra;
    }

    public void setCodigoDetalleCompra(int codigoDetalleCompra) {
        this.codigoDetalleCompra = codigoDetalleCompra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaReception() {
        return fechaReception;
    }

    public void setFechaReception(Date fechaReception) {
        this.fechaReception = fechaReception;
    }

    public int getCodigoProveedor() {
        return codigoProveedor;
    }

    public void setCodigoProveedor(int codigoProveedor) {
        this.codigoProveedor = codigoProveedor;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public int getNumeroCompra() {
        return numeroCompra;
    }

    public void setNumeroCompra(int numeroCompra) {
        this.numeroCompra = numeroCompra;
    }
}
