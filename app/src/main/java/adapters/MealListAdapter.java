package adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.caloriediary.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import dao.MealDAO;
import db.DatabaseHelper;
import entities.Meal;

public class MealListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Meal> mealList;
    private MealDAO mealDAO;
    private DatabaseHelper db;
    public MealListAdapter(ArrayList<Meal> mealList,Context context, DatabaseHelper db){
        this.context = context;
        this.mealList = mealList;
        this.db = db;
        this.mealDAO = new MealDAO(context,db);
    }
    @Override
    public int getCount() {
        return mealList.size();
    }

    @Override
    public Object getItem(int position) {
        return mealList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mealList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.meallistadapter,parent,false);
        }

        Meal meal = mealList.get(position);

        //Get views
        TextView recipeName = convertView.findViewById(R.id.recipe_name);
        EditText portionAmount = convertView.findViewById(R.id.portion_amount);
        FloatingActionButton addAmount = convertView.findViewById(R.id.add_amount);

        //Set views
        recipeName.setText(meal.getRecipeName());

        //Set buttons
        addAmount = setAddAmountButton(addAmount, portionAmount, meal);

        return convertView;
    }

    /**
     * Configures a FloatingActionButton to handle taking a portion of a meal.
     *
     * @param addAmount The FloatingActionButton to be configured
     * @param portionAmount An EditText containing the amount to be added
     * @param meal The Meal object
     * @return The configured FloatingActionButton
     */
    private FloatingActionButton setAddAmountButton(FloatingActionButton addAmount, EditText portionAmount, Meal meal) {
        addAmount.setOnClickListener(onClick -> {
            // Calculating the new portion amount
            double amountTaken = Double.parseDouble(portionAmount.getText().toString());
            double currentAmount = meal.getAmount();
            double newAmount = currentAmount - amountTaken;

            if (newAmount < 0) {
                // Create and show an AlertDialog
                new AlertDialog.Builder(context)
                        .setTitle("Warning")
                        .setMessage("There is not that much left. Do you want to take the rest of the meal")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            mealDAO.setAmount(meal.getId(), 0);
                            meal.setAmount(0);

                        })
                        .setNegativeButton("No", (dialog, which) -> {
                        })
                        .show();
            } else {
                mealDAO.setAmount(meal.getId(), newAmount);
                meal.setAmount(newAmount);
            }
        });

        return addAmount;
    }

}
