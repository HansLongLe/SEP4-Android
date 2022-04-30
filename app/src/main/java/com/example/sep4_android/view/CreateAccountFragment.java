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

public class CreateAccountFragment extends Fragment {

    private Intent mainActivityIntent;
    private CreateAccountViewModel mViewModel;
    private TextView title, errorMsg;
    public static CreateAccountFragment newInstance() {
        return new CreateAccountFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(CreateAccountViewModel.class);
        View view = inflater.inflate(R.layout.create_account_fragment, container, false);
        mainActivityIntent = new Intent(getActivity(), MainActivity.class);
        title = view.findViewById(R.id.createAccountTitle);
        errorMsg = view.findViewById(R.id.createAccountError);
        Button btn = view.findViewById(R.id.createAccountBtn);
        TextView redirectLink = view.findViewById(R.id.loginRedirect);
        EditText email = view.findViewById(R.id.createAccountEmail);
        EditText username = view.findViewById(R.id.createAccountUsername);
        EditText password = view.findViewById(R.id.createAccountPassword);
        EditText passwordConfirm = view.findViewById(R.id.createAccountConfirmPassword);
        setTitleGradient();

        redirectLink.setOnClickListener(v -> redirectToLogin());
        btn.setOnClickListener(v -> {
            if (email.getText() != null
            && username.getText() != null
            && password.getText() != null
            && passwordConfirm.getText() != null) {
                createAccount(email.getText().toString(),
                        username.getText().toString(),
                        password.getText().toString(),
                        passwordConfirm.getText().toString());
            }
        });
        return view;
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

    private void createAccount(String email, String username, String password, String passwordConfirm) {
        // logic to create account here
        startActivity(mainActivityIntent);
    }

    private void redirectToLogin() {
        getParentFragmentManager().beginTransaction().replace(R.id.authFragment, new LoginFragment()).commit();
    }

}