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
public final class PantallaVendedor {
    
       
    private BorderPane root;
    

    PantallaVendedor(){        

        organizarpanel();

    }

    public void organizarpanel(){
         Button buscar;
         Button compend;
         Button arttop;
         Button salir;
         Button venPen;
         Button misProd;
         HBox boxBusqueda;
         HBox boxButton;
         VBox box;
    
            root = new BorderPane();
            box = new VBox();
            boxBusqueda = new HBox();
            boxButton = new HBox();


            buscar = new Button("Buscar");
            arttop = new Button("Articulos Top");
            compend = new Button("Compras");
            salir = new Button("Cerrar Sesión");
            venPen = new Button("Ventas Pendientes");
            misProd = new Button("Mis Productos");
            
            darEfectoBoton(misProd);
            misProd.setOnAction(e -> PoliVentas.cambiarVentana(root, new PantallaMisProductos().getRoot()));
            
            
            
            darEfectoBoton(arttop);
            
            darEfectoBoton(compend);
            
            
            darEfectoBoton(venPen);
            venPen.setOnAction(e -> PoliVentas.cambiarVentana(root, new PantallaVentas().getRoot()));
            
           
            darEfectoBoton(buscar);
            buscar.setOnAction(e -> PoliVentas.cambiarVentana(root, new PantallaBusqueda().getRoot()));

   
        
        
            darEfectoBoton(salir);
            salir.setOnAction(e -> PoliVentas.cambiarVentana(root, new Inicio().getRoot()));
            boxBusqueda.setSpacing(50);
            
            boxButton.getChildren().addAll(buscar,compend,arttop, venPen,misProd);
            boxButton.setSpacing(50);
            
            boxBusqueda.setAlignment(Pos.CENTER);
            boxButton.setAlignment(Pos.CENTER);



            box.getChildren().addAll(boxBusqueda,boxButton,salir);



            root.setStyle("-fx-background-image: url('/imagenes/vendedor.jpg'); "
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
