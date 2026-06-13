package OnlineJudge.p13153;

import java.util.Scanner;

public class Main {

    static int[] parent;
    static final int MAX = 1_000_000;

    public static void main(String[] args) {

        // Precompute prime factor for every possible number.
        int[] smallestPrimeFactorList = new int[MAX + 1];
        for (int i = 2; i <= MAX; i++) {
            if (smallestPrimeFactorList[i] == 0) {
                for (int j = i; j <= MAX; j += i) {
                    if (smallestPrimeFactorList[j] == 0) {
                        smallestPrimeFactorList[j] = i;
                    }
                }
            }
        }

        Scanner scanner = new Scanner(System.in);

        int t = scanner.nextInt();
        int tCurrent = 0;

        while (tCurrent < t) {

            int n = scanner.nextInt();
            int[] numbers = new int[n];
            parent = new int[n];

            // Initialize DSU - every number is it´s own component.
            for (int i = 0; i < n; i++) {
                numbers[i] = scanner.nextInt();
                parent[i] = i;
            }

            int[] firstNodeIndexByPrimeFactor = new int[MAX + 1];
            for (int i = 0; i <= MAX; i++) {
                firstNodeIndexByPrimeFactor[i] = -1;
            }

            // Compute and connect numbers based on prime factors.
            for (int nodeIndex = 0; nodeIndex < n; nodeIndex++) {
                int currentNumber = numbers[nodeIndex];

                while (currentNumber > 1) {
                    int primeFactor = smallestPrimeFactorList[currentNumber];

                    if (firstNodeIndexByPrimeFactor[primeFactor] == -1) {
                        firstNodeIndexByPrimeFactor[primeFactor] = nodeIndex;
                    } else {
                        union(nodeIndex, firstNodeIndexByPrimeFactor[primeFactor]);
                    }

                    while (currentNumber % primeFactor == 0) {
                        currentNumber /= primeFactor;
                    }
                }
            }

            // Count component roots.
            int componentCount = 0;
            for (int i = 0; i < n; i++) {
                if (find(i) == i) {
                    componentCount++;
                }
            }

            System.out.println("Case " + (++tCurrent) + ": " + componentCount);
        }
    }

    // Find root of the component.
    static int find(int node) {
        if (parent[node] == node) {
            return node;
        }
        parent[node] = find(parent[node]);
        return parent[node];
    }

    // Merge two connected components.
    static void union(int firstNode, int secondNode) {
        int firstRoot = find(firstNode);
        int secondRoot = find(secondNode);

        if (firstRoot != secondRoot) {
            parent[secondRoot] = firstRoot;
        }
    }
}