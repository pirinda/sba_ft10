/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod;

import ft.mod.stk.db.DDbStock;
import ft.mod.stk.db.DDbWarehouse;
import ft.mod.stk.db.DDbWsd;
import ft.mod.stk.db.DDbWsdRow;
import ft.mod.stk.form.DFormWarehouse;
import ft.mod.stk.form.DFormWsd;
import ft.mod.stk.view.DViewStock;
import ft.mod.stk.view.DViewWarehouse;
import ft.mod.stk.view.DViewWsd;
import javax.swing.JMenu;
import sba.lib.DLibConsts;
import sba.lib.db.DDbConsts;
import sba.lib.db.DDbRegistry;
import sba.lib.db.DDbRegistrySysFly;
import sba.lib.grid.DGridPaneView;
import sba.lib.gui.DGuiCatalogueSettings;
import sba.lib.gui.DGuiClient;
import sba.lib.gui.DGuiForm;
import sba.lib.gui.DGuiModule;
import sba.lib.gui.DGuiOptionPicker;
import sba.lib.gui.DGuiParams;
import sba.lib.gui.DGuiReport;

/**
 *
 * @author Sergio Flores
 */
public class DModModuleStk extends DGuiModule {
    
    private DFormWarehouse moFormWarehouse;
    private DFormWsd moFormWsdIn;
    private DFormWsd moFormWsdOut;

    public DModModuleStk(DGuiClient client) {
        super(client, DModSysConsts.CX_PAC_STK, DLibConsts.UNDEFINED);
    }

    @Override
    public JMenu[] getMenus() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DDbRegistry getRegistry(final int type, DGuiParams params) {
        DDbRegistry registry = null;

        switch (type) {
            case DModConsts.SS_WHS_TP:
                registry = new DDbRegistrySysFly(type) {
                    @Override
                    public String getSqlTable() { return DModConsts.TablesMap.get(type); }
                    
                    @Override
                    public String getSqlWhere(int[] pk) { return "WHERE id_whs_tp = " + pk[0] + " "; }
                };
                break;
            case DModConsts.SS_MOV_CL:
                registry = new DDbRegistrySysFly(type) {
                    @Override
                    public String getSqlTable() { return DModConsts.TablesMap.get(type); }
                    
                    @Override
                    public String getSqlWhere(int[] pk) { return "WHERE id_mov_cl = " + pk[0] + " "; }
                };
                break;
            case DModConsts.SS_MOV_TP:
                registry = new DDbRegistrySysFly(type) {
                    @Override
                    public String getSqlTable() { return DModConsts.TablesMap.get(type); }
                    
                    @Override
                    public String getSqlWhere(int[] pk) { return "WHERE id_mov_cl = " + pk[0] + " AND id_mov_tp = " + pk[1] + " "; }
                };
                break;
            case DModConsts.SS_TRN_TP:
                registry = new DDbRegistrySysFly(type) {
                    @Override
                    public String getSqlTable() { return DModConsts.TablesMap.get(type); }
                    
                    @Override
                    public String getSqlWhere(int[] pk) { return "WHERE id_trn_tp = " + pk[0] + " "; }
                };
                break;
            case DModConsts.SS_ADJ_TP:
                registry = new DDbRegistrySysFly(type) {
                    @Override
                    public String getSqlTable() { return DModConsts.TablesMap.get(type); }
                    
                    @Override
                    public String getSqlWhere(int[] pk) { return "WHERE id_adj_tp = " + pk[0] + " "; }
                };
                break;
            case DModConsts.SS_MFG_TP:
                registry = new DDbRegistrySysFly(type) {
                    @Override
                    public String getSqlTable() { return DModConsts.TablesMap.get(type); }
                    
                    @Override
                    public String getSqlWhere(int[] pk) { return "WHERE id_mfg_tp = " + pk[0] + " "; }
                };
                break;
            case DModConsts.SU_WHS:
                registry = new DDbWarehouse();
                break;
            case DModConsts.S_WSD:
                registry = new DDbWsd();
                registry.postInitMembers(params);
                break;
            case DModConsts.S_WSD_ROW:
                registry = new DDbWsdRow();
                break;
            case DModConsts.S_STK:
                registry = new DDbStock();
                break;
            default:
                miClient.showMsgBoxError(DLibConsts.ERR_MSG_OPTION_UNKNOWN);
        }

        return registry;
    }

    @Override
    public DGuiCatalogueSettings getCatalogueSettings(final int type, final int subtype, final DGuiParams params) {
        String sql = "";
        DGuiCatalogueSettings settings = null;

        switch (type) {
            case DModConsts.SS_WHS_TP:
                settings = new DGuiCatalogueSettings("Tipo almacén", 1);
                sql = "SELECT id_whs_tp AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY sort ";
                break;
            case DModConsts.SS_MOV_CL:
                break;
            case DModConsts.SS_MOV_TP:
                break;
            case DModConsts.SS_TRN_TP:
                settings = new DGuiCatalogueSettings("Tipo mov. transacción", 1);
                sql = "SELECT id_trn_tp AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY sort ";
                break;
            case DModConsts.SS_ADJ_TP:
                settings = new DGuiCatalogueSettings("Tipo ajuste", 1);
                sql = "SELECT id_adj_tp AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY sort ";
                break;
            case DModConsts.SS_MFG_TP:
                settings = new DGuiCatalogueSettings("Tipo mov. producción", 1);
                sql = "SELECT id_mfg_tp AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY sort ";
                break;
            case DModConsts.SU_WHS:
                settings = new DGuiCatalogueSettings("Almacén", 1);
                sql = "SELECT id_whs AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY name, id_whs ";
                break;
            case DModConsts.S_WSD:
                break;
            case DModConsts.S_WSD_ROW:
                break;
            case DModConsts.S_STK:
                break;
            default:
                miClient.showMsgBoxError(DLibConsts.ERR_MSG_OPTION_UNKNOWN);
        }

        if (settings != null) {
            settings.setSql(sql);
        }

        return settings;
    }

    @Override
    public DGridPaneView getView(final int type, final int subtype, final DGuiParams params) {
        DGridPaneView view = null;
        String title = "";

        switch (type) {
            case DModConsts.SS_WHS_TP:
                break;
            case DModConsts.SS_MOV_CL:
                break;
            case DModConsts.SS_MOV_TP:
                break;
            case DModConsts.SS_TRN_TP:
                break;
            case DModConsts.SS_ADJ_TP:
                break;
            case DModConsts.SS_MFG_TP:
                break;
            case DModConsts.SU_WHS:
                view = new DViewWarehouse(miClient, "Almacenes");
                break;
            case DModConsts.S_WSD:
                title = (String) miClient.getSession().readField(DModConsts.SS_MOV_CL, new int[] { subtype }, DDbRegistry.FIELD_NAME);
                view = new DViewWsd(miClient, subtype, "Doctos. almacén " + title.toLowerCase());
                break;
            case DModConsts.S_WSD_ROW:
                break;
            case DModConsts.S_STK:
                switch (subtype) {
                    case DModSysConsts.SX_STK:
                        title = "Existencias";
                        break;
                    case DModSysConsts.SX_STK_LOT:
                        title = "Existencias lote";
                        break;
                    default:
                        miClient.showMsgBoxError(DLibConsts.ERR_MSG_OPTION_UNKNOWN);
                }
                view = new DViewStock(miClient, subtype, title);
                break;
            default:
                miClient.showMsgBoxError(DLibConsts.ERR_MSG_OPTION_UNKNOWN);
        }

        return view;
    }

    @Override
    public DGuiOptionPicker getOptionPicker(final int type, final int subtype, final DGuiParams params) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DGuiForm getForm(final int type, final int subtype, final DGuiParams params) {
        DGuiForm form = null;
        String title = "";

        switch (type) {
            case DModConsts.SS_WHS_TP:
                break;
            case DModConsts.SS_MOV_CL:
                break;
            case DModConsts.SS_MOV_TP:
                break;
            case DModConsts.SS_TRN_TP:
                break;
            case DModConsts.SS_ADJ_TP:
                break;
            case DModConsts.SS_MFG_TP:
                break;
            case DModConsts.SU_WHS:
                if (moFormWarehouse == null) moFormWarehouse = new DFormWarehouse(miClient, "Almacén");
                form = moFormWarehouse;
                break;
            case DModConsts.S_WSD:
                title = (String) miClient.getSession().readField(DModConsts.SS_MOV_CL, new int[] { subtype }, DDbRegistry.FIELD_NAME);
                switch (subtype) {
                    case DModSysConsts.SS_MOV_CL_IN:
                        if (moFormWsdIn == null) moFormWsdIn = new DFormWsd(miClient, subtype, "Docto. almacén " + title.toLowerCase());
                        form = moFormWsdIn;
                        break;
                    case DModSysConsts.SS_MOV_CL_OUT:
                        if (moFormWsdOut == null) moFormWsdOut = new DFormWsd(miClient, subtype, "Docto. almacén " + title.toLowerCase());
                        form = moFormWsdOut;
                        break;
                    default:
                }
                break;
            case DModConsts.S_WSD_ROW:
                break;
            case DModConsts.S_STK:
                break;
            default:
                miClient.showMsgBoxError(DLibConsts.ERR_MSG_OPTION_UNKNOWN);
        }

        return form;
    }

    @Override
    public DGuiReport getReport(final int type, final int subtype, final DGuiParams params) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
