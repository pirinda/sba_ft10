/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.mfg.db;

import ft.mod.DModConsts;
import ft.mod.cfg.db.DDbItem;
import ft.mod.cfg.db.DDbPresent;
import ft.mod.cfg.db.DDbUnit;
import java.sql.ResultSet;
import java.sql.SQLException;
import sba.lib.DLibConsts;
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
public class DDbJobMfgProd extends DDbRegistryUser implements DGridRow {

    protected int mnPkJobId;
    protected int mnPkMfgProdId;
    protected double mdQuantity;
    protected double mdMassUnit;
    protected double mdMass_r;
    protected int mnFkItemId;
    protected int mnFkItemTypeId;
    protected int mnFkUnitId;
    protected int mnFkPresentId;

    protected DDbItem moRegItem;
    protected DDbUnit moRegUnit;
    protected DDbPresent moRegPresent;

    protected String msXtaItemTypeCode;
    protected String msXtaItemTypeName;
    
    public DDbJobMfgProd() {
        super(DModConsts.M_JOB_MFG);
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
        moRegPresent = mnFkPresentId == DLibConsts.UNDEFINED ? null : (DDbPresent) session.readRegistry(DModConsts.CU_PRE, new int[] { mnFkPresentId });
    }
    
    private void readXtaMembers(final DGuiSession session) {
        msXtaItemTypeCode = (String) session.readField(DModConsts.CS_ITM_TP, new int[] { mnFkItemTypeId }, DDbRegistry.FIELD_CODE);
        msXtaItemTypeName = (String) session.readField(DModConsts.CS_ITM_TP, new int[] { mnFkItemTypeId }, DDbRegistry.FIELD_NAME);
    }

    public void setPkJobId(int n) { mnPkJobId = n; }
    public void setPkMfgProdId(int n) { mnPkMfgProdId = n; }
    public void setQuantity(double d) { mdQuantity = d; }
    public void setMassUnit(double d) { mdMassUnit = d; }
    public void setMass_r(double d) { mdMass_r = d; }
    public void setFkItemId(int n) { mnFkItemId = n; }
    public void setFkItemTypeId(int n) { mnFkItemTypeId = n; }
    public void setFkUnitId(int n) { mnFkUnitId = n; }
    public void setFkPresentId(int n) { mnFkPresentId = n; }

    public int getPkJobId() { return mnPkJobId; }
    public int getPkMfgProdId() { return mnPkMfgProdId; }
    public double getQuantity() { return mdQuantity; }
    public double getMassUnit() { return mdMassUnit; }
    public double getMass_r() { return mdMass_r; }
    public int getFkItemId() { return mnFkItemId; }
    public int getFkItemTypeId() { return mnFkItemTypeId; }
    public int getFkUnitId() { return mnFkUnitId; }
    public int getFkPresentId() { return mnFkPresentId; }

    public void setRegItem(DDbItem o) { moRegItem = o; }
    public void setRegUnit(DDbUnit o) { moRegUnit = o; }
    public void setRegPresent(DDbPresent o) { moRegPresent = o; }
    
    public DDbItem getRegItem() { return moRegItem; }
    public DDbUnit getRegUnit() { return moRegUnit; }
    public DDbPresent getRegPresent() { return moRegPresent; }
    
    public void setXtaItemTypeCode(String s) { msXtaItemTypeCode = s; }
    public void setXtaItemTypeName(String s) { msXtaItemTypeName = s; }
    
    public String getXtaItemTypeCode() { return msXtaItemTypeCode; }
    public String getXtaItemTypeName() { return msXtaItemTypeName; }
    
    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkJobId = pk[0];
        mnPkMfgProdId = pk[1];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkJobId, mnPkMfgProdId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkJobId = 0;
        mnPkMfgProdId = 0;
        mdQuantity = 0;
        mdMassUnit = 0;
        mdMass_r = 0;
        mnFkItemId = 0;
        mnFkItemTypeId = 0;
        mnFkUnitId = 0;
        mnFkPresentId = 0;
        
        moRegItem = null;
        moRegUnit = null;
        moRegPresent = null;
        
        msXtaItemTypeCode = "";
        msXtaItemTypeName = "";
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_job = " + mnPkJobId + " AND id_mfg = " + mnPkMfgProdId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_job = " + pk[0] + " AND id_mfg = " + pk[1] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkMfgProdId = 0;

        msSql = "SELECT COALESCE(MAX(id_mfg), 0) + 1 FROM " + getSqlTable() + " " +
                "WHERE id_job = " + mnPkJobId + " ";
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkMfgProdId = resultSet.getInt(1);
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
            mnPkJobId = resultSet.getInt("id_job");
            mnPkMfgProdId = resultSet.getInt("id_mfg");
            mdQuantity = resultSet.getDouble("qty");
            mdMassUnit = resultSet.getDouble("mass_unt");
            mdMass_r = resultSet.getDouble("mass_r");
            mnFkItemId = resultSet.getInt("fk_itm");
            mnFkItemTypeId = resultSet.getInt("fk_itm_tp");
            mnFkUnitId = resultSet.getInt("fk_uom");
            mnFkPresentId = resultSet.getInt("fk_pre");

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
                    mnPkJobId + ", " + 
                    mnPkMfgProdId + ", " + 
                    mdQuantity + ", " + 
                    mdMassUnit + ", " + 
                    mdMass_r + ", " + 
                    mnFkItemId + ", " + 
                    mnFkItemTypeId + ", " + 
                    mnFkUnitId + ", " + 
                    mnFkPresentId + " " + 
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
    public DDbJobMfgProd clone() throws CloneNotSupportedException {
        DDbJobMfgProd registry = new DDbJobMfgProd();

        registry.setPkJobId(this.getPkJobId());
        registry.setPkMfgProdId(this.getPkMfgProdId());
        registry.setQuantity(this.getQuantity());
        registry.setMassUnit(this.getMassUnit());
        registry.setMass_r(this.getMass_r());
        registry.setFkItemId(this.getFkItemId());
        registry.setFkItemTypeId(this.getFkItemTypeId());
        registry.setFkUnitId(this.getFkUnitId());
        registry.setFkPresentId(this.getFkPresentId());

        registry.setRegItem(this.getRegItem() == null ? null : this.getRegItem().clone());
        registry.setRegUnit(this.getRegUnit() == null ? null : this.getRegUnit().clone());
        registry.setRegPresent(this.getRegPresent()== null ? null : this.getRegPresent().clone());

        registry.setXtaItemTypeCode(this.getXtaItemTypeCode());
        registry.setXtaItemTypeName(this.getXtaItemTypeName());
        
        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }

    public void compute(final DGuiSession session) {
        readRegMembers(session, true);
        readXtaMembers(session);
        
        mdMass_r = DLibUtils.round(mdMassUnit * mdQuantity, DLibUtils.getDecimalFormatQuantity().getMaximumFractionDigits());
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
                value = mdQuantity;
                break;
            case 2:
                value = moRegUnit.getCode();
                break;
            case 3:
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
}
