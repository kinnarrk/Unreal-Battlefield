/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WarZone;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author 19712
 */
public class Target {
    int targetDemand;
   
    public Target(int demand){
        this.targetDemand=demand;
    }
    @Override
	public String toString() {
		return "Target Demand=" + targetDemand ;
	}
   
   
    
}
