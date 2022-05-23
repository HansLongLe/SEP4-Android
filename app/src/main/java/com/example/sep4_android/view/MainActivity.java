package com.example.sep4_android.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.sep4_android.R;
import com.example.sep4_android.model.Roles;
import com.example.sep4_android.model.User;
import com.example.sep4_android.model.UserList;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation();
    }

    private void bottomNavigation() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        navController = Navigation.findNavController(this,  R.id.fragmentContainerViewMainActivity);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar, menu);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            DatabaseReference ref = db.getReference();
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    UserList users = snapshot.getValue(UserList.class);
                    if (users != null) {
                        if (users.containsKey(user.getUid())) {
                            User userInDb = users.get(user.getUid());
                            // if the user is an admin
                            if (userInDb.role.equals(Roles.ADMIN.name().toLowerCase())) {
                                // hide menu item
                                menu.findItem(R.id.users_list).setVisible(true);
                                menu.findItem(R.id.create_user).setVisible(true);
                            } else {
                                menu.findItem(R.id.users_list).setVisible(false);
                                menu.findItem(R.id.create_user).setVisible(false);
                            }
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {}
            });
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

       switch (item.getItemId()) {
           case R.id.users_list:
               getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewMainActivity, new UserListFragment()).commit();
               break;
           case R.id.log_out:
               FirebaseAuth.getInstance().signOut();
               finish();
               startActivity(new Intent(this, LoginCreateAccountPage.class));
               break;
           case R.id.create_user:
               getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerViewMainActivity, new CreateUserFragment()).commit();
               break;
       }
        return true;
    }
}