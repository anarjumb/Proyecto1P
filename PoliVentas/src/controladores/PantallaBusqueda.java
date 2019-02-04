package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
/**
 *
 * @author RBLOO
 */
public final class PantallaBusqueda {
    
    private BorderPane root;
    
    private TextField tfbuscar;
    
    
    public PantallaBusqueda(){
        iniciarComponentes();
    }
    
    public void iniciarComponentes(){
        
     Button btnbuscar;
     Button btnsalir;
     HBox panelSuperior;
     VBox box;
        root = new BorderPane();
        box = new VBox();
        btnbuscar = new Button("Buscar");
        btnsalir = new Button("Atrás");
        tfbuscar = new TextField();
        panelSuperior = new HBox();
        
        darEfectoBoton(btnsalir);
        darEfectoBoton(btnbuscar);
        
        tfbuscar.setPromptText("Buscar producto...");
        HBox.setHgrow(tfbuscar, Priority.ALWAYS);
        
        HBox searchBar = new HBox();
        searchBar.getChildren().add(tfbuscar);
        
        
        
        panelSuperior.getChildren().addAll(searchBar, btnbuscar,btnsalir);
        panelSuperior.setSpacing(50);
        panelSuperior.setAlignment(Pos.CENTER);
        
        box.getChildren().addAll(panelSuperior,tableView());
        box.setAlignment(Pos.CENTER);
        box.setSpacing(35);
        
        root.setStyle("-fx-background-image: url('/imagenes/buscar.jpg'); "
                    + "-fx-background-position: center center; "
                    + "-fx-background-repeat: stretch;"
                    + "-fx-background-size:" + Constantes.ANCHO + " " + Constantes.ALTO + ";");

        
        btnsalir.setOnAction(e ->{
            if(PoliVentas.getRol().equals("vendedor")){
                PoliVentas.cambiarVentana(root, new PantallaVendedor().getRoot());
            }
            else{
                PoliVentas.cambiarVentana(root, new PantallaComprador().getRoot());
            }
            
                });
        root.setCenter(box);
       
        btnbuscar.setOnAction(e ->{ 
            
                if(tfbuscar.getText().length()>=3){
        
                    try {
                       Conexion con=new Conexion();
                       con.connect();           
                       PreparedStatement stmt2;


                       stmt2 = con.getCn().prepareStatement("SELECT * FROM producto where nombre_producto LIKE '%?%' and descripcion= '%?%' ORDER BY calificacion_total");

                        stmt2.setString(1, verificarTexto(tfbuscar.getText()).get(0));
                        stmt2.setString(2, verificarTexto(tfbuscar.getText()).get(0));
                    
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


                   }                  
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

        TableColumn<Producto, String> colCategoria = new TableColumn<>("Categoría");
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoría"));

        TableColumn<Producto, Number> colPrecio = new TableColumn<>("Precio");
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        
        TableColumn<Producto, Number> colEntrega = new TableColumn<>("Tiempo máximo de entrega");
        colEntrega.setCellValueFactory(new PropertyValueFactory<>("precio"));
        
        TableColumn<Producto, Number> colCalificacion = new TableColumn<>("Calificación");
        colCalificacion.setCellValueFactory(new PropertyValueFactory<>("calificacionProducto"));

       tableView.getColumns().addAll(colNombre, colCategoria, colPrecio, colEntrega, colCalificacion);
       
       Producto p1 = new Producto(1,"Cargador","Telefonía",1,"2 días",4);
       Producto p2 = new Producto(2,"Mouse","Tecnología",5,"5 días",3);
       Producto p3 = new Producto(3,"Hawei p20","Telefonía",300,"7 días",5);
       
       
       List<Producto> list = new ArrayList<>();
       list.add(p1);
       list.add(p2);
       list.add(p3);
       ObservableList<Producto> obList = FXCollections.observableList(list);
       
       tableView.getItems().addAll(obList);
       
        FilteredList<Producto> filteredData = new FilteredList<>(obList, p -> true);
        tableView.setItems(filteredData);
        
        tfbuscar.textProperty().addListener((prop, old, text) -> 
            filteredData.setPredicate(producto -> {
                if(text == null || text.isEmpty()) return true;
                String name = producto.getNombre().toLowerCase();  
                return name.contains(text.toLowerCase());
            })
        );
       return tableView;
       
    }
    
    public static List<String> verificarTexto(String cadena){
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
        return parametros;
    }
    
    public static boolean tiene3caracteres(String cadena){
        if(cadena.length() < 3){
            Alert alert = new Alert(AlertType.INFORMATION, "Advertencia, debes ingresar al menos tres caracteres diferentes de espacio.", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
            return false;
        }
        
        return true;
    }
    
    public static boolean tiene3caracteres2(String cadena){
        if(cadena.length() < 3){
            
            return false;
        }
        
        return true;
    }
    
    public BorderPane getRoot(){
        return root;
    }
    
    
    public void darEfectoBoton(Button boton){
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
