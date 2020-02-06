package com.lukmo.kamsos.Utils;

import android.util.Log;

import com.google.gson.internal.$Gson$Preconditions;
import com.lukmo.kamsos.Callback.Callback;
import com.lukmo.kamsos.Models.Login.User;
import com.lukmo.kamsos.Models.Vet.Vet;
import com.lukmo.kamsos.Networking.NetworkUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserTask {
    public static void getData(final Callback<Vet> callback){
        NetworkUtils.ApiInstance()
                .getVets("application/json")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Vet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Vet vets) {
                        callback.getData(vets);
                        Log.i("Vet response: " + "", vets.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.returnError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
