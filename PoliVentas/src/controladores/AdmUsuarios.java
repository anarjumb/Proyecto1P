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
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;
import modelo.Persona;

/**
 *
 * @author adan
 */
public class AdmUsuarios {
    
    
    private BorderPane root;
    private TableView tabla;
    private VBox box,agregar;
    private Button add,delete,edit,atras,add1;
    private HBox botones;
    private TextField usuario,clave,nombre,apellido,telefono,email,direccion,matricula,cedula;
    private ComboBox rol;
    
    public AdmUsuarios(){
        OrganizarPanel();
        
    }
    
    
    public void PanelAdd(){
        
        add1 = new Button("Agregar");
        DarEfectoBoton(add1);
        agregar.getChildren().clear();
        box.getChildren().removeAll(agregar);
        Label error = new Label("Error, favor llenar todos los campos");
        error.setFont(Font.font("Cambria", 32));
        error.setTextFill(Color.web("#F33030"));
        
        
        
        
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
            if(usuario.getText().isEmpty() || clave.getText().isEmpty()||rol.getValue()==null){
           // System.out.println(rol.getValue());
            
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Advertencia, debes llenar todos los campos.", ButtonType.OK);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.show();
            
            }else{

                
                
                if(ValidarDatos(usuario.getText())){
                    
                    try {
                        Insertar();
                        box.getChildren().remove(agregar);
                    }catch (SQLException ex) {
                       Logger.getLogger(AdmUsuarios.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Advertencia, el usuario ya esta registrado anteriormente.", ButtonType.OK);
                    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    alert.show();
                    
                }
            }  
            });
        
        
        
    }
    
    
    
    public void PanelDelete(){
        
        add1 = new Button("Eliminar");
        DarEfectoBoton(add1);
        
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
        
        
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Advertencia, el usuario no esta registrado.", ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();
        
        box.getChildren().add(agregar);
        add1.setOnAction(e -> box.getChildren().remove(agregar));
        
        
        
        
    }
    public void PanelEdit(){
        
        
        
        
        add1 = new Button("Edit");
        DarEfectoBoton(add1);
        
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
    
    
    public void OrganizarPanel(){
        
        root = new BorderPane();
        tabla = new TableView();
        box = new VBox();
        botones = new HBox();
        agregar = new VBox();
     //   agregar = new HBox();
        tabla.setEditable(true);
        
        
        
            
        
        
        add = new Button("Agregar Usuario");
        delete = new Button("Eliminar Usuario");
        edit = new Button("Editar Usuario");
        atras = new Button("Atrás");
        
        DarEfectoBoton(add);
        DarEfectoBoton(delete);
        DarEfectoBoton(edit);
        DarEfectoBoton(atras);
        
        atras.setOnAction(e -> PoliVentas.cambiarVentana(root, new PantallaAdministrador().getRoot()));
        add.setOnAction(e -> PanelAdd());
        edit.setOnAction(e -> PanelEdit());
        delete.setOnAction(e -> PanelDelete());
        
        
        CargarDatos();
        
                
        
                
                
           
        
        
        
        /*
        nombre.setMinWidth(100);
        apellido.setMinWidth(50);
        
        
        nombre.setCellFactory(new PropertyValueFactory("hola"));*/
        
       // tabla.setItems(data);
        
        
        
        

            
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
        
        
        
    public void Insertar() throws SQLException{
        
        Conexion con=new Conexion();

        con.connect();           

        PreparedStatement stmt2;

        ArrayList<Persona> personas=new ArrayList();
        System.out.println("a");
        stmt2 = con.getCn().prepareStatement("INSERT INTO `usuario` (`usuario`, `contrasenia`, `tipo`) VALUES ('am', 'am', 'am'");
        System.out.println("n");
       // stmt2 = con.getCn().prepareStatement("INSERT INT * FROM usuario u, comprador c where u.usuario=c.usuario");

        ResultSet rs2= stmt2.executeQuery();
        
        System.out.println("m");
        
    }
    
    
    public void CargarDatos(){
        
        try {
            
            
            
            TableColumn cedula = new TableColumn("Cedula");
            TableColumn nombre = new TableColumn("Nombre");
            TableColumn apellido = new TableColumn("Apellido");
            TableColumn correo = new TableColumn("Correo");
            TableColumn telefono = new TableColumn("Telefono");
            TableColumn usuario = new TableColumn("Usuario");
            TableColumn contrasenia = new TableColumn("Clave");
            TableColumn rol = new TableColumn("Rol");


            tabla.getColumns().addAll(cedula,usuario,contrasenia,nombre,apellido,telefono,correo,rol);
            Conexion con=new Conexion();

            con.connect();           

            PreparedStatement stmt2;

            ArrayList<Persona> personas=new ArrayList();
            stmt2 = con.getCn().prepareStatement("SELECT * FROM usuario u, comprador c where u.usuario=c.usuario");
           // stmt2.setString(1, usuario.getText());
            //stmt2.setString(2, clave.getText());
            ResultSet rs2= stmt2.executeQuery();


            while(rs2.next()){
                    personas.add(new Persona(rs2.getString("cedula"), rs2.getString("nombres"), rs2.getString("apellidos"), rs2.getString("telefono"),rs2.getString("correo"), rs2.getString("u.usuario"), rs2.getString("contrasenia"), rs2.getString("u.tipo")));

            }

            stmt2 = con.getCn().prepareStatement("SELECT * FROM usuario u, vendedor c where u.usuario=c.usuario");
            rs2= stmt2.executeQuery();


            while(rs2.next()){
                    personas.add(new Persona(rs2.getString("cedula"), rs2.getString("nombres"), rs2.getString("apellidos"), rs2.getString("telefono"),rs2.getString("correo"), rs2.getString("u.usuario"), rs2.getString("contrasenia"), rs2.getString("u.tipo")));

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
            nombre.setCellValueFactory(new PropertyValueFactory<Persona, String>("nombres"));


            apellido.setMinWidth(100);
            apellido.setCellValueFactory(new PropertyValueFactory<Persona, String>("apellidos"));



            correo.setMinWidth(200);
            correo.setCellValueFactory(new PropertyValueFactory<Persona, String>("correo"));


            cedula.setMinWidth(200);
            cedula.setCellValueFactory(new PropertyValueFactory<Persona, String>("cedula"));


            usuario.setMinWidth(200);
            usuario.setCellValueFactory(
                    new PropertyValueFactory<Persona, String>("usuario"));


            telefono.setMinWidth(200);
            telefono.setCellValueFactory(
                    new PropertyValueFactory<Persona, String>("telefono"));

            contrasenia.setMinWidth(200);
            contrasenia.setCellValueFactory(
                    new PropertyValueFactory<Persona, String>("contrasenia"));

            rol.setMinWidth(200);
            rol.setCellValueFactory(
                    new PropertyValueFactory<Persona, String>("rol"));




            tabla.setItems(data);


            // TODO code application logic here
        } catch (SQLException ex) {
            Logger.getLogger(PoliVentas.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
        
        
    public boolean ValidarDatos(String usuario){
        try {
                    Conexion con=new Conexion();

                    con.connect();           

                    PreparedStatement stmt2;


                    stmt2 = con.getCn().prepareStatement("SELECT usuario FROM usuario where usuario=?");
                    stmt2.setString(1, usuario);
                    
                    ResultSet rs2= stmt2.executeQuery();
                    if(!rs2.next()){
                        return true;
                        
                    }else{
                        //rs2.previous();
                        return false;
                        
                    }
                        

                    
                
                

                // TODO code application logic here
            } catch (SQLException ex) {
                Logger.getLogger(PoliVentas.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
    }
    

