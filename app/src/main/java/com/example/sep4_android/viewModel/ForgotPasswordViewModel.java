package com.example.sep4_android.viewModel;

import android.content.res.Resources;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4_android.App;
import com.example.sep4_android.R;
import com.example.sep4_android.model.AuthStatus;
import com.example.sep4_android.model.Statuses;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordViewModel extends ViewModel {
    private final FirebaseAuth mAuth;
    private final MutableLiveData<AuthStatus> authStatus;
    private final Resources res;

    public ForgotPasswordViewModel() {
        mAuth = FirebaseAuth.getInstance();
        authStatus = new MutableLiveData<>();
        res = App.getContext().getResources();
    }

    public void resetPassword(String email) {
        if (email == null || email.equals("")) {
            authStatus.setValue(new AuthStatus(Statuses.ERROR.name(), res.getString(R.string.email_empty_error), res.getString(0+R.color.red)));
            return;
        }

        mAuth.setLanguageCode("en");
        authStatus.setValue(new AuthStatus(Statuses.IDLE.name(), "", res.getString(0+R.color.black)));
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(task -> {
                    //
                })
                .addOnSuccessListener(task -> authStatus.setValue(new AuthStatus(Statuses.SUCCESS.name(), res.getString(R.string.reset_email_sent, email), res.getString(0+R.color.green))))
                .addOnFailureListener(task -> authStatus.setValue(new AuthStatus(Statuses.ERROR.name(), task.getMessage(), res.getString(0+R.color.red))));
    }

    public LiveData<AuthStatus> getAuthStatus() {
        return authStatus;
    }
}
