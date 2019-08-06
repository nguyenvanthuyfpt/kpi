<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<!-- HO TRO -->
<logic:equal name="support" property="statusId" value="1">		
<table  class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
<tbody>	
	<TR>
	    <TH width="10px" align="center">#</TH>
      <TH width="18%" align="center"><bean:message key="hotro.list.label.createDate" bundle="<%=interfaces%>"/></TH>
	    <TH><bean:message key="common.label.createby" bundle="<%=interfaces%>"/></TH>	    			
      <TH><bean:message key="hotro.list.label.detail" bundle="<%=interfaces%>"/></TH>		
      <TH width="18%"><bean:message key="common.label.re-examination" bundle="<%=interfaces%>"/></TH>		
      <TH width="40px" align="center">#</TH>
	</TR>
	
    <logic:present name="BSupportTrailers">
	    <%  int i =1;    %>
	    <logic:iterate name="BSupportTrailers" id="bean" type="com.form.disability.FSupport">   
          <tr class="<%=(i%2==0)?"content1":"content"%>">
              <td align="center">
                <span class="index"><%=i++%></span>
              </td>					
              <td class="center" width="100px;">
                  <a href="javascript:postAjax('kpi','MainCate',anchor + ':_DETAIL_SUPPORT:nktId:<%=bean.getIdNkt()%>:dateCreate:<%=bean.getDateCreate()%>')">
                    <bean:write name="bean" property="dateCreate" /></a>
              </td>
              <td align="center">
                  <a href="javascript:postAjax('kpi','MainCate',anchor + ':_DETAIL_SUPPORT:nktId:<%=bean.getIdNkt()%>:dateCreate:<%=bean.getDateCreate()%>')">
                    <bean:write name="bean" property="fullName" /></a>
              </td>
              <td align="center">
                  <a href="javascript:postAjax('kpi','MainCate',anchor + ':_DETAIL_SUPPORT:nktId:<%=bean.getIdNkt()%>:dateCreate:<%=bean.getDateCreate()%>')">
                    <bean:write name="bean" property="reson" /></a>
              </td>				
              <td align="center">
                  <a href="javascript:postAjax('kpi','MainCate',anchor + ':_DETAIL_SUPPORT:nktId:<%=bean.getIdNkt()%>:dateCreate:<%=bean.getDateCreate()%>')">
                    <bean:write name="bean" property="thoiDiemTK" /></a>
              </td>
              <td width="40px">
                  <img style="border:0px;cursor:pointer;" src="<%=contextPath%>/images/editdraft.png" 
                      title="<bean:message key="action.edit" bundle="<%=interfaces%>"/>" 
                      onClick="javascript:postAjax('kpi','MainCate',anchor + ':_DETAIL_SUPPORT:nktId:<%=bean.getIdNkt()%>:dateCreate:<%=bean.getDateCreate()%>')">		            
                  <img style="border:0px;cursor:pointer;" src="<%=contextPath%>/images/delete.png" 
                      title="<bean:message key="action.delete" bundle="<%=interfaces%>"/>" 
                      onClick="javascript:if(messageDelete())postAjax('kpi','MainCate',anchor + ':_DELETE_SUPPORT:nktId:<%=bean.getIdNkt()%>:dateCreate:<%=bean.getDateCreate()%>')">
              </td>
          </tr>
	    </logic:iterate>
    </logic:present>
</tbody>
</table>
</logic:equal>

<!-- NHU CAU -->
<logic:equal name="support" property="statusId" value="0">		
<table  class="list-voffice" cellpadding="0" cellspacing="0" width="100%" border="0">
<tbody>
    <tr>
        <th width="10px" align="center">#</th>
        <th width="18%" align="center"><bean:message key="nhucau.list.label.createDate" bundle="<%=interfaces%>"/></th>
        <th><bean:message key="common.label.createby" bundle="<%=interfaces%>"/></th>                
        <th width="40px" align="center">#</th>
    </tr>
    <logic:present name="BSupportTrailers">
        <%  int i =1;    %>
        <logic:iterate name="BSupportTrailers" id="bean" type="com.form.disability.FSupport">
            <tr class='<%=(i%2==0)?"content1":"content"%>'>
                <td align="center">
                    <span class="index">
                        <%=i++%></span>
                </td>
                <td class="center" width="100px;">
                    <a href="javascript:postAjax('kpi','MainCate',anchor + ':_DETAIL_SUPPORT:nktId:<%=bean.getIdNkt()%>:dateCreate:<%=bean.getDateCreate()%>')">
                      <bean:write name="bean" property="dateCreate" /></a>
                </td>
                <td align="center">
                    <a href="javascript:postAjax('kpi','MainCate',anchor + ':_DETAIL_SUPPORT:nktId:<%=bean.getIdNkt()%>:dateCreate:<%=bean.getDateCreate()%>')">
                      <bean:write name="bean" property="fullName" /></a>
                </td>                 
                <td width="40px">
                    <img style="border:0px;cursor:pointer;"
                         src="<%=contextPath%>/images/editdraft.png"
                         title='<bean:message key="action.edit" bundle="<%=interfaces%>"/>'
                         onclick="javascript:postAjax('kpi','MainCate',anchor + ':_DETAIL_SUPPORT:nktId:<%=bean.getIdNkt()%>:dateCreate:<%=bean.getDateCreate()%>')"></img>
                     
                    <img style="border:0px;cursor:pointer;"
                         src="<%=contextPath%>/images/delete.png"
                         title='<bean:message key="action.delete" bundle="<%=interfaces%>"/>'
                         onclick="javascript:if(messageDelete())postAjax('kpi','MainCate',anchor + ':_DELETE_SUPPORT:nktId:<%=bean.getIdNkt()%>:dateCreate:<%=bean.getDateCreate()%>')"></img>
                </td>
            </tr>
        </logic:iterate>
    </logic:present>
</tbody>
</table>
</logic:equal> 
