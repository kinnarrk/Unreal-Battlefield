/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PSO;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bhaVYa
 */
public class Swarm {
    private List<Particle> swarm;
    private double[] gBestPath;
    private double gBestDistance;
    private double[] gBestVelocity;
    
    public Swarm() {
        swarm = new ArrayList<Particle>();
    }
    
    public Particle addParticle() {
        Particle particle = new Particle();
        swarm.add(particle);
        return particle;
    }
    
    public List<Particle> getParticles() {
        return swarm;
    }
}
