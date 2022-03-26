package org.utl.helpdesk.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {

    String urlBase = "http://192.168.100.11:8080/HelpDesk/api/";

    public Retrofit createService (){
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(urlBase).
                addConverterFactory(GsonConverterFactory.create()).
                build();

        return retrofit;
    }

}
