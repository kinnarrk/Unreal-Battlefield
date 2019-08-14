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

    public Drone(int capacity) {
        this.name = "Drone-" + (++no);
        this.payLoadCapacity = capacity;
    }

    public int getPayLoadCapacity() {
        return payLoadCapacity;
    }

    public void setPayLoadCapacity(int payLoadCapacity) {
        this.payLoadCapacity = payLoadCapacity;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
