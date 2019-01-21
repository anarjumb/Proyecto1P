/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

/**
 *
 * @author adan
 */
public class Producto {
    private String nombre;
    private String categoria;
    private float precio;
    private String tiempoEntrega;
    private int calificacionProducto;

    public Producto(String nombre, String categoria){
        this.nombre = nombre;
        this.categoria = categoria;
    }
    
    public Producto(String nombre, String categoria,float precio, String tiempoEntrega,int calificacionProducto) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.tiempoEntrega = tiempoEntrega;
        this.calificacionProducto = calificacionProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getTiempoEntrega() {
        return tiempoEntrega;
    }

    public void setTiempoEntrega(String tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }

    public int getCalificacionProducto() {
        return calificacionProducto;
    }

    public void setCalificacionProducto(int calificacionProducto) {
        this.calificacionProducto = calificacionProducto;
    }
    
    
}
