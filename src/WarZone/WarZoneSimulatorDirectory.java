/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WarZone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private DroneDirectory droneDirectory;
    private TargetDirectory targetDirectory;
    
    public WarZoneSimulatorDirectory(int targets, int drones, int minb, int maxb) {
        this.TOTAL_TARGETS = targets;
        this.TOTAL_DRONES = drones;

        adjMatrix = new double[TOTAL_TARGETS + 1][TOTAL_TARGETS + 1];

        for (int i = 0; i < TOTAL_TARGETS + 1; i++) {
            for (int j = i; j < TOTAL_TARGETS + 1; j++) {
                if (i == j) {
                    adjMatrix[i][j] = 0;
                } else {
                    adjMatrix[i][j] = adjMatrix[j][i] = r.nextInt(1000) + 1;
                }
            }
        }
        
        droneDirectory = new DroneDirectory();
        for(int i=0; i<drones; i++) {
            droneDirectory.addDrone(6+i);
        }
        
        targetDirectory = new TargetDirectory();
        for(int i=0; i<targets; i++) {
            int demand = generateCapacity(minb, maxb);
            targetDirectory.addTarget(demand);
        }
        
    }
    
    public Map<String, List<Integer>> findStrikeRoute(int[] optimalRoute) {
        Map<String, List<Integer>> hashMap = new HashMap<String, List<Integer>>();
        List<Integer> strikeRoute;
        int totalTrips = 0;
        int totalDistance = 0;
        List<Drone> drones = droneDirectory.getDrone();
        int totalDrones = drones.size();
        
        List<Target> targets = targetDirectory.getTarget();
        
        for (int i = 0; i < totalDrones; i++) {
            strikeRoute = new ArrayList<Integer>();
            strikeRoute.add(0);
            totalTrips = 0;
            totalDistance = 0;
            int avblCapacity = drones.get(i).getPayLoadCapacity();
            for (int j = 0; j < optimalRoute.length; j++) {
                int targetCapacity = targets.get(optimalRoute[j] - 1).getTargetPayload();
                if (avblCapacity - targetCapacity >= 0) {
                    strikeRoute.add(optimalRoute[j]);
                    totalDistance += adjMatrix[strikeRoute.get(strikeRoute.indexOf(optimalRoute[j]) - 1)][optimalRoute[j]];
                    avblCapacity -= targetCapacity;
                } else {
                    totalTrips++;
                    avblCapacity = drones.get(i).getPayLoadCapacity();
                    strikeRoute.add(0);
                    totalDistance += adjMatrix[optimalRoute[j - 1]][0];
                    j--;
                }
            }
            strikeRoute.add(0);
            totalTrips++;
            hashMap.put("Drone Capacity:" + drones.get(i).getPayLoadCapacity() + ",Trips:" + totalTrips + ",TotalDistance:" + totalDistance, strikeRoute);
        }
        return hashMap;

    }

    public void printDistanceMatrix() {
        System.out.print("\tStation\t");
        for (int k = 0; k < 10; k++) {
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
