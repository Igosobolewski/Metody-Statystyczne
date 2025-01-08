package zestaw2.zadanieb;

import java.util.Random;

public class Main {
   
    private static final double[][] P = {
        {0.64, 0.32, 0.04},
        {0.40, 0.50, 0.10},
        {0.25, 0.50, 0.25}
    };


    private static final int STATES = P.length;

  
    private static final int N_MAX = 10000;

    public static void main(String[] args) {
      
        for (int initialState = 0; initialState < STATES; initialState++) {
            System.out.printf("Symulacja dla stanu początkowego: %d\n", initialState);
            simulateMarkovChain(initialState);
            System.out.println();
        }
    }

    private static void simulateMarkovChain(int initialState) {
        Random random = new Random();
        int currentState = initialState;
        int[] visitCounts = new int[STATES];

      
        for (int step = 1; step <= N_MAX; step++) {
    
            visitCounts[currentState]++;

        
            double rand = random.nextDouble();
            double cumulativeProbability = 0.0;
            for (int nextState = 0; nextState < STATES; nextState++) {
                cumulativeProbability += P[currentState][nextState];
                if (rand < cumulativeProbability) {
                    currentState = nextState;
                    break;
                }
            }


            if (step % 1000 == 0) {
                System.out.printf("Krok: %d\n", step);
                for (int state = 0; state < STATES; state++) {
                    double piExp = (double) visitCounts[state] / step;
                    System.out.printf("π_%d^EXP = %.4f\n", state, piExp);
                }
                System.out.println();
            }
        }
    }
}
