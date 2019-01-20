/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
/**
 *
 * @author RBLOO
 */
public class PantallaBusqueda {
    
    private BorderPane root;
    private StackPane panelCentral;
    private Button btn_buscar;
    private Button btn_salir;
    private Label lbl_elemento;
    private TextField tf_buscar;
    private HBox panelSuperior;
    private TextArea ta_resultados;
    
    
    public PantallaBusqueda(){
        iniciarComponentes();
    }
    
    public void iniciarComponentes(){
        root = new BorderPane();
        btn_buscar = new Button("Buscar");
        btn_salir = new Button("Salir");
        lbl_elemento = new Label("Ingrese producto a buscar");
        tf_buscar = new TextField();
        panelSuperior = new HBox();
        ta_resultados = new TextArea();
        panelCentral = new StackPane();
        
        Image image = new Image(getClass().getResourceAsStream("/imagenes/azul.jpg"));
        ImageView iv = new ImageView(image);
        
        ta_resultados.setScaleX(0.5);
        ta_resultados.setScaleY(0.5);
        iv.setFitHeight(Constantes.ALTO/2);
        iv.setFitWidth(Constantes.ANCHO/2);
        
        
        panelCentral.getChildren().addAll(iv,ta_resultados);
        
        panelSuperior.getChildren().addAll(lbl_elemento,tf_buscar, btn_buscar);
        panelSuperior.setSpacing(50);
        
        root.setStyle("-fx-background-image: url('/imagenes/busqueda.jpg'); "
                    + "-fx-background-position: center center; "
                    + "-fx-background-repeat: stretch;"
                    + "-fx-background-size:" + Constantes.ANCHO + " " + Constantes.ALTO + ";");

        panelSuperior.setAlignment(Pos.CENTER);
        btn_salir.setOnAction(e ->PoliVentas.cambiarVentana(root, new Inicio().getRoot()));
        root.setTop(panelSuperior);
        root.setCenter(panelCentral);
        root.setBottom(btn_salir);
        
        lbl_elemento.setTextFill(Color.web("#59FF33"));
        lbl_elemento.setFont(Font.font("Cambria", 27));
        
        
        btn_buscar.setOnAction(e ->{ 
            
        
                 try {
                    Conexion con=new Conexion();
                    con.connect();           
                    PreparedStatement stmt2;


                    stmt2 = con.getCn().prepareStatement("SELECT * FROM producto where nombre_producto LIKE '%?%' and descripcion= '%?%' ORDER BY calificacion_total");
                    
                    stmt2.setString(1, verificarTexto(tf_buscar.getText()).get(0));
                    stmt2.setString(2, verificarTexto(tf_buscar.getText()).get(0));
                    ResultSet rs2= stmt2.executeQuery();
                    if(!rs2.next()){
                        Alert alert = new Alert(AlertType.INFORMATION, "No se hallaron resultados.", ButtonType.OK);
                        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                        alert.show();
                    }else{
                        rs2.previous();
                        
                        while(rs2.next()){
                        if(rs2.getString("tipo").equals("V")){
                            PoliVentas.cambiarVentana(root, new PantallaVendedor().getRoot());

                        }else{
                            PoliVentas.cambiarVentana(root, new PantallaComprador().getRoot());
                        }     
                    }  

                    
                }
                

                // TODO code application logic here
            } catch (SQLException ex) {
                Logger.getLogger(PoliVentas.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        });
    }
    
    private ArrayList<String> verificarTexto(String cadena){
        String palabra = "";
        ArrayList<String> parametros = new ArrayList<>();
        
        if(!tiene3caracteres(cadena)) return null;
            cadena += " ";
        if(!"".equals(cadena)){
            for(int i = 0; i <= cadena.length() -1 ;i++){
                if(Character.isLetter(cadena.charAt(i)) || Character.isDigit(cadena.charAt(i))){
                    palabra += cadena.charAt(i);
                }else if(cadena.charAt(i) == ' ' && !palabra.equals("")){
                    parametros.add(palabra);
                    palabra = "";
                }
            }
        }
        System.out.println(parametros);
        return parametros;
    }
    
    private boolean tiene3caracteres(String cadena){
        if(cadena.length() < 3){
            Alert alert = new Alert(AlertType.INFORMATION, "Advertencia, debes ingresar al menos tres caracteres diferentes de espacio.", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
            return false;
        }
        
        return true;
    }
    
    public BorderPane getRoot(){
        return root;
    }
}
