package modelo;

import java.util.Date;


public class DetalleCompra {
    private int codigoDetalleCompra;
    private int cantidad;
    private double costo;
    private String direccion;
    private double subTotal;
    private String fechaRecepcion;
    private int codigoProveedor;
    private int codigoProducto;
    private int numeroCompra;
    private int item;
    private String nombreProducto;

    public DetalleCompra() {
    }

    public DetalleCompra(int codigoDetalleCompra, int cantidad, double costo, String direccion, double subTotal, String fechaRecepcion, int codigoProveedor, int codigoProducto, int numeroCompra, int item, String nombreProducto) {
        this.codigoDetalleCompra = codigoDetalleCompra;
        this.cantidad = cantidad;
        this.costo = costo;
        this.direccion = direccion;
        this.subTotal = subTotal;
        this.fechaRecepcion = fechaRecepcion;
        this.codigoProveedor = codigoProveedor;
        this.codigoProducto = codigoProducto;
        this.numeroCompra = numeroCompra;
        this.item = item;
        this.nombreProducto = nombreProducto;
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

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public String getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(String fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
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

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

   
    
    
    
}