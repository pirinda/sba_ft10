/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod;

import ft.mod.mfg.db.DDbDepart;
import ft.mod.mfg.db.DDbLine;
import ft.mod.mfg.form.DFormDepart;
import ft.mod.mfg.form.DFormLine;
import ft.mod.mfg.view.DViewDepart;
import ft.mod.mfg.view.DViewLine;
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
    //private DFormFormula moFormFormula;
    //private DFormJob moFormJob;

    public DModModuleMfg(DGuiClient client) {
        super(client, DModSysConsts.CS_MOD_MFG, DLibConsts.UNDEFINED);
    }

    @Override
    public JMenu[] getMenus() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DDbRegistry getRegistry(final int type) {
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
                break;
            case DModConsts.MU_FRM_CMP_FAM:
                break;
            case DModConsts.MU_FRM_CMP_ITM:
                break;
            case DModConsts.MU_FRM_BYP:
                break;
            case DModConsts.MU_VAR:
                break;
            case DModConsts.MU_VAR_FAM:
                break;
            case DModConsts.M_JOB:
                break;
            case DModConsts.M_JOB_REQ_FAM:
                break;
            case DModConsts.M_JOB_REQ_ITM:
                break;
            case DModConsts.M_JOB_CON:
                break;
            case DModConsts.M_JOB_MFG:
                break;
            case DModConsts.M_JOB_VAR:
                break;
            default:
        }
/*
        switch (type) {
            case DModConsts.MS_REF_TP:
                registry = new DDbRegistrySysFly(type) {
                    @Override
                    public String getSqlTable() { return DModConsts.TablesMap.get(type); }
                    
                    @Override
                    public String getSqlWhere(int[] pk) { return "WHERE id_ref_tp = " + pk[0] + " "; }
                };
                break;
            case DModConsts.MS_FRM_TP:
                registry = new DDbRegistrySysFly(type) {
                    @Override
                    public String getSqlTable() { return DModConsts.TablesMap.get(type); }
                    
                    @Override
                    public String getSqlWhere(int[] pk) { return "WHERE id_frm_tp = " + pk[0] + " "; }
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
                registry = new DDbDepartment();
                break;
            case DModConsts.MU_LIN_PCK:
                registry = new DDbLinePack();
                break;
            case DModConsts.MU_LIN_PCK_PRS:
                registry = new DDbLinePackPresent();
                break;
            case DModConsts.MU_LIN_PRP:
                registry = new DDbLinePrep();
                break;
            case DModConsts.MU_LIN_PRP_FAM:
                registry = new DDbLinePrepItemFamily();
                break;
            case DModConsts.MU_LIN_PRP_LIN_PCK:
                registry = new DDbLinePrepLinePack();
                break;
            case DModConsts.MU_FRM:
                registry = new DDbFormula();
                break;
            case DModConsts.MU_FRM_CMP:
                registry = new DDbFormulaComp();
                break;
            case DModConsts.M_YER:
                registry = new DDbYear();
                break;
            case DModConsts.M_YER_WEK:
                registry = new DDbYearWeek();
                break;
            case DModConsts.M_JOB:
                registry = new DDbJob();
                break;
            case DModConsts.M_JOB_PRP:
                registry = new DDbJobLinePrep();
                break;
            case DModConsts.M_JOB_PRP_REQ:
                registry = new DDbJobLinePrepRqmt();
                break;
            case DModConsts.M_JOB_PRP_CON:
                registry = new DDbJobLinePrepCons();
                break;
            case DModConsts.M_JOB_PRP_MFG:
                registry = new DDbJobLinePrepMfg();
                break;
            case DModConsts.M_JOB_PCK:
                registry = new DDbJobLinePack();
                break;
            case DModConsts.M_JOB_PCK_REQ:
                registry = new DDbJobLinePackRqmt();
                break;
            case DModConsts.M_JOB_PCK_CON:
                registry = new DDbJobLinePackCons();
                break;
            case DModConsts.M_JOB_PCK_MFG:
                registry = new DDbJobLinePackMfg();
                break;
            default:
                miClient.showMsgBoxError(DLibConsts.ERR_MSG_OPTION_UNKNOWN);
        }
*/
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
            case DModConsts.MS_JOB_TP:
                settings = new DGuiCatalogueSettings("Tipo orden", 1);
                sql = "SELECT id_job_tp AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY sort ";
                break;
            case DModConsts.MS_JOB_ST:
                settings = new DGuiCatalogueSettings("Estatus orden", 1);
                sql = "SELECT id_job_st AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY sort ";
                break;
            case DModConsts.MU_DPT:
                settings = new DGuiCatalogueSettings("Departamento producción", 1);
                sql = "SELECT id_dpt AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY name, id_dpt ";
                break;
            case DModConsts.MU_LIN:
                settings = new DGuiCatalogueSettings("Línea producción", 1, 1);
                sql = "SELECT id_lin AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + ", fk_dpt AS " + DDbConsts.FIELD_FK + "1 " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY fk_dpt, name, id_lin ";
                break;
            case DModConsts.MU_FRM:
                break;
            case DModConsts.MU_FRM_CMP_FAM:
                break;
            case DModConsts.MU_FRM_CMP_ITM:
                break;
            case DModConsts.MU_FRM_BYP:
                break;
            case DModConsts.MU_VAR:
                break;
            case DModConsts.MU_VAR_FAM:
                break;
            case DModConsts.M_JOB:
                break;
            case DModConsts.M_JOB_REQ_FAM:
                break;
            case DModConsts.M_JOB_REQ_ITM:
                break;
            case DModConsts.M_JOB_CON:
                break;
            case DModConsts.M_JOB_MFG:
                break;
            case DModConsts.M_JOB_VAR:
                break;
            default:
        }
/*
        switch (type) {
            case DModConsts.MS_REF_TP:
                settings = new DGuiCatalogueSettings("Tipo referencia", 1);
                sql = "SELECT id_ref_tp AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY sort ";
                break;
            case DModConsts.MS_FRM_TP:
                settings = new DGuiCatalogueSettings("Tipo fórmula", 1);
                sql = "SELECT id_frm_tp AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY sort ";
                break;
            case DModConsts.MS_JOB_TP:
                settings = new DGuiCatalogueSettings("Tipo orden", 1);
                sql = "SELECT id_job_tp AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY sort ";
                break;
            case DModConsts.MS_JOB_ST:
                settings = new DGuiCatalogueSettings("Estatus orden", 1);
                sql = "SELECT id_job_st AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY sort ";
                break;
            case DModConsts.MU_DPT:
                settings = new DGuiCatalogueSettings("Departamento", 1);
                sql = "SELECT id_dpt AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY name, id_dpt ";
                break;
            case DModConsts.MU_LIN_PCK:
                settings = new DGuiCatalogueSettings("Línea envasado", 1, 0, DLibConsts.DATA_TYPE_TEXT);
                sql = "SELECT id_lin_pck AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + ", code AS " + DDbConsts.FIELD_COMP + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY name, id_lin_pck ";
                break;
            case DModConsts.MU_LIN_PCK_PRS:
                break;
            case DModConsts.MU_LIN_PRP:
                settings = new DGuiCatalogueSettings("Línea preparación", 1, 0, DLibConsts.DATA_TYPE_TEXT);
                sql = "SELECT id_lin_prp AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + ", code AS " + DDbConsts.FIELD_COMP + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY name, id_lin_prp ";
                break;
            case DModConsts.MU_LIN_PRP_FAM:
                break;
            case DModConsts.MU_LIN_PRP_LIN_PCK:
                break;
            case DModConsts.MU_FRM:
                break;
            case DModConsts.MU_FRM_CMP:
                break;
            case DModConsts.M_YER:
                break;
            case DModConsts.M_YER_WEK:
                break;
            case DModConsts.M_JOB:
                break;
            case DModConsts.M_JOB_PRP:
                break;
            case DModConsts.M_JOB_PRP_REQ:
                break;
            case DModConsts.M_JOB_PRP_CON:
                break;
            case DModConsts.M_JOB_PRP_MFG:
                break;
            case DModConsts.M_JOB_PCK:
                break;
            case DModConsts.M_JOB_PCK_REQ:
                break;
            case DModConsts.M_JOB_PCK_CON:
                break;
            case DModConsts.M_JOB_PCK_MFG:
                break;
            case DModConsts.MX_LIN_PRP_BY_FAM:
                settings = new DGuiCatalogueSettings("Línea preparación", 1, 0, DLibConsts.DATA_TYPE_TEXT);
                sql = "SELECT lp.id_lin_prp AS " + DDbConsts.FIELD_ID + "1, lp.name AS " + DDbConsts.FIELD_ITEM + ", lp.code AS " + DDbConsts.FIELD_COMP + " " +
                        "FROM " + DModConsts.TablesMap.get(DModConsts.MU_LIN_PRP) + " AS lp " +
                        "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.MU_LIN_PRP_FAM) + " lpf ON " +
                        "lp.id_lin_prp = lpf.id_lin_prp AND lpf.id_fam = " + params.getKey()[0] + " " +
                        "WHERE lp.b_del = 0 ORDER BY lp.name, lp.id_lin_prp ";
                break;
            case DModConsts.MX_LIN_PCK_BY_LIN_PRP:
                settings = new DGuiCatalogueSettings("Línea envasado", 1, 0, DLibConsts.DATA_TYPE_TEXT);
                sql = "SELECT lp.id_lin_pck AS " + DDbConsts.FIELD_ID + "1, lp.name AS " + DDbConsts.FIELD_ITEM + ", lp.code AS " + DDbConsts.FIELD_COMP + " " +
                        "FROM " + DModConsts.TablesMap.get(DModConsts.MU_LIN_PCK) + " AS lp " +
                        "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.MU_LIN_PRP_LIN_PCK) + " AS lplp ON " +
                        "lp.id_lin_pck = lplp.id_lin_pck AND lplp.id_lin_prp = " + params.getKey()[0] + " " +
                        "WHERE lp.b_del = 0 ORDER BY lp.name, lp.id_lin_pck ";
                break;
            case DModConsts.MX_PF_BY_PB_N_LIN_PCK:
                settings = new DGuiCatalogueSettings("Ítem", 1, 0, DLibConsts.DATA_TYPE_TEXT);
                sql = "SELECT i.id_itm AS " + DDbConsts.FIELD_ID + "1, i.name AS " + DDbConsts.FIELD_ITEM + ", u.code AS " + DDbConsts.FIELD_COMP + " " +
                        "FROM " + DModConsts.TablesMap.get(DModConsts.CU_ITM) + " AS i " +
                        "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_UOM) + " AS u ON i.fk_uom = u.id_uom " +
                        "WHERE i.b_del = 0 AND i.id_itm IN (" +
                            "SELECT f.fk_itm " +
                            "FROM " + DModConsts.TablesMap.get(DModConsts.MU_FRM) + " AS f " +
                            "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.MU_FRM_CMP) + " AS fc ON " +
                            "f.id_frm = fc.id_frm AND " +
                            "f.fk_itm_tp = " + DModSysConsts.CS_ITM_TP_PF + " AND fc.fk_itm_tp = " + DModSysConsts.CS_ITM_TP_PB + " AND " +
                            "fc.fk_itm = " + params.getParamsMap().get(DModConsts.CU_ITM) + " AND f.fk_prs IN (" +
                                "SELECT lpp.id_prs " +
                                "FROM " + DModConsts.TablesMap.get(DModConsts.MU_LIN_PCK_PRS) + " AS lpp " +
                                "WHERE lpp.id_lin_pck = " + params.getParamsMap().get(DModConsts.MU_LIN_PCK) + " " +
                                "ORDER BY lpp.id_prs " +
                            ") " +
                            "ORDER BY f.fk_itm " +
                        ") " +
                        "ORDER BY i.name, i.id_itm ";
                break;
            case DModConsts.MX_FRM_BY_ITM:
                settings = new DGuiCatalogueSettings("Fórmula", 1);
                sql = "SELECT f.id_frm AS " + DDbConsts.FIELD_ID + "1, CONCAT(f.name, ' (', ft.code, ')') AS " + DDbConsts.FIELD_ITEM + " " +
                        "FROM " + DModConsts.TablesMap.get(DModConsts.MU_FRM) + " AS f " +
                        "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.MS_FRM_TP) + " AS ft ON f.fk_frm_tp = ft.id_frm_tp " +
                        "WHERE f.b_del = 0 AND f.fk_itm = " + params.getKey()[0] + " ORDER BY f.name, f.id_frm ";
                break;
            default:
                miClient.showMsgBoxError(DLibConsts.ERR_MSG_OPTION_UNKNOWN);
        }
*/

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
            case DModConsts.MS_JOB_TP:
                break;
            case DModConsts.MS_JOB_ST:
                break;
            case DModConsts.MU_DPT:
                view = new DViewDepart(miClient, "Departamentos producción");
                break;
            case DModConsts.MU_LIN:
                view = new DViewLine(miClient, "Líneas producción");
                break;
            case DModConsts.MU_FRM:
                break;
            case DModConsts.MU_FRM_CMP_FAM:
                break;
            case DModConsts.MU_FRM_CMP_ITM:
                break;
            case DModConsts.MU_FRM_BYP:
                break;
            case DModConsts.MU_VAR:
                break;
            case DModConsts.MU_VAR_FAM:
                break;
            case DModConsts.M_JOB:
                break;
            case DModConsts.M_JOB_REQ_FAM:
                break;
            case DModConsts.M_JOB_REQ_ITM:
                break;
            case DModConsts.M_JOB_CON:
                break;
            case DModConsts.M_JOB_MFG:
                break;
            case DModConsts.M_JOB_VAR:
                break;
            default:
        }
/*
        switch (type) {
            case DModConsts.MS_REF_TP:
                break;
            case DModConsts.MS_FRM_TP:
                break;
            case DModConsts.MS_JOB_TP:
                break;
            case DModConsts.MS_JOB_ST:
                break;
            case DModConsts.MU_DPT:
                view = new DViewDepartment(miClient, "Departamentos");
                break;
            case DModConsts.MU_LIN_PCK:
                view = new DViewLinePack(miClient, "Líneas envasado");
                break;
            case DModConsts.MU_LIN_PCK_PRS:
                break;
            case DModConsts.MU_LIN_PRP:
                view = new DViewLinePrep(miClient, "Líneas preparación");
                break;
            case DModConsts.MU_LIN_PRP_FAM:
                break;
            case DModConsts.MU_LIN_PRP_LIN_PCK:
                break;
            case DModConsts.MU_FRM:
                view = new DViewFormula(miClient, "Fórmulas");
                break;
            case DModConsts.MU_FRM_CMP:
                view = new DViewFormulaComp(miClient, "Fórmulas componentes");
                break;
            case DModConsts.M_YER:
                view = new DViewYear(miClient, "Años");
                break;
            case DModConsts.M_YER_WEK:
                view = new DViewYearWeek(miClient, "Años semanas");
                break;
            case DModConsts.M_JOB:
                view = new DViewJob(miClient, "Órdenes producción");
                break;
            case DModConsts.M_JOB_PRP:
                break;
            case DModConsts.M_JOB_PRP_REQ:
                break;
            case DModConsts.M_JOB_PRP_CON:
                break;
            case DModConsts.M_JOB_PRP_MFG:
                break;
            case DModConsts.M_JOB_PCK:
                break;
            case DModConsts.M_JOB_PCK_REQ:
                break;
            case DModConsts.M_JOB_PCK_CON:
                break;
            case DModConsts.M_JOB_PCK_MFG:
                break;
            default:
                miClient.showMsgBoxError(DLibConsts.ERR_MSG_OPTION_UNKNOWN);
        }
*/
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
            case DModConsts.MS_JOB_TP:
                break;
            case DModConsts.MS_JOB_ST:
                break;
            case DModConsts.MU_DPT:
                if (moFormDepart == null) moFormDepart = new DFormDepart(miClient, "Departamento de producción");
                form = moFormDepart;
                break;
            case DModConsts.MU_LIN:
                if (moFormLine == null) moFormLine = new DFormLine(miClient, "Línea de producción");
                form = moFormLine;
                break;
            case DModConsts.MU_FRM:
                break;
            case DModConsts.MU_FRM_CMP_FAM:
                break;
            case DModConsts.MU_FRM_CMP_ITM:
                break;
            case DModConsts.MU_FRM_BYP:
                break;
            case DModConsts.MU_VAR:
                break;
            case DModConsts.MU_VAR_FAM:
                break;
            case DModConsts.M_JOB:
                break;
            case DModConsts.M_JOB_REQ_FAM:
                break;
            case DModConsts.M_JOB_REQ_ITM:
                break;
            case DModConsts.M_JOB_CON:
                break;
            case DModConsts.M_JOB_MFG:
                break;
            case DModConsts.M_JOB_VAR:
                break;
            default:
        }
/*
        switch (type) {
            case DModConsts.MS_REF_TP:
                break;
            case DModConsts.MS_FRM_TP:
                break;
            case DModConsts.MS_JOB_TP:
                break;
            case DModConsts.MS_JOB_ST:
                break;
            case DModConsts.MU_DPT:
                if (moFormDepartment == null) moFormDepartment = new DFormDepartment(miClient, "Departamento");
                form = moFormDepartment;
                break;
            case DModConsts.MU_LIN_PCK:
                if (moFormLinePack == null) moFormLinePack = new DFormLinePack(miClient, "Línea envasado");
                form = moFormLinePack;
                break;
            case DModConsts.MU_LIN_PCK_PRS:
                break;
            case DModConsts.MU_LIN_PRP:
                if (moFormLinePrep == null) moFormLinePrep = new DFormLinePrep(miClient, "Línea preparación");
                form = moFormLinePrep;
                break;
            case DModConsts.MU_LIN_PRP_FAM:
                break;
            case DModConsts.MU_LIN_PRP_LIN_PCK:
                break;
            case DModConsts.MU_FRM:
                if (moFormFormula == null) moFormFormula = new DFormFormula(miClient, "Fórmula");
                form = moFormFormula;
                break;
            case DModConsts.MU_FRM_CMP:
                break;
            case DModConsts.M_YER:
                if (moFormYear == null) moFormYear = new DFormYear(miClient, "Año");
                form = moFormYear;
                break;
            case DModConsts.M_YER_WEK:
                break;
            case DModConsts.M_JOB:
                if (moFormJob == null) moFormJob = new DFormJob(miClient, "Orden producción");
                form = moFormJob;
                break;
            case DModConsts.M_JOB_PRP:
                break;
            case DModConsts.M_JOB_PRP_REQ:
                break;
            case DModConsts.M_JOB_PRP_CON:
                break;
            case DModConsts.M_JOB_PRP_MFG:
                break;
            case DModConsts.M_JOB_PCK:
                break;
            case DModConsts.M_JOB_PCK_REQ:
                break;
            case DModConsts.M_JOB_PCK_CON:
                break;
            case DModConsts.M_JOB_PCK_MFG:
                break;
            default:
                miClient.showMsgBoxError(DLibConsts.ERR_MSG_OPTION_UNKNOWN);
        }
*/
        return form;
    }

    @Override
    public DGuiReport getReport(final int type, final int subtype, final DGuiParams params) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
