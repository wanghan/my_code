<%@page import="rmi.AssociateResult"%>
<%@ page language="java"
	import="java.util.*,hibernate.*,AUDRwebJavaBeans.*,java.io.*"
	pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>Associate Results</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="<%=path %>/css/bodyCss.css" rel="stylesheet"
			type="text/css" />
		<link href="<%=path %>/css/hyperlink.css" rel="stylesheet"
			type="text/css" />
		<link href="<%=path %>/css/inner.css" rel="stylesheet" type="text/css" />
		<link href="<%=path %>/css/person_info.css" rel="stylesheet"
			type="text/css" />
		<link href="<%=path %>/css/viewpub.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript" src="<%=path %>/js/image_utils.js"></script>
	</head>
	<style type="text/css">
.showDeck {
	display: block;
	top: 0px;
	left: 0px;
	margin: 0px;
	padding: 0px;
	width: 100%;
	height: 100%;
	position: absolute;
	z-index: 3;
	background: #000000;
}
</style>

<% 
List<TopicAssociate> item = (List<TopicAssociate>)request.getAttribute("TopicAssociates");
%>
	<body>
		<div id="main2">
			<div style="border: solid 1px #6fbee7; margin-bottom: 20px;" width="100%">
			<table><tbody>
					<%for(int i=0;i<item.size();++i){%>
					<tr>
								<table width="100%">
									<tbody>
										<tr style="padding: 0; margin: 5px; height: 13px;">
											<td valign="top" colspan="2"
												style="border: solid #e0e0a0 1px; padding: 1px; margin: 1px; text-align: center;"
												bgColor="#909090">
												<div>
													<b><%=item.get(i).getTitle() %></b>
												</div>
											</td>
										</tr>
										<tr>
											<table width="100%">
												<tbody>
													<tr>
														<%for(int k=0;k<8;++k){
														DbAuthor ttm=item.get(i).getTopAuthors().get(k);
														%>

														<td width="12%">
															<%
															String imagepath="/profile/no_image.jpg";
															String newPath="/profile/"+String.valueOf(ttm.getId())+".jpg";
															String realPath="H:/author_profile/"+String.valueOf(ttm.getId())+".jpg";
															if(new File(realPath).exists()){
																imagepath=newPath;
															} 
															 %>
															<img border="0" width="80" height="90"
																src="<%=imagepath %>" onload="resizeimg(this, 80,90);"
																style="display: block;">
														</td>

														<%} %>
													</tr>
													<tr>
														<%for(int k=0;k<8;++k){
															DbAuthor ttm=item.get(i).getTopAuthors().get(k);
														%>
														<td width="12%">
															<%
															String imagepath="/profile/no_image.jpg";
															String newPath="/profile/"+String.valueOf(ttm.getId())+".jpg";
															String realPath="H:/author_profile/"+String.valueOf(ttm.getId())+".jpg";
															if(new File(realPath).exists()){
																imagepath=newPath;
															} 
															 %>
															<a class="url"
																href="<%=path %>/authorItemShow?fid=<%=ttm.getId()%>"><%=ttm.getName()%></a>

														</td>

														<%} %>
													</tr>
												</tbody>
											</table>
										</tr>
									</tbody>
								</table>
					</tr>
					<%} %>
				</tbody></table>
			</div>
		</div>
	</body>
</html>
