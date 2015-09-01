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
public interface DRowJobPackMask {
    
    public String getLineCode();
    public int getProg();
    public String getItemTypeCode();
    public String getItem();
    public double getQuantity();
    public String getUnitCode();
    public String getLot();
}
