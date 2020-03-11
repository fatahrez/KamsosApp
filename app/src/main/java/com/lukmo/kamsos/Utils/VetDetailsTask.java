package com.lukmo.kamsos.Utils;

import android.util.Log;

import com.lukmo.kamsos.Callback.VetDetailCallback;
import com.lukmo.kamsos.Models.VetDetails.VetDetails;
import com.lukmo.kamsos.Networking.NetworkUtils;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class VetDetailsTask {
    private static final String TAG = "VetDetailsTask";

    public static void getVetDetail(final VetDetailCallback<VetDetails> callback){
        NetworkUtils.ApiInstance().getVetDetails()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<VetDetails>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VetDetails vetDetails) {
                        callback.getVetDetail(vetDetails);
                        Log.i(TAG, "onNext: " + vetDetails.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
