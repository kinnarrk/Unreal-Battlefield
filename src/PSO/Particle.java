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

    double[] path;
    double fitnessValue;

    double[] particleBest;
    double particleBestValue;
    double[] particleBestVelocity;
    double[] particleVelocity;
    Random r = new Random();

    public Particle(int[] path) {
        this.path = Optimizer.copyFromRandomArray(path);
        this.particleBest = this.path;
        setRandomVelcities(path.length);
        this.particleBestVelocity = particleVelocity;
    }

    public void setRandomVelcities(int val) {
        this.particleVelocity = new double[val];
        for (int i = 0; i < val; i++) {
            this.particleVelocity[i] = getRandomVelocity(val);
        }
    }

    private double getRandomVelocity(int maximum) {
        int minimum = 0;
        return (r.nextDouble() * (maximum - minimum)) + minimum;
    }

    public double[] getPath() {
        return path;
    }

    public void setPath(double[] solution) {
        this.path = solution;
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
