<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<div id="left">    
     <div class="ctn-left">
        <div class="title clearfix"><img src="<%=contextPath%>/images/newImages/i_15.gif" hspace="7" align="left" /><div class="topic"><bean:message key="menu.top.doc.caption" bundle="<%=interfaces%>"/></div></div>                                                    
            <div class="leftEditList">                                        
                       
                     <a class="modal-button" href="docsrecv<%=extention%>?<%=anchor%>=_CREATE_DOSS_RECV" rel="{handler: 'iframe', size: {x: 370, y: 260},bookmark:'postAjax(\'dossiers\',\'idDossiers\',anchor + \':_SAVE_NEW_RECV\')'}">   
                        <bean:message key="form.docs.dossierId.add" bundle="<%=interfaces%>"/>
                    </a>
            </div>  
            <div align="center"><hr /></div>   
            <div class="leftEditList">                                                            
                      <a class="modal-button" href="docsrecv<%=extention%>?<%=anchor%>=_CREATE_FROM" rel="{handler: 'iframe', size: {x: 370, y: 270},bookmark:'postAjax(\'from\',\'iddonvi\',anchor + \':_SAVE_NEW_RECV\')'}">
                        <bean:message key="form.docs.fromId.add" bundle="<%=interfaces%>"/> 
                    </a>  
            </div>  
    </div>    
</div>      