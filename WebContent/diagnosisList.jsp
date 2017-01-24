<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="mypackage.model.Diagnosis"%>
<%@page import="java.util.List"%>
<%
	String title = (String) request.getAttribute("title");
%>
<div class="row">
	<div class="span7 offset1">
		<h1><%=title%></h1>
	</div>
</div>
<br />
<div class="row">
	<div class="span12">
		<a href="?action=add" class="btn btn-primary pull-right">Добавить диагноз</a>
	</div>
</div>
<br />
<table class="table table-bordered table-hover table-condensed">
	<thead>
		<tr>
			<th>#</th>
			<th>Диагноз</th>
			<th>Назначенное лечение</th>
			<th>Кол-во больных с данным диагнозом</th>
			<th colspan="2">Действия</th>
		</tr>
	</thead>
	<tbody>
		<%
			List<Diagnosis> diagnoses = (ArrayList<Diagnosis>) request
					.getAttribute("diagnoses");
		%>
		<%
			for (Diagnosis diagnosis : diagnoses) {
		%>
		<tr>
			<td><%=diagnosis.getDiagnosisId()%></td>
			<td><%=diagnosis.getDiagnosis()%></td>
			<td><%=diagnosis.getTreatment()%></td>
			<td><%=diagnosis.getCountPatients()%></td>
			<td><a href="?action=edit&diagnosisid=<%=diagnosis.getDiagnosisId()%>" class="btn btn-mini btn-primary">Изменить</a></td>
			<td><a href="?action=delete&diagnosisid=<%=diagnosis.getDiagnosisId()%>" class="btn btn-mini btn-primary">Удалить</a></td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>