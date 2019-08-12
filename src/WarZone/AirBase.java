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

    private String airBaseName;
    private static int number = 0;

    AirBase() {
        this.airBaseName = "Air Base-" + (++number);
    }

    public String getAirBaseName() {
        return airBaseName;
    }

    public void setAirBaseName(String airBaseName) {
        this.airBaseName = airBaseName;
    }

    @Override
    public String toString() {
        return this.airBaseName;
    }

}
