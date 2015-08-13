<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>


<style>
      
      label {width:70px; display:inline-block;}
      form {line-height: 160%; }
     
    </style>
</head>
<body>

<form:form method="post" action="insertuser.htm" commandName="user" >

<table border="2">
<tr>
<td><form:label path="username">User Name </form:label></td>
<td><form:input  path="username"/>  </td>
</tr>

<tr>
<td><form:label path="password">Password </form:label></td>
<td><form:input path="password"/>  </td>
</tr>

<tr>
<td><form:label path="name">Name </form:label></td>
<td><form:input path="name"/>  </td>
</tr>

<tr>
<td><form:label path="email">Email </form:label></td>
<td><form:input path="email"/>  </td>
</tr>

<tr>
<td><form:label path="phone">Phone</form:label></td>
<td><form:input path="phone"/>  </td>
</tr>


<tr>
<td colspan="2">
<input type="submit" value="Submit"/></td>

</tr>

</table>

</form:form>

</body>
</html>