package org.utl.helpdesk_admin.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.utl.helpdesk_admin.Api.Service;
import org.utl.helpdesk_admin.Api.TicketApi;
import org.utl.helpdesk_admin.Model.Employee;
import org.utl.helpdesk_admin.Model.Ticket;
import org.utl.helpdesk_admin.R;
import org.utl.helpdesk_admin.databinding.ActivityMainBinding;
import org.utl.helpdesk_admin.databinding.FragmentHomeBinding;
import org.utl.helpdesk_admin.databinding.FragmentTicketsBinding;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class TicketsFragment extends Fragment {

    private FragmentTicketsBinding binding;
    TicketsAdapter ticketsAdapter;
    List<Ticket> listaTickets;

    Service service;
    Retrofit retrofit;

    int idSelected;

    public TicketsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTicketsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        service = new Service();
        retrofit = service.createService();

        getAll(Integer.toString(1));
        //loadTickets();
        return view;
    }


    private void loadTickets() {
        ticketsAdapter = new TicketsAdapter();
        binding.RecyclerViewTickets.setAdapter(ticketsAdapter);
        listaTickets = new ArrayList<>();

        Employee e = new Employee(1);

        listaTickets.add(new Ticket(1, e, "Laptop", "No enciende", "2022-02-10", "12:00:00", "Se apagó", 1));
        listaTickets.add(new Ticket(2, e, "SmartPhone", "No enciende", "2022-02-10", "12:00:00", "Se mojó", 1));
        listaTickets.add(new Ticket(3, e, "Impresora", "No funciona", "2022-02-10", "12:00:00", "Mancha el papel", 1));
        listaTickets.add(new Ticket(4, e, "Laptop", "No enciende", "2022-02-10", "12:00:00", "Se apagó", 1));
        listaTickets.add(new Ticket(5, e, "SmartPhone", "No enciende", "2022-02-10", "12:00:00", "Se mojó", 1));
        listaTickets.add(new Ticket(6, e, "Impresora", "No funciona", "2022-02-10", "12:00:00", "Mancha el papel", 1));
        listaTickets.add(new Ticket(7, e, "Laptop", "No enciende", "2022-02-10", "12:00:00", "Se apagó", 1));
        listaTickets.add(new Ticket(8, e, "SmartPhone", "No enciende", "2022-02-10", "12:00:00", "Se mojó", 1));
        listaTickets.add(new Ticket(9, e, "Impresora", "No funciona", "2022-02-10", "12:00:00", "Mancha el papel", 1));

        binding.RecyclerViewTickets.setHasFixedSize(true);
        ticketsAdapter.updateTicketsList(listaTickets);

        ticketsAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idSelected = listaTickets.get(binding.RecyclerViewTickets.getChildAdapterPosition(view)).getId_ticket();
                Toast.makeText(getActivity(), "Seleccionaste el ticket: " + idSelected, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getAll(String estatus) {
        TicketApi ticketApi = retrofit.create(TicketApi.class);
        Call<List<Ticket>> call = ticketApi.getAllSinEmp(estatus);

        call.enqueue(new Callback<List<Ticket>>() {
            @Override
            public void onResponse(Call<List<Ticket>> call, Response<List<Ticket>> response) {
                try {
                    if (response.code() == 200 & response.body().size() > 0) {
                        System.out.println("Sí llega");
                        System.out.println(response.code());
                        //System.out.println(response.body().toString());
                        ticketsAdapter = new TicketsAdapter();
                        binding.RecyclerViewTickets.setAdapter(ticketsAdapter);
                        listaTickets = new ArrayList<>();
                        listaTickets = response.body();
                        binding.RecyclerViewTickets.setHasFixedSize(true);
                        ticketsAdapter.updateTicketsList(listaTickets);

                        ticketsAdapter.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                idSelected = listaTickets.get(binding.RecyclerViewTickets.getChildAdapterPosition(view)).getId_ticket();

                                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("¿Quieres atender el ticket " + idSelected + "?")
                                        .setContentText("Se cambiará el estatus a Atendido")
                                        .setConfirmText("Si, atender!")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {

                                                Call<String> call1 = ticketApi.atender(Integer.toString(idSelected));

                                                call1.enqueue(new Callback<String>() {
                                                    @Override
                                                    public void onResponse(Call<String> call, Response<String> response) {
                                                        new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                                                .setTitleText("Realizado!")
                                                                .setContentText("Se atendió el ticket!")
                                                                .show();
                                                        sDialog.dismissWithAnimation();
                                                        System.out.println(response.body());
                                                    }

                                                    @Override
                                                    public void onFailure(Call<String> call, Throwable t) {
                                                        /*new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                                                .setTitleText("Error")
                                                                .setContentText("Ocurrió un error de conexión")
                                                                .show();
                                                        sDialog.dismissWithAnimation();*/
                                                        new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                                                .setTitleText("Realizado!")
                                                                .setContentText("Se atendió el ticket!")
                                                                .show();
                                                        sDialog.dismissWithAnimation();
                                                        System.out.println(response.body());
                                                        getAll(Integer.toString(1));
                                                    }
                                                });

                                            }
                                        })
                                        .setCancelButton("Cancelar", new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                sDialog.dismissWithAnimation();
                                            }
                                        })
                                        .show();
                            }
                        });

                    } else {
                        Toast.makeText(getActivity(), "La lista está vacia", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Toast.makeText(getActivity(), "Error en la aplicación", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Ticket>> call, Throwable t) {
                System.out.println("No llega");
                t.printStackTrace();
                Toast.makeText(getActivity(), "Error de conexión", Toast.LENGTH_LONG).show();
            }
        });
    }

}