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
public class DDbVariable extends DDbRegistryUser {

    protected int mnPkVariableId;
    protected String msCode;
    protected String msName;
    protected int mnDecimals;
    protected double mdValueMin;
    protected double mdValueMax;
    protected String msUnit;
    /*
    protected boolean mbDeleted;
    protected boolean mbSystem;
    protected int mnFkUserInsertId;
    protected int mnFkUserUpdateId;
    protected Date mtTsUserInsert;
    protected Date mtTsUserUpdate;
    */
    
    protected ArrayList<DDbVariableFamily> maChildFamilies;
    
    public DDbVariable() {
        super(DModConsts.MU_VAR);
        maChildFamilies = new ArrayList<>();
        initRegistry();
    }

    public void setPkVariableId(int n) { mnPkVariableId = n; }
    public void setCode(String s) { msCode = s; }
    public void setName(String s) { msName = s; }
    public void setDecimals(int n) { mnDecimals = n; }
    public void setValueMin(double d) { mdValueMin = d; }
    public void setValueMax(double d) { mdValueMax = d; }
    public void setUnit(String s) { msUnit = s; }
    public void setDeleted(boolean b) { mbDeleted = b; }
    public void setSystem(boolean b) { mbSystem = b; }
    public void setFkUserInsertId(int n) { mnFkUserInsertId = n; }
    public void setFkUserUpdateId(int n) { mnFkUserUpdateId = n; }
    public void setTsUserInsert(Date t) { mtTsUserInsert = t; }
    public void setTsUserUpdate(Date t) { mtTsUserUpdate = t; }

    public int getPkVariableId() { return mnPkVariableId; }
    public String getCode() { return msCode; }
    public String getName() { return msName; }
    public int getDecimals() { return mnDecimals; }
    public double getValueMin() { return mdValueMin; }
    public double getValueMax() { return mdValueMax; }
    public String getUnit() { return msUnit; }
    public boolean isDeleted() { return mbDeleted; }
    public boolean isSystem() { return mbSystem; }
    public int getFkUserInsertId() { return mnFkUserInsertId; }
    public int getFkUserUpdateId() { return mnFkUserUpdateId; }
    public Date getTsUserInsert() { return mtTsUserInsert; }
    public Date getTsUserUpdate() { return mtTsUserUpdate; }

    public ArrayList<DDbVariableFamily> getChildFamilies() { return maChildFamilies; }
    
    public boolean isUtilFamilyChecked(final int idFamily) {
        boolean checked = false;
        
        for (DDbVariableFamily family : maChildFamilies) {
            if (idFamily == family.getPkFamilyId()) {
                checked = true;
                break;
            }
        }
        
        return checked;
    }
    
    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkVariableId = pk[0];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkVariableId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkVariableId = 0;
        msCode = "";
        msName = "";
        mnDecimals = 0;
        mdValueMin = 0;
        mdValueMax = 0;
        msUnit = "";
        mbDeleted = false;
        mbSystem = false;
        mnFkUserInsertId = 0;
        mnFkUserUpdateId = 0;
        mtTsUserInsert = null;
        mtTsUserUpdate = null;
        
        maChildFamilies.clear();
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_var = " + mnPkVariableId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_var = " + pk[0] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkVariableId = 0;

        msSql = "SELECT COALESCE(MAX(id_var), 0) + 1 FROM " + getSqlTable();
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkVariableId = resultSet.getInt(1);
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
            mnPkVariableId = resultSet.getInt("id_var");
            msCode = resultSet.getString("code");
            msName = resultSet.getString("name");
            mnDecimals = resultSet.getInt("decs");
            mdValueMin = resultSet.getDouble("val_min");
            mdValueMax = resultSet.getDouble("val_max");
            msUnit = resultSet.getString("uom");
            mbDeleted = resultSet.getBoolean("b_del");
            mbSystem = resultSet.getBoolean("b_sys");
            mnFkUserInsertId = resultSet.getInt("fk_usr_ins");
            mnFkUserUpdateId = resultSet.getInt("fk_usr_upd");
            mtTsUserInsert = resultSet.getTimestamp("ts_usr_ins");
            mtTsUserUpdate = resultSet.getTimestamp("ts_usr_upd");
            
            // Read aswell child registries:
            
            statement = session.getStatement().getConnection().createStatement();
            
            msSql = "SELECT id_fam FROM " + DModConsts.TablesMap.get(DModConsts.MU_VAR_FAM) + " " + getSqlWhere() +
                    "ORDER BY id_fam ";
            resultSet = statement.executeQuery(msSql);
            while (resultSet.next()) {
                DDbVariableFamily child = new DDbVariableFamily();
                child.read(session, new int[] { mnPkVariableId, resultSet.getInt(1) });
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
                msCode = "" + mnPkVariableId;
            }

            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkVariableId + ", " + 
                    "'" + msCode + "', " + 
                    "'" + msName + "', " + 
                    mnDecimals + ", " + 
                    mdValueMin + ", " + 
                    mdValueMax + ", " + 
                    "'" + msUnit + "', " + 
                    (mbDeleted ? 1 : 0) + ", " + 
                    (mbSystem ? 1 : 0) + ", " + 
                    mnFkUserInsertId + ", " + 
                    mnFkUserUpdateId + ", " + 
                    "NOW()" + ", " + 
                    "NOW()" + " " + 
                    ")";
        }
        else {
            mnFkUserUpdateId = session.getUser().getPkUserId();

            msSql = "UPDATE " + getSqlTable() + " SET " +
                    "id_var = " + mnPkVariableId + ", " +
                    "code = '" + msCode + "', " +
                    "name = '" + msName + "', " +
                    "decs = " + mnDecimals + ", " +
                    "val_min = " + mdValueMin + ", " +
                    "val_max = " + mdValueMax + ", " +
                    "uom = '" + msUnit + "', " +
                    "b_del = " + (mbDeleted ? 1 : 0) + ", " +
                    "b_sys = " + (mbSystem ? 1 : 0) + ", " +
                    //"fk_usr_ins = " + mnFkUserInsertId + ", " +
                    "fk_usr_upd = " + mnFkUserUpdateId + ", " +
                    //"ts_usr_ins = " + "NOW()" + ", " +
                    "ts_usr_upd = " + "NOW()" + " " +
                    getSqlWhere();
        }

        session.getStatement().execute(msSql);
        
        // Save aswell child registries:

        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.MU_VAR_FAM) + " " + getSqlWhere();
        session.getStatement().execute(msSql);
        
        for (DDbVariableFamily child : maChildFamilies) {
            child.setRegistryNew(true);
            child.setPkVariableId(mnPkVariableId);
            child.save(session);
        }

        // Finish registry saving:
        
        mbRegistryNew = false;
        mnQueryResultId = DDbConsts.SAVE_OK;
    }

    @Override
    public DDbVariable clone() throws CloneNotSupportedException {
        DDbVariable registry = new DDbVariable();

        registry.setPkVariableId(this.getPkVariableId());
        registry.setCode(this.getCode());
        registry.setName(this.getName());
        registry.setDecimals(this.getDecimals());
        registry.setValueMin(this.getValueMin());
        registry.setValueMax(this.getValueMax());
        registry.setUnit(this.getUnit());
        registry.setDeleted(this.isDeleted());
        registry.setSystem(this.isSystem());
        registry.setFkUserInsertId(this.getFkUserInsertId());
        registry.setFkUserUpdateId(this.getFkUserUpdateId());
        registry.setTsUserInsert(this.getTsUserInsert());
        registry.setTsUserUpdate(this.getTsUserUpdate());

        for (DDbVariableFamily child : maChildFamilies) {
            registry.getChildFamilies().add(child.clone());
        }
        
        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
}
