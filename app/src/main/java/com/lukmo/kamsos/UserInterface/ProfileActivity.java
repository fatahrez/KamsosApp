package com.lukmo.kamsos.UserInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.ProgressBar;

import com.lukmo.kamsos.R;
import com.lukmo.kamsos.Utils.Constants;

public class ProfileActivity extends Main2Activity{
    @Override
    int getContentViewId() {
        return R.layout.activity_profile;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_account;
    }
//    private Button mBtLogout;
//
//    private ProgressBar mProgressbar;
//
//    private SharedPreferences mSharedPreferences;
//    private String mToken;
//    private String mEmail;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_profile);
//
//        initViews();
//        initSharedPreferences();
//    }
//
//    private void initViews() {
//        mBtLogout = (Button) findViewById(R.id.btn_logout);
//        mProgressbar = (ProgressBar) findViewById(R.id.progress);
//
//        mBtLogout.setOnClickListener(view -> logout());
//    }
//
//    private void logout() {
//        SharedPreferences.Editor editor = mSharedPreferences.edit();
//        editor.putString(Constants.TOKEN, "");
//        editor.putString(Constants.EMAIL, "");
//        editor.apply();
//
//        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
//        startActivity(intent);
//
//        finish();
//    }
//
//    private void initSharedPreferences() {
//
//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mToken = mSharedPreferences.getString(Constants.TOKEN,"");
//        mEmail = mSharedPreferences.getString(Constants.EMAIL,"");
//    }
//
//    @Override
//    public void onBackPressed() {
//        finish();
//    }
}
