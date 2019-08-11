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
    
    
    public Drone(int capacity){
        this.payLoadCapacity=capacity;
    }
    @Override
	public String toString() {
		return "Drone Payload Capacity=" + payLoadCapacity ;
	}
}
