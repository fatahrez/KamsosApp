package com.lukmo.kamsos;

import com.lukmo.kamsos.Models.User;

import retrofit2.Call;
import retrofit2.http.POST;

public interface UserService {
    @POST("/auth/login")
    Call<User> me();
}
