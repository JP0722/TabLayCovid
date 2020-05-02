package com.example.tablaycovid;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DistrictObj {


    @SerializedName("district")
    private String district;

    @SerializedName("notes")
    private String notes;

    @SerializedName("active")
    private Integer active;

    @SerializedName("confirmed")
    private Integer confirmed;

    @SerializedName("deceased")
    private Integer deceased;

    @SerializedName("recovered")
    private Integer recovered;

    @SerializedName("delta")
    DeltaObj delta;

    public String getDistrict() {
        return district;
    }

    public String getNotes() {
        return notes;
    }

    public Integer getActive() {
        return active;
    }

    public Integer getConfirmed() {
        return confirmed;
    }

    public Integer getDeceased() {
        return deceased;
    }

    public Integer getRecovered() {
        return recovered;
    }

    public DeltaObj getDelta() {
        return delta;
    }

/*
    {
        "district": "North and Middle Andaman",
        "notes": "",
        "active": 0,
        "confirmed": 1,
        "deceased": 0,
        "recovered": 1,
        "delta": {
        "confirmed": 0,
        "deceased": 0,
        "recovered": 0
        }
        },
     */
}
