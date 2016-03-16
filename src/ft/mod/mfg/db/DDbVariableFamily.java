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
import sba.lib.gui.DGuiSession;

/**
 *
 * @author Sergio Flores
 */
public class DDbVariableFamily extends DDbRegistryUser {

    protected int mnPkVariableId;
    protected int mnPkFamilyId;
    
    public DDbVariableFamily() {
        super(DModConsts.MU_VAR_FAM);
        initRegistry();
    }

    public void setPkVariableId(int n) { mnPkVariableId = n; }
    public void setPkFamilyId(int n) { mnPkFamilyId = n; }

    public int getPkVariableId() { return mnPkVariableId; }
    public int getPkFamilyId() { return mnPkFamilyId; }

    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkVariableId = pk[0];
        mnPkFamilyId = pk[1];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkVariableId, mnPkFamilyId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkVariableId = 0;
        mnPkFamilyId = 0;
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_var = " + mnPkVariableId + " AND " +
                "id_fam = " + mnPkFamilyId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_var = " + pk[0] + " AND " +
                "id_fam = " + pk[1] + " ";
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
            mnPkVariableId = resultSet.getInt("id_var");
            mnPkFamilyId = resultSet.getInt("id_fam");

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
                    mnPkVariableId + ", " + 
                    mnPkFamilyId + " " + 
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
    public DDbVariableFamily clone() throws CloneNotSupportedException {
        DDbVariableFamily registry = new DDbVariableFamily();

        registry.setPkVariableId(this.getPkVariableId());
        registry.setPkFamilyId(this.getPkFamilyId());

        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
}
