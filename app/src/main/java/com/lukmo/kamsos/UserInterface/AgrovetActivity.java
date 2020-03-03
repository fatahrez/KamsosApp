package com.lukmo.kamsos.UserInterface;


import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.lukmo.kamsos.R;
import com.lukmo.kamsos.Utils.Constants;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                PreferenceManager.getDefaultSharedPreferences(this).edit().remove(Constants.TOKEN).apply();
                startActivity(new Intent(this, MainActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
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
