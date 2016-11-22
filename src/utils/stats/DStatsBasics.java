/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stats;

import java.util.Arrays;

/**
 *
 * @author Sergio Flores
 */
public class DStatsBasics {
    
    private double[] madData;
    
    /**
     * Creates DStatistics objects to compute simple statistics on an array of data.
     * @param data Array of data.
     */
    public DStatsBasics(final double[] data) {
        madData = data.clone();
        Arrays.sort(madData);
    }
    
    /*
     * Private methods
     */
    
    /*
     * Public methods
     */
    
    public int getCount() {
        return madData.length;
    }
    
    public double getMin() {
        return madData[0];
    }
    
    public double getMax() {
        return madData[madData.length - 1];
    }
    
    public double getRange() {
        return getMax() - getMin();
    }
    
    public double getSum() {
        double sum = 0;
        
        for (double d : madData) {
            sum += d;
        }
        
        return sum;
    }
    
    public double getMean() {
        double sum = 0;
        
        for (double d : madData) {
            sum += d;
        }
        
        return sum / madData.length;
    }
    
    public double getMedian() {
        if (madData.length % 2 != 0) {
            return madData[madData.length / 2];
        }
        else {
            return (madData[(madData.length / 2) - 1] + madData[madData.length / 2]) / 2;
        }
    }
    
    public double getVariance() {
        double mean = getMean();
        double temp = 0;
        
        for (double d : madData) {
            temp += (d - mean) * (d - mean);
        }
        
        return temp / madData.length;
    }
    
    public double getStdDeviation() {
        return Math.sqrt(getVariance());
    }
}
