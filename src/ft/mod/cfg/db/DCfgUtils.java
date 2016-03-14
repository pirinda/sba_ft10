/*
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.cfg.db;

import ft.mod.DModConsts;
import ft.mod.DModSysConsts;
import sba.lib.DLibConsts;
import sba.lib.DLibUtils;
import sba.lib.db.DDbRegistry;
import sba.lib.gui.DGuiSession;

/**
 *
 * @author Sergio Flores
 */
public abstract class DCfgUtils {

    public static boolean doesItemTypeRequireLotCode(final int itemType) {
        return DLibUtils.belongsTo(itemType, new int[] { DModSysConsts.CS_ITM_TP_PB, DModSysConsts.CS_ITM_TP_PF });
    }

    public static boolean doesItemTypeRequireMassUnit(final int itemType) {
        return DLibUtils.belongsTo(itemType, new int[] { DModSysConsts.CS_ITM_TP_RMI, DModSysConsts.CS_ITM_TP_PB, DModSysConsts.CS_ITM_TP_PF, DModSysConsts.CS_ITM_TP_BP, DModSysConsts.CS_ITM_TP_SC });
    }

    public static boolean doesItemTypeRequireFamilyBase(final int itemType) {
        return itemType == DModSysConsts.CS_ITM_TP_PF;
    }

    public static boolean doesItemTypeRequireItemBase(final int itemType) {
        return itemType == DModSysConsts.CS_ITM_TP_PF;
    }

    public static boolean doesItemTypeRequirePresent(final int itemType) {
        return itemType == DModSysConsts.CS_ITM_TP_PF;
    }
    
    public static String getMassUnitCode(final DGuiSession session) {
        return ((DDbConfig) session.getConfigCompany()).getRegMassUnit().getCode();
    }
    
    public static String getMassUnitName(final DGuiSession session) {
        return ((DDbConfig) session.getConfigCompany()).getRegMassUnit().getName();
    }
    
    private static String getBizPartnerType(final DGuiSession session, final int bizPartnerType, final int field) {
        return (String) session.readField(DModConsts.CS_BPR_TP, new int[] { bizPartnerType }, field);
    }
    
    public static String getBizPartnerTypeCode(final DGuiSession session, final int bizPartnerType) {
        return getBizPartnerType(session, bizPartnerType, DDbRegistry.FIELD_CODE);
    }
    
    public static String getBizPartnerClassName(final DGuiSession session, final int bizPartnerType) {
        return getBizPartnerType(session, bizPartnerType, DDbRegistry.FIELD_NAME);
    }
    
    public static int getBizPartnerTypeForWhsMoveType(final int[] keyWhsMoveClass) {
        int type = DLibConsts.UNDEFINED;
        
        switch (keyWhsMoveClass[0]) {
            case DModSysConsts.SS_MOV_CL_IN:
                type = keyWhsMoveClass[1] == DModSysConsts.SS_MOV_TP_IN_SAL[1] ? DModSysConsts.CS_BPR_TP_CUS : DModSysConsts.CS_BPR_TP_SUP;
                break;
            case DModSysConsts.SS_MOV_CL_OUT:
                type = keyWhsMoveClass[1] == DModSysConsts.SS_MOV_TP_OUT_SAL[1] ? DModSysConsts.CS_BPR_TP_CUS : DModSysConsts.CS_BPR_TP_SUP;
                break;
            default:
        }
        
        return type;
    }
}
