
package modelo;
/*
create table Producto(
	codigoProducto int not null auto_increment,
    producto varchar(45) not null,
    descripcion varchar(45) not null,
    costoRenta double(10,2) not null,
    existencia int not null,
    tamanio varchar(10) not null,
    codigoCategoriaProducto int not null,
    primary key PK_codigoProducto (codigoProducto),
    constraint FK_Producto_CategoriaProducto foreign key (codigoCategoriaProducto) 
		references CategoriaProducto(codigoCategoriaProducto)
);
*/
public class Producto {
    private int codigoProducto;
    private String producto;
    private String descripción;
    private double costoRenta;
    private int existencia;
    private String tamanio;
    private int codigoCategoriaProducto;

    public Producto() {
    }

    public Producto(int codigoProducto, String producto, String descripción, double costoRenta, int existencia, String tamanio, int codigoCategoriaProducto) {
        this.codigoProducto = codigoProducto;
        this.producto = producto;
        this.descripción = descripción;
        this.costoRenta = costoRenta;
        this.existencia = existencia;
        this.tamanio = tamanio;
        this.codigoCategoriaProducto = codigoCategoriaProducto;
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

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
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
    
    
}
