package adapters;


import com.example.caloriediary.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import dao.FoodDAO;
import db.DatabaseHelper;
import entities.Food;

public class FoodListAdapter extends BaseAdapter {
    private FoodDAO foodDAO;
    private List<Food> foodList;
    private Context context;

    public FoodListAdapter(Context context, DatabaseHelper databaseHelper){
        this.context = context;
        this.foodDAO = new FoodDAO(context,databaseHelper);
        this.foodList = foodDAO.getAll();

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
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.foodlistadapterview, parent, false);
        }

        //The food to be displayed
        Food food = (Food) getItem(position);

        TextView foodName = convertView.findViewById(R.id.food_list_name);
        FloatingActionButton remove = convertView.findViewById(R.id.food_list_remove);
        FloatingActionButton showInfo = convertView.findViewById(R.id.food_list_dropdown_button);
        LinearLayout dropdown = convertView.findViewById(R.id.dropdown_food_info);

        //Getting dropdown info TextViews
        TextView calP100 = convertView.findViewById(R.id.dropdown_calories);
        TextView carP100 = convertView.findViewById(R.id.dropdown_carbs);
        TextView proP100 = convertView.findViewById(R.id.dropdown_protein);
        TextView fatP100 = convertView.findViewById(R.id.dropdown_fat);

        //Setting dropdown information
        calP100.setText(String.valueOf(food.getCaloriePer100g()));
        carP100.setText(String.valueOf(food.getCarbPer100g()));
        proP100.setText(String.valueOf(food.getProteinPer100g()));
        fatP100.setText(String.valueOf(food.getFatPer100g()));

        //Setting name for food
        foodName.setText(food.getName());

        //Setting remove button to delete the food from db
        remove.setOnClickListener(onClick->{
            foodDAO.delete(food);
            foodList.remove(food);
            notifyDataSetChanged();
        });

        //Setting the dropdown button showInfo to make dropdown with food info visible or gone depending on visibility
        showInfo.setOnClickListener(onClick->{
         if(dropdown.getVisibility()==View.VISIBLE){
             dropdown.setVisibility(View.GONE);
         }else{
             dropdown.setVisibility(View.VISIBLE);
         }

        });

      return convertView;
    }
}
