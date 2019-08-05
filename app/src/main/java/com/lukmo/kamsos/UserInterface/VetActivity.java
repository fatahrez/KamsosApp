package com.lukmo.kamsos.UserInterface;

import android.os.Bundle;

import com.lukmo.kamsos.Adapters.VetCustomAdapter;
import com.lukmo.kamsos.Models.Vet.Vet;
import com.lukmo.kamsos.Presenters.UserPresenter;
import com.lukmo.kamsos.R;
import com.lukmo.kamsos.UserInfrastructure.UserInfrastructure;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class VetActivity extends Main2Activity implements UserInfrastructure.View{
    private UserInfrastructure.Presenter mPresenter;
    VetCustomAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new UserPresenter(this);
        mPresenter.start();
    }

    @Override
    public void init() {
        recyclerView = findViewById(R.id.vet_recycler_view);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        mPresenter.loadVets();
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void loadDataInList(List<Vet> vets) {
        adapter = new VetCustomAdapter(vets);
        recyclerView.setAdapter(adapter);
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_vet;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_vets;
    }
}
