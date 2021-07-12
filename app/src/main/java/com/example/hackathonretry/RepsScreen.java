package com.example.hackathonretry;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.commons.lang3.time.StopWatch;

public class RepsScreen extends AppCompatActivity implements SensorEventListener {

    public int numReps = 0, numSets = 0, numRepsLimit = 0, points = 0, sessionScore = 0;
    public boolean isCounting = true;
    public String text, name;

    public static int lifetimeReps;

    private boolean isMoving = false;
    public float[] times = new float[numSets];
    public float totalTime = 0;
    private int j;
    StopWatch watch = new StopWatch();

    private SensorManager sensorMan;
    private Sensor accelerometer;
    private float[] mGravity;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;
    public int count;

    public Button doneButton;

    SharedPreferences stats = this.getSharedPreferences("liftimeReps", 0);
    SharedPreferences stats2 = this.getSharedPreferences("numPoints", 0);
    SharedPreferences stats3 = this.getSharedPreferences("highScore", 0);
    SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reps_screen);

        //this is to make sure this screen gets information
        //these are info from the main screen
        if (getIntent().hasExtra("com.example.hackathonretry.SOMETHING")) {
            TextView tv = (TextView) findViewById(R.id.textView2);
            text = getIntent().getExtras().getString("com.example.hackathonretry.SOMETHING");
            tv.setText("/" + text);
        }
        if (getIntent().hasExtra("com.example.hackathonretry.workoutName")) {
            TextView tv = (TextView) findViewById(R.id.workoutName);
            name = getIntent().getExtras().getString("com.example.hackathonretry.workoutName");
            tv.setText(name);
        }

        //to put out information/go to different screens
        doneButton = (Button)findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), FinishScreen.class);
                //what to pass information to another activity
                String k = String.valueOf(j);
                startIntent.putExtra("com.example.hackathonretry.DONE", k);
                startIntent.putExtra("com.example.hackathonretry.LIMIT", text);
                startActivity(startIntent);
            }
        });

        //menu
        Button buttonMenu = (Button)findViewById(R.id.buttonQuit);
        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMenu(); //this goes to the menu screen
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        sensorMan.registerListener(this, accelerometer,
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorMan.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // required method
    }

    //to count each rep and set it to screen
    public void rep(View view) {
        numReps++;
        numRepsLimit = Integer.parseInt(text);
        TextView reps = (TextView) findViewById(R.id.labelNumReps);

        //counting the reps
        if (numReps <= numRepsLimit) {
            //to get rid of the zero
            if (numReps < 10) {
                reps.setText("0" + numReps);
            } else {
                reps.setText("" + numReps);
            }
        }
        if (numReps == numRepsLimit) {
            isCounting = false;
            numSets++;
            numReps = 0;
            j++;
            watch.stop();
            times[j] = watch.getTime();
            watch.reset();
            sessionScore += numReps;
            if (sessionScore > stats3.getInt("highScore", 0)) {
                editor.putInt("highScore", sessionScore);
                editor.apply();
                doneButton.performClick();
            }
            editor.putInt("lifetimeReps", stats.getInt("lifetimeReps", 0) + numReps);
            editor.apply();
            lifetimeReps = stats2.getInt("numPoints",0);
            editor.putInt("numPoints", stats2.getInt("numPoints", 0) + numReps);
            editor.apply();
            for (int i = 0; i < times.length; i++) {
                totalTime += times[i];
            }
        }
    }


    public void toMenu() {
        Intent menu = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(menu);
    }

    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER & isCounting){
            watch.start();
            mGravity = event.values.clone();
            float x = mGravity[0];
            float y = mGravity[1];
            float z = mGravity[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float)Math.sqrt(x*x + y*y + z*z);
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta;
            // Make this higher or lower according to how much
            // motion you want to detect
            if(mAccel > 3){
                isMoving = true;
            }
            else
            if(mAccel==0 & isMoving){
                isMoving = false;
                numReps++;
            }
            if(count==numReps){
            }
            count = 0;
        }

    }

}



