<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String title = (String) request.getAttribute("title");
	Patient patient = (Patient) request.getAttribute("patient");
%>
<%@page import="mypackage.model.Patient"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<div class="row">
	<div class="span7 offset1">
		<h1><%=title%></h1>
	</div>
</div>
<br />
<dl class="dl-horizontal">
	<dt>#</dt>
	<dd><%=patient.getPatientId()%></dd>
	<dt>ФИО</dt>
	<dd><%=patient.getFio()%></dd>
	<dt>Возраст</dt>
	<dd><%=patient.getAge()%></dd>
	<dt>Дата поступления</dt>
	<dd><%=patient.getDor()%></dd>
	<dt>Диагноз</dt>
	<dd><%=patient.getDiagnosis()%></dd>
	<dt>Назначенное лечение</dt>
	<dd><%=patient.getTreatment()%></dd>
	<dt>Состояние</dt>
	<dd><%=patient.getState()%></dd>
</dl>