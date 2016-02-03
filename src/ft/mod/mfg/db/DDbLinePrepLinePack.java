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
public class DDbLinePrepLinePack extends DDbRegistryUser {

    protected int mnPkLinePrepId;
    protected int mnPkLinePackId;

    public DDbLinePrepLinePack() {
        super(/*XXXDModConsts.MU_LIN_PRP_LIN_PCK*/0);
        initRegistry();
    }

    public void setPkLinePrepId(int n) { mnPkLinePrepId = n; }
    public void setPkLinePackId(int n) { mnPkLinePackId = n; }

    public int getPkLinePrepId() { return mnPkLinePrepId; }
    public int getPkLinePackId() { return mnPkLinePackId; }

    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkLinePrepId = pk[0];
        mnPkLinePackId = pk[1];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkLinePrepId, mnPkLinePackId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkLinePrepId = 0;
        mnPkLinePackId = 0;
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_lin_prp = " + mnPkLinePrepId + " AND id_lin_pck = " + mnPkLinePackId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_lin_prp = " + pk[0] + " AND id_lin_pck = " + pk[1] + " ";
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
            mnPkLinePrepId = resultSet.getInt("id_lin_prp");
            mnPkLinePackId = resultSet.getInt("id_lin_pck");

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
                    mnPkLinePrepId + ", " + 
                    mnPkLinePackId + " " + 
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
    public DDbLinePrepLinePack clone() throws CloneNotSupportedException {
        DDbLinePrepLinePack registry = new DDbLinePrepLinePack();

        registry.setPkLinePrepId(this.getPkLinePrepId());
        registry.setPkLinePackId(this.getPkLinePackId());

        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
}
