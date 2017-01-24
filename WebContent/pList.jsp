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
	<div class="span4 offset4">
<form action="patientlist" method="post" class="form-inline">
	<input name="action" type="hidden" value="showlist"> <input
		name="fio" type="text" class="input-medium" placeholder="Фамилия">
	<button type="submit" class="btn">Поиск</button>
</form>
</div>
<div class="span4">
<form action="patientlist" method="post" class="form-inline">
	<input name="action" type="hidden" value="showlist"> <input
		name="dor" id="inputDate" type="text" class="input-medium"
		placeholder="Дата поступления" data-date-format="mm/dd/yy">
	<button type="submit" class="btn">Поиск</button>
</form>
</div>
</div>
<table class="table table-bordered table-hover table-condensed">
	<thead>
		<tr>
			<th>#</th>
			<th>ФИО</th>
			<th>Возраст</th>
			<th>Дата поступления</th>
			<th>Диагноз</th>
			<th>Назначенное лечение</th>
			<th>Состояние</th>
			<th>Действия</th>
		</tr>
	</thead>
	<tbody>
		<%
			int count = 0;
			for (Patient patient : Patients) {
				count++;
		%>
		<tr>
			<td><%=patient.getPatientId()%></td>
			<td><%=patient.getFio()%></td>
			<td><%=patient.getAge()%></td>
			<td><%=patient.getDor()%></td>
			<td><%=patient.getDiagnosis()%></td>
			<td><%=patient.getTreatment()%></td>
			<td><%=patient.getState()%></td>
			<td><a
				href="?action=show&patientid=<%=patient.getPatientId()%>"
				class="btn btn-mini">Подробнее</a></td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>
<p class="pull-right">
	Количество пациентов: <strong><%=count%></strong>
</p>