/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.cfg.db;

import ft.lib.DLibRegistry;
import ft.mod.DModConsts;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import sba.gui.util.DUtilConsts;
import sba.lib.DLibConsts;
import sba.lib.db.DDbConsts;
import sba.lib.db.DDbRegistryUser;
import sba.lib.gui.DGuiSession;

/**
 *
 * @author Sergio Flores
 */
public class DDbItem extends DDbRegistryUser implements DLibRegistry {

    protected int mnPkItemId;
    protected String msCode;
    protected String msName;
    protected String msLotCode;
    protected double mdMassUnit;
    protected boolean mbBrix;
    protected double mdBrix;
    /*
    protected boolean mbDeleted;
    protected boolean mbSystem;
    */
    protected int mnFkFamilyId;
    protected int mnFkUnitId;
    protected int mnFkPresentId;
    protected int mnFkItemBaseId_n;
    /*
    protected int mnFkUserInsertId;
    protected int mnFkUserUpdateId;
    protected Date mtTsUserInsert;
    protected Date mtTsUserUpdate;
    */
    
    protected DDbFamily moRegFamily;
    protected DDbUnit moRegUnit;
    protected DDbPresent moRegPresent;
    
    public DDbItem() {
        super(DModConsts.CU_ITM);
        initRegistry();
    }

    private void readRegMembers(final DGuiSession session) {
        moRegFamily = (DDbFamily) session.readRegistry(DModConsts.CU_FAM, new int[] { mnFkFamilyId }, DDbConsts.MODE_STEALTH);
        moRegUnit = (DDbUnit) session.readRegistry(DModConsts.CU_UOM, new int[] { mnFkUnitId }, DDbConsts.MODE_STEALTH);
        moRegPresent = (DDbPresent) session.readRegistry(DModConsts.CU_PRE, new int[] { mnFkPresentId }, DDbConsts.MODE_STEALTH);
    }
    
    public void setPkItemId(int n) { mnPkItemId = n; }
    public void setCode(String s) { msCode = s; }
    public void setName(String s) { msName = s; }
    public void setLotCode(String s) { msLotCode = s; }
    public void setMassUnit(double d) { mdMassUnit = d; }
    public void setBrix(boolean b) { mbBrix = b; }
    public void setBrix(double d) { mdBrix = d; }
    public void setDeleted(boolean b) { mbDeleted = b; }
    public void setSystem(boolean b) { mbSystem = b; }
    public void setFkFamilyId(int n) { mnFkFamilyId = n; }
    public void setFkUnitId(int n) { mnFkUnitId = n; }
    public void setFkPresentId(int n) { mnFkPresentId = n; }
    public void setFkItemBaseId_n(int n) { mnFkItemBaseId_n = n; }
    public void setFkUserInsertId(int n) { mnFkUserInsertId = n; }
    public void setFkUserUpdateId(int n) { mnFkUserUpdateId = n; }
    public void setTsUserInsert(Date t) { mtTsUserInsert = t; }
    public void setTsUserUpdate(Date t) { mtTsUserUpdate = t; }

    public int getPkItemId() { return mnPkItemId; }
    public String getCode() { return msCode; }
    public String getName() { return msName; }
    public String getLotCode() { return msLotCode; }
    public double getMassUnit() { return mdMassUnit; }
    public boolean isBrix() { return mbBrix; }
    public double getBrix() { return mdBrix; }
    public boolean isDeleted() { return mbDeleted; }
    public boolean isSystem() { return mbSystem; }
    public int getFkFamilyId() { return mnFkFamilyId; }
    public int getFkUnitId() { return mnFkUnitId; }
    public int getFkPresentId() { return mnFkPresentId; }
    public int getFkItemBaseId_n() { return mnFkItemBaseId_n; }
    public int getFkUserInsertId() { return mnFkUserInsertId; }
    public int getFkUserUpdateId() { return mnFkUserUpdateId; }
    public Date getTsUserInsert() { return mtTsUserInsert; }
    public Date getTsUserUpdate() { return mtTsUserUpdate; }

    public void setRegFamily(DDbFamily o) { moRegFamily = o; }
    public void setRegUnit(DDbUnit o) { moRegUnit = o; }
    public void setRegPresent(DDbPresent o) { moRegPresent = o; }
    
    public DDbFamily getRegFamily() { return moRegFamily; }
    public DDbUnit getRegUnit() { return moRegUnit; }
    public DDbPresent getRegPresent() { return moRegPresent; }
    
    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkItemId = pk[0];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkItemId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkItemId = 0;
        msCode = "";
        msName = "";
        msLotCode = "";
        mdMassUnit = 0;
        mbBrix = false;
        mdBrix = 0;
        mbDeleted = false;
        mbSystem = false;
        mnFkFamilyId = 0;
        mnFkUnitId = 0;
        mnFkPresentId = 0;
        mnFkItemBaseId_n = 0;
        mnFkUserInsertId = 0;
        mnFkUserUpdateId = 0;
        mtTsUserInsert = null;
        mtTsUserUpdate = null;
        
        moRegFamily = null;
        moRegUnit = null;
        moRegPresent = null;
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_itm = " + mnPkItemId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_itm = " + pk[0] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkItemId = 0;

        msSql = "SELECT COALESCE(MAX(id_itm), 0) + 1 FROM " + getSqlTable();
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkItemId = resultSet.getInt(1);
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
            mnPkItemId = resultSet.getInt("id_itm");
            msCode = resultSet.getString("code");
            msName = resultSet.getString("name");
            msLotCode = resultSet.getString("lot_code");
            mdMassUnit = resultSet.getDouble("mass_unt");
            mbBrix = resultSet.getBoolean("b_brix");
            mdBrix = resultSet.getDouble("brix");
            mbDeleted = resultSet.getBoolean("b_del");
            mbSystem = resultSet.getBoolean("b_sys");
            mnFkFamilyId = resultSet.getInt("fk_fam");
            mnFkUnitId = resultSet.getInt("fk_uom");
            mnFkPresentId = resultSet.getInt("fk_pre");
            mnFkItemBaseId_n = resultSet.getInt("fk_itm_bas_n");
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

        compute(session);
        
        if (mbRegistryNew) {
            computePrimaryKey(session);
            mbDeleted = false;
            mbSystem = false;
            mnFkUserInsertId = session.getUser().getPkUserId();
            mnFkUserUpdateId = DUtilConsts.USR_NA_ID;

            if (msCode.isEmpty()) {
                msCode = "" + mnPkItemId;
            }

            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkItemId + ", " + 
                    "'" + msCode + "', " + 
                    "'" + msName + "', " + 
                    "'" + msLotCode + "', " + 
                    mdMassUnit + ", " + 
                    (mbBrix ? 1 : 0) + ", " + 
                    mdBrix + ", " + 
                    (mbDeleted ? 1 : 0) + ", " + 
                    (mbSystem ? 1 : 0) + ", " + 
                    mnFkFamilyId + ", " + 
                    mnFkUnitId + ", " + 
                    mnFkPresentId + ", " + 
                    (mnFkItemBaseId_n == DLibConsts.UNDEFINED ? "NULL" : "" + mnFkItemBaseId_n) + ", " + 
                    mnFkUserInsertId + ", " + 
                    mnFkUserUpdateId + ", " + 
                    "NOW()" + ", " + 
                    "NOW()" + " " + 
                    ")";
        }
        else {
            mnFkUserUpdateId = session.getUser().getPkUserId();

            msSql = "UPDATE " + getSqlTable() + " SET " +
                    //"id_itm = " + mnPkItemId + ", " +
                    "code = '" + msCode + "', " +
                    "name = '" + msName + "', " +
                    "lot_code = '" + msLotCode + "', " +
                    "mass_unt = " + mdMassUnit + ", " +
                    "b_brix = " + (mbBrix ? 1 : 0) + ", " +
                    "brix = " + mdBrix + ", " +
                    "b_del = " + (mbDeleted ? 1 : 0) + ", " +
                    "b_sys = " + (mbSystem ? 1 : 0) + ", " +
                    "fk_fam = " + mnFkFamilyId + ", " +
                    "fk_uom = " + mnFkUnitId + ", " +
                    "fk_pre = " + mnFkPresentId + ", " +
                    "fk_itm_bas_n = " + (mnFkItemBaseId_n == DLibConsts.UNDEFINED ? "NULL" : "" + mnFkItemBaseId_n) + ", " +
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
    public DDbItem clone() throws CloneNotSupportedException {
        DDbItem registry = new DDbItem();

        registry.setPkItemId(this.getPkItemId());
        registry.setCode(this.getCode());
        registry.setName(this.getName());
        registry.setLotCode(this.getLotCode());
        registry.setMassUnit(this.getMassUnit());
        registry.setBrix(this.isBrix());
        registry.setBrix(this.getBrix());
        registry.setDeleted(this.isDeleted());
        registry.setSystem(this.isSystem());
        registry.setFkFamilyId(this.getFkFamilyId());
        registry.setFkUnitId(this.getFkUnitId());
        registry.setFkPresentId(this.getFkPresentId());
        registry.setFkItemBaseId_n(this.getFkItemBaseId_n());
        registry.setFkUserInsertId(this.getFkUserInsertId());
        registry.setFkUserUpdateId(this.getFkUserUpdateId());
        registry.setTsUserInsert(this.getTsUserInsert());
        registry.setTsUserUpdate(this.getTsUserUpdate());

        registry.setRegFamily(this.getRegFamily() == null ? null : this.getRegFamily().clone());
        registry.setRegUnit(this.getRegUnit() == null ? null : this.getRegUnit().clone());
        registry.setRegPresent(this.getRegPresent() == null ? null : this.getRegPresent().clone());
        
        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }

    @Override
    public void compute(DGuiSession session) {
        readRegMembers(session);
        
        if (!moRegFamily.isBrix()) {
            mbBrix = false;
        }
        
        if (!mbBrix) {
            mdBrix = DCfgConsts.BRIX_MAX;
        }
    }
}
