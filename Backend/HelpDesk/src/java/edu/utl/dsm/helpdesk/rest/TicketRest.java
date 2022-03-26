package edu.utl.dsm.helpdesk.rest;

import com.google.gson.Gson;
import edu.utl.dsm502.helpDesk.controller.ControllerTicket;
import edu.utl.dsm502.helpDesk.model.Ticket;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**
 *
 * @author Chris
 */
@Path("Ticket")
public class TicketRest extends Application {
    
    @Path("enviar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response enviarTicket(@QueryParam("ticket") String ticket){
        String out="";
        
        try {
            Gson objGS = new Gson();
            Ticket tick = objGS.fromJson(ticket, Ticket.class);
            
            ControllerTicket ct = new ControllerTicket();
            int idG = ct.enviarTicket(tick);
            out = "{\"idTicket\":"+idG+"}";
        } catch (Exception e) {
            e.printStackTrace();
            out ="{\"error\":\"Hubo un fallo en la generación del ticket, vuelva a intentarlo, o llama al administrador del sistema\"}";
        }        
        return Response.status(Response.Status.OK).entity(out).build();
    }

    
    @Path("enviar2")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response enviarTicket2(@QueryParam("employee") int employee, 
                                 @QueryParam("device") String device,
                                 @QueryParam("type") String type, 
                                 @QueryParam("date_of") String date_of,
                                 @QueryParam("time_of") String time_of, 
                                 @QueryParam("description_of") String description_of,
                                 @QueryParam("estatus") int estatus){
        String out="";
        Ticket t = new Ticket();
        try {
            ControllerTicket objCT = new ControllerTicket();
            t = objCT.enviarTicket2(employee, device, type, date_of, time_of, description_of, estatus);
            
            Gson objGS = new Gson();
            out = objGS.toJson(t);
            
        } catch (Exception e) {
            e.printStackTrace();
            out ="{\"error\":\"Hubo un fallo en la generación del ticket, vuelva a intentarlo, o llama al administrador del sistema\"}";
        }        
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
        
    @Path("getAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTickets(@QueryParam("id_employee") String id_employee, @QueryParam("estatus") String estatus){
        String out="";
        
        try {
            ControllerTicket ct = new ControllerTicket();
            List<Ticket> tickets = new ArrayList<Ticket>();
            
            tickets = ct.getAll(Integer.parseInt(id_employee), Integer.parseInt(estatus));
            
             Gson obGS = new Gson();
             out = obGS.toJson(tickets);
        } catch (Exception e) {
            e.printStackTrace();
            out ="{\"error\":\"No se pudo realizar la extracción de datos\"}"; 
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("getAllSinEmp")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTicketsSinEmp(@QueryParam("estatus") String estatus){
        String out="";
        
        try {
            ControllerTicket ct = new ControllerTicket();
            List<Ticket> tickets = new ArrayList<Ticket>();
            
            tickets = ct.getAllSinEmp(Integer.parseInt(estatus));
            
             Gson obGS = new Gson();
             out = obGS.toJson(tickets);
        } catch (Exception e) {
            e.printStackTrace();
            out ="{\"error\":\"No se pudo realizar la extracción de datos\"}"; 
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("cancelar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response cancelarTicket(@QueryParam("id_ticket") String ticket){
       String out="";
       
        try {
            ControllerTicket ct = new ControllerTicket();
            
            ct.cancelarTicket(Integer.parseInt(ticket));
            
            out="{\"result\":\"Se desactivó el ticket exitosamente\"}";
        } catch (Exception e) {
            e.printStackTrace();
            out ="{\"error\":\"Se produjo un error en la desactivación del \"}"; 
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("atender")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response atenderTicket(@QueryParam("id_ticket") String ticket){
       String out="";
       
        try {
            ControllerTicket ct = new ControllerTicket();
            
            ct.atenderTicket(Integer.parseInt(ticket));
            
            out="{\"result\":\"Se atendió el ticket exitosamente\"}";
        } catch (Exception e) {
            e.printStackTrace();
            out ="{\"error\":\"Se produjo un error en la desactivación del \"}"; 
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
}
