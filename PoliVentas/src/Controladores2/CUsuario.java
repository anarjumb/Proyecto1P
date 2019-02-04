/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mi compu
 */
public class CUsuario {
    
    controladores.Conexion con = new controladores.Conexion();
    
    
    public String VerificarUsuario(String usuario, String contraseña){
        
        con.connect();           

        PreparedStatement stmt2;
        String rol = null;


        try {
            stmt2 = con.getCn().prepareStatement("SELECT * FROM usuario where usuario=?");
            stmt2.setString(1, usuario);
            ResultSet rs2= stmt2.executeQuery();
            if(!rs2.next()){
                return null;
            }
            
                rs2.previous();
                        while(rs2.next()){
                            if(rs2.getString("contrasenia").equals(contraseña)){
                                rol= rs2.getString("tipo");
                                break;
                                
                            }
                        }
                
                
                       
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rol;
        
    } 
    
    public boolean AgregarUsuario(String usuario, String clave, String rol){
        
        con.connect();

        PreparedStatement stmt;
        String query="INSERT INTO `usuario` (`usuario`, `contrasenia`, `tipo`)  "
              + "VALUES (\""+usuario+"\",\""+clave+"\",\""+rol+"\")";
        
        try {
            stmt = con.getCn().prepareStatement(query);
            stmt.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        return true;
        
        
    }
    
    
}
