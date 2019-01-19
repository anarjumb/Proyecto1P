/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class AdmUsuarios {
    
    
    private BorderPane root;
    private TableView tabla;
    private VBox box,agregar;
    private Button add,delete,edit,atras,add1;
    private HBox botones;
    private TextField usuario,clave,nombre,apellido,telefono,email,direccion,matricula,rol,cedula;
    
    public AdmUsuarios(){
        OrganizarPanel();
        
    }
    
    
    public void PanelAdd(){
        agregar = new VBox();
        add1 = new Button("Agregar");
        DarEfectoBoton(add1);
        
        
        
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
        
        
        usuario.setPromptText("Ingrese usuario");
        clave.setPromptText("Ingrese clave");
        nombre.setPromptText("Ingrese nombre");
        apellido.setPromptText("Ingrese apellido");
        telefono.setPromptText("Ingrese telefono");
        email.setPromptText("Ingrese email");
        direccion.setPromptText("Ingrese direccion");
        cedula.setPromptText("Ingrese cedula");
        matricula.setPromptText("Ingrese matricula");
        
        n1.getChildren().addAll(usuario,clave,nombre,apellido,telefono);
        n2.getChildren().addAll(email,direccion,cedula,matricula,add1);
        n1.setAlignment(Pos.CENTER);
        n2.setAlignment(Pos.CENTER);
        n1.setSpacing(15);
        n2.setSpacing(15);
        
        agregar.getChildren().addAll(n1,n2);
        agregar.setAlignment(Pos.CENTER);
        agregar.setSpacing(15);
        
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
       
        agregar.getChildren().addAll(usuario,clave,nombre,apellido,telefono,email,direccion,cedula,add1);
        
        box.getChildren().add(agregar);
        add1.setOnAction(e -> box.getChildren().remove(agregar));
        
    }
    
    
    public void OrganizarPanel(){
        
        root = new BorderPane();
        tabla = new TableView();
        box = new VBox();
        botones = new HBox();
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
        
        TableColumn nombre = new TableColumn("Nombre");
        TableColumn apellido = new TableColumn("Apellido");
        TableColumn correo = new TableColumn("Apellido");
        TableColumn telefono = new TableColumn("Apellido");
        TableColumn usuario = new TableColumn("Usuario");
        TableColumn clave = new TableColumn("Clave");
        TableColumn rol = new TableColumn("Rol");
        
        nombre.setMinWidth(100);
        apellido.setMinWidth(50);
        
        
        nombre.setCellFactory(new PropertyValueFactory("hola"));
        
       // tabla.setItems(data);
        
        
        tabla.getColumns().addAll(usuario,clave,nombre,apellido,telefono,correo,rol);
        
        /*try {
                    Conexion con=new Conexion();

                    con.connect();           

                    PreparedStatement stmt2;


                    stmt2 = con.getCn().prepareStatement("SELECT * FROM usuario where usuario== and contrasenia==");
                   // stmt2.setString(1, usuario.getText());
                    //stmt2.setString(2, clave.getText());
                    ResultSet rs2= stmt2.executeQuery();
                    if(!rs2.next()){
                        PoliVentas.cambiarVentana(root, new Registro().getRoot());
                    }else{
                        rs2.previous();
                        while(rs2.next()){
                            
                            System.out.println(rs2);
                            
                    }  

                    
                }
                

                // TODO code application logic here
            } catch (SQLException ex) {
                Logger.getLogger(PoliVentas.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            
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

