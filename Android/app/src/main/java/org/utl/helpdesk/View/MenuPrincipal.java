package org.utl.helpdesk.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import org.utl.helpdesk.R;

public class MenuPrincipal extends AppCompatActivity {

    Button btnFormulario;
    Button btnListado;

    int idEmpleado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        btnFormulario = findViewById(R.id.btnFormulario);
        btnListado = findViewById(R.id.btnListado);

        //Bundle empleado = getIntent().getExtras();
        //idEmpleado=empleado.getInt("empleado");

        btnFormulario.setOnClickListener(view -> {
            Intent registro = new Intent(MenuPrincipal.this, RegistroTicket.class);
            //registro.putExtra("empleado",idEmpleado);
            startActivity(registro);
        });
        btnListado.setOnClickListener(view -> {
            Intent listado = new Intent(MenuPrincipal.this, ListadoTickets.class);
            //listado.putExtra("empleado",idEmpleado);
            startActivity(listado);
        });
    }
}