/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ft.mod.mfg.db;

/**
 *
 * @author Sergio Flores
 */
public interface DRowJobProgMask {
    
    public String getLineCode();
    public int getProg();
    public String getItem();
    public String getFormula();
    public double getQuantity();
    public String getUnitCode();
    public double getDefaultVariable1();
}
