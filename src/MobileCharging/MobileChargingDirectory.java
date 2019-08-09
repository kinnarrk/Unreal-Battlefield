/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MobileCharging;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author bhaVYa
 */
public class MobileChargingDirectory {
    int TOTAL_STOPS;
    int TOTAL_CARS;
    private double[][] adjMatrix;
    private final Random r = new Random();
    private CarDirectory carDirectory;
    private StopDirectory stopDirectory;
    
    public MobileChargingDirectory(int stops, int cars, int minb, int maxb) {
        this.TOTAL_STOPS = stops;
        this.TOTAL_CARS = cars;

        adjMatrix = new double[TOTAL_STOPS + 1][TOTAL_STOPS + 1];

        for (int i = 0; i < TOTAL_STOPS + 1; i++) {
            for (int j = i; j < TOTAL_STOPS + 1; j++) {
                if (i == j) {
                    adjMatrix[i][j] = 0;
                } else {
                    adjMatrix[i][j] = adjMatrix[j][i] = r.nextInt(100) + 1;
                }
            }
        }
        
        carDirectory = new CarDirectory();
        for(int i=0; i<cars; i++) {
            carDirectory.addCar();
        }
        
        stopDirectory = new StopDirectory();
        for(int i=0; i<stops; i++) {
            stopDirectory.addStop();
        }
        
        System.out.println(carDirectory.getCar().size());
    }

    public void printDistanceMatrix() {
        System.out.print("\tStation\t");
        for (int k = 0; k < 40; k++) {
            System.out.print("Store" + (k + 1) + "\t");
        }
        System.out.println();

        for (int i = 0; i < adjMatrix.length; i++) {
            System.out.print((i == 0 ? "Station" : "Stop" + i) + "\t");
            for (int j = 0; j < adjMatrix.length; j++) {
                System.out.print(adjMatrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

}
