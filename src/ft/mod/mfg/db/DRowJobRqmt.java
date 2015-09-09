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
    
    protected DRowJobRqmtMask miJobRqmtMask;
    
    public DRowJobRqmt(DRowJobRqmtMask mask) {
        miJobRqmtMask = mask;
    }

    public DRowJobRqmtMask getJobRqmtMask() { return miJobRqmtMask; }
    
    @Override
    public int[] getRowPrimaryKey() {
        return ((DDbRegistryUser) miJobRqmtMask).getPrimaryKey();
    }

    @Override
    public String getRowCode() {
        return ((DDbRegistryUser) miJobRqmtMask).getCode();
    }

    @Override
    public String getRowName() {
        return ((DDbRegistryUser) miJobRqmtMask).getName();
    }

    @Override
    public boolean isRowSystem() {
        return ((DDbRegistryUser) miJobRqmtMask).isSystem();
    }

    @Override
    public boolean isRowDeletable() {
        return ((DDbRegistryUser) miJobRqmtMask).isDeletable();
    }

    @Override
    public boolean isRowEdited() {
        return ((DDbRegistryUser) miJobRqmtMask).isRegistryEdited();
    }

    @Override
    public void setRowEdited(boolean edited) {
        ((DDbRegistryUser) miJobRqmtMask).setRegistryEdited(edited);
    }

    @Override
    public Object getRowValueAt(int col) {
        Object value = null;
        
        switch (col) {
            case 0:
                value = miJobRqmtMask.getLineCode();
                break;
            case 1:
                value = miJobRqmtMask.getProduct();
                break;
            case 2:
                value = miJobRqmtMask.getRqmtTypeCode();
                break;
            case 3:
                value = miJobRqmtMask.getRqmt();
                break;
            case 4:
                value = miJobRqmtMask.getQuantityRqmt();
                break;
            case 5:
                value = miJobRqmtMask.getQuantityCons();
                break;
            case 6:
                value = miJobRqmtMask.getUnitCode();
                break;
            case 7:
                value = miJobRqmtMask.isConsByVar1();
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
