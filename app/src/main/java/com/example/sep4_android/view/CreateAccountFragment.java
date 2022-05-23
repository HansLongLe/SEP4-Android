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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4_android.R;
import com.example.sep4_android.model.User;
import com.example.sep4_android.viewModel.CreateAccountViewModel;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class CreateAccountFragment extends Fragment {

    private FirebaseAuth mAuth;
    private Intent mainActivityIntent;
    private CreateAccountViewModel mViewModel;
    private View view;
    private TextView title, errorMsg, redirectLink;
    private TextInputLayout email, password, passwordConfirm;
    private Button btn;
    private DatabaseReference databaseReference;

    public static CreateAccountFragment newInstance() {
        return new CreateAccountFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.create_account_fragment, container, false);
        init();
        ((LoginCreateAccountPage) requireActivity()).setTitleGradient(title);
        redirectLink.setOnClickListener(v -> redirectToLogin());
        btn.setOnClickListener(v -> {
            if (email.getEditText() != null
                    && password.getEditText() != null
                    && passwordConfirm.getEditText() != null) {
                createAccount(email.getEditText().getText().toString(),
                        password.getEditText().getText().toString(),
                        passwordConfirm.getEditText().getText().toString());
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
        databaseReference = FirebaseDatabase.getInstance().getReference();
        mainActivityIntent = new Intent(getActivity(), MainActivity.class);
        title = view.findViewById(R.id.createAccountTitle);
        errorMsg = view.findViewById(R.id.createAccountError);
        btn = view.findViewById(R.id.createAccountBtn);
        redirectLink = view.findViewById(R.id.loginRedirect);
        redirectLink.setPaintFlags(redirectLink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        email = view.findViewById(R.id.createAccountEmail);
        password = view.findViewById(R.id.createAccountPassword);
        passwordConfirm = view.findViewById(R.id.createAccountConfirmPassword);
    }

    private void createAccount(String email, String password, String passwordConfirm) {
        setError("", getString(0+R.color.black));

        if (email == null || email.equals("")) {
            setError(getString(R.string.email_empty_error), getString(0+R.color.red));
            return;
        }

        if (password == null || password.equals("")) {
            setError(getString(R.string.password_empty_error), getString(0+R.color.red));
            return;
        }

        if (!password.equals(passwordConfirm)) {
            setError(getString(R.string.passwords_not_matching), getString(0+R.color.red));
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), task -> {
                    if (task.isSuccessful()) {
                        if (task.getResult().getUser() != null) {
                            // DOESN'T WORK???????
                            databaseReference.child("users").child(task.getResult().getUser().getUid()).setValue(new User(email));
                        }
                        // Create account successful
                        startActivity(mainActivityIntent);
                    }
                    else
                        setError(Objects.requireNonNull(task.getException()).getMessage(), getString(0+R.color.red));
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