package com.lukmo.kamsos.Networking;

import com.lukmo.kamsos.Models.Response;
import com.lukmo.kamsos.Models.User;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserService {
    @POST("/auth/createpastoralist")
    @Headers("X-Requested-With:XMLHttpRequest")
    Observable<Response> register(@Body User user);

    @POST("/auth/login")
    @Headers("X-Requested-With:XMLHttpRequest")
    Observable<Response> login();
}
