package com.example.hackathonretry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FinishScreen extends AppCompatActivity {

    public String repLimit;
    public String finishedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_screen);

        //this is to make sure this screen gets information
        if (getIntent().hasExtra("com.example.hackathonretry.DONE")) {
            TextView tv = (TextView) findViewById(R.id.labelFinish);
            String finishedText = getIntent().getExtras().getString("com.example.hackathonretry.DONE");
            tv.setText("Congrats! You have done a set!");
        }
        if (getIntent().hasExtra("com.example.hackathonretry.LIMIT")) {
            repLimit = getIntent().getExtras().getString("com.example.hackathonretry.LIMIT");
        }

        //to go to different screens/give info
        Button buttonMenu = (Button)findViewById(R.id.buttonMenu);
        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMenu(); //this goes to the menu screen
            }
        });

        Button buttonSet = (Button)findViewById(R.id.buttonSet);
        buttonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            toReps(); //while this goes to the reps screen
            }
        });
    }

    //public voids for the menus
    public void toMenu() {
        Intent menu = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(menu);
    }

    public void toReps(){
        Intent reps = new Intent(getApplicationContext(), RepsScreen.class);
        reps.putExtra("com.example.hackathonretry.SOMETHING", repLimit);
        reps.putExtra("com.example.hackathonretry.numOfSets", finishedText);
        startActivity(reps);
    }
}