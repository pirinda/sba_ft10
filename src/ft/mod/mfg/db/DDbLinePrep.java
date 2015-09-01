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
public class DDbLinePrep extends DDbRegistryUser {

    protected int mnPkLinePrepId;
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

    protected ArrayList<DDbLinePrepItemFamily> maChildItemFamilies;
    protected ArrayList<DDbLinePrepLinePack> maChildLinePacks;
    
    public DDbLinePrep() {
        super(DModConsts.MU_LIN_PRP);
        maChildItemFamilies = new ArrayList<>();
        maChildLinePacks = new ArrayList<>();
        initRegistry();
    }

    public void setPkLinePrepId(int n) { mnPkLinePrepId = n; }
    public void setCode(String s) { msCode = s; }
    public void setName(String s) { msName = s; }
    public void setDeleted(boolean b) { mbDeleted = b; }
    public void setSystem(boolean b) { mbSystem = b; }
    public void setFkDepartmentId(int n) { mnFkDepartmentId = n; }
    public void setFkUserInsertId(int n) { mnFkUserInsertId = n; }
    public void setFkUserUpdateId(int n) { mnFkUserUpdateId = n; }
    public void setTsUserInsert(Date t) { mtTsUserInsert = t; }
    public void setTsUserUpdate(Date t) { mtTsUserUpdate = t; }

    public int getPkLinePrepId() { return mnPkLinePrepId; }
    public String getCode() { return msCode; }
    public String getName() { return msName; }
    public boolean isDeleted() { return mbDeleted; }
    public boolean isSystem() { return mbSystem; }
    public int getFkDepartmentId() { return mnFkDepartmentId; }
    public int getFkUserInsertId() { return mnFkUserInsertId; }
    public int getFkUserUpdateId() { return mnFkUserUpdateId; }
    public Date getTsUserInsert() { return mtTsUserInsert; }
    public Date getTsUserUpdate() { return mtTsUserUpdate; }

    public ArrayList<DDbLinePrepItemFamily> getChildItemFamilies() { return maChildItemFamilies; }
    public ArrayList<DDbLinePrepLinePack> getChildLinePacks() { return maChildLinePacks; }
    
    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkLinePrepId = pk[0];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkLinePrepId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkLinePrepId = 0;
        msCode = "";
        msName = "";
        mbDeleted = false;
        mbSystem = false;
        mnFkDepartmentId = 0;
        mnFkUserInsertId = 0;
        mnFkUserUpdateId = 0;
        mtTsUserInsert = null;
        mtTsUserUpdate = null;
        
        maChildItemFamilies.clear();
        maChildLinePacks.clear();
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_lin_prp = " + mnPkLinePrepId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_lin_prp = " + pk[0] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkLinePrepId = 0;

        msSql = "SELECT COALESCE(MAX(id_lin_prp), 0) + 1 FROM " + getSqlTable();
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkLinePrepId = resultSet.getInt(1);
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
            mnPkLinePrepId = resultSet.getInt("id_lin_prp");
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

            msSql = "SELECT id_fam FROM " + DModConsts.TablesMap.get(DModConsts.MU_LIN_PRP_FAM) + " " + getSqlWhere();
            resultSet = statement.executeQuery(msSql);
            while (resultSet.next()) {
                DDbLinePrepItemFamily child = new DDbLinePrepItemFamily();
                child.read(session, new int[] { mnPkLinePrepId, resultSet.getInt(1) });
                maChildItemFamilies.add(child);
            }

            msSql = "SELECT id_lin_pck FROM " + DModConsts.TablesMap.get(DModConsts.MU_LIN_PRP_LIN_PCK) + " " + getSqlWhere();
            resultSet = statement.executeQuery(msSql);
            while (resultSet.next()) {
                DDbLinePrepLinePack child = new DDbLinePrepLinePack();
                child.read(session, new int[] { mnPkLinePrepId, resultSet.getInt(1) });
                maChildLinePacks.add(child);
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
                msCode = "" + mnPkLinePrepId;
            }

            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkLinePrepId + ", " +
                    "'" + msCode + "', " +
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
                    //"id_lin_prp = " + mnPkLinePrepId + ", " +
                    "code = '" + (msCode.length() > 0 ? msCode : "" + mnPkLinePrepId) + "', " +
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

        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.MU_LIN_PRP_FAM) + " " + getSqlWhere();
        session.getStatement().execute(msSql);

        for (DDbLinePrepItemFamily child : maChildItemFamilies) {
            child.setPkLinePrepId(mnPkLinePrepId);
            child.setRegistryNew(true);
            child.save(session);
        }

        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.MU_LIN_PRP_LIN_PCK) + " " + getSqlWhere();
        session.getStatement().execute(msSql);

        for (DDbLinePrepLinePack child : maChildLinePacks) {
            child.setPkLinePrepId(mnPkLinePrepId);
            child.setRegistryNew(true);
            child.save(session);
        }

        // Finish registry updating:

        mbRegistryNew = false;
        mnQueryResultId = DDbConsts.SAVE_OK;
    }

    @Override
    public DDbLinePrep clone() throws CloneNotSupportedException {
        DDbLinePrep registry = new DDbLinePrep();

        registry.setPkLinePrepId(this.getPkLinePrepId());
        registry.setCode(this.getCode());
        registry.setName(this.getName());
        registry.setDeleted(this.isDeleted());
        registry.setSystem(this.isSystem());
        registry.setFkDepartmentId(this.getFkDepartmentId());
        registry.setFkUserInsertId(this.getFkUserInsertId());
        registry.setFkUserUpdateId(this.getFkUserUpdateId());
        registry.setTsUserInsert(this.getTsUserInsert());
        registry.setTsUserUpdate(this.getTsUserUpdate());

        for (DDbLinePrepItemFamily child : maChildItemFamilies) {
            registry.getChildItemFamilies().add(child.clone());
        }

        for (DDbLinePrepLinePack child : maChildLinePacks) {
            registry.getChildLinePacks().add(child.clone());
        }

        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
}
