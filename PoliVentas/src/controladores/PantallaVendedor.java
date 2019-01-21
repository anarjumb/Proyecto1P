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
public class PantallaVendedor {
    
       
    private BorderPane root;
    private Button buscar, compend,arttop,salir,venPen,misProd;
    private HBox BoxBusqueda, BoxButton;
    //private TextField busqueda;
   // private Label lblbuscar;
    private VBox box;

    PantallaVendedor(){        

        organizarpanel();

    }

    public void organizarpanel(){
            root = new BorderPane();
            box = new VBox();
            BoxBusqueda = new HBox();
            BoxButton = new HBox();


            buscar = new Button("Buscar");
            arttop = new Button("Articulos Top");
            compend = new Button("Compras");
            salir = new Button("Cerrar Sesión");
            venPen = new Button("Ventas Pendientes");
            misProd = new Button("Mis Productos");
            
            //busqueda = new TextField();
            
            
            /*lblbuscar = new Label("Ingrese búsqueda: ");
            lblbuscar.setTextFill(Color.web("#59FF33"));
            lblbuscar.setFont(Font.font("Cambria", 27));*/
            
            
            
            
           
            
            
            
            
            
            
            
            DarEfectoBoton(misProd);
            misProd.setOnAction(e -> PoliVentas.cambiarVentana(root, new PantallaMisProductos().getRoot()));
            
            
            
            DarEfectoBoton(arttop);
            //
            
            DarEfectoBoton(compend);
            //
            
            
            DarEfectoBoton(venPen);
            venPen.setOnAction(e -> PoliVentas.cambiarVentana(root, new PantallaVentas().getRoot()));
            //
            
           
            DarEfectoBoton(buscar);
            buscar.setOnAction(e -> PoliVentas.cambiarVentana(root, new PantallaBusqueda().getRoot()));

   
        
        
            DarEfectoBoton(salir);
            salir.setOnAction(e -> PoliVentas.cambiarVentana(root, new Inicio().getRoot()));
            
            
          //  BoxBusqueda.getChildren().addAll(lblbuscar,busqueda,buscar);
            BoxBusqueda.setSpacing(50);
            
            BoxButton.getChildren().addAll(buscar,compend,arttop, venPen,misProd);
            BoxButton.setSpacing(50);
            
            BoxBusqueda.setAlignment(Pos.CENTER);
            BoxButton.setAlignment(Pos.CENTER);



            box.getChildren().addAll(BoxBusqueda,BoxButton,salir);



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
