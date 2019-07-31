package com.lukmo.kamsos.UserInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.lukmo.kamsos.R;

public class VetActivity extends Main2Activity {

    @Override
    int getContentViewId() {
        return R.layout.activity_vet;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_vets;
    }
}
