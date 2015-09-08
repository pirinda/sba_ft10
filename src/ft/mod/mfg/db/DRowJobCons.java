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
public class DRowJobCons implements DGridRow {
    
    protected DRowJobConsMask miJobCons;
    
    public DRowJobCons(DRowJobConsMask jobPrep) {
        miJobCons = jobPrep;
    }

    public DRowJobConsMask getJobCons() { return miJobCons; }
    
    @Override
    public int[] getRowPrimaryKey() {
        return ((DDbRegistryUser) miJobCons).getPrimaryKey();
    }

    @Override
    public String getRowCode() {
        return ((DDbRegistryUser) miJobCons).getCode();
    }

    @Override
    public String getRowName() {
        return ((DDbRegistryUser) miJobCons).getName();
    }

    @Override
    public boolean isRowSystem() {
        return ((DDbRegistryUser) miJobCons).isSystem();
    }

    @Override
    public boolean isRowDeletable() {
        return ((DDbRegistryUser) miJobCons).isDeletable();
    }

    @Override
    public boolean isRowEdited() {
        return ((DDbRegistryUser) miJobCons).isRegistryEdited();
    }

    @Override
    public void setRowEdited(boolean edited) {
        ((DDbRegistryUser) miJobCons).setRegistryEdited(edited);
    }

    @Override
    public Object getRowValueAt(int col) {
        Object value = null;
        
        switch (col) {
            case 0:
                value = miJobCons.getLineCode();
                break;
            case 1:
                value = miJobCons.getProduct();
                break;
            case 2:
                value = miJobCons.getRequirementTypeCode();
                break;
            case 3:
                value = miJobCons.getRequirement();
                break;
            case 4:
                value = miJobCons.getQuantity();
                break;
            case 5:
                value = miJobCons.getUnitCode();
                break;
            case 6:
                value = miJobCons.getLot();
                break;
            case 7:
                value = miJobCons.getVariable1();
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
