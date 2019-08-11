/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WarZone;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 19712
 */
public class DroneDirectory {
    private List<Drone> droneDirectory;
    
    public DroneDirectory() {
        droneDirectory = new ArrayList<Drone>();
    }
    
    public Drone addDrone(int cap) {
        Drone drone = new Drone(cap);
        droneDirectory.add(drone);
        return drone;
    }
    
    public List<Drone> getDrone() {
        return droneDirectory;
    }
}
