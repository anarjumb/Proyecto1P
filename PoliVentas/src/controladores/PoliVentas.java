
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author adan
 */
public class PoliVentas extends Application{
    private static String rol;

    public static String getRol() {
        return rol;
    }

    public static void setRol(String rol) {
        PoliVentas.rol = rol;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
        
        
    
    }

    @Override
    public void start(Stage primaryStage){
        Scene scene = new Scene(new Inicio().getRoot(), Constantes.ANCHO, Constantes.ALTO);
        
        
        primaryStage.setTitle("PoliVentas");
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
