package com.lukmo.kamsos.Networking;

import com.lukmo.kamsos.Models.Login.User;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserService {
    @POST("auth/createpastoralist")

    Observable<User> register(

    );

    @POST("auth/login")
    @Headers("X-Requested-With:XMLHttpRequest")
    Observable<User> login(
            @Header("Content-Type") String content_type,
            @Body User user
    );
}
