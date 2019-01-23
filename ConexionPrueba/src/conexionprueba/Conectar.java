/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionprueba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adan
 */
public class Conectar {
    
    private static Connection connect;
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String usuario = "root";
    private static final String password = "";
    private static final String url = "jdbc:mysql://localhost:3306/proyecto_tienda";
    
    public Conectar(){
        connect = null;
        try{
            Class.forName(driver);
            connect = DriverManager.getConnection(url, usuario, password);
            if(connect != null){
                System.out.println("Conexion establecida");
            }
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error al conectar" + e);
        }
    }
    //Retorna la conexion
    public Connection getConnection(){
        return connect;
    }
    //Desconectamos la base de datos
    public void desconectar(){
        try {
            connect.close();
            System.out.println("Conexion finalizada");
        } catch (SQLException ex) {
            Logger.getLogger(Conectar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
