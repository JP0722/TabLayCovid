package com.example.tablaycovid;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiClass {

    @GET("/world/total")
    Call<CovidObj> gettotaldetails();

    @GET("/summary")
    Call<CovidAll> getCountriesdetails();

    @GET("/state_district_wise.json")
    Call<List<StateObject>> getStateDetails();

}