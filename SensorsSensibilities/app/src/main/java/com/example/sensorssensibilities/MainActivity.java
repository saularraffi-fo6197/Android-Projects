package com.example.sensorssensibilities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.icu.util.Calendar;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView sensorOutText_X;
    private TextView sensorOutText_Y;
    private TextView sensorOutText_Z;
    private Button startBtn;
    private Button stopBtn;

    private Timer timer;

    private Sensor mySensor;
    private SensorManager sensorManager;

    private static final String FILE_NAME = "sensor_out.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sensorOutText_X = findViewById(R.id.xVal);
        sensorOutText_Y = findViewById(R.id.yVal);
        sensorOutText_Z = findViewById(R.id.zVal);
        startBtn = findViewById(R.id.startButton);
        stopBtn = findViewById(R.id.stopButton);

        setStartBtnListener();
        setStopBtnListener();

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        mySensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorManager.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            AlertDialog.Builder about = new AlertDialog.Builder(this);
            about.setTitle("About");
            about.setMessage("App name:     Sensors&Sensibilities \n" +
                             "Author:           Saular Raffi \n" +
                             "Version:         1.0");
            about.setCancelable(true);
            about.setNeutralButton(android.R.string.ok,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert = about.create();
            alert.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setStartBtnListener() {
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimerTask();
                toast("Capturing sensor output");
            }
        });
    }

    public void setStopBtnListener() {
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timer != null)
                    timer.cancel();
                timer = null;

                toast("Sensor output capturing has stopped");
            }
        });
    }

    public void startTimerTask() {
        timer = new Timer();
        timer.schedule(new TimerTasks(this), Shared.Data.THREAD_PAUSE*1000, Shared.Data.THREAD_PAUSE*1000);
    }

    public Handler updateUI = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            Date currentTime = Calendar.getInstance().getTime();
            saveSensorOutput("" + currentTime + "\t" +
                    "  X: " + Shared.Data.sensorX +
                    "  Y: " + Shared.Data.sensorY +
                    "  Z: " + Shared.Data.sensorZ);

            sensorOutText_X.setText("X: " + Shared.Data.sensorX);
            sensorOutText_Y.setText("Y: " + Shared.Data.sensorY);
            sensorOutText_Z.setText("Z: " + Shared.Data.sensorZ);
        }

    };

    public void saveSensorOutput(String text) {

        FileOutputStream fostream = null;
        PrintStream streamOut = null;

        try
        {
            fostream = openFileOutput(FILE_NAME, MODE_APPEND);

            streamOut = new PrintStream(fostream);

            streamOut.println(text);

            fostream.close();
            streamOut.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void toast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Shared.Data.sensorX = event.values[0];
        Shared.Data.sensorY = event.values[1];
        Shared.Data.sensorZ = event.values[2];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // not imp
    }
}
