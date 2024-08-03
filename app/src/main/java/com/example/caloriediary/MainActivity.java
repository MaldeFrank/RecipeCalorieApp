package com.example.caloriediary;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;


public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.mainactivity);
        Button addFoodButton = addFoodButton();
        Button goToFoodListButton = goToFoodList();
        Button goToCreateRecipe = goToCreateRecipe();
        Button goToRecipeList = goToRecipeList();
        Button goToMealList = goToMealList();

    }

    private Button addFoodButton(){
        Button addFoodButton = findViewById(R.id.button_add_food);
        addFoodButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddFood.class);
            startActivity(intent);
        });
        return addFoodButton;
    }

    private Button goToFoodList(){
        Button goToFoodList = findViewById(R.id.food_list_activity);
        goToFoodList.setOnClickListener(onClick->{
            Intent intent = new Intent(this,FoodList.class);
            startActivity(intent);
        });
        return goToFoodList;
    }

    private Button goToCreateRecipe(){
        Button goToCreateRecipe = findViewById(R.id.button_go_to_create_recipe);
        goToCreateRecipe.setOnClickListener(onClick->{
            Intent intent = new Intent(this,CreateRecipe.class);
            startActivity(intent);
        });
        return goToCreateRecipe;
    }

    private Button goToRecipeList(){
        Button goToRecipeList = findViewById(R.id.recipe_list_activity);
        goToRecipeList.setOnClickListener(onClick->{
            Intent intent = new Intent(this,RecipeList.class);
            startActivity(intent);
        });
        return goToRecipeList;
    }

    private Button goToMealList(){
        Button goToMealList = findViewById(R.id.meal_list);
        goToMealList.setOnClickListener(onClick->{
            Intent intent = new Intent(this,MealList.class);
            startActivity(intent);
        });
        return goToMealList;
    }
}
