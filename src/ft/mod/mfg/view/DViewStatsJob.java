/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.mfg.view;

import ft.mod.DModConsts;
import ft.mod.DModSysConsts;
import ft.mod.cfg.db.DCfgUtils;
import java.util.Date;
import sba.lib.DLibConsts;
import sba.lib.DLibTimeUtils;
import sba.lib.db.DDbConsts;
import sba.lib.grid.DGridColumnView;
import sba.lib.grid.DGridConsts;
import sba.lib.grid.DGridFilterDateRange;
import sba.lib.grid.DGridPaneSettings;
import sba.lib.grid.DGridPaneView;
import sba.lib.grid.DGridUtils;
import sba.lib.gui.DGuiClient;
import sba.lib.gui.DGuiConsts;

/**
 *
 * @author Sergio Flores
 */
public class DViewStatsJob extends DGridPaneView {

    private DGridFilterDateRange moFilterDateRange;
    
    public DViewStatsJob(DGuiClient client, String title) {
        super(client, DGridConsts.GRID_VIEW_TAB, DModConsts.MX_STA_JOB, DLibConsts.UNDEFINED, title);
        
        setRowButtonsEnabled(false, false, false, false, false);
        jtbFilterDeleted.setEnabled(false);
        
        moFilterDateRange = new DGridFilterDateRange(miClient, this);
        moFilterDateRange.initFilter(new Date[] { DLibTimeUtils.getBeginOfMonth(miClient.getSession().getWorkingDate()), DLibTimeUtils.getEndOfMonth(miClient.getSession().getWorkingDate()) });
        getPanelCommandsSys(DGuiConsts.PANEL_CENTER).add(moFilterDateRange);
    }

    @Override
    public void prepareSqlQuery() {
        String sql = "";
        Object filter = null;

        moPaneSettings = new DGridPaneSettings(1);
        moPaneSettings.setDateApplying(true);

        filter = (Date[]) moFiltersMap.get(DGridConsts.FILTER_DATE_RANGE);
        sql += (sql.isEmpty() ? "" : "AND ") + DGridUtils.getSqlFilterDateRange("j.dat", (Date[]) filter);
        
        msSql = "SELECT " +
                "j.id_job AS " + DDbConsts.FIELD_ID + "1, " +
                "j.num AS " + DDbConsts.FIELD_CODE + ", " +
                "j.num AS " + DDbConsts.FIELD_NAME + ", " +
                "j.dat AS " + DDbConsts.FIELD_DATE + ", " +
                "j.ts_sta_n, " +
                "j.ts_end_n, " +
                "j.job_qty_r, " +
                "j.mfg_qty_r, " +
                "j.req_mass_r, " +
                "j.con_mass_r, " +
                "j.tar_mass_r, " +
                "j.mfg_mass_r, " +
                "j.lot, " +
                "j.pack_fac, " +
                "jt.code, " +
                "jt.name, " +
                "js.code, " +
                "js.name, " +
                "i.code, " +
                "i.name, " +
                "it.code, " +
                "it.name, " +
                "u.code, " +
                "u.name, " +
                "p.code, " +
                "p.name " +
                "FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB) + " AS j " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.MS_JOB_TP) + " AS jt ON " +
                "j.fk_job_tp = jt.id_job_tp " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.MS_JOB_ST) + " AS js ON " +
                "j.fk_job_st = js.id_job_st " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_ITM) + " AS i ON " +
                "j.fk_itm = i.id_itm " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CS_ITM_TP) + " AS it ON " +
                "j.fk_itm_tp = it.id_itm_tp " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_UOM) + " AS u ON " +
                "j.fk_uom = u.id_uom " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_PRE) + " AS p ON " +
                "j.fk_pre = p.id_pre " +
                "WHERE NOT j.b_ann AND NOT j.b_del AND j.fk_job_st = " + DModSysConsts.MS_JOB_ST_FIN + " AND " + sql +
                "ORDER BY j.num, j.id_job ";
    }

    @Override
    public void createGridColumns() {
        int col = 0;
        DGridColumnView[] columns = new DGridColumnView[19];

        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_INT_RAW, DDbConsts.FIELD_NAME, DGridConsts.COL_TITLE_NUM);
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_DATE, DDbConsts.FIELD_DATE, DGridConsts.COL_TITLE_DATE);
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_CAT, "jt.code", DGridConsts.COL_TITLE_TYPE);
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_CAT, "js.code", DGridConsts.COL_TITLE_STAT);
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_ITM_L, "i.name", DGridConsts.COL_TITLE_NAME + " producto");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_ITM, "i.code", DGridConsts.COL_TITLE_CODE + " producto");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_CAT, "it.code", DGridConsts.COL_TITLE_TYPE + " producto");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_DATE_DATETIME, "j.ts_sta_n", "Fecha-hr inicial");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_DATE_DATETIME, "j.ts_sta_n", "Fecha-hr final");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_DEC_QTY, "j.job_qty_r", "Cantidad orden");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_DEC_QTY, "j.mfg_qty_r", "Cantidad producción");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_UNT, "u.code", "Unidad medida");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_UNT, "p.code", "Presentación");
        columns[col] = new DGridColumnView(DGridConsts.COL_TYPE_DEC_AMT_UNIT, "j.req_mass_r", "Masa requerida (" + DCfgUtils.getMassUnitCode(miClient.getSession()) + ")");
        columns[col++].setSumApplying(true);
        columns[col] = new DGridColumnView(DGridConsts.COL_TYPE_DEC_AMT_UNIT, "j.con_mass_r", "Masa consumida (" + DCfgUtils.getMassUnitCode(miClient.getSession()) + ")");
        columns[col++].setSumApplying(true);
        columns[col] = new DGridColumnView(DGridConsts.COL_TYPE_DEC_AMT_UNIT, "j.tar_mass_r", "Masa objetivo (" + DCfgUtils.getMassUnitCode(miClient.getSession()) + ")");
        columns[col++].setSumApplying(true);
        columns[col] = new DGridColumnView(DGridConsts.COL_TYPE_DEC_AMT_UNIT, "j.mfg_mass_r", "Masa producción (" + DCfgUtils.getMassUnitCode(miClient.getSession()) + ")");
        columns[col++].setSumApplying(true);
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_CAT_S, "j.lot", "Lote");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_DEC_AMT_UNIT, "j.pack_fac", "Factor consumo");

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
