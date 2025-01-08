package zestaw1.zadaniea;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int a = 50; 
        int b = 50; 
        int simulations = 100000; 

        double pA = simulateGame(a, b, simulations);
        double analyticalResult = calculateAnalyticalProbability(a, b);

        System.out.printf("Symulowane prawdopodobieństwo zwycięstwa gracza A: %.5f\n", pA);
        System.out.printf("Analityczne prawdopodobieństwo zwycięstwa gracza A: %.5f\n", analyticalResult);
    }

    public static double simulateGame(int a, int b, int simulations) {
        Random random = new Random();
        int winsA = 0;

        for (int i = 0; i < simulations; i++) {
            int pointsA = a;
            int pointsB = b;

            while (pointsA > 0 && pointsB > 0) {
                if (random.nextBoolean()) {
                    pointsA++;
                    pointsB--;
                } else {
                    pointsA--;
                    pointsB++;
                }
            }

            if (pointsA > 0) {
                winsA++;
            }
        }

        return (double) winsA / simulations;
    }

    public static double calculateAnalyticalProbability(int a, int b) {
       
        if (a == 0) return 0.0;
        if (b == 0) return 1.0;

        return (1.0 - Math.pow((double) b / (a + b), 2)) / (1.0 - Math.pow((double) b / (a + b), 2));
    }
}
