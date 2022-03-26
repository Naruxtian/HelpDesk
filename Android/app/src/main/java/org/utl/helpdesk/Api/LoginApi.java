package org.utl.helpdesk.Api;

import org.utl.helpdesk.Model.Employee;
import org.utl.helpdesk.Model.Users;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface  LoginApi {

    @GET("Log/in")
    public Call<Users> login(@Query("user_name") String user_name,
                             @Query("password") String password
                            //,@Body Employee employee
                            );
}