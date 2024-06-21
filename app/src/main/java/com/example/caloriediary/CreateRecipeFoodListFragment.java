package com.example.caloriediary;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

import adapters.RecipeAddFoodAdapter;
import dao.FoodDAO;
import db.DatabaseHelper;
import entities.Food;

public class CreateRecipeFoodListFragment extends Fragment {
    private List<Food> foodList;
    private FoodDAO foodDAO;
    private DatabaseHelper db;
    private int recipeId;
    private Context context;

    private CreateRecipe activity;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.recipeaddfoodfragment, container, false);
        context = getActivity();
        db = DatabaseHelper.getInstance(context);
        foodDAO = new FoodDAO(context, db);
        foodList = foodDAO.getAll();

        //set widgets
        EditText searchbar = view.findViewById(R.id.recipe_search_food);
        ListView foodSelection = view.findViewById(R.id.recipe_food_selection);
        Button goBack = view.findViewById(R.id.recipe_button_go_back);


        //Get recipe id from CreateRecipe activity
        if (getActivity() instanceof CreateRecipe) {
            activity = (CreateRecipe) getActivity();
            if (activity != null) {
                recipeId = activity.getRecipeId();
                Log.d("recipeId_frag",String.valueOf(recipeId));
            }
        }


        RecipeAddFoodAdapter recipeAddFoodAdapter = new RecipeAddFoodAdapter(foodList,context,db,recipeId);
        foodSelection.setAdapter(recipeAddFoodAdapter);

        //Set views
        setGoBackButton(goBack);
        return view;
    }

    private void setGoBackButton(Button goBack){
       goBack.setOnClickListener(onClick->{
               activity.updateRecipeFoodList();
               FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
               transaction.remove(this);
               transaction.commit();

       });
    }


}
