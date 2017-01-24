<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="mypackage.model.Diagnosis"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%
	String title = (String) request.getAttribute("title");
	Diagnosis diagnosis = (Diagnosis) request.getAttribute("diagnosis");
%>
<div class="row">
	<div class="span7 offset1">
		<h1><%=title%></h1>
	</div>
</div>
<br />
<dl class="dl-horizontal">
	<dt>Диагноз:</dt>
	<dd><%=diagnosis.getDiagnosis()%></dd>
	<dt>Лечение:</dt>
	<dd><%=diagnosis.getTreatment()%></dd>
</dl>
