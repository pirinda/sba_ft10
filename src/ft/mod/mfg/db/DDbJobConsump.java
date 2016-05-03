/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.mfg.db;

import ft.lib.DLibRegistry;
import ft.mod.DModConsts;
import ft.mod.cfg.db.DDbItem;
import ft.mod.cfg.db.DDbUnit;
import ft.mod.stk.db.DDbWsdRow;
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
public class DDbJobConsump extends DDbRegistryUser implements DGridRow, DLibRegistry {

    protected int mnPkJobId;
    protected int mnPkConsumpId;
    protected double mdQuantity;
    protected double mdMassUnit;
    protected double mdMass_r;
    protected String msLot;
    protected boolean mbRework;
    protected int mnFkReqmentJobId;
    protected int mnFkReqmentReqmentId;
    protected int mnFkItemId;
    protected int mnFkItemTypeId;
    protected int mnFkUnitId;

    protected DDbItem moRegItem;
    protected DDbUnit moRegUnit;

    protected String msXtaItemTypeCode;
    protected String msXtaItemTypeName;
    
    public DDbJobConsump() {
        super(DModConsts.M_JOB_CON);
        initRegistry();
    }

    private void readRegMembers(final DGuiSession session, final boolean update) {
        moRegItem = (DDbItem) session.readRegistry(DModConsts.CU_ITM, new int[] { mnFkItemId });
        
        if (update) {
            mnFkItemTypeId = moRegItem.getRegFamily().getFkItemTypeId();
            mnFkUnitId = moRegItem.getFkUnitId();
            mdMassUnit = moRegItem.getMassUnit();
        }
        
        moRegUnit = (DDbUnit) session.readRegistry(DModConsts.CU_UOM, new int[] { mnFkUnitId });
    }
    
    private void readXtaMembers(final DGuiSession session) {
        msXtaItemTypeCode = (String) session.readField(DModConsts.CS_ITM_TP, new int[] { mnFkItemTypeId }, DDbRegistry.FIELD_CODE);
        msXtaItemTypeName = (String) session.readField(DModConsts.CS_ITM_TP, new int[] { mnFkItemTypeId }, DDbRegistry.FIELD_NAME);
    }

    public void setPkJobId(int n) { mnPkJobId = n; }
    public void setPkConsumpId(int n) { mnPkConsumpId = n; }
    public void setQuantity(double d) { mdQuantity = d; }
    public void setMassUnit(double d) { mdMassUnit = d; }
    public void setMass_r(double d) { mdMass_r = d; }
    public void setLot(String s) { msLot = s; }
    public void setRework(boolean b) { mbRework = b; }
    public void setFkReqmentJobId(int n) { mnFkReqmentJobId = n; }
    public void setFkReqmentReqmentId(int n) { mnFkReqmentReqmentId = n; }
    public void setFkItemId(int n) { mnFkItemId = n; }
    public void setFkItemTypeId(int n) { mnFkItemTypeId = n; }
    public void setFkUnitId(int n) { mnFkUnitId = n; }

    public int getPkJobId() { return mnPkJobId; }
    public int getPkConsumpId() { return mnPkConsumpId; }
    public double getQuantity() { return mdQuantity; }
    public double getMassUnit() { return mdMassUnit; }
    public double getMass_r() { return mdMass_r; }
    public String getLot() { return msLot; }
    public boolean isRework() { return mbRework; }
    public int getFkReqmentJobId() { return mnFkReqmentJobId; }
    public int getFkReqmentReqmentId() { return mnFkReqmentReqmentId; }
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
    
    public int[] getUtilReqmentKey() { return new int[] { mnFkReqmentJobId, mnFkReqmentReqmentId }; }
    
    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkJobId = pk[0];
        mnPkConsumpId = pk[1];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkJobId, mnPkConsumpId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkJobId = 0;
        mnPkConsumpId = 0;
        mdQuantity = 0;
        mdMassUnit = 0;
        mdMass_r = 0;
        msLot = "";
        mbRework = false;
        mnFkReqmentJobId = 0;
        mnFkReqmentReqmentId = 0;
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
        return "WHERE id_job = " + mnPkJobId + " AND id_con = " + mnPkConsumpId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_job = " + pk[0] + " AND id_con = " + pk[1] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkConsumpId = 0;

        msSql = "SELECT COALESCE(MAX(id_con), 0) + 1 FROM " + getSqlTable() + " " +
                "WHERE id_job = " + mnPkJobId + " ";
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkConsumpId = resultSet.getInt(1);
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
            mnPkConsumpId = resultSet.getInt("id_con");
            mdQuantity = resultSet.getDouble("qty");
            mdMassUnit = resultSet.getDouble("mass_unt");
            mdMass_r = resultSet.getDouble("mass_r");
            msLot = resultSet.getString("lot");
            mbRework = resultSet.getBoolean("b_rwk");
            mnFkReqmentJobId = resultSet.getInt("fk_req_job");
            mnFkReqmentReqmentId = resultSet.getInt("fk_req_req");
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
                    mnPkJobId + ", " + 
                    mnPkConsumpId + ", " + 
                    mdQuantity + ", " + 
                    mdMassUnit + ", " + 
                    mdMass_r + ", " + 
                    "'" + msLot + "', " + 
                    (mbRework ? 1 : 0) + ", " + 
                    mnFkReqmentJobId + ", " + 
                    mnFkReqmentReqmentId + ", " + 
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
    public DDbJobConsump clone() throws CloneNotSupportedException {
        DDbJobConsump registry = new DDbJobConsump();

        registry.setPkJobId(this.getPkJobId());
        registry.setPkConsumpId(this.getPkConsumpId());
        registry.setQuantity(this.getQuantity());
        registry.setMassUnit(this.getMassUnit());
        registry.setMass_r(this.getMass_r());
        registry.setLot(this.getLot());
        registry.setRework(this.isRework());
        registry.setFkReqmentJobId(this.getFkReqmentJobId());
        registry.setFkReqmentReqmentId(this.getFkReqmentReqmentId());
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
            case 4:
                value = msLot;
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
    }
    
    public DDbWsdRow createWsdRow() {
        DDbWsdRow row = new DDbWsdRow();
        
        //row.setPkWsdId(...);
        //row.setPkRowId(...);
        row.setUnits(this.getQuantity());
        row.setAmountUnit(0d);
        //row.setAmount_r(...); // computed on save
        row.setLot(this.getLot());
        //row.setMassUnit(...); // updated on save
        //row.setMass_r(...); // computed on save
        row.setFkItemId(this.getFkItemId());
        //row.setFkItemTypeId(...); // updated on save
        //row.setFkUnitId(...); // updated on save
        //row.setFkWsdWsdId_n(...); // not needed
        //row.setFkWsdRowId_n(...); // not needed
        
        return row;
    }
}
