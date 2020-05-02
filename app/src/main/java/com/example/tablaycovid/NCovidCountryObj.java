package com.example.tablaycovid;

import com.google.gson.annotations.SerializedName;

public class NCovidCountryObj {

    @SerializedName("updated")
    private String updated;

    @SerializedName("country")
    private String country;

    @SerializedName("countryInfo")
    private CountryInfoObj countryInfo;

    @SerializedName("cases")
    private Integer cases;

    @SerializedName("todayCases")
    private Integer todayCases;

    @SerializedName("deaths")
    private Integer deaths;

    @SerializedName("todayDeaths")
    private Integer todayDeaths;

    @SerializedName("recovered")
    private Integer recovered;

    @SerializedName("active")
    private Integer active;

    @SerializedName("critical")
    private Integer critical;

    @SerializedName("casesPerOneMillion")
    private Integer casesPerOneMillion;

    @SerializedName("deathsPerOneMillion")
    private Integer deathsPerOneMillion;

    @SerializedName("tests")
    private Integer tests;

    @SerializedName("testsPerOneMillion")
    private Integer testsPerOneMillion;

    @SerializedName("continent")
    private String continent;



    public String  getUpdated() {
        return updated;
    }

    public String getCountry() {
        return country;
    }

    public CountryInfoObj getCountryInfo() {
        return countryInfo;
    }

    public Integer getCases() {
        return cases;
    }

    public Integer getTodayCases() {
        return todayCases;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public Integer getTodayDeaths() {
        return todayDeaths;
    }

    public Integer getRecovered() {
        return recovered;
    }

    public Integer getActive() {
        return active;
    }

    public Integer getCritical() {
        return critical;
    }

    public Integer getCasesPerOneMillion() {
        return casesPerOneMillion;
    }

    public Integer getDeathsPerOneMillion() {
        return deathsPerOneMillion;
    }

    public Integer getTests() {
        return tests;
    }

    public Integer getTestsPerOneMillion() {
        return testsPerOneMillion;
    }

    public String getContinent() {
        return continent;
    }

    /*
    {"updated":1588260101827,
     "country":"Afghanistan",
     "countryInfo":{    "_id":4,
                        "iso2":"AF",
                        "iso3":"AFG",
                        "lat":33,
                        "long":65,
                        "flag":"https://corona.lmao.ninja/assets/img/flags/af.png"},
     "cases":2171,
     "todayCases":232,
     "deaths":64,
     "todayDeaths":4,
     "recovered":260,
     "active":1847,
     "critical":7,
     "casesPerOneMillion":56,
     "deathsPerOneMillion":2,
     "tests":10022,
     "testsPerOneMillion":257,
     "continent":"Asia"
     }
     */
}
