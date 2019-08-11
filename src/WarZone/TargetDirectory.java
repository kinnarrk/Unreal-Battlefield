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
public class TargetDirectory {
    
    private List<Target> targetDirectory;
    
    public TargetDirectory() {
        targetDirectory = new ArrayList<Target>();
    }
    
    public Target addStop(int demand) {
        Target target = new Target(demand);
        targetDirectory.add(target);
        return target;
    }
    
    public List<Target> getStop() {
        return targetDirectory;
    }
    
}
