package com.example.gamexoapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Boolean nextMove = false;
    Boolean gameOver = false;
    Button[][] btns = new Button[3][3];
    Button btnRestart;
    int cntMoves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        btns[0][0] = (Button)findViewById(R.id.button00);
        btns[0][1] = (Button)findViewById(R.id.button01);
        btns[0][2] = (Button)findViewById(R.id.button02);
        btns[1][0] = (Button)findViewById(R.id.button10);
        btns[1][1] = (Button)findViewById(R.id.button11);
        btns[1][2] = (Button)findViewById(R.id.button12);
        btns[2][0] = (Button)findViewById(R.id.button20);
        btns[2][1] = (Button)findViewById(R.id.button21);
        btns[2][2] = (Button)findViewById(R.id.button22);
        btnRestart = (Button)findViewById(R.id.buttonRestart);

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                btns[i][j].setText("");
            }
        }
        btnRestart.setVisibility(View.INVISIBLE);
        textView.setText("Играем. Следующий ход X");
    }

    @SuppressLint("NonConstantResourceId")
    public void onButtonClick(View view) {
        //Toast.makeText(getApplicationContext(), "Мы нажали кнопку 1", Toast.LENGTH_LONG).show();
        //textView.setText("qwerty");
        if (!gameOver) {
        switch (view.getId()) {
            //case R.id.button00: btn00.setText(mark); break;
            case R.id.button00: doMove(0,0); break;
            case R.id.button01: doMove(0,1); break;
            case R.id.button02: doMove(0,2); break;
            case R.id.button10: doMove(1,0); break;
            case R.id.button11: doMove(1,1); break;
            case R.id.button12: doMove(1,2); break;
            case R.id.button20: doMove(2,0); break;
            case R.id.button21: doMove(2,1); break;
            case R.id.button22: doMove(2,2); break;
            }
        }
        switch (view.getId()) {
            case R.id.buttonRestart: doRestart(); break;
        }
    }

    protected void doMove(int x, int y){
        String mark = "X";

        if (btns[x][y].getText() == ""){
            if (!gameOver && nextMove == false){
                mark = "X";
                textView.setText("Следующий ход O");
            } else {
                mark = "O";
                textView.setText("Следующий ход X");
            }
            nextMove = !nextMove;
            btns[x][y].setText(mark);
            cntMoves++;
        }
        //Проверка на победу: текущая строка
        for (int i = 0; i < 3; i++) {
            if (btns[x][i].getText() == mark) {gameOver = true;} else {gameOver = false; break;};
        }
        //Проверка на победу: текущий столбец
        if (!gameOver) {
            for (int i = 0; i < 3; i++) {
                if (btns[i][y].getText() == mark) {gameOver = true;} else {gameOver = false; break;};
            }
        }
        //Проверка на победу: главная диагональ
        if (!gameOver) {
            for (int i = 0; i < 3; i++) {
                if (btns[i][i].getText() == mark) {gameOver = true;} else {gameOver = false; break;};
            }
        }
        //Проверка на победу: побочная диагональ
        if (!gameOver) {
            for (int i = 0; i < 3; i++) {
                if (btns[2-i][i].getText() == mark) {gameOver = true;} else {gameOver = false; break;};
            }
        }
        if (gameOver==true) {
            textView.setText("Игра завершилась победой " + mark);
            btnRestart.setVisibility(View.VISIBLE);

        }
        if (!gameOver && cntMoves >= 9){
            gameOver = true;
            textView.setText("Игра завершилась ничьёй ");
            btnRestart.setVisibility(View.VISIBLE);
        }
    }
    protected void doRestart(){
        gameOver = false;
        nextMove = false;
        cntMoves = 0;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                btns[i][j].setText("");
            }
        }
        btnRestart.setVisibility(View.INVISIBLE);
        textView.setText("Играем. Следующий ход X");
    }
}