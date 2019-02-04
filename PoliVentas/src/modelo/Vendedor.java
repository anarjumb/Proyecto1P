/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author adan
 */
public class Vendedor extends Comprador{
    private ArrayList<Integer> listaDeCalificaciones;

    public Vendedor(String cedula, String nombres, String apellidos, String telefono, String correo, String usuario, String contrasenia, String rol) {
        super(cedula, nombres, apellidos, telefono, correo, usuario, contrasenia, rol);
    }

    
    
    
    
    
    public void setListaDeCalificaciones(ArrayList<Integer> listaDeCalificaciones) {
        this.listaDeCalificaciones = listaDeCalificaciones;
    } 
    
    public float getCalificacionTotal(){
        float total=0;
        for(int i=0;i<=this.listaDeCalificaciones.size();i++){
            total=total+this.listaDeCalificaciones.get(i);
        }
        return total;
    }
}
