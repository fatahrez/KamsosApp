package com.lukmo.kamsos.Networking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lukmo.kamsos.Models.Login.User;
import com.lukmo.kamsos.Utils.Constants;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthServiceGenerator {

    private static Retrofit retrofit;
    private static Gson gson;

    public static synchronized Retrofit RequestInstance(String authToken) {
        if (retrofit == null) {
            if (gson == null) {
                gson = new GsonBuilder().setLenient().create();
            }
            OkHttpClient.Builder client = new OkHttpClient.Builder();

            Interceptor headerAuthorizationInterceptor = new Interceptor() {
                @NotNull
                @Override
                public Response intercept(@NotNull Chain chain) throws IOException {
                    Request request = chain.request();
                    Headers headers = request.headers().newBuilder().add("Authorization", "Token " + authToken).build();
                    request = request.newBuilder().headers(headers).build();
                    return chain.proceed(request);
                }
            };

            client.addInterceptor(headerAuthorizationInterceptor);

            retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

        }
        return retrofit;
    }

}
