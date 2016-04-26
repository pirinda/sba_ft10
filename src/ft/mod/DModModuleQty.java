/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod;

import ft.mod.qty.db.DDbTest;
import ft.mod.qty.db.DDbTestApp;
import ft.mod.qty.db.DDbTestAppResult;
import ft.mod.qty.db.DDbTestFamily;
import ft.mod.qty.db.DDbTestVariable;
import ft.mod.qty.db.DDbVariable;
import ft.mod.qty.form.DFormTest;
import ft.mod.qty.form.DFormVariable;
import ft.mod.qty.view.DViewTest;
import ft.mod.qty.view.DViewTestApp;
import ft.mod.qty.view.DViewTestAppResult;
import ft.mod.qty.view.DViewVariable;
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
public class DModModuleQty extends DGuiModule {
    
    private DFormVariable moFormVariable;
    private DFormTest moFormTest;
    
    public DModModuleQty(DGuiClient client) {
        super(client, DModSysConsts.CS_MOD_QTY, DLibConsts.UNDEFINED);
    }

    @Override
    public JMenu[] getMenus() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DDbRegistry getRegistry(final int type, DGuiParams params) {
        DDbRegistry registry = null;

        switch (type) {
            case DModConsts.QS_TST_TP:
                registry = new DDbRegistrySysFly(type) {
                    @Override
                    public String getSqlTable() { return DModConsts.TablesMap.get(type); }
                    
                    @Override
                    public String getSqlWhere(int[] pk) { return "WHERE id_tst_tp = " + pk[0] + " "; }
                };
                break;
            case DModConsts.QU_VAR:
                registry = new DDbVariable();
                break;
            case DModConsts.QU_TST:
                registry = new DDbTest();
                break;
            case DModConsts.QU_TST_VAR:
                registry = new DDbTestVariable();
                break;
            case DModConsts.QU_TST_FAM:
                registry = new DDbTestFamily();
                break;
            case DModConsts.Q_APP:
                registry = new DDbTestApp();
                break;
            case DModConsts.Q_APP_RES:
                registry = new DDbTestAppResult();
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
            case DModConsts.QS_TST_TP:
                settings = new DGuiCatalogueSettings("Tipo test calidad", 1);
                sql = "SELECT id_tst_tp AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY sort ";
                break;
            case DModConsts.QU_VAR:
                settings = new DGuiCatalogueSettings("Variable calidad", 1);
                settings.setCodeApplying(true);
                sql = "SELECT id_var AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + ", code AS " + DDbConsts.FIELD_CODE + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY name, id_var ";
                break;
            case DModConsts.QU_TST:
                break;
            case DModConsts.QU_TST_VAR:
                break;
            case DModConsts.QU_TST_FAM:
                break;
            case DModConsts.Q_APP:
                break;
            case DModConsts.Q_APP_RES:
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

        switch (type) {
            case DModConsts.QS_TST_TP:
                break;
            case DModConsts.QU_VAR:
                view = new DViewVariable(miClient, "Variables calidad");
                break;
            case DModConsts.QU_TST:
                view = new DViewTest(miClient, "Tests calidad");
                break;
            case DModConsts.QU_TST_VAR:
                break;
            case DModConsts.QU_TST_FAM:
                break;
            case DModConsts.Q_APP:
                view = new DViewTestApp(miClient, "Aplicaciones tests calidad");
                break;
            case DModConsts.Q_APP_RES:
                view = new DViewTestAppResult(miClient, "Resultados tests calidad");
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

        switch (type) {
            case DModConsts.QS_TST_TP:
                break;
            case DModConsts.QU_VAR:
                if (moFormVariable == null) moFormVariable = new DFormVariable(miClient, "Variable calidad");
                form = moFormVariable;
                break;
            case DModConsts.QU_TST:
                if (moFormTest == null) moFormTest = new DFormTest(miClient, "Test calidad");
                form = moFormTest;
                break;
            case DModConsts.QU_TST_VAR:
                break;
            case DModConsts.QU_TST_FAM:
                break;
            case DModConsts.Q_APP:
                break;
            case DModConsts.Q_APP_RES:
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
