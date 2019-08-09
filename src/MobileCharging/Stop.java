/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MobileCharging;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author 19712
 */
public class Stop {
    int stopDemand;
   
    public Stop(int demand){
        this.stopDemand=demand;
    }
    @Override
	public String toString() {
		return "Stop Demand=" + stopDemand ;
	}
   
   
    
}
