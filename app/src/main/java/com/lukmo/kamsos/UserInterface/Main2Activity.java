package com.lukmo.kamsos.UserInterface;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lukmo.kamsos.R;

import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;

public abstract class Main2Activity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    protected BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.element_bottom_navigation);

        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        updateNavigationBarState();
    }

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        navigationView.postDelayed(() -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home){
                startActivity(new Intent(this, HomeActivity.class));
            } else if (itemId == R.id.navigation_vets){
                startActivity(new Intent(this, VetActivity.class));
            } else if (itemId == R.id.navigation_agrovets){
                startActivity(new Intent(this, AgrovetActivity.class));
            } else if (itemId == R.id.navigation_account){
                startActivity(new Intent(this, ProfileActivity.class));
            }
            finish();
        }, 3000);
        return true;
    }


    private void updateNavigationBarState() {
        int actionId = getNavigationMenuItemId();
        selectBottomNavigationBarItem(actionId);
    }

    private void selectBottomNavigationBarItem(int itemId) {
        MenuItem menuItem = navigationView.getMenu().findItem(itemId);
        menuItem.setChecked(true);
    }

    abstract int getContentViewId();
    abstract int getNavigationMenuItemId();
}
