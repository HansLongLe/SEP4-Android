package com.example.sep4_android.view;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sep4_android.R;
import com.example.sep4_android.viewModel.LoginViewModel;

public class LoginFragment extends Fragment {

    private LoginViewModel mViewModel;
    private Intent mainActivityIntent;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        mainActivityIntent = new Intent(getActivity(), MainActivity.class);
        Button btn = view.findViewById(R.id.loginBtn);
        TextView redirectLink = view.findViewById(R.id.createAccountRedirect);
        redirectLink.setOnClickListener(v -> redirectToCreateAccount());
        btn.setOnClickListener(v -> login());
        return view;
    }

    private void login() {
        startActivity(mainActivityIntent);
    }

    private void redirectToCreateAccount() {
        getParentFragmentManager().beginTransaction().replace(R.id.authFragment, new CreateAccountFragment()).commit();
    }
}