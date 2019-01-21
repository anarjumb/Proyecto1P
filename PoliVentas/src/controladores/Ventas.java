/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

/**
 *
 * @author Mi compu
 */
public class Ventas {
    
    private String comprador;
    private String producto;
    private int cantidad;
    private float precio;

    public Ventas(String comprador, String producto, int cantidad, float precio) {
        this.comprador = comprador;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int catidad) {
        this.cantidad = catidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    
    
}
