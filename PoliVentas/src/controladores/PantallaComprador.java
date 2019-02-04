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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author adan
 */
public final class PantallaComprador {
    
    private BorderPane root;
    

    PantallaComprador(){        

        organizarpanel();

    }
    

    public void organizarpanel(){
            root = new BorderPane();
             Button buscar;
             Button compend;
             Button arttop;
             Button salir;
             HBox boxBusqueda;
             HBox boxButton;
             VBox box;
            box = new VBox();
            boxBusqueda = new HBox();
            boxButton = new HBox();


            buscar = new Button("Buscar");
            arttop = new Button("Articulos Top");
            compend = new Button("Compras");
            salir = new Button("Cerrar Sesión");
            
            darEfectoBoton(arttop);
            
            darEfectoBoton(compend);
            
            darEfectoBoton(buscar);           
            buscar.setOnAction(e -> PoliVentas.cambiarVentana(root, new PantallaBusqueda().getRoot()));
            darEfectoBoton(salir);
            salir.setOnAction(e -> PoliVentas.cambiarVentana(root, new Inicio().getRoot()));
            boxButton.getChildren().addAll(buscar,compend,arttop);
            boxButton.setSpacing(50);
            
            boxBusqueda.setAlignment(Pos.CENTER);
            boxButton.setAlignment(Pos.CENTER);



            box.getChildren().addAll(boxButton,salir);



            root.setStyle("-fx-background-image: url('/imagenes/comprador.jpg'); "
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
