package Algorithms.NumberTheory;

// Prime Factorization using Smallest Prime Factor Sieve.
public class PrimeFactorization {

    // Performs prime factorization using a precomputed smallest prime factor table.
    public PrimeFactorization(int number, int[] smallestPrimeFactorList) {
        while (number > 1) {
            int primeFactor = smallestPrimeFactorList[number];

            // Process current prime factor.

            while (number % primeFactor == 0) {
                number /= primeFactor;
            }
        }
    }
}