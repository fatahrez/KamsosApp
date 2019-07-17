package com.lukmo.kamsos.UserInterface;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.lukmo.kamsos.R;

public class MainActivity extends AppCompatActivity {
//    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        textView = findViewById(R.id.textView);

//        if (Build.VERSION.SDK_INT > 9){
//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//        }
//
//        UserService userService = ServiceGenerator.createService(UserService.class, "user25@email.com", "userpassword", "authToken");
//        Call<User> call = userService.me();
//        try {
//            User user = call.execute().body();
//            Log.i(user.getEmail(), "onCreate: show token");
////            textView.setText(user.getEmail());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }



    }
}
