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

        if (type < DModSysConsts.CX_PAC_STK) {
            module = DModSysConsts.CX_PAC_CFG;
        }
        else if (type < DModSysConsts.CX_PAC_MFG) {
            module = DModSysConsts.CX_PAC_STK;
        }
        else if (type < DModSysConsts.CX_PAC_QTY) {
            module = DModSysConsts.CX_PAC_MFG;
        }
        else {
            module = DModSysConsts.CX_PAC_QTY;
        }

        return module;
    }

    @Override
    public int getModuleSubtypeBySubtype(final int type, final int subtype) {
        return DLibConsts.UNDEFINED;
    }
}
