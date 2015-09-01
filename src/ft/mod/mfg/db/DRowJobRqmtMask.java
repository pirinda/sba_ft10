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
    public int getProg();
    public String getItemTypeCode();
    public String getItem();
    public double getQuantityReq();
    public double getQuantityCon();
    public String getUnitCode();
    public boolean isConsVariable1();
}
