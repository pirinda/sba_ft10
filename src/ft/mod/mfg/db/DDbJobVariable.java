/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.mfg.db;

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
public class DDbJobVariable extends DDbRegistryUser implements DGridRow, DGridCellNumber {

    protected int mnPkJobId;
    protected int mnPkVariableId;
    protected double mdValue;

    protected DDbVariable moRegVariable;

    public DDbJobVariable() {
        super(DModConsts.M_JOB_VAR);
        initRegistry();
    }

    private void readRegMembers(final DGuiSession session, final boolean update) {
        moRegVariable = (DDbVariable) session.readRegistry(DModConsts.MU_VAR, new int[] { mnPkVariableId });
    }
    
    public void setPkJobId(int n) { mnPkJobId = n; }
    public void setPkVariableId(int n) { mnPkVariableId = n; }
    public void setValue(double d) { mdValue = d; }

    public int getPkJobId() { return mnPkJobId; }
    public int getPkVariableId() { return mnPkVariableId; }
    public double getValue() { return mdValue; }

    public void setRegVariable(DDbVariable o) { moRegVariable = o; }
    
    public DDbVariable getRegVariable() { return moRegVariable; }

    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkJobId = pk[0];
        mnPkVariableId = pk[1];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkJobId, mnPkVariableId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkJobId = 0;
        mnPkVariableId = 0;
        mdValue = 0;
        
        moRegVariable = null;
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_job = " + mnPkJobId + " AND id_var = " + mnPkVariableId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_job = " + pk[0] + " AND id_var = " + pk[1] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkVariableId = 0;

        msSql = "SELECT COALESCE(MAX(id_var), 0) + 1 FROM " + getSqlTable() + " " +
                "WHERE id_job = " + mnPkJobId + " ";
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkVariableId = resultSet.getInt(1);
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
            mnPkVariableId = resultSet.getInt("id_var");
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

        if (mbRegistryNew) {
            computePrimaryKey(session);
            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkJobId + ", " + 
                    mnPkVariableId + ", " + 
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
    public DDbJobVariable clone() throws CloneNotSupportedException {
        DDbJobVariable registry = new DDbJobVariable();

        registry.setPkJobId(this.getPkJobId());
        registry.setPkVariableId(this.getPkVariableId());
        registry.setValue(this.getValue());
        
        registry.setRegVariable(this.getRegVariable()== null ? null : this.getRegVariable().clone());
        
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
                value = moRegVariable.getName();
                break;
            case 1:
                value = mdValue;
                break;
            case 2:
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
                break;
            case 1:
                mdValue = (Double) value;
                break;
            case 2:
                break;
            default:
        }
    }

    @Override
    public int getDecimals() {
        return moRegVariable.getDecimals();
    }
}
