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

import com.blongho.country_data.World;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
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
            tdc_text,newdcases,totaldcases,
            tac_text,totalacases;
    PieChart pieChart;


    public GlobalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://corona.lmao.ninja")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiClass=retrofit.create(ApiClass.class);
        // dostuff();

        View view=inflater.inflate(R.layout.fragment_global, container, false);

        tcc_text=(TextView)view.findViewById(R.id.tcc_text);
        newcases=(TextView)view.findViewById(R.id.newcases);
        totalcases=(TextView)view.findViewById(R.id.totalcases);

        trc_text=(TextView)view.findViewById(R.id.trc_text);
      //  newrcases=(TextView)view.findViewById(R.id.newrcases);
        totalrcases=(TextView)view.findViewById(R.id.totalrcases);

        tdc_text=(TextView)view.findViewById(R.id.tdc_text);
        newdcases=(TextView)view.findViewById(R.id.newdcases);
        totaldcases=(TextView)view.findViewById(R.id.totaldcases);
        pieChart=(PieChart)view.findViewById(R.id.pieChart);
        tac_text=(TextView)view.findViewById(R.id.tac_text);
        totalacases=(TextView)view.findViewById(R.id.totalacases);

        dostuff();
        return view;

    }
    public void dostuff()
    {
       // Toast.makeText(getActivity(), "GETTING DATA", Toast.LENGTH_SHORT).show();


        Call<NCovidGlobalObj> call=apiClass.getNewGlobalDetails();
        call.enqueue(new Callback<NCovidGlobalObj>() {
            @Override
            public void onResponse(Call<NCovidGlobalObj> call, Response<NCovidGlobalObj> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(getContext(), "CONNECTION DENIED", Toast.LENGTH_SHORT).show();
                    return;
                }

                NCovidGlobalObj covidGlobal=response.body();
                newcases.setText("New Confirmed Cases:- "+covidGlobal.getTodayCases());
                totalcases.setText("Total Confirmed cases:- "+ covidGlobal.getCases());

             //   newrcases.setText("New Cases:- ");
                totalrcases.setText("Total Recovered cases:- "+covidGlobal.getRecovered());

                totalacases.setText("Toatal Active Cases :- "+covidGlobal.getActive());

                newdcases.setText("Today Deaths:- "+covidGlobal.getTodayDeaths());
                totaldcases.setText("Total Death cases:- "+covidGlobal.getDeaths());

                pieChart.setUsePercentValues(true);
                pieChart.getDescription().setEnabled(false);
                pieChart.setExtraOffsets(5,10,5,5);

                pieChart.setDragDecelerationFrictionCoef(0.95f);

                pieChart.setDrawHoleEnabled(true);
                pieChart.setHoleColor(Color.WHITE);
                pieChart.setTransparentCircleRadius(55f);

                ArrayList<PieEntry> casevalues=new ArrayList<>();

                casevalues.add(new PieEntry(Integer.parseInt(covidGlobal.getRecovered()),"Recovered"));
                casevalues.add(new PieEntry(Integer.parseInt(covidGlobal.getDeaths()),"Death"));
                int activeacses=Integer.parseInt(covidGlobal.getActive());

                Description description=new Description();
                description.setText("COVID-19,Global Stats");
                description.setTextSize(20);
                pieChart.setDescription(description);

                casevalues.add(new PieEntry(activeacses,"Active"));
                pieChart.animateY(1500, Easing.EaseInOutCubic);

                PieDataSet dataSet=new PieDataSet(casevalues,"STATS");
                dataSet.setSliceSpace(3f);
                dataSet.setSelectionShift(5f);
                dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                PieData data=new PieData(dataSet);
                data.setValueTextSize(20f);
                data.setValueFormatter(new PercentFormatter(pieChart));
                data.setValueTextColor(Color.BLACK);

                pieChart.setData(data);


            }

            @Override
            public void onFailure(Call<NCovidGlobalObj> call, Throwable t) {

            }
        });
    }
}
