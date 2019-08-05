package com.lukmo.kamsos.UserInterface;

import android.os.Bundle;

import com.lukmo.kamsos.Models.Vet.Vet;
import com.lukmo.kamsos.Presenters.UserPresenter;
import com.lukmo.kamsos.R;
import com.lukmo.kamsos.UserInfrastructure.UserInfrastructure;

import java.util.List;

public class VetActivity extends Main2Activity implements UserInfrastructure.View{
    private UserInfrastructure.Presenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vet);

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
