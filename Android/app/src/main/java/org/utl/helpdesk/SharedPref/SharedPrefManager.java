package org.utl.helpdesk.SharedPref;

import android.content.Context;
import android.content.SharedPreferences;

import org.utl.helpdesk.Model.Employee;
import org.utl.helpdesk.Model.Users;

public class SharedPrefManager {
    //Definir las variables de SharedPreferences que vamos a guardar

    private static final String SHARED_PREFERENCES = "SHARED_PREFERENCES";

    private static final String SHARED_PREFERENCES_ID_USER = "SHARED_PREFERENCES_ID_USER";
    private static final String SHARED_PREFERENCES_USER_NAME = "SHARED_PREFERENCES_USER_NAME";
    private static final String SHARED_PREFERENCES_PASSWORD = "SHARED_PREFERENCES_PASSWORD";
    private static final String SHARED_PREFERENCES_ID_EMPLOYEE = "SHARED_PREFERENCES_ID_EMPLOYEE";

    //Crear la instancia de nuestros objetos a utilizar en la clase
    private static SharedPrefManager instance;
    private Context context;
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;

    private  SharedPrefManager (Context context){
        this.context = context;
        //Configuramos el modo privatea para que solo se pueda acceder a la información almacenada dentro de la app
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static synchronized SharedPrefManager getInstance(Context context){
        if (instance == null){
            instance = new SharedPrefManager(context);
        }
        return instance;
    }

    public void saveUser(Users users){
        editor.putInt(SHARED_PREFERENCES_ID_USER, users.getId_user());
        editor.putString(SHARED_PREFERENCES_USER_NAME, users.getUser_name());
        editor.putString(SHARED_PREFERENCES_PASSWORD, users.getPassword());
        editor.putInt(SHARED_PREFERENCES_ID_EMPLOYEE, users.getEmployee().getId_employee());
        editor.apply();
    }

    public Users getUser(){
        Employee employee = new Employee();
        employee.setId_employee(sharedPreferences.getInt(SHARED_PREFERENCES_ID_EMPLOYEE,-1));

        Users user = new Users(sharedPreferences.getInt(SHARED_PREFERENCES_ID_USER,-1),
                                sharedPreferences.getString(SHARED_PREFERENCES_USER_NAME, null),
                                sharedPreferences.getString(SHARED_PREFERENCES_PASSWORD, null),
                                employee);
        return user;
    }
}
