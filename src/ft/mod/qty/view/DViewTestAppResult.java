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
public class DViewTestAppResult extends DGridPaneView {

    private DGridFilterDatePeriod moFilterDatePeriod;
    
    public DViewTestAppResult(DGuiClient client, String title) {
        super(client, DGridConsts.GRID_VIEW_TAB, DModConsts.Q_APP_RES, DLibConsts.UNDEFINED, title);
        setRowButtonsEnabled(false);
        
        moFilterDatePeriod = new DGridFilterDatePeriod(miClient, this, DGuiConsts.DATE_PICKER_DATE_PERIOD);
        moFilterDatePeriod.initFilter(new DGuiDate(DGuiConsts.GUI_DATE_MONTH, miClient.getSession().getWorkingDate().getTime()));
        getPanelCommandsSys(DGuiConsts.PANEL_CENTER).add(moFilterDatePeriod);
    }

    @Override
    public void prepareSqlQuery() {
        String sql = "";
        Object filter = null;

        moPaneSettings = new DGridPaneSettings(3);
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
        sql += (sql.isEmpty() ? "" : "AND ") + DGridUtils.getSqlFilterDate("a.dat", (DGuiDate) filter);
        
        msSql = "SELECT " +
                "arv.id_app AS " + DDbConsts.FIELD_ID + "1, " +
                "arv.id_res AS " + DDbConsts.FIELD_ID + "2, " +
                "arv.id_var AS " + DDbConsts.FIELD_ID + "3, " +
                "t.code AS " + DDbConsts.FIELD_CODE + ", " +
                "t.name AS " + DDbConsts.FIELD_NAME + ", " +
                "a.dat AS " + DDbConsts.FIELD_DATE + ", " +
                "arv.val, " +
                "tt.code, " +
                "tt.name, " +
                "i.code, " +
                "i.name, " +
                "it.code, " +
                "it.name, " +
                "j.num, " +
                "j.dat, " +
                "v.code, " +
                "v.name, " +
                "v.uom, " +
                "a.b_del AS " + DDbConsts.FIELD_IS_DEL + ", " +
                "a.b_sys AS " + DDbConsts.FIELD_IS_SYS + ", " +
                "a.fk_usr_ins AS " + DDbConsts.FIELD_USER_INS_ID + ", " +
                "a.fk_usr_upd AS " + DDbConsts.FIELD_USER_UPD_ID + ", " +
                "a.ts_usr_ins AS " + DDbConsts.FIELD_USER_INS_TS + ", " +
                "a.ts_usr_upd AS " + DDbConsts.FIELD_USER_UPD_TS + ", " +
                "ui.name AS " + DDbConsts.FIELD_USER_INS_NAME + ", " +
                "uu.name AS " + DDbConsts.FIELD_USER_UPD_NAME + " " +
                "FROM " + DModConsts.TablesMap.get(DModConsts.Q_APP_RES_VAR) + " AS arv " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.Q_APP_RES) + " AS ar ON " +
                "arv.id_app = ar.id_app AND arv.id_res = ar.id_res " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.Q_APP) + " AS a ON " +
                "arv.id_app = a.id_app " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.QU_TST) + " AS t ON " +
                "a.fk_tst = t.id_tst " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.QS_TST_TP) + " AS tt ON " +
                "a.fk_tst_tp = tt.id_tst_tp " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_ITM) + " AS i ON " +
                "a.fk_itm = i.id_itm " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CS_ITM_TP) + " AS it ON " +
                "a.fk_itm_tp = it.id_itm_tp " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.QU_VAR) + " AS v ON " +
                "arv.id_var = v.id_var " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_USR) + " AS ui ON " +
                "a.fk_usr_ins = ui.id_usr " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_USR) + " AS uu ON " +
                "a.fk_usr_upd = uu.id_usr " +
                "LEFT OUTER JOIN " + DModConsts.TablesMap.get(DModConsts.M_JOB) + " AS j ON " +
                "a.fk_job_n = j.id_job " +
                (sql.length() == 0 ? "" : "WHERE " + sql) +
                "ORDER BY a.dat, t.name, t.code, i.name, i.code, j.num, arv.id_app, arv.id_res, v.name, v.code, arv.id_var ";
    }

    @Override
    public void createGridColumns() {
        int col = 0;
        DGridColumnView[] columns = new DGridColumnView[19];

        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_DATE, DDbConsts.FIELD_DATE, DGridConsts.COL_TITLE_DATE + " aplicación test calidad");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_CAT_M, DDbConsts.FIELD_NAME, DGridConsts.COL_TITLE_NAME + " test calidad");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_CAT, DDbConsts.FIELD_CODE, DGridConsts.COL_TITLE_CODE + " test calidad");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_CAT, "tt.code", DGridConsts.COL_TITLE_TYPE + " test calidad");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_ITM_S, "i.name", DGridConsts.COL_TITLE_NAME + " producto");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_ITM, "i.code", DGridConsts.COL_TITLE_CODE + " producto");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_CAT, "it.code", DGridConsts.COL_TITLE_TYPE + " producto");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_INT_RAW, "j.num", DGridConsts.COL_TITLE_NUM + " orden producción");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_DATE, "j.dat", DGridConsts.COL_TITLE_DATE + " orden producción");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_INT_1B, DDbConsts.FIELD_ID + "2", "# resultado");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_CAT_S, "v.name", DGridConsts.COL_TITLE_NAME + " variable");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_DEC_AMT_UNIT, "arv.val", "Valor");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_CAT, "v.uom", "Unidad medida");
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
