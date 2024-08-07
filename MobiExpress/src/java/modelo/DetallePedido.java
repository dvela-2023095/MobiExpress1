package modelo;

public class DetallePedido {
    private int codigoDetallePedido;
    private double precioRenta;
    private int cantidad;
    private double subTotal;
    private int descuento;
    private int codigoProducto;
    private int numeroPedido;
    private String nombreProducto;
    

    public DetallePedido() {
    }

    public DetallePedido(int codigoDetallePedido, double precioRenta, int cantidad, double subTotal, int descuento, int codigoProducto, int numeroPedido, String nombreProducto) {
        this.codigoDetallePedido = codigoDetallePedido;
        this.precioRenta = precioRenta;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
        this.descuento = descuento;
        this.codigoProducto = codigoProducto;
        this.numeroPedido = numeroPedido;
        this.nombreProducto = nombreProducto;
    }

    

    public int getCodigoDetallePedido() {
        return codigoDetallePedido;
    }

    public void setCodigoDetallePedido(int codigoDetallePedido) {
        this.codigoDetallePedido = codigoDetallePedido;
    }

    public double getPrecioRenta() {
        return precioRenta;
    }

    public void setPrecioRenta(double precioRenta) {
        this.precioRenta = precioRenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    
}