package com.example.sep4_android.view;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sep4_android.R;
import com.example.sep4_android.viewModel.LoginViewModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class LoginFragment extends Fragment {
    private FirebaseAuth mAuth;
    private LoginViewModel mViewModel;
    private TextView redirectLink, forgotPasswordLink, error;
    private Button btn;
    private View view;
    private Intent mainActivityIntent;
    private EditText email, password;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login_fragment, container, false);
        init();
        btn.setOnClickListener(v -> {
            if (email.getText() != null && password.getText() != null) {
                login(email.getText().toString(), password.getText().toString());
            }
        });
        redirectLink.setOnClickListener(v -> redirectToCreateAccount());
        forgotPasswordLink.setOnClickListener(v -> redirectToForgotPassword());

        return view;
    }

    private void init() {
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        mAuth = FirebaseAuth.getInstance();
        mainActivityIntent = new Intent(getActivity(), MainActivity.class);
        email = view.findViewById(R.id.loginEmail);
        password = view.findViewById(R.id.loginPassword);
        error = view.findViewById(R.id.loginError);
        btn = view.findViewById(R.id.loginBtn);
        redirectLink = view.findViewById(R.id.createAccountRedirect);
        forgotPasswordLink = view.findViewById(R.id.forgotPasswordLink);
    }

    private void login(String email, String password) {
        error.setText(null);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), task -> {
                   if (task.isSuccessful()) {
                        // Login successful
                       startActivity(mainActivityIntent);
                   }
                   else {
                       // Login failed
                       Log.w("A", Objects.requireNonNull(task.getException()).getMessage());
                       error.setText(Objects.requireNonNull(task.getException()).getMessage());
                   }
                });
    }

    private void redirectToCreateAccount() {
        getParentFragmentManager().beginTransaction().replace(R.id.authFragment, new CreateAccountFragment()).commit();
    }

    private void redirectToForgotPassword() {
        getParentFragmentManager().beginTransaction().replace(R.id.authFragment, new ForgotPasswordFragment()).commit();
    }
}