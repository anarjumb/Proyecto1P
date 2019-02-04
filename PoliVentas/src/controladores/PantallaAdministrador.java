/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author adan
 */
public class PantallaAdministrador {
    
    private BorderPane root;
    
    

    PantallaAdministrador(){        

        organizarpanel();

    }

    public void organizarpanel(){
            VBox box;
            root = new BorderPane();
            box = new VBox();
             Button admUser;
             Button admProd;
             Button salir;


            admUser = new Button("Administrar Usuarios");
            admProd = new Button("Adminsitrar Productos");
            salir = new Button("Cerrar Sesión");
           
           
            darEfectoBoton(admUser);
            admUser.setOnAction(e -> PoliVentas.cambiarVentana(root, new AdmUsuarios().getRoot()));
            
            darEfectoBoton(admProd);
            admProd.setOnAction(e -> PoliVentas.cambiarVentana(root, new AdmProducto().getRoot()));
        
            darEfectoBoton(salir);
            
            salir.setOnAction(e -> PoliVentas.cambiarVentana(root, new Inicio().getRoot()));
            
            
            box.getChildren().addAll(admUser,admProd,salir);
            box.setSpacing(50);
            
            box.setAlignment(Pos.CENTER);
            

            root.setStyle("-fx-background-image: url('/imagenes/admin.jpg'); "
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

    
    
}
