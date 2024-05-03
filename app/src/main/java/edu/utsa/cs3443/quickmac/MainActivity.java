package edu.utsa.cs3443.quickmac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.username_view);
        passwordEditText = findViewById(R.id.password_view);
        loginButton = findViewById(R.id.login_button);
        signupButton = findViewById(R.id.signup_button);

        setupButtonListeners();
    }

    private void setupButtonListeners() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(usernameEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp(usernameEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });
    }

    private void signIn(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Username and password cannot be empty", Toast.LENGTH_SHORT).show();
            return; // Exit the method early if any field is empty
        }

        File csvFile = new File(getFilesDir(), "users.csv");
        try {
            Scanner scanner = new Scanner(csvFile);
            boolean found = false;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.isEmpty()) {
                    String[] data = line.split(",");
                    if (data.length >= 2 && data[0].equals(username) && data[1].equals(password)) {
                        found = true;
                        break;
                    }
                }
            }
            scanner.close();
            if (found) {
                Toast.makeText(this, "User signed in successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, DayActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            Toast.makeText(this, "Failed to sign in", Toast.LENGTH_SHORT).show();
        }
    }



    private void signUp(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Username and password cannot be empty", Toast.LENGTH_SHORT).show();
            return; // Exit the method early if any field is empty
        }

        File csvFile = new File(getFilesDir(), "users.csv");
        boolean usernameExists = false;

        try {
            // Check if the username already exists in the file
            Scanner scanner = new Scanner(csvFile);
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                if (data.length > 0 && data[0].equals(username)) {
                    usernameExists = true;
                    break;
                }
            }
            scanner.close();

            // If username does not exist, append new user to the file
            if (!usernameExists) {
                FileWriter writer = new FileWriter(csvFile, true); // Append mode
                writer.append(username + "," + password + "\n");
                writer.close();
                Toast.makeText(this, "User registered successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Username already exists. Please try a different username.", Toast.LENGTH_LONG).show();
            }

        } catch (IOException e) {
            Toast.makeText(this, "Failed to register user", Toast.LENGTH_SHORT).show();
        }
    }
}
