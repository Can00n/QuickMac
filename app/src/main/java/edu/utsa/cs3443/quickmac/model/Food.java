package edu.utsa.cs3443.quickmac.model;

import java.io.Serial;
import java.io.Serializable;
/**
 * The Food class represents a food consumed by the user
 *
 * @author Canaan Eggers zko777
 */
public class Food implements Serializable {
    private String name;
    private int calories;
    private double fat;
    private double protein;
    private double carbs;
    private double sugar;
    private double fiber;

    /**
     * Constructor for Food objects
     *  -if a user does not know a given value, they are instructed to input 0 or 0.0
     * @param name, the name of the food (String)
     * @param calories, the calories of the food (int)
     * @param fat, the grams of fat in the food (double)
     * @param protein, the grams of protein in the food (double)
     * @param carbs, the grams of carbs in the food (double)
     * @param sugar, the grams of sugar in the food (double)
     * @param fiber, the grams of fiber in the food (double)
     */
    public Food(String name, int calories, double fat, double protein, double carbs, double sugar, double fiber) {
        this.name = name;
        this.calories = calories;
        this.fat = fat;
        this.protein = protein;
        this.carbs = carbs;
        this.sugar = sugar;
        this.fiber = fiber;
    } //Include note to users to input 0 or 0.0 if unknown

    /**
     * setter for name
     * @param name, the name of the food (String)
     */
    public void setName(String name) {this.name = name;}

    /**
     * setter for calories
     * @param calories, the calories of the food (int)
     */
    public void setCalories(int calories) {this.calories = calories;}

    /**
     * setter for fat
     * @param fat, the grams of fat in the food (double)
     */
    public void setFat(double fat) {this.fat = fat;}

    /**
     * setter for protein
     * @param protein, the grams of protein in the food (double)
     */
    public void setProtein(double protein) {this.protein = protein;}

    /**
     * setter for carbs
     * @param carbs, the grams of carbs in the food (double)
     */
    public void setCarbs(double carbs) {this.carbs = carbs;}

    /**
     * setter for sugar
     * @param sugar, the grams of sugar in the food (double)
     */
    public void setSugar(double sugar) {this.sugar = sugar;}

    /**
     * setter for fiber
     * @param fiber, the grams of fiber in the food (double)
     */
    public void setFiber(double fiber) {this.fiber = fiber;}

    /**
     * getter for name
     * @return String, the name of the food
     */
    public String getName() {return name;}

    /**
     * getter for calories
     * @return int, the calories of the food
     */
    public int getCalories() {return calories;}

    /**
     * getter for fat
     * @return double, the grams of fat in the food
     */
    public double getFat() {return fat;}

    /**
     * getter for protein
     * @return double, the grams of protein in the food
     */
    public double getProtein() {return protein;}

    /**
     * getter for carbs
     * @return double, the grams of carbs in the food
     */
    public double getCarbs() {return carbs;}

    /**
     * getter for sugar
     * @return double, the grams of sugar in the food
     */
    public double getSugar() {return sugar;}

    /**
     * getter for fiber
     * @return double, the grams of fiber in the food
     */
    public double getFiber() {return fiber;}

    /**
     * generates a String representation of the Food
     * @return String, a representation of the Food and all of its characteristics
     */
    public String toString() {
        return String.format("%s:\t%d cal\t%.1fg fat\t%.1fg protein\t%.1fg carbs\t%.1fg sugar\t%.1fg fiber", getName(), getCalories(),
                getFat(), getProtein(), getCarbs(), getSugar(), getFiber());
    }

    /**
     * generates a comma delimited String representation of the Food to be written to the csv file
     * @return String, a comma delimited String representation of the Food to be written to the csv file
     */
    public String toCSVString() {
        return String.format("%s,%d,%.1f,%.1f,%.1f,%.1f,%.1f", getName(), getCalories(),
                getFat(), getProtein(), getCarbs(), getSugar(), getFiber());
    }
}
