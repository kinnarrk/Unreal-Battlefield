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
public class AirBaseDirectory {

    private List<AirBase> airBaseDirectory;

    public AirBaseDirectory() {
         airBaseDirectory = new ArrayList<AirBase>();
    }
    
    public AirBase addAirBase() {
        AirBase airBase = new AirBase();
        airBaseDirectory.add(airBase);
        return airBase;
    }

    public List<AirBase> getAirBaseDirectory() {
        return airBaseDirectory;
    }

    public void setAirBaseDirectory(List<AirBase> airBaseDirectory) {
        this.airBaseDirectory = airBaseDirectory;
    }

    
}
