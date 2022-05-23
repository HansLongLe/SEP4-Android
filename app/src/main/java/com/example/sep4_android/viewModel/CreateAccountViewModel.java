package com.example.sep4_android.viewModel;

import android.content.res.Resources;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4_android.App;
import com.example.sep4_android.R;
import com.example.sep4_android.model.AuthStatus;
import com.example.sep4_android.model.Statuses;
import com.example.sep4_android.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccountViewModel extends ViewModel {
    private final FirebaseAuth mAuth;
    private final DatabaseReference databaseReference;
    private final MutableLiveData<AuthStatus> authStatus;


    public CreateAccountViewModel() {
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        authStatus = new MutableLiveData<>();
    }

    public boolean isLoggedIn() {
        FirebaseUser user = mAuth.getCurrentUser();
        return user != null;
    }

    public void createAccount(String email, String password, String passwordConfirm) {
        Resources res = App.getContext().getResources();
        authStatus.setValue(new AuthStatus(Statuses.ERROR.name(), "", res.getString(0+R.color.black)));

        if (email == null || email.equals("")) {
            authStatus.setValue(new AuthStatus(Statuses.ERROR.name(), res.getString(R.string.email_empty_error), res.getString(0+R.color.red)));
            return;
        }

        if (password == null || password.equals("")) {
            authStatus.setValue(new AuthStatus(Statuses.ERROR.name(), res.getString(R.string.password_empty_error), res.getString(0+R.color.red)));
            return;
        }

        if (!password.equals(passwordConfirm)) {
            authStatus.setValue(new AuthStatus(Statuses.ERROR.name(), res.getString(R.string.passwords_not_matching), res.getString(0+R.color.red)));
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        if (task.getResult().getUser() != null) {
                            // Create account successful
                            databaseReference.child("users").child(task.getResult().getUser().getUid()).setValue(new User(email));
                            authStatus.setValue(new AuthStatus(Statuses.SUCCESS.name(), "", res.getString(0+R.color.black)));
                        }
                    }
                    else {
                        if (task.getException() != null)
                        authStatus.setValue(new AuthStatus(Statuses.ERROR.name(), task.getException().getLocalizedMessage(), res.getString(0+R.color.red)));
                    }
                });
    }

    public LiveData<AuthStatus> getAuthStatus() {
        return authStatus;
    }
}