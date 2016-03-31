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
    protected Date mtTsStart_n;
    protected Date mtTsEnd_n;
    protected double mdLoads;
    protected double mdQuantityFormula;
    protected double mdQuantity_r;
    protected double mdMassFormula;
    protected double mdMass_r;
    protected String msLot;
    protected double mdPackingFactor;
    /*
    protected boolean mbDeleted;
    protected boolean mbSystem;
    */
    protected int mnFkJobTypeId;
    protected int mnFkJobStatusId;
    protected int mnFkDepartId;
    protected int mnFkLineId;
    protected int mnFkJobId_n;
    protected int mnFkFormulaId;
    protected int mnFkFormulaTypeId;
    protected int mnFkItemId;
    protected int mnFkItemTypeId;
    protected int mnFkUnitId;
    protected int mnFkPresentId;
    /*
    protected int mnFkUserInsertId;
    protected int mnFkUserUpdateId;
    protected Date mtTsUserInsert;
    protected Date mtTsUserUpdate;
    */
    
    protected DDbFormula moRegFormula;
    
    protected ArrayList<DDbJobReqment> maChildReqments;
    protected ArrayList<DDbJobConsump> maChildConsumps;
    protected ArrayList<DDbJobMfgProd> maChildMfgProds;

    public DDbJob() {
        super(DModConsts.M_JOB);
        maChildReqments = new ArrayList<>();
        maChildConsumps = new ArrayList<>();
        maChildMfgProds = new ArrayList<>();
        initRegistry();
    }

    private void readRegMembers(final DGuiSession session, final boolean update) {
        moRegFormula = (DDbFormula) session.readRegistry(DModConsts.MU_FRM, new int[] { mnFkFormulaId });
        
        if (update) {
            mnFkFormulaTypeId = moRegFormula.getFkFormulaTypeId();
            mnFkItemId = moRegFormula.getFkItemId();
            mnFkItemTypeId = moRegFormula.getFkItemTypeId();
            mnFkUnitId = moRegFormula.getFkUnitId();
            mnFkPresentId = moRegFormula.getFkPresentId();
        }
    }
    
    public void setPkJobId(int n) { mnPkJobId = n; }
    public void setNumber(int n) { mnNumber = n; }
    public void setDate(Date t) { mtDate = t; }
    public void setTsStart_n(Date t) { mtTsStart_n = t; }
    public void setTsEnd_n(Date t) { mtTsEnd_n = t; }
    public void setLoads(double d) { mdLoads = d; }
    public void setQuantityFormula(double d) { mdQuantityFormula = d; }
    public void setQuantity_r(double d) { mdQuantity_r = d; }
    public void setMassFormula(double d) { mdMassFormula = d; }
    public void setMass_r(double d) { mdMass_r = d; }
    public void setLot(String s) { msLot = s; }
    public void setPackingFactor(double d) { mdPackingFactor = d; }
    public void setDeleted(boolean b) { mbDeleted = b; }
    public void setSystem(boolean b) { mbSystem = b; }
    public void setFkJobTypeId(int n) { mnFkJobTypeId = n; }
    public void setFkJobStatusId(int n) { mnFkJobStatusId = n; }
    public void setFkDepartId(int n) { mnFkDepartId = n; }
    public void setFkLineId(int n) { mnFkLineId = n; }
    public void setFkJobId_n(int n) { mnFkJobId_n = n; }
    public void setFkFormulaId(int n) { mnFkFormulaId = n; }
    public void setFkFormulaTypeId(int n) { mnFkFormulaTypeId = n; }
    public void setFkItemId(int n) { mnFkItemId = n; }
    public void setFkItemTypeId(int n) { mnFkItemTypeId = n; }
    public void setFkUnitId(int n) { mnFkUnitId = n; }
    public void setFkPresentId(int n) { mnFkPresentId = n; }
    public void setFkUserInsertId(int n) { mnFkUserInsertId = n; }
    public void setFkUserUpdateId(int n) { mnFkUserUpdateId = n; }
    public void setTsUserInsert(Date t) { mtTsUserInsert = t; }
    public void setTsUserUpdate(Date t) { mtTsUserUpdate = t; }

    public int getPkJobId() { return mnPkJobId; }
    public int getNumber() { return mnNumber; }
    public Date getDate() { return mtDate; }
    public Date getTsStart_n() { return mtTsStart_n; }
    public Date getTsEnd_n() { return mtTsEnd_n; }
    public double getLoads() { return mdLoads; }
    public double getQuantityFormula() { return mdQuantityFormula; }
    public double getQuantity_r() { return mdQuantity_r; }
    public double getMassFormula() { return mdMassFormula; }
    public double getMass_r() { return mdMass_r; }
    public String getLot() { return msLot; }
    public double getPackingFactor() { return mdPackingFactor; }
    public boolean isDeleted() { return mbDeleted; }
    public boolean isSystem() { return mbSystem; }
    public int getFkJobTypeId() { return mnFkJobTypeId; }
    public int getFkJobStatusId() { return mnFkJobStatusId; }
    public int getFkDepartId() { return mnFkDepartId; }
    public int getFkLineId() { return mnFkLineId; }
    public int getFkJobId_n() { return mnFkJobId_n; }
    public int getFkFormulaId() { return mnFkFormulaId; }
    public int getFkFormulaTypeId() { return mnFkFormulaTypeId; }
    public int getFkItemId() { return mnFkItemId; }
    public int getFkItemTypeId() { return mnFkItemTypeId; }
    public int getFkUnitId() { return mnFkUnitId; }
    public int getFkPresentId() { return mnFkPresentId; }
    public int getFkUserInsertId() { return mnFkUserInsertId; }
    public int getFkUserUpdateId() { return mnFkUserUpdateId; }
    public Date getTsUserInsert() { return mtTsUserInsert; }
    public Date getTsUserUpdate() { return mtTsUserUpdate; }

    public void setRegFormula(DDbFormula o) { moRegFormula = o; }
    
    public DDbFormula getRegFormula() { return moRegFormula; }
    
    public ArrayList<DDbJobReqment> getChildReqemnts() { return maChildReqments; }
    public ArrayList<DDbJobConsump> getChildConsumps() { return maChildConsumps; }
    public ArrayList<DDbJobMfgProd> getChildMfgProds() { return maChildMfgProds; }

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
        mtTsStart_n = null;
        mtTsEnd_n = null;
        mdLoads = 0;
        mdQuantityFormula = 0;
        mdQuantity_r = 0;
        mdMassFormula = 0;
        mdMass_r = 0;
        msLot = "";
        mdPackingFactor = 0;
        mbDeleted = false;
        mbSystem = false;
        mnFkJobTypeId = 0;
        mnFkJobStatusId = 0;
        mnFkDepartId = 0;
        mnFkLineId = 0;
        mnFkJobId_n = 0;
        mnFkFormulaId = 0;
        mnFkFormulaTypeId = 0;
        mnFkItemId = 0;
        mnFkItemTypeId = 0;
        mnFkUnitId = 0;
        mnFkPresentId = 0;
        mnFkUserInsertId = 0;
        mnFkUserUpdateId = 0;
        mtTsUserInsert = null;
        mtTsUserUpdate = null;
        
        moRegFormula = null;
        
        maChildReqments.clear();
        maChildConsumps.clear();
        maChildMfgProds.clear();
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
            mtTsStart_n = resultSet.getTimestamp("ts_sta_n");
            mtTsEnd_n = resultSet.getTimestamp("ts_end_n");
            mdLoads = resultSet.getDouble("lds");
            mdQuantityFormula = resultSet.getDouble("qty_frm");
            mdQuantity_r = resultSet.getDouble("qty_r");
            mdMassFormula = resultSet.getDouble("mass_unt");
            mdMass_r = resultSet.getDouble("mass_r");
            msLot = resultSet.getString("lot");
            mdPackingFactor = resultSet.getDouble("pack_fac");
            mbDeleted = resultSet.getBoolean("b_del");
            mbSystem = resultSet.getBoolean("b_sys");
            mnFkJobTypeId = resultSet.getInt("fk_job_tp");
            mnFkJobStatusId = resultSet.getInt("fk_job_st");
            mnFkDepartId = resultSet.getInt("fk_dep");
            mnFkLineId = resultSet.getInt("fk_lin");
            mnFkJobId_n = resultSet.getInt("fk_job_n");
            mnFkFormulaId = resultSet.getInt("fk_frm");
            mnFkFormulaTypeId = resultSet.getInt("fk_frm_tp");
            mnFkItemId = resultSet.getInt("fk_itm");
            mnFkItemTypeId = resultSet.getInt("fk_itm_tp");
            mnFkUnitId = resultSet.getInt("fk_uom");
            mnFkPresentId = resultSet.getInt("fk_pre");
            mnFkUserInsertId = resultSet.getInt("fk_usr_ins");
            mnFkUserUpdateId = resultSet.getInt("fk_usr_upd");
            mtTsUserInsert = resultSet.getTimestamp("ts_usr_ins");
            mtTsUserUpdate = resultSet.getTimestamp("ts_usr_upd");

            readRegMembers(session, false);

            // Read aswell child registries:

            statement = session.getStatement().getConnection().createStatement();

            msSql = "SELECT id_req FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB_REQ) + " " + getSqlWhere() +
                    "ORDER BY id_req ";
            resultSet = statement.executeQuery(msSql);
            while (resultSet.next()) {
                DDbJobReqment child = new DDbJobReqment();
                child.read(session, new int[] { mnPkJobId, resultSet.getInt(1) });
                maChildReqments.add(child);
            }

            msSql = "SELECT id_con FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB_CON) + " " + getSqlWhere() +
                    "ORDER BY id_con ";
            resultSet = statement.executeQuery(msSql);
            while (resultSet.next()) {
                DDbJobConsump child = new DDbJobConsump();
                child.read(session, new int[] { mnPkJobId, resultSet.getInt(1) });
                maChildConsumps.add(child);
            }

            msSql = "SELECT id_mfg FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB_MFG) + " " + getSqlWhere() +
                    "ORDER BY id_mfg ";
            resultSet = statement.executeQuery(msSql);
            while (resultSet.next()) {
                DDbJobMfgProd child = new DDbJobMfgProd();
                child.read(session, new int[] { mnPkJobId, resultSet.getInt(1) });
                maChildMfgProds.add(child);
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
        
        compute(session);

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
                    "NOW()" + ", " + 
                    "NOW()" + ", " + 
                    mdLoads + ", " + 
                    mdQuantityFormula + ", " + 
                    mdQuantity_r + ", " + 
                    mdMassFormula + ", " + 
                    mdMass_r + ", " + 
                    "'" + msLot + "', " + 
                    mdPackingFactor + ", " + 
                    (mbDeleted ? 1 : 0) + ", " + 
                    (mbSystem ? 1 : 0) + ", " + 
                    mnFkJobTypeId + ", " + 
                    mnFkJobStatusId + ", " + 
                    mnFkDepartId + ", " + 
                    mnFkLineId + ", " + 
                    mnFkJobId_n + ", " + 
                    mnFkFormulaId + ", " + 
                    mnFkFormulaTypeId + ", " + 
                    mnFkItemId + ", " + 
                    mnFkItemTypeId + ", " + 
                    mnFkUnitId + ", " + 
                    mnFkPresentId + ", " + 
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
                    "ts_sta_n = " + "NOW()" + ", " +
                    "ts_end_n = " + "NOW()" + ", " +
                    "lds = " + mdLoads + ", " +
                    "qty_frm = " + mdQuantityFormula + ", " +
                    "qty_r = " + mdQuantity_r + ", " +
                    "mass_unt = " + mdMassFormula + ", " +
                    "mass_r = " + mdMass_r + ", " +
                    "lot = '" + msLot + "', " +
                    "pack_fac = " + mdPackingFactor + ", " +
                    "b_del = " + (mbDeleted ? 1 : 0) + ", " +
                    "b_sys = " + (mbSystem ? 1 : 0) + ", " +
                    "fk_job_tp = " + mnFkJobTypeId + ", " +
                    "fk_job_st = " + mnFkJobStatusId + ", " +
                    "fk_dep = " + mnFkDepartId + ", " +
                    "fk_lin = " + mnFkLineId + ", " +
                    "fk_job_n = " + mnFkJobId_n + ", " +
                    "fk_frm = " + mnFkFormulaId + ", " +
                    "fk_frm_tp = " + mnFkFormulaTypeId + ", " +
                    "fk_itm = " + mnFkItemId + ", " +
                    "fk_itm_tp = " + mnFkItemTypeId + ", " +
                    "fk_uom = " + mnFkUnitId + ", " +
                    "fk_pre = " + mnFkPresentId + ", " +
                    //"fk_usr_ins = " + mnFkUserInsertId + ", " +
                    "fk_usr_upd = " + mnFkUserUpdateId + ", " +
                    //"ts_usr_ins = " + "NOW()" + ", " +
                    "ts_usr_upd = " + "NOW()" + " " +
                    getSqlWhere();
        }

        // Save aswell child registries:
        
        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB_REQ) + " " + getSqlWhere();
        session.getStatement().execute(msSql);

        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB_CON) + " " + getSqlWhere();
        session.getStatement().execute(msSql);

        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB_MFG) + " " + getSqlWhere();
        session.getStatement().execute(msSql);

        for (DDbJobReqment child : maChildReqments) {
            child.setPkJobId(mnPkJobId);
            child.setRegistryNew(true);
            child.save(session);
        }

        for (DDbJobConsump child : maChildConsumps) {
            child.setPkJobId(mnPkJobId);
            child.setFkReqmentJobId(mnPkJobId);
            child.setRegistryNew(true);
            child.save(session);
        }

        for (DDbJobMfgProd child : maChildMfgProds) {
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
        registry.setTsStart_n(this.getTsStart_n());
        registry.setTsEnd_n(this.getTsEnd_n());
        registry.setLoads(this.getLoads());
        registry.setQuantityFormula(this.getQuantityFormula());
        registry.setQuantity_r(this.getQuantity_r());
        registry.setMassFormula(this.getMassFormula());
        registry.setMass_r(this.getMass_r());
        registry.setLot(this.getLot());
        registry.setPackingFactor(this.getPackingFactor());
        registry.setDeleted(this.isDeleted());
        registry.setSystem(this.isSystem());
        registry.setFkJobTypeId(this.getFkJobTypeId());
        registry.setFkJobStatusId(this.getFkJobStatusId());
        registry.setFkDepartId(this.getFkDepartId());
        registry.setFkLineId(this.getFkLineId());
        registry.setFkJobId_n(this.getFkJobId_n());
        registry.setFkFormulaId(this.getFkFormulaId());
        registry.setFkFormulaTypeId(this.getFkFormulaTypeId());
        registry.setFkItemId(this.getFkItemId());
        registry.setFkItemTypeId(this.getFkItemTypeId());
        registry.setFkUnitId(this.getFkUnitId());
        registry.setFkPresentId(this.getFkPresentId());
        registry.setFkUserInsertId(this.getFkUserInsertId());
        registry.setFkUserUpdateId(this.getFkUserUpdateId());
        registry.setTsUserInsert(this.getTsUserInsert());
        registry.setTsUserUpdate(this.getTsUserUpdate());

        for (DDbJobReqment child : maChildReqments) {
            registry.getChildReqemnts().add(child.clone());
        }

        for (DDbJobConsump child : maChildConsumps) {
            registry.getChildConsumps().add(child.clone());
        }

        for (DDbJobMfgProd child : maChildMfgProds) {
            registry.getChildMfgProds().add(child.clone());
        }

        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
    
    public void compute(final DGuiSession session) {
        readRegMembers(session, true);
        
        mdMass_r = 0;
        
        for (DDbJobConsump child : maChildConsumps) {
            mdMass_r = DLibUtils.round(mdMass_r + child.getMass_r(), DLibUtils.getDecimalFormatQuantity().getMaximumFractionDigits());
        }
        
        mdMassFormula = DLibUtils.round(moRegFormula.getMass_r() * mdLoads, DLibUtils.getDecimalFormatQuantity().getMaximumFractionDigits());
        mdPackingFactor = DLibUtils.round(mdMassFormula == 0 ? 0 : mdMass_r / mdMassFormula, DLibUtils.getDecimalFormatQuantity().getMaximumFractionDigits());
    }
}
