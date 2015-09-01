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
import sba.lib.db.DDbConsts;
import sba.lib.db.DDbRegistryUser;
import sba.lib.gui.DGuiSession;

/**
 *
 * @author Sergio Flores
 */
public class DDbLinePack extends DDbRegistryUser {

    protected int mnPkLinePackId;
    protected String msCode;
    protected String msName;
    /*
    protected boolean mbDeleted;
    protected boolean mbSystem;
    */
    protected int mnFkDepartmentId;
    /*
    protected int mnFkUserInsertId;
    protected int mnFkUserUpdateId;
    protected Date mtTsUserInsert;
    protected Date mtTsUserUpdate;
    */

    protected ArrayList<DDbLinePackPresent> maChildPresents;
    
    public DDbLinePack() {
        super(DModConsts.MU_LIN_PCK);
        maChildPresents = new ArrayList<>();
        initRegistry();
    }

    public void setPkLinePackId(int n) { mnPkLinePackId = n; }
    public void setCode(String s) { msCode = s; }
    public void setName(String s) { msName = s; }
    public void setDeleted(boolean b) { mbDeleted = b; }
    public void setSystem(boolean b) { mbSystem = b; }
    public void setFkDepartmentId(int n) { mnFkDepartmentId = n; }
    public void setFkUserInsertId(int n) { mnFkUserInsertId = n; }
    public void setFkUserUpdateId(int n) { mnFkUserUpdateId = n; }
    public void setTsUserInsert(Date t) { mtTsUserInsert = t; }
    public void setTsUserUpdate(Date t) { mtTsUserUpdate = t; }

    public int getPkLinePackId() { return mnPkLinePackId; }
    public String getCode() { return msCode; }
    public String getName() { return msName; }
    public boolean isDeleted() { return mbDeleted; }
    public boolean isSystem() { return mbSystem; }
    public int getFkDepartmentId() { return mnFkDepartmentId; }
    public int getFkUserInsertId() { return mnFkUserInsertId; }
    public int getFkUserUpdateId() { return mnFkUserUpdateId; }
    public Date getTsUserInsert() { return mtTsUserInsert; }
    public Date getTsUserUpdate() { return mtTsUserUpdate; }

    public ArrayList<DDbLinePackPresent> getChildPresents() { return maChildPresents; }
    
    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkLinePackId = pk[0];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkLinePackId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkLinePackId = 0;
        msCode = "";
        msName = "";
        mbDeleted = false;
        mbSystem = false;
        mnFkDepartmentId = 0;
        mnFkUserInsertId = 0;
        mnFkUserUpdateId = 0;
        mtTsUserInsert = null;
        mtTsUserUpdate = null;
        
        maChildPresents.clear();
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_lin_pck = " + mnPkLinePackId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_lin_pck = " + pk[0] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkLinePackId = 0;

        msSql = "SELECT COALESCE(MAX(id_lin_pck), 0) + 1 FROM " + getSqlTable();
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkLinePackId = resultSet.getInt(1);
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
            mnPkLinePackId = resultSet.getInt("id_lin_pck");
            msCode = resultSet.getString("code");
            msName = resultSet.getString("name");
            mbDeleted = resultSet.getBoolean("b_del");
            mbSystem = resultSet.getBoolean("b_sys");
            mnFkDepartmentId = resultSet.getInt("fk_dpt");
            mnFkUserInsertId = resultSet.getInt("fk_usr_ins");
            mnFkUserUpdateId = resultSet.getInt("fk_usr_upd");
            mtTsUserInsert = resultSet.getTimestamp("ts_usr_ins");
            mtTsUserUpdate = resultSet.getTimestamp("ts_usr_upd");

            // Read aswell child registries:

            statement = session.getStatement().getConnection().createStatement();

            msSql = "SELECT id_prs FROM " + DModConsts.TablesMap.get(DModConsts.MU_LIN_PCK_PRS) + " " + getSqlWhere();
            resultSet = statement.executeQuery(msSql);
            while (resultSet.next()) {
                DDbLinePackPresent child = new DDbLinePackPresent();
                child.read(session, new int[] { mnPkLinePackId, resultSet.getInt(1) });
                maChildPresents.add(child);
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

            if (msCode.isEmpty()) {
                msCode = "" + mnPkLinePackId;
            }

            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkLinePackId + ", " +
                    "'" + msCode.length() + "', " +
                    "'" + msName + "', " +
                    (mbDeleted ? 1 : 0) + ", " +
                    (mbSystem ? 1 : 0) + ", " +
                    mnFkDepartmentId + ", " + 
                    mnFkUserInsertId + ", " +
                    mnFkUserUpdateId + ", " +
                    "NOW()" + ", " +
                    "NOW()" + " " +
                    ")";
        }
        else {
            mnFkUserUpdateId = session.getUser().getPkUserId();

            msSql = "UPDATE " + getSqlTable() + " SET " +
                    //"id_lin_pck = " + mnPkLinePackId + ", " +
                    "code = '" + (msCode.length() > 0 ? msCode : "" + mnPkLinePackId) + "', " +
                    "name = '" + msName + "', " +
                    "b_del = " + (mbDeleted ? 1 : 0) + ", " +
                    "b_sys = " + (mbSystem ? 1 : 0) + ", " +
                    "fk_dpt = " + mnFkDepartmentId + ", " +
                    //"fk_usr_ins = " + mnFkUserInsertId + ", " +
                    "fk_usr_upd = " + mnFkUserUpdateId + ", " +
                    //"ts_usr_ins = " + "NOW()" + ", " +
                    "ts_usr_upd = " + "NOW()" + " " +
                    getSqlWhere();
        }

        session.getStatement().execute(msSql);
        
        // Save aswell child registries:

        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.MU_LIN_PCK_PRS) + " " + getSqlWhere();
        session.getStatement().execute(msSql);

        for (DDbLinePackPresent child : maChildPresents) {
            child.setPkLinePackId(mnPkLinePackId);
            child.setRegistryNew(true);
            child.save(session);
        }

        // Finish registry updating:

        mbRegistryNew = false;
        mnQueryResultId = DDbConsts.SAVE_OK;
    }

    @Override
    public DDbLinePack clone() throws CloneNotSupportedException {
        DDbLinePack registry = new DDbLinePack();

        registry.setPkLinePackId(this.getPkLinePackId());
        registry.setCode(this.getCode());
        registry.setName(this.getName());
        registry.setDeleted(this.isDeleted());
        registry.setSystem(this.isSystem());
        registry.setFkDepartmentId(this.getFkDepartmentId());
        registry.setFkUserInsertId(this.getFkUserInsertId());
        registry.setFkUserUpdateId(this.getFkUserUpdateId());
        registry.setTsUserInsert(this.getTsUserInsert());
        registry.setTsUserUpdate(this.getTsUserUpdate());

        for (DDbLinePackPresent child : maChildPresents) {
            registry.getChildPresents().add(child.clone());
        }

        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
}
