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
public interface DRowJobConsMask {
    
    public String getLineCode();
    public String getLineName();
    public String getProduct();
    public String getRqmtTypeCode();
    public String getRqmt();
    public double getQuantity();
    public String getUnitCode();
    public String getLot();
    public double getVar1();
}
