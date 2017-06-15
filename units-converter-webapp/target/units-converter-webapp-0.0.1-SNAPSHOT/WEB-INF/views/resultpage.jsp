<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>This is your result</title>
</head>
<body>
	<p>For now this is where conversion result will be shown</p>
	<p>You have input temperature in Fahrenheit: ${inputTemperature}</p>
	<p>This temperature in Celsius is: ${conversionResult}</p>

	<a href="<c:url value="converter" />" >Back to input page</a>
	
	<p>Those are some previous calculated temperatures (only first now)</p>
	<p>${previousValues}</p>
</body>
</html>