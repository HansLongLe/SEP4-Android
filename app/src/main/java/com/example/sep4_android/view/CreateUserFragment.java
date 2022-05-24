package com.example.sep4_android.view;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.example.sep4_android.R;
import com.example.sep4_android.model.User;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateUserFragment extends Fragment {

    private FirebaseAuth mAuth1;
    private FirebaseAuth mAuth2;
    private View view;
    private TextInputLayout email, password;
    private Button saveButton;
    private DatabaseReference databaseReference;

    public CreateUserFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_create_user, container, false);
        init();

        saveButton.setOnClickListener(v -> {
            if(email.getEditText() != null && password.getEditText() != null) {
                createAccount(email.getEditText().getText().toString(), password.getEditText().getText().toString());
            }
        });

        return view;
    }

    private void init() {
        email =  view.findViewById(R.id.addEmail);
        password = view.findViewById(R.id.password);
        saveButton = view.findViewById(R.id.addUserButton);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth1 = FirebaseAuth.getInstance();

        FirebaseOptions firebaseOptions = new FirebaseOptions.Builder().setDatabaseUrl("https://smart-gym-c02ac-default-rtdb.europe-west1.firebasedatabase.app")
                .setApiKey("AIzaSyDsxP6jEwgA-h56OOxFldtItyoXtC7YFIE")
                .setApplicationId("smart-gym-c02ac").build();

        try { FirebaseApp myApp = FirebaseApp.initializeApp(getContext(), firebaseOptions, "Smart Gym");
            mAuth2 = FirebaseAuth.getInstance(myApp);
        } catch (IllegalStateException e){
            mAuth2 = FirebaseAuth.getInstance(FirebaseApp.getInstance("Smart Gym"));
        }
    }

    private void createAccount(String email, String password) {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        mAuth2.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), task -> {
                    if(task.isSuccessful()) {
                        if (task.getResult().getUser() != null) {
                            databaseReference.child("users").child(task.getResult().getUser().getUid()).setValue(new User(email));
                        }
                        Toast.makeText(getContext(), email + " added to user list.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(requireActivity(), MainActivity.class));
                    }
                        else
                        {
//                            Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
}

