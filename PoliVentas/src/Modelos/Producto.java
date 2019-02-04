/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author Mi compu
 */
public class Producto {
    
    private String id;
    private String cedula_v;
    private String calificacion_total;
    private String descripcion;
    private String nombre;
    private float precio;

    public Producto(String id, String cedula_v, String calificacion_total, String descripcion, String nombre, float precio) {
        this.id = id;
        this.cedula_v = cedula_v;
        this.calificacion_total = calificacion_total;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCedula_v() {
        return cedula_v;
    }

    public void setCedula_v(String cedula_v) {
        this.cedula_v = cedula_v;
    }

    public String getCalificacion_total() {
        return calificacion_total;
    }

    public void setCalificacion_total(String calificacion_total) {
        this.calificacion_total = calificacion_total;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    
    
}
