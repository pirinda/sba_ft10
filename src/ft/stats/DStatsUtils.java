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
public abstract class DStatsUtils {
    
    /**
     * 
     * @param samplesNum Number of desired samples.
     * @param samples Available samples that must count less or equal than number of desired samples.
     * @return Requested number of samples.
     */
    public static double[] completeSamples(final int samplesNum, final double[] samples) throws Exception {
        double total = 0;
        double[] samplesComplete = null;
        
        if (samplesNum <= 0) {
            throw new Exception(DStatsConsts.ERR_NUM_ZERO);
        }
        
        if (samples == null || samples.length == 0) {
            throw new Exception(DStatsConsts.ERR_SAM_NULL);
        }
        
        samplesComplete = new double[samplesNum];
        
        if (samples.length <= samplesNum) {
            // complete samples, if needed, with average of available ones
            
            for (int i = 0; i < samples.length; i++) {
                total += samples[i];
                samplesComplete[i] = samples[i];
            }
            
            for (int i = samples.length; i < samplesNum; i++) {
                samplesComplete[i] = total / samples.length;
            }
        }
        else {
            // cut samples up to the ones required
            
            for (int i = 0; i < samplesNum; i++) {
                samplesComplete[i] = samples[i];
            }
        }
        
        return samplesComplete;
    }
}
