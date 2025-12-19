<%@page contentType="text/html; charset=utf-8" %>
<%@page pageEncoding = "UTF-8" language="java"%>
<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="..\css\PageNhaHang.css" rel="stylesheet" type="text/css" />

<title>Insert title here</title>
<%
String id = request.getParameter("id");
%>
</head>
<body>
	<div id="menu">
		<ul>
			<li><a href="PageNhaHang.jsp">Trang chủ</a></li>
			<li><a href="../food.jsp">Thực đơn</a></li>
			<li><a href="dattruoc.jsp">Đặt bàn</a></li>
			<li><a href="../promotion.jsp">Tin tức</a></li>
			<li><a href="#">Đăng nhập</a></li>
		</ul>
	</div>
	<div style="width: 300px; margin-top: 50px;">
		<div align="center">
			<p style="font-size: 20px;">Mã khách hàng của quý khách là <%=id %>, quý khách có thể sử dụng mã này để thay đổi món ăn mình đã đặt</p>
			<p style="font-size: 20px;">Xin vui lòng chuyển khoản 100000 đặt cọc vào tài khoản 1234567891011<br/>
			cho đơn hàng của quý khách</p>
			<br/>
			<p style="font-size: 20px;">Liên hệ vào số 12345678910 để biết thêm chi tiết</p>
			<br/>
			<a href="PageNhaHang.jsp">Click vào đây để quay lại trang chủ</a>
		</div>
	</div>
</body>
</html>