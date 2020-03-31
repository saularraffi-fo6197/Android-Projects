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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView laneQueue1;
    TextView laneQueue2;
    TextView laneQueue3;
    TextView laneQueue4;
    TextView laneQueue5;

    Button laneSign1;
    Button laneSign2;
    Button laneSign3;
    Button laneSign4;
    Button laneSign5;

    Button startSim;
    Button stopSim;
    Button resetSim;

    SeekBar carSeekBar;
    SeekBar truckSeekBar;

    TextView incomingCars;
    TextView incomingTrucks;

    int[] laneStatus = {0,0,0,0,0};

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

        startSim = findViewById(R.id.startSimButton);
        stopSim = findViewById(R.id.stopSimButton);
        resetSim = findViewById(R.id.resetSimButton);

        carSeekBar = findViewById(R.id.carSeekBar);
        truckSeekBar = findViewById(R.id.truckSeekBar);

        incomingCars = findViewById(R.id.incomingCars);
        incomingTrucks = findViewById(R.id.incomingTrucks);

        laneSign1.setOnClickListener(this);
        laneSign2.setOnClickListener(this);
        laneSign3.setOnClickListener(this);
        laneSign4.setOnClickListener(this);
        laneSign5.setOnClickListener(this);

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
