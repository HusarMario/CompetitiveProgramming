package OnlineJudge.p12005;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Integer> primeNumbers = new ArrayList<>();
        boolean[] isNotPrime = new boolean[20_000_000 + 1];
        for (int number = 2; number <= 20_000_000; number++) {
            if (!isNotPrime[number]) {
                primeNumbers.add(number);

                if ((long) number * number <= 20_000_000) {
                    for (int multiple = number * number; multiple <= 20_000_000; multiple += number) {
                        isNotPrime[multiple] = true;
                    }
                }
            }
        }

        while (true) {
            long c = scanner.nextLong();

            if (c == 0) {
                break;
            }

            //c = ab - ((a + b) / 2) + 1
            //4c = 4ab - 2a - 2b + 4
            //4c - 3 = 4ab - 2a - 2b + 1
            //4c - 3 = (2a - 1)(2b - 1)
            long leftSide = 4 * c - 3;
            long combinationsCount = 1;

            for (int primeNumber : primeNumbers) {
                if ((long) primeNumber * primeNumber > leftSide) {
                    break;
                }

                int divisorCount = 0;

                while (leftSide % primeNumber == 0) {
                    divisorCount++;
                    leftSide /= primeNumber;
                }

                if (divisorCount > 0) {
                    combinationsCount *= divisorCount + 1;
                }
            }

            if (leftSide > 1) {
                combinationsCount *= 2;
            }

            System.out.println(c + " " + combinationsCount);
        }
    }
}