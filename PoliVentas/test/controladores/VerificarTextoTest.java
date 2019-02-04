/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import junit.framework.TestCase;

/**
 *
 * @author Mi compu
 */
public class VerificarTextoTest extends TestCase {
    
    
        
    public void testSimple() {
        ArrayList lista = new ArrayList();
        lista.add("aa");
        lista.add("aa");
        lista.add("aa");          
        
        assertEquals(lista ,PantallaBusqueda.verificarTexto("aa aa aa"));
    }
    
    public void testmalo(){
        ArrayList lista = new ArrayList();
                 
        
        assertEquals(null ,PantallaBusqueda.verificarTexto(""));
        
        
    } 
    public void testUnapalabra() {
        ArrayList lista = new ArrayList();
        lista.add("aa");
        
        assertEquals(null ,PantallaBusqueda.verificarTexto("aa"));
    }
    
    
    
    
    
    
}
