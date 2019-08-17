/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WarZone;

/**
 *
 * @author 19712
 */
public class Drone {

    int payLoadCapacity;
    private String name;
    private static int no;
    private double fuel;
    private int avg; 
            
    public Drone(int capacity) {
        this.name = "Drone-" + (++no);
        this.payLoadCapacity = capacity;

        this.fuel = 100;
        this.avg = 20;
    }

    public int getPayLoadCapacity() {
        return payLoadCapacity;
    }

    public void setPayLoadCapacity(int payLoadCapacity) {
        this.payLoadCapacity = payLoadCapacity;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public int getAvg() {
        return avg;
    }

    public void setAvg(int avg) {
        this.avg = avg;
    }

    
    
    @Override
    public String toString() {
        return this.name;
    }
}
