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
public class AirBase {
    
    int payLoadAirBaseCapacity;
    private String airBaseName;
    private static int number;

    AirBase(int capacity) {
        
        this.airBaseName = "Air Base-"+(++number);
        this.payLoadAirBaseCapacity=capacity;
    }

    public int getPayLoadAirBaseCapacity() {
        return payLoadAirBaseCapacity;
    }

    public void setPayLoadAirBaseCapacity(int payLoadAirBaseCapacity) {
        this.payLoadAirBaseCapacity = payLoadAirBaseCapacity;
    }
    
    @Override
	public String toString() {
		return this.airBaseName ;
	}
    
}
