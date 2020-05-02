package com.example.tablaycovid;

import com.google.gson.annotations.SerializedName;

class CountryInfoObj {

    @SerializedName("_id")
    private Integer _id;

    @SerializedName("iso2")
    private String iso2;

    @SerializedName("iso3")
    private String iso3;

    @SerializedName("lat")
    private String lat;

    @SerializedName("long")
    private String lon;

    @SerializedName("flag")
    private String flag;

    public Integer get_id() {
        return _id;
    }

    public String getIso2() {
        return iso2;
    }

    public String getIso3() { return iso3; }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public String getFlag() {
        return flag;
    }
}
