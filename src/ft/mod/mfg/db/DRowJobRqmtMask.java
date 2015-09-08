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
public interface DRowJobRqmtMask {
    
    public int getLineId();
    public String getLineCode();
    public String getLineName();
    public int getProductId();
    public String getProduct();
    public int getRqmtId();
    public String getRqmt();
    public String getRqmtTypeCode();
    public double getQuantityRqmt();
    public double getQuantityCons();
    public String getUnitCode();
    public boolean isConsVariable1();
}
