<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%! 
		int totalAddNum = 1;		//默认添加一个人
		
	%>
	
	<% 
			if( null != request.getParameter("totalAddNum")){
				totalAddNum = Integer.parseInt(request.getParameter("totalAddNum"));
			}
	%>
	
	<script type="text/javascript">
		function addNumChangeEvent(){
			//获取对应的索引值
			var select = document.getElementById("addNum");
			var selectIndex = select.selectedIndex;
			var str = select.options[selectIndex].value;
			
			//判断url中是否是包含get参数的，如果是，剔除这部分，仅仅保留原有的url
			var index = document.URL.indexOf("?", 0);
			var path = document.URL;
			
			if(-1!=index){
				path = path.substring(0, index);
			}

			//构建新的get参数，并跳转到对应的url
			path = path+"?totalAddNum="+str;
			window.location=path;

		}
		
		function messageNullAlert(totalAddNum){
			
			//判断name是否有为输入的
			for(var index=0;index<totalAddNum;index++){
				var id = "id_name_"+index.toString();
				var name= document.getElementById(id).value;
				if(name==""){
					alert("name can not be null.");
					return false;
				}				
			}
			
			//所有校验都通过，返回true
			return true;
		}
	</script>

	<center><h2><b>学生登记系统</b></h2></center>
	<hr>
	
	<%="请选择需要等级的人数：" %>
	<select id="addNum" style="width:50px;background-color: gray; " onchange="addNumChangeEvent()">
		<option value="1" <%=totalAddNum==1?"selected=\"selected\"":"" %>>1个</option>
		<option value="2" <%=totalAddNum==2?"selected=\"selected\"":"" %>>2个</option>
		<option value="3" <%=totalAddNum==3?"selected=\"selected\"":"" %>>3个</option>
		<option value="4" <%=totalAddNum==4?"selected=\"selected\"":"" %>>4个</option>
		<option value="5" <%=totalAddNum==5?"selected=\"selected\"":"" %>>5个</option>
	</select>
	
	<hr>
	
	<form action="/JSP_PROJ/servlet/CheckinDataBase" method="post" onsubmit="return messageNullAlert(<%=totalAddNum%>)">
	<table border="1">
	<% 
		for(int i=0;i<totalAddNum;i++){
			%>
			
			<tr bgcolor="<%=(i%2==1)?"gray":"white" %>">
				<td>姓名：<input type="text" name="name" id="id_name_<%=i %>" style="background-color: <%=(i%2==1)?"gray":"white" %>"></td>
				<!-- 
				<td>年龄：<input type="text" name="age" style="background-color: <%=(i%2==1)?"gray":"white" %>"></td>
				 -->
				<td>
					<%="年龄：" %>
					<select name="age" style="background-color: <%=(i%2==1)?"gray":"white" %>">
						<% 
						for(int age=1;age<=99;age++){
							%>
							<option value="<%=age%>"><%=age%></option>
							<%
						}
						%>
					</select>
				</td>
				<td>
					<%="性别：" %>
				 	<select name="sex" style="background-color: <%=(i%2==1)?"gray":"white" %>">
						<option value="男" selected="selected">男</option>
						<option value="女">女</option>
         			</select>
				</td>
				<td>工作：<input type="text" name="job" style="background-color: <%=(i%2==1)?"gray":"white" %>"></td>
				<td>生日：<input type="text" name="birthday" style="background-color: <%=(i%2==1)?"gray":"white" %>"></td>
			</tr>
			<%
		}
	%>
	</table>
	<br>
	<input type="submit" value="提交到数据库"><br>
	
	</form>
	<hr>
	<form action="/JSP_PROJ/servlet/QueryAllDB" method="post">
		<input type="submit" value="查询已等级信息">
	</form>
	
</body>
</html>