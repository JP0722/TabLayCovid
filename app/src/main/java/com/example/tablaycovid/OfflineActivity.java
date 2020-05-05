package com.example.tablaycovid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class OfflineActivity extends AppCompatActivity {

    TextView guidelines;
    Spanned text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);
        guidelines=findViewById(R.id.guidelines);
        text= Html.fromHtml("Image above shows the guidelines by Ministry of Health ,Government of India. To know more about safety and precautions of COVID-19\n"
                +" visit "+"<a href='https://www.mohfw.gov.in/'>mohfw.gov.in</a>");
        guidelines.setMovementMethod(LinkMovementMethod.getInstance());
        guidelines.setText(text);
    }
}
