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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.Persona;

/**
 *
 * @author Angel Arturo
 */
public class PatallaCompras {
    
     private BorderPane root;
     private TableView tabla;
    
    
    private String usuario;

    public PatallaCompras(String usuario) {
        this.usuario = usuario;
        organizarpanel();
    }
     
     public void organizarpanel(){
          VBox box;
         Button add;
         Button delete;
         Button edit;
         Button atras;
         HBox botones;
         root = new BorderPane();
        tabla = new TableView();
        box = new VBox();
        botones = new HBox();
        tabla.setEditable(true);

        add = new Button("Agregar Usuario");
        delete = new Button("Eliminar Usuario");
        edit = new Button("Editar Usuario");
        atras = new Button("Atrás");
        
        
        darEfectoBoton(atras);
        
        atras.setOnAction(e -> PoliVentas.cambiarVentana(root, new PantallaAdministrador().getRoot()));
        
        cargarDatos();
        
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
     
     
     
     public void cargarDatos(){
        ResultSet rs2 = null;
        try {

            TableColumn cedula = new TableColumn("Cedula");
            TableColumn nombre = new TableColumn("Nombre");
            TableColumn apellido = new TableColumn("Apellido");
            TableColumn correo = new TableColumn("Correo");
            TableColumn telefono = new TableColumn("Telefono");
            TableColumn usuario1 = new TableColumn("Usuario");
            TableColumn contrasenia = new TableColumn("Clave");
            TableColumn rol = new TableColumn("Rol");


            tabla.getColumns().addAll(cedula,usuario1,contrasenia,nombre,apellido,telefono,correo,rol);
            Conexion con=new Conexion();

            con.connect();           

            PreparedStatement stmt2;

            ArrayList<Persona> personas=new ArrayList();
            stmt2 = con.getCn().prepareStatement("SELECT * FROM usuario u, comprador c where u.usuario=c.usuario");
            rs2= stmt2.executeQuery();


            while(rs2.next()){
                    personas.add(new Persona(rs2.getString("cedula"), rs2.getString("nombres"), rs2.getString("apellidos"), rs2.getString("telefono"),rs2.getString("correo"), rs2.getString("u.usuario"), rs2.getString("contrasenia"), rs2.getString("u.tipo")));

            }
            
            if(rs2 !=null){
                try {
                    rs2.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PantallaMisProductos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            stmt2 = con.getCn().prepareStatement("SELECT * FROM usuario u, vendedor c where u.usuario=c.usuario");
            rs2= stmt2.executeQuery();


            while(rs2.next()){
                    personas.add(new Persona(rs2.getString("cedula"), rs2.getString("nombres"), rs2.getString("apellidos"), rs2.getString("telefono"),rs2.getString("correo"), rs2.getString("u.usuario"), rs2.getString("contrasenia"), rs2.getString("u.tipo")));

            }
            
            if(rs2 !=null){
                try {
                    rs2.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PantallaMisProductos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            stmt2 = con.getCn().prepareStatement("SELECT * FROM usuario u, administrador c where u.usuario=c.usuario");
            rs2= stmt2.executeQuery();


            while(rs2.next()){
                    personas.add(new Persona(rs2.getString("cedula"), rs2.getString("nombres"), rs2.getString("apellidos"), rs2.getString("telefono"),rs2.getString("correo"), rs2.getString("u.usuario"), rs2.getString("contrasenia"), rs2.getString("tipo")));

            }



            final ObservableList<Persona> data = FXCollections.observableArrayList(personas); 



            tabla.setEditable(true);
            tabla.setVisible(true);
            



            nombre.setMinWidth(100);
            nombre.setCellValueFactory(new PropertyValueFactory<>("nombres"));


            apellido.setMinWidth(100);
            apellido.setCellValueFactory(new PropertyValueFactory<>("apellidos"));



            correo.setMinWidth(200);
            correo.setCellValueFactory(new PropertyValueFactory<>("correo"));


            cedula.setMinWidth(200);
            cedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));


            usuario1.setMinWidth(200);
            usuario1.setCellValueFactory(
                    new PropertyValueFactory<>("usuario"));


            telefono.setMinWidth(200);
            telefono.setCellValueFactory(
                    new PropertyValueFactory<>("telefono"));

            contrasenia.setMinWidth(200);
            contrasenia.setCellValueFactory(
                    new PropertyValueFactory<>("contrasenia"));

            rol.setMinWidth(200);
            rol.setCellValueFactory(
                    new PropertyValueFactory<>("rol"));




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

     
     
     
     
     BorderPane getRoot() {
            return root;
        }
    
}
