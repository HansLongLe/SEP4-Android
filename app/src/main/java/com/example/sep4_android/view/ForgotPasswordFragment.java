package com.example.sep4_android.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4_android.R;
import com.example.sep4_android.viewModel.ForgotPasswordViewModel;
import com.google.android.material.textfield.TextInputLayout;


public class ForgotPasswordFragment extends Fragment {
    private ForgotPasswordViewModel mViewModel;
    private View view;
    private TextView err;
    private TextInputLayout in;
    private Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_forgot_password, container, false);
        init();
        requireActivity()
                .getOnBackPressedDispatcher()
                .addCallback(requireActivity(), new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        getParentFragmentManager().beginTransaction().replace(container.getId(), new LoginFragment()).commit();
                    }
                });
        if (in.getEditText() != null)
        btn.setOnClickListener(v -> mViewModel.resetPassword(in.getEditText().getText().toString()));
        return view;
    }

    private void init() {
        mViewModel = new ViewModelProvider(this).get(ForgotPasswordViewModel.class);
        err = view.findViewById(R.id.forgotPassError);
        in = view.findViewById(R.id.forgotPassIn);
        btn = view.findViewById(R.id.forgotPassBtn);
        mViewModel.getAuthStatus().observe(getViewLifecycleOwner(), status -> setMessage(status.getMsg(), status.getMsgColor()));
    }

    private void setMessage(String message, String color) {
        err.setTextColor(Color.parseColor(color));
        err.setText(message);
    }
}