package com.rajeshkc.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    byte activePlayer = 0;
    byte steps = 0;
    boolean activeGame = true;
    byte [] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    // State meanings: 0 -> X, 1 -> O, 2 -> Null
    byte [][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    @SuppressLint("SetTextI18n")
    public void  playerTap(View view){
        TextView field = (TextView) view;
        int tappedPosition = Integer.parseInt(field.getTag().toString());

        if (activeGame) {
            if (gameState[tappedPosition] == 2) {
                gameState[tappedPosition] = activePlayer;
                TextView status = findViewById(R.id.status);
                steps++;
                if (activePlayer == 0) {
                    field.setText("X");
                    activePlayer = 1;
                    status.setText("O's Turn - Tap to play");
                } else {
                    field.setText("O");
                    activePlayer = 0;
                    status.setText("X's Turn - Tap to play");
                }
                // Check if any player won
                for (byte[] winPosition : winPositions) {
                    if (gameState[winPosition[0]] == gameState[winPosition[1]]
                            && gameState[winPosition[1]] == gameState[winPosition[2]]
                            && gameState[winPosition[0]] != 2) {
                        if (gameState[winPosition[0]] == 0) {
                            status.setText("X win! - Tap to reset");
                        } else {
                            status.setText("O win! - Tap to reset");
                        }
                        activeGame = false;
                        break;
                    }
                }
                if (steps == 9 && activeGame) {
                    status.setText("It's a tie! - Tap to reset");
                    activeGame = false;
                }
            }
        } else {
            gameReset();
        }
    }

    @SuppressLint("SetTextI18n")
    private void gameReset(){
        activePlayer = 0;
        steps = 0;
        activeGame = true;
        TextView status = findViewById(R.id.status);
        status.setText("X's Turn - Tap to play");
        for (byte i = 0; i < 9; i++) {
            gameState[i] = 2;
        }
        ((TextView)findViewById(R.id.field0)).setText("");
        ((TextView)findViewById(R.id.field1)).setText("");
        ((TextView)findViewById(R.id.field2)).setText("");
        ((TextView)findViewById(R.id.field3)).setText("");
        ((TextView)findViewById(R.id.field4)).setText("");
        ((TextView)findViewById(R.id.field5)).setText("");
        ((TextView)findViewById(R.id.field6)).setText("");
        ((TextView)findViewById(R.id.field7)).setText("");
        ((TextView)findViewById(R.id.field8)).setText("");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}