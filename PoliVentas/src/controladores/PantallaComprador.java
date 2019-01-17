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

/**
 *
 * @author adan
 */
public class PantallaComprador {
    
       
    private BorderPane root;
    private Button buscar, compend,arttop,salir,venPen,misProd;
    private HBox BoxBusqueda, BoxButton;
    private TextField busqueda;
    private Label lblbuscar;
    private VBox box;

    PantallaComprador(){        

        organizarpanel();

    }

    public void organizarpanel(){
            root = new BorderPane();
            box = new VBox();
            BoxBusqueda = new HBox();
            BoxButton = new HBox();


            buscar = new Button("buscar");
            arttop = new Button("articulos top");
            compend = new Button("compras");
            salir = new Button("salir");
            venPen = new Button("ventas pendientes");
            misProd = new Button("mis productos--");
            
            busqueda = new TextField();
            
            
            lblbuscar = new Label("Ingrese búsqueda: ");
            
            
           
            misProd.setOnAction(e -> PoliVentas.cambiarVentana(root, new Registro().getRoot()));
            

           buscar.setOnAction(e -> PoliVentas.cambiarVentana(root, new PantallaAdministrador().getRoot()));


            

            buscar.setOnMouseEntered((MouseEvent e) -> {
                buscar.setScaleX(1.1);
                buscar.setScaleY(1.1);
            });

            buscar.setOnMouseExited((MouseEvent e) -> {
                buscar.setScaleX(1);
                buscar.setScaleY(1);
            });

        //    inicio.setOnAction(e -> PoliVentas.cambiarVentana(root, new PantallaVendedor().getRoot()));
            
            salir.setOnAction(e -> PoliVentas.cambiarVentana(root, new Inicio().getRoot()));
            
            
            BoxBusqueda.getChildren().addAll(lblbuscar,busqueda,buscar);
            BoxBusqueda.setSpacing(50);
            
            BoxButton.getChildren().addAll(compend,arttop, venPen,misProd);
            BoxButton.setSpacing(50);
            
            BoxBusqueda.setAlignment(Pos.CENTER);
            BoxButton.setAlignment(Pos.CENTER);



            box.getChildren().addAll(BoxBusqueda,BoxButton,salir);



            root.setStyle("-fx-background-image: url('/imagenes/iniciojuego.jpg'); "
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
