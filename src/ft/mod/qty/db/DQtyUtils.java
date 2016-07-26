/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ft.mod.qty.db;

import ft.mod.DModConsts;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import sba.lib.gui.DGuiSession;

/**
 *
 * @author Sergio Flores
 */
public abstract class DQtyUtils {
    
    public static ArrayList<DDbTestApp> createTestAppsForFamily(final DGuiSession session, final int idFamily, final int typeTest) throws Exception {
        String sql = "";
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<DDbTestApp> testApps = new ArrayList<>();
        
        statement = session.getStatement().getConnection().createStatement();
        
        sql = "SELECT t.name, t.code, t.id_tst, tf.res "
                + "FROM " + DModConsts.TablesMap.get(DModConsts.QU_TST_FAM) + " AS tf "
                + "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.QU_TST) + " AS t ON tf.id_tst = t.id_tst "
                + "WHERE tf.id_fam = " + idFamily + " AND t.fk_tst_tp = " + typeTest + " AND t.b_del = 0 "
                + "ORDER BY t.name, t.code, t.id_tst ";
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            DDbTest test = (DDbTest) session.readRegistry(DModConsts.QU_TST, new int[] { resultSet.getInt("t.id_tst") });
            DDbTestApp testApp = new DDbTestApp();
            
            //testApp.setPkJobId(...); // to be set by caller
            //testApp.setPkAppId(...); // to be set by caller
            testApp.setResultsDesired(resultSet.getInt("tf.res"));
            //testApp.setDeleted(...);
            //testApp.setSystem(...);
            testApp.setFkTestId(test.getPkTestId());
            testApp.setFkTestTypeId(test.getFkTestTypeId());
            //testApp.setFkItemId(...); // to be set by caller
            //testApp.setFkItemTypeId(...); // to be set by caller
            
            testApp.setRegTest(test);
            //testApp.setRegItem(...); // to be set by caller
            
            testApps.add(testApp);
        }
        
        return testApps;
    }
}
