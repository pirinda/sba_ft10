/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.mfg.view;

import ft.mod.DModConsts;
import ft.mod.DModSysConsts;
import sba.lib.db.DDbConsts;
import sba.lib.grid.DGridColumnView;
import sba.lib.grid.DGridConsts;
import sba.lib.grid.DGridFilterDatePeriod;
import sba.lib.grid.DGridPaneSettings;
import sba.lib.grid.DGridPaneView;
import sba.lib.grid.DGridUtils;
import sba.lib.gui.DGuiClient;
import sba.lib.gui.DGuiConsts;
import sba.lib.gui.DGuiDate;

/**
 *
 * @author Sergio Flores
 */
public class DViewJob extends DGridPaneView {

    private DGridFilterDatePeriod moFilterDatePeriod;
    
    public DViewJob(DGuiClient client, int status, String title) {
        super(client, DGridConsts.GRID_VIEW_TAB, DModConsts.M_JOB, status, title);
        
        switch (mnGridSubtype) {
            case DModSysConsts.MS_JOB_ST_NEW:
                setRowButtonsEnabled(true, true, true, true, false);
                break;
            case DModSysConsts.MS_JOB_ST_PRC:
            case DModSysConsts.MS_JOB_ST_QTY:
            case DModSysConsts.MS_JOB_ST_FIN:
                setRowButtonsEnabled(false, true, false, false, false);
                break;
            default:
        }
        
        moFilterDatePeriod = new DGridFilterDatePeriod(miClient, this, DGuiConsts.DATE_PICKER_DATE_PERIOD);
        moFilterDatePeriod.initFilter(new DGuiDate(DGuiConsts.GUI_DATE_MONTH, miClient.getSession().getWorkingDate().getTime()));
        getPanelCommandsSys(DGuiConsts.PANEL_CENTER).add(moFilterDatePeriod);
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
        moPaneSettings.setDateApplying(true);

        filter = (Boolean) moFiltersMap.get(DGridConsts.FILTER_DELETED);
        if ((Boolean) filter) {
            sql += (sql.length() == 0 ? "" : "AND ") + "v.b_del = 0 ";
        }

        filter = (DGuiDate) moFiltersMap.get(DGridConsts.FILTER_DATE_PERIOD);
        sql += (sql.isEmpty() ? "" : "AND ") + DGridUtils.getSqlFilterDate("v.dat", (DGuiDate) filter);
        
        msSql = "SELECT " +
                "v.id_job AS " + DDbConsts.FIELD_ID + "1, " +
                "v.num AS " + DDbConsts.FIELD_CODE + ", " +
                "v.num AS " + DDbConsts.FIELD_NAME + ", " +
                "v.dat AS " + DDbConsts.FIELD_DATE + ", " +
                "v.job_qty_r, " +
                "v.lot, " +
                "i.code, " +
                "i.name, " +
                "it.code, " +
                "it.name, " +
                "u.code, " +
                "u.name, " +
                "v.b_del AS " + DDbConsts.FIELD_IS_DEL + ", " +
                "v.b_sys AS " + DDbConsts.FIELD_IS_SYS + ", " +
                "v.fk_usr_ins AS " + DDbConsts.FIELD_USER_INS_ID + ", " +
                "v.fk_usr_upd AS " + DDbConsts.FIELD_USER_UPD_ID + ", " +
                "v.ts_usr_ins AS " + DDbConsts.FIELD_USER_INS_TS + ", " +
                "v.ts_usr_upd AS " + DDbConsts.FIELD_USER_UPD_TS + ", " +
                "v.ts_usr_upd AS " + DDbConsts.FIELD_USER_UPD_TS + ", " +
                "ui.name AS " + DDbConsts.FIELD_USER_INS_NAME + ", " +
                "uu.name AS " + DDbConsts.FIELD_USER_UPD_NAME + " " +
                "FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB) + " AS v " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_ITM) + " AS i ON " +
                "v.fk_itm = i.id_itm " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CS_ITM_TP) + " AS it ON " +
                "v.fk_itm_tp = it.id_itm_tp " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_UOM) + " AS u ON " +
                "v.fk_uom = u.id_uom " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_USR) + " AS ui ON " +
                "v.fk_usr_ins = ui.id_usr " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_USR) + " AS uu ON " +
                "v.fk_usr_upd = uu.id_usr " +
                "WHERE v.fk_job_st = " + mnGridSubtype + " " + (sql.length() == 0 ? "" : "AND " + sql) +
                "ORDER BY v.num, v.id_job ";
    }

    @Override
    public void createGridColumns() {
        int col = 0;
        DGridColumnView[] columns = new DGridColumnView[14];

        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_INT_RAW, DDbConsts.FIELD_NAME, DGridConsts.COL_TITLE_NUM);
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_DATE, DDbConsts.FIELD_DATE, DGridConsts.COL_TITLE_DATE);
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_ITM_L, "i.name", DGridConsts.COL_TITLE_NAME + " producto");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_ITM, "i.code", DGridConsts.COL_TITLE_CODE + " producto");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_CAT, "it.code", DGridConsts.COL_TITLE_TYPE + " producto");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_DEC_QTY, "v.job_qty_r", "Cantidad");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_UNT, "u.code", "Unidad medida");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_CAT_S, "v.lot", "Lote");
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
}
