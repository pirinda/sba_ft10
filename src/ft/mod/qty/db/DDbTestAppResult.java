/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.qty.db;

import ft.mod.DModConsts;
import java.sql.ResultSet;
import java.sql.SQLException;
import sba.lib.db.DDbConsts;
import sba.lib.db.DDbRegistryUser;
import sba.lib.grid.DGridRow;
import sba.lib.grid.cell.DGridCellNumber;
import sba.lib.gui.DGuiSession;

/**
 *
 * @author Sergio Flores
 */
public class DDbTestAppResult extends DDbRegistryUser implements DGridRow, DGridCellNumber {

    protected int mnPkAppId;
    protected int mnPkVariableId;
    protected int mnPkResultId;
    protected double mdValue;

    protected DDbVariable moRegVariable;

    public DDbTestAppResult() {
        super(DModConsts.Q_APP_RES);
        initRegistry();
    }

    private void readRegMembers(final DGuiSession session, final boolean update) {
        moRegVariable = (DDbVariable) session.readRegistry(DModConsts.QU_VAR, new int[] { mnPkVariableId });
    }
    
    public void setPkAppId(int n) { mnPkAppId = n; }
    public void setPkVariableId(int n) { mnPkVariableId = n; }
    public void setPkResultId(int n) { mnPkResultId = n; }
    public void setValue(double d) { mdValue = d; }

    public int getPkAppId() { return mnPkAppId; }
    public int getPkVariableId() { return mnPkVariableId; }
    public int getPkResultId() { return mnPkResultId; }
    public double getValue() { return mdValue; }

    public void setRegVariable(DDbVariable o) { moRegVariable = o; }
    
    public DDbVariable getRegVariable() { return moRegVariable; }

    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkAppId = pk[0];
        mnPkVariableId = pk[1];
        mnPkResultId = pk[2];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkVariableId, mnPkVariableId, mnPkResultId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkAppId = 0;
        mnPkVariableId = 0;
        mnPkResultId = 0;
        mdValue = 0;
        
        moRegVariable = null;
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_app = " + mnPkAppId + " AND id_var = " + mnPkVariableId + " AND id_res = " + mnPkResultId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_app = " + pk[1] + " AND id_var = " + pk[2] + " AND id_res = " + pk[3] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkResultId = 0;

        msSql = "SELECT COALESCE(MAX(id_var), 0) + 1 FROM " + getSqlTable() + " " +
                "WHERE id_app = " + mnPkAppId + " AND id_var = " + mnPkVariableId + " ";
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkResultId = resultSet.getInt(1);
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
            mnPkAppId = resultSet.getInt("id_app");
            mnPkVariableId = resultSet.getInt("id_var");
            mnPkResultId = resultSet.getInt("id_res");
            mdValue = resultSet.getDouble("val");

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
            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkAppId + ", " + 
                    mnPkVariableId + ", " + 
                    mnPkResultId + ", " + 
                    mdValue + " " + 
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
    public DDbTestAppResult clone() throws CloneNotSupportedException {
        DDbTestAppResult registry = new DDbTestAppResult();

        registry.setPkAppId(this.getPkAppId());
        registry.setPkVariableId(this.getPkVariableId());
        registry.setPkResultId(this.getPkResultId());
        registry.setValue(this.getValue());
        
        registry.setRegVariable(this.getRegVariable() == null ? null : this.getRegVariable().clone());
        
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
                value = mnPkResultId;
                break;
            case 1:
                value = moRegVariable.getName();
                break;
            case 2:
                value = mdValue;
                break;
            case 3:
                value = moRegVariable.getUnit();
                break;
            default:
        }
        
        return value;
    }

    @Override
    public void setRowValueAt(Object value, int col) {
        switch (col) {
            case 0:
            case 1:
                break;
            case 2:
                mdValue = (Double) value;
                break;
            case 3:
                break;
            default:
        }
    }

    @Override
    public int getDecimals() {
        return moRegVariable.getDecimals();
    }
    
    public void compute(final DGuiSession session) {
        readRegMembers(session, true);
    }
}
