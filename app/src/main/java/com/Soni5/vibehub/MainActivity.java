package com.Soni5.vibehub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.Soni5.vibehub.Fragment.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       BottomNavigationView bottomNavigationView;

       HomeFragment homeFragment = new HomeFragment();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.navigation_home) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                }
                else if ( item.getItemId() == R.id.navigation_dashboard)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                }
                else if ( item.getItemId() == R.id.navigation_notifications)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                }
                return false;
            }

        });

    }
}