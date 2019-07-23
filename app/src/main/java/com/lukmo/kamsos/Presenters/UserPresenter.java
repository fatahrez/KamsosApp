package com.lukmo.kamsos.Presenters;

import com.lukmo.kamsos.UserInfrastructure.UserInfrastructure;

public class UserPresenter implements UserInfrastructure.Presenter {
    public UserInfrastructure.View mView;

    public UserPresenter(UserInfrastructure.View mView){
        this.mView = mView;
    }

    @Override
    public void start() {
        mView.init();
    }

    @Override
    public void loadUsers() {

    }
}
