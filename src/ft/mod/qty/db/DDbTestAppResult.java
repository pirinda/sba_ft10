/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.qty.db;

import ft.mod.DModConsts;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import sba.lib.DLibConsts;
import sba.lib.db.DDbConsts;
import sba.lib.db.DDbRegistryUser;
import sba.lib.gui.DGuiSession;

/**
 *
 * @author Sergio Flores
 */
public class DDbTestAppResult extends DDbRegistryUser {

    protected int mnPkJobId;
    protected int mnPkAppId;
    protected int mnPkResultId;
    
    protected ArrayList<DDbTestAppResultVariable> maChildVariables;

    protected DDbTestApp moParentTestApp;

    public DDbTestAppResult() {
        super(DModConsts.Q_APP_RES);
        maChildVariables = new ArrayList<>();
        initRegistry();
    }

    public void setPkJobId(int n) { mnPkJobId = n; }
    public void setPkAppId(int n) { mnPkAppId = n; }
    public void setPkResultId(int n) { mnPkResultId = n; }

    public int getPkJobId() { return mnPkJobId; }
    public int getPkAppId() { return mnPkAppId; }
    public int getPkResultId() { return mnPkResultId; }

    public ArrayList<DDbTestAppResultVariable> getChildVariables() { return maChildVariables; }
    
    public void setParentTestApp(DDbTestApp o) { moParentTestApp = o; }
    
    public DDbTestApp getParentTestApp() { return moParentTestApp; }
    
    public void decrementResultId() {
        if (mnPkResultId > DLibConsts.UNDEFINED) {
            for (DDbTestAppResultVariable child : maChildVariables) {
                if (child.getPkResultId() == mnPkResultId) {
                    child.decrementResultId();
                }
            }
            mnPkResultId--;
        }
    }

    public void incrementResultId() {
        for (DDbTestAppResultVariable child : maChildVariables) {
            if (child.getPkResultId() == mnPkResultId) {
                child.incrementResultId();
            }
        }
        mnPkResultId++;
    }

    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkJobId = pk[0];
        mnPkAppId = pk[1];
        mnPkResultId = pk[2];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkJobId, mnPkAppId, mnPkResultId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkJobId = 0;
        mnPkAppId = 0;
        mnPkResultId = 0;
        
        maChildVariables.clear();
        
        moParentTestApp = null;
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_job = " + mnPkJobId + " AND id_app = " + mnPkAppId + " AND id_res = " + mnPkResultId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_job = " + pk[0] + " AND id_app = " + pk[1] + " AND id_res = " + pk[2] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkResultId = 0;

        msSql = "SELECT COALESCE(MAX(id_res), 0) + 1 FROM " + getSqlTable() + " " +
                "WHERE id_job = " + mnPkJobId + " AND id_app = " + mnPkAppId + " ";
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkResultId = resultSet.getInt(1);
        }
    }

    @Override
    public void read(DGuiSession session, int[] pk) throws SQLException, Exception {
        Statement statement = null;
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
            
            // Read aswell child registries:
            
            statement = session.getStatement().getConnection().createStatement();
            
            msSql = "SELECT id_var FROM " + DModConsts.TablesMap.get(DModConsts.Q_APP_RES_VAR) + " " + getSqlWhere() +
                    "ORDER BY id_var ";
            resultSet = statement.executeQuery(msSql);
            while (resultSet.next()) {
                DDbTestAppResultVariable child = new DDbTestAppResultVariable();
                child.read(session, new int[] { mnPkJobId, mnPkAppId, mnPkResultId, resultSet.getInt(1) });
                child.setParentTestAppResult(this);
                maChildVariables.add(child);
            }
            
            // Finish registry reading:

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
                    mnPkAppId + ", " + 
                    mnPkResultId + " " + 
                    ")";
        }
        else {
            throw new Exception(DDbConsts.ERR_MSG_REG_NON_UPDATABLE);
        }

        session.getStatement().execute(msSql);
        
        // Save aswell child registries:
        
        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.Q_APP_RES_VAR) + " " + getSqlWhere();
        session.getStatement().execute(msSql);
        
        for (DDbTestAppResultVariable child : maChildVariables) {
            child.setRegistryNew(true);
            child.setPkJobId(mnPkJobId);
            child.setPkAppId(mnPkAppId);
            child.setPkResultId(mnPkResultId);
            child.save(session);
        }
        // Finish saving registry:
        
        mbRegistryNew = false;
        mnQueryResultId = DDbConsts.SAVE_OK;
    }

    @Override
    public DDbTestAppResult clone() throws CloneNotSupportedException {
        DDbTestAppResult registry = new DDbTestAppResult();

        registry.setPkJobId(this.getPkJobId());
        registry.setPkAppId(this.getPkAppId());
        registry.setPkResultId(this.getPkResultId());
        
        for (DDbTestAppResultVariable child : maChildVariables) {
            registry.getChildVariables().add(child.clone());
        }
        
        registry.setParentTestApp(moParentTestApp); // same parent object, not needed to be cloned!
        
        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
}
