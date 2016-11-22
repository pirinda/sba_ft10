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

    public DSamplesGroup(final int samplesNum, final double[] samples) throws Exception {
        if (samplesNum <= 0) {
            throw new Exception(DStatsConsts.ERR_NUM_ZERO);
        }
        
        if (samples == null) {
            throw new Exception(DStatsConsts.ERR_SAM_NULL);
        }
        
        if (samples.length != samplesNum) {
            throw new Exception(DStatsConsts.ERR_SAM_NUM);
        }
        
        madSamples = samples.clone();
    }
    
    public int getSamplesNum() {
        return madSamples.length;
    }
    
    public double getSample(final int index) throws Exception {
        if (index < 0 || index >= getSamplesNum()) {
            throw new Exception(DStatsConsts.ERR_SAM_IDX);
        }
        
        return madSamples[index];
    }
}
