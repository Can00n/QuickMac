package edu.utsa.cs3443.quickmac;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import edu.utsa.cs3443.quickmac.model.Day;
import edu.utsa.cs3443.quickmac.model.Food;

public class DayActivity extends AppCompatActivity {

    private Day currentDay;
    private TextView foodContentsView, macrosCountView, proteinCountView, calorieCountView, fatCountView, sugarCountView, fiberCountView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        initializeDay();
        initializeViews();
        updateDayDisplay();

        Button addFoodButton = findViewById(R.id.btnAddFoodItem);
        addFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DayActivity.this, NewFoodActivity.class);
                startActivityForResult(intent, 1); // Request code 1 for adding food
            }
        });
    }

    private void initializeDay() {
        // This method should initialize the currentDay object
        String todayDate = "04-22-2024";  // Example date, should be dynamically set
        currentDay = new Day(todayDate);
    }

    private void initializeViews() {
        foodContentsView = findViewById(R.id.food_contents);
        macrosCountView = findViewById(R.id.macros_dailyCount);
        proteinCountView = findViewById(R.id.protein_dailyCount);
        calorieCountView = findViewById(R.id.calorie_dailyCount);
        fatCountView = findViewById(R.id.fat_dailyCount);
        sugarCountView = findViewById(R.id.sugar_dailyCount);
        fiberCountView = findViewById(R.id.fiber_dailyCount);
    }

    private void updateDayDisplay() {
        StringBuilder foodNames = new StringBuilder();
        for (Food food : currentDay.getFoods()) {
            if (foodNames.length() > 0) foodNames.append(", ");  // Add a comma separator between food names
            foodNames.append(food.getName());
        }

        // Update the display with the information from currentDay
        foodContentsView.setText("Foods today: " + foodNames.toString());
        macrosCountView.setText("Total Macros: " + currentDay.getTotalCarbs() + "g");
        proteinCountView.setText("Total Protein: " + currentDay.getTotalProtein() + "g");
        calorieCountView.setText("Total Calories: " + currentDay.getTotalCalories());
        fatCountView.setText("Total Fat: " + currentDay.getTotalFat() + "g");
        sugarCountView.setText("Total Sugar: " + currentDay.getTotalSugar() + "g");
        fiberCountView.setText("Total Fiber: " + currentDay.getTotalFiber() + "g");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Assuming you pass back a Food object
            Food newFood = (Food) data.getSerializableExtra("newFood");
            currentDay.addFood(newFood);
            updateDayDisplay();  // Refresh the UI with the new food item
        }
    }
}
