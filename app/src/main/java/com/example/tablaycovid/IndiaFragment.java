package com.example.tablaycovid;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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
public class IndiaFragment extends Fragment {

    ImageView imageIcon;

    int position;

    private static final int MY_PERMISSION_REQUEST=1;
    TextView  value_conf,value_active,value_recovered,value_deceased;

    private RecyclerView mRecyclerView;
    private StateAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<StateItem> exampleList=new ArrayList<>();
    List<StateObject> statelists;
    ApiClass apiClass,apiClass1;
    EditText editTextindia;



    public IndiaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.covid19india.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiClass=retrofit.create(ApiClass.class);

        Retrofit retrofit1=new Retrofit.Builder()
                                .baseUrl("https://corona.lmao.ninja/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
        apiClass1=retrofit1.create(ApiClass.class);

        View view=inflater.inflate(R.layout.fragment_india, container, false);
        mRecyclerView=(RecyclerView)view.findViewById(R.id.rViewindia);
        editTextindia=(EditText) view.findViewById(R.id.editTextindia);
        exampleList.clear();

        value_conf=(TextView)view.findViewById(R.id.value_conf);
        value_active=(TextView)view.findViewById(R.id.value_active);
        value_recovered=(TextView)view.findViewById(R.id.value_recovered);
        value_deceased=(TextView)view.findViewById(R.id.value_deceased);

        editTextindia.addTextChangedListener(new TextWatcher() {
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



        Call<NCovidCountryObj> call1=apiClass1.getIndiaDetals();

        call1.enqueue(new Callback<NCovidCountryObj>() {
            @Override
            public void onResponse(Call<NCovidCountryObj> call, Response<NCovidCountryObj> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }
                NCovidCountryObj indiaObj=response.body();

                value_conf.setText(indiaObj.getCases().toString());
                value_active.setText(indiaObj.getActive().toString());
                value_recovered.setText(indiaObj.getRecovered().toString());
                value_deceased.setText(indiaObj.getDeaths().toString());

            }

            @Override
            public void onFailure(Call<NCovidCountryObj> call, Throwable t) {

            }
        });




        Call<List<StateObject>> call=apiClass.getStateDetails();


        call.enqueue(new Callback<List<StateObject>>() {
            @Override
            public void onResponse(Call<List<StateObject>> call, Response<List<StateObject>> response) {
                statelists=response.body();


                for(StateObject stateObject:statelists)
                {

                    List<DistrictObj> districtlist=stateObject.getDistrictData();

                    Integer confirmed = 0;
                    Integer active = 0;
                    Integer recovered= 0;
                    Integer deceased= 0;

                    for(DistrictObj districtObj:districtlist)
                    {
                        confirmed+=districtObj.getConfirmed();
                        active+=districtObj.getActive();
                        recovered+=districtObj.getRecovered();
                        deceased+=districtObj.getDeceased();
                    }


                    exampleList.add(new StateItem(stateObject.getState(),
                                         "Confirmed Cases :- "+Integer.toString(confirmed),
                                         "Active Cases :- "+Integer.toString(active),
                                        "Recovered Cases :- "+Integer.toString(recovered),
                                        "Deceased Cases :- "+Integer.toString(deceased)));


                }

                mAdapter = new StateAdapter(exampleList);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.setOnItemClickListener(new StateAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position){

                    }
                });


            }

            @Override
            public void onFailure(Call<List<StateObject>> call, Throwable t) {

                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void filter(String Text)
    {
        ArrayList<StateItem> filteredList=new ArrayList<>();
        for(StateItem item:exampleList)
        {
            if(item.getState_name().toLowerCase().contains(Text.toLowerCase()))
            {
                filteredList.add(item);
            }
        }
        mAdapter.filteredList(filteredList);
    }


}
