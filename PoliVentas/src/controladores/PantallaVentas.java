/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import javafx.scene.control.*;
import javafx.scene.layout.*;
/**
 *
 * @author Mi compu
 */
public class PantallaVentas {
    VBox root = new VBox();
    GridPane top = new GridPane();
    VBox center = new VBox();
    
    Button cerrar = new Button("Cerrar Secion");
    Label titulo= new Label("Bienvenido Vendedor");
    
    
    
    void cargarventas(){
        top.add(center, 0, 2);
        top.add(titulo, 0, 1);
        
        root.getChildren().add(top);
        
        //pedir informacion a la base
        
        
        //Mostar los datos
        
//        String [] ventas = new String[3];
//        for(int x = 0;x < ventas.length; x++){
//            
//            Label fila = new Label(ventas[x]);
//            center.getChildren().add(fila);
//            
//            
//        }
//        
        
        
        
        
        
        
        
        
    }
    
    
    
    
    
    
    Pane getroot(){
        
        this.cargarventas();
        
        return root;
        
        
    }
    
    
}
