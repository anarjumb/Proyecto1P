/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 *
 * @author adan
 */
public class Registro {
    
      
    private BorderPane root;
   
    

    Registro(){        

        organizarpanel();

    }

    public void organizarpanel(){
        
             ComboBox rol;
     VBox box;
     VBox n1;
     VBox n2;
             Button registrar;
            Button salir;
            HBox datos;
            TextField nombre;
            TextField apellido;
            TextField telefono;
            TextField email;
            TextField direccion;
            TextField cedula;
            TextField matricula;
            TextField clave;
            TextField usuario;
            root = new BorderPane();
            box = new VBox();
            datos = new HBox();
            n1 = new VBox();
            n2 = new VBox();
            rol = new ComboBox();
            Conexion con=new Conexion();
            
            
            


            registrar = new Button("Registrar");
            salir = new Button("Regresar");
            
            usuario = new TextField();
            clave = new TextField();
            nombre = new TextField();
            apellido = new TextField();
            telefono = new TextField();
            email = new TextField();
            direccion = new TextField();
            cedula = new TextField();
            matricula = new TextField();
            rol.setPromptText("Ingrese rol");
            
            
            rol.getItems().addAll("comprador","vendedor");
            usuario.setPromptText("Ingrese usuario");
            clave.setPromptText("Ingrese clave");
            nombre.setPromptText("Ingrese nombre");
            apellido.setPromptText("Ingrese apellido");
            telefono.setPromptText("Ingrese telefono");
            email.setPromptText("Ingrese email");
            direccion.setPromptText("Ingrese direccion");
            cedula.setPromptText("Ingrese cedula");
            matricula.setPromptText("Ingrese matricula");
            
            
            n1.getChildren().addAll(usuario,nombre,telefono,email,cedula);
            n2.getChildren().addAll(clave,apellido,matricula,direccion,rol);
            
            n1.setAlignment(Pos.CENTER);
            n2.setAlignment(Pos.CENTER);
            
            n1.setSpacing(25);
            n2.setSpacing(25);
            
            datos.getChildren().addAll(n1,n2);
            datos.setAlignment(Pos.CENTER);
            datos.setSpacing(35);
            
            box.getChildren().addAll(datos,registrar,salir);
            
            box.setAlignment(Pos.CENTER);
            box.setSpacing(35);

            darEfectoBoton(registrar);
           registrar.setOnAction(e -> {
            if(usuario.getText().isEmpty()||nombre.getText().isEmpty()||telefono.getText().isEmpty()||email.getText().isEmpty()
                    ||direccion.getText().isEmpty()||cedula.getText().isEmpty()||matricula.getText().isEmpty()||
                    clave.getText().isEmpty()||(rol.getValue()==null)){
                 Alert alert = new Alert(Alert.AlertType.INFORMATION, "Advertencia, llene todos los campos.", ButtonType.OK);
                 alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                 alert.show();          
            }else{

                if(validarDatos(usuario.getText())){
                 try {                   
                     con.connect();

                     PreparedStatement stmt;
                     String query="INSERT INTO `usuario` (`usuario`, `contrasenia`, `tipo`)  "
                             + "VALUES (\""+usuario.getText()+"\",\""+clave.getText()+"\",\""+rol.getValue().toString()+"\")";

                     stmt = con.getCn().prepareStatement(query);


                     int rs= stmt.executeUpdate();

                     query="INSERT INTO `"+rol.getValue().toString()+"` (`cedula`, `nombres`, `apellidos`, `telefono`, `correo`,`direccion`,`matricula`, `usuario`) "
                             + "VALUES ('"+cedula.getText()+"','"+nombre.getText()+"','"+apellido.getText()+"','"+telefono.getText()+"','"
                             +email.getText()+"','" +direccion.getText()+"','"+matricula.getText()+"','" +usuario.getText()+"')";

                     stmt = con.getCn().prepareStatement(query);


                     int rs2= stmt.executeUpdate();

                     if(rol.getValue()=="vendedor"){
                         PoliVentas.cambiarVentana(root, new PantallaVendedor().getRoot());

                     }
                     else if(rol.getValue()=="comprador"){
                         PoliVentas.cambiarVentana(root, new PantallaComprador().getRoot());

                     }
                 } catch (SQLException ex) {
                     Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
                     try {
                         Statement stmt = con.getCn().createStatement();
                         stmt.executeUpdate("delete from usuario where usuario ='"+usuario.getText()+"';");
                     } catch (SQLException ex1) {
                         Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex1);
                     }
                 } 
             }
             else{

                 Alert alert = new Alert(Alert.AlertType.INFORMATION, "Advertencia, el usuario ya esta registrado anteriormente.", ButtonType.OK);
                 alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                 alert.show();

             }
                         }
            });

           
           
            darEfectoBoton(salir);
            salir.setOnAction(e->PoliVentas.cambiarVentana(root, new Inicio().getRoot()));
            
            
            


            root.setStyle("-fx-background-image: url('/imagenes/registro.jpg'); "
                    + "-fx-background-position: center center; "
                    + "-fx-background-repeat: stretch;"
                    + "-fx-background-size:" + Constantes.ANCHO + " " + Constantes.ALTO + ";");

            
            root.setCenter(box);               
        }



        BorderPane getRoot() {
            return root;
        }
        
        
        public void darEfectoBoton(Button boton){
            boton.setStyle("-fx-font: 18 arial; -fx-base: #b6e7c9;");
        

            boton.setOnMouseEntered((MouseEvent e) -> {
                boton.setScaleX(1.1);
                boton.setScaleY(1.1);
            });

            boton.setOnMouseExited((MouseEvent e) -> {
                boton.setScaleX(1);
                boton.setScaleY(1);
            });
        }
    
        
        public static boolean validarDatos(String usuario){
            ResultSet rs2 = null;
        try {
                    Conexion con=new Conexion();

                    con.connect();           

                    PreparedStatement stmt2;


                    stmt2 = con.getCn().prepareStatement("SELECT usuario FROM usuario where usuario=?");
                    stmt2.setString(1, usuario);
                    
                    rs2= stmt2.executeQuery();
                    return !rs2.next();


            } catch (SQLException ex) {
                Logger.getLogger(PoliVentas.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }finally{
            if(rs2 != null){
                try {
                    rs2.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        }
}
