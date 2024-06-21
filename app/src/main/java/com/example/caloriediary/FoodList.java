package com.example.caloriediary;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import adapters.FoodListAdapter;
import db.DatabaseHelper;

public class FoodList extends AppCompatActivity {
    private FoodListAdapter adapter;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.foodlistactivity);
        db = DatabaseHelper.getInstance(this);
        adapter = new FoodListAdapter(this,db);

        ListView listview = findViewById(R.id.recipe_listview);
        listview.setAdapter(adapter);
    }
}
