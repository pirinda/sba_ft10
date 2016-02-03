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
    public static final int CS_USR_TP = 111101;
    public static final int CS_ACS_TP = 111106;
    public static final int CS_PER_TP = 111201;
    public static final int CS_BPR_TP = 111206;
    public static final int CS_UOM_TP = 111301;
    public static final int CS_ITM_TP = 111316;

    public static final int CU_USR = 112101;
    public static final int CU_USR_MOD = 112106;
    public static final int CU_BPR = 112201;
    public static final int CU_UOM = 112301;
    public static final int CU_PRE = 112306;
    public static final int CU_FAM = 112311;
    public static final int CU_ITM = 112316;

    public static final int C_CFG = 113001;
    public static final int C_YEA = 113101;
    public static final int C_USR_GUI = 113201;

    public static final int CX_ITM_TP_PRO = 115316;
    public static final int CX_ITM_TP_CMP = 115317;
    public static final int CX_ITM_BY_ITM_TP = 115318;
    
    public static final int SS_WHS_TP = 111001;
    public static final int SS_MOV_CL = 111101;
    public static final int SS_MOV_TP = 111106;
    public static final int SS_TRN_TP = 111201;
    public static final int SS_ADJ_TP = 111206;

    public static final int SU_WHS = 112001;

    public static final int S_WSD = 113001;
    public static final int S_WSD_ROW = 113101;
    public static final int S_STK = 113201;

    public static final int MS_FRM_TP = 211041;
    public static final int MS_JOB_TP = 211061;
    public static final int MS_JOB_ST = 211062;

    public static final int MU_DPT = 212011;
    public static final int MU_LIN = 212021;
    public static final int MU_FRM = 212041;
    public static final int MU_FRM_CMP_FAM = 212046;
    public static final int MU_FRM_CMP_ITM = 212047;
    public static final int MU_FRM_BYP = 212049;
    public static final int MU_VAR = 212051;
    public static final int MU_VAR_FAM = 212052;

    public static final int M_JOB = 213061;
    public static final int M_JOB_REQ_FAM = 213066;
    public static final int M_JOB_REQ_ITM = 213067;
    public static final int M_JOB_CON = 213071;
    public static final int M_JOB_MFG = 213076;
    public static final int M_JOB_VAR = 213081;

    public static final int QS_RES_TP = 311011;

    public static final int QU_PAR = 312011;
    public static final int QU_TST = 312021;
    public static final int QU_TST_PAR = 312022;
    public static final int QU_TST_FAM = 312026;

    public static final int Q_APP = 313021;
    public static final int Q_APP_RES = 313026;

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

        TablesMap.put(CU_USR, "cu_usr");
        TablesMap.put(CU_USR_MOD, "cu_usr_mod");
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
        TablesMap.put(SS_ADJ_TP, "ss_adj_tp");

        TablesMap.put(SU_WHS, "su_whs");

        TablesMap.put(S_WSD, "s_wsd");
        TablesMap.put(S_WSD_ROW, "s_wsd_row");
        TablesMap.put(S_STK, "s_stk");

        TablesMap.put(MS_FRM_TP, "ms_frm_tp");
        TablesMap.put(MS_JOB_TP, "ms_job_tp");
        TablesMap.put(MS_JOB_ST, "ms_job_st");

        TablesMap.put(MU_DPT, "mu_dpt");
        TablesMap.put(MU_LIN, "mu_lin");
        TablesMap.put(MU_FRM, "mu_frm");
        TablesMap.put(MU_FRM_CMP_FAM, "mu_frm_cmp_fam");
        TablesMap.put(MU_FRM_CMP_ITM, "mu_frm_cmp_itm");
        TablesMap.put(MU_FRM_BYP, "mu_frm_byp");
        TablesMap.put(MU_VAR, "mu_var");
        TablesMap.put(MU_VAR_FAM, "mu_var_fam");

        TablesMap.put(M_JOB, "m_job");
        TablesMap.put(M_JOB_REQ_FAM, "m_job_req_fam");
        TablesMap.put(M_JOB_REQ_ITM, "m_job_req_itm");
        TablesMap.put(M_JOB_CON, "m_job_con");
        TablesMap.put(M_JOB_MFG, "m_job_mfg");
        TablesMap.put(M_JOB_VAR, "m_job_var");

        TablesMap.put(QS_RES_TP, "qs_res_tp");

        TablesMap.put(QU_PAR, "qu_par");
        TablesMap.put(QU_TST, "qu_tst");
        TablesMap.put(QU_TST_PAR, "qu_tst_par");
        TablesMap.put(QU_TST_FAM, "qu_tst_fam");

        TablesMap.put(Q_APP, "q_app");
        TablesMap.put(Q_APP_RES, "q_app_res");
    }
}
