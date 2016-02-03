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
import sba.lib.db.DDbRegistryUser;
import sba.lib.gui.DGuiSession;

/**
 *
 * @author Sergio Flores
 */
public class DDbJobLinePrep extends DDbRegistryUser implements DRowJobProgMask {

    protected int mnPkJobId;
    protected int mnPkLinePrepId;
    protected int mnPkPrepId;
    protected double mdLoads;
    protected double mdDefaultVar1;
    protected double mdQuantity;
    protected double mdMassUnit;
    protected double mdMass_r;
    protected double mdPackagingFactor;
    protected boolean mbQuantityByVar1;
    protected int mnFkItemTypeId;
    protected int mnFkItemId;
    protected int mnFkUnitId;
    protected int mnFkFormulaTypeId;
    protected int mnFkFormulaId;
    
    protected ArrayList<DDbJobLinePrepRqmt> maChildRqmts;
    protected ArrayList<DDbJobLinePrepCons> maChildConss;
    protected ArrayList<DDbJobLinePrepMfg> maChildMfgs;

    protected String msXtaLinePrepCode;
    protected String msXtaLinePrepName;
    protected String msXtaItemCode;
    protected String msXtaItemName;
    protected String msXtaUnitCode;
    protected String msXtaUnitName;
    protected String msXtaFormulaName;
    
    public DDbJobLinePrep() {
        super(/*XXXDModConsts.M_JOB_PRP*/0);
        maChildRqmts = new ArrayList<>();
        maChildConss = new ArrayList<>();
        maChildMfgs = new ArrayList<>();
        initRegistry();
    }

    public void setPkJobId(int n) { mnPkJobId = n; }
    public void setPkLinePrepId(int n) { mnPkLinePrepId = n; }
    public void setPkPrepId(int n) { mnPkPrepId = n; }
    public void setLoads(double d) { mdLoads = d; }
    public void setDefaultVar1(double d) { mdDefaultVar1 = d; }
    public void setQuantity(double d) { mdQuantity = d; }
    public void setMassUnit(double d) { mdMassUnit = d; }
    public void setMass_r(double d) { mdMass_r = d; }
    public void setPackagingFactor(double d) { mdPackagingFactor = d; }
    public void setQuantityByVar1(boolean b) { mbQuantityByVar1 = b; }
    public void setFkItemTypeId(int n) { mnFkItemTypeId = n; }
    public void setFkItemId(int n) { mnFkItemId = n; }
    public void setFkUnitId(int n) { mnFkUnitId = n; }
    public void setFkFormulaTypeId(int n) { mnFkFormulaTypeId = n; }
    public void setFkFormulaId(int n) { mnFkFormulaId = n; }

    public int getPkJobId() { return mnPkJobId; }
    public int getPkLinePrepId() { return mnPkLinePrepId; }
    public int getPkPrepId() { return mnPkPrepId; }
    public double getLoads() { return mdLoads; }
    public double getDefaultVar1() { return mdDefaultVar1; }
    public double getQuantity() { return mdQuantity; }
    public double getMassUnit() { return mdMassUnit; }
    public double getMass_r() { return mdMass_r; }
    public double getPackagingFactor() { return mdPackagingFactor; }
    public boolean isQuantityByVar1() { return mbQuantityByVar1; }
    public int getFkItemTypeId() { return mnFkItemTypeId; }
    public int getFkItemId() { return mnFkItemId; }
    public int getFkUnitId() { return mnFkUnitId; }
    public int getFkFormulaTypeId() { return mnFkFormulaTypeId; }
    public int getFkFormulaId() { return mnFkFormulaId; }

    public ArrayList<DDbJobLinePrepRqmt> getChildRqmts() { return maChildRqmts; }
    public ArrayList<DDbJobLinePrepCons> getChildConss() { return maChildConss; }
    public ArrayList<DDbJobLinePrepMfg> getChildMfgs() { return maChildMfgs; }

    public void setXtaLinePrepCode(String s) { msXtaLinePrepCode = s; }
    public void setXtaLinePrepName(String s) { msXtaLinePrepName = s; }
    public void setXtaItemCode(String s) { msXtaItemCode = s; }
    public void setXtaItemName(String s) { msXtaItemName = s; }
    public void setXtaUnitCode(String s) { msXtaUnitCode = s; }
    public void setXtaUnitName(String s) { msXtaUnitName = s; }
    public void setXtaFormulaName(String s) { msXtaFormulaName = s; }
    
    public String getXtaLinePrepCode() { return msXtaLinePrepCode; }
    public String getXtaLinePrepName() { return msXtaLinePrepName; }
    public String getXtaItemCode() { return msXtaItemCode; }
    public String getXtaItemName() { return msXtaItemName; }
    public String getXtaUnitCode() { return msXtaUnitCode; }
    public String getXtaUnitName() { return msXtaUnitName; }
    public String getXtaFormulaName() { return msXtaFormulaName; }
    
    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkJobId = pk[0];
        mnPkLinePrepId = pk[1];
        mnPkPrepId = pk[2];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkJobId, mnPkLinePrepId, mnPkPrepId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkJobId = 0;
        mnPkLinePrepId = 0;
        mnPkPrepId = 0;
        mdLoads = 0;
        mdDefaultVar1 = 0;
        mdQuantity = 0;
        mdMassUnit = 0;
        mdMass_r = 0;
        mdPackagingFactor = 0;
        mbQuantityByVar1 = false;
        mnFkItemTypeId = 0;
        mnFkItemId = 0;
        mnFkUnitId = 0;
        mnFkFormulaTypeId = 0;
        mnFkFormulaId = 0;
        
        maChildRqmts.clear();
        maChildConss.clear();
        maChildMfgs.clear();
        
        msXtaLinePrepCode = "";
        msXtaLinePrepName = "";
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
        return "WHERE id_job = " + mnPkJobId + " AND id_lin_prp = " + mnPkLinePrepId + " AND id_prp = " + mnPkPrepId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_job = " + pk[0] + " AND id_lin_prp = " + pk[1] + " AND id_prp = " + pk[2] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkPrepId = 0;

        msSql = "SELECT COALESCE(MAX(id_prp), 0) + 1 FROM " + getSqlTable() + " " +
                "WHERE id_job = " + mnPkJobId + " AND id_lin_prp = " + mnPkLinePrepId + " ";
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkPrepId = resultSet.getInt(1);
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
            mnPkLinePrepId = resultSet.getInt("id_lin_prp");
            mnPkPrepId = resultSet.getInt("id_prp");
            mdLoads = resultSet.getDouble("lds");
            mdDefaultVar1 = resultSet.getDouble("def_var_1");
            mdQuantity = resultSet.getDouble("qty");
            mdMassUnit = resultSet.getDouble("mss_unt");
            mdMass_r = resultSet.getDouble("mss_r");
            mdPackagingFactor = resultSet.getDouble("pck_fac");
            mbQuantityByVar1 = resultSet.getBoolean("b_qty_var_1");
            mnFkItemTypeId = resultSet.getInt("fk_itm_tp");
            mnFkItemId = resultSet.getInt("fk_itm");
            mnFkUnitId = resultSet.getInt("fk_unt");
            mnFkFormulaTypeId = resultSet.getInt("fk_frm_tp");
            mnFkFormulaId = resultSet.getInt("fk_frm");

            // Read aswell child registries:

            statement = session.getStatement().getConnection().createStatement();
/*XXX
            msSql = "SELECT id_req FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB_PRP_REQ) + " " + getSqlWhere();
            resultSet = statement.executeQuery(msSql);
            while (resultSet.next()) {
                DDbJobLinePrepRqmt child = new DDbJobLinePrepRqmt();
                child.read(session, new int[] { mnPkJobId, resultSet.getInt(1) });
                maChildRqmts.add(child);
            }

            msSql = "SELECT id_con FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB_PRP_CON) + " " + getSqlWhere();
            resultSet = statement.executeQuery(msSql);
            while (resultSet.next()) {
                DDbJobLinePrepCons child = new DDbJobLinePrepCons();
                child.read(session, new int[] { mnPkJobId, resultSet.getInt(1) });
                maChildConss.add(child);
            }

            msSql = "SELECT id_mfg FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB_PRP_MFG) + " " + getSqlWhere();
            resultSet = statement.executeQuery(msSql);
            while (resultSet.next()) {
                DDbJobLinePrepMfg child = new DDbJobLinePrepMfg();
                child.read(session, new int[] { mnPkJobId, resultSet.getInt(1) });
                maChildMfgs.add(child);
            }

            // Read aswell extra data:
            
            msXtaLinePrepCode = (String) session.readField(DModConsts.MU_LIN_PRP, new int[] { mnPkLinePrepId }, DDbRegistry.FIELD_CODE);
            msXtaLinePrepName = (String) session.readField(DModConsts.MU_LIN_PRP, new int[] { mnPkLinePrepId }, DDbRegistry.FIELD_NAME);
            msXtaItemCode = (String) session.readField(DModConsts.CU_ITM, new int[] { mnFkItemId }, DDbRegistry.FIELD_CODE);
            msXtaItemName = (String) session.readField(DModConsts.CU_ITM, new int[] { mnFkItemId }, DDbRegistry.FIELD_NAME);
            msXtaUnitCode = (String) session.readField(DModConsts.CU_UOM, new int[] { mnFkUnitId }, DDbRegistry.FIELD_CODE);
            msXtaUnitName = (String) session.readField(DModConsts.CU_UOM, new int[] { mnFkUnitId }, DDbRegistry.FIELD_NAME);
            msXtaFormulaName = (String) session.readField(DModConsts.MU_FRM, new int[] { mnFkFormulaId }, DDbRegistry.FIELD_NAME);
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
            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkJobId + ", " + 
                    mnPkLinePrepId + ", " + 
                    mnPkPrepId + ", " + 
                    mdLoads + ", " + 
                    mdDefaultVar1 + ", " + 
                    mdQuantity + ", " + 
                    mdMassUnit + ", " + 
                    mdMass_r + ", " + 
                    mdPackagingFactor + ", " + 
                    (mbQuantityByVar1 ? 1 : 0) + ", " + 
                    mnFkItemTypeId + ", " + 
                    mnFkItemId + ", " + 
                    mnFkUnitId + ", " + 
                    mnFkFormulaTypeId + ", " + 
                    mnFkFormulaId + " " + 
                    ")";
        }
        else {
            mnFkUserUpdateId = session.getUser().getPkUserId();

            msSql = "UPDATE " + getSqlTable() + " SET " +
                    //"id_job = " + mnPkJobId + ", " +
                    //"id_lin_prp = " + mnPkLinePrepId + ", " +
                    //"id_prp = " + mnPkPrepId + ", " +
                    "lds = " + mdLoads + ", " +
                    "def_var_1 = " + mdDefaultVar1 + ", " +
                    "qty = " + mdQuantity + ", " +
                    "mss_unt = " + mdMassUnit + ", " +
                    "mss_r = " + mdMass_r + ", " +
                    "pck_fac = " + mdPackagingFactor + ", " +
                    "b_qty_var_1 = " + (mbQuantityByVar1 ? 1 : 0) + ", " +
                    "fk_itm_tp = " + mnFkItemTypeId + ", " +
                    "fk_itm = " + mnFkItemId + ", " +
                    "fk_unt = " + mnFkUnitId + ", " +
                    "fk_frm_tp = " + mnFkFormulaTypeId + ", " +
                    "fk_frm = " + mnFkFormulaId + " " +
                    getSqlWhere();
        }

        session.getStatement().execute(msSql);
        
        // Save aswell child registries:
/*XXX
        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB_PRP_REQ) + " " + getSqlWhere();
        session.getStatement().execute(msSql);

        for (DDbJobLinePrepRqmt child : maChildRqmts) {
            child.setPkJobId(mnPkJobId);
            child.setPkLinePrepId(mnPkLinePrepId);
            child.setPkPrepId(mnPkPrepId);
            child.setRegistryNew(true);
            child.save(session);
        }

        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB_PRP_CON) + " " + getSqlWhere();
        session.getStatement().execute(msSql);

        for (DDbJobLinePrepCons child : maChildConss) {
            child.setPkJobId(mnPkJobId);
            child.setPkLinePrepId(mnPkLinePrepId);
            child.setPkPrepId(mnPkPrepId);
            child.setRegistryNew(true);
            child.save(session);
        }

        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB_PRP_MFG) + " " + getSqlWhere();
        session.getStatement().execute(msSql);

        for (DDbJobLinePrepMfg child : maChildMfgs) {
            child.setPkJobId(mnPkJobId);
            child.setPkLinePrepId(mnPkLinePrepId);
            child.setPkPrepId(mnPkPrepId);
            child.setRegistryNew(true);
            child.save(session);
        }
*/
        // Finish registry updating:

        mbRegistryNew = false;
        mnQueryResultId = DDbConsts.SAVE_OK;
    }

    @Override
    public DDbJobLinePrep clone() throws CloneNotSupportedException {
        DDbJobLinePrep registry = new DDbJobLinePrep();

        registry.setPkJobId(this.getPkJobId());
        registry.setPkLinePrepId(this.getPkLinePrepId());
        registry.setPkPrepId(this.getPkPrepId());
        registry.setLoads(this.getLoads());
        registry.setDefaultVar1(this.getDefaultVar1());
        registry.setQuantity(this.getQuantity());
        registry.setMassUnit(this.getMassUnit());
        registry.setMass_r(this.getMass_r());
        registry.setPackagingFactor(this.getPackagingFactor());
        registry.setQuantityByVar1(this.isQuantityByVar1());
        registry.setFkItemTypeId(this.getFkItemTypeId());
        registry.setFkItemId(this.getFkItemId());
        registry.setFkUnitId(this.getFkUnitId());
        registry.setFkFormulaTypeId(this.getFkFormulaTypeId());
        registry.setFkFormulaId(this.getFkFormulaId());

        for (DDbJobLinePrepRqmt child : maChildRqmts) {
            registry.getChildRqmts().add(child.clone());
        }

        for (DDbJobLinePrepCons child : maChildConss) {
            registry.getChildConss().add(child.clone());
        }

        for (DDbJobLinePrepMfg child : maChildMfgs) {
            registry.getChildMfgs().add(child.clone());
        }
        
        registry.setXtaLinePrepCode(this.getXtaLinePrepCode());
        registry.setXtaLinePrepName(this.getXtaLinePrepName());
        registry.setXtaItemCode(this.getXtaItemCode());
        registry.setXtaItemName(this.getXtaItemName());
        registry.setXtaUnitCode(this.getXtaUnitCode());
        registry.setXtaUnitName(this.getXtaUnitName());
        registry.setXtaFormulaName(this.getXtaFormulaName());
        
        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }

    @Override
    public int getLineId() {
        return getPkLinePrepId();
    }

    @Override
    public String getLineCode() {
        return getXtaLinePrepCode();
    }

    @Override
    public String getLineName() {
        return getXtaLinePrepName();
    }
    
    @Override
    public int getProductId() {
        return getFkItemId();
    }

    @Override
    public String getProduct() {
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
    public ArrayList<DRowJobRqmtMask> createRqmtMasks(final DGuiSession session) {
        DDbItem itemRqmt = null;
        DDbJobLinePrepRqmt rqmt = null;
        ArrayList<DRowJobRqmtMask> masks = new ArrayList<>();
        DDbFormula formula = (DDbFormula) session.readRegistry(DModConsts.MU_FRM, new int[] { mnFkFormulaId });
        
        for (DDbFormulaComp comp : formula.maChildComps) {
            itemRqmt = (DDbItem) session.readRegistry(DModConsts.CU_ITM, new int[] { comp.getFkItemId() });
            
            rqmt = new DDbJobLinePrepRqmt();
            rqmt.setPkJobId(mnPkJobId);
            rqmt.setPkLinePrepId(mnPkLinePrepId);
            rqmt.setPkPrepId(mnPkPrepId);
            //rqmt.setPkRqmtId(...);
            rqmt.setQuantity(comp.getQuantity() * mdLoads);
            rqmt.setMassUnit(itemRqmt.getMassUnit());
            rqmt.setMass_r(itemRqmt.getMassUnit() * comp.getQuantity() * mdLoads);
            rqmt.setStatisticsReference(comp.getStatisticsReference());
            rqmt.setStandard(comp.isStandard());
            rqmt.setConsByVar1(comp.isConsByVar1());
            rqmt.setFkItemTypeId(comp.getFkItemTypeId());
            rqmt.setFkItemId(comp.getFkItemId());
            rqmt.setFkUnitId(comp.getFkUnitId());
            rqmt.setXtaLinePrepCode(msXtaLinePrepCode);
            rqmt.setXtaLinePrepName(msXtaLinePrepName);
            rqmt.setXtaProductId(mnFkItemId);
            rqmt.setXtaProductCode(msXtaItemCode);
            rqmt.setXtaProductName(msXtaItemName);
            rqmt.setXtaRqmtTypeCode(itemRqmt.getXtaItemTypeCode());
            rqmt.setXtaRqmtCode(itemRqmt.getCode());
            rqmt.setXtaRqmtName(itemRqmt.getName());
            rqmt.setXtaUnitCode(itemRqmt.getXtaUnitCode());
            rqmt.setXtaUnitName(itemRqmt.getXtaUnitName());
            masks.add(rqmt);
        }
        
        return masks;
    }
}
