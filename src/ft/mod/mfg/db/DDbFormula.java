/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.mfg.db;

import ft.mod.DModConsts;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import sba.gui.util.DUtilConsts;
import sba.lib.DLibConsts;
import sba.lib.db.DDbConsts;
import sba.lib.db.DDbRegistryUser;
import sba.lib.gui.DGuiSession;

/**
 *
 * @author Sergio Flores
 */
public class DDbFormula extends DDbRegistryUser {

    protected int mnPkFormulaId;
    protected String msCode;
    protected String msName;
    protected String msNameReference;
    protected double mdDefaultVar1;
    protected double mdQuantity;
    protected boolean mbQuantityByVar1;
    /*
    protected boolean mbDeleted;
    protected boolean mbSystem;
    */
    protected int mnFkFormulaTypeId;
    protected int mnFkItemTypeId;
    protected int mnFkReferenceTypeId;
    protected int mnFkItemFamilyId_n;
    protected int mnFkItemGroupId_n;
    protected int mnFkItemId_n;
    protected int mnFkUnitId;
    protected int mnFkPresentId;
    /*
    protected int mnFkUserInsertId;
    protected int mnFkUserUpdateId;
    protected Date mtTsUserInsert;
    protected Date mtTsUserUpdate;
    */
    
    protected ArrayList<DDbFormulaComp> maChildComps;
    
    public DDbFormula() {
        super(DModConsts.MU_FRM);
        maChildComps = new ArrayList<>();
        initRegistry();
    }
/*    
    public static String computeName(final DGuiSession session, final String name, final String nameReference) {
        String separator = ((DDbConfig) session.getConfigCompany()).getNameSeparatorFormatted();
        
        return DLibUtils.textTrim((name.isEmpty() ? "?" : name) + (nameReference.isEmpty() ? "" : separator + nameReference));
    }
    
    public void updateSnapshotData(final DGuiSession session) throws Exception {
        int itemTypeId = DLibConsts.UNDEFINED;
        int unitId = DLibConsts.UNDEFINED;
        int presentId = DLibConsts.UNDEFINED;
        DDbRegistry registry = null;
        
        switch (mnFkReferenceTypeId) {
            case DModSysConsts.MS_REF_TP_FAM:
                registry = session.readRegistry(DModConsts.CU_FAM, new int[] { mnFkItemFamilyId_n }, DDbConsts.MODE_STEALTH);
                itemTypeId = ((DDbFamily) registry).getFkItemTypeId();
                unitId = DModSysConsts.CU_UNT_NA;
                presentId = DModSysConsts.CU_PRS_NA;
                break;
            case DModSysConsts.MS_REF_TP_GRP:
                registry = session.readRegistry(DModConsts.CU_GRP, new int[] { mnFkItemGroupId_n }, DDbConsts.MODE_STEALTH);
                itemTypeId = ((DDbItemGroup) registry).getXtaFkItemTypeId();
                unitId = DModSysConsts.CU_UNT_NA;
                presentId = DModSysConsts.CU_PRS_NA;
                break;
            case DModSysConsts.MS_REF_TP_ITM:
                registry = session.readRegistry(DModConsts.CU_ITM, new int[] { mnFkItemId_n }, DDbConsts.MODE_STEALTH);
                itemTypeId = ((DDbItem) registry).getXtaFkItemTypeId();
                unitId = ((DDbItem) registry).getFkUnitId();
                presentId = ((DDbItem) registry).getFkPresentId();
                break;
            default:
                throw new Exception(DLibConsts.ERR_MSG_OPTION_UNKNOWN);
        }

        msCode = registry.getCode();
        msName = computeName(session, registry.getName(), msNameReference);
        mnFkItemTypeId = itemTypeId;
        mnFkUnitId = unitId;
        mnFkPresentId = presentId;
    }
*/
    public void setPkFormulaId(int n) { mnPkFormulaId = n; }
    public void setCode(String s) { msCode = s; }
    public void setName(String s) { msName = s; }
    public void setNameReference(String s) { msNameReference = s; }
    public void setDefaultVar1(double d) { mdDefaultVar1 = d; }
    public void setQuantity(double d) { mdQuantity = d; }
    public void setQuantityByVar1(boolean b) { mbQuantityByVar1 = b; }
    public void setDeleted(boolean b) { mbDeleted = b; }
    public void setSystem(boolean b) { mbSystem = b; }
    public void setFkFormulaTypeId(int n) { mnFkFormulaTypeId = n; }
    public void setFkItemTypeId(int n) { mnFkItemTypeId = n; }
    public void setFkReferenceTypeId(int n) { mnFkReferenceTypeId = n; }
    public void setFkItemFamilyId_n(int n) { mnFkItemFamilyId_n = n; }
    public void setFkItemGroupId_n(int n) { mnFkItemGroupId_n = n; }
    public void setFkItemId_n(int n) { mnFkItemId_n = n; }
    public void setFkUnitId(int n) { mnFkUnitId = n; }
    public void setFkPresentId(int n) { mnFkPresentId = n; }
    public void setFkUserInsertId(int n) { mnFkUserInsertId = n; }
    public void setFkUserUpdateId(int n) { mnFkUserUpdateId = n; }
    public void setTsUserInsert(Date t) { mtTsUserInsert = t; }
    public void setTsUserUpdate(Date t) { mtTsUserUpdate = t; }

    public int getPkFormulaId() { return mnPkFormulaId; }
    public String getCode() { return msCode; }
    public String getName() { return msName; }
    public String getNameReference() { return msNameReference; }
    public double getDefaultVar1() { return mdDefaultVar1; }
    public double getQuantity() { return mdQuantity; }
    public boolean isQuantityByVar1() { return mbQuantityByVar1; }
    public boolean isDeleted() { return mbDeleted; }
    public boolean isSystem() { return mbSystem; }
    public int getFkFormulaTypeId() { return mnFkFormulaTypeId; }
    public int getFkItemTypeId() { return mnFkItemTypeId; }
    public int getFkReferenceTypeId() { return mnFkReferenceTypeId; }
    public int getFkItemFamilyId_n() { return mnFkItemFamilyId_n; }
    public int getFkItemGroupId_n() { return mnFkItemGroupId_n; }
    public int getFkItemId_n() { return mnFkItemId_n; }
    public int getFkUnitId() { return mnFkUnitId; }
    public int getFkPresentId() { return mnFkPresentId; }
    public int getFkUserInsertId() { return mnFkUserInsertId; }
    public int getFkUserUpdateId() { return mnFkUserUpdateId; }
    public Date getTsUserInsert() { return mtTsUserInsert; }
    public Date getTsUserUpdate() { return mtTsUserUpdate; }

    public ArrayList<DDbFormulaComp> getChildComps() { return maChildComps; }
    
    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkFormulaId = pk[0];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkFormulaId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkFormulaId = 0;
        msCode = "";
        msName = "";
        msNameReference = "";
        mdDefaultVar1 = 0;
        mdQuantity = 0;
        mbQuantityByVar1 = false;
        mbDeleted = false;
        mbSystem = false;
        mnFkFormulaTypeId = 0;
        mnFkItemTypeId = 0;
        mnFkReferenceTypeId = 0;
        mnFkItemFamilyId_n = 0;
        mnFkItemGroupId_n = 0;
        mnFkItemId_n = 0;
        mnFkUnitId = 0;
        mnFkPresentId = 0;
        mnFkUserInsertId = 0;
        mnFkUserUpdateId = 0;
        mtTsUserInsert = null;
        mtTsUserUpdate = null;
        
        maChildComps.clear();
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_frm = " + mnPkFormulaId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_frm = " + pk[0] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkFormulaId = 0;

        msSql = "SELECT COALESCE(MAX(id_frm), 0) + 1 FROM " + getSqlTable();
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkFormulaId = resultSet.getInt(1);
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
            mnPkFormulaId = resultSet.getInt("id_frm");
            msCode = resultSet.getString("code");
            msName = resultSet.getString("name");
            msNameReference = resultSet.getString("name_ref");
            mdDefaultVar1 = resultSet.getDouble("def_var_1");
            mdQuantity = resultSet.getDouble("qty");
            mbQuantityByVar1 = resultSet.getBoolean("b_qty_var_1");
            mbDeleted = resultSet.getBoolean("b_del");
            mbSystem = resultSet.getBoolean("b_sys");
            mnFkFormulaTypeId = resultSet.getInt("fk_frm_tp");
            mnFkItemTypeId = resultSet.getInt("fk_itm_tp");
            mnFkReferenceTypeId = resultSet.getInt("fk_ref_tp");
            mnFkItemFamilyId_n = resultSet.getInt("fk_fam_n");
            mnFkItemGroupId_n = resultSet.getInt("fk_grp_n");
            mnFkItemId_n = resultSet.getInt("fk_itm");
            mnFkUnitId = resultSet.getInt("fk_unt");
            mnFkPresentId = resultSet.getInt("fk_prs");
            mnFkUserInsertId = resultSet.getInt("fk_usr_ins");
            mnFkUserUpdateId = resultSet.getInt("fk_usr_upd");
            mtTsUserInsert = resultSet.getTimestamp("ts_usr_ins");
            mtTsUserUpdate = resultSet.getTimestamp("ts_usr_upd");

            // Read aswell child registries:

            statement = session.getStatement().getConnection().createStatement();
/*
            msSql = "SELECT id_cmp FROM " + DModConsts.TablesMap.get(DModConsts.MU_FRM_CMP) + " " + getSqlWhere();
            resultSet = statement.executeQuery(msSql);
            while (resultSet.next()) {
                DDbFormulaComp child = new DDbFormulaComp();
                child.read(session, new int[] { mnPkFormulaId, resultSet.getInt(1) });
                maChildComps.add(child);
            }
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

  //      updateSnapshotData(session);

        if (mbRegistryNew) {
            computePrimaryKey(session);
            mbDeleted = false;
            mbSystem = false;
            mnFkUserInsertId = session.getUser().getPkUserId();
            mnFkUserUpdateId = DUtilConsts.USR_NA_ID;
            
            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkFormulaId + ", " + 
                    "'" + msCode + "', " + 
                    "'" + msName + "', " + 
                    "'" + msNameReference + "', " + 
                    mdDefaultVar1 + ", " + 
                    mdQuantity + ", " + 
                    (mbQuantityByVar1 ? 1 : 0) + ", " + 
                    (mbDeleted ? 1 : 0) + ", " + 
                    (mbSystem ? 1 : 0) + ", " + 
                    mnFkFormulaTypeId + ", " + 
                    mnFkItemTypeId + ", " + 
                    mnFkReferenceTypeId + ", " + 
                    (mnFkItemFamilyId_n == DLibConsts.UNDEFINED ? "" : "" + mnFkItemFamilyId_n) + ", " + 
                    (mnFkItemGroupId_n == DLibConsts.UNDEFINED ? "" : "" + mnFkItemGroupId_n) + ", " + 
                    (mnFkItemId_n == DLibConsts.UNDEFINED ? "" : "" + mnFkItemId_n) + ", " + 
                    mnFkUnitId + ", " + 
                    mnFkPresentId + ", " + 
                    mnFkUserInsertId + ", " + 
                    mnFkUserUpdateId + ", " + 
                    "NOW()" + ", " + 
                    "NOW()" + " " + 
                    ")";
        }
        else {
            mnFkUserUpdateId = session.getUser().getPkUserId();

            msSql = "UPDATE " + getSqlTable() + " SET " +
                    //"id_frm = " + mnPkFormulaId + ", " +
                    "code = '" + msCode + "', " +
                    "name = '" + msName + "', " +
                    "name_ref = '" + msNameReference + "', " +
                    "def_var_1 = " + mdDefaultVar1 + ", " +
                    "qty = " + mdQuantity + ", " +
                    "b_qty_var_1 = " + (mbQuantityByVar1 ? 1 : 0) + ", " +
                    "b_del = " + (mbDeleted ? 1 : 0) + ", " +
                    "b_sys = " + (mbSystem ? 1 : 0) + ", " +
                    "fk_frm_tp = " + mnFkFormulaTypeId + ", " +
                    "fk_itm_tp = " + mnFkItemTypeId + ", " +
                    "fk_ref_tp = " + mnFkReferenceTypeId + ", " +
                    "fk_fam_n = " + (mnFkItemFamilyId_n == DLibConsts.UNDEFINED ? "" : "" + mnFkItemFamilyId_n) + ", " +
                    "fk_grp_n = " + (mnFkItemGroupId_n == DLibConsts.UNDEFINED ? "" : "" + mnFkItemGroupId_n) + ", " +
                    "fk_itm = " + (mnFkItemId_n == DLibConsts.UNDEFINED ? "" : "" + mnFkItemId_n) + ", " +
                    "fk_unt = " + mnFkUnitId + ", " +
                    "fk_prs = " + mnFkPresentId + ", " +
                    //"fk_usr_ins = " + mnFkUserInsertId + ", " +
                    "fk_usr_upd = " + mnFkUserUpdateId + ", " +
                    //"ts_usr_ins = " + "NOW()" + ", " +
                    "ts_usr_upd = " + "NOW()" + " " +
                    getSqlWhere();
        }

        session.getStatement().execute(msSql);
        
        // Save aswell child registries:
/*
        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.MU_FRM_CMP) + " " + getSqlWhere();
        session.getStatement().execute(msSql);

        for (DDbFormulaComp child : maChildComps) {
            child.setPkFormulaId(mnPkFormulaId);
            child.setRegistryNew(true);
            child.save(session);
        }
*/
        // Finish registry updating:

        mbRegistryNew = false;
        mnQueryResultId = DDbConsts.SAVE_OK;
    }

    @Override
    public DDbFormula clone() throws CloneNotSupportedException {
        DDbFormula registry = new DDbFormula();

        registry.setPkFormulaId(this.getPkFormulaId());
        registry.setCode(this.getCode());
        registry.setName(this.getName());
        registry.setNameReference(this.getNameReference());
        registry.setDefaultVar1(this.getDefaultVar1());
        registry.setQuantity(this.getQuantity());
        registry.setQuantityByVar1(this.isQuantityByVar1());
        registry.setDeleted(this.isDeleted());
        registry.setSystem(this.isSystem());
        registry.setFkFormulaTypeId(this.getFkFormulaTypeId());
        registry.setFkItemTypeId(this.getFkItemTypeId());
        registry.setFkReferenceTypeId(this.getFkReferenceTypeId());
        registry.setFkItemFamilyId_n(this.getFkItemFamilyId_n());
        registry.setFkItemGroupId_n(this.getFkItemGroupId_n());
        registry.setFkItemId_n(this.getFkItemId_n());
        registry.setFkUnitId(this.getFkUnitId());
        registry.setFkPresentId(this.getFkPresentId());
        registry.setFkUserInsertId(this.getFkUserInsertId());
        registry.setFkUserUpdateId(this.getFkUserUpdateId());
        registry.setTsUserInsert(this.getTsUserInsert());
        registry.setTsUserUpdate(this.getTsUserUpdate());

        for (DDbFormulaComp child : maChildComps) {
            registry.getChildComps().add(child.clone());
        }

        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
}
