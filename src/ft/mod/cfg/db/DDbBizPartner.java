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
import sba.lib.db.DDbConsts;
import sba.lib.db.DDbRegistryUser;
import sba.lib.gui.DGuiSession;

/**
 *
 * @author Sergio Flores
 */
public class DDbBizPartner extends DDbRegistryUser {

    protected int mnPkBizPartnerId;
    protected String msCode;
    protected String msName;
    protected String msNameShort;
    protected String msNameLast;
    protected String msNameFirst;
    protected String msTaxId;
    protected String msAddress1;
    protected String msAddress2;
    protected String msAddress3;
    protected String msZip;
    protected String msContact;
    protected String msTelephone;
    protected String msMail;
    protected String msNotes;
    /*
    protected boolean mbDeleted;
    protected boolean mbSystem;
    */
    protected int mnFkBizPartnerTypeId;
    protected int mnFkPersonTypeId;
    /*
    protected int mnFkUserInsertId;
    protected int mnFkUserUpdateId;
    protected Date mtTsUserInsert;
    protected Date mtTsUserUpdate;
    */
    
    public DDbBizPartner() {
        super(DModConsts.CU_BPR);
        initRegistry();
    }

    public void setPkBizPartnerId(int n) { mnPkBizPartnerId = n; }
    public void setCode(String s) { msCode = s; }
    public void setName(String s) { msName = s; }
    public void setNameShort(String s) { msNameShort = s; }
    public void setNameLast(String s) { msNameLast = s; }
    public void setNameFirst(String s) { msNameFirst = s; }
    public void setTaxId(String s) { msTaxId = s; }
    public void setAddress1(String s) { msAddress1 = s; }
    public void setAddress2(String s) { msAddress2 = s; }
    public void setAddress3(String s) { msAddress3 = s; }
    public void setZip(String s) { msZip = s; }
    public void setContact(String s) { msContact = s; }
    public void setTelephone(String s) { msTelephone = s; }
    public void setMail(String s) { msMail = s; }
    public void setNotes(String s) { msNotes = s; }
    public void setDeleted(boolean b) { mbDeleted = b; }
    public void setSystem(boolean b) { mbSystem = b; }
    public void setFkBizPartnerTypeId(int n) { mnFkBizPartnerTypeId = n; }
    public void setFkPersonTypeId(int n) { mnFkPersonTypeId = n; }
    public void setFkUserInsertId(int n) { mnFkUserInsertId = n; }
    public void setFkUserUpdateId(int n) { mnFkUserUpdateId = n; }
    public void setTsUserInsert(Date t) { mtTsUserInsert = t; }
    public void setTsUserUpdate(Date t) { mtTsUserUpdate = t; }

    public int getPkBizPartnerId() { return mnPkBizPartnerId; }
    public String getCode() { return msCode; }
    public String getName() { return msName; }
    public String getNameShort() { return msNameShort; }
    public String getNameLast() { return msNameLast; }
    public String getNameFirst() { return msNameFirst; }
    public String getTaxId() { return msTaxId; }
    public String getAddress1() { return msAddress1; }
    public String getAddress2() { return msAddress2; }
    public String getAddress3() { return msAddress3; }
    public String getZip() { return msZip; }
    public String getContact() { return msContact; }
    public String getTelephone() { return msTelephone; }
    public String getMail() { return msMail; }
    public String getNotes() { return msNotes; }
    public boolean isDeleted() { return mbDeleted; }
    public boolean isSystem() { return mbSystem; }
    public int getFkBizPartnerTypeId() { return mnFkBizPartnerTypeId; }
    public int getFkPersonTypeId() { return mnFkPersonTypeId; }
    public int getFkUserInsertId() { return mnFkUserInsertId; }
    public int getFkUserUpdateId() { return mnFkUserUpdateId; }
    public Date getTsUserInsert() { return mtTsUserInsert; }
    public Date getTsUserUpdate() { return mtTsUserUpdate; }

    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkBizPartnerId = pk[0];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkBizPartnerId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkBizPartnerId = 0;
        msCode = "";
        msName = "";
        msNameShort = "";
        msNameLast = "";
        msNameFirst = "";
        msTaxId = "";
        msAddress1 = "";
        msAddress2 = "";
        msAddress3 = "";
        msZip = "";
        msContact = "";
        msTelephone = "";
        msMail = "";
        msNotes = "";
        mbDeleted = false;
        mbSystem = false;
        mnFkBizPartnerTypeId = 0;
        mnFkPersonTypeId = 0;
        mnFkUserInsertId = 0;
        mnFkUserUpdateId = 0;
        mtTsUserInsert = null;
        mtTsUserUpdate = null;
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_bpr = " + mnPkBizPartnerId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_bpr = " + pk[0] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkBizPartnerId = 0;

        msSql = "SELECT COALESCE(MAX(id_bpr), 0) + 1 FROM " + getSqlTable();
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkBizPartnerId = resultSet.getInt(1);
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
            mnPkBizPartnerId = resultSet.getInt("id_bpr");
            msCode = resultSet.getString("code");
            msName = resultSet.getString("name");
            msNameShort = resultSet.getString("name_sht");
            msNameLast = resultSet.getString("name_lst");
            msNameFirst = resultSet.getString("name_fst");
            msTaxId = resultSet.getString("tax_id");
            msAddress1 = resultSet.getString("add_1");
            msAddress2 = resultSet.getString("add_2");
            msAddress3 = resultSet.getString("add_3");
            msZip = resultSet.getString("zip");
            msContact = resultSet.getString("con");
            msTelephone = resultSet.getString("tel");
            msMail = resultSet.getString("mail");
            msNotes = resultSet.getString("note");
            mbDeleted = resultSet.getBoolean("b_del");
            mbSystem = resultSet.getBoolean("b_sys");
            mnFkBizPartnerTypeId = resultSet.getInt("fk_bpr_tp");
            mnFkPersonTypeId = resultSet.getInt("fk_per_tp");
            mnFkUserInsertId = resultSet.getInt("fk_usr_ins");
            mnFkUserUpdateId = resultSet.getInt("fk_usr_upd");
            mtTsUserInsert = resultSet.getTimestamp("ts_usr_ins");
            mtTsUserUpdate = resultSet.getTimestamp("ts_usr_upd");

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
            mbDeleted = false;
            mbSystem = false;
            mnFkUserInsertId = session.getUser().getPkUserId();
            mnFkUserUpdateId = DUtilConsts.USR_NA_ID;

            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkBizPartnerId + ", " + 
                    "'" + msCode + "', " + 
                    "'" + msName + "', " + 
                    "'" + msNameShort + "', " + 
                    "'" + msNameLast + "', " + 
                    "'" + msNameFirst + "', " + 
                    "'" + msTaxId + "', " + 
                    "'" + msAddress1 + "', " + 
                    "'" + msAddress2 + "', " + 
                    "'" + msAddress3 + "', " + 
                    "'" + msZip + "', " + 
                    "'" + msContact + "', " + 
                    "'" + msTelephone + "', " + 
                    "'" + msMail + "', " + 
                    "'" + msNotes + "', " + 
                    (mbDeleted ? 1 : 0) + ", " + 
                    (mbSystem ? 1 : 0) + ", " + 
                    mnFkBizPartnerTypeId + ", " + 
                    mnFkPersonTypeId + ", " + 
                    mnFkUserInsertId + ", " + 
                    mnFkUserUpdateId + ", " + 
                    "NOW()" + ", " + 
                    "NOW()" + " " + 
                    ")";
        }
        else {
            mnFkUserUpdateId = session.getUser().getPkUserId();

            msSql = "UPDATE " + getSqlTable() + " SET " +
                    //"id_bpr = " + mnPkBizPartnerId + ", " +
                    "code = '" + msCode + "', " +
                    "name = '" + msName + "', " +
                    "name_sht = '" + msNameShort + "', " +
                    "name_lst = '" + msNameLast + "', " +
                    "name_fst = '" + msNameFirst + "', " +
                    "tax_id = '" + msTaxId + "', " +
                    "add_1 = '" + msAddress1 + "', " +
                    "add_2 = '" + msAddress2 + "', " +
                    "add_3 = '" + msAddress3 + "', " +
                    "zip = '" + msZip + "', " +
                    "con = '" + msContact + "', " +
                    "tel = '" + msTelephone + "', " +
                    "mail = '" + msMail + "', " +
                    "note = '" + msNotes + "', " +
                    "b_del = " + (mbDeleted ? 1 : 0) + ", " +
                    "b_sys = " + (mbSystem ? 1 : 0) + ", " +
                    "fk_bpr_tp = " + mnFkBizPartnerTypeId + ", " +
                    "fk_per_tp = " + mnFkPersonTypeId + ", " +
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
    public DDbBizPartner clone() throws CloneNotSupportedException {
        DDbBizPartner registry = new DDbBizPartner();

        registry.setPkBizPartnerId(this.getPkBizPartnerId());
        registry.setCode(this.getCode());
        registry.setName(this.getName());
        registry.setNameShort(this.getNameShort());
        registry.setNameLast(this.getNameLast());
        registry.setNameFirst(this.getNameFirst());
        registry.setTaxId(this.getTaxId());
        registry.setAddress1(this.getAddress1());
        registry.setAddress2(this.getAddress2());
        registry.setAddress3(this.getAddress3());
        registry.setZip(this.getZip());
        registry.setContact(this.getContact());
        registry.setTelephone(this.getTelephone());
        registry.setMail(this.getMail());
        registry.setNotes(this.getNotes());
        registry.setDeleted(this.isDeleted());
        registry.setSystem(this.isSystem());
        registry.setFkBizPartnerTypeId(this.getFkBizPartnerTypeId());
        registry.setFkPersonTypeId(this.getFkPersonTypeId());
        registry.setFkUserInsertId(this.getFkUserInsertId());
        registry.setFkUserUpdateId(this.getFkUserUpdateId());
        registry.setTsUserInsert(this.getTsUserInsert());
        registry.setTsUserUpdate(this.getTsUserUpdate());

        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
}
