<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<script language=javascript>
function navigator(pageIndex){    
    postAjax('list','divMainListEmp',anchor + ':_SHOW_LISTEMP:pageIndex:'+ pageIndex);
}
function navigatorList(pageIndexList){          
        postAjax('list','divMainListReport',anchor + ':_LIST_SHOW:pageIndexList:'+ pageIndexList);
}
function report(flag,code,id){  
    post('reportList',anchor + ':'+flag+':listCode:'+code+':listId:'+ id);remove('reportList',anchor);
}
</script>

<html:form action="disabilityReport" method="post" enctype="multipart/form-data" />
<html:form action="danSoHuyen" method="post"  >
<div><jsp:include page="/disability/danSoHuyen/form.jsp"/></div>
<div style="height:20px"></div>
<div align="center" id="listIdDanSoHuyen">
    <jsp:include page="/disability/danSoHuyen/list.jsp"/></div>  
</div>
</html:form>
 
