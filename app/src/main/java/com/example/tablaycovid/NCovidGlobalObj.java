package com.example.tablaycovid;

import com.google.gson.annotations.SerializedName;

public class NCovidGlobalObj {

    @SerializedName("updated")
    private String updated;

    @SerializedName("cases")
    private String cases;

    @SerializedName("todayCases")
    private String todayCases;

    @SerializedName("deaths")
    private String deaths;

    @SerializedName("todayDeaths")
    private String todayDeaths;

    @SerializedName("recovered")
    private String recovered;

    @SerializedName("active")
    private String active;

    @SerializedName("critical")
    private String critical;

    @SerializedName("casesPerOneMillion")
    private String casesPerOneMillion;

    @SerializedName("deathsPerOneMillion")
    private String deathsPerOneMillion;

    @SerializedName("tests")
    private String tests;

    @SerializedName("testsPerOneMillion")
    private String testsPerOneMillion;

    @SerializedName("affectedCountries")
    private String affectedCountries;

    public String getUpdated() {
        return updated;
    }

    public String getCases() {
        return cases;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public String getDeaths() {
        return deaths;
    }

    public String getTodayDeaths() {
        return todayDeaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getActive() {
        return active;
    }

    public String getCritical() {
        return critical;
    }

    public String getCasesPerOneMillion() {
        return casesPerOneMillion;
    }

    public String getDeathsPerOneMillion() {
        return deathsPerOneMillion;
    }

    public String getTests() {
        return tests;
    }

    public String getTestsPerOneMillion() {
        return testsPerOneMillion;
    }

    public String getAffectedCountries() {
        return affectedCountries;
    }
}

/*
{
"updated":1588311081141,
 "cases":3308643,
 "todayCases":4423,
 "deaths":234123,
 "todayDeaths":293,
 "recovered":1042981,
 "active":2031539,
 "critical":50937,
 "casesPerOneMillion":424,
 "deathsPerOneMillion":30,
 "tests":33475345,
 "testsPerOneMillion":4289.8,
  "affectedCountries":214
  }

 */