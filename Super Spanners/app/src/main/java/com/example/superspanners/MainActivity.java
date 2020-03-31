package com.example.superspanners;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView laneQueue1;
    private TextView laneQueue2;
    private TextView laneQueue3;
    private TextView laneQueue4;
    private TextView laneQueue5;

    private Button laneSign1;
    private Button laneSign2;
    private Button laneSign3;
    private Button laneSign4;
    private Button laneSign5;

    private TextView incomingCars;
    private TextView incomingTrucks;

    private int[] laneStatus = {0,0,0,0,0};
    private int[] laneQueues = {0,0,0,0,0};

    private Timer timer;
    private TimerTask timerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        laneQueue1 = findViewById(R.id.laneQueue1);
        laneQueue2 = findViewById(R.id.laneQueue2);
        laneQueue3 = findViewById(R.id.laneQueue3);
        laneQueue4 = findViewById(R.id.laneQueue4);
        laneQueue5 = findViewById(R.id.laneQueue5);

        laneSign1 = findViewById(R.id.laneSign1);
        laneSign2 = findViewById(R.id.laneSign2);
        laneSign3 = findViewById(R.id.laneSign3);
        laneSign4 = findViewById(R.id.laneSign4);
        laneSign5 = findViewById(R.id.laneSign5);

        final Button[] laneSigns = {laneSign1, laneSign2, laneSign3, laneSign4, laneSign5};

        Button startSim = findViewById(R.id.startSimButton);
        Button stopSim = findViewById(R.id.stopSimButton);
        Button resetSim = findViewById(R.id.resetSimButton);

        final SeekBar carSeekBar = findViewById(R.id.carSeekBar);
        final SeekBar truckSeekBar = findViewById(R.id.truckSeekBar);

        incomingCars = findViewById(R.id.incomingCars);
        incomingTrucks = findViewById(R.id.incomingTrucks);

        laneSign1.setOnClickListener(this);
        laneSign2.setOnClickListener(this);
        laneSign3.setOnClickListener(this);
        laneSign4.setOnClickListener(this);
        laneSign5.setOnClickListener(this);

        timerTask = new TimerTask() {
            @Override
            public void run() {
                laneQueue1.setText("5");
            }
        };

        startSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // crashes app
                startTimerTask();
            }
        });

        resetSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carSeekBar.setProgress(0);
                truckSeekBar.setProgress(0);

                incomingCars.setText("0");
                incomingTrucks.setText("0");

                laneQueue1.setText("0");
                laneQueue2.setText("0");
                laneQueue3.setText("0");
                laneQueue4.setText("0");
                laneQueue5.setText("0");

                for (int i=0; i<5; i++) {
                    laneQueues[i] = 0;
                    laneStatus[i] = 0;
                    laneSigns[i].setText("Closed");
                    laneSigns[i].setTextColor(Color.parseColor("#d10000"));
                }
            }
        });

        carSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                incomingCars.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        truckSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                incomingTrucks.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.laneSign1:
                changeLaneStatus(0, laneSign1);
                break;
            case R.id.laneSign2:
                changeLaneStatus(1, laneSign2);
                break;
            case R.id.laneSign3:
                changeLaneStatus(2, laneSign3);
                break;
            case R.id.laneSign4:
                changeLaneStatus(3, laneSign4);
                break;
            case R.id.laneSign5:
                changeLaneStatus(4, laneSign5);
                break;
        }
    }

    public void changeLaneStatus(int lane, Button button) {
        if (laneStatus[lane] == 0) {
            laneStatus[lane] = 1;
            button.setText("Cars Only");
            button.setTextColor(Color.parseColor("#4196db"));
        }
        else if (laneStatus[lane] == 1) {
            laneStatus[lane] = 2;
            button.setText("Trucks Only");
            button.setTextColor(Color.parseColor("#4196db"));
        }
        else {
            laneStatus[lane] = 0;
            button.setText("Closed");
            button.setTextColor(Color.parseColor("#d10000"));
        }
    }

    public void startTimerTask() {
        timer = new Timer();
        timer.schedule(timerTask, 1000, 1000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            AlertDialog.Builder about = new AlertDialog.Builder(this);
            about.setTitle("About");
            about.setMessage("App name: Super Spanners \nAuthor: Saular Raffi \nVersion: 1.0");
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
}
