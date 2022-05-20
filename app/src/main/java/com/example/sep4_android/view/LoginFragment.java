package com.example.sep4_android.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4_android.R;
import com.example.sep4_android.model.User;
import com.example.sep4_android.viewModel.LoginViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class LoginFragment extends Fragment {
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private GoogleSignInClient oneTapClient;
//    private BeginSignInRequest signInRequest;
    // private GoogleSignInClient googleSignInClient;
    private LoginViewModel mViewModel;
    private TextView redirectLink, forgotPasswordLink, error;
    private Button btn;
    private SignInButton googleBtn;
    private View view;
    private Intent mainActivityIntent;
    private EditText email, password;
    private static final String TAG = "GOOGLE_SIGN_IN";

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login_fragment, container, false);
        init();
        setupOneTapLogin();
        btn.setOnClickListener(v -> {
            if (email.getText() != null && password.getText() != null) {
                login(email.getText().toString(), password.getText().toString());
            }
        });
        redirectLink.setOnClickListener(v -> redirectToCreateAccount());
        forgotPasswordLink.setOnClickListener(v -> redirectToForgotPassword());
        googleBtn.setOnClickListener(v -> loginWithGoogle());
        return view;
    }

    private void init() {
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        mainActivityIntent = new Intent(getActivity(), MainActivity.class);
        email = view.findViewById(R.id.loginEmail);
      password = view.findViewById(R.id.loginPassword);
        error = view.findViewById(R.id.loginError);
        btn = view.findViewById(R.id.loginBtn);
        googleBtn = view.findViewById(R.id.loginGoogleBtn);
        redirectLink = view.findViewById(R.id.createAccountRedirect);
        forgotPasswordLink = view.findViewById(R.id.forgotPasswordLink);
    }

    private void setupOneTapLogin() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client))
                .requestEmail()
                .build();
        oneTapClient = GoogleSignIn.getClient(requireActivity(), gso);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {
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
                                .addOnCompleteListener(requireActivity(), task -> {
                                    if (task.isSuccessful()) {
                                        // add user to db
                                        FirebaseUser currentUser = task.getResult().getUser();
                                        if (currentUser != null) {
                                            databaseReference.child("users").child(currentUser.getUid()).setValue(new User(currentUser.getEmail()));
                                        }
                                        startActivity(new Intent(requireActivity()
                                                , MainActivity.class)
                                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                                    } else {
                                        // Log in failed
                                        Log.w("Google log in", task.getException().getMessage());
                                    }
                                });

                    }
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void loginWithGoogle() {
        Intent intent = oneTapClient.getSignInIntent();
        startActivityForResult(intent, 100);
    }

    private void login(String email, String password) {

        if (email == null || email.equals("")) {
            setError("Please enter your email address", "#FF0000");
            return;
        }

        if (password == null || password.equals("")) {
            setError("Please enter your password", "#FF0000");
            return;
        }

        setError("", "#000000");
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), task -> {
                   if (task.isSuccessful()) {
                        // Login successful
                       startActivity(mainActivityIntent);
                   }
                   else {
                       // Login failed
                       Log.w("A", Objects.requireNonNull(task.getException()).getMessage());
                       setError(Objects.requireNonNull(task.getException()).getMessage(), "#FF0000");
                   }
                });
    }

    private void redirectToCreateAccount() {
        getParentFragmentManager().beginTransaction().replace(R.id.authFragment, new CreateAccountFragment()).commit();
    }

    private void redirectToForgotPassword() {
        getParentFragmentManager().beginTransaction().replace(R.id.authFragment, new ForgotPasswordFragment()).commit();
    }

    private void setError(String message, String color) {
        error.setTextColor(Color.parseColor(color));
        error.setText(message);
    }
}