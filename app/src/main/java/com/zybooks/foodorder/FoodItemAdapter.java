package com.zybooks.foodorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Locale;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder> {
    private final ArrayList<Food> foodItems;
    private final Context context;

    public FoodItemAdapter(ArrayList<Food> foodItems,Context context) {
        this.foodItems = foodItems;
        this.context = context;
    }

    @NonNull
    @Override
    public FoodItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FoodItemViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.list_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FoodItemAdapter.FoodItemViewHolder holder, int position) {
        Food food = foodItems.get(position);
        holder.foodImageView.setImageResource(food.getDrawableId());
        holder.foodTitleTextView.setText(String.format(Locale.ENGLISH,"%s $%.2f",
                food.getFoodTitle(),food.getCost()));
        //setting the click listener to the order Button
        holder.orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(holder.orderCountTextView.getText().toString());
                if(count != 0) {
                    String out = String.format(Locale.ENGLISH,
                            "Order placed: %s, qty: %d, amt: %.2f",
                            food.getFoodTitle(),count,food.getCost()*count);
                    //displaying a Snackbar to show th order status
                    Snackbar.make(v,out,Snackbar.LENGTH_LONG).show();
                    holder.orderCountTextView.setText("0");
                }else{
                    Snackbar.make(v,"Order quantity should be greater than 0",
                            Snackbar.LENGTH_LONG).show();
                }
            }
        });
        holder.increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //increasing the count of the order
                int count = Integer.parseInt(holder.orderCountTextView.getText().toString());
                holder.orderCountTextView.setText(String.valueOf(count+1));
            }
        });
        holder.decreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //decreasing the count of the order only if greater than 0
                int count = Integer.parseInt(holder.orderCountTextView.getText().toString());
                if(count > 0){
                    count -= 1;
                }
                holder.orderCountTextView.setText(String.valueOf(count));
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    public static class FoodItemViewHolder extends RecyclerView.ViewHolder{
        public final ImageView foodImageView;
        public final TextView foodTitleTextView;
        public final Button orderButton;
        public final TextView increaseButton;
        public final TextView decreaseButton;
        public final TextView orderCountTextView;

        public FoodItemViewHolder(@NonNull View itemView) {
            super(itemView);
            foodImageView = itemView.findViewById(R.id.food_image);
            foodTitleTextView = itemView.findViewById(R.id.food_name);
            orderButton = itemView.findViewById(R.id.order_button);
            increaseButton = itemView.findViewById(R.id.order_increase);
            decreaseButton = itemView.findViewById(R.id.order_decrease);
            orderCountTextView = itemView.findViewById(R.id.order_count);
        }
    }
}
