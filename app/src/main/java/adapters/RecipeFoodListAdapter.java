package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.caloriediary.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import entities.RecipeFood;


public class RecipeFoodListAdapter extends BaseAdapter {
    private ArrayList<RecipeFood> recipeFoods;
    private Context context;

    public RecipeFoodListAdapter(ArrayList<RecipeFood> recipeFoods,Context context) {
        this.recipeFoods = recipeFoods;
        this.context = context;
    }

    @Override
    public int getCount() {
        return recipeFoods.size();
    }

    @Override
    public Object getItem(int position) {
        return recipeFoods.get(position);
    }

    @Override
    public long getItemId(int position) {
        return recipeFoods.get(position).getFoodId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.recipefoodlistadapterview,parent,false);
        }

        //Get the recipe food on this position
        RecipeFood recipeFood = recipeFoods.get(position);

        //Getting all views
        TextView foodName = convertView.findViewById(R.id.recipe_food_list_name);
        TextView foodAmount = convertView.findViewById(R.id.recipe_food_list_amount);
        FloatingActionButton remove = convertView.findViewById(R.id.recipe_food_list_remove);
        Button edit = convertView.findViewById(R.id.recipe_food_list_dropdown_button);
        LinearLayout dropdown = convertView.findViewById(R.id.recipe_food_edit);
        FloatingActionButton setNewAmount = convertView.findViewById(R.id.set_new_amount);
        EditText dropdownSetter = convertView.findViewById(R.id.dropdown_recipe_amount);

        foodName.setText(recipeFood.getName());
        foodAmount.setText(String.valueOf(recipeFood.getAmount()));

        //Set button onclick events
        setRemoveButton(remove,recipeFood);
        setEditButton(edit,dropdown);
        setNewAmountButton(setNewAmount,recipeFood,dropdown,dropdownSetter);
        return convertView;
    }

    private void setRemoveButton(FloatingActionButton remove,RecipeFood food){
        remove.setOnClickListener(onClick->{
            recipeFoods.remove(food);
            notifyDataSetChanged();
        });
    }

    private void setEditButton(Button edit,LinearLayout dropdown){
        edit.setOnClickListener(onClick->{
            if(dropdown.getVisibility()==View.VISIBLE){
                dropdown.setVisibility(View.GONE);
            }else{
                dropdown.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setNewAmountButton(FloatingActionButton button,RecipeFood food,LinearLayout dropdown, EditText dropdownSetter){
        button.setOnClickListener(onClick->{
         double newAmount = Double.parseDouble(dropdownSetter.getText().toString());
         food.setAmount(newAmount);
         dropdown.setVisibility(View.GONE);
        });
    }

}
