/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animation;

import javax.swing.JFrame;
import java.awt.EventQueue;
import java.util.List;

/**
 *
 * @author Kinnar
 */
public class TimerAnimationUtility extends JFrame{
    AnimationStorage storage;
    List<List<String>> parentRoute;
    
    public TimerAnimationUtility(List<List<String>> parentRoute) {
        this.parentRoute = parentRoute;
        initUI();
    }
    
    private void initUI() {
        
        add(new AnimationBoard(parentRoute));
                        
        setResizable(false);
        pack();
        
        setTitle("Drone");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
    }

    public void invokeUI() {        
//        EventQueue.invokeLater(() -> {
//            JFrame ex = new TimerAnimationUtility(storage);
//            ex.setVisible(true);
//        });
    }
}
