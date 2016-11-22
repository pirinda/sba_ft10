/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ft.stats;

import java.util.ArrayList;

/**
 *
 * @author Sergio Flores
 */
public class DSamplesGroupsArray {
    
    private int mnSamplesNum;
    private ArrayList<DSamplesGroup> maSamplesGroups;
    
    public DSamplesGroupsArray(final int samplesNum) throws Exception {
        if (samplesNum <= 0) {
            throw new Exception(DStatsConsts.ERR_NUM_ZERO);
        }
        
        mnSamplesNum = samplesNum;
        maSamplesGroups = new ArrayList<>();
    }
    
    public void addSamples(final DSamplesGroup samplesGroup) throws Exception {
        if (samplesGroup == null) {
            throw new Exception(DStatsConsts.ERR_SAM_NULL);
        }
        
        if (samplesGroup.getSamplesNum() != mnSamplesNum) {
            throw new Exception(DStatsConsts.ERR_SAM_NUM);
        }
        
        maSamplesGroups.add(samplesGroup);
    }
    
    public int getGroupsNum() {
        return maSamplesGroups.size();
    }
    
    public double[][] getSamples() throws Exception {
        double[][] samples = new double[maSamplesGroups.size()][mnSamplesNum];
        
        for (int group = 0; group < maSamplesGroups.size(); group++) {
            for (int sample = 0; sample < mnSamplesNum; sample++) {
                samples[group][sample] = maSamplesGroups.get(group).getSample(sample);
            }
        }
        
        return samples;
    }
}
