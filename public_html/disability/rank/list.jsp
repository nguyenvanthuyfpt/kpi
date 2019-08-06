<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>

<div>  	
		<jsp:include page="/disability/rank/form.jsp" />
</div>

<div id="alert">
    <jsp:include page="/admin/alert.jsp"/>
</div>

<div style="padding:5px;">
<span id="result"></span>
<table class="list-voffice" cellpadding="2" width="100%" align="center" cellspacing="2" border="0">    
  <TR>               
	    <TH width="10px" align="center">#</TH>            
	    <TH>Ch&#7881; ti&#234;u &#273;&#225;nh gi&#225;</TH>
      <TH># &#273;&#225;nh gi&#225;</TH>
  </TR>

	<logic:present name="BRanks">
    <bean:define name="BRanks" id="beans" type="com.form.FBeans" />
		<logic:notEmpty name="BRanks">
    <%  
        int subSTT = 1;
        int STT = 1;
        String strSTT = "";
    %>
		<logic:iterate id="bean" name="BRanks" type= "com.form.disability.categorys.FRank">
     <%  int i = beans.getFirstRecord();%>
    <tr class="<%=(subSTT%2==0)?"content":"content1"%>">
      <td width="5%">
          <%
              int currParentId = bean.getParentID();
              if (currParentId==0) {
                 strSTT = String.valueOf(STT);
                 STT++;
                 subSTT = 1;
              } else {
                 strSTT = "" + subSTT;
                 subSTT++;
              }
          %>          
          <%if(bean.getParentID()==0){%>
            <strong><%=strSTT%></strong>
          <%}else{%>
              <%=strSTT%>
          <%}%>
      </td>
      <td>
        <%if(bean.getParentID()==0){%>
            <strong><%=bean.getName()%></strong>
        <%}else{%>
            &nbsp;&nbsp;<a href="#" onClick="postAjax('kpi','MainCate', anchor + ':_DETAIL_RANK:rankId:<%=bean.getId()%>');"><%=bean.getName()%></a>
        <%}%>
      </td>
      <td>
          <%=bean.getTotal()%>
      </td>
    </tr>
		</logic:iterate>
		</logic:notEmpty>
	</logic:present>
</table>
</div>