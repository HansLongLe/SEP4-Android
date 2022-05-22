package com.example.sep4_android.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sep4_android.R;
import com.example.sep4_android.model.CO2;
import com.example.sep4_android.model.Humidity;
import com.example.sep4_android.model.Temperature;

import java.util.ArrayList;

public class TemperatureAdapter extends RecyclerView.Adapter<TemperatureAdapter.ViewHolder> {

    private ArrayList<Temperature> temperatureData;
    private View view;

    public TemperatureAdapter (ArrayList<Temperature> temperatureData){
        this.temperatureData = temperatureData;
    }

    @NonNull
    @Override
    public TemperatureAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.recycler_view_item, parent, false);
        int height = parent.getHeight()/10;
        int width = parent.getWidth();
        view.setLayoutParams(new RecyclerView.LayoutParams(width,height));
        return new TemperatureAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TemperatureAdapter.ViewHolder holder, int position) {
        holder.dataInformation.setText(temperatureData.get(position).getTemperature() + " °C");
        holder.dataDate.setText(temperatureData.get(position).getTime().toString() + "");
        holder.dateID.setText(temperatureData.get(position).getTemperatureId() + "");
    }

    @Override
    public int getItemCount() {
        return  temperatureData == null ? 0 : temperatureData.size();
    }


    public void updateTemperatureData(ArrayList<Temperature> temperatureData)
    {
        this.temperatureData = temperatureData;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView dataInformation;
        private final TextView dataDate;
        private final TextView dateID;

        ViewHolder (@NonNull View itemView)
        {
            super(itemView);
            this.dataInformation = itemView.findViewById(R.id.text_name);
            this.dataDate = itemView.findViewById(R.id.text_date);
            this.dateID = itemView.findViewById(R.id.text_id);
        }
    }
}
