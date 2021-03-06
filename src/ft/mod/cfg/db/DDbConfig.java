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
import sba.lib.db.DDbRegistry;
import sba.lib.db.DDbRegistryUser;
import sba.lib.gui.DGuiConfigCompany;
import sba.lib.gui.DGuiSession;

/**
 *
 * @author Sergio Flores
 */
public class DDbConfig extends DDbRegistryUser implements DGuiConfigCompany {

    public static final int FIELD_VERSION = DDbRegistry.FIELD_BASE + 1;

    protected int mnPkConfigId;
    protected int mnVersion;
    protected Date mtVersionTs;
    protected String msLotFormatProductBase;
    protected String msLotFormatProductFinished;
    protected boolean mbLotRawMaterialIngredient;
    protected boolean mbLotRawMaterialPackage;
    protected boolean mbModuleCfg;
    protected boolean mbModuleFrm;
    protected boolean mbModuleMfg;
    protected boolean mbModuleQty;
    protected boolean mbModuleStk;
    protected boolean mbModulePur;
    protected boolean mbModuleSal;
    /*
    protected boolean mbDeleted;
    protected boolean mbSystem;
    */
    protected int mnFkMassUnitId;
    protected int mnFkWarehouseMaterialsId;
    protected int mnFkWarehouseProductsId;
    /*
    protected int mnFkUserInsertId;
    protected int mnFkUserUpdateId;
    protected Date mtTsUserInsert;
    protected Date mtTsUserUpdate;
    */
    
    protected DDbUnit moRegMassUnit;
    
    public DDbConfig() {
        super(DModConsts.C_CFG);
        initRegistry();
    }
    
    private void readRegMembers(final DGuiSession session) {
        moRegMassUnit = (DDbUnit) session.readRegistry(DModConsts.CU_UOM, new int[] { mnFkMassUnitId });
    }

    public void setPkConfigId(int n) { mnPkConfigId = n; }
    public void setVersion(int n) { mnVersion = n; }
    public void setVersionTs(Date t) { mtVersionTs = t; }
    public void setLotFormatProductBase(String s) { msLotFormatProductBase = s; }
    public void setLotFormatProductFinished(String s) { msLotFormatProductFinished = s; }
    public void setLotRawMaterialIngredient(boolean b) { mbLotRawMaterialIngredient = b; }
    public void setLotRawMaterialPackage(boolean b) { mbLotRawMaterialPackage = b; }
    public void setModuleCfg(boolean b) { mbModuleCfg = b; }
    public void setModuleFrm(boolean b) { mbModuleFrm = b; }
    public void setModuleMfg(boolean b) { mbModuleMfg = b; }
    public void setModuleQty(boolean b) { mbModuleQty = b; }
    public void setModuleStk(boolean b) { mbModuleStk = b; }
    public void setModulePur(boolean b) { mbModulePur = b; }
    public void setModuleSal(boolean b) { mbModuleSal = b; }
    public void setDeleted(boolean b) { mbDeleted = b; }
    public void setSystem(boolean b) { mbSystem = b; }
    public void setFkMassUnitId(int n) { mnFkMassUnitId = n; }
    public void setFkWarehouseMaterialsId(int n) { mnFkWarehouseMaterialsId = n; }
    public void setFkWarehouseProductsId(int n) { mnFkWarehouseProductsId = n; }
    public void setFkUserInsertId(int n) { mnFkUserInsertId = n; }
    public void setFkUserUpdateId(int n) { mnFkUserUpdateId = n; }
    public void setTsUserInsert(Date t) { mtTsUserInsert = t; }
    public void setTsUserUpdate(Date t) { mtTsUserUpdate = t; }

    public int getPkConfigId() { return mnPkConfigId; }
    public int getVersion() { return mnVersion; }
    public Date getVersionTs() { return mtVersionTs; }
    public String getLotFormatProductBase() { return msLotFormatProductBase; }
    public String getLotFormatProductFinished() { return msLotFormatProductFinished; }
    public boolean isLotRawMaterialIngredient() { return mbLotRawMaterialIngredient; }
    public boolean isLotRawMaterialPackage() { return mbLotRawMaterialPackage; }
    public boolean isModuleCfg() { return mbModuleCfg; }
    public boolean isModuleFrm() { return mbModuleFrm; }
    public boolean isModuleMfg() { return mbModuleMfg; }
    public boolean isModuleQty() { return mbModuleQty; }
    public boolean isModuleStk() { return mbModuleStk; }
    public boolean isModulePur() { return mbModulePur; }
    public boolean isModuleSal() { return mbModuleSal; }
    public boolean isDeleted() { return mbDeleted; }
    public boolean isSystem() { return mbSystem; }
    public int getFkMassUnitId() { return mnFkMassUnitId; }
    public int getFkWarehouseMaterialsId() { return mnFkWarehouseMaterialsId; }
    public int getFkWarehouseProductsId() { return mnFkWarehouseProductsId; }
    public int getFkUserInsertId() { return mnFkUserInsertId; }
    public int getFkUserUpdateId() { return mnFkUserUpdateId; }
    public Date getTsUserInsert() { return mtTsUserInsert; }
    public Date getTsUserUpdate() { return mtTsUserUpdate; }

    public void setRegMassUnit(DDbUnit o) { moRegMassUnit = o; }
    
    public DDbUnit getRegMassUnit() { return moRegMassUnit; }
    
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
        msLotFormatProductBase = "";
        msLotFormatProductFinished = "";
        mbLotRawMaterialIngredient = false;
        mbLotRawMaterialPackage = false;
        mbModuleCfg = false;
        mbModuleFrm = false;
        mbModuleMfg = false;
        mbModuleQty = false;
        mbModuleStk = false;
        mbModulePur = false;
        mbModuleSal = false;
        mbDeleted = false;
        mbSystem = false;
        mnFkMassUnitId = 0;
        mnFkWarehouseMaterialsId = 0;
        mnFkWarehouseProductsId = 0;
        mnFkUserInsertId = 0;
        mnFkUserUpdateId = 0;
        mtTsUserInsert = null;
        mtTsUserUpdate = null;
        
        moRegMassUnit = null;
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
            msLotFormatProductBase = resultSet.getString("lot_fmt_pb");
            msLotFormatProductFinished = resultSet.getString("lot_fmt_pf");
            mbLotRawMaterialIngredient = resultSet.getBoolean("b_lot_rmi");
            mbLotRawMaterialPackage = resultSet.getBoolean("b_lot_rmp");
            mbModuleCfg = resultSet.getBoolean("b_mod_cfg");
            mbModuleFrm = resultSet.getBoolean("b_mod_frm");
            mbModuleMfg = resultSet.getBoolean("b_mod_mfg");
            mbModuleQty = resultSet.getBoolean("b_mod_qty");
            mbModuleStk = resultSet.getBoolean("b_mod_stk");
            mbModulePur = resultSet.getBoolean("b_mod_pur");
            mbModuleSal = resultSet.getBoolean("b_mod_sal");
            mbDeleted = resultSet.getBoolean("b_del");
            mbSystem = resultSet.getBoolean("b_sys");
            mnFkMassUnitId = resultSet.getInt("fk_mass_uom");
            mnFkWarehouseMaterialsId = resultSet.getInt("fk_whs_mat");
            mnFkWarehouseProductsId = resultSet.getInt("fk_whs_pro");
            mnFkUserInsertId = resultSet.getInt("fk_usr_ins");
            mnFkUserUpdateId = resultSet.getInt("fk_usr_upd");
            mtTsUserInsert = resultSet.getTimestamp("ts_usr_ins");
            mtTsUserUpdate = resultSet.getTimestamp("ts_usr_upd");
            
            readRegMembers(session);
            
            mbRegistryNew = false;
        }

        mnQueryResultId = DDbConsts.READ_OK;
    }

    @Override
    public void save(DGuiSession session) throws SQLException, Exception {
        initQueryMembers();
        mnQueryResultId = DDbConsts.SAVE_ERROR;

        readRegMembers(session);
        
        if (mbRegistryNew) {
            mbDeleted = false;
            mbSystem = false;
            mnFkUserInsertId = session.getUser().getPkUserId();
            mnFkUserUpdateId = DUtilConsts.USR_NA_ID;

            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkConfigId + ", " + 
                    mnVersion + ", " + 
                    "NOW()" + ", " + 
                    "'" + msLotFormatProductBase + "', " + 
                    "'" + msLotFormatProductFinished + "', " + 
                    (mbLotRawMaterialIngredient ? 1 : 0) + ", " + 
                    (mbLotRawMaterialPackage ? 1 : 0) + ", " + 
                    (mbModuleCfg ? 1 : 0) + ", " + 
                    (mbModuleFrm ? 1 : 0) + ", " + 
                    (mbModuleMfg ? 1 : 0) + ", " + 
                    (mbModuleQty ? 1 : 0) + ", " + 
                    (mbModuleStk ? 1 : 0) + ", " + 
                    (mbModulePur ? 1 : 0) + ", " + 
                    (mbModuleSal ? 1 : 0) + ", " + 
                    (mbDeleted ? 1 : 0) + ", " + 
                    (mbSystem ? 1 : 0) + ", " + 
                    mnFkMassUnitId + ", " + 
                    mnFkWarehouseMaterialsId + ", " + 
                    mnFkWarehouseProductsId + ", " + 
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
                    "lot_fmt_pb = '" + msLotFormatProductBase + "', " +
                    "lot_fmt_pf = '" + msLotFormatProductFinished + "', " +
                    "b_lot_rmi = " + (mbLotRawMaterialIngredient ? 1 : 0) + ", " +
                    "b_lot_rmp = " + (mbLotRawMaterialPackage ? 1 : 0) + ", " +
                    "b_mod_cfg = " + (mbModuleCfg ? 1 : 0) + ", " +
                    "b_mod_frm = " + (mbModuleFrm ? 1 : 0) + ", " +
                    "b_mod_mfg = " + (mbModuleMfg ? 1 : 0) + ", " +
                    "b_mod_qty = " + (mbModuleQty ? 1 : 0) + ", " +
                    "b_mod_stk = " + (mbModuleStk ? 1 : 0) + ", " +
                    "b_mod_pur = " + (mbModulePur ? 1 : 0) + ", " +
                    "b_mod_sal = " + (mbModuleSal ? 1 : 0) + ", " +
                    "b_del = " + (mbDeleted ? 1 : 0) + ", " +
                    "b_sys = " + (mbSystem ? 1 : 0) + ", " +
                    "fk_mass_uom = " + mnFkMassUnitId + ", " +
                    "fk_whs_mat = " + mnFkWarehouseMaterialsId + ", " +
                    "fk_whs_pro = " + mnFkWarehouseProductsId + ", " +
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
        registry.setLotFormatProductBase(this.getLotFormatProductBase());
        registry.setLotFormatProductFinished(this.getLotFormatProductFinished());
        registry.setLotRawMaterialIngredient(this.isLotRawMaterialIngredient());
        registry.setLotRawMaterialPackage(this.isLotRawMaterialPackage());
        registry.setModuleCfg(this.isModuleCfg());
        registry.setModuleFrm(this.isModuleFrm());
        registry.setModuleMfg(this.isModuleMfg());
        registry.setModuleQty(this.isModuleQty());
        registry.setModuleStk(this.isModuleStk());
        registry.setModulePur(this.isModulePur());
        registry.setModuleSal(this.isModuleSal());
        registry.setDeleted(this.isDeleted());
        registry.setSystem(this.isSystem());
        registry.setFkMassUnitId(this.getFkMassUnitId());
        registry.setFkWarehouseMaterialsId(this.getFkWarehouseMaterialsId());
        registry.setFkWarehouseProductsId(this.getFkWarehouseProductsId());
        registry.setFkUserInsertId(this.getFkUserInsertId());
        registry.setFkUserUpdateId(this.getFkUserUpdateId());
        registry.setTsUserInsert(this.getTsUserInsert());
        registry.setTsUserUpdate(this.getTsUserUpdate());
        
        registry.setRegMassUnit(this.getRegMassUnit() == null ? null : this.getRegMassUnit().clone());

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
