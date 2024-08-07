package modelo;

public class Producto {
    private int codigoProducto;
    private String producto;
    private String descripcion;
    private double costoRenta;
    private int existencia;
    private String tamanio;
    private int codigoCategoriaProducto;
    private String imgProducto;

    public Producto() {
    }

    public Producto(int codigoProducto, String producto, String descripcion, double costoRenta, int existencia, String tamanio, int codigoCategoriaProducto, String imgProducto) {
        this.codigoProducto = codigoProducto;
        this.producto = producto;
        this.descripcion = descripcion;
        this.costoRenta = costoRenta;
        this.existencia = existencia;
        this.tamanio = tamanio;
        this.codigoCategoriaProducto = codigoCategoriaProducto;
        this.imgProducto = imgProducto;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCostoRenta() {
        return costoRenta;
    }

    public void setCostoRenta(double costoRenta) {
        this.costoRenta = costoRenta;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public int getCodigoCategoriaProducto() {
        return codigoCategoriaProducto;
    }

    public void setCodigoCategoriaProducto(int codigoCategoriaProducto) {
        this.codigoCategoriaProducto = codigoCategoriaProducto;
    }

    public String getImgProducto() {
        return imgProducto;
    }

    public void setImgProducto(String imgProducto) {
        this.imgProducto = imgProducto;
    }

}
