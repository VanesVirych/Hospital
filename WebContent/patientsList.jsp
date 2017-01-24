<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="mypackage.model.Patient"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%
	String title = (String) request.getAttribute("title");
	List<Patient> Patients = (ArrayList<Patient>) request
			.getAttribute("patients");
%>
<div class="row">
	<div class="span7 offset1">
		<h1><%=title%></h1>
	</div>
</div>
<br />
<div class="row">
	<div class="span12">
<a href="?action=add" class="btn btn-primary pull-right">Добавить пациента</a>
</div>
</div>
<br />
<table class="table table-bordered table-hover table-condensed">
	<thead>
		<tr>
			<th>#</th>
			<th>ФИО</th>
			<th>Возраст</th>
			<th>Дата поступления</th>
			<th>Диагноз</th>
			<th>Состояние</th>
			<th colspan="2">Действия</th>
		</tr>
	</thead>
	<tbody>
		<%
			for (Patient patient : Patients) {
		%>
		<tr>
			<td><%=patient.getPatientId()%></td>
			<td><%=patient.getFio()%></td>
			<td><%=patient.getAge()%></td>
			<td><%=patient.getDor()%></td>
			<td><%=patient.getDiagnosis()%></td>
			<td><%=patient.getState()%></td>
			<td><a
				href="?action=edit&patientid=<%=patient.getPatientId()%>"
				class="btn btn-mini btn-primary">Изменить</a></td>
			<td><a
				href="?action=delete&patientid=<%=patient.getPatientId()%>"
				class="btn btn-mini btn-primary">Удалить</a></td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>