/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.qty.db;

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
public class DDbTest extends DDbRegistryUser {

    protected int mnPkTestId;
    protected String msCode;
    protected String msName;
    /*
    protected boolean mbDeleted;
    protected boolean mbSystem;
    */
    protected int mnFkTestTypeId;
    /*
    protected int mnFkUserInsertId;
    protected int mnFkUserUpdateId;
    protected Date mtTsUserInsert;
    protected Date mtTsUserUpdate;
    */
    
    protected ArrayList<DDbTestVariable> maChildVariables;
    protected ArrayList<DDbTestFamily> maChildFamilies;
    
    public DDbTest() {
        super(DModConsts.QU_TST);
        maChildVariables = new ArrayList<>();
        maChildFamilies = new ArrayList<>();
        initRegistry();
    }

    public void setPkTestId(int n) { mnPkTestId = n; }
    public void setCode(String s) { msCode = s; }
    public void setName(String s) { msName = s; }
    public void setDeleted(boolean b) { mbDeleted = b; }
    public void setSystem(boolean b) { mbSystem = b; }
    public void setFkTestTypeId(int n) { mnFkTestTypeId = n; }
    public void setFkUserInsertId(int n) { mnFkUserInsertId = n; }
    public void setFkUserUpdateId(int n) { mnFkUserUpdateId = n; }
    public void setTsUserInsert(Date t) { mtTsUserInsert = t; }
    public void setTsUserUpdate(Date t) { mtTsUserUpdate = t; }

    public int getPkTestId() { return mnPkTestId; }
    public String getCode() { return msCode; }
    public String getName() { return msName; }
    public boolean isDeleted() { return mbDeleted; }
    public boolean isSystem() { return mbSystem; }
    public int getFkTestTypeId() { return mnFkTestTypeId; }
    public int getFkUserInsertId() { return mnFkUserInsertId; }
    public int getFkUserUpdateId() { return mnFkUserUpdateId; }
    public Date getTsUserInsert() { return mtTsUserInsert; }
    public Date getTsUserUpdate() { return mtTsUserUpdate; }

    public ArrayList<DDbTestVariable> getChildVariables() { return maChildVariables; }
    public ArrayList<DDbTestFamily> getChildFamilies() { return maChildFamilies; }
    
    public boolean isUtilVariableSelected(final int idVariable) {
        boolean checked = false;
        
        for (DDbTestVariable child : maChildVariables) {
            if (idVariable == child.getPkVariableId()) {
                checked = true;
                break;
            }
        }
        
        return checked;
    }
    
    public boolean isUtilFamilySelected(final int idFamily) {
        boolean checked = false;
        
        for (DDbTestFamily child : maChildFamilies) {
            if (idFamily == child.getPkFamilyId()) {
                checked = true;
                break;
            }
        }
        
        return checked;
    }
    
    public int getUtilFamilyResults(final int idFamily) {
        int results = 0;
        
        for (DDbTestFamily child : maChildFamilies) {
            if (idFamily == child.getPkFamilyId()) {
                results = child.getResultsDesired();
                break;
            }
        }
        
        return results;
    }
    
    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkTestId = pk[0];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkTestId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkTestId = 0;
        msCode = "";
        msName = "";
        mbDeleted = false;
        mbSystem = false;
        mnFkTestTypeId = 0;
        mnFkUserInsertId = 0;
        mnFkUserUpdateId = 0;
        mtTsUserInsert = null;
        mtTsUserUpdate = null;
        
        maChildVariables.clear();
        maChildFamilies.clear();
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_tst = " + mnPkTestId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_tst = " + pk[0] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkTestId = 0;

        msSql = "SELECT COALESCE(MAX(id_tst), 0) + 1 FROM " + getSqlTable();
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkTestId = resultSet.getInt(1);
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
            mnPkTestId = resultSet.getInt("id_tst");
            msCode = resultSet.getString("code");
            msName = resultSet.getString("name");
            mbDeleted = resultSet.getBoolean("b_del");
            mbSystem = resultSet.getBoolean("b_sys");
            mnFkTestTypeId = resultSet.getInt("fk_tst_tp");
            mnFkUserInsertId = resultSet.getInt("fk_usr_ins");
            mnFkUserUpdateId = resultSet.getInt("fk_usr_upd");
            mtTsUserInsert = resultSet.getTimestamp("ts_usr_ins");
            mtTsUserUpdate = resultSet.getTimestamp("ts_usr_upd");

            // Read aswell child registries:
            
            statement = session.getStatement().getConnection().createStatement();
            
            msSql = "SELECT id_var FROM " + DModConsts.TablesMap.get(DModConsts.QU_TST_VAR) + " " + getSqlWhere() +
                    "ORDER BY id_var ";
            resultSet = statement.executeQuery(msSql);
            while (resultSet.next()) {
                DDbTestVariable child = new DDbTestVariable();
                child.read(session, new int[] { mnPkTestId, resultSet.getInt(1) });
                maChildVariables.add(child);
            }
            
            msSql = "SELECT id_fam FROM " + DModConsts.TablesMap.get(DModConsts.QU_TST_FAM) + " " + getSqlWhere() +
                    "ORDER BY id_fam ";
            resultSet = statement.executeQuery(msSql);
            while (resultSet.next()) {
                DDbTestFamily child = new DDbTestFamily();
                child.read(session, new int[] { mnPkTestId, resultSet.getInt(1) });
                maChildFamilies.add(child);
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
                msCode = "" + mnPkTestId;
            }

            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkTestId + ", " +
                    "'" + msCode + "', " +
                    "'" + msName + "', " +
                    (mbDeleted ? 1 : 0) + ", " +
                    (mbSystem ? 1 : 0) + ", " +
                    mnFkTestTypeId + ", " + 
                    mnFkUserInsertId + ", " +
                    mnFkUserUpdateId + ", " +
                    "NOW()" + ", " +
                    "NOW()" + " " +
                    ")";
        }
        else {
            mnFkUserUpdateId = session.getUser().getPkUserId();

            msSql = "UPDATE " + getSqlTable() + " SET " +
                    //"id_tst = " + mnPkTestId + ", " +
                    "code = '" + (msCode.length() > 0 ? msCode : "" + mnPkTestId) + "', " +
                    "name = '" + msName + "', " +
                    "b_del = " + (mbDeleted ? 1 : 0) + ", " +
                    "b_sys = " + (mbSystem ? 1 : 0) + ", " +
                    "fk_tst_tp = " + mnFkTestTypeId + ", " +
                    //"fk_usr_ins = " + mnFkUserInsertId + ", " +
                    "fk_usr_upd = " + mnFkUserUpdateId + ", " +
                    //"ts_usr_ins = " + "NOW()" + ", " +
                    "ts_usr_upd = " + "NOW()" + " " +
                    getSqlWhere();
        }

        session.getStatement().execute(msSql);
        
        // Save aswell child registries:

        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.QU_TST_VAR) + " " + getSqlWhere();
        session.getStatement().execute(msSql);
        
        for (DDbTestVariable child : maChildVariables) {
            child.setRegistryNew(true);
            child.setPkTestId(mnPkTestId);
            child.save(session);
        }

        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.QU_TST_FAM) + " " + getSqlWhere();
        session.getStatement().execute(msSql);
        
        for (DDbTestFamily child : maChildFamilies) {
            child.setRegistryNew(true);
            child.setPkTestId(mnPkTestId);
            child.save(session);
        }

        // Finish registry saving:
        
        mbRegistryNew = false;
        mnQueryResultId = DDbConsts.SAVE_OK;
    }

    @Override
    public DDbTest clone() throws CloneNotSupportedException {
        DDbTest registry = new DDbTest();

        registry.setPkTestId(this.getPkTestId());
        registry.setCode(this.getCode());
        registry.setName(this.getName());
        registry.setDeleted(this.isDeleted());
        registry.setSystem(this.isSystem());
        registry.setFkTestTypeId(this.getFkTestTypeId());
        registry.setFkUserInsertId(this.getFkUserInsertId());
        registry.setFkUserUpdateId(this.getFkUserUpdateId());
        registry.setTsUserInsert(this.getTsUserInsert());
        registry.setTsUserUpdate(this.getTsUserUpdate());
        
        for (DDbTestVariable child : maChildVariables) {
            registry.getChildVariables().add(child.clone());
        }
        
        for (DDbTestFamily child : maChildFamilies) {
            registry.getChildFamilies().add(child.clone());
        }
        
        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
}
