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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author RBLOO
 */
public class PantallaBusqueda {
    
    private BorderPane root;
    private Button btn_buscar;
    private Button btn_salir;
    private Label lbl_elemento;
    private TextField tf_buscar;
    private HBox panelSuperior;
    
    
    public PantallaBusqueda(){
        iniciarComponentes();
    }
    
    public void iniciarComponentes(){
        root = new BorderPane();
        btn_buscar = new Button("Buscar");
        btn_salir = new Button("Salir");
        lbl_elemento = new Label("Ingrese elementoa a buscar");
        tf_buscar = new TextField();
        panelSuperior = new HBox();
        
        panelSuperior.getChildren().addAll(lbl_elemento,tf_buscar, btn_buscar);
        panelSuperior.setSpacing(50);
        
        root.setStyle("-fx-background-image: url('/imagenes/iniciojuego.jpg'); "
                    + "-fx-background-position: center center; "
                    + "-fx-background-repeat: stretch;"
                    + "-fx-background-size:" + Constantes.ANCHO + " " + Constantes.ALTO + ";");

        panelSuperior.setAlignment(Pos.CENTER);
        btn_salir.setOnAction(e -> PoliVentas.cambiarVentana(root, new Inicio().getRoot()));
        root.setCenter(panelSuperior);
        root.setBottom(btn_salir);
        
    }
    
    public BorderPane getRoot(){
        return root;
    }
}
