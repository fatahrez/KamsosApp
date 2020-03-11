package com.lukmo.kamsos.Presenters;

import android.util.Log;

import com.lukmo.kamsos.Models.VetDetails.VetDetails;
import com.lukmo.kamsos.Networking.NetworkUtils;
import com.lukmo.kamsos.UserInfrastructure.VetDetailsInfrastructure;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VetDetailPresenter implements VetDetailsInfrastructure.Presenter {
    private static final String TAG = "VetDetailPresenter";

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
        NetworkUtils.ApiInstance().getVetDetails(mView.getSlug()).enqueue(new Callback<VetDetails>() {
            @Override
            public void onResponse(Call<VetDetails> call, Response<VetDetails> response) {
                mView.loadDataInVetDetail(response.body());
                Log.i(TAG, "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<VetDetails> call, Throwable t) {

            }
        });
    }
}
