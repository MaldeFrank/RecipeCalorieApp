package dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import db.DatabaseHelper;
import entities.Food;

public class FoodDAO implements DAOInterface<Food> {
    public static final String TABLE_FOOD = "food";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_CALORIES = "caloriesPer100g";
    public static final String COLUMN_PROTEIN = "proteinsPer100g";
    public static final String COLUMN_CARBS = "carbsPer100g";
    public static final String COLUMN_FAT = "fatsPer100g";

    private DatabaseHelper databaseHelper;

    public FoodDAO(Context context, DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    @Override
    public List<Food> getAll() {
        List<Food> foods = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FOOD,
                null,
                null,
                null,
                null,
                null,
                null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                @SuppressLint("Range") double calories = cursor.getDouble(cursor.getColumnIndex(COLUMN_CALORIES));
                @SuppressLint("Range") double protein = cursor.getDouble(cursor.getColumnIndex(COLUMN_PROTEIN));
                @SuppressLint("Range") double carbs = cursor.getDouble(cursor.getColumnIndex(COLUMN_CARBS));
                @SuppressLint("Range") double fat = cursor.getDouble(cursor.getColumnIndex(COLUMN_FAT));

                Food food = new Food(id, name, calories, protein, carbs, fat);
                foods.add(food);
            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();

        return foods;
    }

    @Override
    public Food getById(int id) {
        Food food = null;
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FOOD,
                null,
                COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            @SuppressLint("Range") double calories = cursor.getDouble(cursor.getColumnIndex(COLUMN_CALORIES));
            @SuppressLint("Range") double protein = cursor.getDouble(cursor.getColumnIndex(COLUMN_PROTEIN));
            @SuppressLint("Range") double carbs = cursor.getDouble(cursor.getColumnIndex(COLUMN_CARBS));
            @SuppressLint("Range") double fat = cursor.getDouble(cursor.getColumnIndex(COLUMN_FAT));

            food = new Food(id, name, calories, protein, carbs, fat);
            cursor.close();
        }

        db.close();

        return food;
    }

    @Override
    public Food create(Food food) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, food.getName());
        values.put(COLUMN_CALORIES, food.getCaloriePer100g());
        values.put(COLUMN_PROTEIN, food.getProteinPer100g());
        values.put(COLUMN_CARBS, food.getCarbPer100g());
        values.put(COLUMN_FAT, food.getFatPer100g());

        long id = db.insert(TABLE_FOOD, null, values);
        db.close();

        food.setId((int) id); // Update the food object with the new id
        return food;
    }

    @Override
    public void delete(Food food) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.delete(TABLE_FOOD,
                COLUMN_ID + "=?",
                new String[]{String.valueOf(food.getId())});
        db.close();
    }


}
