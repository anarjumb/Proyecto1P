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
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author adan
 */
public class PoliVentas extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
        
        
    
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new AdmUsuarios().getRoot(), Constantes.ANCHO, Constantes.ALTO);
        
        
        //scene.onKeyPressedProperty().bind(new PaneOrganizer(ruta).getRoot().onKeyPressedProperty());
        
        primaryStage.setTitle("Fighter!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void cambiarVentana(Pane root, Pane root2 ){
        Scene scn = root.getScene();
         Stage stage = (Stage) scn.getWindow();
         Scene scn2 = new Scene( root2,Constantes.ANCHO, Constantes.ALTO );
         
         stage.setScene(scn2);
    }
    
}
