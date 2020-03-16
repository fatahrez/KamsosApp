package com.lukmo.kamsos.UserInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lukmo.kamsos.Models.RequestVet.RequestVet;
import com.lukmo.kamsos.Models.VetDetails.VetDetails;
import com.lukmo.kamsos.Networking.AuthNetworkUtils;
import com.lukmo.kamsos.Networking.AuthServiceGenerator;
import com.lukmo.kamsos.Presenters.VetDetailPresenter;
import com.lukmo.kamsos.R;
import com.lukmo.kamsos.UserInfrastructure.VetDetailsInfrastructure;
import com.lukmo.kamsos.Utils.Constants;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.lukmo.kamsos.Utils.Constants.TOKEN;

public class VetDetailActivity extends AppCompatActivity implements VetDetailsInfrastructure.View {
    private static final String TAG = "VetDetailActivity";

    private VetDetailsInfrastructure.Presenter mPresenter;

    private TextView vetNameTextView;
    private TextView middleNameTextView;
    private TextView lastNameTextView;
    private ImageView vetImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vet_detail);

        String token = PreferenceManager.getDefaultSharedPreferences(this).getString(TOKEN, null);

        if(token == null) {
            startActivity(new Intent(this, MainActivity.class));
        }

        mPresenter = new VetDetailPresenter(this);
        mPresenter.start();
    }

    @Override
    public void init() {
        vetNameTextView = findViewById(R.id.firstNameTextView);
        middleNameTextView = findViewById(R.id.middleNameTV);
        lastNameTextView = findViewById(R.id.lastNameTV);

        vetImageView = findViewById(R.id.vetImageView);

        Button requestVetButton = findViewById(R.id.vetRequestButton);

        mPresenter.loadVetDetails();

        requestVetButton.setOnClickListener(view -> requestVet());
    }

    private void requestVet() {
        String token = PreferenceManager.getDefaultSharedPreferences(this).getString(TOKEN, null);
        RequestVet requestVet = new RequestVet();
        AuthNetworkUtils.postAuthRequest(token)
                .requestVet(getIntent().getStringExtra("slug"), requestVet)
                .enqueue(new Callback<RequestVet>() {
                    @Override
                    public void onResponse(Call<RequestVet> call, Response<RequestVet> response) {
                        Intent intent = new Intent(VetDetailActivity.this, VetSuccessActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<RequestVet> call, Throwable t) {

                    }
                });
//        NetworkUtils.ApiInstance().requestVet(token, getIntent().getStringExtra("slug"))
//                .enqueue(new Callback<RequestVet>() {
//                    @Override
//                    public void onResponse(Call<RequestVet> call, Response<RequestVet> response) {
//                        Log.i(TAG, "onResponse: " + response.body());
//                    }
//
//                    @Override
//                    public void onFailure(Call<RequestVet> call, Throwable t) {
//
//                    }
//                });
    }

    @Override
    public void loadDataInVetDetail(VetDetails vetDetails) {
        vetNameTextView.setText(vetDetails.getVet().getFirstName());
        Picasso.get().load(vetDetails.getVet().getVetImage()).into(vetImageView);
        middleNameTextView.setText(vetDetails.getVet().getMiddleName());
        lastNameTextView.setText(vetDetails.getVet().getLastName());
    }

    @Override
    public String getSlug() {
        return getIntent().getStringExtra("slug");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                PreferenceManager.getDefaultSharedPreferences(this).edit().remove(Constants.TOKEN).apply();
                startActivity(new Intent(this, MainActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}