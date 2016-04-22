/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.qty.db;

import ft.lib.DLibRegistry;
import ft.mod.DModConsts;
import java.sql.ResultSet;
import java.sql.SQLException;
import sba.lib.db.DDbConsts;
import sba.lib.db.DDbRegistryUser;
import sba.lib.gui.DGuiSession;

/**
 *
 * @author Sergio Flores
 */
public class DDbTestVariable extends DDbRegistryUser implements DLibRegistry {

    protected int mnPkTestId;
    protected int mnPkVariableId;
    
    protected DDbVariable moRegVariable;

    public DDbTestVariable() {
        super(DModConsts.QU_TST_VAR);
        initRegistry();
    }

    private void readRegMembers(final DGuiSession session, final boolean update) {
        moRegVariable = (DDbVariable) session.readRegistry(DModConsts.QU_VAR, new int[] { mnPkVariableId });
    }
    
    public void setPkTestId(int n) { mnPkTestId = n; }
    public void setPkVariableId(int n) { mnPkVariableId = n; }

    public int getPkTestId() { return mnPkTestId; }
    public int getPkVariableId() { return mnPkVariableId; }

    public void setRegVariable(DDbVariable o) { moRegVariable = o; }
    
    public DDbVariable getRegVariable() { return moRegVariable; }

    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkTestId = pk[0];
        mnPkVariableId = pk[1];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkTestId, mnPkVariableId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkTestId = 0;
        mnPkVariableId = 0;
        
        moRegVariable = null;
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_tst = " + mnPkTestId + " AND " +
                "id_var = " + mnPkVariableId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_tst = " + pk[0] + " AND " +
                "id_var = " + pk[1] + " ";
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
            mnPkTestId = resultSet.getInt("id_tst");
            mnPkVariableId = resultSet.getInt("id_var");

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
        verifyRegistryNew(session);

        if (mbRegistryNew) {
            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkTestId + ", " + 
                    mnPkVariableId + " " + 
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
    public DDbTestVariable clone() throws CloneNotSupportedException {
        DDbTestVariable registry = new DDbTestVariable();

        registry.setPkTestId(this.getPkTestId());
        registry.setPkVariableId(this.getPkVariableId());

        registry.setRegVariable(this.getRegVariable() == null ? null : this.getRegVariable().clone());
        
        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
    
    @Override
    public void compute(final DGuiSession session) {
        readRegMembers(session, true);
    }
}
