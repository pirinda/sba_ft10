/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.cfg.db;

import ft.mod.DModConsts;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import sba.gui.util.DUtilConsts;
import sba.lib.DLibUtils;
import sba.lib.db.DDbConsts;
import sba.lib.db.DDbRegistry;
import sba.lib.db.DDbRegistryUser;
import sba.lib.gui.DGuiSession;

/**
 *
 * @author Sergio Flores
 */
public class DDbPresent extends DDbRegistryUser {

    protected int mnPkPresentId;
    protected String msCode;
    protected String msName;
    protected String msNameRaw;
    protected String msLotCode;
    protected double mdContentUnit;
    /*
    protected boolean mbDeleted;
    protected boolean mbSystem;
    */
    protected int mnFkUnitId;
    /*
    protected int mnFkUserInsertId;
    protected int mnFkUserUpdateId;
    protected Date mtTsUserInsert;
    protected Date mtTsUserUpdate;
    */
    
    protected String msXtaUnitCode;
    protected String msXtaUnitName;

    public DDbPresent() {
        super(DModConsts.CU_PRS);
        initRegistry();
    }

    public void readXtaData(final DGuiSession session) {
        // Reset extra data:
        
        msXtaUnitCode = "";
        msXtaUnitName = "";
        
        // Read extra data:
        
        msXtaUnitCode = (String) session.readField(DModConsts.CU_UNT, new int[] { mnFkUnitId }, DDbRegistry.FIELD_CODE);
        msXtaUnitName = (String) session.readField(DModConsts.CU_UNT, new int[] { mnFkUnitId }, DDbRegistry.FIELD_NAME);
    }
    
    public static String computeName(final DGuiSession session, final String nameRaw, final double contentUnit, final String unitCode) {
        String separator = ((DDbConfig) session.getConfigCompany()).getNameSeparatorFormatted();
        
        return DLibUtils.textTrim((nameRaw.isEmpty() ? "?" : nameRaw) + separator + DLibUtils.getDecimalFormatQuantity().format(contentUnit) + " " + unitCode);
    }
    
    public void setPkPresentId(int n) { mnPkPresentId = n; }
    public void setCode(String s) { msCode = s; }
    public void setName(String s) { msName = s; }
    public void setNameRaw(String s) { msNameRaw = s; }
    public void setLotCode(String s) { msLotCode = s; }
    public void setContentUnit(double d) { mdContentUnit = d; }
    public void setDeleted(boolean b) { mbDeleted = b; }
    public void setSystem(boolean b) { mbSystem = b; }
    public void setFkUnitId(int n) { mnFkUnitId = n; }
    public void setFkUserInsertId(int n) { mnFkUserInsertId = n; }
    public void setFkUserUpdateId(int n) { mnFkUserUpdateId = n; }
    public void setTsUserInsert(Date t) { mtTsUserInsert = t; }
    public void setTsUserUpdate(Date t) { mtTsUserUpdate = t; }

    public int getPkPresentId() { return mnPkPresentId; }
    public String getCode() { return msCode; }
    public String getName() { return msName; }
    public String getNameRaw() { return msNameRaw; }
    public String getLotCode() { return msLotCode; }
    public double getContentUnit() { return mdContentUnit; }
    public boolean isDeleted() { return mbDeleted; }
    public boolean isSystem() { return mbSystem; }
    public int getFkUnitId() { return mnFkUnitId; }
    public int getFkUserInsertId() { return mnFkUserInsertId; }
    public int getFkUserUpdateId() { return mnFkUserUpdateId; }
    public Date getTsUserInsert() { return mtTsUserInsert; }
    public Date getTsUserUpdate() { return mtTsUserUpdate; }

    public void setXtaUnitCode(String s) { msXtaUnitCode = s; }
    public void setXtaUnitName(String s) { msXtaUnitName = s; }

    public String getXtaUnitCode() { return msXtaUnitCode; }
    public String getXtaUnitName() { return msXtaUnitName; }

    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkPresentId = pk[0];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkPresentId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkPresentId = 0;
        msCode = "";
        msName = "";
        msNameRaw = "";
        msLotCode = "";
        mdContentUnit = 0;
        mbDeleted = false;
        mbSystem = false;
        mnFkUnitId = 0;
        mnFkUserInsertId = 0;
        mnFkUserUpdateId = 0;
        mtTsUserInsert = null;
        mtTsUserUpdate = null;
        
        msXtaUnitCode = "";
        msXtaUnitName = "";
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_prs = " + mnPkPresentId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_prs = " + pk[0] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkPresentId = 0;

        msSql = "SELECT COALESCE(MAX(id_prs), 0) + 1 FROM " + getSqlTable();
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkPresentId = resultSet.getInt(1);
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
            mnPkPresentId = resultSet.getInt("id_prs");
            msCode = resultSet.getString("code");
            msName = resultSet.getString("name");
            msNameRaw = resultSet.getString("name_raw");
            msLotCode = resultSet.getString("lot_code");
            mdContentUnit = resultSet.getDouble("cnt_unt");
            mbDeleted = resultSet.getBoolean("b_del");
            mbSystem = resultSet.getBoolean("b_sys");
            mnFkUnitId = resultSet.getInt("fk_unt");
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
                mdContentUnit,
                msXtaUnitCode);

        if (mbRegistryNew) {
            computePrimaryKey(session);
            mbDeleted = false;
            mbSystem = false;
            mnFkUserInsertId = session.getUser().getPkUserId();
            mnFkUserUpdateId = DUtilConsts.USR_NA_ID;

            if (msCode.isEmpty()) {
                msCode = "" + mnPkPresentId;
            }

            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkPresentId + ", " + 
                    "'" + msCode + "', " + 
                    "'" + msName + "', " + 
                    "'" + msNameRaw + "', " + 
                    "'" + msLotCode + "', " + 
                    mdContentUnit + ", " + 
                    (mbDeleted ? 1 : 0) + ", " + 
                    (mbSystem ? 1 : 0) + ", " + 
                    mnFkUnitId + ", " + 
                    mnFkUserInsertId + ", " + 
                    mnFkUserUpdateId + ", " + 
                    "NOW()" + ", " + 
                    "NOW()" + " " + 
                    ")";
        }
        else {
            mnFkUserUpdateId = session.getUser().getPkUserId();

            msSql = "UPDATE " + getSqlTable() + " SET " +
                    //"id_prs = " + mnPkPresentId + ", " +
                    "code = '" + msCode + "', " +
                    "name = '" + msName + "', " +
                    "name_raw = '" + msNameRaw + "', " +
                    "lot_code = '" + msLotCode + "', " +
                    "cnt_unt = " + mdContentUnit + ", " +
                    "b_del = " + (mbDeleted ? 1 : 0) + ", " +
                    "b_sys = " + (mbSystem ? 1 : 0) + ", " +
                    "fk_unt = " + mnFkUnitId + ", " +
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
    public DDbPresent clone() throws CloneNotSupportedException {
        DDbPresent registry = new DDbPresent();

        registry.setPkPresentId(this.getPkPresentId());
        registry.setCode(this.getCode());
        registry.setName(this.getName());
        registry.setNameRaw(this.getNameRaw());
        registry.setLotCode(this.getLotCode());
        registry.setContentUnit(this.getContentUnit());
        registry.setDeleted(this.isDeleted());
        registry.setSystem(this.isSystem());
        registry.setFkUnitId(this.getFkUnitId());
        registry.setFkUserInsertId(this.getFkUserInsertId());
        registry.setFkUserUpdateId(this.getFkUserUpdateId());
        registry.setTsUserInsert(this.getTsUserInsert());
        registry.setTsUserUpdate(this.getTsUserUpdate());
        
        registry.setXtaUnitCode(this.getXtaUnitCode());
        registry.setXtaUnitName(this.getXtaUnitName());

        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
}
