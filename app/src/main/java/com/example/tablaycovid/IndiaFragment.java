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

    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ApiClass apiClass;
    TextView tv_fi;

    public IndiaFragment() {
        // Required empty public constructor


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        /*
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.covid19india.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiClass=retrofit.create(ApiClass.class);
        dostuff();
        View view=inflater.inflate(R.layout.fragment_india, container, false);
        tv_fi=(TextView)view.findViewById(R.id.tv_fi);


         */
        View view=inflater.inflate(R.layout.fragment_india, container, false);
        return view;
    }
    public void dostuff()
    {
        Call<List<StateObject>> call=apiClass.getStateDetails();
        Toast.makeText(getActivity(), "IS IT GOING ON", Toast.LENGTH_SHORT).show();
        tv_fi.setText("LOL DONT KNOW WTH");

        call.enqueue(new Callback<List<StateObject>>() {
            @Override
            public void onResponse(Call<List<StateObject>> call, Response<List<StateObject>> response) {
                List<StateObject> statelists=response.body();
               // tv_fi.setText(Integer.toString(statelists.size()));
                //tv_fi.setText(response.code());


            }

            @Override
            public void onFailure(Call<List<StateObject>> call, Throwable t) {

            }
        });
    }
}
