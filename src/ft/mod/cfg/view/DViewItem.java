/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.cfg.view;

import ft.mod.DModConsts;
import ft.mod.cfg.db.DCfgConsts;
import ft.mod.cfg.db.DCfgUtils;
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
public class DViewItem extends DGridPaneView {

    public DViewItem(DGuiClient client, int itemType, String title) {
        super(client, DGridConsts.GRID_VIEW_TAB, DModConsts.CU_ITM, itemType, title);
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
                "v.id_itm AS " + DDbConsts.FIELD_ID + "1, " +
                "v.code AS " + DDbConsts.FIELD_CODE + ", " +
                "v.name AS " + DDbConsts.FIELD_NAME + ", " +
                "v.lot_code, " +
                "v.mass_unt, " +
                "v.b_brix, " +
                "v.brix, " +
                "f.code, " +
                "f.name, " +
                "it.code, " +
                "it.name, " +
                "u.code, " +
                "u.name, " +
                "p.code, " +
                "p.name, " +
                "ib.code, " +
                "ib.name, " +
                "v.b_del AS " + DDbConsts.FIELD_IS_DEL + ", " +
                "v.b_sys AS " + DDbConsts.FIELD_IS_SYS + ", " +
                "v.fk_usr_ins AS " + DDbConsts.FIELD_USER_INS_ID + ", " +
                "v.fk_usr_upd AS " + DDbConsts.FIELD_USER_UPD_ID + ", " +
                "v.ts_usr_ins AS " + DDbConsts.FIELD_USER_INS_TS + ", " +
                "v.ts_usr_upd AS " + DDbConsts.FIELD_USER_UPD_TS + ", " +
                "ui.name AS " + DDbConsts.FIELD_USER_INS_NAME + ", " +
                "uu.name AS " + DDbConsts.FIELD_USER_UPD_NAME + " " +
                "FROM " + DModConsts.TablesMap.get(DModConsts.CU_ITM) + " AS v " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_FAM) + " AS f ON " +
                "v.fk_fam = f.id_fam AND f.fk_itm_tp = " + mnGridSubtype + " " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CS_ITM_TP) + " AS it ON " +
                "f.fk_itm_tp = it.id_itm_tp " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_UOM) + " AS u ON " +
                "v.fk_uom = u.id_uom " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_PRE) + " AS p ON " +
                "v.fk_pre = p.id_pre " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_USR) + " AS ui ON " +
                "v.fk_usr_ins = ui.id_usr " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_USR) + " AS uu ON " +
                "v.fk_usr_upd = uu.id_usr " +
                "LEFT OUTER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_ITM) + " AS ib ON " +
                "v.fk_itm_bas_n = ib.id_itm " +
                (sql.length() == 0 ? "" : "WHERE " + sql) +
                "ORDER BY v.name, v.code, v.id_itm ";
    }

    @Override
    public void createGridColumns() {
        int col = 0;
        DGridColumnView[] columns = new DGridColumnView[17];

        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_ITM_L, DDbConsts.FIELD_NAME, DGridConsts.COL_TITLE_NAME);
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_ITM, DDbConsts.FIELD_CODE, DGridConsts.COL_TITLE_CODE);
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_CAT, "it.code", DGridConsts.COL_TITLE_TYPE + " ítem");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_CAT_S, "f.name", "Familia");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_CAT_S, "ib.name", "Producto base");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_CAT_S, "u.name", "Unidad medida");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_CAT_S, "p.name", "Presentación");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_CAT, "v.lot_code", DGridConsts.COL_TITLE_CODE + " lotes");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_DEC_AMT_UNIT, "v.mass_unt", "Masa unit (" + DCfgUtils.getMassUnitCode(miClient.getSession()) + ")");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_BOOL_S, "v.b_brix", "Aplican " + DCfgConsts.BRIX);
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_DEC_QTY, "v.brix", DCfgConsts.BRIX);
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
        moSuscriptionsSet.add(DModConsts.CU_FAM);
        moSuscriptionsSet.add(DModConsts.CU_UOM);
        moSuscriptionsSet.add(DModConsts.CU_PRE);
        moSuscriptionsSet.add(DModConsts.CU_USR);
    }
}
