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
public class DRowJobPack implements DGridRow {
    
    protected DRowJobPackMask miJobPack;
    
    public DRowJobPack(DRowJobPackMask jobPack) {
        miJobPack = jobPack;
    }

    public DRowJobPackMask getJobPack() { return miJobPack; }
    
    @Override
    public int[] getRowPrimaryKey() {
        return ((DDbRegistryUser) miJobPack).getPrimaryKey();
    }

    @Override
    public String getRowCode() {
        return ((DDbRegistryUser) miJobPack).getCode();
    }

    @Override
    public String getRowName() {
        return ((DDbRegistryUser) miJobPack).getName();
    }

    @Override
    public boolean isRowSystem() {
        return ((DDbRegistryUser) miJobPack).isSystem();
    }

    @Override
    public boolean isRowDeletable() {
        return ((DDbRegistryUser) miJobPack).isDeletable();
    }

    @Override
    public boolean isRowEdited() {
        return ((DDbRegistryUser) miJobPack).isRegistryEdited();
    }

    @Override
    public void setRowEdited(boolean edited) {
        ((DDbRegistryUser) miJobPack).setRegistryEdited(edited);
    }

    @Override
    public Object getRowValueAt(int col) {
        Object value = null;
        
        switch (col) {
            case 0:
                value = miJobPack.getLineCode();
                break;
            case 1:
                value = miJobPack.getProg();
                break;
            case 2:
                value = miJobPack.getItemTypeCode();
                break;
            case 3:
                value = miJobPack.getItem();
                break;
            case 4:
                value = miJobPack.getQuantity();
                break;
            case 5:
                value = miJobPack.getUnitCode();
                break;
            case 6:
                value = miJobPack.getLot();
                break;
            default:
        }
        
        return value;
    }

    @Override
    public void setRowValueAt(Object value, int col) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
