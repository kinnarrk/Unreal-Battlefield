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
    private AirBaseDirectory airbaseDirectory;
    private int airbase;

    public WarZoneSimulatorDirectory(int targets, int drones, int minb, int maxb, int airbase) {
        this.TOTAL_TARGETS = targets;
        this.TOTAL_DRONES = drones;

        this.airbase = airbase;
        airbaseDirectory = new AirBaseDirectory();
        for (int i = 0; i < airbase; i++) {
            airbaseDirectory.addAirBase();
        }

        droneDirectory = new DroneDirectory();
        for (int i = 0; i < drones; i++) {
            droneDirectory.addDrone(6 + i);
        }

        targetDirectory = new TargetDirectory();
        for (int i = 0; i < targets; i++) {
            int demand = generateCapacity(minb, maxb);
            targetDirectory.addTarget(demand);
        }

        int total = TOTAL_TARGETS + airbase;
//        adjMatrix = new double[TOTAL_TARGETS + 1][TOTAL_TARGETS + 1];
        adjMatrix = new double[total][total];

        // Airbase to Airbase distance
        for (int i = 0; i < airbase; i++) {
            AirBase airbase1 = airbaseDirectory.getAirBaseDirectory().get(i);
            for (int j = 0; j < airbase; j++) {
                AirBase airbase2 = airbaseDirectory.getAirBaseDirectory().get(j);
                if (i == j) {
                    adjMatrix[i][j] = 0;
                } else {
                    adjMatrix[i][j] = adjMatrix[j][i] = getDistance(airbase1.getPosition(), airbase2.getPosition());
                }
            }
        }

        // Target to Target distance
        for (int i = airbase; i < TOTAL_TARGETS + airbase; i++) {
            Target target1 = targetDirectory.getTargetDirectory().get(i - airbase);
            for (int j = airbase; j < TOTAL_TARGETS + airbase; j++) {
                Target target2 = targetDirectory.getTargetDirectory().get(j - airbase);
                if (i == j) {
                    adjMatrix[i][j] = 0;
                } else {
                    adjMatrix[i][j] = adjMatrix[j][i] = getDistance(target1.getPosition(), target2.getPosition());
                }
            }
        }

        // Airbase to Target distance
        for (int i = 0; i < airbase; i++) {
            AirBase airbase1 = airbaseDirectory.getAirBaseDirectory().get(i);
            for (int j = airbase; j < total; j++) {
                Target target1 = targetDirectory.getTargetDirectory().get(j - airbase);
                adjMatrix[i][j] = adjMatrix[j][i] = getDistance(airbase1.getPosition(), target1.getPosition());
            }
        }
        printDistanceMatrix();

//        for (int i = 0; i < TOTAL_TARGETS + 1; i++) {
//            for (int j = i; j < TOTAL_TARGETS + 1; j++) {
//                if (i == j) {
//                    adjMatrix[i][j] = 0;
//                } else {
//                    adjMatrix[i][j] = adjMatrix[j][i] = r.nextInt(100) + 1;
//                }
//            }
//        }
    }

    public List<List<String>> getInitialPoints() {
        List<List<String>> mainPoints = new ArrayList<List<String>>();
        List<String> subPoints;
        
        int temp = 0;
        for(AirBase a: airbaseDirectory.getAirBaseDirectory()) {
            subPoints = new ArrayList<String>();
            Position p = a.getPosition();
            subPoints.add("AB"+(temp++));
            subPoints.add(String.valueOf((int)p.getLat()));
            subPoints.add(String.valueOf((int)p.getLng()));
            subPoints.add("AB");
            mainPoints.add(subPoints);
        }
        
        temp = 0;
        for(Target t: targetDirectory.getTargetDirectory()) {
            subPoints = new ArrayList<String>();
            Position p = t.getPosition();
            subPoints.add("T"+(++temp));
            subPoints.add(String.valueOf((int)p.getLat()));
            subPoints.add(String.valueOf((int)p.getLng()));
            subPoints.add("T");
            mainPoints.add(subPoints);
        }
        
        return mainPoints;
    }
    
//    public Map<String, List<Integer>> findStrikeRoute(int[] optimalRoute) {
//        Map<String, List<Integer>> hashMap = new HashMap<String, List<Integer>>();
//        List<Integer> strikeRoute;
//        int totalTrips = 0;
//        int totalDistance = 0;
//        List<Drone> drones = droneDirectory.getDrone();
//        int totalDrones = drones.size();
//        
//        List<Target> targets = targetDirectory.getTarget();
//        
//        for (int i = 0; i < totalDrones; i++) {
//            strikeRoute = new ArrayList<Integer>();
//            strikeRoute.add(0);
//            totalTrips = 0;
//            totalDistance = 0;
//            int avblCapacity = drones.get(i).getPayLoadCapacity();
//            for (int j = 0; j < optimalRoute.length; j++) {
//                int targetCapacity = targets.get(optimalRoute[j] - 1).getTargetPayload();
//                if (avblCapacity - targetCapacity >= 0) {
//                    strikeRoute.add(optimalRoute[j]);
//                    totalDistance += adjMatrix[strikeRoute.get(strikeRoute.indexOf(optimalRoute[j]) - 1)][optimalRoute[j]];
//                    avblCapacity -= targetCapacity;
//                } else {
//                    totalTrips++;
//                    avblCapacity = drones.get(i).getPayLoadCapacity();
//                    strikeRoute.add(0);
//                    totalDistance += adjMatrix[optimalRoute[j - 1]][0];
//                    j--;
//                }
//            }
//            strikeRoute.add(0);
//            totalTrips++;
//            hashMap.put(drones.get(i)+" \nPayload:" + drones.get(i).getPayLoadCapacity() + "\nTrips:" + totalTrips + "\nTotalDistance: " + totalDistance +" miles", strikeRoute);
//        }
//        return hashMap;
//
//    }
   
     public List<List<String>> decodeStrikeRouteWithParams(int[] route) {
        List<List<String>> mainRoute = new ArrayList<List<String>>();
        List<String> strikeRoute;
        List<Target> targetDir = targetDirectory.getTargetDirectory();
        for(int i=0; i<route.length; i++) {
            strikeRoute = new ArrayList<String>();
            Target t = targetDir.get(route[i]-1);
            Position p = t.getPosition();
            strikeRoute.add("T"+route[i]);
            strikeRoute.add(String.valueOf(p.getLat()));
            strikeRoute.add(String.valueOf(p.getLng()));
            mainRoute.add(strikeRoute);
        }
        return mainRoute;
    }
     
    public Map<String, List<List<String>>> findStrikeRoute(int[] optimalRoute) {
        Map<String, List<List<String>>> hashMap = new HashMap<String, List<List<String>>>();
        List<List<String>> parentRoute;
        List<String> strikeRoute;
        int totalTrips = 0;
        int totalDistance = 0;
        List<Drone> drones = droneDirectory.getDrone();
        int totalDrones = drones.size();

        List<Target> targets = targetDirectory.getTarget();
        List<AirBase> airbases = airbaseDirectory.getAirBaseDirectory();

        for (int i = 0; i < totalDrones; i++) {
            int avblCapacity = drones.get(i).getPayLoadCapacity();
            double avblFuel = drones.get(i).getFuel();
            parentRoute = new ArrayList<List<String>>();
            strikeRoute = new ArrayList<String>();
            AirBase airbase = airbases.get(0);
            Position pos = airbase.getPosition();
//            strikeRoute.add("AB0,"+pos.getLat()+","+pos.getLng()+","+avblCapacity+",0");
            strikeRoute.add("AB0");
            strikeRoute.add(String.valueOf((int)pos.getLat()));
            strikeRoute.add(String.valueOf((int)pos.getLng()));
            strikeRoute.add(String.valueOf(avblCapacity));
            strikeRoute.add("0");
            parentRoute.add(strikeRoute);
            int from = 0;
            totalTrips = 0;
            totalDistance = 0;
            for (int j = 0; j < optimalRoute.length; j++) {
                int targetCapacity = targets.get(optimalRoute[j] - 1).getTargetPayload();
                int to = optimalRoute[j] + 4;
                if (avblCapacity - targetCapacity >= 0) {
                    Target t1 = targets.get(optimalRoute[j] - 1);
                    Position pos1 = t1.getPosition();
                    double distance = adjMatrix[from][to];
                    avblFuel -= distance/drones.get(i).getAvg();
                    totalDistance += distance;
                    from = to;
                    avblCapacity -= targetCapacity;
                    strikeRoute = new ArrayList<String>();
                    strikeRoute.add("T" + String.valueOf(optimalRoute[j]));
                    strikeRoute.add(String.valueOf((int)pos1.getLat()));
                    strikeRoute.add(String.valueOf((int)pos1.getLng()));
                    strikeRoute.add(String.valueOf(avblCapacity));
                    strikeRoute.add("1");
                    parentRoute.add(strikeRoute);
                    //strikeRoute.add("T"+String.valueOf(optimalRoute[j])+","+pos1.getLat()+","+pos1.getLng()+","+avblCapacity+",1");
                } else {
                    totalTrips++;
                    avblCapacity = drones.get(i).getPayLoadCapacity();
                    avblFuel = drones.get(i).getFuel();
                    int minDistance = getMinDistance(from);
                    AirBase airbase1 = airbases.get(minDistance);
                    Position pos1 = airbase1.getPosition();
                    strikeRoute = new ArrayList<String>();
                    strikeRoute.add("AB" + minDistance);
                    strikeRoute.add(String.valueOf((int)pos1.getLat()));
                    strikeRoute.add(String.valueOf((int)pos1.getLng()));
                    strikeRoute.add(String.valueOf(avblCapacity));
                    strikeRoute.add("0");
                    parentRoute.add(strikeRoute);
//                    strikeRoute.add("AB"+minDistance+","+pos1.getLat()+","+pos1.getLng()+","+avblCapacity+",0");
                    totalDistance += adjMatrix[from][minDistance];
                    from = minDistance;
                    j--;
                }
            }
            strikeRoute = new ArrayList<String>();
            strikeRoute.add("AB0");
            strikeRoute.add(String.valueOf((int)pos.getLat()));
            strikeRoute.add(String.valueOf((int)pos.getLng()));
            strikeRoute.add(String.valueOf((int)avblCapacity));
            strikeRoute.add("0");
            parentRoute.add(strikeRoute);
//            strikeRoute.add("AB0,"+pos.getLat()+","+pos.getLng()+","+avblCapacity+",0");
            totalTrips++;
            hashMap.put(drones.get(i) + " \nPayload:" + drones.get(i).getPayLoadCapacity() + "\nTrips:" + totalTrips + "\nTotalDistance: " + totalDistance + " miles", parentRoute);
        }
        return hashMap;

    }

    public int getMinDistance(int index) {
        int minValue = (int) adjMatrix[index][0];
        int min = 0;
        for (int i = 0; i < 5; i++) {
            if (minValue < (int) adjMatrix[index][i]) {
                min = i;
            }
            minValue = (int) adjMatrix[index][i];
        }
        return min;
    }

    public static double getDistance(Position p1, Position p2) {
        double x = p1.getLat() - p2.getLat();
        double y = p1.getLng() - p2.getLng();
        double distance = Math.sqrt(x * x + y * y);
        return Math.round(distance*100.0)/100.0;
    }

    public void printDistanceMatrix() {
        System.out.print("Station\t");
        for (int k = 0; k < adjMatrix.length; k++) {
            System.out.print((k < airbase ? "AB" + k : "T" + (k - airbase + 1)) + "\t");
        }
        System.out.println();

        for (int i = 0; i < adjMatrix.length; i++) {
            System.out.print((i < airbase ? "AB" + i : "T" + (i - airbase + 1)) + "\t");
            for (int j = 0; j < adjMatrix.length; j++) {
                System.out.print(adjMatrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

//    public void printDistanceMatrix() {
//        System.out.print("Station\t");
//        for (int k = 0; k < adjMatrix.length; k++) {
//            System.out.print((k == 0? "AB":"T" + (k)) + "\t");
//        }
//        System.out.println();
//
//        for (int i = 0; i < adjMatrix.length; i++) {
//            System.out.print((i == 0 ? "AB" : "T" + i) + "\t");
//            for (int j = 0; j < adjMatrix.length; j++) {
//                System.out.print(adjMatrix[i][j] + "\t");
//            }
//            System.out.println();
//        }
//    }
    int generateCapacity(int min, int max) {
        return r.nextInt(max - min) + min;
    }

    public double[][] getAdjMatrix() {
        return adjMatrix;
    }

    public DroneDirectory getDroneDirectory() {
        return droneDirectory;
    }

    public void setDroneDirectory(DroneDirectory droneDirectory) {
        this.droneDirectory = droneDirectory;
    }

    public TargetDirectory getTargetDirectory() {
        return targetDirectory;
    }

    public void setTargetDirectory(TargetDirectory targetDirectory) {
        this.targetDirectory = targetDirectory;
    }

}
