/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod;

import sba.lib.DLibConsts;
import sba.lib.gui.DGuiModuleUtils;

/**
 *
 * @author Sergio Flores
 */
public class DModUtils implements DGuiModuleUtils {

    public DModUtils() {

    }

    @Override
    public int getModuleTypeByType(final int type) {
        int module = DLibConsts.UNDEFINED;

        if (type < DModSysConsts.CS_MOD_STK) {
            module = DModSysConsts.CS_MOD_CFG;
        }
        else if (type < DModSysConsts.CS_MOD_MFG) {
            module = DModSysConsts.CS_MOD_STK;
        }
        else if (type < DModSysConsts.CS_MOD_QTY) {
            module = DModSysConsts.CS_MOD_MFG;
        }
        else if (type < DModSysConsts.CS_MOD_CST) {
            module = DModSysConsts.CS_MOD_QTY;
        }
        else {
            module = DModSysConsts.CS_MOD_CST;
        }

        return module;
    }

    @Override
    public int getModuleSubtypeBySubtype(final int type, final int subtype) {
        return DLibConsts.UNDEFINED;
    }
}
