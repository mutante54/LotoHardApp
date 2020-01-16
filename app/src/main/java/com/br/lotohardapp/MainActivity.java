package com.br.lotohardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText editTextSortedNumbers;
    private EditText editTextMinPercOddSortedNumbers;
    private EditText editTextMinPercEvenSortedNumbers;

    private EditText editTextFixedNumber1;
    private EditText editTextFixedNumber2;
    private EditText editTextFixedNumber3;
    private EditText editTextFixedNumber4;
    private EditText editTextFixedNumber5;

    private EditText editTextLine1Priority;
    private EditText editTextLine1MinNumbers;
    private EditText editTextLine1MaxNumbers;
    private EditText editTextLine2Priority;
    private EditText editTextLine2MinNumbers;
    private EditText editTextLine2MaxNumbers;
    private EditText editTextLine3Priority;
    private EditText editTextLine3MinNumbers;
    private EditText editTextLine3MaxNumbers;
    private EditText editTextLine4Priority;
    private EditText editTextLine4MinNumbers;
    private EditText editTextLine4MaxNumbers;
    private EditText editTextLine5Priority;
    private EditText editTextLine5MinNumbers;
    private EditText editTextLine5MaxNumbers;

    // total de numeros a serem sorteados
    public static Integer qtdSortedNumbers;

    // percentual minimo de números impares a serem sorteados
    public static Integer minPercOddSortedNumbers;

    // percentual minimo de números pares a serem sorteados
    public static Integer minPercEvenSortedNumbers;

    // numeros sorteados (jogo original)
    public static List<Integer> sortedNumbers;

    // Bloco Aleatório (com "x" dezenas do jogo original)
    public static List<Integer> sortedNumbersBlockRandom = new ArrayList<>();

    private static final int minDez = 1;
    private static final int maxDez = 25;

    // quantidade de numeros impares ja sorteados
    public static Double countSortedOddNumbers = 0D;

    // quantidade de numeros pares ja sorteados
    public static Double countSortedEvenNumbers = 0D;

    public static Integer fixedNumber1;
    public static Integer fixedNumber2;
    public static Integer fixedNumber3;
    public static Integer fixedNumber4;
    public static Integer fixedNumber5;

    public static Integer line1Priority;
    public static Integer line1MinNumbers;
    public static Integer line1MaxNumbers;
    public static Integer line2Priority;
    public static Integer line2MinNumbers;
    public static Integer line2MaxNumbers;
    public static Integer line3Priority;
    public static Integer line3MinNumbers;
    public static Integer line3MaxNumbers;
    public static Integer line4Priority;
    public static Integer line4MinNumbers;
    public static Integer line4MaxNumbers;
    public static Integer line5Priority;
    public static Integer line5MinNumbers;
    public static Integer line5MaxNumbers;

    public static List<Integer> game2Numbers;
    public static List<Integer> game3Numbers;
    public static List<Integer> game4Numbers;
    public static List<Integer> game5Numbers;
    public static List<Integer> game6Numbers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buildGames(View view) {

        editTextSortedNumbers = (EditText) findViewById(R.id.qtdSortedNumbers);
        editTextMinPercOddSortedNumbers = (EditText) findViewById(R.id.minPercOddSortedNumbers);
        editTextMinPercEvenSortedNumbers = (EditText) findViewById(R.id.minPercEvenSortedNumbers);
        editTextFixedNumber1 = (EditText) findViewById(R.id.fixedNumber1);
        editTextFixedNumber2 = (EditText) findViewById(R.id.fixedNumber2);
        editTextFixedNumber3 = (EditText) findViewById(R.id.fixedNumber3);
        editTextFixedNumber4 = (EditText) findViewById(R.id.fixedNumber4);
        editTextFixedNumber5 = (EditText) findViewById(R.id.fixedNumber5);
        editTextLine1Priority = (EditText) findViewById(R.id.line1Priority);
        editTextLine1MinNumbers = (EditText) findViewById(R.id.line1MinNumbers);
        editTextLine1MaxNumbers = (EditText) findViewById(R.id.line1MaxNumbers);
        editTextLine2Priority = (EditText) findViewById(R.id.line2Priority);
        editTextLine2MinNumbers = (EditText) findViewById(R.id.line2MinNumbers);
        editTextLine2MaxNumbers = (EditText) findViewById(R.id.line2MaxNumbers);
        editTextLine3Priority = (EditText) findViewById(R.id.line3Priority);
        editTextLine3MinNumbers = (EditText) findViewById(R.id.line3MinNumbers);
        editTextLine3MaxNumbers = (EditText) findViewById(R.id.line3MaxNumbers);
        editTextLine4Priority = (EditText) findViewById(R.id.line4Priority);
        editTextLine4MinNumbers = (EditText) findViewById(R.id.line4MinNumbers);
        editTextLine4MaxNumbers = (EditText) findViewById(R.id.line4MaxNumbers);
        editTextLine5Priority = (EditText) findViewById(R.id.line5Priority);
        editTextLine5MinNumbers = (EditText) findViewById(R.id.line5MinNumbers);
        editTextLine5MaxNumbers = (EditText) findViewById(R.id.line5MaxNumbers);

        /*
            Setando valores para o processamento na próxima Activity (má prática, mas aqui o objetivo é otimizar trabalho :D)
         */
        qtdSortedNumbers = Integer.parseInt(editTextSortedNumbers.getText().toString());
        minPercEvenSortedNumbers = Integer.parseInt(editTextMinPercEvenSortedNumbers.getText().toString());
        minPercOddSortedNumbers = Integer.parseInt(editTextMinPercOddSortedNumbers.getText().toString());

        fixedNumber1 = !TextUtils.isEmpty(editTextFixedNumber1.getText()) ? Integer.parseInt(editTextFixedNumber1.getText().toString()) : null;
        fixedNumber2 = !TextUtils.isEmpty(editTextFixedNumber2.getText()) ? Integer.parseInt(editTextFixedNumber2.getText().toString()) : null;
        fixedNumber3 = !TextUtils.isEmpty(editTextFixedNumber3.getText()) ? Integer.parseInt(editTextFixedNumber3.getText().toString()) : null;
        fixedNumber4 = !TextUtils.isEmpty(editTextFixedNumber4.getText()) ? Integer.parseInt(editTextFixedNumber4.getText().toString()) : null;
        fixedNumber5 = !TextUtils.isEmpty(editTextFixedNumber5.getText()) ? Integer.parseInt(editTextFixedNumber5.getText().toString()) : null;

        line1Priority = Integer.parseInt(editTextLine1Priority.getText().toString());
        line1MinNumbers = Integer.parseInt(editTextLine1MinNumbers.getText().toString());
        line1MaxNumbers = Integer.parseInt(editTextLine1MaxNumbers.getText().toString());
        line2Priority = Integer.parseInt(editTextLine2Priority.getText().toString());
        line2MinNumbers = Integer.parseInt(editTextLine2MinNumbers.getText().toString());
        line2MaxNumbers = Integer.parseInt(editTextLine2MaxNumbers.getText().toString());
        line3Priority = Integer.parseInt(editTextLine3Priority.getText().toString());
        line3MinNumbers = Integer.parseInt(editTextLine3MinNumbers.getText().toString());
        line3MaxNumbers = Integer.parseInt(editTextLine3MaxNumbers.getText().toString());
        line4Priority = Integer.parseInt(editTextLine4Priority.getText().toString());
        line4MinNumbers = Integer.parseInt(editTextLine4MinNumbers.getText().toString());
        line4MaxNumbers = Integer.parseInt(editTextLine4MaxNumbers.getText().toString());
        line5Priority = Integer.parseInt(editTextLine5Priority.getText().toString());
        line5MinNumbers = Integer.parseInt(editTextLine5MinNumbers.getText().toString());
        line5MaxNumbers = Integer.parseInt(editTextLine5MaxNumbers.getText().toString());

        Integer totalMinNumbers = line1MinNumbers + line2MinNumbers + line3MinNumbers + line4MinNumbers + line5MinNumbers;

        if (totalMinNumbers > qtdSortedNumbers) {
            Context context = getApplicationContext();
            CharSequence text = "A soma da quantidade mínima de dezenas por linha é superior a quantidade de dezenas do jogo!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            return;
        }

        Integer totalPercTypeNumbers = minPercEvenSortedNumbers + minPercOddSortedNumbers;

        if (totalPercTypeNumbers != 100) {
            Context context = getApplicationContext();
            CharSequence text = "A soma dos percentuais de pares e ímpares deve ser igual a 100";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            return;
        }

        /*
            Executa o processamento
         */
        process();

        Intent intent = new Intent(MainActivity.this, LotoHardActivity.class);
        startActivity(intent);
    }

    /**
     * Executa o processamento dos jogos da lotofácil
     */
    private static void process() {

        sortedNumbers = new ArrayList<>();
        countSortedOddNumbers = 0D;
        countSortedEvenNumbers = 0D;

        List<Line> lines = new ArrayList<>();
        lines.add(new Line(1, line1Priority, line1MinNumbers, line1MaxNumbers, Arrays.asList(1, 2, 3, 4, 5)));
        lines.add(new Line(2, line2Priority, line2MinNumbers, line2MaxNumbers, Arrays.asList(6, 7, 8, 9, 10)));
        lines.add(new Line(3, line3Priority, line3MinNumbers, line3MaxNumbers, Arrays.asList(11, 12, 13, 14, 15)));
        lines.add(new Line(4, line4Priority, line4MinNumbers, line4MaxNumbers, Arrays.asList(16, 17, 18, 19, 20)));
        lines.add(new Line(5, line5Priority, line5MinNumbers, line5MaxNumbers, Arrays.asList(21, 22, 23, 24, 25)));

        /**
         * Parte 0 - Processando as dezenas fixas (se houver).
         * Caso existam, será necessário atualizar os contadores das linhas e também de pares e ímpares
         */
        if (fixedNumber1 != null) sortedNumbers.add(fixedNumber1);
        if (fixedNumber2 != null) sortedNumbers.add(fixedNumber2);
        if (fixedNumber3 != null) sortedNumbers.add(fixedNumber3);
        if (fixedNumber4 != null) sortedNumbers.add(fixedNumber4);
        if (fixedNumber5 != null) sortedNumbers.add(fixedNumber5);

        if (sortedNumbers.size() > 0) {
            sortedNumbers.forEach(sortedNumber -> {

                for (Line line : lines) {
                    if (line.getNumbers().contains(sortedNumber)) {
                        line.setCountSortedNumbers(line.getCountSortedNumbers() + 1);
                        break;
                    }
                }

                if (sortedNumber % 2 != 0) {
                    countSortedOddNumbers++;
                } else {
                    countSortedEvenNumbers++;
                }
            });
        }

        /*
         * Parte 1 - Preenchendo o minimo de dezenas por linha (sorteando dezenas
         * aleatoriamente)
         */
        lines.forEach(line -> {
            while (line.getCountSortedNumbers() < line.getMinSortedNumbers() && sortedNumbers.size() < qtdSortedNumbers) {
                int sortedNumber = line.getNumbers().get(new Random().nextInt(line.getNumbers().size()));
                if (!sortedNumbers.contains(sortedNumber)) {
                    line.setCountSortedNumbers(line.getCountSortedNumbers() + 1);
                    sortedNumbers.add(sortedNumber);
                    if (sortedNumber % 2 != 0) {
                        countSortedOddNumbers++;
                    } else {
                        countSortedEvenNumbers++;
                    }
                }
            }
        });

        /*
         * Parte 2 - Preenchendo as demais dezenas (com dezenas aleatórias), atentendo
         * aos requisitos minimos
         */

        lines.sort(Comparator.comparing(Line::getPriority));

        lines.forEach(line -> {
            int maxAttempts = 50;
            int countAttempts = 0;
            // temos a necessidade de preencher a linha em no máximo "x" tentativas..
            // pode ocorrer de não ser possível preencher a linha porque não existem mais
            // dezenas disponíveis para atender à regra de pares/ ímpares
            while (line.getCountSortedNumbers() < line.getMaxSortedNumbers() && sortedNumbers.size() < qtdSortedNumbers && countAttempts < maxAttempts) {

                // sorteando próxima dezena
                int sortedNumber = line.getNumbers().get(new Random().nextInt(line.getNumbers().size()));

                boolean isOddNumber = false;

                if (sortedNumber % 2 != 0) {
                    isOddNumber = true;
                }

                // percentual de dezenas ímpares
                double percOddNumbers = Double.valueOf(countSortedOddNumbers / qtdSortedNumbers) * 100;

                // percentual de dezenas pares
                double percEvenNumbers = Double.valueOf(countSortedEvenNumbers / qtdSortedNumbers) * 100;

                // validacao quanto a quantidade minima de numeros pares/ impares
                if (isOddNumber == true && (percOddNumbers >= minPercOddSortedNumbers && percEvenNumbers < minPercEvenSortedNumbers)) {
                    // dezena é ÍMPAR e o percentual de ímpares já foi satisfeito (e o percentual de
                    // pares não foi satisfeito)
                    continue;
                } else if (isOddNumber == false && (percEvenNumbers >= minPercEvenSortedNumbers || percOddNumbers < minPercOddSortedNumbers)) {
                    // dezena é PAR e o percentual de pares já foi satisfeito (e o percentual de
                    // ímpares não foi satisfeito)
                    continue;
                }

                // validando se a dezena já não foi sorteada
                if (!sortedNumbers.contains(sortedNumber)) {

                    if (isOddNumber) {
                        countSortedOddNumbers++;
                    } else {
                        countSortedEvenNumbers++;
                    }

                    line.setCountSortedNumbers(line.getCountSortedNumbers() + 1);
                    sortedNumbers.add(sortedNumber);
                } else {
                    // mais uma tentativa de preencher essa linha..
                    countAttempts++;
                }
            }

        });

        sortedNumbers.sort(Comparator.naturalOrder());

        /*
         * Parte 3 - construindo demais jogos (espelhos)
         */

        // Jogo 2- Isolando 6 dezenas do jogo original
        sortedNumbersBlockRandom = extractRandomNumbersFromGame(sortedNumbers, 6);

        System.out.println("\nJogo #2 (Movendo 6 dezenas aleatórias): ");
        game2Numbers = new ArrayList<>();
        game2Numbers.addAll(moveNumbersTo(sortedNumbersBlockRandom));
        game2Numbers.addAll(getExceptionNumbersFromOriginalGame(sortedNumbersBlockRandom));
        game2Numbers.sort(Comparator.naturalOrder());

        // Jogo 3- Isolando 5 dezenas do jogo original
        sortedNumbersBlockRandom = extractRandomNumbersFromGame(sortedNumbers, 5);

        System.out.println("\nJogo #3 (Movendo 5 dezenas aleatórias): ");
        game3Numbers = new ArrayList<>();
        game3Numbers.addAll(moveNumbersTo(sortedNumbersBlockRandom));
        game3Numbers.addAll(getExceptionNumbersFromOriginalGame(sortedNumbersBlockRandom));
        game3Numbers.sort(Comparator.naturalOrder());

        // Jogo 4- Isolando 5 dezenas do jogo original
        sortedNumbersBlockRandom = extractRandomNumbersFromGame(sortedNumbers, 5);

        System.out.println("\nJogo #4 (Movendo 5 dezenas aleatórias): ");
        game4Numbers = new ArrayList<>();
        game4Numbers.addAll(moveNumbersTo(sortedNumbersBlockRandom));
        game4Numbers.addAll(getExceptionNumbersFromOriginalGame(sortedNumbersBlockRandom));
        game4Numbers.sort(Comparator.naturalOrder());

        // Jogo 5- Isolando 4 dezenas do jogo original
        sortedNumbersBlockRandom = extractRandomNumbersFromGame(sortedNumbers, 4);

        System.out.println("\nJogo #5 (Movendo 4 dezenas aleatórias): ");
        game5Numbers = new ArrayList<>();
        game5Numbers.addAll(moveNumbersTo(sortedNumbersBlockRandom));
        game5Numbers.addAll(getExceptionNumbersFromOriginalGame(sortedNumbersBlockRandom));
        game5Numbers.sort(Comparator.naturalOrder());

        // Jogo 6- Isolando 3 dezenas do jogo original
        sortedNumbersBlockRandom = extractRandomNumbersFromGame(sortedNumbers, 3);

        System.out.println("\nJogo #6 (Movendo 3 dezenas aleatórias): ");
        game6Numbers = new ArrayList<>();
        game6Numbers.addAll(moveNumbersTo(sortedNumbersBlockRandom));
        game6Numbers.addAll(getExceptionNumbersFromOriginalGame(sortedNumbersBlockRandom));
        game6Numbers.sort(Comparator.naturalOrder());
    }

    /**
     * Primeiramente, tenta-se obter a mesma quantidade de dezenas dos blocos
     * prioritários (conforme estatísticas). Caso não se tenha obtido a meMove cada
     * dezena para dois sentidos possíveis (LEFT ou RIGHT)
     *
     * @param numbers
     * @return Lista de números atualizados
     */
    private static List<Integer> moveNumbersTo(List<Integer> numbers) {

        List<Integer> newNumbers = new ArrayList<>();
        Integer[] directions = {1, 2};

        numbers.forEach(number -> {

            Boolean found = null;
            int maxLoopToTry = 2;
            int loopCounter = 1;

            Integer nextNumber = null;

            while ((found == null || found == false) && loopCounter <= maxLoopToTry) {

                int direction = directions[new Random().nextInt(2)];

                if ((direction == 1 && found == null) || (found != null && found == false)) {
                    // left
                    nextNumber = number - 1;

                    while ((newNumbers.contains(nextNumber) || sortedNumbers.contains(nextNumber)) && nextNumber > minDez) {
                        nextNumber--;
                    }

                    if ((!newNumbers.contains(nextNumber) && !sortedNumbers.contains(nextNumber)) && nextNumber >= minDez) {
                        found = true;
                    } else {
                        found = false;
                    }
                }

                if ((direction == 2 && found == null) || (found != null && found == false)) {
                    // right
                    nextNumber = number + 1;

                    while ((newNumbers.contains(nextNumber) || sortedNumbers.contains(nextNumber)) && nextNumber < maxDez) {
                        nextNumber++;
                    }

                    if ((!newNumbers.contains(nextNumber) && !sortedNumbers.contains(nextNumber)) && nextNumber <= maxDez) {
                        found = true;
                    } else {
                        found = false;
                    }
                }

                loopCounter++;
            }

            if (found == true) {
                // encontrou a próxima dezena/ anterior
                newNumbers.add(nextNumber);
            } else {
                // repete a dezena
                newNumbers.add(number);
            }

        });

        return newNumbers;
    }

    /**
     * Obtém as dezenas excedentes do jogo original, desconsiderando as dezenas
     * informadas no parâmetro
     *
     * @param numbers
     * @return
     */
    private static List<Integer> getExceptionNumbersFromOriginalGame(List<Integer> numbers) {
        List<Integer> exceptNumbers = new ArrayList<>();
        for (Integer integer : sortedNumbers) {
            if (!numbers.contains(integer)) {
                exceptNumbers.add(integer);
            }
        }
        return exceptNumbers;
    }

    /**
     * Extrai uma quantidade "x" de dezenas do conjunto de dezenas informado. As
     * dezenas extraídas são aleatórias.
     *
     * @param numbers    Lista de dezenas informadas
     * @param qtdNumbers Qtd máxima de dezenas que serão extraídas
     * @return
     */
    private static List<Integer> extractRandomNumbersFromGame(List<Integer> numbers, int qtdNumbers) {
        Random r = new Random();
        Integer sortedIndex;
        Integer sortedNumber;
        List<Integer> extractedNumbers = new ArrayList<>();
        while (extractedNumbers.size() < qtdNumbers) {
            sortedIndex = r.nextInt(numbers.size() - 1);
            sortedNumber = numbers.get(sortedIndex);

            if (!extractedNumbers.contains(sortedNumber)) {
                extractedNumbers.add(sortedNumber);
            }
        }

        return extractedNumbers;
    }

}