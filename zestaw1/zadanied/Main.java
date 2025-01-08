package zestaw1.zadanied;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int totalPoints = 20; 
        int a = 10; 
        int b = totalPoints - a; 
        double[] pAValues = {0.2, 0.5, 0.8}; 

        for (double pA : pAValues) {
            System.out.printf("\nTrajektorie dla pA = %.2f:\n", pA);
            simulateTrajectories(a, b, pA, 3); 
        }
    }

    public static void simulateTrajectories(int a, int b, double pA, int games) {
        Random random = new Random();

        for (int game = 1; game <= games; game++) {
            int pointsA = a;
            int pointsB = b;
            List<Integer> trajectoryA = new ArrayList<>();

            trajectoryA.add(pointsA);
            int turn = 0;

            while (pointsA > 0 && pointsB > 0) {
                if (random.nextDouble() < pA) {
                    pointsA++;
                    pointsB--;
                } else {
                    pointsA--;
                    pointsB++;
                }
                trajectoryA.add(pointsA);
                turn++;
            }

            System.out.printf("Gra %d: Trajektoria gracza A: %s\n", game, trajectoryA);
        }
    }
}
