package com.example.sep4_android.viewModel;

import android.content.Intent;
import android.content.res.Resources;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4_android.App;
import com.example.sep4_android.R;
import com.example.sep4_android.model.AuthStatus;
import com.example.sep4_android.model.Statuses;
import com.example.sep4_android.model.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginViewModel extends ViewModel {
    private final MutableLiveData<AuthStatus> authStatus;
    private final MutableLiveData<Intent> intentData;
    private final FirebaseAuth mAuth;
    private final DatabaseReference databaseReference;
    private GoogleSignInClient oneTapClient;
    private final Resources res;

    public LoginViewModel() {
        authStatus = new MutableLiveData<>();
        intentData = new MutableLiveData<>();
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        res = App.getContext().getResources();
    }

    public void login(String email, String password) {

        if (email == null || email.equals("")) {
            authStatus.setValue(new AuthStatus(Statuses.ERROR.name(), res.getString(R.string.email_empty_error), res.getString(0+R.color.red)));
            return;
        }

        if (password == null || password.equals("")) {
            authStatus.setValue(new AuthStatus(Statuses.ERROR.name(), res.getString(R.string.password_empty_error), res.getString(0+R.color.red)));
            return;
        }

        authStatus.setValue(new AuthStatus(Statuses.IDLE.name(), "", res.getString(0+R.color.black)));
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Login successful
                        authStatus.setValue(new AuthStatus(Statuses.SUCCESS.name(), "", res.getString(0+R.color.black)));
                    } else {
                        // Login failed
                        if (task.getException() != null)
                        authStatus.setValue(new AuthStatus(Statuses.ERROR.name(), task.getException().getLocalizedMessage(), res.getString(0+R.color.red)));
                    }
                });
    }

    public void setupOneTapLogin() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(res.getString(R.string.default_web_client))
                .requestEmail()
                .build();
        oneTapClient = GoogleSignIn.getClient(App.getContext(), gso);
    }

    public void triggerGooglePopup() {
        intentData.setValue(oneTapClient.getSignInIntent());
    }

    public void loginWithGoogle(Intent data) {
        Task<GoogleSignInAccount> signInAccountTask = GoogleSignIn
                .getSignedInAccountFromIntent(data);

        if (signInAccountTask.isSuccessful()) {
            try {
                GoogleSignInAccount googleSignInAccount = signInAccountTask
                        .getResult(ApiException.class);
                if (googleSignInAccount != null) {
                    AuthCredential authCredential = GoogleAuthProvider
                            .getCredential(googleSignInAccount.getIdToken()
                                    , null);
                    // Check credentials
                    mAuth.signInWithCredential(authCredential)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    // add user to db
                                    FirebaseUser currentUser = task.getResult().getUser();
                                    if (currentUser != null) {
                                        databaseReference.child("users").child(currentUser.getUid()).setValue(new User(currentUser.getEmail()));
                                    }
                                    authStatus.setValue(new AuthStatus(Statuses.GOOGLE_SUCCESS.name(), "", res.getString(0+R.color.black)));
                                } else {
                                    // Log in failed
                                    if (task.getException() != null)
                                    authStatus.setValue(new AuthStatus(Statuses.ERROR.name(), task.getException().getLocalizedMessage(), res.getString(0+R.color.red)));
                                }
                            });

                }
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }

    public LiveData<AuthStatus> getAuthStatus() {
        return authStatus;
    }

    public LiveData<Intent> getIntentData() {
        return intentData;
    }
}