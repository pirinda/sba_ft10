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
    
    protected DRowJobProgMask miJobProg;
    
    public DRowJobProg(DRowJobProgMask jobProgPack) {
        miJobProg = jobProgPack;
    }

    public DRowJobProgMask getJobProg() { return miJobProg; }
    
    @Override
    public int[] getRowPrimaryKey() {
        return ((DDbRegistryUser) miJobProg).getPrimaryKey();
    }

    @Override
    public String getRowCode() {
        return ((DDbRegistryUser) miJobProg).getCode();
    }

    @Override
    public String getRowName() {
        return ((DDbRegistryUser) miJobProg).getName();
    }

    @Override
    public boolean isRowSystem() {
        return ((DDbRegistryUser) miJobProg).isSystem();
    }

    @Override
    public boolean isRowDeletable() {
        return ((DDbRegistryUser) miJobProg).isDeletable();
    }

    @Override
    public boolean isRowEdited() {
        return ((DDbRegistryUser) miJobProg).isRegistryEdited();
    }

    @Override
    public void setRowEdited(boolean edited) {
        ((DDbRegistryUser) miJobProg).setRegistryEdited(edited);
    }

    @Override
    public Object getRowValueAt(int col) {
        Object value = null;
        
        switch (col) {
            case 0:
                value = miJobProg.getLineCode();
                break;
            case 1:
                value = miJobProg.getProduct();
                break;
            case 2:
                value = miJobProg.getFormula();
                break;
            case 3:
                value = miJobProg.getQuantity();
                break;
            case 4:
                value = miJobProg.getUnitCode();
                break;
            case 5:
                value = miJobProg.getDefaultVariable1();
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
