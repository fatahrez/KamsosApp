package com.lukmo.kamsos.UserInterface;

import android.os.Bundle;

import com.lukmo.kamsos.R;
import com.lukmo.kamsos.Utils.PreferenceUtil;

public class HomeActivity extends Main2Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
