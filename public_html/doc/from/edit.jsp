<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script type="text/javascript">
function checkSubmit(form){
    if(form.code.value==''  ||  form.vnName.value==''  ||  form.enName.value==''){
        alert(<bean:message key="alert.not.enough.data" bundle="<%=interfaces%>"/>);
        return false;
    }
    return true;
}
</script>   

<html:form action="from" method="post" target="_top" >
<html:hidden name="from"  property="id" />
<table cellpadding="0" cellspacing="0" border="0" width="99%" align="left" >
        <tr><td width="8"><img src="<%=contextPath%>/images/newImages/i_19.gif" width="8" height="43" /></td>
            <td class="sharebackground" width="100%">
            <Strong><bean:message key="form.docs.dossierId" bundle="<%=interfaces%>"/></strong>
            </td>
            <td width="10"><img src="<%=contextPath%>/images/newImages/i_20.gif" width="10" height="43" /></td>
        </tr>
        <tr>
            <td colspan="4" align="center">
            <TABLE cellSpacing=0 width="100%" cellPadding=0  border="0" align="center">
                    
                     <tr>
                        <td height="24px" style="padding-left:10px"><bean:message key="categoryform.form" bundle="<%=interfaces%>"/> <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
                        <td align="left">
                              <html:select name="from" property="fomId" styleClass="fieldSelect" >                                    
                                <logic:present name="BFoms">
                                 <html:options collection="BFoms" property="id" labelProperty="name"/>
                                 </logic:present>
                              </html:select>
                        </td>
                   </tr>
                    <tr>
                                <td height="24px" style="padding-left:10px"><bean:message key="lable.from.code" bundle="<%=interfaces%>"/> <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
                                <td align="left"><html:text name="from" property="code" style="width:200px" maxlength="100"/></td>
                    </tr>
                    <tr>
                                <td height="24px" style="padding-left:10px"><bean:message key="lable.from.vnName" bundle="<%=interfaces%>"/> <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
                                <td align="left"><html:text name="from" property="vnName" style="width:200px" maxlength="100"/></td>
                    </tr>
                    <tr>
                                <td height="24px" style="padding-left:10px"><bean:message key="lable.from.enName" bundle="<%=interfaces%>"/> <bean:message key="alert.type.information" bundle="<%=interfaces%>"/></td>
                                <td align="left"><html:text name="from" property="enName" style="width:200px" maxlength="100"/></td>
                    </tr>
                    <tr>
                                <td height="24px" style="padding-left:10px"><bean:message key="lable.from.description" bundle="<%=interfaces%>"/></td>
                                <td align="left"><html:textarea name="from" property="description" style="width:200px;height:50px;"/></td>
                    </tr>
                    <tr>
                        <td height="24px" colspan="2" align="center">
                            <logic:equal name="from" property="id" value="0">
                            <html:button property="_CREATE"  onclick="javascript:if(checkSubmit(this.form)){post('from',anchor+':_CREATE');}" styleClass="button">                       
                            <bean:message key="action.insert" bundle="<%=interfaces%>"/>
                            </html:button>
                            </logic:equal>  
                            <logic:notEqual name="from" property="id" value="0">
                            <html:button property="_EDIT"  onclick="javascript:if(checkSubmit(this.form)){post('from',anchor + ':_EDIT');}"  styleClass="button">                       
                            <bean:message key="action.update" bundle="<%=interfaces%>"/>
                            </html:button>
                            </logic:notEqual>  
                        </td>
                    </tr>
            </table>
 
 </td>
 </tr>
 </table>
 
 </html:form>        