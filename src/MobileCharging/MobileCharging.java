/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MobileCharging;

import PSO.Optimizer;
import PSO.Particle;
import PSO.Swarm;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author bhaVYa
 */
public class MobileCharging {

    private static final int STOP = 50;
    private static final int TOTAL_CARS = 5;
    private static final int MIN_CAR_BATTERY = 15;
    private static final int MAX_CAR_BATTERY = 50;
    private static final int TOTAL_PARTICLES = 30;
    private static final int TOTAL_ITERATIONS = 40;

    public static void main(String[] args) {
        MobileChargingDirectory directory = new MobileChargingDirectory(STOP, TOTAL_CARS, MIN_CAR_BATTERY, MAX_CAR_BATTERY);

        Swarm swarm = new Swarm(STOP);
        swarm.setMap(directory.getAdjMatrix());
        
        int[] possiblePath = new int[STOP];

        for (int i = 0; i < STOP; i++) {
            possiblePath[i] = i + 1;
        }
        
        for (int i = 0; i < TOTAL_PARTICLES; i++) {
            Optimizer.randomArray(possiblePath);
            Particle p = swarm.addParticle(possiblePath);
            
            double fitnessValue = swarm.getFitnessValue(p.getPath());
            p.setFitnessValue(fitnessValue);
            System.out.println("get f 1st done");
            double bestfitnessValue = swarm.getFitnessValue(p.getpBest());
            p.setpBestValue(bestfitnessValue);
            System.out.println("get f 2nd done");
        }
        
        swarm.findGlobalBest();
        
        Map<String, Map<Double, Double>> particleIterations = new HashMap<String, Map<Double, Double>>();
        
         //print iteration 0 results
        System.out.print("Iteration\t");
        for (int i = 0; i < swarm.getParticles().size(); i++) {
            System.out.print("f(x:" + (i + 1) + ") f(pBest:" + (i + 1) + ")\t");
        }

        System.out.println("f(gBest)");
        swarm.printIterationResults(0, particleIterations);

        //Optimize the solution and return the best solution after the iterations terminate
        for (int t = 1; t <= TOTAL_ITERATIONS; t++) {
            System.out.println("\nfor loop iteration: " + t);
            swarm.optimizeSolutions();
            swarm.printIterationResults(t, particleIterations);
        }
    }
}
