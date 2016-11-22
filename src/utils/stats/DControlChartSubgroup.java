/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stats;

/**
 *
 * @author Sergio Flores
 */
public class DControlChartSubgroup {
    
    private double[] madSamples;
    private int mnSamples;
    private double mdMean;
    private double mdMin;
    private double mdMax;
    private double mdRange;
    
    public DControlChartSubgroup(double[] samples) {
        DStatsBasics basics = new DStatsBasics(samples);
        
        madSamples = samples.clone();
        
        mnSamples = basics.getCount();
        mdMean = basics.getMean();
        mdMin = basics.getMin();
        mdMax = basics.getMax();
        mdRange = basics.getRange();
    }
    
    public int getSamples() {
        return mnSamples;
    }
    
    public double getMean() {
        return mdMean;
    }
    
    public double getMin() {
        return mdMin;
    }
    
    public double getMax() {
        return mdMax;
    }
    
    public double getRange() {
        return mdRange;
    }
}
