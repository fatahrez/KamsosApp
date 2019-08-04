package com.lukmo.kamsos.UserInterface;


import android.os.Bundle;

import com.lukmo.kamsos.R;

public class AgrovetActivity extends Main2Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agrovet);
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_agrovet;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_agrovets;
    }
}
