/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionprueba;

import java.sql.Connection;

/**
 *
 * @author adan
 */
public class Probar {
    Conectar connect;
    
    public void conectarBD(){
        connect = new Conectar();
        Connection reg =  connect.getConnection();
    }
    public void desconectarBD(){
        connect.desconectar();
    }
}
