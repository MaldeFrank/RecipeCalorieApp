package entities;

public class Food {
    private int id;
    private String name;
    private double caloriePer100g;
    private double proteinPer100g;
    private double carbPer100g;
    private double fatPer100g;

    public Food(int id, String name, double caloriePer100g, double proteinPer100g, double carbPer100g, double fatPer100g) {
        this.id = id;
        this.name = name;
        this.caloriePer100g = caloriePer100g;
        this.proteinPer100g = proteinPer100g;
        this.carbPer100g = carbPer100g;
        this.fatPer100g = fatPer100g;
    }

    public Food(String name, double caloriePer100g, double proteinPer100g, double carbPer100g, double fatPer100g) {
        this.name = name;
        this.caloriePer100g = caloriePer100g;
        this.proteinPer100g = proteinPer100g;
        this.carbPer100g = carbPer100g;
        this.fatPer100g = fatPer100g;
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

    public double getCaloriePer100g() {
        return caloriePer100g;
    }

    public void setCaloriePer100g(double caloriePer100g) {
        this.caloriePer100g = caloriePer100g;
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
}
