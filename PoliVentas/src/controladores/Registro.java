/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author adan
 */
public class Registro {
    
      
    private BorderPane root;
    private Button registrar, salir;
    private HBox datos;
    private TextField nombre,apellido,telefono,email,direccion,cedula,matricula,clave,usuario;
    
    private ComboBox rol;


    private VBox box, n1,n2;

    Registro(){        

        organizarpanel();

    }

    public void organizarpanel(){
            root = new BorderPane();
            box = new VBox();
            datos = new HBox();
            n1 = new VBox();
            n2 = new VBox();
            rol = new ComboBox();
            
            
            
            


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
            /*
            lbluser = new Label("Ingrese usuario: ");
            lblpass = new Label("Ingrese clave: ");
            
            lbluser.setTextFill(Color.web("#0076a3"));
            lblpass.setTextFill(Color.web("#0076a3"));*/
            
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
            

           


           /* inicio.setStyle("-fx-background-color: transparent; ");
            ImageView image = new ImageView("/imagenes/jugargoku.png");
            image.setFitWidth(260);
            image.setFitHeight(150);
            inicio.setGraphic(image);*/

            DarEfectoBoton(registrar);
           registrar.setOnAction(e -> {
               
               //nombre,apellido,telefono,email,direccion,cedula,matricula,clave,usuario;
           if(usuario.getText().isEmpty()||nombre.getText().isEmpty()||telefono.getText().isEmpty()||email.getText().isEmpty()
                   ||direccion.getText().isEmpty()||cedula.getText().isEmpty()||matricula.getText().isEmpty()||
                   clave.getText().isEmpty()||(rol.getValue()==null)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Advertencia, llene todos los campos.", ButtonType.OK);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.show();          
                //------------angel mira asi se obtiene el valor de un combo box System.out.println(rol.getValue()); si esta vacio bota null.--------
                
           }else{
               try {                   
                   
                   
                   Conexion con=new Conexion();          
                  
                   con.connect();
                                  
                   PreparedStatement stmt;
                   String query="INSERT INTO `usuario` (`usuario`, `contrasenia`, `tipo`)  "
                           + "VALUES (\""+usuario.getText()+"\",\""+clave.getText()+"\",\""+rol.getValue().toString()+"\")";
                   
                   System.out.println(query);
                   
                   
                   stmt = con.getCn().prepareStatement(query);
                   
                   
                   int rs= stmt.executeUpdate();
                   
                   query="INSERT INTO `"+rol.getValue().toString()+"` (`cedula`, `nombres`, `apellidos`, `correo`, `telefono`, `usuario`) "
                           + "VALUES (\""+cedula.getText()+"\",\""+nombre.getText()+"\",\""+apellido.getText()+"\",\""+email.getText()+"\",\""+telefono.getText()+"\",\""+usuario.getText()+"\")";
                   
                   stmt = con.getCn().prepareStatement(query);
                   
                   System.out.println("am");
                   int rs2= stmt.executeUpdate();
                   
                   if(rol.getValue()=="vendedor"){
                       PoliVentas.cambiarVentana(root, new PantallaVendedor().getRoot());
                       
                   }
                   else if(rol.getValue()=="comprador"){
                       PoliVentas.cambiarVentana(root, new PantallaComprador().getRoot());
                       
                   }
                   
                   
                   
               } catch (SQLException ex) {
                   Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
               }
           }});
           
           
           
            DarEfectoBoton(salir);
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
        
        
        public void DarEfectoBoton(Button boton){
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
    
}
