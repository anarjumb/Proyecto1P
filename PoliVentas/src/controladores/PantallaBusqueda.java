
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
    private TextField tf_buscar;
    private HBox panelSuperior;
    private TextArea ta_resultados;
    private VBox box;
    
    
    public PantallaBusqueda(){
        iniciarComponentes();
    }
    
    public void iniciarComponentes(){
        root = new BorderPane();
        box = new VBox();
        btn_buscar = new Button("Buscar");
        btn_salir = new Button("Atrás");
        tf_buscar = new TextField();
        panelSuperior = new HBox();
        ta_resultados = new TextArea();
        panelCentral = new StackPane();
        
        DarEfectoBoton(btn_salir);
        DarEfectoBoton(btn_buscar);
        
        tf_buscar.setPromptText("Buscar producto...");
        HBox.setHgrow(tf_buscar, Priority.ALWAYS);
        
        HBox searchBar = new HBox();
        searchBar.getChildren().add(tf_buscar);
        
        Image image = new Image(getClass().getResourceAsStream("/imagenes/azul.jpg"));
        ImageView iv = new ImageView(image);
        
        ta_resultados.setScaleX(0.5);
        ta_resultados.setScaleY(0.5);
        iv.setFitHeight(Constantes.ALTO/2);
        iv.setFitWidth(Constantes.ANCHO/2);
        
        
        panelCentral.getChildren().addAll(iv,tableView());
        panelCentral.setAlignment(Pos.CENTER);
        
        panelSuperior.getChildren().addAll(searchBar, btn_buscar,btn_salir);
        panelSuperior.setSpacing(50);
        panelSuperior.setAlignment(Pos.CENTER);
        
        box.getChildren().addAll(panelSuperior,panelCentral);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(35);
        
        root.setStyle("-fx-background-image: url('/imagenes/buscar.jpg'); "
                    + "-fx-background-position: center center; "
                    + "-fx-background-repeat: stretch;"
                    + "-fx-background-size:" + Constantes.ANCHO + " " + Constantes.ALTO + ";");

        
        btn_salir.setOnAction(e ->{
            if(PoliVentas.getRol().equals("vendedor")){
                PoliVentas.cambiarVentana(root, new PantallaVendedor().getRoot());
            }
            else{
                PoliVentas.cambiarVentana(root, new PantallaComprador().getRoot());
            }
            
                });
       // root.setTop(panelSuperior);
        root.setCenter(box);
       
//        
//        lbl_elemento.setTextFill(Color.web("#59FF33"));
//        lbl_elemento.setFont(Font.font("Cambria", 27));
        
        
        btn_buscar.setOnAction(e ->{ 
            
                if(tf_buscar.getText().length()>=3){
        
                    try {
                       Conexion con=new Conexion();
                       con.connect();           
                       PreparedStatement stmt2;


                       stmt2 = con.getCn().prepareStatement("SELECT * FROM producto where nombre_producto LIKE '%?%' and descripcion= '%?%' ORDER BY calificacion_total");

                        stmt2.setString(1, verificarTexto(tf_buscar.getText()).get(0));
                        stmt2.setString(2, verificarTexto(tf_buscar.getText()).get(0));
                       //stmt2.setString(1, tf_buscar.getText());
                       //stmt2.setString(2, tf_buscar.getText());
                    
                        ResultSet rs2= stmt2.executeQuery();
                       if(!rs2.next()){
                           
                            rs2.previous();

                            while(rs2.next()){
                                if(rs2.getString("tipo").equals("V")){
                                    PoliVentas.cambiarVentana(root, new PantallaVendedor().getRoot());

                                }else{
                                    PoliVentas.cambiarVentana(root, new PantallaComprador().getRoot());
                                }     
                            } 
                           
                       }else{
                             
                           Alert alert = new Alert(AlertType.INFORMATION, "No se hallaron resultados.", ButtonType.OK);
                           alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                           alert.show();


                   }                       // TODO code application logic here
                    } catch (SQLException ex) {
                        Logger.getLogger(PoliVentas.class.getName()).log(Level.SEVERE, null, ex);

                    }
                 
        }
                
        else{
                    
            Alert alert = new Alert(AlertType.INFORMATION, "Advertencia, debes ingresar al menos tres caracteres diferentes de espacio.", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
                         
            }
    });
        
    }
    
    
    private TableView tableView(){
        TableView<Producto> tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Producto, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Producto, String> colCategoría = new TableColumn<>("Categoría");
        colCategoría.setCellValueFactory(new PropertyValueFactory<>("categoría"));

        TableColumn<Producto, Number> colPrecio = new TableColumn<>("Precio");
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        
        TableColumn<Producto, Number> colEntrega = new TableColumn<>("Tiempo máximo de entrega");
        colEntrega.setCellValueFactory(new PropertyValueFactory<>("precio"));
        
        TableColumn<Producto, Number> colCalificacion = new TableColumn<>("Calificación");
        colCalificacion.setCellValueFactory(new PropertyValueFactory<>("calificacionProducto"));

       tableView.getColumns().addAll(colNombre, colCategoría, colPrecio, colEntrega, colCalificacion);
       
       Producto p1 = new Producto("Cargador","Telefonía",7,"2 días",4);
       Producto p2 = new Producto("Mouse","Tecnología",5,"5 días",3);
       Producto p3 = new Producto("Hawei p20","Telefonía",300,"7 días",5);
       
       tableView.getItems().addAll(p1,p2,p3);
       return tableView;
       
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
    
    
    public void DarEfectoBoton(Button boton){
            boton.setStyle("-fx-font: 18 arial; -fx-base: #84307A;");
        

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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
