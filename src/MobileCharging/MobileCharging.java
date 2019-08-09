/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MobileCharging;

/**
 *
 * @author bhaVYa
 */
public class MobileCharging {
    private static final int STOP = 40;
    private static final int TOTAL_CARS = 5;
    private static final int MIN_CAR_BATTERY = 15;
    private static final int MAX_CAR_BATTERY = 50;
    private static final int TOTAL_PARTICLES = 30;
    private static final int TOTAL_ITERATIONS = 40;
    
    public static void main(String[] args) {
        MobileChargingDirectory directory = new MobileChargingDirectory(STOP, TOTAL_CARS, MIN_CAR_BATTERY, MAX_CAR_BATTERY);
        
        
    }
}
