package com.example.sep4_android.view;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import com.example.sep4_android.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;


public class ForgotPasswordFragment extends Fragment {

    private FirebaseAuth mAuth;
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
        btn.setOnClickListener(v -> resetPassword(in.getEditText().getText().toString()));
        return view;
    }

    private void init() {
        mAuth = FirebaseAuth.getInstance();
        err = view.findViewById(R.id.forgotPassError);
        in = view.findViewById(R.id.forgotPassIn);
        btn = view.findViewById(R.id.forgotPassBtn);
    }

    private void resetPassword(String email) {
        if (email == null || email.equals("")) {
            setError(getString(R.string.email_empty_error), getString(0+R.color.red));
            return;
        }

        mAuth.setLanguageCode("en");
        setError("", getString(0+R.color.black));
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(requireActivity(), task -> {
                    //
                })
                .addOnSuccessListener(requireActivity(), task -> {
                    setError(getString(R.string.reset_email_sent, email), getString(0+R.color.green));
                })
                .addOnFailureListener(requireActivity(), task -> {
                    setError(task.getMessage(), getString(0+R.color.red));
                });
    }

    private void setError(String message, String color) {
        err.setTextColor(Color.parseColor(color));
        err.setText(message);
    }
}