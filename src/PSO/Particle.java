/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PSO;

/**
 *
 * @author 19712
 */
public class Particle {

    double[] solution;
    double fitnessValue;

    double[] pBest;
    double pBestValue;
    double[] pBestVelocity;
    double[] pVelocity;

    public Particle(int[] x) {

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
        return pBest;
    }

    public void setpBest(double[] pBest) {
        this.pBest = pBest;
    }

    public double getpBestValue() {
        return pBestValue;
    }

    public void setpBestValue(double pBestValue) {
        this.pBestValue = pBestValue;
    }

    public double[] getpBestVelocity() {
        return pBestVelocity;
    }

    public void setpBestVelocity(double[] pBestVelocity) {
        this.pBestVelocity = pBestVelocity;
    }

    public double[] getpVelocity() {
        return pVelocity;
    }

    public void setpVelocity(double[] pVelocity) {
        this.pVelocity = pVelocity;
    }

}
