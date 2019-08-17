/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WarZone;

import Animation.AnimationStorage;
import Animation.TimerAnimationUtility;
import Graph.Graph;
import PSO.Helper;
import PSO.Particle;
import PSO.Swarm;
import java.awt.EventQueue;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import static psa_project.PSA_ProjectApp.logger;

/**
 *
 * @author bhaVYa
 */
public class WarZoneSimulator {

    private static final int TARGET = 10;
    private static final int TOTAL_DRONES = 5;
    private static final int MIN_TARGET_PAYLOAD = 1;
    private static final int MAX_TARGET_PAYLOAD = 5;
    private static final int TOTAL_PARTICLES = 50;
    private static final int TOTAL_ITERATIONS = 40;
    private static final int TOTAL_AIRBASE = 5;

    public void main(JPanel mainPanel, JTextArea jtaOp, JPanel jpChart) {
        WarZoneSimulatorDirectory directory = new WarZoneSimulatorDirectory(TARGET, TOTAL_DRONES, MIN_TARGET_PAYLOAD, MAX_TARGET_PAYLOAD, TOTAL_AIRBASE, jtaOp);

        final Swarm swarm = new Swarm(TARGET, TOTAL_PARTICLES, TOTAL_ITERATIONS, jtaOp);
        swarm.setMap(directory.getAdjMatrix());

        List<List<String>> initialPoints = directory.getInitialPoints();

        System.out.println("----------------------------------------");
        jtaOp.append("----------------------------------------\n");
        System.out.println("Initial Points");
        jtaOp.append("Initial Points\n");
        System.out.println(initialPoints);
        jtaOp.append(initialPoints + "\n");
        System.out.println("----------------------------------------");
        jtaOp.append("----------------------------------------\n");
        System.out.println("Drone Details");
        jtaOp.append("Drone Details\n");
        System.out.println("----------------------------------------");
        jtaOp.append("----------------------------------------\n");
        for (Drone d : directory.getDroneDirectory().getDrone()) {
            System.out.println(d + " Payload Capacity:" + d.getPayLoadCapacity());
            jtaOp.append(d + " Payload Capacity:" + d.getPayLoadCapacity() + "\n");
        }

        System.out.println("----------------------------------------");
        jtaOp.append("----------------------------------------\n");
        System.out.println("Target Details");
        jtaOp.append("Target Details\n");
        System.out.println("----------------------------------------");
        jtaOp.append("----------------------------------------\n");
        for (Target t : directory.getTargetDirectory().getTarget()) {
            System.out.println(t);
            jtaOp.append(t + "\n");
        }
        System.out.println("\n-------------------------------------------------");
        jtaOp.append("\n-------------------------------------------------\n");
        System.out.println("Particle Swarm Optimization for Battlefield");
        jtaOp.append("Particle Swarm Optimization for Battlefield\n");
        System.out.println("-------------------------------------------------\n");
        jtaOp.append("-------------------------------------------------\n\n");
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
                        double fitnessValue = swarm.getFitnessValue(p.getPath());
                        p.setFitnessValue(fitnessValue);
                        double bestfitnessValue = swarm.getFitnessValue(p.getpBest());
                        p.setpBestValue(bestfitnessValue);
                    } catch (Exception e) {
                        logger.error("Particle Thread exception", e);
                    }
                }
            };
            threadArr[i].start();
        }

        for (Thread t : threadArr) {
            joinT(t);
        }
        
        logger.debug("Thread join complete after execution of pBest");

        swarm.findGlobalBest();

        logger.debug("Global best process complete");
        
        final Map<String, Map<Double, Double>> particleIterations = new HashMap<String, Map<Double, Double>>();

        // Print result for iterations
        System.out.print("|  Iteration(n)\t|\t");
        jtaOp.append("|  Iteration(n)\t|\t");
        for (int i = 0; i < swarm.getParticles().size(); i++) {
            System.out.print("p(" + (i + 1) + ")\tpBest(" + (i + 1) + ")\t|\t");
            jtaOp.append("p(" + (i + 1) + ")\tpBest(" + (i + 1) + ")\t|\t");
        }

        System.out.println("p(gBest)\t|");
        jtaOp.append("p(gBest)\t|\n");
        System.out.print("----------------|-------");
        jtaOp.append("----------------|-------");
        for (int i = 0; i < swarm.getParticles().size(); i++) {
            System.out.print("------------------------|-------");
            jtaOp.append("------------------------|-------");
        }
        System.out.print("----------------|\n");
        jtaOp.append("----------------|\n");
        swarm.printIteration(0, particleIterations);

        final Thread thread[] = new Thread[TOTAL_ITERATIONS - 1];
        //Optimize the solution and return the best solution after the iterations terminate
        for (int t = 1; t < TOTAL_ITERATIONS; t++) {
            final int tmp = t;
            swarm.updateParticleValue();
            swarm.printIteration(tmp, particleIterations);

        }
        
        logger.debug("Iterations complete");

        // Getting array for particle progess to show in graph
        double[][] graphArray = swarm.getGraphArray();
        Graph graph = new Graph(graphArray, jpChart);
        
        logger.debug("Graph display process complete");
        
        System.out.println("\n\nStrike Path");
        jtaOp.append("\n\nStrike Path\n");
        System.out.println("====================================================");
        jtaOp.append("====================================================\n");

        int[] decodedStrikeRoute = swarm.decodeStrikeRoute();
        System.out.println("Optimal Strike Route : " + Arrays.toString(decodedStrikeRoute));
        jtaOp.append("Optimal Strike Route : " + Arrays.toString(decodedStrikeRoute) + "\n");

        List<List<String>> strikeWithParams = directory.decodeStrikeRouteWithParams(decodedStrikeRoute);

        System.out.println(strikeWithParams);
        jtaOp.append(strikeWithParams + "\n");
        System.out.println("\n\nDrone Simulation with different payload");
        jtaOp.append("\n\nDrone Simulation with different payload\n");
        System.out.println("====================================================");
        jtaOp.append("====================================================\n");

        Map<String, List<List<String>>> strikeRoute = directory.findStrikeRoute(decodedStrikeRoute);

        for (Map.Entry<String, List<List<String>>> entry : strikeRoute.entrySet()) {
            System.out.println(entry.getKey() + " \nStrike Route: " + entry.getValue());
            jtaOp.append(entry.getKey() + " \nStrike Route: " + entry.getValue() + "\n");
            System.out.println("-------------------------------------------------------");
            jtaOp.append("-------------------------------------------------------\n");
        }

        logger.debug("Unreal battlefield animation start");
        //For doing animation
        AnimationStorage storage = new AnimationStorage();
        storage.setHashMap(strikeRoute);
        EventQueue.invokeLater(() -> {
            for (List<List<String>> parentRoute : storage.getHashMap().values()) {
                JFrame ex = new TimerAnimationUtility(parentRoute, directory.getInitialPoints());
                ex.setVisible(true);
                break;
            }
        });
        logger.debug("Unreal battlefield animation end");
        
    }

    public static void joinT(Thread t) {
        try {
            t.join();
        } catch (InterruptedException e) {
            logger.error("Thread interrupted exception in joining thread of particles", e);
        }
    }
}
