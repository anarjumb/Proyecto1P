/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author adan
 */
public class Inicio {
    
    private BorderPane root;
    private Button inicio, salir;
    private HBox BoxUser, BoxPass;
    private TextField usuario, clave;
    private Label lbluser, lblpass;
    private  static String nombreusuario;
    


    private VBox box, boxlabel, boxfield;

    Inicio(){        

        organizarpanel();

    }
        
    
    
    public void organizarpanel(){
            root = new BorderPane();
            box = new VBox();
            BoxUser = new HBox();
            BoxPass = new HBox();
            boxlabel = new VBox();
            boxfield = new VBox();


            inicio = new Button("Iniciar Sesion");
            salir = new Button("Salir");
            
            usuario = new TextField();
            clave = new TextField();
            
            lbluser = new Label("Ingrese usuario: ");
            lblpass = new Label("Ingrese clave: ");
            
            lbluser.setTextFill(Color.web("#59FF33"));
            lblpass.setTextFill(Color.web("#59FF33"));
            
            usuario.setPromptText("User..");
            clave.setPromptText("Password..");
            
            lbluser.setFont(Font.font("Cambria", 27));
            lblpass.setFont(Font.font("Cambria", 27));          

           
            
            
            


            DarEfectoBoton(inicio);
            inicio.setOnAction(e -> {
                
                
                
                try {
                    Conexion con=new Conexion();

                    con.connect();           

                    PreparedStatement stmt2;


                    stmt2 = con.getCn().prepareStatement("SELECT * FROM usuario where usuario=?");
                    nombreusuario = usuario.getText();
                    stmt2.setString(1, usuario.getText());
                   // stmt2.setString(2, clave.getText());
                    ResultSet rs2= stmt2.executeQuery();
                    if(!rs2.next()){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "No existe ese usuario, registrese.", ButtonType.OK);
                        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                        alert.show();
                        PoliVentas.cambiarVentana(root, new Registro().getRoot());
                    }else{
                        rs2.previous();
                        while(rs2.next()){
                            if(rs2.getString("contrasenia").equals(clave.getText())){
                                PoliVentas.setRol(rs2.getString("tipo"));
                                if(rs2.getString("tipo").equals("vendedor")){
                                    PoliVentas.cambiarVentana(root, new PantallaVendedor().getRoot());

                                }else if(rs2.getString("tipo").equals("administrador")){
                                    PoliVentas.cambiarVentana(root, new PantallaAdministrador().getRoot());

                                }else if(rs2.getString("tipo").equals("comprador")){
                                    PoliVentas.cambiarVentana(root, new PantallaComprador().getRoot());

                                }
                            }else{
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Clave incorrecta.", ButtonType.OK);
                                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                                alert.show();
                                
                            }
                          
                    }  

                    
                }
                

                // TODO code application logic here
            } catch (SQLException ex) {
                Logger.getLogger(PoliVentas.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            
            });
            
            
            
           
           
           
           
            DarEfectoBoton(salir);
            
            salir.setOnAction(e ->Platform.exit());
            
            
            
            
            boxlabel.getChildren().addAll(lbluser,lblpass);
            boxlabel.setSpacing(30);
            
            boxfield.getChildren().addAll(usuario,clave);
            boxfield.setSpacing(35);
            
            boxlabel.setAlignment(Pos.TOP_LEFT);
            boxfield.setAlignment(Pos.BASELINE_LEFT);
            
            BoxUser.getChildren().addAll(boxlabel,boxfield);

            BoxUser.setAlignment(Pos.CENTER);
            BoxUser.setSpacing(35);

            box.getChildren().addAll(BoxUser,inicio,salir);

            

            root.setStyle("-fx-background-image: url('/imagenes/login.jpg'); "
                    + "-fx-background-position: center center; "
                    + "-fx-background-repeat: stretch;"
                    + "-fx-background-size:" + Constantes.ANCHO + " " + Constantes.ALTO + ";");

            box.setAlignment(Pos.CENTER);
            box.setSpacing(50);
            root.setCenter(box);               
        }



        BorderPane getRoot() {
            return root;
        }
        
        public static String getUsuario(){
            return nombreusuario;
            
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

