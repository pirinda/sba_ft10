/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.stk.view;

import ft.mod.DModConsts;
import ft.mod.DModGridFilter;
import ft.mod.DModSysConsts;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import sba.lib.DLibTimeUtils;
import sba.lib.DLibUtils;
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
import sba.lib.img.DImgConsts;

/**
 *
 * @author Sergio Flores
 */
public class DViewStock extends DGridPaneView implements ActionListener {
    
    private JButton mjShowCardex;
    private DGridFilterDatePeriod moFilterDatePeriod;
    private DModGridFilter moFilterWarehouse;

    public DViewStock(DGuiClient client, int stockMode, String title) {
        super(client, DGridConsts.GRID_VIEW_TAB, DModConsts.S_STK, stockMode, title);
        setRowButtonsEnabled(false);
        
        moFilterDatePeriod = new DGridFilterDatePeriod(miClient, this, DGuiConsts.DATE_PICKER_DATE);
        moFilterDatePeriod.initFilter(new DGuiDate(DGuiConsts.GUI_DATE_DATE, DLibTimeUtils.getEndOfYear(miClient.getSession().getWorkingDate()).getTime()));
        getPanelCommandsSys(DGuiConsts.PANEL_CENTER).add(moFilterDatePeriod);
        
        mjShowCardex = DGridUtils.createButton(miClient.getImageIcon(DImgConsts.CMD_STD_CARDEX), "Ver cárdex", this);
        getPanelCommandsSys(DGuiConsts.PANEL_CENTER).add(mjShowCardex);
        
        mjShowCardex.setEnabled(false);
        
        moFilterWarehouse = new DModGridFilter(this, DModConsts.SU_WHS);
        getPanelCommandsSys(DGuiConsts.PANEL_CENTER).add(moFilterWarehouse);
    }

    @Override
    public void prepareSqlQuery() {
        String sql = "";
        String lot = "";
        String having = "";
        Object filter = null;

        moPaneSettings = new DGridPaneSettings(2);

        filter = (Boolean) moFiltersMap.get(DGridConsts.FILTER_DELETED);
        if ((Boolean) filter) {
            having = "HAVING f_stk <> 0 ";
        }

        filter = (DGuiDate) moFiltersMap.get(DGridConsts.FILTER_DATE_PERIOD);
        sql += (sql.length() == 0 ? "" : "AND ") + "v.dat <= '" + DLibUtils.DbmsDateFormatDate.format((DGuiDate) filter) + "' ";

        filter = (int[]) moFiltersMap.get(DModConsts.SU_WHS);
        if (filter != null && ((int[]) filter).length == 1) {
            sql += (sql.length() == 0 ? "" : "AND ") + "v.id_whs = " + ((int[]) filter)[0] + " ";
        }
        
        switch (mnGridSubtype) {
            case DModSysConsts.SX_STK:
                break;
            case DModSysConsts.SX_STK_LOT:
                lot = "v.lot";
                break;
            default:
        }

        msSql = "SELECT " +
                "v.id_itm AS " + DDbConsts.FIELD_ID + "1, " +
                "v.id_uom AS " + DDbConsts.FIELD_ID + "2, " +
                "i.code AS " + DDbConsts.FIELD_CODE + ", " +
                "i.name AS " + DDbConsts.FIELD_NAME + ", " +
                "i.code, " +
                "i.name, " +
                "u.code, " +
                "u.name, " +
                (lot.isEmpty() ? "" : lot + ", ") +
                "SUM(v.unt_in - v.unt_out) AS f_stk " +
                "FROM " + DModConsts.TablesMap.get(DModConsts.S_STK) + " AS v " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_ITM) + " AS i ON " +
                "v.id_itm = i.id_itm " +
                "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_UOM) + " AS u ON " +
                "v.id_uom = u.id_uom " +
                (sql.length() == 0 ? "" : "WHERE " + sql) +
                "GROUP BY i.name, i.code, v.id_itm, " + (lot.isEmpty() ? "" : lot + ", ") + "u.code, v.id_uom " +
                having +
                "ORDER BY i.name, i.code, v.id_itm, " + (lot.isEmpty() ? "" : lot + ", ") + "u.code, v.id_uom ";
    }

    @Override
    public void createGridColumns() {
        int col = 0;
        DGridColumnView[] columns = null;

        switch (mnGridSubtype) {
            case DModSysConsts.SX_STK:
                columns = new DGridColumnView[4];
                break;
            case DModSysConsts.SX_STK_LOT:
                columns = new DGridColumnView[5];
                break;
            default:
        }

        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_ITM_L, DDbConsts.FIELD_NAME, DGridConsts.COL_TITLE_NAME);
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_ITM, DDbConsts.FIELD_CODE, DGridConsts.COL_TITLE_CODE);
        if (mnGridSubtype == DModSysConsts.SX_STK_LOT) {
            columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_NAME_CAT_S, "v.lot", "Lote");
        }
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_DEC_QTY, "f_stk", "Existencias");
        columns[col++] = new DGridColumnView(DGridConsts.COL_TYPE_TEXT_CODE_UNT, "u.code", "Unidad");

        for (col = 0; col < columns.length; col++) {
            moModel.getGridColumns().add(columns[col]);
        }
    }

    @Override
    public void defineSuscriptions() {
        moSuscriptionsSet.add(mnGridType);
        moSuscriptionsSet.add(DModConsts.CU_ITM);
        moSuscriptionsSet.add(DModConsts.CU_ITM);
        moSuscriptionsSet.add(DModConsts.S_WSD);
    }
    
    private void actionPerformedShowCardex() {
        if (mjShowCardex.isEnabled()) {
            
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            
            if (button == mjShowCardex) {
                actionPerformedShowCardex();
            }
        }
    }
}
