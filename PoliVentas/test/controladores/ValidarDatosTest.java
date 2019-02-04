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
        
        assertTrue(Registro.validarDatos("a"));
        
    }    
    
    public void testMalo(){
        
        assertTrue(!Registro.validarDatos("z"));
        
    }
    public void testVacio(){
        
        assertTrue(!Registro.validarDatos(""));
        
    }
    public void testNulo(){
        
        assertTrue(!Registro.validarDatos(null));
        
    }
    
    
}
