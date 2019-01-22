/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionprueba;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author adan
 */
public class ConexionPrueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Conectar conn = new Conectar();
        try {
            Statement st = (conn.getConnection()).createStatement();
            String usuarios[]= {"Juan"};
            String contrasenia[]= {"123"};
            String tipos[]= {"v"};

            st.executeUpdate( "CREATE TABLE contacto ("
            + "id INT AUTO_INCREMENT, "
            + "PRIMARY KEY(id), "
            + "nombre VARCHAR(20), "
            + "apellidos VARCHAR(20), "
            + "telefono VARCHAR(20))" );
            System.out.println("-Creada tabla (contacto) - Ok");

            // Insertar datos a la tabla
            String nombres[]={"Juan","Maria","Antonio"};
            String apellidos[]={"Sanchez","Jimenez","Moreno"};
            String telefonos[]={"918971234","918984621","935741254"};
            for (int i=0;i < nombres.length;i++) {
            st.executeUpdate("INSERT INTO contacto ("
            + "nombre, "
            + "apellidos, "
            + "telefono) "
            + "VALUES ("
            + "'"+nombres[i]+
            "','"+apellidos[i]+
            "','"+telefonos[i]+
            "' )");
            } 
            System.out.println("-Añadir registros a la tabla - Ok");

            // Consulta de datos
            System.out.println("-Consultar registros:");
            ResultSet rs = st.executeQuery ("select * from contacto");
            while (rs.next()) {
            System.out.println (
            rs.getString (1) + " " +
            rs.getString (2) + " " +
            rs.getString (3) + " " +
            rs.getString (4)
            );
            }

            // Borrar tabla
            st.executeUpdate("DROP TABLE contacto");
            System.out.println("-Borrar tabla contacto - Ok");

            conn.getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionPrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
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
