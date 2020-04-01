package com.example.superspanners;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.os.Message;
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

    private TextView laneQueue1TextView;
    private TextView laneQueue2TextView;
    private TextView laneQueue3TextView;
    private TextView laneQueue4TextView;
    private TextView laneQueue5TextView;

    private Button laneSign1Btn;
    private Button laneSign2Btn;
    private Button laneSign3Btn;
    private Button laneSign4Btn;
    private Button laneSign5Btn;

    private TextView carEntryRateTextView;
    private TextView truckEntryRateTextView;

    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        laneQueue1TextView = findViewById(R.id.laneQueue1);
        laneQueue2TextView = findViewById(R.id.laneQueue2);
        laneQueue3TextView = findViewById(R.id.laneQueue3);
        laneQueue4TextView = findViewById(R.id.laneQueue4);
        laneQueue5TextView = findViewById(R.id.laneQueue5);

        laneSign1Btn = findViewById(R.id.laneSign1);
        laneSign2Btn = findViewById(R.id.laneSign2);
        laneSign3Btn = findViewById(R.id.laneSign3);
        laneSign4Btn = findViewById(R.id.laneSign4);
        laneSign5Btn = findViewById(R.id.laneSign5);

        final Button[] laneSigns = {laneSign1Btn, laneSign2Btn, laneSign3Btn, laneSign4Btn, laneSign5Btn};
        final TextView[] laneQueuesTextViews = {laneQueue1TextView, laneQueue2TextView, laneQueue3TextView,
                laneQueue4TextView, laneQueue5TextView};

        Button startSim = findViewById(R.id.startSimButton);
        Button stopSim = findViewById(R.id.stopSimButton);
        Button resetSim = findViewById(R.id.resetSimButton);

        final SeekBar carSeekBar = findViewById(R.id.carSeekBar);
        final SeekBar truckSeekBar = findViewById(R.id.truckSeekBar);

        carEntryRateTextView = findViewById(R.id.carEntryRate);
        truckEntryRateTextView = findViewById(R.id.truckEntryRate);

        laneSign1Btn.setOnClickListener(this);
        laneSign2Btn.setOnClickListener(this);
        laneSign3Btn.setOnClickListener(this);
        laneSign4Btn.setOnClickListener(this);
        laneSign5Btn.setOnClickListener(this);

        startSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimerTask();
            }
        });

        resetSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shared.Data.carEntryRate = 0;
                Shared.Data.truckEntryRate = 0;
                Shared.Data.numberOfCarEntryLanes = 0;
                Shared.Data.numberOfTruckEntryLanes = 0;

                carSeekBar.setProgress(0);
                truckSeekBar.setProgress(0);

                carEntryRateTextView.setText("0");
                truckEntryRateTextView.setText("0");

                for (int i=0; i<5; i++) {
                    Shared.Data.laneInfo[i][0] = 0;
                    Shared.Data.laneInfo[i][1] = 0;
                    laneSigns[i].setText("Closed");
                    laneQueuesTextViews[i].setText("0");
                    laneSigns[i].setTextColor(Color.parseColor("#d10000"));
                    Shared.Data.laneInfo[i][0] = 0;
                    Shared.Data.laneInfo[i][1] = 0;
                }
            }
        });

        carSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Shared.Data.carEntryRate = progress;
                carEntryRateTextView.setText(Integer.toString(progress));
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
                Shared.Data.truckEntryRate = progress;
                truckEntryRateTextView.setText(Integer.toString(progress));
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
                changeLaneStatus(0, laneSign1Btn);
                break;
            case R.id.laneSign2:
                changeLaneStatus(1, laneSign2Btn);
                break;
            case R.id.laneSign3:
                changeLaneStatus(2, laneSign3Btn);
                break;
            case R.id.laneSign4:
                changeLaneStatus(3, laneSign4Btn);
                break;
            case R.id.laneSign5:
                changeLaneStatus(4, laneSign5Btn);
                break;
        }
    }

    public void changeLaneStatus(int lane, Button button) {
        if (Shared.Data.laneInfo[lane][0] == 0) {
            Shared.Data.laneInfo[lane][0] = 1;
            button.setText("Cars Only");
            button.setTextColor(Color.parseColor("#4196db"));
        }
        else if (Shared.Data.laneInfo[lane][0] == 1) {
            Shared.Data.laneInfo[lane][0] = 2;
            button.setText("Trucks Only");
            button.setTextColor(Color.parseColor("#4196db"));
        }
        else {
            Shared.Data.laneInfo[lane][0] = 0;
            button.setText("Closed");
            button.setTextColor(Color.parseColor("#d10000"));
        }
    }

    public void startTimerTask() {
        timer = new Timer();
        timer.schedule(new TimerTasks(this), Shared.Data.THREAD_PAUSE, Shared.Data.THREAD_PAUSE);
    }

    public Handler updateUI = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            final TextView[] laneQueuesTextViews = {laneQueue1TextView, laneQueue2TextView, laneQueue3TextView,
                    laneQueue4TextView, laneQueue5TextView};
            for (int i = 0; i < 5; i++) {
                // TODO: for some reason, the Shared.Data.laneInfo[i][1] values are crazy high numbers
//                laneQueuesTextViews[1].setText(Integer.toString(Shared.Data.laneInfo[i][1]));
            }
        }

    };

    public void toastTest() {
        Toast.makeText(this, "Handler invoked", Toast.LENGTH_SHORT).show();
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
