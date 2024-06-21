package entities;

public class Macros {

    private double gramsTotal;
    private double carbsTotal;
    private double proteinTotal;
    private double fatTotal;

    private double caloriesTotal;

    public Macros(double carbsTotal, double proteinTotal, double fatTotal, double gramsTotal, double caloriesTotal) {
        this.carbsTotal = carbsTotal;
        this.proteinTotal = proteinTotal;
        this.fatTotal = fatTotal;
        this.gramsTotal = gramsTotal;
        this.caloriesTotal = caloriesTotal;
    }


    public double getCarbsTotal() {
        return carbsTotal;
    }

    public void setCarbsTotal(double carbsTotal) {
        this.carbsTotal = carbsTotal;
    }

    public double getProteinTotal() {
        return proteinTotal;
    }

    public void setProteinTotal(double proteinTotal) {
        this.proteinTotal = proteinTotal;
    }

    public double getFatTotal() {
        return fatTotal;
    }

    public void setFatTotal(double fatTotal) {
        this.fatTotal = fatTotal;
    }

    public double getGramsTotal() {
        return gramsTotal;
    }

    public void setGramsTotal(double gramsTotal) {
        this.gramsTotal = gramsTotal;
    }

    public double getCaloriesTotal() {
        return caloriesTotal;
    }

    public void setCaloriesTotal(double caloriesTotal) {
        this.caloriesTotal = caloriesTotal;
    }
}
