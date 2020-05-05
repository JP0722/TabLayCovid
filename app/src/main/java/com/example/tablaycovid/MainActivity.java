package com.example.tablaycovid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    PageAdapter pageAdapter;
    TabItem tabGlobal;
    TabItem tabCountry;
    TabItem tabIndia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("COVID TRACKER");
        setSupportActionBar(toolbar);

        tabLayout=findViewById(R.id.tabLayouts);
        viewPager=findViewById(R.id.viewPager);
        tabGlobal=findViewById(R.id.ti_global);
        tabCountry=findViewById(R.id.ti_country);
        tabIndia=findViewById(R.id.ti_india);


        if(checkConnection()) {

            pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

            viewPager.setAdapter(pageAdapter);

            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                    if (tab.getPosition() == 1) {

                        // Toast.makeText(MainActivity.this, Integer.toString(tab.getPosition()), Toast.LENGTH_SHORT).show();
                    } else if (tab.getPosition() == 2) {
                        // Toast.makeText(MainActivity.this, Integer.toString(tab.getPosition()), Toast.LENGTH_SHORT).show();
                    } else {
                        //Toast.makeText(MainActivity.this, Integer.toString(tab.getPosition()), Toast.LENGTH_SHORT).show();
                    }


                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        }
        else{
            startActivity(new Intent(MainActivity.this,OfflineActivity.class));

        }


    }

    public boolean checkConnection()
    {
        ConnectivityManager manager=(ConnectivityManager) getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork=manager.getActiveNetworkInfo();
        if(null==activeNetwork)
        {
              // Toast.makeText(this, "PLEASE ENABLE INTERNET,TO SEE COVID-19 GlOBAL & INDIA STATS", Toast.LENGTH_SHORT).show();
                return false;
        }
        else
        {
            return true;
        }
    }


}
