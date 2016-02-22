/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.cfg.form;

import ft.mod.DModConsts;
import ft.mod.DModSysConsts;
import ft.mod.cfg.db.DCfgConsts;
import ft.mod.cfg.db.DDbBizPartner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
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
public class DFormBizPartner extends DBeanForm implements ActionListener, ItemListener {

    private DDbBizPartner moRegistry;

    /** Creates new form DFormBizPartner */
    public DFormBizPartner(DGuiClient client, int personType, String title) {
        setFormSettings(client, DGuiConsts.BEAN_FORM_EDIT, DModConsts.CU_BPR, personType, title);
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
        jlPersonType = new javax.swing.JLabel();
        moKeyPersonType = new sba.lib.gui.bean.DBeanFieldKey();
        jPanel3 = new javax.swing.JPanel();
        jlCode = new javax.swing.JLabel();
        moTextCode = new sba.lib.gui.bean.DBeanFieldText();
        jPanel4 = new javax.swing.JPanel();
        jlNameLast = new javax.swing.JLabel();
        moTextNameLast = new sba.lib.gui.bean.DBeanFieldText();
        jPanel8 = new javax.swing.JPanel();
        jlNameFirst = new javax.swing.JLabel();
        moTextNameFirst = new sba.lib.gui.bean.DBeanFieldText();
        jPanel9 = new javax.swing.JPanel();
        jlName = new javax.swing.JLabel();
        moTextName = new sba.lib.gui.bean.DBeanFieldText();
        jPanel10 = new javax.swing.JPanel();
        jlNameShort = new javax.swing.JLabel();
        moTextNameShort = new sba.lib.gui.bean.DBeanFieldText();
        jPanel11 = new javax.swing.JPanel();
        jlTaxId = new javax.swing.JLabel();
        moTextTaxId = new sba.lib.gui.bean.DBeanFieldText();
        jbSetDefaultTax = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jlAddress1 = new javax.swing.JLabel();
        moTextAddress1 = new sba.lib.gui.bean.DBeanFieldText();
        jPanel16 = new javax.swing.JPanel();
        jlAddress2 = new javax.swing.JLabel();
        moTextAddress2 = new sba.lib.gui.bean.DBeanFieldText();
        jPanel17 = new javax.swing.JPanel();
        jlAddress3 = new javax.swing.JLabel();
        moTextAddress3 = new sba.lib.gui.bean.DBeanFieldText();
        jPanel18 = new javax.swing.JPanel();
        jlZip = new javax.swing.JLabel();
        moTextZip = new sba.lib.gui.bean.DBeanFieldText();
        jPanel13 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jlContact = new javax.swing.JLabel();
        moTextContact = new sba.lib.gui.bean.DBeanFieldText();
        jPanel20 = new javax.swing.JPanel();
        jlTelephone = new javax.swing.JLabel();
        moTextTelephone = new sba.lib.gui.bean.DBeanFieldText();
        jPanel21 = new javax.swing.JPanel();
        jlMail = new javax.swing.JLabel();
        moTextMail = new sba.lib.gui.bean.DBeanFieldText();
        jPanel22 = new javax.swing.JPanel();
        jlNotes = new javax.swing.JLabel();
        moTextNotes = new sba.lib.gui.bean.DBeanFieldText();

        jpContainer.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del registro:"));
        jpContainer.setLayout(new java.awt.BorderLayout(0, 5));

        jPanel1.setLayout(new java.awt.GridLayout(7, 1, 0, 5));

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlPersonType.setText("Tipo persona:*");
        jlPersonType.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel5.add(jlPersonType);

        moKeyPersonType.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel5.add(moKeyPersonType);

        jPanel1.add(jPanel5);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlCode.setText("Código:*");
        jlCode.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel3.add(jlCode);
        jPanel3.add(moTextCode);

        jPanel1.add(jPanel3);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlNameLast.setText("Apellido(s):*");
        jlNameLast.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel4.add(jlNameLast);

        moTextNameLast.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel4.add(moTextNameLast);

        jPanel1.add(jPanel4);

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlNameFirst.setText("Nombre(s):*");
        jlNameFirst.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel8.add(jlNameFirst);

        moTextNameFirst.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel8.add(moTextNameFirst);

        jPanel1.add(jPanel8);

        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlName.setText("Razón social:*");
        jlName.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel9.add(jlName);

        moTextName.setPreferredSize(new java.awt.Dimension(300, 23));
        jPanel9.add(moTextName);

        jPanel1.add(jPanel9);

        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlNameShort.setText("Alias:");
        jlNameShort.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel10.add(jlNameShort);

        moTextNameShort.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel10.add(moTextNameShort);

        jPanel1.add(jPanel10);

        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlTaxId.setText("RFC:*");
        jlTaxId.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel11.add(jlTaxId);

        moTextTaxId.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel11.add(moTextTaxId);

        jbSetDefaultTax.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sba/lib/img/cmd_std_ok.gif"))); // NOI18N
        jbSetDefaultTax.setPreferredSize(new java.awt.Dimension(23, 23));
        jPanel11.add(jbSetDefaultTax);

        jPanel1.add(jPanel11);

        jpContainer.add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel15.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.GridLayout(1, 2, 5, 0));

        jPanel12.setLayout(new java.awt.GridLayout(4, 1, 0, 5));

        jPanel14.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlAddress1.setText("Calle:");
        jlAddress1.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel14.add(jlAddress1);

        moTextAddress1.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel14.add(moTextAddress1);

        jPanel12.add(jPanel14);

        jPanel16.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlAddress2.setText("Colonia:");
        jlAddress2.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel16.add(jlAddress2);

        moTextAddress2.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel16.add(moTextAddress2);

        jPanel12.add(jPanel16);

        jPanel17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlAddress3.setText("Localidad:");
        jlAddress3.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel17.add(jlAddress3);

        moTextAddress3.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel17.add(moTextAddress3);

        jPanel12.add(jPanel17);

        jPanel18.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlZip.setText("Código postal:");
        jlZip.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel18.add(jlZip);
        jPanel18.add(moTextZip);

        jPanel12.add(jPanel18);

        jPanel2.add(jPanel12);

        jPanel13.setLayout(new java.awt.GridLayout(4, 1, 0, 5));

        jPanel19.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlContact.setText("Contacto(s):");
        jlContact.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel19.add(jlContact);

        moTextContact.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel19.add(moTextContact);

        jPanel13.add(jPanel19);

        jPanel20.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlTelephone.setText("Teléfono(s):");
        jlTelephone.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel20.add(jlTelephone);

        moTextTelephone.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel20.add(moTextTelephone);

        jPanel13.add(jPanel20);

        jPanel21.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlMail.setForeground(new java.awt.Color(0, 102, 102));
        jlMail.setText("Correo(s):");
        jlMail.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel21.add(jlMail);

        moTextMail.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel21.add(moTextMail);

        jPanel13.add(jPanel21);

        jPanel22.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlNotes.setText("Notas:");
        jlNotes.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel22.add(jlNotes);

        moTextNotes.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel22.add(moTextNotes);

        jPanel13.add(jPanel22);

        jPanel2.add(jPanel13);

        jPanel15.add(jPanel2, java.awt.BorderLayout.NORTH);

        jpContainer.add(jPanel15, java.awt.BorderLayout.CENTER);

        getContentPane().add(jpContainer, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JButton jbSetDefaultTax;
    private javax.swing.JLabel jlAddress1;
    private javax.swing.JLabel jlAddress2;
    private javax.swing.JLabel jlAddress3;
    private javax.swing.JLabel jlCode;
    private javax.swing.JLabel jlContact;
    private javax.swing.JLabel jlMail;
    private javax.swing.JLabel jlName;
    private javax.swing.JLabel jlNameFirst;
    private javax.swing.JLabel jlNameLast;
    private javax.swing.JLabel jlNameShort;
    private javax.swing.JLabel jlNotes;
    private javax.swing.JLabel jlPersonType;
    private javax.swing.JLabel jlTaxId;
    private javax.swing.JLabel jlTelephone;
    private javax.swing.JLabel jlZip;
    private javax.swing.JPanel jpContainer;
    private sba.lib.gui.bean.DBeanFieldKey moKeyPersonType;
    private sba.lib.gui.bean.DBeanFieldText moTextAddress1;
    private sba.lib.gui.bean.DBeanFieldText moTextAddress2;
    private sba.lib.gui.bean.DBeanFieldText moTextAddress3;
    private sba.lib.gui.bean.DBeanFieldText moTextCode;
    private sba.lib.gui.bean.DBeanFieldText moTextContact;
    private sba.lib.gui.bean.DBeanFieldText moTextMail;
    private sba.lib.gui.bean.DBeanFieldText moTextName;
    private sba.lib.gui.bean.DBeanFieldText moTextNameFirst;
    private sba.lib.gui.bean.DBeanFieldText moTextNameLast;
    private sba.lib.gui.bean.DBeanFieldText moTextNameShort;
    private sba.lib.gui.bean.DBeanFieldText moTextNotes;
    private sba.lib.gui.bean.DBeanFieldText moTextTaxId;
    private sba.lib.gui.bean.DBeanFieldText moTextTelephone;
    private sba.lib.gui.bean.DBeanFieldText moTextZip;
    // End of variables declaration//GEN-END:variables

    /*
     * Private methods
     */

    private void initComponentsCustom() {
        DGuiUtils.setWindowBounds(this, 720, 450);
        
        moKeyPersonType.setKeySettings(miClient, DGuiUtils.getLabelName(jlPersonType), true);
        moTextCode.setTextSettings(DGuiUtils.getLabelName(jlCode), 10);
        moTextNameLast.setTextSettings(DGuiUtils.getLabelName(jlNameLast), 50);
        moTextNameFirst.setTextSettings(DGuiUtils.getLabelName(jlNameFirst), 50);
        moTextName.setTextSettings(DGuiUtils.getLabelName(jlName), 102);
        moTextNameShort.setTextSettings(DGuiUtils.getLabelName(jlNameShort), 50, 0);
        moTextTaxId.setTextSettings(DGuiUtils.getLabelName(jlTaxId), 25);
        moTextAddress1.setTextSettings(DGuiUtils.getLabelName(jlAddress1), 100, 0);
        moTextAddress2.setTextSettings(DGuiUtils.getLabelName(jlAddress2), 100, 0);
        moTextAddress3.setTextSettings(DGuiUtils.getLabelName(jlAddress3), 100, 0);
        moTextZip.setTextSettings(DGuiUtils.getLabelName(jlZip), 10, 0);
        moTextContact.setTextSettings(DGuiUtils.getLabelName(jlContact), 100, 0);
        moTextTelephone.setTextSettings(DGuiUtils.getLabelName(jlTelephone), 100, 0);
        moTextMail.setTextSettings(DGuiUtils.getLabelName(jlMail), 255, 0);
        moTextMail.setTextCaseType(DLibConsts.UNDEFINED);
        moTextNotes.setTextSettings(DGuiUtils.getLabelName(jlNotes), 255, 0);
        
        moFields.addField(moKeyPersonType);
        moFields.addField(moTextCode);
        moFields.addField(moTextNameLast);
        moFields.addField(moTextNameFirst);
        moFields.addField(moTextName);
        moFields.addField(moTextNameShort);
        moFields.addField(moTextTaxId);
        moFields.addField(moTextAddress1);
        moFields.addField(moTextAddress2);
        moFields.addField(moTextAddress3);
        moFields.addField(moTextZip);
        moFields.addField(moTextContact);
        moFields.addField(moTextTelephone);
        moFields.addField(moTextMail);
        moFields.addField(moTextNotes);
        
        moFields.setFormButton(jbSave);
    }
    
    private void updatePersonType() {
        if (moKeyPersonType.getSelectedIndex() <= 0) {
            moTextNameLast.setEditable(false);
            moTextNameFirst.setEditable(false);
            moTextName.setEditable(false);
        }
        else {
            moTextNameLast.setEditable(moKeyPersonType.getValue()[0] == DModSysConsts.CS_PER_TP_PER);
            moTextNameFirst.setEditable(moKeyPersonType.getValue()[0] == DModSysConsts.CS_PER_TP_PER);
            moTextName.setEditable(moKeyPersonType.getValue()[0] == DModSysConsts.CS_PER_TP_ORG);
        }
    }
    
    private void actionSetDefaultTax() {
        moTextTaxId.setValue(DCfgConsts.TAX_ID_DEFAULT);
        moTextTaxId.requestFocus();
    }
    
    private void itemStateChangePersonType() {
        updatePersonType();
    }
    
    /*
     * Public methods
     */
    
    /*
     * Overriden methods
     */
    
    @Override
    public void addAllListeners() {
        jbSetDefaultTax.addActionListener(this);
        moKeyPersonType.addItemListener(this);
    }

    @Override
    public void removeAllListeners() {
        jbSetDefaultTax.removeActionListener(this);
        moKeyPersonType.removeItemListener(this);
    }

    @Override
    public void reloadCatalogues() {
        miClient.getSession().populateCatalogue(moKeyPersonType, DModConsts.CS_PER_TP, DLibConsts.UNDEFINED, null);
    }

    @Override
    public void setRegistry(DDbRegistry registry) throws Exception {
        moRegistry = (DDbBizPartner) registry;

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

        moKeyPersonType.setValue(new int[] { moRegistry.getFkPersonTypeId() });
        moTextCode.setValue(moRegistry.getCode());
        moTextNameLast.setValue(moRegistry.getNameLast());
        moTextNameFirst.setValue(moRegistry.getNameFirst());
        moTextName.setValue(moRegistry.getName());
        moTextNameShort.setValue(moRegistry.getNameShort());
        moTextTaxId.setValue(moRegistry.getTaxId());
        moTextAddress1.setValue(moRegistry.getAddress1());
        moTextAddress2.setValue(moRegistry.getAddress2());
        moTextAddress3.setValue(moRegistry.getAddress3());
        moTextZip.setValue(moRegistry.getZip());
        moTextContact.setValue(moRegistry.getContact());
        moTextTelephone.setValue(moRegistry.getTelephone());
        moTextMail.setValue(moRegistry.getMail());
        moTextNotes.setValue(moRegistry.getNotes());
        
        setFormEditable(true);
        
        moTextCode.setEnabled(false);
        
        updatePersonType();
        
        if (moRegistry.isRegistryNew()) {
            
        }
        else {
            
        }
        
        addAllListeners();
    }

    @Override
    public DDbBizPartner getRegistry() throws Exception {
        DDbBizPartner registry = moRegistry.clone();

        if (registry.isRegistryNew()) {
            //registry.setPkBizPartnerId(...);
        }

        registry.setCode(moTextCode.getValue());
        registry.setName(moTextName.getValue());
        registry.setNameShort(moTextNameShort.getValue());
        registry.setNameLast(moTextNameLast.getValue());
        registry.setNameFirst(moTextNameFirst.getValue());
        registry.setTaxId(moTextTaxId.getValue());
        registry.setAddress1(moTextAddress1.getValue());
        registry.setAddress2(moTextAddress2.getValue());
        registry.setAddress3(moTextAddress3.getValue());
        registry.setZip(moTextZip.getValue());
        registry.setContact(moTextContact.getValue());
        registry.setTelephone(moTextTelephone.getValue());
        registry.setMail(moTextMail.getValue());
        registry.setNotes(moTextNotes.getValue());
        //registry.setDeleted(...);
        //registry.setSystem(...);
        registry.setFkBizPartnerTypeId(mnFormSubtype);
        registry.setFkPersonTypeId(moKeyPersonType.getValue()[0]);

        return registry;
    }

    @Override
    public DGuiValidation validateForm() {
        return moFields.validateFields();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            
            if (button == jbSetDefaultTax) {
                actionSetDefaultTax();
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() instanceof DBeanFieldKey) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                DBeanFieldKey field = (DBeanFieldKey) e.getSource();
                
                if (field == moKeyPersonType) {
                    itemStateChangePersonType();
                }
            }
        }
    }
}
