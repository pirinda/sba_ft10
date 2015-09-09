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
public class DRowJobMfg implements DGridRow {
    
    protected DRowJobMfgMask miJobMfg;
    
    public DRowJobMfg(DRowJobMfgMask jobPack) {
        miJobMfg = jobPack;
    }

    public DRowJobMfgMask getJobMfg() { return miJobMfg; }
    
    @Override
    public int[] getRowPrimaryKey() {
        return ((DDbRegistryUser) miJobMfg).getPrimaryKey();
    }

    @Override
    public String getRowCode() {
        return ((DDbRegistryUser) miJobMfg).getCode();
    }

    @Override
    public String getRowName() {
        return ((DDbRegistryUser) miJobMfg).getName();
    }

    @Override
    public boolean isRowSystem() {
        return ((DDbRegistryUser) miJobMfg).isSystem();
    }

    @Override
    public boolean isRowDeletable() {
        return ((DDbRegistryUser) miJobMfg).isDeletable();
    }

    @Override
    public boolean isRowEdited() {
        return ((DDbRegistryUser) miJobMfg).isRegistryEdited();
    }

    @Override
    public void setRowEdited(boolean edited) {
        ((DDbRegistryUser) miJobMfg).setRegistryEdited(edited);
    }

    @Override
    public Object getRowValueAt(int col) {
        Object value = null;
        
        switch (col) {
            case 0:
                value = miJobMfg.getLineCode();
                break;
            case 1:
                value = miJobMfg.getProduct();
                break;
            case 2:
                value = miJobMfg.getQuantity();
                break;
            case 3:
                value = miJobMfg.getUnitCode();
                break;
            case 4:
                value = miJobMfg.getVar1();
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
