package org.utl.helpdesk.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.utl.helpdesk.Api.LoginApi;
import org.utl.helpdesk.Api.Service;
import org.utl.helpdesk.Model.Users;
import org.utl.helpdesk.R;
import org.utl.helpdesk.SharedPref.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText txtUser;
    EditText txtPassword;
    Button btnLogin;
    Service service;
    Retrofit retrofit;

    int idEmpleado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUser = findViewById(R.id.txtUser);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        service = new Service();
        retrofit = service.createService();

        btnLogin.setOnClickListener(view -> {
                Users u = new Users();
                u.setUser_name(txtUser.getText().toString());
                u.setPassword(txtPassword.getText().toString());
                checkUser(u);


            /* if (validarUsuario()==true){
                Intent ingresar = new Intent(this,RegistroTicket.class);
                startActivity(ingresar);
            }else{
                txtUser.setError("Usuario Incorrecto");
            };*/
        });
    }

    public void checkUser(Users u){



        LoginApi loginApi = retrofit.create(LoginApi.class);
        Call<Users> call = loginApi.login(u.getUser_name(),u.getPassword());
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                try {
                    if (response.isSuccessful() && response.body().getId_user() != 0){

                        SharedPrefManager.getInstance(getApplicationContext()).saveUser(response.body());

                        Toast.makeText(MainActivity.this, "Login correcto", Toast.LENGTH_SHORT).show();

                        idEmpleado = response.body().getId_user();
                        Intent ingresar = new Intent(MainActivity.this, MenuPrincipal.class);
                        //ingresar.putExtra("empleado",idEmpleado);
                        startActivity(ingresar);
                    }else{
                        Toast.makeText(MainActivity.this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
                        txtUser.setError("Datos incorrectos");
                        txtPassword.setError("Datos incorrectos");
                        System.out.println(response.code());
                        System.out.println(response.body());
                    }
                }catch (Exception e){
                    //Toast.makeText(MainActivity.this, "Error en la aplicación", Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
                    System.out.println(e.toString());
                    txtUser.setError("Datos incorrectos");
                    txtPassword.setError("Datos incorrectos");
                    System.out.println(response.code());
                    System.out.println(response.body());
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                System.out.println(t.toString());
            }
        });
    }

   /* public boolean validarUsuario(){
        boolean respuesta = false;
        if(txtPassword.getText().toString().equals("1234") & txtUser.getText().toString().equals("Admin")){
            respuesta = true;
        }
        return respuesta;
    }*/
}