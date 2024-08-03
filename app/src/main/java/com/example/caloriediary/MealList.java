package com.example.caloriediary;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import adapters.MealListAdapter;
import dao.MealDAO;
import db.DatabaseHelper;
import entities.Meal;

public class MealList extends AppCompatActivity {
    private MealListAdapter mealListAdapter;
    private DatabaseHelper db;
    private ArrayList<Meal> mealList;
    private MealDAO mealDAO;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meallistactivity);

        //Instantiate variables on create
        this.db = DatabaseHelper.getInstance(this);
        this.mealList = new ArrayList<>();
        this.mealDAO = new MealDAO(this,db);

        getMealList();

        //Instantiate mealListAdapter
        mealListAdapter = new MealListAdapter(mealList,this,db);

        //Getting listView and setting it
        ListView mealList = findViewById(R.id.listView);
        mealList.setAdapter(mealListAdapter);
    }

    private void getMealList(){
        mealList = (ArrayList<Meal>) mealDAO.getAll();
    }
}
