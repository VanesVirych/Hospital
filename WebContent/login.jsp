<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String error = (String) request.getAttribute("error");
	String title = (String) request.getAttribute("title");
	if (error != null) {
%>
<p class="text-error"><%=error%></p>
<%
	}
%>
<form action="login" method="post" class="form-horizontal">
<fieldset>
	<legend>Вход в админ-панель</legend>
	<div class="control-group">
		<label class="control-label" for="inputEmail">Логин</label>
		<div class="controls">
			<input name="username" class="input-medium" type="text"
				id="inputEmail" placeholder="Пароль">
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="inputPassword">Пароль</label>
		<div class="controls">
			<input name="password" class="input-medium" type="password"
				id="inputPassword" placeholder="Пароль">
		</div>
	</div>
	<div class="control-group">
		<div class="controls">
			<button type="submit" class="btn">Войти</button>
		</div>
	</div>
	</fieldset>
</form>