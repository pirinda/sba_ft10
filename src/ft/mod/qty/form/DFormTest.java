/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod.qty.form;

import ft.gui.DRowOption;
import ft.mod.DModConsts;
import ft.mod.qty.db.DDbTest;
import ft.mod.qty.db.DDbTestFamily;
import ft.mod.qty.db.DDbTestVariable;
import java.awt.BorderLayout;
import java.util.Vector;
import javax.swing.border.TitledBorder;
import sba.lib.DLibConsts;
import sba.lib.DLibUtils;
import sba.lib.db.DDbRegistry;
import sba.lib.grid.DGridColumnForm;
import sba.lib.grid.DGridConsts;
import sba.lib.grid.DGridPaneForm;
import sba.lib.grid.DGridRow;
import sba.lib.gui.DGuiClient;
import sba.lib.gui.DGuiConsts;
import sba.lib.gui.DGuiItem;
import sba.lib.gui.DGuiUtils;
import sba.lib.gui.DGuiValidation;
import sba.lib.gui.bean.DBeanForm;

/**
 *
 * @author Sergio Flores
 */
public class DFormTest extends DBeanForm {

    private DDbTest moRegistry;
    private DGridPaneForm moGridVariables;
    private DGridPaneForm moGridFamilies;

    /** Creates new form DFormVariable */
    public DFormTest(DGuiClient client, String title) {
        setFormSettings(client, DGuiConsts.BEAN_FORM_EDIT, DModConsts.QU_TST, DLibConsts.UNDEFINED, title);
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
        jpTest = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jlCode = new javax.swing.JLabel();
        moTextCode = new sba.lib.gui.bean.DBeanFieldText();
        jPanel4 = new javax.swing.JPanel();
        jlName = new javax.swing.JLabel();
        moTextName = new sba.lib.gui.bean.DBeanFieldText();
        jPanel8 = new javax.swing.JPanel();
        jlType = new javax.swing.JLabel();
        moKeyType = new sba.lib.gui.bean.DBeanFieldKey();
        jpComponents = new javax.swing.JPanel();
        jpVariables = new javax.swing.JPanel();
        jpFamilies = new javax.swing.JPanel();

        jpContainer.setLayout(new java.awt.BorderLayout());

        jpTest.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del registro:"));
        jpTest.setLayout(new java.awt.GridLayout(3, 1, 0, 5));

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlCode.setText("Código:*");
        jlCode.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel3.add(jlCode);

        moTextCode.setPreferredSize(new java.awt.Dimension(50, 23));
        jPanel3.add(moTextCode);

        jpTest.add(jPanel3);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlName.setText("Nombre:*");
        jlName.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel4.add(jlName);

        moTextName.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel4.add(moTextName);

        jpTest.add(jPanel4);

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlType.setText("Tipo:");
        jlType.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel8.add(jlType);

        moKeyType.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel8.add(moKeyType);

        jpTest.add(jPanel8);

        jpContainer.add(jpTest, java.awt.BorderLayout.NORTH);

        jpComponents.setLayout(new java.awt.GridLayout(1, 2, 5, 0));

        jpVariables.setBorder(javax.swing.BorderFactory.createTitledBorder("Variables:"));
        jpVariables.setLayout(new java.awt.BorderLayout());
        jpComponents.add(jpVariables);

        jpFamilies.setBorder(javax.swing.BorderFactory.createTitledBorder("Familias:"));
        jpFamilies.setLayout(new java.awt.BorderLayout());
        jpComponents.add(jpFamilies);

        jpContainer.add(jpComponents, java.awt.BorderLayout.CENTER);

        getContentPane().add(jpContainer, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel jlCode;
    private javax.swing.JLabel jlName;
    private javax.swing.JLabel jlType;
    private javax.swing.JPanel jpComponents;
    private javax.swing.JPanel jpContainer;
    private javax.swing.JPanel jpFamilies;
    private javax.swing.JPanel jpTest;
    private javax.swing.JPanel jpVariables;
    private sba.lib.gui.bean.DBeanFieldKey moKeyType;
    private sba.lib.gui.bean.DBeanFieldText moTextCode;
    private sba.lib.gui.bean.DBeanFieldText moTextName;
    // End of variables declaration//GEN-END:variables

    /*
     * Private methods
     */

    private void initComponentsCustom() {
        DGuiUtils.setWindowBounds(this, 640, 400);
        
        moTextCode.setTextSettings(DGuiUtils.getLabelName(jlCode), 5);
        moTextName.setTextSettings(DGuiUtils.getLabelName(jlName), 50);
        moKeyType.setKeySettings(miClient, DGuiUtils.getLabelName(jlType), true);
        
        moFields.addField(moTextCode);
        moFields.addField(moTextName);
        moFields.addField(moKeyType);
        
        moFields.setFormButton(jbSave);
        
        moGridVariables = new DGridPaneForm(miClient, mnFormType, DModConsts.QU_VAR, DGuiUtils.getLabelName(((TitledBorder) jpFamilies.getBorder()).getTitle())) {
            
            @Override
            public void initGrid() {
                setRowButtonsEnabled(false);
            }
            
            @Override
            public void createGridColumns() {
                int col = 0;
                DGridColumnForm[] columns = new DGridColumnForm[3];

                columns[col++] = new DGridColumnForm(DGridConsts.COL_TYPE_TEXT_NAME_CAT_S, DGridConsts.COL_TITLE_NAME);
                columns[col++] = new DGridColumnForm(DGridConsts.COL_TYPE_TEXT_CODE_CAT, DGridConsts.COL_TITLE_CODE);
                columns[col] = new DGridColumnForm(DGridConsts.COL_TYPE_BOOL_S, "Aplica");
                columns[col++].setEditable(true);

                for (col = 0; col < columns.length; col++) {
                    moModel.getGridColumns().add(columns[col]);
                }
            }
        };
        
        moGridFamilies = new DGridPaneForm(miClient, mnFormType, DModConsts.CU_FAM, DGuiUtils.getLabelName(((TitledBorder) jpFamilies.getBorder()).getTitle())) {
            
            @Override
            public void initGrid() {
                setRowButtonsEnabled(false);
            }
            
            @Override
            public void createGridColumns() {
                int col = 0;
                DGridColumnForm[] columns = new DGridColumnForm[4];

                columns[col++] = new DGridColumnForm(DGridConsts.COL_TYPE_TEXT_NAME_CAT_S, DGridConsts.COL_TITLE_NAME);
                columns[col++] = new DGridColumnForm(DGridConsts.COL_TYPE_TEXT_CODE_CAT, DGridConsts.COL_TITLE_CODE);
                columns[col] = new DGridColumnForm(DGridConsts.COL_TYPE_BOOL_S, "Aplica");
                columns[col++].setEditable(true);
                columns[col] = new DGridColumnForm(DGridConsts.COL_TYPE_INT_4B, "Resultados");
                columns[col++].setEditable(true);

                for (col = 0; col < columns.length; col++) {
                    moModel.getGridColumns().add(columns[col]);
                }
            }
        };
        
        jpVariables.add(moGridVariables, BorderLayout.CENTER);
        jpFamilies.add(moGridFamilies, BorderLayout.CENTER);
        
        mvFormGrids.add(moGridVariables);
        mvFormGrids.add(moGridFamilies);
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
        miClient.getSession().populateCatalogue(moKeyType, DModConsts.QS_TST_TP, DLibConsts.UNDEFINED, null);
    }

    @Override
    public void setRegistry(DDbRegistry registry) throws Exception {
        Vector<DGuiItem> items = null;
        Vector<DGridRow> rows = new Vector<>();
        
        moRegistry = (DDbTest) registry;

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
        moTextName.setValue(moRegistry.getName());
        moKeyType.setValue(new int[] { moRegistry.getFkTestTypeId() });
        
        items = miClient.getSession().readItems(DModConsts.QU_VAR, DLibConsts.UNDEFINED, null);
        items.remove(0);
        rows.clear(); // just for consistence
        
        for (DGuiItem item : items) {
            rows.add(new DRowOption(item.getPrimaryKey()[0], item.getCode(), item.getItem(), moRegistry.isUtilVariableSelected(item.getPrimaryKey()[0])));
        }
        
        moGridVariables.populateGrid(rows);
        
        items = miClient.getSession().readItems(DModConsts.CU_FAM, DLibConsts.UNDEFINED, null);
        items.remove(0);
        rows.clear();
        
        for (DGuiItem item : items) {
            rows.add(new DRowOption(item.getPrimaryKey()[0], item.getCode(), item.getItem(), moRegistry.isUtilFamilySelected(item.getPrimaryKey()[0]), moRegistry.getUtilFamilyResults(item.getPrimaryKey()[0])));
        }
        
        moGridFamilies.populateGrid(rows);
        
        setFormEditable(true);
        
        moTextCode.setEditable(false);

        if (moRegistry.isRegistryNew()) {

        }
        else {
            
        }
        
        addAllListeners();
    }

    @Override
    public DDbTest getRegistry() throws Exception {
        DDbTest registry = moRegistry.clone();

        if (registry.isRegistryNew()) {
            //registry.setPkVariableId(...);
        }

        registry.setCode(moTextCode.getValue());
        registry.setName(moTextName.getValue());
        //registry.setDeleted(...);
        //registry.setSystem(...);
        registry.setFkTestTypeId(moKeyType.getValue()[0]);
        
        registry.getChildVariables().clear();
        for (DGridRow row : moGridVariables.getModel().getGridRows()) {
            if (((DRowOption) row).Selected) {
                DDbTestVariable testVariable = new DDbTestVariable();
                //variableFamily.setPkTestId(...);
                testVariable.setPkVariableId(((DRowOption) row).OptionId);
                registry.getChildVariables().add(testVariable);
            }
        }
        
        registry.getChildFamilies().clear();
        for (DGridRow row : moGridFamilies.getModel().getGridRows()) {
            if (((DRowOption) row).Selected) {
                DDbTestFamily testFamily = new DDbTestFamily();
                //variableFamily.setPkTestId(...);
                testFamily.setPkFamilyId(((DRowOption) row).OptionId);
                testFamily.setResults(((DRowOption) row).Quantity);
                registry.getChildFamilies().add(testFamily);
            }
        }
        
        return registry;
    }

    @Override
    public DGuiValidation validateForm() {
        return moFields.validateFields();
    }
}