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
public class DDbItemFamily extends DDbRegistryUser {

    protected int mnPkItemFamilyId;
    protected String msCode;
    protected String msName;
    protected String msLotCode;
    /*
    protected boolean mbDeleted;
    protected boolean mbSystem;
    */
    protected int mnFkItemTypeId;
    protected int mnFkItemFamilyId_n;
    /*
    protected int mnFkUserInsertId;
    protected int mnFkUserUpdateId;
    protected Date mtTsUserInsert;
    protected Date mtTsUserUpdate;
    */

    public DDbItemFamily() {
        super(DModConsts.CU_FAM);
        initRegistry();
    }

    public void setPkItemFamilyId(int n) { mnPkItemFamilyId = n; }
    public void setCode(String s) { msCode = s; }
    public void setName(String s) { msName = s; }
    public void setLotCode(String s) { msLotCode = s; }
    public void setDeleted(boolean b) { mbDeleted = b; }
    public void setSystem(boolean b) { mbSystem = b; }
    public void setFkItemTypeId(int n) { mnFkItemTypeId = n; }
    public void setFkItemFamilyId_n(int n) { mnFkItemFamilyId_n = n; }
    public void setFkUserInsertId(int n) { mnFkUserInsertId = n; }
    public void setFkUserUpdateId(int n) { mnFkUserUpdateId = n; }
    public void setTsUserInsert(Date t) { mtTsUserInsert = t; }
    public void setTsUserUpdate(Date t) { mtTsUserUpdate = t; }

    public int getPkItemFamilyId() { return mnPkItemFamilyId; }
    public String getCode() { return msCode; }
    public String getName() { return msName; }
    public String getLotCode() { return msLotCode; }
    public boolean isDeleted() { return mbDeleted; }
    public boolean isSystem() { return mbSystem; }
    public int getFkItemTypeId() { return mnFkItemTypeId; }
    public int getFkItemFamilyId_n() { return mnFkItemFamilyId_n; }
    public int getFkUserInsertId() { return mnFkUserInsertId; }
    public int getFkUserUpdateId() { return mnFkUserUpdateId; }
    public Date getTsUserInsert() { return mtTsUserInsert; }
    public Date getTsUserUpdate() { return mtTsUserUpdate; }

    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkItemFamilyId = pk[0];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkItemFamilyId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkItemFamilyId = 0;
        msCode = "";
        msName = "";
        msLotCode = "";
        mbDeleted = false;
        mbSystem = false;
        mnFkItemTypeId = 0;
        mnFkItemFamilyId_n = 0;
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
        return "WHERE id_fam = " + mnPkItemFamilyId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_fam = " + pk[0] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkItemFamilyId = 0;

        msSql = "SELECT COALESCE(MAX(id_fam), 0) + 1 FROM " + getSqlTable();
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkItemFamilyId = resultSet.getInt(1);
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
            mnPkItemFamilyId = resultSet.getInt("id_fam");
            msCode = resultSet.getString("code");
            msName = resultSet.getString("name");
            msLotCode = resultSet.getString("lot_code");
            mbDeleted = resultSet.getBoolean("b_del");
            mbSystem = resultSet.getBoolean("b_sys");
            mnFkItemTypeId = resultSet.getInt("fk_itm_tp");
            mnFkItemFamilyId_n = resultSet.getInt("fk_fam_n");
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
                msCode = "" + mnPkItemFamilyId;
            }

            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkItemFamilyId + ", " +
                    "'" + msCode + "', " +
                    "'" + msName + "', " +
                    "'" + msLotCode + "', " + 
                    (mbDeleted ? 1 : 0) + ", " +
                    (mbSystem ? 1 : 0) + ", " +
                    mnFkItemTypeId + ", " + 
                    (mnFkItemFamilyId_n == DLibConsts.UNDEFINED ? "NULL" : "" + mnFkItemFamilyId_n) + ", " + 
                    mnFkUserInsertId + ", " +
                    mnFkUserUpdateId + ", " +
                    "NOW()" + ", " +
                    "NOW()" + " " +
                    ")";
        }
        else {
            mnFkUserUpdateId = session.getUser().getPkUserId();

            msSql = "UPDATE " + getSqlTable() + " SET " +
                    //"id_fam = " + mnPkItemFamilyId + ", " +
                    "code = '" + (msCode.length() > 0 ? msCode : "" + mnPkItemFamilyId) + "', " +
                    "name = '" + msName + "', " +
                    "lot_code = '" + msLotCode + "', " +
                    "b_del = " + (mbDeleted ? 1 : 0) + ", " +
                    "b_sys = " + (mbSystem ? 1 : 0) + ", " +
                    "fk_itm_tp = " + mnFkItemTypeId + ", " +
                    "fk_fam_n = " + (mnFkItemFamilyId_n == DLibConsts.UNDEFINED ? "NULL" : "" + mnFkItemFamilyId_n) + ", " +
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
    public DDbItemFamily clone() throws CloneNotSupportedException {
        DDbItemFamily registry = new DDbItemFamily();

        registry.setPkItemFamilyId(this.getPkItemFamilyId());
        registry.setCode(this.getCode());
        registry.setName(this.getName());
        registry.setLotCode(this.getLotCode());
        registry.setDeleted(this.isDeleted());
        registry.setSystem(this.isSystem());
        registry.setFkItemTypeId(this.getFkItemTypeId());
        registry.setFkItemFamilyId_n(this.getFkItemFamilyId_n());
        registry.setFkUserInsertId(this.getFkUserInsertId());
        registry.setFkUserUpdateId(this.getFkUserUpdateId());
        registry.setTsUserInsert(this.getTsUserInsert());
        registry.setTsUserUpdate(this.getTsUserUpdate());

        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
}
