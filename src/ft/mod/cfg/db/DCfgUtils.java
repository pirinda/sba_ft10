/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ft.mod.cfg.db;

import ft.mod.DModConsts;
import ft.mod.DModSysConsts;
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
    
    public static String getSystemUnitCodeMass(final DGuiSession session) {
        return (String) session.readField(DModConsts.CS_UOM_TP, new int[] { DModSysConsts.CS_UOM_TP_MSS }, DDbRegistry.FIELD_CODE);
    }
}
