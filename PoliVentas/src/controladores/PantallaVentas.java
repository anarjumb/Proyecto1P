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
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
/**
 *
 * @author Mi compu
 */
public final class PantallaVentas {
    
    private TableView tabla;
    private BorderPane root;
    
    
    
   public PantallaVentas(){
       organizarPanel();
   }
   
   
   public void organizarPanel(){
       
       
    root = new BorderPane();
    VBox box = new VBox();
    tabla = new TableView();
    
    Button cerrar = new Button("Atrás");
    
    darEfectoBoton(cerrar);
    
    cerrar.setOnAction(e -> PoliVentas.cambiarVentana(root, new PantallaVendedor().getRoot()));
    
    cargarventas();
    box.getChildren().addAll(tabla,cerrar);
    box.setSpacing(35);
    box.setAlignment(Pos.CENTER);
    root.setCenter(box);
    
    root.setStyle("-fx-background-image: url('/imagenes/ventas.jpg'); "
                    + "-fx-background-position: center center; "
                    + "-fx-background-repeat: stretch;"
                    + "-fx-background-size:" + Constantes.ANCHO + " " + Constantes.ALTO + ";");
    
       
   }
    
    
    
    void cargarventas(){
        
        TableColumn comprador = new TableColumn("Comprador");
        TableColumn producto = new TableColumn("Producto");
        TableColumn cantidad = new TableColumn("Cantidad");
        TableColumn precio = new TableColumn("Precio total");
       
        tabla.getColumns().addAll(comprador,producto,cantidad,precio);
        
       
        ResultSet rs2 = null;
        
        try {
                    Conexion con=new Conexion();

                    con.connect();           

                    PreparedStatement stmt2;
                    
                    ArrayList<Ventas> ventas=new ArrayList<>();
                    stmt2 = con.getCn().prepareStatement("SELECT comprador, producto, cantidad,precio FROM venta where vendedor=?" );
                   stmt2.setString(1, Inicio.getUsuario());
                    rs2= stmt2.executeQuery();
                    
                  
                    while(rs2.next()){
                            ventas.add(new Ventas(rs2.getString("comprador"), rs2.getString("producto"), rs2.getInt("cantidad"), rs2.getFloat("precio")));
                                  
                    }
                    
                    
                    final ObservableList<Ventas> data = FXCollections.observableArrayList(ventas); 
                     
                     
                    tabla.setEditable(true);
                    tabla.setVisible(true);
                    
                    comprador.setMinWidth(100);
                    comprador.setCellValueFactory(
                            new PropertyValueFactory<>("comprador"));

                    
                    producto.setMinWidth(100);
                    producto.setCellValueFactory(
                            new PropertyValueFactory<>("producto"));
                    
                    cantidad.setMinWidth(100);
                    cantidad.setCellValueFactory(
                            new PropertyValueFactory<>("cantidad"));
                    
                    precio.setMinWidth(100);
                    precio.setCellValueFactory(
                            new PropertyValueFactory<>("precio"));

                    tabla.setItems(data);


               
            } catch (SQLException ex) {
                Logger.getLogger(PoliVentas.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
            if(rs2 !=null){
                try {
                    rs2.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PantallaMisProductos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
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
