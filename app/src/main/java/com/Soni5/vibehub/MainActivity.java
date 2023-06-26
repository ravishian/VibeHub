package com.Soni5.vibehub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.Soni5.vibehub.Fragment.HomeFragment;
import com.Soni5.vibehub.Fragment.ProfileFragment;
import com.Soni5.vibehub.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



       BottomNavigationView bottomNavigation;

       HomeFragment homeFragment = new HomeFragment();
       ProfileFragment profileFragment = new ProfileFragment();

        bottomNavigation = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                int itemId = item.getItemId();
                if (itemId == R.id.navigation_home) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                    // Handle Home tab selection
                    return true;
                } else if (itemId == R.id.navigation_dashboard) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                    // Handle Dashboard tab selection
                    return true;
                } else if (itemId == R.id.navigation_notifications) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).commit();
                    // Handle Notifications tab selection
                    return true;
                }
                return false;




            }

        });

    }
}