/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.mfg.db;

import ft.lib.DLibRegistry;
import ft.mod.DModConsts;
import ft.mod.DModSysConsts;
import ft.mod.qty.db.DDbTestApp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import sba.gui.util.DUtilConsts;
import sba.lib.DLibConsts;
import sba.lib.DLibUtils;
import sba.lib.db.DDbConsts;
import sba.lib.db.DDbRegistryUser;
import sba.lib.gui.DGuiSession;

/**
 *
 * @author Sergio Flores
 */
public class DDbJob extends DDbRegistryUser implements DLibRegistry {

    protected int mnPkJobId;
    protected int mnNumber;
    protected Date mtDate;
    protected Date mtTsStart_n;
    protected Date mtTsEnd_n;
    protected double mdFormulaQuantity;
    protected double mdFormulaMass_r;
    protected double mdLoads;
    protected double mdJobQuantity_r;
    protected double mdMfgProdQuantity_r;
    protected double mdReqmentMass_r;
    protected double mdConsumpMass_r;
    protected double mdMfgProdMass_r;
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
    protected ArrayList<DDbJobVariable> maChildVariables;
    protected ArrayList<DDbTestApp> maRegTestApps;
    protected ArrayList<DDbTestApp> maRegFormerTestApps;

    public DDbJob() {
        super(DModConsts.M_JOB);
        maChildReqments = new ArrayList<>();
        maChildConsumps = new ArrayList<>();
        maChildMfgProds = new ArrayList<>();
        maChildVariables = new ArrayList<>();
        maRegTestApps = new ArrayList<>();
        maRegFormerTestApps = new ArrayList<>();
        initRegistry();
    }

    private void readRegMembers(final DGuiSession session, final boolean update) {
        moRegFormula = (DDbFormula) session.readRegistry(DModConsts.MU_FRM, new int[] { mnFkFormulaId });
        
        if (update) {
            mdFormulaQuantity = moRegFormula.getQuantity();
            mdFormulaMass_r = moRegFormula.getMass_r();
            
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
    public void setFormulaQuantity(double d) { mdFormulaQuantity = d; }
    public void setFormulaMass_r(double d) { mdFormulaMass_r = d; }
    public void setLoads(double d) { mdLoads = d; }
    public void setJobQuantity_r(double d) { mdJobQuantity_r = d; }
    public void setMfgProdQuantity_r(double d) { mdMfgProdQuantity_r = d; }
    public void setReqmentMass_r(double d) { mdReqmentMass_r = d; }
    public void setConsumpMass_r(double d) { mdConsumpMass_r = d; }
    public void setMfgProdMass_r(double d) { mdMfgProdMass_r = d; }
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
    public double getFormulaQuantity() { return mdFormulaQuantity; }
    public double getFormulaMass_r() { return mdFormulaMass_r; }
    public double getLoads() { return mdLoads; }
    public double getJobQuantity_r() { return mdJobQuantity_r; }
    public double getMfgProdQuantity_r() { return mdMfgProdQuantity_r; }
    public double getReqmentMass_r() { return mdReqmentMass_r; }
    public double getConsumpMass_r() { return mdConsumpMass_r; }
    public double getMfgProdMass_r() { return mdMfgProdMass_r; }
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
    public ArrayList<DDbJobVariable> getChildVariables() { return maChildVariables; }
    public ArrayList<DDbTestApp> getRegTestApps() { return maRegTestApps; }
    public ArrayList<DDbTestApp> getRegFormerTestApps() { return maRegFormerTestApps; }

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
        mdFormulaQuantity = 0;
        mdFormulaMass_r = 0;
        mdLoads = 0;
        mdJobQuantity_r = 0;
        mdMfgProdQuantity_r = 0;
        mdReqmentMass_r = 0;
        mdConsumpMass_r = 0;
        mdMfgProdMass_r = 0;
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
        maChildVariables.clear();
        maRegTestApps.clear();
        maRegFormerTestApps.clear();
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
            mdFormulaQuantity = resultSet.getDouble("frm_qty");
            mdFormulaMass_r = resultSet.getDouble("frm_mass_r");
            mdLoads = resultSet.getDouble("lds");
            mdJobQuantity_r = resultSet.getDouble("job_qty_r");
            mdMfgProdQuantity_r = resultSet.getDouble("mfg_qty_r");
            mdReqmentMass_r = resultSet.getDouble("req_mass_r");
            mdConsumpMass_r = resultSet.getDouble("con_mass_r");
            mdMfgProdMass_r = resultSet.getDouble("mfg_mass_r");
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

            msSql = "SELECT id_var FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB_VAR) + " " + getSqlWhere() +
                    "ORDER BY id_var ";
            resultSet = statement.executeQuery(msSql);
            while (resultSet.next()) {
                DDbJobVariable child = new DDbJobVariable();
                child.read(session, new int[] { mnPkJobId, resultSet.getInt(1) });
                maChildVariables.add(child);
            }
            
            // Read aswell embeeded registries:

            msSql = "SELECT id_app FROM " + DModConsts.TablesMap.get(DModConsts.Q_APP) + " WHERE fk_job_n = " + mnPkJobId + " AND b_del = 0 " +
                    "ORDER BY id_app ";
            resultSet = statement.executeQuery(msSql);
            while (resultSet.next()) {
                DDbTestApp registry = new DDbTestApp();
                registry.read(session, new int[] { resultSet.getInt(1) });
                maRegTestApps.add(registry);
                maRegFormerTestApps.add(registry);
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

        if (mnNumber == 0) {
            computeNumber(session);
        }
        
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
                    mdFormulaQuantity + ", " + 
                    mdFormulaMass_r + ", " + 
                    mdLoads + ", " + 
                    mdJobQuantity_r + ", " + 
                    mdMfgProdQuantity_r + ", " + 
                    mdReqmentMass_r + ", " + 
                    mdConsumpMass_r + ", " + 
                    mdMfgProdMass_r + ", " + 
                    "'" + msLot + "', " + 
                    mdPackingFactor + ", " + 
                    (mbDeleted ? 1 : 0) + ", " + 
                    (mbSystem ? 1 : 0) + ", " + 
                    mnFkJobTypeId + ", " + 
                    mnFkJobStatusId + ", " + 
                    mnFkDepartId + ", " + 
                    mnFkLineId + ", " + 
                    (mnFkJobId_n == DLibConsts.UNDEFINED ? "NULL" : "" + mnFkJobId_n) + ", " + 
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
                    "frm_qty = " + mdFormulaQuantity + ", " +
                    "frm_mass_r = " + mdFormulaMass_r + ", " +
                    "lds = " + mdLoads + ", " +
                    "job_qty_r = " + mdJobQuantity_r + ", " +
                    "mfg_qty_r = " + mdMfgProdQuantity_r + ", " +
                    "req_mass_r = " + mdReqmentMass_r + ", " +
                    "con_mass_r = " + mdConsumpMass_r + ", " +
                    "mfg_mass_r = " + mdMfgProdMass_r + ", " +
                    "lot = '" + msLot + "', " +
                    "pack_fac = " + mdPackingFactor + ", " +
                    "b_del = " + (mbDeleted ? 1 : 0) + ", " +
                    "b_sys = " + (mbSystem ? 1 : 0) + ", " +
                    "fk_job_tp = " + mnFkJobTypeId + ", " +
                    "fk_job_st = " + mnFkJobStatusId + ", " +
                    "fk_dep = " + mnFkDepartId + ", " +
                    "fk_lin = " + mnFkLineId + ", " +
                    "fk_job_n = " + (mnFkJobId_n == DLibConsts.UNDEFINED ? "NULL" : "" + mnFkJobId_n) + ", " +
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

        session.getStatement().execute(msSql);
        
        // Save aswell child registries:
        
        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB_VAR) + " " + getSqlWhere();
        session.getStatement().execute(msSql);

        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB_MFG) + " " + getSqlWhere();
        session.getStatement().execute(msSql);

        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB_CON) + " " + getSqlWhere();
        session.getStatement().execute(msSql);

        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB_REQ) + " " + getSqlWhere();
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

        for (DDbJobVariable child : maChildVariables) {
            child.setPkJobId(mnPkJobId);
            child.setRegistryNew(true);
            child.save(session);
        }

        // Save aswell embeeded registries:
        
        for (DDbTestApp registry : maRegFormerTestApps) {
            registry.delete(session); // delete all former test applications
        }
        
        for (DDbTestApp registry : maRegTestApps) {
            registry.setFkJobId_n(mnPkJobId);
            registry.save(session);
        }
        
        // Finish registry updating:

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
        registry.setFormulaQuantity(this.getFormulaQuantity());
        registry.setFormulaMass_r(this.getFormulaMass_r());
        registry.setLoads(this.getLoads());
        registry.setJobQuantity_r(this.getJobQuantity_r());
        registry.setMfgProdQuantity_r(this.getMfgProdQuantity_r());
        registry.setReqmentMass_r(this.getReqmentMass_r());
        registry.setConsumpMass_r(this.getConsumpMass_r());
        registry.setMfgProdMass_r(this.getMfgProdMass_r());
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

        registry.setRegFormula(this.getRegFormula() == null ? null : this.getRegFormula().clone());
        
        for (DDbJobReqment child : maChildReqments) {
            registry.getChildReqemnts().add(child.clone());
        }

        for (DDbJobConsump child : maChildConsumps) {
            registry.getChildConsumps().add(child.clone());
        }

        for (DDbJobMfgProd child : maChildMfgProds) {
            registry.getChildMfgProds().add(child.clone());
        }

        for (DDbJobVariable child : maChildVariables) {
            registry.getChildVariables().add(child.clone());
        }

        for (DDbTestApp app : maRegTestApps) {
            registry.getRegTestApps().add(app.clone());
        }

        for (DDbTestApp app : maRegFormerTestApps) {
            registry.getRegFormerTestApps().add(app.clone());
        }

        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
    
    @Override
    public boolean canDisable(final DGuiSession session) throws SQLException, Exception {
        initQueryMembers();
        return !mbSystem && mbDisableable && DLibUtils.belongsTo(mnFkJobStatusId, new int[] { DModSysConsts.MS_JOB_ST_PEN, DModSysConsts.MS_JOB_ST_CAN });
    }
    
    @Override
    public void disable(final DGuiSession session) throws SQLException, Exception {
        initQueryMembers();
        mnQueryResultId = DDbConsts.SAVE_ERROR;
        
        switch (mnFkJobStatusId) {
            case DModSysConsts.MS_JOB_ST_PEN:
                mnFkJobStatusId = DModSysConsts.MS_JOB_ST_CAN; // when pending set to cancelled
                break;
            case DModSysConsts.MS_JOB_ST_CAN:
                mnFkJobStatusId = DModSysConsts.MS_JOB_ST_PEN; // when cancelled set to pending
                break;
            default:
                throw new Exception(DLibConsts.ERR_MSG_OPTION_UNKNOWN);
        }

        mnFkUserUpdateId = session.getUser().getPkUserId();

        msSql = "UPDATE " + getSqlTable() + " SET " +
                "fk_job_st = " + mnFkJobStatusId + ", " +
                "fk_usr_upd = " + mnFkUserUpdateId + ", " +
                "ts_usr_upd = NOW() " +
                getSqlWhere();

        session.getStatement().execute(msSql);
        mnQueryResultId = DDbConsts.SAVE_OK;
    }
    
    @Override
    public void compute(final DGuiSession session) {
        readRegMembers(session, true);
        
        mdJobQuantity_r = DLibUtils.round(mdFormulaQuantity * mdLoads, DLibUtils.getDecimalFormatQuantity().getMaximumFractionDigits());
        mdReqmentMass_r = DLibUtils.round(mdFormulaMass_r * mdLoads, DLibUtils.getDecimalFormatQuantity().getMaximumFractionDigits());
        
        mdConsumpMass_r = 0;
        
        for (DDbJobConsump child : maChildConsumps) {
            mdConsumpMass_r = DLibUtils.round(mdConsumpMass_r + child.getMass_r(), DLibUtils.getDecimalFormatQuantity().getMaximumFractionDigits());
        }
        
        mdMfgProdQuantity_r = 0;
        mdMfgProdMass_r = 0;
        
        for (DDbJobMfgProd child : maChildMfgProds) {
            mdMfgProdQuantity_r = DLibUtils.round(mdMfgProdQuantity_r + child.getQuantity(), DLibUtils.getDecimalFormatQuantity().getMaximumFractionDigits());
            mdMfgProdMass_r = DLibUtils.round(mdMfgProdMass_r + child.getMass_r(), DLibUtils.getDecimalFormatQuantity().getMaximumFractionDigits());
        }
        
        mdPackingFactor = DLibUtils.round(mdReqmentMass_r == 0 ? 0 : mdConsumpMass_r / mdReqmentMass_r, DLibUtils.getDecimalFormatQuantity().getMaximumFractionDigits());
    }
    
    public void computeNumber(final DGuiSession session) throws Exception {
        String sql = "";
        ResultSet resultSet = null;
        
        sql = "SELECT COALESCE(MAX(num), 0) + 1 "
                + "FROM " + getSqlTable() + " "
                + "WHERE b_del = 0 ";
        resultSet = session.getStatement().executeQuery(sql);
        if (!resultSet.next()) {
            throw new Exception(DDbConsts.ERR_MSG_REG_NOT_FOUND);
        }
        else {
            mnNumber = resultSet.getInt(1);
        }
    }
}
