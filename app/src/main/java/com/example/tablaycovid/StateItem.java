package com.example.tablaycovid;

public class StateItem {

    private String state_name;
    private String state_confirmed;
    private String state_active;
    private String state_recovered;
    private String state_deceased;

    public StateItem(String state_name, String state_confirmed, String state_active, String state_recovered, String state_deceased) {
        this.state_name = state_name;
        this.state_confirmed = state_confirmed;
        this.state_active = state_active;
        this.state_recovered = state_recovered;
        this.state_deceased = state_deceased;
    }

    public String getState_name() {
        return state_name;
    }

    public String getState_confirmed() {
        return state_confirmed;
    }

    public String getState_active() {
        return state_active;
    }

    public String getState_recovered() {
        return state_recovered;
    }

    public String getState_deceased() {
        return state_deceased;
    }
}
