/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.mfg.db;

import ft.mod.DModConsts;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import sba.gui.util.DUtilConsts;
import sba.lib.DLibUtils;
import sba.lib.db.DDbConsts;
import sba.lib.db.DDbRegistryUser;
import sba.lib.gui.DGuiSession;

/**
 *
 * @author Sergio Flores
 */
public class DDbJob extends DDbRegistryUser {

    protected int mnPkJobId;
    protected int mnNumber;
    protected Date mtDate;
    protected String msLot;
    protected Date mtJobTimeStart;
    protected Date mtJobTimeEnd;
    /*
    protected boolean mbDeleted;
    protected boolean mbSystem;
    */
    protected int mnFkJobTypeId;
    protected int mnFkJobStatusId;
    protected int mnFkItemTypeId;
    protected int mnFkItemId;
    protected int mnFkUnitId;
    /*
    protected int mnFkUserInsertId;
    protected int mnFkUserUpdateId;
    protected Date mtTsUserInsert;
    protected Date mtTsUserUpdate;
    */
    
    protected ArrayList<DDbJobLinePrep> maChildPreps;
    protected ArrayList<DDbJobLinePack> maChildPacks;

    public DDbJob() {
        super(DModConsts.M_JOB);
        maChildPreps = new ArrayList<>();
        maChildPacks = new ArrayList<>();
        initRegistry();
    }

    public void setPkJobId(int n) { mnPkJobId = n; }
    public void setNumber(int n) { mnNumber = n; }
    public void setDate(Date t) { mtDate = t; }
    public void setLot(String s) { msLot = s; }
    public void setJobStart(Date t) { mtJobTimeStart = t; }
    public void setJobEnd(Date t) { mtJobTimeEnd = t; }
    public void setDeleted(boolean b) { mbDeleted = b; }
    public void setSystem(boolean b) { mbSystem = b; }
    public void setFkJobTypeId(int n) { mnFkJobTypeId = n; }
    public void setFkJobStatusId(int n) { mnFkJobStatusId = n; }
    public void setFkItemTypeId(int n) { mnFkItemTypeId = n; }
    public void setFkItemId(int n) { mnFkItemId = n; }
    public void setFkUnitId(int n) { mnFkUnitId = n; }
    public void setFkUserInsertId(int n) { mnFkUserInsertId = n; }
    public void setFkUserUpdateId(int n) { mnFkUserUpdateId = n; }
    public void setTsUserInsert(Date t) { mtTsUserInsert = t; }
    public void setTsUserUpdate(Date t) { mtTsUserUpdate = t; }

    public int getPkJobId() { return mnPkJobId; }
    public int getNumber() { return mnNumber; }
    public Date getDate() { return mtDate; }
    public String getLot() { return msLot; }
    public Date getJobStart() { return mtJobTimeStart; }
    public Date getJobEnd() { return mtJobTimeEnd; }
    public boolean isDeleted() { return mbDeleted; }
    public boolean isSystem() { return mbSystem; }
    public int getFkJobTypeId() { return mnFkJobTypeId; }
    public int getFkJobStatusId() { return mnFkJobStatusId; }
    public int getFkItemTypeId() { return mnFkItemTypeId; }
    public int getFkItemId() { return mnFkItemId; }
    public int getFkUnitId() { return mnFkUnitId; }
    public int getFkUserInsertId() { return mnFkUserInsertId; }
    public int getFkUserUpdateId() { return mnFkUserUpdateId; }
    public Date getTsUserInsert() { return mtTsUserInsert; }
    public Date getTsUserUpdate() { return mtTsUserUpdate; }

    public ArrayList<DDbJobLinePrep> getChildPreps() { return maChildPreps; }
    public ArrayList<DDbJobLinePack> getChildPacks() { return maChildPacks; }

    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkJobId = pk[0];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkJobId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkJobId = 0;
        mnNumber = 0;
        mtDate = null;
        msLot = "";
        mtJobTimeStart = null;
        mtJobTimeEnd = null;
        mbDeleted = false;
        mbSystem = false;
        mnFkJobTypeId = 0;
        mnFkJobStatusId = 0;
        mnFkItemTypeId = 0;
        mnFkItemId = 0;
        mnFkUnitId = 0;
        mnFkUserInsertId = 0;
        mnFkUserUpdateId = 0;
        mtTsUserInsert = null;
        mtTsUserUpdate = null;
        
        maChildPreps.clear();
        maChildPacks.clear();
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_job = " + mnPkJobId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_job = " + pk[0] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkJobId = 0;

        msSql = "SELECT COALESCE(MAX(id_job), 0) + 1 FROM " + getSqlTable();
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkJobId = resultSet.getInt(1);
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
            mnNumber = resultSet.getInt("num");
            mtDate = resultSet.getDate("dat");
            msLot = resultSet.getString("lot");
            mtJobTimeStart = resultSet.getTimestamp("job_sta");
            mtJobTimeEnd = resultSet.getTimestamp("job_end");
            mbDeleted = resultSet.getBoolean("b_del");
            mbSystem = resultSet.getBoolean("b_sys");
            mnFkJobTypeId = resultSet.getInt("fk_job_tp");
            mnFkJobStatusId = resultSet.getInt("fk_job_st");
            mnFkItemTypeId = resultSet.getInt("fk_itm_tp");
            mnFkItemId = resultSet.getInt("fk_itm");
            mnFkUnitId = resultSet.getInt("fk_uom");
            mnFkUserInsertId = resultSet.getInt("fk_usr_ins");
            mnFkUserUpdateId = resultSet.getInt("fk_usr_upd");
            mtTsUserInsert = resultSet.getTimestamp("ts_usr_ins");
            mtTsUserUpdate = resultSet.getTimestamp("ts_usr_upd");

            // Read aswell child registries:

            statement = session.getStatement().getConnection().createStatement();
/*XXX
            msSql = "SELECT id_prp FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB_PRP) + " " + getSqlWhere();
            resultSet = statement.executeQuery(msSql);
            while (resultSet.next()) {
                DDbJobLinePrep child = new DDbJobLinePrep();
                child.read(session, new int[] { mnPkJobId, resultSet.getInt(1) });
                maChildPreps.add(child);
            }

            msSql = "SELECT id_pck FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB_PCK) + " " + getSqlWhere();
            resultSet = statement.executeQuery(msSql);
            while (resultSet.next()) {
                DDbJobLinePack child = new DDbJobLinePack();
                child.read(session, new int[] { mnPkJobId, resultSet.getInt(1) });
                maChildPacks.add(child);
            }
*/
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
            mbDeleted = false;
            mbSystem = false;
            mnFkUserInsertId = session.getUser().getPkUserId();
            mnFkUserUpdateId = DUtilConsts.USR_NA_ID;

            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkJobId + ", " + 
                    mnNumber + ", " + 
                    "'" + DLibUtils.DbmsDateFormatDate.format(mtDate) + "', " + 
                    "'" + msLot + "', " + 
                    "NOW()" + ", " + 
                    "NOW()" + ", " + 
                    (mbDeleted ? 1 : 0) + ", " + 
                    (mbSystem ? 1 : 0) + ", " + 
                    mnFkJobTypeId + ", " + 
                    mnFkJobStatusId + ", " + 
                    mnFkItemTypeId + ", " + 
                    mnFkItemId + ", " + 
                    mnFkUnitId + ", " + 
                    mnFkUserInsertId + ", " + 
                    mnFkUserUpdateId + ", " + 
                    "NOW()" + ", " + 
                    "NOW()" + " " + 
                    ")";
        }
        else {
            mnFkUserUpdateId = session.getUser().getPkUserId();

            msSql = "UPDATE " + getSqlTable() + " SET " +
                    //"id_job = " + mnPkJobId + ", " +
                    "num = " + mnNumber + ", " +
                    "dat = '" + DLibUtils.DbmsDateFormatDate.format(mtDate) + "', " +
                    "lot = '" + msLot + "', " +
                    "job_sta = " + "NOW()" + ", " +
                    "job_end = " + "NOW()" + ", " +
                    "b_del = " + (mbDeleted ? 1 : 0) + ", " +
                    "b_sys = " + (mbSystem ? 1 : 0) + ", " +
                    "fk_job_tp = " + mnFkJobTypeId + ", " +
                    "fk_job_st = " + mnFkJobStatusId + ", " +
                    "fk_itm_tp = " + mnFkItemTypeId + ", " +
                    "fk_itm = " + mnFkItemId + ", " +
                    "fk_uom = " + mnFkUnitId + ", " +
                    //"fk_usr_ins = " + mnFkUserInsertId + ", " +
                    "fk_usr_upd = " + mnFkUserUpdateId + ", " +
                    //"ts_usr_ins = " + "NOW()" + ", " +
                    "ts_usr_upd = " + "NOW()" + " " +
                    getSqlWhere();
        }

        // Save aswell child registries:
/*XXX
        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB_PRP_MFG) + " " + getSqlWhere();
        session.getStatement().execute(msSql);

        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB_PRP_CON) + " " + getSqlWhere();
        session.getStatement().execute(msSql);

        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB_PRP_REQ) + " " + getSqlWhere();
        session.getStatement().execute(msSql);

        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB_PRP) + " " + getSqlWhere();
        session.getStatement().execute(msSql);

        for (DDbJobLinePrep child : maChildPreps) {
            child.setPkJobId(mnPkJobId);
            child.setRegistryNew(true);
            child.save(session);
        }

        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB_PCK_MFG) + " " + getSqlWhere();
        session.getStatement().execute(msSql);

        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB_PCK_CON) + " " + getSqlWhere();
        session.getStatement().execute(msSql);

        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB_PCK_REQ) + " " + getSqlWhere();
        session.getStatement().execute(msSql);

        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB_PCK) + " " + getSqlWhere();
        session.getStatement().execute(msSql);
*/
        for (DDbJobLinePack child : maChildPacks) {
            child.setPkJobId(mnPkJobId);
            child.setRegistryNew(true);
            child.save(session);
        }

        // Finish registry updating:

        session.getStatement().execute(msSql);
        mbRegistryNew = false;
        mnQueryResultId = DDbConsts.SAVE_OK;
    }

    @Override
    public DDbJob clone() throws CloneNotSupportedException {
        DDbJob registry = new DDbJob();

        registry.setPkJobId(this.getPkJobId());
        registry.setNumber(this.getNumber());
        registry.setDate(this.getDate());
        registry.setLot(this.getLot());
        registry.setJobStart(this.getJobStart());
        registry.setJobEnd(this.getJobEnd());
        registry.setDeleted(this.isDeleted());
        registry.setSystem(this.isSystem());
        registry.setFkJobTypeId(this.getFkJobTypeId());
        registry.setFkJobStatusId(this.getFkJobStatusId());
        registry.setFkItemTypeId(this.getFkItemTypeId());
        registry.setFkItemId(this.getFkItemId());
        registry.setFkUnitId(this.getFkUnitId());
        registry.setFkUserInsertId(this.getFkUserInsertId());
        registry.setFkUserUpdateId(this.getFkUserUpdateId());
        registry.setTsUserInsert(this.getTsUserInsert());
        registry.setTsUserUpdate(this.getTsUserUpdate());

        for (DDbJobLinePrep child : maChildPreps) {
            registry.getChildPreps().add(child.clone());
        }

        for (DDbJobLinePack child : maChildPacks) {
            registry.getChildPacks().add(child.clone());
        }
        
        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
}
