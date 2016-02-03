/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.cfg.db;

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
public class DDbUserModuleAccess extends DDbRegistryUser {

    protected int mnPkUserId;
    protected int mnPkModuleId;
    protected int mnPkAccessTypeId;

    public DDbUserModuleAccess() {
        super(DModConsts.CU_USR_MOD);
        initRegistry();
    }

    public void setPkUserId(int n) { mnPkUserId = n; }
    public void setPkModuleId(int n) { mnPkModuleId = n; }
    public void setPkAccessTypeId(int n) { mnPkAccessTypeId = n; }

    public int getPkUserId() { return mnPkUserId; }
    public int getPkModuleId() { return mnPkModuleId; }
    public int getPkAccessTypeId() { return mnPkAccessTypeId; }

    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkUserId = pk[0];
        mnPkModuleId = pk[1];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkUserId, mnPkModuleId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkUserId = 0;
        mnPkModuleId = 0;
        mnPkAccessTypeId = 0;
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_usr = " + mnPkUserId+ " AND id_mod = " + mnPkModuleId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_usr = " + pk[0] + " AND id_mod = " + pk[1] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet.");
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
            mnPkUserId = resultSet.getInt("id_usr");
            mnPkModuleId = resultSet.getInt("id_mod");
            mnPkAccessTypeId = resultSet.getInt("fk_acs_tp");

            mbRegistryNew = false;
        }

        mnQueryResultId = DDbConsts.READ_OK;
    }

    @Override
    public void save(DGuiSession session) throws SQLException, Exception {
        initQueryMembers();
        mnQueryResultId = DDbConsts.SAVE_ERROR;
        
        if (mbRegistryNew) {
            verifyRegistryNew(session);
        }

        if (mbRegistryNew) {
            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkUserId + ", " + 
                    mnPkModuleId + ", " + 
                    mnPkAccessTypeId + " " + 
                    ")";
        }
        else {
            msSql = "UPDATE " + getSqlTable() + " SET " +
                    //"id_usr = " + mnPkUserId + ", " +
                    //"id_mod = " + mnPkModuleId + ", " +
                    "fk_acs_tp = " + mnPkAccessTypeId + " " +
                    getSqlWhere();
        }

        session.getStatement().execute(msSql);
        mbRegistryNew = false;

        mnQueryResultId = DDbConsts.SAVE_OK;
    }

    @Override
    public DDbUserModuleAccess clone() throws CloneNotSupportedException {
        DDbUserModuleAccess registry = new DDbUserModuleAccess();

        registry.setPkUserId(this.getPkUserId());
        registry.setPkModuleId(this.getPkModuleId());
        registry.setPkAccessTypeId(this.getPkAccessTypeId());

        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
}
