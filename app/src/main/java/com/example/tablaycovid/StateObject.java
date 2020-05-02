package com.example.tablaycovid;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StateObject {


    @SerializedName("state")
    private String state;

    @SerializedName("statecode")
    private String statecode;

    @SerializedName("districtData")
    List<DistrictObj> districtData;


    public String getState() {
        return state;
    }

    public String getStatecode() {
        return statecode;
    }

    public List<DistrictObj> getDistrictData() {
        return districtData;
    }
}

/*

{
        "state": "Andaman and Nicobar Islands",
        "statecode": "AN",
        "districtData": [
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
        {
        "district": "South Andaman",
        "notes": "",
        "active": 17,
        "confirmed": 32,
        "deceased": 0,
        "recovered": 15,
        "delta": {
        "confirmed": 0,
        "deceased": 0,
        "recovered": 0
        }
        }
        ]
        },

 */
