/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PSO;

import java.util.Random;

/**
 *
 * @author 19712
 */
public class Particle {

    double[] solution;
    double fitnessValue;

    double[] particleBest;
    double particleBestValue;
    double[] particleBestVelocity;
    double[] particleVelocity;

    public Particle() {
        
    }
    
    public Particle(int[] x) {

    }
    
    Random random = new Random();
    
    public void setRandomVelcities(int x) {
		this.particleVelocity = new double[x];
		for (int i = 0; i < x; i++) {
			this.particleVelocity[i] = getRandomVelocity(x);
		}
	}

	private double getRandomVelocity(int MAX) {
		int MIN = 0;
		return (random.nextDouble() * (MAX - MIN)) + MIN;
	}

    public double[] getSolution() {
        return solution;
    }

    public void setSolution(double[] solution) {
        this.solution = solution;
    }

    public double getFitnessValue() {
        return fitnessValue;
    }

    public void setFitnessValue(double fitnessValue) {
        this.fitnessValue = fitnessValue;
    }

    public double[] getpBest() {
        return particleBest;
    }

    public void setpBest(double[] pBest) {
        this.particleBest = pBest;
    }

    public double getpBestValue() {
        return particleBestValue;
    }

    public void setpBestValue(double pBestValue) {
        this.particleBestValue = pBestValue;
    }

    public double[] getpBestVelocity() {
        return particleBestVelocity;
    }

    public void setpBestVelocity(double[] pBestVelocity) {
        this.particleBestVelocity = pBestVelocity;
    }

    public double[] getpVelocity() {
        return particleVelocity;
    }

    public void setpVelocity(double[] pVelocity) {
        this.particleVelocity = pVelocity;
    }

}
