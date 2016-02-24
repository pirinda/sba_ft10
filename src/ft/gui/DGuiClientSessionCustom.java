/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ft.gui;

import sba.lib.DLibUtils;
import sba.lib.gui.DGuiSession;
import sba.lib.gui.DGuiSessionCustom;

/**
 *
 * @author Sergio Flores
 */
public class DGuiClientSessionCustom implements DGuiSessionCustom {
    
    private DGuiSession moSession;
    
    public DGuiClientSessionCustom(DGuiSession session) {
        moSession = session;
    }

    @Override
    public int[] getLocalCountryKey() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getLocalCountry() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getLocalCountryCode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isLocalCountry(int[] key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int[] getLocalCurrencyKey() {
        return new int[] { DGuiConsts.CUR_MXN };
    }

    @Override
    public String getLocalCurrency() {
        return DGuiConsts.Currencies.get(getLocalCurrencyKey()[0])[1];
    }

    @Override
    public String getLocalCurrencyCode() {
        return DGuiConsts.Currencies.get(getLocalCurrencyKey()[0])[0];
    }

    @Override
    public boolean isLocalCurrency(int[] key) {
        return DLibUtils.compareKeys(key, getLocalCurrencyKey());
    }

    @Override
    public String getLocalLanguage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCountry(int[] key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCountryCode(int[] key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCurrency(int[] key) {
        return DGuiConsts.Currencies.get(key[0])[1];
    }

    @Override
    public String getCurrencyCode(int[] key) {
        return DGuiConsts.Currencies.get(key[0])[0];
    }

    @Override
    public String getLanguage(int[] key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getTerminal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
