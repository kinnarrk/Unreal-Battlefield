/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WarZone;

import PSO.Helper;
import PSO.Particle;
import PSO.Swarm;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhaVYa
 */
public class WarZoneSimulator {

    private static final int TARGET = 10;
    private static final int TOTAL_DRONES = 5;
    private static final int MIN_TARGET_PAYLOAD = 1;
    private static final int MAX_TARGET_PAYLOAD = 5;
    private static final int TOTAL_PARTICLES = 30;
    private static final int TOTAL_ITERATIONS = 20;

    public static void main(String[] args) {
        WarZoneSimulatorDirectory directory = new WarZoneSimulatorDirectory(TARGET, TOTAL_DRONES, MIN_TARGET_PAYLOAD, MAX_TARGET_PAYLOAD);

        final Swarm swarm = new Swarm(TARGET);
        swarm.setMap(directory.getAdjMatrix());
        
        System.out.println("----------------------------------------");
        System.out.println("Drone Details");
        System.out.println("----------------------------------------");
        for(Drone d: directory.getDroneDirectory().getDrone()) {
            System.out.println(d+" Payload Capacity:"+d.getPayLoadCapacity());
        }
        
        System.out.println("----------------------------------------");
        System.out.println("Target Details");
        System.out.println("----------------------------------------");
        for(Target t: directory.getTargetDirectory().getTarget()) {
            System.out.println(t);
        }
        System.out.println("\n-------------------------------------------------");
        System.out.println("Particle Swarm Optimization for Battlefield");
        System.out.println("-------------------------------------------------\n");
        int[] possiblePath = new int[TARGET];

        for (int i = 0; i < TARGET; i++) {
            possiblePath[i] = i + 1;
        }

        
        final Thread threadArr[] = new Thread[TOTAL_PARTICLES];
        for (int i = 0; i < TOTAL_PARTICLES; i++) {
            Helper.shuffleArray(possiblePath);
            final Particle p = swarm.addParticle(possiblePath);
            final int temp = i;
            threadArr[i] = new Thread("ParticleThread" + i) {
                @Override
                public void run() {
                    try {                        
                        if(temp>0){
//                            joinT(threadArr[temp-1]);
                        }
                        double fitnessValue = swarm.getFitnessValue(p.getPath());
                        p.setFitnessValue(fitnessValue);
//                        System.out.println("get f 1st done");
                        double bestfitnessValue = swarm.getFitnessValue(p.getpBest());
                        p.setpBestValue(bestfitnessValue);
//                        System.out.println("get f 2nd done");
                    } catch (Exception e) {}
                }
            };
            threadArr[i].start();
            
//            double fitnessValue = swarm.getFitnessValue(p.getPath());
//            p.setFitnessValue(fitnessValue);
//            System.out.println("get f 1st done");
//            double bestfitnessValue = swarm.getFitnessValue(p.getpBest());
//            p.setpBestValue(bestfitnessValue);
//            System.out.println("get f 2nd done");
            
        }
//        joinT(threadArr[TOTAL_PARTICLES-1]);
        
        for (Thread t : threadArr) {
            joinT(t);
        }
        
        swarm.findGlobalBest();

        final Map<String, Map<Double, Double>> particleIterations = new HashMap<String, Map<Double, Double>>();

        //print iteration 0 results
        System.out.print("|  Iteration(n)\t|\t");
        for (int i = 0; i < swarm.getParticles().size(); i++) {
            System.out.print("p(" + (i + 1) + ")\tpBest(" + (i + 1) + ")\t|\t");
        }

        System.out.println("p(gBest)\t|");
        System.out.print("----------------|-------");
        for (int i = 0; i < swarm.getParticles().size(); i++) {
            System.out.print("------------------------|-------");
        }
        System.out.print("----------------|\n");
        swarm.printIterationResults(0, particleIterations);

        final Thread thread[] = new Thread[TOTAL_ITERATIONS-1];
        //Optimize the solution and return the best solution after the iterations terminate
        for (int t = 1; t < TOTAL_ITERATIONS; t++) {
            final int tmp = t;
//            thread[t - 1] = new Thread("Thread" + t) {
//                @Override
//                public void run() {
//                    if (tmp > 1) {
//                        try {
//                        thread[tmp - 2].join();
//
//                            this.sleep(tmp * 250);
//                        } catch (InterruptedException e) {
//                        }
//                    }
                    swarm.optimizeSolutions();
                    swarm.printIterationResults(tmp, particleIterations);

//                }
//            };
//            thread[t - 1].start();
        }
//        for (Thread t : thread) {
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//            }
//        }

        System.out.println("\n\nStrike Path");
        System.out.println("====================================================");
        
        int[] decodedStrikeRoute = swarm.decodeStrikeRoute();
        System.out.println("Optimal Strike Route : " + Arrays.toString(decodedStrikeRoute));

        System.out.println("\n\nDrone Simulation with different payload");
        System.out.println("====================================================");
        
        Map<String, List<Integer>> strikeRoute = directory.findStrikeRoute(decodedStrikeRoute);

        for (Map.Entry<String, List<Integer>> entry : strikeRoute.entrySet()) {
            System.out.println(entry.getKey() + " \nStrike Route: " + entry.getValue());
            System.out.println("-------------------------------------------------------");
        }
    }
    
    public static void joinT(Thread t){
        try {
            t.join();
        } catch (InterruptedException e) {}
    }
}