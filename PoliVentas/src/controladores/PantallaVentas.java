/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
/**
 *
 * @author Mi compu
 */
public class PantallaVentas {
    VBox root = new VBox();
    GridPane top = new GridPane();
    VBox center = new VBox();
    TableView tabla;
    
    Button cerrar = new Button("Cerrar Secion");
    Label titulo= new Label("Bienvenido Vendedor");
    
    
    
    void cargarventas(){
        top.add(center, 0, 2);
        top.add(titulo, 0, 1);
        
        root.getChildren().add(top);
        
        //pedir informacion a la base
        
        tabla = new TableView();
        TableColumn nombre = new TableColumn("Nombre");
        TableColumn categoria = new TableColumn("Categoria");
        TableColumn precio = new TableColumn("Precio");
        TableColumn direccion = new TableColumn("Direccion");
        TableColumn comprador = new TableColumn("Comprador");
        
        
        
        nombre.setMinWidth(100);
        
        
        
        nombre.setCellFactory(new PropertyValueFactory("hola"));
        
       // tabla.setItems(data);
        
        
        tabla.getColumns().addAll(nombre,categoria,precio);
        
        
        try {
                    Conexion con=new Conexion();

                    con.connect();           

                    PreparedStatement stmt2;
                    
                    ArrayList<Producto> productos=new ArrayList();
                    stmt2 = con.getCn().prepareStatement("SELECT * FROM ventas");
                   // stmt2.setString(1, usuario.getText());
                    //stmt2.setString(2, clave.getText());
                    ResultSet rs2= stmt2.executeQuery();
                    
                  
                    while(rs2.next()){
                            productos.add(new Producto(rs2.getString("nombre_producto"), rs2.getString("descripcion")));
                                  
                    }
                    
                    
                    
                  
                    
                    
                    
                    final ObservableList<Producto> data = FXCollections.observableArrayList(productos); 
                     
                     
                     
                     
                     
                    tabla.setEditable(true);
                    tabla.setVisible(true);


                            //TableColumn nombre = new TableColumn("First Name");
                    nombre.setMinWidth(100);
                    nombre.setCellValueFactory(
                            new PropertyValueFactory<Producto, String>("nombre"));

                    
                    categoria.setMinWidth(100);
                    categoria.setCellValueFactory(
                            new PropertyValueFactory<Producto, String>("categoria"));

               




                    tabla.setItems(data);


               
            } catch (SQLException ex) {
                Logger.getLogger(PoliVentas.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        
        
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
