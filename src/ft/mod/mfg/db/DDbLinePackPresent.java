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
public class DDbLinePackPresent extends DDbRegistryUser {

    protected int mnPkLinePackId;
    protected int mnPkPresentId;

    public DDbLinePackPresent() {
        super(DModConsts.MU_LIN_PCK_PRS);
        initRegistry();
    }

    public void setPkLinePackId(int n) { mnPkLinePackId = n; }
    public void setPkPresentId(int n) { mnPkPresentId = n; }

    public int getPkLinePackId() { return mnPkLinePackId; }
    public int getPkPresentId() { return mnPkPresentId; }

    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkLinePackId = pk[0];
        mnPkPresentId = pk[1];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkLinePackId, mnPkPresentId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkLinePackId = 0;
        mnPkPresentId = 0;
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_lin_pck = " + mnPkLinePackId + " AND id_prs = " + mnPkPresentId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_lin_pck = " + pk[0] + " AND id_prs = " + pk[1] + " ";
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
            mnPkLinePackId = resultSet.getInt("id_lin_pck");
            mnPkPresentId = resultSet.getInt("id_prs");

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
                    mnPkLinePackId + ", " + 
                    mnPkPresentId + " " + 
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
    public DDbLinePackPresent clone() throws CloneNotSupportedException {
        DDbLinePackPresent registry = new DDbLinePackPresent();

        registry.setPkLinePackId(this.getPkLinePackId());
        registry.setPkPresentId(this.getPkPresentId());

        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
}
