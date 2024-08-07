package modelo;

public class DetallePedido {
    private int codigoDetallePedido;
    private double precioRenta;
    private int cantidad;
    private double subTotal;
    private String descuento;
    private int codigoProducto;
    private int numeroPedido;

    public DetallePedido() {
    }

    public DetallePedido(int codigoDetallePedido, double precioRenta, int cantidad, double subTotal, String descuento, int codigoProducto, int numeroPedido) {
        this.codigoDetallePedido = codigoDetallePedido;
        this.precioRenta = precioRenta;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
        this.descuento = descuento;
        this.codigoProducto = codigoProducto;
        this.numeroPedido = numeroPedido;
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

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
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
    
}