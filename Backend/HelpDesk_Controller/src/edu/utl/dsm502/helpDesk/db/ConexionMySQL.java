/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.dsm502.helpDesk.db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Chris
 */
public class ConexionMySQL {
    //Esta variable es para guardar y gestionar la conexion 
    Connection conexion;

    /**
     * Metodo para generar una conexión con la BD
     * @return Un objeto de tipo Connection 
     * @throws Exception 
     */
    
    public Connection open() throws Exception{
        //Establecemos el driver que vamos a usar
        String driver = "com.mysql.jdbc.Driver";
        
        //Establecemos la ruta de conexion
        String url = "jdbc:mysql://127.0.0.1:3306/help_desk";
        
        //Establecer los valores de los permisos de acceso
        String user = "root";
        String password = "";
        
        //Dar de alta el uso del driver
        Class.forName(driver);
        
        //Abrimos la conexion
        conexion = DriverManager.getConnection(url, user, password);
        
        //Retornamos la conexion que se creó y abrió
        return conexion;
    }
    
    public void close(){
        try{
            if(conexion != null)
                conexion.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
