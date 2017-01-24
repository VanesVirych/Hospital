<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="mypackage.model.Diagnosis"%>
<%
	Diagnosis diagnosis = (Diagnosis) request.getAttribute("diagnosis");
%>
<form action="diagnosiscontroller" method="POST" class="form-horizontal">
	<fieldset>
		<legend>Редактирование диагноза</legend>
		<input type="hidden" name="diagnosisid"
			value="<%=diagnosis.getDiagnosisId()%>" />
		<div class="control-group">
			<label class="control-label" for="inputName">Диагноз</label>
			<div class="controls">
				<input type="text" name="diagnosis"
					value="<%=diagnosis.getDiagnosis()%>" id="inputName"
					placeholder="">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputTreatment">Лечение</label>
			<div class="controls">
				<textarea rows="3" name="treatment" id="inputTreatment"><%=diagnosis.getTreatment()%></textarea>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<button type="submit" class="btn">Добавить</button>
			</div>
		</div>
</form>