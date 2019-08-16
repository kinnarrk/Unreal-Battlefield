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
    private Position position;
    
    AirBase() {
        this.airBaseName = "Air Base-" + (++number);
        if(number == 1)
            position = new Position(400, 0);
        else
            position = new Position("Airbase");
    }

    public String getAirBaseName() {
        return airBaseName;
    }

    public void setAirBaseName(String airBaseName) {
        this.airBaseName = airBaseName;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
    
    @Override
    public String toString() {
        return this.airBaseName;
    }

}
