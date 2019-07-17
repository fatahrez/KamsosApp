package com.lukmo.kamsos.Networking;

import android.text.TextUtils;
import android.util.Log;

import com.lukmo.kamsos.Utils.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Scheduler;
import rx.schedulers.Schedulers;

public class ServiceGenerator {
    public static UserService getUser(){
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());

        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserService.class);
    }

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();


    private static Retrofit.Builder builder = new Retrofit.Builder().
            baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static <S> S createService(Class<S> serviceClass){
        return createService(serviceClass, null, null, null);
    }

    public static <S> S createService(
            Class<S> serviceClass, final String email, final String password, final String authToken){
        if (!TextUtils.isEmpty(authToken)){
            AuthentificationInterceptor interceptor = new AuthentificationInterceptor(authToken);

            if (!httpClient.interceptors().contains(interceptor)){
                httpClient.addInterceptor(interceptor);

                builder.client(httpClient.build());
                retrofit = builder.build();
            }
        }

        return retrofit.create(serviceClass);
    }
}
