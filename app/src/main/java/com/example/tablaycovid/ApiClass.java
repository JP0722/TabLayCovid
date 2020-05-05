package com.example.tablaycovid;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiClass {

    @GET("/world/total")
    Call<CovidObj> gettotaldetails();

    @GET("/v2/countries")
    Call<List<NCovidCountryObj>> getNewCountryDetails();

    @GET("/v2/all")
    Call<NCovidGlobalObj> getNewGlobalDetails();

    @GET("/v2/state_district_wise.json")
    Call<List<StateObject>> getStateDetails();

    @GET("/v2/countries/india")
    Call<NCovidCountryObj> getIndiaDetals();


}