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
public class DDbJobLinePrepMfg extends DDbRegistryUser implements DRowJobMfgMask {

    protected int mnPkJobId;
    protected int mnPkLinePrepId;
    protected int mnPkPrepId;
    protected int mnPkMfgId;
    protected double mdVar1;
    protected double mdQuantity;
    protected double mdMassUnit;
    protected double mdMass_r;
    protected int mnFkItemTypeId;
    protected int mnFkItemId;
    protected int mnFkUnitId;

    protected String msXtaLinePrepCode;
    protected String msXtaLinePrepName;
    protected String msXtaProductCode;
    protected String msXtaProductName;
    protected String msXtaUnitCode;
    protected String msXtaUnitName;
    
    public DDbJobLinePrepMfg() {
        super(/*XXXDModConsts.M_JOB_PRP_MFG*/0);
        initRegistry();
    }

    public void setPkJobId(int n) { mnPkJobId = n; }
    public void setPkLinePrepId(int n) { mnPkLinePrepId = n; }
    public void setPkPrepId(int n) { mnPkPrepId = n; }
    public void setPkMfgId(int n) { mnPkMfgId = n; }
    public void setVar1(double d) { mdVar1 = d; }
    public void setQuantity(double d) { mdQuantity = d; }
    public void setMassUnit(double d) { mdMassUnit = d; }
    public void setMass_r(double d) { mdMass_r = d; }
    public void setFkItemTypeId(int n) { mnFkItemTypeId = n; }
    public void setFkItemId(int n) { mnFkItemId = n; }
    public void setFkUnitId(int n) { mnFkUnitId = n; }

    public int getPkJobId() { return mnPkJobId; }
    public int getPkLinePrepId() { return mnPkLinePrepId; }
    public int getPkPrepId() { return mnPkPrepId; }
    public int getPkMfgId() { return mnPkMfgId; }
    public double getVar1() { return mdVar1; }
    public double getQuantity() { return mdQuantity; }
    public double getMassUnit() { return mdMassUnit; }
    public double getMass_r() { return mdMass_r; }
    public int getFkItemTypeId() { return mnFkItemTypeId; }
    public int getFkItemId() { return mnFkItemId; }
    public int getFkUnitId() { return mnFkUnitId; }

    public void setXtaLinePrepCode(String s) { msXtaLinePrepCode = s; }
    public void setXtaLinePrepName(String s) { msXtaLinePrepName = s; }
    public void setXtaProductCode(String s) { msXtaProductCode = s; }
    public void setXtaProductName(String s) { msXtaProductName = s; }
    public void setXtaUnitCode(String s) { msXtaUnitCode = s; }
    public void setXtaUnitName(String s) { msXtaUnitName = s; }
    
    public String getXtaLinePrepCode() { return msXtaLinePrepCode; }
    public String getXtaLinePrepName() { return msXtaLinePrepName; }
    public String getXtaProductCode() { return msXtaProductCode; }
    public String getXtaProductName() { return msXtaProductName; }
    public String getXtaUnitCode() { return msXtaUnitCode; }
    public String getXtaUnitName() { return msXtaUnitName; }
    
    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkJobId = pk[0];
        mnPkLinePrepId = pk[1];
        mnPkPrepId = pk[2];
        mnPkMfgId = pk[3];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkJobId, mnPkLinePrepId, mnPkPrepId, mnPkMfgId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkJobId = 0;
        mnPkLinePrepId = 0;
        mnPkPrepId = 0;
        mnPkMfgId = 0;
        mdVar1 = 0;
        mdQuantity = 0;
        mdMassUnit = 0;
        mdMass_r = 0;
        mnFkItemTypeId = 0;
        mnFkItemId = 0;
        mnFkUnitId = 0;
        
        msXtaLinePrepCode = "";
        msXtaLinePrepName = "";
        msXtaProductCode = "";
        msXtaProductName = "";
        msXtaUnitCode = "";
        msXtaUnitName = "";
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_job = " + mnPkJobId + " AND id_lin_prp = " + mnPkLinePrepId + " AND id_prp = " + mnPkPrepId + " AND id_mfg = " + mnPkMfgId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_job = " + pk[0] + " AND id_lin_prp = " + pk[1] + " AND id_prp = " + pk[2] + " AND id_mfg = " + pk[3] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkMfgId = 0;

        msSql = "SELECT COALESCE(MAX(id_mfg), 0) + 1 FROM " + getSqlTable() + " " +
                "WHERE id_job = " + mnPkJobId + " AND id_lin_prp = " + mnPkLinePrepId + " AND id_prp = " + mnPkPrepId + " ";
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkMfgId = resultSet.getInt(1);
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
            mnPkMfgId = resultSet.getInt("id_mfg");
            mdVar1 = resultSet.getDouble("var_1");
            mdQuantity = resultSet.getDouble("qty");
            mdMassUnit = resultSet.getDouble("mss_unt");
            mdMass_r = resultSet.getDouble("mss_r");
            mnFkItemTypeId = resultSet.getInt("fk_itm_tp");
            mnFkItemId = resultSet.getInt("fk_itm");
            mnFkUnitId = resultSet.getInt("fk_uom");

            // Read aswell extra members:
/*XXX
            msXtaLinePrepCode = (String) session.readField(DModConsts.MU_LIN_PRP, new int[] { mnPkLinePrepId }, DDbRegistry.FIELD_CODE);
            msXtaLinePrepName = (String) session.readField(DModConsts.MU_LIN_PRP, new int[] { mnPkLinePrepId }, DDbRegistry.FIELD_NAME);
            msXtaProductCode = (String) session.readField(DModConsts.CU_ITM, new int[] { mnFkItemId }, DDbRegistry.FIELD_CODE);
            msXtaProductName = (String) session.readField(DModConsts.CU_ITM, new int[] { mnFkItemId }, DDbRegistry.FIELD_NAME);
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
                    mnPkMfgId + ", " + 
                    mdVar1 + ", " + 
                    mdQuantity + ", " + 
                    mdMassUnit + ", " + 
                    mdMass_r + ", " + 
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
                    //"id_mfg = " + mnPkMfgId + ", " +
                    "var_1 = " + mdVar1 + ", " +
                    "qty = " + mdQuantity + ", " +
                    "mss_unt = " + mdMassUnit + ", " +
                    "mss_r = " + mdMass_r + ", " +
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
    public DDbJobLinePrepMfg clone() throws CloneNotSupportedException {
        DDbJobLinePrepMfg registry = new DDbJobLinePrepMfg();

        registry.setPkJobId(this.getPkJobId());
        registry.setPkLinePrepId(this.getPkLinePrepId());
        registry.setPkPrepId(this.getPkPrepId());
        registry.setPkMfgId(this.getPkMfgId());
        registry.setVar1(this.getVar1());
        registry.setQuantity(this.getQuantity());
        registry.setMassUnit(this.getMassUnit());
        registry.setMass_r(this.getMass_r());
        registry.setFkItemTypeId(this.getFkItemTypeId());
        registry.setFkItemId(this.getFkItemId());
        registry.setFkUnitId(this.getFkUnitId());

        registry.setXtaLinePrepCode(this.getXtaLinePrepCode());
        registry.setXtaLinePrepName(this.getXtaLinePrepName());
        registry.setXtaProductCode(this.getXtaProductCode());
        registry.setXtaProductName(this.getXtaProductName());
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
    public String getUnitCode() {
        return getXtaUnitCode();
    }
}
