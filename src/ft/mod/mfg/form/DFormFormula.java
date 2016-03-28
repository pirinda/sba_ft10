/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.mfg.form;

import ft.mod.DModConsts;
import ft.mod.DModSysConsts;
import ft.mod.cfg.db.DCfgUtils;
import ft.mod.cfg.db.DDbItem;
import ft.mod.mfg.db.DDbFormula;
import ft.mod.mfg.db.DDbFormulaComp;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import sba.lib.DLibConsts;
import sba.lib.DLibUtils;
import sba.lib.db.DDbRegistry;
import sba.lib.grid.DGridColumnForm;
import sba.lib.grid.DGridConsts;
import sba.lib.grid.DGridPaneForm;
import sba.lib.grid.DGridPaneFormOwner;
import sba.lib.grid.DGridRow;
import sba.lib.grid.DGridUtils;
import sba.lib.gui.DGuiClient;
import sba.lib.gui.DGuiConsts;
import sba.lib.gui.DGuiFieldKeyGroup;
import sba.lib.gui.DGuiFields;
import sba.lib.gui.DGuiUtils;
import sba.lib.gui.DGuiValidation;
import sba.lib.gui.bean.DBeanFieldKey;
import sba.lib.gui.bean.DBeanFieldRadio;
import sba.lib.gui.bean.DBeanForm;

/**
 *
 * @author Sergio Flores
 */
public class DFormFormula extends DBeanForm implements DGridPaneFormOwner, ActionListener, ItemListener {

    private DDbFormula moRegistry;
    private DGuiFieldKeyGroup moKeyGroupItem;
    private DGuiFields moFieldsComps;
    private DGridPaneForm moPaneFormComps;
    private JButton mjCompMoveUp;
    private JButton mjCompMoveDown;

    /** Creates new form DFormFormula */
    public DFormFormula(DGuiClient client, String title) {
        setFormSettings(client, DGuiConsts.BEAN_FORM_EDIT, DModConsts.MU_FRM, DLibConsts.UNDEFINED, title);
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

        jbgCompType = new javax.swing.ButtonGroup();
        jpContainer = new javax.swing.JPanel();
        jpFormula = new javax.swing.JPanel();
        jpFormula1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jlFormulaType = new javax.swing.JLabel();
        moKeyFormulaType = new sba.lib.gui.bean.DBeanFieldKey();
        jPanel6 = new javax.swing.JPanel();
        jlItemType = new javax.swing.JLabel();
        moKeyItemType = new sba.lib.gui.bean.DBeanFieldKey();
        jPanel16 = new javax.swing.JPanel();
        jlFamily = new javax.swing.JLabel();
        moKeyFamily = new sba.lib.gui.bean.DBeanFieldKey();
        jPanel15 = new javax.swing.JPanel();
        jlItem = new javax.swing.JLabel();
        moKeyItem = new sba.lib.gui.bean.DBeanFieldKey();
        jPanel9 = new javax.swing.JPanel();
        jlQuantity = new javax.swing.JLabel();
        moCompQuantity = new sba.lib.gui.bean.DBeanCompoundField();
        jPanel12 = new javax.swing.JPanel();
        jlReference = new javax.swing.JLabel();
        moTextReference = new sba.lib.gui.bean.DBeanFieldText();
        jpFormula2 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jlCode = new javax.swing.JLabel();
        jtfCode = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jlName = new javax.swing.JLabel();
        jtfName = new javax.swing.JTextField();
        jPanel21 = new javax.swing.JPanel();
        jlUnit = new javax.swing.JLabel();
        jtfUnit = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jlPresent = new javax.swing.JLabel();
        jtfPresent = new javax.swing.JTextField();
        jPanel22 = new javax.swing.JPanel();
        jlMassUnit = new javax.swing.JLabel();
        moCompMassUnit = new sba.lib.gui.bean.DBeanCompoundField();
        jPanel23 = new javax.swing.JPanel();
        jlMass = new javax.swing.JLabel();
        moCompMass = new sba.lib.gui.bean.DBeanCompoundField();
        jpFormulaComps = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        moRadCompTypeFamily = new sba.lib.gui.bean.DBeanFieldRadio();
        jlCompItemType = new javax.swing.JLabel();
        jlCompComponent = new javax.swing.JLabel();
        jlCompQuantity = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        moRadCompTypeItem = new sba.lib.gui.bean.DBeanFieldRadio();
        moKeyCompItemType = new sba.lib.gui.bean.DBeanFieldKey();
        moKeyCompComponent = new sba.lib.gui.bean.DBeanFieldKey();
        moCompCompQuantity = new sba.lib.gui.bean.DBeanCompoundField();
        moBoolCompStandard = new sba.lib.gui.bean.DBeanFieldBoolean();
        jbCompAdd = new javax.swing.JButton();
        jbCompClear = new javax.swing.JButton();

        jpContainer.setLayout(new java.awt.BorderLayout());

        jpFormula.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del registro:"));
        jpFormula.setLayout(new java.awt.GridLayout(1, 2));

        jpFormula1.setLayout(new java.awt.GridLayout(6, 1, 0, 5));

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlFormulaType.setText("Tipo fórmula:*");
        jlFormulaType.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel5.add(jlFormulaType);

        moKeyFormulaType.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel5.add(moKeyFormulaType);

        jpFormula1.add(jPanel5);

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlItemType.setText("Tipo producto:*");
        jlItemType.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel6.add(jlItemType);

        moKeyItemType.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel6.add(moKeyItemType);

        jpFormula1.add(jPanel6);

        jPanel16.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlFamily.setText("Familia:*");
        jlFamily.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel16.add(jlFamily);

        moKeyFamily.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel16.add(moKeyFamily);

        jpFormula1.add(jPanel16);

        jPanel15.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlItem.setText("Producto:*");
        jlItem.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel15.add(jlItem);

        moKeyItem.setPreferredSize(new java.awt.Dimension(300, 23));
        jPanel15.add(moKeyItem);

        jpFormula1.add(jPanel15);

        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlQuantity.setText("Cantidad:*");
        jlQuantity.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel9.add(jlQuantity);
        jPanel9.add(moCompQuantity);

        jpFormula1.add(jPanel9);

        jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlReference.setText("Referencia:");
        jlReference.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel12.add(jlReference);

        moTextReference.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel12.add(moTextReference);

        jpFormula1.add(jPanel12);

        jpFormula.add(jpFormula1);

        jpFormula2.setLayout(new java.awt.GridLayout(6, 1, 0, 5));

        jPanel18.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlCode.setText("Código:");
        jlCode.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel18.add(jlCode);

        jtfCode.setEditable(false);
        jtfCode.setFocusable(false);
        jtfCode.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel18.add(jtfCode);

        jpFormula2.add(jPanel18);

        jPanel19.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlName.setText("Nombre:");
        jlName.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel19.add(jlName);

        jtfName.setEditable(false);
        jtfName.setFocusable(false);
        jtfName.setPreferredSize(new java.awt.Dimension(300, 23));
        jPanel19.add(jtfName);

        jpFormula2.add(jPanel19);

        jPanel21.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlUnit.setText("Unidad:");
        jlUnit.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel21.add(jlUnit);

        jtfUnit.setEditable(false);
        jtfUnit.setFocusable(false);
        jtfUnit.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel21.add(jtfUnit);

        jpFormula2.add(jPanel21);

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlPresent.setText("Presentación:");
        jlPresent.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel8.add(jlPresent);

        jtfPresent.setEditable(false);
        jtfPresent.setFocusable(false);
        jtfPresent.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel8.add(jtfPresent);

        jpFormula2.add(jPanel8);

        jPanel22.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlMassUnit.setText("Masa unitaria:");
        jlMassUnit.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel22.add(jlMassUnit);
        jPanel22.add(moCompMassUnit);

        jpFormula2.add(jPanel22);

        jPanel23.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlMass.setText("Masa:");
        jlMass.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel23.add(jlMass);
        jPanel23.add(moCompMass);

        jpFormula2.add(jPanel23);

        jpFormula.add(jpFormula2);

        jpContainer.add(jpFormula, java.awt.BorderLayout.NORTH);

        jpFormulaComps.setBorder(javax.swing.BorderFactory.createTitledBorder("Componentes:"));
        jpFormulaComps.setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new java.awt.GridLayout(2, 1, 0, 5));

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jbgCompType.add(moRadCompTypeFamily);
        moRadCompTypeFamily.setText("Familia");
        jPanel4.add(moRadCompTypeFamily);

        jlCompItemType.setText("Tipo ítem:*");
        jlCompItemType.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel4.add(jlCompItemType);

        jlCompComponent.setText("Componente:*");
        jlCompComponent.setPreferredSize(new java.awt.Dimension(300, 23));
        jPanel4.add(jlCompComponent);

        jlCompQuantity.setText("Cantidad:");
        jlCompQuantity.setPreferredSize(new java.awt.Dimension(140, 23));
        jPanel4.add(jlCompQuantity);

        jPanel3.add(jPanel4);

        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jbgCompType.add(moRadCompTypeItem);
        moRadCompTypeItem.setText("Ítem");
        jPanel11.add(moRadCompTypeItem);

        moKeyCompItemType.setToolTipText("Tipo componente");
        moKeyCompItemType.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel11.add(moKeyCompItemType);

        moKeyCompComponent.setToolTipText("Componente");
        moKeyCompComponent.setPreferredSize(new java.awt.Dimension(300, 23));
        jPanel11.add(moKeyCompComponent);
        jPanel11.add(moCompCompQuantity);

        moBoolCompStandard.setText("Estándar");
        moBoolCompStandard.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel11.add(moBoolCompStandard);

        jbCompAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sba/lib/img/cmd_std_add.gif"))); // NOI18N
        jbCompAdd.setToolTipText("Agregar");
        jbCompAdd.setPreferredSize(new java.awt.Dimension(23, 23));
        jPanel11.add(jbCompAdd);

        jbCompClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sba/lib/img/cmd_std_clear.gif"))); // NOI18N
        jbCompClear.setToolTipText("Limpiar");
        jbCompClear.setPreferredSize(new java.awt.Dimension(23, 23));
        jPanel11.add(jbCompClear);

        jPanel3.add(jPanel11);

        jpFormulaComps.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jpContainer.add(jpFormulaComps, java.awt.BorderLayout.CENTER);

        getContentPane().add(jpContainer, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JButton jbCompAdd;
    private javax.swing.JButton jbCompClear;
    private javax.swing.ButtonGroup jbgCompType;
    private javax.swing.JLabel jlCode;
    private javax.swing.JLabel jlCompComponent;
    private javax.swing.JLabel jlCompItemType;
    private javax.swing.JLabel jlCompQuantity;
    private javax.swing.JLabel jlFamily;
    private javax.swing.JLabel jlFormulaType;
    private javax.swing.JLabel jlItem;
    private javax.swing.JLabel jlItemType;
    private javax.swing.JLabel jlMass;
    private javax.swing.JLabel jlMassUnit;
    private javax.swing.JLabel jlName;
    private javax.swing.JLabel jlPresent;
    private javax.swing.JLabel jlQuantity;
    private javax.swing.JLabel jlReference;
    private javax.swing.JLabel jlUnit;
    private javax.swing.JPanel jpContainer;
    private javax.swing.JPanel jpFormula;
    private javax.swing.JPanel jpFormula1;
    private javax.swing.JPanel jpFormula2;
    private javax.swing.JPanel jpFormulaComps;
    private javax.swing.JTextField jtfCode;
    private javax.swing.JTextField jtfName;
    private javax.swing.JTextField jtfPresent;
    private javax.swing.JTextField jtfUnit;
    private sba.lib.gui.bean.DBeanFieldBoolean moBoolCompStandard;
    private sba.lib.gui.bean.DBeanCompoundField moCompCompQuantity;
    private sba.lib.gui.bean.DBeanCompoundField moCompMass;
    private sba.lib.gui.bean.DBeanCompoundField moCompMassUnit;
    private sba.lib.gui.bean.DBeanCompoundField moCompQuantity;
    private sba.lib.gui.bean.DBeanFieldKey moKeyCompComponent;
    private sba.lib.gui.bean.DBeanFieldKey moKeyCompItemType;
    private sba.lib.gui.bean.DBeanFieldKey moKeyFamily;
    private sba.lib.gui.bean.DBeanFieldKey moKeyFormulaType;
    private sba.lib.gui.bean.DBeanFieldKey moKeyItem;
    private sba.lib.gui.bean.DBeanFieldKey moKeyItemType;
    private sba.lib.gui.bean.DBeanFieldRadio moRadCompTypeFamily;
    private sba.lib.gui.bean.DBeanFieldRadio moRadCompTypeItem;
    private sba.lib.gui.bean.DBeanFieldText moTextReference;
    // End of variables declaration//GEN-END:variables

    /*
     * Private methods
     */

    private void initComponentsCustom() {
        String mass = "";
        
        DGuiUtils.setWindowBounds(this, 960, 600);
        
        moKeyFormulaType.setKeySettings(miClient, DGuiUtils.getLabelName(jlFormulaType), true);
        moKeyItemType.setKeySettings(miClient, DGuiUtils.getLabelName(jlItemType), true);
        moKeyFamily.setKeySettings(miClient, DGuiUtils.getLabelName(jlFamily), true);
        moKeyItem.setKeySettings(miClient, DGuiUtils.getLabelName(jlItem), true);
        moCompQuantity.setCompoundFieldSettings(miClient);
        moCompQuantity.getField().setDecimalSettings(DGuiUtils.getLabelName(jlQuantity), DGuiConsts.GUI_TYPE_DEC_QTY, true);
        moTextReference.setTextSettings(DGuiUtils.getLabelName(jlReference), 50, 0);
        moCompMassUnit.setCompoundFieldSettings(miClient);
        moCompMassUnit.getField().setDecimalSettings(DGuiUtils.getLabelName(jlMassUnit), DGuiConsts.GUI_TYPE_DEC_AMT_UNIT, false);
        moCompMass.setCompoundFieldSettings(miClient);
        moCompMass.getField().setDecimalSettings(DGuiUtils.getLabelName(jlMass), DGuiConsts.GUI_TYPE_DEC_QTY, false);
        moRadCompTypeFamily.setBooleanSettings(moRadCompTypeFamily.getText(), true);
        moRadCompTypeItem.setBooleanSettings(moRadCompTypeItem.getText(), false);
        moKeyCompItemType.setKeySettings(miClient, moKeyCompItemType.getToolTipText(), false);
        moKeyCompComponent.setKeySettings(miClient, moKeyCompComponent.getToolTipText(), false);
        moCompCompQuantity.setCompoundFieldSettings(miClient);
        moCompCompQuantity.getField().setDecimalSettings(DGuiUtils.getLabelName(jlCompQuantity), DGuiConsts.GUI_TYPE_DEC_QTY, false);
        moBoolCompStandard.setBooleanSettings(moBoolCompStandard.getText(), false);
        
        moFields.addField(moKeyFormulaType);
        moFields.addField(moKeyItemType);
        moFields.addField(moKeyFamily);
        moFields.addField(moKeyItem);
        moFields.addField(moCompQuantity.getField());
        moFields.addField(moTextReference);
        //moFields.addField(moCompMassUnit.getField()); // always disabled
        //moFields.addField(moCompMass.getField()); // always disabled
        //moFields.setFormButton(jbSave); // not required
        
        moTextReference.setNextField(moRadCompTypeFamily);
        
        moFieldsComps = new DGuiFields();
        moFieldsComps.addField(moRadCompTypeFamily);
        moFieldsComps.addField(moRadCompTypeItem);
        moFieldsComps.addField(moKeyCompItemType);
        moFieldsComps.addField(moKeyCompComponent);
        moFieldsComps.addField(moCompCompQuantity.getField());
        moFieldsComps.addField(moBoolCompStandard);
        moFieldsComps.setFormButton(jbCompAdd);
        
        moKeyGroupItem = new DGuiFieldKeyGroup(miClient);
        
        mass = DCfgUtils.getMassUnitCode(miClient.getSession());
        moCompMassUnit.setCompoundText(mass);
        moCompMass.setCompoundText(mass);
        
        moCompMassUnit.setEditable(false);
        moCompMass.setEditable(false);
        
        mjCompMoveUp = DGridUtils.createButton(new ImageIcon(getClass().getResource("/sba/lib/img/cmd_std_move_up.gif")), "Mover arriba", this);
        mjCompMoveDown = DGridUtils.createButton(new ImageIcon(getClass().getResource("/sba/lib/img/cmd_std_move_down.gif")), "Mover abajo", this);
        
        moPaneFormComps = new DGridPaneForm(miClient, mnFormType, DModConsts.MU_FRM_CMP, DGuiUtils.getLabelName(((TitledBorder) jpFormulaComps.getBorder()).getTitle())) {
            
            @Override
            public void initGrid() {
                setRowButtonsEnabled(false, false, true);
            }
            
            @Override
            public void createGridColumns() {
                int col = 0;
                DGridColumnForm[] columns = new DGridColumnForm[8];

                columns[col++] = new DGridColumnForm(DGridConsts.COL_TYPE_INT_1B, "# componente");
                columns[col++] = new DGridColumnForm(DGridConsts.COL_TYPE_TEXT_CODE_CAT, DGridConsts.COL_TITLE_TYPE + " componente");
                columns[col++] = new DGridColumnForm(DGridConsts.COL_TYPE_TEXT_CODE_CAT, DGridConsts.COL_TITLE_TYPE + " ítem");
                columns[col++] = new DGridColumnForm(DGridConsts.COL_TYPE_TEXT_NAME_ITM_L, DGridConsts.COL_TITLE_NAME + " componente");
                columns[col++] = new DGridColumnForm(DGridConsts.COL_TYPE_TEXT_CODE_ITM, DGridConsts.COL_TITLE_CODE + " componente");
                columns[col++] = new DGridColumnForm(DGridConsts.COL_TYPE_DEC_QTY, "Cantidad componente");
                columns[col++] = new DGridColumnForm(DGridConsts.COL_TYPE_TEXT_CODE_UNT, "Unidad componente");
                columns[col++] = new DGridColumnForm(DGridConsts.COL_TYPE_BOOL_M, "Estándar componente");

                for (col = 0; col < columns.length; col++) {
                    moModel.getGridColumns().add(columns[col]);
                }
            }
        };
        
        moPaneFormComps.setPaneFormOwner(this);
        moPaneFormComps.getPanelCommandsSys(DGuiConsts.PANEL_CENTER).add(mjCompMoveUp);
        moPaneFormComps.getPanelCommandsSys(DGuiConsts.PANEL_CENTER).add(mjCompMoveDown);
        
        jpFormulaComps.add(moPaneFormComps, BorderLayout.CENTER);
        
        mvFormGrids.add(moPaneFormComps);
    }
    
    private void renderItem() {
        DDbItem moItem = null;
        
        if (moKeyItem.getSelectedIndex() <= 0) {
            jtfCode.setText("");
            jtfName.setText("");
            jtfUnit.setText("");
            jtfPresent.setText("");
            moCompMassUnit.getField().resetField();
            moCompQuantity.setCompoundText("");
        }
        else {
            moItem = (DDbItem) miClient.getSession().readRegistry(DModConsts.CU_ITM, moKeyItem.getValue());
            jtfCode.setText(moItem.getCode());
            jtfName.setText(moItem.getName());
            jtfUnit.setText(moItem.getRegUnit().getName());
            jtfPresent.setText(moItem.getRegPresent().getName());
            moCompMassUnit.getField().setValue(moItem.getMassUnit());
            moCompQuantity.setCompoundText(moItem.getRegUnit().getCode());
            
            jtfCode.setCaretPosition(0);
            jtfName.setCaretPosition(0);
            jtfUnit.setCaretPosition(0);
            jtfPresent.setCaretPosition(0);
        }
    }
    
    private void renderCompComponent() {
        if (moKeyCompComponent.getSelectedIndex() <= 0) {
            moCompCompQuantity.setCompoundText("");
        }
        else {
            moCompCompQuantity.setCompoundText((String) moKeyCompComponent.getSelectedItem().getComplement()); // unit code
        }
    }
    
    private void compute() {
        double mass = 0d;
        
        for (DGridRow row : moPaneFormComps.getModel().getGridRows()) {
            mass += ((DDbFormulaComp) row).getMass_r();
        }
        
        moCompMass.getField().setValue(mass);
    }
    
    private void updateCompsNumbers() {
        int number = 0;
        
        for (DGridRow row : moPaneFormComps.getModel().getGridRows()) {
            ((DDbFormulaComp) row).setPkCompId(++number);
        }
        
        moPaneFormComps.renderGridRows();
    }
    
    private void doCompAdd() {
        DGuiValidation validation = moFieldsComps.validateFields();

        if (validation.isValid()) {
            if (moBoolCompStandard.isSelected() && moCompCompQuantity.getField().getValue() == 0) {
                validation.setMessage(DGuiConsts.ERR_MSG_FIELD_REQ + "'" + DGuiUtils.getLabelName(jlCompQuantity) + "'.");
                validation.setComponent(moCompCompQuantity.getField().getComponent());
            }
            
            if (validation.isValid()) {
                DDbFormulaComp comp = new DDbFormulaComp();
                int indexItemType = moKeyCompItemType.getSelectedIndex();
                int indexItem = moKeyCompComponent.getSelectedIndex();

                //comp.setPkFormulaId(...);
                //comp.setPkCompId(...);
                comp.setQuantity(moCompCompQuantity.getField().getValue());
                //comp.setMassUnit(...);
                //comp.setMass_r(...);
                comp.setStandard(moBoolCompStandard.getValue());
                comp.setFkCompTypeId(moRadCompTypeItem.isSelected() ? DModSysConsts.MS_CMP_TP_ITM : DModSysConsts.MS_CMP_TP_FAM);
                comp.setFkCompId(moKeyCompComponent.getValue()[0]);
                //comp.setFkItemTypeId(...);
                //comp.setFkUnitId(...);

                comp.compute(miClient.getSession());

                moPaneFormComps.addGridRow(comp);
                updateCompsNumbers();
                moPaneFormComps.setSelectedGridRow(moPaneFormComps.getTable().getRowCount() - 1);

                compute();
                actionPerformedCompClear();
                moKeyCompItemType.setSelectedIndex(indexItemType);
                moKeyCompComponent.setSelectedIndex(indexItem);
            }
        }
        
        if (!validation.isValid()) {
            DGuiUtils.computeValidation(miClient, validation);
        }
    }
    
    private void doCompDelete() {
        compute();
    }
    
    private void doRowClear() {
        moFieldsComps.resetFields();
        moBoolCompStandard.setSelected(moKeyFormulaType.getValue()[0] == DModSysConsts.MS_FRM_TP_STD);
    }
    
    private void actionPerformedCompAdd() {
        doCompAdd();
    }
    
    private void actionPerformedCompClear() {
        doRowClear();
        moKeyCompItemType.requestFocus();
    }
    
    private void actionPerformedCompMoveUp() {
        int index = -1;
        DGridRow row = null;
        
        if (moPaneFormComps.getTable().getSelectedRowCount() != 1) {
            miClient.showMsgBoxInformation(DGridConsts.MSG_SELECT_ROW);
        }
        else if ((index = moPaneFormComps.getTable().getSelectedRow()) > 0) {
            row = moPaneFormComps.getModel().getGridRows().remove(index);
            moPaneFormComps.getModel().getGridRows().add(index - 1, row);
            updateCompsNumbers();
            moPaneFormComps.renderGridRows();
            moPaneFormComps.setSelectedGridRow(index - 1);
        }
    }
    
    private void actionPerformedCompMoveDown() {
        int index = -1;
        DGridRow row = null;
        
        if (moPaneFormComps.getTable().getSelectedRowCount() != 1) {
            miClient.showMsgBoxInformation(DGridConsts.MSG_SELECT_ROW);
        }
        else if ((index = moPaneFormComps.getTable().getSelectedRow()) < moPaneFormComps.getTable().getRowCount() - 1) {
            row = moPaneFormComps.getModel().getGridRows().remove(index);
            moPaneFormComps.getModel().getGridRows().add(index + 1, row);
            updateCompsNumbers();
            moPaneFormComps.renderGridRows();
            moPaneFormComps.setSelectedGridRow(index + 1);
        }
    }
    
    private void itemStateChangedFormulaType() {
        if (moKeyFormulaType.getSelectedIndex() <= 0) {
            moBoolCompStandard.setSelected(false);
        }
        else {
            moBoolCompStandard.setSelected(moKeyFormulaType.getValue()[0] == DModSysConsts.MS_FRM_TP_STD);
        }
    }
    
    private void itemStateChangedItem() {
        renderItem();
    }
    
    private void itemStateChangedCompTypeFamily() {
        moKeyCompItemType.setSelectedIndex(0);
    }
    
    private void itemStateChangedCompTypeItem() {
        moKeyCompItemType.setSelectedIndex(0);
    }
    
    private void itemStateChangedCompItemType() {
        int type = DLibConsts.UNDEFINED;
        
        if (moRadCompTypeItem.isSelected()) {
            type = DModConsts.CU_ITM;
        }
        else {
            type = DModConsts.CU_FAM;
        }
        
        moKeyCompComponent.setEnabled(false);
        
        if (moKeyCompItemType.getSelectedIndex() <= 0) {
            moKeyCompComponent.removeAllItems();
        }
        else {
            miClient.getSession().populateCatalogue(moKeyCompComponent, type, moKeyCompItemType.getValue()[0], null);
            moKeyCompComponent.setEnabled(true);
        }
        
        renderCompComponent();
    }
    
    private void itemStateChangedCompComponent() {
        renderCompComponent();
    }
    
    /*
     * Public methods
     */
    
    /*
     * Overriden methods
     */
    
    @Override
    public void addAllListeners() {
        jbCompAdd.addActionListener(this);
        jbCompClear.addActionListener(this);
        mjCompMoveUp.addActionListener(this);
        mjCompMoveDown.addActionListener(this);
        moKeyFormulaType.addItemListener(this);
        moKeyItem.addItemListener(this);
        moKeyCompItemType.addItemListener(this);
        moKeyCompComponent.addItemListener(this);
        moRadCompTypeFamily.addItemListener(this);
        moRadCompTypeItem.addItemListener(this);
    }

    @Override
    public void removeAllListeners() {
        jbCompAdd.removeActionListener(this);
        jbCompClear.removeActionListener(this);
        mjCompMoveUp.removeActionListener(this);
        mjCompMoveDown.removeActionListener(this);
        moKeyFormulaType.removeItemListener(this);
        moKeyItem.removeItemListener(this);
        moKeyCompItemType.removeItemListener(this);
        moKeyCompComponent.removeItemListener(this);
        moRadCompTypeFamily.removeItemListener(this);
        moRadCompTypeItem.removeItemListener(this);
    }

    @Override
    public void reloadCatalogues() {
        miClient.getSession().populateCatalogue(moKeyFormulaType, DModConsts.MS_FRM_TP, DLibConsts.UNDEFINED, null);
        
        moKeyGroupItem.initGroup();
        moKeyGroupItem.addFieldKey(moKeyItemType, DModConsts.CX_ITM_TP_PRO_MFG, DLibConsts.UNDEFINED, null);
        moKeyGroupItem.addFieldKey(moKeyFamily, DModConsts.CU_FAM, DLibConsts.UNDEFINED, null);
        moKeyGroupItem.addFieldKey(moKeyItem, DModConsts.CX_ITM_FK_FAM, DLibConsts.UNDEFINED, null);
        moKeyGroupItem.populateCatalogues();
        
        miClient.getSession().populateCatalogue(moKeyCompItemType, DModConsts.CX_ITM_TP_CMP, DLibConsts.UNDEFINED, null);
    }

    @Override
    public void setRegistry(DDbRegistry registry) throws Exception {
        moRegistry = (DDbFormula) registry;

        mnFormResult = DLibConsts.UNDEFINED;
        mbFirstActivation = true;

        removeAllListeners();
        reloadCatalogues();

        if (moRegistry.isRegistryNew()) {
            moRegistry.initPrimaryKey();
            jtfRegistryKey.setText("");
        }
        else {
            jtfRegistryKey.setText(DLibUtils.textKey(moRegistry.getPrimaryKey()));
        }

        moKeyFormulaType.setValue(new int[] { moRegistry.getFkFormulaTypeId() });
        moKeyItemType.setValue(new int[] { moRegistry.getFkItemTypeId() });
        moKeyFamily.setValue(new int[] { moRegistry.getRegItem() == null ? DLibConsts.UNDEFINED : moRegistry.getRegItem().getFkFamilyId() });
        moKeyItem.setValue(new int[] { moRegistry.getFkItemId() });
        moCompQuantity.getField().setValue(moRegistry.getQuantity());
        moTextReference.setValue(moRegistry.getReference());
        moCompMass.getField().setValue(moRegistry.getMass_r());
        
        itemStateChangedFormulaType();
        itemStateChangedItem();
        
        moRadCompTypeItem.setSelected(true);
        itemStateChangedCompTypeItem();
        itemStateChangedCompItemType();
        
        moPaneFormComps.populateGrid(new Vector<>(moRegistry.getChildComps()));
        
        setFormEditable(true);
        
        if (moRegistry.isRegistryNew()) {
            moKeyGroupItem.resetGroup();
        }
        else {
        }
        
        addAllListeners();
    }

    @Override
    public DDbFormula getRegistry() throws Exception {
        DDbFormula registry = moRegistry.clone();

        if (registry.isRegistryNew()) {
            //registry.setPkFormulaId(...);
        }

        //registry.setCode(...);
        //registry.setName(...);
        registry.setReference(moTextReference.getValue());
        registry.setQuantity(moCompQuantity.getField().getValue());
        //registry.setMass_r(...);
        //registry.setDeleted(...);
        //registry.setSystem(...);
        registry.setFkFormulaTypeId(moKeyFormulaType.getValue()[0]);
        registry.setFkItemId(moKeyItem.getValue()[0]);
        //registry.setFkItemTypeId(...);
        //registry.setFkUnitId(...);
        //registry.setFkPresentId(...);
        
        registry.getChildComps().clear();
        for (DGridRow row : moPaneFormComps.getModel().getGridRows()) {
            registry.getChildComps().add((DDbFormulaComp) row);
        }

        return registry;
    }

    @Override
    public DGuiValidation validateForm() {
        return moFields.validateFields();
    }

    @Override
    public void notifyRowNew(int gridType, int gridSubtype, int row, DGridRow gridRow) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void notifyRowEdit(int gridType, int gridSubtype, int row, DGridRow gridRow) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void notifyRowDelete(int gridType, int gridSubtype, int row, DGridRow gridRow) {
        doCompDelete();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            
            if (button == jbCompAdd) {
                actionPerformedCompAdd();
            }
            else if (button == jbCompClear) {
                actionPerformedCompClear();
            }
            else if (button == mjCompMoveUp) {
                actionPerformedCompMoveUp();
            }
            else if (button == mjCompMoveDown) {
                actionPerformedCompMoveDown();
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() instanceof DBeanFieldKey) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                DBeanFieldKey field = (DBeanFieldKey) e.getSource();
                
                if (field == moKeyFormulaType) {
                    itemStateChangedFormulaType();
                }
                else if (field == moKeyItem) {
                    itemStateChangedItem();
                }
                else if (field == moKeyCompItemType) {
                    itemStateChangedCompItemType();
                }
                else if (field == moKeyCompComponent) {
                    itemStateChangedCompComponent();
                }
            }
        }
        else if (e.getSource() instanceof DBeanFieldRadio) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                DBeanFieldRadio field = (DBeanFieldRadio) e.getSource();

                if (field == moRadCompTypeFamily) {
                    itemStateChangedCompTypeFamily();
                }
                else if (field == moRadCompTypeItem) {
                    itemStateChangedCompTypeItem();
                }
            }
        }
    }
}
