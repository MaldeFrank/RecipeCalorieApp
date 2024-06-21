package entities;

public class RecipeFood {
    private int id;
    private int foodId;
    private int recipeId;
    private String name;
    private double caloriesPer100g;
    private double proteinPer100g;
    private double carbPer100g;
    private double fatPer100g;
    private double amount;

    public RecipeFood(int id, int foodId, int recipeId, String name, double caloriesPer100g, double proteinPer100g, double carbPer100g, double fatPer100g, double amount) {
        this.id = id;
        this.foodId = foodId;
        this.recipeId = recipeId;
        this.name = name;
        this.caloriesPer100g = caloriesPer100g;
        this.proteinPer100g = proteinPer100g;
        this.carbPer100g = carbPer100g;
        this.fatPer100g = fatPer100g;
        this.amount = amount;
    }

    public RecipeFood(int foodId, int recipeId, String name, double caloriesPer100g, double proteinPer100g, double carbPer100g, double fatPer100g, double amount) {
        this.foodId = foodId;
        this.recipeId = recipeId;
        this.name = name;
        this.caloriesPer100g = caloriesPer100g;
        this.proteinPer100g = proteinPer100g;
        this.carbPer100g = carbPer100g;
        this.fatPer100g = fatPer100g;
        this.amount = amount;
    }

    public RecipeFood(Food food, double amount, int recipeId) {
        this.foodId = food.getId();
        this.recipeId = recipeId;
        this.name = food.getName();
        this.caloriesPer100g = food.getCaloriePer100g();
        this.proteinPer100g = food.getProteinPer100g();
        this.carbPer100g = food.getCarbPer100g();
        this.fatPer100g = food.getFatPer100g();
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCaloriesPer100g() {
        return caloriesPer100g;
    }

    public void setCaloriesPer100g(double caloriesPer100g) {
        this.caloriesPer100g = caloriesPer100g;
    }

    public double getProteinPer100g() {
        return proteinPer100g;
    }

    public void setProteinPer100g(double proteinPer100g) {
        this.proteinPer100g = proteinPer100g;
    }

    public double getCarbPer100g() {
        return carbPer100g;
    }

    public void setCarbPer100g(double carbPer100g) {
        this.carbPer100g = carbPer100g;
    }

    public double getFatPer100g() {
        return fatPer100g;
    }

    public void setFatPer100g(double fatPer100g) {
        this.fatPer100g = fatPer100g;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
