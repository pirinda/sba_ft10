/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DFormPresent.java
 *
 * Created on 29/08/2011, 08:02:13 PM
 */

package ft.mod.cfg.form;

import ft.mod.DModConsts;
import ft.mod.cfg.db.DDbPresent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import sba.lib.DLibConsts;
import sba.lib.DLibUtils;
import sba.lib.db.DDbRegistry;
import sba.lib.gui.DGuiClient;
import sba.lib.gui.DGuiConsts;
import sba.lib.gui.DGuiUtils;
import sba.lib.gui.DGuiValidation;
import sba.lib.gui.bean.DBeanFieldDecimal;
import sba.lib.gui.bean.DBeanFieldKey;
import sba.lib.gui.bean.DBeanFieldText;
import sba.lib.gui.bean.DBeanForm;

/**
 *
 * @author Sergio Flores
 */
public class DFormPresent extends DBeanForm implements ItemListener, FocusListener {

    private DDbPresent moRegistry;

    /** Creates new form DFormPresent */
    public DFormPresent(DGuiClient client, String title) {
        setFormSettings(client, DGuiConsts.BEAN_FORM_EDIT, DModConsts.CU_PRS, DLibConsts.UNDEFINED, title);
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
        jPanel3 = new javax.swing.JPanel();
        jlCode = new javax.swing.JLabel();
        moTextCode = new sba.lib.gui.bean.DBeanFieldText();
        jPanel4 = new javax.swing.JPanel();
        jlNameRaw = new javax.swing.JLabel();
        moTextNameRaw = new sba.lib.gui.bean.DBeanFieldText();
        jPanel5 = new javax.swing.JPanel();
        jlUnit = new javax.swing.JLabel();
        moKeyUnit = new sba.lib.gui.bean.DBeanFieldKey();
        jPanel6 = new javax.swing.JPanel();
        jlContentUnit = new javax.swing.JLabel();
        moCompContentUnit = new sba.lib.gui.bean.DBeanCompoundField();
        jPanel8 = new javax.swing.JPanel();
        jlName = new javax.swing.JLabel();
        jtfName = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jlLotCode = new javax.swing.JLabel();
        moTextLotCode = new sba.lib.gui.bean.DBeanFieldText();

        jpContainer.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del registro:"));
        jpContainer.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridLayout(6, 1, 0, 5));

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlCode.setText("Código:*");
        jlCode.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel3.add(jlCode);

        moTextCode.setPreferredSize(new java.awt.Dimension(50, 23));
        jPanel3.add(moTextCode);

        jPanel1.add(jPanel3);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlNameRaw.setText("Nombre simple:*");
        jlNameRaw.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel4.add(jlNameRaw);

        moTextNameRaw.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel4.add(moTextNameRaw);

        jPanel1.add(jPanel4);

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlUnit.setText("Unidad:*");
        jlUnit.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel5.add(jlUnit);

        moKeyUnit.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel5.add(moKeyUnit);

        jPanel1.add(jPanel5);

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlContentUnit.setText("Contenido neto:*");
        jlContentUnit.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel6.add(jlContentUnit);
        jPanel6.add(moCompContentUnit);

        jPanel1.add(jPanel6);

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlName.setText("Nombre:");
        jlName.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel8.add(jlName);

        jtfName.setEditable(false);
        jtfName.setFocusable(false);
        jtfName.setPreferredSize(new java.awt.Dimension(250, 23));
        jPanel8.add(jtfName);

        jPanel1.add(jPanel8);

        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlLotCode.setText("Código para lote:");
        jlLotCode.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel7.add(jlLotCode);

        moTextLotCode.setPreferredSize(new java.awt.Dimension(50, 23));
        jPanel7.add(moTextLotCode);

        jPanel1.add(jPanel7);

        jpContainer.add(jPanel1, java.awt.BorderLayout.NORTH);

        getContentPane().add(jpContainer, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel jlCode;
    private javax.swing.JLabel jlContentUnit;
    private javax.swing.JLabel jlLotCode;
    private javax.swing.JLabel jlName;
    private javax.swing.JLabel jlNameRaw;
    private javax.swing.JLabel jlUnit;
    private javax.swing.JPanel jpContainer;
    private javax.swing.JTextField jtfName;
    private sba.lib.gui.bean.DBeanCompoundField moCompContentUnit;
    private sba.lib.gui.bean.DBeanFieldKey moKeyUnit;
    private sba.lib.gui.bean.DBeanFieldText moTextCode;
    private sba.lib.gui.bean.DBeanFieldText moTextLotCode;
    private sba.lib.gui.bean.DBeanFieldText moTextNameRaw;
    // End of variables declaration//GEN-END:variables

    /*
     * Private methods
     */

    private void initComponentsCustom() {
        DGuiUtils.setWindowBounds(this, 400, 250);
        
        moTextCode.setTextSettings(DGuiUtils.getLabelName(jlCode), 5);
        moTextNameRaw.setTextSettings(DGuiUtils.getLabelName(jlNameRaw), 50);
        moKeyUnit.setKeySettings(miClient, DGuiUtils.getLabelName(jlUnit), true);
        moCompContentUnit.setCompoundFieldSettings(miClient);
        moCompContentUnit.getField().setDecimalSettings(DGuiUtils.getLabelName(jlContentUnit), DGuiConsts.GUI_TYPE_DEC_AMT_UNIT, true);
        moTextLotCode.setTextSettings(DGuiUtils.getLabelName(jlLotCode), 5, 0);
        
        moFields.addField(moTextCode);
        moFields.addField(moTextNameRaw);
        moFields.addField(moKeyUnit);
        moFields.addField(moCompContentUnit.getField());
        moFields.addField(moTextLotCode);
        
        moFields.setFormButton(jbSave);
    }
    
    private void displayUnitCode() {
        moCompContentUnit.setCompoundText(moKeyUnit.getSelectedIndex() <= 0 ? "" : (String) moKeyUnit.getSelectedItem().getComplement());
    }
    
    private void itemStateChangeUnit() {
        displayUnitCode();
    }
    
    private void computeName() {
        jtfName.setText(DDbPresent.computeName(
                miClient.getSession(),
                moTextNameRaw.getValue(),
                moCompContentUnit.getField().getValue(),
                moKeyUnit.getSelectedIndex() <= 0 ? "" : (String) moKeyUnit.getSelectedItem().getComplement()));
    }
    
    /*
     * Public methods
     */
    
    /*
     * Overriden methods
     */
    
    @Override
    public void addAllListeners() {
        moTextNameRaw.addFocusListener(this);
        moCompContentUnit.getField().getComponent().addFocusListener(this);
        moKeyUnit.addItemListener(this);
    }

    @Override
    public void removeAllListeners() {
        moTextNameRaw.removeFocusListener(this);
        moCompContentUnit.getField().getComponent().removeFocusListener(this);
        moKeyUnit.removeItemListener(this);
    }

    @Override
    public void reloadCatalogues() {
        miClient.getSession().populateCatalogue(moKeyUnit, DModConsts.CU_UNT, DLibConsts.UNDEFINED, null);
    }

    @Override
    public void setRegistry(DDbRegistry registry) throws Exception {
        moRegistry = (DDbPresent) registry;

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

        moTextCode.setValue(moRegistry.getCode());
        moTextNameRaw.setValue(moRegistry.getNameRaw());
        moKeyUnit.setValue(new int[] { moRegistry.getFkUnitId()});
        moCompContentUnit.getField().setValue(moRegistry.getContentUnit());
        moTextLotCode.setValue(moRegistry.getLotCode());
        
        computeName();
        displayUnitCode();

        setFormEditable(true);
        moTextCode.setEnabled(false);
        
        addAllListeners();
    }

    @Override
    public DDbPresent getRegistry() throws Exception {
        DDbPresent registry = moRegistry.clone();

        if (registry.isRegistryNew()) { }

        registry.setCode(moTextCode.getValue());
        //registry.setName(...);
        registry.setNameRaw(moTextNameRaw.getValue());
        registry.setLotCode(moTextLotCode.getValue());
        registry.setContentUnit(moCompContentUnit.getField().getValue());
        registry.setFkUnitId(moKeyUnit.getValue()[0]);

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
                
                if (field == moKeyUnit) {
                    computeName();
                    itemStateChangeUnit();
                }
            }
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() instanceof DBeanFieldText) {
            DBeanFieldText field = (DBeanFieldText) e.getSource();
            
            if (field == moTextNameRaw) {
                computeName();
            }
        }
        else if (e.getSource() instanceof DBeanFieldDecimal) {
            DBeanFieldDecimal field = (DBeanFieldDecimal) e.getSource();
            
            if (field == moCompContentUnit.getField()) {
                computeName();
            }
        }
    }
}
