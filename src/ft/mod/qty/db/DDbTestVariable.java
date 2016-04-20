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
import sba.lib.gui.DGuiSession;

/**
 *
 * @author Sergio Flores
 */
public class DDbTestVariable extends DDbRegistryUser {

    protected int mnPkTestId;
    protected int mnPkVariableId;
    
    public DDbTestVariable() {
        super(DModConsts.QU_TST_VAR);
        initRegistry();
    }

    public void setPkTestId(int n) { mnPkTestId = n; }
    public void setPkVariableId(int n) { mnPkVariableId = n; }

    public int getPkTestId() { return mnPkTestId; }
    public int getPkVariableId() { return mnPkVariableId; }

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

            mbRegistryNew = false;
        }

        mnQueryResultId = DDbConsts.READ_OK;
    }

    @Override
    public void save(DGuiSession session) throws SQLException, Exception {
        initQueryMembers();
        mnQueryResultId = DDbConsts.SAVE_ERROR;
        
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

        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
}
