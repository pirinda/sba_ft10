/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.mfg.db;

import ft.mod.DModConsts;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import sba.gui.util.DUtilConsts;
import sba.lib.DLibTimeUtils;
import sba.lib.DLibUtils;
import sba.lib.db.DDbConsts;
import sba.lib.db.DDbRegistryUser;
import sba.lib.gui.DGuiSession;

/**
 *
 * @author Sergio Flores
 */
public class DDbYear extends DDbRegistryUser {

    protected int mnPkYearId;
    protected Date mtStart;
    /*
    protected boolean mbDeleted;
    protected boolean mbSystem;
    protected int mnFkUserInsertId;
    protected int mnFkUserUpdateId;
    protected Date mtTsUserInsert;
    protected Date mtTsUserUpdate;
    */
    
    protected ArrayList<DDbYearWeek> maChildWeeks;

    public DDbYear() {
        super(DModConsts.M_YER);
        maChildWeeks = new ArrayList<>();
        initRegistry();
    }
    
    private void createWeeks() {
        DDbYearWeek child = null;
        
        maChildWeeks.clear();
        
        for (int week = 0; week < DMfgConsts.YEAR_WEEKS; week++) {
            child = new DDbYearWeek();
            
            //child.setPkYearId(...);
            child.setPkWeekId(week + 1);
            child.setStart(DLibTimeUtils.addDate(mtStart, 0, 0, week * 7));
            
            maChildWeeks.add(child);
        }
    }
    
    private void updateWeeks() {
        int week = 0;
        
        for (DDbYearWeek child : maChildWeeks) {
            child.setStart(DLibTimeUtils.addDate(mtStart, 0, 0, week++ * 7));
        }
    }
    
    public void setPkYearId(int n) { mnPkYearId = n; }
    public void setStart(Date t) { mtStart = t; }
    public void setDeleted(boolean b) { mbDeleted = b; }
    public void setSystem(boolean b) { mbSystem = b; }
    public void setFkUserInsertId(int n) { mnFkUserInsertId = n; }
    public void setFkUserUpdateId(int n) { mnFkUserUpdateId = n; }
    public void setTsUserInsert(Date t) { mtTsUserInsert = t; }
    public void setTsUserUpdate(Date t) { mtTsUserUpdate = t; }

    public int getPkYearId() { return mnPkYearId; }
    public Date getStart() { return mtStart; }
    public boolean isDeleted() { return mbDeleted; }
    public boolean isSystem() { return mbSystem; }
    public int getFkUserInsertId() { return mnFkUserInsertId; }
    public int getFkUserUpdateId() { return mnFkUserUpdateId; }
    public Date getTsUserInsert() { return mtTsUserInsert; }
    public Date getTsUserUpdate() { return mtTsUserUpdate; }

    public ArrayList<DDbYearWeek> getChildWeeks() { return maChildWeeks; }
    
    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkYearId = pk[0];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkYearId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkYearId = 0;
        mtStart = null;
        mbDeleted = false;
        mbSystem = false;
        mnFkUserInsertId = 0;
        mnFkUserUpdateId = 0;
        mtTsUserInsert = null;
        mtTsUserUpdate = null;
        
        maChildWeeks.clear();
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_yer = " + mnPkYearId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_yer = " + pk[0] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet.");
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
            mnPkYearId = resultSet.getInt("id_yer");
            mtStart = resultSet.getDate("sta");
            mbDeleted = resultSet.getBoolean("b_del");
            mbSystem = resultSet.getBoolean("b_sys");
            mnFkUserInsertId = resultSet.getInt("fk_usr_ins");
            mnFkUserUpdateId = resultSet.getInt("fk_usr_upd");
            mtTsUserInsert = resultSet.getTimestamp("ts_usr_ins");
            mtTsUserUpdate = resultSet.getTimestamp("ts_usr_upd");

            // Read aswell child registries:

            statement = session.getStatement().getConnection().createStatement();

            msSql = "SELECT id_wek FROM " + DModConsts.TablesMap.get(DModConsts.M_YER_WEK) + " " + getSqlWhere();
            resultSet = statement.executeQuery(msSql);
            while (resultSet.next()) {
                DDbYearWeek child = new DDbYearWeek();
                child.read(session, new int[] { mnPkYearId, resultSet.getInt(1) });
                maChildWeeks.add(child);
            }

            // Finish registry reading:

            mbRegistryNew = false;
        }

        mnQueryResultId = DDbConsts.READ_OK;
    }

    @Override
    public void save(DGuiSession session) throws SQLException, Exception {
        initQueryMembers();
        mnQueryResultId = DDbConsts.SAVE_ERROR;

        if (mbRegistryNew) {
            verifyRegistryNew(session);
        }
        
        if (mbRegistryNew) {
            mbDeleted = false;
            mbSystem = false;
            mnFkUserInsertId = session.getUser().getPkUserId();
            mnFkUserUpdateId = DUtilConsts.USR_NA_ID;

            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkYearId + ", " + 
                    "'" + DLibUtils.DbmsDateFormatDate.format(mtStart) + "', " + 
                    (mbDeleted ? 1 : 0) + ", " + 
                    (mbSystem ? 1 : 0) + ", " + 
                    mnFkUserInsertId + ", " + 
                    mnFkUserUpdateId + ", " + 
                    "NOW()" + ", " + 
                    "NOW()" + " " + 
                    ")";
            
            createWeeks();
        }
        else {
            mnFkUserUpdateId = session.getUser().getPkUserId();

            msSql = "UPDATE " + getSqlTable() + " SET " +
                    //"id_yer = " + mnPkYearId + ", " +
                    "sta = '" + DLibUtils.DbmsDateFormatDate.format(mtStart) + "', " +
                    "b_del = " + (mbDeleted ? 1 : 0) + ", " +
                    "b_sys = " + (mbSystem ? 1 : 0) + ", " +
                    //"fk_usr_ins = " + mnFkUserInsertId + ", " +
                    "fk_usr_upd = " + mnFkUserUpdateId + ", " +
                    //"ts_usr_ins = " + "NOW()" + ", " +
                    "ts_usr_upd = " + "NOW()" + " " +
                    getSqlWhere();
            
            updateWeeks();
        }

        session.getStatement().execute(msSql);
        
        // Save aswell child registries:

        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.M_YER_WEK) + " " + getSqlWhere();
        session.getStatement().execute(msSql);

        for (DDbYearWeek child : maChildWeeks) {
            child.setPkYearId(mnPkYearId);
            child.setRegistryNew(true);
            child.save(session);
        }

        // Finish registry updating:

        mbRegistryNew = false;
        mnQueryResultId = DDbConsts.SAVE_OK;
    }

    @Override
    public DDbYear clone() throws CloneNotSupportedException {
        DDbYear registry = new DDbYear();

        registry.setPkYearId(this.getPkYearId());
        registry.setStart(this.getStart());
        registry.setDeleted(this.isDeleted());
        registry.setSystem(this.isSystem());
        registry.setFkUserInsertId(this.getFkUserInsertId());
        registry.setFkUserUpdateId(this.getFkUserUpdateId());
        registry.setTsUserInsert(this.getTsUserInsert());
        registry.setTsUserUpdate(this.getTsUserUpdate());
        
        for (DDbYearWeek child : maChildWeeks) {
            registry.getChildWeeks().add(child.clone());
        }

        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
}
