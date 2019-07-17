package com.lukmo.kamsos.UserInterface;

import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lukmo.kamsos.Models.User;
import com.lukmo.kamsos.Networking.ServiceGenerator;
import com.lukmo.kamsos.R;
import com.lukmo.kamsos.Networking.UserService;

import java.io.IOException;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {
//    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        textView = findViewById(R.id.textView);

        if (Build.VERSION.SDK_INT > 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        UserService userService = ServiceGenerator.createService(UserService.class, "user25@email.com", "userpassword", "authToken");
        Call<User> call = userService.me();
        try {
            User user = call.execute().body();
            Log.i(user.getEmail(), "onCreate: show token");
//            textView.setText(user.getEmail());
            
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
