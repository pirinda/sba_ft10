/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stats;

import java.util.ArrayList;

/**
 *
 * @author Sergio Flores
 */
public class DControlCharts {
    
    private final ArrayList<DControlChartSubgroup> maSubgroups;
    private final int mnSubgroupSamples;  // samples number on each subgroup
    private final double[] madMeans;
    private final double[] madRanges;
    private final double mdMeanMeans;
    private final double mdMeanRanges;
    
    /**
     * Creates DControlCharts objects to compute control charts on a two-dimensional array of data.
     * @param data Two-dimensional array of data (index 1: subgroups; index 2: samples).
     */
    public DControlCharts(final double[][] data) {
        maSubgroups = new ArrayList<>();
        
        for (double[] samples : data) {
            maSubgroups.add(new DControlChartSubgroup(samples));
        }
        
        mnSubgroupSamples = data[0].length;
        
        madMeans = new double[maSubgroups.size()];
        madRanges = new double[maSubgroups.size()];
        
        for (int i = 0; i < maSubgroups.size(); i++) {
            madMeans[i] = maSubgroups.get(i).getMean();
            madRanges[i] = maSubgroups.get(i).getRange();
        }
        
        DStatsBasics moStatisticsMeans = new DStatsBasics(madMeans);
        DStatsBasics moStatisticsRanges = new DStatsBasics(madRanges);
        
        mdMeanMeans = moStatisticsMeans.getMean();
        mdMeanRanges = moStatisticsRanges.getMean();
    }
    
    /*
     * Private methods
     */
    
    private double getXChartA2() {
        return -0.3570 * Math.log(mnSubgroupSamples) + 1.1924;
    }
    
    private double getRChartD3() {
        return 0.2086 * Math.log(mnSubgroupSamples) - 0.2330;
    }
    
    private double getRChartD4() {
        return -0.5740 * Math.log(mnSubgroupSamples) + 3.1913;
    }
    
    private double getSigmad2() {
        return 1.0864 * Math.log(mnSubgroupSamples) + 0.5306;
    }
    
    /*
     * Public methods
     */
    
    public double[] getXChartData() {
        return madMeans.clone();
    }
    
    public double getXChartCentralLim() {
        return mdMeanMeans;
    }
    
    public double getXChartCentralLimLower() {
        return mdMeanMeans - getXChartA2() * mdMeanRanges;
    }
    
    public double getXChartCentralLimUpper() {
        return mdMeanMeans + getXChartA2() * mdMeanRanges;
    }
    
    public double[] getRChartData() {
        return madRanges.clone();
    }
    
    public double getRChartCentralLim() {
        return mdMeanRanges;
    }
    
    public double getRChartCentralLimLower() {
        return getRChartD3() * mdMeanRanges;
    }
    
    public double getRChartCentralLimUpper() {
        return getRChartD4() * mdMeanRanges;
    }
    
    public double getProcessCapacity() {
        return (getXChartCentralLimUpper() - getXChartCentralLimLower()) / (6 * (mdMeanRanges / getSigmad2()));
    }
}
