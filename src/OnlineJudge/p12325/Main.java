package OnlineJudge.p12325;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testCaseCount = scanner.nextInt();
        int testCaseCurrent = 0;

        while (testCaseCurrent < testCaseCount) {
            long chestSize = scanner.nextLong();
            long emeraldSize = scanner.nextLong();
            long emeraldValue = scanner.nextLong();
            long sapphireSize = scanner.nextLong();
            long sapphireValue = scanner.nextLong();

            long result = 0;

            for (long emeraldCount = 0;  emeraldCount <= Math.min(100000, chestSize / emeraldSize); emeraldCount++) {
                long remainingSize = chestSize - emeraldCount * emeraldSize;
                long sapphireCount = remainingSize / sapphireSize;
                long totalValue = emeraldCount * emeraldValue + sapphireCount * sapphireValue;
                result = Math.max(result, totalValue);
            }

            for (long sapphireCount = 0; sapphireCount <= Math.min(100000, chestSize / sapphireSize); sapphireCount++) {
                long remainingSize = chestSize - sapphireCount * sapphireSize;
                long emeraldCount = remainingSize / emeraldSize;
                long totalValue = sapphireCount * sapphireValue + emeraldCount * emeraldValue;
                result = Math.max(result, totalValue);
            }

            System.out.println("Case #" + ++testCaseCurrent + ": " + result);
        }
    }
}
