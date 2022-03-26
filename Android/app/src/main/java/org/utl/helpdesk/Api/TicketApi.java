package org.utl.helpdesk.Api;

import org.utl.helpdesk.Model.Ticket;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TicketApi {
//    @GET("Ticket/enviar")
//    public Call<Ticket> enviar(@Query("ticket") String ticket);

    @GET("Ticket/enviar2")
    public Call<Ticket> enviar2(@Query("employee") int employee,
                                @Query("device") String device,
                                @Query("type") String type,
                                @Query("date_of") String date_of,
                                @Query("time_of") String time_of,
                                @Query("description_of") String description_of,
                                @Query("estatus") int estatus);

    @GET("Ticket/getAll")
    public Call<List<Ticket>> getAll(@Query("id_employee") String id_employee,
                                     @Query("estatus") String estatus);

    @GET("Ticket/cancelar")
    public Call<String> cancelar(@Query("id_ticket") String id_ticket);

}