/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.dsm502.helpDesk.controller;

import com.mysql.jdbc.Statement;
import edu.utl.dsm502.helpDesk.db.ConexionMySQL;
import edu.utl.dsm502.helpDesk.model.Empleado;
import edu.utl.dsm502.helpDesk.model.Ticket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Chris
 */
public class ControllerTicket {
    
    /**
     * 
     * @param t Ticket que se enviará
     * @return el id del ticket enviado
     * @throws Exception 
     */
    public int enviarTicket(Ticket t) throws Exception{
        //Definir la sentencia SQL que realiza la inserción de una sucursal en la BD
        String query ="INSERT INTO ticket(id_employee, device, type, date_of, time_of, description_of, estatus)"
                      +"VALUES (?,?,?,?,?,?,?)";
        
        //Se declara la variable sobre la que se almacena el id generado
        int idGenerado =-1;
        
        //Se genera un objeto de la conexion y la abrimos
        ConexionMySQL connMySQL = new ConexionMySQL();
        Connection conn = connMySQL.open();
        
        /*Generamos un objeto que va a llevar la consulta a la BD y que permita
        devolver el ID generado*/
        PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        
        //Declaramos un objeto que va a guardar el resultado devuelto de la consulta
        ResultSet rs = null;
        
        //Terminar de armar la sentencia / Cargar el objeto pstmt
        pstmt.setInt(1, t.getEmployee().getId_employee());
        pstmt.setString(2, t.getDevice());
        pstmt.setString(3, t.getType());
        pstmt.setString(4, t.getDate_of());
        pstmt.setString(5, t.getTime_of());
        pstmt.setString(6, t.getDescription_of());
        pstmt.setInt(7, t.getEstatus());
        
        //Ejecutamos la consulta
        pstmt.executeUpdate();
        
        //Solicitamos el PreparedStatment el valor que se generó (id)
        rs = pstmt.getGeneratedKeys();
        
        if(rs.next()){
            idGenerado = rs.getInt(1);
            t.setId_ticket(idGenerado);
       }
        
       //Cerramos los objetos de conexón que se abrieron
       rs.close();
       pstmt.close();
       connMySQL.close();
       
       //Devolvemos el ID
       return idGenerado;
        
    }
    
    public Ticket enviarTicket2(int empleado, String device, String type, String date, String time, String description, int estatus) throws Exception{
        String query ="INSERT INTO ticket(id_employee, device, type, date_of, time_of, description_of, estatus)"
                      +"VALUES (?,?,?,?,?,?,?)";
         
        int idGenerado =-1;
        
        ConexionMySQL connMySQL = new ConexionMySQL();
        Connection conn = connMySQL.open();
        
        PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        
        ResultSet rs = null;
        
        pstmt.setInt(1,empleado);
        pstmt.setString(2,device);
        pstmt.setString(3,type);
        pstmt.setString(4,date);
        pstmt.setString(5,time);
        pstmt.setString(6,description);
        pstmt.setInt(7,estatus);
        
        pstmt.executeUpdate();
        
        rs = pstmt.getGeneratedKeys();
        
        Ticket t = new Ticket();
        Empleado e = new Empleado(empleado);
        if(rs.next()){
            idGenerado = rs.getInt(1);
            
            t.setId_ticket(idGenerado);
            t.setEmployee(e);
            t.setDate_of(date);
            t.setDescription_of(description);
            t.setDevice(device);
            t.setTime_of(time);
            t.setType(type);
            t.setEstatus(estatus);
       }
        
       rs.close();
       pstmt.close();
       connMySQL.close();
       
       return t;    
    }
    
    public List<Ticket> getAll(int id_employee, int estatus) throws Exception{
        //Definir la consulta SQL
        String query = "SELECT * FROM ticket WHERE id_employee ="+id_employee+" AND estatus ="+estatus+";";
        
        //Generar la lista de productos que se va a obtener
        List<Ticket> tickets = new ArrayList<Ticket>();
                
        //Crear un objeto de la conexion a la BD y abrirla
        ConexionMySQL connMySQL = new ConexionMySQL();
        Connection conn = connMySQL.open();
        
        //Se genera un objeto para poder enviar y ejecutar la sentencia
        PreparedStatement pstmt = conn.prepareStatement(query);
        
        //Se ejectua la sentencia y recibimos el resultado de la consulta
        ResultSet rs = pstmt.executeQuery();
        
        //Recorrer el RS
        while(rs.next()){
            //Generar una variable temporal para crear nuevas instancias de Producto
            Ticket t = new Ticket();
            Empleado e = new Empleado();
            //Llenamos los atributos del objeto con los datos del RS
            t.setId_ticket(rs.getInt("id_ticket"));
            e.setId_employee(rs.getInt("id_employee"));
            t.setEmployee(e);
            t.setDevice(rs.getString("device"));
            t.setType(rs.getString("type"));
            t.setDate_of(rs.getString("date_of"));
            t.setTime_of(rs.getString("time_of"));
            t.setDescription_of(rs.getString("description_of"));
            t.setEstatus(rs.getInt("estatus"));
            
            //Se agrega esa sucursal a la lista de sucursales
            tickets.add(t); 
        }
        //Cerrar los objetos de conexion
        rs.close();
        connMySQL.close();
        
        //Devolver la lista dinamica de productos
        return tickets;
    }
    
    public List<Ticket> getAllSinEmp(int estatus) throws Exception{
        //Definir la consulta SQL
        String query = "SELECT * FROM ticket WHERE estatus ="+estatus+";";
        
        //Generar la lista de productos que se va a obtener
        List<Ticket> tickets = new ArrayList<Ticket>();
                
        //Crear un objeto de la conexion a la BD y abrirla
        ConexionMySQL connMySQL = new ConexionMySQL();
        Connection conn = connMySQL.open();
        
        //Se genera un objeto para poder enviar y ejecutar la sentencia
        PreparedStatement pstmt = conn.prepareStatement(query);
        
        //Se ejectua la sentencia y recibimos el resultado de la consulta
        ResultSet rs = pstmt.executeQuery();
        
        //Recorrer el RS
        while(rs.next()){
            //Generar una variable temporal para crear nuevas instancias de Producto
            Ticket t = new Ticket();
            Empleado e = new Empleado();
            //Llenamos los atributos del objeto con los datos del RS
            t.setId_ticket(rs.getInt("id_ticket"));
            e.setId_employee(rs.getInt("id_employee"));
            t.setEmployee(e);
            t.setDevice(rs.getString("device"));
            t.setType(rs.getString("type"));
            t.setDate_of(rs.getString("date_of"));
            t.setTime_of(rs.getString("time_of"));
            t.setDescription_of(rs.getString("description_of"));
            t.setEstatus(rs.getInt("estatus"));
            
            //Se agrega esa sucursal a la lista de sucursales
            tickets.add(t); 
        }
        //Cerrar los objetos de conexion
        rs.close();
        connMySQL.close();
        
        //Devolver la lista dinamica de productos
        return tickets;
    }
    
    public boolean cancelarTicket(int idTicket) throws Exception{
        //Generar la consulta
        String query ="UPDATE ticket SET estatus = 0 WHERE id_ticket = "+idTicket+";";
        
        //Generar la variable boleana
        boolean r = false;
        
        //Generar los objetos de conexion y abrirlos
        ConexionMySQL objConMySQL = new ConexionMySQL();
        Connection conn = objConMySQL.open();
        
        //Se genera el objeto que lleva la consulta
        PreparedStatement pstmt = conn.prepareStatement(query);
        
        //Se genera un objeto para recibir el resultado de la consulta
        int res = pstmt.executeUpdate();
        
        if(res == 1)
            r = true;
        
        //Cerrar los objetos de conexion
        pstmt.close();
        conn.close();
        
        return r;
    }
    
    public boolean atenderTicket(int idTicket) throws Exception{
        //Generar la consulta
        String query ="UPDATE ticket SET estatus = 2 WHERE id_ticket = "+idTicket+";";
        
        //Generar la variable boleana
        boolean r = false;
        
        //Generar los objetos de conexion y abrirlos
        ConexionMySQL objConMySQL = new ConexionMySQL();
        Connection conn = objConMySQL.open();
        
        //Se genera el objeto que lleva la consulta
        PreparedStatement pstmt = conn.prepareStatement(query);
        
        //Se genera un objeto para recibir el resultado de la consulta
        int res = pstmt.executeUpdate();
        
        if(res == 1)
            r = true;
        
        //Cerrar los objetos de conexion
        pstmt.close();
        conn.close();
        
        return r;
    }
    
}
