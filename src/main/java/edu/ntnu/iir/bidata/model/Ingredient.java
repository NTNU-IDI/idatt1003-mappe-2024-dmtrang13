package edu.ntnu.iir.bidata.model;

import java.time.LocalDate;

/**
 * Represents an ingredient with properties such as name, amount, measurement unit,
 * expiration date, and price. This class implements the Comparable interface
 * to enable sorting based on ingredient name and expiration date.
 */
public class Ingredient implements Comparable<Ingredient> {
    private final String ingredientName;
    private double ingredientAmount;
    private String ingredientMeasurement;
    private LocalDate expireDate;
    private double ingredientPrice;

    /**
     * Constructs a new Ingredient with the specified properties.
     *
     * @param ingredientName       the name of the ingredient.
     * @param ingredientAmount     the amount of the ingredient.
     * @param ingredientMeasurement the measurement unit of the ingredient.
     * @param expireDate           the expiration date of the ingredient.
     * @param ingredientPrice      the price of the ingredient.
     */
    public Ingredient(String ingredientName, double ingredientAmount, String ingredientMeasurement, LocalDate expireDate, double ingredientPrice) {
        this.ingredientName = ingredientName;
        this.ingredientAmount = ingredientAmount;
        this.ingredientMeasurement = ingredientMeasurement;
        this.expireDate = expireDate;
        this.ingredientPrice = ingredientPrice;
    }

    /**
     * Retrieves the name of the ingredient.
     *
     * @return the ingredient name.
     */
    public String getIngredientName() {
        return ingredientName;
    }

    /**
     * Retrieves the amount of the ingredient.
     *
     * @return the ingredient amount.
     */
    public double getIngredientAmount() {
        return ingredientAmount;
    }

    /**
     * Sets the amount of the ingredient.
     *
     * @param ingredientAmount the new ingredient amount.
     */
    public void setIngredientAmount(double ingredientAmount) {
        this.ingredientAmount = ingredientAmount;
    }

    /**
     * Retrieves the measurement unit of the ingredient.
     *
     * @return the ingredient measurement unit.
     */
    public String getIngredientMeasurement() {
        return ingredientMeasurement;
    }

    /**
     * Sets the measurement unit of the ingredient.
     *
     * @param ingredientMeasurement the new measurement unit.
     */
    public void setIngredientMeasurement(String ingredientMeasurement) {
        this.ingredientMeasurement = ingredientMeasurement;
    }

    /**
     * Retrieves the expiration date of the ingredient.
     *
     * @return the expiration date.
     */
    public LocalDate getExpireDate() {
        return expireDate;
    }

    /**
     * Sets the expiration date of the ingredient.
     *
     * @param expireDate the new expiration date.
     */
    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * Retrieves the price of the ingredient.
     *
     * @return the ingredient price.
     */
    public double getIngredientPrice() {
        return ingredientPrice;
    }

    /**
     * Sets the price of the ingredient.
     *
     * @param ingredientPrice the new ingredient price.
     */
    public void setIngredientPrice(double ingredientPrice) {
        this.ingredientPrice = ingredientPrice;
    }

    /**
     * Returns a string representation of the ingredient, including its name, amount, measurement unit,
     * expiration date, and price.
     *
     * @return a string containing the details of the ingredient.
     */
    @Override
    public String toString() {
        return "Ingredient: " + ingredientName
                + " " + ingredientAmount
                + " " + ingredientMeasurement
                + "\nExpire date: " + expireDate
                + "\nPrice: " + ingredientPrice + " kr"
                + "\n";
    }

    /**
     * Compares this ingredient to another based on name (case-insensitive) and expiration date.
     *
     * @param other the ingredient to compare to.
     * @return a negative value if this ingredient is less than the other, zero if equal, and a positive value if greater.
     */
    @Override
    public int compareTo(Ingredient other) {
        int nameComparison = this.ingredientName.compareToIgnoreCase(other.ingredientName);
        if (nameComparison != 0) return nameComparison;
        return this.expireDate.compareTo(other.expireDate);
    }
}
