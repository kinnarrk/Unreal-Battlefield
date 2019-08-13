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
    
    public AirBase addAirBase(int aiBasePayLoad) {
        AirBase airBase = new AirBase(aiBasePayLoad);
        airBaseDirectory.add(airBase);
        return airBase;
    }

}
