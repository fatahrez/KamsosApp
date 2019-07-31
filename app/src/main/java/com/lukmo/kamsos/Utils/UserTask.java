package com.lukmo.kamsos.Utils;

import android.util.Log;

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
    public static void getData(final Callback<List<Vet>> callback){
        NetworkUtils.ApiInstance()
                .getVets()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Vet>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Vet> vets) {
                        callback.getData(vets);
                        Log.i("Vet response: " + "",vets.get(0).getEmail());
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
