package zestaw2.zadaniec;

import java.util.Random;

public class Main {
    private static final int TOTAL_USERS = 100;
    private static final double P_LOGIN = 0.2;
    private static final double P_LOGOUT = 0.5;
    private static final int N_MAX = 10000;

    public static void main(String[] args) {

        for (int initialState = 0; initialState <= TOTAL_USERS; initialState += 50) {
            System.out.printf("Symulacja dla stanu początkowego: %d\n", initialState);
            simulateMarkovChain(initialState);
            System.out.println();
        }
    }

    private static void simulateMarkovChain(int initialState) {
        Random random = new Random();
        int currentState = initialState;
        int[] visitCounts = new int[TOTAL_USERS + 1];

    
        for (int step = 1; step <= N_MAX; step++) {
      
            visitCounts[currentState]++;

   
            double rand = random.nextDouble();
            double pStay = calculateStayProbability(currentState);
            double pUp = calculateUpProbability(currentState);
            double pDown = calculateDownProbability(currentState);

            if (rand < pDown) {
                currentState--;
            } else if (rand < pDown + pStay) {

            } else {
                currentState++;
            }


            if (step % 1000 == 0) {
                System.out.printf("Krok: %d\n", step);
                for (int state = 0; state <= TOTAL_USERS; state++) {
                    double piExp = (double) visitCounts[state] / step;
                    System.out.printf("π_%d^EXP = %.4f\n", state, piExp);
                }
                System.out.println();
            }
        }
    }

    private static double calculateStayProbability(int currentState) {
        int loggedIn = currentState;
        int loggedOut = TOTAL_USERS - loggedIn;
        double pStayLoggedIn = Math.pow(0.5, loggedIn);
        double pStayLoggedOut = Math.pow(0.8, loggedOut);
        return pStayLoggedIn * pStayLoggedOut;
    }

    private static double calculateUpProbability(int currentState) {
        int loggedOut = TOTAL_USERS - currentState;
        if (loggedOut == 0) return 0;
        return 1 - Math.pow(0.8, loggedOut);
    }

    private static double calculateDownProbability(int currentState) {
        if (currentState == 0) return 0;
        return 1 - Math.pow(0.5, currentState);
    }
}

