<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Simple temperature converter</title>
</head>
<body>
	<p>Simple temperature converter</p>
	<p>Type in the temperature in Fahrenheit</p>
	<form:form method="POST" modelAttribute="unitConverterForm">
		<form:input type="text" path="temperatureF"/>
	</form:form>
</body>
</html>