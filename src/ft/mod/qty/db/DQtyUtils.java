/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ft.mod.qty.db;

import ft.mod.DModConsts;
import java.sql.ResultSet;
import java.util.ArrayList;
import sba.lib.gui.DGuiSession;

/**
 *
 * @author Sergio Flores
 */
public abstract class DQtyUtils {
    
    public static ArrayList<DDbTest> readTests(final DGuiSession session, final int type, final int idFamily) throws Exception {
        String sql = "";
        ResultSet resultSet = null;
        ArrayList<DDbTest> tests = new ArrayList<>();
        
        sql = "SELECT DISTINCT t.name, t.code, t.id_tst, tf.res "
                + "FROM " + DModConsts.TablesMap.get(DModConsts.QU_TST_FAM) + " AS tf "
                + "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.QU_TST) + " AS t ON tf.id_tst = t.id_tst "
                + "WHERE tf.id_fam = " + idFamily + " AND t.fk_tst_tp = " + type + " AND t.b_del = 0 "
                + "ORDER BY t.name, t.code, t.id_tst ";
        resultSet = session.getStatement().getConnection().createStatement().executeQuery(sql);
        while (resultSet.next()) {
            DDbTest test = new DDbTest();
            test.read(session, new int[] { resultSet.getInt("t.id_tst") });
            test.setAuxResults(resultSet.getInt("tf.res"));
            tests.add(test);
        }
        
        return tests;
    }
}
