/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animation;

import javax.swing.JFrame;
import java.awt.EventQueue;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Kinnar
 */
public class TimerAnimationUtility extends JFrame{
    AnimationStorage storage;
    List<List<String>> parentRoute;
    List<List<String>> initialPoints;
    
    public TimerAnimationUtility(List<List<String>> parentRoute, List<List<String>> initialPoints) {
        this.parentRoute = parentRoute;
        this.initialPoints = initialPoints;
        initUI();
    }
    
    private void initUI() {
        
        add(new AnimationBoard(parentRoute, initialPoints));
                        
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
