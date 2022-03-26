
package edu.utl.dsm.helpdesk.rest;

import com.google.gson.Gson;
import edu.utl.dsm502.helpDesk.controller.ControllerLogin;
import edu.utl.dsm502.helpDesk.model.Usuario;
import javax.ws.rs.DefaultValue;
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
@Path("Log")
public class LoginRest extends Application {
    
    @Path("in")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response in(@QueryParam("user_name") @DefaultValue("0") String user_name, @QueryParam("password") @DefaultValue("0") String password){
        String out="";
        try {
            ControllerLogin objCL = new ControllerLogin();
            Usuario usu = objCL.login(user_name, password);
            
            Gson objGS = new Gson();
            out = objGS.toJson(usu);
            
        } catch (Exception e) {
            e.printStackTrace();
            out= "{\"error\":\"Hubo en fallo la verificación de datos\"}";
        }        
        return Response.status(Response.Status.OK).entity(out).build();
    }
}
