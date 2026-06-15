package OnlineJudge.p12700;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testCaseCount = scanner.nextInt();
        int testCaseCurrent = 0;

        while (testCaseCurrent < testCaseCount) {
            int matchCount = scanner.nextInt();
            String matchResults = scanner.next();
            int bResult = 0;
            int wResult = 0;
            int tResult = 0;
            int aResult = 0;

            for (int i = 0; i < matchCount; i++) {
                char result = matchResults.charAt(i);
                if (result == 'B') {
                    bResult++;
                }
                else if (result == 'W') {
                    wResult++;
                }
                else if (result == 'T') {
                    tResult++;
                }
                else {
                    aResult++;
                }
            }

            int playedMatches = matchCount - aResult;

            System.out.print("Case " + ++testCaseCurrent + ": ");
            if (playedMatches == 0) {
                System.out.println("ABANDONED");
            }
            else if (bResult == playedMatches) {
                System.out.println("BANGLAWASH");
            }
            else if (wResult == playedMatches) {
                System.out.println("WHITEWASH");
            }
            else if (bResult > wResult) {
                System.out.println("BANGLADESH " + bResult + " - " + wResult);
            }
            else if (wResult > bResult) {
                System.out.println("WWW " + wResult + " - " + bResult);
            }
            else {
                System.out.println("DRAW " + bResult + " " + tResult);
            }
        }
    }
}