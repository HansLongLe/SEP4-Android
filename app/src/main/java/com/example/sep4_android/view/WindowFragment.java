package com.example.sep4_android.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sep4_android.R;
import com.example.sep4_android.model.Window;
import com.example.sep4_android.viewModel.WindowViewModel;

import java.util.ArrayList;

public class WindowFragment extends Fragment {

    private View view;
    private WindowViewModel viewModel;
    private Window window;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.window_fragment, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(WindowViewModel.class);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.getWindowState().observe(getViewLifecycleOwner(), window -> {
            this.window = window;
            TextView currentState = view.findViewById(R.id.current_window);
            if (window.getWindowOpen())
            {
                currentState.setText("Opened");
            }
            else {
                currentState.setText("Closed");
            }
        });
    }
}
