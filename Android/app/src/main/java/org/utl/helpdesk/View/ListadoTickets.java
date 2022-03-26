package org.utl.helpdesk.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import org.utl.helpdesk.Api.LoginApi;
import org.utl.helpdesk.Api.Service;
import org.utl.helpdesk.Api.TicketApi;
import org.utl.helpdesk.Model.Employee;
import org.utl.helpdesk.Model.Ticket;
import org.utl.helpdesk.Model.Users;
import org.utl.helpdesk.R;
import org.utl.helpdesk.SharedPref.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListadoTickets extends AppCompatActivity {

    RecyclerView recyclerViewTickets;
    List<Ticket> listaTickets;

    Switch swtEstado;

    int idEmpleado;
    int idSelected;

    Service service;
    Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_tickets);

        recyclerViewTickets = findViewById(R.id.RecyclerViewTickets);
        swtEstado = findViewById(R.id.swtEstado);

        service = new Service();
        retrofit = service.createService();

        /* Intent, viejo metodo
        Bundle empleado = getIntent().getExtras();
        idEmpleado = empleado.getInt("empleado");
        System.out.println(idEmpleado);
        */

        Users usuario = SharedPrefManager.getInstance(getApplicationContext()).getUser();

        listaTickets = new ArrayList<>();

        //loadTickets();

        getAll(Integer.toString(usuario.getEmployee().getId_employee()), Integer.toString(1));

        swtEstado.setChecked(true);
        swtEstado.setOnClickListener(view -> {
            if (swtEstado.isChecked()) {

                for (int i = 0; i > listaTickets.size(); i++) {
                    listaTickets.remove(i);
                }
                recyclerViewTickets.removeAllViewsInLayout();
                swtEstado.setText("Tickets activos");
                getAll(Integer.toString(usuario.getEmployee().getId_employee()), Integer.toString(1));
            } else {
                for (int i = 0; i > listaTickets.size(); i++) {
                    listaTickets.remove(i);
                }
                recyclerViewTickets.removeAllViewsInLayout();
                swtEstado.setText("Tickets inactivos");
                getAll(Integer.toString(usuario.getEmployee().getId_employee()), Integer.toString(0));
            }
        });

    }

    private void loadTickets() {
        Employee e = new Employee(1);

        listaTickets.add(new Ticket(1, e, "Laptop", "No enciende", "2022-02-10", "12:00:00", "Se apagó", 1));
        listaTickets.add(new Ticket(2, e, "SmartPhone", "No enciende", "2022-02-10", "12:00:00", "Se mojó", 1));
        listaTickets.add(new Ticket(3, e, "Impresora", "No funciona", "2022-02-10", "12:00:00", "Mancha el papel", 1));

        TicketsAdapter ticketsAdapter = new TicketsAdapter(listaTickets, ListadoTickets.this);

        recyclerViewTickets.setHasFixedSize(true);
        recyclerViewTickets.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTickets.setAdapter(ticketsAdapter);
    }

    private void getAll(String id_empleado, String estatus) {
        TicketApi ticketApi = retrofit.create(TicketApi.class);
        Call<List<Ticket>> call = ticketApi.getAll(id_empleado, estatus);

        call.enqueue(new Callback<List<Ticket>>() {
            @Override
            public void onResponse(Call<List<Ticket>> call, Response<List<Ticket>> response) {
                try {
                    if (response.code() == 200 & response.body().size() > 0) {

                        listaTickets = response.body();

                        TicketsAdapter ticketsAdapter = new TicketsAdapter(listaTickets, ListadoTickets.this);

                        ticketsAdapter.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                idSelected = listaTickets.get(recyclerViewTickets.getChildAdapterPosition(view)).getId_ticket();
                                int estatusSelected = listaTickets.get(recyclerViewTickets.getChildAdapterPosition(view)).getEstatus();
                                //Toast.makeText(ListadoTickets.this, "Seleccionaste el ticket: " + idSelected, Toast.LENGTH_SHORT).show();

                                if (estatusSelected == 1){
                                new SweetAlertDialog(ListadoTickets.this, SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("¿Quieres cancelar el ticket " + idSelected + "?")
                                        .setContentText("Se cambiará el estatus a Cancelado")
                                        .setConfirmText("Si, Cancelar!")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {

                                                Call<String> call1 = ticketApi.cancelar(Integer.toString(idSelected));

                                                call1.enqueue(new Callback<String>() {
                                                    @Override
                                                    public void onResponse(Call<String> call, Response<String> response) {

                                                        new SweetAlertDialog(ListadoTickets.this, SweetAlertDialog.SUCCESS_TYPE)
                                                                .setTitleText("Realizado!")
                                                                .setContentText("Se cancelo el ticket!")
                                                                .show();
                                                        sDialog.dismissWithAnimation();
                                                        System.out.println(response.body());
                                                    }

                                                    @Override
                                                    public void onFailure(Call<String> call, Throwable t) {
                                                        /*new SweetAlertDialog(ListadoTickets.this, SweetAlertDialog.ERROR_TYPE)
                                                                .setTitleText("Error")
                                                                .setContentText("Ocurrió un error de conexión")
                                                                .show();
                                                        sDialog.dismissWithAnimation();*/
                                                        new SweetAlertDialog(ListadoTickets.this, SweetAlertDialog.SUCCESS_TYPE)
                                                                .setTitleText("Realizado!")
                                                                .setContentText("Se canceló el ticket!")
                                                                .show();
                                                        sDialog.dismissWithAnimation();
                                                        System.out.println(t.toString());
                                                        getAll(Integer.toString(idEmpleado), Integer.toString(1));
                                                    }
                                                });

                                            }
                                        })
                                        .setCancelButton("Regresar", new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                sDialog.dismissWithAnimation();
                                            }
                                        })
                                        .show();}
                                else{
                                    new SweetAlertDialog(ListadoTickets.this, SweetAlertDialog.WARNING_TYPE)
                                            .setTitleText("")
                                            .setContentText("El ticket ya está cancelado")
                                            .show();
                                }
                            }
                        });

                        recyclerViewTickets.setHasFixedSize(true);
                        recyclerViewTickets.setLayoutManager(new LinearLayoutManager(ListadoTickets.this));
                        recyclerViewTickets.setAdapter(ticketsAdapter);
                    } else {
                        Toast.makeText(ListadoTickets.this, "La lista está vacia", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Toast.makeText(ListadoTickets.this, "Error en la aplicación", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Ticket>> call, Throwable t) {
                Toast.makeText(ListadoTickets.this, "Error de conexión", Toast.LENGTH_LONG).show();
            }
        });
    }


}