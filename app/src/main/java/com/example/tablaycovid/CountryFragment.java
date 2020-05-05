package com.example.tablaycovid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

//import com.blongho.country_data.World;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class CountryFragment extends Fragment {

    ImageView imageIcon;

    int position;

    private static final int MY_PERMISSION_REQUEST=1;

    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private long mLastClickTime = 0;
    ArrayList<String> arrayList;
    ArrayList<ExampleItem> exampleList=new ArrayList<>();
    ApiClass apiClass;
    EditText editText;

    public CountryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       // World.init(getContext());

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://corona.lmao.ninja") //https://api.covid19api.com/summary ---- https://corona.lmao.ninja/v2/
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiClass=retrofit.create(ApiClass.class);

        View view=inflater.inflate(R.layout.fragment_country, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rView);
        editText=(EditText)view.findViewById(R.id.editText);
        exampleList.clear();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });

        dostuff();

        return view;

    }
    public void dostuff()
    {
       mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        getMusic();
    }


    public void getMusic()
    {
        Toast.makeText(getActivity(), "GETTING DATA", Toast.LENGTH_SHORT).show();

        Call<List<NCovidCountryObj>> call=apiClass.getNewCountryDetails();
        call.enqueue(new Callback<List<NCovidCountryObj>>() {
            @Override
            public void onResponse(Call<List<NCovidCountryObj>> call, Response<List<NCovidCountryObj>> response) {

                List<NCovidCountryObj> clist=response.body();

                for(NCovidCountryObj country:clist)
                {
                    /*

                    Bitmap bitmap = null;

                    try {
                        URL url = new URL(country.getCountryInfo().getFlag());
                        bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    } catch(Exception e) {
                        System.out.println(e);
                        Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    }

                     */
                    exampleList.add(new ExampleItem(country.getCountryInfo().getIso3(),
                            ""+country.getCountry(),
                            "Total Confirmed Cases:- "+Integer.toString(country.getCases())+" (+"+Integer.toString(country.getTodayCases())+")",
                            "TotalActive :- "+Integer.toString(country.getActive()),
                            "TotalRecovered :- "+Integer.toString(country.getRecovered()),
                            "Total Deaths :- "+Integer.toString(country.getDeaths())));
                }


                mAdapter = new ExampleAdapter(exampleList);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int getposition) {

                    }
                });


            }

            @Override
            public void onFailure(Call<List<NCovidCountryObj>> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });






    }
    private void filter(String Text)
    {
        ArrayList<ExampleItem> filteredList=new ArrayList<>();
        for(ExampleItem item:exampleList)
        {
            if(item.getCountry().toLowerCase().contains(Text.toLowerCase()))
            {
                filteredList.add(item);
            }
        }
        mAdapter.filteredList(filteredList);
    }



}

