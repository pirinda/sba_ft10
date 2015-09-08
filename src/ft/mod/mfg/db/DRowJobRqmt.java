/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ft.mod.mfg.db;

import sba.lib.db.DDbRegistryUser;
import sba.lib.grid.DGridRow;

/**
 *
 * @author Sergio Flores
 */
public class DRowJobRqmt implements DGridRow {
    
    protected DRowJobRqmtMask miJobRqmt;
    
    public DRowJobRqmt(DRowJobRqmtMask jobRqmt) {
        miJobRqmt = jobRqmt;
    }

    public DRowJobRqmtMask getJobRqmt() { return miJobRqmt; }
    
    @Override
    public int[] getRowPrimaryKey() {
        return ((DDbRegistryUser) miJobRqmt).getPrimaryKey();
    }

    @Override
    public String getRowCode() {
        return ((DDbRegistryUser) miJobRqmt).getCode();
    }

    @Override
    public String getRowName() {
        return ((DDbRegistryUser) miJobRqmt).getName();
    }

    @Override
    public boolean isRowSystem() {
        return ((DDbRegistryUser) miJobRqmt).isSystem();
    }

    @Override
    public boolean isRowDeletable() {
        return ((DDbRegistryUser) miJobRqmt).isDeletable();
    }

    @Override
    public boolean isRowEdited() {
        return ((DDbRegistryUser) miJobRqmt).isRegistryEdited();
    }

    @Override
    public void setRowEdited(boolean edited) {
        ((DDbRegistryUser) miJobRqmt).setRegistryEdited(edited);
    }

    @Override
    public Object getRowValueAt(int col) {
        Object value = null;
        
        switch (col) {
            case 0:
                value = miJobRqmt.getLineCode();
                break;
            case 1:
                value = miJobRqmt.getProduct();
                break;
            case 2:
                value = miJobRqmt.getRqmtTypeCode();
                break;
            case 3:
                value = miJobRqmt.getRqmt();
                break;
            case 4:
                value = miJobRqmt.getQuantityRqmt();
                break;
            case 5:
                value = miJobRqmt.getQuantityCons();
                break;
            case 6:
                value = miJobRqmt.getUnitCode();
                break;
            case 7:
                value = miJobRqmt.isConsVariable1();
                break;
            default:
        }
        
        return value;
    }

    @Override
    public void setRowValueAt(Object value, int col) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
