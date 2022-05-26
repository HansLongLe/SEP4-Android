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

import java.util.ArrayList;

public class HumidityAdapter extends RecyclerView.Adapter<HumidityAdapter.ViewHolder> {

    private ArrayList<Humidity> humidityData;
    private View view;

    public HumidityAdapter (ArrayList<Humidity> humidityData){
        this.humidityData = humidityData;
    }

    @NonNull
    @Override
    public HumidityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.recycler_view_item, parent, false);
        int height = parent.getHeight()/10;
        int width = parent.getWidth();
        view.setLayoutParams(new RecyclerView.LayoutParams(width,height));
        return new HumidityAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HumidityAdapter.ViewHolder holder, int position) {
        holder.dataInformation.setText(Double.toString(humidityData.get(position).getHumidity()) + " %");
        holder.dataDate.setText(humidityData.get(position).getTime().toString() + "");
        holder.dateID.setText(humidityData.get(position).getHumidityId() + "");
    }

    @Override
    public int getItemCount() {
        return  humidityData == null ? 0 : humidityData.size();
    }

    public void updateHumidityData(ArrayList<Humidity> humidityData)
    {
        this.humidityData = humidityData;
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
