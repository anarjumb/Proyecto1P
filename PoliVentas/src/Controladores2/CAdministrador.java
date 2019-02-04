/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores2;

import Modelos.Persona;
import Modelos.Usuario;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mi compu
 */
public class CAdministrador {
    
    controladores.Conexion con = new controladores.Conexion();
    
    
    public boolean AgregarAdministrador(Usuario u, Persona p){
        
        
        PreparedStatement stmt;
        
        
        String query="INSERT INTO `"+u.getTipo()+"` (`cedula`, `nombres`, `apellidos`, `telefono`, `correo`,`direccion`) "
                             + "VALUES ('"+p.getCedula()+"','"+p.getNombres()+"','"+p.getApellidos()+"','"+p.getTelefono()+"','"
                             +p.getCorreo()+"')";

        try {
            stmt = con.getCn().prepareStatement(query);
            
            int rs2= stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CComprador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;


                     
        
        
        
    }
}
