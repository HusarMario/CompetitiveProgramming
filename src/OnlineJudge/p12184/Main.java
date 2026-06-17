package OnlineJudge.p12184;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testCaseCount = scanner.nextInt();
        int testCaseCurrent = 0;

        while (testCaseCurrent++ < testCaseCount) {
            long serialNumbers = scanner.nextLong();

            long result = 0;
            long maximumLastNumber = 0;

            for (int i = 0; i < serialNumbers; i++) {
                long sum = 0;
                for (int j = 0; j < 9; j++) {
                    sum += scanner.nextLong();
                }
                long lastNumber = scanner.nextLong();
                maximumLastNumber = Math.max(maximumLastNumber, lastNumber);
                long difference = Math.abs(sum - lastNumber);

                while (difference != 0) {
                    long remainder = result % difference;
                    result = difference;
                    difference = remainder;
                }
                result = Math.abs(result);
            }

            if (result <= 1 || result <= maximumLastNumber) {
                System.out.println("impossible");
            } else {
                System.out.println(result);
            }
        }
    }
}