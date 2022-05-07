package com.example.sep4_android.view;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.security.identity.NoAuthenticationKeyAvailableException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sep4_android.R;
import com.example.sep4_android.viewModel.CreateAccountViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class CreateAccountFragment extends Fragment {

    private FirebaseAuth mAuth;
    private Intent mainActivityIntent;
    private CreateAccountViewModel mViewModel;
    private View view;
    private TextView title, errorMsg, redirectLink;
    private EditText email, password, passwordConfirm;
    private Button btn;

    public static CreateAccountFragment newInstance() {
        return new CreateAccountFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.create_account_fragment, container, false);
        init();
        setTitleGradient();

        redirectLink.setOnClickListener(v -> redirectToLogin());
        btn.setOnClickListener(v -> {
            if (email.getText() != null
                    && password.getText() != null
                    && passwordConfirm.getText() != null) {
                createAccount(email.getText().toString(),
                        password.getText().toString(),
                        passwordConfirm.getText().toString());
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) startActivity(mainActivityIntent);
    }

    private void init() {
        mViewModel = new ViewModelProvider(this).get(CreateAccountViewModel.class);
        mAuth = FirebaseAuth.getInstance();
        mainActivityIntent = new Intent(getActivity(), MainActivity.class);
        title = view.findViewById(R.id.createAccountTitle);
        errorMsg = view.findViewById(R.id.createAccountError);
        btn = view.findViewById(R.id.createAccountBtn);
        redirectLink = view.findViewById(R.id.loginRedirect);
        email = view.findViewById(R.id.createAccountEmail);
        password = view.findViewById(R.id.createAccountPassword);
        passwordConfirm = view.findViewById(R.id.createAccountConfirmPassword);
    }

    private void setTitleGradient() {
        Shader titleShader = new LinearGradient(0, 0, title.getPaint().measureText(title.getText().toString()), title.getTextSize(),
                new int[]{
                        Color.parseColor("#1A73E9"),
                        Color.parseColor("#6C92F4")
                }, null, Shader.TileMode.CLAMP);
        title.setTextColor(Color.parseColor("#1A73E9"));
        title.getPaint().setShader(titleShader);
    }

    private void createAccount(String email, String password, String passwordConfirm) {
        setError("", "#000000");

        if (email == null || email.equals("")) {
            setError("Please enter your email address", "#FF0000");
            return;
        }

        if (password == null || password.equals("")) {
            setError("Please enter a password", "#FF0000");
            return;
        }

        if (!password.equals(passwordConfirm)) {
            setError("Passwords do not match. Please try again", "#FF0000");
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), task -> {
                    if (task.isSuccessful())
                        // Create account successful
                        startActivity(mainActivityIntent);
                    else
                        setError(Objects.requireNonNull(task.getException()).getMessage(), "#FF0000");
                });
        startActivity(mainActivityIntent);
    }

    private void redirectToLogin() {
        getParentFragmentManager().beginTransaction().replace(R.id.authFragment, new LoginFragment()).commit();
    }

    private void setError(String message, String color) {
        errorMsg.setTextColor(Color.parseColor(color));
        errorMsg.setText(message);
    }

}