/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod;

import java.util.HashMap;

/**
 *
 * @author Sergio Flores
 */
public abstract class DModConsts {

    public static final int SU_SYS = 110001;
    public static final int SU_COM = 110002;

    public static final int CS_MOD = 111001;
    public static final int CS_USR_TP = 111111;
    public static final int CS_ACS_TP = 111116;
    public static final int CS_PER_TP = 111201;
    public static final int CS_BPR_TP = 111206;
    public static final int CS_UOM_TP = 111311;
    public static final int CS_ITM_TP = 111326;

    public static final int CU_UPR = 112101;
    public static final int CU_UPR_ACS = 112102;
    public static final int CU_USR = 112111;
    public static final int CU_USR_UPR = 112112;
    public static final int CU_BPR = 112206;
    public static final int CU_UOM = 112311;
    public static final int CU_PRE = 112316;
    public static final int CU_FAM = 112321;
    public static final int CU_ITM = 112326;

    public static final int C_CFG = 113001;
    public static final int C_YEA = 113011;
    public static final int C_USR_GUI = 113101;

    public static final int CX_ITM_FK_ITM_TP = 115326;
    public static final int CX_ITM_FK_FAM = 115327;
    public static final int CX_ITM_TP_PRO_MFG = 115331;
    public static final int CX_ITM_TP_PRO_MFG_ALL = 115332;
    public static final int CX_ITM_TP_CMP = 115336;

    public static final int SS_WHS_TP = 211001;
    public static final int SS_MOV_CL = 211101;
    public static final int SS_MOV_TP = 211106;
    public static final int SS_TRN_TP = 211201;
    public static final int SS_MFG_TP = 211211;
    public static final int SS_ADJ_TP = 211206;

    public static final int SU_WHS = 212001;

    public static final int S_WSD = 213101;
    public static final int S_WSD_ROW = 213106;
    public static final int S_STK = 213121;

    public static final int MS_FRM_TP = 311041;
    public static final int MS_CMP_TP = 311046;
    public static final int MS_CMP_INC_TP = 311047;
    public static final int MS_JOB_TP = 311061;
    public static final int MS_JOB_ST = 311066;

    public static final int MU_DPT = 312011;
    public static final int MU_LIN = 312021;
    public static final int MU_FRM = 312041;
    public static final int MU_FRM_CMP = 312046;
    public static final int MU_FRM_BYP = 312049;
    public static final int MU_VAR = 312051;
    public static final int MU_VAR_FAM = 312052;

    public static final int M_JOB = 313061;
    public static final int M_JOB_REQ = 313066;
    public static final int M_JOB_CON = 313071;
    public static final int M_JOB_MFG = 313076;
    public static final int M_JOB_VAR = 313081;

    public static final int QS_TST_TP = 411011;

    public static final int QU_VAR = 412011;
    public static final int QU_TST = 412021;
    public static final int QU_TST_VAR = 412022;
    public static final int QU_TST_FAM = 412026;

    public static final int Q_APP = 413021;
    public static final int Q_APP_RES = 413026;
    public static final int Q_APP_RES_VAR = 413027;

    public static final HashMap<Integer, String> TablesMap = new HashMap<Integer, String>();

    static {
        TablesMap.put(SU_SYS, "su_sys");
        TablesMap.put(SU_COM, "su_com");

        TablesMap.put(CS_MOD, "cs_mod");
        TablesMap.put(CS_USR_TP, "cs_usr_tp");
        TablesMap.put(CS_ACS_TP, "cs_acs_tp");
        TablesMap.put(CS_PER_TP, "cs_per_tp");
        TablesMap.put(CS_BPR_TP, "cs_bpr_tp");
        TablesMap.put(CS_UOM_TP, "cs_uom_tp");
        TablesMap.put(CS_ITM_TP, "cs_itm_tp");

        TablesMap.put(CU_UPR, "cu_upr");
        TablesMap.put(CU_UPR_ACS, "cu_upr_acs");
        TablesMap.put(CU_USR, "cu_usr");
        TablesMap.put(CU_USR_UPR, "cu_usr_upr");
        TablesMap.put(CU_BPR, "cu_bpr");
        TablesMap.put(CU_UOM, "cu_uom");
        TablesMap.put(CU_PRE, "cu_pre");
        TablesMap.put(CU_FAM, "cu_fam");
        TablesMap.put(CU_ITM, "cu_itm");

        TablesMap.put(C_CFG, "c_cfg");
        TablesMap.put(C_YEA, "c_yea");
        TablesMap.put(C_USR_GUI, "c_usr_gui");

        TablesMap.put(SS_WHS_TP, "ss_whs_tp");
        TablesMap.put(SS_MOV_CL, "ss_mov_cl");
        TablesMap.put(SS_MOV_TP, "ss_mov_tp");
        TablesMap.put(SS_TRN_TP, "ss_trn_tp");
        TablesMap.put(SS_MFG_TP, "ss_mfg_tp");
        TablesMap.put(SS_ADJ_TP, "ss_adj_tp");

        TablesMap.put(SU_WHS, "su_whs");

        TablesMap.put(S_WSD, "s_wsd");
        TablesMap.put(S_WSD_ROW, "s_wsd_row");
        TablesMap.put(S_STK, "s_stk");

        TablesMap.put(MS_FRM_TP, "ms_frm_tp");
        TablesMap.put(MS_CMP_TP, "ms_cmp_tp");
        TablesMap.put(MS_CMP_INC_TP, "ms_cmp_inc_tp");
        TablesMap.put(MS_JOB_TP, "ms_job_tp");
        TablesMap.put(MS_JOB_ST, "ms_job_st");

        TablesMap.put(MU_DPT, "mu_dpt");
        TablesMap.put(MU_LIN, "mu_lin");
        TablesMap.put(MU_FRM, "mu_frm");
        TablesMap.put(MU_FRM_CMP, "mu_frm_cmp");
        TablesMap.put(MU_FRM_BYP, "mu_frm_byp");
        TablesMap.put(MU_VAR, "mu_var");
        TablesMap.put(MU_VAR_FAM, "mu_var_fam");

        TablesMap.put(M_JOB, "m_job");
        TablesMap.put(M_JOB_REQ, "m_job_req");
        TablesMap.put(M_JOB_CON, "m_job_con");
        TablesMap.put(M_JOB_MFG, "m_job_mfg");
        TablesMap.put(M_JOB_VAR, "m_job_var");

        TablesMap.put(QS_TST_TP, "qs_tst_tp");

        TablesMap.put(QU_VAR, "qu_var");
        TablesMap.put(QU_TST, "qu_tst");
        TablesMap.put(QU_TST_VAR, "qu_tst_var");
        TablesMap.put(QU_TST_FAM, "qu_tst_fam");

        TablesMap.put(Q_APP, "q_app");
        TablesMap.put(Q_APP_RES, "q_app_res");
        TablesMap.put(Q_APP_RES_VAR, "q_app_res_var");
    }
}
