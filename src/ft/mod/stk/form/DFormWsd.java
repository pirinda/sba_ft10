/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.stk.form;

import ft.mod.DModConsts;
import ft.mod.DModSysConsts;
import ft.mod.cfg.db.DCfgUtils;
import ft.mod.stk.db.DDbWsd;
import ft.mod.stk.db.DDbWsdRow;
import ft.mod.stk.db.DStkConsts;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Vector;
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
import sba.lib.gui.DGuiClient;
import sba.lib.gui.DGuiConsts;
import sba.lib.gui.DGuiField;
import sba.lib.gui.DGuiFieldKeyGroup;
import sba.lib.gui.DGuiFields;
import sba.lib.gui.DGuiUtils;
import sba.lib.gui.DGuiValidation;
import sba.lib.gui.bean.DBeanFieldDecimal;
import sba.lib.gui.bean.DBeanFieldKey;
import sba.lib.gui.bean.DBeanForm;

/**
 *
 * @author Sergio Flores
 */
public class DFormWsd extends DBeanForm implements DGridPaneFormOwner, ActionListener, ItemListener, FocusListener {

    private DDbWsd moRegistry;
    private DGuiFieldKeyGroup moKeyGroupItem;
    private DGuiFields moFieldsRow;
    private ArrayList<DGuiField> maFieldsWsd;
    private DGridPaneForm moPaneFormRows;
    private boolean mbIsStockAdjustTypeReq;
    private boolean mbIsBizPartnerReq;

    /** Creates new form DFormWsd */
    public DFormWsd(DGuiClient client, int moveClass, String title) {
        setFormSettings(client, DGuiConsts.BEAN_FORM_EDIT, DModConsts.SU_WHS, moveClass, title);
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

        jpWsd = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jpContainer = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jlMoveType = new javax.swing.JLabel();
        jtfMoveType = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jlTransactMoveType = new javax.swing.JLabel();
        jtfTransactMoveType = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jlMfgMoveType = new javax.swing.JLabel();
        jtfMfgMoveType = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jlWarehouse = new javax.swing.JLabel();
        moKeyWarehouse = new sba.lib.gui.bean.DBeanFieldKey();
        jPanel13 = new javax.swing.JPanel();
        jlStockAdjustType = new javax.swing.JLabel();
        moKeyStockAdjustType = new sba.lib.gui.bean.DBeanFieldKey();
        jpContainer1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jlDepart = new javax.swing.JLabel();
        jtfDepart = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jlLine = new javax.swing.JLabel();
        jtfLine = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jlJob = new javax.swing.JLabel();
        jtfJob = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jlBizPartner = new javax.swing.JLabel();
        moKeyBizPartner = new sba.lib.gui.bean.DBeanFieldKey();
        jPanel18 = new javax.swing.JPanel();
        jlItemType = new javax.swing.JLabel();
        moKeyItemType = new sba.lib.gui.bean.DBeanFieldKey();
        jPanel11 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jlNumber = new javax.swing.JLabel();
        jtfSeries = new javax.swing.JTextField();
        jtfNumber = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        jlDate = new javax.swing.JLabel();
        moDateDate = new sba.lib.gui.bean.DBeanFieldDate();
        jPanel21 = new javax.swing.JPanel();
        jlReference = new javax.swing.JLabel();
        moTextReference = new sba.lib.gui.bean.DBeanFieldText();
        jPanel22 = new javax.swing.JPanel();
        jlAmount = new javax.swing.JLabel();
        moCurAmount = new sba.lib.gui.bean.DBeanCompoundFieldCurrency();
        jPanel23 = new javax.swing.JPanel();
        jlMass = new javax.swing.JLabel();
        moCompMass = new sba.lib.gui.bean.DBeanCompoundField();
        jpWsdRows = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jlRowItem = new javax.swing.JLabel();
        jlRowUnits = new javax.swing.JLabel();
        jlRowLot = new javax.swing.JLabel();
        jlRowAmountUnit = new javax.swing.JLabel();
        jlRowAmount = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        moKeyRowItem = new sba.lib.gui.bean.DBeanFieldKey();
        moCompRowUnits = new sba.lib.gui.bean.DBeanCompoundField();
        moTextRowLot = new sba.lib.gui.bean.DBeanFieldText();
        moCurRowAmountUnit = new sba.lib.gui.bean.DBeanCompoundFieldCurrency();
        moCurRowAmount = new sba.lib.gui.bean.DBeanCompoundFieldCurrency();
        jbRowAdd = new javax.swing.JButton();
        jbRowClear = new javax.swing.JButton();

        jpWsd.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del documento:"));
        jpWsd.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.GridLayout(1, 3));

        jpContainer.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridLayout(5, 1, 0, 5));

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlMoveType.setText("Mov. almacén:");
        jlMoveType.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel5.add(jlMoveType);

        jtfMoveType.setEditable(false);
        jtfMoveType.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jtfMoveType.setFocusable(false);
        jtfMoveType.setPreferredSize(new java.awt.Dimension(225, 23));
        jPanel5.add(jtfMoveType);

        jPanel1.add(jPanel5);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlTransactMoveType.setText("Mov. transacción:");
        jlTransactMoveType.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel3.add(jlTransactMoveType);

        jtfTransactMoveType.setEditable(false);
        jtfTransactMoveType.setFocusable(false);
        jtfTransactMoveType.setPreferredSize(new java.awt.Dimension(225, 23));
        jPanel3.add(jtfTransactMoveType);

        jPanel1.add(jPanel3);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlMfgMoveType.setText("Mov. producción:");
        jlMfgMoveType.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel4.add(jlMfgMoveType);

        jtfMfgMoveType.setEditable(false);
        jtfMfgMoveType.setFocusable(false);
        jtfMfgMoveType.setPreferredSize(new java.awt.Dimension(225, 23));
        jPanel4.add(jtfMfgMoveType);

        jPanel1.add(jPanel4);

        jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlWarehouse.setText("Almacén:*");
        jlWarehouse.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel12.add(jlWarehouse);

        moKeyWarehouse.setPreferredSize(new java.awt.Dimension(225, 23));
        jPanel12.add(moKeyWarehouse);

        jPanel1.add(jPanel12);

        jPanel13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlStockAdjustType.setText("Tipo ajuste:*");
        jlStockAdjustType.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel13.add(jlStockAdjustType);

        moKeyStockAdjustType.setPreferredSize(new java.awt.Dimension(225, 23));
        jPanel13.add(moKeyStockAdjustType);

        jPanel1.add(jPanel13);

        jpContainer.add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.add(jpContainer);

        jpContainer1.setLayout(new java.awt.BorderLayout());

        jPanel6.setLayout(new java.awt.GridLayout(5, 1, 0, 5));

        jPanel14.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlDepart.setText("Depto. producción:");
        jlDepart.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel14.add(jlDepart);

        jtfDepart.setEditable(false);
        jtfDepart.setFocusable(false);
        jtfDepart.setPreferredSize(new java.awt.Dimension(225, 23));
        jPanel14.add(jtfDepart);

        jPanel6.add(jPanel14);

        jPanel15.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlLine.setText("Línea producción:");
        jlLine.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel15.add(jlLine);

        jtfLine.setEditable(false);
        jtfLine.setFocusable(false);
        jtfLine.setPreferredSize(new java.awt.Dimension(225, 23));
        jPanel15.add(jtfLine);

        jPanel6.add(jPanel15);

        jPanel16.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlJob.setText("Orden producción:");
        jlJob.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel16.add(jlJob);

        jtfJob.setEditable(false);
        jtfJob.setFocusable(false);
        jtfJob.setPreferredSize(new java.awt.Dimension(225, 23));
        jPanel16.add(jtfJob);

        jPanel6.add(jPanel16);

        jPanel17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlBizPartner.setText("Asoc. negocios:*");
        jlBizPartner.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel17.add(jlBizPartner);

        moKeyBizPartner.setPreferredSize(new java.awt.Dimension(225, 23));
        jPanel17.add(moKeyBizPartner);

        jPanel6.add(jPanel17);

        jPanel18.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlItemType.setText("Tipo ítem:*");
        jlItemType.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel18.add(jlItemType);

        moKeyItemType.setPreferredSize(new java.awt.Dimension(225, 23));
        jPanel18.add(moKeyItemType);

        jPanel6.add(jPanel18);

        jpContainer1.add(jPanel6, java.awt.BorderLayout.NORTH);

        jPanel2.add(jpContainer1);

        jpWsd.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel11.setLayout(new java.awt.GridLayout(5, 1, 0, 5));

        jPanel19.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlNumber.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jlNumber.setText("Folio:");
        jlNumber.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel19.add(jlNumber);

        jtfSeries.setEditable(false);
        jtfSeries.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jtfSeries.setText("S");
        jtfSeries.setFocusable(false);
        jtfSeries.setPreferredSize(new java.awt.Dimension(35, 23));
        jPanel19.add(jtfSeries);

        jtfNumber.setEditable(false);
        jtfNumber.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jtfNumber.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfNumber.setText("0");
        jtfNumber.setFocusable(false);
        jtfNumber.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel19.add(jtfNumber);

        jPanel11.add(jPanel19);

        jPanel20.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlDate.setText("Fecha:*");
        jlDate.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel20.add(jlDate);
        jPanel20.add(moDateDate);

        jPanel11.add(jPanel20);

        jPanel21.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlReference.setText("Referencia:");
        jlReference.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel21.add(jlReference);

        moTextReference.setPreferredSize(new java.awt.Dimension(140, 23));
        jPanel21.add(moTextReference);

        jPanel11.add(jPanel21);

        jPanel22.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlAmount.setText("Valor:");
        jlAmount.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel22.add(jlAmount);
        jPanel22.add(moCurAmount);

        jPanel11.add(jPanel22);

        jPanel23.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlMass.setText("Masa:");
        jlMass.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel23.add(jlMass);
        jPanel23.add(moCompMass);

        jPanel11.add(jPanel23);

        jpWsd.add(jPanel11, java.awt.BorderLayout.EAST);

        getContentPane().add(jpWsd, java.awt.BorderLayout.NORTH);

        jpWsdRows.setBorder(javax.swing.BorderFactory.createTitledBorder("Renglones del documento:"));
        jpWsdRows.setLayout(new java.awt.BorderLayout());

        jPanel8.setLayout(new java.awt.GridLayout(2, 1, 0, 5));

        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlRowItem.setText("Ítem:*");
        jlRowItem.setPreferredSize(new java.awt.Dimension(300, 23));
        jPanel9.add(jlRowItem);

        jlRowUnits.setText("Cantidad:*");
        jlRowUnits.setPreferredSize(new java.awt.Dimension(140, 23));
        jPanel9.add(jlRowUnits);

        jlRowLot.setText("Lote:*");
        jlRowLot.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel9.add(jlRowLot);

        jlRowAmountUnit.setText("Valor unitario:*");
        jlRowAmountUnit.setPreferredSize(new java.awt.Dimension(140, 23));
        jPanel9.add(jlRowAmountUnit);

        jlRowAmount.setText("Valor:*");
        jlRowAmount.setPreferredSize(new java.awt.Dimension(140, 23));
        jPanel9.add(jlRowAmount);

        jPanel8.add(jPanel9);

        jPanel24.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        moKeyRowItem.setPreferredSize(new java.awt.Dimension(300, 23));
        jPanel24.add(moKeyRowItem);
        jPanel24.add(moCompRowUnits);
        jPanel24.add(moTextRowLot);
        jPanel24.add(moCurRowAmountUnit);
        jPanel24.add(moCurRowAmount);

        jbRowAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sba/lib/img/cmd_std_add.gif"))); // NOI18N
        jbRowAdd.setToolTipText("Agregar");
        jbRowAdd.setPreferredSize(new java.awt.Dimension(23, 23));
        jPanel24.add(jbRowAdd);

        jbRowClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sba/lib/img/cmd_std_clear.gif"))); // NOI18N
        jbRowClear.setToolTipText("Limpiar");
        jbRowClear.setPreferredSize(new java.awt.Dimension(23, 23));
        jPanel24.add(jbRowClear);

        jPanel8.add(jPanel24);

        jpWsdRows.add(jPanel8, java.awt.BorderLayout.NORTH);

        getContentPane().add(jpWsdRows, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
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
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JButton jbRowAdd;
    private javax.swing.JButton jbRowClear;
    private javax.swing.JLabel jlAmount;
    private javax.swing.JLabel jlBizPartner;
    private javax.swing.JLabel jlDate;
    private javax.swing.JLabel jlDepart;
    private javax.swing.JLabel jlItemType;
    private javax.swing.JLabel jlJob;
    private javax.swing.JLabel jlLine;
    private javax.swing.JLabel jlMass;
    private javax.swing.JLabel jlMfgMoveType;
    private javax.swing.JLabel jlMoveType;
    private javax.swing.JLabel jlNumber;
    private javax.swing.JLabel jlReference;
    private javax.swing.JLabel jlRowAmount;
    private javax.swing.JLabel jlRowAmountUnit;
    private javax.swing.JLabel jlRowItem;
    private javax.swing.JLabel jlRowLot;
    private javax.swing.JLabel jlRowUnits;
    private javax.swing.JLabel jlStockAdjustType;
    private javax.swing.JLabel jlTransactMoveType;
    private javax.swing.JLabel jlWarehouse;
    private javax.swing.JPanel jpContainer;
    private javax.swing.JPanel jpContainer1;
    private javax.swing.JPanel jpWsd;
    private javax.swing.JPanel jpWsdRows;
    private javax.swing.JTextField jtfDepart;
    private javax.swing.JTextField jtfJob;
    private javax.swing.JTextField jtfLine;
    private javax.swing.JTextField jtfMfgMoveType;
    private javax.swing.JTextField jtfMoveType;
    private javax.swing.JTextField jtfNumber;
    private javax.swing.JTextField jtfSeries;
    private javax.swing.JTextField jtfTransactMoveType;
    private sba.lib.gui.bean.DBeanCompoundField moCompMass;
    private sba.lib.gui.bean.DBeanCompoundField moCompRowUnits;
    private sba.lib.gui.bean.DBeanCompoundFieldCurrency moCurAmount;
    private sba.lib.gui.bean.DBeanCompoundFieldCurrency moCurRowAmount;
    private sba.lib.gui.bean.DBeanCompoundFieldCurrency moCurRowAmountUnit;
    private sba.lib.gui.bean.DBeanFieldDate moDateDate;
    private sba.lib.gui.bean.DBeanFieldKey moKeyBizPartner;
    private sba.lib.gui.bean.DBeanFieldKey moKeyItemType;
    private sba.lib.gui.bean.DBeanFieldKey moKeyRowItem;
    private sba.lib.gui.bean.DBeanFieldKey moKeyStockAdjustType;
    private sba.lib.gui.bean.DBeanFieldKey moKeyWarehouse;
    private sba.lib.gui.bean.DBeanFieldText moTextReference;
    private sba.lib.gui.bean.DBeanFieldText moTextRowLot;
    // End of variables declaration//GEN-END:variables

    /*
     * Private methods
     */

    private void initComponentsCustom() {
        DGuiUtils.setWindowBounds(this, 960, 600);
        
        moDateDate.setDateSettings(miClient, DGuiUtils.getLabelName(jlDate), true);
        moTextReference.setTextSettings(DGuiUtils.getLabelName(jlReference), 25, 0);
        moKeyWarehouse.setKeySettings(miClient, DGuiUtils.getLabelName(jlWarehouse), true);
        moKeyStockAdjustType.setKeySettings(miClient, DGuiUtils.getLabelName(jlStockAdjustType), true);
        moKeyBizPartner.setKeySettings(miClient, DGuiUtils.getLabelName(jlBizPartner), true);
        moKeyItemType.setKeySettings(miClient, DGuiUtils.getLabelName(jlItemType), true);
        moCurAmount.setCompoundFieldSettings(miClient);
        moCurAmount.getField().setDecimalSettings(DGuiUtils.getLabelName(jlAmount), DGuiConsts.GUI_TYPE_DEC_AMT, true);
        moCompMass.setCompoundFieldSettings(miClient);
        moCompMass.getField().setDecimalSettings(DGuiUtils.getLabelName(jlMass), DGuiConsts.GUI_TYPE_DEC_QTY, true);
        moKeyRowItem.setKeySettings(miClient, DGuiUtils.getLabelName(jlRowItem), true);
        moCompRowUnits.setCompoundFieldSettings(miClient);
        moCompRowUnits.getField().setDecimalSettings(DGuiUtils.getLabelName(jlRowUnits), DGuiConsts.GUI_TYPE_DEC_QTY, true);
        moTextRowLot.setTextSettings(DGuiUtils.getLabelName(jlRowLot), 25);
        moCurRowAmountUnit.setCompoundFieldSettings(miClient);
        moCurRowAmountUnit.getField().setDecimalSettings(DGuiUtils.getLabelName(jlRowAmountUnit), DGuiConsts.GUI_TYPE_DEC_AMT_UNIT, true);
        moCurRowAmount.setCompoundFieldSettings(miClient);
        moCurRowAmount.getField().setDecimalSettings(DGuiUtils.getLabelName(jlRowAmount), DGuiConsts.GUI_TYPE_DEC_AMT, true);
        
        moFields.addField(moDateDate);
        moFields.addField(moTextReference);
        moFields.addField(moKeyWarehouse);
        moFields.addField(moKeyStockAdjustType);
        moFields.addField(moKeyBizPartner);
        moFields.addField(moKeyItemType);
        //moFields.addField(moCurAmount.getField()); // always disabled
        //moFields.addField(moCompMass.getField()); // always disabled
        //moFields.setFormButton(jbSave); // not required
        
        moKeyItemType.setNextField(moKeyRowItem);
        
        moFieldsRow = new DGuiFields();
        moFieldsRow.addField(moKeyRowItem);
        moFieldsRow.addField(moCompRowUnits.getField());
        moFieldsRow.addField(moTextRowLot);
        moFieldsRow.addField(moCurRowAmountUnit.getField());
        moFieldsRow.addField(moCurRowAmount.getField());
        moFieldsRow.setFormButton(jbRowAdd);
        
        moCompMass.setCompoundText(DCfgUtils.getMassUnitCode(miClient.getSession()));
        moCompRowUnits.setCompoundText("");
        
        moKeyGroupItem = new DGuiFieldKeyGroup(miClient);
        
        maFieldsWsd = new ArrayList<>();
        maFieldsWsd.add(moKeyWarehouse);
        maFieldsWsd.add(moKeyBizPartner);
        maFieldsWsd.add(moKeyItemType);
        
        moPaneFormRows = new DGridPaneForm(miClient, DModConsts.S_WSD_ROW, DLibConsts.UNDEFINED, DGuiUtils.getLabelName(((TitledBorder) jpWsdRows.getBorder()).getTitle())) {
            
            @Override
            public void initGrid() {
                setRowButtonsEnabled(false, false, true);
            }
            
            @Override
            public void createGridColumns() {
                int col = 0;
                DGridColumnForm[] columns = new DGridColumnForm[9];

                columns[col++] = new DGridColumnForm(DGridConsts.COL_TYPE_TEXT_NAME_ITM_L, DGridConsts.COL_TITLE_NAME);
                columns[col++] = new DGridColumnForm(DGridConsts.COL_TYPE_TEXT_CODE_ITM, DGridConsts.COL_TITLE_CODE);
                columns[col++] = new DGridColumnForm(DGridConsts.COL_TYPE_DEC_QTY, "Cantidad");
                columns[col++] = new DGridColumnForm(DGridConsts.COL_TYPE_TEXT_CODE_UNT, "Unidad");
                columns[col++] = new DGridColumnForm(DGridConsts.COL_TYPE_TEXT_NAME_CAT_S, "Lote");
                columns[col++] = new DGridColumnForm(DGridConsts.COL_TYPE_DEC_AMT_UNIT, "Valor unitario $");
                columns[col++] = new DGridColumnForm(DGridConsts.COL_TYPE_DEC_AMT, "Valor $");
                columns[col++] = new DGridColumnForm(DGridConsts.COL_TYPE_DEC_AMT_UNIT, "Masa unitaria");
                columns[col++] = new DGridColumnForm(DGridConsts.COL_TYPE_DEC_QTY, "Masa");

                for (col = 0; col < columns.length; col++) {
                    moModel.getGridColumns().add(columns[col]);
                }
            }
        };
        
        moPaneFormRows.setPaneFormOwner(this);
        
        jpWsdRows.add(moPaneFormRows, BorderLayout.CENTER);
        
        mvFormGrids.add(moPaneFormRows);
    }
    
    private void displayFieldsRegistry() throws Exception {
        if (mnFormSubtype != moRegistry.getFkMoveClassId()) {
            throw new Exception(DStkConsts.ERR_MSG_WSD_CLASS);
        }
        else {
            jtfMoveType.setText((String) miClient.getSession().readField(DModConsts.SS_MOV_TP, moRegistry.getKeyMoveType(), DDbRegistry.FIELD_NAME));
            jtfSeries.setText((String) miClient.getSession().readField(DModConsts.SS_MOV_TP, moRegistry.getKeyMoveType(), DDbRegistry.FIELD_CODE));
            jtfTransactMoveType.setText((String) miClient.getSession().readField(DModConsts.SS_TRN_TP, new int[] { moRegistry.getFkTransactMoveTypeId() }, DDbRegistry.FIELD_NAME));
            jtfMfgMoveType.setText((String) miClient.getSession().readField(DModConsts.SS_MFG_TP, new int[] { moRegistry.getFkMfgMoveTypeId() }, DDbRegistry.FIELD_NAME));
            jtfDepart.setText(moRegistry.getFkDepartId_n() == DLibConsts.UNDEFINED ? "" : (String) miClient.getSession().readField(DModConsts.MU_DPT, new int[] { moRegistry.getFkDepartId_n() }, DDbRegistry.FIELD_NAME));
            jtfLine.setText(moRegistry.getFkLineId_n()== DLibConsts.UNDEFINED ? "" : (String) miClient.getSession().readField(DModConsts.MU_LIN, new int[] { moRegistry.getFkLineId_n() }, DDbRegistry.FIELD_NAME));
            jtfLine.setText(moRegistry.getFkJobId_n()== DLibConsts.UNDEFINED ? "" : (String) miClient.getSession().readField(DModConsts.M_JOB, new int[] { moRegistry.getFkJobId_n() }, DDbRegistry.FIELD_NAME));
        }
    }
    
    private void updateFieldsRegistry() {
        if (moRegistry != null) {
            mbIsStockAdjustTypeReq = moRegistry.isUtilStockAdjustTypeReq();
            mbIsBizPartnerReq = moRegistry.isUtilBizPartnerReq();
        }
        
        moKeyStockAdjustType.setEnabled(mbIsStockAdjustTypeReq);
        moKeyBizPartner.setEnabled(mbIsBizPartnerReq);

        if (!mbIsStockAdjustTypeReq) {
            moKeyStockAdjustType.setValue(new int[] { DModSysConsts.SS_ADJ_TP_NA });
        }
        
        if (!mbIsBizPartnerReq) {
            moKeyBizPartner.resetField();
        }
    }
    
    private void setFieldsWsdEditable(final boolean editable) {
        moDateDate.setEditable(editable);
        moKeyWarehouse.setEnabled(editable);
        moKeyBizPartner.setEnabled(editable && mbIsBizPartnerReq);
    }
    
    private void compute() {
        double amount = 0d;
        double mass = 0d;
        
        for (DGridRow row : moPaneFormRows.getModel().getGridRows()) {
            amount += ((DDbWsdRow) row).getAmount_r();
            mass += ((DDbWsdRow) row).getMass_r();
        }
        
        moCurAmount.getField().setValue(amount);
        moCompMass.getField().setValue(mass);
    }
    
    private void doRowAdd() {
        DGuiValidation validation = null;
        
        for (DGuiField field : maFieldsWsd) {
            if (field.isEnabled()) {
                validation = field.validateField();
                if (!validation.isValid()) {
                    break;
                }
            }
        }
        
        if (validation.isValid()) {
            validation = moFieldsRow.validateFields();
            
            if (validation.isValid()) {
                DDbWsdRow row = new DDbWsdRow();
                int index = moKeyRowItem.getSelectedIndex();

                //row.setPkWsdId(...);
                //row.setPkRowId(...);
                row.setUnits(moCompRowUnits.getField().getValue());
                row.setAmountUnit(moCurRowAmountUnit.getField().getValue());
                //row.setAmount_r(...);
                row.setLot(moTextRowLot.getValue());
                //row.setMassUnit(...);
                //row.setMass_r(...);
                //row.setDeleted(...);
                //row.setSystem(...);
                row.setFkItemId(moKeyRowItem.getValue()[0]);
                //row.setFkItemTypeId(...);
                //row.setFkUnitId(...);
                //row.setFkWsdWsdId_n(...);
                //row.setFkWsdRowId_n(...);

                row.compute(miClient.getSession());

                moPaneFormRows.addGridRow(row);
                moPaneFormRows.renderGridRows();
                moPaneFormRows.setSelectedGridRow(moPaneFormRows.getTable().getRowCount() - 1);

                compute();
                setFieldsWsdEditable(false);
                actionPerformedRowClear();
                moKeyRowItem.setSelectedIndex(index);
            }
        }
        
        if (!validation.isValid()) {
            DGuiUtils.computeValidation(miClient, validation);
        }
    }
    
    private void doRowDelete() {
        compute();
        setFieldsWsdEditable(moPaneFormRows.getTable().getRowCount() == 0);
    }
    
    private void doRowClear() {
        moFieldsRow.resetFields();
    }
    
    private void actionPerformedRowAdd() {
        doRowAdd();
    }
    
    private void actionPerformedRowClear() {
        doRowClear();
        moKeyRowItem.requestFocus();
    }
    
    private void itemStateChangedRowItem() {
        if (moKeyRowItem.getSelectedIndex() <= 0) {
            moCompRowUnits.setCompoundText("");
        }
        else {
            moCompRowUnits.setCompoundText((String) moKeyRowItem.getSelectedItem().getComplement());
        }
    }
    
    private void focusLostCurRowAmountUnit() {
        if (moCurRowAmountUnit.getField().getValue() != 0d && moCompRowUnits.getField().getValue() != 0) {
            moCurRowAmount.getField().setValue(DLibUtils.round(moCurRowAmountUnit.getField().getValue() * moCompRowUnits.getField().getValue(), DLibUtils.getDecimalFormatAmount().getMaximumFractionDigits()));
        }
    }
    
    private void focusLostCurRowAmount() {
        if (moCurRowAmountUnit.getField().getValue() == 0d && moCurRowAmount.getField().getValue() != 0d && moCompRowUnits.getField().getValue() != 0) {
            moCurRowAmountUnit.getField().setValue(DLibUtils.round(moCurRowAmount.getField().getValue() / moCompRowUnits.getField().getValue(), DLibUtils.getDecimalFormatAmountUnitary().getMaximumFractionDigits()));
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
        jbRowAdd.addActionListener(this);
        jbRowClear.addActionListener(this);
        moKeyRowItem.addItemListener(this);
        moCurRowAmountUnit.getField().getComponent().addFocusListener(this);
        moCurRowAmount.getField().getComponent().addFocusListener(this);
    }

    @Override
    public void removeAllListeners() {
        jbRowAdd.removeActionListener(this);
        jbRowClear.removeActionListener(this);
        moKeyRowItem.removeItemListener(this);
        moCurRowAmountUnit.getField().getComponent().removeFocusListener(this);
        moCurRowAmount.getField().getComponent().removeFocusListener(this);
    }

    @Override
    public void reloadCatalogues() {
        miClient.getSession().populateCatalogue(moKeyWarehouse, DModConsts.SU_WHS, DLibConsts.UNDEFINED, null);
        miClient.getSession().populateCatalogue(moKeyStockAdjustType, DModConsts.SS_ADJ_TP, DLibConsts.UNDEFINED, null);
        miClient.getSession().populateCatalogue(moKeyBizPartner, DModConsts.CU_BPR, moRegistry.getUtilBizPartnerType(), null);
        
        moKeyGroupItem.initGroup();
        moKeyGroupItem.addFieldKey(moKeyItemType, DModConsts.CS_ITM_TP, DLibConsts.UNDEFINED, null);
        moKeyGroupItem.addFieldKey(moKeyRowItem, DModConsts.CX_ITM_FK_ITM_TP, DLibConsts.UNDEFINED, null);
        moKeyGroupItem.populateCatalogues();
    }

    @Override
    public void setRegistry(DDbRegistry registry) throws Exception {
        boolean isCopy = false;
        
        moRegistry = (DDbWsd) registry;

        mnFormResult = DLibConsts.UNDEFINED;
        mbFirstActivation = true;

        removeAllListeners();
        reloadCatalogues();
        
        displayFieldsRegistry();

        if (moRegistry.isRegistryNew()) {
            moRegistry.initPrimaryKey();
            
            moRegistry.setNumber(0);
            
            if (!isCopy) {
                moRegistry.setDate(miClient.getSession().getWorkingDate());
            }
            
            jtfRegistryKey.setText("");
        }
        else {
            jtfRegistryKey.setText(DLibUtils.textKey(moRegistry.getPrimaryKey()));
        }

        jtfSeries.setText((String) miClient.getSession().readField(DModConsts.SS_MOV_TP, moRegistry.getKeyMoveType(), DDbRegistry.FIELD_CODE));
        jtfNumber.setText("" + moRegistry.getNumber());
        moDateDate.setValue(moRegistry.getDate());
        moTextReference.setValue(moRegistry.getReference());
        moKeyWarehouse.setValue(new int[] { moRegistry.getFkWarehouseId() });
        moKeyStockAdjustType.setValue(new int[] { moRegistry.getFkStockAdjustTypeId() });
        moKeyBizPartner.setValue(new int[] { moRegistry.getFkBizPartnerId_n() });
        moKeyItemType.setValue(new int[] { moRegistry.getFkItemTypeId() });
        moCurAmount.getField().setValue(moRegistry.getAmount_r());
        moCompMass.getField().setValue(moRegistry.getMass_r());
        
        moPaneFormRows.populateGrid(new Vector<>(moRegistry.getRows()));
        
        doRowClear();
        
        setFormEditable(true);

        updateFieldsRegistry();
        moCurAmount.setEditable(false);
        moCompMass.setEditable(false);
        
        if (moRegistry.isRegistryNew()) {
            moKeyGroupItem.resetGroup();
        }
        else {
            setFieldsWsdEditable(false);
        }
        
        addAllListeners();
    }

    @Override
    public DDbWsd getRegistry() throws Exception {
        DDbWsd registry = moRegistry.clone();

        if (registry.isRegistryNew()) {
            //registry.setPkWsdId(...);
        }

        //registry.setNumber(...);
        registry.setDate(moDateDate.getValue());
        registry.setReference(moTextReference.getValue());
        //registry.setAmount_r(...);
        //registry.setMass_r(...);
        //registry.setDeleted(...);
        //registry.setSystem(...);
        //registry.setFkMoveClassId(...); // already set
        //registry.setFkMoveTypeId(...); // already set
        //registry.setFkTransactMoveTypeId(...); // already set
        //registry.setFkMfgMoveTypeId(...); // already set
        registry.setFkStockAdjustTypeId(moKeyStockAdjustType.getValue()[0]);
        registry.setFkItemTypeId(moKeyItemType.getValue()[0]);
        registry.setFkWarehouseId(moKeyWarehouse.getValue()[0]);
        //registry.setFkWsdId_n(...); // not implemented yet
        registry.setFkBizPartnerId_n(moKeyBizPartner.getSelectedIndex() <= 0 ? DLibConsts.UNDEFINED : moKeyBizPartner.getValue()[0]);
        //registry.setFkDepartId_n(...); // already set
        //registry.setFkLineId_n(...); // already set
        //registry.setFkJobId_n(...); // already set
        
        registry.getRows().clear();
        for (DGridRow row : moPaneFormRows.getModel().getGridRows()) {
            registry.getRows().add((DDbWsdRow) row);
        }

        return registry;
    }

    @Override
    public DGuiValidation validateForm() {
        return moFields.validateFields();
    }

    @Override
    public void notifyRowNew(int gridType, int gridSubtype, int row, DGridRow gridRow) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyRowEdit(int gridType, int gridSubtype, int row, DGridRow gridRow) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyRowDelete(int gridType, int gridSubtype, int row, DGridRow gridRow) {
        doRowDelete();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            
            if (button == jbRowAdd) {
                actionPerformedRowAdd();
            }
            else if (button == jbRowClear) {
                actionPerformedRowClear();
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() instanceof DBeanFieldKey) {
            DBeanFieldKey field = (DBeanFieldKey) e.getSource();
            
            if (field == moKeyRowItem) {
                itemStateChangedRowItem();
            }
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() instanceof DBeanFieldDecimal) {
            DBeanFieldDecimal field = (DBeanFieldDecimal) e.getSource();
            
            if (field == moCurRowAmountUnit.getField()) {
                focusLostCurRowAmountUnit();
            }
            else if (field == moCurRowAmount.getField()) {
                focusLostCurRowAmount();
            }
        }
    }
}
