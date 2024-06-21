package entities;

import java.util.List;

public class Recipe {
    private int id;
    private String name;
    private double caloriesTotal;
    private double proteinsTotal;
    private double carbsTotal;
    private double fatsTotal;
    private List<RecipeFood> recipeFoods;

    public Recipe(int id, String name, double caloriesTotal, double proteinsTotal, double carbsTotal, double fatsTotal) {
        this.id = id;
        this.name = name;
        this.caloriesTotal = caloriesTotal;
        this.proteinsTotal = proteinsTotal;
        this.carbsTotal = carbsTotal;
        this.fatsTotal = fatsTotal;
    }

    public Recipe(String name, double caloriesTotal, double proteinsTotal, double carbsTotal, double fatsTotal) {
        this.name = name;
        this.caloriesTotal = caloriesTotal;
        this.proteinsTotal = proteinsTotal;
        this.carbsTotal = carbsTotal;
        this.fatsTotal = fatsTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCaloriesTotal() {
        return caloriesTotal;
    }

    public void setCaloriesTotal(double caloriesTotal) {
        this.caloriesTotal = caloriesTotal;
    }

    public double getProteinsTotal() {
        return proteinsTotal;
    }

    public void setProteinsTotal(double proteinsTotal) {
        this.proteinsTotal = proteinsTotal;
    }

    public double getCarbsTotal() {
        return carbsTotal;
    }

    public void setCarbsTotal(double carbsTotal) {
        this.carbsTotal = carbsTotal;
    }

    public double getFatsTotal() {
        return fatsTotal;
    }

    public void setFatsTotal(double fatsTotal) {
        this.fatsTotal = fatsTotal;
    }

    public List<RecipeFood> getRecipeFoods() {
        return recipeFoods;
    }

    public void setRecipeFoods(List<RecipeFood> recipeFoods) {
        this.recipeFoods = recipeFoods;
    }
}
