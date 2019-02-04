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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author adan
 */
public class AdmProducto {
    
    private BorderPane root;
    private    VBox box;
    private    VBox agregar;
    private    Button add1;
    private    TextField nombre;
    private    TextField categoria; 
    private    TextField precio;
    
    public AdmProducto(){
        organizarPanel();
    }
    
    
    
     public void panelAdd(){
        
        
         
        add1 = new Button("Agregar");
        darEfectoBoton(add1);
        agregar.getChildren().clear();
        box.getChildren().removeAll(agregar);
        
        nombre = new TextField();
        categoria = new TextField();
        precio = new TextField();
        
        
        nombre.setPromptText("Ingrese nombre");
        categoria.setPromptText("Ingrese categoria");
        precio.setPromptText("Ingrese precio");
        
        
        
        
        HBox temp = new HBox();
        temp.getChildren().addAll(nombre,categoria,precio);
        temp.setSpacing(15);
        temp.setAlignment(Pos.CENTER);
        
        
        agregar.getChildren().addAll(temp,add1);
        agregar.setSpacing(15);
        agregar.setAlignment(Pos.CENTER);
        
        box.getChildren().add(agregar);
        add1.setOnAction(e -> box.getChildren().remove(agregar));
        
        
        
    }
    
    public void panelEdit(){
        
        
        
        
        add1 = new Button("Edit");
        darEfectoBoton(add1);
        
        nombre = new TextField();
        categoria = new TextField();
        precio = new TextField();
        
        
        
        nombre.setPromptText("Nombre");
        categoria.setPromptText("Editar Categoria");
        precio.setPromptText("Editar Precio");
       
        
        
        agregar.getChildren().clear();
        box.getChildren().remove(agregar);
       
        HBox temp = new HBox();
        temp.getChildren().addAll(nombre,categoria,precio);
        temp.setSpacing(15);
        temp.setAlignment(Pos.CENTER);
        
        
        agregar.getChildren().addAll(temp,add1);
        agregar.setSpacing(15);
        agregar.setAlignment(Pos.CENTER);
        
        box.getChildren().add(agregar);
        add1.setOnAction(e -> box.getChildren().remove(agregar));
        
    }
    public void organizarPanel(){
            Button delete;
        Button edit; 
        Button atras;
        HBox botones;
        Button add;
         TableView tabla;
        root = new BorderPane();
        tabla = new TableView();
        box = new VBox();
        agregar = new VBox();
        botones = new HBox();
        tabla.setEditable(true);
        
        add = new Button("Agregar Producto");
        delete = new Button("Eliminar Producto");
        edit = new Button("Editar Producto");
        atras = new Button("Atrás");
        
        darEfectoBoton(add);
        darEfectoBoton(delete);
        darEfectoBoton(edit);
        darEfectoBoton(atras);
       
        
        atras.setOnAction(e -> PoliVentas.cambiarVentana(root, new PantallaAdministrador().getRoot()));
        
        add.setOnAction(e -> panelAdd());
        edit.setOnAction(e -> panelEdit());
        
        TableColumn nombre = new TableColumn("Nombre");
        TableColumn categoria = new TableColumn("Categoria");
        TableColumn precio = new TableColumn("Precio");
        
        nombre.setMinWidth(100);
        
        tabla.getColumns().addAll(nombre,categoria,precio);
        Conexion con=new Conexion();
        PreparedStatement stmt2 = null;
        ResultSet rs2 = null;
        
        try{
                    

                    con.connect();           
                    ArrayList<Producto> productos=new ArrayList();
                    stmt2 = con.getCn().prepareStatement("SELECT id_producto,nombre_producto,descripcion,precio FROM producto");
          
                     rs2= stmt2.executeQuery();
                    
                  
                    while(rs2.next()){
                            productos.add(new Producto(rs2.getInt("id_producto"),rs2.getString("nombre_producto"), rs2.getString("descripcion"),rs2.getFloat("precio")));
                                  
                    }
                    
                    
                    
                    final ObservableList<Producto> data = FXCollections.observableArrayList(productos); 
                     
                     
                    tabla.setEditable(true);
                    tabla.setVisible(true);
                         
                    nombre.setMinWidth(100);
                    nombre.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombre"));


                    categoria.setMinWidth(100);
                    categoria.setCellValueFactory(new PropertyValueFactory<Producto, String>("categoria"));

                    precio.setMinWidth(200);
                    precio.setCellValueFactory(
                            new PropertyValueFactory<Producto, Float>("precio"));

                    tabla.setItems(data);

                    
            } catch (SQLException ex) {
                Logger.getLogger(PoliVentas.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
            if (con != null && rs2 !=null) {
                try {
                    con.getCn().close();
                    rs2.close();
                }catch (SQLException e) {
                    System.out.println("Error grave: no se puede cerrar el objeto conexión");
                }
            }
        }
            
            botones.getChildren().addAll(atras,add,delete,edit);
            botones.setSpacing(35);
            botones.setAlignment(Pos.CENTER);
            
            box.getChildren().addAll(tabla,botones);
            box.setAlignment(Pos.CENTER);
            box.setSpacing(35);
            
            
            
            root.setCenter(box);
            
            root.setStyle("-fx-background-image: url('/imagenes/edit.jpg'); "
                    + "-fx-background-position: center center; "
                    + "-fx-background-repeat: stretch;"
                    + "-fx-background-size:" + Constantes.ANCHO + " " + Constantes.ALTO + ";");
            
            
            }

    public BorderPane getRoot() {
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
