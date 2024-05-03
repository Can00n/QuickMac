package edu.utsa.cs3443.quickmac;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import edu.utsa.cs3443.quickmac.model.Food;

public class NewFoodActivity extends AppCompatActivity {

    private EditText editTextFoodName, editTextCalories, editTextCarbs, editTextProtein, editTextFat, editTextSugar, editTextFiber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_food);

        editTextFoodName = findViewById(R.id.editTextFoodName);
        editTextCalories = findViewById(R.id.editTextCalories);
        editTextCarbs = findViewById(R.id.editTextCarbs);
        editTextProtein = findViewById(R.id.editTextProtein);
        editTextFat = findViewById(R.id.editTextFat);
        editTextSugar = findViewById(R.id.editTextSugar);
        editTextFiber = findViewById(R.id.editTextFiber);

        Button addButton = findViewById(R.id.button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String foodName = editTextFoodName.getText().toString();
                int calories = Integer.parseInt(editTextCalories.getText().toString());
                double carbs = Double.parseDouble(editTextCarbs.getText().toString());
                double protein = Double.parseDouble(editTextProtein.getText().toString());
                double fat = Double.parseDouble(editTextFat.getText().toString());
                double sugar = Double.parseDouble(editTextSugar.getText().toString());
                double fiber = Double.parseDouble(editTextFiber.getText().toString());

                Food newFood = new Food(foodName, calories, fat, protein, carbs, sugar, fiber);
                Intent data = new Intent();
                data.putExtra("newFood", newFood);
                setResult(RESULT_OK, data);
                finish(); // Ends the activity and returns to DayActivity
            }
        });
    }
}
