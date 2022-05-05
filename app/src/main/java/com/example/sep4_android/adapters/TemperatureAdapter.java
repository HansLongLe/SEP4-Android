package com.example.sep4_android.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sep4_android.R;
import com.example.sep4_android.model.CO2;
import com.example.sep4_android.model.Temperature;

import java.util.ArrayList;

public class TemperatureAdapter extends RecyclerView.Adapter<TemperatureAdapter.ViewHolder> {

    private ArrayList<Temperature> temperatureData;

    public TemperatureAdapter (ArrayList<Temperature> temperatureData){
        this.temperatureData = temperatureData;
    }

    @NonNull
    @Override
    public TemperatureAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_view_item, parent, false);
        int height = parent.getHeight()/8;
        int width = parent.getWidth();
        view.setLayoutParams(new RecyclerView.LayoutParams(width,height));
        return new TemperatureAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TemperatureAdapter.ViewHolder holder, int position) {
        holder.dataInformation.setText(temperatureData.get(position).getTemperatureData());
    }

    @Override
    public int getItemCount() {
        return temperatureData.size();
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
