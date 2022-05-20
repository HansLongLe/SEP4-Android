package com.example.sep4_android.view;

import androidx.lifecycle.ViewModelProvider;

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
import android.widget.Toast;

import com.example.sep4_android.R;
import com.example.sep4_android.adapters.HumidityAdapter;
import com.example.sep4_android.adapters.TemperatureAdapter;
import com.example.sep4_android.model.Humidity;
import com.example.sep4_android.model.Temperature;
import com.example.sep4_android.viewModel.HumidityViewModel;
import com.example.sep4_android.viewModel.TempViewModel;

import java.util.ArrayList;

public class TempFragment extends Fragment {

    private TempViewModel mViewModel;

    private TemperatureAdapter temperatureAdapter;
    private ArrayList<Temperature> temperatureArrayList;

    private String[] items = {"Last hour", "Today", "Past 7 days", "Last month"};
    private View view;

    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> arrayAdapter;

    public static TempFragment newInstance() {
        return new TempFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.temp_fragment, container, false);


        RecyclerView recyclerView = view.findViewById(R.id.temperature_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        temperatureAdapter = new TemperatureAdapter(temperatureArrayList);

        mViewModel = new ViewModelProvider(this).get(TempViewModel.class);
        mViewModel.getTemperature().observe(getViewLifecycleOwner(), temperatureList -> {
            this.temperatureArrayList = temperatureList;
            temperatureAdapter.updateTemperatureData(temperatureList);
        });
        recyclerView.setAdapter(temperatureAdapter);

        autoCompleteTextView = view.findViewById(R.id.auto_comlete_text_view4);
        arrayAdapter = new ArrayAdapter<String>(view.getContext(), R.layout.dropdown_menu_item,items);
        autoCompleteTextView.setAdapter(arrayAdapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(view.getContext(), "Item:"+item,Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TempViewModel.class);
        // TODO: Use the ViewModel
    }

}