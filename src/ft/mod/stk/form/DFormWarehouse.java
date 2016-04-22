/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.stk.form;

import ft.mod.DModConsts;
import ft.mod.stk.db.DDbWarehouse;
import sba.lib.DLibConsts;
import sba.lib.DLibUtils;
import sba.lib.db.DDbRegistry;
import sba.lib.gui.DGuiClient;
import sba.lib.gui.DGuiConsts;
import sba.lib.gui.DGuiUtils;
import sba.lib.gui.DGuiValidation;
import sba.lib.gui.bean.DBeanForm;

/**
 *
 * @author Sergio Flores
 */
public class DFormWarehouse extends DBeanForm {

    private DDbWarehouse moRegistry;

    /** Creates new form DFormWarehouse */
    public DFormWarehouse(DGuiClient client, String title) {
        setFormSettings(client, DGuiConsts.BEAN_FORM_EDIT, DModConsts.SU_WHS, DLibConsts.UNDEFINED, title);
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
        jlWarehouseType = new javax.swing.JLabel();
        moKeyWarehouseType = new sba.lib.gui.bean.DBeanFieldKey();
        jPanel3 = new javax.swing.JPanel();
        jlCode = new javax.swing.JLabel();
        moTextCode = new sba.lib.gui.bean.DBeanFieldText();
        jPanel4 = new javax.swing.JPanel();
        jlName = new javax.swing.JLabel();
        moTextName = new sba.lib.gui.bean.DBeanFieldText();

        jpContainer.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del registro:"));
        jpContainer.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridLayout(3, 1, 0, 5));

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlWarehouseType.setText("Tipo almacén:*");
        jlWarehouseType.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel5.add(jlWarehouseType);

        moKeyWarehouseType.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel5.add(moKeyWarehouseType);

        jPanel1.add(jPanel5);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlCode.setForeground(new java.awt.Color(0, 102, 102));
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

        jpContainer.add(jPanel1, java.awt.BorderLayout.NORTH);

        getContentPane().add(jpContainer, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel jlCode;
    private javax.swing.JLabel jlName;
    private javax.swing.JLabel jlWarehouseType;
    private javax.swing.JPanel jpContainer;
    private sba.lib.gui.bean.DBeanFieldKey moKeyWarehouseType;
    private sba.lib.gui.bean.DBeanFieldText moTextCode;
    private sba.lib.gui.bean.DBeanFieldText moTextName;
    // End of variables declaration//GEN-END:variables

    /*
     * Private methods
     */

    private void initComponentsCustom() {
        DGuiUtils.setWindowBounds(this, 400, 250);
        
        moKeyWarehouseType.setKeySettings(miClient, DGuiUtils.getLabelName(jlWarehouseType), true);
        moTextCode.setTextSettings(DGuiUtils.getLabelName(jlCode), 5);
        moTextName.setTextSettings(DGuiUtils.getLabelName(jlName), 50);
        
        moFields.addField(moKeyWarehouseType);
        moFields.addField(moTextCode);
        moFields.addField(moTextName);
        
        moFields.setFormButton(jbSave);
    }
    
    /*
     * Public methods
     */
    
    /*
     * Overriden methods
     */
    
    @Override
    public void addAllListeners() {

    }

    @Override
    public void removeAllListeners() {

    }

    @Override
    public void reloadCatalogues() {
        miClient.getSession().populateCatalogue(moKeyWarehouseType, DModConsts.SS_WHS_TP, DLibConsts.UNDEFINED, null);
    }

    @Override
    public void setRegistry(DDbRegistry registry) throws Exception {
        moRegistry = (DDbWarehouse) registry;

        mnFormResult = DLibConsts.UNDEFINED;
        mbFirstActivation = true;

        removeAllListeners();
        reloadCatalogues();

        if (moRegistry.isRegistryNew()) {
            moRegistry.initPrimaryKey();
            moRegistry.setCode("");
            
            jtfRegistryKey.setText("");
        }
        else {
            jtfRegistryKey.setText(DLibUtils.textKey(moRegistry.getPrimaryKey()));
        }

        moKeyWarehouseType.setValue(new int[] { moRegistry.getFkWarehouseTypeId() });
        moTextCode.setValue(moRegistry.getCode());
        moTextName.setValue(moRegistry.getName());
        
        setFormEditable(true);

        moTextCode.setEnabled(false);
        
        if (moRegistry.isRegistryNew()) {
            
        }
        else {
            
        }
        
        addAllListeners();
    }

    @Override
    public DDbWarehouse getRegistry() throws Exception {
        DDbWarehouse registry = moRegistry.clone();

        if (registry.isRegistryNew()) {
            //registry.setPkWarehouseId(...);
        }

        registry.setCode(moTextCode.getValue());
        registry.setName(moTextName.getValue());
        //registry.setDeleted(...);
        //registry.setSystem(...);
        registry.setFkWarehouseTypeId(moKeyWarehouseType.getValue()[0]);

        return registry;
    }

    @Override
    public DGuiValidation validateForm() {
        return moFields.validateFields();
    }
}
