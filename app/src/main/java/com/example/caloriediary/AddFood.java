package com.example.caloriediary;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import dao.FoodDAO;
import db.DatabaseHelper;
import entities.Food;

public class AddFood extends AppCompatActivity {
    private Button addButton;
    private EditText foodNameText;
    private EditText calorieText;
    private EditText proteinText;
    private EditText carbText;
    private EditText fatText;
    private Context context;
    private FoodDAO foodDAO;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.addfoodactivity);

        this.context = getApplicationContext();
        this.databaseHelper = DatabaseHelper.getInstance(context);
        this.foodDAO = new FoodDAO(context,databaseHelper);

        foodNameText = findViewById(R.id.editTextFoodName);
        calorieText = findViewById(R.id.editTextCalories);
        proteinText = findViewById(R.id.editTextProteins);
        carbText = findViewById(R.id.editTextCarbs);
        fatText = findViewById(R.id.editTextFats);
        addButton = addFoodButton();


    }



    private Button addFoodButton() {
        addButton = findViewById(R.id.buttonAddFood);
        addButton.setOnClickListener(v -> {
            String name = foodNameText.getText().toString();
            double calories = Double.parseDouble(calorieText.getText().toString());
            double proteins = Double.parseDouble(proteinText.getText().toString());
            double carbs = Double.parseDouble(carbText.getText().toString());
            double fats = Double.parseDouble(fatText.getText().toString());
            Food food = new Food(name,calories,proteins,carbs,fats);
            foodDAO.create(food);
        });

        return addButton;
    }


}
