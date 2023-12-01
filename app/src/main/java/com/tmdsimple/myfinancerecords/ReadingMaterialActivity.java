package com.tmdsimple.myfinancerecords;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReadingMaterialActivity extends AppCompatActivity {

    // Declare TextViews and Buttons for Activities, Progress Tracking, and Feedback
    private TextView txtActivitiesTitle, txtProgressTrackingTitle, txtFeedbackTitle, txtStory;
    private Button btnActivities, btnProgressTracking, btnFeedback;

    // MediaPlayer for playing sounds
    private MediaPlayer mediaPlayer;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_material);

        // Retrieve the selected grade from the intent
        String selectedGrade = getIntent().getStringExtra("grade");
        Log.d("ReadingMaterialActivity", "Received grade: " + selectedGrade);

        // Find the "Open" button by ID
        Button btnOpen = findViewById(R.id.btnOpen);
        txtStory = findViewById(R.id.txtStory); // Assuming you have a TextView with the ID txtStory in your layout

        // Set a click listener for the "Open" button
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ReadingMaterialActivity", "Open button clicked");
                // Handle opening the reading material
                openReadingMaterial(selectedGrade);
            }
        });

        // Initialize TextViews and Buttons for Activities, Progress Tracking, and Feedback
        txtActivitiesTitle = findViewById(R.id.txtActivitiesTitle);
        txtProgressTrackingTitle = findViewById(R.id.txtProgressTrackingTitle);
        txtFeedbackTitle = findViewById(R.id.txtFeedbackTitle);

        btnActivities = findViewById(R.id.btnActivities);
        btnProgressTracking = findViewById(R.id.btnProgressTracking);
        btnFeedback = findViewById(R.id.btnFeedback);

        // Set click listeners for Activities, Progress Tracking, and Feedback buttons
        btnActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleButtonClick("Activities", selectedGrade);
            }
        });

        btnProgressTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleButtonClick("Progress Tracking", selectedGrade);
            }
        });

        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleButtonClick("Feedback", selectedGrade);
            }
        });

        // Initialize MediaPlayer
        mediaPlayer = new MediaPlayer();
    }

    private void openReadingMaterial(final String selectedGrade) {
        // Show a dialog for dialect selection
        showDialectSelectionDialog(selectedGrade);
    }

    private void showDialectSelectionDialog(final String grade) {
        // Inflate the dialog_dialect_selection.xml layout
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_dialect_selection, null);

        // Create the AlertDialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);

        // Find the RadioGroup and Button in the dialog layout
        RadioGroup radioGroupDialect = dialogView.findViewById(R.id.radioGroupDialect);
        Button btnSelectDialect = dialogView.findViewById(R.id.btnSelectDialect);

        // Create the AlertDialog
        final AlertDialog dialog = builder.create();

        // Set a click listener for the "Select" button
        btnSelectDialect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the selected dialect from the RadioGroup
                int selectedId = radioGroupDialect.getCheckedRadioButtonId();
                if (selectedId != -1) {
                    RadioButton selectedRadioButton = dialogView.findViewById(selectedId);
                    String selectedDialect = selectedRadioButton.getText().toString();

                    // Handle dialect selection
                    handleDialectSelection(grade, selectedDialect);

                    // Dismiss the dialog
                    dialog.dismiss();
                } else {
                    showToast("Please select a dialect");
                }
            }
        });

        // Show the dialog
        dialog.show();
    }

    private void handleDialectSelection(String grade, String dialect) {
        // TODO: Implement logic to display the content based on the selected grade and dialect
        String content = getContentForGradeAndDialect(grade, dialect);

        // Set the content to a TextView (replace txtStory with the actual id of your TextView)
        txtStory.setText(content);

        // Hide other elements
        txtActivitiesTitle.setVisibility(View.GONE);
        txtProgressTrackingTitle.setVisibility(View.GONE);
        txtFeedbackTitle.setVisibility(View.GONE);
        btnActivities.setVisibility(View.GONE);
        btnProgressTracking.setVisibility(View.GONE);
        btnFeedback.setVisibility(View.GONE);

        // For demonstration purposes, show a Toast.
        showToast(content);

        // Play the sound
        playSound("mysound");
    }

    private String getContentForGradeAndDialect(String grade, String dialect) {
        switch (grade) {
            case "Grade 1":
            case "Grade 2":
            case "Grade 3":
            case "Grade 4":
            case "Grade 5":
            case "Grade 6":
                return getStoryForDialect(dialect);
            default:
                return "Unknown Grade";
        }
    }

    private String getStoryForDialect(String dialect) {
        switch (dialect) {
            case "English":
                return getEnglishStory();
            case "Tagalog":
                return getTagalogStory();
            case "Bisaya":
                return getBisayaStory();
            default:
                return "Unknown Dialect";
        }
    }

    private String getEnglishStory() {
        return "Title: A Cold Bear\n\n" +
                "English:\n" +
                "Brrrr! It is getting cold.\n" +
                "Bear needs to get ready for winter.\n" +
                "First, he eats a lot.\n" +
                "Next, he finds a den.\n" +
                "Then, he fills the den with leaves, so he will stay warm.\n" +
                "Last, he eats even more!\n" +
                "Is Bear ready for winter?\n" +
                "Yes, he is. Winter is here";
    }

    private String getTagalogStory() {
        return "Tagalog:\n" +
                "Brrrr! Lumalamig na.\n" +
                "Kailangang maghanda ang oso para sa taglamig.\n" +
                "Una, marami siyang kinakain.\n" +
                "Susunod, nakahanap siya ng isang lungga.\n" +
                "Pagkatapos, pinupuno niya ang yungib ng mga dahon, para manatiling mainit siya.\n" +
                "Sa huli, kumain pa siya!\n" +
                "Handa na ba ang Bear para sa taglamig?\n" +
                "Oo , Taglamig na";
    }

    private String getBisayaStory() {
        return "Bisaya:\n" +
                "Brrr! Nagkabugnaw na.\n" +
                "Ang oso kinahanglan nga mangandam alang sa tingtugnaw.\n" +
                "Una, daghan siyag kaon.\n" +
                "Sunod, nakakita siyag langob.\n" +
                "Unya, gipuno niyag mga dahon ang langob, aron magpabilin siyang mainiton.\n" +
                "Sa katapusan, mikaon pa siya!\n" +
                "Andam na ba ang Oso alang sa tingtugnaw?";
    }

    // Method to handle button click for Activities, Progress Tracking, and Feedback
    private void handleButtonClick(String feature, String grade) {
        showToast("Viewing " + feature + " for " + grade);
        // TODO: Implement logic to handle button click based on the feature and grade
    }

    // Method to show a Toast message
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // Method to play a sound
    private void playSound(String soundName) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }

        // Get the resource ID of the sound file
        int soundResourceId = getResources().getIdentifier(soundName, "raw", getPackageName());

        // Check if the resource ID is valid
        if (soundResourceId != 0) {
            // Create a MediaPlayer with the sound file from the raw folder
            mediaPlayer = MediaPlayer.create(this, soundResourceId);

            // Start playing the sound
            mediaPlayer.start();
        } else {
            Log.e("ReadingMaterialActivity", "Invalid sound resource ID");
        }
    }

    // Don't forget to release the MediaPlayer when your activity is destroyed
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}
