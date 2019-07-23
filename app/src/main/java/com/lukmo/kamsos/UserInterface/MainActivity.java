package com.lukmo.kamsos.UserInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.lukmo.kamsos.Models.User;
import com.lukmo.kamsos.R;
import com.lukmo.kamsos.UserInfrastructure.UserInfrastructure;

import java.util.List;

public class MainActivity extends AppCompatActivity implements UserInfrastructure.View {

    public static final String TAG = MainActivity.class.getSimpleName();

    private LoginFragment mLoginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoginFragment = new LoginFragment();

        if (savedInstanceState == null){
            loadFragment();
        }


    }

    private void loadFragment() {
        if (mLoginFragment==null){
            mLoginFragment = new LoginFragment();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFrame, mLoginFragment, LoginFragment.TAG).commit();
    }

    @Override
    public void init() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void loadDataInList(List<User> users) {

    }
}
