package dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

import db.DatabaseHelper;
import entities.RecipeFood;

public class RecipeFoodDAO implements DAOInterface<RecipeFood> {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    private String TABLE_RECIPE_FOOD = "recipefood";

    public RecipeFoodDAO(Context context, DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @Override
    public List<RecipeFood> getAll() {
        List<RecipeFood> recipeFoods = new ArrayList<>();
        db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                "recipefood", // Table name
                null, // All columns
                null,
                null,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") RecipeFood recipeFood = new RecipeFood(
                        cursor.getInt(cursor.getColumnIndex("id")),
                        cursor.getInt(cursor.getColumnIndex("food_id")),
                        cursor.getInt(cursor.getColumnIndex("recipe_id")),
                        cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getDouble(cursor.getColumnIndex("caloriesPer100g")),
                        cursor.getDouble(cursor.getColumnIndex("proteinsPer100g")),
                        cursor.getDouble(cursor.getColumnIndex("carbsPer100g")),
                        cursor.getDouble(cursor.getColumnIndex("fatsPer100g")),
                        cursor.getDouble(cursor.getColumnIndex("amount"))
                );
                recipeFoods.add(recipeFood);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return recipeFoods;
    }

    @SuppressLint("Range")
    @Override
    public RecipeFood getById(int id) {
        db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                "RecipeFood",
                null,
                "id=?",
                new String[] { String.valueOf(id) },
                null,
                null,
                null
        );

        RecipeFood recipeFood = null;
        if (cursor.moveToFirst()) {
            recipeFood = new RecipeFood(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getInt(cursor.getColumnIndex("food_id")),
                    cursor.getInt(cursor.getColumnIndex("recipe_id")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getDouble(cursor.getColumnIndex("caloriesPer100g")),
                    cursor.getDouble(cursor.getColumnIndex("proteinsPer100g")),
                    cursor.getDouble(cursor.getColumnIndex("carbsPer100g")),
                    cursor.getDouble(cursor.getColumnIndex("fatsPer100g")),
                    cursor.getDouble(cursor.getColumnIndex("amount"))
            );
        }

        cursor.close();
        db.close();

        return recipeFood;
    }

    @Override
    public RecipeFood create(RecipeFood recipeFood) {
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("food_id", recipeFood.getFoodId());
        values.put("recipe_id", recipeFood.getRecipeId());
        values.put("name", recipeFood.getName());
        values.put("caloriesPer100g", recipeFood.getCaloriesPer100g());
        values.put("proteinsPer100g", recipeFood.getProteinPer100g());
        values.put("carbsPer100g", recipeFood.getCarbPer100g());
        values.put("fatsPer100g", recipeFood.getFatPer100g());
        values.put("amount", recipeFood.getAmount());

        long id = db.insert("RecipeFood", null, values);
        db.close();

        recipeFood.setId((int) id); // Set the id of the object to match the inserted id

        return recipeFood;
    }

    @Override
    public void delete(RecipeFood recipeFood) {
        db = dbHelper.getWritableDatabase();

        db.delete(
                "RecipeFood",
                "id=?",
                new String[] { String.valueOf(recipeFood.getId()) }
        );

        db.close();
    }
    public List<RecipeFood> getAllById(int id) {
        List<RecipeFood> recipeFoods = new ArrayList<>();
        db = dbHelper.getReadableDatabase();

        String selection = "recipe_id=?";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor cursor = db.query(
                "recipefood", // Table name
                null, // All columns
                selection, // Selection clause
                selectionArgs, // Selection arguments
                null, // Group By
                null, // Having
                null  // Order By
        );

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") RecipeFood recipeFood = new RecipeFood(
                        cursor.getInt(cursor.getColumnIndex("id")),
                        cursor.getInt(cursor.getColumnIndex("food_id")),
                        cursor.getInt(cursor.getColumnIndex("recipe_id")),
                        cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getDouble(cursor.getColumnIndex("caloriesPer100g")),
                        cursor.getDouble(cursor.getColumnIndex("proteinsPer100g")),
                        cursor.getDouble(cursor.getColumnIndex("carbsPer100g")),
                        cursor.getDouble(cursor.getColumnIndex("fatsPer100g")),
                        cursor.getDouble(cursor.getColumnIndex("amount"))
                );
                recipeFoods.add(recipeFood);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return recipeFoods;
    }

}
