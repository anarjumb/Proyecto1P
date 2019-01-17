/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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
public class Inicio {
    
    private BorderPane root;
    private Button inicio, salir;
    private HBox BoxUser, BoxPass;
    private TextField usuario, clave;
    private Label lbluser, lblpass;


    private VBox box;

    Inicio(){        

        organizarpanel();

    }

    public void organizarpanel(){
            root = new BorderPane();
            box = new VBox();
            BoxUser = new HBox();
            BoxPass = new HBox();


            inicio = new Button("iniciar sesion");
            salir = new Button("salir");
            
            usuario = new TextField();
            clave = new TextField();
            
            lbluser = new Label("Ingrese usuario: ");
            lblpass = new Label("Ingrese clave: ");
            
            lbluser.setTextFill(Color.web("#0076a3"));
            lblpass.setTextFill(Color.web("#0076a3"));
            
            usuario.setPromptText("User..");
            clave.setPromptText("Password..");
            
            lbluser.setFont(Font.font("Cambria", 25));
            lblpass.setFont(Font.font("Cambria", 25));
            

           


           /* inicio.setStyle("-fx-background-color: transparent; ");
            ImageView image = new ImageView("/imagenes/jugargoku.png");
            image.setFitWidth(260);
            image.setFitHeight(150);
            inicio.setGraphic(image);*/

            inicio.setOnMouseEntered((MouseEvent e) -> {
                inicio.setScaleX(1.1);
                inicio.setScaleY(1.1);
            });

            inicio.setOnMouseExited((MouseEvent e) -> {
                inicio.setScaleX(1);
                inicio.setScaleY(1);
            });

           inicio.setOnAction(e -> PoliVentas.cambiarVentana(root, new PantallaBusqueda().getRoot()));
            
            salir.setOnAction(e ->Platform.exit());
            
            
            BoxUser.getChildren().addAll(lbluser,usuario);
            
            BoxPass.getChildren().addAll(lblpass,clave);
            
            BoxUser.setAlignment(Pos.CENTER);
            BoxPass.setAlignment(Pos.CENTER);



            box.getChildren().addAll(BoxUser,BoxPass,inicio,salir);



            root.setStyle("-fx-background-image: url('/imagenes/escenario.jpg'); "
                    + "-fx-background-position: center center; "
                    + "-fx-background-repeat: stretch;"
                    + "-fx-background-size:" + Constantes.ANCHO + " " + Constantes.ALTO + ";");

            box.setAlignment(Pos.CENTER);
            box.setSpacing(50);
            root.setCenter(box);               
        }



        BorderPane getRoot() {
            return root;
        }


    }

