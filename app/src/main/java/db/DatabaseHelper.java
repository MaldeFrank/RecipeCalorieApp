package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "myRecipe";
    private static final int DATABASE_VERSION = 1;

    // Singleton instance
    private static DatabaseHelper instance;

    // Private constructor to prevent direct instantiation
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Public method to get the singleton instance
    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Food (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "caloriesPer100g REAL," +
                "proteinsPer100g REAL," +
                "carbsPer100g REAL," +
                "fatsPer100g REAL" +
                ")");

        db.execSQL("CREATE TABLE IF NOT EXISTS Recipe (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "caloriesTotal REAL," + // Use REAL for double values in SQLite
                "proteinTotal REAL," +
                "carbsTotal REAL," +
                "fatsTotal REAL" +
                ")");

        db.execSQL("CREATE TABLE IF NOT EXISTS RecipeFood (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "recipe_id REAL," +
                "food_id REAL," +
                "amount REAL," + // amount of food in the recipe, for example in grams
                "caloriesPer100g REAL," +
                "proteinsPer100g REAL," +
                "carbsPer100g REAL," +
                "fatsPer100g REAL," +
                "name TEXT," +  // Add the "name" column with data type TEXT (or similar for storing food names)
                "FOREIGN KEY (recipe_id) REFERENCES Recipe(id)," +
                "FOREIGN KEY (food_id) REFERENCES Food(id)" +
                ")");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrade logic here
    }
}
