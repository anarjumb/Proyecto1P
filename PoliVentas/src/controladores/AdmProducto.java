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
import javafx.application.Platform;
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
import modelo.Persona;

/**
 *
 * @author adan
 */
public class AdmProducto {
    
    private BorderPane root;
    private TableView tabla;
    private VBox box,agregar;
    private Button add,delete,edit, atras,add1;
    private HBox botones;
    private TextField nombre,categoria,precio;
    
    public AdmProducto(){
        OrganizarPanel();
    }
    
    
    
     public void PanelAdd(){
        
        add1 = new Button("Agregar");
        DarEfectoBoton(add1);
        agregar.getChildren().clear();
        box.getChildren().removeAll(agregar);
        
        
        
        
        HBox n1 = new HBox();
        HBox n2 = new HBox();
        
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
    
    public void PanelEdit(){
        
        
        
        
        add1 = new Button("Edit");
        DarEfectoBoton(add1);
        
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
    public void OrganizarPanel(){
        
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
        
        DarEfectoBoton(add);
        DarEfectoBoton(delete);
        DarEfectoBoton(edit);
        DarEfectoBoton(atras);
        
        
        atras.setOnAction(e -> PoliVentas.cambiarVentana(root, new PantallaAdministrador().getRoot()));
        
        add.setOnAction(e -> PanelAdd());
        edit.setOnAction(e -> PanelEdit());
        
        TableColumn nombre = new TableColumn("Nombre");
        TableColumn categoria = new TableColumn("Categoria");
        TableColumn precio = new TableColumn("Precio");
        //TableColumn cantidad = new TableColumn("Cantidad");
        
        
        
        
        nombre.setMinWidth(100);
        
        
        
       
        
       // tabla.setItems(data);
        
        
        tabla.getColumns().addAll(nombre,categoria,precio);
        
        try{
                    Conexion con=new Conexion();

                    con.connect();           

                    PreparedStatement stmt2;
                    
                    ArrayList<Producto> productos=new ArrayList();
                    stmt2 = con.getCn().prepareStatement("SELECT nombre_producto,descripcion,precio FROM producto");
                   // stmt2.setString(1, usuario.getText());
                    //stmt2.setString(2, clave.getText());
                    ResultSet rs2= stmt2.executeQuery();
                    
                  
                    while(rs2.next()){
                            productos.add(new Producto(rs2.getString("nombre_producto"), rs2.getString("descripcion"),rs2.getFloat("precio")));
                                  
                    }
                    
                    
                    
                  
                    
                    
                    
                    final ObservableList<Producto> data = FXCollections.observableArrayList(productos); 
                     
                     
                     
                     
                     
                    tabla.setEditable(true);
                    tabla.setVisible(true);


                            //TableColumn nombre = new TableColumn("First Name");
                    nombre.setMinWidth(100);
                    nombre.setCellValueFactory(
                            new PropertyValueFactory<Producto, String>("nombre"));

                    //TableColumn apellido = new TableColumn("Last Name");
                    categoria.setMinWidth(100);
                    categoria.setCellValueFactory(
                            new PropertyValueFactory<Producto, String>("categoria"));

                    //TableColumn emailCol = new TableColumn("Email");
                    precio.setMinWidth(200);
                    precio.setCellValueFactory(
                            new PropertyValueFactory<Producto, Float>("precio"));

                    




                    tabla.setItems(data);


                // TODO code application logic here
            } catch (SQLException ex) {
                Logger.getLogger(PoliVentas.class.getName()).log(Level.SEVERE, null, ex);
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
