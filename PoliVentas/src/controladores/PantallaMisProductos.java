/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author RBLOO
 */
public class PantallaMisProductos {
    
    private GridPane root;
    private Button btn_buscar;
    private Button btn_nuevoProducto;
    private Button btn_regresar;
    private TextField tf_detalles;
    private TextField tf_buscarProducto;
    private HBox panelCentral;
    
    public PantallaMisProductos(){
        iniciarComponentes();
    }        
    
    public void iniciarComponentes(){
        root = new GridPane();
        btn_buscar = new Button("Buscar");
        btn_nuevoProducto = new Button("Nuevo Producto");
        btn_regresar = new Button("Regresar");
        panelCentral = new HBox();
        tf_buscarProducto = new TextField();
        tf_detalles = new TextField();
        
        panelCentral.getChildren().addAll(tf_buscarProducto,btn_buscar);
        
        root.getChildren().add(btn_regresar);
        
        
    }
}
