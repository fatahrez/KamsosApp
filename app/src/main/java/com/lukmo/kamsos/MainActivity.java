package com.lukmo.kamsos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lukmo.kamsos.Models.User;
import com.lukmo.kamsos.Networking.ServiceGenerator;

import java.io.IOException;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserService userService = ServiceGenerator.createService(UserService.class, "user25@email.com", "userpassword", "authToken");
        Call<User> call = userService.me();
        try {
            User user = call.execute().body();
            Log.d(user.getToken(), "onCreate: show token");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
