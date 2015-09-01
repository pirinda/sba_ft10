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
public class DRowJobPrep implements DGridRow {
    
    protected DRowJobPrepMask miJobPrep;
    
    public DRowJobPrep(DRowJobPrepMask jobPrep) {
        miJobPrep = jobPrep;
    }

    public DRowJobPrepMask getJobPrep() { return miJobPrep; }
    
    @Override
    public int[] getRowPrimaryKey() {
        return ((DDbRegistryUser) miJobPrep).getPrimaryKey();
    }

    @Override
    public String getRowCode() {
        return ((DDbRegistryUser) miJobPrep).getCode();
    }

    @Override
    public String getRowName() {
        return ((DDbRegistryUser) miJobPrep).getName();
    }

    @Override
    public boolean isRowSystem() {
        return ((DDbRegistryUser) miJobPrep).isSystem();
    }

    @Override
    public boolean isRowDeletable() {
        return ((DDbRegistryUser) miJobPrep).isDeletable();
    }

    @Override
    public boolean isRowEdited() {
        return ((DDbRegistryUser) miJobPrep).isRegistryEdited();
    }

    @Override
    public void setRowEdited(boolean edited) {
        ((DDbRegistryUser) miJobPrep).setRegistryEdited(edited);
    }

    @Override
    public Object getRowValueAt(int col) {
        Object value = null;
        
        switch (col) {
            case 0:
                value = miJobPrep.getLineCode();
                break;
            case 1:
                value = miJobPrep.getProg();
                break;
            case 2:
                value = miJobPrep.getItemTypeCode();
                break;
            case 3:
                value = miJobPrep.getItem();
                break;
            case 4:
                value = miJobPrep.getQuantity();
                break;
            case 5:
                value = miJobPrep.getUnitCode();
                break;
            case 6:
                value = miJobPrep.getVariable1();
                break;
            case 7:
                value = miJobPrep.getLot();
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
