package com.lukmo.kamsos.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lukmo.kamsos.Models.Vet.Result;
import com.lukmo.kamsos.Models.Vet.Vet;
import com.lukmo.kamsos.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class VetCustomAdapter extends RecyclerView.Adapter<VetCustomAdapter.MyViewHolder> {
    private static final String TAG = "VetCustomAdapter";
    public List<Result> vetList = new ArrayList<>();

    public VetCustomAdapter(List<Result> vetList){
        this.vetList = vetList;
    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.vet_recycler_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VetCustomAdapter.MyViewHolder myViewHolder, int i){
        Log.i(TAG, "onBindViewHolder: vetEmail " + vetList.get(i).getEmail());
        myViewHolder.tvVet.setText(vetList.get(i).getEmail());
    }

    @Override
    public int getItemCount(){
        return vetList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvVet;
        public MyViewHolder(View itemView){
            super(itemView);
            tvVet = (TextView) itemView.findViewById(R.id.user_name);
        }
    }
}
