package edu.utl.dsm502.helpDesk.controller;

import edu.utl.dsm502.helpDesk.db.ConexionMySQL;
import edu.utl.dsm502.helpDesk.model.Usuario;
import edu.utl.dsm502.helpDesk.model.Empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Chris
 */
public class ControllerLogin {
    
    public Usuario login(String nombreU, String contrasenia) throws Exception{
        //Definir la consulta que se va a ejecutar
        String query = "SELECT * FROM users WHERE user_name=? AND password=?";
        //Generar el objeto de la conexion
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        //Abrir la conexion
        Connection conn = connMySQL.open();
        
        //Objeto para ejectuar la consulta
        PreparedStatement pstmt = conn.prepareStatement(query);
        
        //llenar los parametors de consulta
        pstmt.setString(1, nombreU);
        pstmt.setString(2, contrasenia);
        
        //objeto para recibir los datos de consulta
        ResultSet rs= pstmt.executeQuery();
        
        //Objeto de tipo empleado
        Usuario usu = null;
    
        if(rs.next()){
            usu = fill(rs);  
        }
        
        //Cerrar los objetos de uso para la BD
        rs.close();
        pstmt.close();
        connMySQL.close();
        
        //Se devuelve el objeto de tipo empleado
        return usu;
    }
    
    private Usuario fill(ResultSet rs) throws SQLException{
        Usuario u = new Usuario();
        Empleado e = new Empleado();
        
        e.setId_employee(rs.getInt("id_employee"));
        /*e.setNombre(rs.getString("first_name"));
        e.setApellido(rs.getString("last_name"));
        e.setEmail(rs.getString("email"));
        e.setTelefono(rs.getString("phone_number"));
        e.setDepartamento(rs.getString("department"));*/
        
        u.setId_user(rs.getInt("id_user"));
        u.setUser_name(rs.getString("user_name"));
        u.setPassword((rs.getString("password")));
        u.setEmployee(e);
               
        return u;
    }
}
