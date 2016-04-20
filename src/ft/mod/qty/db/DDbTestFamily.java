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
public class DDbTestFamily extends DDbRegistryUser {

    protected int mnPkTestId;
    protected int mnPkFamilyId;
    protected int mnResults;
    
    public DDbTestFamily() {
        super(DModConsts.QU_TST_FAM);
        initRegistry();
    }

    public void setPkTestId(int n) { mnPkTestId = n; }
    public void setPkFamilyId(int n) { mnPkFamilyId = n; }
    public void setResults(int n) { mnResults = n; }

    public int getPkTestId() { return mnPkTestId; }
    public int getPkFamilyId() { return mnPkFamilyId; }
    public int getResults() { return mnResults; }

    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkTestId = pk[0];
        mnPkFamilyId = pk[1];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkTestId, mnPkFamilyId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkTestId = 0;
        mnPkFamilyId = 0;
        mnResults = 0;
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_tst = " + mnPkTestId + " AND " +
                "id_fam = " + mnPkFamilyId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_tst = " + pk[0] + " AND " +
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
            mnPkTestId = resultSet.getInt("id_tst");
            mnPkFamilyId = resultSet.getInt("id_fam");
            mnResults = resultSet.getInt("res");

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
                    mnPkFamilyId + ", " + 
                    mnResults + " " + 
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
    public DDbTestFamily clone() throws CloneNotSupportedException {
        DDbTestFamily registry = new DDbTestFamily();

        registry.setPkTestId(this.getPkTestId());
        registry.setPkFamilyId(this.getPkFamilyId());
        registry.setResults(this.getResults());

        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
}
