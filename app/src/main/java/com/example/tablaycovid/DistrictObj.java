package com.example.tablaycovid;

import java.util.List;

public class DistrictObj {

    private String notes;
    private String active;
    private String confirmed;
    private String deceased;
    private String recovered;
    List<DeltaObj> delta;


    public String getNotes() {
        return notes;
    }

    public String getActive() {
        return active;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public String getDeceased() {
        return deceased;
    }

    public String getRecovered() {
        return recovered;
    }

    public List<DeltaObj> getDelta() {
        return delta;
    }
}
