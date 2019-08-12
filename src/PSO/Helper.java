/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PSO;

import java.util.Random;

/**
 *
 * @author Kinnar
 */
public class Helper {

    public static void shuffleArray(int[] ar) {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int idx = rnd.nextInt(i + 1);
            // Simple swap
            int temp = ar[idx];
            ar[idx] = ar[i];
            ar[i] = temp;
        }
    }

    public static double[] intToDoubleArray(int[] src) {
        double[] dest = new double[src.length];
        for (int i = 0; i < src.length; i++) {
            dest[i] = src[i];
        }
        return dest;
    }
    
    public static double[] replicateArray(double[] src) {
        double[] dest = new double[src.length];
        for (int i = 0; i < src.length; i++) {
            dest[i] = src[i];
        }
        return dest;
    }
}
