/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ft.mod.qty.form;

import ft.mod.DModConsts;
import ft.mod.qty.db.DQtyUtils;
import ft.stats.DSamplesGroup;
import ft.stats.DSamplesGroupsArray;
import ft.stats.DStatsUtils;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javax.swing.JButton;
import sba.lib.DLibConsts;
import sba.lib.DLibTimeUtils;
import sba.lib.DLibUtils;
import sba.lib.db.DDbConsts;
import sba.lib.db.DDbRegistry;
import sba.lib.gui.DGuiClient;
import sba.lib.gui.DGuiConsts;
import sba.lib.gui.DGuiFieldKeyGroup;
import sba.lib.gui.DGuiItem;
import sba.lib.gui.DGuiUtils;
import sba.lib.gui.DGuiValidation;
import sba.lib.gui.bean.DBeanFieldKey;
import sba.lib.gui.bean.DBeanFormDialog;
import stats.DControlCharts;

/**
 *
 * @author 
 */
public class DDialogControlCharts extends DBeanFormDialog implements ActionListener, ItemListener {

    private DGuiFieldKeyGroup moKeyGroupJobFormula;
    private DControlCharts moControlCharts;
    private final JFXPanel mjfxpChartX = new JFXPanel();
    private final JFXPanel mjfxpChartR = new JFXPanel();
    
    /**
     * Creates new form DDialogControlCharts
     */
    public DDialogControlCharts(DGuiClient client, String title) {
        setFormSettings(client, DGuiConsts.BEAN_FORM_EDIT, DLibConsts.UNDEFINED, DLibConsts.UNDEFINED, title);
        initComponents();
        initComponentsCustom();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jlPeriod = new javax.swing.JLabel();
        moDateDateStart = new sba.lib.gui.bean.DBeanFieldDate();
        moDateDateEnd = new sba.lib.gui.bean.DBeanFieldDate();
        jPanel6 = new javax.swing.JPanel();
        jlItemType = new javax.swing.JLabel();
        moKeyItemType = new sba.lib.gui.bean.DBeanFieldKey();
        jPanel11 = new javax.swing.JPanel();
        jlFamily = new javax.swing.JLabel();
        moKeyFamily = new sba.lib.gui.bean.DBeanFieldKey();
        jPanel22 = new javax.swing.JPanel();
        jlItem = new javax.swing.JLabel();
        moKeyItem = new sba.lib.gui.bean.DBeanFieldKey();
        jPanel15 = new javax.swing.JPanel();
        jbShowStats = new javax.swing.JButton();
        jbWipeStats = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jlJobsDummy = new javax.swing.JLabel();
        jlJobsTotal = new javax.swing.JLabel();
        jlJobsFinished = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jlJobs = new javax.swing.JLabel();
        jtfJobsTotal = new javax.swing.JTextField();
        jtfJobsFinished = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jlMfg = new javax.swing.JLabel();
        moCompMfgUnits = new sba.lib.gui.bean.DBeanCompoundField();
        moCompMfgMass = new sba.lib.gui.bean.DBeanCompoundField();
        jPanel9 = new javax.swing.JPanel();
        jlVariable = new javax.swing.JLabel();
        moKeyVariable = new sba.lib.gui.bean.DBeanFieldKey();
        jPanel12 = new javax.swing.JPanel();
        jlSamples = new javax.swing.JLabel();
        jtfSamples = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jlSamples2 = new javax.swing.JLabel();
        jtfGroups = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jlSamples1 = new javax.swing.JLabel();
        jtfGroupSamples = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jlChartLables = new javax.swing.JLabel();
        jlChartX = new javax.swing.JLabel();
        jlChartR = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jlLC = new javax.swing.JLabel();
        jtfLCChartX = new javax.swing.JTextField();
        jtfLCChartR = new javax.swing.JTextField();
        jPanel21 = new javax.swing.JPanel();
        jlLCU = new javax.swing.JLabel();
        jtfLCUChartX = new javax.swing.JTextField();
        jtfLCUChartR = new javax.swing.JTextField();
        jPanel23 = new javax.swing.JPanel();
        jlLCL = new javax.swing.JLabel();
        jtfLCLChartX = new javax.swing.JTextField();
        jtfLCLChartR = new javax.swing.JTextField();
        jPanel24 = new javax.swing.JPanel();
        jlProcessCapacity = new javax.swing.JLabel();
        jtfProcessCapacity = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jpChartX = new javax.swing.JPanel();
        jpChartR = new javax.swing.JPanel();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Variables:"));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.GridLayout(18, 1, 0, 5));

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlPeriod.setText("Período:");
        jlPeriod.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel5.add(jlPeriod);
        jPanel5.add(moDateDateStart);
        jPanel5.add(moDateDateEnd);

        jPanel2.add(jPanel5);

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlItemType.setText("Tipo producto:*");
        jlItemType.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel6.add(jlItemType);

        moKeyItemType.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel6.add(moKeyItemType);

        jPanel2.add(jPanel6);

        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlFamily.setText("Familia:*");
        jlFamily.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel11.add(jlFamily);

        moKeyFamily.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel11.add(moKeyFamily);

        jPanel2.add(jPanel11);

        jPanel22.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlItem.setText("Producto:*");
        jlItem.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel22.add(jlItem);

        moKeyItem.setPreferredSize(new java.awt.Dimension(300, 23));
        jPanel22.add(moKeyItem);

        jPanel2.add(jPanel22);

        jPanel15.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jbShowStats.setText("Mostrar");
        jbShowStats.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel15.add(jbShowStats);

        jbWipeStats.setText("Limpiar");
        jbWipeStats.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel15.add(jbWipeStats);

        jPanel2.add(jPanel15);

        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlJobsDummy.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel10.add(jlJobsDummy);

        jlJobsTotal.setText("Totales:");
        jlJobsTotal.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel10.add(jlJobsTotal);

        jlJobsFinished.setText("Terminadas:");
        jlJobsFinished.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel10.add(jlJobsFinished);

        jPanel2.add(jPanel10);

        jPanel18.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlJobs.setText("Órdenes prod.:");
        jlJobs.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel18.add(jlJobs);

        jtfJobsTotal.setEditable(false);
        jtfJobsTotal.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfJobsTotal.setText("0");
        jtfJobsTotal.setFocusable(false);
        jtfJobsTotal.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel18.add(jtfJobsTotal);

        jtfJobsFinished.setEditable(false);
        jtfJobsFinished.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfJobsFinished.setText("0");
        jtfJobsFinished.setFocusable(false);
        jtfJobsFinished.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel18.add(jtfJobsFinished);

        jPanel2.add(jPanel18);

        jPanel14.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlMfg.setText("Producción:");
        jlMfg.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel14.add(jlMfg);

        moCompMfgUnits.setEditable(false);
        jPanel14.add(moCompMfgUnits);

        moCompMfgMass.setEditable(false);
        jPanel14.add(moCompMfgMass);

        jPanel2.add(jPanel14);

        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlVariable.setText("Variable calidad:*");
        jlVariable.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel9.add(jlVariable);

        moKeyVariable.setPreferredSize(new java.awt.Dimension(300, 23));
        jPanel9.add(moKeyVariable);

        jPanel2.add(jPanel9);

        jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlSamples.setText("Muestras:");
        jlSamples.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel12.add(jlSamples);

        jtfSamples.setEditable(false);
        jtfSamples.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfSamples.setText("0");
        jtfSamples.setFocusable(false);
        jtfSamples.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel12.add(jtfSamples);

        jPanel2.add(jPanel12);

        jPanel19.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlSamples2.setText("Grupos muestras:");
        jlSamples2.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel19.add(jlSamples2);

        jtfGroups.setEditable(false);
        jtfGroups.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfGroups.setText("0");
        jtfGroups.setFocusable(false);
        jtfGroups.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel19.add(jtfGroups);

        jPanel2.add(jPanel19);

        jPanel13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlSamples1.setText("Muestras grupo:");
        jlSamples1.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel13.add(jlSamples1);

        jtfGroupSamples.setEditable(false);
        jtfGroupSamples.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfGroupSamples.setText("0");
        jtfGroupSamples.setFocusable(false);
        jtfGroupSamples.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel13.add(jtfGroupSamples);

        jPanel2.add(jPanel13);

        jPanel17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlChartLables.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel17.add(jlChartLables);

        jlChartX.setText("Carta X:");
        jlChartX.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel17.add(jlChartX);

        jlChartR.setText("Carta R:");
        jlChartR.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel17.add(jlChartR);

        jPanel2.add(jPanel17);

        jPanel20.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlLC.setText("LC:");
        jlLC.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel20.add(jlLC);

        jtfLCChartX.setEditable(false);
        jtfLCChartX.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfLCChartX.setText("0");
        jtfLCChartX.setFocusable(false);
        jtfLCChartX.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel20.add(jtfLCChartX);

        jtfLCChartR.setEditable(false);
        jtfLCChartR.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfLCChartR.setText("0");
        jtfLCChartR.setFocusable(false);
        jtfLCChartR.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel20.add(jtfLCChartR);

        jPanel2.add(jPanel20);

        jPanel21.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlLCU.setText("LC superior:");
        jlLCU.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel21.add(jlLCU);

        jtfLCUChartX.setEditable(false);
        jtfLCUChartX.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfLCUChartX.setText("0");
        jtfLCUChartX.setFocusable(false);
        jtfLCUChartX.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel21.add(jtfLCUChartX);

        jtfLCUChartR.setEditable(false);
        jtfLCUChartR.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfLCUChartR.setText("0");
        jtfLCUChartR.setFocusable(false);
        jtfLCUChartR.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel21.add(jtfLCUChartR);

        jPanel2.add(jPanel21);

        jPanel23.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlLCL.setText("LC inferior:");
        jlLCL.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel23.add(jlLCL);

        jtfLCLChartX.setEditable(false);
        jtfLCLChartX.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfLCLChartX.setText("0");
        jtfLCLChartX.setFocusable(false);
        jtfLCLChartX.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel23.add(jtfLCLChartX);

        jtfLCLChartR.setEditable(false);
        jtfLCLChartR.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfLCLChartR.setText("0");
        jtfLCLChartR.setFocusable(false);
        jtfLCLChartR.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel23.add(jtfLCLChartR);

        jPanel2.add(jPanel23);

        jPanel24.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlProcessCapacity.setText("Capacidad proceso:");
        jlProcessCapacity.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel24.add(jlProcessCapacity);

        jtfProcessCapacity.setEditable(false);
        jtfProcessCapacity.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfProcessCapacity.setText("0");
        jtfProcessCapacity.setFocusable(false);
        jtfProcessCapacity.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel24.add(jtfProcessCapacity);

        jPanel2.add(jPanel24);

        jPanel1.add(jPanel2, java.awt.BorderLayout.NORTH);

        getContentPane().add(jPanel1, java.awt.BorderLayout.WEST);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Cartas de control:"));
        jPanel3.setLayout(new java.awt.GridLayout(2, 1, 0, 5));

        jpChartX.setLayout(new java.awt.BorderLayout());
        jPanel3.add(jpChartX);

        jpChartR.setLayout(new java.awt.BorderLayout());
        jPanel3.add(jpChartR);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JButton jbShowStats;
    private javax.swing.JButton jbWipeStats;
    private javax.swing.JLabel jlChartLables;
    private javax.swing.JLabel jlChartR;
    private javax.swing.JLabel jlChartX;
    private javax.swing.JLabel jlFamily;
    private javax.swing.JLabel jlItem;
    private javax.swing.JLabel jlItemType;
    private javax.swing.JLabel jlJobs;
    private javax.swing.JLabel jlJobsDummy;
    private javax.swing.JLabel jlJobsFinished;
    private javax.swing.JLabel jlJobsTotal;
    private javax.swing.JLabel jlLC;
    private javax.swing.JLabel jlLCL;
    private javax.swing.JLabel jlLCU;
    private javax.swing.JLabel jlMfg;
    private javax.swing.JLabel jlPeriod;
    private javax.swing.JLabel jlProcessCapacity;
    private javax.swing.JLabel jlSamples;
    private javax.swing.JLabel jlSamples1;
    private javax.swing.JLabel jlSamples2;
    private javax.swing.JLabel jlVariable;
    private javax.swing.JPanel jpChartR;
    private javax.swing.JPanel jpChartX;
    private javax.swing.JTextField jtfGroupSamples;
    private javax.swing.JTextField jtfGroups;
    private javax.swing.JTextField jtfJobsFinished;
    private javax.swing.JTextField jtfJobsTotal;
    private javax.swing.JTextField jtfLCChartR;
    private javax.swing.JTextField jtfLCChartX;
    private javax.swing.JTextField jtfLCLChartR;
    private javax.swing.JTextField jtfLCLChartX;
    private javax.swing.JTextField jtfLCUChartR;
    private javax.swing.JTextField jtfLCUChartX;
    private javax.swing.JTextField jtfProcessCapacity;
    private javax.swing.JTextField jtfSamples;
    private sba.lib.gui.bean.DBeanCompoundField moCompMfgMass;
    private sba.lib.gui.bean.DBeanCompoundField moCompMfgUnits;
    private sba.lib.gui.bean.DBeanFieldDate moDateDateEnd;
    private sba.lib.gui.bean.DBeanFieldDate moDateDateStart;
    private sba.lib.gui.bean.DBeanFieldKey moKeyFamily;
    private sba.lib.gui.bean.DBeanFieldKey moKeyItem;
    private sba.lib.gui.bean.DBeanFieldKey moKeyItemType;
    private sba.lib.gui.bean.DBeanFieldKey moKeyVariable;
    // End of variables declaration//GEN-END:variables

    /*
     * Private methods:
     */
    
    private void initComponentsCustom() {
        DGuiUtils.setWindowBounds(this, 1040, 650);
        
        moDateDateStart.setDateSettings(miClient, DGuiUtils.getLabelName(jlPeriod), true);
        moDateDateEnd.setDateSettings(miClient, DGuiUtils.getLabelName(jlPeriod), true);
        moKeyItemType.setKeySettings(miClient, DGuiUtils.getLabelName(jlItemType), true);
        moKeyFamily.setKeySettings(miClient, DGuiUtils.getLabelName(jlFamily), true);
        moKeyItem.setKeySettings(miClient, DGuiUtils.getLabelName(jlItem), true);
        
        moFields.addField(moDateDateStart);
        moFields.addField(moDateDateEnd);
        moFields.addField(moKeyItemType);
        moFields.addField(moKeyFamily);
        moFields.addField(moKeyItem);
        
        moFields.setFormButton(jbShowStats);
        
        moKeyGroupJobFormula = new DGuiFieldKeyGroup(miClient);
        
        mjfxpChartX.setPreferredSize(new Dimension(600, 275));
        mjfxpChartR.setPreferredSize(new Dimension(600, 275));
        
        jpChartX.add(mjfxpChartX, BorderLayout.CENTER);
        jpChartR.add(mjfxpChartR, BorderLayout.CENTER);
        
        Platform.setImplicitExit(false);    // prevents JavaFX thread from stopping when this dialog closes
        
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                createJfxPanel(mjfxpChartX, "Carta X");
                createJfxPanel(mjfxpChartR, "Carta R");
            }
        });
    }
    
    private void createJfxPanel(final JFXPanel fxPanel, final String title) {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        
        lineChart.setTitle(title);
        lineChart.setCreateSymbols(false);
        Scene scene = new Scene(lineChart);
        
        fxPanel.setScene(scene);
    }
    
    private void addSamplesGroup(final DSamplesGroupsArray groupsArray, final ArrayList<Double> group, final int samplesNum) throws Exception {
        if (!group.isEmpty()) {
            double[] samples = new double[group.size()];
            
            for (int i = 0; i < group.size(); i++) {
                samples[i] = group.get(i);
            }
            
            groupsArray.addSamples(new DSamplesGroup(samplesNum, DStatsUtils.completeSamples(samplesNum, samples)));
            
            group.clear();
        }
    }
    
    private void clearVariableItems() {
        moKeyVariable.removeAllItems();
        moKeyVariable.setEnabled(false);
    }
    
    private void createVariableItems() throws Exception {
        ArrayList<DGuiItem> items = DQtyUtils.getVariableItems(miClient.getSession(), moDateDateStart.getValue(), moDateDateEnd.getValue(), moKeyItem.getValue()[0]);

        moKeyVariable.addItem(new DGuiItem("- " + DGuiUtils.getLabelName(jlVariable) + " -"));

        for (DGuiItem item : items) {
            moKeyVariable.addItem(item);
        }

        moKeyVariable.setEnabled(true);
    }
    
    private void clearChartX() {
        LineChart<Number, Number> lineChart = (LineChart<Number, Number>) mjfxpChartX.getScene().getRoot();
        lineChart.getData().clear();
    }
    
    private void clearChartR() {
        LineChart<Number, Number> lineChart = (LineChart<Number, Number>) mjfxpChartR.getScene().getRoot();
        lineChart.getData().clear();
    }
    
    private void clearCharts() {
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                clearChartX();
                clearChartR();
            }
        });
    }
    
    private void createChartX() {
        double[] data = moControlCharts.getXChartData();
        LineChart<Number, Number> lineChart = (LineChart<Number, Number>) mjfxpChartX.getScene().getRoot();
        
        XYChart.Series seriesCL = new XYChart.Series<>();
        seriesCL.setName("Límite control");
        seriesCL.getData().add(new XYChart.Data(1, moControlCharts.getXChartCentralLim()));
        seriesCL.getData().add(new XYChart.Data(data.length, moControlCharts.getXChartCentralLim()));
        
        XYChart.Series seriesCLU = new XYChart.Series<>();
        seriesCLU.setName("Límite control superior");
        seriesCLU.getData().add(new XYChart.Data(1, moControlCharts.getXChartCentralLimUpper()));
        seriesCLU.getData().add(new XYChart.Data(data.length, moControlCharts.getXChartCentralLimUpper()));
        
        XYChart.Series seriesCLL = new XYChart.Series<>();
        seriesCLL.setName("Límite control inferior");
        seriesCLL.getData().add(new XYChart.Data(1, moControlCharts.getXChartCentralLimLower()));
        seriesCLL.getData().add(new XYChart.Data(data.length, moControlCharts.getXChartCentralLimLower()));
        
        XYChart.Series series = new XYChart.Series<>();
        series.setName("Variable");
        for (int i = 0; i < data.length; i++) {
            series.getData().add(new XYChart.Data(i + 1, data[i]));
        }
        
        lineChart.getData().clear();
        lineChart.getData().add(seriesCL);
        lineChart.getData().add(seriesCLU);
        lineChart.getData().add(seriesCLL);
        lineChart.getData().add(series);
    }
    
    private void createChartR() {
        double[] data = moControlCharts.getRChartData();
        LineChart<Number, Number> lineChart = (LineChart<Number, Number>) mjfxpChartR.getScene().getRoot();
        
        XYChart.Series seriesCL = new XYChart.Series<>();
        seriesCL.setName("Límite control");
        seriesCL.getData().add(new XYChart.Data(1, moControlCharts.getRChartCentralLim()));
        seriesCL.getData().add(new XYChart.Data(data.length, moControlCharts.getRChartCentralLim()));
        
        XYChart.Series seriesCLU = new XYChart.Series<>();
        seriesCLU.setName("Límite control superior");
        seriesCLU.getData().add(new XYChart.Data(1, moControlCharts.getRChartCentralLimUpper()));
        seriesCLU.getData().add(new XYChart.Data(data.length, moControlCharts.getRChartCentralLimUpper()));
        
        XYChart.Series seriesCLL = new XYChart.Series<>();
        seriesCLL.setName("Límite control inferior");
        seriesCLL.getData().add(new XYChart.Data(1, moControlCharts.getRChartCentralLimLower()));
        seriesCLL.getData().add(new XYChart.Data(data.length, moControlCharts.getRChartCentralLimLower()));
        
        XYChart.Series series = new XYChart.Series<>();
        series.setName("Variable");
        for (int i = 0; i < data.length; i++) {
            series.getData().add(new XYChart.Data(i + 1, data[i]));
        }
        
        lineChart.getData().clear();
        lineChart.getData().add(seriesCL);
        lineChart.getData().add(seriesCLU);
        lineChart.getData().add(seriesCLL);
        lineChart.getData().add(series);
    }
    
    private void createCharts() {
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                createChartX();
                createChartR();
            }
        });
    }
    private void wipeDataJobs() {
        jtfJobsTotal.setText("");
        jtfJobsFinished.setText("");
        moCompMfgUnits.getField().resetField();
        moCompMfgUnits.setCompoundText("");
        moCompMfgMass.getField().resetField();
        moCompMfgMass.setCompoundText("");
    }
    
    private void showDataJobs() {
        jtfJobsTotal.setText("");
        jtfJobsFinished.setText("");
        moCompMfgUnits.getField().resetField();
        moCompMfgUnits.setCompoundText("");
        moCompMfgMass.getField().resetField();
        moCompMfgMass.setCompoundText("");
    }
    
    private void wipeDataStats() {
        jtfSamples.setText("");
        jtfGroups.setText("");
        jtfGroupSamples.setText("");
        jtfLCChartX.setText("");
        jtfLCChartR.setText("");
        jtfLCUChartX.setText("");
        jtfLCUChartR.setText("");
        jtfLCLChartX.setText("");
        jtfLCLChartR.setText("");
        jtfProcessCapacity.setText("");
        
        clearCharts();
    }
    
    private void showDataStats() throws Exception {
        int samples = 0;
        int samplesNum = 0;
        int idItem = moKeyItem.getValue()[0];
        int idVariable = moKeyVariable.getValue()[0];
        Date start = moDateDateStart.getValue();
        Date end = moDateDateEnd.getValue();
        String sql = "";
        ResultSet resultSet = null;
        
        // get maximum number of samples on quality-test applications to job orders
        
        sql = "SELECT COALESCE(MAX(arv.id_res), 0) "
                + "FROM " + DModConsts.TablesMap.get(DModConsts.Q_APP) + " AS a "
                + "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.Q_APP_RES) + " AS ar ON a.id_job = ar.id_job AND a.id_app = ar.id_app "
                + "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.Q_APP_RES_VAR) + " AS arv ON ar.id_job = arv.id_job AND ar.id_app = arv.id_app AND ar.id_res = arv.id_res "
                + "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.M_JOB) + " AS j ON a.id_job = j.id_job "
                + "WHERE NOT a.b_del AND a.fk_itm = " + idItem + " AND arv.id_var = " + idVariable + " AND "
                + "NOT j.b_del AND NOT j.b_ann AND j.dat BETWEEN '" + DLibUtils.DbmsDateFormatDate.format(start) + "' AND '" + DLibUtils.DbmsDateFormatDate.format(end) + "' ";
        resultSet = miClient.getSession().getStatement().executeQuery(sql);
        
        if (resultSet.next()) {
            samplesNum = resultSet.getInt(1);
        }
        else {
            throw new Exception(DDbConsts.ERR_MSG_REG_NOT_FOUND);
        }
        
        // get samples
        
        sql = "SELECT arv.id_job, arv.id_app, arv.id_res, arv.id_var, arv.val "
                + "FROM " + DModConsts.TablesMap.get(DModConsts.Q_APP) + " AS a "
                + "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.Q_APP_RES) + " AS ar ON a.id_job = ar.id_job AND a.id_app = ar.id_app "
                + "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.Q_APP_RES_VAR) + " AS arv ON ar.id_job = arv.id_job AND ar.id_app = arv.id_app AND ar.id_res = arv.id_res "
                + "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.M_JOB) + " AS j ON a.id_job = j.id_job "
                + "WHERE NOT a.b_del AND a.fk_itm = " + idItem + " AND arv.id_var = " + idVariable + " AND "
                + "NOT j.b_del AND NOT j.b_ann AND j.dat BETWEEN '" + DLibUtils.DbmsDateFormatDate.format(start) + "' AND '" + DLibUtils.DbmsDateFormatDate.format(end) + "' "
                + "ORDER BY arv.id_job, arv.id_app, arv.id_res, arv.id_var ";
        resultSet = miClient.getSession().getStatement().executeQuery(sql);
        
        int job = 0;    // current job
        int app = 0;    // current job's application
        ArrayList<Double> group = new ArrayList<>();
        DSamplesGroupsArray groupsArray = new DSamplesGroupsArray(samplesNum);
        
        while (resultSet.next()) {
            samples++;
            
            if (job != resultSet.getInt("arv.id_job")) {
                addSamplesGroup(groupsArray, group, samplesNum);    // add samples, if any
                
                job = resultSet.getInt("arv.id_job");
                app = 0;    // reset current job's application
            }
            
            if (app != resultSet.getInt("arv.id_app")) {
                addSamplesGroup(groupsArray, group, samplesNum);    // add samples, if any
                
                app = resultSet.getInt("arv.id_app");
            }
            
            group.add(resultSet.getDouble("arv.val"));
        }
        
        addSamplesGroup(groupsArray, group, samplesNum);    // add samples, if any
        
        moControlCharts = new DControlCharts(groupsArray.getSamples());
        
        jtfSamples.setText(DLibUtils.DecimalFormatInteger.format(samples));
        jtfGroups.setText(DLibUtils.DecimalFormatInteger.format(groupsArray.getGroupsNum()));
        jtfGroupSamples.setText(DLibUtils.DecimalFormatInteger.format(samplesNum));
        jtfLCChartX.setText(DLibUtils.DecimalFormatValue4D.format(moControlCharts.getXChartCentralLim()));
        jtfLCChartR.setText(DLibUtils.DecimalFormatValue4D.format(moControlCharts.getRChartCentralLim()));
        jtfLCUChartX.setText(DLibUtils.DecimalFormatValue4D.format(moControlCharts.getXChartCentralLimUpper()));
        jtfLCUChartR.setText(DLibUtils.DecimalFormatValue4D.format(moControlCharts.getRChartCentralLimUpper()));
        jtfLCLChartX.setText(DLibUtils.DecimalFormatValue4D.format(moControlCharts.getXChartCentralLimLower()));
        jtfLCLChartR.setText(DLibUtils.DecimalFormatValue4D.format(moControlCharts.getRChartCentralLimLower()));
        jtfProcessCapacity.setText(DLibUtils.DecimalFormatValue4D.format(moControlCharts.getProcessCapacity()));
        
        createCharts();
    }
    
    private void actionPerformedWipeStats() {
        try {
            removeAllListeners();
            
            wipeDataJobs();

            moDateDateStart.setEditable(true);
            moDateDateEnd.setEditable(true);
            moKeyItemType.setEnabled(true);
            moKeyFamily.setEnabled(true);
            moKeyItem.setEnabled(true);

            clearVariableItems();
            itemStateChangedVariable();

            jbWipeStats.setEnabled(false);
            jbShowStats.setEnabled(true);

            moDateDateStart.requestFocus();
        }
        catch (Exception e) {
            DLibUtils.showException(this, e);
        }
        finally {
            addAllListeners();
        }
    }
    
    private void actionPerformedShowStats() {
        try {
            removeAllListeners();

            DGuiValidation validation = moFields.validateFields();
            if (validation.isValid()) {
                validation = DGuiUtils.validateDateRange(moDateDateStart, moDateDateEnd);
                if (validation.isValid()) {
                    showDataJobs();
                    
                    moDateDateStart.setEditable(false);
                    moDateDateEnd.setEditable(false);
                    moKeyItemType.setEnabled(false);
                    moKeyFamily.setEnabled(false);
                    moKeyItem.setEnabled(false);

                    createVariableItems();
                    itemStateChangedVariable();

                    jbWipeStats.setEnabled(true);
                    jbShowStats.setEnabled(false);

                    moKeyVariable.requestFocus();
                }
            }

            if (!validation.isValid()) {
                DGuiUtils.computeValidation(miClient, validation);
            }
        }
        catch (Exception e) {
            DLibUtils.showException(this, e);
        }
        finally {
            addAllListeners();
        }
    }
    
    private void itemStateChangedVariable() {
        try {
            if (moKeyVariable.getSelectedIndex() <= 0) {
                wipeDataStats();
            }
            else {
                showDataStats();
            }
        }
        catch (Exception e) {
            DLibUtils.showException(this, e);
        }
    }
    
    /*
     * Public methods:
     */
    
    /*
     * Overriden methods:
     */
    
    @Override
    public void addAllListeners() {
        jbShowStats.addActionListener(this);
        jbWipeStats.addActionListener(this);
        moKeyVariable.addItemListener(this);
    }

    @Override
    public void removeAllListeners() {
        jbShowStats.removeActionListener(this);
        jbWipeStats.removeActionListener(this);
        moKeyVariable.removeItemListener(this);
    }

    @Override
    public void reloadCatalogues() {
        moKeyGroupJobFormula.initGroup();
        moKeyGroupJobFormula.addFieldKey(moKeyItemType, DModConsts.CX_ITM_TP_PRO_MFG, DLibConsts.UNDEFINED, null);
        moKeyGroupJobFormula.addFieldKey(moKeyFamily, DModConsts.CU_FAM, DLibConsts.UNDEFINED, null);
        moKeyGroupJobFormula.addFieldKey(moKeyItem, DModConsts.CX_ITM_FK_FAM, DLibConsts.UNDEFINED, null);
        moKeyGroupJobFormula.populateCatalogues();
    }

    @Override
    public void setRegistry(DDbRegistry registry) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DDbRegistry getRegistry() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DGuiValidation validateForm() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initForm() {
        moDateDateStart.setValue(DLibTimeUtils.getBeginOfMonth(miClient.getSession().getWorkingDate()));
        moDateDateEnd.setValue(DLibTimeUtils.getEndOfMonth(miClient.getSession().getWorkingDate()));
        
        removeAllListeners();
        reloadCatalogues();
        
        wipeDataJobs();
        wipeDataStats();
        clearVariableItems();
        
        moKeyGroupJobFormula.resetGroup();
        
        addAllListeners();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            
            if (button == jbWipeStats) {
                actionPerformedWipeStats();
            }
            else if (button == jbShowStats) {
                actionPerformedShowStats();
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() instanceof DBeanFieldKey) {
            DBeanFieldKey field = (DBeanFieldKey) e.getSource();
            
            if (field == moKeyVariable) {
                itemStateChangedVariable();
            }
        }
    }
}

