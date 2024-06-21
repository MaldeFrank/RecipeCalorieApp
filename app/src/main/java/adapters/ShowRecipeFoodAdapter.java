package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.caloriediary.R;

import java.util.List;

import entities.Food;
import entities.RecipeFood;

public class ShowRecipeFoodAdapter extends BaseAdapter {
private List<RecipeFood> foodList;
private Context context;
    public ShowRecipeFoodAdapter(Context context, List<RecipeFood> foodList){
        this.context = context;
        this.foodList = foodList;
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
            convertView = LayoutInflater.from(context).inflate(com.example.caloriediary.R.layout.showrecipefoodadapter,parent,false);
        }

        RecipeFood food = (RecipeFood) getItem(position);

        TextView foodName = convertView.findViewById(R.id.food_list_name);
        TextView grams = convertView.findViewById(R.id.food_amount);

        foodName.setText(food.getName().toString());
        grams.setText(String.valueOf(food.getAmount()+" g"));

        return convertView;
    }
}
