package com.lukmo.kamsos.UserInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.lukmo.kamsos.R;


import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;

public abstract class Main2Activity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener {

    protected BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getContentViewId());

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
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        if (item.getItemId() != getNavigationMenuItemId()){
            switch (item.getItemId()){
                case R.id.navigation_home:
                    Intent homeIntent = new Intent(this, HomeActivity.class);
                    startActivity(homeIntent);
                    break;
                case R.id.navigation_vets:
                    Intent vetIntent = new Intent(this, VetActivity.class);
                    startActivity(vetIntent);
                    break;
                case R.id.navigation_agrovets:
                    Intent agrovetIntent = new Intent(this, AgrovetActivity.class);
                    startActivity(agrovetIntent);
                    break;
                case R.id.navigation_account:
                    Intent accountIntent = new Intent(this, ProfileActivity.class);
                    startActivity(accountIntent);
                    break;
            }
            finish();
        }
        return true;
    }


    private void updateNavigationBarState() {
        int actionId = getNavigationMenuItemId();
        selectBottomNavigationBarItem(actionId);
    }

    void selectBottomNavigationBarItem(int itemId) {
        Menu menu = navigationView.getMenu();
        for (int i =0, size = menu.size(); i< size; i++){
            MenuItem item = menu.getItem(i);
            boolean shouldBeChecked = item.getItemId() == itemId;
            if (shouldBeChecked){
                item.setChecked(true);
                break;
            }
        }
    }

    abstract int getContentViewId();
    abstract int getNavigationMenuItemId();
}
