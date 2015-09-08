/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ft.mod.mfg.db;

import java.util.ArrayList;
import sba.lib.gui.DGuiSession;

/**
 *
 * @author Sergio Flores
 */
public interface DRowJobProgMask {
    
    public int getLineId();
    public String getLineCode();
    public String getLineName();
    public int getProductId();
    public String getProduct();
    public String getFormula();
    public double getQuantity();
    public String getUnitCode();
    public double getDefaultVariable1();
    public ArrayList<DRowJobRqmtMask> createRqmts(final DGuiSession session);
}
