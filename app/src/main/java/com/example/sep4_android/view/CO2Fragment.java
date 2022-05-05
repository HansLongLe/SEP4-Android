package com.example.sep4_android.view;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.sep4_android.R;
import com.example.sep4_android.viewModel.CO2ViewModel;

public class CO2Fragment extends Fragment {

    private CO2ViewModel mViewModel;

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

        autoCompleteTextView = view.findViewById(R.id.auto_complete_text_view);
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
        mViewModel = new ViewModelProvider(this).get(CO2ViewModel.class);
        // TODO: Use the ViewModel
    }

}