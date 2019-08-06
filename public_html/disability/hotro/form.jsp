<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>	

<script type="text/javascript">
    function getSearchPanel(obj) {
         $(".khung-div-search").show();
        if (trim(getObj('td' + obj.value).innerHTML) == '') {
            messageImg('td' + obj.value, '<bean:message key="search.loading" bundle="<%=interfaces%>"/>');
            postAjax('searchPanel', 'td' + obj.value, anchor + ':_PANEL:panel:' + obj.name);
        }    
        hideshow('td' + obj.value, obj.checked);
    }
</script>

<html:hidden name="support" property="idNkt" styleId="idNkt" />
<bean:define id="strStatusId" name="support" property="statusId" type="java.lang.Integer" />
<%
    String checkBox="";
    String subCheck="";
    String hotroIdsTemp="";
    String supportSelTemp = "";
    String strCreateDate = "";
    int hotroId = 0;    
    int dtlId = 0;
    
    int statusId = strStatusId;
    java.util.Map<String, String> map_hotro_kn_chitra = (java.util.Map<String, String>) request.getAttribute("map_hotro_kn_chitra");
    java.util.Map<String, String> map_hotro_the_bhyt = (java.util.Map<String, String>) request.getAttribute("map_hotro_the_bhyt");
    java.util.Map<String, String> map_hotro_sd_the = (java.util.Map<String, String>) request.getAttribute("map_hotro_sd_the");
    java.util.Map<String, String> map_hotro_sd_the_phcn = (java.util.Map<String, String>) request.getAttribute("map_hotro_sd_the_phcn");    
%>
<logic:notEmpty name="support" property="hotroIds">
<bean:define name="support" property="hotroIds" id="hotroIds" type="java.lang.String" />	    
<%	
    hotroIdsTemp = hotroIds;
%>
</logic:notEmpty>

<logic:notEmpty name="support" property="supportSel">
<bean:define name="support" property="supportSel" id="supportSel" type="java.lang.String" />
<%	
    supportSelTemp = supportSel;
%>
</logic:notEmpty>

<logic:notEqual value="0" name="support" property="idNkt">
    <bean:define id="nktId" name="support" property="idNkt" type="java.lang.Integer" />
    <bean:define id="createDate" name="support" property="dateCreate" type="java.lang.String" />    
    <%  
        dtlId = nktId;
        strCreateDate = createDate;
    %>
</logic:notEqual>

<div>
    <div align="left" class="fullName">
        <strong>H&#7885; v&#224; t&#234;n NKT: <bean:write name="kpi" property="disName"/></strong>
    </div>  
    
    <div style="overflow-x:scroll;height:400px;">
		<table cellpadding="2" width="100%" align="center" style="border-clilapse: clilapse" cellspacing="2" border="0">    
		<tr>
		<logic:present name="BSupports">
			<logic:notEmpty name="BSupports">
				<logic:iterate id="bean" name="BSupports" indexId="i" type= "com.form.disability.categorys.FTinh">					
				<%if(bean.getLevel()==0){%>  
				    <%if(i>0){%>
				    </table>            
				    </td>
				    <%}%>
				    <td align="left" valign="top" width="33%">
				    <div><strong><%=bean.getName().replaceAll("--- ","")%></strong></div>
				    <table border="0" width="100%">			
				<%}else if(bean.getLevel()==1){%>

				<%
            String SQL_HOTRO_LEVEL_1="SELECT hotro_id, parent_id, " +  ((statusId==1) ? "name_htro":"name") + " FROM DR_HOTRO WHERE parent_id = ? order by order_by";
            com.form.FBeans beans = new com.form.FBeans();
            beans = new com.bo.tree.BTreeView().getTree(bean.getId(),false,SQL_HOTRO_LEVEL_1,"","");
            request.setAttribute("BTreeHotros",beans);
				%>
				    <tr>
				    	<td>
				    	<%	
                  checkBox = (hotroIdsTemp.indexOf("#"+bean.getId()+"#")>=0) ? "checked" : "";	%>				       
			            <table width="100%" cellpadding="0" cellspacing="0">
                  <tr>
                      <td colspan="2">
                        <%
                            String display = (hotroIdsTemp.indexOf("#"+bean.getId()+"#")>=0) ? "":"style='display:none;'";
                            String style = (hotroIdsTemp.indexOf("#"+bean.getId()+"#")>=0) ? "style='font-style:italic;font-weight:bold;'":"";
                        %>
                        <input type="checkbox" name="hotroIds" id="supportIds" <%=checkBox%> onclick="showCongcu(this.value,this.checked);" value="<%=bean.getId()%>" />
                        <%                       
                            String hrefPopup = "javascript:openWindow('kpi',anchor +':_PREPARED_DGIA:dtlId:"+dtlId+":supportId:"+bean.getId()+":dateCreate:"+strCreateDate+"');remove('kpi',anchor);";
                        %>
                          <span <%=style%> >
                              <logic:equal name="support" property="statusId" value="0">
                                    <%=bean.getName().replaceAll("--- ","")%>
                               </logic:equal>   
                               <logic:equal name="support" property="statusId" value="1">
                                    <%if(hotroIdsTemp.indexOf("#"+bean.getId()+"#")>=0){%>
                                        <a href="<%=hrefPopup%>" > <%=bean.getName().replaceAll("--- ","")%></a>
                                    <%}else{%>
                                        <%=bean.getName().replaceAll("--- ","")%>
                                    <%}%>
                               </logic:equal>
                          </span>
                          <%
                              String displaySub = (supportSelTemp.indexOf("#"+bean.getId()+"#")>=0) ? "":"style='display:none;'";                              
                          %>
                          <div id="<%=bean.getId()%>" <%=displaySub%>>
                               <logic:iterate id="subBean" name="BTreeHotros" indexId="i" type= "com.form.disability.categorys.FTinh">
                               <%if(subBean.getLevel()==0){%>
                                  &nbsp;&nbsp;&nbsp;
                               <%}else{%>
                                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                               <%}%>
                               <input type="checkbox" name="hotroIds" <%=(hotroIdsTemp.indexOf("#"+subBean.getId()+"#")>=0) ? "checked" : ""%> id="supportIds" <%=subCheck%> value="<%=subBean.getId()%>" />
                               &nbsp;<i><%=subBean.getName().replaceAll("--- ","")%></i><br/>
                               </logic:iterate>                               
                          </div>
                      </td>
                  </tr>
            </table>
            
					</td>
				</tr>
				<%}%>
				</logic:iterate>   
			</logic:notEmpty>
		</logic:present>
    </table>		
	</td>
	</tr>
</table>
</div>

<table class="tableForm" cellpadding="2" width="100%" align="center" style="border-clilapse: clilapse" cellspacing="2" border="0">    
<tr height="25">
    <td width="30%">
        <bean:message key="common.label.createdate" bundle="<%=interfaces%>"/>:
    </td>
    <td colspan="3">
        <input type="text" name="createDate" id="createDate" 
            onkeypress="return formatDate(event,this);" 
            onblur="isDate(this);" style="width:80px;" 
            class="textfield_date"
            value="<bean:write name="support" property="createDate"/>" />
        <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'createDate','dd/mm/yyyy');return false;">
    </td>
</tr>

<logic:equal name="support" property="statusId" value="1">
<tr>
    <td><bean:message key="hotro.list.label.formDate" bundle="<%=interfaces%>"/>:</td>
    <td colspan="3">
        <input type="text" 
            name="dateForm" id="dateForm" 
            onkeypress="return formatDate(event,this);" 
            onblur="isDate(this);" style="width:80px;" 
            class="textfield_date"
            value="<bean:write name="support" property="dateForm"/>" />
        <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'dateForm','dd/mm/yyyy');return false;">
      
        <bean:message key="hotro.list.label.toDate" bundle="<%=interfaces%>"/>:
        <input type="text" name="dateTo" id="dateTo" 
            onkeypress="return formatDate(event,this);" 
            onblur="isDate(this);"  style="width:80px;" 
            class="textfield_date"
            value="<bean:write name="support" property="dateTo"/>" />
        <img src="<%=contextPath%>/images/ew_calendar.gif" alt='option date' onClick="popUpCalendar(this,'dateTo','dd/mm/yyyy');return false;">
    </td>
</tr>
</logic:equal>

<tr height="25">
    <td><bean:message key="hotro.list.label.kn.chitra" bundle="<%=interfaces%>"/>:</td>
    <td>
        <html:select name="support" property="knChiTra">
            <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
            <html:options collection="map_hotro_kn_chitra" property="key" labelProperty="value" />
        </html:select>    	
    </td>
    <td><bean:message key="hotro.list.label.the.bhyt" bundle="<%=interfaces%>"/>:</td>
    <td>
        <html:select name="support" property="theBhyte">
            <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
            <html:options collection="map_hotro_the_bhyt" property="key" labelProperty="value" />
        </html:select>    	
    </td>
</tr>

<tr height="25">
    <td width="35%"><bean:message key="hotro.list.label.sd.the" bundle="<%=interfaces%>"/>:</td>
    <td>
        <html:select name="support" property="sdThe" styleClass="combobox_w100">
            <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
            <html:options collection="map_hotro_sd_the" property="key" labelProperty="value" />
        </html:select>    	
    </td>
    <td><bean:message key="hotro.list.label.sd.the.phcn" bundle="<%=interfaces%>"/>:</td>
    <td>
        <html:select name="support" property="sdThePhcn" styleClass="combobox_w100">
            <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
            <html:options collection="map_hotro_sd_the_phcn" property="key" labelProperty="value" />
        </html:select>    	
    </td>
</tr>

<tr>
    <td colspan="4">
        <table width="100%">
        <tr>
            <td width="35%"><bean:message key="hotro.list.label.mtieu.gdinh" bundle="<%=interfaces%>"/>:</td>
            <td colspan="3">
                  <html:textarea name="support" property="mtieuGdinh" rows="3" cols="55"/>
            </td>
        </tr>
        
        <tr>
            <td width="35%"><bean:message key="hotro.list.label.mtieu.dtri" bundle="<%=interfaces%>"/>:</td>
            <td colspan="3">
                  <html:textarea name="support" property="mtieuDtri" rows="3" cols="55"/>
            </td>
        </tr>
        
        <tr>
            <td width="35%"><bean:message key="hotro.list.label.ctr.vltl" bundle="<%=interfaces%>"/>:</td>
            <td colspan="3">
                  <html:textarea name="support" property="ctVltl" rows="3" cols="55"/>
            </td>
        </tr>
        
        <tr>
            <td width="35%"><bean:message key="hotro.list.label.ctr.hdtl" bundle="<%=interfaces%>"/>:</td>
            <td colspan="3">
                  <html:textarea name="support" property="ctHdtl" rows="3" cols="55"/>
            </td>
        </tr>
        
        <tr>
            <td width="35%"><bean:message key="hotro.list.label.ctr.antl" bundle="<%=interfaces%>"/>:</td>
            <td colspan="3">
                  <html:textarea name="support" property="ctAntl" rows="3" cols="55"/>
            </td>
        </tr>
        
        <tr>
            <td width="35%">
                <!--<logic:equal name="support" property="statusId" value="1">
                    D&#7909;ng cu tr&#7907; gi&#250;p
                </logic:equal>
                <logic:notEqual name="support" property="statusId" value="1">
                    Nhu c&#7847;u DV Kh&#225;c:
                </logic:notEqual>-->
                D&#7909;ng cu tr&#7907; gi&#250;p
            </td>
            <td colspan="3">
                  <html:textarea name="support" property="dungcuKhac" rows="3" cols="55"/>
            </td>
        </tr>
        
        <logic:equal name="support" property="statusId" value="1">
        <tr>
            <td>C&#7909; th&#7875; v&#7873; h&#7895; tr&#7907;</td>
            <td colspan="3">
                <html:textarea name="support" property="reson" rows="3" cols="55"/>
            </td>
        </tr>
        <tr>
            <td><bean:message key="hotro.list.label.nguonhotro" bundle="<%=interfaces%>"/></td>
            <td colspan="3">
             <%
              String onchange = "postAjax('support','MainCate',anchor + ':_VIEW_COMBO_BOX:');";
            %>
              
             <html:select styleClass="inputbox" name="support" property="nguonHoTroId">
                <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                <logic:present name="BNguonHoTros">
                  <html:options collection="BNguonHoTros" property="id" labelProperty="name"/>
                </logic:present>
             </html:select>
            </td>
           </tr>
        
        <tr>
            <td><bean:message key="hotro.list.label.nguonhotroId.detail" bundle="<%=interfaces%>"/></td>
            <td colspan="3"><html:textarea name="support" property="nguonhotro" rows="3" cols="55"/></td>
        </tr>
        
        <tr>
            <td align="left" width="25%">
                <bean:message key="common.label.re-examination" bundle="<%=interfaces%>"/>:
            </td>
            <td>                    
                 <input type="text" name="disNgayTK" id="disNgayTK"                         
                    class="textfield_date" onblur="isMonth(this)"
                    value="<bean:write name="kpi" property="disNgayTK"/>" />
            </td>
        </tr>
        
        <tr>
            <td align="left" width="25%">
                <bean:message key="common.label.address.support.exam" bundle="<%=interfaces%>"/>:
            </td>
            <td>
                <html:select name="kpi" property="disDiaDiem" >
                    <html:option value="0"><bean:message key="combo.luachon" bundle="<%=interfaces%>"/></html:option>
                    <html:options collection="mapPLoaiDDiem" property="key" labelProperty="value" />
                </html:select>
            </td>
        </tr>
        </logic:equal>        
        </table>
    </td>
</tr>    
</table>
</div>

<div>
    <html:errors property="alert" bundle="<%=interfaces%>" />
</div>
	
<div class="bottom">
    <logic:notEqual name="support" property="idNkt" value="0">
    <span class="bt_left_Search">
        <span class="bt_right_Search">
            <span class="bt_center_Search">
              <logic:notEqual name="support" property="mode" value="UPDATE" > 
                  <html:button property="_CREATE" styleClass="button" onclick="postAjax('kpi','MainCate',anchor + ':_INSERT_SUPPORT');">
                      <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                  </html:button>
              </logic:notEqual>
              
              <logic:equal name="support" property="mode" value="UPDATE" >
                  <html:button property="_CREATE" styleClass="button" onclick="postAjax('kpi','MainCate',anchor + ':_UPDATE_SUPPORT');">
                      <bean:message key="action.update" bundle="<%=interfaces%>"/>
                  </html:button>
              </logic:equal>
            </span>
        </span>
     </span>   
     </logic:notEqual>
 </div>   
</div> 