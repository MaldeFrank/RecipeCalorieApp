package entities;

public class Meal {

    private int id;
    private String recipeName;
    private double amount;
    private double calories;
    private double proteins;
    private double carbs;
    private double fats;

    public Meal(int id, String recipeName, double amount, double calories, double proteins, double carbs, double fats) {
        this.id = id;
        this.recipeName = recipeName;
        this.amount = amount;
        this.calories = calories;
        this.proteins = proteins;
        this.carbs = carbs;
        this.fats = fats;
    }

    public Meal(String recipeName, double amount, double calories, double proteins, double carbs, double fats) {
        this.recipeName = recipeName;
        this.amount = amount;
        this.calories = calories;
        this.proteins = proteins;
        this.carbs = carbs;
        this.fats = fats;
    }

    public Meal(Recipe recipe, double amount){
        this.recipeName = recipe.getName();
        this.amount = amount;
        this.calories = recipe.getCaloriesTotal();
        this.proteins = recipe.getProteinsTotal();
        this.carbs = recipe.getCarbsTotal();
        this.fats = recipe.getFatsTotal();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }
}
