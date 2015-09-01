/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.mfg.view;

import ft.mod.DModConsts;
import sba.lib.DLibConsts;
import sba.lib.db.DDbConsts;
import sba.lib.grid.DGridColumnView;
import sba.lib.grid.DGridConsts;
import sba.lib.grid.DGridPaneSettings;
import sba.lib.grid.DGridPaneView;
import sba.lib.gui.DGuiClient;

/**
 *
 * @author Sergio Flores
 */
public class DViewYearWeek extends DGridPaneView {

    public DViewYearWeek(DGuiClient client, String title) {
        super(client, DGridConsts.GRID_VIEW_TAB, DModConsts.M_YER_WEK, DLibConsts.UNDEFINED, title);
        setRowButtonsEnabled(false);
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
            sql += (sql.length() == 0 ? "" : "AND ") + "y.b_del = 0 ";
        }

        msSql = "SELECT " +
                "v.id_yer AS " + DDbConsts.FIELD_ID + "1, " +
                "v.id_wek AS " + DDbConsts.FIELD_ID + "2, " +
                "v.id_wek AS " + DDbConsts.FIELD_CODE + ", " +
                "v.id_wek AS " + DDbConsts.FIELD_NAME + ", " +
                "v.sta, " +
                "ADDDATE(v.sta, 6) AS f_end, " +
                "y.b_del AS " + DDbConsts.FIELD_IS_DEL + ", " +
                "y.b_sys AS " + DDbConsts.FIELD_IS_SYS + ", " +
                "y.fk_usr_ins AS " + DDbConsts.FIELD_USER_INS_ID + ", " +
                "y.fk_usr_upd AS " + DDbConsts.FIELD_USER_UPD_ID + ", " +
                "y.ts_usr_ins AS " + DDbConsts.FIELD_USER_INS_TS + ", " +
                "y.ts_usr_upd AS " + DDbConsts.FIELD_USER_UPD_TS + ", " +
                "y.ts_usr_upd AS " + DDbConsts.FIELD_USER_UPD_TS + ", " +
                "ui.name AS " + DDbConsts.FIELD_USER_INS_NAME + ", " +
                "uu.name AS " + DDbConsts.FIELD_USER_UPD_NAME + " " +
                "FROM " + DModConsts.TablesMap.get(DModConsts.M_YER) + " AS y " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.M_YER_WEK) + " AS v ON " +
                "y.id_yer = v.id_yer " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_USR) + " AS ui ON " +
                "y.fk_usr_ins = ui.id_usr " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_USR) + " AS uu ON " +
                "y.fk_usr_upd = uu.id_usr " +
                (sql.length() == 0 ? "" : "WHERE " + sql) +
                "ORDER BY v.id_yer, v.id_wek ";
    }

    @Override
    public void createGridColumns() {
        int col = 0;
        DGridColumnView[] columns = new DGridColumnView[10];

        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_INT_CAL_YEAR, DDbConsts.FIELD_ID + "1", "Año");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_INT_CAL_MONTH, DDbConsts.FIELD_ID + "2", "Semana");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_DATE, "v.sta", "Inicio");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_DATE, "f_end", "Fin");
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
        moSuscriptionsSet.add(DModConsts.M_YER);
        moSuscriptionsSet.add(DModConsts.CU_USR);
    }
}
