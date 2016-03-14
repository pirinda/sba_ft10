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
    protected int mnFkItemId;
    protected int mnFkItemTypeId;
    protected int mnFkUnitId;
    protected int mnFkWsdWsdId_n;
    protected int mnFkWsdRowId_n;
    
    protected DDbItem moRegItem;
    protected DDbUnit moRegUnit;
    
    public DDbWsdRow() {
        super(DModConsts.S_WSD_ROW);
        initRegistry();
    }
    
    private void readRegMembers(final DGuiSession session, final boolean update) {
        moRegItem = (DDbItem) session.readRegistry(DModConsts.CU_ITM, new int[] { mnFkItemId });
        
        if (update) {
            mnFkItemTypeId = moRegItem.getXtaFkItemTypeId();
            mnFkUnitId = moRegItem.getFkUnitId();
            mdMassUnit = moRegItem.getMassUnit();
        }
        
        moRegUnit = (DDbUnit) session.readRegistry(DModConsts.CU_UOM, new int[] { mnFkUnitId });
    }
    
    public void setPkWsdId(int n) { mnPkWsdId = n; }
    public void setPkRowId(int n) { mnPkRowId = n; }
    public void setUnits(double d) { mdUnits = d; }
    public void setAmountUnit(double d) { mdAmountUnit = d; }
    public void setAmount_r(double d) { mdAmount_r = d; }
    public void setLot(String s) { msLot = s; }
    public void setMassUnit(double d) { mdMassUnit = d; }
    public void setMass_r(double d) { mdMass_r = d; }
    public void setFkItemId(int n) { mnFkItemId = n; }
    public void setFkItemTypeId(int n) { mnFkItemTypeId = n; }
    public void setFkUnitId(int n) { mnFkUnitId = n; }
    public void setFkWsdWsdId_n(int n) { mnFkWsdWsdId_n = n; }
    public void setFkWsdRowId_n(int n) { mnFkWsdRowId_n = n; }

    public int getPkWsdId() { return mnPkWsdId; }
    public int getPkRowId() { return mnPkRowId; }
    public double getUnits() { return mdUnits; }
    public double getAmountUnit() { return mdAmountUnit; }
    public double getAmount_r() { return mdAmount_r; }
    public String getLot() { return msLot; }
    public double getMassUnit() { return mdMassUnit; }
    public double getMass_r() { return mdMass_r; }
    public int getFkItemId() { return mnFkItemId; }
    public int getFkItemTypeId() { return mnFkItemTypeId; }
    public int getFkUnitId() { return mnFkUnitId; }
    public int getFkWsdWsdId_n() { return mnFkWsdWsdId_n; }
    public int getFkWsdRowId_n() { return mnFkWsdRowId_n; }

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
        mnFkItemId = 0;
        mnFkItemTypeId = 0;
        mnFkUnitId = 0;
        mnFkWsdWsdId_n = 0;
        mnFkWsdRowId_n = 0;
        
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

        msSql = "SELECT COALESCE(MAX(id_row), 0) + 1 FROM " + getSqlTable() + " "
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
            mnFkItemId = resultSet.getInt("fk_itm");
            mnFkItemTypeId = resultSet.getInt("fk_itm_tp");
            mnFkUnitId = resultSet.getInt("fk_uom");
            mnFkWsdWsdId_n = resultSet.getInt("fk_wsd_wsd_n");
            mnFkWsdRowId_n = resultSet.getInt("fk_wsd_row_n");
            
            readRegMembers(session, false);
            
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

            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkWsdId + ", " + 
                    mnPkRowId + ", " + 
                    mdUnits + ", " + 
                    mdAmountUnit + ", " + 
                    mdAmount_r + ", " + 
                    "'" + msLot + "', " + 
                    mdMassUnit + ", " + 
                    mdMass_r + ", " + 
                    mnFkItemId + ", " + 
                    mnFkItemTypeId + ", " + 
                    mnFkUnitId + ", " + 
                    (mnFkWsdWsdId_n == DLibConsts.UNDEFINED ? "NULL" : "" + mnFkWsdWsdId_n) + ", " + 
                    (mnFkWsdRowId_n == DLibConsts.UNDEFINED ? "NULL" : "" + mnFkWsdRowId_n) + " " + 
                    ")";
        }
        else {
            throw new Exception(DDbConsts.ERR_MSG_REG_NON_UPDATABLE);
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
        registry.setFkItemId(this.getFkItemId());
        registry.setFkItemTypeId(this.getFkItemTypeId());
        registry.setFkUnitId(this.getFkUnitId());
        registry.setFkWsdWsdId_n(this.getFkWsdWsdId_n());
        registry.setFkWsdRowId_n(this.getFkWsdRowId_n());
        
        registry.setRegItem(this.getRegItem());
        registry.setRegUnit(this.getRegUnit());

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
    
    public void compute(final DGuiSession session) {
        readRegMembers(session, true);
        
        mdAmount_r = DLibUtils.round(mdAmountUnit * mdUnits, DLibUtils.getDecimalFormatAmount().getMaximumFractionDigits());
        mdMass_r = DLibUtils.round(mdMassUnit * mdUnits, DLibUtils.getDecimalFormatAmountUnitary().getMaximumFractionDigits());
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
