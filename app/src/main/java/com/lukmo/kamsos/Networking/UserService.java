package com.lukmo.kamsos.Networking;

import com.lukmo.kamsos.Models.Response;
import com.lukmo.kamsos.Models.User;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserService {
    @POST("/auth/createpastoralist")
    Observable<User> register(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password
    );

    @POST("/auth/login")
    Observable<User> login(
            @Field("email") String email,
            @Field("password") String password
    );
}
