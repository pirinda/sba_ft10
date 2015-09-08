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
public class DDbJobLinePackCons extends DDbRegistryUser implements DRowJobConsMask {

    protected int mnPkJobId;
    protected int mnPkLinePackId;
    protected int mnPkPackId;
    protected int mnPkConsId;
    protected double mdQuantity;
    protected double mdMassUnit;
    protected double mdMass_r;
    protected String msLot;
    protected int mnFkItemTypeId;
    protected int mnFkItemId;
    protected int mnFkUnitId;

    protected String msXtaLinePackCode;
    protected String msXtaLinePackName;
    protected int mnXtaProductId;
    protected String msXtaProductCode;
    protected String msXtaProductName;
    protected String msXtaLinePrepName;
    protected String msXtaRqmtTypeCode;
    protected String msXtaRqmtCode;
    protected String msXtaRqmtName;
    protected String msXtaUnitCode;
    protected String msXtaUnitName;
    
    public DDbJobLinePackCons() {
        super(DModConsts.M_JOB_PCK_CON);
        initRegistry();
    }

    public void setPkJobId(int n) { mnPkJobId = n; }
    public void setPkLinePackId(int n) { mnPkLinePackId = n; }
    public void setPkPackId(int n) { mnPkPackId = n; }
    public void setPkConsId(int n) { mnPkConsId = n; }
    public void setQuantity(double d) { mdQuantity = d; }
    public void setMassUnit(double d) { mdMassUnit = d; }
    public void setMass_r(double d) { mdMass_r = d; }
    public void setLot(String s) { msLot = s; }
    public void setFkItemTypeId(int n) { mnFkItemTypeId = n; }
    public void setFkItemId(int n) { mnFkItemId = n; }
    public void setFkUnitId(int n) { mnFkUnitId = n; }

    public int getPkJobId() { return mnPkJobId; }
    public int getPkLinePackId() { return mnPkLinePackId; }
    public int getPkPackId() { return mnPkPackId; }
    public int getPkConsId() { return mnPkConsId; }
    public double getQuantity() { return mdQuantity; }
    public double getMassUnit() { return mdMassUnit; }
    public double getMass_r() { return mdMass_r; }
    public String getLot() { return msLot; }
    public int getFkItemTypeId() { return mnFkItemTypeId; }
    public int getFkItemId() { return mnFkItemId; }
    public int getFkUnitId() { return mnFkUnitId; }

    public void setXtaLinePackCode(String s) { msXtaLinePackCode = s; }
    public void setXtaLinePrepName(String s) { msXtaLinePrepName = s; }
    public void setXtaProductId(int n) { mnXtaProductId = n; }
    public void setXtaProductCode(String s) { msXtaProductCode = s; }
    public void setXtaProductName(String s) { msXtaProductName = s; }
    public void setXtaRqmtTypeCode(String s) { msXtaRqmtTypeCode = s; }
    public void setXtaRqmtCode(String s) { msXtaRqmtCode = s; }
    public void setXtaRqmtName(String s) { msXtaRqmtName = s; }
    public void setXtaUnitCode(String s) { msXtaUnitCode = s; }
    public void setXtaUnitName(String s) { msXtaUnitName = s; }
    
    public String getXtaLinePackCode() { return msXtaLinePackCode; }
    public String getXtaLinePrepName() { return msXtaLinePrepName; }
    public int getXtaProductId() { return mnXtaProductId; }
    public String getXtaProductCode() { return msXtaProductCode; }
    public String getXtaProductName() { return msXtaProductName; }
    public String getXtaRqmtTypeCode() { return msXtaRqmtTypeCode; }
    public String getXtaRqmtCode() { return msXtaRqmtCode; }
    public String getXtaRqmtName() { return msXtaRqmtName; }
    public String getXtaUnitCode() { return msXtaUnitCode; }
    public String getXtaUnitName() { return msXtaUnitName; }
    
    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkJobId = pk[0];
        mnPkLinePackId = pk[1];
        mnPkPackId = pk[2];
        mnPkConsId = pk[3];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkJobId, mnPkLinePackId, mnPkPackId, mnPkConsId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkJobId = 0;
        mnPkLinePackId = 0;
        mnPkPackId = 0;
        mnPkConsId = 0;
        mdQuantity = 0;
        mdMassUnit = 0;
        mdMass_r = 0;
        msLot = "";
        mnFkItemTypeId = 0;
        mnFkItemId = 0;
        mnFkUnitId = 0;
        
        msXtaLinePackCode = "";
        msXtaLinePrepName = "";
        mnXtaProductId = 0;
        msXtaProductCode = "";
        msXtaProductName = "";
        msXtaRqmtTypeCode = "";
        msXtaRqmtCode = "";
        msXtaRqmtName = "";
        msXtaUnitCode = "";
        msXtaUnitName = "";
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_job = " + mnPkJobId + " AND id_lin_pck = " + mnPkLinePackId + " AND id_pck = " + mnPkPackId + " AND id_con = " + mnPkConsId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_job = " + pk[0] + " AND id_lin_pck = " + pk[1] + " AND id_pck = " + pk[2] + " AND id_con = " + pk[3] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkConsId = 0;

        msSql = "SELECT COALESCE(MAX(id_con), 0) + 1 FROM " + getSqlTable() + " " +
                "WHERE id_job = " + mnPkJobId + " AND id_lin_pck = " + mnPkLinePackId + " AND id_pck = " + mnPkPackId + " ";
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
            mnPkLinePackId = resultSet.getInt("id_lin_pck");
            mnPkPackId = resultSet.getInt("id_pck");
            mnPkConsId = resultSet.getInt("id_con");
            mdQuantity = resultSet.getDouble("qty");
            mdMassUnit = resultSet.getDouble("mss_unt");
            mdMass_r = resultSet.getDouble("mss_r");
            msLot = resultSet.getString("lot");
            mnFkItemTypeId = resultSet.getInt("fk_itm_tp");
            mnFkItemId = resultSet.getInt("fk_itm");
            mnFkUnitId = resultSet.getInt("fk_unt");

            // Read aswell extra data:
            
            msSql = "SELECT fk_itm FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB_PCK) + " "
                    + "WHERE id_job = " + mnPkJobId + " AND id_lin_pck = " + mnPkLinePackId + " AND id_pck = " + mnPkPackId + " ";
            resultSet = session.getStatement().executeQuery(msSql);
            if (!resultSet.next()) {
                throw new Exception(DDbConsts.ERR_MSG_REG_NOT_FOUND);
            }
            else {
                mnXtaProductId = resultSet.getInt(1);
            }
            
            msXtaLinePackCode = (String) session.readField(DModConsts.MU_LIN_PCK, new int[] { mnPkLinePackId }, DDbRegistry.FIELD_CODE);
            msXtaLinePrepName = (String) session.readField(DModConsts.MU_LIN_PRP, new int[] { mnPkLinePackId }, DDbRegistry.FIELD_NAME);
            msXtaProductCode = (String) session.readField(DModConsts.CU_ITM, new int[] { mnXtaProductId }, DDbRegistry.FIELD_CODE);
            msXtaProductName = (String) session.readField(DModConsts.CU_ITM, new int[] { mnXtaProductId }, DDbRegistry.FIELD_NAME);
            msXtaRqmtTypeCode = (String) session.readField(DModConsts.CS_ITM_TP, new int[] { mnFkItemTypeId }, DDbRegistry.FIELD_CODE);
            msXtaRqmtCode = (String) session.readField(DModConsts.CU_ITM, new int[] { mnFkItemId }, DDbRegistry.FIELD_CODE);
            msXtaRqmtName = (String) session.readField(DModConsts.CU_ITM, new int[] { mnFkItemId }, DDbRegistry.FIELD_NAME);
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
                    mnPkLinePackId + ", " + 
                    mnPkPackId + ", " + 
                    mnPkConsId + ", " + 
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
                    //"id_lin_pck = " + mnPkLinePackId + ", " +
                    //"id_pck = " + mnPkPackId + ", " +
                    //"id_con = " + mnPkConsId + ", " +
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
    public DDbJobLinePackCons clone() throws CloneNotSupportedException {
        DDbJobLinePackCons registry = new DDbJobLinePackCons();

        registry.setPkJobId(this.getPkJobId());
        registry.setPkLinePackId(this.getPkLinePackId());
        registry.setPkPackId(this.getPkPackId());
        registry.setPkConsId(this.getPkConsId());
        registry.setQuantity(this.getQuantity());
        registry.setMassUnit(this.getMassUnit());
        registry.setMass_r(this.getMass_r());
        registry.setLot(this.getLot());
        registry.setFkItemTypeId(this.getFkItemTypeId());
        registry.setFkItemId(this.getFkItemId());
        registry.setFkUnitId(this.getFkUnitId());

        registry.setXtaLinePackCode(this.getXtaLinePackCode());
        registry.setXtaLinePrepName(this.getXtaLinePrepName());
        registry.setXtaProductId(this.getXtaProductId());
        registry.setXtaProductCode(this.getXtaProductCode());
        registry.setXtaProductName(this.getXtaProductName());
        registry.setXtaRqmtTypeCode(this.getXtaRqmtTypeCode());
        registry.setXtaRqmtCode(this.getXtaRqmtCode());
        registry.setXtaRqmtName(this.getXtaRqmtName());
        registry.setXtaUnitCode(this.getXtaUnitCode());
        registry.setXtaUnitName(this.getXtaUnitName());
        
        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }

    @Override
    public String getLineCode() {
        return getXtaLinePackCode();
    }

    @Override
    public String getLineName() {
        return getXtaLinePrepName();
    }

    @Override
    public String getProduct() {
        return getXtaProductName();
    }

    @Override
    public String getRequirementTypeCode() {
        return getXtaRqmtTypeCode();
    }

    @Override
    public String getRequirement() {
        return getXtaRqmtName();
    }

    @Override
    public String getUnitCode() {
        return getXtaUnitCode();
    }

    @Override
    public double getVariable1() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
