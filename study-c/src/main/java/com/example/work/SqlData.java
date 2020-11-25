package com.example.work;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;

public class SqlData {

    public static HashMap<String, ArrayList<String>> getSqlData() {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        int a=5;
        String startDate="2018-07-01";
        String endDate="2018-08-31";
        if(a>0){

            //案件基本信息
            map.put("select ('000085'||to_char(SYSDATE,'YYYYMMDD')||lpad(ROWNUM, 12, '0')) ,'000085' ,a.caseno ,a.caseno ,'',a.customerno,'',b.AppDate,a.RgtDate," +
                            "'','','','','','','','','',(select DOCID from LLSTORE where caseno='Relation' and LPNAME=b.Relation),b.RgtantName," +
                            "(select LPNAME from LISUSER.LLSTORE where DOCID='IdType' and  CASENO=b.IDType),b.IDNo,b.RgtantPhone,b.RgtantAddress," +
                            "(select accdate from LISUSER.LLSUBREPORT where SUBRPTNO=(select SUBRPTNO from LISUSER.LLCASERELA where CASENO=a.CASENO and ROWNUM=1))," +
                            "(select accidenttype from LISUSER.LLSUBREPORT where SUBRPTNO=(select SUBRPTNO from LISUSER.LLCASERELA where CASENO=a.CASENO and ROWNUM=1))," +
                            "(select TRANSLATE (AccPlace,CHR (13) || CHR (10),'，') from LISUSER.LLSUBREPORT where SUBRPTNO=(select SUBRPTNO from LISUSER.LLCASERELA where CASENO=a.CASENO and ROWNUM=1))," +
                            "(select TRANSLATE (AccDesc,CHR (13) || CHR (10),'，') from LISUSER.LLSUBREPORT where SUBRPTNO=(select SUBRPTNO from LISUSER.LLCASERELA where CASENO=a.CASENO and ROWNUM=1))," +
                            "(SELECT LPNAME FROM LISUSER.LLSTORE WHERE DOCID='LLAccident' AND CASENO= (select code from LISUSER.LLACCIDENT where CASENO=a.CASENO and CASERELANO=(select CASERELANO from LISUSER.LLCASERELA where CASENO=a.CASENO and ROWNUM=1)and ROWNUM=1))," +
                            "a.SurveyFlag,'','',(select checkDecision2 from LISUSER.LLClaimUWMain where CASENO=a.CASENO  AND ROWNUM=1),a.SignerDate,(select TRANSLATE (Remark2,CHR (13) || CHR (10),'，') from LISUSER.LLClaimUWMain where CASENO=a.CASENO and RGTNO=a.RGTNO AND ROWNUM=1)," +
                            "a.EndCaseDate,'',CASE WHEN (select DISTINCT 1 from LISUSER.LLAPPCLAIMREASON where caseno=a.CASENO and rgtno=a.RGTNO and ReasonCode='05')='1' THEN '1' ELSE '0' END ," +
                            "a.DeathDate,'',(select DeformityGrade from LISUSER.LLCaseInfo where caseno=a.caseno and CASERELANO=(select CASERELANO from LISUSER.LLCASERELA where CASENO=a.CASENO and ROWNUM=1)and ROWNUM=1 )," +
                            "a.MngCom,'',CASE WHEN (select DISTINCT 1 from LISUSER.lmriskapp where RISKCODE in (select RISKCODE from LISUSER.LLCLAIMDETAIL where caseno=a.CASENO) and  risktype='A' )='1' THEN '1' ELSE '0' END " +
                            " from LISUSER.llcase a, LISUSER.LLREGISTER b" +
                            " where a.rgtno=b.rgtno and  a.RGTSTATE in ('11','12')" +
                            " and a.endcasedate between  to_date('"+startDate+"','YYYY-MM-dd') and to_date('"+endDate+"','YYYY-MM-dd') ",
                    Lists.newArrayList("LLCase_11", "保险机构代码","立案号","赔案号","报案号","出险人客户编号","报案日期","申请日期","立案日期","报案方式代码","报案人客户编号","报案人与被保险人关系代码","报案人姓名","报案人证件类别代码","报案人证件号码","报案人联系电话","报案人联系地址","申请人客户编号","申请人与被保险人关系代码","申请人姓名","申请人证件类别代码","申请人证件号码","申请人联系电话","申请人联系地址","出险日期","出险原因分类代码","具体出险地点","事故经过","损伤外部原因代码","理赔调查标志","立案标志","不立案原因","审核通过标志","审核通过日期","审核意见","结案日期","案件类型","死亡标志","死亡日期","死亡原因代码","伤残代码","管理机构代码","是否为逃逸案 ","是否意外险案件"
                    ));

            //医疗收据信息
            map.put("select ('000085'||to_char(SYSDATE,'YYYYMMDD')||lpad(ROWNUM, 12, '0')) ,'000085',b.caseno,b.caseno,a.MainFeeNo,'',a.MedicareType, " +
                            "(select GETMONEY from LLSTORE where CASENO='FeeType' and lpname= a.FeeType),'',a.HospitalCode,TRANSLATE (a.HospitalName,CHR (13) || CHR (10),'，'), " +
                            "(select DOCID from LLSTORE where CASENO='hosgrade' and lpname=a.HosGrade),a.HospStartDate,a.HospEndDate,a.RealHospDate, " +
                            "a.SeriousWard,a.SumFee,(select sum(feemoney) from LISUSER.LLFeeOtherItem where LLFeeOtherItem.itemcode='BJ301' and  MAINFEENO=a.MAINFEENO  AND CASENO=A.CASENO AND RGTNO=A.RGTNO), " +
                            "'','','','',a.SelfAmnt,'','','','','','',(select sum(feemoney) from LISUSER.LLFeeOtherItem where LLFeeOtherItem.itemcode='318' and  MAINFEENO=a.MAINFEENO  AND CASENO=A.CASENO AND RGTNO=A.RGTNO), " +
                            "case when(select DISTINCT 1 from LISUSER.LLCASECURE where CASENO=b.CASENO and OperationFlag='1')='1' then '1' else '0' END,case when a.FeeAtti='03' then '1' else '0' end  , " +
                            "'','','','' from LISUSER.LLFEEMAIN  a,LISUSER.llcase b where a.CASENO=b.CASENO and b.rgtstate in ('11','12') " +
                            "and b.endcasedate between  to_date('"+startDate+"','YYYY-MM-dd') and to_date('"+endDate+"','YYYY-MM-dd') ",
                    Lists.newArrayList("LLReceipt_12","保险机构代码", "立案号", "赔案号", "收据编号", "收据编号类型代码", "医保类型代码", "收据医疗类型代码", "医院性质代码", "医院代码", "医院名称", "医院级别", "住院日期", "出院日期", "实际住院天数", "重症监护天数", "费用金额", "医疗保险范围内金额", "个人支付金额", "其中起付金额", "其中超大额封顶金额", "乙类自付金额", "自费金额", "个人账户支付金额", "医疗保险基金支付金额", "其中门诊大额支付金额", "新农合标志", "新农合报销金额", "第三方给付标志", "第三方给付金额", "手术标志", "门诊特殊病标志", "执行医生执业编码", "医生名称", "科室编码", "科室名称"
                    ));

            //医疗费用明细信息
            map.put("select  ('000085'||to_char(SYSDATE,'YYYYMMDD')||lpad(ROWNUM, 12, '0')) ,'000085',a.caseno,a.caseno,b.Mainfeeno,(select lpname from LISUSER.LLSTORE where docid='feeitem' and caseno= c.FeeItemCode), " +
                            "'','',c.FeeItemCode,c.FeeItemName,'','','','',c.Fee,'',(select Amount from LISUSER.LLCaseChargeDetail where MAINFEENO=b.MAINFEENO and ROWNUM=1), " +
                            "(select UnitPrice from LISUSER.LLCaseChargeDetail where MAINFEENO=b.MAINFEENO and ROWNUM=1),'','','','','','','','' " +
                            "from LISUSER.llcase a , LISUSER.LLFeeMain b ,LISUSER.LLCaseReceipt c " +
                            "where a.caseno=b.CASENO and b.CASENO=c.CASENO and a.rgtstate in ('11','12')" +
                            "and a.endcasedate between  to_date('"+startDate+"','YYYY-MM-dd') and to_date('"+endDate+"','YYYY-MM-dd')   ",
                    Lists.newArrayList("LLMedicalFeeDetail_13","保险机构代码", "立案号", "赔案号", "收据编号", "医疗费用类型代码", "子公司医疗费用类型代码", "子公司医疗费用类型名称", "明细费用编码", "明细费用商品名称", "明细费用化学名称", "明细费用项目之拼音码", "明细费用项目之五笔码", "收费项目等级代码", "费用金额", "费用扣减金额", "数量", "单价", "计价单位", "规格", "剂型", "用法", "给药途径", "用量", "频次", "用药天数"
                    ));

            //疾病诊断信息
            map.put("select ('000085'||to_char(SYSDATE,'YYYYMMDD')||lpad(ROWNUM, 12, '0')) ,'000085',a.caseno,a.caseno," +
                            "case when b.seriousflag='0' then TRANSLATE (b.DiseaseCode,CHR (13) || CHR (10),'，') else '' end ," +
                            "case when b.seriousflag='0' then TRANSLATE (b.DiseaseName,CHR (13) || CHR (10),'，') else '' end ," +
                            "'', b.HospitalCode,TRANSLATE (b.HospitalName,CHR (13) || CHR (10),'，')," +
                            "case when b.seriousflag='1' then TRANSLATE (b.DiseaseCode,CHR (13) || CHR (10),'，') else '' end ," +
                            "case when b.seriousflag='1' then TRANSLATE (b.DiseaseName,CHR (13) || CHR (10),'，') else '' end " +
                            "FROM  LISUSER.llcase a ,LISUSER.llcasecure b " +
                            "where a.caseno=b.CASENO and a.RGTSTATE in ('11','12')" +
                            "and a.endcasedate between  to_date('"+startDate+"','YYYY-MM-dd') and to_date('"+endDate+"','YYYY-MM-dd')  " ,
                    Lists.newArrayList("LLDisease_14", "保险机构代码","立案号","赔案号","疾病代码","疾病名称","诊断类型代码","医院代码","医院名称","重大疾病代码 ","重大疾病名称"
                    ));

            //手术信息
            map.put("select ('000085'||to_char(SYSDATE,'YYYYMMDD')||lpad(ROWNUM, 12, '0')) ,'000085',a.caseno,a.caseno,b.HospitalCode,b.HospitalName,c.OperationCode,c.OperationName,'' " +
                            "from LISUSER.LLCASE a ,LISUSER.llfeemain b ,LISUSER.LLOPERATION c " +
                            "where  a.CASENO=b.CASENO and b.CASENO=c.CASENO and b.CASERELANO=c.CASERELANO and a.rgtstate in ('11','12') " +
                            "and a.endcasedate between  to_date('"+startDate+"','YYYY-MM-dd') and to_date('"+endDate+"','YYYY-MM-dd') ",
                    Lists.newArrayList("LLOperation_15","保险机构代码","立案号","赔案号","医院代码","医院名称","手术代码","手术名称","诊断类型代码"
                    ));

            //赔案信息
            map.put("select ('000085'||to_char(SYSDATE,'YYYYMMDD')||lpad(ROWNUM, 12, '0')) ,'000085',a.caseno,a.caseno,c.contno,b.ClmState,b.StandPay, " +
                            "b.RealPay,(select docid from llstore where CASENO='givetype' and lpname= b.GiveType),(select CONTNO from llstore where CASENO='givetype'and lpname= b.GiveType), " +
                            "a.EndCaseDate,b.CasePayType,(select getmoney from llstore where CASENO='checktype'and lpname= b.GiveType) " +
                            "from LISUSER.LLCASE a,LISUSER.LLCLAIM b,LISUSER.LLCLAIMDETAIL c " +
                            "where a.CASENO=b.CASENO and b.CASENO=c.CASENO and a.rgtstate in ('11','12') " +
                            "and a.endcasedate between  to_date('"+startDate+"','YYYY-MM-dd') and to_date('"+endDate+"','YYYY-MM-dd') ",
                    Lists.newArrayList("LLClaim_16","保险机构代码","立案号","赔案号","保单号","赔案状态","核算赔付金额","核赔赔付金额","赔付结论","赔付结论描述","结案日期","案件给付类型","审核类型"
                    ));

            //赔案明细信息
            map.put(" select ('000085'||to_char(SYSDATE,'YYYYMMDD')||lpad(ROWNUM, 12, '0')), '000085', LLClaimDetail.CaseNo, LLClaimDetail.CaseNo, LLClaimDetail.ContNo, " +
                            "LLClaimDetail.KindCode, LLClaimDetail.RiskCode, LLClaimDetail.RiskVer, LLClaimDetail.GetDutyCode, llstore.LPNAME, LLClaimDetail.STANDPAY, " +
                            "LLClaimDetail.REALPAY, LLClaimDetail.DECLINEAMNT, (SELECT abs(LLPrepaidTrace.money) FROM lisuser.LLPrepaidTrace LLPrepaidTrace where llcase.CASENO=LLPrepaidTrace.OTHERNO), LLClaimDetail.APPROVEAMNT, " +
                            "LLClaimDetail.AGREEAMNT, '', llcase.EndCaseDate, LLClaimDetail.MNGCOM, (SELECT LDCOM.name FROM lisuser.LDCOM WHERE LLClaimDetail.MNGCOM=LDcom.comcode), LLClaimDetail.OUTDUTYAMNT, " +
                            "LLClaimDetail.OutDutyRate, CASE LLClaimDetail.GiveType WHEN '5' THEN LLClaimDetail.GiveReason ELSE '' end,CASE LLClaimDetail.GiveType WHEN '4'  THEN LLClaimDetail.GiveReason ELSE '' end,''  " +
                            "FROM lisuser.LLClaimDetail LLClaimDetail JOIN lisuser.LLCase llcase ON llcase.CASENO=LLClaimDetail.CASENO " +
                            "LEFT JOIN lisuser.llstore llstore ON llstore.DOCID='GetDuty' AND llstore.CASENO=LLClaimDetail.GetDutyKind " +
                            "WHERE llcase.endcasedate BETWEEN date'"+startDate+"' AND date'"+endDate+"' and  llcase.rgtstate in ('11','12')",
                    Lists.newArrayList("LLClaimDuty_17","交易编码","保险机构代码", "立案号", "赔案号", "保单号", "险类代码", "险种代码", "险种版本号", "给付责任编码", "给付责任类型", "核算赔付金额", "核赔赔付金额", "拒赔金额", "预付金额", "通融给付金额合计", "协议给付合计", "未决赔款金额", "结案日期", "核赔机构代码", "核赔机构名称", "免赔额", "免赔比例", "通融给付原因代码", "协议给付原因代码", "条款编码"
                    ));

            //理赔受益人信息
            map.put("SELECT ('000085'||to_char(SYSDATE,'YYYYMMDD')||lpad(ROWNUM, 12, '0')),'000085',LLBnf.CaseNo,LLBnf.INSUREDNO,CASE LLBnf.BNFTYPE WHEN '0' THEN '2' ELSE LLBnf.BNFTYPE END ,LLBnf.BNFGRADE,  LLBnf.RELATIONTOINSURED, " +
                            "LLBnf.name,CASE LLBnf.sex WHEN '0' THEN '1'  WHEN '1' THEN '2' WHEN '2' THEN '0' END , LLBnf.BIRTHDAY,llstore.LPNAME,LLBnf.IDNO,LLBnf.BNFLOT,LLBnf.GETMONEY " +
                            "FROM lisuser.llcase llcase JOIN lisuser.LLBnf LLBnf ON LLBnf.caseno =llcase.CASENO LEFT JOIN lisuser.llstore llstore ON llstore.DOCID='IdType' AND llstore.CASENO=LLBnf.IDTYPE " +
                            "WHERE llcase.endcasedate BETWEEN date'"+startDate+"' AND date'"+endDate+"' AND llcase.rgtstate in ('11','12') ",
                    Lists.newArrayList("LLBnf_18","交易编码","保险机构代码", "赔案号", "被保人客户编号", "受益人类别代码", "受益顺序代码", "受益人与被保人关系代码", "受益人姓名", "受益人性别代码", "受益人出生日期", "受益人证件类型代码", "受益人证件号码", "受益比例", "受益金额"
                    ));

            //理赔调查信息
            map.put("SELECT  ('000085'||to_char(SYSDATE,'YYYYMMDD')||lpad(ROWNUM, 12, '0')), '000085', LLSurvey.OtherNo, LLSurvey.OtherNo, LLSurvey.SURVEYNO, " +
                            "LLSurvey.CustomerNo, LLSurvey.CustomerName, nvl(LLSurvey.SURVEYCLASS,2), '', '',TRANSLATE(LLSurvey.SurveyRDesc ,chr(13)||chr(10) , '，'), TRANSLATE(LLSurvey.Content ,chr(13)||chr(10) , '，'), " +
                            "TRANSLATE(LLSurvey.RESULT , chr(13)||chr(10),'，'),LLSurvey.SurveyStartDate, LLSurvey.SurveyEndDate, LLSurvey.SurveyFlag, " +
                            "TRANSLATE(LLSurvey.AccidentDesc , chr(13)||chr(10) , '，') , TRANSLATE(LLSurvey.OHresult ,chr(13)||chr(10) , '，'), LLInqApply.InqDept, LLInqApply.LocFlag " +
                            "FROM LISUSER.llcase c  JOIN LISUSER.LLSurvey LLSurvey ON c.CASENO=LLSurvey.OTHERNO  JOIN LISUSER.LLInqApply LLInqApply ON LLInqApply.SURVEYNO=LLSurvey.SURVEYNO " +
                            "WHERE c.endcasedate BETWEEN date'"+startDate+"' AND date'"+endDate+"' AND c.rgtstate  in ('11','12') " ,
                    Lists.newArrayList("LLInvestigation_19","交易编码","保险机构代码", "立案号", "报案号", "调查项目号", "出险人客户号", "出险人名称", "调查类型", "调查地点", "调查原因", "调查原因内容", "调查内容", "调查结论/回复内容","调查开始日期/分配日期", "调查结束日期", "调查报告状态", "事故描述", "院外调查回复", "调查机构", "本地标志"
                    ));

            //理赔实付表
            map.put("select ('000085'||to_char(SYSDATE,'YYYYMMDD')||lpad(ROWNUM, 12, '0')), '000085', llcase.caseno, ljagetclaim.OtherNo, ljagetclaim.ContNo, ljagetclaim.ActuGetNo," +
                            "'', ljagetclaim.GetDutyCode, (SELECT llstore.LPNAME FROM lisuser.llstore WHERE llstore.DOCID='GetDuty' AND ljagetclaim.GetDutyKind=llstore.CASENO), ljagetclaim.KindCode, ljagetclaim.RiskCode," +
                            "ljagetclaim.RiskVersion, ljaget.shoulddate, ljagetclaim.EnterAccDate, ljagetclaim.ConfDate, ljagetclaim.Pay, ljagetclaim.ManageCom, ljagetclaim.GetNoticeNo," +
                            "'', '', '', ljaget.drawer, (SELECT llstore.LPNAME FROM lisuser.llstore WHERE llstore.DOCID='IdType' AND llstore.CASENO=(SELECT llcaseext.draweridtype FROM lisuser.llcaseext llcaseext where llcase.CASENO=llcaseext.CASENO AND rownum=1))," +
                            "ljaget.drawerid, ljaget.bankcode, (SELECT LDBank.bankname FROM  lisuser.LDBank LDBank where ljaget.BANKCODE=LDBank.BANKCODE), ljaget.accname, ljaget.bankaccno," +
                            "'', '', llregister.togetherflag, CASE ljaget.PayMode WHEN '11' THEN '5' ELSE ljaget.PayMode END  FROM lisuser.llcase llcase  JOIN lisuser.LLREGISTER llregister ON llcase.RGTNO=LLREGISTER.RGTNO" +
                            " join lisuser.ljagetclaim LJAGETCLAIM ON llcase.CASENO=LJAGETCLAIM.OTHERNO  join lisuser.LJAGET ljaget ON LJAGET.OTHERNO=LLCASE.CASENO " +
                            "WHERE llcase.endcasedate BETWEEN date'"+startDate+"' AND date'"+endDate+"' AND llcase.rgtstate in ('11','12')",
                    Lists.newArrayList("LLClaimPayFee_21","交易编码","保险机构代码", "立案号", "赔案号", "保单号", "实付号码", "交易类型", "给付责任编码", "给付责任类型", "险类代码", "险种代码", "险种版本号", "给付日期", "财务到帐日期", "财务确认日期", "赔付金额", "管理机构", "给付通知书号码", "主体类型", "客户性质", "领取人子公司客户号", "领取人客户姓名", "领取人证件类型", "领取人证件号码", "领取人银行联行号(支行代码)", "领取人开户银行支行名称", "领取人银行户名", "领取人银行账号", "银行附言", "银行账户类型", "给付方式", "赔款领取方式"
                    ));
        }
         if(a==0){
             //病种
             map.put("SELECT ('000085'||to_char(SYSDATE,'YYYYMMDD')||lpad(ROWNUM, 12, '0')), '000085',LDDISEASE.ICDCode,LDDISEASE.ICDName,'','','','','','','','','','','','','','','','' FROM LISUSER.LDDISEASE " ,
                     Lists.newArrayList("KA06_108","交易编码","保险机构代码", "疾病编码", "疾病名称", "拼音助记码", "五笔助记码", "开始日期", "终止日期", "有效标志", "疾病分类", "病种类别", "备注", "特病分类", "国家目录编码", "疾病描述", "免赔病种标识", "方法套系", "是否使用治疗方案", "统筹区编码", "社保经办机构编码"
                     ));

             //手术
             map.put("SELECT ('000085'||to_char(SYSDATE,'YYYYMMDD')||lpad(ROWNUM, 12, '0')), '000085','',LDICDOPS.ICDOPSCode,LDICDOPS.ICDOPSName,'','',''FROM LISUSER.LDICDOPS LDICDOPS",
                     Lists.newArrayList("KA08_109","交易编码","保险机构代码", "手术信息ID", "手术代码", "手术名称", "备注", "统筹区编码", "社保经办机构编码"
                     ));

             //医疗机构
             map.put("SELECT ('000085'||to_char(SYSDATE,'YYYYMMDD')||lpad(ROWNUM, 12, '0')), '000085',LDHospital.HospitCode,LDHospital.HospitName,CASE LDHospital.HospitalType WHEN '2' THEN '2' ELSE '1' END, " +
                             "LDHospital.AdminiSortCode,'',CASE LDHospital.LevelCode WHEN '31' THEN '02'  WHEN '21' THEN '05'  WHEN '11' THEN '08'  WHEN '00' THEN '11'  WHEN '10' THEN '14' " +
                             "WHEN '20' THEN '13' WHEN '30' THEN '12' ELSE LDHospital.LevelCode END ,LDHospital.SatrapName, " +
                             "'','','','','','','','','','','',s.lpname,'','',LDHospital.LINKMAN,TRANSLATE (LDHospital.PHONE,CHR (13) || CHR (10),'，'),TRANSLATE (LDHospital.ADDRESS,CHR (13) || CHR (10),'，'),TRANSLATE (LDHospital.ZIPCODE,CHR (13) || CHR (10),'，'),'', " +
                             "LDHospital.HOSPITLICENCNO,'','','','','','','',LDHospital.SuperiorNo,LDHospital.InterNo,'','',LDHospital.BedAmount,'',LDHospital.TotalNo,'','','','','','','' " +
                             "FROM lisuser.LDHospital LDHospital LEFT JOIN lisuser.llstore s ON s.CASENO=LDHospital.AREACODE " +
                             "WHERE s.DOCID='AreaCode' ",
                     Lists.newArrayList("KB01_110","交易编码","保险机构代码", "医疗服务机构编号", "医疗服务机构名称", "定点医疗服务机构类型", "医疗机构分类代码", "组织机构代码", "医院等级", "法定代表人姓名", "法定代表人公民身份号码", "法定代表人电话", "上级医疗机构编号", "有效标志", "签订医疗服务协议标识", "职工基本医疗保险定点单位标识", "离休人员医疗保障定点单位标识", "城镇居民基本医疗保险定点单位标识", "新型农村合作医疗定点单位标识", "开始日期", "终止日期", "行政区划代码", "医疗机构执业范围代码", "医疗机构执业范围名称", "联系人", "联系电话", "地址", "邮政编码", "备注", "执业许可证号", "医疗服务内容", "医疗服务人群", "联网结算标识", "药品经营许可证号", "在编职工数", "医院科室数", "医院重点科室数", "正高职称人数", "中级职称人数", "专业技术人员人数", "副高职称人数", "床位数", "经营面积", "营业人数", "其它人数", "康复医师人数", "副高职称以上康复医师人数", "康复治疗师人数", "副高职称以上康复治疗师人数", "统筹区编码", "社保经办机构编码"
                     ));

         }
        System.out.println("map+++++"+map.size());
        return map;
    }
}
