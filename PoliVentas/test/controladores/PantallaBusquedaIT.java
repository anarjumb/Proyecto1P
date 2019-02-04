/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import junit.framework.TestCase;

import static org.junit.Assert.*;
import controladores.PantallaBusqueda;


/**
 *
 * @author Mi compu
 */
public class PantallaBusquedaIT extends TestCase {
    
    
        
    public void testSimple() {
        assertTrue( !PantallaBusqueda.tiene3caracteres2(""));
    }
    
    public void testBueno(){
        assertTrue(PantallaBusqueda.tiene3caracteres2("aaaaa"));
        
    }
    
    public void testNumeros(){
        assertTrue(PantallaBusqueda.tiene3caracteres2("1234"));
        
    }
    
    
    
}
