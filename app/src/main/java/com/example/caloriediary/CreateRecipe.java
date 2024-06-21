package com.example.caloriediary;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import java.util.ArrayList;
import java.util.List;
import adapters.RecipeFoodListAdapter;
import dao.RecipeDAO;
import dao.RecipeFoodDAO;
import db.DatabaseHelper;
import entities.Macros;
import entities.Recipe;
import entities.RecipeFood;
import macro.calc.RecipeMacroCalculator;

public class CreateRecipe extends AppCompatActivity {
    private List<RecipeFood> recipeFoods;
    private DatabaseHelper db;
    private RecipeFoodDAO dao;
    private RecipeDAO recipeDAO;
    private EditText recipeName;
    private ListView recipeFoodList;
    private Button addFoodButton;
    private Button saveRecipeButton;
    private RecipeFoodListAdapter recipeFoodListAdapter;

    private int recipeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createrecipeactivity);

        // Initialize variables
        recipeFoods = new ArrayList<>();
        db = DatabaseHelper.getInstance(this);
        dao = new RecipeFoodDAO(this,db);
        recipeDAO = new RecipeDAO(this);

        // Find views
        recipeName = findViewById(R.id.recipe_name);
        recipeFoodList = findViewById(R.id.food_recipe_list);
        addFoodButton = findViewById(R.id.button_add_food);
        saveRecipeButton = findViewById(R.id.button_save_recipe);




        //Create the recipe in the database
        String name = recipeName.getText().toString();
        Recipe recipe = new Recipe(name,0,0,0,0);
        Recipe recipe1 = recipeDAO.create(recipe);
        recipeId = recipe1.getId();
        Log.d("recipeId", String.valueOf(recipeId));

        // Set views
        setFoodList();
        setAddFoodButton(addFoodButton);
        setSafeRecipeButton(saveRecipeButton);
    }

    private void setFoodList() {
        if (dao != null) {
            // Retrieve recipe foods from DAO
            recipeFoods = dao.getAllById(recipeId);
            recipeFoodListAdapter = new RecipeFoodListAdapter((ArrayList<RecipeFood>) recipeFoods, this);
            recipeFoodList.setAdapter(recipeFoodListAdapter);
        } else {
            Log.e("CreateRecipe", "RecipeFoodDAO instance is null");
        }
    }

    private void setAddFoodButton(Button addFoodButton){
        addFoodButton.setOnClickListener(onClick->{
         startCreateRecipeFoodListFragment();
        });
    }

    //Updating recipe name before creation, calculating macros with calculator and saving in macros object, and then updating macros for recipe in db.
    private void setSafeRecipeButton(Button saveRecipeButton){
    saveRecipeButton.setOnClickListener(onClick->{
        recipeDAO.updateRecipeName(recipeId,recipeName.getText().toString());

        RecipeMacroCalculator macroCalc = new RecipeMacroCalculator();
        Macros macros = macroCalc.calcMacrosInRecipe(recipeFoods);

        recipeDAO.updateMacros(recipeId,macros.getCaloriesTotal(),macros.getProteinTotal(),macros.getCarbsTotal(),macros.getFatTotal());

        finish();
    });
    }

    private void startCreateRecipeFoodListFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.recipe_food_fragment_container, CreateRecipeFoodListFragment.class,null)
                .setReorderingAllowed(true)
                .commit();
    }


    public int getRecipeId(){
        return recipeId;
    }

    public void updateRecipeFoodList(){
        recipeFoods = dao.getAllById(recipeId);
        recipeFoodListAdapter = new RecipeFoodListAdapter((ArrayList<RecipeFood>) recipeFoods, this);
        recipeFoodList.setAdapter(recipeFoodListAdapter);
    }

}
