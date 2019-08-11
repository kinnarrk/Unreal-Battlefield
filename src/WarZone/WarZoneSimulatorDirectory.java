/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WarZone;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author bhaVYa
 */
public class WarZoneSimulatorDirectory {
    int TOTAL_TARGETS;
    int TOTAL_DRONES;
    private double[][] adjMatrix;
    private final Random r = new Random();
    private DroneDirectory carDirectory;
    private TargetDirectory stopDirectory;
    
    public WarZoneSimulatorDirectory(int targets, int drones, int minb, int maxb) {
        this.TOTAL_TARGETS = targets;
        this.TOTAL_DRONES = drones;

        adjMatrix = new double[TOTAL_TARGETS + 1][TOTAL_TARGETS + 1];

        for (int i = 0; i < TOTAL_TARGETS + 1; i++) {
            for (int j = i; j < TOTAL_TARGETS + 1; j++) {
                if (i == j) {
                    adjMatrix[i][j] = 0;
                } else {
                    adjMatrix[i][j] = adjMatrix[j][i] = r.nextInt(100) + 1;
                }
            }
        }
        
        carDirectory = new DroneDirectory();
        for(int i=0; i<drones; i++) {
            int cap = generateCapacity(50, 75);
            carDirectory.addDrone(cap);
        }
        
        stopDirectory = new TargetDirectory();
        for(int i=0; i<targets; i++) {
            int demand = generateCapacity(minb, maxb);
            stopDirectory.addStop(demand);
        }
        
    }

    public void printDistanceMatrix() {
        System.out.print("\tStation\t");
        for (int k = 0; k < 40; k++) {
            System.out.print("Target" + (k + 1) + "\t");
        }
        System.out.println();

        for (int i = 0; i < adjMatrix.length; i++) {
            System.out.print((i == 0 ? "Air Base" : "Targets" + i) + "\t");
            for (int j = 0; j < adjMatrix.length; j++) {
                System.out.print(adjMatrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
    
    int generateCapacity(int min, int max) {
        return r.nextInt(max-min)+min;
    }

    public double[][] getAdjMatrix() {
        return adjMatrix;
    }
    
}
