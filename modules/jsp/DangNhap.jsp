<%@page contentType="text/html; charset=utf-8"%>
<%@page pageEncoding="UTF-8" language="java"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<link href="..\css\PageNhaHang.css" rel="stylesheet" type="text/css" />
<title>Xin chào</title>
<body>
	<div id="menu">
		<ul>
			<li><a href="PageNhaHang.jsp">Trang chủ</a></li>
			<li><a href="food.jsp">Thực đơn</a></li>
			<li><a href="#">Đặt bàn</a></li>
			<li><a href="promotion.jsp">Tin tức</a></li>
			<li><a href="#">Đăng nhập</a></li>
		</ul>
	</div>
	<div id="dangnhap">
		
		<span id="logo">
			<img src="..\images\zfYvmRWJSyz.jpg" >
		</span>
		<span id="thongtindangnhap">
		<form id="thonhgtindangky">
			<p style="font-size: 40"><b>Đăng ký</b></p>
			<input class="inputword" type="text" placeholder="Họ và tên" ></input>
			<input class="inputword" type="number" placeholder="Số điện thoại"style="margin-top: 20px"></input>
			<input class="inputword" type="text" placeholder="Địa chỉ email" style="margin-top: 20px"></input>
			<input class="inputword" type="password" placeholder="Mật khẩu" style="margin-top: 20px"></input>
			<p style="font-size: 20"><b>Ngày sinh</b></p>
			<select name="Ngày sinh">
				<option value="Ngày">Ngày</option>	
				<option value="1">1</option><option value="2">2</option><option value="3">3</option>
				<option value="4">4</option><option value="5">5</option><option value="6">6</option>
				<option value="7">7</option><option value="8">8</option><option value="9">9</option>
				<option value="10">10</option><option value="11">11</option><option value="12">12</option>
				<option value="13">13</option><option value="14">14</option><option value="15">15</option>
				<option value="16">16</option><option value="17">17</option><option value="18">18</option>
				<option value="19">19</option><option value="20">20</option><option value="21">21</option>
				<option value="22">22</option><option value="23">23</option><option value="24">24</option>
				<option value="25">25</option><option value="26">26</option><option value="27">27</option>
				<option value="28">28</option><option value="29">29</option><option value="30">30</option><option value="31">31</option>
			</select>
			<select name="Tháng">
				<option value="Tháng">Tháng</option>	
				<option value="1">1</option><option value="2">2</option><option value="3">3</option>
				<option value="4">4</option><option value="5">5</option><option value="6">6</option>
				<option value="7">7</option><option value="8">8</option><option value="9">9</option>
				<option value="10">10</option><option value="11">11</option><option value="12">12</option>				
			</select>
			<select name="Năm">
				<option value="Năm">Năm</option>	
				<option value="1905">1905</option><option value="1906">1906</option>					
			</select>
			<br/>
			<input class="radiocheck" type="radio" value="admin" name="quyen">admin</input>
			<input class="radiocheck" type="radio" value="customer" name="quyen">customer</input>
			<br/>
			<input type="submit" value="Đăng ký" style="margin-top: 20px"></input>
		</form>
		</span>
	</div>
</body>
</html>