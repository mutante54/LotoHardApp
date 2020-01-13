package com.br.lotohardapp;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class LotoHardActivity extends AppCompatActivity {

    // total de numeros a serem sorteados
    private static int qtdSortedNumbers = 15;

    // percentual minimo de números impares a serem sorteados
    private static int minPercOddSortedNumbers = 50;

    // percentual minimo de números pares a serem sorteados
    private static int minPercEvenSortedNumbers = 50;

    // numeros sorteados (jogo original)
    private static List<Integer> sortedNumbers = new ArrayList<>();

    // Bloco Aleatório (com "x" dezenas do jogo original)
    private static List<Integer> sortedNumbersBlockRandom = new ArrayList<>();

    private static final int minDez = 1;
    private static final int maxDez = 25;

    // quantidade de numeros impares ja sorteados
    private static double countSortedOddNumbers = 0;

    // quantidade de numeros pares ja sorteados
    private static double countSortedEvenNumbers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loto_hard);
    }

    public static void main(String[] args) {

        List<Line> lines = new ArrayList<>();
        lines.add(new Line(1, 0, 3, 4, Arrays.asList(1, 2, 3, 4, 5)));
        lines.add(new Line(2, 1, 1, 2, Arrays.asList(6, 7, 8, 9, 10)));
        lines.add(new Line(3, 0, 3, 3, Arrays.asList(11, 12, 13, 14, 15)));
        lines.add(new Line(4, 1, 2, 3, Arrays.asList(16, 17, 18, 19, 20)));
        lines.add(new Line(5, 0, 3, 3, Arrays.asList(21, 22, 23, 24, 25)));

        /*
         * Parte 1 - Preenchendo o minimo de dezenas por linha (sorteando dezenas
         * aleatoriamente)
         */
        lines.forEach(line -> {
            while (line.getCountSortedNumbers() < line.getMinSortedNumbers()) {
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
            while (line.getCountSortedNumbers() < line.getMaxSortedNumbers() && countAttempts < maxAttempts) {

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

        System.out.println("Data de geração: " + new Date().toString());

        /*
         * Imprimir as dezenas
         */

        System.out.println("\nDezenas sorteadas (Jogo #1- original): \n");
        printNumbers(sortedNumbers);

        System.out.println("\nQtd Pares: " + (int) countSortedEvenNumbers);
        System.out.println("\nQtd Ímpares: " + (int) countSortedOddNumbers);

        /*
         * Parte 3 - construindo demais jogos (espelhos)
         */

        // Jogo 2- Isolando 6 dezenas do jogo original
        sortedNumbersBlockRandom = extractRandomNumbersFromGame(sortedNumbers, 6);

        System.out.println("\nJogo #2 (Movendo 6 dezenas aleatórias): ");
        List<Integer> game2Numbers = new ArrayList<>();
        game2Numbers.addAll(moveNumbersTo(sortedNumbersBlockRandom));
        game2Numbers.addAll(getExceptionNumbersFromOriginalGame(sortedNumbersBlockRandom));
        game2Numbers.sort(Comparator.naturalOrder());
        printNumbers(game2Numbers);
        System.out.println("Qtd de dezenas que não constam no jogo #1: " + getQtdNumbersDiffWithOriginalGame(game2Numbers));

        // Jogo 3- Isolando 5 dezenas do jogo original
        sortedNumbersBlockRandom = extractRandomNumbersFromGame(sortedNumbers, 5);

        System.out.println("\nJogo #3 (Movendo 5 dezenas aleatórias): ");
        List<Integer> game3Numbers = new ArrayList<>();
        game3Numbers.addAll(moveNumbersTo(sortedNumbersBlockRandom));
        game3Numbers.addAll(getExceptionNumbersFromOriginalGame(sortedNumbersBlockRandom));
        game3Numbers.sort(Comparator.naturalOrder());
        printNumbers(game3Numbers);
        System.out.println("Qtd de dezenas que não constam no jogo #1: " + getQtdNumbersDiffWithOriginalGame(game3Numbers));

        // Jogo 4- Isolando 5 dezenas do jogo original
        sortedNumbersBlockRandom = extractRandomNumbersFromGame(sortedNumbers, 5);

        System.out.println("\nJogo #4 (Movendo 5 dezenas aleatórias): ");
        List<Integer> game4Numbers = new ArrayList<>();
        game4Numbers.addAll(moveNumbersTo(sortedNumbersBlockRandom));
        game4Numbers.addAll(getExceptionNumbersFromOriginalGame(sortedNumbersBlockRandom));
        game4Numbers.sort(Comparator.naturalOrder());
        printNumbers(game4Numbers);
        System.out.println("Qtd de dezenas que não constam no jogo #1: " + getQtdNumbersDiffWithOriginalGame(game4Numbers));

        // Jogo 5- Isolando 4 dezenas do jogo original
        sortedNumbersBlockRandom = extractRandomNumbersFromGame(sortedNumbers, 4);

        System.out.println("\nJogo #5 (Movendo 4 dezenas aleatórias): ");
        List<Integer> game5Numbers = new ArrayList<>();
        game5Numbers.addAll(moveNumbersTo(sortedNumbersBlockRandom));
        game5Numbers.addAll(getExceptionNumbersFromOriginalGame(sortedNumbersBlockRandom));
        game5Numbers.sort(Comparator.naturalOrder());
        printNumbers(game5Numbers);
        System.out.println("Qtd de dezenas que não constam no jogo #1: " + getQtdNumbersDiffWithOriginalGame(game5Numbers));

        // Jogo 6- Isolando 3 dezenas do jogo original
        sortedNumbersBlockRandom = extractRandomNumbersFromGame(sortedNumbers, 3);

        System.out.println("\nJogo #6 (Movendo 3 dezenas aleatórias): ");
        List<Integer> game6Numbers = new ArrayList<>();
        game6Numbers.addAll(moveNumbersTo(sortedNumbersBlockRandom));
        game6Numbers.addAll(getExceptionNumbersFromOriginalGame(sortedNumbersBlockRandom));
        game6Numbers.sort(Comparator.naturalOrder());
        printNumbers(game6Numbers);
        System.out.println("Qtd de dezenas que não constam no jogo #1: " + getQtdNumbersDiffWithOriginalGame(game6Numbers));

    }

    /**
     * Imprime os números (como uma única string)
     *
     * @param numbers
     */
    private static void printNumbers(List<Integer> numbers) {
        String line = "";
        for (int i = 0; i < numbers.size(); i++) {
            if (!line.isEmpty()) {
                line += "-";
            }
            line += numbers.get(i);
        }

        System.out.println(line);
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
        Integer[] directions = { 1, 2 };

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
     * Obtém a quantidade de dezenas do jogo informado que não constam no jogo
     * original
     *
     * @param numbers
     * @return
     */
    private static int getQtdNumbersDiffWithOriginalGame(List<Integer> numbers) {
        int qtd = 0;
        for (Integer integer : numbers) {
            if (!sortedNumbers.contains(integer)) {
                qtd++;
            }
        }
        return qtd;
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
     * @param numbers
     *            Lista de dezenas informadas
     * @param qtdNumbers
     *            Qtd máxima de dezenas que serão extraídas
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
