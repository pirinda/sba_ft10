/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.mfg.db;

import ft.lib.DLibRegistry;
import ft.mod.DModConsts;
import ft.mod.cfg.db.DCfgConsts;
import ft.mod.cfg.db.DDbItem;
import ft.mod.cfg.db.DDbUnit;
import java.sql.ResultSet;
import java.sql.SQLException;
import sba.lib.DLibUtils;
import sba.lib.db.DDbConsts;
import sba.lib.db.DDbRegistry;
import sba.lib.db.DDbRegistryUser;
import sba.lib.grid.DGridRow;
import sba.lib.gui.DGuiSession;

/**
 *
 * @author Sergio Flores
 */
public class DDbFormulaByProd extends DDbRegistryUser implements DGridRow, DLibRegistry {

    protected int mnPkFormulaId;
    protected int mnPkByProdId;
    protected double mdQuantity;
    protected double mdMassUnit;
    protected double mdMass_r;
    protected double mdBrix;
    protected double mdMassSolid_r;
    protected boolean mbStandard;
    protected int mnFkItemId;
    protected int mnFkItemTypeId;
    protected int mnFkUnitId;
    
    protected DDbItem moRegItem;
    protected DDbUnit moRegUnit;

    protected String msXtaItemTypeCode;
    protected String msXtaItemTypeName;
    
    public DDbFormulaByProd() {
        super(DModConsts.MU_FRM_BYP);
        initRegistry();
    }

    private void readRegMembers(final DGuiSession session, final boolean update) {
        moRegItem = (DDbItem) session.readRegistry(DModConsts.CU_ITM, new int[] { mnFkItemId });
        
        if (update) {
            mnFkItemTypeId = moRegItem.getRegFamily().getFkItemTypeId();
            mnFkUnitId = moRegItem.getFkUnitId();
        }
        
        moRegUnit = (DDbUnit) session.readRegistry(DModConsts.CU_UOM, new int[] { mnFkUnitId });
        
        if (update) {
            mdMassUnit = moRegItem.getMassUnit();
        }
    }
    
    private void readXtaMembers(final DGuiSession session) {
        msXtaItemTypeCode = (String) session.readField(DModConsts.CS_ITM_TP, new int[] { mnFkItemTypeId}, DDbRegistry.FIELD_CODE);
        msXtaItemTypeName = (String) session.readField(DModConsts.CS_ITM_TP, new int[] { mnFkItemTypeId}, DDbRegistry.FIELD_NAME);
    }
    
    public void setPkFormulaId(int n) { mnPkFormulaId = n; }
    public void setPkByProdId(int n) { mnPkByProdId = n; }
    public void setQuantity(double d) { mdQuantity = d; }
    public void setMassUnit(double d) { mdMassUnit = d; }
    public void setMass_r(double d) { mdMass_r = d; }
    public void setBrix(double d) { mdBrix = d; }
    public void setMassSolid_r(double d) { mdMassSolid_r = d; }
    public void setStandard(boolean b) { mbStandard = b; }
    public void setFkItemId(int n) { mnFkItemId = n; }
    public void setFkItemTypeId(int n) { mnFkItemTypeId = n; }
    public void setFkUnitId(int n) { mnFkUnitId = n; }

    public int getPkFormulaId() { return mnPkFormulaId; }
    public int getPkByProdId() { return mnPkByProdId; }
    public double getQuantity() { return mdQuantity; }
    public double getMassUnit() { return mdMassUnit; }
    public double getMass_r() { return mdMass_r; }
    public double getBrix() { return mdBrix; }
    public double getMassSolid_r() { return mdMassSolid_r; }
    public boolean isStandard() { return mbStandard; }
    public int getFkItemId() { return mnFkItemId; }
    public int getFkItemTypeId() { return mnFkItemTypeId; }
    public int getFkUnitId() { return mnFkUnitId; }

    public void setRegItem(DDbItem o) { moRegItem = o; }
    public void setRegUnit(DDbUnit o) { moRegUnit = o; }
    
    public DDbItem getRegItem() { return moRegItem; }
    public DDbUnit getRegUnit() { return moRegUnit; }
    
    public void setXtaItemTypeCode(String s) { msXtaItemTypeCode = s; }
    public void setXtaItemTypeName(String s) { msXtaItemTypeName = s; }
    
    public String getXtaItemTypeCode() { return msXtaItemTypeCode; }
    public String getXtaItemTypeName() { return msXtaItemTypeName; }
    
    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkFormulaId = pk[0];
        mnPkByProdId = pk[1];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkFormulaId, mnPkByProdId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkFormulaId = 0;
        mnPkByProdId = 0;
        mdQuantity = 0;
        mdMassUnit = 0;
        mdMass_r = 0;
        mdBrix = 0;
        mdMassSolid_r = 0;
        mbStandard = false;
        mnFkItemId = 0;
        mnFkItemTypeId = 0;
        mnFkUnitId = 0;
        
        moRegItem = null;
        moRegUnit = null;
        
        msXtaItemTypeCode = "";
        msXtaItemTypeName = "";
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_frm = " + mnPkFormulaId + " AND id_byp = " + mnPkByProdId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_frm = " + pk[0] + " AND id_byp = " + pk[1] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkByProdId = 0;

        msSql = "SELECT COALESCE(MAX(id_byp), 0) + 1 FROM " + getSqlTable() + " " +
                "WHERE id_frm = " + mnPkFormulaId + " ";
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkByProdId = resultSet.getInt(1);
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
            mnPkFormulaId = resultSet.getInt("id_frm");
            mnPkByProdId = resultSet.getInt("id_byp");
            mdQuantity = resultSet.getDouble("qty");
            mdMassUnit = resultSet.getDouble("mass_unt");
            mdMass_r = resultSet.getDouble("mass_r");
            mdBrix = resultSet.getDouble("brix");
            mdMassSolid_r = resultSet.getDouble("mass_sld_r");
            mbStandard = resultSet.getBoolean("b_std");
            mnFkItemId = resultSet.getInt("fk_itm");
            mnFkItemTypeId = resultSet.getInt("fk_itm_tp");
            mnFkUnitId = resultSet.getInt("fk_uom");

            readRegMembers(session, false);
            readXtaMembers(session);

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

            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkFormulaId + ", " + 
                   mnPkByProdId + ", " + 
                   mdQuantity + ", " + 
                   mdMassUnit + ", " + 
                   mdMass_r + ", " + 
                    mdBrix + ", " + 
                    mdMassSolid_r + ", " + 
                   (mbStandard ? 1 : 0) + ", " + 
                   mnFkItemTypeId + ", " + 
                   mnFkItemId + ", " + 
                   mnFkItemTypeId + ", " + 
                   mnFkUnitId + " " + 
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
    public DDbFormulaByProd clone() throws CloneNotSupportedException {
        DDbFormulaByProd registry = new DDbFormulaByProd();

        registry.setPkFormulaId(this.getPkFormulaId());
        registry.setPkByProdId(this.getPkByProdId());
        registry.setQuantity(this.getQuantity());
        registry.setMassUnit(this.getMassUnit());
        registry.setMass_r(this.getMass_r());
        registry.setBrix(this.getBrix());
        registry.setMassSolid_r(this.getMassSolid_r());
        registry.setStandard(this.isStandard());
        registry.setFkItemId(this.getFkItemId());
        registry.setFkItemTypeId(this.getFkItemTypeId());
        registry.setFkUnitId(this.getFkUnitId());
        
        registry.setRegItem(this.getRegItem() == null ? null : this.getRegItem().clone());
        registry.setRegUnit(this.getRegUnit() == null ? null : this.getRegUnit().clone());
        
        registry.setXtaItemTypeCode(this.getXtaItemTypeCode());
        registry.setXtaItemTypeName(this.getXtaItemTypeName());

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
                value = mnPkByProdId;
                break;
            case 1:
                value = moRegItem.getRegFamily().getXtaItemTypeCode();
                break;
            case 2:
                value = moRegItem.getName();
                break;
            case 3:
                value = mdQuantity;
                break;
            case 4:
                value = moRegUnit.getCode();
                break;
            case 5:
                value = mbStandard;
                break;
            default:
        }
        
        return value;
    }

    @Override
    public void setRowValueAt(Object value, int col) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void compute(final DGuiSession session) {
        readRegMembers(session, true);
        readXtaMembers(session);
        
        mdMass_r = DLibUtils.round(mdMassUnit * mdQuantity, DLibUtils.getDecimalFormatQuantity().getMaximumFractionDigits());
        mdMassSolid_r = DLibUtils.round(mdMass_r * (mdBrix / DCfgConsts.BRIX_MAX), DLibUtils.getDecimalFormatQuantity().getMaximumFractionDigits());
    }
}
