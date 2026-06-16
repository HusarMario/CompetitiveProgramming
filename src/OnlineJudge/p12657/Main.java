package OnlineJudge.p12657;

import java.util.Scanner;

public class Main {

    static int[] prev;
    static int[] next;
    static boolean reversed;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testCaseCurrent = 0;
        while (scanner.hasNextInt()) {
            int numberOfBoxes = scanner.nextInt();
            int numberOfCommands = scanner.nextInt();

            prev = new int[numberOfBoxes + 1];
            next = new int[numberOfBoxes + 1];
            for (int i = 1; i <= numberOfBoxes; i++) {
                prev[i] = i - 1;
                next[i] = i + 1;
            }
            next[0] = 1;
            prev[0] = numberOfBoxes;
            next[numberOfBoxes] = 0;
            reversed = false;

            for (int i = 1; i <= numberOfCommands; i++) {
                int command = scanner.nextInt();

                if (command == 4) {
                    reversed = !reversed;
                } else {
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();

                    if (command == 3) {
                        if (next[x] == y) {
                            next[prev[x]] = next[x];
                            prev[next[x]] = prev[x];
                            prev[next[y]] = x;
                            next[x] = next[y];
                            prev[x] = y;
                            next[y] = x;
                        } else if (next[y] == x) {
                            next[prev[y]] = next[y];
                            prev[next[y]] = prev[y];
                            prev[next[x]] = y;
                            next[y] = next[x];
                            prev[y] = x;
                            next[x] = y;
                        } else {
                            int prevX = prev[x];
                            int nextX = next[x];
                            int prevY = prev[y];
                            int nextY = next[y];
                            next[prevX] = y;
                            prev[y] = prevX;
                            next[y] = nextX;
                            prev[nextX] = y;
                            next[prevY] = x;
                            prev[x] = prevY;
                            next[x] = nextY;
                            prev[nextY] = x;
                        }
                    }
                    else if ((command == 2 && !reversed) || (command == 1 && reversed)) {
                        if (next[y] != x) {
                            next[prev[x]] = next[x];
                            prev[next[x]] = prev[x];
                            prev[next[y]] = x;
                            next[x] = next[y];
                            prev[x] = y;
                            next[y] = x;
                        }
                    }
                    else if ((command == 1 && !reversed) || (command == 2 && reversed)) {
                        if (prev[y] != x) {
                            next[prev[x]] = next[x];
                            prev[next[x]] = prev[x];
                            next[prev[y]] = x;
                            prev[x] = prev[y];
                            next[x] = y;
                            prev[y] = x;
                        }
                    }
                }
            }

            long sum = 0;
            int current = reversed ? prev[0] : next[0];
            while (current != 0) {
                sum += current;
                current = reversed ? prev[current] : next[current];
                if (current != 0) {
                    current = reversed ? prev[current] : next[current];
                }
            }
            System.out.println("Case " + (++testCaseCurrent) + ": " + sum);
        }
    }
}
