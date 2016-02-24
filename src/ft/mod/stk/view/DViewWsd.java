/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.stk.view;

import ft.mod.DModConsts;
import ft.mod.DModSysConsts;
import ft.mod.cfg.db.DCfgUtils;
import ft.mod.stk.db.DDbWsd;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import sba.gui.util.DUtilConsts;
import sba.lib.db.DDbConsts;
import sba.lib.db.DDbRegistry;
import sba.lib.grid.DGridColumnView;
import sba.lib.grid.DGridConsts;
import sba.lib.grid.DGridPaneSettings;
import sba.lib.grid.DGridPaneView;
import sba.lib.grid.DGridUtils;
import sba.lib.gui.DGuiClient;
import sba.lib.gui.DGuiConsts;
import sba.lib.gui.DGuiParams;

/**
 *
 * @author Sergio Flores
 */
public class DViewWsd extends DGridPaneView implements ActionListener {
    
    private JButton mjNewSal;
    private JButton mjNewPur;
    private JButton mjNewAdj;

    public DViewWsd(DGuiClient client, int moveClass, String title) {
        super(client, DGridConsts.GRID_VIEW_TAB, DModConsts.S_WSD, moveClass, title);
        setRowButtonsEnabled(false, true, true, false, true);
        
        mjNewSal = DGridUtils.createButton(new ImageIcon(getClass().getResource("/ft/gui/img/ico_new_sal.gif")), 
                DUtilConsts.TXT_CREATE + " " + ((String) miClient.getSession().readField(DModConsts.SS_MOV_TP, 
                        new int[] { mnGridSubtype, DModSysConsts.SX_MOV_TP_SAL }, DDbRegistry.FIELD_NAME)).toLowerCase(), this);
        mjNewPur = DGridUtils.createButton(new ImageIcon(getClass().getResource("/ft/gui/img/ico_new_pur.gif")), 
                DUtilConsts.TXT_CREATE + " " + ((String) miClient.getSession().readField(DModConsts.SS_MOV_TP, 
                        new int[] { mnGridSubtype, DModSysConsts.SX_MOV_TP_PUR }, DDbRegistry.FIELD_NAME)).toLowerCase(), this);
        mjNewAdj = DGridUtils.createButton(new ImageIcon(getClass().getResource("/ft/gui/img/ico_new_adj.gif")), 
                DUtilConsts.TXT_CREATE + " " + ((String) miClient.getSession().readField(DModConsts.SS_MOV_TP, 
                        new int[] { mnGridSubtype, DModSysConsts.SX_MOV_TP_ADJ }, DDbRegistry.FIELD_NAME)).toLowerCase(), this);
        
        getPanelCommandsSys(DGuiConsts.PANEL_CENTER).add(mjNewSal);
        getPanelCommandsSys(DGuiConsts.PANEL_CENTER).add(mjNewPur);
        getPanelCommandsSys(DGuiConsts.PANEL_CENTER).add(mjNewAdj);
    }

    @Override
    public void prepareSqlQuery() {
        String sql = "";
        Object filter = null;

        moPaneSettings = new DGridPaneSettings(1);
        moPaneSettings.setDeletedApplying(true);
        moPaneSettings.setSystemApplying(true);
        moPaneSettings.setUserInsertApplying(true);
        moPaneSettings.setUserUpdateApplying(true);

        filter = (Boolean) moFiltersMap.get(DGridConsts.FILTER_DELETED);
        if ((Boolean) filter) {
            sql += (sql.length() == 0 ? "" : "AND ") + "v.b_del = 0 ";
        }

        msSql = "SELECT " +
                "v.id_wsd AS " + DDbConsts.FIELD_ID + "1, " +
                "CONCAT(movt.code, '-', v.num) AS " + DDbConsts.FIELD_CODE + ", " +
                "CONCAT(movt.code, '-', v.num) AS " + DDbConsts.FIELD_NAME + ", " +
                "v.num, " +
                "v.dat, " +
                "v.ref, " +
                "v.amt_r, " +
                "v.mass_r, " +
                "movt.code, " +
                "movt.name, " +
                "trnt.code, " +
                "trnt.name, " +
                "mfgt.code, " +
                "mfgt.name, " +
                "adjt.code, " +
                "adjt.name, " +
                "whs.code, " +
                "whs.name, " +
                "CONCAT(wsdmovt.code, '-', wsd.num) AS f_wsd, " +
                "wsdmovt.code, " +
                "wsdmovt.name, " +
                "bpr.code, " +
                "bpr.name, " +
                "bpr.tax_id, " +
                "dpt.code, " +
                "dpt.name, " +
                "lin.code, " +
                "lin.name, " +
                "job.num, " +
                "v.b_del AS " + DDbConsts.FIELD_IS_DEL + ", " +
                "v.b_sys AS " + DDbConsts.FIELD_IS_SYS + ", " +
                "v.fk_usr_ins AS " + DDbConsts.FIELD_USER_INS_ID + ", " +
                "v.fk_usr_upd AS " + DDbConsts.FIELD_USER_UPD_ID + ", " +
                "v.ts_usr_ins AS " + DDbConsts.FIELD_USER_INS_TS + ", " +
                "v.ts_usr_upd AS " + DDbConsts.FIELD_USER_UPD_TS + ", " +
                "ui.name AS " + DDbConsts.FIELD_USER_INS_NAME + ", " +
                "uu.name AS " + DDbConsts.FIELD_USER_UPD_NAME + " " +
                "FROM " + DModConsts.TablesMap.get(DModConsts.S_WSD) + " AS v " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.SS_MOV_TP) + " AS movt ON " +
                "v.fk_mov_cl = movt.id_mov_cl AND v.fk_mov_tp = movt.id_mov_tp " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.SS_TRN_TP) + " AS trnt ON " +
                "v.fk_trn_tp = trnt.id_trn_tp " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.SS_MFG_TP) + " AS mfgt ON " +
                "v.fk_mfg_tp = mfgt.id_mfg_tp " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.SS_ADJ_TP) + " AS adjt ON " +
                "v.fk_adj_tp = adjt.id_adj_tp " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.SU_WHS) + " AS whs ON " +
                "v.fk_whs = whs.id_whs " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_USR) + " AS ui ON " +
                "v.fk_usr_ins = ui.id_usr " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_USR) + " AS uu ON " +
                "v.fk_usr_upd = uu.id_usr " +
                "LEFT OUTER JOIN " + DModConsts.TablesMap.get(DModConsts.S_WSD) + " AS wsd ON " +
                "v.fk_wsd_n = wsd.id_wsd " +
                "LEFT OUTER JOIN " + DModConsts.TablesMap.get(DModConsts.SS_MOV_TP) + " AS wsdmovt ON " +
                "wsd.fk_mov_cl = wsdmovt.id_mov_cl AND wsd.fk_mov_tp = wsdmovt.id_mov_tp " +
                "LEFT OUTER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_BPR) + " AS bpr ON " +
                "v.fk_bpr_n = bpr.id_bpr " +
                "LEFT OUTER JOIN " + DModConsts.TablesMap.get(DModConsts.MU_DPT) + " AS dpt ON " +
                "v.fk_dpt_n = dpt.id_dpt " +
                "LEFT OUTER JOIN " + DModConsts.TablesMap.get(DModConsts.MU_LIN) + " AS lin ON " +
                "v.fk_lin_n = lin.id_lin " +
                "LEFT OUTER JOIN " + DModConsts.TablesMap.get(DModConsts.M_JOB) + " AS job ON " +
                "v.fk_job_n = job.id_job " +
                "WHERE v.fk_mov_cl = " + mnGridSubtype + " " +
                (sql.length() == 0 ? "" : "AND " + sql) +
                "ORDER BY movt.code, movt.id_mov_cl, movt.id_mov_tp, v.num, v.id_wsd ";
    }

    @Override
    public void createGridColumns() {
        int col = 0;
        DGridColumnView[] columns = new DGridColumnView[22];

        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_CAT, "movt.code", DGridConsts.COL_TITLE_TYPE + " doc");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_REG_NUM, "v.num", DGridConsts.COL_TITLE_NUM + " doc");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_DATE, "v.dat", DGridConsts.COL_TITLE_DATE + " doc");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_DEC_AMT, "v.amt_r", "Valor $");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_DEC_QTY, "v.mass_r", "Masa (" + DCfgUtils.getSystemUnitCodeMass(miClient.getSession()) + ")");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_CAT_S, "whs.name", "Almacén");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_CAT_S, "movt.name", DGridConsts.COL_TITLE_TYPE + " mov almacén");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_CAT_S, "trnt.name", DGridConsts.COL_TITLE_TYPE + " mov transacción");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_CAT_S, "mfgt.name", DGridConsts.COL_TITLE_TYPE + " mov producción");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_CAT_S, "adjt.name", DGridConsts.COL_TITLE_TYPE + " ajuste");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_CAT_S, "f_wsd", DGridConsts.COL_TITLE_NUM + " doc contraparte");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_CAT_M, "bpr.name", "Asociado negocios");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_CAT_S, "bpr.tax_id", "RFC");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_CAT_S, "dpt.name", "Depto producción");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_CAT_S, "lin.name", "Línea producción");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_INT_2B, "job.num", "Orden producción");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_BOOL_S, DDbConsts.FIELD_IS_DEL, DGridConsts.COL_TITLE_IS_DEL);
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_BOOL_S, DDbConsts.FIELD_IS_SYS, DGridConsts.COL_TITLE_IS_SYS);
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_USR, DDbConsts.FIELD_USER_INS_NAME, DGridConsts.COL_TITLE_USER_INS_NAME);
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_DATE_DATETIME, DDbConsts.FIELD_USER_INS_TS, DGridConsts.COL_TITLE_USER_INS_TS);
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_USR, DDbConsts.FIELD_USER_UPD_NAME, DGridConsts.COL_TITLE_USER_UPD_NAME);
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_DATE_DATETIME, DDbConsts.FIELD_USER_UPD_TS, DGridConsts.COL_TITLE_USER_UPD_TS);

        for (col = 0; col < columns.length; col++) {
            moModel.getGridColumns().add(columns[col]);
        }
    }

    @Override
    public void defineSuscriptions() {
        moSuscriptionsSet.add(mnGridType);
        moSuscriptionsSet.add(DModConsts.CU_USR);
    }
    
    public void actionPerformedNewSal() {
        DGuiParams params = new DGuiParams();
        
        params.getPostInitValuesMap().put(DDbWsd.PARAM_MOV_TP, new int[] { mnGridSubtype, DModSysConsts.SX_MOV_TP_SAL });
        params.getPostInitValuesMap().put(DDbWsd.PARAM_TRN_TP, DModSysConsts.SS_TRN_TP_SR);
        params.getPostInitValuesMap().put(DDbWsd.PARAM_MFG_TP, DModSysConsts.SS_MFG_TP_NA);
        
        miClient.getSession().getModule(mnModuleType, mnModuleSubtype).showForm(mnGridType, mnGridSubtype, params);
    }
    
    public void actionPerformedNewPur() {
        DGuiParams params = new DGuiParams();
        
        params.getPostInitValuesMap().put(DDbWsd.PARAM_MOV_TP, new int[] { mnGridSubtype, DModSysConsts.SX_MOV_TP_PUR });
        params.getPostInitValuesMap().put(DDbWsd.PARAM_TRN_TP, DModSysConsts.SS_TRN_TP_SR);
        params.getPostInitValuesMap().put(DDbWsd.PARAM_MFG_TP, DModSysConsts.SS_MFG_TP_NA);
        
        miClient.getSession().getModule(mnModuleType, mnModuleSubtype).showForm(mnGridType, mnGridSubtype, params);
    }
    
    public void actionPerformedNewAdj() {
        DGuiParams params = new DGuiParams();
        
        params.getPostInitValuesMap().put(DDbWsd.PARAM_MOV_TP, new int[] { mnGridSubtype, DModSysConsts.SX_MOV_TP_ADJ });
        params.getPostInitValuesMap().put(DDbWsd.PARAM_TRN_TP, DModSysConsts.SS_TRN_TP_NA);
        params.getPostInitValuesMap().put(DDbWsd.PARAM_MFG_TP, DModSysConsts.SS_MFG_TP_NA);
        
        miClient.getSession().getModule(mnModuleType, mnModuleSubtype).showForm(mnGridType, mnGridSubtype, params);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            
            if (button == mjNewSal) {
                actionPerformedNewSal();
            }
            else if (button == mjNewPur) {
                actionPerformedNewPur();
            }
            else if (button == mjNewAdj) {
                actionPerformedNewAdj();
            }
        }
    }
}
