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
    private final AirBaseDirectory airbaseDirectory = new AirBaseDirectory();
    Random r = new Random();
    private static int airbaseCount = 0;
    private double[][] airbaseLocation = {{375, 425, 0, 200}, {300, 375, 0, 400}, {375, 425, 200, 400}, {425, 500, 0, 400}};
//    private double[][] targetLocation = {{0, 300, 0, 400}, {0, 800, 400, 800}, {500, 800, 0, 400}};
    private static int targetCount = 0;
    private double[][] targetLocation = {{0, 300, 0, 200}, {0, 300, 200, 400}, {0, 150, 400, 800}, {150, 300, 500, 800},
    {300, 400, 400, 800}, {400, 500, 400, 800}, {500, 650, 400, 800}, {650, 800, 400, 800}, {500, 800, 200, 400}, {500, 800, 0, 200}};
    private final TargetDirectory targetDirectory = new TargetDirectory();

    Position(String type) {
        switch (type) {
            case "Airbase":
                int randomIndex = airbaseCount;
                this.lat = airbaseLocation[randomIndex][0] + (airbaseLocation[randomIndex][1] - airbaseLocation[randomIndex][0]) * r.nextDouble();
                this.lng = airbaseLocation[randomIndex][2] + (airbaseLocation[randomIndex][3] - airbaseLocation[randomIndex][2]) * r.nextDouble();

                for (Target t : targetDirectory.getTarget()) {
                    while (t.getPosition().getLat() == this.lat
                            && t.getPosition().getLng() == this.lng) {
                        this.lat = airbaseLocation[randomIndex][0] + (airbaseLocation[randomIndex][1] - airbaseLocation[randomIndex][0]) * r.nextDouble();
                        this.lng = airbaseLocation[randomIndex][2] + (airbaseLocation[randomIndex][3] - airbaseLocation[randomIndex][2]) * r.nextDouble();
                    }
                }
                airbaseCount++;
                break;
            case "Target":
                randomIndex  = targetCount;
                this.lat = targetLocation[randomIndex][0] + (targetLocation[randomIndex][1] - targetLocation[randomIndex][0]) * r.nextDouble();
                this.lng = targetLocation[randomIndex][2] + (targetLocation[randomIndex][3] - targetLocation[randomIndex][2]) * r.nextDouble();

                for (Target t : targetDirectory.getTarget()) {
                    while (t.getPosition().getLat() == this.lat
                            && t.getPosition().getLng() == this.lng) {
                        this.lat = targetLocation[randomIndex][0] + (targetLocation[randomIndex][1] - targetLocation[randomIndex][0]) * r.nextDouble();
                        this.lng = targetLocation[randomIndex][2] + (targetLocation[randomIndex][3] - targetLocation[randomIndex][2]) * r.nextDouble();
                    }
                }
                targetCount++;
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
