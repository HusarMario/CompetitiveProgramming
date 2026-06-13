package Algorithms.NumberTheory;

// Precomputes the smallest prime factor for every number from 2 up to the specified limit.
public class SmallestPrimeFactorSieve {

    // Stores prime numbers of each number index.
    private final int[] smallestPrimeFactor;

    // Build the smallest prime factor table.
    public SmallestPrimeFactorSieve(int maximumNumber) {
        smallestPrimeFactor = new int[maximumNumber + 1];
        for (int i = 2; i <= maximumNumber; i++) {
            if (smallestPrimeFactor[i] == 0) {
                for (int j = i; j <= maximumNumber; j += i) {
                    if (smallestPrimeFactor[j] == 0) {
                        smallestPrimeFactor[j] = i;
                    }
                }
            }
        }
    }
}
