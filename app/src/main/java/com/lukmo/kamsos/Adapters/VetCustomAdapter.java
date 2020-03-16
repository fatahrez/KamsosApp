package com.lukmo.kamsos.Adapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lukmo.kamsos.Models.Vet.Result;
import com.lukmo.kamsos.Models.Vet.Vet;
import com.lukmo.kamsos.R;
import com.lukmo.kamsos.UserInterface.VetDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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
        myViewHolder.tvVet.setText(vetList.get(i).getFirstName());
        Picasso.get().load(vetList.get(i).getVetImage()).into(myViewHolder.imageVet);
        myViewHolder.middleNameTextView.setText(vetList.get(i).getMiddleName());
        myViewHolder.lastNameTextView.setText(vetList.get(i).getLastName());

        myViewHolder.vetCardView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), VetDetailActivity.class);
            intent.putExtra("slug", vetList.get(i).getSlug());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount(){
        return vetList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvVet;
        ImageView imageVet;
        CardView vetCardView;
        TextView middleNameTextView;
        TextView lastNameTextView;
        public MyViewHolder(View itemView){
            super(itemView);
            tvVet = (TextView) itemView.findViewById(R.id.user_name);
            imageVet = (ImageView) itemView.findViewById(R.id.vet_recycler_view_image);
            vetCardView = (CardView) itemView.findViewById(R.id.vet_card_view);
            middleNameTextView = (TextView) itemView.findViewById(R.id.middleNameTextView);
            lastNameTextView = (TextView) itemView.findViewById(R.id.lastNameTextView);

            imageVet.getLayoutParams().height = 1000;
            imageVet.requestLayout();
        }
    }
}
