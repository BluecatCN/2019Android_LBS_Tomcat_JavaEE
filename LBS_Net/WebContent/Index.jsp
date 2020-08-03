<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<h1>First Servlet demo! Vafanculoooooo!</h1>

<hr>
<a href="com.servlet.demo/LogUpload">Get mode to request HelloServlet</a><!-- 超链接访问就是get请求的一种 -->
<form action="com.servlet.demo/LogUpload" method="post">  <!-- .demo/后面的名字一定要和src里的java文件一样 -->
<input type="submit" value="Post mode to request HelloServlet"/>
</form>
</body>
</html>