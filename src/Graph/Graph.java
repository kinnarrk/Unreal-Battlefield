/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

/**
 *
 * @author 19712
 */
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedRangeCategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class Graph extends ApplicationFrame {

    private static int TOTAL_ITERATIONS;
    private static double[][] result;
    private static int[] samples;
    private static String[] particles = {};
    
    public static JPanel createDemoPanel() {
        JFreeChart jfreechart = createChart();
        return new ChartPanel(jfreechart);
    }

    private static JFreeChart createChart() {
        CategoryDataset categorydataset = createDataset1();
        CategoryAxis categoryaxis = new CategoryAxis("Iterations");
        categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        categoryaxis.setMaximumCategoryLabelWidthRatio(5F);
        LineAndShapeRenderer lineandshaperenderer = new LineAndShapeRenderer();
        lineandshaperenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
        CategoryPlot categoryplot = new CategoryPlot(categorydataset, categoryaxis, null, lineandshaperenderer);
        categoryplot.setDomainGridlinesVisible(true);
        NumberAxis numberaxis = new NumberAxis("Distance (Miles)");
        CombinedRangeCategoryPlot combinedrangecategoryplot = new CombinedRangeCategoryPlot(numberaxis);
        combinedrangecategoryplot.add(categoryplot, 3);
        combinedrangecategoryplot.setOrientation(PlotOrientation.VERTICAL);
        JFreeChart jfreechart = new JFreeChart("Particle Progress", new Font("SansSerif", 1, 14), combinedrangecategoryplot, true);
        return jfreechart;
    }

    public static CategoryDataset createDataset1() {
        DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                defaultcategorydataset.addValue(result[i][j], particles[i], j+1 + "");
            }
            System.out.println("");
        }

        return defaultcategorydataset;
    }

    public Graph(String title, double[][] graphArray) {
        super("Particle Progress");
        
        this.result = graphArray;
        
        for(int i=0; i<result.length; i++) {
            particles[i] = "p"+(i+1);
        }
        ChartPanel chartpanel = new ChartPanel(createChart());
        chartpanel.setPreferredSize(new Dimension(1000, 600));
        setContentPane(chartpanel);
        
        
        this.pack();
        RefineryUtilities.centerFrameOnScreen(this);
        this.setVisible(true);
        
        
        
    }

}
