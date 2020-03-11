package com.lukmo.kamsos.Presenters;

import com.lukmo.kamsos.UserInfrastructure.VetDetailsInfrastructure;

public class VetDetailPresenter implements VetDetailsInfrastructure.Presenter {
    VetDetailsInfrastructure.View mView;

    public VetDetailPresenter(VetDetailsInfrastructure.View mView){
        this.mView = mView;
    }

    @Override
    public void start() {
        mView.init();
    }

    @Override
    public void loadVetDetails() {

    }
}
