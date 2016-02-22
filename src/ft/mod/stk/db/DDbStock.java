/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.stk.db;

import ft.mod.DModConsts;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import sba.gui.util.DUtilConsts;
import sba.lib.DLibUtils;
import sba.lib.db.DDbConsts;
import sba.lib.db.DDbRegistryUser;
import sba.lib.gui.DGuiSession;

/**
 *
 * @author Sergio Flores
 */
public class DDbStock extends DDbRegistryUser {

    protected int mnPkItemId;
    protected int mnPkUnitId;
    protected int mnPkWarehouseId;
    protected int mnPkMoveId;
    protected Date mtDate;
    protected double mdUnitsIn;
    protected double mdUnitsOut;
    protected double mdAmountUnit;
    protected double mdDebit_r;
    protected double mdCredit_r;
    protected String msLot;
    /*
    protected boolean mbDeleted;
    protected boolean mbSystem;
    */
    protected int mnFkMoveClassId;
    protected int mnFkMoveTypeId;
    protected int mnFkWsdWsdId;
    protected int mnFkWsdRowId;
    /*
    protected int mnFkUserInsertId;
    protected int mnFkUserUpdateId;
    protected Date mtTsUserInsert;
    protected Date mtTsUserUpdate;
    */
    
    public DDbStock() {
        super(DModConsts.S_STK);
        initRegistry();
    }

    public void setPkItemId(int n) { mnPkItemId = n; }
    public void setPkUnitId(int n) { mnPkUnitId = n; }
    public void setPkWarehouseId(int n) { mnPkWarehouseId = n; }
    public void setPkMoveId(int n) { mnPkMoveId = n; }
    public void setDate(Date t) { mtDate = t; }
    public void setUnitsIn(double d) { mdUnitsIn = d; }
    public void setUnitsOut(double d) { mdUnitsOut = d; }
    public void setAmountUnit(double d) { mdAmountUnit = d; }
    public void setDebit_r(double d) { mdDebit_r = d; }
    public void setCredit_r(double d) { mdCredit_r = d; }
    public void setLot(String s) { msLot = s; }
    public void setDeleted(boolean b) { mbDeleted = b; }
    public void setSystem(boolean b) { mbSystem = b; }
    public void setFkMoveClassId(int n) { mnFkMoveClassId = n; }
    public void setFkMoveTypeId(int n) { mnFkMoveTypeId = n; }
    public void setFkWsdWsdId(int n) { mnFkWsdWsdId = n; }
    public void setFkWsdRowId(int n) { mnFkWsdRowId = n; }
    public void setFkUserInsertId(int n) { mnFkUserInsertId = n; }
    public void setFkUserUpdateId(int n) { mnFkUserUpdateId = n; }
    public void setTsUserInsert(Date t) { mtTsUserInsert = t; }
    public void setTsUserUpdate(Date t) { mtTsUserUpdate = t; }

    public int getPkItemId() { return mnPkItemId; }
    public int getPkUnitId() { return mnPkUnitId; }
    public int getPkWarehouseId() { return mnPkWarehouseId; }
    public int getPkMoveId() { return mnPkMoveId; }
    public Date getDate() { return mtDate; }
    public double getUnitsIn() { return mdUnitsIn; }
    public double getUnitsOut() { return mdUnitsOut; }
    public double getAmountUnit() { return mdAmountUnit; }
    public double getDebit_r() { return mdDebit_r; }
    public double getCredit_r() { return mdCredit_r; }
    public String getLot() { return msLot; }
    public boolean isDeleted() { return mbDeleted; }
    public boolean isSystem() { return mbSystem; }
    public int getFkMoveClassId() { return mnFkMoveClassId; }
    public int getFkMoveTypeId() { return mnFkMoveTypeId; }
    public int getFkWsdWsdId() { return mnFkWsdWsdId; }
    public int getFkWsdRowId() { return mnFkWsdRowId; }
    public int getFkUserInsertId() { return mnFkUserInsertId; }
    public int getFkUserUpdateId() { return mnFkUserUpdateId; }
    public Date getTsUserInsert() { return mtTsUserInsert; }
    public Date getTsUserUpdate() { return mtTsUserUpdate; }

    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkItemId = pk[0];
        mnPkUnitId = pk[1];
        mnPkWarehouseId = pk[2];
        mnPkMoveId = pk[3];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkItemId, mnPkUnitId, mnPkWarehouseId, mnPkMoveId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkItemId = 0;
        mnPkUnitId = 0;
        mnPkWarehouseId = 0;
        mnPkMoveId = 0;
        mtDate = null;
        mdUnitsIn = 0;
        mdUnitsOut = 0;
        mdAmountUnit = 0;
        mdDebit_r = 0;
        mdCredit_r = 0;
        msLot = "";
        mbDeleted = false;
        mbSystem = false;
        mnFkMoveClassId = 0;
        mnFkMoveTypeId = 0;
        mnFkWsdWsdId = 0;
        mnFkWsdRowId = 0;
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
        return "WHERE id_itm = " + mnPkItemId + " AND "
                + "id_uom = " + mnPkUnitId + " AND "
                + "id_whs = " + mnPkWarehouseId + " AND "
                + "id_mov = " + mnPkMoveId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_itm = " + pk[0] + " AND "
                + "id_uom = " + pk[1] + " AND "
                + "id_whs = " + pk[2] + " AND "
                + "id_mov = " + pk[3] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkMoveId = 0;

        msSql = "SELECT COALESCE(MAX(id_mov), 0) + 1 FROM " + getSqlTable() + " "
                + "WHERE id_itm = " + mnPkItemId + " AND "
                + "id_uom = " + mnPkUnitId + " AND "
                + "id_whs = " + mnPkWarehouseId + " ";
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkMoveId = resultSet.getInt(1);
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
            mnPkUnitId = resultSet.getInt("id_uom");
            mnPkWarehouseId = resultSet.getInt("id_whs");
            mnPkMoveId = resultSet.getInt("id_mov");
            mtDate = resultSet.getDate("dat");
            mdUnitsIn = resultSet.getDouble("unt_in");
            mdUnitsOut = resultSet.getDouble("unt_out");
            mdAmountUnit = resultSet.getDouble("amt_unt");
            mdDebit_r = resultSet.getDouble("dbt_r");
            mdCredit_r = resultSet.getDouble("cdt_r");
            msLot = resultSet.getString("lot");
            mbDeleted = resultSet.getBoolean("b_del");
            mbSystem = resultSet.getBoolean("b_sys");
            mnFkMoveClassId = resultSet.getInt("fk_mov_cl");
            mnFkMoveTypeId = resultSet.getInt("fk_mov_tp");
            mnFkWsdWsdId = resultSet.getInt("fk_wsd_wsd");
            mnFkWsdRowId = resultSet.getInt("fk_wsd_row");
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

            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkItemId + ", " + 
                    mnPkUnitId + ", " + 
                    mnPkWarehouseId + ", " + 
                    mnPkMoveId + ", " + 
                    "'" + DLibUtils.DbmsDateFormatDate.format(mtDate) + "', " + 
                    mdUnitsIn + ", " + 
                    mdUnitsOut + ", " + 
                    mdAmountUnit + ", " + 
                    mdDebit_r + ", " + 
                    mdCredit_r + ", " + 
                    "'" + msLot + "', " + 
                    (mbDeleted ? 1 : 0) + ", " + 
                    (mbSystem ? 1 : 0) + ", " + 
                    mnFkMoveClassId + ", " + 
                    mnFkMoveTypeId + ", " + 
                    mnFkWsdWsdId + ", " + 
                    mnFkWsdRowId + ", " + 
                    mnFkUserInsertId + ", " + 
                    mnFkUserUpdateId + ", " + 
                    "NOW()" + ", " + 
                    "NOW()" + " " + 
                    ")";
        }
        else {
            mnFkUserUpdateId = session.getUser().getPkUserId();

            msSql = "UPDATE " + getSqlTable() + " SET " +
                    /*
                    "id_itm = " + mnPkItemId + ", " +
                    "id_uom = " + mnPkUnitId + ", " +
                    "id_whs = " + mnPkWarehouseId + ", " +
                    "id_mov = " + mnPkMoveId + ", " +
                    */
                    "dat = '" + DLibUtils.DbmsDateFormatDate.format(mtDate) + "', " +
                    "unt_in = " + mdUnitsIn + ", " +
                    "unt_out = " + mdUnitsOut + ", " +
                    "amt_unt = " + mdAmountUnit + ", " +
                    "dbt_r = " + mdDebit_r + ", " +
                    "cdt_r = " + mdCredit_r + ", " +
                    "lot = '" + msLot + "', " +
                    "b_del = " + (mbDeleted ? 1 : 0) + ", " +
                    "b_sys = " + (mbSystem ? 1 : 0) + ", " +
                    "fk_mov_cl = " + mnFkMoveClassId + ", " +
                    "fk_mov_tp = " + mnFkMoveTypeId + ", " +
                    "fk_wsd_wsd = " + mnFkWsdWsdId + ", " +
                    "fk_wsd_row = " + mnFkWsdRowId + ", " +
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
    public DDbStock clone() throws CloneNotSupportedException {
        DDbStock registry = new DDbStock();

        registry.setPkItemId(this.getPkItemId());
        registry.setPkUnitId(this.getPkUnitId());
        registry.setPkWarehouseId(this.getPkWarehouseId());
        registry.setPkMoveId(this.getPkMoveId());
        registry.setDate(this.getDate());
        registry.setUnitsIn(this.getUnitsIn());
        registry.setUnitsOut(this.getUnitsOut());
        registry.setAmountUnit(this.getAmountUnit());
        registry.setDebit_r(this.getDebit_r());
        registry.setCredit_r(this.getCredit_r());
        registry.setLot(this.getLot());
        registry.setDeleted(this.isDeleted());
        registry.setSystem(this.isSystem());
        registry.setFkMoveClassId(this.getFkMoveClassId());
        registry.setFkMoveTypeId(this.getFkMoveTypeId());
        registry.setFkWsdWsdId(this.getFkWsdWsdId());
        registry.setFkWsdRowId(this.getFkWsdRowId());
        registry.setFkUserInsertId(this.getFkUserInsertId());
        registry.setFkUserUpdateId(this.getFkUserUpdateId());
        registry.setTsUserInsert(this.getTsUserInsert());
        registry.setTsUserUpdate(this.getTsUserUpdate());

        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
}
