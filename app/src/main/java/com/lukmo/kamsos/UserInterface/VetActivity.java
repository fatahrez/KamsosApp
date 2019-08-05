package com.lukmo.kamsos.UserInterface;

import android.os.Bundle;

import com.lukmo.kamsos.R;

public class VetActivity extends Main2Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vet);

    }

    @Override
    int getContentViewId() {
        return R.layout.activity_vet;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_vets;
    }
}
