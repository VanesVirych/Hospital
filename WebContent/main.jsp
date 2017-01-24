<%@page import="mypackage.model.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String title = (String) request.getAttribute("title");
	String adminMenu = (String) request.getAttribute("menu");
	String content = (String) request.getAttribute("content");
%>
<!DOCTYPE html>
<html lang="ru">
<head>
<meta charset="utf-8">
<title><%=title%></title>
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/datepicker.css"
	rel="stylesheet" type="text/css" />
<style>
body {padding-top: 60px;background-image: url(<%=request.getContextPath()%>/img/subtle_dots.png) !important;}
.brand {margin-left: 50px !important;}
.navbar-text {
margin-right: 50px;}
th {font-size: 13px !important;}
td {font-size: 12px !important;}
</style>
</head>
<body>
	<div class="container">
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-inner">
				<a class="brand" href="<%=request.getContextPath() %>">Больничная палата</a>
				<%if (adminMenu != null) {%>
				<jsp:include page="<%=adminMenu%>" />
				<%} else {%>
				<jsp:include page="menu.jsp" />
				<%}%>
				
				
				<%
				User userInfo = (User)request.getSession().getAttribute("userInfo");
				if(userInfo== null) {
				%>
				<p class="navbar-text pull-right">Гость | <a href="<%=request.getContextPath() %>/login">Войти</a></p>
				<%} else {%>
				<p class="navbar-text pull-right"><a href="<%=request.getContextPath() %>/admin"><%=userInfo.getUserName() %></a> | <a href="<%=request.getContextPath() %>/logout">Выйти</a></p>
				<%} %>
			</div>
		</div>
		<div class="row">
			<div class="span12">
				<jsp:include page="<%=content%>" />
			</div>
		</div>

	</div>
	<script src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap-datepicker.js"></script>
	<script>
		$(function() {
			$('#inputDate').datepicker();
			$('#add')
					.click(
							function() {
								$("#newDiv").empty();
								$("#selectDiagnosisId").attr("disabled",
										"disabled");
								$(
										'<div class="control-group"><label class="control-label" for="inputName">Диагноз</label>'
												+ '<div class="controls"><input type="text" name="diagnosis" id="inputName" placeholder="">'
												+ '</div></div><div class="control-group"><label class="control-label" for="inputAge">'
												+ 'Лечение</label><div class="controls">'
												+ '<textarea rows="3" name="treatment" id="inputTreatment"></textarea></div></div>')
										.fadeIn('slow').appendTo('#newDiv');
							});
		});
	</script>
</body>
</html>