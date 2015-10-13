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
    public static final int CS_USR_TP = 111011;
    public static final int CS_UNT_TP = 111021;
    public static final int CS_ITM_TP = 111031;

    public static final int CU_USR = 112011;
    public static final int CU_USR_MOD = 112016;
    public static final int CU_UNT = 112021;
    public static final int CU_PRS = 112026;
    public static final int CU_FAM = 112031;
    public static final int CU_GRP = 112032;
    public static final int CU_ITM = 112036;

    public static final int C_CFG = 113001;
    public static final int C_USR_GUI = 113101;

    public static final int CX_ITM_TP_PRO = 115001;     // products
    public static final int CX_ITM_TP_CMP = 115002;     // components
    public static final int CX_ITM_BY_ITM_TP = 115006;
    
    public static final int MS_REF_TP = 211021;
    public static final int MS_FRM_TP = 211041;
    public static final int MS_JOB_TP = 211051;
    public static final int MS_JOB_ST = 211052;

    public static final int MU_DPT = 212011;
    public static final int MU_LIN_PCK = 212021;
    public static final int MU_LIN_PCK_PRS = 212022;
    public static final int MU_LIN_PRP = 212031;
    public static final int MU_LIN_PRP_FAM = 212032;
    public static final int MU_LIN_PRP_LIN_PCK = 212033;
    public static final int MU_FRM = 212041;
    public static final int MU_FRM_CMP = 212042;

    public static final int M_YER = 213011;
    public static final int M_YER_WEK = 213012;
    public static final int M_JOB = 213051;
    public static final int M_JOB_PRP = 213061;
    public static final int M_JOB_PRP_REQ = 213062;
    public static final int M_JOB_PRP_CON = 213063;
    public static final int M_JOB_PRP_MFG = 213064;
    public static final int M_JOB_PCK = 213071;
    public static final int M_JOB_PCK_REQ = 213072;
    public static final int M_JOB_PCK_CON = 213073;
    public static final int M_JOB_PCK_MFG = 213074;
    
    public static final int MX_LIN_PRP_BY_FAM = 215011;
    public static final int MX_LIN_PCK_BY_LIN_PRP = 215012;
    public static final int MX_PF_BY_PB_N_LIN_PCK = 215016;
    public static final int MX_FRM_BY_ITM = 215021;

    public static final int QS_PAR_TP = 311011;

    public static final int QU_PAR = 312011;
    public static final int QU_TST_CL = 312021;
    public static final int QU_TST_TP = 312022;
    public static final int QU_TST_TP_PAR = 312023;

    public static final int Q_TST = 313021;

    public static final int OS_WHS_TP = 411051;

    public static final int OU_WHS = 412051;

    public static final int O_STK = 413051;

    public static final HashMap<Integer, String> TablesMap = new HashMap<Integer, String>();

    static {
        TablesMap.put(SU_SYS, "su_sys");
        TablesMap.put(SU_COM, "su_com");

        TablesMap.put(CS_MOD, "cs_mod");
        TablesMap.put(CS_USR_TP, "cs_usr_tp");
        TablesMap.put(CS_UNT_TP, "cs_unt_tp");
        TablesMap.put(CS_ITM_TP, "cs_itm_tp");

        TablesMap.put(CU_USR, "cu_usr");
        TablesMap.put(CU_USR_MOD, "cu_usr_mod");
        TablesMap.put(CU_UNT, "cu_unt");
        TablesMap.put(CU_PRS, "cu_prs");
        TablesMap.put(CU_FAM, "cu_fam");
        TablesMap.put(CU_GRP, "cu_grp");
        TablesMap.put(CU_ITM, "cu_itm");

        TablesMap.put(C_CFG, "c_cfg");
        TablesMap.put(C_USR_GUI, "c_usr_gui");

        TablesMap.put(MS_REF_TP, "ms_ref_tp");
        TablesMap.put(MS_FRM_TP, "ms_frm_tp");
        TablesMap.put(MS_JOB_TP, "ms_job_tp");
        TablesMap.put(MS_JOB_ST, "ms_job_st");

        TablesMap.put(MU_DPT, "mu_dpt");
        TablesMap.put(MU_LIN_PCK, "mu_lin_pck");
        TablesMap.put(MU_LIN_PCK_PRS, "mu_lin_pck_prs");
        TablesMap.put(MU_LIN_PRP, "mu_lin_prp");
        TablesMap.put(MU_LIN_PRP_FAM, "mu_lin_prp_fam");
        TablesMap.put(MU_LIN_PRP_LIN_PCK, "mu_lin_prp_lin_pck");
        TablesMap.put(MU_FRM, "mu_frm");
        TablesMap.put(MU_FRM_CMP, "mu_frm_cmp");

        TablesMap.put(M_YER, "m_yer");
        TablesMap.put(M_YER_WEK, "m_yer_wek");
        TablesMap.put(M_JOB, "m_job");
        TablesMap.put(M_JOB_PRP, "m_job_prp");
        TablesMap.put(M_JOB_PRP_REQ, "m_job_prp_req");
        TablesMap.put(M_JOB_PRP_CON, "m_job_prp_con");
        TablesMap.put(M_JOB_PRP_MFG, "m_job_prp_mfg");
        TablesMap.put(M_JOB_PCK, "m_job_pck");
        TablesMap.put(M_JOB_PCK_REQ, "m_job_pck_req");
        TablesMap.put(M_JOB_PCK_CON, "m_job_pck_con");
        TablesMap.put(M_JOB_PCK_MFG, "m_job_pck_mfg");

        TablesMap.put(QS_PAR_TP, "qs_par_tp");

        TablesMap.put(QU_PAR, "qu_par");
        TablesMap.put(QU_TST_CL, "qu_tst_cl");
        TablesMap.put(QU_TST_TP, "qu_tst_tp");
        TablesMap.put(QU_TST_TP_PAR, "qu_tst_tp_par");

        TablesMap.put(Q_TST, "q_tst");

        TablesMap.put(OS_WHS_TP, "os_whs_tp");

        TablesMap.put(OU_WHS, "ou_whs");

        TablesMap.put(O_STK, "o_stk");
    }
}
