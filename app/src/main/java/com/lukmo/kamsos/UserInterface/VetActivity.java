package com.lukmo.kamsos.UserInterface;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.lukmo.kamsos.Adapters.VetCustomAdapter;
import com.lukmo.kamsos.Models.Vet.Result;
import com.lukmo.kamsos.Models.Vet.Vet;
import com.lukmo.kamsos.Networking.NetworkUtils;
import com.lukmo.kamsos.Presenters.UserPresenter;
import com.lukmo.kamsos.R;
import com.lukmo.kamsos.UserInfrastructure.UserInfrastructure;
import com.lukmo.kamsos.Utils.Constants;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.lukmo.kamsos.Utils.Constants.TOKEN;

public class VetActivity extends AppCompatActivity implements UserInfrastructure.View{
    private static final String TAG = "VetActivity";

    private UserInfrastructure.Presenter mPresenter;
    VetCustomAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vet);

        String token = PreferenceManager.getDefaultSharedPreferences(this).getString(TOKEN, null);

        if(token == null) {
            startActivity(new Intent(this, MainActivity.class));
        }

        mPresenter = new UserPresenter(this);
        mPresenter.start();
    }

    @Override
    public void init() {
        recyclerView = (RecyclerView) findViewById(R.id.vet_recycler_view);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        mPresenter.loadVets();

    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void loadDataInList(List<Result> vets) {
        adapter = new VetCustomAdapter(vets);
        recyclerView.setAdapter(adapter);
    }


//    @Override
//    int getContentViewId() {
//        return R.layout.activity_vet;
//    }
//
//    @Override
//    int getNavigationMenuItemId() {
//        return R.id.navigation_vets;
//    }
}
