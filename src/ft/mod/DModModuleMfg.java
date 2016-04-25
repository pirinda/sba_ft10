/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod;

import ft.mod.mfg.db.DDbDepart;
import ft.mod.mfg.db.DDbFormula;
import ft.mod.mfg.db.DDbFormulaByProd;
import ft.mod.mfg.db.DDbFormulaComp;
import ft.mod.mfg.db.DDbJob;
import ft.mod.mfg.db.DDbJobConsump;
import ft.mod.mfg.db.DDbJobMfgProd;
import ft.mod.mfg.db.DDbJobReqment;
import ft.mod.mfg.db.DDbLine;
import ft.mod.mfg.db.DDbVariable;
import ft.mod.mfg.db.DDbVariableFamily;
import ft.mod.mfg.form.DFormDepart;
import ft.mod.mfg.form.DFormFormula;
import ft.mod.mfg.form.DFormJob;
import ft.mod.mfg.form.DFormLine;
import ft.mod.mfg.form.DFormVariable;
import ft.mod.mfg.view.DViewDepart;
import ft.mod.mfg.view.DViewFormula;
import ft.mod.mfg.view.DViewFormulaComp;
import ft.mod.mfg.view.DViewJob;
import ft.mod.mfg.view.DViewLine;
import ft.mod.mfg.view.DViewVariable;
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
public class DModModuleMfg extends DGuiModule {
    
    private DFormDepart moFormDepart;
    private DFormLine moFormLine;
    private DFormFormula moFormFormula;
    private DFormVariable moFormVariable;
    private DFormJob moFormJob;

    public DModModuleMfg(DGuiClient client) {
        super(client, DModSysConsts.CS_MOD_MFG, DLibConsts.UNDEFINED);
    }

    @Override
    public JMenu[] getMenus() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DDbRegistry getRegistry(final int type, DGuiParams params) {
        DDbRegistry registry = null;
        
        switch (type) {
            case DModConsts.MS_FRM_TP:
                registry = new DDbRegistrySysFly(type) {
                    @Override
                    public String getSqlTable() { return DModConsts.TablesMap.get(type); }
                    
                    @Override
                    public String getSqlWhere(int[] pk) { return "WHERE id_frm_tp = " + pk[0] + " "; }
                };
                break;
            case DModConsts.MS_CMP_TP:
                registry = new DDbRegistrySysFly(type) {
                    @Override
                    public String getSqlTable() { return DModConsts.TablesMap.get(type); }
                    
                    @Override
                    public String getSqlWhere(int[] pk) { return "WHERE id_cmp_tp = " + pk[0] + " "; }
                };
                break;
            case DModConsts.MS_JOB_TP:
                registry = new DDbRegistrySysFly(type) {
                    @Override
                    public String getSqlTable() { return DModConsts.TablesMap.get(type); }
                    
                    @Override
                    public String getSqlWhere(int[] pk) { return "WHERE id_job_tp = " + pk[0] + " "; }
                };
                break;
            case DModConsts.MS_JOB_ST:
                registry = new DDbRegistrySysFly(type) {
                    @Override
                    public String getSqlTable() { return DModConsts.TablesMap.get(type); }
                    
                    @Override
                    public String getSqlWhere(int[] pk) { return "WHERE id_job_st = " + pk[0] + " "; }
                };
                break;
            case DModConsts.MU_DPT:
                registry = new DDbDepart();
                break;
            case DModConsts.MU_LIN:
                registry = new DDbLine();
                break;
            case DModConsts.MU_FRM:
                registry = new DDbFormula();
                break;
            case DModConsts.MU_FRM_CMP:
                registry = new DDbFormulaComp();
                break;
            case DModConsts.MU_FRM_BYP:
                registry = new DDbFormulaByProd();
                break;
            case DModConsts.MU_VAR:
                registry = new DDbVariable();
                break;
            case DModConsts.MU_VAR_FAM:
                registry = new DDbVariableFamily();
                break;
            case DModConsts.M_JOB:
                registry = new DDbJob();
                if (params == null) {
                    ((DDbJob) registry).setFkJobTypeId(DModSysConsts.MS_JOB_TP_SMP);
                }
                else {
                    ((DDbJob) registry).setFkJobTypeId(params.getType());
                }
                break;
            case DModConsts.M_JOB_REQ:
                registry = new DDbJobReqment();
                break;
            case DModConsts.M_JOB_CON:
                registry = new DDbJobConsump();
                break;
            case DModConsts.M_JOB_MFG:
                registry = new DDbJobMfgProd();
                break;
            case DModConsts.M_JOB_VAR:
                //registry = new DDbJobVariable();
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
            case DModConsts.MS_FRM_TP:
                settings = new DGuiCatalogueSettings("Tipo fórmula", 1);
                sql = "SELECT id_frm_tp AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY sort ";
                break;
            case DModConsts.MS_CMP_TP:
                settings = new DGuiCatalogueSettings("Tipo componente", 1);
                sql = "SELECT id_cmp_tp AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY sort ";
                break;
            case DModConsts.MS_JOB_TP:
                settings = new DGuiCatalogueSettings("Tipo orden producción", 1);
                sql = "SELECT id_job_tp AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY sort ";
                break;
            case DModConsts.MS_JOB_ST:
                settings = new DGuiCatalogueSettings("Estatus orden producción", 1);
                sql = "SELECT id_job_st AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY sort ";
                break;
            case DModConsts.MU_DPT:
                settings = new DGuiCatalogueSettings("Depto. producción", 1);
                settings.setCodeApplying(true);
                sql = "SELECT id_dpt AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + ", code AS " + DDbConsts.FIELD_CODE + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY name, id_dpt ";
                break;
            case DModConsts.MU_LIN:
                settings = new DGuiCatalogueSettings("Línea producción", 1, 1);
                settings.setCodeApplying(true);
                sql = "SELECT id_lin AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + ", code AS " + DDbConsts.FIELD_CODE + ", fk_dpt AS " + DDbConsts.FIELD_FK + "1 " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY fk_dpt, name, id_lin ";
                break;
            case DModConsts.MU_FRM:
                settings = new DGuiCatalogueSettings("Fórmula", 1, 1);
                settings.setCodeApplying(true);
                sql = "SELECT id_frm AS " + DDbConsts.FIELD_ID + "1, CONCAT(name, IF(ref = '', '', CONCAT(' (', ref, ')'))) AS " + DDbConsts.FIELD_ITEM + ", code AS " + DDbConsts.FIELD_CODE + ", fk_itm AS " + DDbConsts.FIELD_FK + "1 " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY fk_itm, name, id_frm ";
                break;
            case DModConsts.MU_FRM_CMP:
                break;
            case DModConsts.MU_FRM_BYP:
                break;
            case DModConsts.MU_VAR:
                settings = new DGuiCatalogueSettings("Variable producción", 1);
                settings.setCodeApplying(true);
                sql = "SELECT id_var AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + ", code AS " + DDbConsts.FIELD_CODE + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY name, id_var ";
                break;
            case DModConsts.MU_VAR_FAM:
                break;
            case DModConsts.M_JOB:
                break;
            case DModConsts.M_JOB_REQ:
                break;
            case DModConsts.M_JOB_CON:
                break;
            case DModConsts.M_JOB_MFG:
                break;
            case DModConsts.M_JOB_VAR:
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
            case DModConsts.MS_FRM_TP:
                break;
            case DModConsts.MS_CMP_TP:
                break;
            case DModConsts.MS_JOB_TP:
                break;
            case DModConsts.MS_JOB_ST:
                break;
            case DModConsts.MU_DPT:
                view = new DViewDepart(miClient, "Deptos. producción");
                break;
            case DModConsts.MU_LIN:
                view = new DViewLine(miClient, "Líneas producción");
                break;
            case DModConsts.MU_FRM:
                view = new DViewFormula(miClient, "Fórmulas");
                break;
            case DModConsts.MU_FRM_CMP:
                view = new DViewFormulaComp(miClient, "Fórmulas componentes");
                break;
            case DModConsts.MU_FRM_BYP:
                break;
            case DModConsts.MU_VAR:
                view = new DViewVariable(miClient, "Variables producción");
                break;
            case DModConsts.MU_VAR_FAM:
                break;
            case DModConsts.M_JOB:
                view = new DViewJob(miClient, "Órdenes producción");
                break;
            case DModConsts.M_JOB_REQ:
                break;
            case DModConsts.M_JOB_CON:
                break;
            case DModConsts.M_JOB_MFG:
                break;
            case DModConsts.M_JOB_VAR:
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
            case DModConsts.MS_FRM_TP:
                break;
            case DModConsts.MS_CMP_TP:
                break;
            case DModConsts.MS_JOB_TP:
                break;
            case DModConsts.MS_JOB_ST:
                break;
            case DModConsts.MU_DPT:
                if (moFormDepart == null) moFormDepart = new DFormDepart(miClient, "Depto. de producción");
                form = moFormDepart;
                break;
            case DModConsts.MU_LIN:
                if (moFormLine == null) moFormLine = new DFormLine(miClient, "Línea de producción");
                form = moFormLine;
                break;
            case DModConsts.MU_FRM:
                if (moFormFormula == null) moFormFormula = new DFormFormula(miClient, "Fórmula");
                form = moFormFormula;
                break;
            case DModConsts.MU_FRM_CMP:
                break;
            case DModConsts.MU_FRM_BYP:
                break;
            case DModConsts.MU_VAR:
                if (moFormVariable == null) moFormVariable = new DFormVariable(miClient, "Variable de producción");
                form = moFormVariable;
                break;
            case DModConsts.MU_VAR_FAM:
                break;
            case DModConsts.M_JOB:
                if (moFormJob == null) moFormJob = new DFormJob(miClient, "Orden de producción");
                form = moFormJob;
                break;
            case DModConsts.M_JOB_REQ:
                break;
            case DModConsts.M_JOB_CON:
                break;
            case DModConsts.M_JOB_MFG:
                break;
            case DModConsts.M_JOB_VAR:
                break;
            default:
        }
        
        return form;
    }

    @Override
    public DGuiReport getReport(final int type, final int subtype, final DGuiParams params) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
