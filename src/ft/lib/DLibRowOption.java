/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ft.lib;

import sba.lib.grid.DGridRow;

/**
 *
 * @author Sergio Flores
 */
public class DLibRowOption implements DGridRow {
    
    public int OptionId;
    public String Code;
    public String Name;
    public boolean Selected;
    public int Quantity;
    
    public DLibRowOption(int optionId, String code, String name, boolean selected) {
        this(optionId, code, name, selected, 0);
    }

    public DLibRowOption(int optionId, String code, String name, boolean selected, int quantity) {
        OptionId = optionId;
        Code = code;
        Name = name;
        Selected = selected;
        Quantity = quantity;
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isRowDeletable() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isRowEdited() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setRowEdited(boolean edited) {
        throw new UnsupportedOperationException("Not supported yet.");
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
            case 3:
                value = Quantity;
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
            case 3:
                Quantity = (Integer) value;
                break;
            default:
        }
    }
}
