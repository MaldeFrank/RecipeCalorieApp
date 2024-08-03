package dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import db.DatabaseHelper;
import entities.Meal;

public class MealDAO implements DAOInterface<Meal> {

    public static final String TABLE_MEAL = "meal";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_RECIPE_NAME = "recipe_name";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_CALORIES = "calories";
    public static final String COLUMN_PROTEIN = "proteins";
    public static final String COLUMN_CARBS = "carbs";
    public static final String COLUMN_FAT = "fats";

    private DatabaseHelper databaseHelper;

    public MealDAO(Context context, DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    @Override
    public List<Meal> getAll() {
        List<Meal> meals = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MEAL,
                null,
                null,
                null,
                null,
                null,
                null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") String recipeName = cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_NAME));
                @SuppressLint("Range") double amount = cursor.getDouble(cursor.getColumnIndex(COLUMN_AMOUNT));
                @SuppressLint("Range") double calories = cursor.getDouble(cursor.getColumnIndex(COLUMN_CALORIES));
                @SuppressLint("Range") double protein = cursor.getDouble(cursor.getColumnIndex(COLUMN_PROTEIN));
                @SuppressLint("Range") double carbs = cursor.getDouble(cursor.getColumnIndex(COLUMN_CARBS));
                @SuppressLint("Range") double fat = cursor.getDouble(cursor.getColumnIndex(COLUMN_FAT));

                Meal meal = new Meal(id, recipeName, amount, calories, protein, carbs, fat);
                meals.add(meal);
            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();

        return meals;
    }

    @Override
    public Meal getById(int id) {
        Meal meal = null;
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MEAL,
                null,
                COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") String recipeName = cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_NAME));
            @SuppressLint("Range") double amount = cursor.getDouble(cursor.getColumnIndex(COLUMN_AMOUNT));
            @SuppressLint("Range") double calories = cursor.getDouble(cursor.getColumnIndex(COLUMN_CALORIES));
            @SuppressLint("Range") double protein = cursor.getDouble(cursor.getColumnIndex(COLUMN_PROTEIN));
            @SuppressLint("Range") double carbs = cursor.getDouble(cursor.getColumnIndex(COLUMN_CARBS));
            @SuppressLint("Range") double fat = cursor.getDouble(cursor.getColumnIndex(COLUMN_FAT));

            meal = new Meal(id, recipeName, amount, calories, protein, carbs, fat);
            cursor.close();
        }

        db.close();

        return meal;
    }

    @Override
    public Meal create(Meal meal) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_RECIPE_NAME, meal.getRecipeName());
        values.put(COLUMN_AMOUNT, meal.getAmount());
        values.put(COLUMN_CALORIES, meal.getCalories());
        values.put(COLUMN_PROTEIN, meal.getProteins());
        values.put(COLUMN_CARBS, meal.getCarbs());
        values.put(COLUMN_FAT, meal.getFats());

        long id = db.insert(TABLE_MEAL, null, values);
        db.close();

        meal.setId((int) id); // Update the meal object with the new id
        return meal;
    }

    @Override
    public void delete(Meal meal) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.delete(TABLE_MEAL,
                COLUMN_ID + "=?",
                new String[]{String.valueOf(meal.getId())});
        db.close();
    }

    public void setAmount(int id, double newAmount) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_AMOUNT, Math.max(0, newAmount));

        db.update(TABLE_MEAL, values, COLUMN_ID + "=?", new String[]{String.valueOf(id)});

        db.close();
    }
}
