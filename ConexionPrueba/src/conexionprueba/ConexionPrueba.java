/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionprueba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author adan
 */
public class ConexionPrueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    public static void connect(){
String url = "jdbc:mysql://localhost:3306/myjavaapp1";
String user = "root";
String pass = "";
System.out.println("Conectando...");
try(Connection connection = DriverManager.getConnection(url, user,pass)){
System.out.println("Conectado!!");
 
}catch(SQLException e){
System.out.println(e.getMessage());
}
}
}
