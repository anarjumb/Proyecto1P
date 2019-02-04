/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adan
 */
public class Conexion {
    private Connection cn;
    
    
    
    public void connect(){
        String url = "jdbc:mysql://localhost:3306/proyecto_tienda";
        //para los demas que no sean adan
        String user = "root";
        String pass = "";
        //especialmente para adan
//        String user = "user1";
//        String pass = "user1";
        
        System.out.println("Conectando...");
        try  {
            
            cn = DriverManager.getConnection(url, user, pass);
            System.out.println("Conectado!!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getCn() {
        return cn;
    }
    
    public void desconectar(){
        try {
            if(cn != null){
            cn.close();}
            System.out.println("Conexion finalizada");
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
