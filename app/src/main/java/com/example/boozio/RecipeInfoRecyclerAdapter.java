package com.example.boozio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

class RecipeInfoRecyclerAdapter extends RecyclerView.Adapter<RecipeInfoRecyclerAdapter.MyViewHolder> {

    private final HashMap<String, String> ingredientList;
    private final Set<String> keySet;
    private final ArrayList<String> listOfKeys;
    private final ArrayList<String> listOfValues;

    public RecipeInfoRecyclerAdapter(HashMap<String, String> ingredientList){
        this.ingredientList = ingredientList;

        keySet = ingredientList.keySet();
        listOfKeys = new ArrayList<>(keySet);

        // Getting Collection of values from HashMap
        Collection<String> values = ingredientList.values();

        // Creating an ArrayList of values
        listOfValues = new ArrayList<>(values);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView ingredientNameTextView;
        private final TextView ingredientAmountTextView;

        private final ImageView ingredientTypeImageView;

        public MyViewHolder(final View view) {
            super(view);

            ingredientNameTextView = view.findViewById(R.id.ingredientNameTextView);
            ingredientAmountTextView = view.findViewById(R.id.ingredientAmountTextView);

            ingredientTypeImageView = view.findViewById(R.id.ingredientTypeImageView);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_information, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeInfoRecyclerAdapter.MyViewHolder holder, int position) {
        String ingredientName = listOfKeys.get(position);
        String capitalized_ingredientName = ingredientName.substring(0, 1).toUpperCase() + ingredientName.substring(1);

        String ingredientAmount = listOfValues.get(position);
        String ingredientType = Controller.getInstance().getIngredientType(ingredientName);

        holder.ingredientNameTextView.setText(capitalized_ingredientName);
        holder.ingredientAmountTextView.setText(ingredientAmount);

        switch (ingredientType){
            case "fruit_herbs":
                holder.ingredientTypeImageView.setImageResource(R.drawable.ic_fruits);
                break;
            case "juice":
                holder.ingredientTypeImageView.setImageResource(R.drawable.ic_juice);
                break;
            case "soda":
                holder.ingredientTypeImageView.setImageResource(R.drawable.ic_soda);
                break;
            case "syrup":
                holder.ingredientTypeImageView.setImageResource(R.drawable.ic_syrups);
                break;
            default:
                holder.ingredientTypeImageView.setImageResource(R.drawable.ic_spirits);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public interface OnIngredientListener{
        void onIngredientAmountClick(int position, View view, ArrayList<Ingredient> ingredientList);
    }
}
