package com.lukmo.kamsos.Networking;

import com.lukmo.kamsos.Models.Login.User;
import com.lukmo.kamsos.Models.Register.Register;
import com.lukmo.kamsos.Models.Vet.Vet;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserService {
    @POST("auth/createpastoralist")
    @Headers("X-Requested-With:XMLHttpRequest")
    Observable<Register> register(
        @Header("Content-Type") String content_type,
        @Body Register register
    );

    @POST("auth/login")
    @Headers("X-Requested-With:XMLHttpRequest")
    Observable<User> login(
            @Header("Content-Type") String content_type,
            @Body User user
    );

    @GET("vets")
    @Headers("X-Requested-With:XMLHttpRequest")
    Observable<Vet> getVets(
            @Header("Content-Type") String content_type
    );
}
