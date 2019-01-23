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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 *
 * @author RBLOO
 */
public class PantallaMisProductos {
    
    private BorderPane root;
    private TableView<Producto> tabla;
    private VBox box,agregar;
    private Button add,delete,edit, atras,add1;
    private HBox botones;
    private TextField nombre,categoria,precio;
    private Conexion con=new Conexion();
    private ArrayList<Producto> productos;
    private String cedula="";
    
    public PantallaMisProductos(){
        OrganizarPanel();
    }
    
    
    
     public void PanelAdd(){
        
        add1 = new Button("Agregar");
        DarEfectoBoton(add1);
        agregar.getChildren().clear();
        box.getChildren().removeAll(agregar);
        
        
        
        
        HBox n1 = new HBox();
        HBox n2 = new HBox();
        
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
                                System.out.println("Se agregó");        
            }
        );
        
        
        
    }
    
    public void PanelEdit(){
        
        
        
        
        add1 = new Button("Edit");
        DarEfectoBoton(add1);
        
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
                                System.out.println("Se agregó");
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
            //Obtiene el índice de seleción, el cual es la primary key del producto
            //int index = tabla.getSelectionModel().selectedIndexProperty().get() + 1;
            
            try {
                Statement stmt = con.getCn().createStatement();
               stmt.executeUpdate("DELETE FROM producto WHERE id_producto = " + producto.getId_producto() );
                
               
//                ResultSet rs2= stmt.executeQuery("SELECT * FROM producto WHERE id_producto = " + producto.getId_producto() );
//                while(rs2.next()){
//                    System.out.println(rs2.getString(1) + " " +rs2.getString(2) + " " +rs2.getString(6));
//                }
                mostrarResultados();
                System.out.println("Se eliminó el producto: " +producto.getNombre());
                
            } catch (SQLException ex) {
                Logger.getLogger(PantallaMisProductos.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
        }else{
            crearAlerta("Seleccione un producto a eliminar.");
        }
    }
    
    //Relaciona la variable cedula con el usuario que ingresó al sistema
    private void relacionarCedula(){
        
        try {
            PreparedStatement stmt;
            
            
            stmt = con.getCn().prepareStatement("SELECT cedula FROM vendedor where usuario=?");
            
            stmt.setString(1, Inicio.getUsuario());
            //stmt2.setString(2, clave.getText());
            ResultSet rs2= stmt.executeQuery();
            
            
            while(rs2.next()){
                
                cedula = rs2.getString("cedula");
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PantallaMisProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void crearTableView(){
        TableColumn nombre = new TableColumn("Nombre");
        TableColumn categoria = new TableColumn("Categoria");
        TableColumn precio = new TableColumn("Precio");
        //TableColumn cantidad = new TableColumn("Cantidad");
        
        nombre.setMinWidth(100);
        
       // tabla.setItems(data);
        
        tabla.getColumns().addAll(nombre,categoria,precio);
        
        nombre.setMinWidth(100);
        nombre.setCellValueFactory(
                new PropertyValueFactory<Producto, String>("nombre"));

        //TableColumn apellido = new TableColumn("Last Name");
        categoria.setMinWidth(100);
        categoria.setCellValueFactory(
                new PropertyValueFactory<Producto, String>("categoria"));

        //TableColumn emailCol = new TableColumn("Email");
        precio.setMinWidth(200);
        precio.setCellValueFactory(
                new PropertyValueFactory<Producto, Float>("precio"));
    }
    private void mostrarResultados(){
        
        tabla.getItems().clear();
        
        try{
                    

                    con.connect();           
                    relacionarCedula();
                    
                    PreparedStatement stmt2;
                    
                    
                    stmt2 = con.getCn().prepareStatement("SELECT cedula FROM vendedor where usuario=?");
                    
                    stmt2 = con.getCn().prepareStatement("SELECT id_producto,nombre_producto,descripcion,precio FROM producto where cedula_vendedor=?");
                    stmt2.setString(1, cedula);
                    //stmt2.setString(2, clave.getText());
                    ResultSet rs= stmt2.executeQuery();
                    
                    //Vaciando los productos residuales, sirve cada ves que se vaya a actualizar
                    productos.clear();
                    
                    while(rs.next()){
                            productos.add(new Producto(rs.getInt("id_producto"),rs.getString("nombre_producto"), rs.getString("descripcion"),rs.getFloat("precio")));
                                  
                    }
                    final ObservableList<Producto> data = FXCollections.observableArrayList(productos); 
                    tabla.setEditable(true);
                    tabla.setVisible(true);
                            //TableColumn nombre = new TableColumn("First Name");
                    
                    tabla.getItems().addAll(data);


                // TODO code application logic here
            } catch (SQLException ex) {
                Logger.getLogger(PoliVentas.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void OrganizarPanel(){
        
        root = new BorderPane();
        tabla = new TableView<>();
        box = new VBox();
        agregar = new VBox();
        botones = new HBox();
        tabla.setEditable(true);
        productos =new ArrayList();
        //Muestra los productos actuales
        crearTableView();
        mostrarResultados();
        
        add = new Button("Agregar Producto");
        delete = new Button("Eliminar Producto");
        edit = new Button("Editar Producto");
        atras = new Button("Atrás");
        
        DarEfectoBoton(add);
        DarEfectoBoton(delete);
        DarEfectoBoton(edit);
        DarEfectoBoton(atras);
        
        delete.setOnAction(e -> eliminarProducto());
        atras.setOnAction(e -> PoliVentas.cambiarVentana(root, new PantallaVendedor().getRoot()));
        
        add.setOnAction(e -> PanelAdd());
        edit.setOnAction(e -> PanelEdit());
        
        
            
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
        
        
    public void crearAlerta(String mensaje){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, mensaje, ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
    }
        
    
}
