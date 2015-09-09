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
public class DRowJobProg implements DGridRow {
    
    protected DRowJobProgMask miJobProgMask;
    
    public DRowJobProg(DRowJobProgMask mask) {
        miJobProgMask = mask;
    }

    public DRowJobProgMask getJobProgMask() { return miJobProgMask; }
    
    @Override
    public int[] getRowPrimaryKey() {
        return ((DDbRegistryUser) miJobProgMask).getPrimaryKey();
    }

    @Override
    public String getRowCode() {
        return ((DDbRegistryUser) miJobProgMask).getCode();
    }

    @Override
    public String getRowName() {
        return ((DDbRegistryUser) miJobProgMask).getName();
    }

    @Override
    public boolean isRowSystem() {
        return ((DDbRegistryUser) miJobProgMask).isSystem();
    }

    @Override
    public boolean isRowDeletable() {
        return ((DDbRegistryUser) miJobProgMask).isDeletable();
    }

    @Override
    public boolean isRowEdited() {
        return ((DDbRegistryUser) miJobProgMask).isRegistryEdited();
    }

    @Override
    public void setRowEdited(boolean edited) {
        ((DDbRegistryUser) miJobProgMask).setRegistryEdited(edited);
    }

    @Override
    public Object getRowValueAt(int col) {
        Object value = null;
        
        switch (col) {
            case 0:
                value = miJobProgMask.getLineCode();
                break;
            case 1:
                value = miJobProgMask.getProduct();
                break;
            case 2:
                value = miJobProgMask.getFormula();
                break;
            case 3:
                value = miJobProgMask.getQuantity();
                break;
            case 4:
                value = miJobProgMask.getUnitCode();
                break;
            case 5:
                value = miJobProgMask.getDefaultVar1();
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
