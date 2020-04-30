package com.example.tablaycovid;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

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
    PieChart pieChart;

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
        pieChart=(PieChart)view.findViewById(R.id.pieChart);
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

                pieChart.setUsePercentValues(true);
                pieChart.getDescription().setEnabled(false);
                pieChart.setExtraOffsets(5,10,5,5);

                pieChart.setDragDecelerationFrictionCoef(0.95f);

                pieChart.setDrawHoleEnabled(true);
                pieChart.setHoleColor(Color.WHITE);
                pieChart.setTransparentCircleRadius(55f);

                ArrayList<PieEntry> casevalues=new ArrayList<>();

                casevalues.add(new PieEntry(Integer.parseInt(covidGlobal.getTotalRecovered()),"Recovered"));
                casevalues.add(new PieEntry(Integer.parseInt(covidGlobal.getTotalDeaths()),"Death"));
                int activeacses=Integer.parseInt(covidGlobal.getTotalConfirmed())-
                        (Integer.parseInt(covidGlobal.getTotalRecovered())+Integer.parseInt(covidGlobal.getTotalDeaths()));

                casevalues.add(new PieEntry(activeacses,"Active"));

                PieDataSet dataSet=new PieDataSet(casevalues,"STATS");
                dataSet.setSliceSpace(3f);
                dataSet.setSelectionShift(5f);
                dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                PieData data=new PieData(dataSet);
                data.setValueTextSize(20f);
                data.setValueTextColor(Color.BLACK);



                pieChart.setData(data);

            }

            @Override
            public void onFailure(Call<CovidAll> call, Throwable t) {

            }
        });
    }
}
