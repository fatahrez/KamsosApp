package com.lukmo.kamsos.UserInterface;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lukmo.kamsos.Models.Vet.Vet;
import com.lukmo.kamsos.Presenters.UserPresenter;
import com.lukmo.kamsos.R;
import com.lukmo.kamsos.UserInfrastructure.UserInfrastructure;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

public class Main2Activity extends AppCompatActivity implements UserInfrastructure.View {
    private UserInfrastructure.Presenter mPresenter;

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mPresenter = new UserPresenter(this);
        mPresenter.start();
    }

    @Override
    public void init() {
        mPresenter.loadVets();
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void loadDataInList(List<Vet> vets) {
        Log.d("Vet Response " , vets.toString());
        mTextMessage.setText(vets.get(1).getEmail());
    }
}
