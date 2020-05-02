package com.example.tablaycovid;
import android.graphics.Bitmap;

public class ExampleItem {

    private String mImageResource;
    private String country;
    private String totConfirmed;
    private String totActive;
    private String totRecovered;
    private String totDeaths;


    public ExampleItem(String imageResource, String country, String totConfirmed, String totActivie, String totRecovered
            , String totDeaths) {
        mImageResource = imageResource;
        this.country = country;
        this.totConfirmed = totConfirmed;
        this.totActive = totActivie;
        this.totRecovered = totRecovered;
        this.totDeaths = totDeaths;
    }

    public String getImageResource() {
        return mImageResource;
    }

    public String getCountry() {
        return country;
    }

    public String getTotConfirmed() {
        return totConfirmed;
    }

    public String getTotActive() {
        return totActive;
    }

    public String getTotRecovered() {
        return totRecovered;
    }

    public String getTotDeaths() {
        return totDeaths;
    }
}