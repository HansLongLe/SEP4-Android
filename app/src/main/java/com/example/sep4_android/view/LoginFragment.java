package com.example.sep4_android.view;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4_android.R;
import com.example.sep4_android.model.Statuses;
import com.example.sep4_android.viewModel.LoginViewModel;
import com.google.android.gms.common.SignInButton;
import com.google.android.material.textfield.TextInputLayout;

public class LoginFragment extends Fragment {
    private LoginViewModel mViewModel;
    private TextView redirectLink, forgotPasswordLink, error, title;
    private Button btn;
    private SignInButton googleBtn;
    private View view;
    private Intent mainActivityIntent;
    private TextInputLayout email, password;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login_fragment, container, false);
        init();
        mViewModel.setupOneTapLogin();
        ((LoginCreateAccountPage) requireActivity()).setTitleGradient(title);
        btn.setOnClickListener(v -> {
            if (email.getEditText() != null && password.getEditText() != null) {
                mViewModel.login(email.getEditText().getText().toString(), password.getEditText().getText().toString());
            }
        });
        redirectLink.setOnClickListener(v -> redirectToCreateAccount());
        forgotPasswordLink.setOnClickListener(v -> redirectToForgotPassword());
        googleBtn.setOnClickListener(v -> mViewModel.triggerGooglePopup());
        requireActivity()
                .getOnBackPressedDispatcher()
                .addCallback(requireActivity(), new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        getParentFragmentManager().beginTransaction().replace(container.getId(), new CreateAccountFragment()).commit();
                    }
                });
        return view;
    }

    private void init() {
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        mainActivityIntent = new Intent(getActivity(), MainActivity.class);
        title = view.findViewById(R.id.loginTitle);
        email = view.findViewById(R.id.loginEmail);
        password = view.findViewById(R.id.loginPassword);
        error = view.findViewById(R.id.loginError);
        btn = view.findViewById(R.id.loginBtn);
        googleBtn = view.findViewById(R.id.loginGoogleBtn);
        redirectLink = view.findViewById(R.id.createAccountRedirect);
        forgotPasswordLink = view.findViewById(R.id.forgotPasswordLink);
        forgotPasswordLink.setPaintFlags(forgotPasswordLink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        redirectLink.setPaintFlags(redirectLink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        mViewModel.getIntentData().observe(getViewLifecycleOwner(), intent -> startActivityForResult(intent, 100));
        mViewModel.getAuthStatus().observe(getViewLifecycleOwner(), status -> {
            if (status.getStatus().equals(Statuses.SUCCESS.name())) {
                startActivity(mainActivityIntent);
            } else if (status.getStatus().equals(Statuses.ERROR.name())) {
                setMessage(status.getMsg(), status.getMsgColor());
            } else if (status.getStatus().equals(Statuses.GOOGLE_SUCCESS.name())) {
                startActivity(new Intent(requireActivity()
                        , MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            mViewModel.loginWithGoogle(data);
        }
    }

    private void redirectToCreateAccount() {
        getParentFragmentManager().beginTransaction().replace(R.id.authFragment, new CreateAccountFragment()).commit();
    }

    private void redirectToForgotPassword() {
        getParentFragmentManager().beginTransaction().replace(R.id.authFragment, new ForgotPasswordFragment()).commit();
    }

    private void setMessage(String message, String color) {
        error.setTextColor(Color.parseColor(color));
        error.setText(message);
    }
}