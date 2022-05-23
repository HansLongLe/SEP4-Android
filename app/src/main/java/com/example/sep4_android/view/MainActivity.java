package com.example.sep4_android.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.sep4_android.R;
import com.example.sep4_android.model.Roles;
import com.example.sep4_android.model.User;
import com.example.sep4_android.model.UserList;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    private boolean buttonState = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation();
        onWindowButtonClick();
    }

    private void bottomNavigation() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        navController = Navigation.findNavController(this,  R.id.fragmentContainerViewMainActivity);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

    public void onWindowButtonClick() {
        FloatingActionButton floatingActionButton = findViewById(R.id.windowButton);
        floatingActionButton.setOnClickListener(view -> {
            if(buttonState) {
                floatingActionButton.setImageResource(R.drawable.ic_window);
                floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.main_color)));
                buttonState = false;
            }
            else if (!buttonState) {
                floatingActionButton.setImageResource(R.drawable.ic_motion);
                floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                buttonState = true;
            }
        });
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
                    Log.w("USERS", users.size()+"");
                    if (users != null) {
                        if (users.containsKey(user.getUid())) {
                            User userInDb = users.get(user.getUid());
                            // if the user is an admin
                            if (userInDb.role.equals(Roles.ADMIN.name().toLowerCase())) {
                                // hide menu item
                                Log.w("TEST", "ISADMIN");
                                menu.findItem(R.id.users_list).setVisible(true);
                            } else {
                                Log.w("TEST", "ISUser");
                                menu.findItem(R.id.users_list).setVisible(false);
                            }
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
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
       }
        return true;
    }
}