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
import sba.lib.DLibConsts;
import sba.lib.db.DDbConsts;
import sba.lib.db.DDbRegistryUser;
import sba.lib.gui.DGuiSession;

/**
 *
 * @author Sergio Flores
 */
public class DDbFamily extends DDbRegistryUser {

    protected int mnPkFamilyId;
    protected String msCode;
    protected String msName;
    protected String msLotCode;
    /*
    protected boolean mbDeleted;
    protected boolean mbSystem;
    */
    protected int mnFkItemTypeId;
    protected int mnFkUnitId;
    protected int mnFkFamilyBaseId_n;
    /*
    protected int mnFkUserInsertId;
    protected int mnFkUserUpdateId;
    protected Date mtTsUserInsert;
    protected Date mtTsUserUpdate;
    */

    public DDbFamily() {
        super(DModConsts.CU_FAM);
        initRegistry();
    }

    public void setPkFamilyId(int n) { mnPkFamilyId = n; }
    public void setCode(String s) { msCode = s; }
    public void setName(String s) { msName = s; }
    public void setLotCode(String s) { msLotCode = s; }
    public void setDeleted(boolean b) { mbDeleted = b; }
    public void setSystem(boolean b) { mbSystem = b; }
    public void setFkItemTypeId(int n) { mnFkItemTypeId = n; }
    public void setFkUnitId(int n) { mnFkUnitId = n; }
    public void setFkFamilyBaseId_n(int n) { mnFkFamilyBaseId_n = n; }
    public void setFkUserInsertId(int n) { mnFkUserInsertId = n; }
    public void setFkUserUpdateId(int n) { mnFkUserUpdateId = n; }
    public void setTsUserInsert(Date t) { mtTsUserInsert = t; }
    public void setTsUserUpdate(Date t) { mtTsUserUpdate = t; }

    public int getPkFamilyId() { return mnPkFamilyId; }
    public String getCode() { return msCode; }
    public String getName() { return msName; }
    public String getLotCode() { return msLotCode; }
    public boolean isDeleted() { return mbDeleted; }
    public boolean isSystem() { return mbSystem; }
    public int getFkItemTypeId() { return mnFkItemTypeId; }
    public int getFkUnitId() { return mnFkUnitId; }
    public int getFkFamilyBaseId_n() { return mnFkFamilyBaseId_n; }
    public int getFkUserInsertId() { return mnFkUserInsertId; }
    public int getFkUserUpdateId() { return mnFkUserUpdateId; }
    public Date getTsUserInsert() { return mtTsUserInsert; }
    public Date getTsUserUpdate() { return mtTsUserUpdate; }

    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkFamilyId = pk[0];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkFamilyId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkFamilyId = 0;
        msCode = "";
        msName = "";
        msLotCode = "";
        mbDeleted = false;
        mbSystem = false;
        mnFkItemTypeId = 0;
        mnFkUnitId = 0;
        mnFkFamilyBaseId_n = 0;
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
        return "WHERE id_fam = " + mnPkFamilyId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_fam = " + pk[0] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkFamilyId = 0;

        msSql = "SELECT COALESCE(MAX(id_fam), 0) + 1 FROM " + getSqlTable();
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkFamilyId = resultSet.getInt(1);
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
            mnPkFamilyId = resultSet.getInt("id_fam");
            msCode = resultSet.getString("code");
            msName = resultSet.getString("name");
            msLotCode = resultSet.getString("lot_code");
            mbDeleted = resultSet.getBoolean("b_del");
            mbSystem = resultSet.getBoolean("b_sys");
            mnFkItemTypeId = resultSet.getInt("fk_itm_tp");
            mnFkUnitId = resultSet.getInt("fk_uom");
            mnFkFamilyBaseId_n = resultSet.getInt("fk_fam_bas_n");
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
            computePrimaryKey(session);
            mbDeleted = false;
            mbSystem = false;
            mnFkUserInsertId = session.getUser().getPkUserId();
            mnFkUserUpdateId = DUtilConsts.USR_NA_ID;

            if (msCode.isEmpty()) {
                msCode = "" + mnPkFamilyId;
            }

            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkFamilyId + ", " + 
                    "'" + msCode + "', " + 
                    "'" + msName + "', " + 
                    "'" + msLotCode + "', " + 
                    (mbDeleted ? 1 : 0) + ", " + 
                    (mbSystem ? 1 : 0) + ", " + 
                    mnFkItemTypeId + ", " + 
                    mnFkUnitId + ", " + 
                    (mnFkFamilyBaseId_n == DLibConsts.UNDEFINED ? "NULL" : "" + mnFkFamilyBaseId_n) + ", " + 
                    mnFkUserInsertId + ", " + 
                    mnFkUserUpdateId + ", " + 
                    "NOW()" + ", " + 
                    "NOW()" + " " + 
                    ")";
        }
        else {
            mnFkUserUpdateId = session.getUser().getPkUserId();

            msSql = "UPDATE " + getSqlTable() + " SET " +
                    //"id_fam = " + mnPkFamilyId + ", " +
                    "code = '" + msCode + "', " +
                    "name = '" + msName + "', " +
                    "lot_code = '" + msLotCode + "', " +
                    "b_del = " + (mbDeleted ? 1 : 0) + ", " +
                    "b_sys = " + (mbSystem ? 1 : 0) + ", " +
                    "fk_itm_tp = " + mnFkItemTypeId + ", " +
                    "fk_uom = " + mnFkUnitId + ", " +
                    "fk_fam_bas_n = " + (mnFkFamilyBaseId_n == DLibConsts.UNDEFINED ? "NULL" : "" + mnFkFamilyBaseId_n) + ", " +
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
    public DDbFamily clone() throws CloneNotSupportedException {
        DDbFamily registry = new DDbFamily();

        registry.setPkFamilyId(this.getPkFamilyId());
        registry.setCode(this.getCode());
        registry.setName(this.getName());
        registry.setLotCode(this.getLotCode());
        registry.setDeleted(this.isDeleted());
        registry.setSystem(this.isSystem());
        registry.setFkItemTypeId(this.getFkItemTypeId());
        registry.setFkUnitId(this.getFkUnitId());
        registry.setFkFamilyBaseId_n(this.getFkFamilyBaseId_n());
        registry.setFkUserInsertId(this.getFkUserInsertId());
        registry.setFkUserUpdateId(this.getFkUserUpdateId());
        registry.setTsUserInsert(this.getTsUserInsert());
        registry.setTsUserUpdate(this.getTsUserUpdate());

        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
}