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
public class CarDirectory {
    private List<Car> carDirectory;
    
    public CarDirectory() {
        carDirectory = new ArrayList<Car>();
    }
    
    public Car addCar() {
        Car car = new Car();
        carDirectory.add(car);
        return car;
    }
    
    public List<Car> getCar() {
        return carDirectory;
    }
}
