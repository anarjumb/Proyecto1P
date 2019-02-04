package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 *
 * @author RBLOO
 */
public final class PantallaMisProductos {
    
    private BorderPane root;
    private TableView<Producto> tabla;
    private VBox box;
    private VBox agregar;
    
    private Button add1;
    
    private TextField nombre;
    private TextField categoria;
    private TextField precio;
    private Conexion con=new Conexion();
    private ArrayList<Producto> productos;
    private String cedula="";
    
    public PantallaMisProductos(){
        organizarPanel();
    }
    
    
    
     public void panelAdd(){
        
        add1 = new Button("Agregar");
        darEfectoBoton(add1);
        agregar.getChildren().clear();
        box.getChildren().removeAll(agregar);
        
        
        
        
    
        
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
        add1.setOnAction(e -> {box.getChildren().remove(agregar);
                                agregrarProducto();
                                       
            }
        );
        
        
        
    }
    
    public void panelEdit(){
        add1 = new Button("Edit");
        darEfectoBoton(add1);
        
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
        add1.setOnAction(e -> {box.getChildren().remove(agregar);
                                agregrarProducto();
                               
                        }
        
        );
        
    }
    
    private void agregrarProducto(){
       
        con.connect();
        
        try {
            relacionarCedula();
            Statement stmt = con.getCn().createStatement();
            
            stmt.executeUpdate("INSERT INTO producto (cedula_vendedor,descripcion,precio,nombre_producto) VALUES( '"
                                + cedula+"','"+ categoria.getText()+ "','"+Float.parseFloat(precio.getText())+ "','"+nombre.getText()+"')"
            );
            
             mostrarResultados();
             crearAlerta("Se agregó el producto correctamente.");
        } catch (SQLException ex) {
            Logger.getLogger(PantallaMisProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void eliminarProducto(){
        Producto producto = tabla.getSelectionModel().getSelectedItem(); 
        if(producto != null){
            try {
                Statement stmt = con.getCn().createStatement();
               stmt.executeUpdate("DELETE FROM producto WHERE id_producto = " + producto.getIdproducto() );
                
                crearAlerta("Se eliminó el producto: " +producto.getNombre());
                
            } catch (SQLException ex) {
                Logger.getLogger(PantallaMisProductos.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
        }else{
            crearAlerta("Seleccione un producto a eliminar.");
        }
    }
    private void relacionarCedula(){
        
         ResultSet rs2 = null;
        try {
            PreparedStatement stmt;
            
            stmt = con.getCn().prepareStatement("SELECT cedula FROM vendedor where usuario=?");
            
            stmt.setString(1, Inicio.getUsuario());
            rs2= stmt.executeQuery();
            
            
            while(rs2.next()){
                
                cedula = rs2.getString("cedula");
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PantallaMisProductos.class.getName()).log(Level.SEVERE, null, ex);
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
    
    
    private void crearTableView(){
        TableColumn nombre = new TableColumn("Nombre");
        TableColumn categoria = new TableColumn("Categoria");
        TableColumn precio = new TableColumn("Precio");
        nombre.setMinWidth(100);
        
        tabla.getColumns().addAll(nombre,categoria,precio);
        
        nombre.setMinWidth(100);
        nombre.setCellValueFactory(
                new PropertyValueFactory<Producto, String>("nombre"));
        categoria.setMinWidth(100);
        categoria.setCellValueFactory(
                new PropertyValueFactory<Producto, String>("categoria"));
        precio.setMinWidth(200);
        precio.setCellValueFactory(
                new PropertyValueFactory<Producto, Float>("precio"));
    }
    private void mostrarResultados(){
        
        tabla.getItems().clear();
        ResultSet rs = null;
        try{
                    

                    con.connect();           
                    relacionarCedula();
                    
                    PreparedStatement stmt2;
                    
                    
                    stmt2 = con.getCn().prepareStatement("SELECT cedula FROM vendedor where usuario=?");
                    
                    stmt2 = con.getCn().prepareStatement("SELECT id_producto,nombre_producto,descripcion,precio FROM producto where cedula_vendedor=?");
                    stmt2.setString(1, cedula);
                    rs= stmt2.executeQuery();
                    productos.clear();
                    
                    while(rs.next()){
                            productos.add(new Producto(rs.getInt("id_producto"),rs.getString("nombre_producto"), rs.getString("descripcion"),rs.getFloat("precio")));
                                  
                    }
                    final ObservableList<Producto> data = FXCollections.observableArrayList(productos); 
                    tabla.setEditable(true);
                    tabla.setVisible(true);
                    
                    tabla.getItems().addAll(data);

            } catch (SQLException ex) {
                Logger.getLogger(PoliVentas.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
            if(rs !=null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PantallaMisProductos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void organizarPanel(){
         Button delete;
     Button edit;
     Button atras;
     HBox botones;
        Button add;
        root = new BorderPane();
        tabla = new TableView<>();
        box = new VBox();
        agregar = new VBox();
        botones = new HBox();
        tabla.setEditable(true);
        productos =new ArrayList();
        crearTableView();
        mostrarResultados();
        
        add = new Button("Agregar Producto");
        delete = new Button("Eliminar Producto");
        edit = new Button("Editar Producto");
        atras = new Button("Atrás");
        
        darEfectoBoton(add);
        darEfectoBoton(delete);
        darEfectoBoton(edit);
        darEfectoBoton(atras);
        
        delete.setOnAction(e -> eliminarProducto());
        atras.setOnAction(e -> PoliVentas.cambiarVentana(root, new PantallaVendedor().getRoot()));
        
        add.setOnAction(e -> panelAdd());
        edit.setOnAction(e -> panelEdit());
        
        
            
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
        
        
    public void crearAlerta(String mensaje){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, mensaje, ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
    }
        
    
}
