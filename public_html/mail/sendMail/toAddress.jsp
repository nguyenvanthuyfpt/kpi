<%@ include file="/commons/tags.jsp" %>
<%@ include file="/commons/params.jsp" %>
<style type="text/css">
	#mainContainer{
		width:660px;
		margin:0 auto;
		text-align:left;
		height:100%;
		background-color:#FFF;
		border-left:3px double #000;
		border-right:3px double #000;
	}
	#formContent{
		padding:5px;
	}
	#doc_from{
		position:absolute;	/* Never change this one */
		width:300px;	/* Width of box */
		height:120px;	/* Height of box */
		overflow:auto;	/* Scrolling features */
		border:1px solid #317082;	/* Dark green border */
		background-color:#FFF;	/* White background color */
		text-align:left;
		font-size:0.9em;
		z-index:100;
	}
	#doc_from div{	/* General rule for both .optionDiv and .optionDivSelected */
		margin:1px;		
		padding:1px;
		cursor:pointer;
		font-size:0.9em;
	}
	#doc_from .optionDiv{	/* Div for each item in list */
		
	}
	#doc_from .optionDivSelected{ /* Selected item in the list */
		background-color:#317082;
		color:#FFF;
	}
	#doc_from_iframe{
		background-color:#F00;
		position:absolute;
		z-index:5;
	}
	form{
		display:inline;
	}
</style>
        
        <html:text name="sendMail" property="toAddress" styleId="toAddress" onclick="getObj('tem').value=0"  onkeyup="searchEmail(this,'2','formMyContact','Anchor=_SEARCH_PAGE',event,'doc_from')" style="width:95%" />