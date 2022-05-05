package com.example.sep4_android.adapters;

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
        int height = parent.getHeight()/7;
        int width = parent.getWidth();
        view.setLayoutParams(new RecyclerView.LayoutParams(width,height));
        return new ViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.dataInformation.setText(cO2Data.get(position).getCo2Level() + " ppm");
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

    ViewHolder (@NonNull View itemView)
    {
        super(itemView);
        this.dataInformation = itemView.findViewById(R.id.recycler_view_text);
    }
    }
}