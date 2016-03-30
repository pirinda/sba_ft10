/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.mfg.db;

import ft.mod.DModConsts;
import ft.mod.DModSysConsts;
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
public class DDbFormulaComp extends DDbRegistryUser implements DGridRow {

    protected int mnPkFormulaId;
    protected int mnPkCompId;
    protected double mdQuantity;
    protected double mdMassUnit;
    protected double mdMass_r;
    protected boolean mbStandard;
    protected int mnFkCompTypeId;
    protected int mnFkCompId;
    protected int mnFkItemTypeId;
    protected int mnFkUnitId;
    
    protected DDbFamily moRegFamily;
    protected DDbItem moRegItem;
    protected DDbUnit moRegUnit;

    protected String msXtaCompTypeCode;
    protected String msXtaCompTypeName;
    
    public DDbFormulaComp() {
        super(DModConsts.MU_FRM_CMP);
        initRegistry();
    }
    
    private void readRegMembers(final DGuiSession session, final boolean update) {
        switch (mnFkCompTypeId) {
            case DModSysConsts.MS_CMP_TP_FAM:
                moRegFamily = (DDbFamily) session.readRegistry(DModConsts.CU_FAM, new int[] { mnFkCompId });
                moRegItem = null;
                
                if (update) {
                    mnFkItemTypeId = moRegFamily.getFkItemTypeId();
                    mnFkUnitId = moRegFamily.getFkUnitId();
                }
                
                moRegUnit = (DDbUnit) session.readRegistry(DModConsts.CU_UOM, new int[] { mnFkUnitId });
                
                break;
                
            case DModSysConsts.MS_CMP_TP_ITM:
                moRegItem = (DDbItem) session.readRegistry(DModConsts.CU_ITM, new int[] { mnFkCompId });
                moRegFamily = null;
                
                if (update) {
                    mnFkItemTypeId = moRegItem.getXtaFkItemTypeId();
                    mnFkUnitId = moRegItem.getFkUnitId();
                }
                
                moRegUnit = (DDbUnit) session.readRegistry(DModConsts.CU_UOM, new int[] { mnFkUnitId });
                break;
                
            default:
        }
    }
    
    private void readXtaMembers(final DGuiSession session) {
        msXtaCompTypeCode = (String) session.readField(DModConsts.MS_CMP_TP, new int[] { mnFkCompTypeId }, DDbRegistry.FIELD_CODE);
        msXtaCompTypeName = (String) session.readField(DModConsts.MS_CMP_TP, new int[] { mnFkCompTypeId }, DDbRegistry.FIELD_NAME);
    }

    public void setPkFormulaId(int n) { mnPkFormulaId = n; }
    public void setPkCompId(int n) { mnPkCompId = n; }
    public void setQuantity(double d) { mdQuantity = d; }
    public void setMassUnit(double d) { mdMassUnit = d; }
    public void setMass_r(double d) { mdMass_r = d; }
    public void setStandard(boolean b) { mbStandard = b; }
    public void setFkCompTypeId(int n) { mnFkCompTypeId = n; }
    public void setFkCompId(int n) { mnFkCompId = n; }
    public void setFkItemTypeId(int n) { mnFkItemTypeId = n; }
    public void setFkUnitId(int n) { mnFkUnitId = n; }

    public int getPkFormulaId() { return mnPkFormulaId; }
    public int getPkCompId() { return mnPkCompId; }
    public double getQuantity() { return mdQuantity; }
    public double getMassUnit() { return mdMassUnit; }
    public double getMass_r() { return mdMass_r; }
    public boolean isStandard() { return mbStandard; }
    public int getFkCompTypeId() { return mnFkCompTypeId; }
    public int getFkCompId() { return mnFkCompId; }
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
    
    public String getXtaCompTypeCode() { return msXtaCompTypeCode; }
    public String getXtaCompTypeName() { return msXtaCompTypeName; }
    
    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkFormulaId = pk[0];
        mnPkCompId = pk[1];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkFormulaId, mnPkCompId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkFormulaId = 0;
        mnPkCompId = 0;
        mdQuantity = 0;
        mdMassUnit = 0;
        mdMass_r = 0;
        mbStandard = false;
        mnFkCompTypeId = 0;
        mnFkCompId = 0;
        mnFkItemTypeId = 0;
        mnFkUnitId = 0;
        
        moRegFamily = null;
        moRegItem = null;
        moRegUnit = null;
        
        msXtaCompTypeCode = "";
        msXtaCompTypeName = "";
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_frm = " + mnPkFormulaId + " AND id_cmp = " + mnPkCompId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_frm = " + pk[0] + " AND id_cmp = " + pk[1] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkCompId = 0;

        msSql = "SELECT COALESCE(MAX(id_cmp), 0) + 1 FROM " + getSqlTable() + " " +
                "WHERE id_frm = " + mnPkFormulaId + " ";
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkCompId = resultSet.getInt(1);
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
            mnPkCompId = resultSet.getInt("id_cmp");
            mdQuantity = resultSet.getDouble("qty");
            mdMassUnit = resultSet.getDouble("mass_unt");
            mdMass_r = resultSet.getDouble("mass_r");
            mbStandard = resultSet.getBoolean("b_std");
            mnFkCompTypeId = resultSet.getInt("fk_cmp_tp");
            mnFkCompId = resultSet.getInt("fk_cmp");
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
                   mnPkCompId + ", " + 
                   mdQuantity + ", " + 
                   mdMassUnit + ", " + 
                   mdMass_r + ", " + 
                   (mbStandard ? 1 : 0) + ", " + 
                   mnFkCompTypeId + ", " + 
                   mnFkCompId + ", " + 
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
    public DDbFormulaComp clone() throws CloneNotSupportedException {
        DDbFormulaComp registry = new DDbFormulaComp();

        registry.setPkFormulaId(this.getPkFormulaId());
        registry.setPkCompId(this.getPkCompId());
        registry.setQuantity(this.getQuantity());
        registry.setMassUnit(this.getMassUnit());
        registry.setMass_r(this.getMass_r());
        registry.setStandard(this.isStandard());
        registry.setFkCompTypeId(this.getFkCompTypeId());
        registry.setFkCompId(this.getFkCompId());
        registry.setFkItemTypeId(this.getFkItemTypeId());
        registry.setFkUnitId(this.getFkUnitId());

        registry.setRegFamily(this.getRegFamily() == null ? null : this.getRegFamily().clone());
        registry.setRegItem(this.getRegItem() == null ? null : this.getRegItem().clone());
        registry.setRegUnit(this.getRegUnit() == null ? null : this.getRegUnit().clone());
        
        registry.setXtaCompTypeCode(this.getXtaCompTypeCode());
        registry.setXtaCompTypeName(this.getXtaCompTypeName());
        
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
                value = mnPkCompId;
                break;
            case 1:
                value = msXtaCompTypeCode;
                break;
            case 2:
                switch (mnFkCompTypeId) {
                    case DModSysConsts.MS_CMP_TP_FAM:
                        value = moRegFamily.getXtaItemTypeCode();
                        break;
                    case DModSysConsts.MS_CMP_TP_ITM:
                        value = moRegItem.getXtaItemTypeCode();
                        break;
                    default:
                }
                break;
            case 3:
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
            case 4:
                switch (mnFkCompTypeId) {
                    case DModSysConsts.MS_CMP_TP_FAM:
                        value = moRegFamily.getCode();
                        break;
                    case DModSysConsts.MS_CMP_TP_ITM:
                        value = moRegItem.getCode();
                        break;
                    default:
                }
                break;
            case 5:
                value = mdQuantity;
                break;
            case 6:
                value = moRegUnit.getCode();
                break;
            case 7:
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
    
    public void compute(final DGuiSession session) {
        readRegMembers(session, true);
        readXtaMembers(session);
        
        mdMass_r = 0;
        
        switch (mnFkCompTypeId) {
            case DModSysConsts.MS_CMP_TP_FAM:
                mdMass_r = DLibUtils.round(mdMassUnit * mdQuantity, DLibUtils.getDecimalFormatAmountUnitary().getMaximumFractionDigits());
                break;
            case DModSysConsts.MS_CMP_TP_ITM:
                mdMass_r = DLibUtils.round((mdMassUnit = moRegItem.getMassUnit()) * mdQuantity, DLibUtils.getDecimalFormatAmountUnitary().getMaximumFractionDigits());
                break;
            default:
        }
    }
    
    public DDbJobReqment createJobReqment(final double loads) throws CloneNotSupportedException {
        DDbJobReqment registry = new DDbJobReqment();
        
        //registry.setPkJobId(...);
        registry.setPkReqmentId(this.getPkCompId());
        registry.setQuantity(this.getQuantity() * loads);
        registry.setMassUnit(this.getMassUnit());
        registry.setMass_r(this.getMass_r() * loads);
        registry.setStandard(this.isStandard());
        registry.setFkCompTypeId(this.getFkCompTypeId());
        registry.setFkCompId(this.getFkCompId());
        registry.setFkItemTypeId(this.getFkItemTypeId());
        registry.setFkUnitId(this.getFkUnitId());
        
        registry.setRegFamily(this.getRegFamily() == null ? null : this.getRegFamily().clone());
        registry.setRegItem(this.getRegItem() == null ? null : this.getRegItem().clone());
        registry.setRegUnit(this.getRegUnit() == null ? null : this.getRegUnit().clone());
        
        registry.setXtaCompTypeCode(this.getXtaCompTypeCode());
        registry.setXtaCompTypeName(this.getXtaCompTypeName());
        
        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
}
