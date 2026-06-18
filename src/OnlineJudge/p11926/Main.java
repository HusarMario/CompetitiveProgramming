package OnlineJudge.p11926;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int singleTask = scanner.nextInt();
            int repeatTask = scanner.nextInt();

            if (singleTask == 0 && repeatTask == 0) {
                break;
            }

            boolean[] minutes = new boolean[1000000 + 1];
            boolean conflict = false;

            for (int i = 0; i < singleTask; i++) {
                int start = scanner.nextInt();
                int end = scanner.nextInt();

                if (!conflict) {
                    for (int j = start; j < end; j++) {
                        if (minutes[j]) {
                            conflict = true;
                            break;
                        }
                        minutes[j] = true;

                    }
                }
            }

            for (int i = 0; i < repeatTask; i++) {
                int start = scanner.nextInt();
                int end = scanner.nextInt();
                int interval = scanner.nextInt();

                if (!conflict) {
                    while (start <= 1000000) {
                        for (int j = start; j < end && j <= 1000000; j++) {
                            if (minutes[j]) {
                                conflict = true;
                                break;
                            }
                            minutes[j] = true;

                        }

                        start += interval;
                        end += interval;
                    }
                }
            }

            if (conflict) {
                System.out.println("CONFLICT");
            } else {
                System.out.println("NO CONFLICT");
            }
        }
    }
}