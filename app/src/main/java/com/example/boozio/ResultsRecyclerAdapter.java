package com.example.boozio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ResultsRecyclerAdapter extends RecyclerView.Adapter<ResultsRecyclerAdapter.MyViewHolder> {
    private ArrayList<Recipe> recipeList;
    private final OnRecyclerListener onRecyclerListener;

    public ResultsRecyclerAdapter(ArrayList<Recipe> _recipeList, OnRecyclerListener onRecyclerListener){
        recipeList = _recipeList;
        this.onRecyclerListener = onRecyclerListener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView resultsTextView;
        private final ImageView resultsImageView;
        private final OnRecyclerListener onRecyclerListener;

        public MyViewHolder(final View view, OnRecyclerListener onRecyclerListener){
            super(view);
            resultsTextView = view.findViewById(R.id.resultsTextView);
            resultsImageView = view.findViewById(R.id.resultsImageView);

            this.onRecyclerListener = onRecyclerListener;

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onRecyclerListener.onRecyclerClick(getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public ResultsRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_results, parent, false);

        return new MyViewHolder(itemView, onRecyclerListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultsRecyclerAdapter.MyViewHolder holder, int position) {
        String recipeName = recipeList.get(position).getName();

        // Set recipe name to show
        holder.resultsTextView.setText(recipeName);

        // Needed to "reset" the image for the list item and prevent that the wrong image is loaded for the wrong item
        Picasso.get().load(R.drawable.ic_spirits).into(holder.resultsImageView);

        // If the recipe item has an image, set the list item image view to show that image
        if(recipeList.get(position).getImage_url() != null){
            Picasso.get().load(recipeList.get(position).getImage_url()).into(holder.resultsImageView);
        } else {
            holder.resultsImageView.setImageResource(R.drawable.ic_spirits);
        }

    }

    public interface OnRecyclerListener{
        void onRecyclerClick(int position);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public void setItems(ArrayList<Recipe> _recipeList){
        this.recipeList = _recipeList;
    }
}
