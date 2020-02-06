package com.lukmo.kamsos.Presenters;

import com.lukmo.kamsos.Callback.Callback;
import com.lukmo.kamsos.Models.Vet.Vet;
import com.lukmo.kamsos.UserInfrastructure.UserInfrastructure;
import com.lukmo.kamsos.Utils.UserTask;

import java.util.List;

public class UserPresenter implements UserInfrastructure.Presenter {
    UserInfrastructure.View mView;

    public UserPresenter(UserInfrastructure.View mView){
        this.mView = mView;
    }

    @Override
    public void start() {
        mView.init();
    }

    @Override
    public void loadVets() {
        UserTask.getData(new Callback<List<Vet>>() {
            @Override
            public void getData(List<Vet> vets) {
                mView.loadDataInList(vets);
            }

            @Override
            public void returnError(String message) {

            }
        });
    }
}
