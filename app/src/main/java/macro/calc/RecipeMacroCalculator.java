package macro.calc;

import java.util.ArrayList;
import java.util.List;

import entities.Macros;
import entities.RecipeFood;

public class RecipeMacroCalculator {
    public Macros calcMacrosInRecipe(List<RecipeFood> recipeFoods){
        Macros macros = new Macros(0,0,0,0,0);
        macros.setCarbsTotal(totalCarbs(recipeFoods));
        macros.setProteinTotal(totalProtein(recipeFoods));
        macros.setFatTotal(totalFats(recipeFoods));
        macros.setGramsTotal(totalGrams(recipeFoods));
        macros.setCaloriesTotal(totalCalories(recipeFoods));

        return macros;
    }

    public double totalGrams(List<RecipeFood> recipeFoods){
        double totalGrams = 0;

        for (int i = 0; i < recipeFoods.size(); i++) {
            totalGrams += recipeFoods.get(i).getAmount();
        }

        return totalGrams;
    }
    public double totalCalories(List<RecipeFood> recipeFoods) {
        double calories = 0;

        for (int i = 0; i < recipeFoods.size(); i++) {
            double caloriePer100g = recipeFoods.get(i).getCaloriesPer100g();
            double amountInGrams = recipeFoods.get(i).getAmount();
            double totalCalorie = (amountInGrams / 100) * caloriePer100g;
            calories += totalCalorie;
        }

        return calories;
    }

    public double totalProtein(List<RecipeFood> recipeFoods) {
        double protein = 0;

        for (RecipeFood recipeFood : recipeFoods) {
            double proteinPer100g = recipeFood.getProteinPer100g();
            double amountInGrams = recipeFood.getAmount();
            double totalProtein = (amountInGrams / 100) * proteinPer100g;
            protein += totalProtein;
        }

        return protein;
    }

    public double totalCarbs(List<RecipeFood> recipeFoods) {
        double carbs = 0;

        for (RecipeFood recipeFood : recipeFoods) {
            double carbsPer100g = recipeFood.getCarbPer100g();
            double amountInGrams = recipeFood.getAmount();
            double totalCarbs = (amountInGrams / 100) * carbsPer100g;
            carbs += totalCarbs;
        }

        return carbs;
    }

    public double totalFats(List<RecipeFood> recipeFoods) {
        double fats = 0;

        for (RecipeFood recipeFood : recipeFoods) {
            double fatsPer100g = recipeFood.getFatPer100g();
            double amountInGrams = recipeFood.getAmount();
            double totalFats = (amountInGrams / 100) * fatsPer100g;
            fats += totalFats;
        }

        return fats;
    }
}
