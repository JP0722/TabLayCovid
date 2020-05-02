package com.example.tablaycovid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ExampleViewHolder> implements Filterable {


    private ArrayList<StateItem> mExampleList;
    private ArrayList<StateItem> exampleListFull;
    private OnItemClickListener mListener;

    public interface  OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener=listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder{

        public TextView statename;
        public TextView confcases;
        public TextView activecases;
        public TextView recoveredcases;
        public TextView deadcases;

        public ExampleViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            statename=itemView.findViewById(R.id.state_name);
            activecases=itemView.findViewById(R.id.state_total_active);
            confcases=itemView.findViewById(R.id.state_total_confirmed);
            recoveredcases=itemView.findViewById(R.id.state_total_recovered);
            deadcases=itemView.findViewById(R.id.state_total_deceased);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null)
                    {
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION)
                        {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public StateAdapter(ArrayList<StateItem> exampleList){
        mExampleList=exampleList;
        exampleListFull=new ArrayList<>(mExampleList);
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.state_item,parent,false);
        ExampleViewHolder evh=new ExampleViewHolder(v,mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {


        StateItem currentItem=mExampleList.get(position);


        holder.statename.setText(currentItem.getState_name());
        holder.confcases.setText(currentItem.getState_confirmed());
        holder.activecases.setText(currentItem.getState_active());
        holder.recoveredcases.setText(currentItem.getState_recovered());
        holder.deadcases.setText(currentItem.getState_deceased());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


    @Override
    public Filter getFilter() {
        return exapleFilter;
    }
    private Filter exapleFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<StateItem> filteredList=new ArrayList<>();
            if(constraint==null || constraint.length()==0)
            {
                filteredList.addAll(exampleListFull);
            }
            else
            {
                String filteredpattern=constraint.toString().toLowerCase().trim();
                for(StateItem item: exampleListFull)
                {
                    if(item.getState_name().toLowerCase().contains(filteredpattern))
                    {
                        filteredList.add(item);
                    }
                }

            }
            FilterResults results=new FilterResults();
            results.values=filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mExampleList.clear();
            mExampleList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public void filteredList(ArrayList<StateItem> filteredList)
    {

        mExampleList=filteredList;
        notifyDataSetChanged();
    }


}
