package com.example.sep4_android.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sep4_android.R;
import com.example.sep4_android.model.Humidity;

import java.util.ArrayList;

public class HumidityAdapter extends RecyclerView.Adapter<HumidityAdapter.ViewHolder> {

    private ArrayList<Humidity> humidityData;

    public HumidityAdapter (ArrayList<Humidity> humidityData){
        this.humidityData = humidityData;
    }

    @NonNull
    @Override
    public HumidityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_view_item, parent, false);
        int height = parent.getHeight()/8;
        int width = parent.getWidth();
        view.setLayoutParams(new RecyclerView.LayoutParams(width,height));
        return new HumidityAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HumidityAdapter.ViewHolder holder, int position) {
        holder.dataInformation.setText(humidityData.get(position).getHumidityData());
    }

    @Override
    public int getItemCount() {
        return humidityData.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView dataInformation;

        ViewHolder (@NonNull View itemView)
        {
            super(itemView);
            this.dataInformation = itemView.findViewById(R.id.recycler_view_text);
        }
    }
}
