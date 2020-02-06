package com.lukmo.kamsos.UserInterface;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.lukmo.kamsos.Adapters.VetCustomAdapter;
import com.lukmo.kamsos.Models.Vet.Vet;
import com.lukmo.kamsos.Presenters.UserPresenter;
import com.lukmo.kamsos.R;
import com.lukmo.kamsos.UserInfrastructure.UserInfrastructure;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class VetActivity extends AppCompatActivity implements UserInfrastructure.View{
    private static final String TAG = "VetActivity";

    private UserInfrastructure.Presenter mPresenter;
//    VetCustomAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vet);

        mPresenter = new UserPresenter(this);
        mPresenter.start();
    }

    @Override
    public void init() {
        recyclerView = (RecyclerView) findViewById(R.id.vet_recycler_view);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        mPresenter.loadVets();

    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void loadDataInList(List<Vet> vets) {
        VetCustomAdapter adapter = new VetCustomAdapter(vets);
        recyclerView.setAdapter(adapter);
        Log.i(TAG, "loadDataInList: vets" + vets.toString());
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
