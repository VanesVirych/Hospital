<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="mypackage.model.Diagnosis"%>
<form action="patientcontroller" method="post" class="form-horizontal">
	<fieldset>
		<legend>Добавление пациента</legend>
		<div class="control-group">
			<label class="control-label" for="inputName">ФИО</label>
			<div class="controls">
				<input type="text" name="fio" id="inputName" placeholder="">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="inputAge">Возраст</label>
			<div class="controls">
				<input type="text" name="age" id="inputAge" placeholder="">
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
					%>
					<option value="<%=diagnosis.getDiagnosisId()%>"><%=diagnosis.getDiagnosis()%></option>
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
				<input type="text" name="dor" id="inputDate" placeholder=""
					data-date-format="mm/dd/yy">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="selectState">Состояние</label>
			<div class="controls">
				<select name="state" id="selectState">
					<option value="1">Удовлетворительное состояние</option>
					<option value="2">Состояние средней тяжести</option>
					<option value="3">Тяжелое состояние</option>
					<option value="4">Состояние крайне тяжелое</option>
					<option value="5">Терминальное состояние</option>
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