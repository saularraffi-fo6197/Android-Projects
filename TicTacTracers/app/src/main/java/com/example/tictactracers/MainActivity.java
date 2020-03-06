package com.example.tictactracers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton button_00;
    ImageButton button_01;
    ImageButton button_02;
    ImageButton button_10;
    ImageButton button_11;
    ImageButton button_12;
    ImageButton button_20;
    ImageButton button_21;
    ImageButton button_22;

    Button resetGame;
    Button resetStats;

    Integer turn = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        resetGame = findViewById(R.id.resetGame);
        resetStats = findViewById(R.id.resetStats);

        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_00.setImageResource(0);
                button_01.setImageResource(0);
                button_02.setImageResource(0);
                button_10.setImageResource(0);
                button_11.setImageResource(0);
                button_12.setImageResource(0);
                button_20.setImageResource(0);
                button_21.setImageResource(0);
                button_22.setImageResource(0);
            }
        });

        button_00 = findViewById(R.id.button_00);
        button_01 = findViewById(R.id.button_01);
        button_02 = findViewById(R.id.button_02);
        button_10 = findViewById(R.id.button_10);
        button_11 = findViewById(R.id.button_11);
        button_12 = findViewById(R.id.button_12);
        button_20 = findViewById(R.id.button_20);
        button_21 = findViewById(R.id.button_21);
        button_22 = findViewById(R.id.button_22);

        button_00.setOnClickListener(this);
        button_01.setOnClickListener(this);
        button_02.setOnClickListener(this);
        button_10.setOnClickListener(this);
        button_11.setOnClickListener(this);
        button_12.setOnClickListener(this);
        button_20.setOnClickListener(this);
        button_21.setOnClickListener(this);
        button_22.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_00:
                takeTurn(button_00);
                break;
            case R.id.button_01:
                takeTurn(button_01);
                break;
            case R.id.button_02:
                takeTurn(button_02);
                break;
            case R.id.button_10:
                takeTurn(button_10);
                break;
            case R.id.button_11:
                takeTurn(button_11);
                break;
            case R.id.button_12:
                takeTurn(button_12);
                break;
            case R.id.button_20:
                takeTurn(button_20);
                break;
            case R.id.button_21:
                takeTurn(button_21);
                break;
            case R.id.button_22:
                takeTurn(button_22);
                break;
        }
        if (turn == 0) { turn = 1; }
        else { turn = 0; }
    }

    public void takeTurn(ImageButton button) {
        if (turn == 0) {
            button.setImageResource(R.drawable.android_logo);
        }
        else {
            button.setImageResource(R.drawable.apple_logo);
        }
    }

}
