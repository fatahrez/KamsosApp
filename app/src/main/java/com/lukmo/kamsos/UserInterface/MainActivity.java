package com.lukmo.kamsos.UserInterface;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.lukmo.kamsos.Models.Vet.Vet;
import com.lukmo.kamsos.Presenters.UserPresenter;
import com.lukmo.kamsos.R;
import com.lukmo.kamsos.UserInfrastructure.UserInfrastructure;

import java.util.List;

public class MainActivity extends AppCompatActivity implements UserInfrastructure.View {

    public static final String TAG = MainActivity.class.getSimpleName();

    public UserInfrastructure.Presenter mPresenter;
    private LoginFragment mLoginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new UserPresenter(this);
        mPresenter.start();

    }

    private void loadFragment() {
        if (mLoginFragment==null){
            mLoginFragment = new LoginFragment();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFrame, mLoginFragment, LoginFragment.TAG).commit();
    }

    @Override
    public void init() {
        mLoginFragment = new LoginFragment();
        loadFragment();
        mPresenter.loadVets();
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void loadDataInList(List<Vet> vets) {
        Log.i( TAG, "Vet Response"+ vets.get(0).getEmail());
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
