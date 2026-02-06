<%@page contentType="text/html; charset=utf-8" %>
<%@page pageEncoding = "UTF-8" language="java"%>
<%
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="..\js\new1.js"></script>

<link href="..\css\PageNhaHang.css" rel="stylesheet" type="text/css" />
<title>Xin chào</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$("#datban").click(function() {
			$('html, body').animate({
				scrollTop : $("#form").offset().top
			}, 2000);
		});
	});
</script>
</head>
<body>
	<div id="menu">
		<ul>
			<li><a href="PageNhaHang.jsp">Trang chủ</a></li>
			<li><a href="../food.jsp">Thực đơn</a></li>
			<li><a id="datban" href="#">Đặt bàn</a></li>
			<li><a href="../promotion.jsp">Tin tức</a></li>			
		</ul>
	</div>
	<div id="pagebody">
		<img src="..\images\theuudai.jpg" style="height: 100%; width: 100%">
	</div>
	<div id="gioithieu" class="pagebody">
		<table style="width: 100%">
		<tr>
			<td> <img src="..\images\234_1456109310_96156ca76fe85bcc.jpg" style="width: 600px"></td>
			<td style="padding-left: 50px; text-align: center">Nhà hàng Cơm Việt Nam – Gia Viên 228 Bà Triệu
được thiết kế hiện đại, trang nhã và ấm cúng, với hơn 200 món ngon Hà Nội được chọn lọc từ các món truyền thống Việt Nam và những món ngon Châu Á, được bếp trưởng sáng tạo thành những món ăn "độc đáo riêng của Gia Viên", mang lại trải nghiệm ẩm thực "lạ" cho thực khách Việt</td>
		</tr>
		</table>
	</div>
	<div id="thucdon" class="pagebody">
		<table style="width: 100%">
		<tr>
			<td width="33%"> <img src="..\images\105_1456110385_84156ca7b316fb2b.jpg" style="width: 100%"></td>
			<td width="33%"> <img src="..\images\247_1456110263_56056ca7ab71e3a6.jpg" style="width: 100%"></td>
			<td width="33%"> <img src="..\images\423_1456110519_36456ca7bb7c3c06.jpg" style="width: 100%"></td>
		</tr>
		</table>
	</div>
	<div id="form">		
		<form name="ThongTinKhachHang" action="newcus.jsp">
			<table id="bang" style="padding-top: 100px; padding-left: 200px;">
			<tr>
				<td colspan="2"><input class="inputPageNhaHang" type="text" name="ten" style="width: 100%; color: blue" placeholder="Họ và tên" size="25%"></input></td>
				<td colspan="2"> <input class="inputPageNhaHang" type="text" name="email" style="width: 100%; color: blue" placeholder="Email" size="25%"></input></td>
				<td colspan="4" rowspan="3"> <input class="inputPageNhaHang" name="tinnhan" type="text" style="width: 100%; height: 100%; color: blue" placeholder="Tin nhắn" size="50%"></input></td>
			</tr>
			<tr>
				<td colspan="2"><input class="inputPageNhaHang" type="number" name="sdt" style="width: 100%; color: blue" placeholder="Điện thoại"></input></td>
				<td colspan="2"> <input class="inputPageNhaHang" type="number" name="soNguoi" style="width: 100%; color: blue"placeholder="Số khách"></input></td>				
			</tr>
			<tr>				
				<td colspan="1" bgcolor="white"><input type="radio" name="buoi" value="1">Trưa</input></td>
				<td colspan="1" bgcolor="white"><input type="radio" name="buoi" value="2">Tối</input></td>
				<td colspan="2"> <input type="date" name="ngay" style="width: 100%" placeholder="Ngày"></input></td>				
			</tr>
			
			
			
			
			
			
			<%
			String confirm = null;
			confirm = request.getParameter("confirm");
			if(confirm==null) confirm = "0";
			int x = Integer.parseInt(confirm);
			if (x==3){
			%>
			<tr>
				<td colspan = "4" style = "color: red"><b>Chúng tôi chưa thể đáp ứng yêu cầu của bạn. Vui lòng nhập lại thông tin đặt hàng</b></td>
				<td colspan = "4" style = "color: red"><b>Nếu bạn cảm thấy khó khăn trong việc đặt bàn, xin vui lòng gọi vào số 01643830937</b></td>  
			</tr>
			<%} %>
			<%
			if (x==2){
			%>
			<tr>
				<td colspan = "4" style = "color: red"><b>Xin vui lòng nhập đầy đủ thông tin</b></td>
				<td colspan = "4" style = "color: red"><b>Nếu bạn cảm thấy khó khăn trong việc đặt bàn, xin vui lòng gọi vào số 01643830937</b></td>  
			</tr>
			<%} %>
			<%
			if (x==4){
			%>
			<tr>
				<td colspan = "4" style = "color: red"><b>Bạn đã nhập sai mã khác hàng</b></td>
				<td colspan = "4" style = "color: red"><b>Nếu bạn cảm thấy khó khăn trong việc đặt bàn, xin vui lòng gọi vào số 01643830937</b></td>  
			</tr>
			<%} %>
			<tr>
				<td><input type="submit" value="Đặt"/></td>
				<td><input type="reset" value="Nhập lại"/></td>
			</tr>
			<tr>
				<td colspan = "2" style = "color: red"><b>Nếu bạn đã đặt trước và muốn thay đổi đơn đặt hàng, vui lòng nhập mã khách hàng</b></td>
				<td colspan = "4" style = "color: red"><input type="number" name="maKhachHang"/></td>
				<td colspan = "2"><input type = "submit" value = "Nhập mã khách hàng"></td>
			</tr>
			</table>
		</form>
	</div>
</body>
</html>
