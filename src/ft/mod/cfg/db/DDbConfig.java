/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.cfg.db;

import ft.mod.DModConsts;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import sba.gui.util.DUtilConsts;
import sba.lib.DLibConsts;
import sba.lib.db.DDbConsts;
import sba.lib.db.DDbRegistryUser;
import sba.lib.gui.DGuiConfigCompany;
import sba.lib.gui.DGuiSession;

/**
 *
 * @author Sergio Flores
 */
public class DDbConfig extends DDbRegistryUser implements DGuiConfigCompany {

    public static final int FIELD_VERSION = 1;

    protected int mnPkConfigId;
    protected int mnVersion;
    protected Date mtVersionTs;
    protected String msNameSeparator;
    protected String msLotFormat;
    protected boolean mbVariable1;
    protected String msVariable1;
    protected boolean mbModuleCfg;
    protected boolean mbModuleMfg;
    protected boolean mbModuleQas;
    protected boolean mbModuleOpe;
    protected boolean mbModuleFin;
    /*
    protected boolean mbDeleted;
    protected boolean mbSystem;
    protected int mnFkUserInsertId;
    protected int mnFkUserUpdateId;
    protected Date mtTsUserInsert;
    protected Date mtTsUserUpdate;
    */

    public DDbConfig() {
        super(DModConsts.C_CFG);
        initRegistry();
    }

    public void setPkConfigId(int n) { mnPkConfigId = n; }
    public void setVersion(int n) { mnVersion = n; }
    public void setVersionTs(Date t) { mtVersionTs = t; }
    public void setNameSeparator(String s) { msNameSeparator = s; }
    public void setLotFormat(String s) { msLotFormat = s; }
    public void setVariable1(boolean b) { mbVariable1 = b; }
    public void setVariable1(String s) { msVariable1 = s; }
    public void setModuleCfg(boolean b) { mbModuleCfg = b; }
    public void setModuleMfg(boolean b) { mbModuleMfg = b; }
    public void setModuleQas(boolean b) { mbModuleQas = b; }
    public void setModuleOpe(boolean b) { mbModuleOpe = b; }
    public void setModuleFin(boolean b) { mbModuleFin = b; }
    public void setDeleted(boolean b) { mbDeleted = b; }
    public void setSystem(boolean b) { mbSystem = b; }
    public void setFkUserInsertId(int n) { mnFkUserInsertId = n; }
    public void setFkUserUpdateId(int n) { mnFkUserUpdateId = n; }
    public void setTsUserInsert(Date t) { mtTsUserInsert = t; }
    public void setTsUserUpdate(Date t) { mtTsUserUpdate = t; }

    public int getPkConfigId() { return mnPkConfigId; }
    public int getVersion() { return mnVersion; }
    public Date getVersionTs() { return mtVersionTs; }
    public String getNameSeparator() { return msNameSeparator; }
    public String getLotFormat() { return msLotFormat; }
    public boolean isVariable1() { return mbVariable1; }
    public String getVariable1() { return msVariable1; }
    public boolean isModuleCfg() { return mbModuleCfg; }
    public boolean isModuleMfg() { return mbModuleMfg; }
    public boolean isModuleQas() { return mbModuleQas; }
    public boolean isModuleOpe() { return mbModuleOpe; }
    public boolean isModuleFin() { return mbModuleFin; }
    public boolean isDeleted() { return mbDeleted; }
    public boolean isSystem() { return mbSystem; }
    public int getFkUserInsertId() { return mnFkUserInsertId; }
    public int getFkUserUpdateId() { return mnFkUserUpdateId; }
    public Date getTsUserInsert() { return mtTsUserInsert; }
    public Date getTsUserUpdate() { return mtTsUserUpdate; }
    
    public String getNameSeparatorFormatted() { return msNameSeparator.replaceAll("_", " "); }

    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkConfigId = pk[0];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkConfigId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkConfigId = 0;
        mnVersion = 0;
        mtVersionTs = null;
        msNameSeparator = "";
        msLotFormat = "";
        mbVariable1 = false;
        msVariable1 = "";
        mbModuleCfg = false;
        mbModuleMfg = false;
        mbModuleQas = false;
        mbModuleOpe = false;
        mbModuleFin = false;
        mbDeleted = false;
        mbSystem = false;
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
        return "WHERE id_cfg = " + mnPkConfigId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_cfg = " + pk[0] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkConfigId = 0;

        msSql = "SELECT COALESCE(MAX(id_cfg), 0) + 1 FROM " + getSqlTable();
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkConfigId = resultSet.getInt(1);
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
            mnPkConfigId = resultSet.getInt("id_cfg");
            mnVersion = resultSet.getInt("ver");
            mtVersionTs = resultSet.getTimestamp("ver_ts");
            msNameSeparator = resultSet.getString("name_sep");
            msLotFormat = resultSet.getString("lot_fmt");
            mbVariable1 = resultSet.getBoolean("b_var_1");
            msVariable1 = resultSet.getString("var_1");
            mbModuleCfg = resultSet.getBoolean("b_mod_cfg");
            mbModuleMfg = resultSet.getBoolean("b_mod_mfg");
            mbModuleQas = resultSet.getBoolean("b_mod_qas");
            mbModuleOpe = resultSet.getBoolean("b_mod_ope");
            mbModuleFin = resultSet.getBoolean("b_mod_fin");
            mbDeleted = resultSet.getBoolean("b_del");
            mbSystem = resultSet.getBoolean("b_sys");
            mnFkUserInsertId = resultSet.getInt("fk_usr_ins");
            mnFkUserUpdateId = resultSet.getInt("fk_usr_upd");
            mtTsUserInsert = resultSet.getTimestamp("ts_usr_ins");
            mtTsUserUpdate = resultSet.getTimestamp("ts_usr_upd");

            mbRegistryNew = false;
        }

        mnQueryResultId = DDbConsts.READ_OK;
    }

    @Override
    public void save(DGuiSession session) throws SQLException, Exception {
        initQueryMembers();
        mnQueryResultId = DDbConsts.SAVE_ERROR;

        if (mbRegistryNew) {
            mbDeleted = false;
            mbSystem = false;
            mnFkUserInsertId = session.getUser().getPkUserId();
            mnFkUserUpdateId = DUtilConsts.USR_NA_ID;

            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkConfigId + ", " + 
                    mnVersion + ", " + 
                    "NOW()" + ", " + 
                    "'" + msNameSeparator + "', " + 
                    "'" + msLotFormat + "', " + 
                    (mbVariable1 ? 1 : 0) + ", " + 
                    "'" + msVariable1 + "', " + 
                    (mbModuleCfg ? 1 : 0) + ", " + 
                    (mbModuleMfg ? 1 : 0) + ", " + 
                    (mbModuleQas ? 1 : 0) + ", " + 
                    (mbModuleOpe ? 1 : 0) + ", " + 
                    (mbModuleFin ? 1 : 0) + ", " + 
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
                    //"id_cfg = " + mnPkConfigId + ", " +
                    "ver = " + mnVersion + ", " +
                    "ver_ts = " + "NOW()" + ", " +
                    "name_sep = '" + msNameSeparator + "', " +
                    "lot_fmt = '" + msLotFormat + "', " +
                    "b_var_1 = " + (mbVariable1 ? 1 : 0) + ", " +
                    "var_1 = '" + msVariable1 + "', " +
                    "b_mod_cfg = " + (mbModuleCfg ? 1 : 0) + ", " +
                    "b_mod_mfg = " + (mbModuleMfg ? 1 : 0) + ", " +
                    "b_mod_qas = " + (mbModuleQas ? 1 : 0) + ", " +
                    "b_mod_ope = " + (mbModuleOpe ? 1 : 0) + ", " +
                    "b_mod_fin = " + (mbModuleFin ? 1 : 0) + ", " +
                    "b_del = " + (mbDeleted ? 1 : 0) + ", " +
                    "b_sys = " + (mbSystem ? 1 : 0) + ", " +
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
    public DDbConfig clone() throws CloneNotSupportedException {
        DDbConfig registry = new DDbConfig();

        registry.setPkConfigId(this.getPkConfigId());
        registry.setVersion(this.getVersion());
        registry.setVersionTs(this.getVersionTs());
        registry.setNameSeparator(this.getNameSeparator());
        registry.setLotFormat(this.getLotFormat());
        registry.setVariable1(this.isVariable1());
        registry.setVariable1(this.getVariable1());
        registry.setModuleCfg(this.isModuleCfg());
        registry.setModuleMfg(this.isModuleMfg());
        registry.setModuleQas(this.isModuleQas());
        registry.setModuleOpe(this.isModuleOpe());
        registry.setModuleFin(this.isModuleFin());
        registry.setDeleted(this.isDeleted());
        registry.setSystem(this.isSystem());
        registry.setFkUserInsertId(this.getFkUserInsertId());
        registry.setFkUserUpdateId(this.getFkUserUpdateId());
        registry.setTsUserInsert(this.getTsUserInsert());
        registry.setTsUserUpdate(this.getTsUserUpdate());

        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }

    @Override
    public void saveField(final Statement statement, final int[] pk, final int field, final Object value) throws SQLException, Exception {
        initQueryMembers();
        mnQueryResultId = DDbConsts.SAVE_ERROR;

        msSql = "UPDATE " + getSqlTable() + " SET ";

        switch (field) {
            case FIELD_VERSION:
                msSql += "ver = " + value + ", ver_ts = NOW() ";
                break;
            default:
                throw new Exception(DLibConsts.ERR_MSG_OPTION_UNKNOWN);
        }

        msSql += getSqlWhere(pk);
        statement.execute(msSql);
        mnQueryResultId = DDbConsts.SAVE_OK;
    }

    @Override
    public int getCompanyId() {
        return getPkConfigId();
    }
}
