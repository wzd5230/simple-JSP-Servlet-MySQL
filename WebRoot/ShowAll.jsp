<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.wzd.pkt.*" %>
<%@ page import="java.util.ListIterator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		ArrayList<Person> personList = (ArrayList<Person>)request.getAttribute("ALL_LIST"); 
		
		if( personList!=null ){
	%>
			<h3 align="center"><b>获取到数据</b></h3>
	<%
			ListIterator<Person> listIterator =  personList.listIterator();
		
	%>
		<table border="1" width="50%" align="center">
		<tr>
			<td align="center">ID</td>
			<td align="center">姓名</td>
			<td align="center">年龄</td>
			<td align="center">性别</td>
			<td align="center">工作</td>
			<td align="center">生日</td>
		</tr>
	<%
			while(listIterator.hasNext()){
	%>
				<tr>
	<%
				Person person = listIterator.next();
	%>
	
				<td align="center"><%=person.getId() %></td>
				<td align="center"><%=person.getName() %></td>					 				
				<td align="center"><%=person.getAge() %></td>					 				
				<td align="center"><%=person.getSex() %></td>					 				
				<td align="center"><%=person.getJob() %></td>					 									 				
				<td align="center"><%=person.getBirthday() %></td>					 									 				
	
				</tr>
	<%						
			}
	%>
		</table>
	<%
		} else {
	%>
			<h3 align="center"><b>没有获取到数据</b></h3>
	<%
		}
	%>


</body>
</html>