/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ft.mod.mfg.db;

import ft.mod.DModConsts;
import ft.mod.DModSysConsts;
import ft.mod.cfg.db.DDbItemFamily;
import ft.mod.cfg.db.DDbPresent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import sba.lib.gui.DGuiSession;

/**
 *
 * @author Sergio Flores
 */
public abstract class DMfgUtils {
    
    public static ArrayList<DDbPresent> readPresents(final DGuiSession session) throws SQLException, Exception {
        String sql = "";
        Statement statement = null;
        ResultSet resultSet = null;
        DDbPresent registry = null;
        ArrayList<DDbPresent> array = new ArrayList<>();
        
        sql = "SELECT id_prs, name FROM " + DModConsts.TablesMap.get(DModConsts.CU_PRS) + " WHERE b_del = 0 AND id_prs <> " + DModSysConsts.CU_PRS_NA + " ORDER BY name, id_prs ";
        statement = session.getStatement().getConnection().createStatement();
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            registry = new DDbPresent();
            registry.read(session, new int[] { resultSet.getInt(1) });
            array.add(registry);
        }
        
        return array;
    }
    
    public static ArrayList<DDbItemFamily> readItemFamilies(final DGuiSession session, final int itemTypeId) throws SQLException, Exception {
        String sql = "";
        Statement statement = null;
        ResultSet resultSet = null;
        DDbItemFamily registry = null;
        ArrayList<DDbItemFamily> array = new ArrayList<>();
        
        sql = "SELECT id_fam, name FROM " + DModConsts.TablesMap.get(DModConsts.CU_FAM) + " WHERE b_del = 0 AND fk_itm_tp = " + itemTypeId + " ORDER BY name, id_fam ";
        statement = session.getStatement().getConnection().createStatement();
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            registry = new DDbItemFamily();
            registry.read(session, new int[] { resultSet.getInt(1) });
            array.add(registry);
        }
        
        return array;
    }
    
    public static ArrayList<DDbLinePack> readLinePacks(final DGuiSession session) throws SQLException, Exception {
        String sql = "";
        Statement statement = null;
        ResultSet resultSet = null;
        DDbLinePack registry = null;
        ArrayList<DDbLinePack> array = new ArrayList<>();
        
        sql = "SELECT id_lin_pck, name FROM " + DModConsts.TablesMap.get(DModConsts.MU_LIN_PCK) + " WHERE b_del = 0 ORDER BY name, id_lin_pck ";
        statement = session.getStatement().getConnection().createStatement();
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            registry = new DDbLinePack();
            registry.read(session, new int[] { resultSet.getInt(1) });
            array.add(registry);
        }
        
        return array;
    }
    
    public static ArrayList<DDbLinePrep> readLinePreps(final DGuiSession session) throws SQLException, Exception {
        String sql = "";
        Statement statement = null;
        ResultSet resultSet = null;
        DDbLinePrep registry = null;
        ArrayList<DDbLinePrep> array = new ArrayList<>();
        
        sql = "SELECT id_lin_prp, name FROM " + DModConsts.TablesMap.get(DModConsts.MU_LIN_PRP) + " WHERE b_del = 0 ORDER BY name, id_lin_prp ";
        statement = session.getStatement().getConnection().createStatement();
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            registry = new DDbLinePrep();
            registry.read(session, new int[] { resultSet.getInt(1) });
            array.add(registry);
        }
        
        return array;
    }
}
