<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="mypackage.model.Diagnosis"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%
	String title = (String) request.getAttribute("title");
	List<Diagnosis> diagnoses = (ArrayList<Diagnosis>) request
			.getAttribute("diagnoses");
%>
<div class="row">
	<div class="span7 offset1">
		<h1><%=title%></h1>
	</div>
</div>
<br />
<ul>
	<%
		for (Diagnosis diagnosis : diagnoses) {
	%>
	<li><a
		href="?action=show&diagnosisid=<%=diagnosis.getDiagnosisId()%>"><%=diagnosis.getDiagnosis()%></a>
	</li>
	<%
		}
	%>
</ul>
