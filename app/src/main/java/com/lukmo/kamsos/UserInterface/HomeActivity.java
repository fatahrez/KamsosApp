package com.lukmo.kamsos.UserInterface;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lukmo.kamsos.R;
import com.lukmo.kamsos.Utils.Constants;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import static com.lukmo.kamsos.Utils.Constants.TOKEN;

public class HomeActivity extends AppCompatActivity {
    private CardView vetCardView;
    private CardView agrovetCardView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String token = PreferenceManager.getDefaultSharedPreferences(this).getString(TOKEN, null);

        if(token == null) {
            startActivity(new Intent(this, MainActivity.class));
        }

        vetCardView = (CardView) findViewById(R.id.to_vet_card_view);
        agrovetCardView = (CardView) findViewById(R.id.to_agrovet_card_view);

        vetCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, VetActivity.class));
            }
        });

        agrovetCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AgrovetActivity.class));
            }
        });
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
}
