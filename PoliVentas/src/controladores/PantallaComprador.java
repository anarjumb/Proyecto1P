/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
public class PantallaComprador {
    
    private BorderPane root;
    private Button buscar, compend,arttop,salir;
    private HBox BoxBusqueda, BoxButton;
  //  private TextField busqueda;
   // private Label lblbuscar;
    private VBox box;

    PantallaComprador(){        

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
            
     //       busqueda = new TextField();
            
            
           /* lblbuscar = new Label("Ingrese búsqueda: ");
            lblbuscar.setFont(Font.font("Cambria", 27));
            lblbuscar.setTextFill(Color.web("#59FF33"));*/
            
            

           


       
            
            
            DarEfectoBoton(arttop);
            //
            
            DarEfectoBoton(compend);
            //

            
            
            DarEfectoBoton(buscar);           
             buscar.setOnAction(e -> PoliVentas.cambiarVentana(root, new PantallaBusqueda().getRoot()));

        //    inicio.setOnAction(e -> PoliVentas.cambiarVentana(root, new PantallaVendedor().getRoot()));
        
        
        
            DarEfectoBoton(salir);
            salir.setOnAction(e -> PoliVentas.cambiarVentana(root, new Inicio().getRoot()));
            
            
          /*  BoxBusqueda.getChildren().addAll(lblbuscar,busqueda,buscar);
            BoxBusqueda.setSpacing(50);*/
            
            BoxButton.getChildren().addAll(buscar,compend,arttop);
            BoxButton.setSpacing(50);
            
            BoxBusqueda.setAlignment(Pos.CENTER);
            BoxButton.setAlignment(Pos.CENTER);



            box.getChildren().addAll(BoxButton,salir);



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
