package com.lukmo.kamsos.Networking;

import android.util.Base64;

import com.lukmo.kamsos.Utils.Constants;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
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

    public static UserService getUser(String email, String password){
//        String credentials = email + ":" + password;
//        String basic = "Bearer " + Base64.encodeToString(credentials.getBytes(),Base64.NO_WRAP);
        final MediaType JSON = MediaType.parse("application/json");
        String postData = "{" + "\"user\"" + ":" + "\"email\"" + email + "," + "\"password\"" + ":" + password + "}}";

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();


        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            RequestBody body = RequestBody.create(JSON, postData);
            Request.Builder builder = original.newBuilder()
//                    .addHeader("Authorization", basic)
                    .addHeader("Accept", "application/json")
                    .post(body)
                    .method(original.method(), original.body());
            return chain.proceed(builder.build());
        });

        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());

        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(httpClient.build())
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserService.class);
    }

    public static UserService getUser(String token){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder builder = original.newBuilder()
                    .addHeader("x-access-token", token)
                    .method(original.method(), original.body());
            return chain.proceed(builder.build());
        });

        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(httpClient.build())
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(UserService.class);
    }
}
