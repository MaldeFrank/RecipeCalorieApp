package adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.caloriediary.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import dao.RecipeFoodDAO;
import db.DatabaseHelper;
import entities.Food;
import entities.RecipeFood;

public class RecipeAddFoodAdapter extends BaseAdapter {
    private List<Food> foodList;
    private Context context;
    private RecipeFoodDAO recipeFoodDAO;
    private int recipeId;
    public RecipeAddFoodAdapter(List<Food> foodlist, Context context, DatabaseHelper db, int recipeId){
        this.foodList = foodlist;
        this.context = context;
        this.recipeId = recipeId;
        recipeFoodDAO = new RecipeFoodDAO(context,db);
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return foodList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.recipeaddfoodadapter,parent,false);
        }

        Food food = (Food) getItem(position);
        TextView foodName = convertView.findViewById(R.id.recipe_food_name);
        EditText foodAmount = convertView.findViewById(R.id.recipe_food_amount);
        FloatingActionButton addFood = convertView.findViewById(R.id.recipe_add_food);
        FloatingActionButton showDropdown = convertView.findViewById(R.id.recipe_food_info);
        TableLayout dropdown = convertView.findViewById(R.id.dropdown_food_info);

        //Set dropdown info
        setDropDownInfo(convertView,food);

        //Set widgets
        setAddFood(addFood,foodAmount,food);
        setShowDropdown(showDropdown,dropdown);
        foodName.setText(food.getName().toString());

        return convertView;
    }

    private void setAddFood(FloatingActionButton addFood, EditText foodAmount, Food food){
        addFood.setOnClickListener(onClick->{
            double amount = Double.parseDouble(foodAmount.getText().toString());
            RecipeFood recipeFood = new RecipeFood(food,amount,recipeId);
            recipeFoodDAO.create(recipeFood);
        });

    }

    private void setShowDropdown(FloatingActionButton showDropdown, TableLayout dropdown){
        showDropdown.setOnClickListener(onClick->{
            if(dropdown.getVisibility()==View.VISIBLE){
                dropdown.setVisibility(View.GONE);
            }else{
                dropdown.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setDropDownInfo(View convertView, Food food){
        TextView calories = convertView.findViewById(R.id.dropdown_calories);
        TextView carb = convertView.findViewById(R.id.dropdown_carbs);
        TextView protein = convertView.findViewById(R.id.dropdown_protein);
        TextView fat = convertView.findViewById(R.id.dropdown_fat);
        calories.setText(String.valueOf(food.getCaloriePer100g()));
        carb.setText(String.valueOf(food.getCarbPer100g()));
        protein.setText(String.valueOf(food.getProteinPer100g()));
        fat.setText(String.valueOf(food.getFatPer100g()));
    }
}
