package com.lukmo.kamsos.UserInterface;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.lukmo.kamsos.R;
import com.lukmo.kamsos.Utils.Constants;

import static com.lukmo.kamsos.Utils.Constants.TOKEN;

public class ProfileActivity extends AppCompatActivity {


    private Button mBtLogout;

    private ProgressBar mProgressbar;

    private SharedPreferences mSharedPreferences;
    private String mToken;
    private String mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String token = PreferenceManager.getDefaultSharedPreferences(this).getString(TOKEN, null);

        if(token == null) {
            startActivity(new Intent(this, MainActivity.class));
        }

        initViews();
        initSharedPreferences();
    }

    private void initViews() {
        mBtLogout = (Button) findViewById(R.id.btn_logout);
        mProgressbar = (ProgressBar) findViewById(R.id.progress);

        mBtLogout.setOnClickListener(view -> logout());
    }

    private void logout() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(Constants.TOKEN, "");
        editor.putString(Constants.EMAIL, "");
        editor.apply();

        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(intent);

        finish();
    }

    private void initSharedPreferences() {

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mToken = mSharedPreferences.getString(Constants.TOKEN,"");
        mEmail = mSharedPreferences.getString(Constants.EMAIL,"");
    }

//    @Override
//    public void onBackPressed() {
//        finish();
//    }

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

//    @Override
//    int getContentViewId() {
//        return R.layout.activity_profile;
//    }
//
//    @Override
//    int getNavigationMenuItemId() {
//        return R.id.navigation_account;
//    }
}
