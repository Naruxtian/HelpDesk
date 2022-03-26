/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utl.dsm502.helpDesk.db;

import edu.utl.dsm502.helpDesk.controller.ControllerLogin;
import edu.utl.dsm502.helpDesk.controller.ControllerTicket;
import edu.utl.dsm502.helpDesk.model.Empleado;
import edu.utl.dsm502.helpDesk.model.Ticket;
import edu.utl.dsm502.helpDesk.model.Usuario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chris
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
 //       probarConexion();
//        probarEnviarTicket();
//       probarGetAllTicket();
 //       probarLogin();
    }
    
    public static void probarConexion(){
        ConexionMySQL objCon = new ConexionMySQL(); 
        try{
            
           objCon.open();
           System.out.println(objCon.toString());
           objCon.close();
        }
        catch(Exception ex){
            Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void probarEnviarTicket(){
        try {
            Empleado e = new Empleado(1,"admin","admin","admin@admin.com","","Administracion");
            Ticket t = new Ticket(e,"Laptop","Dejó de funcionar","2022-02-04","22:30","Se apaga la pila",1);
            
            ControllerTicket ct = new ControllerTicket();
            
            int idG = ct.enviarTicket(t);
            System.out.println("El id del ticket es "+idG);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void probarGetAllTicket(){
        try {
            ControllerTicket ct = new ControllerTicket();
            List<Ticket> tickets = ct.getAll(1,1);
            
            for (int i = 0; i<tickets.size();i++){
                System.out.println(tickets.get(i).toString());
            }
        } catch (Exception ex) {
            Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
    
    public static void probarLogin(){
        try {
            ControllerLogin objCL = new ControllerLogin();
            Usuario u = objCL.login("admin", "1234");
            
            System.out.println(u.getUser_name());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
