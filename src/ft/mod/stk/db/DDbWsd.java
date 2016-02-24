/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.stk.db;

import ft.mod.DModConsts;
import ft.mod.DModSysConsts;
import ft.mod.cfg.db.DCfgUtils;
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
import sba.lib.gui.DGuiConsts;
import sba.lib.gui.DGuiParams;
import sba.lib.gui.DGuiSession;

/**
 *
 * @author Sergio Flores
 */
public class DDbWsd extends DDbRegistryUser {
    
    public static final int PARAM_MOV_TP = 11;
    public static final int PARAM_TRN_TP = 12;
    public static final int PARAM_MFG_TP = 13;
    public static final int PARAM_DPT = 21;
    public static final int PARAM_LIN = 22;
    public static final int PARAM_JOB = 23;

    protected int mnPkWsdId;
    protected int mnNumber;
    protected Date mtDate;
    protected String msReference;
    protected double mdAmount_r;
    protected double mdMass_r;
    /*
    protected boolean mbDeleted;
    protected boolean mbSystem;
    */
    protected int mnFkMoveClassId;
    protected int mnFkMoveTypeId;
    protected int mnFkTransactMoveTypeId;
    protected int mnFkMfgMoveTypeId;
    protected int mnFkStockAdjustTypeId;
    protected int mnFkItemTypeId;
    protected int mnFkWarehouseId;
    protected int mnFkWsdId_n;
    protected int mnFkBizPartnerId_n;
    protected int mnFkDepartId_n;
    protected int mnFkLineId_n;
    protected int mnFkJobId_n;
    /*
    protected int mnFkUserInsertId;
    protected int mnFkUserUpdateId;
    protected Date mtTsUserInsert;
    protected Date mtTsUserUpdate;
    */
    
    protected ArrayList<DDbWsdRow> maRows;

    public DDbWsd() {
        super(DModConsts.S_WSD);
        maRows = new ArrayList<>();
        initRegistry();
    }
    
    /*
     * Private methods
     */
    
    private void saveStock(final DGuiSession session) throws Exception {
        String sql = "";
        ArrayList<DDbStock> stocks = new ArrayList<>();
        
        for (DDbWsdRow child : maRows) {
            if (!child.isDeleted()) {
                stocks.add(child.createStock(this));
            }
        }
        
        sql = "UPDATE " + DModConsts.TablesMap.get(DModConsts.S_STK) + " SET "
                + "b_del = 1, "
                + "fk_usr_upd = " + session.getUser().getPkUserId() + ", "
                + "ts_usr_upd = NOW() "
                + "WHERE fk_wsd_wsd = " + mnPkWsdId + " AND b_del = 0 ";
        session.getStatement().execute(sql);
        
        for (DDbStock stock : stocks) {
            stock.save(session);
        }
    }

    /*
     * Public methods
     */

    public void setPkWsdId(int n) { mnPkWsdId = n; }
    public void setNumber(int n) { mnNumber = n; }
    public void setDate(Date t) { mtDate = t; }
    public void setReference(String s) { msReference = s; }
    public void setAmount_r(double d) { mdAmount_r = d; }
    public void setMass_r(double d) { mdMass_r = d; }
    public void setDeleted(boolean b) { mbDeleted = b; }
    public void setSystem(boolean b) { mbSystem = b; }
    public void setFkMoveClassId(int n) { mnFkMoveClassId = n; }
    public void setFkMoveTypeId(int n) { mnFkMoveTypeId = n; }
    public void setFkTransactMoveTypeId(int n) { mnFkTransactMoveTypeId = n; }
    public void setFkMfgMoveTypeId(int n) { mnFkMfgMoveTypeId = n; }
    public void setFkStockAdjustTypeId(int n) { mnFkStockAdjustTypeId = n; }
    public void setFkItemTypeId(int n) { mnFkItemTypeId = n; }
    public void setFkWarehouseId(int n) { mnFkWarehouseId = n; }
    public void setFkWsdId_n(int n) { mnFkWsdId_n = n; }
    public void setFkBizPartnerId_n(int n) { mnFkBizPartnerId_n = n; }
    public void setFkDepartId_n(int n) { mnFkDepartId_n = n; }
    public void setFkLineId_n(int n) { mnFkLineId_n = n; }
    public void setFkJobId_n(int n) { mnFkJobId_n = n; }
    public void setFkUserInsertId(int n) { mnFkUserInsertId = n; }
    public void setFkUserUpdateId(int n) { mnFkUserUpdateId = n; }
    public void setTsUserInsert(Date t) { mtTsUserInsert = t; }
    public void setTsUserUpdate(Date t) { mtTsUserUpdate = t; }

    public int getPkWsdId() { return mnPkWsdId; }
    public int getNumber() { return mnNumber; }
    public Date getDate() { return mtDate; }
    public String getReference() { return msReference; }
    public double getAmount_r() { return mdAmount_r; }
    public double getMass_r() { return mdMass_r; }
    public boolean isDeleted() { return mbDeleted; }
    public boolean isSystem() { return mbSystem; }
    public int getFkMoveClassId() { return mnFkMoveClassId; }
    public int getFkMoveTypeId() { return mnFkMoveTypeId; }
    public int getFkTransactMoveTypeId() { return mnFkTransactMoveTypeId; }
    public int getFkMfgMoveTypeId() { return mnFkMfgMoveTypeId; }
    public int getFkStockAdjustTypeId() { return mnFkStockAdjustTypeId; }
    public int getFkItemTypeId() { return mnFkItemTypeId; }
    public int getFkWarehouseId() { return mnFkWarehouseId; }
    public int getFkWsdId_n() { return mnFkWsdId_n; }
    public int getFkBizPartnerId_n() { return mnFkBizPartnerId_n; }
    public int getFkDepartId_n() { return mnFkDepartId_n; }
    public int getFkLineId_n() { return mnFkLineId_n; }
    public int getFkJobId_n() { return mnFkJobId_n; }
    public int getFkUserInsertId() { return mnFkUserInsertId; }
    public int getFkUserUpdateId() { return mnFkUserUpdateId; }
    public Date getTsUserInsert() { return mtTsUserInsert; }
    public Date getTsUserUpdate() { return mtTsUserUpdate; }

    public ArrayList<DDbWsdRow> getRows() { return maRows; }
    
    public int[] getKeyMoveType() { return new int[] { mnFkMoveClassId, mnFkMoveTypeId }; }
    
    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkWsdId = pk[0];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkWsdId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkWsdId = 0;
        mnNumber = 0;
        mtDate = null;
        msReference = "";
        mdAmount_r = 0;
        mdMass_r = 0;
        mbDeleted = false;
        mbSystem = false;
        mnFkMoveClassId = 0;
        mnFkMoveTypeId = 0;
        mnFkTransactMoveTypeId = 0;
        mnFkMfgMoveTypeId = 0;
        mnFkStockAdjustTypeId = 0;
        mnFkItemTypeId = 0;
        mnFkWarehouseId = 0;
        mnFkWsdId_n = 0;
        mnFkBizPartnerId_n = 0;
        mnFkDepartId_n = 0;
        mnFkLineId_n = 0;
        mnFkJobId_n = 0;
        mnFkUserInsertId = 0;
        mnFkUserUpdateId = 0;
        mtTsUserInsert = null;
        mtTsUserUpdate = null;
        
        maRows.clear();
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_wsd = " + mnPkWsdId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_wsd = " + pk[0] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkWsdId = 0;

        msSql = "SELECT COALESCE(MAX(id_wsd), 0) + 1 FROM " + getSqlTable();
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkWsdId = resultSet.getInt(1);
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
            mnPkWsdId = resultSet.getInt("id_wsd");
            mnNumber = resultSet.getInt("num");
            mtDate = resultSet.getDate("dat");
            msReference = resultSet.getString("ref");
            mdAmount_r = resultSet.getDouble("amt_r");
            mdMass_r = resultSet.getDouble("mass_r");
            mbDeleted = resultSet.getBoolean("b_del");
            mbSystem = resultSet.getBoolean("b_sys");
            mnFkMoveClassId = resultSet.getInt("fk_mov_cl");
            mnFkMoveTypeId = resultSet.getInt("fk_mov_tp");
            mnFkTransactMoveTypeId = resultSet.getInt("fk_trn_tp");
            mnFkMfgMoveTypeId = resultSet.getInt("fk_mfg_tp");
            mnFkStockAdjustTypeId = resultSet.getInt("fk_adj_tp");
            mnFkItemTypeId = resultSet.getInt("fk_itm_tp");
            mnFkWarehouseId = resultSet.getInt("fk_whs");
            mnFkWsdId_n = resultSet.getInt("fk_wsd_n");
            mnFkBizPartnerId_n = resultSet.getInt("fk_bpr_n");
            mnFkDepartId_n = resultSet.getInt("fk_dep_n");
            mnFkLineId_n = resultSet.getInt("fk_lin_n");
            mnFkJobId_n = resultSet.getInt("fk_job_n");
            mnFkUserInsertId = resultSet.getInt("fk_usr_ins");
            mnFkUserUpdateId = resultSet.getInt("fk_usr_upd");
            mtTsUserInsert = resultSet.getTimestamp("ts_usr_ins");
            mtTsUserUpdate = resultSet.getTimestamp("ts_usr_upd");
            
            // Read aswell child registries:
            
            statement = session.getStatement().getConnection().createStatement();
            
            msSql = "SELECT id_row FROM " + DModConsts.TablesMap.get(DModConsts.S_WSD_ROW) + " " + getSqlWhere();
            resultSet = statement.executeQuery(msSql);
            while (resultSet.next()) {
                DDbWsdRow child = new DDbWsdRow();
                child.read(session, new int[] { mnPkWsdId, resultSet.getInt(1) });
                maRows.add(child);
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
        
        computeWsd();
        
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
                    mnPkWsdId + ", " + 
                    mnNumber + ", " + 
                    "'" + DLibUtils.DbmsDateFormatDate.format(mtDate) + "', " + 
                    "'" + msReference + "', " + 
                    mdAmount_r + ", " + 
                    mdMass_r + ", " + 
                    (mbDeleted ? 1 : 0) + ", " + 
                    (mbSystem ? 1 : 0) + ", " + 
                    mnFkMoveClassId + ", " + 
                    mnFkMoveTypeId + ", " + 
                    mnFkTransactMoveTypeId + ", " + 
                    mnFkMfgMoveTypeId + ", " + 
                    mnFkStockAdjustTypeId + ", " + 
                    mnFkItemTypeId + ", " + 
                    mnFkWarehouseId + ", " + 
                    (mnFkWsdId_n == DLibConsts.UNDEFINED ? "NULL" : "" + mnFkWsdId_n) + ", " + 
                    (mnFkBizPartnerId_n == DLibConsts.UNDEFINED ? "NULL" : "" + mnFkBizPartnerId_n) + ", " + 
                    (mnFkDepartId_n == DLibConsts.UNDEFINED ? "NULL" : "" + mnFkDepartId_n) + ", " + 
                    (mnFkLineId_n == DLibConsts.UNDEFINED ? "NULL" : "" + mnFkLineId_n) + ", " + 
                    (mnFkJobId_n == DLibConsts.UNDEFINED ? "NULL" : "" + mnFkJobId_n) + ", " + 
                    mnFkUserInsertId + ", " + 
                    mnFkUserUpdateId + ", " + 
                    "NOW()" + ", " + 
                    "NOW()" + " " + 
                    ")";
        }
        else {
            mnFkUserUpdateId = session.getUser().getPkUserId();

            msSql = "UPDATE " + getSqlTable() + " SET " +
                    //"id_wsd = " + mnPkWsdId + ", " +
                    "num = " + mnNumber + ", " +
                    "dat = '" + DLibUtils.DbmsDateFormatDate.format(mtDate) + "', " +
                    "ref = '" + msReference + "', " +
                    "amt_r = " + mdAmount_r + ", " +
                    "mass_r = " + mdMass_r + ", " +
                    "b_del = " + (mbDeleted ? 1 : 0) + ", " +
                    "b_sys = " + (mbSystem ? 1 : 0) + ", " +
                    "fk_mov_cl = " + mnFkMoveClassId + ", " +
                    "fk_mov_tp = " + mnFkMoveTypeId + ", " +
                    "fk_trn_tp = " + mnFkTransactMoveTypeId + ", " +
                    "fk_mfg_tp = " + mnFkMfgMoveTypeId + ", " +
                    "fk_adj_tp = " + mnFkStockAdjustTypeId + ", " +
                    "fk_itm_tp = " + mnFkItemTypeId + ", " +
                    "fk_whs = " + mnFkWarehouseId + ", " +
                    "fk_wsd_n = " + (mnFkWsdId_n == DLibConsts.UNDEFINED ? "NULL" : "" + mnFkWsdId_n) + ", " +
                    "fk_bpr_n = " + (mnFkBizPartnerId_n == DLibConsts.UNDEFINED ? "NULL" : "" + mnFkBizPartnerId_n) + ", " +
                    "fk_dep_n = " + (mnFkDepartId_n == DLibConsts.UNDEFINED ? "NULL" : "" + mnFkDepartId_n) + ", " +
                    "fk_lin_n = " + (mnFkLineId_n == DLibConsts.UNDEFINED ? "NULL" : "" + mnFkLineId_n) + ", " +
                    "fk_job_n = " + (mnFkJobId_n == DLibConsts.UNDEFINED ? "NULL" : "" + mnFkJobId_n) + ", " +
                    //"fk_usr_ins = " + mnFkUserInsertId + ", " +
                    "fk_usr_upd = " + mnFkUserUpdateId + ", " +
                    //"ts_usr_ins = " + "NOW()" + ", " +
                    "ts_usr_upd = " + "NOW()" + " " +
                    getSqlWhere();
        }

        session.getStatement().execute(msSql);
        
        // Save aswell child registries:
        
        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.S_WSD_ROW) + " " + getSqlWhere();
        session.getStatement().execute(msSql);

        for (DDbWsdRow child : maRows) {
            child.setPkWsdId(mnPkWsdId);
            child.save(session);
        }
        
        saveStock(session);

        // Finish registry saving:
            
        mbRegistryNew = false;
        mnQueryResultId = DDbConsts.SAVE_OK;
    }

    @Override
    public DDbWsd clone() throws CloneNotSupportedException {
        DDbWsd registry = new DDbWsd();

        registry.setPkWsdId(this.getPkWsdId());
        registry.setNumber(this.getNumber());
        registry.setDate(this.getDate());
        registry.setReference(this.getReference());
        registry.setAmount_r(this.getAmount_r());
        registry.setMass_r(this.getMass_r());
        registry.setDeleted(this.isDeleted());
        registry.setSystem(this.isSystem());
        registry.setFkMoveClassId(this.getFkMoveClassId());
        registry.setFkMoveTypeId(this.getFkMoveTypeId());
        registry.setFkTransactMoveTypeId(this.getFkTransactMoveTypeId());
        registry.setFkMfgMoveTypeId(this.getFkMfgMoveTypeId());
        registry.setFkStockAdjustTypeId(this.getFkStockAdjustTypeId());
        registry.setFkItemTypeId(this.getFkItemTypeId());
        registry.setFkWarehouseId(this.getFkWarehouseId());
        registry.setFkWsdId_n(this.getFkWsdId_n());
        registry.setFkBizPartnerId_n(this.getFkBizPartnerId_n());
        registry.setFkDepartId_n(this.getFkDepartId_n());
        registry.setFkLineId_n(this.getFkLineId_n());
        registry.setFkJobId_n(this.getFkJobId_n());
        registry.setFkUserInsertId(this.getFkUserInsertId());
        registry.setFkUserUpdateId(this.getFkUserUpdateId());
        registry.setTsUserInsert(this.getTsUserInsert());
        registry.setTsUserUpdate(this.getTsUserUpdate());

        for (DDbWsdRow child : maRows) {
            registry.getRows().add(child.clone());
        }
        
        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
    
    @Override
    public void postInitMembers(final DGuiParams params) {
        Object value = null;
        
        if (params != null) {
            value = params.getPostInitValuesMap().get(PARAM_MOV_TP);
            if (value != null) {
                mnFkMoveClassId = ((int[]) value)[0];
                mnFkMoveTypeId = ((int[]) value)[0];
            }

            value = params.getPostInitValuesMap().get(PARAM_TRN_TP);
            if (value != null) {
                mnFkTransactMoveTypeId = (int) value;
            }

            value = params.getPostInitValuesMap().get(PARAM_MFG_TP);
            if (value != null) {
                mnFkMfgMoveTypeId = (int) value;
            }

            value = params.getPostInitValuesMap().get(PARAM_DPT);
            if (value != null) {
                mnFkDepartId_n = (int) value;
            }

            value = params.getPostInitValuesMap().get(PARAM_LIN);
            if (value != null) {
                mnFkLineId_n = (int) value;
            }

            value = params.getPostInitValuesMap().get(PARAM_JOB);
            if (value != null) {
                mnFkJobId_n = (int) value;
            }
        }
    }
    
    public void validateNewRegistry() throws Exception {
        if (DLibUtils.belongsTo(mnFkMoveTypeId, new int[] { DModSysConsts.SS_MOV_TP_IN_SAL[1], DModSysConsts.SS_MOV_TP_IN_PUR[1] })) {
            if (mnFkTransactMoveTypeId == DLibConsts.UNDEFINED) {
                throw new Exception(DGuiConsts.ERR_MSG_FIELD_REQ + "'mov. transacción'.");
            }
            else if (mnFkMfgMoveTypeId != DLibConsts.UNDEFINED) {
                throw new Exception(DGuiConsts.ERR_MSG_FIELD_DIF + "'mov. producción': 'N/A'.");
            }
            else if (mnFkStockAdjustTypeId != DLibConsts.UNDEFINED) {
                throw new Exception(DGuiConsts.ERR_MSG_FIELD_DIF + "'tipo ajuste': 'N/A'.");
            }
            else if (mnFkDepartId_n != DLibConsts.UNDEFINED) {
                throw new Exception(DGuiConsts.ERR_MSG_FIELD_DIF + "'depto. producción': 'N/A'.");
            }
            else if (mnFkLineId_n != DLibConsts.UNDEFINED) {
                throw new Exception(DGuiConsts.ERR_MSG_FIELD_DIF + "'línea producción': 'N/A'.");
            }
            else if (mnFkJobId_n != DLibConsts.UNDEFINED) {
                throw new Exception(DGuiConsts.ERR_MSG_FIELD_DIF + "'orden producción': 'N/A'.");
            }
        }
        else if (DLibUtils.belongsTo(mnFkMoveTypeId, new int[] { DModSysConsts.SS_MOV_TP_IN_MFG[1] })) {
            if (mnFkMfgMoveTypeId== DLibConsts.UNDEFINED) {
                throw new Exception(DGuiConsts.ERR_MSG_FIELD_REQ + "'mov. producción'.");
            }
            else if (mnFkStockAdjustTypeId != DLibConsts.UNDEFINED) {
                throw new Exception(DGuiConsts.ERR_MSG_FIELD_DIF + "'tipo ajuste': 'N/A'.");
            }
            else if (mnFkDepartId_n == DLibConsts.UNDEFINED) {
                throw new Exception(DGuiConsts.ERR_MSG_FIELD_REQ + "'depto. producción'.");
            }
            else if (mnFkLineId_n == DLibConsts.UNDEFINED) {
                throw new Exception(DGuiConsts.ERR_MSG_FIELD_REQ + "'línea producción'.");
            }
            else if (mnFkJobId_n == DLibConsts.UNDEFINED) {
                throw new Exception(DGuiConsts.ERR_MSG_FIELD_REQ + "'orden producción'.");
            }
        }
    }
    
    public boolean isUtilStockAdjustTypeReq() {
        return DLibUtils.belongsTo(mnFkMoveTypeId, new int[] { DModSysConsts.SS_MOV_TP_IN_ADJ[1] });
    }
    
    public boolean isUtilBizPartnerReq() {
        return DLibUtils.belongsTo(mnFkMoveTypeId, new int[] { DModSysConsts.SS_MOV_TP_IN_SAL[1], DModSysConsts.SS_MOV_TP_IN_PUR[1] });
    }
    
    public int getUtilBizPartnerType() {
        return DCfgUtils.getBizPartnerTypeForWhsMoveType(getKeyMoveType());
    }
    
    public void computeWsd() {
        int row = 0;
        
        mdAmount_r = 0;
        mdMass_r = 0;
        
        for (DDbWsdRow child : maRows) {
            child.setPkRowId(++row);
            
            if (!child.isDeleted()) {
                mdAmount_r = DLibUtils.round(mdAmount_r + child.getAmount_r(), DLibUtils.getDecimalFormatAmount().getMaximumFractionDigits());
                mdMass_r = DLibUtils.round(mdMass_r + child.getMass_r(), DLibUtils.getDecimalFormatAmount().getMaximumFractionDigits());
            }
        }
    }
    
    public void computeNumber(final DGuiSession session) throws Exception {
        String sql = "";
        ResultSet resultSet = null;
        
        sql = "SELECT COALESCE(MAX(num), 0) + 1 "
                + "FROM " + DModConsts.TablesMap.get(DModConsts.S_WSD) + " "
                + "WHERE fk_mov_cl=" + mnFkMoveClassId + " AND fk_mov_tp=" + mnFkMoveTypeId + " AND b_del=0 ";
        resultSet = session.getStatement().executeQuery(sql);
        if (!resultSet.next()) {
            throw new Exception(DDbConsts.ERR_MSG_REG_NOT_FOUND);
        }
        else {
            mnNumber = resultSet.getInt(1);
        }
    }
}
