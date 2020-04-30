package com.example.tablaycovid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
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
public class GlobalFragment extends Fragment {

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

    TextView tcc_text,newcases,totalcases,
            trc_text,newrcases,totalrcases,
            tdc_text,newdcases,totaldcases;

    public GlobalFragment() {
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

        View view=inflater.inflate(R.layout.fragment_global, container, false);

        tcc_text=(TextView)view.findViewById(R.id.tcc_text);
        newcases=(TextView)view.findViewById(R.id.newcases);
        totalcases=(TextView)view.findViewById(R.id.totalcases);

        trc_text=(TextView)view.findViewById(R.id.trc_text);
        newrcases=(TextView)view.findViewById(R.id.newrcases);
        totalrcases=(TextView)view.findViewById(R.id.totalrcases);

        tdc_text=(TextView)view.findViewById(R.id.tdc_text);
        newdcases=(TextView)view.findViewById(R.id.newdcases);
        totaldcases=(TextView)view.findViewById(R.id.totaldcases);

        dostuff();
        return view;

    }
    public void dostuff()
    {
        Toast.makeText(getActivity(), "GETTING DATA", Toast.LENGTH_SHORT).show();


        Call<CovidAll> call=apiClass.getCountriesdetails();
        call.enqueue(new Callback<CovidAll>() {
            @Override
            public void onResponse(Call<CovidAll> call, Response<CovidAll> response) {

                CovidAll covidAll=response.body();

                CovidGlobal covidGlobal=covidAll.getCovidGlobal();
                newcases.setText("New Cases:- "+covidGlobal.getNewConfirmed());
                totalcases.setText("Total cases:- "+covidGlobal.getTotalConfirmed());

                newrcases.setText("New Cases:- "+covidGlobal.getNewRecovered());
                totalrcases.setText("Total cases:- "+covidGlobal.getTotalRecovered());

                newdcases.setText("New Cases:- "+covidGlobal.getNewDeaths());
                totaldcases.setText("Total cases:- "+covidGlobal.getTotalDeaths());

            }

            @Override
            public void onFailure(Call<CovidAll> call, Throwable t) {

            }
        });
    }
}
