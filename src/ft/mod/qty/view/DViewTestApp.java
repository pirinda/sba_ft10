/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.qty.view;

import ft.mod.DModConsts;
import sba.lib.DLibConsts;
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
public class DViewTestApp extends DGridPaneView {

    private DGridFilterDatePeriod moFilterDatePeriod;
    
    public DViewTestApp(DGuiClient client, String title) {
        super(client, DGridConsts.GRID_VIEW_TAB, DModConsts.Q_APP, DLibConsts.UNDEFINED, title);
        setRowButtonsEnabled(false);
        
        moFilterDatePeriod = new DGridFilterDatePeriod(miClient, this, DGuiConsts.DATE_PICKER_DATE_PERIOD);
        moFilterDatePeriod.initFilter(new DGuiDate(DGuiConsts.GUI_DATE_MONTH, miClient.getSession().getWorkingDate().getTime()));
        getPanelCommandsSys(DGuiConsts.PANEL_CENTER).add(moFilterDatePeriod);
    }

    @Override
    public void prepareSqlQuery() {
        String sql = "";
        Object filter = null;

        moPaneSettings = new DGridPaneSettings(2);
        moPaneSettings.setDeletedApplying(true);
        moPaneSettings.setSystemApplying(true);
        moPaneSettings.setUserInsertApplying(true);
        moPaneSettings.setUserUpdateApplying(true);

        filter = (Boolean) moFiltersMap.get(DGridConsts.FILTER_DELETED);
        if ((Boolean) filter) {
            sql += (sql.length() == 0 ? "" : "AND ") + "a.b_del = 0 ";
        }

        filter = (DGuiDate) moFiltersMap.get(DGridConsts.FILTER_DATE_PERIOD);
        sql += (sql.isEmpty() ? "" : "AND ") + DGridUtils.getSqlFilterDate("j.dat", (DGuiDate) filter);
        
        msSql = "SELECT " +
                "a.id_job AS " + DDbConsts.FIELD_ID + "1, " +
                "a.id_app AS " + DDbConsts.FIELD_ID + "2, " +
                "a.res, " +
                "j.num, " +
                "j.dat, " +
                "t.code AS " + DDbConsts.FIELD_CODE + ", " +
                "t.name AS " + DDbConsts.FIELD_NAME + ", " +
                "tt.code, " +
                "tt.name, " +
                "i.code, " +
                "i.name, " +
                "it.code, " +
                "it.name, " +
                "a.b_del AS " + DDbConsts.FIELD_IS_DEL + ", " +
                "a.b_sys AS " + DDbConsts.FIELD_IS_SYS + ", " +
                "a.fk_usr_ins AS " + DDbConsts.FIELD_USER_INS_ID + ", " +
                "a.fk_usr_upd AS " + DDbConsts.FIELD_USER_UPD_ID + ", " +
                "a.ts_usr_ins AS " + DDbConsts.FIELD_USER_INS_TS + ", " +
                "a.ts_usr_upd AS " + DDbConsts.FIELD_USER_UPD_TS + ", " +
                "ui.name AS " + DDbConsts.FIELD_USER_INS_NAME + ", " +
                "uu.name AS " + DDbConsts.FIELD_USER_UPD_NAME + " " +
                "FROM " + DModConsts.TablesMap.get(DModConsts.Q_APP) + " AS a " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.M_JOB) + " AS j ON " +
                "a.id_job = j.id_job " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.QU_TST) + " AS t ON " +
                "a.fk_tst = t.id_tst " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.QS_TST_TP) + " AS tt ON " +
                "a.fk_tst_tp = tt.id_tst_tp " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_ITM) + " AS i ON " +
                "a.fk_itm = i.id_itm " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CS_ITM_TP) + " AS it ON " +
                "a.fk_itm_tp = it.id_itm_tp " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_USR) + " AS ui ON " +
                "a.fk_usr_ins = ui.id_usr " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_USR) + " AS uu ON " +
                "a.fk_usr_upd = uu.id_usr " +
                (sql.length() == 0 ? "" : "WHERE " + sql) +
                "ORDER BY j.num, t.name, t.code, i.name, i.code, a.id_job, a.id_app ";
    }

    @Override
    public void createGridColumns() {
        int col = 0;
        DGridColumnView[] columns = new DGridColumnView[14];

        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_INT_RAW, "j.num", DGridConsts.COL_TITLE_NUM + " orden producción");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_DATE, "j.dat", DGridConsts.COL_TITLE_DATE + " orden producción");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_CAT_S, DDbConsts.FIELD_NAME, DGridConsts.COL_TITLE_NAME + " test calidad");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_CAT, DDbConsts.FIELD_CODE, DGridConsts.COL_TITLE_CODE + " test calidad");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_CAT, "tt.code", DGridConsts.COL_TITLE_TYPE + " test calidad");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_ITM_S, "i.name", DGridConsts.COL_TITLE_NAME + " producto");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_ITM, "i.code", DGridConsts.COL_TITLE_CODE + " producto");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_CAT, "it.code", DGridConsts.COL_TITLE_TYPE + " producto");
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
        moSuscriptionsSet.add(DModConsts.M_JOB);
        moSuscriptionsSet.add(DModConsts.CU_USR);
    }
}
