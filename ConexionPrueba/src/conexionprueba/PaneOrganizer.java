/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionprueba;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author RBLOO
 */
public class PaneOrganizer {
    private BorderPane root;
    private TableView<String> tabla;
    private HBox panelC;
    
    public PaneOrganizer(){
        iniciar();
    }
    
    public void iniciar(){
        root = new BorderPane();
        tabla = new TableView<>();
        panelC = new HBox();
        
        TableColumn<String, String> colNombre = new TableColumn<>("Nombre");
        

        TableColumn<String, String> colCategoría = new TableColumn<>("Categoría");

        TableColumn<String, Number> colPrecio = new TableColumn<>("Precio");
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        
        tabla.getColumns().addAll(colNombre, colCategoría, colPrecio);
        
        
        ArrayList<String> ar = new ArrayList<String>();
        
        ar.clear();
        ar.add("soloeste");
        
        for(String a : ar){
            System.out.println(a);
        }
        
        Conectar conn = new Conectar();
        try {
            Statement st = (conn.getConnection()).createStatement();
            

            // Consulta de datos
            System.out.println("-Consultar registros:");
            ResultSet rs = st.executeQuery ("select * from usuario");
            while (rs.next()) {
                tabla.getItems().add(rs.getString (1));
                tabla.getItems().add(rs.getString (2));
                tabla.getItems().add(rs.getString (3));
            }


            conn.getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionPrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        panelC.getChildren().addAll(tabla);
        root.setCenter(tabla);
    }

    public BorderPane getRoot() {
        return root;
    }
    
    
}
