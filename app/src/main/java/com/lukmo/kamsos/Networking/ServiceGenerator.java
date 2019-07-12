package com.lukmo.kamsos.Networking;

import android.text.TextUtils;
import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    public static final String API_BASE_URL = "http://kamsos.herokuapp.com/api/v1/";

//    private OkHttpClient httpClient(){
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        return  new OkHttpClient().newBuilder()
//                .addInterceptor(logging)
//                .build();
//
//    }

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
