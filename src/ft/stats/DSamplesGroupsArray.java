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
            throw new Exception(DStatsConsts.ERR_NUM);
        }
        
        mnSamplesNum = samplesNum;
        maSamplesGroups = new ArrayList<>();
    }
    
    public void addSamples(final DSamplesGroup samplesGroup) throws Exception {
        if (samplesGroup == null) {
            throw new Exception(DStatsConsts.ERR_SAM_NUL);
        }
        
        if (samplesGroup.getSamplesNum() != mnSamplesNum) {
            throw new Exception(DStatsConsts.ERR_SAM_NUM);
        }
        
        maSamplesGroups.add(samplesGroup);
    }
}
