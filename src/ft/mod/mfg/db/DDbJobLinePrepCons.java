/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.mfg.db;

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
public class DDbJobLinePrepCons extends DDbRegistryUser implements DRowJobConsMask {

    protected int mnPkJobId;
    protected int mnPkLinePrepId;
    protected int mnPkPrepId;
    protected int mnPkConsId;
    protected double mdVar1;
    protected double mdQuantity;
    protected double mdMassUnit;
    protected double mdMass_r;
    protected String msLot;
    protected int mnFkItemTypeId;
    protected int mnFkItemId;
    protected int mnFkUnitId;

    protected String msXtaLinePrepCode;
    protected String msXtaLinePrepName;
    protected int mnXtaProductId;
    protected String msXtaProductCode;
    protected String msXtaProductName;
    protected String msXtaRqmtTypeCode;
    protected String msXtaRqmtCode;
    protected String msXtaRqmtName;
    protected String msXtaUnitCode;
    protected String msXtaUnitName;
    
    public DDbJobLinePrepCons() {
        super(/*XXXDModConsts.M_JOB_PRP_CON*/0);
        initRegistry();
    }

    public void setPkJobId(int n) { mnPkJobId = n; }
    public void setPkLinePrepId(int n) { mnPkLinePrepId = n; }
    public void setPkPrepId(int n) { mnPkPrepId = n; }
    public void setPkConsId(int n) { mnPkConsId = n; }
    public void setVar1(double d) { mdVar1 = d; }
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
    public double getVar1() { return mdVar1; }
    public double getQuantity() { return mdQuantity; }
    public double getMassUnit() { return mdMassUnit; }
    public double getMass_r() { return mdMass_r; }
    public String getLot() { return msLot; }
    public int getFkItemTypeId() { return mnFkItemTypeId; }
    public int getFkItemId() { return mnFkItemId; }
    public int getFkUnitId() { return mnFkUnitId; }

    public void setXtaLinePrepCode(String s) { msXtaLinePrepCode = s; }
    public void setXtaLinePrepName(String s) { msXtaLinePrepName = s; }
    public void setXtaProductId(int n) { mnXtaProductId = n; }
    public void setXtaProductCode(String s) { msXtaProductCode = s; }
    public void setXtaProductName(String s) { msXtaProductName = s; }
    public void setXtaRqmtTypeCode(String s) { msXtaRqmtTypeCode = s; }
    public void setXtaRqmtCode(String s) { msXtaRqmtCode = s; }
    public void setXtaRqmtName(String s) { msXtaRqmtName = s; }
    public void setXtaUnitCode(String s) { msXtaUnitCode = s; }
    public void setXtaUnitName(String s) { msXtaUnitName = s; }
    
    public String getXtaLinePrepCode() { return msXtaLinePrepCode; }
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
        mdVar1 = 0;
        mdQuantity = 0;
        mdMassUnit = 0;
        mdMass_r = 0;
        msLot = "";
        mnFkItemTypeId = 0;
        mnFkItemId = 0;
        mnFkUnitId = 0;
        
        msXtaLinePrepCode = "";
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
            mdVar1 = resultSet.getDouble("var_1");
            mdQuantity = resultSet.getDouble("qty");
            mdMassUnit = resultSet.getDouble("mss_unt");
            mdMass_r = resultSet.getDouble("mss_r");
            msLot = resultSet.getString("lot");
            mnFkItemTypeId = resultSet.getInt("fk_itm_tp");
            mnFkItemId = resultSet.getInt("fk_itm");
            mnFkUnitId = resultSet.getInt("fk_uom");

            // Read aswell extra data:
/*XXX
            msSql = "SELECT fk_itm FROM " + DModConsts.TablesMap.get(DModConsts.M_JOB_PRP) + " "
                    + "WHERE id_job = " + mnPkJobId + " AND id_lin_prp = " + mnPkLinePrepId + " AND id_prp = " + mnPkPrepId + " ";
            resultSet = session.getStatement().executeQuery(msSql);
            if (!resultSet.next()) {
                throw new Exception(DDbConsts.ERR_MSG_REG_NOT_FOUND);
            }
            else {
                mnXtaProductId = resultSet.getInt(1);
            }

            msXtaLinePrepCode = (String) session.readField(DModConsts.MU_LIN_PRP, new int[] { mnPkLinePrepId }, DDbRegistry.FIELD_CODE);
            msXtaLinePrepName = (String) session.readField(DModConsts.MU_LIN_PRP, new int[] { mnPkLinePrepId }, DDbRegistry.FIELD_NAME);
            msXtaProductCode = (String) session.readField(DModConsts.CU_ITM, new int[] { mnXtaProductId }, DDbRegistry.FIELD_CODE);
            msXtaProductName = (String) session.readField(DModConsts.CU_ITM, new int[] { mnXtaProductId }, DDbRegistry.FIELD_NAME);
            msXtaRqmtTypeCode = (String) session.readField(DModConsts.CS_ITM_TP, new int[] { mnFkItemTypeId }, DDbRegistry.FIELD_CODE);
            msXtaRqmtCode = (String) session.readField(DModConsts.CU_ITM, new int[] { mnFkItemId }, DDbRegistry.FIELD_CODE);
            msXtaRqmtName = (String) session.readField(DModConsts.CU_ITM, new int[] { mnFkItemId }, DDbRegistry.FIELD_NAME);
            msXtaUnitCode = (String) session.readField(DModConsts.CU_UOM, new int[] { mnFkUnitId }, DDbRegistry.FIELD_CODE);
            msXtaUnitName = (String) session.readField(DModConsts.CU_UOM, new int[] { mnFkUnitId }, DDbRegistry.FIELD_NAME);
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
                    mnPkConsId + ", " + 
                    mdVar1 + ", " + 
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
                    "var_1 = " + mdVar1 + ", " +
                    "qty = " + mdQuantity + ", " +
                    "mss_unt = " + mdMassUnit + ", " +
                    "mss_r = " + mdMass_r + ", " +
                    "lot = '" + msLot + "', " +
                    "fk_itm_tp = " + mnFkItemTypeId + ", " +
                    "fk_itm = " + mnFkItemId + ", " +
                    "fk_uom = " + mnFkUnitId + " " +
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
        registry.setVar1(this.getVar1());
        registry.setQuantity(this.getQuantity());
        registry.setMassUnit(this.getMassUnit());
        registry.setMass_r(this.getMass_r());
        registry.setLot(this.getLot());
        registry.setFkItemTypeId(this.getFkItemTypeId());
        registry.setFkItemId(this.getFkItemId());
        registry.setFkUnitId(this.getFkUnitId());

        registry.setXtaLinePrepCode(this.getXtaLinePrepCode());
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
        return getXtaLinePrepCode();
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
    public String getRqmtTypeCode() {
        return getXtaRqmtTypeCode();
    }

    @Override
    public String getRqmt() {
        return getXtaRqmtName();
    }

    @Override
    public String getUnitCode() {
        return getXtaUnitCode();
    }
}
