<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script type="text/javascript">
function checkSubmit(form){
    if(form.name.value==''){
        alert(<bean:message key="alert.not.enough.data" bundle="<%=interfaces%>"/>);
        return false;
    }
    return true;
}
</script>

<html:form action="pgroup" >
<body onLoad="if(parent.SqueezeBox.presets.target==0){parent.SqueezeBox.close()};">
<table class="openMycontact" align="center">
  <TR>
        <TD vAlign=top width="58px"><bean:message key="pgroup.group.name.catiom" bundle="<%=interfaces%>"/></TD>
        <TD vAlign=top>
                 <html:select styleClass="inputbox"  name="pgroup" property="id" onchange="javascript:post('pgroup',anchor + ':_SELECT_AJAX')">                                     
                     <html:option value="0"><bean:message key="combo.lammoi" bundle="<%=interfaces%>"/></html:option>
                    <logic:present name="BPgroups">
                      <html:options collection="BPgroups" property="id" labelProperty="name"/>
                    </logic:present>
                    </html:select>     
        </TD>
      </TR>
  <TR>
    <TD vAlign=top><bean:message key="pgroup.edit.name" bundle="<%=interfaces%>"/><bean:message key="alert.type.information" bundle="<%=interfaces%>"/></TD>
    <TD vAlign=top><html:text  name="pgroup" property="name" styleClass="inputbox" size="30" maxlength="200" /></TD>
  </TR>
  <TR>
    <TD vAlign=top><bean:message key="pgroup.edit.description" bundle="<%=interfaces%>"/></TD>
    <TD vAlign=top ><html:text  name="pgroup" property="description" styleClass="inputbox" size="30" maxlength="500" /></TD>
  </TR>
  <TR>
    <TD vAlign=top colspan="2" align="left" class="textalignR">       
    <logic:equal name="pgroup" property="id" value="0" >
              <html:button property="_CREATE_FALSE"  onclick="if(checkSubmit(this.form)) {parent.SqueezeBox.presets.target=0 ;post('pgroup',anchor + ':_CREATE_FALSE'); }">
                    <bean:message key="action.insert" bundle="<%=interfaces%>"/>
            </html:button>
    </logic:equal>
        <logic:notEqual name="pgroup" property="id" value="0" >
            <html:button property="_EDIT_FALSE"  onclick="if(checkSubmit(this.form)) {parent.SqueezeBox.presets.target=0;post('pgroup',anchor + ':_EDIT_FALSE'); }">
                    <bean:message key="action.update" bundle="<%=interfaces%>"/>
            </html:button>    
        </logic:notEqual>
    </TD>        
  </TR> 
  </table>
</body>
  </html:form>