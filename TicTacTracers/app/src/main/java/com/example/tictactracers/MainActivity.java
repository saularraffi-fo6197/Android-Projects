package com.example.tictactracers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

//    ImageButton button_00 = findViewById(R.id.button_00);
//    ImageButton button_01 = findViewById(R.id.button_01);
//    ImageButton button_02 = findViewById(R.id.button_02);
//    ImageButton button_10 = findViewById(R.id.button_10);
//    ImageButton button_11 = findViewById(R.id.button_11);
//    ImageButton button_12 = findViewById(R.id.button_12);
//    ImageButton button_20 = findViewById(R.id.button_20);
//    ImageButton button_21 = findViewById(R.id.button_21);
//    ImageButton button_22 = findViewById(R.id.button_22);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        Log.i("Log", "This is a test");
    }
}
