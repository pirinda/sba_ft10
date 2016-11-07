/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ft.mod.mfg.db;

import ft.mod.DModConsts;
import ft.mod.DModSysConsts;
import ft.mod.cfg.db.DDbConfig;
import ft.mod.cfg.db.DDbFamily;
import ft.mod.cfg.db.DDbItem;
import ft.mod.cfg.db.DDbYear;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import sba.lib.DLibConsts;
import sba.lib.DLibTimeUtils;
import sba.lib.DLibUtils;
import sba.lib.gui.DGuiSession;

/**
 *
 * @author Sergio Flores
 */
public abstract class DMfgUtils {
    
    public static final DecimalFormat DecimalFormatDayOfYear = new DecimalFormat("000");
    
    private static int getLot(final DGuiSession session, final Date date, final int idItem, final int pos, final int len) throws Exception {
        int lot = 0;
        String sql = "";
        ResultSet resultSet = null;
        
        sql = "SELECT COALESCE(MAX(SUBSTRING(lot, " + pos + ", " + len + ")), '') AS _lot "
                + "FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB) + " "
                + "WHERE YEAR(dat) = " + DLibTimeUtils.digestYear(date)[0] + " AND fk_itm = " + idItem + " AND b_del = 0 ";
        resultSet = session.getStatement().executeQuery(sql);
        if (resultSet.next()) {
            lot = DLibUtils.parseInt(resultSet.getString(1)) + 1;
        }
        
        return lot;
    }
    
    public static String computeLot(final DGuiSession session, final Date date, final int idItem) throws Exception {
        int len = 0;
        int[] lotDate = DLibTimeUtils.digestDate(date);
        char[] lotFormatArray = null;
        String lot = "";
        String lotFormat = "";
        DecimalFormat decimalFormat = null;
        GregorianCalendar gregorianCalendar = null;
        DDbYear year = null;
        DDbItem item = null;
        DDbFamily family = null;
        
        item = (DDbItem) session.readRegistry(DModConsts.CU_ITM, new int[] { idItem });
        
        switch (item.getRegFamily().getFkItemTypeId()) {
            case DModSysConsts.CS_ITM_TP_PB:
                lotFormat = ((DDbConfig) session.getConfigCompany()).getLotFormatProductBase();
                break;
            case DModSysConsts.CS_ITM_TP_PF:
                lotFormat = ((DDbConfig) session.getConfigCompany()).getLotFormatProductFinished();
                break;
            default:
                throw new Exception(DLibConsts.ERR_MSG_OPTION_UNKNOWN);
        }
        
        if (lotFormat.contains("" + DMfgConsts.LOT_F)) {
            family = (DDbFamily) session.readRegistry(DModConsts.CU_FAM, new int[] { item.getFkFamilyId() });
        }
        
        if (lotFormat.contains("" + DMfgConsts.LOT_W)) {
            year = (DDbYear) session.readRegistry(DModConsts.C_YEA, new int[] { lotDate[0] });
        }
        
        if (lotFormat.contains("" + DMfgConsts.LOT_y) || lotFormat.contains("" + DMfgConsts.LOT_w)) {
            gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(date);
        }
            
        lotFormatArray = lotFormat.toCharArray();
        for (int i = 0; i < lotFormatArray.length; i++) {
            switch (lotFormatArray[i]) {
                case DMfgConsts.LOT_L:
                    len = DLibUtils.parseInt("" + lotFormatArray[++i]);
                    decimalFormat = new DecimalFormat(DLibUtils.textRepeat("0", len));
                    lot += decimalFormat.format(getLot(session, date, idItem, lot.length(), len));
                    break;
                case DMfgConsts.LOT_I:
                    lot += item.getLotCode();
                    break;
                case DMfgConsts.LOT_F:
                    lot += family.getLotCode();
                    break;
                case DMfgConsts.LOT_P:
                    lot += item.getRegPresent().getLotCode();
                    break;
                case DMfgConsts.LOT_D:
                    lot += DLibUtils.DecimalFormatCalendarDay.format(lotDate[2]);
                    break;
                case DMfgConsts.LOT_M:
                    lot += DLibUtils.DecimalFormatCalendarMonth.format(lotDate[1]);
                    break;
                case DMfgConsts.LOT_Y:
                    lot += DLibUtils.DecimalFormatCalendarYear.format(lotDate[0]);
                    break;
                case DMfgConsts.LOT_y:
                    lot += DecimalFormatDayOfYear.format(gregorianCalendar.get(Calendar.DAY_OF_YEAR));
                    break;
                case DMfgConsts.LOT_W:
                    lot += DLibUtils.DecimalFormatCalendarMonth.format(date.before(year.getYearStart()) ? 0 : (((DLibTimeUtils.getDaysDiff(date, DLibTimeUtils.getBeginOfYear(date)) + 1)) / 7) + 1);
                    break;
                case DMfgConsts.LOT_w:
                    lot += gregorianCalendar.get(Calendar.DAY_OF_WEEK);
                    break;
                case DMfgConsts.LOT_A:
                    lot += ("" + lotDate[0]).substring(2, 4);
                    break;
                case '-':
                    lot += "-";
                    break;
                case '/':
                    lot += "/";
                    break;
                default:
                    throw new Exception(DLibConsts.ERR_MSG_OPTION_UNKNOWN);
            }
        }
        
        return lot;
    }
    
    public static ArrayList<DDbVariable> readVariablesForFamily(final DGuiSession session, final int idFamily) throws Exception {
        String sql = "";
        ResultSet resultSet = null;
        ArrayList<DDbVariable> variables = new ArrayList<>();
        
        sql = "SELECT DISTINCT v.name, v.code, v.id_var "
                + "FROM " + DModConsts.TablesMap.get(DModConsts.MU_VAR_FAM) + " AS vf "
                + "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.MU_VAR) + " AS V ON vf.id_var = v.id_var "
                + "WHERE vf.id_fam = " + idFamily + " AND v.b_del = 0 "
                + "ORDER BY v.name, v.code, v.id_var ";
        resultSet = session.getStatement().getConnection().createStatement().executeQuery(sql);
        while (resultSet.next()) {
            DDbVariable variable = new DDbVariable();
            variable.read(session, new int[] { resultSet.getInt("v.id_var") });
            variables.add(variable);
        }
        
        return variables;
    }
}
