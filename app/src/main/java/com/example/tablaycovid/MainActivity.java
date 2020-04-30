package com.example.tablaycovid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
//import android.widget.Toolbar;
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






        pageAdapter=new PageAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==1)
                {

                   // Toast.makeText(MainActivity.this, Integer.toString(tab.getPosition()), Toast.LENGTH_SHORT).show();
                }
                else if(tab.getPosition()==2)
                {
                   // Toast.makeText(MainActivity.this, Integer.toString(tab.getPosition()), Toast.LENGTH_SHORT).show();
                }
                else
                {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.toolbar_search_menu,menu);
        MenuItem menuItem=menu.findItem(R.id.search_bar);
        Toast.makeText(MainActivity.this, "YES I AM IN", Toast.LENGTH_SHORT).show();
        SearchView searchView=(SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type to Search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               // mAdapter.getFilter().filter(newText);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }
}
