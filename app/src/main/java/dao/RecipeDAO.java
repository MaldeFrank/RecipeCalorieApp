package dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import db.DatabaseHelper;
import entities.Recipe;

public class RecipeDAO implements DAOInterface<Recipe> {
    private DatabaseHelper db;
    public static final String TABLE_RECIPE = "Recipe";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_CALORIES = "caloriesTotal";
    public static final String COLUMN_PROTEIN = "proteinTotal";
    public static final String COLUMN_CARBS = "carbsTotal";
    public static final String COLUMN_FAT = "fatsTotal";

    public RecipeDAO(Context context){
        db = DatabaseHelper.getInstance(context);
    }

    @Override
    public List<Recipe> getAll() {
        List<Recipe> recipes = new ArrayList<>();

        SQLiteDatabase database = db.getReadableDatabase();

        Cursor cursor = database.query(TABLE_RECIPE,
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

                Recipe recipe = new Recipe(id, name, calories, protein, carbs, fat);
                recipes.add(recipe);
            } while (cursor.moveToNext());

            cursor.close();
        }

        database.close();

        return recipes;
    }

    @Override
    public Recipe getById(int id) {
        Recipe recipe = null;
        SQLiteDatabase database = db.getReadableDatabase();

        Cursor cursor = database.query(TABLE_RECIPE,
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

            recipe = new Recipe(id, name, calories, protein, carbs, fat);
            cursor.close();
        }

        database.close();

        return recipe;
    }

    @Override
    public Recipe create(Recipe recipe) {
        SQLiteDatabase database = db.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, recipe.getName());
        values.put(COLUMN_CALORIES, recipe.getCaloriesTotal());
        values.put(COLUMN_PROTEIN, recipe.getProteinsTotal());
        values.put(COLUMN_CARBS, recipe.getCarbsTotal());
        values.put(COLUMN_FAT, recipe.getFatsTotal());

        long id = database.insert(TABLE_RECIPE, null, values);
        recipe.setId((int) id); // Update the recipe object with the new id

        database.close();

        return recipe;
    }

    @Override
    public void delete(Recipe recipe) {
        SQLiteDatabase database = db.getWritableDatabase();
        database.delete(TABLE_RECIPE,
                COLUMN_ID + "=?",
                new String[]{String.valueOf(recipe.getId())});
        database.close();
    }

    public int updateRecipeName(int id, String newName) {
        SQLiteDatabase database = db.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, newName);

        int rowsUpdated = database.update(TABLE_RECIPE, contentValues, COLUMN_ID + "=?", new String[]{String.valueOf(id)});

        database.close();

        return rowsUpdated;
    }

    public void updateMacros(int recipeId, double caloriesTotal, double proteinsTotal, double carbsTotal, double fatsTotal) {
        SQLiteDatabase database = db.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CALORIES, caloriesTotal);
        values.put(COLUMN_PROTEIN, proteinsTotal);
        values.put(COLUMN_CARBS, carbsTotal);
        values.put(COLUMN_FAT, fatsTotal);

        // Opdateringsbetingelse
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = { String.valueOf(recipeId) };

        // Udfør opdateringen
        int count = database.update(
                TABLE_RECIPE,
                values,
                selection,
                selectionArgs);

        database.close();

        // Log antallet af opdaterede rækker (valgfrit)
        Log.d("RecipeDAO", "updateMacros: Updated " + count + " rows for recipeId " + recipeId);
    }
}
