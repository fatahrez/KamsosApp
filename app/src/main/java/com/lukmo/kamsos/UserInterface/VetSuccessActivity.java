package com.lukmo.kamsos.UserInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lukmo.kamsos.R;

public class VetSuccessActivity extends AppCompatActivity {
    private Button callVetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vet_success);

        callVetButton = findViewById(R.id.callVetButton);

        callVetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+254720533635"));
                startActivity(intent);
            }
        });
    }
}