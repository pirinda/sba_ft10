/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DFormItem.java
 *
 * Created on 29/08/2011, 08:02:13 PM
 */

package ft.mod.cfg.form;

import ft.mod.DModConsts;
import ft.mod.DModSysConsts;
import ft.mod.cfg.db.DCfgUtils;
import ft.mod.cfg.db.DDbConfig;
import ft.mod.cfg.db.DDbFamily;
import ft.mod.cfg.db.DDbItem;
import ft.mod.cfg.db.DDbUnit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import sba.lib.DLibConsts;
import sba.lib.DLibUtils;
import sba.lib.db.DDbRegistry;
import sba.lib.gui.DGuiClient;
import sba.lib.gui.DGuiConsts;
import sba.lib.gui.DGuiParams;
import sba.lib.gui.DGuiUtils;
import sba.lib.gui.DGuiValidation;
import sba.lib.gui.bean.DBeanFieldKey;
import sba.lib.gui.bean.DBeanForm;

/**
 *
 * @author Sergio Flores
 */
public class DFormItem extends DBeanForm implements ItemListener {

    private DDbItem moRegistry;
    private DDbFamily moItemFamily;
    private DDbUnit moUnitItem;
    private boolean mbAppliesItemBase;
    private boolean mbAppliesPresent;
    private boolean mbAppliesMassUnit;
    private boolean mbAppliesLotCode;

    /** Creates new form DFormItem */
    public DFormItem(DGuiClient client, int itemType, String title) {
        setFormSettings(client, DGuiConsts.BEAN_FORM_EDIT, DModConsts.CU_ITM, itemType, title);
        initComponents();
        initComponentsCustom();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpContainer = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jlFamily = new javax.swing.JLabel();
        moKeyFamily = new sba.lib.gui.bean.DBeanFieldKey();
        jPanel10 = new javax.swing.JPanel();
        jlItemBase = new javax.swing.JLabel();
        moKeyItemBase = new sba.lib.gui.bean.DBeanFieldKey();
        jPanel3 = new javax.swing.JPanel();
        jlCode = new javax.swing.JLabel();
        moTextCode = new sba.lib.gui.bean.DBeanFieldText();
        jPanel4 = new javax.swing.JPanel();
        jlName = new javax.swing.JLabel();
        moTextName = new sba.lib.gui.bean.DBeanFieldText();
        jPanel5 = new javax.swing.JPanel();
        jlUnit = new javax.swing.JLabel();
        moKeyUnit = new sba.lib.gui.bean.DBeanFieldKey();
        jPanel9 = new javax.swing.JPanel();
        jlPresent = new javax.swing.JLabel();
        moKeyPresent = new sba.lib.gui.bean.DBeanFieldKey();
        jPanel6 = new javax.swing.JPanel();
        jlMassUnit = new javax.swing.JLabel();
        moCompMassUnit = new sba.lib.gui.bean.DBeanCompoundField();
        jPanel11 = new javax.swing.JPanel();
        jlLotCode = new javax.swing.JLabel();
        moTextLotCode = new sba.lib.gui.bean.DBeanFieldText();

        jpContainer.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del registro:"));
        jpContainer.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridLayout(8, 1, 0, 5));

        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlFamily.setText("Familia:*");
        jlFamily.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel7.add(jlFamily);

        moKeyFamily.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel7.add(moKeyFamily);

        jPanel1.add(jPanel7);

        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlItemBase.setText("Producto base:*");
        jlItemBase.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel10.add(jlItemBase);

        moKeyItemBase.setPreferredSize(new java.awt.Dimension(300, 23));
        jPanel10.add(moKeyItemBase);

        jPanel1.add(jPanel10);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlCode.setText("Código:*");
        jlCode.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel3.add(jlCode);
        jPanel3.add(moTextCode);

        jPanel1.add(jPanel3);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlName.setText("Nombre:*");
        jlName.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel4.add(jlName);

        moTextName.setPreferredSize(new java.awt.Dimension(300, 23));
        jPanel4.add(moTextName);

        jPanel1.add(jPanel4);

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlUnit.setText("Unidad:*");
        jlUnit.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel5.add(jlUnit);

        moKeyUnit.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel5.add(moKeyUnit);

        jPanel1.add(jPanel5);

        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlPresent.setText("Presentación:*");
        jlPresent.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel9.add(jlPresent);

        moKeyPresent.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel9.add(moKeyPresent);

        jPanel1.add(jPanel9);

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlMassUnit.setText("Masa unitaria:*");
        jlMassUnit.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel6.add(jlMassUnit);
        jPanel6.add(moCompMassUnit);

        jPanel1.add(jPanel6);

        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlLotCode.setText("Código lotes:");
        jlLotCode.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel11.add(jlLotCode);

        moTextLotCode.setPreferredSize(new java.awt.Dimension(50, 23));
        jPanel11.add(moTextLotCode);

        jPanel1.add(jPanel11);

        jpContainer.add(jPanel1, java.awt.BorderLayout.NORTH);

        getContentPane().add(jpContainer, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel jlCode;
    private javax.swing.JLabel jlFamily;
    private javax.swing.JLabel jlItemBase;
    private javax.swing.JLabel jlLotCode;
    private javax.swing.JLabel jlMassUnit;
    private javax.swing.JLabel jlName;
    private javax.swing.JLabel jlPresent;
    private javax.swing.JLabel jlUnit;
    private javax.swing.JPanel jpContainer;
    private sba.lib.gui.bean.DBeanCompoundField moCompMassUnit;
    private sba.lib.gui.bean.DBeanFieldKey moKeyFamily;
    private sba.lib.gui.bean.DBeanFieldKey moKeyItemBase;
    private sba.lib.gui.bean.DBeanFieldKey moKeyPresent;
    private sba.lib.gui.bean.DBeanFieldKey moKeyUnit;
    private sba.lib.gui.bean.DBeanFieldText moTextCode;
    private sba.lib.gui.bean.DBeanFieldText moTextLotCode;
    private sba.lib.gui.bean.DBeanFieldText moTextName;
    // End of variables declaration//GEN-END:variables

    /*
     * Private methods
     */
    
    private void initComponentsCustom() {
        DGuiUtils.setWindowBounds(this, 560, 350);
        
        mbAppliesItemBase = DCfgUtils.doesItemTypeRequireItemBase(mnFormSubtype);
        mbAppliesPresent = DCfgUtils.doesItemTypeRequirePresent(mnFormSubtype);
        mbAppliesMassUnit = DCfgUtils.doesItemTypeRequireMassUnit(mnFormSubtype);
        mbAppliesLotCode = DCfgUtils.doesItemTypeRequireLotCode(mnFormSubtype);
        
        moKeyFamily.setKeySettings(miClient, DGuiUtils.getLabelName(jlFamily), true);
        moKeyItemBase.setKeySettings(miClient, DGuiUtils.getLabelName(jlItemBase), true);
        moTextCode.setTextSettings(DGuiUtils.getLabelName(jlCode), 25);
        moTextName.setTextSettings(DGuiUtils.getLabelName(jlName), 50);
        moKeyUnit.setKeySettings(miClient, DGuiUtils.getLabelName(jlUnit), true);
        moKeyPresent.setKeySettings(miClient, DGuiUtils.getLabelName(jlPresent), true);
        moCompMassUnit.setCompoundFieldSettings(miClient);
        moCompMassUnit.getField().setDecimalSettings(DGuiUtils.getLabelName(jlMassUnit), DGuiConsts.GUI_TYPE_DEC_AMT_UNIT, true);
        moTextLotCode.setTextSettings(DGuiUtils.getLabelName(jlLotCode), 5, 0);
        
        moFields.addField(moKeyFamily);
        moFields.addField(moKeyItemBase);
        moFields.addField(moTextCode);
        moFields.addField(moTextName);
        moFields.addField(moKeyUnit);
        moFields.addField(moKeyPresent);
        moFields.addField(moCompMassUnit.getField());
        moFields.addField(moTextLotCode);
        
        moFields.setFormButton(jbSave);
        
        moCompMassUnit.setCompoundText(DCfgUtils.getMassUnitCode(miClient.getSession()));
    }
    
    private void updateFieldItemBase() {
        if (moKeyFamily.getSelectedIndex() <= 0) {
            moKeyItemBase.setEnabled(false);
        }
        else {
            moKeyItemBase.setEnabled(mbAppliesItemBase);
        }
    }
    
    private void itemStateChangeFamily() {
        if (moKeyFamily.getSelectedIndex() <= 0) {
            moItemFamily = null;
            
            moKeyItemBase.removeAllItems();
            
            moKeyUnit.resetField();
        }
        else {
            moItemFamily = (DDbFamily) miClient.getSession().readRegistry(DModConsts.CU_FAM, moKeyFamily.getValue());
            
            if (mbAppliesItemBase) {
                miClient.getSession().populateCatalogue(moKeyItemBase, DModConsts.CU_ITM, DLibConsts.UNDEFINED, new DGuiParams(new int[] { moItemFamily.getFkFamilyBaseId_n() }));
            }
            
            moKeyUnit.setValue(new int[] { moItemFamily.getFkUnitId() });
        }
        
        updateFieldItemBase();
    }
    
    private void itemStateChangeUnit() {
        if (moKeyUnit.getSelectedIndex() <= 0) {
            moUnitItem = null;
        }
        else {
            moUnitItem = (DDbUnit) miClient.getSession().readRegistry(DModConsts.CU_UOM, moKeyUnit.getValue());
        }
        
        if (moUnitItem == null || moUnitItem.getFkUnitTypeId() != DModSysConsts.CS_UOM_TP_MSS) {
            moCompMassUnit.getField().setValue(0d);
        }
        else {
            moCompMassUnit.getField().setValue(moUnitItem.getConversionFactor() / ((DDbConfig) miClient.getSession().getConfigCompany()).getRegMassUnit().getConversionFactor());
        }
    }
    
    /*
     * Public methods
     */
    
    /*
     * Overriden methods
     */
    
    @Override
    public void addAllListeners() {
        moKeyFamily.addItemListener(this);
        moKeyUnit.addItemListener(this);
    }

    @Override
    public void removeAllListeners() {
        moKeyFamily.removeItemListener(this);
        moKeyUnit.removeItemListener(this);
    }

    @Override
    public void reloadCatalogues() {
        miClient.getSession().populateCatalogue(moKeyFamily, DModConsts.CU_FAM, mnFormSubtype, null);
        miClient.getSession().populateCatalogue(moKeyUnit, DModConsts.CU_UOM, DLibConsts.UNDEFINED, null);
        miClient.getSession().populateCatalogue(moKeyPresent, DModConsts.CU_PRE, DLibConsts.UNDEFINED, null);
    }

    @Override
    public void setRegistry(DDbRegistry registry) throws Exception {
        moRegistry = (DDbItem) registry;

        mnFormResult = DLibConsts.UNDEFINED;
        mbFirstActivation = true;

        removeAllListeners();
        reloadCatalogues();

        if (moRegistry.isRegistryNew()) {
            moRegistry.setCode("");
            moRegistry.initPrimaryKey();
            
            if (!mbAppliesItemBase) {
                moRegistry.setFkItemBaseId_n(DLibConsts.UNDEFINED);
            }
            
            if (!mbAppliesPresent) {
                moRegistry.setFkPresentId(DModSysConsts.CU_PRE_NA);
            }
            
            if (!mbAppliesMassUnit) {
                moRegistry.setMassUnit(0);
            }
            
            if (!mbAppliesLotCode) {
                moRegistry.setLotCode("");
            }
            
            jtfRegistryKey.setText("");
        }
        else {
            jtfRegistryKey.setText(DLibUtils.textKey(moRegistry.getPrimaryKey()));
        }

        moKeyFamily.setValue(new int[] { moRegistry.getFkFamilyId() });
        itemStateChangeFamily();
        moKeyItemBase.setValue(new int[] { moRegistry.getFkItemBaseId_n() });
        moTextCode.setValue(moRegistry.getCode());
        moTextName.setValue(moRegistry.getName());
        moKeyUnit.setValue(new int[] { moRegistry.getFkUnitId() });
        moKeyPresent.setValue(new int[] { moRegistry.getFkPresentId() });
        moCompMassUnit.getField().setValue(moRegistry.getMassUnit());
        moTextLotCode.setValue(moRegistry.getLotCode());
        
        setFormEditable(true);
        
        updateFieldItemBase();
        
        moTextCode.setEnabled(false);
        moKeyPresent.setEnabled(mbAppliesPresent);
        moCompMassUnit.setEditable(mbAppliesMassUnit);
        moTextLotCode.setEditable(mbAppliesLotCode);

        if (moRegistry.isRegistryNew()) {

        }
        else {
            
        }
        
        addAllListeners();
    }

    @Override
    public DDbItem getRegistry() throws Exception {
        DDbItem registry = moRegistry.clone();

        if (registry.isRegistryNew()) {
            //registry.setPkItemId(...);
        }

        registry.setCode(moTextCode.getValue());
        registry.setName(moTextName.getValue());
        registry.setLotCode(moTextLotCode.getValue());
        registry.setMassUnit(moCompMassUnit.getField().getValue());
        //registry.setDeleted(...);
        //registry.setSystem(...);
        registry.setFkFamilyId(moKeyFamily.getValue()[0]);
        registry.setFkUnitId(moKeyUnit.getValue()[0]);
        registry.setFkPresentId(moKeyPresent.getValue()[0]);
        registry.setFkItemBaseId_n(moKeyItemBase.getSelectedIndex() <= 0 ? DLibConsts.UNDEFINED : moKeyItemBase.getValue()[0]);

        return registry;
    }

    @Override
    public DGuiValidation validateForm() {
        return moFields.validateFields();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() instanceof DBeanFieldKey) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                DBeanFieldKey field = (DBeanFieldKey) e.getSource();
                
                if (field == moKeyFamily) {
                    itemStateChangeFamily();
                }
                else if (field == moKeyUnit) {
                    itemStateChangeUnit();
                }
            }
        }
    }
}
