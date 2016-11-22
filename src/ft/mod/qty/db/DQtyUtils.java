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
import java.util.Date;
import sba.lib.DLibUtils;
import sba.lib.gui.DGuiItem;
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
    
    /**
     * Gets manufacturing variables used on quality tests application in provided period for provided item.
     * @param session Current GUI user session.
     * @param start Period's start.
     * @param end Period's end.
     * @param idItem Item's primary key.
     * @return <code>ArrayList</code> of <code>DGuiItem</code> objects.
     * @throws Exception 
     */
    public static ArrayList<DGuiItem> getVariableItems(final DGuiSession session, final Date start, final Date end, final int idItem) throws Exception {
        String sql = "";
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<DGuiItem> items = new ArrayList<>();
        
        statement = session.getStatement().getConnection().createStatement();
        
        sql = "SELECT DISTINCT v.id_var, v.name, v.code "
                + "FROM " + DModConsts.TablesMap.get(DModConsts.Q_APP) + " AS a "
                + "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.Q_APP_RES) + " AS ar ON a.id_job = ar.id_job AND a.id_app = ar.id_app "
                + "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.Q_APP_RES_VAR) + " AS arv ON ar.id_job = arv.id_job AND ar.id_app = arv.id_app AND ar.id_res = arv.id_res "
                + "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.QU_VAR) + " AS v ON arv.id_var = v.id_var "
                + "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.M_JOB) + " AS j ON a.id_job = j.id_job "
                + "WHERE NOT a.b_del AND a.fk_itm = " + idItem + " AND "
                + "NOT j.b_del AND NOT j.b_ann AND j.dat BETWEEN '" + DLibUtils.DbmsDateFormatDate.format(start) + "' AND '" + DLibUtils.DbmsDateFormatDate.format(end) + "' "
                + "ORDER BY v.name, v.code, v.id_var ";
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            items.add(new DGuiItem(new int[] { resultSet.getInt("v.id_var")}, resultSet.getString("v.name")));
        }
        
        return items;
    }
}
