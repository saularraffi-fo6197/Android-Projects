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

public class MainActivity extends AppCompatActivity {

    private TextView sensorOut;
    private Button startBtn;
    private Button stopBtn;

    private Timer timer;

    private static final String FILE_NAME = "sensor_out.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sensorOut = findViewById(R.id.sensorOutput);
        startBtn = findViewById(R.id.startButton);
        stopBtn = findViewById(R.id.stopButton);

        sensorOut.setText("0");

        setStartBtnListener();
        setStopBtnListener();
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
            saveSensorOutput("" + currentTime + "\t" + Shared.Data.sensorOutput);
            sensorOut.setText(Double.toString(Shared.Data.sensorOutput));
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
}
