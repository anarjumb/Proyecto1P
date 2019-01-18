/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
    private Label lblrol;
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
            
            
            
            lblrol = new Label("Escoja rol:");


            registrar = new Button("Registrar");
            salir = new Button("Salir");
            
            usuario = new TextField();
            clave = new TextField();
            nombre = new TextField();
            apellido = new TextField();
            telefono = new TextField();
            email = new TextField();
            direccion = new TextField();
            cedula = new TextField();
            matricula = new TextField();
            
            
            rol.getItems().addAll("Comprador","Vendedor");
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
           registrar.setOnAction(e -> PoliVentas.cambiarVentana(root, new PantallaVendedor().getRoot()));
           
           
           
            DarEfectoBoton(salir);
            salir.setOnAction(e ->Platform.exit());
            
            
            


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
