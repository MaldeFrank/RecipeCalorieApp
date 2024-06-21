package com.example.caloriediary;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import adapters.RecipeListAdapter;
import dao.RecipeDAO;
import entities.Recipe;

public class RecipeList extends AppCompatActivity {
    private RecipeDAO recipeDAO;
    private List<Recipe> recipes;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.recipelistactivity);
        recipeDAO = new RecipeDAO(this);
        recipes = recipeDAO.getAll();
        RecipeListAdapter recipeListAdapter = new RecipeListAdapter(this,recipes);

        ListView recipeList = findViewById(R.id.recipe_listview);
        recipeList.setAdapter(recipeListAdapter);
    }
}
