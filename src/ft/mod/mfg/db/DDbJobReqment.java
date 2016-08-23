/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.mfg.db;

import ft.lib.DLibRegistry;
import ft.mod.DModConsts;
import ft.mod.DModSysConsts;
import ft.mod.cfg.db.DCfgConsts;
import ft.mod.cfg.db.DDbFamily;
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
public class DDbJobReqment extends DDbRegistryUser implements DGridRow, DLibRegistry {

    protected int mnPkJobId;
    protected int mnPkReqmentId;
    protected double mdQuantity;
    protected double mdMassUnit;
    protected double mdMass_r;
    protected double mdBrix;
    protected double mdMassSolid_r;
    protected String msExclusionLabel;
    protected int mnFkCompTypeId;
    protected int mnFkCompIncTypeId;
    protected int mnFkItemId;
    protected int mnFkFamilyId;
    protected int mnFkItemTypeId;
    protected int mnFkUnitId;

    protected DDbFamily moRegFamily;
    protected DDbItem moRegItem;
    protected DDbUnit moRegUnit;

    protected String msXtaCompTypeCode;
    protected String msXtaCompTypeName;
    protected String msXtaCompIncTypeCode;
    protected String msXtaCompIncTypeName;
    
    protected double mdAuxQuantityConsump;
    
    public DDbJobReqment() {
        super(DModConsts.M_JOB_REQ);
        initRegistry();
    }

    private void readRegMembers(final DGuiSession session, final boolean update) {
        switch (mnFkCompTypeId) {
            case DModSysConsts.MS_CMP_TP_FAM:
                moRegFamily = (DDbFamily) session.readRegistry(DModConsts.CU_FAM, new int[] { mnFkFamilyId });
                moRegItem = null;
                
                if (update) {
                    mnFkItemTypeId = moRegFamily.getFkItemTypeId();
                    mnFkUnitId = moRegFamily.getFkUnitId();
                    mdMassUnit = moRegFamily.getMassUnit();
                    mdBrix = moRegFamily.getBrix();
                    
                    mnFkItemId = DModSysConsts.CU_ITM_ND;
                }
                
                moRegUnit = (DDbUnit) session.readRegistry(DModConsts.CU_UOM, new int[] { mnFkUnitId });
                break;
                
            case DModSysConsts.MS_CMP_TP_ITM:
                moRegItem = (DDbItem) session.readRegistry(DModConsts.CU_ITM, new int[] { mnFkItemId });
                moRegFamily = moRegItem.getRegFamily();
                
                if (update) {
                    mnFkItemTypeId = moRegItem.getRegFamily().getFkItemTypeId();
                    mnFkUnitId = moRegItem.getFkUnitId();
                    mdMassUnit = moRegItem.getMassUnit();
                    mdBrix = moRegItem.getBrix();
                    
                    mnFkFamilyId = moRegItem.getFkFamilyId();
                }
                
                moRegUnit = (DDbUnit) session.readRegistry(DModConsts.CU_UOM, new int[] { mnFkUnitId });
                break;
                
            default:
        }
    }
    
    private void readXtaMembers(final DGuiSession session) {
        msXtaCompTypeCode = (String) session.readField(DModConsts.MS_CMP_TP, new int[] { mnFkCompTypeId }, DDbRegistry.FIELD_CODE);
        msXtaCompTypeName = (String) session.readField(DModConsts.MS_CMP_TP, new int[] { mnFkCompTypeId }, DDbRegistry.FIELD_NAME);
        msXtaCompIncTypeCode = (String) session.readField(DModConsts.MS_CMP_INC_TP, new int[] { mnFkCompIncTypeId }, DDbRegistry.FIELD_CODE);
        msXtaCompIncTypeName = (String) session.readField(DModConsts.MS_CMP_INC_TP, new int[] { mnFkCompIncTypeId }, DDbRegistry.FIELD_NAME);
    }

    public void setPkJobId(int n) { mnPkJobId = n; }
    public void setPkReqmentId(int n) { mnPkReqmentId = n; }
    public void setQuantity(double d) { mdQuantity = d; }
    public void setMassUnit(double d) { mdMassUnit = d; }
    public void setMass_r(double d) { mdMass_r = d; }
    public void setBrix(double d) { mdBrix = d; }
    public void setMassSolid_r(double d) { mdMassSolid_r = d; }
    public void setExclusionLabel(String s) { msExclusionLabel = s; }
    public void setFkCompTypeId(int n) { mnFkCompTypeId = n; }
    public void setFkCompIncTypeId(int n) { mnFkCompIncTypeId = n; }
    public void setFkItemId(int n) { mnFkItemId = n; }
    public void setFkFamilyId(int n) { mnFkFamilyId = n; }
    public void setFkItemTypeId(int n) { mnFkItemTypeId = n; }
    public void setFkUnitId(int n) { mnFkUnitId = n; }

    public int getPkJobId() { return mnPkJobId; }
    public int getPkReqmentId() { return mnPkReqmentId; }
    public double getQuantity() { return mdQuantity; }
    public double getMassUnit() { return mdMassUnit; }
    public double getMass_r() { return mdMass_r; }
    public double getBrix() { return mdBrix; }
    public double getMassSolid_r() { return mdMassSolid_r; }
    public String getExclusionLabel() { return msExclusionLabel; }
    public int getFkCompTypeId() { return mnFkCompTypeId; }
    public int getFkCompIncTypeId() { return mnFkCompIncTypeId; }
    public int getFkItemId() { return mnFkItemId; }
    public int getFkFamilyId() { return mnFkFamilyId; }
    public int getFkItemTypeId() { return mnFkItemTypeId; }
    public int getFkUnitId() { return mnFkUnitId; }

    public void setRegFamily(DDbFamily o) { moRegFamily = o; }
    public void setRegItem(DDbItem o) { moRegItem = o; }
    public void setRegUnit(DDbUnit o) { moRegUnit = o; }
    
    public DDbFamily getRegFamily() { return moRegFamily; }
    public DDbItem getRegItem() { return moRegItem; }
    public DDbUnit getRegUnit() { return moRegUnit; }

    public void setXtaCompTypeCode(String s) { msXtaCompTypeCode = s; }
    public void setXtaCompTypeName(String s) { msXtaCompTypeName = s; }
    public void setXtaCompIncTypeCode(String s) { msXtaCompIncTypeCode = s; }
    public void setXtaCompIncTypeName(String s) { msXtaCompIncTypeName = s; }
    
    public String getXtaCompTypeCode() { return msXtaCompTypeCode; }
    public String getXtaCompTypeName() { return msXtaCompTypeName; }
    public String getXtaCompIncTypeCode() { return msXtaCompIncTypeCode; }
    public String getXtaCompIncTypeName() { return msXtaCompIncTypeName; }
    
    public void setAuxQuantityConsump(double d) { mdAuxQuantityConsump = d; }
    
    public double getAuxQuantityConsump() { return mdAuxQuantityConsump; }
    
    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkJobId = pk[0];
        mnPkReqmentId = pk[1];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkJobId, mnPkReqmentId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkJobId = 0;
        mnPkReqmentId = 0;
        mdQuantity = 0;
        mdMassUnit = 0;
        mdMass_r = 0;
        mdBrix = 0;
        mdMassSolid_r = 0;
        msExclusionLabel = "";
        mnFkCompTypeId = 0;
        mnFkCompIncTypeId = 0;
        mnFkItemId = 0;
        mnFkFamilyId = 0;
        mnFkItemTypeId = 0;
        mnFkUnitId = 0;
        
        moRegFamily = null;
        moRegItem = null;
        moRegUnit = null;
        
        msXtaCompTypeCode = "";
        msXtaCompTypeName = "";
        msXtaCompIncTypeCode = "";
        msXtaCompIncTypeName = "";
        
        mdAuxQuantityConsump = 0;
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_job = " + mnPkJobId + " AND id_req = " + mnPkReqmentId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_job = " + pk[0] + " AND id_req = " + pk[1] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkReqmentId = 0;

        msSql = "SELECT COALESCE(MAX(id_req), 0) + 1 FROM " + getSqlTable() + " " +
                "WHERE id_job = " + mnPkJobId + " ";
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkReqmentId = resultSet.getInt(1);
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
            mnPkReqmentId = resultSet.getInt("id_req");
            mdQuantity = resultSet.getDouble("qty");
            mdMassUnit = resultSet.getDouble("mass_unt");
            mdMass_r = resultSet.getDouble("mass_r");
            mdBrix = resultSet.getDouble("brix");
            mdMassSolid_r = resultSet.getDouble("mass_sld_r");
            msExclusionLabel = resultSet.getString("exc_lab");
            mnFkCompTypeId = resultSet.getInt("fk_cmp_tp");
            mnFkCompIncTypeId = resultSet.getInt("fk_cmp_inc_tp");
            mnFkItemId = resultSet.getInt("fk_itm");
            mnFkFamilyId = resultSet.getInt("fk_fam");
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
                    mnPkJobId + ", " + 
                    mnPkReqmentId + ", " + 
                    mdQuantity + ", " + 
                    mdMassUnit + ", " + 
                    mdMass_r + ", " + 
                    mdBrix + ", " + 
                    mdMassSolid_r + ", " + 
                    "'" + msExclusionLabel + "', " + 
                    mnFkCompTypeId + ", " + 
                    mnFkCompIncTypeId + ", " + 
                    mnFkItemId + ", " + 
                    mnFkFamilyId + ", " + 
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
    public DDbJobReqment clone() throws CloneNotSupportedException {
        DDbJobReqment registry = new DDbJobReqment();

        registry.setPkJobId(this.getPkJobId());
        registry.setPkReqmentId(this.getPkReqmentId());
        registry.setQuantity(this.getQuantity());
        registry.setMassUnit(this.getMassUnit());
        registry.setMass_r(this.getMass_r());
        registry.setBrix(this.getBrix());
        registry.setMassSolid_r(this.getMassSolid_r());
        registry.setExclusionLabel(this.getExclusionLabel());
        registry.setFkCompTypeId(this.getFkCompTypeId());
        registry.setFkCompIncTypeId(this.getFkCompIncTypeId());
        registry.setFkItemId(this.getFkItemId());
        registry.setFkFamilyId(this.getFkFamilyId());
        registry.setFkItemTypeId(this.getFkItemTypeId());
        registry.setFkUnitId(this.getFkUnitId());
        
        registry.setRegFamily(this.getRegFamily() == null ? null : this.getRegFamily().clone());
        registry.setRegItem(this.getRegItem() == null ? null : this.getRegItem().clone());
        registry.setRegUnit(this.getRegUnit() == null ? null : this.getRegUnit().clone());
        
        registry.setXtaCompTypeCode(this.getXtaCompTypeCode());
        registry.setXtaCompTypeName(this.getXtaCompTypeName());
        registry.setXtaCompIncTypeCode(this.getXtaCompIncTypeCode());
        registry.setXtaCompIncTypeName(this.getXtaCompIncTypeName());
        
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
                value = mnPkReqmentId;
                break;
            case 1:
                switch (mnFkCompTypeId) {
                    case DModSysConsts.MS_CMP_TP_FAM:
                        value = moRegFamily.getName();
                        break;
                    case DModSysConsts.MS_CMP_TP_ITM:
                        value = moRegItem.getName();
                        break;
                    default:
                }
                break;
            case 2:
                value = mdQuantity;
                break;
            case 3:
                value = mdAuxQuantityConsump;
                break;
            case 4:
                value = moRegUnit.getCode();
                break;
            case 5:
                value = msXtaCompTypeCode;
                break;
            case 6:
                switch (mnFkCompTypeId) {
                    case DModSysConsts.MS_CMP_TP_FAM:
                        value = moRegFamily.getXtaItemTypeCode();
                        break;
                    case DModSysConsts.MS_CMP_TP_ITM:
                        value = moRegItem.getRegFamily().getXtaItemTypeCode();
                        break;
                    default:
                }
                break;
            case 7:
                value = msXtaCompIncTypeCode;
                break;
            case 8:
                value = msExclusionLabel;
                break;
            default:
        }
        
        return value;
    }

    @Override
    public void setRowValueAt(Object value, int col) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void compute(final DGuiSession session) {
        readRegMembers(session, true);
        readXtaMembers(session);
        
        mdMass_r = DLibUtils.round(mdMassUnit * mdQuantity, DLibUtils.getDecimalFormatQuantity().getMaximumFractionDigits());
        mdMassSolid_r = DLibUtils.round(mdMass_r * (mdBrix / DCfgConsts.BRIX_MAX), DLibUtils.getDecimalFormatQuantity().getMaximumFractionDigits());
    }
}
