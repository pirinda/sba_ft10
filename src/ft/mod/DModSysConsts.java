/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ft.mod;

/**
 *
 * @author Sergio Flores
 */
public abstract class DModSysConsts {

    public static final int CX_PAC_CFG = 100000;    // package configuration
    public static final int CX_PAC_STK = 200000;    // package stock
    public static final int CX_PAC_MFG = 300000;    // package manufacturing
    public static final int CX_PAC_QTY = 400000;    // package quality
    
    public static final int CS_MOD_CFG = 1;
    public static final int CS_MOD_FRM = 2;
    public static final int CS_MOD_MFG = 3;
    public static final int CS_MOD_QTY = 4;
    public static final int CS_MOD_STK = 5;
    public static final int CS_MOD_PUR = 6;
    public static final int CS_MOD_SAL = 7;

    public static final int CS_USR_TP_USR = 1;
    public static final int CS_USR_TP_ADM = 2;
    public static final int CS_USR_TP_SUP = 3;

    public static final int CS_ACS_TP_REA = 1;
    public static final int CS_ACS_TP_AUT = 2;
    public static final int CS_ACS_TP_EDI = 3;
    public static final int CS_ACS_TP_ADM = 4;

    public static final int CS_PER_TP_PER = 1;
    public static final int CS_PER_TP_ORG = 2;

    public static final int CS_BPR_TP_COM = 1;
    public static final int CS_BPR_TP_CUS = 11;
    public static final int CS_BPR_TP_SUP = 16;

    public static final int CS_UOM_TP_NA = 1;
    public static final int CS_UOM_TP_QTY = 2;
    public static final int CS_UOM_TP_VOL = 3;
    public static final int CS_UOM_TP_MSS = 4;

    public static final int CS_ITM_TP_NA = 1;
    public static final int CS_ITM_TP_RMI = 11;
    public static final int CS_ITM_TP_RMP = 12;
    public static final int CS_ITM_TP_MI = 16;
    public static final int CS_ITM_TP_P = 21;
    public static final int CS_ITM_TP_PB = 23;
    public static final int CS_ITM_TP_PF = 25;
    public static final int CS_ITM_TP_BP = 27;
    public static final int CS_ITM_TP_SC = 29;
    public static final int CS_ITM_TP_MO = 31;

    public static final int CU_UPR_CFG = 1;
    public static final int CU_UPR_FRM = 2;
    public static final int CU_UPR_MFG = 3;
    public static final int CU_UPR_QTY = 4;
    public static final int CU_UPR_STK = 5;
    public static final int CU_UPR_PUR = 6;
    public static final int CU_UPR_SAL = 7;
    public static final int CU_UPR_COO = 11;
    public static final int CU_UPR_CEO = 19;

    public static final int CU_USR_NA = 1;
    public static final int CU_USR_ADM = 2;
    public static final int CU_USR_SUP = 3;

    public static final int CU_BPR_COM = 1;

    public static final int CU_UOM_NA = 1;
    public static final int CU_UOM_PCE = 21;
    public static final int CU_UOM_TEN = 22;
    public static final int CU_UOM_HUN = 23;
    public static final int CU_UOM_THO = 24;
    public static final int CU_UOM_ML = 31;
    public static final int CU_UOM_L = 32;
    public static final int CU_UOM_KL = 33;
    public static final int CU_UOM_MG = 41;
    public static final int CU_UOM_G = 42;
    public static final int CU_UOM_KG = 43;
    public static final int CU_UOM_TON = 44;

    public static final int CU_PRE_NA = 1;

    public static final int CU_FAM_ND = 0;
    
    public static final int CU_ITM_ND = 0;
    
    public static final int C_CFG_CFG = 1;
    
    public static final int SS_WHS_TP_MAT = 1;
    public static final int SS_WHS_TP_PRO = 2;

    public static final int SS_MOV_CL_IN = 1;
    public static final int SS_MOV_CL_OUT = 2;

    public static final int[] SS_MOV_TP_IN_SAL = new int[] { 1, 1 };
    public static final int[] SS_MOV_TP_IN_PUR = new int[] { 1, 2 };
    public static final int[] SS_MOV_TP_IN_MFG = new int[] { 1, 3 };
    public static final int[] SS_MOV_TP_IN_ADJ = new int[] { 1, 4 };
    public static final int[] SS_MOV_TP_IN_TRN = new int[] { 1, 5 };
    public static final int[] SS_MOV_TP_IN_CNV = new int[] { 1, 6 };
    public static final int[] SS_MOV_TP_OUT_SAL = new int[] { 2, 1 };
    public static final int[] SS_MOV_TP_OUT_PUR = new int[] { 2, 2 };
    public static final int[] SS_MOV_TP_OUT_MFG = new int[] { 2, 3 };
    public static final int[] SS_MOV_TP_OUT_ADJ = new int[] { 2, 4 };
    public static final int[] SS_MOV_TP_OUT_TRN = new int[] { 2, 5 };
    public static final int[] SS_MOV_TP_OUT_CNV = new int[] { 2, 6 };

    public static final int SX_MOV_TP_SAL = 1;
    public static final int SX_MOV_TP_PUR = 2;
    public static final int SX_MOV_TP_MFG = 3;
    public static final int SX_MOV_TP_ADJ = 4;
    public static final int SX_MOV_TP_TRN = 5;
    public static final int SX_MOV_TP_CNV = 6;
    
    public static final int SS_TRN_TP_NA = 1;
    public static final int SS_TRN_TP_SR = 2;
    public static final int SS_TRN_TP_CHA = 3;
    public static final int SS_TRN_TP_WAR = 4;
    public static final int SS_TRN_TP_CON = 5;

    public static final int SS_MFG_TP_NA = 1;
    public static final int SS_MFG_TP_MAT = 2;
    public static final int SS_MFG_TP_PRO = 3;
    
    public static final int SS_ADJ_TP_NA = 1;
    public static final int SS_ADJ_TP_DAM = 2;
    public static final int SS_ADJ_TP_EXP = 3;
    public static final int SS_ADJ_TP_MIS = 4;
    public static final int SS_ADJ_TP_STK = 5;
    public static final int SS_ADJ_TP_PRO = 6;
    public static final int SS_ADJ_TP_DIS = 7;

    public static final int SU_WHS_MAT = 1;
    public static final int SU_WHS_PRO = 2;

    public static final int SX_STK = 1;
    public static final int SX_STK_LOT = 2;
    
    public static final int MS_FRM_TP_STD = 1;
    public static final int MS_FRM_TP_VAR = 2;

    public static final int MS_CMP_TP_ITM = 1;
    public static final int MS_CMP_TP_FAM = 2;

    public static final int MS_CMP_INC_TP_SNG_REQ = 11;
    public static final int MS_CMP_INC_TP_SNG_OPT = 12;
    public static final int MS_CMP_INC_TP_EXC_REQ = 21;
    public static final int MS_CMP_INC_TP_EXC_OPT = 22;
    
    public static final int MS_JOB_TP_SMP = 1;
    public static final int MS_JOB_TP_CMP = 2;

    public static final int MS_JOB_ST_NEW = 1;
    public static final int MS_JOB_ST_PRC = 3;
    public static final int MS_JOB_ST_QTY = 5;
    public static final int MS_JOB_ST_FIN = 9;

    public static final int MU_DPT_DEF = 1;

    public static final int MU_LIN_DEF = 1;

    public static final int QS_TST_TP_INP = 1;
    public static final int QS_TST_TP_PRO = 2;
}
