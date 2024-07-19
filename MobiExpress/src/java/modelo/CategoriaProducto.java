
package modelo;


public class CategoriaProducto {
    private int codigoCategoriaProducto;
    private String categoria;
    private String tipoDeProducto;
    private String color;
    private String marca;

    public CategoriaProducto() {
    }

    public CategoriaProducto(int codigoCategoriaProducto, String categoria, String tipoDeProducto, String color, String marca) {
        this.codigoCategoriaProducto = codigoCategoriaProducto;
        this.categoria = categoria;
        this.tipoDeProducto = tipoDeProducto;
        this.color = color;
        this.marca = marca;
    }

    public int getCodigoCategoriaProducto() {
        return codigoCategoriaProducto;
    }

    public void setCodigoCategoriaProducto(int codigoCategoriaProducto) {
        this.codigoCategoriaProducto = codigoCategoriaProducto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTipoDeProducto() {
        return tipoDeProducto;
    }

    public void setTipoDeProducto(String tipoDeProducto) {
        this.tipoDeProducto = tipoDeProducto;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    
    
    
    
}
