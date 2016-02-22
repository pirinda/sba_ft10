/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.cfg.form;

import ft.mod.DModConsts;
import ft.mod.DModSysConsts;
import ft.mod.cfg.db.DCfgUtils;
import ft.mod.cfg.db.DDbFamily;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import sba.lib.DLibConsts;
import sba.lib.DLibUtils;
import sba.lib.db.DDbRegistry;
import sba.lib.gui.DGuiClient;
import sba.lib.gui.DGuiConsts;
import sba.lib.gui.DGuiUtils;
import sba.lib.gui.DGuiValidation;
import sba.lib.gui.bean.DBeanFieldKey;
import sba.lib.gui.bean.DBeanForm;

/**
 *
 * @author Sergio Flores
 */
public class DFormFamily extends DBeanForm implements ItemListener {

    private DDbFamily moRegistry;

    /** Creates new form DFormFamily */
    public DFormFamily(DGuiClient client, String title) {
        setFormSettings(client, DGuiConsts.BEAN_FORM_EDIT, DModConsts.CU_FAM, DLibConsts.UNDEFINED, title);
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
        jPanel5 = new javax.swing.JPanel();
        jlItemType = new javax.swing.JLabel();
        moKeyItemType = new sba.lib.gui.bean.DBeanFieldKey();
        jPanel3 = new javax.swing.JPanel();
        jlCode = new javax.swing.JLabel();
        moTextCode = new sba.lib.gui.bean.DBeanFieldText();
        jPanel4 = new javax.swing.JPanel();
        jlName = new javax.swing.JLabel();
        moTextName = new sba.lib.gui.bean.DBeanFieldText();
        jPanel9 = new javax.swing.JPanel();
        jlUnit = new javax.swing.JLabel();
        moKeyUnit = new sba.lib.gui.bean.DBeanFieldKey();
        jPanel7 = new javax.swing.JPanel();
        jlFamilyBase = new javax.swing.JLabel();
        moKeyFamilyBase = new sba.lib.gui.bean.DBeanFieldKey();
        jPanel8 = new javax.swing.JPanel();
        jlLotCode = new javax.swing.JLabel();
        moTextLotCode = new sba.lib.gui.bean.DBeanFieldText();

        jpContainer.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del registro:"));
        jpContainer.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridLayout(6, 1, 0, 5));

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlItemType.setText("Tipo ítem:*");
        jlItemType.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel5.add(jlItemType);

        moKeyItemType.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel5.add(moKeyItemType);

        jPanel1.add(jPanel5);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlCode.setText("Código:*");
        jlCode.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel3.add(jlCode);

        moTextCode.setPreferredSize(new java.awt.Dimension(50, 23));
        jPanel3.add(moTextCode);

        jPanel1.add(jPanel3);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlName.setText("Nombre:*");
        jlName.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel4.add(jlName);

        moTextName.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel4.add(moTextName);

        jPanel1.add(jPanel4);

        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlUnit.setText("Unidad:*");
        jlUnit.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel9.add(jlUnit);

        moKeyUnit.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel9.add(moKeyUnit);

        jPanel1.add(jPanel9);

        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlFamilyBase.setText("Familia base:*");
        jlFamilyBase.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel7.add(jlFamilyBase);

        moKeyFamilyBase.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel7.add(moKeyFamilyBase);

        jPanel1.add(jPanel7);

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlLotCode.setText("Código lotes:");
        jlLotCode.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel8.add(jlLotCode);

        moTextLotCode.setPreferredSize(new java.awt.Dimension(50, 23));
        jPanel8.add(moTextLotCode);

        jPanel1.add(jPanel8);

        jpContainer.add(jPanel1, java.awt.BorderLayout.NORTH);

        getContentPane().add(jpContainer, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel jlCode;
    private javax.swing.JLabel jlFamilyBase;
    private javax.swing.JLabel jlItemType;
    private javax.swing.JLabel jlLotCode;
    private javax.swing.JLabel jlName;
    private javax.swing.JLabel jlUnit;
    private javax.swing.JPanel jpContainer;
    private sba.lib.gui.bean.DBeanFieldKey moKeyFamilyBase;
    private sba.lib.gui.bean.DBeanFieldKey moKeyItemType;
    private sba.lib.gui.bean.DBeanFieldKey moKeyUnit;
    private sba.lib.gui.bean.DBeanFieldText moTextCode;
    private sba.lib.gui.bean.DBeanFieldText moTextLotCode;
    private sba.lib.gui.bean.DBeanFieldText moTextName;
    // End of variables declaration//GEN-END:variables

    /*
     * Private methods
     */

    private void initComponentsCustom() {
        DGuiUtils.setWindowBounds(this, 480, 300);
        
        moKeyItemType.setKeySettings(miClient, DGuiUtils.getLabelName(jlItemType), true);
        moTextCode.setTextSettings(DGuiUtils.getLabelName(jlCode), 5);
        moTextName.setTextSettings(DGuiUtils.getLabelName(jlName), 50);
        moKeyUnit.setKeySettings(miClient, DGuiUtils.getLabelName(jlUnit), true);
        moKeyFamilyBase.setKeySettings(miClient, DGuiUtils.getLabelName(jlFamilyBase), true);
        moTextLotCode.setTextSettings(DGuiUtils.getLabelName(jlLotCode), 5, 0);
        
        moFields.addField(moKeyItemType);
        moFields.addField(moTextCode);
        moFields.addField(moTextName);
        moFields.addField(moKeyUnit);
        moFields.addField(moKeyFamilyBase);
        moFields.addField(moTextLotCode);
        
        moFields.setFormButton(jbSave);
    }
    
    private void updateFieldItemType() {
        if (moKeyItemType.getSelectedIndex() <= 0) {
            moKeyFamilyBase.setEnabled(false);
            moTextLotCode.setEditable(false);
        }
        else {
            moKeyFamilyBase.setEnabled(DCfgUtils.doesItemTypeRequireFamilyBase(moKeyItemType.getValue()[0]));
            moTextLotCode.setEditable(DCfgUtils.doesItemTypeRequireLotCode(moKeyItemType.getValue()[0]));
        }
    }
    
    private void itemStateChangeItemType() {
        if (moKeyItemType.getSelectedIndex() <= 0) {
            moKeyFamilyBase.resetField();
            moTextLotCode.resetField();
        }
        
        updateFieldItemType();
    }
    
    /*
     * Public methods
     */
    
    /*
     * Overriden methods
     */
    
    @Override
    public void addAllListeners() {
        moKeyItemType.addItemListener(this);
    }

    @Override
    public void removeAllListeners() {
        moKeyItemType.removeItemListener(this);
    }

    @Override
    public void reloadCatalogues() {
        miClient.getSession().populateCatalogue(moKeyItemType, DModConsts.CS_ITM_TP, DLibConsts.UNDEFINED, null);
        miClient.getSession().populateCatalogue(moKeyUnit, DModConsts.CU_UOM, DLibConsts.UNDEFINED, null);
        miClient.getSession().populateCatalogue(moKeyFamilyBase, DModConsts.CU_FAM, DModSysConsts.CS_ITM_TP_PB, null);
    }

    @Override
    public void setRegistry(DDbRegistry registry) throws Exception {
        moRegistry = (DDbFamily) registry;

        mnFormResult = DLibConsts.UNDEFINED;
        mbFirstActivation = true;

        removeAllListeners();
        reloadCatalogues();

        if (moRegistry.isRegistryNew()) {
            moRegistry.setCode("");
            moRegistry.initPrimaryKey();
            
            jtfRegistryKey.setText("");
        }
        else {
            jtfRegistryKey.setText(DLibUtils.textKey(moRegistry.getPrimaryKey()));
        }

        moKeyItemType.setValue(new int[] { moRegistry.getFkItemTypeId() });
        moTextCode.setValue(moRegistry.getCode());
        moTextName.setValue(moRegistry.getName());
        moKeyUnit.setValue(new int[] { moRegistry.getFkUnitId() });
        moKeyFamilyBase.setValue(new int[] { moRegistry.getFkFamilyBaseId_n() });
        moTextLotCode.setValue(moRegistry.getLotCode());

        setFormEditable(true);
        
        moTextCode.setEnabled(false);
        
        updateFieldItemType();
        
        if (moRegistry.isRegistryNew()) {
            
        }
        else {
            
        }

        addAllListeners();
    }

    @Override
    public DDbFamily getRegistry() throws Exception {
        DDbFamily registry = moRegistry.clone();

        if (registry.isRegistryNew()) {
            //registry.setPkFamilyId(...);
        }

        registry.setCode(moTextCode.getValue());
        registry.setName(moTextName.getValue());
        registry.setLotCode(moTextLotCode.getValue());
        //registry.setDeleted(...);
        //registry.setSystem(...);
        registry.setFkItemTypeId(moKeyItemType.getValue()[0]);
        registry.setFkUnitId(moKeyUnit.getValue()[0]);
        registry.setFkFamilyBaseId_n(!moKeyFamilyBase.isEnabled() ? DLibConsts.UNDEFINED : moKeyFamilyBase.getValue()[0]);

        return registry;
    }

    @Override
    public DGuiValidation validateForm() {
        DGuiValidation validation = moFields.validateFields();
        
        if (validation.isValid()) {
            if (moKeyFamilyBase.isEnabled() && moRegistry.getPkFamilyId() == moKeyFamilyBase.getValue()[0]) {
                validation.setMessage(DGuiConsts.ERR_MSG_FIELD_DIF + "'" + moKeyFamilyBase.getFieldName() + ".'");
                validation.setComponent(moKeyFamilyBase);
            }
        }
        
        return validation;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() instanceof DBeanFieldKey) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                DBeanFieldKey field = (DBeanFieldKey) e.getSource();
                
                if (field == moKeyItemType) {
                    itemStateChangeItemType();
                }
            }
        }
    }
}
