<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'form.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    	配置参数：<%=request.getParameter("row") %>行，<%=request.getParameter("column") %>列<br>
    	path<%=path %><br>
    	basepath<%=basePath %><br>
    	exception<%=1/0 %>
    	
    	<table border="1" width="100%" height="100%">
	    	<% 
	    		int row = Integer.parseInt(request.getParameter("row"));
	    		int column = Integer.parseInt(request.getParameter("column"));
	    	
	    		for(int i=0;i<row;i++){
	    			%>
	    				<tr>
	    	    	<%		
	    			for(int j=0;j<column;j++){
	    	%>
	    			<td>
	    				<%=i+1 %>行<%=j+1 %>列
	    			</td>
	    	<%
	    			}
	    	    	%>
	    	    		</tr>
	    	    	<%
	    		}
	    	%>
    	</table>
  </body>
</html>
