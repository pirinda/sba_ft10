/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.qty.db;

import ft.lib.DLibRegistry;
import ft.mod.DModConsts;
import ft.mod.cfg.db.DDbItem;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import sba.gui.util.DUtilConsts;
import sba.lib.DLibConsts;
import sba.lib.DLibUtils;
import sba.lib.db.DDbConsts;
import sba.lib.db.DDbRegistryUser;
import sba.lib.gui.DGuiSession;

/**
 *
 * @author Sergio Flores
 */
public class DDbTestApp extends DDbRegistryUser implements DLibRegistry {

    protected int mnPkAppId;
    protected Date mtDate;
    /*
    protected boolean mbDeleted;
    protected boolean mbSystem;
    */
    protected int mnFkTestId;
    protected int mnFkTestTypeId;
    protected int mnFkItemId;
    protected int mnFkItemTypeId;
    protected int mnFkJobId_n;
    /*
    protected int mnFkUserInsertId;
    protected int mnFkUserUpdateId;
    protected Date mtTsUserInsert;
    protected Date mtTsUserUpdate;
    */
    
    protected ArrayList<DDbTestAppResult> maChildResults;
    
    protected DDbTest moRegTest;
    protected DDbItem moRegItem;
    
    public DDbTestApp() {
        super(DModConsts.Q_APP);
        maChildResults = new ArrayList<>();
        initRegistry();
    }

    private void readRegMembers(final DGuiSession session, final boolean update) {
        moRegTest = (DDbTest) session.readRegistry(DModConsts.QU_TST, new int[] { mnFkTestId });
        moRegItem = (DDbItem) session.readRegistry(DModConsts.CU_ITM, new int[] { mnFkItemId });
        
        if (update) {
            mnFkTestTypeId = moRegTest.getFkTestTypeId();
            mnFkItemTypeId = moRegItem.getXtaFkItemTypeId();
        }
    }
    
    public void setPkAppId(int n) { mnPkAppId = n; }
    public void setDate(Date t) { mtDate = t; }
    public void setDeleted(boolean b) { mbDeleted = b; }
    public void setSystem(boolean b) { mbSystem = b; }
    public void setFkTestId(int n) { mnFkTestId = n; }
    public void setFkTestTypeId(int n) { mnFkTestTypeId = n; }
    public void setFkItemId(int n) { mnFkItemId = n; }
    public void setFkItemTypeId(int n) { mnFkItemTypeId = n; }
    public void setFkJobId_n(int n) { mnFkJobId_n = n; }
    public void setFkUserInsertId(int n) { mnFkUserInsertId = n; }
    public void setFkUserUpdateId(int n) { mnFkUserUpdateId = n; }
    public void setTsUserInsert(Date t) { mtTsUserInsert = t; }
    public void setTsUserUpdate(Date t) { mtTsUserUpdate = t; }

    public int getPkAppId() { return mnPkAppId; }
    public Date getDate() { return mtDate; }
    public boolean isDeleted() { return mbDeleted; }
    public boolean isSystem() { return mbSystem; }
    public int getFkTestId() { return mnFkTestId; }
    public int getFkTestTypeId() { return mnFkTestTypeId; }
    public int getFkItemId() { return mnFkItemId; }
    public int getFkItemTypeId() { return mnFkItemTypeId; }
    public int getFkJobId_n() { return mnFkJobId_n; }
    public int getFkUserInsertId() { return mnFkUserInsertId; }
    public int getFkUserUpdateId() { return mnFkUserUpdateId; }
    public Date getTsUserInsert() { return mtTsUserInsert; }
    public Date getTsUserUpdate() { return mtTsUserUpdate; }

    public ArrayList<DDbTestAppResult> getChildResults() { return maChildResults; }
    
    public void setRegTest(DDbTest o) { moRegTest = o; }
    public void setRegItem(DDbItem o) { moRegItem = o; }
    
    public DDbTest getRegTest() { return moRegTest; }
    public DDbItem getRegItem() { return moRegItem; }
    
    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkAppId = pk[0];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkAppId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkAppId = 0;
        mtDate = null;
        mbDeleted = false;
        mbSystem = false;
        mnFkTestId = 0;
        mnFkTestTypeId = 0;
        mnFkItemId = 0;
        mnFkItemTypeId = 0;
        mnFkJobId_n = 0;
        mnFkUserInsertId = 0;
        mnFkUserUpdateId = 0;
        mtTsUserInsert = null;
        mtTsUserUpdate = null;
        
        maChildResults.clear();
        
        moRegTest = null;
        moRegItem = null;
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_app = " + mnPkAppId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_app = " + pk[0] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkAppId = 0;

        msSql = "SELECT COALESCE(MAX(id_app), 0) + 1 FROM " + getSqlTable();
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkAppId = resultSet.getInt(1);
        }
    }

    @Override
    public void read(DGuiSession session, int[] pk) throws SQLException, Exception {
        Statement statement = null;
        ResultSet resultSet = null;

        initRegistry();
        initQueryMembers();
        mnQueryResultId = DDbConsts.READ_ERROR;

        msSql = "SELECT * " + getSqlFromWhere(pk);
        resultSet = session.getStatement().executeQuery(msSql);
        if (!resultSet.next()) {
            throw new Exception(DDbConsts.ERR_MSG_REG_NOT_FOUND);
        }
        else {
            mnPkAppId = resultSet.getInt("id_app");
            mtDate = resultSet.getDate("dat");
            mbDeleted = resultSet.getBoolean("b_del");
            mbSystem = resultSet.getBoolean("b_sys");
            mnFkTestId = resultSet.getInt("fk_tst");
            mnFkTestTypeId = resultSet.getInt("fk_tst_tp");
            mnFkItemId = resultSet.getInt("fk_itm");
            mnFkItemTypeId = resultSet.getInt("fk_itm_tp");
            mnFkJobId_n = resultSet.getInt("fk_job_n");
            mnFkUserInsertId = resultSet.getInt("fk_usr_ins");
            mnFkUserUpdateId = resultSet.getInt("fk_usr_upd");
            mtTsUserInsert = resultSet.getTimestamp("ts_usr_ins");
            mtTsUserUpdate = resultSet.getTimestamp("ts_usr_upd");

            // Read aswell child registries:
            
            statement = session.getStatement().getConnection().createStatement();
            
            msSql = "SELECT id_res FROM " + DModConsts.TablesMap.get(DModConsts.Q_APP_RES) + " " + getSqlWhere() +
                    "ORDER BY id_res ";
            resultSet = statement.executeQuery(msSql);
            while (resultSet.next()) {
                DDbTestAppResult child = new DDbTestAppResult();
                child.read(session, new int[] { mnPkAppId, resultSet.getInt(1) });
                child.setAuxTestApp(this);
                maChildResults.add(child);
            }
            
            // Read aswell embeeded registries:
            
            readRegMembers(session, false);
            
            // Finish registry reading:

            mbRegistryNew = false;
        }

        mnQueryResultId = DDbConsts.READ_OK;
    }

    @Override
    public void save(DGuiSession session) throws SQLException, Exception {
        initQueryMembers();
        mnQueryResultId = DDbConsts.SAVE_ERROR;
        
        compute(session);

        if (mbRegistryNew) {
            computePrimaryKey(session);
            mbDeleted = false;
            mbSystem = false;
            mnFkUserInsertId = session.getUser().getPkUserId();
            mnFkUserUpdateId = DUtilConsts.USR_NA_ID;

            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkAppId + ", " + 
                    "'" + DLibUtils.DbmsDateFormatDate.format(mtDate) + "', " + 
                    (mbDeleted ? 1 : 0) + ", " + 
                    (mbSystem ? 1 : 0) + ", " + 
                    mnFkTestId + ", " + 
                    mnFkTestTypeId + ", " + 
                    mnFkItemId + ", " + 
                    mnFkItemTypeId + ", " + 
                    (mnFkJobId_n == DLibConsts.UNDEFINED ? "NULL" : "" + mnFkJobId_n) + ", " + 
                    mnFkUserInsertId + ", " + 
                    mnFkUserUpdateId + ", " + 
                    "NOW()" + ", " + 
                    "NOW()" + " " + 
                    ")";
        }
        else {
            mnFkUserUpdateId = session.getUser().getPkUserId();

            msSql = "UPDATE " + getSqlTable() + " SET " +
                    //"id_app = " + mnPkAppId + ", " +
                    "dat = '" + DLibUtils.DbmsDateFormatDate.format(mtDate) + "', " +
                    "b_del = " + (mbDeleted ? 1 : 0) + ", " +
                    "b_sys = " + (mbSystem ? 1 : 0) + ", " +
                    "fk_tst = " + mnFkTestId + ", " +
                    "fk_tst_tp = " + mnFkTestTypeId + ", " +
                    "fk_itm = " + mnFkItemId + ", " +
                    "fk_itm_tp = " + mnFkItemTypeId + ", " +
                    "fk_job_n = " + (mnFkJobId_n == DLibConsts.UNDEFINED ? "NULL" : "" + mnFkJobId_n) + ", " +
                    //"fk_usr_ins = " + mnFkUserInsertId + ", " +
                    "fk_usr_upd = " + mnFkUserUpdateId + ", " +
                    //"ts_usr_ins = " + "NOW()" + ", " +
                    "ts_usr_upd = " + "NOW()" + " " +
                    getSqlWhere();
        }

        session.getStatement().execute(msSql);
        
        // Save aswell child registries:

        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.Q_APP_RES_VAR) + " " + getSqlWhere();
        session.getStatement().execute(msSql);
        
        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.Q_APP_RES) + " " + getSqlWhere();
        session.getStatement().execute(msSql);
        
        for (DDbTestAppResult child : maChildResults) {
            child.setRegistryNew(true);
            child.setPkAppId(mnPkAppId);
            child.save(session);
        }

        // Finish registry saving:
        
        mbRegistryNew = false;
        mnQueryResultId = DDbConsts.SAVE_OK;
    }

    @Override
    public DDbTestApp clone() throws CloneNotSupportedException {
        DDbTestApp registry = new DDbTestApp();

        registry.setPkAppId(this.getPkAppId());
        registry.setDate(this.getDate());
        registry.setDeleted(this.isDeleted());
        registry.setSystem(this.isSystem());
        registry.setFkTestId(this.getFkTestId());
        registry.setFkTestTypeId(this.getFkTestTypeId());
        registry.setFkItemId(this.getFkItemId());
        registry.setFkItemTypeId(this.getFkItemTypeId());
        registry.setFkJobId_n(this.getFkJobId_n());
        registry.setFkUserInsertId(this.getFkUserInsertId());
        registry.setFkUserUpdateId(this.getFkUserUpdateId());
        registry.setTsUserInsert(this.getTsUserInsert());
        registry.setTsUserUpdate(this.getTsUserUpdate());

        for (DDbTestAppResult child : maChildResults) {
            registry.getChildResults().add(child.clone());
        }
        
        registry.setRegTest(this.getRegTest() == null ? null : this.getRegTest());
        
        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }

    @Override
    public void compute(DGuiSession session) {
        readRegMembers(session, true);
    }
}
