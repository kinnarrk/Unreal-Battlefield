/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MobileCharging;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 19712
 */
public class StopDirectory {
    
    private List<Stop> stopDirectory;
    
    public StopDirectory() {
        stopDirectory = new ArrayList<Stop>();
    }
    
    public Stop addStop() {
        Stop stop = new Stop();
        stopDirectory.add(stop);
        return stop;
    }
    
    public List<Stop> getStop() {
        return stopDirectory;
    }
    
}
