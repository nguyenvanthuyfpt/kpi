package com.action.disability.report;


import com.action.ACore;
import com.bo.disability.categorys.BTinh;
import com.bo.disability.report.BReportKpi;
import com.bo.tree.BTreeView;

import com.dao.disability.report.DReportKpi;
import com.dao.disability.report.DReportKpiData;

import com.exp.EException;
import com.form.FBeans;
import com.form.disability.FDisExport;
import com.form.disability.categorys.FTinh;
import com.form.disability.report.FReportKpi;
import com.inf.disability.IKeyDisability;
import com.util.Constant;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AReportKpi extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException, SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target =_LOGOUT;
        ActionErrors errors = new ActionErrors();       
        FReportKpi bean = (FReportKpi)form;        

        String periodType = bean.getPeriodType();
        int locationId = bean.getTinhId();
        int extend = bean.getExtend();
        String subFunction = bean.getSubFunction();
        FTinh beanTinh = new FTinh();
        BTinh boTinh = new BTinh();
        
        beanTinh.setId(bean.getTinhId());
        beanTinh = boTinh.getRecordByID(beanTinh);
        String tinh_name = beanTinh.getName();
        
        Map<String, FTinh> map_location = (HashMap<String, FTinh>)request.getSession().getAttribute("MAP_LOCATION");
        FBeans beans = new FBeans();
        FBeans tinhs = new FBeans();
        String SQL = "SELECT tinh_id,parent_id,name FROM dr_area WHERE parent_id = ? ";
        String characters = "/ ";
        String member = "";
        if ((bean.me.getDepartmentName() != null) && 
            (!bean.me.getDepartmentName().equals(""))) {
            member = bean.me.getDepartmentName();
        }         

        tinhs = new BTreeView().getTree(0, false, SQL, characters, member);        
        beans = (FBeans)request.getSession().getAttribute("BTreeTinhs");        
        int level = 0;
        if(bean.getTinhId()>0){
            List params     = new ArrayList();
            FTinh beanCa    = new FTinh();
            beanCa.setParentID(bean.getTinhId());            
            
            for (int i=bean.getTinhId();i>0;i=beanCa.getParentID()) {
                beanCa.setId(beanCa.getParentID());
                beanCa=new BTinh().getRecordByID(beanCa);
                params.add(beanCa.getName());
            }
            for (int i=params.size()-1;i>-1;i--) {            
                level++;                
            }
        }
        
        String anchor = bean.getValue(APP_ANCHOR,"");
        bean.setPeriodType(periodType);
        bean.setTinhId(locationId);
        bean.setSubFunction(subFunction);
        
        if(anchor.equals("_REPORT_SELECT_TINH")){
            String tinhName = "";
            int defaultLocation = 0;
            if (bean.getTinhId()>0) {
                FTinh tinh = new FTinh();
                tinh.setId(bean.getTinhId());
                tinh = new BTinh().getRecordByID(tinh);
                defaultLocation = tinh.getId();
                tinhName = tinh.getName();
            }
            
            bean.setTinhName(tinhName);      
            request.setAttribute("BTreeTinhs", ("04.01|04.02|04.04".indexOf(subFunction)>-1)? beans:tinhs);
            request.setAttribute("subanchor", bean.getSubFunction());
            request.setAttribute("reportkpi", bean);
            target=anchor;
        } else if(anchor.equals("_REPORT")){
              beans=new FBeans();
              String val = "", strVal = "", period = "";              
              if (Constant.REPORT_PERIOD_TYPE_MONTH.equals(periodType)) {
                  val = String.valueOf(bean.getMonthReport());
                  strVal = val + "/" + bean.getYearReport();
              } else if (Constant.REPORT_PERIOD_TYPE_QUARTER.equals(periodType)) {
                  val = String.valueOf(bean.getQuarterReport());
                  strVal = "Q"+ val + "/" + bean.getYearReport();
              } else if (Constant.REPORT_PERIOD_TYPE_YEAR.equals(periodType)) {
                  val = String.valueOf(bean.getYearReport());
                  strVal = val;
              } else if (Constant.REPORT_PERIOD_TYPE_FT.equals(periodType)) {
                  val = bean.getFromMonth()+"/"+bean.getFromYear()+"-"+bean.getToMonth()+"/"+bean.getToYear();
                  strVal = val;
              }
              
              String reportDtl = "";
              if ("04.01".equals(bean.getSubFunction())) {
                  reportDtl = "_REPORT_INDICATOR";
              } else if ("04.02".equals(bean.getSubFunction())) {
                  reportDtl = "_REPORT_OBJECT";
              } else if ("04.03".equals(bean.getSubFunction())) {
                  reportDtl = "_REPORT_INSURANCE";   
              } else if ("04.04".equals(bean.getSubFunction())) {
                  reportDtl = "_REPORT_SUPPORT";        
              } else if ("04.05".equals(bean.getSubFunction())) {
                  reportDtl = "_REPORT_COMMUNE";
              } else if ("03.01".equals(bean.getSubFunction())) {
                  reportDtl = "_REPORT_EXPORT";
              }
              
              bean.setTinhName(tinh_name);
              try {
                  
                  int lvl = 1;
                  FTinh tinh = (FTinh)map_location.get(String.valueOf(locationId));
                  if (tinh!=null) {
                      lvl = (tinh.getName().indexOf("3")>-1)?3:((tinh.getName().indexOf("2")>-1)?2:1);                      
                  }
                  
                  String fileName = "", report = "";
                  if ("_REPORT_OBJECT".equals(reportDtl)) {
                      beans = new BReportKpi().getDataReportObject(Integer.parseInt(periodType), locationId, val, bean.getYearReport(), extend);
                      fileName = IKeyDisability.REPORT_FILE_KPI_REPORT_OBJECT;
                      FReportKpi beanTemp = new FReportKpi();
                      bean.setTinhId(locationId);
                      beanTemp.setPeriodType(periodType);
                      beanTemp.setVal(strVal);
                      beanTemp.setStore(beans);
                      report = new DReportKpi().exportReportObject(beanTemp, bean, fileName);
                  } else if ("_REPORT_INDICATOR".equals(reportDtl)) {
                      beans = new BReportKpi().getDataReportIndicator(Integer.parseInt(periodType), locationId, val, bean.getYearReport(), extend);
                      fileName = IKeyDisability.REPORT_FILE_KPI_REPORT_INDICATOR;
                      FReportKpi beanTemp = new FReportKpi();
                      bean.setTinhId(locationId); 
                      beanTemp.setPeriodType(periodType);
                      beanTemp.setVal(strVal);
                      beanTemp.setStore(beans);
                      report = new DReportKpi().exportReportIndicator(beanTemp, bean, fileName);
                  } else if ("_REPORT_INSURANCE".equals(reportDtl)) {
                      //beans = new BReportKpi().getDataReportInsurance(bean.getTinhId(), period);
                      fileName = IKeyDisability.REPORT_FILE_KPI_REPORT_HEALTH_INSURANCE;
                      FReportKpi beanTemp = new FReportKpi();
                      bean.setTinhId(locationId);
                      beanTemp.setPeriodType(periodType);
                      beanTemp.setVal(strVal);
                      beanTemp.setStore(beans);
                      report = new DReportKpi().exportReportInsurance(beanTemp, bean, fileName);
                  } else if ("_REPORT_SUPPORT".equals(reportDtl)) {
                      beans = new BReportKpi().getDataReportSupport(locationId, strVal);
                      fileName = IKeyDisability.REPORT_FILE_KPI_REPORT_SUPPORT;                                                
                      FReportKpi beanTemp = new FReportKpi();
                      bean.setTinhId(locationId);                    
                      beanTemp.setPeriodType(periodType);
                      beanTemp.setVal(strVal);
                      beanTemp.setStore(beans);
                      report = new DReportKpi().exportReportSuppor(beanTemp, bean, fileName);                                           
                  } else if ("_REPORT_COMMUNE".equals(reportDtl)) {
                      if (extend==0) {
                            beans = new BReportKpi().getDataDisCommuneSummary(lvl, locationId, strVal);
                            fileName = IKeyDisability.REPORT_FILE_KPI_REPORT_COMMUNE_SUMMARY;
                      } else {
                            beans = new BReportKpi().getDataDisCommuneDetail(lvl, locationId, strVal);  
                            fileName = IKeyDisability.REPORT_FILE_KPI_REPORT_COMMUNE_DETAIL;
                      }
                      
                      FReportKpi beanTemp = new FReportKpi();
                      beanTemp.setStore(beans);
                      beanTemp.setExtend(extend);
                      beanTemp.setVal(strVal);
                      beanTemp.setTinhName(tinh_name);
                      report = new DReportKpi().reportCommune(beanTemp, bean, fileName);
                   } else if ("_REPORT_EXPORT".equals(reportDtl)) {
                      String from = bean.getCreateDateFrom();
                      String to = bean.getCreateDateTo();
                      beans = new BReportKpi().getDataDisExport(lvl, locationId, from, to);                      
                      fileName = IKeyDisability.REPORT_FILE_KPI_REPORT_EXPORT;                      
                      FReportKpi beanTemp = new FReportKpi();
                      beanTemp.setStore(beans);
                      report = new DReportKpi().reportDisExport(beanTemp, bean, fileName);
                  }
                  
                  bean.download(report,fileName,null);
                  bean.deleteFile(report);                   
                  target=null;                 
            } catch (Exception ex) {
                  request.setAttribute("reportSystem",bean);
                  request.setAttribute("errorValue",ex.toString().replaceAll("com.exp.EException:",""));
                  target=_ERROR;    
            }        
        }
        
        if(!errors.isEmpty()) saveErrors(request,errors);
        return mapping.findForward(target);
    }
}
