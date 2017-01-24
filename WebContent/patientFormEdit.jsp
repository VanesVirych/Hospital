<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="mypackage.model.Diagnosis"%>
<%@page import="java.util.List"%>
<%@page import="mypackage.model.Patient"%>
<%
	Patient patient = (Patient) request.getAttribute("patient");
	String selected = "selected=\"selected\"";
%>
<form action="patientcontroller" method="post" class="form-horizontal">
	<fieldset>
		<legend>Добавление пациента</legend>

		<input type="hidden" name="patientid"
			value="<%=patient.getPatientId()%>" />
		<div class="control-group">
			<label class="control-label" for="inputName">ФИО</label>
			<div class="controls">
				<input type="text" name="fio" value="<%=patient.getFio()%>"
					id="inputName" placeholder="">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="inputAge">Возраст</label>
			<div class="controls">
				<input type="text" name="age" value="<%=patient.getAge()%>"
					id="inputAge" placeholder="">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="selectDiagnosisId">Диагноз</label>
			<div class="controls">
				<select name="diagnosisid" id="selectDiagnosisId">
					<option>Выберите диагноз</option>
					<%
						List<Diagnosis> diagnoses = (ArrayList<Diagnosis>) request
								.getAttribute("diagnoses");
						for (Diagnosis diagnosis : diagnoses) {
							if (patient.getDiagnosisId() == diagnosis.getDiagnosisId()) {
					%>
					<option value="<%=diagnosis.getDiagnosisId()%>"
						selected="selected"><%=diagnosis.getDiagnosis()%></option>
					<%
							} else {
					%>
					<option value="<%=diagnosis.getDiagnosisId()%>"><%=diagnosis.getDiagnosis()%></option>
					<%
							}
					%>
					<%
						}
					%>
				</select> <a href="#" id="add" class="btn btn-link btn-mini">Новый
					диагноз</a>
			</div>
		</div>
		<div id="newDiv"></div>

		<div class="control-group">
			<label class="control-label" for="inputDor">Дата поступления</label>
			<div class="controls">
				<input type="text" name="dor" value="<%=patient.getDor()%>"
					id="inputDate" placeholder="" data-date-format="mm/dd/yy">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="selectState">Состояние</label>
			<div class="controls">
				<select name="state" id="selectState">
					<option value="1"
						<%if (patient.getState().equals("Удовлетворительное состояние")) {%>
						<%=selected%> <%}%>>Удовлетворительное состояние</option>
					<option value="2"
						<%if (patient.getState().equals("Состояние средней тяжести")) {%>
						<%=selected%> <%}%>>Состояние средней тяжести</option>
					<option value="3"
						<%if (patient.getState().equals("Тяжелое состояние")) {%>
						<%=selected%> <%}%>>Тяжелое состояние</option>
					<option value="4"
						<%if (patient.getState().equals("Состояние крайне тяжелое")) {%>
						<%=selected%> <%}%>>Состояние крайне тяжелое</option>
					<option value="5"
						<%if (patient.getState().equals("Терминальное состояние")) {%>
						<%=selected%> <%}%>>Терминальное состояние</option>
				</select>
			</div>
		</div>

		<div class="control-group">
			<div class="controls">
				<button type="submit" class="btn">Добавить</button>
			</div>
		</div>
	</fieldset>
</form>