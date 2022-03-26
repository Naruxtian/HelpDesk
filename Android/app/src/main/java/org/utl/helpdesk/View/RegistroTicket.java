package org.utl.helpdesk.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import org.utl.helpdesk.Api.Service;
import org.utl.helpdesk.Api.TicketApi;
import org.utl.helpdesk.Model.Employee;
import org.utl.helpdesk.Model.Ticket;
import org.utl.helpdesk.Model.Users;
import org.utl.helpdesk.R;
import org.utl.helpdesk.SharedPref.SharedPrefManager;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistroTicket extends AppCompatActivity implements View.OnClickListener {

    RadioGroup rgDispositivo;
    RadioButton rbtDesktop;
    RadioButton rbtLaptop;
    RadioButton rbtTablet;
    RadioButton rbtSmartPhone;
    RadioButton rbtDisSalida;

    RadioGroup rgIncidente;
    RadioButton rbtFuncionamiento;
    RadioButton rbtError;
    RadioButton rbtEncendido;
    RadioButton rbtResponde;
    RadioButton rbtSiniestro;

    Button btnDate, btnTime;
    EditText txtDate, txtTime;
    private int dia, mes, anio, hora, minuto;

    EditText txtDescripcion;
    Button btnEnviar;

    Service service;
    Retrofit retrofit;

    String dispositivo;
    String incidente;
    int idEmpleado;
    boolean validar1 = false;
    boolean validar2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_ticket);

        rgDispositivo = findViewById(R.id.rgDispositivo);
        rbtDesktop = findViewById(R.id.rbtDesktop);
        rbtLaptop = findViewById(R.id.rbtLaptop);
        rbtTablet = findViewById(R.id.rbtTablet);
        rbtSmartPhone = findViewById(R.id.rbtSmartphone);
        rbtDisSalida = findViewById(R.id.rbtDisSalida);

        rgIncidente = findViewById(R.id.rgIncidente);
        rbtFuncionamiento = findViewById(R.id.rbtFuncionamiento);
        rbtError = findViewById(R.id.rbtError);
        rbtEncendido = findViewById(R.id.rbtEncendido);
        rbtResponde = findViewById(R.id.rbtResponde);
        rbtSiniestro = findViewById(R.id.rbtSiniestro);

        btnDate = findViewById(R.id.btnDate);
        txtDate = findViewById(R.id.txtDate);
        btnTime = findViewById(R.id.btnTime);
        txtTime = findViewById(R.id.txtTime);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        btnEnviar = findViewById(R.id.btnEnviar);

        service = new Service();
        retrofit = service.createService();

        /*Bundle empleado = getIntent().getExtras();
        idEmpleado = empleado.getInt("empleado");
        System.out.println(idEmpleado);*/

        Users usuario = SharedPrefManager.getInstance(getApplicationContext()).getUser();

        //Parte 1 de los date y time picker
        btnDate.setOnClickListener(this);
        btnTime.setOnClickListener(this);

        btnEnviar.setOnClickListener(view -> {
            validarDispositivo();
            validarIncidente();

            if (validar1 == false) {
                rbtDesktop.setError("Seleccione el tipo de dispositivo");
            }
            if (validar2 == false) {
                rbtFuncionamiento.setError("Seleccione el tipo de incidente");
            }
            if (detectarNull(txtDate) == true) {
                txtDate.setError("Escriba la fecha en que ocurrió incidente");
            }
            if (detectarNull(txtTime) == true) {
                txtTime.setError("Escriba la hora en que ocurrió el incidente");
            }
            if (detectarNull(txtDescripcion) == true) {
                txtDescripcion.setError("Describa el incidente");
            }
            if (validar1 == true && validar2 == true && detectarNull(txtDate) == false && detectarNull(txtTime) == false && detectarNull(txtDescripcion) == false) {
                Ticket ticket = new Ticket();
                Employee e = new Employee(usuario.getEmployee().getId_employee());

                ticket.setEmployee(e);
                ticket.setDevice(dispositivo);
                ticket.setType(incidente);
                ticket.setDate_of(txtDate.getText().toString());
                ticket.setTime_of(txtTime.getText().toString());
                ticket.setDescription_of(txtDescripcion.getText().toString());
                ticket.setEstatus(1);

                System.out.println(ticket.toString());

                enviarApi(ticket);

            } else {
                Toast.makeText(this, "Rellene los datos faltantes", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public String validarDispositivo() {
        if (rbtDesktop.isChecked()) {
            dispositivo = "Desktop";
            validar1 = true;
        } else if (rbtLaptop.isChecked()) {
            dispositivo = "Laptop";
            validar1 = true;
        } else if (rbtTablet.isChecked()) {
            dispositivo = "Tablet";
            validar1 = true;
        } else if (rbtSmartPhone.isChecked()) {
            dispositivo = "SmartPhone";
            validar1 = true;
        } else if (rbtDisSalida.isChecked()) {
            dispositivo = "Dispositivo de salida";
            validar1 = true;
        }
        return dispositivo;
    }

    public String validarIncidente() {
        if (rbtFuncionamiento.isChecked()) {
            incidente = "Dejó de funcionar";
            validar2 = true;
        } else if (rbtError.isChecked()) {
            incidente = "Aparece un error";
            validar2 = true;
        } else if (rbtEncendido.isChecked()) {
            incidente = "No enciende";
            validar2 = true;
        } else if (rbtResponde.isChecked()) {
            incidente = "No responde";
            validar2 = true;
        } else if (rbtSiniestro.isChecked()) {
            incidente = "Sufrió un siniestro";
            validar2 = true;
        }
        return incidente;
    }

    public boolean detectarNull(EditText txt) {
        if (txt.getText().toString().trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void enviarApi(Ticket t) {
        TicketApi tickApi = retrofit.create(TicketApi.class);
        Call<Ticket> call = tickApi.enviar2(t.getEmployee().getId_employee(),
                t.getDevice(),
                t.getType(),
                t.getDate_of(),
                t.getTime_of(),
                t.getDescription_of(),
                t.getEstatus());
        call.enqueue(new Callback<Ticket>() {
            @Override
            public void onResponse(Call<Ticket> call, Response<Ticket> response) {
                try {
                    if (response.isSuccessful() && response.body().getId_ticket() != 0) {
                        Toast.makeText(RegistroTicket.this, "Envio correcto", Toast.LENGTH_SHORT).show();
                        System.out.println(t.toString());

                        Intent enviar = new Intent(RegistroTicket.this, DatosTicket.class);
                        //enviar.putExtra("empleado",idEmpleado);
                        enviar.putExtra("dispositivo", dispositivo);
                        enviar.putExtra("incidente", incidente);
                        enviar.putExtra("fecha", txtDate.getText().toString());
                        enviar.putExtra("hora", txtTime.getText().toString());
                        enviar.putExtra("descripcion", txtDescripcion.getText().toString());
                        startActivity(enviar);
                    } else {
                        Toast.makeText(RegistroTicket.this, "Envio incorrecto", Toast.LENGTH_SHORT).show();
                        System.out.println(t.toString());
                        System.out.println(response.code());
                        System.out.println(response.body());
                    }
                } catch (Exception e) {
                    Toast.makeText(RegistroTicket.this, "Error en la aplicación", Toast.LENGTH_SHORT).show();
                    System.out.println(e.toString());
                    System.out.println(response.code());
                    System.out.println(response.body());
                }
            }

            @Override
            public void onFailure(Call<Ticket> call, Throwable t) {
                Toast.makeText(RegistroTicket.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                System.out.println(t.toString());
            }
        });
    }

    //Parte 2 de los date y time picker
    @Override
    public void onClick(View view) {
        if (view == btnDate) {
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            anio = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    txtDate.setText(year + "-" + (month + 1) + "-" + day);
                }
            }, anio, mes, dia);

            datePickerDialog.show();
        }

        if (view == btnTime) {
            final Calendar c = Calendar.getInstance();
            hora = c.get(Calendar.HOUR_OF_DAY);
            minuto = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hora, int minuto) {
                    txtTime.setText(hora + ":" + minuto);
                }
            }, hora, minuto, true);
            timePickerDialog.show();
        }
    }


}