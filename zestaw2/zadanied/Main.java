package zestaw2.zadanied;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        final int N = 100; 
        final int Nmax = 10_000; 
        int[] odwiedziny = new int[N + 1]; 
        int x = 0; 
        Random rand = new Random();

        for (int i = 0; i < Nmax; i++) {
            odwiedziny[x]++;
            double Plogowania = 0.2;
            double PpozostaniaNiezalogowanym = 0.8;
            double PpozostaniaZalogowanym = 0.008 * x + 0.1;
            double Pwylogowania = 1 - PpozostaniaZalogowanym;

            double p = rand.nextDouble();
            if (x == 0) {
               
                if (p < Plogowania) {
                    x++;
                }
            } else if (x == N) {
            
                if (p < Pwylogowania) {
                    x--;
                }
            } else {
              
                if (p < Plogowania) {
                    x++;
                } else if (p < Plogowania + Pwylogowania) {
                    x--;
                }
            }
        }


        double[] Pi_exp = new double[N + 1];
        for (int i = 0; i <= N; i++) {
            Pi_exp[i] = odwiedziny[i] / (double) Nmax;
            System.out.printf("Stan %d: %.4f%n", i, Pi_exp[i]);
        }

    }
}

