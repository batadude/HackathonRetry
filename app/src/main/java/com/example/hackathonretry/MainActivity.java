package com.example.hackathonretry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //variables
    public int numReps = 0, numSets = 0, numRepsDone = 0, points = 0;
    public boolean isCounting = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Attempts to launch an activity within the app
        Button situpsButton = (Button)findViewById(R.id.situpsButton);
        situpsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSitups(); //situps
            }
        });

        //Attempts to launch an activity within the app
        Button JJButton = (Button)findViewById(R.id.jumpingJacks);
        JJButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openJJ(); //jumping jacks
            }
        });

        //Attempts to launch an activity within the app
        Button statsButton = (Button)findViewById(R.id.buttonStats);
        statsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStats(); //stATS
            }
        });

        Button pointsButton = (Button)findViewById(R.id.buttonPoints);
        pointsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPoints(); //points
            }
        });
    }


    public void openSitups(){
        Intent startIntent = new Intent(getApplicationContext(), RepsScreen.class);
        //show how to pass information to another activity
        EditText inputReps = (EditText)findViewById(R.id.numOfReps);
        String reps = inputReps.getText().toString();

        startIntent.putExtra("com.example.hackathonretry.workoutName", "SitUp Reps");
        startIntent.putExtra("com.example.hackathonretry.SOMETHING", reps);
        startActivity(startIntent);
    }

    public void openJJ(){
        Intent startIntent = new Intent(getApplicationContext(), RepsScreen.class);
        //show how to pass information to another activity
        EditText inputReps = (EditText)findViewById(R.id.numOfReps);
        String reps = inputReps.getText().toString();

        startIntent.putExtra("com.example.hackathonretry.workoutName", "Jumping Jack Reps");
        startIntent.putExtra("com.example.hackathonretry.SOMETHING", reps);
        startActivity(startIntent);
    }

    public void openStats(){
        Intent startIntent = new Intent(getApplicationContext(), STATS.class);
        startActivity(startIntent);
    }

    public void openPoints(){
        Intent startIntent = new Intent(getApplicationContext(), PointsScreen.class);
        startActivity(startIntent);
    }

    public void  openCalorie(){
        Intent startIntent = new Intent(getApplicationContext(), PointsScreen.class);
        startActivity(startIntent);
    }


}