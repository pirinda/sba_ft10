/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.cfg.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import sba.gui.util.DUtilConsts;
import sba.lib.DLibConsts;
import sba.lib.db.DDbConsts;
import sba.lib.db.DDbRegistry;
import sba.lib.db.DDbRegistryUser;
import sba.lib.gui.DGuiClient;
import sba.lib.gui.DGuiSession;
import sba.lib.gui.DGuiSessionCustom;
import sba.lib.gui.DGuiUser;
import ft.mod.DModConsts;
import ft.mod.DModSysConsts;

/**
 *
 * @author Sergio Flores
 */
public class DDbUser extends DDbRegistryUser implements DGuiUser {

    public static final int FIELD_PASSWORD = DDbRegistry.FIELD_BASE + 1;

    protected int mnPkUserId;
    protected String msName;
    protected String msPassword;
    /*
    protected boolean mbDeleted;
    protected boolean mbSystem;
    */
    protected int mnFkUserTypeId;
    /*
    protected int mnFkUserInsertId;
    protected int mnFkUserUpdateId;
    protected Date mtTsUserInsert;
    protected Date mtTsUserUpdate;
    */

    protected ArrayList<DDbUserModule> maChildModules;
    protected HashSet<Integer> moSetModules;

    public DDbUser() {
        super(DModConsts.CU_USR);
        maChildModules = new ArrayList<>();
        moSetModules = new HashSet<>();
        initRegistry();
    }

    public void setPkUserId(int n) { mnPkUserId = n; }
    public void setName(String s) { msName = s; }
    public void setPassword(String s) { msPassword = s; }
    public void setDeleted(boolean b) { mbDeleted = b; }
    public void setSystem(boolean b) { mbSystem = b; }
    public void setFkUserTypeId(int n) { mnFkUserTypeId = n; }
    public void setFkUserInsertId(int n) { mnFkUserInsertId = n; }
    public void setFkUserUpdateId(int n) { mnFkUserUpdateId = n; }
    public void setTsUserInsert(Date t) { mtTsUserInsert = t; }
    public void setTsUserUpdate(Date t) { mtTsUserUpdate = t; }

    public int getPkUserId() { return mnPkUserId; }
    public String getName() { return msName; }
    public String getPassword() { return msPassword; }
    public boolean isDeleted() { return mbDeleted; }
    public boolean isSystem() { return mbSystem; }
    public int getFkUserTypeId() { return mnFkUserTypeId; }
    public int getFkUserInsertId() { return mnFkUserInsertId; }
    public int getFkUserUpdateId() { return mnFkUserUpdateId; }
    public Date getTsUserInsert() { return mtTsUserInsert; }
    public Date getTsUserUpdate() { return mtTsUserUpdate; }

    public ArrayList<DDbUserModule> getChildModules() { return maChildModules; }

    @Override
    public boolean isAdministrator() {
        return isSupervisor() || mnFkUserTypeId == DModSysConsts.CS_USR_TP_ADM;
    }

    @Override
    public boolean isSupervisor() {
        return mnFkUserTypeId == DModSysConsts.CS_USR_TP_SUP;
    }

    @Override
    public boolean hasModuleAccess(final int module) {
        return isAdministrator() || moSetModules.contains(module);
    }

    @Override
    public boolean hasPrivilege(final int privilege) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean hasPrivilege(final int[] privileges) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getPrivilegeLevel(final int privilege) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public HashMap<Integer, Integer> getPrivilegesMap() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public HashSet<Integer> getModulesSet() {
        return moSetModules;
    }

    @Override
    public void computeAccess(DGuiSession session) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DGuiSessionCustom createDefaultUserSession(final DGuiClient client) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DGuiSessionCustom createDefaultUserSession(final DGuiClient client, final int terminal) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean showUserSessionConfigOnLogin() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkUserId = pk[0];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkUserId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkUserId = 0;
        msName = "";
        msPassword = "";
        mbDeleted = false;
        mbSystem = false;
        mnFkUserTypeId = 0;
        mnFkUserInsertId = 0;
        mnFkUserUpdateId = 0;
        mtTsUserInsert = null;
        mtTsUserUpdate = null;

        maChildModules.clear();
        moSetModules.clear();
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_usr = " + mnPkUserId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_usr = " + pk[0] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkUserId = 0;

        msSql = "SELECT COALESCE(MAX(id_usr), 0) + 1 FROM " + getSqlTable();
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkUserId = resultSet.getInt(1);
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
            mnPkUserId = resultSet.getInt("id_usr");
            msName = resultSet.getString("name");
            //msPassword = resultSet.getString("pswd");     // stored value is a string digestion, so it is useless
            mbDeleted = resultSet.getBoolean("b_del");
            mbSystem = resultSet.getBoolean("b_sys");
            mnFkUserTypeId = resultSet.getInt("fk_usr_tp");
            mnFkUserInsertId = resultSet.getInt("fk_usr_ins");
            mnFkUserUpdateId = resultSet.getInt("fk_usr_upd");
            mtTsUserInsert = resultSet.getTimestamp("ts_usr_ins");
            mtTsUserUpdate = resultSet.getTimestamp("ts_usr_upd");

            // Read aswell child registries:

            statement = session.getStatement().getConnection().createStatement();

            msSql = "SELECT id_mod FROM " + DModConsts.TablesMap.get(DModConsts.CU_USR_MOD) + " " + getSqlWhere();
            resultSet = statement.executeQuery(msSql);
            while (resultSet.next()) {
                DDbUserModule child = new DDbUserModule();
                child.read(session, new int[] { mnPkUserId, resultSet.getInt(1) });
                maChildModules.add(child);
                moSetModules.add(resultSet.getInt(1));
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
            computePrimaryKey(session);
            mbDeleted = false;
            mbSystem = false;
            mnFkUserInsertId = session.getUser().getPkUserId();
            mnFkUserUpdateId = DUtilConsts.USR_NA_ID;

            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkUserId + ", " + 
                    "'" + msName + "', " + 
                    "PASSWORD('" + msPassword + "'), " + 
                    (mbDeleted ? 1 : 0) + ", " + 
                    (mbSystem ? 1 : 0) + ", " + 
                    mnFkUserTypeId + ", " + 
                    mnFkUserInsertId + ", " + 
                    mnFkUserUpdateId + ", " + 
                    "NOW()" + ", " + 
                    "NOW()" + " " + 
                    ")";
        }
        else {
            mnFkUserUpdateId = session.getUser().getPkUserId();

            msSql = "UPDATE " + getSqlTable() + " SET " +
                    //"id_usr = " + mnPkUserId + ", " +
                    "name = '" + msName + "', " +
                    (msPassword.length() == 0 ? "" : "pswd = PASSWORD('" + msPassword + "'), ") +
                    "b_del = " + (mbDeleted ? 1 : 0) + ", " +
                    "b_sys = " + (mbSystem ? 1 : 0) + ", " +
                    "fk_usr_tp = " + mnFkUserTypeId + ", " +
                    //"fk_usr_ins = " + mnFkUserInsertId + ", " +
                    "fk_usr_upd = " + mnFkUserUpdateId + ", " +
                    //"ts_usr_ins = " + "NOW()" + ", " +
                    "ts_usr_upd = " + "NOW()" + " " +
                    getSqlWhere();
        }

        session.getStatement().execute(msSql);

        // Save aswell child registries:

        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.CU_USR_MOD) + " " + getSqlWhere();
        session.getStatement().execute(msSql);

        for (DDbUserModule child : maChildModules) {
            child.setPkUserId(mnPkUserId);
            child.setRegistryNew(true);
            child.save(session);
        }

        // Finish registry updating:

        mbRegistryNew = false;
        mnQueryResultId = DDbConsts.SAVE_OK;
    }

    @Override
    public DDbUser clone() throws CloneNotSupportedException {
        DDbUser registry = new DDbUser();

        registry.setPkUserId(this.getPkUserId());
        registry.setName(this.getName());
        registry.setPassword(this.getPassword());
        registry.setDeleted(this.isDeleted());
        registry.setSystem(this.isSystem());
        registry.setFkUserTypeId(this.getFkUserTypeId());
        registry.setFkUserInsertId(this.getFkUserInsertId());
        registry.setFkUserUpdateId(this.getFkUserUpdateId());
        registry.setTsUserInsert(this.getTsUserInsert());
        registry.setTsUserUpdate(this.getTsUserUpdate());

        for (DDbUserModule child : maChildModules) {
            registry.getChildModules().add(child.clone());
        }

        for (Integer item : moSetModules) {
            registry.getModulesSet().add(item);
        }

        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }

    @Override
    public void saveField(final Statement statement, final int[] pk, final int field, final Object value) throws SQLException, Exception {
        initQueryMembers();
        mnQueryResultId = DDbConsts.SAVE_ERROR;

        msSql = "UPDATE " + getSqlTable() + " SET ";

        switch (field) {
            case FIELD_PASSWORD:
                msSql += "pswd = PASSWORD('" + value + "') ";
                break;
            default:
                throw new Exception(DLibConsts.ERR_MSG_OPTION_UNKNOWN);
        }

        msSql += getSqlWhere(pk);
        statement.execute(msSql);
        mnQueryResultId = DDbConsts.SAVE_OK;
    }
}
