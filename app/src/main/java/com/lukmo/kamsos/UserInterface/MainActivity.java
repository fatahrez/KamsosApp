package com.lukmo.kamsos.UserInterface;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.preference.PreferenceManager;
import android.util.Log;

import com.lukmo.kamsos.Models.Vet.Result;
import com.lukmo.kamsos.Models.Vet.Vet;
import com.lukmo.kamsos.Presenters.UserPresenter;
import com.lukmo.kamsos.R;
import com.lukmo.kamsos.UserInfrastructure.UserInfrastructure;
import com.lukmo.kamsos.Utils.Constants;

import java.util.List;

import static com.lukmo.kamsos.Utils.Constants.TOKEN;

public class MainActivity extends AppCompatActivity implements UserInfrastructure.View {

    public static final String TAG = MainActivity.class.getSimpleName();
    private static final String DEFAULT_VALUE = "default";

    public UserInfrastructure.Presenter mPresenter;
    private LoginFragment mLoginFragment;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        sharedPreferences = this.getSharedPreferences("sharedPreferences", MODE_PRIVATE);
//        Log.i(TAG, "onCreate: shredPref " + sharedPreferences);
//        String token = sharedPreferences.getString(TOKEN, null);
        String token = PreferenceManager.getDefaultSharedPreferences(this).getString(TOKEN, null);
        Log.i(TAG, "loadFragment: token " + token);

        if (token != null) {
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
        } else {
            mPresenter = new UserPresenter(this);
            mPresenter.start();
        }
    }

    private void loadFragment() {
        if (mLoginFragment == null) {
            mLoginFragment = new LoginFragment();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFrame, mLoginFragment, LoginFragment.TAG).commit();
    }

    @Override
    public void init() {
        mLoginFragment = new LoginFragment();
        loadFragment();
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void loadDataInList(List<Result> vets) {

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
