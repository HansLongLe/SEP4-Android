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
import com.example.sep4_android.model.Humidity;
import com.example.sep4_android.viewModel.CO2ViewModel;
import com.example.sep4_android.viewModel.HumidityViewModel;

import java.util.ArrayList;

public class HumidityFragment extends Fragment {

    private HumidityViewModel mViewModel;
    private HumidityAdapter humidityAdapter;
    private ArrayList<Humidity> humidityArrayList;

    private String[] items = {"Last hour", "Today", "Past 7 days", "Last month"};
    private View view;

    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> arrayAdapter;

    public static HumidityFragment newInstance() {
        return new HumidityFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.humidity_fragment, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.humidity_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        humidityAdapter = new HumidityAdapter(humidityArrayList);

        mViewModel = new ViewModelProvider(this).get(HumidityViewModel.class);
        mViewModel.getHumidity().observe(getViewLifecycleOwner(), humidityList -> {
            this.humidityArrayList = humidityList;
            humidityAdapter.updateHumidityData(humidityList);
        });
        recyclerView.setAdapter(humidityAdapter);





        autoCompleteTextView = view.findViewById(R.id.auto_comlete_text_view2);
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
        mViewModel = new ViewModelProvider(this).get(HumidityViewModel.class);
        // TODO: Use the ViewModel
    }

}