/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod;

import ft.mod.cfg.db.DDbBizPartner;
import ft.mod.cfg.db.DDbConfig;
import ft.mod.cfg.db.DDbFamily;
import ft.mod.cfg.db.DDbItem;
import ft.mod.cfg.db.DDbPresent;
import ft.mod.cfg.db.DDbUnit;
import ft.mod.cfg.db.DDbUser;
import ft.mod.cfg.db.DDbUserGui;
import ft.mod.cfg.db.DDbUserModule;
import ft.mod.cfg.db.DDbYear;
import ft.mod.cfg.form.DFormBizPartner;
import ft.mod.cfg.form.DFormFamily;
import ft.mod.cfg.form.DFormItem;
import ft.mod.cfg.form.DFormPresent;
import ft.mod.cfg.form.DFormUnit;
import ft.mod.cfg.form.DFormYear;
import ft.mod.cfg.view.DViewBizPartner;
import ft.mod.cfg.view.DViewFamily;
import ft.mod.cfg.view.DViewItem;
import ft.mod.cfg.view.DViewPresent;
import ft.mod.cfg.view.DViewUnit;
import ft.mod.cfg.view.DViewYear;
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
    
    private DFormBizPartner moFormBizPartnerCom;
    private DFormBizPartner moFormBizPartnerCus;
    private DFormBizPartner moFormBizPartnerSup;
    private DFormUnit moFormUnit;
    private DFormPresent moFormPresent;
    private DFormFamily moFormFamily;
    private DFormItem moFormItemRmi;
    private DFormItem moFormItemRmp;
    private DFormItem moFormItemMi;
    private DFormItem moFormItemP;
    private DFormItem moFormItemPb;
    private DFormItem moFormItemPf;
    private DFormItem moFormItemBp;
    private DFormItem moFormItemSc;
    private DFormItem moFormItemMo;
    private DFormYear moFormYear;

    public DModModuleCfg(DGuiClient client) {
        super(client, DModSysConsts.CS_MOD_CFG, DLibConsts.UNDEFINED);
    }

    @Override
    public JMenu[] getMenus() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DDbRegistry getRegistry(final int type, DGuiParams params) {
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
            case DModConsts.CS_ACS_TP:
                registry = new DDbRegistrySysFly(type) {
                    @Override
                    public String getSqlTable() { return DModConsts.TablesMap.get(type); }
                    
                    @Override
                    public String getSqlWhere(int[] pk) { return "WHERE id_acs_tp = " + pk[0] + " "; }
                };
                break;
            case DModConsts.CS_PER_TP:
                registry = new DDbRegistrySysFly(type) {
                    @Override
                    public String getSqlTable() { return DModConsts.TablesMap.get(type); }
                    
                    @Override
                    public String getSqlWhere(int[] pk) { return "WHERE id_per_tp = " + pk[0] + " "; }
                };
                break;
            case DModConsts.CS_BPR_TP:
                registry = new DDbRegistrySysFly(type) {
                    @Override
                    public String getSqlTable() { return DModConsts.TablesMap.get(type); }
                    
                    @Override
                    public String getSqlWhere(int[] pk) { return "WHERE id_bpr_tp = " + pk[0] + " "; }
                };
                break;
            case DModConsts.CS_UOM_TP:
                registry = new DDbRegistrySysFly(type) {
                    @Override
                    public String getSqlTable() { return DModConsts.TablesMap.get(type); }
                    
                    @Override
                    public String getSqlWhere(int[] pk) { return "WHERE id_uom_tp = " + pk[0] + " "; }
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
                registry = new DDbUserModule();
                break;
            case DModConsts.CU_BPR:
                registry = new DDbBizPartner();
                break;
            case DModConsts.CU_UOM:
                registry = new DDbUnit();
                break;
            case DModConsts.CU_PRE:
                registry = new DDbPresent();
                break;
            case DModConsts.CU_FAM:
                registry = new DDbFamily();
                break;
            case DModConsts.CU_ITM:
                registry = new DDbItem();
                break;
            case DModConsts.C_CFG:
                registry = new DDbConfig();
                break;
            case DModConsts.C_YEA:
                registry = new DDbYear();
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
        String label = "";
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
            case DModConsts.CS_ACS_TP:
                settings = new DGuiCatalogueSettings("Tipo acceso", 1);
                sql = "SELECT id_acs_tp AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY sort ";
                break;
            case DModConsts.CS_PER_TP:
                settings = new DGuiCatalogueSettings("Tipo persona", 1);
                sql = "SELECT id_per_tp AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY sort ";
                break;
            case DModConsts.CS_BPR_TP:
                settings = new DGuiCatalogueSettings("Tipo asociado negocios", 1);
                sql = "SELECT id_bpr_tp AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY sort ";
                break;
            case DModConsts.CS_UOM_TP:
                settings = new DGuiCatalogueSettings("Tipo unidad", 1, 0, DLibConsts.DATA_TYPE_TEXT);
                sql = "SELECT id_uom_tp AS " + DDbConsts.FIELD_ID + "1, CONCAT(name, ' (', base, ')') AS " + DDbConsts.FIELD_ITEM + ", base AS " + DDbConsts.FIELD_COMP + " " +
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
            case DModConsts.CU_BPR:
                switch (subtype) {
                    case DModSysConsts.CS_BPR_TP_COM:
                        label = "Empresa";
                        break;
                    case DModSysConsts.CS_BPR_TP_CUS:
                        label = "Cliente";
                        break;
                    case DModSysConsts.CS_BPR_TP_SUP:
                        label = "Proveedor";
                        break;
                    default:
                        miClient.showMsgBoxError(DLibConsts.ERR_MSG_OPTION_UNKNOWN);
                }
                settings = new DGuiCatalogueSettings(label, 1);
                sql = "SELECT id_bpr AS " + DDbConsts.FIELD_ID + "1, CONCAT(name, ' (', code, ')') AS " + DDbConsts.FIELD_ITEM + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " " +
                        "WHERE b_del = 0 AND fk_bpr_tp = " + subtype + " " +
                        "ORDER BY name, code, id_bpr ";
                break;
            case DModConsts.CU_UOM:
                settings = new DGuiCatalogueSettings("Unidad", 1);
                settings.setCodeApplying(true);
                sql = "SELECT id_uom AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + ", code AS " + DDbConsts.FIELD_CODE + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " " +
                        "WHERE b_del = 0 " +
                        (subtype == DLibConsts.UNDEFINED ? "" : "AND fk_uom_tp = " + subtype + " ") +
                        "ORDER BY fk_uom_tp, sort ";
                break;
            case DModConsts.CU_PRE:
                settings = new DGuiCatalogueSettings("Presentación", 1);
                settings.setCodeApplying(true);
                sql = "SELECT id_pre AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + ", code AS " + DDbConsts.FIELD_CODE + " " +
                        "FROM " + DModConsts.TablesMap.get(type) + " WHERE b_del = 0 ORDER BY name, id_pre ";
                break;
            case DModConsts.CU_FAM:
                settings = new DGuiCatalogueSettings("Familia", 1, 1, DLibConsts.DATA_TYPE_TEXT);
                settings.setCodeApplying(true);
                sql = "SELECT f.id_fam AS " + DDbConsts.FIELD_ID + "1, f.name AS " + DDbConsts.FIELD_ITEM + ", f.code AS " + DDbConsts.FIELD_CODE + ", " +
                        "u.code AS " + DDbConsts.FIELD_COMP + ", f.fk_itm_tp AS " + DDbConsts.FIELD_FK + "1 " + // default FK: item type, even when not requested
                        "FROM " + DModConsts.TablesMap.get(type) + " AS f " +
                        "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_UOM) + " AS u ON  f.fk_uom = u.id_uom " +
                        "WHERE f.b_del = 0 " + 
                        (subtype == DLibConsts.UNDEFINED ? "" : "AND f.fk_itm_tp = " + subtype) + " " +
                        "ORDER BY f.name, f.id_fam ";
                break;
            case DModConsts.CU_ITM:
            case DModConsts.CX_ITM_FK_ITM_TP:
            case DModConsts.CX_ITM_FK_FAM:
                settings = new DGuiCatalogueSettings("Ítem", 1, 1, DLibConsts.DATA_TYPE_TEXT);
                settings.setCodeApplying(true);
                sql = "SELECT i.id_itm AS " + DDbConsts.FIELD_ID + "1, i.name AS " + DDbConsts.FIELD_ITEM + ", i.code AS " + DDbConsts.FIELD_CODE + ", " +
                        "u.code AS " + DDbConsts.FIELD_COMP + ", ";
                
                if (type == DModConsts.CX_ITM_FK_FAM) {
                    sql += "f.id_fam AS " + DDbConsts.FIELD_FK + "1 ";
                }
                else {
                    sql += "f.fk_itm_tp AS " + DDbConsts.FIELD_FK + "1 "; // default FK: item type, even when not requested
                }
                
                sql += "FROM " + DModConsts.TablesMap.get(DModConsts.CU_ITM) + " AS i " +
                        "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_FAM) + " AS f ON i.fk_fam = f.id_fam " +
                        "INNER JOIN " + DModConsts.TablesMap.get(DModConsts.CU_UOM) + " AS u ON i.fk_uom = u.id_uom " +
                        "WHERE i.b_del = 0 " + 
                        (subtype == DLibConsts.UNDEFINED ? "" : "AND f.fk_itm_tp = " + subtype) + " " +
                        (params == null ? "" : "AND i.fk_fam = " + params.getKey()[0]) + " " +
                        "ORDER BY i.name, i.id_itm ";
                break;
            case DModConsts.C_CFG:
                break;
            case DModConsts.C_YEA:
                break;
            case DModConsts.C_USR_GUI:
                break;
            case DModConsts.CX_ITM_TP_PRO_MFG:
                settings = new DGuiCatalogueSettings("Tipo producto", 1);
                sql = "SELECT id_itm_tp AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + " " +
                        "FROM " + DModConsts.TablesMap.get(DModConsts.CS_ITM_TP) + " " +
                        "WHERE b_del = 0 AND id_itm_tp IN (" + DModSysConsts.CS_ITM_TP_PB + ", " + DModSysConsts.CS_ITM_TP_PF + ") " +
                        "ORDER BY sort ";
                break;
            case DModConsts.CX_ITM_TP_PRO_MFG_ALL:
                settings = new DGuiCatalogueSettings("Tipo producto", 1);
                sql = "SELECT id_itm_tp AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + " " +
                        "FROM " + DModConsts.TablesMap.get(DModConsts.CS_ITM_TP) + " " +
                        "WHERE b_del = 0 AND id_itm_tp IN (" + DModSysConsts.CS_ITM_TP_PB + ", " + DModSysConsts.CS_ITM_TP_PF + ", " + DModSysConsts.CS_ITM_TP_BP + ", " + DModSysConsts.CS_ITM_TP_SC + ") " +
                        "ORDER BY sort ";
                break;
            case DModConsts.CX_ITM_TP_CMP:
                settings = new DGuiCatalogueSettings("Tipo componente", 1);
                sql = "SELECT id_itm_tp AS " + DDbConsts.FIELD_ID + "1, name AS " + DDbConsts.FIELD_ITEM + " " +
                        "FROM " + DModConsts.TablesMap.get(DModConsts.CS_ITM_TP) + " " +
                        "WHERE b_del = 0 AND id_itm_tp IN (" + DModSysConsts.CS_ITM_TP_RMI + ", " + DModSysConsts.CS_ITM_TP_RMP + ", " + DModSysConsts.CS_ITM_TP_PB + ", " + DModSysConsts.CS_ITM_TP_PF + ") " +
                        "ORDER BY sort ";
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
        String label = "";
        DGridPaneView view = null;

        switch (type) {
            case DModConsts.CS_MOD:
                break;
            case DModConsts.CS_USR_TP:
                break;
            case DModConsts.CS_ACS_TP:
                break;
            case DModConsts.CS_PER_TP:
                break;
            case DModConsts.CS_BPR_TP:
                break;
            case DModConsts.CS_UOM_TP:
                break;
            case DModConsts.CS_ITM_TP:
                break;
            case DModConsts.CU_USR:
                break;
            case DModConsts.CU_USR_MOD:
                break;
            case DModConsts.CU_BPR:
                switch (subtype) {
                    case DModSysConsts.CS_BPR_TP_COM:
                        label = "Empresa";
                        break;
                    case DModSysConsts.CS_BPR_TP_CUS:
                        label = "Clientes";
                        break;
                    case DModSysConsts.CS_BPR_TP_SUP:
                        label = "Proveedores";
                        break;
                    default:
                        miClient.showMsgBoxError(DLibConsts.ERR_MSG_OPTION_UNKNOWN);
                }
                view = new DViewBizPartner(miClient, subtype, label);
                break;
            case DModConsts.CU_UOM:
                view = new DViewUnit(miClient, "Unidades");
                break;
            case DModConsts.CU_PRE:
                view = new DViewPresent(miClient, "Presentaciones");
                break;
            case DModConsts.CU_FAM:
                view = new DViewFamily(miClient, "Familias");
                break;
            case DModConsts.CU_ITM:
                switch (subtype) {
                    case DModSysConsts.CS_ITM_TP_RMI:
                        label = "Materiales directos insumo";
                        break;
                    case DModSysConsts.CS_ITM_TP_RMP:
                        label = "Materiales directos empaque";
                        break;
                    case DModSysConsts.CS_ITM_TP_MI:
                        label = "Materiales indirectos";
                        break;
                    case DModSysConsts.CS_ITM_TP_P:
                        label = "Productos";
                        break;
                    case DModSysConsts.CS_ITM_TP_PB:
                        label = "Productos base";
                        break;
                    case DModSysConsts.CS_ITM_TP_PF:
                        label = "Productos terminados";
                        break;
                    case DModSysConsts.CS_ITM_TP_BP:
                        label = "Subproductos";
                        break;
                    case DModSysConsts.CS_ITM_TP_SC:
                        label = "Desechos";
                        break;
                    case DModSysConsts.CS_ITM_TP_MO:
                        label = "GIF";
                        break;
                    default:
                        miClient.showMsgBoxError(DLibConsts.ERR_MSG_OPTION_UNKNOWN);
                }
                view = new DViewItem(miClient, subtype, label);
                break;
            case DModConsts.C_CFG:
                break;
            case DModConsts.C_YEA:
                view = new DViewYear(miClient, "Años");
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
            case DModConsts.CS_ACS_TP:
                break;
            case DModConsts.CS_PER_TP:
                break;
            case DModConsts.CS_BPR_TP:
                break;
            case DModConsts.CS_UOM_TP:
                break;
            case DModConsts.CS_ITM_TP:
                break;
            case DModConsts.CU_USR:
                break;
            case DModConsts.CU_USR_MOD:
                break;
            case DModConsts.CU_BPR:
                switch (subtype) {
                    case DModSysConsts.CS_BPR_TP_COM:
                        if (moFormBizPartnerCom == null) moFormBizPartnerCom = new DFormBizPartner(miClient, subtype, "Empresa");
                        form = moFormBizPartnerCom;
                        break;
                    case DModSysConsts.CS_BPR_TP_CUS:
                        if (moFormBizPartnerCus == null) moFormBizPartnerCus = new DFormBizPartner(miClient, subtype, "Cliente");
                        form = moFormBizPartnerCus;
                        break;
                    case DModSysConsts.CS_BPR_TP_SUP:
                        if (moFormBizPartnerSup == null) moFormBizPartnerSup = new DFormBizPartner(miClient, subtype, "Proveedor");
                        form = moFormBizPartnerSup;
                        break;
                    default:
                        miClient.showMsgBoxError(DLibConsts.ERR_MSG_OPTION_UNKNOWN);
                }
                break;
            case DModConsts.CU_UOM:
                if (moFormUnit == null) moFormUnit = new DFormUnit(miClient, "Unidad");
                form = moFormUnit;
                break;
            case DModConsts.CU_PRE:
                if (moFormPresent == null) moFormPresent = new DFormPresent(miClient, "Presentación");
                form = moFormPresent;
                break;
            case DModConsts.CU_FAM:
                if (moFormFamily == null) moFormFamily = new DFormFamily(miClient, "Familia ítems");
                form = moFormFamily;
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
                    case DModSysConsts.CS_ITM_TP_P:
                        if (moFormItemP == null) moFormItemP = new DFormItem(miClient, subtype, "Producto");
                        form = moFormItemP;
                        break;
                    case DModSysConsts.CS_ITM_TP_PB:
                        if (moFormItemPb == null) moFormItemPb = new DFormItem(miClient, subtype, "Producto base");
                        form = moFormItemPb;
                        break;
                    case DModSysConsts.CS_ITM_TP_PF:
                        if (moFormItemPf == null) moFormItemPf = new DFormItem(miClient, subtype, "Producto terminado");
                        form = moFormItemPf;
                        break;
                    case DModSysConsts.CS_ITM_TP_BP:
                        if (moFormItemBp == null) moFormItemBp = new DFormItem(miClient, subtype, "Subproducto");
                        form = moFormItemBp;
                        break;
                    case DModSysConsts.CS_ITM_TP_SC:
                        if (moFormItemSc == null) moFormItemSc = new DFormItem(miClient, subtype, "Desecho");
                        form = moFormItemSc;
                        break;
                    case DModSysConsts.CS_ITM_TP_MO:
                        if (moFormItemMo == null) moFormItemMo = new DFormItem(miClient, subtype, "GIF");
                        form = moFormItemMo;
                        break;
                    default:
                        miClient.showMsgBoxError(DLibConsts.ERR_MSG_OPTION_UNKNOWN);
                }
                break;
            case DModConsts.C_CFG:
                break;
            case DModConsts.C_YEA:
                if (moFormYear == null) moFormYear = new DFormYear(miClient, "Año");
                form = moFormYear;
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
