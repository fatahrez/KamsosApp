package com.lukmo.kamsos.UserInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.lukmo.kamsos.Models.VetDetails.Vet;
import com.lukmo.kamsos.Models.VetDetails.VetDetails;
import com.lukmo.kamsos.Presenters.VetDetailPresenter;
import com.lukmo.kamsos.R;
import com.lukmo.kamsos.UserInfrastructure.VetDetailsInfrastructure;
import com.lukmo.kamsos.Utils.Constants;

import static com.lukmo.kamsos.Utils.Constants.TOKEN;

public class VetDetailActivity extends AppCompatActivity implements VetDetailsInfrastructure.View {
    private VetDetailsInfrastructure.Presenter mPresenter;
    private TextView vetNameTextView;
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
        vetNameTextView = findViewById(R.id.vetNameTextView);
        mPresenter.loadVetDetails();
    }

    @Override
    public void loadDataInVetDetail(VetDetails vetDetails) {
        vetNameTextView.setText(vetDetails.getVet().getFirstName());
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