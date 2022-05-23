package com.example.sep4_android.view;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sep4_android.R;
import com.example.sep4_android.adapters.CO2Adapter;
import com.example.sep4_android.model.CO2;
import com.example.sep4_android.viewModel.CO2ViewModel;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CO2Fragment extends Fragment {

    private CO2ViewModel mViewModel;
    private CO2Adapter co2Adapter;
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    private ArrayList<CO2> co2s;
    private ArrayList<CO2> filteredCo2s;

    private String[] items = {"Last hour", "Today", "Past 7 days", "Last month"};
    private View view;

    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> arrayAdapter;

    public static CO2Fragment newInstance() {
        return new CO2Fragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.c_o2_fragment, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.co2_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        co2Adapter = new CO2Adapter(filteredCo2s);


        mViewModel = new ViewModelProvider(this).get(CO2ViewModel.class);
        mViewModel.getCO2().observe(getViewLifecycleOwner(), co2List -> {
            this.co2s = co2List;
            filteredCo2s = co2s;
            co2Adapter.updateCO2Data(co2List);
            TextView currentCO2 = view.findViewById(R.id.current_c_02);
            currentCO2.setText(co2s.get(0).getCo2Level() + "ppm");
        });
        recyclerView.setAdapter(co2Adapter);


        autoCompleteTextView = view.findViewById(R.id.auto_complete_text_view);
        arrayAdapter = new ArrayAdapter<String>(view.getContext(), R.layout.dropdown_menu_item,items);
        autoCompleteTextView.setAdapter(arrayAdapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                if (!co2s.isEmpty())
                {
                    filteredCo2s = new ArrayList<>();
                switch (adapterView.getItemAtPosition(i).toString()){
                    case "Last hour":
                        for (CO2 co2: co2s) {
                            if (timestamp.getTime() - co2.getTime().getTime() <= 3600000)
                            {
                                filteredCo2s.add(co2);
                                co2Adapter.updateCO2Data(filteredCo2s);
                            }
                        }
                        break;
                    case "Today":
                        for (CO2 co2: co2s) {
                            if (co2.getTime().getYear() == timestamp.getYear() && co2.getTime().getMonth() == timestamp.getMonth()
                                    && co2.getTime().getDate() == timestamp.getDate())
                            {
                                filteredCo2s.add(co2);
                                co2Adapter.updateCO2Data(filteredCo2s);
                            }
                        }
                        break;
                    case "Past 7 days":
                        for (CO2 co2: co2s) {
                            if ((timestamp.getTime() - co2.getTime().getTime())/1000 <= 604800)
                            {
                                filteredCo2s.add(co2);
                                co2Adapter.updateCO2Data(filteredCo2s);
                            }
                        }
                        break;
                    case "Last month":
                        for (CO2 co2: co2s) {
                            if ((timestamp.getTime() - co2.getTime().getTime())/1000 <= 2628000){
                                filteredCo2s.add(co2);
                                co2Adapter.updateCO2Data(filteredCo2s);
                            }
                        }
                        break;
                }
                }

            }
        });
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CO2ViewModel.class);
        // TODO: Use the ViewModel
    }

}