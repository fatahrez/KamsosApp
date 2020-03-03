package com.lukmo.kamsos.UserInterface;


import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

import com.lukmo.kamsos.R;

import static com.lukmo.kamsos.Utils.Constants.TOKEN;

public class AgrovetActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agrovet);

        String token = PreferenceManager.getDefaultSharedPreferences(this).getString(TOKEN, null);

        if(token == null) {
            startActivity(new Intent(this, MainActivity.class));
        }

    }

    /*@Override
    int getContentViewId() {
        return R.layout.activity_agrovet;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_agrovets;
    }*/
}
