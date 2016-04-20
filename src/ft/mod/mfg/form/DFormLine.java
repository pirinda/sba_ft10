/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.mfg.form;

import ft.mod.DModConsts;
import ft.mod.mfg.db.DDbLine;
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
public class DFormLine extends DBeanForm {

    private DDbLine moRegistry;

    /** Creates new form DFormLine */
    public DFormLine(DGuiClient client, String title) {
        setFormSettings(client, DGuiConsts.BEAN_FORM_EDIT, DModConsts.MU_LIN, DLibConsts.UNDEFINED, title);
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
        jlDepart = new javax.swing.JLabel();
        moKeyDepart = new sba.lib.gui.bean.DBeanFieldKey();
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

        jlDepart.setText("Depto. producción:*");
        jlDepart.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel5.add(jlDepart);

        moKeyDepart.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel5.add(moKeyDepart);

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

        jpContainer.add(jPanel1, java.awt.BorderLayout.NORTH);

        getContentPane().add(jpContainer, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel jlCode;
    private javax.swing.JLabel jlDepart;
    private javax.swing.JLabel jlName;
    private javax.swing.JPanel jpContainer;
    private sba.lib.gui.bean.DBeanFieldKey moKeyDepart;
    private sba.lib.gui.bean.DBeanFieldText moTextCode;
    private sba.lib.gui.bean.DBeanFieldText moTextName;
    // End of variables declaration//GEN-END:variables

    /*
     * Private methods
     */

    private void initComponentsCustom() {
        DGuiUtils.setWindowBounds(this, 400, 250);
        
        moKeyDepart.setKeySettings(miClient, DGuiUtils.getLabelName(jlDepart), true);
        moTextCode.setTextSettings(DGuiUtils.getLabelName(jlCode), 5);
        moTextName.setTextSettings(DGuiUtils.getLabelName(jlName), 50);
        
        moFields.addField(moKeyDepart);
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
        miClient.getSession().populateCatalogue(moKeyDepart, DModConsts.MU_DPT, DLibConsts.UNDEFINED, null);
    }

    @Override
    public void setRegistry(DDbRegistry registry) throws Exception {
        moRegistry = (DDbLine) registry;
        
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

        moKeyDepart.setValue(new int[] { moRegistry.getFkDepartId() });
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
    public DDbLine getRegistry() throws Exception {
        DDbLine registry = moRegistry.clone();

        if (registry.isRegistryNew()) { }

        registry.setCode(moTextCode.getValue());
        registry.setName(moTextName.getValue());
        registry.setFkDepartId(moKeyDepart.getValue()[0]);

        return registry;
    }

    @Override
    public DGuiValidation validateForm() {
        return moFields.validateFields();
    }
}
