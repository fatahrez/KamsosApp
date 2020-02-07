package com.lukmo.kamsos.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lukmo.kamsos.Models.Vet.Result;
import com.lukmo.kamsos.Models.Vet.Vet;
import com.lukmo.kamsos.R;
import com.squareup.picasso.Picasso;

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
        myViewHolder.tvVet.setText(vetList.get(i).getEmail());
        Picasso.get().load("https://images.unsplash.com/photo-1579202673506-ca3ce28943ef?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=334&q=80").into(myViewHolder.imageVet);
    }

    @Override
    public int getItemCount(){
        return vetList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvVet;
        ImageView imageVet;
        public MyViewHolder(View itemView){
            super(itemView);
            tvVet = (TextView) itemView.findViewById(R.id.user_name);
            imageVet = (ImageView) itemView.findViewById(R.id.vet_recycler_view_image);
        }
    }
}
