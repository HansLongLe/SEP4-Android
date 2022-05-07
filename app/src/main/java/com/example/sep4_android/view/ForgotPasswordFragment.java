package com.example.sep4_android.view;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sep4_android.R;
import com.google.firebase.auth.FirebaseAuth;


public class ForgotPasswordFragment extends Fragment {

    private FirebaseAuth mAuth;
    private View view;
    private TextView err;
    private EditText in;
    private Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_forgot_password, container, false);
        init();
        if (in.getText() != null)
        btn.setOnClickListener(v -> resetPassword(in.getText().toString()));
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
            setError("Please enter your email address", "#FF0000");
            return;
        }

        mAuth.setLanguageCode("en");
        setError("", "#000000");
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(requireActivity(), task -> {
                    //
                })
                .addOnSuccessListener(requireActivity(), task -> {
                    setError("A reset password email has been sent to " + email, "#00FF00");
                })
                .addOnFailureListener(requireActivity(), task -> {
                    setError(task.getMessage(), "#FF0000");
                });
    }

    private void setError(String message, String color) {
        err.setTextColor(Color.parseColor(color));
        err.setText(message);
    }
}