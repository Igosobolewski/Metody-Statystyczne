package zestaw1.zadanie_b;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int totalPoints = 100; 
        int a = 50; 
        int b = totalPoints - a; 
        double pA = 0.5; 
        int simulations = 100000; 

        double ruinProbability = simulateRuin(a, b, pA, simulations);
        double theoreticalProbability = calculateTheoreticalRuinProbability(a, b, pA);

        System.out.printf("Symulowane prawdopodobieństwo ruiny gracza A: %.5f\n", ruinProbability);
        System.out.printf("Teoretyczne prawdopodobieństwo ruiny gracza A: %.5f\n", theoreticalProbability);
    }

    public static double simulateRuin(int a, int b, double pA, int simulations) {
        Random random = new Random();
        int ruinCount = 0;

        for (int i = 0; i < simulations; i++) {
            int pointsA = a;
            int pointsB = b;

            while (pointsA > 0 && pointsB > 0) {
                if (random.nextDouble() < pA) {
                    pointsA++;
                    pointsB--;
                } else {
                    pointsA--;
                    pointsB++;
                }
            }

            if (pointsA == 0) {
                ruinCount++;
            }
        }

        return (double) ruinCount / simulations;
    }

    public static double calculateTheoreticalRuinProbability(int a, int b, double pA) {
        double pB = 1.0 - pA; 
        if (pA == pB) {
            
            return (double) b / (a + b);
        } else {
            
            double q = pB / pA;
            return (1 - Math.pow(q, a)) / (1 - Math.pow(q, a + b));
        }
    }
}
