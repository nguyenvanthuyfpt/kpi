<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
  <tiles:insert page="/layout/layoutPopup.jsp" flush="true">     
      <tiles:put name="content" value="/cabin/editForm.jsp" />
  </tiles:insert>