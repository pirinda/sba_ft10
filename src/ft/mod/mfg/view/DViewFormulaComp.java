/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.mfg.view;

import ft.mod.DModConsts;
import ft.mod.DModSysConsts;
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
public class DViewFormulaComp extends DGridPaneView {

    public DViewFormulaComp(DGuiClient client, String title) {
        super(client, DGridConsts.GRID_VIEW_TAB, DModConsts.MU_FRM_CMP, DLibConsts.UNDEFINED, title);
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
            sql += (sql.length() == 0 ? "" : "AND ") + "v.b_del = 0 ";
        }

        msSql = "SELECT " +
                "vc.id_frm AS " + DDbConsts.FIELD_ID + "1, " +
                "vc.id_cmp AS " + DDbConsts.FIELD_ID + "2, " +
                "v.code AS " + DDbConsts.FIELD_CODE + ", " +
                "v.name AS " + DDbConsts.FIELD_NAME + ", " +
                "v.ref, " +
                "v.qty, " +
                "v.mass_r, " +
                "ft.code, " +
                "ft.name, " +
                "i.code, " +
                "i.name, " +
                "it.code, " +
                "it.name, " +
                "u.code, " +
                "u.name, " +
                "p.code, " +
                "p.name, " +
                "vc.qty, " +
                "vc.b_std, " +
                "ct.code, " +
                "ct.name, " +
                "cit.code, " +
                "cit.name, " +
                "cu.code, " +
                "cu.name, " +
                "CASE WHEN vc.fk_cmp_tp = " + DModSysConsts.MS_CMP_TP_ITM + " THEN ci.code WHEN vc.fk_cmp_tp = " + DModSysConsts.MS_CMP_TP_FAM + " THEN cf.code ELSE '?' END AS _cmp_code, " +
                "CASE WHEN vc.fk_cmp_tp = " + DModSysConsts.MS_CMP_TP_ITM + " THEN ci.name WHEN vc.fk_cmp_tp = " + DModSysConsts.MS_CMP_TP_FAM + " THEN cf.name ELSE '?' END AS _cmp_name, " +
                "v.b_del AS " + DDbConsts.FIELD_IS_DEL + ", " +
                "v.b_sys AS " + DDbConsts.FIELD_IS_SYS + ", " +
                "v.fk_usr_ins AS " + DDbConsts.FIELD_USER_INS_ID + ", " +
                "v.fk_usr_upd AS " + DDbConsts.FIELD_USER_UPD_ID + ", " +
                "v.ts_usr_ins AS " + DDbConsts.FIELD_USER_INS_TS + ", " +
                "v.ts_usr_upd AS " + DDbConsts.FIELD_USER_UPD_TS + ", " +
                "ui.name AS " + DDbConsts.FIELD_USER_INS_NAME + ", " +
                "uu.name AS " + DDbConsts.FIELD_USER_UPD_NAME + " " +
                "FROM " + DModConsts.TablesMap.get(DModConsts.MU_FRM) + " AS v " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.MS_FRM_TP) + " AS ft ON " +
                "v.fk_frm_tp = ft.id_frm_tp " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_ITM) + " AS i ON " +
                "v.fk_itm = i.id_itm " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CS_ITM_TP) + " AS it ON " +
                "v.fk_itm_tp = it.id_itm_tp " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_UOM) + " AS u ON " +
                "v.fk_uom = u.id_uom " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_PRE) + " AS p ON " +
                "v.fk_pre = p.id_pre " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_USR) + " AS ui ON " +
                "v.fk_usr_ins = ui.id_usr " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_USR) + " AS uu ON " +
                "v.fk_usr_upd = uu.id_usr " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.MU_FRM_CMP) + " AS vc ON " +
                "v.id_frm = vc.id_frm " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.MS_CMP_TP) + " AS ct ON " +
                "vc.fk_cmp_tp = ct.id_cmp_tp " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CS_ITM_TP) + " AS cit ON " +
                "vc.fk_itm_tp = cit.id_itm_tp " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_UOM) + " AS cu ON " +
                "vc.fk_uom = cu.id_uom " +
                "LEFT OUTER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_ITM) + " AS ci ON " +
                "vc.fk_cmp = ci.id_itm " +
                "LEFT OUTER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_FAM) + " AS cf ON " +
                "vc.fk_cmp = cf.id_fam " +
                (sql.length() == 0 ? "" : "WHERE " + sql) +
                "ORDER BY v.name, v.code, vc.id_frm, vc.id_cmp ";
    }

    @Override
    public void createGridColumns() {
        int col = 0;
        DGridColumnView[] columns = new DGridColumnView[18];

        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_ITM_L, DDbConsts.FIELD_NAME, DGridConsts.COL_TITLE_NAME);
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_ITM, DDbConsts.FIELD_CODE, DGridConsts.COL_TITLE_CODE);
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_CAT, "it.code", DGridConsts.COL_TITLE_TYPE + " producto");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_CAT_S, "ft.name", DGridConsts.COL_TITLE_TYPE + " fórmula");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_INT_2B, DDbConsts.FIELD_ID + "2", "# componente");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_CAT, "ct.code", DGridConsts.COL_TITLE_TYPE + " componente");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_CAT, "cit.code", DGridConsts.COL_TITLE_TYPE + " ítem");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_ITM_L, "_cmp_name", DGridConsts.COL_TITLE_NAME + " componente");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_ITM, "_cmp_code", DGridConsts.COL_TITLE_CODE + " componente");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_DEC_QTY, "vc.qty", "Cant componente");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_UNT, "cu.code", "Unidad medida componente");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_BOOL_M, "vc.b_std", "Estándar componente");
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
        moSuscriptionsSet.add(DModConsts.MU_FRM);
        moSuscriptionsSet.add(DModConsts.CU_ITM);
        moSuscriptionsSet.add(DModConsts.CU_UOM);
        moSuscriptionsSet.add(DModConsts.CU_PRE);
        moSuscriptionsSet.add(DModConsts.CU_USR);
    }
}
