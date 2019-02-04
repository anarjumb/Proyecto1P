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
public class Compra {
    private String id;
    private String cedula_c;
    private String id_producto;
    private int cantidad;
    private String fecha;

    public Compra(String id, String cedula_c, String id_producto, int cantidad, String fecha) {
        this.id = id;
        this.cedula_c = cedula_c;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCedula_c() {
        return cedula_c;
    }

    public void setCedula_c(String cedula_c) {
        this.cedula_c = cedula_c;
    }

    public String getId_producto() {
        return id_producto;
    }

    public void setId_producto(String id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    
    
}
