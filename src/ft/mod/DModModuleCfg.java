/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod;

import ft.mod.cfg.db.DDbConfig;
import ft.mod.cfg.db.DDbItem;
import ft.mod.cfg.db.DDbItemFamily;
import ft.mod.cfg.db.DDbItemGroup;
import ft.mod.cfg.db.DDbPresent;
import ft.mod.cfg.db.DDbUnit;
import ft.mod.cfg.db.DDbUser;
import ft.mod.cfg.db.DDbUserGui;
import ft.mod.cfg.form.DFormItem;
import ft.mod.cfg.form.DFormItemFamily;
import ft.mod.cfg.form.DFormItemGroup;
import ft.mod.cfg.form.DFormPresent;
import ft.mod.cfg.form.DFormUnit;
import ft.mod.cfg.view.DViewItem;
import ft.mod.cfg.view.DViewItemFamily;
import ft.mod.cfg.view.DViewItemGroup;
import ft.mod.cfg.view.DViewPresent;
import ft.mod.cfg.view.DViewUnit;
import javax.swing.JMenu;
import sba.lib.DLibConsts;
import sba.lib.db.DDbConsts;
import sba.lib.db.DDbRegistry;
import sba.lib.db.DDbRegistrySysFly;
import sba.lib.grid.DGridPaneView;
import sba.lib.gui.DGuiCatalogueSettings;
import sba.lib.gui.DGuiClient;
import sba.lib.gui.DGuiForm;
import sba.lib.gui.DGuiModule;
import sba.lib.gui.DGuiOptionPicker;
import sba.lib.gui.DGuiParams;
import sba.lib.gui.DGuiReport;

/**
 *
 * @author Sergio Flores
 */
public class DModModuleCfg extends DGuiModule {
    
    private DFormUnit moFormUnit;
    private DFormPresent moFormPresent;
    private DFormItemFamily moFormItemFamily;
    private DFormItemGroup moFormItemGroup;
    private DFormItem moFormItemRmi;
    private DFormItem moFormItemRmp;
    private DFormItem moFormItemMi;
    private DFormItem moFormItemPb;
    private DFormItem moFormItemPf;
    private DFormItem moFormItemEm;
    private DFormItem moFormItemEo;

    public DModModuleCfg(DGuiClient client) {
        super(client, DModSysConsts.CS_MOD_CFG, DLibConsts.UNDEFINED);
    }

    @Override
    public JMenu[] getMenus() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DDbRegistry getRegistry(final int type) {
        DDbRegistry registry = null;

        switch (type) {
            case DModConsts.CS_MOD:
                registry = new DDbRegistrySysFly(type) {
                    @Override
                    public String getSqlTable() { return DModConsts.TablesMap.get(type); }
                    
                    @Override
                    public String getSqlWhere(int[] pk) { return "WHERE id_mod = " + pk[0] + " "; }
                };
                break;
            case DModConsts.CS_USR_TP:
                registry = new DDbRegistrySysFly(type) {
                    @Override
                    public String getSqlTable() { return DModConsts.TablesMap.get(type); }
                    
                    @Override
                    public String getSqlWhere(int[] pk) { return "WHERE id_usr_tp = " + pk[0] + " "; }
                };
                break;
            case DModConsts.CS_UNT_TP:
                registry = new DDbRegistrySysFly(type) {
                    @Override
                    public String getSqlTable() { return DModConsts.TablesMap.get(type); }
                    
                    @Override
                    public String getSqlWhere(int[] pk) { return "WHERE id_unt_tp = " + pk[0] + " "; }
                };
                break;
            case DModConsts.CS_ITM_TP:
                registry = new DDbRegistrySysFly(type) {
                    @Override
                    public String getSqlTable() { return DModConsts.TablesMap.get(type); }
                    
                    @Override
                    public String getSqlWhere(int[] pk) { return "WHERE id_itm_tp = " + pk[0] + " "; }
                };
                break;
            case DModConsts.CU_USR:
                registry = new DDbUser();
                break;
            case DModConsts.CU_USR_MOD:
                break;
            case DModConsts.CU_UNT:
                registry = new DDbUnit();
                break;
            case DModConsts.CU_PRS:
                registry = new DDbPresent();
                break;
            case DModConsts.CU_FAM:
                registry = new DDbItemFamily();
                break;
            case DModConsts.CU_GRP:
                registry = new DDbItemGroup();
                break;
            case DModConsts.CU_ITM:
                registry = new DDbItem();
                break;
            case DModConsts.C_CFG:
                registry = new DDbConfig();
                break;
            case DModConsts.C_USR_GUI:
                registry = new DDbUserGui();
                break;
            default:
                miClient.showMsgBoxError(DLibConsts.ERR_MSG_OPTION_UNKNOWN);
        }

        return registry;
    }

    @Override
    public DGuiCatalogueSettings getCatalogueSettings(final int type, final int subtype, final DGuiParams params) {
        String sql = "";
        DGuiCatalogueSettings settings = null;

        switch (type) {
            case DModConsts.CS_MOD:
                settings = new DGuiCatalogueSettings("Módulo", 1);
                sql = "SELECT id_mod AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY sort ";
                break;
            case DModConsts.CS_USR_TP:
                settings = new DGuiCatalogueSettings("Tipo usuario", 1);
                sql = "SELECT id_usr_tp AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY sort ";
                break;
            case DModConsts.CS_UNT_TP:
                settings = new DGuiCatalogueSettings("Tipo unidad", 1, 0, DLibConsts.DATA_TYPE_TEXT);
                sql = "SELECT id_unt_tp AS " + DDbConsts.FIELD_ID + "1, CONCAT(name, ' [', bas, ']') AS " + DDbConsts.FIELD_ITEM + ", bas AS " + DDbConsts.FIELD_COMP + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY sort ";
                break;
            case DModConsts.CS_ITM_TP:
                settings = new DGuiCatalogueSettings("Tipo ítem", 1);
                sql = "SELECT id_itm_tp AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY sort ";
                break;
            case DModConsts.CU_USR:
                break;
            case DModConsts.CU_USR_MOD:
                break;
            case DModConsts.CU_UNT:
                settings = new DGuiCatalogueSettings("Unidad", 1, 0, DLibConsts.DATA_TYPE_TEXT);
                sql = "SELECT id_unt AS " + DDbConsts.FIELD_ID + "1, CONCAT(name, ' [', code, ']') AS " + DDbConsts.FIELD_ITEM + ", code AS " + DDbConsts.FIELD_COMP + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 " + (subtype == DLibConsts.UNDEFINED ? "" : "AND fk_unt_tp = " + subtype + " ") + "ORDER BY fk_unt_tp, sort ";
                break;
            case DModConsts.CU_PRS:
                settings = new DGuiCatalogueSettings("Presentación", 1);
                sql = "SELECT id_prs AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY name, id_prs ";
                break;
            case DModConsts.CU_FAM:
                settings = new DGuiCatalogueSettings("Familia ítem", 1);
                sql = "SELECT id_fam AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 " + (subtype == DLibConsts.UNDEFINED ? "" : "AND fk_itm_tp = " + subtype + " ") + "ORDER BY name, id_fam ";
                break;
            case DModConsts.CU_GRP:
                settings = new DGuiCatalogueSettings("Grupo ítem", 1, 1);
                sql = "SELECT id_grp AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + ", fk_fam AS " + DDbConsts.FIELD_FK + "1 " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY name, id_grp ";
                break;
            case DModConsts.CU_ITM:
                settings = new DGuiCatalogueSettings("Ítem", 1, 1, DLibConsts.DATA_TYPE_TEXT);
                sql = "SELECT i.id_itm AS " + DDbConsts.FIELD_ID + "1, i.name AS " + DDbConsts.FIELD_ITEM + ", i.fk_grp AS " + DDbConsts.FIELD_FK + "1, u.code AS " + DDbConsts.FIELD_COMP + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " AS i " +
                        "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_UNT) + " AS u ON i.fk_unt = u.id_unt " +
                        "WHERE i.b_del = 0 " + (params == null ? "" : "AND i.fk_fam = " + params.getKey()[0] + " ") + "ORDER BY i.name, i.id_itm ";
                break;
            case DModConsts.C_CFG:
                break;
            case DModConsts.C_USR_GUI:
                break;
            case DModConsts.CX_ITM_TP_PRO:
                settings = new DGuiCatalogueSettings("Tipo producto", 1);
                sql = "SELECT id_itm_tp AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + " " +
                        "FROM " + DModConsts.TablesMap.get(DModConsts.CS_ITM_TP) + " " +
                        "WHERE b_del = 0 AND id_itm_tp IN (" + DModSysConsts.CS_ITM_TP_PB + ", " + DModSysConsts.CS_ITM_TP_PF + ") " +
                        "ORDER BY sort ";
                break;
            case DModConsts.CX_ITM_TP_CMP:
                settings = new DGuiCatalogueSettings("Tipo componente", 1);
                sql = "SELECT id_itm_tp AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + " " +
                        "FROM " + DModConsts.TablesMap.get(DModConsts.CS_ITM_TP) + " " +
                        "WHERE b_del = 0 AND id_itm_tp IN (" + DModSysConsts.CS_ITM_TP_RMI + ", " + DModSysConsts.CS_ITM_TP_RMP + ", " + DModSysConsts.CS_ITM_TP_PB + ", " + DModSysConsts.CS_ITM_TP_PF + ") " +
                        "ORDER BY sort ";
                break;
            case DModConsts.CX_ITM_BY_ITM_TP:
                settings = new DGuiCatalogueSettings("Ítem", 1, 1, DLibConsts.DATA_TYPE_TEXT);
                sql = "SELECT i.id_itm AS " + DDbConsts.FIELD_ID + "1, i.name AS " + DDbConsts.FIELD_ITEM + ", f.fk_itm_tp AS " + DDbConsts.FIELD_FK + "1, u.code AS " + DDbConsts.FIELD_COMP + " " +
                        "FROM " + DModConsts.TablesMap.get(DModConsts.CU_ITM) + " AS i " +
                        "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_FAM) + " AS f ON i.fk_fam = f.id_fam " +
                        "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_UNT) + " AS u ON i.fk_unt = u.id_unt " +
                        "WHERE i.b_del = 0 " +
                        "ORDER BY i.name, i.id_itm ";
                break;
            default:
                miClient.showMsgBoxError(DLibConsts.ERR_MSG_OPTION_UNKNOWN);
        }

        if (settings != null) {
            settings.setSql(sql);
        }

        return settings;
    }

    @Override
    public DGridPaneView getView(final int type, final int subtype, final DGuiParams params) {
        DGridPaneView view = null;
        String title = "";

        switch (type) {
            case DModConsts.CS_MOD:
                break;
            case DModConsts.CS_USR_TP:
                break;
            case DModConsts.CS_UNT_TP:
                break;
            case DModConsts.CS_ITM_TP:
                break;
            case DModConsts.CU_USR:
                break;
            case DModConsts.CU_USR_MOD:
                break;
            case DModConsts.CU_UNT:
                view = new DViewUnit(miClient, "Unidades");
                break;
            case DModConsts.CU_PRS:
                view = new DViewPresent(miClient, "Presentaciones");
                break;
            case DModConsts.CU_FAM:
                view = new DViewItemFamily(miClient, "Familias ítems");
                break;
            case DModConsts.CU_GRP:
                view = new DViewItemGroup(miClient, "Grupos ítems");
                break;
            case DModConsts.CU_ITM:
                switch (subtype) {
                    case DModSysConsts.CS_ITM_TP_RMI:
                        title = "Mats. directos insumo";
                        break;
                    case DModSysConsts.CS_ITM_TP_RMP:
                        title = "Mats. directos empaque";
                        break;
                    case DModSysConsts.CS_ITM_TP_MI:
                        title = "Mats. indirectos";
                        break;
                    case DModSysConsts.CS_ITM_TP_PB:
                        title = "Prods. base";
                        break;
                    case DModSysConsts.CS_ITM_TP_PF:
                        title = "Prods. terminados";
                        break;
                    case DModSysConsts.CS_ITM_TP_EM:
                        title = "Gtos. producción";
                        break;
                    case DModSysConsts.CS_ITM_TP_EO:
                        title = "Gtos. operación";
                        break;
                    case DLibConsts.UNDEFINED:
                        title = "Ítems";
                        break;
                    default:
                        miClient.showMsgBoxError(DLibConsts.ERR_MSG_OPTION_UNKNOWN);
                }
                view = new DViewItem(miClient, subtype, title);
                break;
            case DModConsts.C_CFG:
                break;
            case DModConsts.C_USR_GUI:
                break;
            default:
                miClient.showMsgBoxError(DLibConsts.ERR_MSG_OPTION_UNKNOWN);
        }

        return view;
    }

    @Override
    public DGuiOptionPicker getOptionPicker(final int type, final int subtype, final DGuiParams params) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DGuiForm getForm(final int type, final int subtype, final DGuiParams params) {
        DGuiForm form = null;

        switch (type) {
            case DModConsts.CS_MOD:
                break;
            case DModConsts.CS_USR_TP:
                break;
            case DModConsts.CS_UNT_TP:
                break;
            case DModConsts.CS_ITM_TP:
                break;
            case DModConsts.CU_USR:
                break;
            case DModConsts.CU_USR_MOD:
                break;
            case DModConsts.CU_UNT:
                if (moFormUnit == null) moFormUnit = new DFormUnit(miClient, "Unidad");
                form = moFormUnit;
                break;
            case DModConsts.CU_PRS:
                if (moFormPresent == null) moFormPresent = new DFormPresent(miClient, "Presentación");
                form = moFormPresent;
                break;
            case DModConsts.CU_FAM:
                if (moFormItemFamily == null) moFormItemFamily = new DFormItemFamily(miClient, "Familia ítems");
                form = moFormItemFamily;
                break;
            case DModConsts.CU_GRP:
                if (moFormItemGroup == null) moFormItemGroup = new DFormItemGroup(miClient, "Grupo ítems");
                form = moFormItemGroup;
                break;
            case DModConsts.CU_ITM:
                switch (subtype) {
                    case DModSysConsts.CS_ITM_TP_RMI:
                        if (moFormItemRmi == null) moFormItemRmi = new DFormItem(miClient, subtype, "Material directo insumo");
                        form = moFormItemRmi;
                        break;
                    case DModSysConsts.CS_ITM_TP_RMP:
                        if (moFormItemRmp == null) moFormItemRmp = new DFormItem(miClient, subtype, "Material directo empaque");
                        form = moFormItemRmp;
                        break;
                    case DModSysConsts.CS_ITM_TP_MI:
                        if (moFormItemMi == null) moFormItemMi = new DFormItem(miClient, subtype, "Material indirecto");
                        form = moFormItemMi;
                        break;
                    case DModSysConsts.CS_ITM_TP_PB:
                        if (moFormItemPb == null) moFormItemPb = new DFormItem(miClient, subtype, "Producto base");
                        form = moFormItemPb;
                        break;
                    case DModSysConsts.CS_ITM_TP_PF:
                        if (moFormItemPf == null) moFormItemPf = new DFormItem(miClient, subtype, "Producto terminado");
                        form = moFormItemPf;
                        break;
                    case DModSysConsts.CS_ITM_TP_EM:
                        if (moFormItemEm == null) moFormItemEm = new DFormItem(miClient, subtype, "Gasto producción");
                        form = moFormItemEm;
                        break;
                    case DModSysConsts.CS_ITM_TP_EO:
                        if (moFormItemEo == null) moFormItemEo = new DFormItem(miClient, subtype, "Gasto operación");
                        form = moFormItemEo;
                        break;
                    default:
                        miClient.showMsgBoxError(DLibConsts.ERR_MSG_OPTION_UNKNOWN);
                }
                break;
            case DModConsts.C_CFG:
                break;
            case DModConsts.C_USR_GUI:
                break;
            default:
                miClient.showMsgBoxError(DLibConsts.ERR_MSG_OPTION_UNKNOWN);
        }

        return form;
    }

    @Override
    public DGuiReport getReport(final int type, final int subtype, final DGuiParams params) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
