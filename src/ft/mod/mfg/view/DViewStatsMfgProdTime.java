/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.mfg.view;

import ft.mod.DModConsts;
import ft.mod.DModSysConsts;
import ft.mod.cfg.db.DCfgUtils;
import java.util.Calendar;
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
public class DViewStatsMfgProdTime extends DGridPaneView {

    private int mnCalendarOption;
    private String msCalendarOption;
    private DGridFilterDateRange moFilterDateRange;
    
    public DViewStatsMfgProdTime(DGuiClient client, int calendarOption, String title) {
        super(client, DGridConsts.GRID_VIEW_TAB, DModConsts.MX_STA_MFG_PRO_TIME, DLibConsts.UNDEFINED, title);
        
        mnCalendarOption = calendarOption;
        
        setRowButtonsEnabled(false, false, false, false, false);
        jtbFilterDeleted.setEnabled(false);
        
        moFilterDateRange = new DGridFilterDateRange(miClient, this);
        moFilterDateRange.initFilter(new Date[] { DLibTimeUtils.getBeginOfMonth(miClient.getSession().getWorkingDate()), DLibTimeUtils.getEndOfMonth(miClient.getSession().getWorkingDate()) });
        getPanelCommandsSys(DGuiConsts.PANEL_CENTER).add(moFilterDateRange);
        
        switch (mnCalendarOption) {
            case Calendar.WEEK_OF_YEAR:
                msCalendarOption = "Semana";
                break;
            case Calendar.MONTH:
                msCalendarOption = "Mes";
                break;
            default:
        }
    }

    @Override
    public void prepareSqlQuery() {
        String sql = "";
        String cal = "";
        Object filter = null;

        moPaneSettings = new DGridPaneSettings(2);
        
        switch (mnCalendarOption) {
            case Calendar.WEEK_OF_YEAR:
                cal = "WEEKOFYEAR(j.dat)";
                break;
            case Calendar.MONTH:
                cal = "MONTH(j.dat)";
                break;
            default:
        }

        filter = (Date[]) moFiltersMap.get(DGridConsts.FILTER_DATE_RANGE);
        sql += (sql.isEmpty() ? "" : "AND ") + DGridUtils.getSqlFilterDateRange("j.dat", (Date[]) filter);
        
        msSql = "SELECT " +
                "j.fk_itm AS " + DDbConsts.FIELD_ID + "1, " +
                cal + " AS " + DDbConsts.FIELD_ID + "2, " +
                "i.code AS " + DDbConsts.FIELD_CODE + ", " +
                "i.name AS " + DDbConsts.FIELD_NAME + ", " +
                "SUM(j.job_qty_r) AS _job_qty_r, " +
                "SUM(j.mfg_qty_r) AS _mfg_qty_r, " +
                "SUM(j.req_mass_r) AS _req_mass_r, " +
                "SUM(j.con_mass_r) AS _con_mass_r, " +
                "SUM(j.tar_mass_r) AS _tar_mass_r, " +
                "SUM(j.mfg_mass_r) AS _mfg_mass_r, " +
                "AVG(j.pack_fac) AS _pack_fac, " +
                "it.code, " +
                "it.name, " +
                "u.code, " +
                "u.name, " +
                "p.code, " +
                "p.name " +
                "FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB) + " AS j " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_ITM) + " AS i ON " +
                "j.fk_itm = i.id_itm " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CS_ITM_TP) + " AS it ON " +
                "j.fk_itm_tp = it.id_itm_tp " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_UOM) + " AS u ON " +
                "j.fk_uom = u.id_uom " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_PRE) + " AS p ON " +
                "j.fk_pre = p.id_pre " +
                "WHERE NOT j.b_ann AND NOT j.b_del AND j.fk_job_st = " + DModSysConsts.MS_JOB_ST_FIN + " AND " + sql +
                "GROUP BY j.fk_itm, " + DDbConsts.FIELD_ID + "2, i.code, i.name, it.code, it.name, u.code, u.name, p.code, p.name " +
                "ORDER BY i.name, i.code, j.fk_itm, " + DDbConsts.FIELD_ID + "2 ";
    }

    @Override
    public void createGridColumns() {
        int col = 0;
        DGridColumnView[] columns = new DGridColumnView[13];

        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_ITM_L, DDbConsts.FIELD_NAME, DGridConsts.COL_TITLE_NAME + " producto");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_ITM, DDbConsts.FIELD_CODE, DGridConsts.COL_TITLE_CODE + " producto");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_CAT, "it.code", DGridConsts.COL_TITLE_TYPE + " producto");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_INT_CAL_MONTH, DDbConsts.FIELD_ID + "2", msCalendarOption);
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_DEC_QTY, "_job_qty_r", "Cantidad orden");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_DEC_QTY, "_mfg_qty_r", "Cantidad producción");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_UNT, "u.code", "Unidad medida");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_UNT, "p.code", "Presentación");
        columns[col] = new DGridColumnView(DGridConsts.COL_TYPE_DEC_AMT_UNIT, "_req_mass_r", "Masa requerida (" + DCfgUtils.getMassUnitCode(miClient.getSession()) + ")");
        columns[col++].setSumApplying(true);
        columns[col] = new DGridColumnView(DGridConsts.COL_TYPE_DEC_AMT_UNIT, "_con_mass_r", "Masa consumida (" + DCfgUtils.getMassUnitCode(miClient.getSession()) + ")");
        columns[col++].setSumApplying(true);
        columns[col] = new DGridColumnView(DGridConsts.COL_TYPE_DEC_AMT_UNIT, "_tar_mass_r", "Masa objetivo (" + DCfgUtils.getMassUnitCode(miClient.getSession()) + ")");
        columns[col++].setSumApplying(true);
        columns[col] = new DGridColumnView(DGridConsts.COL_TYPE_DEC_AMT_UNIT, "_mfg_mass_r", "Masa producción (" + DCfgUtils.getMassUnitCode(miClient.getSession()) + ")");
        columns[col++].setSumApplying(true);
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_DEC_AMT_UNIT, "_pack_fac", "Factor consumo prom");

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
