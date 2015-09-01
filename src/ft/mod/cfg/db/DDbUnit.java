/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.cfg.db;

import ft.mod.DModConsts;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import sba.gui.util.DUtilConsts;
import sba.lib.db.DDbConsts;
import sba.lib.db.DDbRegistryUser;
import sba.lib.gui.DGuiSession;

/**
 *
 * @author Sergio Flores
 */
public class DDbUnit extends DDbRegistryUser {

    protected int mnPkUnitId;
    protected String msCode;
    protected String msName;
    protected int mnSortingPos;
    protected double mdBaseEquivalence;
    protected boolean mbDefault;
    /*
    protected boolean mbDeleted;
    protected boolean mbSystem;
    */
    protected int mnFkUnitTypeId;
    /*
    protected int mnFkUserInsertId;
    protected int mnFkUserUpdateId;
    protected Date mtTsUserInsert;
    protected Date mtTsUserUpdate;
    */
    
    protected boolean mbXtaMass;

    public DDbUnit() {
        super(DModConsts.CU_UNT);
        initRegistry();
    }

    public void readXtaData(final DGuiSession session) throws SQLException, Exception {
        String sql = "";
        ResultSet resultSet = null;
        
        // Reset extra data:
        
        mbXtaMass = false;
        
        // Read extra data:
        
        sql = "SELECT b_mss FROM " + DModConsts.TablesMap.get(DModConsts.CS_UNT_TP) + " WHERE id_unt_tp = " + mnFkUnitTypeId + " ";
        resultSet = session.getStatement().executeQuery(sql);
        if (!resultSet.next()) {
            throw new Exception(DDbConsts.ERR_MSG_REG_NOT_FOUND);
        }
        else {
            mbXtaMass = resultSet.getBoolean(1);
        }
    }
    
    public void setPkUnitId(int n) { mnPkUnitId = n; }
    public void setCode(String s) { msCode = s; }
    public void setName(String s) { msName = s; }
    public void setSortingPos(int n) { mnSortingPos = n; }
    public void setBaseEquivalence(double d) { mdBaseEquivalence = d; }
    public void setDefault(boolean b) { mbDefault = b; }
    public void setDeleted(boolean b) { mbDeleted = b; }
    public void setSystem(boolean b) { mbSystem = b; }
    public void setFkUnitTypeId(int n) { mnFkUnitTypeId = n; }
    public void setFkUserInsertId(int n) { mnFkUserInsertId = n; }
    public void setFkUserUpdateId(int n) { mnFkUserUpdateId = n; }
    public void setTsUserInsert(Date t) { mtTsUserInsert = t; }
    public void setTsUserUpdate(Date t) { mtTsUserUpdate = t; }

    public int getPkUnitId() { return mnPkUnitId; }
    public String getCode() { return msCode; }
    public String getName() { return msName; }
    public int getSortingPos() { return mnSortingPos; }
    public double getBaseEquivalence() { return mdBaseEquivalence; }
    public boolean isDefault() { return mbDefault; }
    public boolean isDeleted() { return mbDeleted; }
    public boolean isSystem() { return mbSystem; }
    public int getFkUnitTypeId() { return mnFkUnitTypeId; }
    public int getFkUserInsertId() { return mnFkUserInsertId; }
    public int getFkUserUpdateId() { return mnFkUserUpdateId; }
    public Date getTsUserInsert() { return mtTsUserInsert; }
    public Date getTsUserUpdate() { return mtTsUserUpdate; }

    public void setXtaMass(boolean b) { mbXtaMass = b; }
    
    public boolean isXtaMass() { return mbXtaMass; }
    
    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkUnitId = pk[0];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkUnitId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkUnitId = 0;
        msCode = "";
        msName = "";
        mnSortingPos = 0;
        mdBaseEquivalence = 0;
        mbDefault = false;
        mbDeleted = false;
        mbSystem = false;
        mnFkUnitTypeId = 0;
        mnFkUserInsertId = 0;
        mnFkUserUpdateId = 0;
        mtTsUserInsert = null;
        mtTsUserUpdate = null;
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_unt = " + mnPkUnitId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_unt = " + pk[0] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkUnitId = 0;

        msSql = "SELECT COALESCE(MAX(id_unt), 0) + 1 FROM " + getSqlTable();
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkUnitId = resultSet.getInt(1);
        }
    }

    @Override
    public void read(DGuiSession session, int[] pk) throws SQLException, Exception {
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
            mnPkUnitId = resultSet.getInt("id_unt");
            msCode = resultSet.getString("code");
            msName = resultSet.getString("name");
            mnSortingPos = resultSet.getInt("sort");
            mdBaseEquivalence = resultSet.getDouble("bas_eqv");
            mbDefault = resultSet.getBoolean("b_def");
            mbDeleted = resultSet.getBoolean("b_del");
            mbSystem = resultSet.getBoolean("b_sys");
            mnFkUnitTypeId = resultSet.getInt("fk_unt_tp");
            mnFkUserInsertId = resultSet.getInt("fk_usr_ins");
            mnFkUserUpdateId = resultSet.getInt("fk_usr_upd");
            mtTsUserInsert = resultSet.getTimestamp("ts_usr_ins");
            mtTsUserUpdate = resultSet.getTimestamp("ts_usr_upd");
            
            // Read aswell extra data:

            readXtaData(session);
            
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
            computePrimaryKey(session);
            mbDeleted = false;
            mbSystem = false;
            mnFkUserInsertId = session.getUser().getPkUserId();
            mnFkUserUpdateId = DUtilConsts.USR_NA_ID;

            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkUnitId + ", " + 
                    "'" + msCode + "', " + 
                    "'" + msName + "', " + 
                    mnSortingPos + ", " + 
                    mdBaseEquivalence + ", " + 
                    (mbDefault ? 1 : 0) + ", " + 
                    (mbDeleted ? 1 : 0) + ", " + 
                    (mbSystem ? 1 : 0) + ", " + 
                    mnFkUnitTypeId + ", " + 
                    mnFkUserInsertId + ", " + 
                    mnFkUserUpdateId + ", " + 
                    "NOW()" + ", " + 
                    "NOW()" + " " + 
                    ")";
        }
        else {
            mnFkUserUpdateId = session.getUser().getPkUserId();

            msSql = "UPDATE " + getSqlTable() + " SET " +
                    //"id_unt = " + mnPkUnitId + ", " +
                    "code = '" + msCode + "', " +
                    "name = '" + msName + "', " +
                    "sort = " + mnSortingPos + ", " +
                    "bas_eqv = " + mdBaseEquivalence + ", " +
                    "b_def = " + (mbDefault ? 1 : 0) + ", " +
                    "b_del = " + (mbDeleted ? 1 : 0) + ", " +
                    "b_sys = " + (mbSystem ? 1 : 0) + ", " +
                    "fk_unt_tp = " + mnFkUnitTypeId + ", " +
                    //"fk_usr_ins = " + mnFkUserInsertId + ", " +
                    "fk_usr_upd = " + mnFkUserUpdateId + ", " +
                    //"ts_usr_ins = " + "NOW()" + ", " +
                    "ts_usr_upd = " + "NOW()" + " " +
                    getSqlWhere();
        }

        session.getStatement().execute(msSql);
        mbRegistryNew = false;
        mnQueryResultId = DDbConsts.SAVE_OK;
    }

    @Override
    public DDbUnit clone() throws CloneNotSupportedException {
        DDbUnit registry = new DDbUnit();

        registry.setPkUnitId(this.getPkUnitId());
        registry.setCode(this.getCode());
        registry.setName(this.getName());
        registry.setSortingPos(this.getSortingPos());
        registry.setBaseEquivalence(this.getBaseEquivalence());
        registry.setDefault(this.isDefault());
        registry.setDeleted(this.isDeleted());
        registry.setSystem(this.isSystem());
        registry.setFkUnitTypeId(this.getFkUnitTypeId());
        registry.setFkUserInsertId(this.getFkUserInsertId());
        registry.setFkUserUpdateId(this.getFkUserUpdateId());
        registry.setTsUserInsert(this.getTsUserInsert());
        registry.setTsUserUpdate(this.getTsUserUpdate());

        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
}
