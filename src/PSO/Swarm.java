/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PSO;

import WarZone.WarZoneSimulatorDirectory;
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
public class Swarm {

    private List<Particle> swarm;
    private double[] gBestPath;
    private double gBestDistance;
    private double gValue;
    private double[] gBestVelocity;
    private double[][] map;
    private static final double INERTIA = 0.659;
    private static final double CONGNITIVE = 0.213;
    private static final double SOCIAL = 0.247;
    private Random r = new Random();
    
    public Swarm(int targets) {
        swarm = new ArrayList<Particle>();

        gBestPath = new double[targets];
        gBestVelocity = new double[targets];
        gValue = Double.MAX_VALUE;
    }

    public Particle addParticle(int[] path) {
        Particle particle = new Particle(path);
        swarm.add(particle);
        return particle;
    }

    public List<Particle> getParticles() {
        return swarm;
    }

    public double getFitnessValue(double[] sol) {

        int prevTarget = 0;
        double fitnessSum = 0;
//        for(double d: sol){
//            System.out.print(d+" ");
//        }
//        System.out.println("");
        for (int i = 0; i < sol.length; i++) {
            int v = (int) Math.round(sol[i]);
//            System.out.println("prevTarget:" + prevTarget + ": v: " + v);
            fitnessSum += map[prevTarget][v];
            prevTarget = v;
        }

        fitnessSum += map[prevTarget][0];

        return fitnessSum;
    }

    public void findGlobalBest() {
        for (Particle p : this.getParticles()) {
            double pBest = p.getFitnessValue();
            if (pBest < gValue) {
                gValue = pBest;
                gBestPath = p.getpBest();
                gBestVelocity = p.getpBestVelocity();
            }
        }
    }

    public void printIterationResults(int t, Map<String, Map<Double, Double>> particleProgress) {
        System.out.print(t + " \t\t");
        int pNo = 1;
        for (Particle p : this.getParticles()) {
            if (particleProgress.get("p" + pNo) == null) {
                particleProgress.put("p" + pNo, new HashMap<Double, Double>());
            }

            particleProgress.get("p" + pNo).put((double) t, p.getpBestValue());
            System.out.print(p.getFitnessValue() + "\t" + p.getpBestValue() + "\t\t");
            pNo++;
        }
        System.out.println(gValue);
    }

    public void optimizeSolutions() {

        for (Particle p : swarm) {

            // find the new velocity
            updateVelocity(p);

            // find new solution
            updateSolution(p);

            // update the fitness value of the particles
            double fitnessValue = getFitnessValue(p.getPath());
            p.setFitnessValue(fitnessValue);

            double path[] = Optimizer.copyFromArray(p.getPath());
            double velocity[] = Optimizer.copyFromArray(p.getpBestVelocity());
            
            // update pBest of the particle
            if (p.getFitnessValue() < p.getpBestValue()) {
                p.setpBest(path);
                p.setpBestValue(p.getFitnessValue());
                p.setpBestVelocity(velocity);
            }

        }

        //update the gBest after this one iteration
        findGlobalBest();

    }

    private void updateVelocity(Particle p) {

        double[] newVelocity = new double[p.getpVelocity().length];
        double r1 = r.nextDouble();
        double r2 = r.nextDouble();
        
        for (int i = 0; i < newVelocity.length; i++) {
            newVelocity[i] = INERTIA * p.getpVelocity()[i] + (CONGNITIVE * r1 * (p.getpBest()[i] - p.getPath()[i])) + (SOCIAL * r2 * (gBestPath[i] - p.getPath()[i]));
//            if(newVelocity[i]<0)
//                System.out.println("New velo < 0: "+ newVelocity[i]);
        }

        p.setpVelocity(newVelocity);        

    }

    private void updateSolution(Particle p) {
        double path[] = new double[p.getPath().length];
        
        for (int i = 0; i < p.getPath().length; i++) {
            double value = p.getPath()[i] + p.getpVelocity()[i];
            path[i] = value > p.getPath().length ? p.getPath().length : value;   
//            p.xSolution[i] = p.xSolution[i] + p.pVelocity[i] > p.xSolution.length ? p.xSolution.length : p.xSolution[i] + p.pVelocity[i];
        }
        
        p.setPath(path);

    }
    
    
    
    public int[] decodeStrikeRoute(){
		
		System.out.println("gFitnessValue=" + gValue);
		System.out.println("gBest="+Arrays.toString(gBestPath));		
		
		Map<Double, List<Integer>> indicies = new HashMap<>();
		
		for(int i=0; i<gBestPath.length ; i++){
			if(indicies.get(gBestPath[i])== null)
				indicies.put(gBestPath[i], new ArrayList<Integer>());
			
			indicies.get(gBestPath[i]).add(i);
		}
		
		Arrays.sort(gBestPath);
		
		int[] optimalRoute = new int[gBestPath.length];
		
		for(int i=0; i<optimalRoute.length;i++){
			if(indicies.get(gBestPath[i]).size() > 1){
				// find the lowest velocity and add that first				
				int ii = i;
				for(int k=0; k<indicies.get(gBestPath[ii]).size(); k++){					
					optimalRoute[i] = indicies.get(gBestPath[ii]).get(k) + 1;
					i++;
				}				
				
			}else			
			 optimalRoute[i] = indicies.get(gBestPath[i]).get(0) + 1;
		}
		
                double distance[] = Optimizer.copyFromRandomArray(optimalRoute);
                System.out.println("Total Distance: "+getFitnessValue(distance));
		return optimalRoute;
	}
	
	public void printSwarmDetails(){
		
		
		System.out.println("No of Particles : " + getParticles().size());
		
		
		System.out.println("Particle Details : ");
		for(Particle p: getParticles())
			System.out.println(p);
		
		System.out.println("Global   [gBest="+Arrays.toString(gBestPath)+", gFitnessValue=" + gBestDistance+"]");
				
	}
    
    
    
    
    
    

    public double[][] getMap() {
        return map;
    }

    public void setMap(double[][] map) {
        this.map = map;
    }

}
