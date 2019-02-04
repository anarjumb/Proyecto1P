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
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
public final class Inicio {
    
    private BorderPane root;
    
    private  static String nombreusuario;
   

    Inicio(){        

        organizarpanel();

    }
        
    
    
    public void organizarpanel(){
         VBox box;
        VBox boxlabel;
        VBox boxfield;
        
        Button inicio;
        Button salir;
        HBox boxUser;
        TextField usuario;
        TextField clave;
        Label lbluser;
        Label lblpass;
            root = new BorderPane();
            box = new VBox();
            boxUser = new HBox();
            boxlabel = new VBox();
            boxfield = new VBox();
            Conexion con = new Conexion();
            
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

           
            
            
            


            darEfectoBoton(inicio);
            inicio.setOnAction(e -> {
                
                
                
                try {
                    con.connect();           

                    PreparedStatement stmt2;


                    stmt2 = con.getCn().prepareStatement("SELECT * FROM usuario where usuario=?");
                    
                    nombreusuario = usuario.getText();
                    stmt2.setString(1, usuario.getText());
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
                
            } catch (SQLException ex) {
                Logger.getLogger(PoliVentas.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            
            });
           
            darEfectoBoton(salir);
            
            salir.setOnAction(e ->{Platform.exit();
                        con.desconectar();
                    });
            
            
            
            
            boxlabel.getChildren().addAll(lbluser,lblpass);
            boxlabel.setSpacing(30);
            
            boxfield.getChildren().addAll(usuario,clave);
            boxfield.setSpacing(35);
            
            boxlabel.setAlignment(Pos.TOP_LEFT);
            boxfield.setAlignment(Pos.BASELINE_LEFT);
            
            boxUser.getChildren().addAll(boxlabel,boxfield);

            boxUser.setAlignment(Pos.CENTER);
            boxUser.setSpacing(35);

            box.getChildren().addAll(boxUser,inicio,salir);

            

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

