/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.mfg.db;

import ft.mod.DModConsts;
import ft.mod.cfg.db.DDbItem;
import ft.mod.cfg.db.DDbPresent;
import ft.mod.cfg.db.DDbUnit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import sba.gui.util.DUtilConsts;
import sba.lib.DLibUtils;
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
    protected String msReference;
    protected double mdQuantity;
    protected double mdMass_r;
    /*
    protected boolean mbDeleted;
    protected boolean mbSystem;
    */
    protected int mnFkFormulaTypeId;
    protected int mnFkItemId;
    protected int mnFkItemTypeId;
    protected int mnFkUnitId;
    protected int mnFkPresentId;
    /*
    protected int mnFkUserInsertId;
    protected int mnFkUserUpdateId;
    protected Date mtTsUserInsert;
    protected Date mtTsUserUpdate;
    */
    
    protected DDbItem moRegItem;
    protected DDbUnit moRegUnit;
    protected DDbPresent moRegPresent;
    
    protected ArrayList<DDbFormulaComp> maChildComps;
    protected ArrayList<DDbFormulaByProd> maChildByProds;
    
    public DDbFormula() {
        super(DModConsts.MU_FRM);
        maChildComps = new ArrayList<>();
        maChildByProds = new ArrayList<>();
        initRegistry();
    }
    
    private void readRegMembers(final DGuiSession session, final boolean update) {
        moRegItem = (DDbItem) session.readRegistry(DModConsts.CU_ITM, new int[] { mnFkItemId });
        
        if (update) {
            msCode = moRegItem.getCode();
            msName = moRegItem.getName();
            mnFkItemTypeId = moRegItem.getXtaFkItemTypeId();
            mnFkUnitId = moRegItem.getFkUnitId();
            mnFkPresentId = moRegItem.getFkPresentId();
        }
        
        moRegUnit = (DDbUnit) session.readRegistry(DModConsts.CU_UOM, new int[] { mnFkUnitId });
        moRegPresent = (DDbPresent) session.readRegistry(DModConsts.CU_PRE, new int[] { mnFkPresentId });
    }
    
    public void setPkFormulaId(int n) { mnPkFormulaId = n; }
    public void setCode(String s) { msCode = s; }
    public void setName(String s) { msName = s; }
    public void setReference(String s) { msReference = s; }
    public void setQuantity(double d) { mdQuantity = d; }
    public void setMass_r(double d) { mdMass_r = d; }
    public void setDeleted(boolean b) { mbDeleted = b; }
    public void setSystem(boolean b) { mbSystem = b; }
    public void setFkFormulaTypeId(int n) { mnFkFormulaTypeId = n; }
    public void setFkItemId(int n) { mnFkItemId = n; }
    public void setFkItemTypeId(int n) { mnFkItemTypeId = n; }
    public void setFkUnitId(int n) { mnFkUnitId = n; }
    public void setFkPresentId(int n) { mnFkPresentId = n; }
    public void setFkUserInsertId(int n) { mnFkUserInsertId = n; }
    public void setFkUserUpdateId(int n) { mnFkUserUpdateId = n; }
    public void setTsUserInsert(Date t) { mtTsUserInsert = t; }
    public void setTsUserUpdate(Date t) { mtTsUserUpdate = t; }

    public int getPkFormulaId() { return mnPkFormulaId; }
    public String getCode() { return msCode; }
    public String getName() { return msName; }
    public String getReference() { return msReference; }
    public double getQuantity() { return mdQuantity; }
    public double getMass_r() { return mdMass_r; }
    public boolean isDeleted() { return mbDeleted; }
    public boolean isSystem() { return mbSystem; }
    public int getFkFormulaTypeId() { return mnFkFormulaTypeId; }
    public int getFkItemId() { return mnFkItemId; }
    public int getFkItemTypeId() { return mnFkItemTypeId; }
    public int getFkUnitId() { return mnFkUnitId; }
    public int getFkPresentId() { return mnFkPresentId; }
    public int getFkUserInsertId() { return mnFkUserInsertId; }
    public int getFkUserUpdateId() { return mnFkUserUpdateId; }
    public Date getTsUserInsert() { return mtTsUserInsert; }
    public Date getTsUserUpdate() { return mtTsUserUpdate; }

    public void setRegItem(DDbItem o) { moRegItem = o; }
    public void setRegUnit(DDbUnit o) { moRegUnit = o; }
    public void setRegPresent(DDbPresent o) { moRegPresent = o; }
    
    public DDbItem getRegItem() { return moRegItem; }
    public DDbUnit getRegUnit() { return moRegUnit; }
    public DDbPresent getRegPresent() { return moRegPresent; }
    
    public ArrayList<DDbFormulaComp> getChildComps() { return maChildComps; }
    public ArrayList<DDbFormulaByProd> getChildByProds() { return maChildByProds; }
    
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
        msReference = "";
        mdQuantity = 0;
        mdMass_r = 0;
        mbDeleted = false;
        mbSystem = false;
        mnFkFormulaTypeId = 0;
        mnFkItemId = 0;
        mnFkItemTypeId = 0;
        mnFkUnitId = 0;
        mnFkPresentId = 0;
        mnFkUserInsertId = 0;
        mnFkUserUpdateId = 0;
        mtTsUserInsert = null;
        mtTsUserUpdate = null;
        
        moRegItem = null;
        moRegUnit = null;
        moRegPresent = null;
        
        maChildComps.clear();
        maChildByProds.clear();
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
            msReference = resultSet.getString("ref");
            mdQuantity = resultSet.getDouble("qty");
            mdMass_r = resultSet.getDouble("mass_r");
            mbDeleted = resultSet.getBoolean("b_del");
            mbSystem = resultSet.getBoolean("b_sys");
            mnFkFormulaTypeId = resultSet.getInt("fk_frm_tp");
            mnFkItemId = resultSet.getInt("fk_itm");
            mnFkItemTypeId = resultSet.getInt("fk_itm_tp");
            mnFkUnitId = resultSet.getInt("fk_uom");
            mnFkPresentId = resultSet.getInt("fk_pre");
            mnFkUserInsertId = resultSet.getInt("fk_usr_ins");
            mnFkUserUpdateId = resultSet.getInt("fk_usr_upd");
            mtTsUserInsert = resultSet.getTimestamp("ts_usr_ins");
            mtTsUserUpdate = resultSet.getTimestamp("ts_usr_upd");
            
            readRegMembers(session, false);

            // Read aswell child registries:

            statement = session.getStatement().getConnection().createStatement();
            
            msSql = "SELECT id_cmp FROM " + DModConsts.TablesMap.get(DModConsts.MU_FRM_CMP) + " " + getSqlWhere() +
                    "ORDER BY id_cmp ";
            resultSet = statement.executeQuery(msSql);
            while (resultSet.next()) {
                DDbFormulaComp child = new DDbFormulaComp();
                child.read(session, new int[] { mnPkFormulaId, resultSet.getInt(1) });
                maChildComps.add(child);
            }
            
            msSql = "SELECT id_byp FROM " + DModConsts.TablesMap.get(DModConsts.MU_FRM_BYP) + " " + getSqlWhere() +
                    "ORDER BY id_byp ";
            resultSet = statement.executeQuery(msSql);
            while (resultSet.next()) {
                DDbFormulaByProd child = new DDbFormulaByProd();
                child.read(session, new int[] { mnPkFormulaId, resultSet.getInt(1) });
                maChildByProds.add(child);
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

        compute(session);

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
                    "'" + msReference + "', " + 
                    mdQuantity + ", " + 
                    mdMass_r + ", " + 
                    (mbDeleted ? 1 : 0) + ", " + 
                    (mbSystem ? 1 : 0) + ", " + 
                    mnFkFormulaTypeId + ", " + 
                    mnFkItemId + ", " + 
                    mnFkItemTypeId + ", " + 
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
                    "ref = '" + msReference + "', " +
                    "qty = " + mdQuantity + ", " +
                    "mass_r = " + mdMass_r + ", " +
                    "b_del = " + (mbDeleted ? 1 : 0) + ", " +
                    "b_sys = " + (mbSystem ? 1 : 0) + ", " +
                    "fk_frm_tp = " + mnFkFormulaTypeId + ", " +
                    "fk_itm = " + mnFkItemId + ", " +
                    "fk_itm_tp = " + mnFkItemTypeId + ", " +
                    "fk_uom = " + mnFkUnitId + ", " +
                    "fk_pre = " + mnFkPresentId + ", " +
                    //"fk_usr_ins = " + mnFkUserInsertId + ", " +
                    "fk_usr_upd = " + mnFkUserUpdateId + ", " +
                    //"ts_usr_ins = " + "NOW()" + ", " +
                    "ts_usr_upd = " + "NOW()" + " " +
                    getSqlWhere();
        }

        session.getStatement().execute(msSql);
        
        // Save aswell child registries:

        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.MU_FRM_CMP) + " " + getSqlWhere();
        session.getStatement().execute(msSql);

        for (DDbFormulaComp child : maChildComps) {
            child.setPkFormulaId(mnPkFormulaId);
            child.setRegistryNew(true);
            child.save(session);
        }

        msSql = "DELETE FROM " + DModConsts.TablesMap.get(DModConsts.MU_FRM_BYP) + " " + getSqlWhere();
        session.getStatement().execute(msSql);

        for (DDbFormulaByProd child : maChildByProds) {
            child.setPkFormulaId(mnPkFormulaId);
            child.setRegistryNew(true);
            child.save(session);
        }

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
        registry.setReference(this.getReference());
        registry.setQuantity(this.getQuantity());
        registry.setMass_r(this.getMass_r());
        registry.setDeleted(this.isDeleted());
        registry.setSystem(this.isSystem());
        registry.setFkFormulaTypeId(this.getFkFormulaTypeId());
        registry.setFkItemId(this.getFkItemId());
        registry.setFkItemTypeId(this.getFkItemTypeId());
        registry.setFkUnitId(this.getFkUnitId());
        registry.setFkPresentId(this.getFkPresentId());
        registry.setFkUserInsertId(this.getFkUserInsertId());
        registry.setFkUserUpdateId(this.getFkUserUpdateId());
        registry.setTsUserInsert(this.getTsUserInsert());
        registry.setTsUserUpdate(this.getTsUserUpdate());

        registry.setRegItem(this.getRegItem() == null ? null : this.getRegItem().clone());
        registry.setRegUnit(this.getRegUnit() == null ? null : this.getRegUnit().clone());
        registry.setRegPresent(this.getRegPresent()== null ? null : this.getRegPresent().clone());
    
        for (DDbFormulaComp child : maChildComps) {
            registry.getChildComps().add(child.clone());
        }

        for (DDbFormulaByProd child : maChildByProds) {
            registry.getChildByProds().add(child.clone());
        }

        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
    
    public void compute(final DGuiSession session) {
        readRegMembers(session, true);
        
        mdMass_r = 0;
        
        for (DDbFormulaComp child : maChildComps) {
            mdMass_r += child.getMass_r();
        }
        
        mdMass_r = DLibUtils.round(mdMass_r, DLibUtils.getDecimalFormatQuantity().getMaximumFractionDigits());
    }
}
