package com.example.boozio;

import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Locale;

public class SelectorRecyclerAdapter extends RecyclerView.Adapter<SelectorRecyclerAdapter.MyViewHolder> {

    private final ArrayList<Ingredient> ingredientList;
    private final OnIngredientListener onIngredientListener;

    // Hold boolean value in order to see if the checkbox is selected or not (prevent the duplication bug)
    private final SparseBooleanArray isSelectedArray = new SparseBooleanArray();

    public SelectorRecyclerAdapter(ArrayList<Ingredient> _ingredientList, OnIngredientListener onIngredientListener){
        ingredientList = _ingredientList;
        this.onIngredientListener = onIngredientListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView ingredientNameTextView;
        private final AppCompatCheckBox checkBox;

        private final OnIngredientListener onIngredientListener;

        public MyViewHolder(final View view, OnIngredientListener onIngredientListener){
            super(view);
            ingredientNameTextView = view.findViewById(R.id.ingredientNameTextView);
            checkBox = view.findViewById(R.id.checkBox);

            this.onIngredientListener = onIngredientListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(checkBox.isChecked()){
                // Give value to the array at the clicked position
                isSelectedArray.put(getAdapterPosition(), false);
                checkBox.setChecked(false);
            } else{
                isSelectedArray.put(getAdapterPosition(), true);
                checkBox.setChecked(true);
            }

            onIngredientListener.onIngredientClick(getAdapterPosition(), v, ingredientList);
        }
    }

    @NonNull
    @Override
    public SelectorRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_selector, parent, false);

        return new MyViewHolder(itemView, onIngredientListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectorRecyclerAdapter.MyViewHolder holder, int position) {
        String ingredientName = ingredientList.get(position).getName();
        String capitalized_ingredientName = ingredientName.substring(0, 1).toUpperCase() + ingredientName.substring(1);
        holder.ingredientNameTextView.setText(capitalized_ingredientName);


        // If the checkbox at the loaded position is supposed to be checked
        holder.checkBox.setChecked(isSelectedArray.get(position));

    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public interface OnIngredientListener{
        void onIngredientClick(int position, View view, ArrayList<Ingredient> ingredientList);
    }
}
