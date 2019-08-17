/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animation;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Kinnar
 */
public class AnimationBoard extends JPanel {
    private final int B_WIDTH = 800;
    private final int B_HEIGHT = 800;
    private final int INITIAL_X = B_WIDTH/2;
    private final int INITIAL_Y = B_HEIGHT-50;    
    private final int INITIAL_DELAY = 100;
    private final int PERIOD_INTERVAL = 25;
    private final int SHIFT_FACTOR = 20;
    private Shape circles[];
    private Shape squares[];
    private Shape circles2[];
    private Shape squares2[];
    private Image star;
    private Image circle;
    private Image square;
    private Image squaregreen;
    private Image explosion;
    private Image background;
    private Timer timer;
    private int x, y;
    
    private int idx = 1;   
    
    private int arrx[] = {INITIAL_X, 160, 60, 260, 380, 450, 320};
    private int arry[] = {INITIAL_Y, 320, 260, 60, 180, 250, 360};
    private boolean lastPaint = false;
    
    private Map<String, List<List<String>>> hashMap;
    private final List<List<String>> parentRoute;
    private final List<List<String>> initialPoints;
//    private List<String> strikeRoute;
    
    AnimationStorage storage;
    
    public AnimationBoard(List<List<String>> parentRoute, List<List<String>> initialPoints) {
        this.parentRoute = parentRoute;
        this.initialPoints = initialPoints;
        initBoard();        
    }
    
    private void loadImage() {
        
        ImageIcon ii = new ImageIcon("src/resources/star.png");
        star = ii.getImage();
        ii = new ImageIcon("src/resources/circle.png");
        circle = ii.getImage();
        ii = new ImageIcon("src/resources/square.png");
        square = ii.getImage();
        ii = new ImageIcon("src/resources/squaregreen.png");
        squaregreen = ii.getImage();
        ii = new ImageIcon("src/resources/explosion.gif");
        explosion = ii.getImage();
        
        background = Toolkit.getDefaultToolkit().createImage("src/resources/terrain800.jpg");
        
    }
    
    private void initBoard() {        
//        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        circles = new Shape[parentRoute.size()];
        squares = new Shape[parentRoute.size()];
        circles2 = new Shape[initialPoints.size()];
        squares2 = new Shape[initialPoints.size()];
        loadImage();

        if(parentRoute.size() > 0) {
            List<String> strikeRoute = (ArrayList<String>)parentRoute.get(0);
            x = Integer.parseInt(strikeRoute.get(1));
            y = Integer.parseInt(strikeRoute.get(2));
        }
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), 
                INITIAL_DELAY, PERIOD_INTERVAL);

//        BufferedImage img = ImageIO.read(new File("src/resources/terrain.gif"));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this);
        Graphics2D g2 = (Graphics2D) g;        
        g2.setColor(Color.WHITE);
        
        for(int i = 0; i < initialPoints.size(); i++) {
            List<String> strikeRoute = (ArrayList<String>)initialPoints.get(i);
            if(strikeRoute.get(3).equals("AB")){
                if(strikeRoute.get(0).equals("AB0")){
                    g2.setColor(Color.GREEN);
                    squares2[i] = new Rectangle(Integer.parseInt(strikeRoute.get(1)), Integer.parseInt(strikeRoute.get(2)), 30, 30);                            
                    g2.fill(squares2[i]);                    
//                    g.drawImage(squaregreen, Integer.parseInt(strikeRoute.get(1)), Integer.parseInt(strikeRoute.get(2)), this);
                    g2.setColor(Color.WHITE);
                } else {                    
                    squares2[i] = new Rectangle(Integer.parseInt(strikeRoute.get(1)), Integer.parseInt(strikeRoute.get(2)), 30, 30);                            
                    g2.fill(squares2[i]);
                }
//                if(i == 0){
//                    g2.setColor(Color.GREEN);
////                    Shape initCircle = new Ellipse2D.Double(Double.parseDouble(strikeRoute.get(1))+5, Double.parseDouble(strikeRoute.get(2))+5, 20, 20);
//                    g2.fillOval(Integer.parseInt(strikeRoute.get(1))+20, Integer.parseInt(strikeRoute.get(2))+20, 20, 20);
////                    g2.fillOval(initCircle);
//                    g2.setColor(Color.WHITE);
//                }
            } else {
                g2.setColor(Color.WHITE);
                circles2[i] = new Ellipse2D.Double(Double.parseDouble(strikeRoute.get(1)), Double.parseDouble(strikeRoute.get(2)), 20, 20);
    //            g.drawImage(circle, Integer.parseInt(strikeRoute.get(1)), Integer.parseInt(strikeRoute.get(2)), this);
                g2.fill(circles2[i]);
            }           
        }
        
        for(int i = 0; i < parentRoute.size(); i++) {
            List<String> strikeRoute = (ArrayList<String>)parentRoute.get(i);
            if(strikeRoute.get(4).equals("0")){
                if(strikeRoute.get(0).equals("AB0")){
                    g2.setColor(Color.GREEN);
                    squares[i] = new Rectangle(Integer.parseInt(strikeRoute.get(1)), Integer.parseInt(strikeRoute.get(2)), 30, 30);                            
                    g2.fill(squares[i]);                    
//                    g.drawImage(squaregreen, Integer.parseInt(strikeRoute.get(1)), Integer.parseInt(strikeRoute.get(2)), this);
                    g2.setColor(Color.WHITE);
                } else {                    
                    squares[i] = new Rectangle(Integer.parseInt(strikeRoute.get(1)), Integer.parseInt(strikeRoute.get(2)), 30, 30);                            
                    g2.fill(squares[i]);
                }
//                if(i == 0){
//                    g2.setColor(Color.GREEN);
////                    Shape initCircle = new Ellipse2D.Double(Double.parseDouble(strikeRoute.get(1))+5, Double.parseDouble(strikeRoute.get(2))+5, 20, 20);
//                    g2.fillOval(Integer.parseInt(strikeRoute.get(1))+20, Integer.parseInt(strikeRoute.get(2))+20, 20, 20);
////                    g2.fillOval(initCircle);
//                    g2.setColor(Color.WHITE);
//                }
            } else {
                g2.setColor(Color.WHITE);
                circles[i] = new Ellipse2D.Double(Double.parseDouble(strikeRoute.get(1)), Double.parseDouble(strikeRoute.get(2)), 20, 20);
    //            g.drawImage(circle, Integer.parseInt(strikeRoute.get(1)), Integer.parseInt(strikeRoute.get(2)), this);
                g2.fill(circles[i]);
            }
            
//            g2.setColor(Color.BLACK);
            if(i < parentRoute.size()-1){
                List<String> strikeRoute2 = (ArrayList<String>)parentRoute.get(i+1);
                drawDashedLine(g, Integer.parseInt(strikeRoute.get(1)), Integer.parseInt(strikeRoute.get(2)), Integer.parseInt(strikeRoute2.get(1)), Integer.parseInt(strikeRoute2.get(2)));
            } else {
//                drawDashedLine(g, parentRoute[i], arry[i], parentRoute[0], arry[0]);
                if(parentRoute.size() > 0) {
                    List<String> strikeRoute2 = (ArrayList<String>)parentRoute.get(0);
                    drawDashedLine(g, Integer.parseInt(strikeRoute.get(1)), Integer.parseInt(strikeRoute.get(2)), Integer.parseInt(strikeRoute2.get(1)), Integer.parseInt(strikeRoute2.get(2)));
                }
            }
        }        
        drawStar(g);
    }
    
    private void drawStar(Graphics g) {
        
        g.drawImage(star, x, y, this);
        Graphics2D g2 = (Graphics2D) g;        
        List<String> strikeRoute2;
        if(idx == 0)
            strikeRoute2 = (ArrayList<String>)parentRoute.get(0);
        else
            strikeRoute2 = (ArrayList<String>)parentRoute.get(idx-1);
        String str = "Payloads available: " + strikeRoute2.get(3) + "   ";
        String str2 = "Fuel available: " + strikeRoute2.get(5) +" Gal";
        Font font = new Font("Serif", Font.BOLD, 16);
        g2.setFont(font);
        Color textColor = Color.WHITE;
        Color bgColor = Color.BLACK;
        int xx = x+40;
        int yy = y+23;

        FontMetrics fm = g.getFontMetrics();
        Rectangle2D rect = fm.getStringBounds(str, g);

        g.setColor(bgColor);
        g.fillRect(xx,
                   yy - fm.getAscent(),
                   (int) rect.getWidth(),
                   (int) rect.getHeight()*2);

        g.setColor(textColor);
        g2.drawString(str, x+40, y+20);
        g2.drawString(str2, x+40, y+40);
        g2.setColor(Color.RED);
        for(int i = 1; i < idx; i++) {
            List<String> strikeRoute = (ArrayList<String>)parentRoute.get(i);
            if(strikeRoute.get(4).equals("1")){
                circles[i] = new Ellipse2D.Double(Integer.parseInt(strikeRoute.get(1)), Integer.parseInt(strikeRoute.get(2)), 20, 20);
    //            g.drawImage(circle, Integer.parseInt(strikeRoute.get(1)), Integer.parseInt(strikeRoute.get(2)), this);
                g2.fill(circles[i]);
                g.drawImage(explosion, Integer.parseInt(strikeRoute.get(1))-5, Integer.parseInt(strikeRoute.get(2))-10, this);
            }
//            if(i == (idx-1))
//                g2.fill(circles[i+1]);
        }
        if(lastPaint){
            for(int i = 1; i < parentRoute.size(); i++) {
                List<String> strikeRoute = (ArrayList<String>)parentRoute.get(i);
                if(strikeRoute.get(4).equals("1")){
                    g2.fill(circles[i]);
                    g.drawImage(explosion, Integer.parseInt(strikeRoute.get(1))-5, Integer.parseInt(strikeRoute.get(2))-10, this);
    //                g.drawImage(explosion, parentRoute[i]-5, arry[i]-10, this);
                }
            }
//            g2.fill(circles[arrx.length-1]);
//            g.drawImage(explosion, arrx[arrx.length-1]-5, arry[arrx.length-1]-10, this);
                        
        }
//        if(lastPaint && idx == 1){
//            lastPaint = false;
//        }
        Toolkit.getDefaultToolkit().sync();
    }
    
    public void drawDashedLine(Graphics g, int x1, int y1, int x2, int y2){

        //creates a copy of the Graphics instance
        Graphics2D g2d = (Graphics2D) g.create();

        //set the stroke of the copy, not the original 
        Stroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2d.setStroke(dashed);
        g2d.drawLine(x1, y1, x2, y2);

        //gets rid of the copy
        g2d.dispose();
    }

    private class ScheduleTask extends TimerTask {

        @Override
        public void run() {
            List<String> strikeRoute = (ArrayList<String>)parentRoute.get(idx);
            if(x>Integer.parseInt(strikeRoute.get(1))){
                x-=((x-Integer.parseInt(strikeRoute.get(1)))/SHIFT_FACTOR);
            } else if(x<Integer.parseInt(strikeRoute.get(1))){
                x+=((Integer.parseInt(strikeRoute.get(1))-x)/SHIFT_FACTOR);                
            }
            if(y>Integer.parseInt(strikeRoute.get(2))){
                y-=((y-Integer.parseInt(strikeRoute.get(2)))/SHIFT_FACTOR);
            } else if(y<Integer.parseInt(strikeRoute.get(2))){
                y+=((Integer.parseInt(strikeRoute.get(2))-y)/SHIFT_FACTOR);                
            }
//            System.out.println("new x: " + x);
//            System.out.println("new y: " + y);
            
            if(x == Integer.parseInt(strikeRoute.get(1))-SHIFT_FACTOR || x == Integer.parseInt(strikeRoute.get(1))+SHIFT_FACTOR || y == Integer.parseInt(strikeRoute.get(2))+SHIFT_FACTOR || y == Integer.parseInt(strikeRoute.get(2))-SHIFT_FACTOR){
//                System.out.println("got x or y equal to point");
                
//                waitOnTarget(2000, x, y, idx);
//                try{
//                    timer.wait(2000);
//                    wait(2000);
//                    this.wait(2000);
//                    this.sleep(2000);
//                }catch(InterruptedException ex){}

                x = Integer.parseInt(strikeRoute.get(1));
                y = Integer.parseInt(strikeRoute.get(2));
                if(idx < parentRoute.size()-1) {                    
                    idx++;
                } else {                    
                    idx = 0;
                    lastPaint = true;                    
                }
//                if(!lastPaint && idx != 1){
                    timer.cancel();
                    timer = new Timer();
                    timer.scheduleAtFixedRate(new ScheduleTask(), 
                            500, PERIOD_INTERVAL);                
//                }
            }            
            if(lastPaint && idx == 1){
//                repaint();
                timer.cancel();
//                repaint();
            }
            
//            x += 1;
//            y += 1;

//            if (y > B_HEIGHT) {
//                y = INITIAL_Y;
//                x = INITIAL_X;
//            }
            
            repaint();
        }
        private void waitOnTarget(long millis, int xx, int yy, int idx2){
            try{
                Thread.sleep(millis);
            }catch(InterruptedException ex){}
            x = xx;
            y = yy;
            idx = idx2;
            x = arrx[idx2];
            y = arry[idx2];
            if(idx2 < arrx.length-1) {                    
                idx++;
            } else {                    
                idx = 0;
                lastPaint = true;                    
            }
        }
    }
}
