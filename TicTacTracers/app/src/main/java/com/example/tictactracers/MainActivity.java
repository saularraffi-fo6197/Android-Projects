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

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView gamesPlayedTextView;
    TextView humanWinsTextView;
    TextView humanWinsPercentTextView;
    TextView computerWinsTextView;
    TextView computerWinsPercentTextView;

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

    boolean gameIsActive = true;
    int gamesPlayed = 0;
    int humanWins = 0;
    int computerWins = 0;

    final int EMPTY = 0;
    final int HUMAN = 1;
    final int COMPUTER = 2;

    int[] board = {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY};

    int[][] wins = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};


@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        resetGame = findViewById(R.id.resetGame);
        resetStats = findViewById(R.id.resetStats);

        gamesPlayedTextView = findViewById(R.id.gamesPlayed);
        humanWinsTextView = findViewById(R.id.humanWins);
        humanWinsPercentTextView = findViewById(R.id.humanWinsPercent);
        computerWinsTextView = findViewById(R.id.computerWins);
        computerWinsPercentTextView = findViewById(R.id.computerWinsPercent);

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

                for (int i = 0; i < 9; i++) {
                    board[i] = EMPTY;
                }
                gameIsActive = true;
            }
        });

        resetStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamesPlayed = 0;
                humanWins = 0;
                computerWins = 0;

                gamesPlayedTextView.setText("0");
                humanWinsTextView.setText("0");
                computerWinsTextView.setText("0");
                humanWinsPercentTextView.setText("0%");
                computerWinsPercentTextView.setText("0%");
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

        if (gameIsActive) {

            ImageButton button = button_00;
            int position = 0;

            switch (v.getId()) {
                case R.id.button_00:
                    button = button_00;
                    position = 0;
                    break;
                case R.id.button_01:
                    button = button_01;
                    position = 1;
                    break;
                case R.id.button_02:
                    button = button_02;
                    position = 2;
                    break;
                case R.id.button_10:
                    button = button_10;
                    position = 3;
                    break;
                case R.id.button_11:
                    button = button_11;
                    position = 4;
                    break;
                case R.id.button_12:
                    button = button_12;
                    position = 5;
                    break;
                case R.id.button_20:
                    button = button_20;
                    position = 6;
                    break;
                case R.id.button_21:
                    button = button_21;
                    position = 7;
                    break;
                case R.id.button_22:
                    button = button_22;
                    position = 8;
                    break;
            }

            if (isValidPosition(position)) {

                board[position] = HUMAN;

                button.setImageResource(R.drawable.android_logo);

                if (checkWin(HUMAN)) {
                    Toast.makeText(this, "You win!", Toast.LENGTH_LONG).show();
                    humanWins = humanWins + 1;
                    humanWinsTextView.setText(Integer.toString(humanWins));
                    gamesPlayed = gamesPlayed + 1;
                    gamesPlayedTextView.setText(Integer.toString(gamesPlayed));
                    updateScore(HUMAN);
                }

                if (gameIsActive) {
                    computerTurn();
                }

                if (checkWin(COMPUTER)) {
                    Toast.makeText(this, "Computer wins", Toast.LENGTH_LONG).show();
                    computerWins = computerWins + 1;
                    computerWinsTextView.setText(Integer.toString(computerWins));
                    gamesPlayed = gamesPlayed + 1;
                    gamesPlayedTextView.setText(Integer.toString(gamesPlayed));
                    updateScore(COMPUTER);
                }

            } else
                Toast.makeText(this, "Position already taken", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateScore(int winner) {
        int humanWinsPercent = 0;
        int computerWinsPercent = 0;

        if (gamesPlayed == 0) {
            if (winner == HUMAN)
                humanWinsPercentTextView.setText("100%");
            else
                computerWinsPercentTextView.setText("100%");
        }
        else {
            humanWinsPercent = (int)(humanWins/(double)gamesPlayed*100);
            computerWinsPercent = (int)(computerWins/(double) gamesPlayed*100);
            humanWinsPercentTextView.setText(humanWinsPercent + "%");
            computerWinsPercentTextView.setText(computerWinsPercent + "%");
        }
    }

    public void computerTurn() {
        int position;
        ImageButton[] buttons = {button_00, button_01, button_02, button_10, button_11, button_12, button_20, button_21, button_22};

        do {
            Random r = new Random();
            position = r.nextInt(9);
        } while(!isValidPosition(position));

        ImageButton button = buttons[position];
        button.setImageResource(R.drawable.apple_logo);
        board[position] = COMPUTER;
    }

    boolean isValidPosition(int position) {
        return board[position] == EMPTY;
    }

    public Boolean checkWin(int player) {
        for (int i = 0; i < 8; i++) {
            if (board[wins[i][0]] == player &&
                    board[wins[i][1]] == player &&
                    board[wins[i][2]] == player) {
                gameIsActive = false;
                return true;
            }
        }
        return false;
    }
}
