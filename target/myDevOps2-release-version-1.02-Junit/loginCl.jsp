<%@ page language="java" import="java.util.*,java.sql.*,com.sp.model.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'loginCl.jsp' starting page</title>
    
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
  <%
    request.setCharacterEncoding("gbk");//将username和密码重新编码
    String uname=request.getParameter("username");
    String upasswd=request.getParameter("passwd");
    UserBeanCl ubc=new UserBeanCl();
    if(ubc.checkUser(uname,upasswd)){
      response.sendRedirect("wel.jsp?una="+uname);
    }else{
    	 
    	 response.sendRedirect("login.jsp");
    }   
   %>
  </body>
</html>
