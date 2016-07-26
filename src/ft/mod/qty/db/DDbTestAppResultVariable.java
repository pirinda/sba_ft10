/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.qty.db;

import ft.lib.DLibRegistry;
import ft.mod.DModConsts;
import java.sql.ResultSet;
import java.sql.SQLException;
import sba.lib.DLibConsts;
import sba.lib.db.DDbConsts;
import sba.lib.db.DDbRegistryUser;
import sba.lib.grid.DGridRow;
import sba.lib.grid.cell.DGridCellNumber;
import sba.lib.gui.DGuiSession;

/**
 *
 * @author Sergio Flores
 */
public class DDbTestAppResultVariable extends DDbRegistryUser implements DGridRow, DGridCellNumber, DLibRegistry {

    protected int mnPkJobId;
    protected int mnPkAppId;
    protected int mnPkResultId;
    protected int mnPkVariableId;
    protected double mdValue;

    protected DDbVariable moRegVariable;
    
    protected DDbTestAppResult moParentTestAppResult;

    public DDbTestAppResultVariable() {
        super(DModConsts.Q_APP_RES_VAR);
        initRegistry();
    }

    private void readRegMembers(final DGuiSession session, final boolean update) {
        moRegVariable = (DDbVariable) session.readRegistry(DModConsts.QU_VAR, new int[] { mnPkVariableId });
    }
    
    public void setPkJobId(int n) { mnPkJobId = n; }
    public void setPkAppId(int n) { mnPkAppId = n; }
    public void setPkResultId(int n) { mnPkResultId = n; }
    public void setPkVariableId(int n) { mnPkVariableId = n; }
    public void setValue(double d) { mdValue = d; }

    public int getPkJobId() { return mnPkJobId; }
    public int getPkAppId() { return mnPkAppId; }
    public int getPkResultId() { return mnPkResultId; }
    public int getPkVariableId() { return mnPkVariableId; }
    public double getValue() { return mdValue; }

    public void setRegVariable(DDbVariable o) { moRegVariable = o; }
    
    public DDbVariable getRegVariable() { return moRegVariable; }

    public void setParentTestAppResult(DDbTestAppResult o) { moParentTestAppResult = o; }
    
    public DDbTestAppResult getParentTestAppResult() { return moParentTestAppResult; }

    public void decrementResultId() {
        if (mnPkResultId > DLibConsts.UNDEFINED) {
            mnPkResultId--;
        }
    }
    
    public void incrementResultId() {
        mnPkResultId++;
    }
    
    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkJobId = pk[0];
        mnPkAppId = pk[1];
        mnPkResultId = pk[2];
        mnPkVariableId = pk[3];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkJobId, mnPkAppId, mnPkResultId, mnPkVariableId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkJobId = 0;
        mnPkAppId = 0;
        mnPkResultId = 0;
        mnPkVariableId = 0;
        mdValue = 0;
        
        moRegVariable = null;
        
        moParentTestAppResult = null;
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_job = " + mnPkJobId + " AND id_app = " + mnPkAppId + " AND id_res = " + mnPkResultId + " AND id_var = " + mnPkVariableId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_job = " + pk[0] + " AND id_app = " + pk[1] + " AND id_res = " + pk[2] + " AND id_var = " + pk[3] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            mnPkAppId = resultSet.getInt("id_app");
            mnPkResultId = resultSet.getInt("id_res");
            mnPkVariableId = resultSet.getInt("id_var");
            mdValue = resultSet.getDouble("val");

            // Read aswell embeeded registries:
            
            readRegMembers(session, false);

            // Finish registry reading:

            mbRegistryNew = false;
        }

        mnQueryResultId = DDbConsts.READ_OK;
    }

    @Override
    public void save(DGuiSession session) throws SQLException, Exception {
        initQueryMembers();
        mnQueryResultId = DDbConsts.SAVE_ERROR;

        compute(session);
        verifyRegistryNew(session);

        if (mbRegistryNew) {
            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkJobId + ", " + 
                    mnPkAppId + ", " + 
                    mnPkResultId + ", " + 
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
    public DDbTestAppResultVariable clone() throws CloneNotSupportedException {
        DDbTestAppResultVariable registry = new DDbTestAppResultVariable();

        registry.setPkJobId(this.getPkJobId());
        registry.setPkAppId(this.getPkAppId());
        registry.setPkResultId(this.getPkResultId());
        registry.setPkVariableId(this.getPkVariableId());
        registry.setValue(this.getValue());
        
        registry.setRegVariable(this.getRegVariable() == null ? null : this.getRegVariable().clone());
        
        registry.setParentTestAppResult(moParentTestAppResult); // same parent object, not needed to be cloned!
        
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
                value = moParentTestAppResult == null || moParentTestAppResult.getParentTestApp() == null  || moParentTestAppResult.getParentTestApp().getRegTest() == null ? 0 : moParentTestAppResult.getParentTestApp().getRegTest().getPkTestId();
                break;
            case 1:
                value = mnPkResultId;
                break;
            case 2:
                value = moRegVariable.getName();
                break;
            case 3:
                value = mdValue;
                break;
            case 4:
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
            case 2:
                break;
            case 3:
                mdValue = (Double) value;
                break;
            case 4:
                break;
            default:
        }
    }

    @Override
    public int getDecimals() {
        return moRegVariable.getDecimals();
    }
    
    @Override
    public void compute(final DGuiSession session) {
        readRegMembers(session, true);
    }
}
