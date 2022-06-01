package com.example.sep4_android.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sep4_android.R;
import com.example.sep4_android.model.CO2;

import java.util.ArrayList;

public class CO2Adapter extends RecyclerView.Adapter<CO2Adapter.ViewHolder> {

private ArrayList<CO2> cO2Data;
private View view;

public CO2Adapter (ArrayList<CO2> cO2Data){
        this.cO2Data = cO2Data;
        }

@NonNull
@Override
public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.recycler_view_item, parent, false);
        int height = parent.getHeight()/10;
        int width = parent.getWidth();
        view.setLayoutParams(new RecyclerView.LayoutParams(width,height));
        return new ViewHolder(view);
        }

@SuppressLint("SetTextI18n")
@Override
public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.dataInformation.setText(cO2Data.get(position).getCo2Level() + " ppm");
        holder.dateID.setText(cO2Data.get(position).getCo2Id() + "");
        holder.dataDate.setText(cO2Data.get(position).getTime().toString() + "");
        }

@Override
public int getItemCount() {
    return  cO2Data == null ? 0 : cO2Data.size();
}

public void updateCO2Data(ArrayList<CO2> cO2Data)
{
    this.cO2Data = cO2Data;
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
