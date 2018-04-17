<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!--    c标签要使用,那么就必须要有它 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set scope="page" var="url"
	value="${pageContext.request.contextPath }"></c:set>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>新增用户</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page"> 
 

</head>

<body>
	<div align="center"
		style="width: 400px; position: relative;left:450px" color="red">
		<form action="${url}/fan/Servlet_TbUser?who=Insert" method="post">
			<h4>新增用户</h4>
			姓名: <input type="text" name="name"><br />
			 密码: <input	type="text" name="password"><br /> 
			 出生日期 : <input type="text" name="birthday"><br /> 
			 性别: <select name="sex">
				<option value="0">男</option>
				<option value="1">女</option>
			</select><br /> 
		    <input type="submit" value="新增"/>
			<hr />
		</form>
	</div> 
	<div align="center"style="width: 400px; position: relative;left:450px;">
		<form action="${url}/fan/Servlet_TbUser?who=queryAll" method="post">
			 <input type="submit" value="查询所有的数据"/> <br/>
			<table border="1"  cellspacing="0"> 
		         <thead>
		          <tr><td>ID</td><td>姓名</td><td>密码</td><td>日期</td><td>性别</td><td>操作</td></tr>
		         </thead>
				<tbody>
         <c:forEach items="${list}" var="list">
         <tr>
                <td>${list.id }</td>
                <td>${list.name }</td>
                <td>${list.password }</td>
                <td>${list.birthday }</td> 
                <td><c:if test="${list.sex==false }">男</c:if>
                <c:if test="${list.sex==true }">女</c:if></td>
                 <td><a  href= "${url}/fan/Servlet_TbUser?who=queryById&id=${list.id}"     style='text-decoration:none'    onclick='update(this)'   >修改&nbsp;</a>
				    <a    href= "${url}/fan/Servlet_TbUser?who=delete&id=${list.id}"   style='text-decoration:none'     >删除</a>  </td>
         </tr>
         </c:forEach>
		         </tbody>
			</table>
			<hr />
		</form>
	</div> 
	<div align="center"
		style="width: 400px; position: relative;left:450px">
		<form action="${url}/fan/Servlet_TbUser?who=update" method="post">
			<h4>修改用户</h4>
			<input type="hidden"name="id"  value="${user.id }"/>
			姓名: <input type="text" name="name" value="${user.name }"><br />
			 密码: <input	type="text" name="password" value="${user.password }"><br /> 
			 出生日期 : <input type="text" name="birthday" value="${user.birthday }"><br />  
	     	 性别:<c:if test="${user.sex==false }">
                  <select name="sex" >
                <option value="0">男</option>
                <option value="1">女</option>
               </select> 
                  </c:if>
              <c:if test="${user.sex==true }">
                  <select name="sex" >
                  <option value="1">女</option>
                <option value="0">男</option> 
              </select>
                  </c:if><br /> 
		    <input type="submit" value="保存修改"/>
			<hr />
		</form>
	</div>
</body>
</html>
