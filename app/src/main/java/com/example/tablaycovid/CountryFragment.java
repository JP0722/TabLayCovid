package com.example.tablaycovid;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.covid19api.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiClass=retrofit.create(ApiClass.class);
       // dostuff();

        View view=inflater.inflate(R.layout.fragment_country, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rView);
        editText=(EditText)view.findViewById(R.id.editText);
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


        Call<CovidAll> call=apiClass.getCountriesdetails();
        call.enqueue(new Callback<CovidAll>() {
            @Override
            public void onResponse(Call<CovidAll> call, Response<CovidAll> response) {

                CovidAll covidAll=response.body();

                CovidGlobal covidGlobal=covidAll.getCovidGlobal();

                List<CovidCounties> covidCountiesList=covidAll.getCovidCountiesList();

                for(CovidCounties country:covidCountiesList)
                {

                    exampleList.add(new ExampleItem(null,
                            ""+country.getCountry(),
                            "Total Confirmed Cases:- "+country.getTotalConfirmed()+" (+"+country.getNewConfirmed()+")",
                            "TotalActive :- "+Integer.toString(Integer.parseInt(country.getTotalConfirmed())
                                    -(Integer.parseInt(country.getTotalRecovered())+Integer.parseInt(country.getTotalDeaths()))),
                            "TotalRecovered :- "+country.getTotalRecovered(),
                            "Total Deaths :- "+country.getTotalDeaths()));


                }
                // mAdapter.filteredList(exampleList);
                //  mAdapter = new ExampleAdapter(exampleList);

                mAdapter = new ExampleAdapter(exampleList);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int getposition) {

                       // Toast.makeText(CountryFragment.this, Integer.toString(getposition), Toast.LENGTH_SHORT).show();

                    }
                });


            }

            @Override
            public void onFailure(Call<CovidAll> call, Throwable t) {

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



    /*
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_search_menu,menu);
        MenuItem menuItem=menu.findItem(R.id.search_bar);
        Toast.makeText(getContext(), "YES I AM IN", Toast.LENGTH_SHORT).show();
        SearchView searchView=(SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type to Search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });


        super.onCreateOptionsMenu(menu, inflater);


    }

     */



}
