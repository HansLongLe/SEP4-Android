package com.example.sep4_android.repository;

import com.example.sep4_android.model.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

public class UserRepository {
    private static UserRepository instance;
    private final DatabaseReference databaseReference;

    private UserRepository(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public static UserRepository getInstance() {
        if (instance == null){
            instance = new UserRepository();
        }
        return instance;
    }

    public void createUser(Task<AuthResult> task, String email){
        databaseReference.child("users").child(task.getResult().getUser().getUid()).setValue(new User(email));
    }

    public void userIsOnline()
    {
        databaseReference.child("OnlineUsers").setValue(ServerValue.increment(1));
    }

    public void userIsOffline()
    {
        databaseReference.child("OnlineUsers").setValue(ServerValue.increment(-1));
    }
}
