/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.mfg.db;

import ft.mod.DModConsts;
import java.sql.ResultSet;
import java.sql.SQLException;
import sba.lib.db.DDbConsts;
import sba.lib.db.DDbRegistry;
import sba.lib.db.DDbRegistryUser;
import sba.lib.gui.DGuiSession;

/**
 *
 * @author Sergio Flores
 */
public class DDbJobLinePrepCons extends DDbRegistryUser implements DRowJobPrepMask {

    protected int mnPkJobId;
    protected int mnPkLinePrepId;
    protected int mnPkPrepId;
    protected int mnPkConsId;
    protected double mdVariable1;
    protected double mdQuantity;
    protected double mdMassUnit;
    protected double mdMass_r;
    protected String msLot;
    protected int mnFkItemTypeId;
    protected int mnFkItemId;
    protected int mnFkUnitId;

    protected String msXtaLinePrepCode;
    protected String msXtaItemTypeCode;
    protected String msXtaItemCode;
    protected String msXtaItemName;
    protected String msXtaUnitCode;
    protected String msXtaUnitName;
    
    public DDbJobLinePrepCons() {
        super(DModConsts.M_JOB_PRP_CON);
        initRegistry();
    }

    public void setPkJobId(int n) { mnPkJobId = n; }
    public void setPkLinePrepId(int n) { mnPkLinePrepId = n; }
    public void setPkPrepId(int n) { mnPkPrepId = n; }
    public void setPkConsId(int n) { mnPkConsId = n; }
    public void setVariable1(double d) { mdVariable1 = d; }
    public void setQuantity(double d) { mdQuantity = d; }
    public void setMassUnit(double d) { mdMassUnit = d; }
    public void setMass_r(double d) { mdMass_r = d; }
    public void setLot(String s) { msLot = s; }
    public void setFkItemTypeId(int n) { mnFkItemTypeId = n; }
    public void setFkItemId(int n) { mnFkItemId = n; }
    public void setFkUnitId(int n) { mnFkUnitId = n; }

    public int getPkJobId() { return mnPkJobId; }
    public int getPkLinePrepId() { return mnPkLinePrepId; }
    public int getPkPrepId() { return mnPkPrepId; }
    public int getPkConsId() { return mnPkConsId; }
    public double getVariable1() { return mdVariable1; }
    public double getQuantity() { return mdQuantity; }
    public double getMassUnit() { return mdMassUnit; }
    public double getMass_r() { return mdMass_r; }
    public String getLot() { return msLot; }
    public int getFkItemTypeId() { return mnFkItemTypeId; }
    public int getFkItemId() { return mnFkItemId; }
    public int getFkUnitId() { return mnFkUnitId; }

    public void setXtaLinePrepCode(String s) { msXtaLinePrepCode = s; }
    public void setXtaItemTypeCode(String s) { msXtaItemTypeCode = s; }
    public void setXtaItemCode(String s) { msXtaItemCode = s; }
    public void setXtaItemName(String s) { msXtaItemName = s; }
    public void setXtaUnitCode(String s) { msXtaUnitCode = s; }
    public void setXtaUnitName(String s) { msXtaUnitName = s; }
    
    public String getXtaLinePrepCode() { return msXtaLinePrepCode; }
    public String getXtaItemTypeCode() { return msXtaItemTypeCode; }
    public String getXtaItemCode() { return msXtaItemCode; }
    public String getXtaItemName() { return msXtaItemName; }
    public String getXtaUnitCode() { return msXtaUnitCode; }
    public String getXtaUnitName() { return msXtaUnitName; }
    
    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkJobId = pk[0];
        mnPkLinePrepId = pk[1];
        mnPkPrepId = pk[2];
        mnPkConsId = pk[3];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkJobId, mnPkLinePrepId, mnPkPrepId, mnPkConsId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkJobId = 0;
        mnPkLinePrepId = 0;
        mnPkPrepId = 0;
        mnPkConsId = 0;
        mdVariable1 = 0;
        mdQuantity = 0;
        mdMassUnit = 0;
        mdMass_r = 0;
        msLot = "";
        mnFkItemTypeId = 0;
        mnFkItemId = 0;
        mnFkUnitId = 0;
        
        msXtaLinePrepCode = "";
        msXtaItemTypeCode = "";
        msXtaItemCode = "";
        msXtaItemName = "";
        msXtaUnitCode = "";
        msXtaUnitName = "";
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_job = " + mnPkJobId + " AND id_lin_prp = " + mnPkLinePrepId + " AND id_prp = " + mnPkPrepId + " AND id_con = " + mnPkConsId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_job = " + pk[0] + " AND id_lin_prp = " + pk[1] + " AND id_prp = " + pk[2] + " AND id_con = " + pk[3] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkConsId = 0;

        msSql = "SELECT COALESCE(MAX(id_con), 0) + 1 FROM " + getSqlTable() + " " +
                "WHERE id_job = " + mnPkJobId + " AND id_lin_prp = " + mnPkLinePrepId + " AND id_prp = " + mnPkPrepId + " ";
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkConsId = resultSet.getInt(1);
        }
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
            mnPkJobId = resultSet.getInt("id_job");
            mnPkLinePrepId = resultSet.getInt("id_lin_prp");
            mnPkPrepId = resultSet.getInt("id_prp");
            mnPkConsId = resultSet.getInt("id_con");
            mdVariable1 = resultSet.getDouble("var_1");
            mdQuantity = resultSet.getDouble("qty");
            mdMassUnit = resultSet.getDouble("mss_unt");
            mdMass_r = resultSet.getDouble("mss_r");
            msLot = resultSet.getString("lot");
            mnFkItemTypeId = resultSet.getInt("fk_itm_tp");
            mnFkItemId = resultSet.getInt("fk_itm");
            mnFkUnitId = resultSet.getInt("fk_unt");

            // Read aswell extra data:
            
            msXtaLinePrepCode = (String) session.readField(DModConsts.MU_LIN_PRP, new int[] { mnPkLinePrepId }, DDbRegistry.FIELD_CODE);
            msXtaItemTypeCode = (String) session.readField(DModConsts.CS_ITM_TP, new int[] { mnFkItemTypeId }, DDbRegistry.FIELD_CODE);
            msXtaItemCode = (String) session.readField(DModConsts.CU_ITM, new int[] { mnFkItemId }, DDbRegistry.FIELD_CODE);
            msXtaItemName = (String) session.readField(DModConsts.CU_ITM, new int[] { mnFkItemId }, DDbRegistry.FIELD_NAME);
            msXtaUnitCode = (String) session.readField(DModConsts.CU_UNT, new int[] { mnFkUnitId }, DDbRegistry.FIELD_CODE);
            msXtaUnitName = (String) session.readField(DModConsts.CU_UNT, new int[] { mnFkUnitId }, DDbRegistry.FIELD_NAME);

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
                    mnPkConsId + ", " + 
                    mdVariable1 + ", " + 
                    mdQuantity + ", " + 
                    mdMassUnit + ", " + 
                    mdMass_r + ", " + 
                    "'" + msLot + "', " + 
                    mnFkItemTypeId + ", " + 
                    mnFkItemId + ", " + 
                    mnFkUnitId + " " + 
                    ")";
        }
        else {
            mnFkUserUpdateId = session.getUser().getPkUserId();

            msSql = "UPDATE " + getSqlTable() + " SET " +
                    //"id_job = " + mnPkJobId + ", " +
                    //"id_lin_prp = " + mnPkLinePrepId + ", " +
                    //"id_prp = " + mnPkPrepId + ", " +
                    //"id_con = " + mnPkConsId + ", " +
                    "var_1 = " + mdVariable1 + ", " +
                    "qty = " + mdQuantity + ", " +
                    "mss_unt = " + mdMassUnit + ", " +
                    "mss_r = " + mdMass_r + ", " +
                    "lot = '" + msLot + "', " +
                    "fk_itm_tp = " + mnFkItemTypeId + ", " +
                    "fk_itm = " + mnFkItemId + ", " +
                    "fk_unt = " + mnFkUnitId + " " +
                    getSqlWhere();
        }

        session.getStatement().execute(msSql);
        mbRegistryNew = false;
        mnQueryResultId = DDbConsts.SAVE_OK;
    }

    @Override
    public DDbJobLinePrepCons clone() throws CloneNotSupportedException {
        DDbJobLinePrepCons registry = new DDbJobLinePrepCons();

        registry.setPkJobId(this.getPkJobId());
        registry.setPkLinePrepId(this.getPkLinePrepId());
        registry.setPkPrepId(this.getPkPrepId());
        registry.setPkConsId(this.getPkConsId());
        registry.setVariable1(this.getVariable1());
        registry.setQuantity(this.getQuantity());
        registry.setMassUnit(this.getMassUnit());
        registry.setMass_r(this.getMass_r());
        registry.setLot(this.getLot());
        registry.setFkItemTypeId(this.getFkItemTypeId());
        registry.setFkItemId(this.getFkItemId());
        registry.setFkUnitId(this.getFkUnitId());

        registry.setXtaLinePrepCode(this.getXtaLinePrepCode());
        registry.setXtaItemTypeCode(this.getXtaItemTypeCode());
        registry.setXtaItemCode(this.getXtaItemCode());
        registry.setXtaItemName(this.getXtaItemName());
        registry.setXtaUnitCode(this.getXtaUnitCode());
        registry.setXtaUnitName(this.getXtaUnitName());
        
        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }

    @Override
    public String getLineCode() {
        return getXtaLinePrepCode();
    }

    @Override
    public int getProg() {
        return getPkPrepId();
    }

    @Override
    public String getItemTypeCode() {
        return getXtaItemTypeCode();
    }

    @Override
    public String getItem() {
        return getXtaItemName();
    }

    @Override
    public String getUnitCode() {
        return getXtaUnitCode();
    }
}
