/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.cfg.db;

import ft.mod.DModConsts;
import ft.mod.DModSysConsts;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import sba.gui.util.DUtilConsts;
import sba.lib.DLibConsts;
import sba.lib.DLibUtils;
import sba.lib.db.DDbConsts;
import sba.lib.db.DDbRegistry;
import sba.lib.db.DDbRegistryUser;
import sba.lib.gui.DGuiSession;

/**
 *
 * @author Sergio Flores
 */
public class DDbItem extends DDbRegistryUser {

    protected int mnPkItemId;
    protected String msCode;
    protected String msName;
    protected String msNameRaw;
    protected String msLotCode;
    protected double mdMassUnit;
    /*
    protected boolean mbDeleted;
    protected boolean mbSystem;
    */
    protected int mnFkItemFamilyId;
    protected int mnFkUnitId;
    protected int mnFkPresentId;
    protected int mnFkItemId_n;
    /*
    protected int mnFkUserInsertId;
    protected int mnFkUserUpdateId;
    protected Date mtTsUserInsert;
    protected Date mtTsUserUpdate;
    */
    
    protected int mnXtaFkItemTypeId;
    protected String msXtaItemTypeCode;
    protected String msXtaUnitCode;
    protected String msXtaUnitName;
    protected String msXtaPresentCode;
    protected String msXtaPresentName;

    public DDbItem() {
        super(DModConsts.CU_ITM);
        initRegistry();
    }

    public void readXtaData(final DGuiSession session) {
        DDbItemFamily family = null;
        
        // Reset extra data:
        
        mnXtaFkItemTypeId = DLibConsts.UNDEFINED;
        msXtaItemTypeCode = "";

        msXtaUnitCode = "";
        msXtaUnitName = "";
        msXtaPresentCode = "";
        msXtaPresentName = "";
        
        // Read extra data:
        
        family = (DDbItemFamily) session.readRegistry(DModConsts.CU_FAM, new int[] { mnFkItemFamilyId }, DDbConsts.MODE_STEALTH);
        
        mnXtaFkItemTypeId = family.getFkItemTypeId();
        msXtaItemTypeCode = (String) session.readField(DModConsts.CS_ITM_TP, new int[] { family.getFkItemTypeId() }, DDbRegistry.FIELD_CODE);

        msXtaUnitCode = (String) session.readField(DModConsts.CU_UNT, new int[] { mnFkUnitId }, DDbRegistry.FIELD_CODE);
        msXtaUnitName = (String) session.readField(DModConsts.CU_UNT, new int[] { mnFkUnitId }, DDbRegistry.FIELD_NAME);
        msXtaPresentCode = (String) session.readField(DModConsts.CU_PRS, new int[] { mnFkPresentId }, DDbRegistry.FIELD_CODE);
        msXtaPresentName = (String) session.readField(DModConsts.CU_PRS, new int[] { mnFkPresentId }, DDbRegistry.FIELD_NAME);
    }
    
    public static String computeName(final DGuiSession session, final String nameRaw, final int presentationId, final String presentationName, final String unitCode) {
        String separator = ((DDbConfig) session.getConfigCompany()).getNameSeparatorFormatted();
        
        return DLibUtils.textTrim((nameRaw.isEmpty() ? "?" : nameRaw) + separator + (presentationId == DModSysConsts.CU_PRS_NA ? "" : presentationName + separator) + unitCode);
    }
    
    public void setPkItemId(int n) { mnPkItemId = n; }
    public void setCode(String s) { msCode = s; }
    public void setName(String s) { msName = s; }
    public void setNameRaw(String s) { msNameRaw = s; }
    public void setLotCode(String s) { msLotCode = s; }
    public void setMassUnit(double d) { mdMassUnit = d; }
    public void setDeleted(boolean b) { mbDeleted = b; }
    public void setSystem(boolean b) { mbSystem = b; }
    public void setFkItemFamilyId(int n) { mnFkItemFamilyId = n; }
    public void setFkUnitId(int n) { mnFkUnitId = n; }
    public void setFkPresentId(int n) { mnFkPresentId = n; }
    public void setFkItemId_n(int n) { mnFkItemId_n = n; }
    public void setFkUserInsertId(int n) { mnFkUserInsertId = n; }
    public void setFkUserUpdateId(int n) { mnFkUserUpdateId = n; }
    public void setTsUserInsert(Date t) { mtTsUserInsert = t; }
    public void setTsUserUpdate(Date t) { mtTsUserUpdate = t; }

    public int getPkItemId() { return mnPkItemId; }
    public String getCode() { return msCode; }
    public String getName() { return msName; }
    public String getNameRaw() { return msNameRaw; }
    public String getLotCode() { return msLotCode; }
    public double getMassUnit() { return mdMassUnit; }
    public boolean isDeleted() { return mbDeleted; }
    public boolean isSystem() { return mbSystem; }
    public int getFkItemFamilyId() { return mnFkItemFamilyId; }
    public int getFkUnitId() { return mnFkUnitId; }
    public int getFkPresentId() { return mnFkPresentId; }
    public int getFkItemId_n() { return mnFkItemId_n; }
    public int getFkUserInsertId() { return mnFkUserInsertId; }
    public int getFkUserUpdateId() { return mnFkUserUpdateId; }
    public Date getTsUserInsert() { return mtTsUserInsert; }
    public Date getTsUserUpdate() { return mtTsUserUpdate; }

    public void setXtaFkItemTypeId(int n) { mnXtaFkItemTypeId = n; }
    public void setXtaItemTypeCode(String s) { msXtaItemTypeCode = s; }
    public void setXtaUnitCode(String s) { msXtaUnitCode = s; }
    public void setXtaUnitName(String s) { msXtaUnitName = s; }
    public void setXtaPresentCode(String s) { msXtaPresentCode = s; }
    public void setXtaPresentName(String s) { msXtaPresentName = s; }

    public int getXtaFkItemTypeId() { return mnXtaFkItemTypeId; }
    public String getXtaItemTypeCode() { return msXtaItemTypeCode; }
    public String getXtaUnitCode() { return msXtaUnitCode; }
    public String getXtaUnitName() { return msXtaUnitName; }
    public String getXtaPresentCode() { return msXtaPresentCode; }
    public String getXtaPresentName() { return msXtaPresentName; }
    
    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkItemId = pk[0];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkItemId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkItemId = 0;
        msCode = "";
        msName = "";
        msNameRaw = "";
        msLotCode = "";
        mdMassUnit = 0;
        mbDeleted = false;
        mbSystem = false;
        mnFkItemFamilyId = 0;
        mnFkUnitId = 0;
        mnFkPresentId = 0;
        mnFkItemId_n = 0;
        mnFkUserInsertId = 0;
        mnFkUserUpdateId = 0;
        mtTsUserInsert = null;
        mtTsUserUpdate = null;
        
        mnXtaFkItemTypeId = 0;
        msXtaItemTypeCode = "";
        msXtaUnitCode = "";
        msXtaUnitName = "";
        msXtaPresentCode = "";
        msXtaPresentName = "";
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_itm = " + mnPkItemId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_itm = " + pk[0] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkItemId = 0;

        msSql = "SELECT COALESCE(MAX(id_itm), 0) + 1 FROM " + getSqlTable();
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkItemId = resultSet.getInt(1);
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
            mnPkItemId = resultSet.getInt("id_itm");
            msCode = resultSet.getString("code");
            msName = resultSet.getString("name");
            msNameRaw = resultSet.getString("name_raw");
            msLotCode = resultSet.getString("lot_code");
            mdMassUnit = resultSet.getDouble("mss_unt");
            mbDeleted = resultSet.getBoolean("b_del");
            mbSystem = resultSet.getBoolean("b_sys");
            mnFkItemFamilyId = resultSet.getInt("fk_fam");
            mnFkUnitId = resultSet.getInt("fk_unt");
            mnFkPresentId = resultSet.getInt("fk_prs");
            mnFkItemId_n = resultSet.getInt("fk_itm_n");
            mnFkUserInsertId = resultSet.getInt("fk_usr_ins");
            mnFkUserUpdateId = resultSet.getInt("fk_usr_upd");
            mtTsUserInsert = resultSet.getTimestamp("ts_usr_ins");
            mtTsUserUpdate = resultSet.getTimestamp("ts_usr_upd");

            // Read aswell extra data:

            readXtaData(session);
            
            // Finish registry reading:

            mbRegistryNew = false;
        }

        mnQueryResultId = DDbConsts.READ_OK;
    }

    @Override
    public void save(DGuiSession session) throws SQLException, Exception {
        initQueryMembers();
        mnQueryResultId = DDbConsts.SAVE_ERROR;

        readXtaData(session);
        
        msName = computeName(
                session,
                msNameRaw,
                mnFkPresentId,
                msXtaPresentName,
                msXtaUnitCode);

        if (mbRegistryNew) {
            computePrimaryKey(session);
            mbDeleted = false;
            mbSystem = false;
            mnFkUserInsertId = session.getUser().getPkUserId();
            mnFkUserUpdateId = DUtilConsts.USR_NA_ID;

            if (msCode.isEmpty()) {
                msCode = "" + mnPkItemId;
            }

            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkItemId + ", " + 
                    "'" + msCode + "', " + 
                    "'" + msName + "', " + 
                    "'" + msNameRaw + "', " + 
                    "'" + msLotCode + "', " + 
                    mdMassUnit + ", " + 
                    (mbDeleted ? 1 : 0) + ", " + 
                    (mbSystem ? 1 : 0) + ", " + 
                    mnFkItemFamilyId + ", " + 
                    mnFkUnitId + ", " + 
                    mnFkPresentId + ", " + 
                    (mnFkItemId_n == DLibConsts.UNDEFINED ? "NULL" : "" + mnFkItemId_n) + ", " + 
                    mnFkUserInsertId + ", " + 
                    mnFkUserUpdateId + ", " + 
                    "NOW()" + ", " + 
                    "NOW()" + " " + 
                    ")";
        }
        else {
            mnFkUserUpdateId = session.getUser().getPkUserId();

            msSql = "UPDATE " + getSqlTable() + " SET " +
                    //"id_itm = " + mnPkItemId + ", " +
                    "code = '" + msCode + "', " +
                    "name = '" + msName + "', " +
                    "name_raw = '" + msNameRaw + "', " +
                    "lot_code = '" + msLotCode + "', " +
                    "mss_unt = " + mdMassUnit + ", " +
                    "b_del = " + (mbDeleted ? 1 : 0) + ", " +
                    "b_sys = " + (mbSystem ? 1 : 0) + ", " +
                    "fk_fam = " + mnFkItemFamilyId + ", " +
                    "fk_unt = " + mnFkUnitId + ", " +
                    "fk_prs = " + mnFkPresentId + ", " +
                    "fk_itm_n = " + (mnFkItemId_n == DLibConsts.UNDEFINED ? "NULL" : "" + mnFkItemId_n) + ", " +
                    //"fk_usr_ins = " + mnFkUserInsertId + ", " +
                    "fk_usr_upd = " + mnFkUserUpdateId + ", " +
                    //"ts_usr_ins = " + "NOW()" + ", " +
                    "ts_usr_upd = " + "NOW()" + " " +
                    getSqlWhere();
        }

        session.getStatement().execute(msSql);
        mbRegistryNew = false;
        mnQueryResultId = DDbConsts.SAVE_OK;
    }

    @Override
    public DDbItem clone() throws CloneNotSupportedException {
        DDbItem registry = new DDbItem();

        registry.setPkItemId(this.getPkItemId());
        registry.setCode(this.getCode());
        registry.setName(this.getName());
        registry.setNameRaw(this.getNameRaw());
        registry.setLotCode(this.getLotCode());
        registry.setMassUnit(this.getMassUnit());
        registry.setDeleted(this.isDeleted());
        registry.setSystem(this.isSystem());
        registry.setFkItemFamilyId(this.getFkItemFamilyId());
        registry.setFkUnitId(this.getFkUnitId());
        registry.setFkPresentId(this.getFkPresentId());
        registry.setFkItemId_n(this.getFkItemId_n());
        registry.setFkUserInsertId(this.getFkUserInsertId());
        registry.setFkUserUpdateId(this.getFkUserUpdateId());
        registry.setTsUserInsert(this.getTsUserInsert());
        registry.setTsUserUpdate(this.getTsUserUpdate());

        registry.setXtaFkItemTypeId(this.getXtaFkItemTypeId());
        registry.setXtaItemTypeCode(this.getXtaItemTypeCode());
        registry.setXtaUnitCode(this.getXtaUnitCode());
        registry.setXtaUnitName(this.getXtaUnitName());
        registry.setXtaPresentCode(this.getXtaPresentCode());
        registry.setXtaPresentName(this.getXtaPresentName());
        
        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
}
