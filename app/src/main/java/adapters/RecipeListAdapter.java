package adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.caloriediary.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import dao.RecipeDAO;
import dao.RecipeFoodDAO;
import db.DatabaseHelper;
import entities.Recipe;
import entities.RecipeFood;

public class RecipeListAdapter extends BaseAdapter {
    private Context context;
    private List<Recipe> recipeList;

    private List<RecipeFood> recipeFoods;
    private RecipeFoodDAO recipeFoodDAO;
    private RecipeDAO recipeDAO;

    private DatabaseHelper db;
    public RecipeListAdapter(Context context, List<Recipe> recipeList ){
        this.context = context;
        this.recipeList = recipeList;
        this.db = DatabaseHelper.getInstance(context);
        recipeDAO = new RecipeDAO(context);
        recipeFoodDAO = new RecipeFoodDAO(context,db);
    }

    @Override
    public int getCount() {
        return recipeList.size();
    }

    @Override
    public Object getItem(int position) {
        return recipeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return recipeList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.recipelistadapter,parent,false);
        }

        Recipe recipe = (Recipe) getItem(position);

        //get views
        TextView recipeName = convertView.findViewById(R.id.recipe_name);
        FloatingActionButton recipeRemove = convertView.findViewById(R.id.recipe_remove_button);
        FloatingActionButton recipeInfo = convertView.findViewById(R.id.recipe_info_button);
        Button recipeMake = convertView.findViewById(R.id.recipe_make_button);
        TableLayout dropdown = convertView.findViewById(R.id.recipe_dropdown_info);
        ListView dropdownRecipeFoods = convertView.findViewById(R.id.recipe_foods);

        //Dropdown info TextViews
        TextView dropdownCalories = convertView.findViewById(R.id.dropdown_calories);
        TextView dropdownCarbs = convertView.findViewById(R.id.dropdown_carbs);
        TextView dropdownProtein = convertView.findViewById(R.id.dropdown_protein);
        TextView dropdownFats = convertView.findViewById(R.id.dropdown_fat);

        //Set view text
        recipeName.setText(recipe.getName().toString());
        dropdownCalories.setText(String.valueOf(recipe.getCaloriesTotal()));
        dropdownCarbs.setText(String.valueOf(recipe.getCarbsTotal()));
        dropdownProtein.setText(String.valueOf(recipe.getProteinsTotal()));
        dropdownFats.setText(String.valueOf(recipe.getFatsTotal()));

        //Setting listview in dropdown with all recipes.
        recipeFoods = recipeFoodDAO.getAllById(recipe.getId());
        ShowRecipeFoodAdapter showRecipeFoodAdapter = new ShowRecipeFoodAdapter(context,recipeFoods);
        dropdownRecipeFoods.setAdapter(showRecipeFoodAdapter);

        //Set buttons
        setRecipeRemoveButton(recipeRemove,recipe);
        setRecipeInfoButton(recipeInfo,dropdown);

        return convertView;
    }


    private void setRecipeRemoveButton(FloatingActionButton recipeRemove, Recipe recipe){
        recipeRemove.setOnClickListener(onClick->{
            recipeDAO.delete(recipe);
            recipeList = recipeDAO.getAll();
            notifyDataSetChanged();
        });
    }

    private void setRecipeInfoButton(FloatingActionButton recipeInfo,TableLayout dropdown){
        recipeInfo.setOnClickListener(onClick->{
            if(dropdown.getVisibility()==View.VISIBLE){
                dropdown.setVisibility(View.GONE);
            }else{
                dropdown.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setRecipeMakeButton(Button recipeMake){
        recipeMake.setOnClickListener(onclick->{

        });
    }
}
