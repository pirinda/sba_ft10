/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.mfg.db;

import ft.mod.DModConsts;
import ft.mod.cfg.db.DDbItem;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    protected String msStatisticsReference;
    protected boolean mbStandard;
    protected boolean mbConsVariable1;
    protected int mnFkItemTypeId;
    protected int mnFkItemId;
    protected int mnFkUnitId;
    
    protected String msXtaItemTypeCode;
    protected String msXtaItemTypeName;
    protected String msXtaItemCode;
    protected String msXtaItemName;
    protected String msXtaUnitCode;
    protected String msXtaUnitName;

    public DDbFormulaComp() {
        super(DModConsts.MU_FRM_CMP);
        initRegistry();
    }

    public void setPkFormulaId(int n) { mnPkFormulaId = n; }
    public void setPkCompId(int n) { mnPkCompId = n; }
    public void setQuantity(double d) { mdQuantity = d; }
    public void setStatisticsReference(String s) { msStatisticsReference = s; }
    public void setStandard(boolean b) { mbStandard = b; }
    public void setConsVariable1(boolean b) { mbConsVariable1 = b; }
    public void setFkItemTypeId(int n) { mnFkItemTypeId = n; }
    public void setFkItemId(int n) { mnFkItemId = n; }
    public void setFkUnitId(int n) { mnFkUnitId = n; }

    public int getPkFormulaId() { return mnPkFormulaId; }
    public int getPkCompId() { return mnPkCompId; }
    public double getQuantity() { return mdQuantity; }
    public String getStatisticsReference() { return msStatisticsReference; }
    public boolean isStandard() { return mbStandard; }
    public boolean isConsVariable1() { return mbConsVariable1; }
    public int getFkItemTypeId() { return mnFkItemTypeId; }
    public int getFkItemId() { return mnFkItemId; }
    public int getFkUnitId() { return mnFkUnitId; }
    
    public void setXtaItemTypeCode(String s) { msXtaItemTypeCode = s; }
    public void setXtaItemTypeName(String s) { msXtaItemTypeName = s; }
    public void setXtaItemCode(String s) { msXtaItemCode = s; }
    public void setXtaItemName(String s) { msXtaItemName = s; }
    public void setXtaUnitCode(String s) { msXtaUnitCode = s; }
    public void setXtaUnitName(String s) { msXtaUnitName = s; }
    
    public String getXtaItemTypeCode() { return msXtaItemTypeCode; }
    public String getXtaItemTypeName() { return msXtaItemTypeName; }
    public String getXtaItemCode() { return msXtaItemCode; }
    public String getXtaItemName() { return msXtaItemName; }
    public String getXtaUnitCode() { return msXtaUnitCode; }
    public String getXtaUnitName() { return msXtaUnitName; }
    
    public void readXtaData(final DGuiSession session) {
        // Reset extra data:
        
        msXtaItemTypeCode = "";
        msXtaItemTypeName = "";
        msXtaItemCode = "";
        msXtaItemName = "";
        msXtaUnitCode = "";
        msXtaUnitName = "";
        
        // Read extra data:
        
        msXtaItemTypeCode = (String) session.readField(DModConsts.CS_ITM_TP, new int[] { mnFkItemTypeId }, DDbRegistry.FIELD_CODE);
        msXtaItemTypeName = (String) session.readField(DModConsts.CS_ITM_TP, new int[] { mnFkItemTypeId }, DDbRegistry.FIELD_NAME);
        msXtaItemCode = (String) session.readField(DModConsts.CU_ITM, new int[] { mnFkItemId }, DDbRegistry.FIELD_CODE);
        msXtaItemName = (String) session.readField(DModConsts.CU_ITM, new int[] { mnFkItemId }, DDbRegistry.FIELD_NAME);
        msXtaUnitCode = (String) session.readField(DModConsts.CU_UNT, new int[] { mnFkUnitId }, DDbRegistry.FIELD_CODE);
        msXtaUnitName = (String) session.readField(DModConsts.CU_UNT, new int[] { mnFkUnitId }, DDbRegistry.FIELD_NAME);
    }
    
    public void updateSnapshotData(final DGuiSession session) {
        DDbItem item = null;

        item = (DDbItem) session.readRegistry(DModConsts.CU_ITM, new int[] { mnFkItemId }, DDbConsts.MODE_STEALTH);

        mnFkItemTypeId = item.getXtaFkItemTypeId();
        mnFkUnitId = item.getFkUnitId();
    }
    
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
        msStatisticsReference = "";
        mbStandard = false;
        mbConsVariable1 = false;
        mnFkItemTypeId = 0;
        mnFkItemId = 0;
        mnFkUnitId = 0;
        
        msXtaItemTypeCode = "";
        msXtaItemTypeName = "";
        msXtaItemCode = "";
        msXtaItemName = "";
        msXtaUnitCode = "";
        msXtaUnitName = "";
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
            msStatisticsReference = resultSet.getString("sta_ref");
            mbStandard = resultSet.getBoolean("b_std");
            mbConsVariable1 = resultSet.getBoolean("b_con_var_1");
            mnFkItemTypeId = resultSet.getInt("fk_itm_tp");
            mnFkItemId = resultSet.getInt("fk_itm");
            mnFkUnitId = resultSet.getInt("fk_unt");

            // Read aswell extra data:
            
            readXtaData(session);

            // Finish registry reading:

            mbRegistryNew = false;
        }

        mnQueryResultId = DDbConsts.READ_OK;
    }

    @Override
    public void save(DGuiSession session) throws SQLException, Exception {
        initQueryMembers();
        mnQueryResultId = DDbConsts.SAVE_ERROR;
        
        updateSnapshotData(session);

        if (mbRegistryNew) {
            computePrimaryKey(session);

            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkFormulaId + ", " + 
                    mnPkCompId + ", " + 
                    mdQuantity + ", " + 
                    "'" + msStatisticsReference + "', " + 
                    (mbStandard ? 1 : 0) + ", " + 
                    (mbConsVariable1 ? 1 : 0) + ", " + 
                    mnFkItemTypeId + ", " + 
                    mnFkItemId + ", " + 
                    mnFkUnitId + " " + 
                    ")";
        }
        else {
            msSql = "UPDATE " + getSqlTable() + " SET " +
                    //"id_frm = " + mnPkFormulaId + ", " +
                    //"id_cmp = " + mnPkCompId + ", " +
                    "qty = " + mdQuantity + ", " +
                    "sta_ref = '" + msStatisticsReference + "', " +
                    "b_std = " + (mbStandard ? 1 : 0) + ", " +
                    "b_con_var_1 = " + (mbConsVariable1 ? 1 : 0) + ", " +
                    "fk_itm_tp = " + mnFkItemTypeId + ", " +
                    "fk_itm = " + mnFkItemId + ", " +
                    "fk_unt = " + mnFkUnitId + ", " +
                    getSqlWhere();
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
        registry.setStatisticsReference(this.getStatisticsReference());
        registry.setStandard(this.isStandard());
        registry.setConsVariable1(this.isConsVariable1());
        registry.setFkItemTypeId(this.getFkItemTypeId());
        registry.setFkItemId(this.getFkItemId());
        registry.setFkUnitId(this.getFkUnitId());

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
        return true;
    }

    @Override
    public boolean isRowEdited() {
        return mbRegistryEdited;
    }

    @Override
    public void setRowEdited(boolean edited) {
        mbRegistryEdited = edited;
    }

    @Override
    public Object getRowValueAt(int col) {
        Object value = null;
        switch (col) {
            case 0:
                value = mnPkCompId;
                break;
            case 1:
                value = msXtaItemTypeCode;
                break;
            case 2:
                value = msXtaItemName;
                break;
            case 3:
                value = mbStandard;
                break;
            case 4:
                value = mdQuantity;
                break;
            case 5:
                value = msXtaUnitCode;
                break;
            case 6:
                value = mbConsVariable1;
                break;
            case 7:
                value = msStatisticsReference;
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
