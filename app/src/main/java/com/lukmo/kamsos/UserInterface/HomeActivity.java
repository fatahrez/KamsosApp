package com.lukmo.kamsos.UserInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lukmo.kamsos.R;
import com.lukmo.kamsos.Utils.PreferenceUtil;

import androidx.cardview.widget.CardView;

public class HomeActivity extends Main2Activity {
    private CardView vetCardView;
    private CardView agrovetCardView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
    int getContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_home;
    }
}
