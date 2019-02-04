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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import modelo.Persona;

/**
 *
 * @author adan
 */
public final class AdmUsuarios {
    
    
    private BorderPane root;
    private    TextField usuario;
    private    TextField clave;
    private    TextField nombre;
    private    TextField apellido;
    private    TextField telefono;
    private    TextField email;
    private    TextField direccion;
    private    TextField matricula;
    private    TextField cedula;
    private    ComboBox rol;
    private Button add1;
    private VBox agregar;
    private VBox box;
    private TableView tabla;
 
    
    public AdmUsuarios(){
        organizarPanel();
        
    }
    
    
    public void panelAdd(){
        
        
        
        add1 = new Button("Agregar");
        darEfectoBoton(add1);
        agregar.getChildren().clear();
        box.getChildren().removeAll(agregar);
       
        
        
        
        
        HBox n1 = new HBox();
        HBox n2 = new HBox();
        
        usuario = new TextField();
        clave = new TextField();
        nombre = new TextField();
        apellido = new TextField();
        telefono = new TextField();
        email = new TextField();
        direccion = new TextField();
        cedula = new TextField();
        matricula = new TextField();
        rol = new ComboBox();
        
        rol.getItems().addAll(
            "administrador",
            "comprador",
            "vendedor"
        );
        
          
        
        
        usuario.setPromptText("Ingrese usuario");
        rol.setPromptText("Ingrese rol");
        clave.setPromptText("Ingrese clave");
        nombre.setPromptText("Ingrese nombre");
        apellido.setPromptText("Ingrese apellido");
        telefono.setPromptText("Ingrese telefono");
        email.setPromptText("Ingrese email");
        direccion.setPromptText("Ingrese direccion");
        cedula.setPromptText("Ingrese cedula");
        matricula.setPromptText("Ingrese matricula");
        
        n1.getChildren().addAll(usuario,clave,rol,nombre,apellido);
        n2.getChildren().addAll(telefono,email,direccion,cedula,matricula,add1);
        n1.setAlignment(Pos.CENTER);
        n2.setAlignment(Pos.CENTER);
        n1.setSpacing(15);
        n2.setSpacing(15);
        
        agregar.getChildren().addAll(n1,n2);
        agregar.setAlignment(Pos.CENTER);
        agregar.setSpacing(15);
        
        box.getChildren().add(agregar);
        add1.setOnAction(e -> {
               
            if(usuario.getText().isEmpty()||nombre.getText().isEmpty()||telefono.getText().isEmpty()||email.getText().isEmpty()
                    ||direccion.getText().isEmpty()||cedula.getText().isEmpty()||matricula.getText().isEmpty()||
                    clave.getText().isEmpty()||(rol.getValue()==null)){
                 Alert alert = new Alert(Alert.AlertType.INFORMATION, "Advertencia, llene todos los campos.", ButtonType.OK);
                 alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                 alert.show();          
            }else{

                if(validarDatos(usuario.getText())){
                 try {                   


                     Conexion con=new Conexion();          

                     con.connect();

                     PreparedStatement stmt;
                     
                     String query="INSERT INTO `usuario` (`usuario`, `contrasenia`, `tipo`)  "
                             + "VALUES (\""+usuario.getText()+"\",\""+clave.getText()+"\",\""+rol.getValue().toString()+"\")";




                     stmt = con.getCn().prepareStatement(query);

                     query="INSERT INTO `"+rol.getValue().toString()+"` (`cedula`, `nombres`, `apellidos`, `correo`, `telefono`, `usuario`) "
                             + "VALUES (\""+cedula.getText()+"\",\""+nombre.getText()+"\",\""+apellido.getText()+"\",\""+email.getText()+"\",\""+telefono.getText()+"\",\""+usuario.getText()+"\")";

                     stmt = con.getCn().prepareStatement(query);


                     int rs2= stmt.executeUpdate();

                     
                     
                     



                 } catch (SQLException ex) {
                     Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
                 box.getChildren().remove(agregar);
             }
             else{

                 Alert alert = new Alert(Alert.AlertType.INFORMATION, "Advertencia, el usuario ya esta registrado anteriormente.", ButtonType.OK);
                 alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                 alert.show();

             }
                         }
            });

        
        
        
    }
    
    
    
    public void panelDelete(){
        
        add1 = new Button("Eliminar");
        darEfectoBoton(add1);
        
        usuario = new TextField();
       
        
        
        usuario.setPromptText("Ingrese Usuario");
       
        
        
        agregar.getChildren().clear();
        box.getChildren().remove(agregar);
       
        HBox temp = new HBox();
        temp.getChildren().addAll(usuario);
        temp.setSpacing(15);
        temp.setAlignment(Pos.CENTER);
        
        
        agregar.getChildren().addAll(temp,add1);
        agregar.setSpacing(15);
        agregar.setAlignment(Pos.CENTER);
        box.getChildren().add(agregar);
        add1.setOnAction(e ->{
            if(usuario.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Advertencia, el campo esta vacío.", ButtonType.OK);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.show();

            }else{
                if(validarDatos(usuario.getText())){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Advertencia, el usuario no esta registrado.", ButtonType.OK);
                    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    alert.show();
                    

                }else{
               
                    box.getChildren().remove(agregar);
                    
                }

            }
        });
        
        
        
        
    }
    public void panelEdit(){
        
        
        
        
        add1 = new Button("Edit");
        darEfectoBoton(add1);
        
        usuario = new TextField();
        clave = new TextField();
        nombre = new TextField();
        apellido = new TextField();
        telefono = new TextField();
        email = new TextField();
        direccion = new TextField();
        cedula = new TextField();
        matricula = new TextField();
        
        
        usuario.setPromptText("Usuario");
        clave.setPromptText("Editar Clave");
        nombre.setPromptText("Editar Nombre");
        apellido.setPromptText("Editar Apellido");
        telefono.setPromptText("Editar Telefono");
        email.setPromptText("Editar Email");
        direccion.setPromptText("Editar Direccion");
        cedula.setPromptText("Editar Cedula");
        
        
        agregar.getChildren().clear();
        box.getChildren().remove(agregar);
       
        HBox temp = new HBox();
        temp.getChildren().addAll(usuario,clave,nombre,apellido,telefono,email,direccion,cedula);
        temp.setSpacing(15);
        temp.setAlignment(Pos.CENTER);
        
        
        agregar.getChildren().addAll(temp,add1);
        agregar.setSpacing(15);
        agregar.setAlignment(Pos.CENTER);
        
        box.getChildren().add(agregar);
        add1.setOnAction(e -> box.getChildren().remove(agregar));
        
    }
    
    
    public void organizarPanel(){
        
        root = new BorderPane();
        tabla = new TableView();
        box = new VBox();
        HBox botones = new HBox();
        agregar = new VBox();

        tabla.setEditable(true);

        Button add = new Button("Agregar Usuario");
        Button edit = new Button("Editar Usuario");
        Button atras = new Button("Atrás");
        Button delete = new Button("Eliminar");
        
        darEfectoBoton(add);
        darEfectoBoton(delete);
        darEfectoBoton(edit);
        darEfectoBoton(atras);
        
        atras.setOnAction(e -> PoliVentas.cambiarVentana(root, new PantallaAdministrador().getRoot()));
        add.setOnAction(e -> panelAdd());
        edit.setOnAction(e -> panelEdit());
        delete.setOnAction(e -> panelDelete());
        
        
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
        
        
        
    public void insertar() throws SQLException{
        Conexion con=null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        try {                   
                   
                   
                   con=new Conexion();          
                  
                   con.connect();
                                  
                   String query="INSERT INTO `usuario` (`usuario`, `contrasenia`, `tipo`)  "
                           + "VALUES (`"+usuario.getText()+"`,`"+clave.getText()+"`,`"+rol.getValue().toString()+"`)";
                   
                   stmt = con.getCn().prepareStatement(query);
                   
                   
                   rs= stmt.executeQuery();
                   
                   query="INSERT INTO `"+rol.getValue().toString()+"` (`cedula`, `nombres`, `apellidos`, `correo`, `telefono`, `usuario`) "
                           + "VALUES (`"+cedula.getText()+"`,`"+nombre.getText()+"`,`"+apellido.getText()+"`,`"+email.getText()+"`,`"+telefono.getText()+"`,`"+usuario.getText()+"`)";
                   
                   stmt = con.getCn().prepareStatement(query);
                   
                   if(null !=rs){
                        try {
                            rs.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(PantallaMisProductos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                   rs2= stmt.executeQuery();
                   
                   if(rol.getValue()=="vendedor"){
                       PoliVentas.cambiarVentana(root, new PantallaVendedor().getRoot());
                       
                   }
                   else if(rol.getValue()=="comprador"){
                       PoliVentas.cambiarVentana(root, new PantallaComprador().getRoot());
                       
                   }
                   
                   
                   
               } catch (SQLException ex) {
                   Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
               }finally{
            if (con != null && rs2 !=null && rs != null) {
                try {
                    con.getCn().close();
                    rs2.close();
                    rs.close();
                }catch (SQLException e) {
                    Logger.getLogger(PoliVentas.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        
        
        
    }
    
    
    public void cargarDatos(){
        Conexion con=null;
        PreparedStatement stmt2 = null;
        ResultSet rs2 = null;
        try {

            TableColumn cedula1 = new TableColumn("Cedula");
            TableColumn nombre1 = new TableColumn("Nombre");
            TableColumn apellido1 = new TableColumn("Apellido");
            TableColumn correo1 = new TableColumn("Correo");
            TableColumn telefono1 = new TableColumn("Telefono");
            TableColumn usuario1 = new TableColumn("Usuario");
            TableColumn contrasenia1 = new TableColumn("Clave");
            TableColumn rol1 = new TableColumn("Rol");


            tabla.getColumns().addAll(cedula1,usuario1,contrasenia1,nombre1,apellido1,telefono1,correo1,rol1);
            
            con=new Conexion();
            con.connect();           

            

            ArrayList<Persona> personas=new ArrayList();
            stmt2 = con.getCn().prepareStatement("SELECT * FROM usuario u, comprador c where u.usuario=c.usuario");
            rs2= stmt2.executeQuery();


            while(rs2.next()){
                System.out.println(rs2.getString("tipo"));
                    personas.add(new Persona(rs2.getString("cedula"), rs2.getString("nombres"), rs2.getString("apellidos"), rs2.getString("telefono"),rs2.getString("correo"), rs2.getString("u.usuario"), rs2.getString("contrasenia"), rs2.getString("u.tipo")));

            }
            
            if(rs2!=null){
                        try {
                            rs2.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(PantallaMisProductos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            stmt2 = con.getCn().prepareStatement("SELECT * FROM usuario u, vendedor c where u.usuario=c.usuario");
            rs2= stmt2.executeQuery();


            while(rs2.next()){
                System.out.println(rs2.getString("tipo"));
                    personas.add(new Persona(rs2.getString("cedula"), rs2.getString("nombres"), rs2.getString("apellidos"), rs2.getString("telefono"),rs2.getString("correo"), rs2.getString("u.usuario"), rs2.getString("contrasenia"), rs2.getString("u.tipo")));

            }
            
            if(rs2!=null){
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
            



            nombre1.setMinWidth(100);
            nombre1.setCellValueFactory(new PropertyValueFactory<Persona, String>("nombres"));


            apellido1.setMinWidth(100);
            apellido1.setCellValueFactory(new PropertyValueFactory<Persona, String>("apellidos"));



            correo1.setMinWidth(200);
            correo1.setCellValueFactory(new PropertyValueFactory<Persona, String>("correo"));


            cedula1.setMinWidth(200);
            cedula1.setCellValueFactory(new PropertyValueFactory<Persona, String>("cedula"));


            usuario1.setMinWidth(200);
            usuario1.setCellValueFactory(
                    new PropertyValueFactory<Persona, String>("usuario"));


            telefono1.setMinWidth(200);
            telefono1.setCellValueFactory(
                    new PropertyValueFactory<Persona, String>("telefono"));

            contrasenia1.setMinWidth(200);
            contrasenia1.setCellValueFactory(
                    new PropertyValueFactory<Persona, String>("contrasenia"));

            rol1.setMinWidth(200);
            rol1.setCellValueFactory(
                    new PropertyValueFactory<Persona, String>("rol"));




            tabla.setItems(data);

        } catch (SQLException ex) {
            Logger.getLogger(PoliVentas.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (con != null && rs2 !=null) {
                try {
                    con.getCn().close();
                    rs2.close();
                }catch (SQLException e) {
                    Logger.getLogger(PoliVentas.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }

        
    }
        
        
    public boolean validarDatos(String usuario){
        ResultSet rs2 = null;
        try {
                    Conexion con=new Conexion();

                    con.connect();           

                    PreparedStatement stmt2;


                    stmt2 = con.getCn().prepareStatement("SELECT usuario FROM usuario where usuario=?");
                    stmt2.setString(1, usuario);
                    
                    rs2= stmt2.executeQuery();
            return !rs2.next();

            } catch (SQLException ex) {
                Logger.getLogger(PoliVentas.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }finally{
            if(rs2!=null){
                        try {
                            rs2.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(PantallaMisProductos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
        }
        }
    }
    

