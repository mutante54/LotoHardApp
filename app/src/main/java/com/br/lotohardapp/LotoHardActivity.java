package com.br.lotohardapp;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class LotoHardActivity extends AppCompatActivity {

    private TextView textViewGame1;
    private TextView textViewGame1QtdEvenNumbers;
    private TextView textViewGame1QtdOddNumbers;
    private TextView textViewGame2;
    private TextView textViewGame2QtdExceptGame1;
    private TextView textViewGame3;
    private TextView textViewGame3QtdExceptGame1;
    private TextView textViewGame4;
    private TextView textViewGame4QtdExceptGame1;
    private TextView textViewGame5;
    private TextView textViewGame5QtdExceptGame1;
    private TextView textViewGame6;
    private TextView textViewGame6QtdExceptGame1;
    private TextView textViewDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loto_hard);
    }

    @Override
    protected void onResume() {

        textViewDateTime = (TextView) findViewById(R.id.textViewDateTime);

        textViewGame1 = (TextView) findViewById(R.id.textViewGame1);
        textViewGame1QtdEvenNumbers = (TextView) findViewById(R.id.textViewGame1QtdEvenNumbers);
        textViewGame1QtdOddNumbers = (TextView) findViewById(R.id.textViewGame1QtdOddNumbers);

        textViewGame2 = (TextView) findViewById(R.id.textViewGame2);
        textViewGame2QtdExceptGame1 = (TextView) findViewById(R.id.textViewGame2QtdExceptGame1);

        textViewGame3 = (TextView) findViewById(R.id.textViewGame3);
        textViewGame3QtdExceptGame1 = (TextView) findViewById(R.id.textViewGame3QtdExceptGame1);

        textViewGame4 = (TextView) findViewById(R.id.textViewGame4);
        textViewGame4QtdExceptGame1 = (TextView) findViewById(R.id.textViewGame4QtdExceptGame1);

        textViewGame5 = (TextView) findViewById(R.id.textViewGame5);
        textViewGame5QtdExceptGame1 = (TextView) findViewById(R.id.textViewGame5QtdExceptGame1);

        textViewGame6 = (TextView) findViewById(R.id.textViewGame6);
        textViewGame6QtdExceptGame1 = (TextView) findViewById(R.id.textViewGame6QtdExceptGame1);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        textViewDateTime.setText("Data de geração: " + sdf.format(new Date()));

        /*
         * Exibir as dezenas
         */

        textViewGame1.setText(buildGameStr(MainActivity.sortedNumbers));
        textViewGame1QtdEvenNumbers.setText(String.valueOf(MainActivity.countSortedEvenNumbers));
        textViewGame1QtdOddNumbers.setText(String.valueOf(MainActivity.countSortedOddNumbers));

        textViewGame2.setText(buildGameStr(MainActivity.game2Numbers));
        textViewGame2QtdExceptGame1.setText(String.valueOf(getQtdNumbersDiffWithOriginalGame(MainActivity.game2Numbers)));

        textViewGame3.setText(buildGameStr(MainActivity.game3Numbers));
        textViewGame3QtdExceptGame1.setText(String.valueOf(getQtdNumbersDiffWithOriginalGame(MainActivity.game3Numbers)));

        textViewGame4.setText(buildGameStr(MainActivity.game4Numbers));
        textViewGame4QtdExceptGame1.setText(String.valueOf(getQtdNumbersDiffWithOriginalGame(MainActivity.game4Numbers)));

        textViewGame5.setText(buildGameStr(MainActivity.game5Numbers));
        textViewGame5QtdExceptGame1.setText(String.valueOf(getQtdNumbersDiffWithOriginalGame(MainActivity.game5Numbers)));

        textViewGame6.setText(buildGameStr(MainActivity.game6Numbers));
        textViewGame6QtdExceptGame1.setText(String.valueOf(getQtdNumbersDiffWithOriginalGame(MainActivity.game6Numbers)));

        super.onResume();
    }

    /**
     * Imprime os números (como uma única string)
     *
     * @param numbers
     */
    private String buildGameStr(List<Integer> numbers) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < numbers.size(); i++) {
            if (line.length() > 0) {
                line.append("  ");
            }
            line.append(numbers.get(i));
        }

        return line.toString();
    }

    /**
     * Obtém a quantidade de dezenas do jogo informado que não constam no jogo
     * original
     *
     * @param numbers
     * @return
     */
    private int getQtdNumbersDiffWithOriginalGame(List<Integer> numbers) {
        int qtd = 0;
        for (Integer integer : numbers) {
            if (!MainActivity.sortedNumbers.contains(integer)) {
                qtd++;
            }
        }
        return qtd;
    }

}
