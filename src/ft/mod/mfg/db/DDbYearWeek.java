/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.mfg.db;

import ft.mod.DModConsts;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import sba.lib.DLibUtils;
import sba.lib.db.DDbConsts;
import sba.lib.db.DDbRegistryUser;
import sba.lib.gui.DGuiSession;

/**
 *
 * @author Sergio Flores
 */
public class DDbYearWeek extends DDbRegistryUser {

    protected int mnPkYearId;
    protected int mnPkWeekId;
    protected Date mtStart;

    public DDbYearWeek() {
        super(DModConsts.M_YER_WEK);
        initRegistry();
    }

    public void setPkYearId(int n) { mnPkYearId = n; }
    public void setPkWeekId(int n) { mnPkWeekId = n; }
    public void setStart(Date t) { mtStart = t; }

    public int getPkYearId() { return mnPkYearId; }
    public int getPkWeekId() { return mnPkWeekId; }
    public Date getStart() { return mtStart; }

    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkYearId = pk[0];
        mnPkWeekId = pk[1];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkYearId, mnPkWeekId };
    }

    @Override
    public void initRegistry() {
        initBaseRegistry();

        mnPkYearId = 0;
        mnPkWeekId = 0;
        mtStart = null;
    }

    @Override
    public String getSqlTable() {
        return DModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_yer = " + mnPkYearId + " AND id_wek = " + mnPkWeekId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_yer = " + pk[0] + " AND id_wek = " + pk[1] + " ";
    }

    @Override
    public void computePrimaryKey(DGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkWeekId = 0;

        msSql = "SELECT COALESCE(MAX(id_wek), 0) + 1 FROM " + getSqlTable() + " " +
                "WHERE id_yer = " + mnPkYearId + " ";
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkWeekId = resultSet.getInt(1);
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
            mnPkYearId = resultSet.getInt("id_yer");
            mnPkWeekId = resultSet.getInt("id_wek");
            mtStart = resultSet.getDate("sta");

            mbRegistryNew = false;
        }

        mnQueryResultId = DDbConsts.READ_OK;
    }

    @Override
    public void save(DGuiSession session) throws SQLException, Exception {
        initQueryMembers();
        mnQueryResultId = DDbConsts.SAVE_ERROR;
        
        if (mbRegistryNew) {
            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkYearId + ", " + 
                    mnPkWeekId + ", " + 
                    "'" + DLibUtils.DbmsDateFormatDate.format(mtStart) + "' " + 
                    ")";
        }
        else {
            msSql = "UPDATE " + getSqlTable() + " SET " +
                    //"id_yer = " + mnPkYearId + ", " +
                    //"id_wek = " + mnPkWeekId + ", " +
                    "sta = '" + DLibUtils.DbmsDateFormatDate.format(mtStart) + "', " +
                    getSqlWhere();
        }

        session.getStatement().execute(msSql);
        mbRegistryNew = false;

        mnQueryResultId = DDbConsts.SAVE_OK;
    }

    @Override
    public DDbYearWeek clone() throws CloneNotSupportedException {
        DDbYearWeek registry = new DDbYearWeek();

        registry.setPkYearId(this.getPkYearId());
        registry.setPkWeekId(this.getPkWeekId());
        registry.setStart(this.getStart());

        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
}
