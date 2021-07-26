package com.zybooks.foodorder;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FoodItemAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        ArrayList<Food> items = new ArrayList<>();
        //adding 3 food items to the menu
        items.add(new Food("Noodles",4.6,R.drawable.noodles));
        items.add(new Food("Pizza",8.6,R.drawable.pizza));
        items.add(new Food("Burger",3.5,R.drawable.burger));
        //setting up the recycler view
        adapter = new FoodItemAdapter(items,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
