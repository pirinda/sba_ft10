/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ft.mod.mfg.db;

import sba.lib.grid.DGridRow;

/**
 *
 * @author Sergio Flores
 */
public class DRowOption implements DGridRow {
    
    public int OptionId;
    public String Code;
    public String Name;
    public boolean Selected;
    
    public DRowOption(int optionId, String code, String name, boolean selected) {
        OptionId = optionId;
        Code = code;
        Name = name;
        Selected = selected;
    }

    @Override
    public int[] getRowPrimaryKey() {
        return new int[] { OptionId };
    }

    @Override
    public String getRowCode() {
        return Code;
    }

    @Override
    public String getRowName() {
        return Name;
    }

    @Override
    public boolean isRowSystem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isRowDeletable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isRowEdited() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setRowEdited(boolean edited) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getRowValueAt(int col) {
        Object value = null;
        
        switch (col) {
            case 0:
                value = Name;
                break;
            case 1:
                value = Code;
                break;
            case 2:
                value = Selected;
                break;
            default:
        }
        
        return value;
    }

    @Override
    public void setRowValueAt(Object value, int col) {
        switch (col) {
            case 0:
            case 1:
                break;
            case 2:
                Selected = (Boolean) value;
                break;
            default:
        }
    }
}
