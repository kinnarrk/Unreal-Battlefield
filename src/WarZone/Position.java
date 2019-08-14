/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WarZone;

import java.util.Random;

/**
 *
 * @author bhaVYa
 */
public class Position {

    private double lat;
    private double lng;
    private static final int AIRBASE_MIN_LAT = -50;
    private static final int AIRBASE_MAX_LAT = 50;
    private static final int AIRBASE_MIN_LNG = 20;
    private static final int AIRBASE_MAX_LNG = 70;
    private final AirBaseDirectory airbaseDirectory = new AirBaseDirectory();
    Random r = new Random();
    private double[][] targetLocation = {{-100, -50, 20, 70}, {50, 100, 20, 70}, {-100, 100, 70, 120}};
    private final TargetDirectory targetDirectory = new TargetDirectory();

    Position(String type) {
        switch (type) {
            case "Airbase":
                this.lat = AIRBASE_MIN_LAT + (AIRBASE_MAX_LAT - AIRBASE_MIN_LAT) * r.nextDouble();
                this.lng = AIRBASE_MIN_LNG + (AIRBASE_MAX_LNG - AIRBASE_MIN_LNG) * r.nextDouble();

                for (AirBase a : airbaseDirectory.getAirBaseDirectory()) {
                    while (a.getPosition().getLat() == this.lat
                            && a.getPosition().getLng() == this.lng) {
                        this.lat = AIRBASE_MIN_LAT + (AIRBASE_MAX_LAT - AIRBASE_MIN_LAT) * r.nextDouble();
                        this.lng = AIRBASE_MIN_LNG + (AIRBASE_MAX_LNG - AIRBASE_MIN_LNG) * r.nextDouble();
                    }
                }
                break;
            case "Target":
                int randomIndex = r.nextInt(3);
                this.lat = targetLocation[randomIndex][0] + (targetLocation[randomIndex][1] - targetLocation[randomIndex][0]) * r.nextDouble();
                this.lng = targetLocation[randomIndex][2] + (targetLocation[randomIndex][3] - targetLocation[randomIndex][2]) * r.nextDouble();

                for(Target t: targetDirectory.getTarget()) {
                    while (t.getPosition().getLat() == this.lat
                            && t.getPosition().getLng() == this.lng) {
                        this.lat = targetLocation[randomIndex][0] + (targetLocation[randomIndex][1] - targetLocation[randomIndex][0]) * r.nextDouble();
                        this.lng = targetLocation[randomIndex][2] + (targetLocation[randomIndex][3] - targetLocation[randomIndex][2]) * r.nextDouble();
                    }
                }
                break;
        }

    }

    Position(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }
    
    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

}
