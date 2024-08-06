/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author Ricardo
 */
public class Compras {
    private int numeroCompra;
    private String descripcion;
    private double montoTotal;
    private Date fechaDeCompra;
    private String estado;

    public Compras() {
    }

    public Compras(int numeroCompra, String descripcion, double montoTotal, Date fechaDeCompra, String estado) {
        this.numeroCompra = numeroCompra;
        this.descripcion = descripcion;
        this.montoTotal = montoTotal;
        this.fechaDeCompra = fechaDeCompra;
        this.estado = estado;
    }

    public int getNumeroCompra() {
        return numeroCompra;
    }

    public void setNumeroCompra(int numeroCompra) {
        this.numeroCompra = numeroCompra;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Date getFechaDeCompra() {
        return fechaDeCompra;
    }

    public void setFechaDeCompra(Date fechaDeCompra) {
        this.fechaDeCompra = fechaDeCompra;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
