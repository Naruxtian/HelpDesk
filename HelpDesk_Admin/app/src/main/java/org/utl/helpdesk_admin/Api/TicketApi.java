package org.utl.helpdesk_admin.Api;

import org.utl.helpdesk_admin.Model.Ticket;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TicketApi {
    @GET("Ticket/getAll")
    public Call<List<Ticket>> getAll(@Query("id_employee") String id_employee,
                                     @Query("estatus") String estatus);

    @GET("Ticket/getAllSinEmp")
    public Call<List<Ticket>> getAllSinEmp(@Query("estatus") String estatus);

    @GET("Ticket/atender")
    public Call<String> atender(@Query("id_ticket") String id_ticket);
}
