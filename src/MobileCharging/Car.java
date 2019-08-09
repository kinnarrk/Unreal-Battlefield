/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MobileCharging;

/**
 *
 * @author 19712
 */
public class Car {
    
    int chargingCapacity;
    
    
    public Car(int capacity){
        this.chargingCapacity=capacity;
    }
    @Override
	public String toString() {
		return "Car Chargig Capacity=" + chargingCapacity ;
	}
}
