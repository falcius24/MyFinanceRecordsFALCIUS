package com.tmdsimple.myfinancerecords;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserActivity extends AppCompatActivity {

    // Existing declarations...
    Button btnLogOut;
    TextView txtUser;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    private FirebaseAuth.AuthStateListener authStateListener;

    // New declarations for Choose Level functionality
    Button btnChooseLevel;
    Spinner spinnerGrade;
    TextView txtReadingMaterials, txtActivities, txtProgressTracking, txtFeedbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        firebaseAuth = FirebaseAuth.getInstance();
        btnLogOut = findViewById(R.id.btnLogOut);
        txtUser = findViewById(R.id.txtUser);
        user = firebaseAuth.getCurrentUser();

        // Check if the user is logged in
        if (user == null) {
            // If not, redirect to LoginActivity
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            // If logged in, display welcome message
            txtUser.setText("Welcome, " + user.getEmail());
        }

        // Initialize the new UI elements
        btnChooseLevel = findViewById(R.id.btnChooseLevel);
        spinnerGrade = findViewById(R.id.spinnerGrade);
        txtReadingMaterials = findViewById(R.id.txtReadingMaterials);
        txtActivities = findViewById(R.id.txtActivities);
        txtProgressTracking = findViewById(R.id.txtProgressTracking);
        txtFeedbacks = findViewById(R.id.txtFeedbacks);

        // Set up the ArrayAdapter for the Spinner using the 'grades' array resource
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.grades, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinnerGrade.setAdapter(adapter);

        // Set up a click listener for the "Choose Level" button
        btnChooseLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the selected grade from the Spinner
                String selectedGrade = spinnerGrade.getSelectedItem().toString();

                // Handle the selected grade
                handleSelectedGrade(selectedGrade);

                // Start ReadingMaterialActivity with the selected grade
                Intent intent = new Intent(UserActivity.this, ReadingMaterialActivity.class);
                intent.putExtra("grade", selectedGrade);
                startActivity(intent);
            }
        });

        // Set up a click listener for the "Log Out" button
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log out the user and redirect to LoginActivity
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    // Rest of your existing methods...

    // New method to handle the selected grade
    private void handleSelectedGrade(String grade) {
        // Show a welcome message
        showToast("Welcome Student!");

        // Show relevant content based on the selected grade
        switch (grade) {
            case "Grade 1":
                showGradeContent("Reading Materials for Grade 1", "Activities for Grade 1", "Progress Tracking for Grade 1", "Feedbacks for Grade 1");
                break;
            case "Grade 2":
                showGradeContent("Reading Materials for Grade 2", "Activities for Grade 2", "Progress Tracking for Grade 2", "Feedbacks for Grade 2");
                break;
            case "Grade 3":
                showGradeContent("Reading Materials for Grade 3", "Activities for Grade 3", "Progress Tracking for Grade 3", "Feedbacks for Grade 3");
                break;
            case "Grade 4":
                showGradeContent("Reading Materials for Grade 4", "Activities for Grade 4", "Progress Tracking for Grade 4", "Feedbacks for Grade 4");
                break;
            case "Grade 5":
                showGradeContent("Reading Materials for Grade 5", "Activities for Grade 5", "Progress Tracking for Grade 5", "Feedbacks for Grade 5");
                break;
            case "Grade 6":
                showGradeContent("Reading Materials for Grade 6", "Activities for Grade 6", "Progress Tracking for Grade 6", "Feedbacks for Grade 6");
                break;    // Add cases for other grades...
            default:
                // Handle unknown grade
                showToast("Unknown Grade");
        }
    }

    // New method to show content for the selected grade
    private void showGradeContent(String readingMaterials, String activities, String progressTracking, String feedbacks) {
        // Show relevant content for the selected grade
        txtReadingMaterials.setText(readingMaterials);
        txtActivities.setText(activities);
        txtProgressTracking.setText(progressTracking);
        txtFeedbacks.setText(feedbacks);

        // Set the visibility of TextViews to VISIBLE
        txtReadingMaterials.setVisibility(View.VISIBLE);
        txtActivities.setVisibility(View.VISIBLE);
        txtProgressTracking.setVisibility(View.VISIBLE);
        txtFeedbacks.setVisibility(View.VISIBLE);
    }

    // New method to show a Toast message
    private void showToast(String message) {
        Toast.makeText(UserActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
