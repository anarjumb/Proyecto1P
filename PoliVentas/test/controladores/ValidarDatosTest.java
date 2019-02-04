/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import junit.framework.TestCase;


/**
 *
 * @author Mi compu
 */
public class ValidarDatosTest extends TestCase {
    
    
    public void testSimple(){
        
        assertTrue(Registro.ValidarDatos("a"));
        
    }    
    
    public void testMalo(){
        
        assertTrue(!Registro.ValidarDatos("z"));
        
    }
    public void testVacio(){
        
        assertTrue(!Registro.ValidarDatos(""));
        
    }
    public void testNulo(){
        
        assertTrue(!Registro.ValidarDatos(null));
        
    }
    
    
}
