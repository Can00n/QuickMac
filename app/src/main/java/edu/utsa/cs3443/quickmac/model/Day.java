package edu.utsa.cs3443.quickmac.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The Day class represents 1 day logged in the logbook
 *
 * @author Canaan Eggers zko777
 */
public class Day {
    private String date;
    private ArrayList<Food> foods = new ArrayList<>();
    private int totalCalories;
    private double totalFat;
    private double totalProtein;
    private double totalCarbs;
    private double totalSugar;
    private double totalFiber;

    /**
     * Constructor for Day objects
     *  -since days cannot be initialized with existing data, only the dat is needed
     * @param date, the calendar date of the Day object (String)
     */
    public Day(String date) {
        this.date = date;
        this.foods = new ArrayList<Food>(foods);
        this.totalCalories = 0;
        this.totalFat = 0.0;
        this.totalProtein = 0.0;
        this.totalCarbs = 0.0;
        this.totalSugar = 0.0;
        this.totalFiber = 0.0;
    }

    /**
     * setter for the calendar date
     * @param date, the calendar date of the Day object (String)
     */
    public void setDate(String date) {this.date = date;}

    /**
     * setter for the foods ArrayList
     * @param foods, a list of all foods consumed in the day (ArrayList<Food>)
     */
    public void setFoods(ArrayList<Food> foods) {this.foods = foods;}

    /**
     * setter for totalCalories
     * @param totalCalories, the total calories consumed in the day (int)
     */
    public void setTotalCalories(int totalCalories) {this.totalCalories = totalCalories;}

    /**
     * setter for totalFat
     * @param totalFat, the total grams of fat consumed in the day (double)
     */
    public void setTotalFat(double totalFat) {this.totalFat = totalFat;}

    /**
     * setter for totalProtein
     * @param totalProtein, the total grams of protein consumed in the day (double)
     */
    public void setTotalProtein(double totalProtein) {this.totalProtein = totalProtein;}

    /**
     * setter for totalCarbs
     * @param totalCarbs, the total grams of carbs consumed in the day (double)
     */
    public void setTotalCarbs(double totalCarbs) {this.totalCarbs = totalCarbs;}

    /**
     * setter for totalSugar
     * @param totalSugar, the total grams of sugar consumed in the day (double)
     */
    public void setTotalSugar(double totalSugar) {this.totalSugar = totalSugar;}

    /**
     * setter for totalFiber
     * @param totalFiber, the total grams of fiber consumed in the day (double)
     */
    public void setTotalFiber(double totalFiber) {this.totalFiber = totalFiber;}


    /**
     * getter for the calendar date of the Day
     * @return String, the calendar date of the Day
     */
    public String getDate(){return date;}

    /**
     * getter for the foods ArrayList
     * @return ArrayList<Food>, a list of all the foods consumed in the day
     */
    public ArrayList<Food> getFoods() {
        ArrayList<Food> foods = new ArrayList<>();
        String csvFile = "users.csv";
        String line;
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(cvsSplitBy);
                if (tokens.length > 0 && tokens[0].equals(date)) {
                    for (int i = 3; i < tokens.length; i+=7) {
                        foods.add(new Food(tokens[i].trim(), Integer.parseInt(tokens[i+1].trim()),
                                Double.parseDouble(tokens[i+2].trim()), Double.parseDouble(tokens[i+3].trim()),
                                Double.parseDouble(tokens[i+4].trim()), Double.parseDouble(tokens[i+5].trim()),
                                Double.parseDouble(tokens[i+6].trim())));
                    }
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return foods;
    }

    /**
     * getter for totalCalories
     * @return int, the total calories consumed in the day
     */
    public int getTotalCalories() {
        totalCalories = 0;
        for(Food food: foods){
            totalCalories += food.getCalories();
        }
        return totalCalories;
    }

    /**
     * getter for totalFat
     * @return double, the total grams of fat consumed in the day
     */
    public double getTotalFat() {
        totalFat = 0;
        for(Food food: foods){
            totalFat += food.getFat();
        }
        return totalFat;
    }

    /**
     * getter for totalProtein
     * @return double, the total grams of protein consumed in the day
     */
    public double getTotalProtein() {
        totalProtein = 0;
        for(Food food: foods){
            totalProtein += food.getProtein();
        }
        return totalProtein;
    }

    /**
     * getter for totalCarbs
     * @return double, the total grams of carbs consumed in the day
     */
    public double getTotalCarbs() {
        totalCarbs = 0;
        for(Food food: foods){
            totalCarbs += food.getCarbs();
        }
        return totalCarbs;
    }

    /**
     * getter for totalSugar
     * @return double, the total grams of sugar consumed in the day
     */
    public double getTotalSugar() {
        totalSugar = 0;
        for(Food food: foods){
            totalSugar += food.getSugar();
        }
        return totalSugar;
    }

    /**
     * getter for totalFiber
     * @return double, the total grams of fiber consumed in the day
     */
    public double getTotalFiber() {
        totalFiber = 0;
        for(Food food: foods){
            totalFiber += food.getFiber();
        }
        return totalFiber;
    }

    /**
     * adds a new Food object to the foods ArrayList
     * @param newFood, the food to be added to the list (Food)
     */
    public void addFood(Food newFood) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("users.csv", true))) {
            bw.write(date + "," + newFood.toCSVString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * removes a Food object from the foods ArrayList
     * @param outFood, the food to be removed from the list (Food)
     */
    public void removeFood(Food outFood) {
        String csvFile = "users.csv";
        String line;
        String cvsSplitBy = ",";
        ArrayList<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(cvsSplitBy);
                if (tokens.length > 0 && tokens[0].equals(date)) {
                    StringBuilder newLine = new StringBuilder(date);
                    for (String token : tokens) {
                        if (!token.equals(outFood.toCSVString())) {
                            newLine.append(",").append(token);
                        }
                    }
                    lines.add(newLine.toString());
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {
            for (String l : lines) {
                bw.write(l + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * create a String listing the foods ArrayList
     * @param foods, a list of all foods consumed in the day (ArrayList<Food>)
     * @return String, a text String listing all the foods consumed in the day
     */
    public String listFoods(ArrayList<Food> foods) {
        StringBuilder foodList = new StringBuilder();
        for(Food food : foods) {
            foodList.append(food);
        }
        return foodList.toString();
    }

    /**
     * generates a String representation of the Day
     * @return String, a String representing the Day and all of its characteristics
     */
    public String toString() {
        return String.format("%s\n%s\nTotal calories: %d\nTotal fat: %.1fg\nTotal protein: %.1fg\nTotal carbs: %.1fg\nTotal sugar: %.1fg\nTotal fiber: %.1fg",
                getDate(), listFoods(foods), getTotalCalories(), getTotalFat(), getTotalProtein(), getTotalCarbs(), getTotalSugar(), getTotalFiber());
    }

    /**
     * generates a comma delimited String representation of the Day to be written to the csv file
     * @return String, a comma delimited String representation of the Day to be written to the csv file
     */
    public String toCSVString() {
        return String.format("%s,%s,%d,%.1f,%.1f,%.1f,%.1f", getDate(), listFoods(foods), getTotalCalories(), getTotalFat(), getTotalProtein(), getTotalCarbs(), getTotalSugar(), getTotalFiber());
    }
}
