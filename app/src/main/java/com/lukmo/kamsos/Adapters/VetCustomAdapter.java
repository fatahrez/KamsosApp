package com.lukmo.kamsos.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lukmo.kamsos.Models.Vet.Vet;
import com.lukmo.kamsos.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class VetCustomAdapter extends RecyclerView.Adapter<VetCustomAdapter.MyViewHolder> {
    public List<Vet> vetList = new ArrayList<>();

    public VetCustomAdapter(List<Vet> vetList){
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
        myViewHolder.tvVet.setText(vetList.get(i).getUsername());
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
