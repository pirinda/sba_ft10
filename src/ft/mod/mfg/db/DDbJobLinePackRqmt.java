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
public class DDbJobLinePackRqmt extends DDbRegistryUser implements DRowJobRqmtMask {

    protected int mnPkJobId;
    protected int mnPkLinePackId;
    protected int mnPkPackId;
    protected int mnPkRqmtId;
    protected double mdQuantity;
    protected double mdMassUnit;
    protected double mdMass_r;
    protected String msStatisticsReference;
    protected boolean mbStandard;
    protected int mnFkItemTypeId;
    protected int mnFkItemId;
    protected int mnFkUnitId;

    protected String msXtaLinePackCode;
    protected String msXtaItemTypeCode;
    protected String msXtaItemCode;
    protected String msXtaItemName;
    protected String msXtaUnitCode;
    protected String msXtaUnitName;
    
    protected double mdAuxQuantityCon;
    
    public DDbJobLinePackRqmt() {
        super(DModConsts.M_JOB_PCK_REQ);
        initRegistry();
    }

    public void setPkJobId(int n) { mnPkJobId = n; }
    public void setPkLinePackId(int n) { mnPkLinePackId = n; }
    public void setPkPackId(int n) { mnPkPackId = n; }
    public void setPkRqmtId(int n) { mnPkRqmtId = n; }
    public void setQuantity(double d) { mdQuantity = d; }
    public void setMassUnit(double d) { mdMassUnit = d; }
    public void setMass_r(double d) { mdMass_r = d; }
    public void setStatisticsReference(String s) { msStatisticsReference = s; }
    public void setStandard(boolean b) { mbStandard = b; }
    public void setFkItemTypeId(int n) { mnFkItemTypeId = n; }
    public void setFkItemId(int n) { mnFkItemId = n; }
    public void setFkUnitId(int n) { mnFkUnitId = n; }

    public int getPkJobId() { return mnPkJobId; }
    public int getPkLinePackId() { return mnPkLinePackId; }
    public int getPkPackId() { return mnPkPackId; }
    public int getPkRqmtId() { return mnPkRqmtId; }
    public double getQuantity() { return mdQuantity; }
    public double getMassUnit() { return mdMassUnit; }
    public double getMass_r() { return mdMass_r; }
    public String getStatisticsReference() { return msStatisticsReference; }
    public boolean isStandard() { return mbStandard; }
    public int getFkItemTypeId() { return mnFkItemTypeId; }
    public int getFkItemId() { return mnFkItemId; }
    public int getFkUnitId() { return mnFkUnitId; }

    public void setXtaLinePackCode(String s) { msXtaLinePackCode = s; }
    public void setXtaItemTypeCode(String s) { msXtaItemTypeCode = s; }
    public void setXtaItemCode(String s) { msXtaItemCode = s; }
    public void setXtaItemName(String s) { msXtaItemName = s; }
    public void setXtaUnitCode(String s) { msXtaUnitCode = s; }
    public void setXtaUnitName(String s) { msXtaUnitName = s; }
    
    public void setAuxQuantityCon(double d) { mdAuxQuantityCon = d; }
    
    public String getXtaLinePackCode() { return msXtaLinePackCode; }
    public String getXtaItemTypeCode() { return msXtaItemTypeCode; }
    public String getXtaItemCode() { return msXtaItemCode; }
    public String getXtaItemName() { return msXtaItemName; }
    public String getXtaUnitCode() { return msXtaUnitCode; }
    public String getXtaUnitName() { return msXtaUnitName; }
    
    public double getAuxQuantityCon() { return mdAuxQuantityCon; }
    
    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkJobId = pk[0];
        mnPkLinePackId = pk[1];
        mnPkPackId = pk[2];
        mnPkRqmtId = pk[3];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkJobId, mnPkLinePackId, mnPkPackId, mnPkRqmtId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkJobId = 0;
        mnPkLinePackId = 0;
        mnPkPackId = 0;
        mnPkRqmtId = 0;
        mdQuantity = 0;
        mdMassUnit = 0;
        mdMass_r = 0;
        msStatisticsReference = "";
        mbStandard = false;
        mnFkItemTypeId = 0;
        mnFkItemId = 0;
        mnFkUnitId = 0;
        
        msXtaLinePackCode = "";
        msXtaItemTypeCode = "";
        msXtaItemCode = "";
        msXtaItemName = "";
        msXtaUnitCode = "";
        msXtaUnitName = "";

        mdAuxQuantityCon = 0;
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_job = " + mnPkJobId + " AND id_lin_pck = " + mnPkLinePackId + " AND id_pck = " + mnPkPackId + " AND id_req = " + mnPkRqmtId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_job = " + pk[0] + " AND id_lin_pck = " + pk[1] + " AND id_pck = " + pk[2] + " AND id_req = " + pk[3] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkRqmtId = 0;

        msSql = "SELECT COALESCE(MAX(id_req), 0) + 1 FROM " + getSqlTable() + " " +
                "WHERE id_job = " + mnPkJobId + " AND id_lin_pck = " + mnPkLinePackId + " AND id_pck = " + mnPkPackId + " ";
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkRqmtId = resultSet.getInt(1);
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
            mnPkRqmtId = resultSet.getInt("id_req");
            mdQuantity = resultSet.getDouble("qty");
            mdMassUnit = resultSet.getDouble("mss_unt");
            mdMass_r = resultSet.getDouble("mss_r");
            msStatisticsReference = resultSet.getString("sta_ref");
            mbStandard = resultSet.getBoolean("b_std");
            mnFkItemTypeId = resultSet.getInt("fk_itm_tp");
            mnFkItemId = resultSet.getInt("fk_itm");
            mnFkUnitId = resultSet.getInt("fk_unt");

            // Read aswell extra data:
            
            msXtaLinePackCode = (String) session.readField(DModConsts.MU_LIN_PCK, new int[] { mnPkLinePackId }, DDbRegistry.FIELD_CODE);
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
                    mnPkLinePackId + ", " + 
                    mnPkPackId + ", " + 
                    mnPkRqmtId + ", " + 
                    mdQuantity + ", " + 
                    mdMassUnit + ", " + 
                    mdMass_r + ", " + 
                    "'" + msStatisticsReference + "', " + 
                    (mbStandard ? 1 : 0) + ", " + 
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
                    //"id_req = " + mnPkRqmtId + ", " +
                    "qty = " + mdQuantity + ", " +
                    "mss_unt = " + mdMassUnit + ", " +
                    "mss_r = " + mdMass_r + ", " +
                    "sta_ref = '" + msStatisticsReference + "', " +
                    "b_std = " + (mbStandard ? 1 : 0) + ", " +
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
    public DDbJobLinePackRqmt clone() throws CloneNotSupportedException {
        DDbJobLinePackRqmt registry = new DDbJobLinePackRqmt();

        registry.setPkJobId(this.getPkJobId());
        registry.setPkLinePackId(this.getPkLinePackId());
        registry.setPkPackId(this.getPkPackId());
        registry.setPkRqmtId(this.getPkRqmtId());
        registry.setQuantity(this.getQuantity());
        registry.setMassUnit(this.getMassUnit());
        registry.setMass_r(this.getMass_r());
        registry.setStatisticsReference(this.getStatisticsReference());
        registry.setStandard(this.isStandard());
        registry.setFkItemTypeId(this.getFkItemTypeId());
        registry.setFkItemId(this.getFkItemId());
        registry.setFkUnitId(this.getFkUnitId());

        registry.setXtaLinePackCode(this.getXtaLinePackCode());
        registry.setXtaItemTypeCode(this.getXtaItemTypeCode());
        registry.setXtaItemCode(this.getXtaItemCode());
        registry.setXtaItemName(this.getXtaItemName());
        registry.setXtaUnitCode(this.getXtaUnitCode());
        registry.setXtaUnitName(this.getXtaUnitName());
        
        registry.setAuxQuantityCon(this.getAuxQuantityCon());
        
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
    public String getItemTypeCode() {
        return getXtaItemTypeCode();
    }

    @Override
    public String getItem() {
        return getXtaItemName();
    }

    @Override
    public double getQuantityReq() {
        return getQuantity();
    }

    @Override
    public double getQuantityCon() {
        return getAuxQuantityCon();
    }

    @Override
    public String getUnitCode() {
        return getXtaUnitCode();
    }

    @Override
    public boolean isConsVariable1() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
