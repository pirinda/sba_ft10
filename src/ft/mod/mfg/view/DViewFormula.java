/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.mfg.view;

import ft.gui.DGuiClientApp;
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
public class DViewFormula extends DGridPaneView {

    public DViewFormula(DGuiClient client, String title) {
        super(client, DGridConsts.GRID_VIEW_TAB, DModConsts.MU_FRM, DLibConsts.UNDEFINED, title);
        setRowButtonsEnabled(true, true, true, false, true);
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
                "v.id_frm AS " + DDbConsts.FIELD_ID + "1, " +
                "v.code AS " + DDbConsts.FIELD_CODE + ", " +
                "v.name AS " + DDbConsts.FIELD_NAME + ", " +
                "v.def_var_1, " +
                "v.qty, " +
                "v.b_qty_var_1, " +
                "v.b_del AS " + DDbConsts.FIELD_IS_DEL + ", " +
                "v.b_sys AS " + DDbConsts.FIELD_IS_SYS + ", " +
                "v.fk_usr_ins AS " + DDbConsts.FIELD_USER_INS_ID + ", " +
                "v.fk_usr_upd AS " + DDbConsts.FIELD_USER_UPD_ID + ", " +
                "v.ts_usr_ins AS " + DDbConsts.FIELD_USER_INS_TS + ", " +
                "v.ts_usr_upd AS " + DDbConsts.FIELD_USER_UPD_TS + ", " +
                "ft.code, " +
                "ft.name, " +
                "it.code, " +
                "it.name, " +
                "i.code, " +
                "i.name, " +
                "u.code, " +
                "u.name, " +
                "p.code, " +
                "p.name, " +
                "ui.name AS " + DDbConsts.FIELD_USER_INS_NAME + ", " +
                "uu.name AS " + DDbConsts.FIELD_USER_UPD_NAME + " " +
                "FROM " + DModConsts.TablesMap.get(DModConsts.MU_FRM) + " AS v " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.MS_FRM_TP) + " AS ft ON " +
                "v.fk_frm_tp = ft.id_frm_tp " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CS_ITM_TP) + " AS it ON " +
                "v.fk_itm_tp = it.id_itm_tp " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_ITM) + " AS i ON " +
                "v.fk_itm = i.id_itm " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_UNT) + " AS u ON " +
                "v.fk_unt = u.id_unt " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_PRS) + " AS p ON " +
                "v.fk_prs = p.id_prs " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_USR) + " AS ui ON " +
                "v.fk_usr_ins = ui.id_usr " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_USR) + " AS uu ON " +
                "v.fk_usr_upd = uu.id_usr " +
                (sql.length() == 0 ? "" : "WHERE " + sql) +
                "ORDER BY v.name, v.code, v.id_frm ";
    }

    @Override
    public void createGridColumns() {
        int col = 0;
        DGridColumnView[] columns = new DGridColumnView[16];

        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_ITM_L, DDbConsts.FIELD_NAME, DGridConsts.COL_TITLE_NAME);
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_ITM, DDbConsts.FIELD_CODE, DGridConsts.COL_TITLE_CODE);
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_CAT, "it.code", DGridConsts.COL_TITLE_TYPE + " producto");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_CAT_S, "ft.name", DGridConsts.COL_TITLE_TYPE + " fórmula");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_CAT_S, "u.name", "Unidad");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_CAT_S, "p.name", "Presentación");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_DEC_QTY, "v.qty", "Cantidad");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_UNT, "u.code", "Unidad");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_BOOL_M, "v.b_qty_var_1", "Cantidad " + ((DGuiClientApp) miClient).getVar1());
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_DEC_QTY, "v.def_var_1", ((DGuiClientApp) miClient).getVar1() + " default");
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
        moSuscriptionsSet.add(DModConsts.CU_ITM);
        moSuscriptionsSet.add(DModConsts.CU_UNT);
        moSuscriptionsSet.add(DModConsts.CU_PRS);
        moSuscriptionsSet.add(DModConsts.CU_USR);
    }
}
