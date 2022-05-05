package com.example.sep4_android.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sep4_android.R;
import com.example.sep4_android.model.Motion;

import java.util.ArrayList;

public class MotionAdapter extends RecyclerView.Adapter<MotionAdapter.ViewHolder> {

    private ArrayList<Motion> motionData;

    public MotionAdapter (ArrayList<Motion> motionData){
        this.motionData = motionData;
    }

    @NonNull
    @Override
    public MotionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_view_item, parent, false);
        int height = parent.getHeight()/8;
        int width = parent.getWidth();
        view.setLayoutParams(new RecyclerView.LayoutParams(width,height));
        return new MotionAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MotionAdapter.ViewHolder holder, int position) {
        holder.dataInformation.setText(motionData.get(position).getMotionData());
    }

    @Override
    public int getItemCount() {
        return motionData.size();
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
