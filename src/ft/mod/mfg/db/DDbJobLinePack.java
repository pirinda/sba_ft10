/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.mfg.db;

import ft.mod.DModConsts;
import ft.mod.cfg.db.DDbItem;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import sba.lib.db.DDbConsts;
import sba.lib.db.DDbRegistry;
import sba.lib.db.DDbRegistryUser;
import sba.lib.gui.DGuiSession;

/**
 *
 * @author Sergio Flores
 */
public class DDbJobLinePack extends DDbRegistryUser implements DRowJobProgMask {

    protected int mnPkJobId;
    protected int mnPkLinePackId;
    protected int mnPkPackId;
    protected double mdLoads;
    protected double mdQuantity;
    protected double mdMassUnit;
    protected double mdMass_r;
    protected int mnFkItemTypeId;
    protected int mnFkItemId;
    protected int mnFkUnitId;
    protected int mnFkPresentId;
    protected int mnFkFormulaTypeId;
    protected int mnFkFormulaId;
    
    protected ArrayList<DDbJobLinePackRqmt> maChildRqmts;
    protected ArrayList<DDbJobLinePackCons> maChildConss;
    protected ArrayList<DDbJobLinePackMfg> maChildMfgs;
    
    protected String msXtaLinePackCode;
    protected String msXtaItemCode;
    protected String msXtaItemName;
    protected String msXtaUnitCode;
    protected String msXtaUnitName;
    protected String msXtaFormulaName;

    public DDbJobLinePack() {
        super(DModConsts.M_JOB_PCK);
        maChildRqmts = new ArrayList<>();
        maChildConss = new ArrayList<>();
        maChildMfgs = new ArrayList<>();
        initRegistry();
    }

    public void setPkJobId(int n) { mnPkJobId = n; }
    public void setPkLinePackId(int n) { mnPkLinePackId = n; }
    public void setPkPackId(int n) { mnPkPackId = n; }
    public void setLoads(double d) { mdLoads = d; }
    public void setQuantity(double d) { mdQuantity = d; }
    public void setMassUnit(double d) { mdMassUnit = d; }
    public void setMass_r(double d) { mdMass_r = d; }
    public void setFkItemTypeId(int n) { mnFkItemTypeId = n; }
    public void setFkItemId(int n) { mnFkItemId = n; }
    public void setFkUnitId(int n) { mnFkUnitId = n; }
    public void setFkPresentId(int n) { mnFkPresentId = n; }
    public void setFkFormulaTypeId(int n) { mnFkFormulaTypeId = n; }
    public void setFkFormulaId(int n) { mnFkFormulaId = n; }

    public int getPkJobId() { return mnPkJobId; }
    public int getPkLinePackId() { return mnPkLinePackId; }
    public int getPkPackId() { return mnPkPackId; }
    public double getLoads() { return mdLoads; }
    public double getQuantity() { return mdQuantity; }
    public double getMassUnit() { return mdMassUnit; }
    public double getMass_r() { return mdMass_r; }
    public int getFkItemTypeId() { return mnFkItemTypeId; }
    public int getFkItemId() { return mnFkItemId; }
    public int getFkUnitId() { return mnFkUnitId; }
    public int getFkPresentId() { return mnFkPresentId; }
    public int getFkFormulaTypeId() { return mnFkFormulaTypeId; }
    public int getFkFormulaId() { return mnFkFormulaId; }

    public ArrayList<DDbJobLinePackRqmt> getChildRqmts() { return maChildRqmts; }
    public ArrayList<DDbJobLinePackCons> getChildConss() { return maChildConss; }
    public ArrayList<DDbJobLinePackMfg> getChildMfgs() { return maChildMfgs; }
    
    public void setXtaLinePackCode(String s) { msXtaLinePackCode = s; }
    public void setXtaItemCode(String s) { msXtaItemCode = s; }
    public void setXtaItemName(String s) { msXtaItemName = s; }
    public void setXtaUnitCode(String s) { msXtaUnitCode = s; }
    public void setXtaUnitName(String s) { msXtaUnitName = s; }
    public void setXtaFormulaName(String s) { msXtaFormulaName = s; }
    
    public String getXtaLinePackCode() { return msXtaLinePackCode; }
    public String getXtaItemCode() { return msXtaItemCode; }
    public String getXtaItemName() { return msXtaItemName; }
    public String getXtaUnitCode() { return msXtaUnitCode; }
    public String getXtaUnitName() { return msXtaUnitName; }
    public String getXtaFormulaName() { return msXtaFormulaName; }
    
    public ArrayList<DDbJobLinePackRqmt> createRqmts(final DGuiSession session) {
        DDbItem item = null;
        DDbJobLinePackRqmt rqmt = null;
        ArrayList<DDbJobLinePackRqmt> rqmts = new ArrayList<>();
        DDbFormula formula = (DDbFormula) session.readRegistry(DModConsts.MU_FRM, new int[] { mnFkFormulaId });
        
        for (DDbFormulaComp comp : formula.maChildComps) {
            item = (DDbItem) session.readRegistry(DModConsts.CU_ITM, new int[] { comp.getFkItemId() });
            
            rqmt = new DDbJobLinePackRqmt();
            rqmt.setPkJobId(mnPkJobId);
            rqmt.setPkLinePackId(mnPkLinePackId);
            rqmt.setPkPackId(mnPkPackId);
            //rqmt.setPkRqmtId(...);
            rqmt.setQuantity(comp.getQuantity() * mdLoads);
            rqmt.setMassUnit(item.getMassUnit());
            rqmt.setMass_r(item.getMassUnit() * comp.getQuantity() * mdLoads);
            rqmt.setStatisticsReference(comp.getStatisticsReference());
            rqmt.setStandard(comp.isStandard());
            rqmt.setFkItemTypeId(comp.getFkItemTypeId());
            rqmt.setFkItemId(comp.getFkItemId());
            rqmt.setFkUnitId(comp.getFkUnitId());
            rqmt.setXtaLinePackCode(msXtaLinePackCode);
            rqmt.setXtaItemTypeCode(item.getXtaItemTypeCode());
            rqmt.setXtaItemCode(item.getCode());
            rqmt.setXtaItemName(item.getName());
            rqmt.setXtaUnitCode(item.getXtaUnitCode());
            rqmt.setXtaUnitName(item.getXtaUnitName());
            rqmts.add(rqmt);
        }
        
        return rqmts;
    }
    
    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkJobId = pk[0];
        mnPkLinePackId = pk[1];
        mnPkPackId = pk[2];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkJobId, mnPkLinePackId, mnPkPackId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkJobId = 0;
        mnPkLinePackId = 0;
        mnPkPackId = 0;
        mdLoads = 0;
        mdQuantity = 0;
        mdMassUnit = 0;
        mdMass_r = 0;
        mnFkItemTypeId = 0;
        mnFkItemId = 0;
        mnFkUnitId = 0;
        mnFkPresentId = 0;
        mnFkFormulaTypeId = 0;
        mnFkFormulaId = 0;
        
        maChildRqmts.clear();
        maChildConss.clear();
        maChildMfgs.clear();
        
        msXtaLinePackCode = "";
        msXtaItemCode = "";
        msXtaItemName = "";
        msXtaUnitCode = "";
        msXtaUnitName = "";
        msXtaFormulaName = "";
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_job = " + mnPkJobId + " AND id_lin_pck = " + mnPkLinePackId + " AND id_pck = " + mnPkPackId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_job = " + pk[0] + " AND id_lin_pck = " + pk[1] + " AND id_pck = " + pk[2] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkPackId = 0;

        msSql = "SELECT COALESCE(MAX(id_pck), 0) + 1 FROM " + getSqlTable() + " " +
                "WHERE id_job = " + mnPkJobId + " AND id_lin_pck = " + mnPkLinePackId + " ";
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkPackId = resultSet.getInt(1);
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
            mnPkLinePackId = resultSet.getInt("id_lin_pck");
            mnPkPackId = resultSet.getInt("id_pck");
            mdLoads = resultSet.getDouble("lds");
            mdQuantity = resultSet.getDouble("qty");
            mdMassUnit = resultSet.getDouble("mss_unt");
            mdMass_r = resultSet.getDouble("mss_r");
            mnFkItemTypeId = resultSet.getInt("fk_itm_tp");
            mnFkItemId = resultSet.getInt("fk_itm");
            mnFkUnitId = resultSet.getInt("fk_unt");
            mnFkPresentId = resultSet.getInt("fk_prs");
            mnFkFormulaTypeId = resultSet.getInt("fk_frm_tp");
            mnFkFormulaId = resultSet.getInt("fk_frm");

            // Read aswell child registries:

            statement = session.getStatement().getConnection().createStatement();

            msSql = "SELECT id_req FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB_PRP_REQ) + " " + getSqlWhere();
            resultSet = statement.executeQuery(msSql);
            while (resultSet.next()) {
                DDbJobLinePackRqmt child = new DDbJobLinePackRqmt();
                child.read(session, new int[] { mnPkJobId, resultSet.getInt(1) });
                maChildRqmts.add(child);
            }

            msSql = "SELECT id_con FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB_PRP_CON) + " " + getSqlWhere();
            resultSet = statement.executeQuery(msSql);
            while (resultSet.next()) {
                DDbJobLinePackCons child = new DDbJobLinePackCons();
                child.read(session, new int[] { mnPkJobId, resultSet.getInt(1) });
                maChildConss.add(child);
            }

            msSql = "SELECT id_mfg FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB_PRP_MFG) + " " + getSqlWhere();
            resultSet = statement.executeQuery(msSql);
            while (resultSet.next()) {
                DDbJobLinePackMfg child = new DDbJobLinePackMfg();
                child.read(session, new int[] { mnPkJobId, resultSet.getInt(1) });
                maChildMfgs.add(child);
            }

            // Read aswell extra data:
            
            msXtaLinePackCode = (String) session.readField(DModConsts.MU_LIN_PCK, new int[] { mnPkLinePackId }, DDbRegistry.FIELD_CODE);
            msXtaItemCode = (String) session.readField(DModConsts.CU_ITM, new int[] { mnFkItemId }, DDbRegistry.FIELD_CODE);
            msXtaItemName = (String) session.readField(DModConsts.CU_ITM, new int[] { mnFkItemId }, DDbRegistry.FIELD_NAME);
            msXtaUnitCode = (String) session.readField(DModConsts.CU_UNT, new int[] { mnFkUnitId }, DDbRegistry.FIELD_CODE);
            msXtaUnitName = (String) session.readField(DModConsts.CU_UNT, new int[] { mnFkUnitId }, DDbRegistry.FIELD_NAME);
            msXtaFormulaName = (String) session.readField(DModConsts.MU_FRM, new int[] { mnFkFormulaId }, DDbRegistry.FIELD_NAME);

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
                    mnPkLinePackId + ", " + 
                    mnPkPackId + ", " + 
                    mdLoads + ", " + 
                    mdQuantity + ", " + 
                    mdMassUnit + ", " + 
                    mdMass_r + ", " + 
                    mnFkItemTypeId + ", " + 
                    mnFkItemId + ", " + 
                    mnFkUnitId + ", " + 
                    mnFkPresentId + ", " + 
                    mnFkFormulaTypeId + ", " + 
                    mnFkFormulaId + " " + 
                    ")";
        }
        else {
            mnFkUserUpdateId = session.getUser().getPkUserId();

            msSql = "UPDATE " + getSqlTable() + " SET " +
                    //"id_job = " + mnPkJobId + ", " +
                    //"id_lin_pck = " + mnPkLinePackId + ", " +
                    //"id_pck = " + mnPkPackId + ", " +
                    "lds = " + mdLoads + ", " +
                    "qty = " + mdQuantity + ", " +
                    "mss_unt = " + mdMassUnit + ", " +
                    "mss_r = " + mdMass_r + ", " +
                    "fk_itm_tp = " + mnFkItemTypeId + ", " +
                    "fk_itm = " + mnFkItemId + ", " +
                    "fk_unt = " + mnFkUnitId + ", " +
                    "fk_prs = " + mnFkPresentId + ", " +
                    "fk_frm_tp = " + mnFkFormulaTypeId + ", " +
                    "fk_frm = " + mnFkFormulaId + " " +
                    getSqlWhere();
        }

        session.getStatement().execute(msSql);
        
        // Save aswell child registries:

        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB_PRP_REQ) + " " + getSqlWhere();
        session.getStatement().execute(msSql);

        for (DDbJobLinePackRqmt child : maChildRqmts) {
            child.setPkJobId(mnPkJobId);
            child.setPkLinePackId(mnPkLinePackId);
            child.setPkPackId(mnPkPackId);
            child.setRegistryNew(true);
            child.save(session);
        }

        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB_PRP_CON) + " " + getSqlWhere();
        session.getStatement().execute(msSql);

        for (DDbJobLinePackCons child : maChildConss) {
            child.setPkJobId(mnPkJobId);
            child.setPkLinePackId(mnPkLinePackId);
            child.setPkPackId(mnPkPackId);
            child.setRegistryNew(true);
            child.save(session);
        }

        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB_PRP_MFG) + " " + getSqlWhere();
        session.getStatement().execute(msSql);

        for (DDbJobLinePackMfg child : maChildMfgs) {
            child.setPkJobId(mnPkJobId);
            child.setPkLinePackId(mnPkLinePackId);
            child.setPkPackId(mnPkPackId);
            child.setRegistryNew(true);
            child.save(session);
        }

        // Finish registry updating:

        mbRegistryNew = false;
        mnQueryResultId = DDbConsts.SAVE_OK;
    }

    @Override
    public DDbJobLinePack clone() throws CloneNotSupportedException {
        DDbJobLinePack registry = new DDbJobLinePack();

        registry.setPkJobId(this.getPkJobId());
        registry.setPkLinePackId(this.getPkLinePackId());
        registry.setPkPackId(this.getPkPackId());
        registry.setLoads(this.getLoads());
        registry.setQuantity(this.getQuantity());
        registry.setMassUnit(this.getMassUnit());
        registry.setMass_r(this.getMass_r());
        registry.setFkItemTypeId(this.getFkItemTypeId());
        registry.setFkItemId(this.getFkItemId());
        registry.setFkUnitId(this.getFkUnitId());
        registry.setFkPresentId(this.getFkPresentId());
        registry.setFkFormulaTypeId(this.getFkFormulaTypeId());
        registry.setFkFormulaId(this.getFkFormulaId());

        for (DDbJobLinePackRqmt child : maChildRqmts) {
            registry.getChildRqmts().add(child.clone());
        }

        for (DDbJobLinePackCons child : maChildConss) {
            registry.getChildConss().add(child.clone());
        }

        for (DDbJobLinePackMfg child : maChildMfgs) {
            registry.getChildMfgs().add(child.clone());
        }
        
        registry.setXtaLinePackCode(this.getXtaLinePackCode());
        registry.setXtaItemCode(this.getXtaItemCode());
        registry.setXtaItemName(this.getXtaItemName());
        registry.setXtaUnitCode(this.getXtaUnitCode());
        registry.setXtaUnitName(this.getXtaUnitName());
        registry.setXtaFormulaName(this.getXtaFormulaName());
    
        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }

    @Override
    public String getLineCode() {
        return getXtaLinePackCode();
    }

    @Override
    public int getProg() {
        return getPkPackId();
    }

    @Override
    public String getItem() {
        return getXtaItemName();
    }

    @Override
    public String getFormula() {
        return getXtaFormulaName();
    }

    @Override
    public String getUnitCode() {
        return getXtaUnitCode();
    }

    @Override
    public double getDefaultVariable1() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
