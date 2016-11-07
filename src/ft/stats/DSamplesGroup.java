/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ft.stats;

/**
 *
 * @author Sergio Flores
 */
public class DSamplesGroup {

    private double[] madSamples;

    public DSamplesGroup(final int number, final double[] samples) throws Exception {
        if (number <= 0) {
            throw new Exception(DStatsConsts.ERR_NUM);
        }
        
        if (samples == null) {
            throw new Exception(DStatsConsts.ERR_SAM_NUL);
        }
        
        if (samples.length != number) {
            throw new Exception(DStatsConsts.ERR_SAM_NUM);
        }
        
        madSamples = new double[number];
        
        for (int i = 0; i < number; i++) {
            madSamples[i] = samples[i];
        }
    }
    
    public int getSamplesNum() {
        return madSamples.length;
    }
    
    public double getSample(final int index) throws Exception {
        if (index < 0 || index >= madSamples.length) {
            throw new Exception(DStatsConsts.ERR_SAM_IDX);
        }
        
        return madSamples[index];
    }
}
