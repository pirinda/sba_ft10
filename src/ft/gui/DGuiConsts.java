/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ft.gui;

import java.util.HashMap;

/**
 *
 * @author Sergio Flores
 */
public abstract class DGuiConsts {
    
    public static final int CUR_MXN = 104;
    public static final int CUR_USD = 151;
    public static final int CUR_EUR = 51;
    
    public static final HashMap<Integer, String[]> Currencies = new HashMap<>();
    
    static {
        Currencies.put(CUR_MXN, new String[] { "MXN", "PESO" });
        Currencies.put(CUR_USD, new String[] { "USD", "DOLLAR" });
        Currencies.put(CUR_EUR, new String[] { "EUR", "EURO" });
    }
}
