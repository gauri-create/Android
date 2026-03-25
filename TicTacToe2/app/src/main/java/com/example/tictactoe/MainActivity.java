package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button[] buttons = new Button[9];
    Button restart;
    TextView result;

    String[] board = {"", "", "", "", "", "", "", "", ""};

    String turn = "X";
    boolean gameActive = true;
    String mode = "singlePlayer"; // change to multiPlayer if needed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        for (int i = 0; i < buttons.length; i++) {
            int index = i;
            buttons[i].setOnClickListener(v -> playMove(index));
        }

        restart.setOnClickListener(v -> resetGame());
    }

    private void init() {
        buttons[0] = findViewById(R.id.btn1);
        buttons[1] = findViewById(R.id.btn2);
        buttons[2] = findViewById(R.id.btn3);
        buttons[3] = findViewById(R.id.btn4);
        buttons[4] = findViewById(R.id.btn5);
        buttons[5] = findViewById(R.id.btn6);
        buttons[6] = findViewById(R.id.btn7);
        buttons[7] = findViewById(R.id.btn8);
        buttons[8] = findViewById(R.id.btn9);

        restart = findViewById(R.id.restart);
        result = findViewById(R.id.result);
    }

    private void playMove(int index) {
        if (!gameActive || !board[index].equals("")) return;

        board[index] = turn;
        buttons[index].setText(turn);

        if (checkWin(turn)) {
            showResult(turn + " Wins!");
            return;
        }

        if (isDraw()) {
            showResult("Game Draw");
            return;
        }

        if (mode.equals("singlePlayer") && turn.equals("X")) {
            turn = "O";
            playComputer();
            updateBoard();

            if (checkWin("O")) {
                showResult("O Wins!");
                return;
            }

            if (isDraw()) {
                showResult("Game Draw");
                return;
            }

            turn = "X";
        } else {
            turn = turn.equals("X") ? "O" : "X";
        }
    }

    private void playComputer() {
        int bestScore = -100;
        int bestMove = -1;

        for (int i = 0; i < 9; i++) {
            if (board[i].equals("")) {
                board[i] = "O";
                int score = minimax(false);
                board[i] = "";

                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i;
                }
            }
        }

        if (bestMove != -1) {
            board[bestMove] = "O";
        }
    }

    private int minimax(boolean isMax) {
        if (checkWin("O")) return 1;
        if (checkWin("X")) return -1;
        if (isDraw()) return 0;

        if (isMax) {
            int best = -100;
            for (int i = 0; i < 9; i++) {
                if (board[i].equals("")) {
                    board[i] = "O";
                    int score = minimax(false);
                    board[i] = "";
                    best = Math.max(best, score);
                }
            }
            return best;
        } else {
            int best = 100;
            for (int i = 0; i < 9; i++) {
                if (board[i].equals("")) {
                    board[i] = "X";
                    int score = minimax(true);
                    board[i] = "";
                    best = Math.min(best, score);
                }
            }
            return best;
        }
    }

    private boolean checkWin(String player) {
        int[][] win = {
                {0,1,2},{3,4,5},{6,7,8},
                {0,3,6},{1,4,7},{2,5,8},
                {0,4,8},{2,4,6}
        };

        for (int[] pos : win) {
            if (board[pos[0]].equals(player) &&
                    board[pos[1]].equals(player) &&
                    board[pos[2]].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDraw() {
        for (String s : board) {
            if (s.equals("")) return false;
        }
        return true;
    }

    private void updateBoard() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setText(board[i]);
        }
    }

    private void showResult(String text) {
        result.setText(text);
        result.setVisibility(View.VISIBLE);
        gameActive = false;
    }

    private void resetGame() {
        for (int i = 0; i < 9; i++) {
            board[i] = "";
            buttons[i].setText("");
        }
        turn = "X";
        gameActive = true;
        result.setVisibility(View.GONE);
    }
}