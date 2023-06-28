package com.Soni5.vibehub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.Soni5.vibehub.Fragment.HomeFragment;
import com.Soni5.vibehub.Fragment.NotificationFragment;
import com.Soni5.vibehub.Fragment.ProfileFragment;
import com.Soni5.vibehub.Fragment.SearchFragment;
import com.Soni5.vibehub.Fragment.VibeFragment;
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
        NotificationFragment notificationFragment = new NotificationFragment();
       VibeFragment vibeFragment = new VibeFragment();
        SearchFragment searchFragment = new SearchFragment();


        bottomNavigation = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                int itemId = item.getItemId();
                if (itemId == R.id.navigation_home) {
                    frag(profileFragment);
                    return true;


                } else if (itemId == R.id.navigation_search) {
                    frag(searchFragment);
                    return true;


                } else if (itemId == R.id.navigation_Vibe) {
                    frag(vibeFragment);
                    return true;
                }

                else if (itemId == R.id.navigation_notifications) {
                    frag(notificationFragment);
                    return true;
                }

                else if (itemId == R.id.navigation_profile) {

                    frag(profileFragment);
                    return true;
                }
                return false;




            }

        });

    }
    void  frag(Fragment fragment)
    {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }
}