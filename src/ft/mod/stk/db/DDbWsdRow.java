/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.stk.db;

import ft.mod.DModConsts;
import ft.mod.DModSysConsts;
import ft.mod.cfg.db.DDbItem;
import ft.mod.cfg.db.DDbUnit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import sba.gui.util.DUtilConsts;
import sba.lib.DLibConsts;
import sba.lib.DLibUtils;
import sba.lib.db.DDbConsts;
import sba.lib.db.DDbRegistryUser;
import sba.lib.grid.DGridRow;
import sba.lib.gui.DGuiSession;

/**
 *
 * @author Sergio Flores
 */
public class DDbWsdRow extends DDbRegistryUser implements DGridRow {

    protected int mnPkWsdId;
    protected int mnPkRowId;
    protected double mdUnits;
    protected double mdAmountUnit;
    protected double mdAmount_r;
    protected String msLot;
    protected double mdMassUnit;
    protected double mdMass_r;
    /*
    protected boolean mbDeleted;
    protected boolean mbSystem;
    */
    protected int mnFkItemTypeId;
    protected int mnFkItemId;
    protected int mnFkUnitId;
    protected int mnFkWsdWsdId_n;
    protected int mnFkWsdRowId_n;
    /*
    protected int mnFkUserInsertId;
    protected int mnFkUserUpdateId;
    protected Date mtTsUserInsert;
    protected Date mtTsUserUpdate;
    */
    
    protected DDbItem moRegItem;
    protected DDbUnit moRegUnit;
    
    public DDbWsdRow() {
        super(DModConsts.S_WSD_ROW);
        initRegistry();
    }
    
    public void setPkWsdId(int n) { mnPkWsdId = n; }
    public void setPkRowId(int n) { mnPkRowId = n; }
    public void setUnits(double d) { mdUnits = d; }
    public void setAmountUnit(double d) { mdAmountUnit = d; }
    public void setAmount_r(double d) { mdAmount_r = d; }
    public void setLot(String s) { msLot = s; }
    public void setMassUnit(double d) { mdMassUnit = d; }
    public void setMass_r(double d) { mdMass_r = d; }
    public void setDeleted(boolean b) { mbDeleted = b; }
    public void setSystem(boolean b) { mbSystem = b; }
    public void setFkItemTypeId(int n) { mnFkItemTypeId = n; }
    public void setFkItemId(int n) { mnFkItemId = n; }
    public void setFkUnitId(int n) { mnFkUnitId = n; }
    public void setFkWsdWsdId_n(int n) { mnFkWsdWsdId_n = n; }
    public void setFkWsdRowId_n(int n) { mnFkWsdRowId_n = n; }
    public void setFkUserInsertId(int n) { mnFkUserInsertId = n; }
    public void setFkUserUpdateId(int n) { mnFkUserUpdateId = n; }
    public void setTsUserInsert(Date t) { mtTsUserInsert = t; }
    public void setTsUserUpdate(Date t) { mtTsUserUpdate = t; }

    public int getPkWsdId() { return mnPkWsdId; }
    public int getPkRowId() { return mnPkRowId; }
    public double getUnits() { return mdUnits; }
    public double getAmountUnit() { return mdAmountUnit; }
    public double getAmount_r() { return mdAmount_r; }
    public String getLot() { return msLot; }
    public double getMassUnit() { return mdMassUnit; }
    public double getMass_r() { return mdMass_r; }
    public boolean isDeleted() { return mbDeleted; }
    public boolean isSystem() { return mbSystem; }
    public int getFkItemTypeId() { return mnFkItemTypeId; }
    public int getFkItemId() { return mnFkItemId; }
    public int getFkUnitId() { return mnFkUnitId; }
    public int getFkWsdWsdId_n() { return mnFkWsdWsdId_n; }
    public int getFkWsdRowId_n() { return mnFkWsdRowId_n; }
    public int getFkUserInsertId() { return mnFkUserInsertId; }
    public int getFkUserUpdateId() { return mnFkUserUpdateId; }
    public Date getTsUserInsert() { return mtTsUserInsert; }
    public Date getTsUserUpdate() { return mtTsUserUpdate; }

    public void setRegItem(DDbItem o) { moRegItem = o; }
    public void setRegUnit(DDbUnit o) { moRegUnit = o; }
    
    public DDbItem getRegItem() { return moRegItem; }
    public DDbUnit getRegUnit() { return moRegUnit; }
    
    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkWsdId = pk[0];
        mnPkRowId = pk[1];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkWsdId, mnPkRowId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkWsdId = 0;
        mnPkRowId = 0;
        mdUnits = 0;
        mdAmountUnit = 0;
        mdAmount_r = 0;
        msLot = "";
        mdMassUnit = 0;
        mdMass_r = 0;
        mbDeleted = false;
        mbSystem = false;
        mnFkItemTypeId = 0;
        mnFkItemId = 0;
        mnFkUnitId = 0;
        mnFkWsdWsdId_n = 0;
        mnFkWsdRowId_n = 0;
        mnFkUserInsertId = 0;
        mnFkUserUpdateId = 0;
        mtTsUserInsert = null;
        mtTsUserUpdate = null;
        
        moRegItem = null;
        moRegUnit = null;
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_wsd = " + mnPkWsdId + " AND "
                + "id_row = " + mnPkRowId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_wsd = " + pk[0] + " AND "
                + "id_row = " + pk[1] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkRowId = 0;

        msSql = "SELECT COALESCE(MAX(id_wsd), 0) + 1 FROM " + getSqlTable() + " "
                + "WHERE id_wsd = " + mnPkWsdId + " ";
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkRowId = resultSet.getInt(1);
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
            mnPkWsdId = resultSet.getInt("id_wsd");
            mnPkRowId = resultSet.getInt("id_row");
            mdUnits = resultSet.getDouble("unt");
            mdAmountUnit = resultSet.getDouble("amt_unt");
            mdAmount_r = resultSet.getDouble("amt_r");
            msLot = resultSet.getString("lot");
            mdMassUnit = resultSet.getDouble("mass_unt");
            mdMass_r = resultSet.getDouble("mass_r");
            mbDeleted = resultSet.getBoolean("b_del");
            mbSystem = resultSet.getBoolean("b_sys");
            mnFkItemTypeId = resultSet.getInt("fk_itm_tp");
            mnFkItemId = resultSet.getInt("fk_itm");
            mnFkUnitId = resultSet.getInt("fk_uom");
            mnFkWsdWsdId_n = resultSet.getInt("fk_wsd_wsd_n");
            mnFkWsdRowId_n = resultSet.getInt("fk_wsd_row_n");
            mnFkUserInsertId = resultSet.getInt("fk_usr_ins");
            mnFkUserUpdateId = resultSet.getInt("fk_usr_upd");
            mtTsUserInsert = resultSet.getTimestamp("ts_usr_ins");
            mtTsUserUpdate = resultSet.getTimestamp("ts_usr_upd");
            
            // Read aswell registry members:
            
            readRegData(session);
            
            // Finish registry reading:

            mbRegistryNew = false;
        }

        mnQueryResultId = DDbConsts.READ_OK;
    }

    @Override
    public void save(DGuiSession session) throws SQLException, Exception {
        initQueryMembers();
        mnQueryResultId = DDbConsts.SAVE_ERROR;
        
        computeWsdRow(session);
        
        if (mbRegistryNew) {
            computePrimaryKey(session);
            mbDeleted = false;
            mbSystem = false;
            mnFkUserInsertId = session.getUser().getPkUserId();
            mnFkUserUpdateId = DUtilConsts.USR_NA_ID;

            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkWsdId + ", " + 
                    mnPkRowId + ", " + 
                    mdUnits + ", " + 
                    mdAmountUnit + ", " + 
                    mdAmount_r + ", " + 
                    "'" + msLot + "', " + 
                    mdMassUnit + ", " + 
                    mdMass_r + ", " + 
                    (mbDeleted ? 1 : 0) + ", " + 
                    (mbSystem ? 1 : 0) + ", " + 
                    mnFkItemTypeId + ", " + 
                    mnFkItemId + ", " + 
                    mnFkUnitId + ", " + 
                    (mnFkWsdWsdId_n == DLibConsts.UNDEFINED ? "NULL" : "" + mnFkWsdWsdId_n) + ", " + 
                    (mnFkWsdRowId_n == DLibConsts.UNDEFINED ? "NULL" : "" + mnFkWsdRowId_n) + ", " + 
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
                    "id_wsd = " + mnPkWsdId + ", " +
                    "id_row = " + mnPkRowId + ", " +
                    */
                    "unt = " + mdUnits + ", " +
                    "amt_unt = " + mdAmountUnit + ", " +
                    "amt_r = " + mdAmount_r + ", " +
                    "lot = '" + msLot + "', " +
                    "mass_unt = " + mdMassUnit + ", " +
                    "mass_r = " + mdMass_r + ", " +
                    "b_del = " + (mbDeleted ? 1 : 0) + ", " +
                    "b_sys = " + (mbSystem ? 1 : 0) + ", " +
                    "fk_itm_tp = " + mnFkItemTypeId + ", " +
                    "fk_itm = " + mnFkItemId + ", " +
                    "fk_uom = " + mnFkUnitId + ", " +
                    "fk_wsd_wsd_n = " + (mnFkWsdWsdId_n == DLibConsts.UNDEFINED ? "NULL" : "" + mnFkWsdWsdId_n) + ", " +
                    "fk_wsd_row_n = " + (mnFkWsdRowId_n == DLibConsts.UNDEFINED ? "NULL" : "" + mnFkWsdRowId_n) + ", " +
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
    public DDbWsdRow clone() throws CloneNotSupportedException {
        DDbWsdRow registry = new DDbWsdRow();

        registry.setPkWsdId(this.getPkWsdId());
        registry.setPkRowId(this.getPkRowId());
        registry.setUnits(this.getUnits());
        registry.setAmountUnit(this.getAmountUnit());
        registry.setAmount_r(this.getAmount_r());
        registry.setLot(this.getLot());
        registry.setMassUnit(this.getMassUnit());
        registry.setMass_r(this.getMass_r());
        registry.setDeleted(this.isDeleted());
        registry.setSystem(this.isSystem());
        registry.setFkItemTypeId(this.getFkItemTypeId());
        registry.setFkItemId(this.getFkItemId());
        registry.setFkUnitId(this.getFkUnitId());
        registry.setFkWsdWsdId_n(this.getFkWsdWsdId_n());
        registry.setFkWsdRowId_n(this.getFkWsdRowId_n());
        registry.setFkUserInsertId(this.getFkUserInsertId());
        registry.setFkUserUpdateId(this.getFkUserUpdateId());
        registry.setTsUserInsert(this.getTsUserInsert());
        registry.setTsUserUpdate(this.getTsUserUpdate());

        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }

    @Override
    public int[] getRowPrimaryKey() {
        return getPrimaryKey();
    }

    @Override
    public String getRowCode() {
        return getCode();
    }

    @Override
    public String getRowName() {
        return getName();
    }

    @Override
    public boolean isRowSystem() {
        return isSystem();
    }

    @Override
    public boolean isRowDeletable() {
        return isDeletable();
    }

    @Override
    public boolean isRowEdited() {
        return isRegistryEdited();
    }

    @Override
    public void setRowEdited(boolean edited) {
        setRegistryEdited(edited);
    }

    @Override
    public Object getRowValueAt(int col) {
        Object value = null;
        
        switch (col) {
            case 0:
                value = moRegItem.getName();
                break;
            case 1:
                value = moRegItem.getCode();
                break;
            case 2:
                value = mdUnits;
                break;
            case 3:
                value = moRegUnit.getCode();
                break;
            case 4:
                value = msLot;
                break;
            case 5:
                value = mdAmountUnit;
                break;
            case 6:
                value = mdAmount_r;
                break;
            case 7:
                value = mdMassUnit;
                break;
            case 8:
                value = mdMass_r;
                break;
            default:
        }
        
        return value;
    }

    @Override
    public void setRowValueAt(Object value, int col) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void readRegData(final DGuiSession session) {
        moRegItem = (DDbItem) session.readRegistry(DModConsts.CU_ITM, new int[] { mnFkItemId });
        moRegUnit = (DDbUnit) session.readRegistry(DModConsts.CU_UOM, new int[] { mnFkUnitId });
    }
    
    public void computeWsdRow(final DGuiSession session) {
        readRegData(session);
        
        mdAmount_r = DLibUtils.round(mdAmountUnit * mdUnits, DLibUtils.getDecimalFormatAmount().getMaximumFractionDigits());
        mdMass_r = DLibUtils.round(mdMassUnit = moRegItem.getMassUnit() * mdUnits, DLibUtils.getDecimalFormatAmountUnitary().getMaximumFractionDigits());
        mnFkItemTypeId = moRegItem.getXtaFkItemTypeId();
        mnFkUnitId = moRegItem.getFkUnitId();
    }
    
    public DDbStock createStock(final DDbWsd wsd) {
        DDbStock stock = new DDbStock();
        
        stock.setPkItemId(mnFkItemId);
        stock.setPkUnitId(mnFkUnitId);
        stock.setPkWarehouseId(wsd.getFkWarehouseId());
        //stock.setPkMoveId(...);
        stock.setDate(wsd.getDate());
        
        stock.setAmountUnit(mdAmountUnit);
        
        switch (wsd.getFkMoveClassId()) {
            case DModSysConsts.SS_MOV_CL_IN:
                stock.setUnitsIn(mdUnits);
                stock.setUnitsOut(0);
                stock.setDebit_r(mdAmount_r);
                stock.setCredit_r(0);
                break;
            case DModSysConsts.SS_MOV_CL_OUT:
                stock.setUnitsIn(0);
                stock.setUnitsOut(mdUnits);
                stock.setDebit_r(0);
                stock.setCredit_r(mdAmount_r);
                break;
            default:
        }
        
        stock.setLot(msLot);
        stock.setDeleted(mbDeleted);
        stock.setSystem(mbSystem);
        stock.setFkMoveClassId(wsd.getFkMoveClassId());
        stock.setFkMoveTypeId(wsd.getFkMoveTypeId());
        stock.setFkWsdWsdId(mnPkWsdId);
        stock.setFkWsdRowId(mnPkRowId);
        /*
        stock.setFkUserInsertId(...);
        stock.setFkUserUpdateId(...);
        stock.setTsUserInsert(...);
        stock.setTsUserUpdate(...);
        */
        
        return stock;
    }
}
