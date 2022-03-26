package org.utl.helpdesk.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.utl.helpdesk.Model.Users;
import org.utl.helpdesk.R;
import org.utl.helpdesk.SharedPref.SharedPrefManager;

public class DatosTicket extends AppCompatActivity {
    TextView txtDispositivo;
    TextView txtIncidente;
    TextView txtFecha;
    TextView txtHora;
    EditText txtDescripcion;
    Button btnSalir;
    Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_ticket);

        txtDispositivo = findViewById(R.id.txtDispositivoF);
        txtIncidente = findViewById(R.id.txtIndidenteF);
        txtFecha = findViewById(R.id.txtFechaF);
        txtHora = findViewById(R.id.txtHoraF);
        txtDescripcion = findViewById(R.id.txtDescripcionF);
        btnSalir = findViewById(R.id.btnSalir);
        btnVolver = findViewById(R.id.btnVolver);

        //Recibir los datos del activity anterior
        Bundle datos = getIntent().getExtras();

        //idEmpleado = datos.getInt("empleado");
        txtDispositivo.setText(datos.getString("dispositivo"));
        txtIncidente.setText(datos.getString("incidente"));
        txtFecha.setText(datos.getString("fecha"));
        txtHora.setText(datos.getString("hora"));
        txtDescripcion.setText(datos.getString("descripcion"));

        btnSalir.setOnClickListener(view -> {
            Intent salir = new Intent(this, MainActivity.class);
            startActivity(salir);
        });

        btnVolver.setOnClickListener(view -> {
            Intent volver = new Intent(this, MenuPrincipal.class);
            startActivity(volver);
        });

    }
}