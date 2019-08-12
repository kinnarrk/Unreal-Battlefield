/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WarZone;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author 19712
 */
public class Target {
    int targetPayload;
    private String name;
    private static int no = 0;
    
    public Target(int payload){
        this.name = "Target-"+(++no);
        this.targetPayload=payload;
    }

    public int getTargetPayload() {
        return targetPayload;
    }

    public void setTargetPayload(int targetPayload) {
        this.targetPayload = targetPayload;
    }
    
    
    
    @Override
	public String toString() {
		return this.name+" Payload required:" + targetPayload ;
	}
   
   
    
}
